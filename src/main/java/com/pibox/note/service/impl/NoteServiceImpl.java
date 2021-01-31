package com.pibox.note.service.impl;

import com.pibox.note.model.Note;
import com.pibox.note.model.User;
import com.pibox.note.repository.NoteRepository;
import com.pibox.note.service.NoteService;
import com.pibox.note.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserService userService;

    public NoteServiceImpl(NoteRepository noteRepository, UserService userService) {
        this.noteRepository = noteRepository;
        this.userService = userService;
    }

    @Override
    public Note getNote(Long id) {
        return noteRepository.findNoteById(id);
    }

    @Override
    public List<Note> getUserNotes(Long id) {
        return noteRepository.findAllByUser_Id(id);
    }

    @Override
    public void addNote(Long user_id, String title, String description) {
        Note note = new Note();
        User user = userService.getUser(user_id);
        note.setTitle(title);
        note.setDescription(description);
        note.setUser(user);
        noteRepository.save(note);
    }

    @Override
    public void updateNote(Long id, String title, String description) {
        Note note = noteRepository.findNoteById(id);
        note.setTitle(title);
        note.setDescription(description);
        note.setDate(new Date());
        noteRepository.save(note);
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
