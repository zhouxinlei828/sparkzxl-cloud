package com.sparksys.file.domain.dto;

import cn.hutool.core.io.FileUtil;
import com.sparksys.file.domain.entity.FileMaterial;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

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

    public FileMaterial builder(OssCallbackDTO ossCallbackDTO) {
        FileMaterial fileMaterialDO = new FileMaterial();
        String filePath = ossCallbackDTO.getFilePath();
        String extension = FileUtil.extName(ossCallbackDTO.getFilename());
        fileMaterialDO.setFileName(ossCallbackDTO.getFilename());
        fileMaterialDO.setSuffix(extension);
        fileMaterialDO.setFilePath(filePath);
        fileMaterialDO.setSize(ossCallbackDTO.getSize());
        fileMaterialDO.setContentType(ossCallbackDTO.getContentType());
        fileMaterialDO.setUid(String.valueOf(System.currentTimeMillis()));
        fileMaterialDO.setCreateTime(LocalDateTime.now());
        return fileMaterialDO;
    }
}
