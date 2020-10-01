package com.github.sparkzxl.activiti.domain.service.process;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.activiti.application.service.process.IProcessDetailService;
import com.github.sparkzxl.activiti.domain.repository.IProcessDetailRepository;
import com.github.sparkzxl.activiti.infrastructure.convert.ProcessDetailConvert;
import com.github.sparkzxl.activiti.infrastructure.entity.ProcessDetail;
import com.github.sparkzxl.activiti.infrastructure.mapper.ProcessDetailMapper;
import com.github.sparkzxl.activiti.interfaces.dto.process.ProcessDetailDTO;
import com.github.sparkzxl.activiti.interfaces.dto.process.ProcessDetailPageDTO;
import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 流程详细节点 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-21 14:22:17
 */
@Service
public class ProcessDetailServiceImpl extends AbstractSuperCacheServiceImpl<ProcessDetailMapper, ProcessDetail> implements IProcessDetailService {

    private final IProcessDetailRepository processDetailRepository;

    public ProcessDetailServiceImpl(IProcessDetailRepository processDetailRepository) {
        this.processDetailRepository = processDetailRepository;
    }

    @Override
    public List<ProcessDetailDTO> getProcessDetail(String modelId) {
        List<ProcessDetail> processDetails = processDetailRepository.getProcessDetail(modelId);
        List<ProcessDetailDTO> processDetailDTOList = Lists.newArrayList();
        processDetails.forEach(processDetail -> processDetailDTOList.add(ProcessDetailConvert.INSTANCE.convertProcessDetailDTO(processDetail)));
        return processDetailDTOList;
    }

    @Override
    public PageInfo<ProcessDetail> getProcessDetailList(ProcessDetailPageDTO processDetailPageDTO) {
        return processDetailRepository.getProcessDetailList(processDetailPageDTO.getPageNum(),
                processDetailPageDTO.getPageSize(), processDetailPageDTO.getProcessName());
    }

    @Override
    protected String getRegion() {
        return null;
    }
}
