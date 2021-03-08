package com.github.sparkzxl.authorization.domain.service;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.domain.repository.IAuthApplicationRepository;
import com.github.sparkzxl.authorization.infrastructure.convert.AuthApplicationConvert;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthApplication;
import com.github.sparkzxl.authorization.infrastructure.mapper.AuthApplicationMapper;
import com.github.sparkzxl.authorization.application.service.IAuthApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.sparkzxl.authorization.interfaces.dto.application.AuthApplicationPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.application.AuthApplicationSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.application.AuthApplicationUpdateDTO;
import com.github.sparkzxl.database.dto.DeleteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description: 租户客户端服务实现类
 *
 * @author: zhouxinlei
 * @date: 2021-02-20 09:44:43
 */
@Service
public class AuthApplicationServiceServiceImpl extends ServiceImpl<AuthApplicationMapper, AuthApplication> implements IAuthApplicationService {


    @Autowired
    private IAuthApplicationRepository authApplicationRepository;

    @Override
    public boolean saveApplication(AuthApplicationSaveDTO authApplicationSaveDTO) {
        AuthApplication authApplication = AuthApplicationConvert.INSTANCE.convertAuthApplication(authApplicationSaveDTO);
        return authApplicationRepository.saveAuthApplication(authApplication);
    }

    @Override
    public PageInfo<AuthApplication> listPage(AuthApplicationPageDTO authApplicationPageDTO) {
        return authApplicationRepository.listPage(authApplicationPageDTO.getPageNum(), authApplicationPageDTO.getPageSize(),
                authApplicationPageDTO.getClientId(), authApplicationPageDTO.getAppName());
    }

    @Override
    public boolean deleteApplication(DeleteDTO deleteDTO) {
        return authApplicationRepository.deleteAuthApplication(deleteDTO.getIds());
    }

    @Override
    public boolean updateApplication(AuthApplicationUpdateDTO authApplicationUpdateDTO) {
        AuthApplication authApplication = AuthApplicationConvert.INSTANCE.convertAuthApplication(authApplicationUpdateDTO);
        return authApplicationRepository.updateAuthApplication(authApplication);
    }
}
