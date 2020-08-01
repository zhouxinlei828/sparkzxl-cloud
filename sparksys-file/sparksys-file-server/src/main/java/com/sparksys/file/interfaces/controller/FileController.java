package com.sparksys.file.interfaces.controller;

import com.sparksys.file.api.FileApi;
import com.sparksys.file.dto.FileDTO;
import com.sparksys.log.annotation.WebLog;
import com.sparksys.web.annotation.ResponseResult;
import com.sparksys.file.application.service.IFileService;
import com.sparksys.file.interfaces.dto.OssPolicyResult;
import com.sparksys.file.interfaces.dto.OssCallbackDTO;
import com.sparksys.file.interfaces.dto.FileMaterialDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * description: 文件上传 前端控制器
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:40:10
 */
@RestController
@WebLog
@Api(tags = "文件管理")
public class FileController implements FileApi {

    private final IFileService fileService;

    public FileController(IFileService fileService) {
        this.fileService = fileService;
    }

    @ApiOperation("文件上传")
    @ResponseResult
    @PostMapping("/file/upload")
    public FileMaterialDTO upload(@RequestParam("file") MultipartFile multipartFile) {
        return fileService.upload(multipartFile);
    }

    @ApiOperation("删除文件")
    @ResponseResult
    @DeleteMapping("/file/delete/{fileName}")
    public boolean delete(@PathVariable("fileName") String fileName) {
        return fileService.deleteFile(fileName);
    }

    @Override
    @ApiOperation("转换html文件")
    public FileDTO getHtml(@RequestBody FileDTO fileDTO) {
        return fileService.getHtml(fileDTO);
    }

    @ApiOperation("获取oss配置信息")
    @ResponseResult
    @GetMapping("/file/ossPolicy")
    public OssPolicyResult ossPolicy() {
        return fileService.policy();
    }

    @ApiOperation("文件上传回调")
    @ResponseResult
    @PostMapping("/file/callback")
    public FileMaterialDTO callback(@RequestBody OssCallbackDTO ossCallbackDTO) {
        return fileService.callback(ossCallbackDTO);
    }

    @Override
    public FileDTO getPdf(FileDTO fileDTO) {
        return null;
    }

    @ResponseResult
    @Override
    public String getSayHello() {
        return "sayHello";
    }
}
