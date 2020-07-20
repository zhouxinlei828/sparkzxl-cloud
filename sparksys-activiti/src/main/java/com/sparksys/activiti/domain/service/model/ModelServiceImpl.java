package com.sparksys.activiti.domain.service.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sparksys.activiti.application.service.model.IModelService;
import com.sparksys.core.support.SparkSysExceptionAssert;
import lombok.extern.slf4j.Slf4j;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class ModelServiceImpl implements IModelService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ObjectMapper objectMapper;

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
            repositoryService.addModelEditorSource(model.getId(), json_xml.getBytes("utf-8"));
            InputStream svgStream = new ByteArrayInputStream(svg_xml.getBytes("utf-8"));
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
