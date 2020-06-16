package com.sparksys.file.domain.repository;

import com.sparksys.file.domain.model.FileMaterialDO;

/*
 * description：
 *
 * @author zhouxinlei
 * @date  2020/6/16 0016
 */
public interface IFileMaterialRepository {

    FileMaterialDO selectByFileName(String fileName);

    /**
     * 保存文件上传记录
     *
     * @param fileMaterialDO
     * @return
     */
    boolean saveFileMaterialDO(FileMaterialDO fileMaterialDO);

    /**
     * 删除文件记录
     *
     * @param fileName
     * @return
     */
    boolean deleteFile(String fileName);

    FileMaterialDO selectByFilePath(String filePath);
}
