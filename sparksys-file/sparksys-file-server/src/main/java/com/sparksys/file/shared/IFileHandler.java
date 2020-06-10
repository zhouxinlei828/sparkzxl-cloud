package com.sparksys.file.shared;

import com.aliyun.oss.model.OSSObject;
import com.sparksys.commons.core.support.BusinessException;
import com.sparksys.file.domain.dto.OssPolicyResult;
import com.sparksys.file.domain.model.UploadResult;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * description: File handler interface
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:34:47
 */
public interface IFileHandler {

    MediaType IMAGE_TYPE = MediaType.valueOf("image/*");

    /**
     * 判断是否是图片类型
     *
     * @param mediaType
     * @return
     */
    static boolean isImageType(@Nullable MediaType mediaType) {
        return mediaType != null && IMAGE_TYPE.includes(mediaType);
    }


    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    UploadResult upload(MultipartFile file);


    /**
     * 上传本地文件
     *
     * @param file
     * @return
     */
    UploadResult upload(File file);

    /**
     * 删除文件
     *
     * @param key
     * @throws BusinessException
     */
    void delete(String key);

    /**
     * 获取文件
     *
     * @param fileName
     * @return
     */
    OSSObject getFile(String fileName);

    /**
     * 判断文件是否存在
     *
     * @param fileName
     * @return
     */
    boolean exist(String fileName);

    /**
     * 签名生成
     *
     * @return
     */
    OssPolicyResult policy();
}
