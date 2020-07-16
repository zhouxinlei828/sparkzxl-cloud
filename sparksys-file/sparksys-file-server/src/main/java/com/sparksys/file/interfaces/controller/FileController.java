package com.sparksys.file.interfaces.controller;

import com.sparksys.log.annotation.WebLog;
import com.sparksys.web.annotation.ResponseResult;
import com.sparksys.file.application.service.IFileService;
import com.sparksys.file.domain.dto.OssPolicyResult;
import com.sparksys.file.domain.dto.FileDTO;
import com.sparksys.file.domain.dto.OssCallbackDTO;
import com.sparksys.file.interfaces.dto.FileMaterialDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * description: 文件上传 前端控制器
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:40:10
 */
@RestController
@ResponseResult
@WebLog
@RequestMapping("/file")
@Api(tags = "文件管理")
public class FileController {

    private final IFileService fileService;

    public FileController(IFileService fileService) {
        this.fileService = fileService;
    }

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public FileMaterialDTO upload(@RequestParam("file") MultipartFile multipartFile) {
        return fileService.upload(multipartFile);
    }

    @ApiOperation("删除文件")
    @DeleteMapping("/delete/{fileName}")
    public boolean delete(@PathVariable("fileName") String fileName){
        return fileService.deleteFile(fileName);
    }

    @ApiOperation("转换html文件")
    @PostMapping("/html")
    public FileDTO getHtml(@Validated @RequestBody FileDTO fileDTO) {
        return fileService.getHtml(fileDTO);
    }

    @ApiOperation("获取oss配置信息")
    @GetMapping("/ossPolicy")
    public OssPolicyResult ossPolicy() {
        return fileService.policy();
    }

    @ApiOperation("文件上传回调")
    @PostMapping("/callback")
    public FileMaterialDTO callback(@RequestBody OssCallbackDTO ossCallbackDTO) {
        return fileService.callback(ossCallbackDTO);
    }
}
