package com.github.sparkzxl.authority.infrastructure.fallback;

import com.github.sparkzxl.core.support.ResponseResultStatus;
import com.github.sparkzxl.file.dto.FileDTO;
import com.github.sparkzxl.authority.infrastructure.client.FileFeignClient;
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
