package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.AppForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteServices;
import com.udacity.jwdnd.course1.cloudstorage.services.UserServices;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/notes")
public class NoteController {

    private final NoteServices noteServices;
    private final UserServices userServices;

    public NoteController(NoteServices noteServices, UserServices userServices) {
        this.noteServices = noteServices;
        this.userServices = userServices;
    }

    @PostMapping
    public String postAndUpdateUserNote(Authentication authentication, AppForm appForm, Model model, Notes notes){
        Users user = this.userServices.getUser(authentication.getName());
        appForm.setUserId(user.getUserId());
        String noteError = null;
        if (notes.getNoteId() == null) {

            if (noteServices.isNoteAvailable(user.getUserId(), appForm.getNoteTitle())) {
                noteError = "Already have a Note with this Title";
            }
            if (noteError == null) {
                this.noteServices.addUserNote(appForm);
                appForm.setNoteTitle("");
                appForm.setNoteDescription("");
                model.addAttribute("success", true);
            } else {
                model.addAttribute("errors", noteError);
            }
        } else {
            this.noteServices.updateUserNote(appForm);
            model.addAttribute("success", true);
        }

        return "result";
    }


    @GetMapping("delete/{noteId}")
    public String deleteUserNote(@PathVariable Integer noteId, Model model ){
        this.noteServices.deleteUserNote(noteId);
        model.addAttribute("success",true);
        return "result";

    }
}
