package com.github.sparkzxl.file.application.service;

import com.github.sparkzxl.core.support.BusinessException;
import com.github.sparkzxl.file.dto.FileDTO;
import com.github.sparkzxl.file.interfaces.dto.FileMaterialDTO;
import org.springframework.web.multipart.MultipartFile;


/**
 * description: 文件上传服务
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:30:30
 */
public interface IFileService {

    /**
     * 上传文件
     *
     * @param multipartFile 文件
     * @return
     * @throws Exception
     */
    FileMaterialDTO upload(MultipartFile multipartFile) throws Exception;

    /**
     * 删除文件
     *
     * @param objectName 文件名
     * @return
     * @throws BusinessException
     */
    boolean deleteFile(String objectName);

    /**
     * 转换html文件
     *
     * @param fileDTO 文件入参
     * @return
     * @throws Exception
     */
    FileDTO getHtml(FileDTO fileDTO) throws Exception;

    /**
     * 保存文件
     * @param path 文件路径
     */
    void saveFile(String path);
}
