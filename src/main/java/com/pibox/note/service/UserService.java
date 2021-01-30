package com.pibox.note.service;

import com.pibox.note.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User getUser(Long id);

    void addUser(String firstName, String lastName, String email);

    void deleteUser(Long id);
}
