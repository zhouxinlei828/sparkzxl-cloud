package com.sparksys.file.infrastructure.repository;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sparksys.file.domain.mapper.FileMaterialMapper;
import com.sparksys.file.domain.model.FileMaterialDO;
import com.sparksys.file.domain.repository.IFileMaterialRepository;
import org.springframework.stereotype.Repository;

/*
 * description：
 *
 * @author zhouxinlei
 * @date  2020/6/16 0016
 */
@Repository
public class FileMaterialRepository implements IFileMaterialRepository {

    private final FileMaterialMapper fileMaterialMapper;

    public FileMaterialRepository(FileMaterialMapper fileMaterialMapper) {
        this.fileMaterialMapper = fileMaterialMapper;
    }

    @Override
    public FileMaterialDO selectByFileName(String fileName) {
        //文件新路径
        String extension = FileUtil.extName(fileName);
        QueryWrapper<FileMaterialDO> materialQueryWrapper = new QueryWrapper<>();
        materialQueryWrapper.eq("file_name", fileName);
        materialQueryWrapper.eq("suffix", extension);
        return fileMaterialMapper.selectOne(materialQueryWrapper);
    }

    @Override
    public boolean saveFileMaterialDO(FileMaterialDO fileMaterialDO) {
        return fileMaterialMapper.insert(fileMaterialDO) == 1;
    }

    @Override
    public boolean deleteFile(String fileName) {
        QueryWrapper<FileMaterialDO> materialQueryWrapper = new QueryWrapper<>();
        materialQueryWrapper.eq("file_name", fileName);
        return fileMaterialMapper.delete(materialQueryWrapper) == 1;
    }

    @Override
    public FileMaterialDO selectByFilePath(String filePath) {
        QueryWrapper<FileMaterialDO> materialQueryWrapper = new QueryWrapper<>();
        materialQueryWrapper.eq("file_path", filePath);
        return fileMaterialMapper.selectOne(materialQueryWrapper);
    }
}
