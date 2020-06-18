package com.judgesystem.dao;

import com.judgesystem.entity.FileEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface FileEntityDao {
    public void saveUploadFile(FileEntity fileEntity);
    public FileEntity findFileById(int id);
    public List<FileEntity> findAll();
    void update(FileEntity fileEntity);
    void deleteById(int id);
    void addFile(@Param("list") List<FileEntity> list);
}
