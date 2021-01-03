package com.github.sparkzxl.file.infrastructure.repository;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.database.utils.PageInfoUtils;
import com.github.sparkzxl.file.infrastructure.mapper.FileMaterialMapper;
import com.github.sparkzxl.file.infrastructure.entity.FileMaterial;
import com.github.sparkzxl.file.domain.repository.IFileMaterialRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

/**
 * description： 文件 仓储实现类
 *
 * @author zhouxinlei
 * @date 2020/6/16 0016
 */
@Repository
public class FileMaterialRepository implements IFileMaterialRepository {

    private final FileMaterialMapper fileMaterialMapper;

    public FileMaterialRepository(FileMaterialMapper fileMaterialMapper) {
        this.fileMaterialMapper = fileMaterialMapper;
    }

    @Override
    public FileMaterial selectByFileName(String fileName) {
        //文件新路径
        String extension = FileUtil.extName(fileName);
        QueryWrapper<FileMaterial> materialQueryWrapper = new QueryWrapper<>();
        materialQueryWrapper.eq("file_name", fileName);
        materialQueryWrapper.eq("suffix", extension);
        return fileMaterialMapper.selectOne(materialQueryWrapper);
    }

    @Override
    public boolean saveFileMaterial(FileMaterial fileMaterialDO) {
        return fileMaterialMapper.insert(fileMaterialDO) == 1;
    }

    @Override
    public boolean deleteFile(String fileName) {
        QueryWrapper<FileMaterial> materialQueryWrapper = new QueryWrapper<>();
        materialQueryWrapper.eq("file_name", fileName);
        return fileMaterialMapper.delete(materialQueryWrapper) == 1;
    }

    @Override
    public FileMaterial selectByFilePath(String filePath) {
        QueryWrapper<FileMaterial> materialQueryWrapper = new QueryWrapper<>();
        materialQueryWrapper.eq("file_path", filePath);
        return fileMaterialMapper.selectOne(materialQueryWrapper);
    }


    @Override
    public PageInfo<FileMaterial> fileMaterialPageList(int pageNum, int pageSize, String fileName, String contentType) {
        LambdaQueryWrapper<FileMaterial> materialQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(fileName)) {
            materialQueryWrapper.likeRight(FileMaterial::getFileName, fileName);
        }
        if (StringUtils.isNotEmpty(contentType)) {
            materialQueryWrapper.eq(FileMaterial::getContentType, contentType);
        }
        PageHelper.startPage(pageNum, pageSize);
        return PageInfoUtils.pageInfo(fileMaterialMapper.selectList(materialQueryWrapper));
    }
}
