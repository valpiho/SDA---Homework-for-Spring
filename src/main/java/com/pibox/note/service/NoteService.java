package com.pibox.note.service;

import com.pibox.note.model.Note;
import com.pibox.note.model.User;

import java.util.List;

public interface NoteService {

    List<Note> getUserNotes(Long id);

    Note getNote(Long id);

    void addNote(User user, String title, String description);

    void updateNote(Long id, String title, String description);
}
