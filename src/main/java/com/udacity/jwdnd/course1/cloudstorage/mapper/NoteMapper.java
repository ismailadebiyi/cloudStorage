package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE userId=#{userId}")
    List<Notes> getAllUserNote(Integer userId);

    @Insert("INSERT INTO NOTES (noteTitle, noteDescription, userId)" +
            " VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int addUserNote(Notes note);

    @Update("UPDATE NOTES SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} " +
            "WHERE noteId = #{noteId}")
    int updateUserNote(Notes note);

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
    void deleteUserNote(Integer noteId);
}
