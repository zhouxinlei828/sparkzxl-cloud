package com.github.sparkzxl.authority.infrastructure.client;

import com.github.sparkzxl.file.api.FileApi;
import com.github.sparkzxl.authority.infrastructure.fallback.FileFeignFallback;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * description: 文件client
 *
 * @author: zhouxinlei
 * @date: 2020-07-31 22:10:09
 */
@FeignClient(value = "sparkzxl-file", fallback = FileFeignFallback.class)
@Qualifier("fileFeignClient")
public interface FileFeignClient extends FileApi {

}
