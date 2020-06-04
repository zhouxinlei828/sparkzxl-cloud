package com.sparksys.file.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * description: 文件入参
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:36:23
 */
@Data
@ApiModel(value = "文件入参", description = "文件入参")
public class FileDTO {

    @ApiModelProperty(value = "文件地址")
    @NotEmpty(message = "文件地址不能为空")
    private String filePath;

    @ApiModelProperty(value = "文件地址列表")
    @NotEmpty(message = "文件地址列表不能为空")
    private List<String> fileList;

}
