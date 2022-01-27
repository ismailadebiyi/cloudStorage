package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.AppForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServices {

    private final NoteMapper noteMapper;

    public NoteServices(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public void addUserNote(AppForm appForm){

        Notes notes = new Notes();
        notes.setUserId(appForm.getUserId());
        notes.setNoteTitle(appForm.getNoteTitle());
        notes.setNoteDescription(appForm.getNoteDescription());
        noteMapper.addUserNote(notes);

    }


    public List<Notes> displayUserNotes(Integer userId){
        return noteMapper.getAllUserNote(userId);

    }

    public Boolean isNoteAvailable(Integer userId, String noteTitle){
        List<Notes> notes = displayUserNotes(userId);
        for (Notes n : notes){
            if (n.getNoteTitle().equals(noteTitle)){
                return noteTitle!=null;
            }
        }
        return noteTitle == null ;
    }

    public void updateUserNote(AppForm appForm){
        Notes notes = new Notes();
        notes.setNoteId(appForm.getNoteId());
        notes.setNoteTitle(appForm.getNoteTitle());
        notes.setNoteDescription(appForm.getNoteDescription());
        noteMapper.updateUserNote(notes);

    }

    public void deleteUserNote(Integer noteId){
        noteMapper.deleteUserNote(noteId);

    }

}
