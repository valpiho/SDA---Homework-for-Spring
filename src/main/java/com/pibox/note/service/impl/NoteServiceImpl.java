package com.pibox.note.service.impl;

import com.pibox.note.model.Note;
import com.pibox.note.model.User;
import com.pibox.note.repository.NoteRepository;
import com.pibox.note.service.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public void addNote(User user, String title, String description) {
        Note note = new Note();
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
        noteRepository.save(note);
    }

    @Override
    public Note getNote(Long id) {
        return noteRepository.findNoteById(id);
    }


    @Override
    public List<Note> getUserNotes(Long id) {
        return noteRepository.findAllByUser_Id(id);
    }
}
