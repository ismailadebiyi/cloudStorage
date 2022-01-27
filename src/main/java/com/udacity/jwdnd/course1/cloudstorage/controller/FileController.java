package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.AppForm;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserServices;
import org.apache.tomcat.jni.User;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;
    private final UserServices userServices;

    public FileController(FileService fileService, UserServices userServices) {
        this.fileService = fileService;
        this.userServices = userServices;
    }

    @PostMapping
    public String uploadUserFile( Authentication authentication, @RequestParam("fileUpload") MultipartFile multipartFile, Model model) throws IOException {
        Integer userId = this.userServices.getUser(authentication.getName()).getUserId();
        String fileError = null;
        if (fileService.isUserFileAvailable(userId,multipartFile.getOriginalFilename())){
            fileError = "File Already Uploaded, Upload another file or Rename Before Uploading";
        }
        if (fileError == null){
            fileService.uploadUserFile(multipartFile,userId);
            model.addAttribute("success", true);
        }else {
            model.addAttribute("errors", fileError);
        }

        return "result";
    }

    @GetMapping("view/{fileId}")
    public ResponseEntity<ByteArrayResource> viewUserFile(@PathVariable Integer fileId){
        File sFile = this.fileService.viewSelectedUserFile(fileId);
        ByteArrayResource byteArrayResource = new ByteArrayResource(sFile.getFileData());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(sFile.getContentType())).body(byteArrayResource);


    }

    @GetMapping("delete/{fileId}")
    public String deleteUserFile(@PathVariable Integer fileId, Model model){
        this.fileService.deleteUserFile(fileId);
        model.addAttribute("success", true);
        return "result";

    }

}
