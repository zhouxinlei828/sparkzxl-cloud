package com.sparksys.file.application.service;

import com.sparksys.core.support.BusinessException;
import com.sparksys.file.domain.dto.FileDTO;
import com.sparksys.file.domain.dto.OssCallbackDTO;
import com.sparksys.file.domain.dto.OssPolicyResult;
import com.sparksys.file.interfaces.dto.FileMaterialDTO;
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
    FileMaterialDTO upload(MultipartFile multipartFile);

    /**
     * 删除文件
     *
     * @param objectName 文件名
     * @return
     * @throws BusinessException
     */
    boolean deleteFile(String objectName);

    /**
     * 文件上传回调
     *
     * @param ossCallbackDTO 回调参数
     * @return
     */
    FileMaterialDTO callback(OssCallbackDTO ossCallbackDTO);

    /**
     * 转换html文件
     *
     * @param fileDTO 文件入参
     * @return
     * @throws Exception
     */
    FileDTO getHtml(FileDTO fileDTO);



    /**
     * 获取oss配置信息
     *
     * @return
     */
    OssPolicyResult policy();
}
