package com.sparksys.activiti.domain.service.model;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sparksys.activiti.application.service.process.IProcessDetailService;
import com.sparksys.activiti.application.service.model.IModelService;
import com.sparksys.activiti.infrastructure.entity.ProcessDetail;
import com.sparksys.core.support.SparkSysExceptionAssert;
import lombok.extern.slf4j.Slf4j;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class)
public class ModelServiceImpl implements IModelService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IProcessDetailService processDetailService;

    @Override
    public ObjectNode getEditorJson(String modelId) {
        ObjectNode modelNode = null;
        Model model = repositoryService.getModel(modelId);
        if (model != null) {
            try {
                if (StringUtils.isNotEmpty(model.getMetaInfo())) {
                    modelNode = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
                } else {
                    modelNode = objectMapper.createObjectNode();
                    modelNode.put(ModelDataJsonConstants.MODEL_NAME, model.getName());
                }
                modelNode.put(ModelDataJsonConstants.MODEL_ID, model.getId());
                ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(
                        new String(repositoryService.getModelEditorSource(model.getId()), StandardCharsets.UTF_8));
                modelNode.put("model", editorJsonNode);
            } catch (Exception e) {
                log.error("Error creating model JSON {}", e.getMessage());
                SparkSysExceptionAssert.businessFail("Error creating model JSON");
            }
        }
        return modelNode;
    }

    @Override
    public boolean saveModel(String modelId, String name, String description, String json_xml, String svg_xml) {
        try {
            Model model = repositoryService.getModel(modelId);
            ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
            modelJson.put(ModelDataJsonConstants.MODEL_NAME, name);
            modelJson.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            model.setMetaInfo(modelJson.toString());
            model.setName(name);
            repositoryService.saveModel(model);
            saveProcessDetail(modelId, name, json_xml);
            repositoryService.addModelEditorSource(model.getId(), json_xml.getBytes(StandardCharsets.UTF_8));
            InputStream svgStream = new ByteArrayInputStream(svg_xml.getBytes(StandardCharsets.UTF_8));
            TranscoderInput input = new TranscoderInput(svgStream);
            PNGTranscoder transcoder = new PNGTranscoder();
            // Setup output
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            TranscoderOutput output = new TranscoderOutput(outStream);
            // Do the transformation
            transcoder.transcode(input, output);
            final byte[] result = outStream.toByteArray();
            repositoryService.addModelEditorSourceExtra(model.getId(), result);
            outStream.close();
        } catch (Exception e) {
            log.error("Error saving model", e);
            SparkSysExceptionAssert.businessFail("Error saving model");
        }
        return true;
    }

    private void saveProcessDetail(String modelId, String name, String json_xml) {
        JSONObject jsonObject = JSONObject.parseObject(json_xml);
        JSONArray jsonNodes = jsonObject.getJSONArray("childShapes");
        JSONObject propertiesNode = jsonObject.getJSONObject("properties");
        String processId = propertiesNode.getString("process_id");
        List<ProcessDetail> processDetails = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(jsonNodes)) {
            processDetailService.remove(new QueryWrapper<ProcessDetail>().lambda().eq(ProcessDetail::getModelId, modelId));
            for (Object object : jsonNodes) {
                JSONObject jsonNode = (JSONObject) object;
                JSONObject stencilNode = jsonNode.getJSONObject("stencil");
                String type = stencilNode.getString("id");
                if ("SequenceFlow".equals(type)) {
                    continue;
                }
                JSONObject properties = jsonNode.getJSONObject("properties");
                String overrideId = properties.getString("overrideid");
                String taskName = properties.getString("name");
                System.out.println("overrideId:" + overrideId);
                if ("StartNoneEvent".equals(type)) {
                    overrideId = "startEvent";
                    taskName = "开始";
                }
                if ("EndNoneEvent".equals(type)) {
                    overrideId = "endEvent";
                    taskName = "结束";
                }
                ProcessDetail processDetail = new ProcessDetail();
                processDetail.setModelId(modelId);
                processDetail.setProcessId(processId);
                processDetail.setProcessName(name);
                processDetail.setTaskDefKey(overrideId);
                processDetail.setTaskName(taskName);
                processDetail.setType(type);
                processDetails.add(processDetail);
            }
            processDetailService.saveBatch(processDetails);
        }
    }


    @Override
    public String getProcessJson() {
        InputStream stencilsetStream = this.getClass().getClassLoader().getResourceAsStream("stencilset.json");
        try {
            assert stencilsetStream != null;
            return IOUtils.toString(stencilsetStream, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("Error while loading stencil set", e);
            SparkSysExceptionAssert.businessFail("Error while loading stencil set");
        }
        return null;
    }
}
