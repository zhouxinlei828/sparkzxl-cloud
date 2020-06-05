package com.sparksys.authorization.domain.service;

import com.sparksys.authorization.application.service.IAuthUserService;
import com.sparksys.authorization.infrastructure.convert.AuthUserConvert;
import com.sparksys.authorization.infrastructure.po.AuthUserDO;
import com.sparksys.authorization.infrastructure.po.AuthUserDetail;
import com.sparksys.authorization.domain.repository.IAuthUserRepository;
import com.sparksys.authorization.interfaces.dto.AuthUserDTO;
import com.sparksys.authorization.interfaces.dto.AuthUserLoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

/**
 * description: 用户查询 服务实现类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:22:57
 */
@Service
@Slf4j
public class AuthUserServiceImpl implements IAuthUserService {

    private final IAuthUserRepository authUserRepository;

    public AuthUserServiceImpl(IAuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public AuthUserDetail getAuthUserDetail(String username) {
        AuthUserDO authUserDO = authUserRepository.selectByUserName(username);
        if (ObjectUtils.isNotEmpty(authUserDO)) {
            return new AuthUserDetail(authUserDO.getAccount(), authUserDO.getPassword(),
                    AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        }
        return null;
    }

    @Override
    public AuthUserDTO getAuthUserDetail(AuthUserLoginDTO authUserLoginDTO) {
        AuthUserDO authUserDO = AuthUserConvert.INSTANCE.convertAuthUserDO(authUserLoginDTO);
        return AuthUserConvert.INSTANCE.convertAuthUserDTO(authUserRepository.selectByCondition(authUserDO));
    }
}
