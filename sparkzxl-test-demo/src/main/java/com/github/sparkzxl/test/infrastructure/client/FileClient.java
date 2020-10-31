package com.github.sparkzxl.test.infrastructure.client;

import com.github.sparkzxl.file.api.FileApi;
import com.github.sparkzxl.test.infrastructure.fallback.FileFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(value = "sparkzxl-file-server",fallback = FileFallback.class)
@Component
public interface FileClient extends FileApi {
}
