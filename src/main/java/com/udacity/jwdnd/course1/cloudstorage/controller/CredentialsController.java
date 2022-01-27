package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.AppForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserServices;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credentials")
public class CredentialsController {

    private final CredentialService credentialService;
    private final UserServices userServices;

    public CredentialsController(CredentialService credentialService, UserServices userServices) {
        this.credentialService = credentialService;
        this.userServices = userServices;
    }

    @PostMapping
    public String postAndUpdateUserCredential(Authentication authentication, AppForm appForm, Model model, Credentials credentials){
        Users user = this.userServices.getUser(authentication.getName());
        appForm.setUserId(user.getUserId());
        String noteError = null;
        if (credentials.getCredentialId() == null) {

            if (noteError == null) {
                this.credentialService.addNewUserCredentials(appForm);
                model.addAttribute("success", true);
            } else {
                model.addAttribute("errors", noteError);
            }
        } else {
            this.credentialService.updateUserCredentials(appForm);
            model.addAttribute("success", true);
        }

        return "result";
    }

    @GetMapping("delete/{credentialId}")
    public String deleteUserCredential(@PathVariable Integer credentialId, Model model){
        this.credentialService.deleteUserCredentials(credentialId);
        model.addAttribute("success", true);
        return "result";
    }
}
