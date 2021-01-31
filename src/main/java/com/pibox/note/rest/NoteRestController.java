package com.pibox.note.rest;

import com.pibox.note.model.Note;
import com.pibox.note.model.User;
import com.pibox.note.service.NoteService;
import com.pibox.note.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class NoteRestController {

    private final UserService userService;
    private final NoteService noteService;

    public NoteRestController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @GetMapping("/{id}")
    public User findUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/addNote")
    public String addNote(@RequestBody Note note) {
        noteService.addNote(note.getUser().getId(), note.getTitle(), note.getDescription());
        return "User has been added";
    }

    @PostMapping("/editNote")
    public String updateNote(@RequestBody Note note) {
        noteService.updateNote(note.getId(), note.getTitle(), note.getDescription());
        return "User has been added";
    }

    @PostMapping("/deleteNote/{note_id}")
    public String deleteNote(@PathVariable("note_id") Long id) {
        noteService.deleteNote(id);
        return "User has been added";
    }
}
