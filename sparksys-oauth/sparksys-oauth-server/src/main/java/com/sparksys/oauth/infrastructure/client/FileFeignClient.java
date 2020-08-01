package com.sparksys.oauth.infrastructure.client;

import com.sparksys.file.api.FileApi;
import com.sparksys.oauth.infrastructure.fallback.FileFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * description: 文件client
 *
 * @author: zhouxinlei
 * @date: 2020-07-31 22:10:09
 */
@FeignClient(value = "sparksys-file", fallback = FileFeignFallback.class)
public interface FileFeignClient extends FileApi {
    
}
