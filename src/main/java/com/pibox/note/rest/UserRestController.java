package com.pibox.note.rest;

import com.pibox.note.model.User;
import com.pibox.note.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        userService.addUser(user.getFirstName(), user.getLastName(), user.getEmail());
        return "User has been added";
    }

    @PostMapping("/user/{user_id}")
    public User findUser(@PathVariable("user_id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/editUser")
    public String editUser(@RequestBody User user) {
        userService.updateUser(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
        return "User has been updated";
    }

    @PostMapping("/deleteUser/{user_id}")
    public String editUser(@PathVariable("user_id") Long id) {
        userService.deleteUser(id);
        return "User has been deleted";
    }
}
