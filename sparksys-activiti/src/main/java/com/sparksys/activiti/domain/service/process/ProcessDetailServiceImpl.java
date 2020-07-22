package com.sparksys.activiti.domain.service.process;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sparksys.activiti.infrastructure.entity.ProcessDetail;
import com.sparksys.activiti.infrastructure.mapper.ProcessDetailMapper;
import com.sparksys.activiti.application.service.process.IProcessDetailService;
import com.sparksys.activiti.interfaces.dto.ProcessDetailDTO;
import com.sparksys.database.dto.PageDTO;
import com.sparksys.database.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.database.utils.PageInfoUtils;
import org.springframework.stereotype.Service;
import com.sparksys.activiti.domain.repository.IProcessDetailRepository;
import java.util.List;

/**
 * description: 流程详细节点 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-21 14:22:17
 */
@Service
public class ProcessDetailServiceImpl extends AbstractSuperCacheServiceImpl<ProcessDetailMapper, ProcessDetail> implements IProcessDetailService {

    private final IProcessDetailRepository processDetailRepssitory;

    public ProcessDetailServiceImpl(IProcessDetailRepository processDetailRepssitory) {
        this.processDetailRepssitory = processDetailRepssitory;
    }

    @Override
    public List<ProcessDetailDTO> getProcessDetail(String modelId) {

        return null;
    }

    @Override
    public PageInfo<ProcessDetail> getProcessDetailList(PageDTO pageDTO, String name) {
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<ProcessDetail> processDetails = processDetailRepssitory.getProcessDetailList(name);
        return PageInfoUtils.pageInfo(processDetails);
    }

    @Override
    protected String getRegion() {
        return null;
    }
}
