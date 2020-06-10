package com.sparksys.file.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * description: oss上传回调入参
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:36:34
 */
@Data
@ApiModel(description = "oss上传回调入参")
public class OssCallbackDTO {

    @NotEmpty(message = "登陆账号不能为空")
    private String filename;

    private Double size;

    private String contentType;

    private String filePath;
}
