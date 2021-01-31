package com.pibox.note.controller;

import com.pibox.note.model.Note;
import com.pibox.note.model.User;
import com.pibox.note.service.NoteService;
import com.pibox.note.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping("/{user_id}")
    public String GetUserNotes(@PathVariable(value = "user_id") Long userId, Model model) {
        User user = userService.getUser(userId);
        List<Note> notes = noteService.getUserNotes(userId);
        model.addAttribute("user", user);
        model.addAttribute("notes", notes);
        return "user-notes";
    }

    @GetMapping("/{user_id}/note-add")
    public String addNoteForm(@PathVariable(value = "user_id") Long userId, Model model) {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        model.addAttribute("note", new Note());
        return "note-add";
    }

    @PostMapping("/{user_id}/noteAddForm")
    public String addNote(@PathVariable(value = "user_id") Long userId,
                          @ModelAttribute(value = "note") Note note) {
        User user = userService.getUser(userId);
        noteService.addNote(user.getId(), note.getTitle(), note.getDescription());
        return "redirect:/user/{user_id}";
    }

    @GetMapping("{user_id}/note-edit/{note_id}")
    public String editNoteForm(@PathVariable(value = "note_id") Long noteId,
                               Model model) {
        Note note = noteService.getNote(noteId);
        model.addAttribute("note", note);
        return "note-edit";
    }

    @PostMapping("{user_id}/noteEditForm")
    public String editNote(@ModelAttribute(value = "note") Note note) {
        noteService.updateNote(note.getId(), note.getTitle(), note.getDescription());
        return "redirect:/user/{user_id}";
    }

    @GetMapping("{user_id}/delete/{note_id}")
    public String deleteNote(@PathVariable(value = "note_id") Long noteId) {
        noteService.deleteNote(noteId);
        return "redirect:/user/{user_id}";
    }
}
