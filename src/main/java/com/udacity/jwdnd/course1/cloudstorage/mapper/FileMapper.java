package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    List<File> getUserFile(Integer userId);

    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, userId, fileData) " +
            "VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId},#{fileData})")
    @Options (useGeneratedKeys = true, keyProperty = "fileId")
    int uploadNewUserFile(File file);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File viewSelectedFile(Integer fileId);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    void deleteUserFile(Integer fileId);
}
