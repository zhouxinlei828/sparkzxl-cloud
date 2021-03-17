package com.github.sparkzxl.file.api;

import com.github.sparkzxl.file.dto.FileDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * description: 文件API
 *
 * @author charles.zhou
 * @date 2020-05-24 12:27:04
 */
public interface FileApi {

    /**
     * 转换pdf文件
     *
     * @param fileDTO 文件入参
     * @return
     */
    @PostMapping("/pdf")
    FileDTO getPdf(@RequestBody FileDTO fileDTO);


    /**
     * 转换HTML文件
     *
     * @param fileDTO 文件入参
     * @return
     */
    @PostMapping("/html")
    FileDTO getHtml(@RequestBody FileDTO fileDTO) throws Exception;
}
