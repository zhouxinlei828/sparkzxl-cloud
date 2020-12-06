package com.github.sparkzxl.test.infrastructure.fallback;

import com.github.sparkzxl.distributed.cloud.hystrix.FallBackHandler;
import com.github.sparkzxl.file.dto.FileDTO;
import com.github.sparkzxl.test.infrastructure.client.FileClient;
import org.springframework.stereotype.Component;

@Component
public class FileFallback implements FileClient {

    @Override
    public FileDTO getPdf(FileDTO fileDTO) {
        FallBackHandler.fallBack();
        return null;
    }

    @Override
    public FileDTO getHtml(FileDTO fileDTO) throws Exception {
        FallBackHandler.fallBack();
        return null;
    }

    @Override
    public String getSayHello() {
        FallBackHandler.fallBack();
        return null;
    }

    @Override
    public FileDTO getLocalDateTime() {
        FallBackHandler.fallBack();
        return null;
    }

    @Override
    public void saveFile(String path) {
        FallBackHandler.fallBack();
    }
}
