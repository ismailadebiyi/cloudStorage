package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.UserServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")

public class SignupController {

    private final UserServices userServices;

    public SignupController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping
    public String loadSignupPage(){
        return "signup";
    }

    @PostMapping
    public  String createUser(@ModelAttribute Users user, Model model){
        String errorStatus = null;
        if(!userServices.isUsernameAvailable(user.getUserName())){
            errorStatus = "Username is not Available";
        }
        if (errorStatus == null){
            int userIndex = userServices.createUser(user);
            if (userIndex<0){
                errorStatus ="Error registering User";
            }
        }
        if (errorStatus == null){
            model.addAttribute("signupSuccess", true);
        }else {
            model.addAttribute("signupError", errorStatus);
        }

        return "redirect:/login";
    }

}
