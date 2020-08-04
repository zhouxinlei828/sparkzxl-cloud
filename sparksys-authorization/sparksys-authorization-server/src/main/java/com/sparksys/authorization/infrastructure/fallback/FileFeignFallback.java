package com.sparksys.authorization.infrastructure.fallback;

import com.sparksys.core.support.ResponseResultStatus;
import com.sparksys.file.dto.FileDTO;
import com.sparksys.authorization.infrastructure.client.FileFeignClient;
import org.springframework.stereotype.Component;

/**
 * description: 文件降级实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-31 22:15:42
 */
@Component
public class FileFeignFallback implements FileFeignClient {
    @Override
    public FileDTO getPdf(FileDTO fileDTO) {
        ResponseResultStatus.SERVICE_DEGRADATION.assertNotNull(fileDTO);
        return null;
    }

    @Override
    public FileDTO getHtml(FileDTO fileDTO) {
        ResponseResultStatus.SERVICE_DEGRADATION.assertNotNull(fileDTO);
        return null;
    }

    @Override
    public String getSayHello() {
        ResponseResultStatus.SERVICE_DEGRADATION.assertNotNull(null);
        return null;
    }
}
