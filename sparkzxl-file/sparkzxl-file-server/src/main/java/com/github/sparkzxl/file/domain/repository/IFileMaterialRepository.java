package com.github.sparkzxl.file.domain.repository;

import com.github.sparkzxl.file.infrastructure.entity.FileMaterial;

/**
 * description：文件 仓储类
 *
 * @author zhouxinlei
 * @date 2020/6/16 0016
 */
public interface IFileMaterialRepository {

    /**
     * 根据文件名查询文件
     *
     * @param fileName 文件名
     * @return FileMaterial
     */
    FileMaterial selectByFileName(String fileName);

    /**
     * 保存文件上传记录
     *
     * @param fileMaterial 文件素材
     * @return boolean
     */
    boolean saveFileMaterial(FileMaterial fileMaterial);

    /**
     * 删除文件记录
     *
     * @param fileName 文件名
     * @return boolean
     */
    boolean deleteFile(String fileName);

    /**
     * 根据文件路径查询文件素材
     *
     * @param filePath 文件路径
     * @return FileMaterial
     */
    FileMaterial selectByFilePath(String filePath);
}
