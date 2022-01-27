package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE userId =#{userId}")
    List<Credentials> getUserCredentials(Integer userId);

    @Insert("INSERT INTO CREDENTIALS (url, userName, password, key, userId)" +
            "VALUES (#{url}, #{userName}, #{password}, #{key}, #{userId})")
    @Options (useGeneratedKeys = true, keyProperty = "credentialId")
    int addNewUserCredentials(Credentials credentials);

    @Update("UPDATE NOTES SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} " +
            "WHERE noteId = #{noteId}")
    int updateUserNote(Notes note);

    @Update("UPDATE CREDENTIALS SET url= #{url}, userName = #{userName}, password = #{password}, key = #{key} " +
            "WHERE credentialId = #{credentialId}")
    int updateUserCredentials(Credentials credentials);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
        void deleteUserCredential(Integer credentialId);
}
