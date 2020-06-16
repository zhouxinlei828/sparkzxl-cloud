package com.sparksys.file.domain.service;

import cn.hutool.core.io.FileUtil;
import com.sparksys.commons.core.utils.pdf.Mht2HtmlUtil;
import com.sparksys.file.application.service.IFileService;
import com.sparksys.file.domain.dto.FileDTO;
import com.sparksys.file.domain.dto.OssCallbackDTO;
import com.sparksys.file.domain.dto.OssPolicyResult;
import com.sparksys.file.domain.model.FileMaterialDO;
import com.sparksys.file.domain.model.UploadResult;
import com.sparksys.file.domain.repository.IFileMaterialRepository;
import com.sparksys.file.infrastructure.convert.FileMaterialConvert;
import com.sparksys.file.infrastructure.upload.AliOssFileHandler;
import com.sparksys.file.interfaces.dto.FileMaterialDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;

/**
 * description: 文件上传服务实现类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:32:31
 */
@Service
@Slf4j
public class FileServiceImpl implements IFileService {


    private final AliOssFileHandler aliOssFileHandler;
    private final IFileMaterialRepository fileMaterialRepository;

    public FileServiceImpl(AliOssFileHandler aliOssFileHandler, IFileMaterialRepository fileMaterialRepository) {
        this.aliOssFileHandler = aliOssFileHandler;
        this.fileMaterialRepository = fileMaterialRepository;
    }

    @Override
    public FileMaterialDTO upload(MultipartFile multipartFile) {
        FileMaterialDO fileMaterialDO;
        String fileName = FileUtil.mainName(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        fileMaterialDO = fileMaterialRepository.selectByFileName(fileName);
        if (ObjectUtils.isEmpty(fileMaterialDO)) {
            // 上传到阿里云
            UploadResult uploadResult = aliOssFileHandler.upload(multipartFile);
            fileMaterialDO = uploadResult.builder(uploadResult);
            boolean result = fileMaterialRepository.saveFileMaterialDO(fileMaterialDO);
            log.info("文件上传结果 result is {}", result);
        }
        return FileMaterialConvert.INSTANCE.convertFileMaterialDTO(fileMaterialDO);
    }

    @Override
    public boolean deleteFile(String fileName) {
        aliOssFileHandler.delete(fileName);
        return fileMaterialRepository.deleteFile(fileName);
    }

    @Override
    public FileMaterialDTO callback(OssCallbackDTO ossCallbackDTO) {
        FileMaterialDO fileMaterialDO;
        fileMaterialDO = fileMaterialRepository.selectByFilePath(ossCallbackDTO.getFilePath());
        if (ObjectUtils.isEmpty(fileMaterialDO)) {
            fileMaterialDO = ossCallbackDTO.builder(ossCallbackDTO);
            boolean result = fileMaterialRepository.saveFileMaterialDO(fileMaterialDO);
            log.info("文件上传结果 result is {}", result);
        }
        return FileMaterialConvert.INSTANCE.convertFileMaterialDTO(fileMaterialDO);
    }

    @Override
    public FileDTO getHtml(FileDTO fileDTO) {
        String fileName = FileUtil.getName(fileDTO.getFilePath());
        String baseName = FileUtil.mainName(fileName);
        String tempFilePath = "/data/" + baseName + ".html";
        Mht2HtmlUtil.mht2html(fileDTO.getFilePath(), tempFilePath);
        File file = FileUtil.file(tempFilePath);
        UploadResult uploadResult = aliOssFileHandler.upload(file);
        FileDTO outDto = new FileDTO();
        outDto.setFilePath(uploadResult.getFilePath());
        file.delete();
        return outDto;
    }

    @Override
    public OssPolicyResult policy() {
        return aliOssFileHandler.policy();
    }
}
