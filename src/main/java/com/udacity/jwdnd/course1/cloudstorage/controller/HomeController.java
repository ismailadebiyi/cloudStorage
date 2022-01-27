package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.AppForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final NoteServices noteServices;
    private final UserServices userServices;
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;
    private final FileService fileService;

    public HomeController(NoteServices noteServices, UserServices userServices,
                          CredentialService credentialService, EncryptionService encryptionService, FileService fileService) {
        this.noteServices = noteServices;
        this.userServices = userServices;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
        this.fileService = fileService;
    }


    @GetMapping
    public String loadHomePage(AppForm appForm, Model model, Authentication authentication){
        Users user = this.userServices.getUser(authentication.getName());
        model.addAttribute("notes",this.noteServices.displayUserNotes(user.getUserId()));
        model.addAttribute("credentials",this.credentialService.displayUserCredentials(user.getUserId()));
        model.addAttribute("files",this.fileService.getAllUserFiles(user.getUserId()));
        model.addAttribute("encryptionService", encryptionService);

        return "home";
    }


}

