package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public void uploadUserFile(@RequestParam("fileUpload") MultipartFile multipartFile, Integer userId) throws IOException {
        File file =new File();
        file.setUserId(userId);
        file.setFileName(multipartFile.getOriginalFilename());
        file.setContentType(multipartFile.getContentType());
        file.setFileSize(multipartFile.getSize());
        file.setFileData(multipartFile.getBytes());
        fileMapper.uploadNewUserFile(file);
    }

    public List<File> getAllUserFiles(Integer userId){
        return fileMapper.getUserFile(userId);
    }

    public boolean isUserFileAvailable(Integer userId, String fileName){
        List<File> userFile = getAllUserFiles(userId);
        for (File f: userFile){
            if (f.getFileName().equals(fileName)){
                return fileName!=null;
            }
        }

        return fileName==null;
    }

    public File viewSelectedUserFile(Integer fileId){
        return fileMapper.viewSelectedFile(fileId);
    }

    public void deleteUserFile(Integer fileId){
        fileMapper.deleteUserFile(fileId);
    }
}
