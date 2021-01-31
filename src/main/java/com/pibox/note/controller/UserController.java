package com.pibox.note.controller;

import com.pibox.note.model.User;
import com.pibox.note.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/user-add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-add";
    }

    @PostMapping("/userAddForm")
    public String addUser(@ModelAttribute(value = "user") User user) {
        userService.addUser(user.getFirstName(), user.getLastName(), user.getEmail());
        return "redirect:/";
    }

    @GetMapping("/user-edit/{user_id}")
    public String editUserForm(@PathVariable(value = "user_id") Long userId, Model model) {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "user-edit";
    }

    @PostMapping("/userEditForm")
    public String editUser(@ModelAttribute(value = "user") User user) {
        userService.updateUser(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
        return "redirect:/";
    }

    @GetMapping("/delete/{user_id}")
    public String deleteUser(@PathVariable(value = "user_id") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/";
    }
}
