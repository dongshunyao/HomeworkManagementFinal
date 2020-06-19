package com.bjtu.dsy.homeworkmanagement.controller;

import com.bjtu.dsy.homeworkmanagement.service.UserService;
import com.bjtu.dsy.homeworkmanagement.util.response.UserResponse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public LogResponse login(int id, String password, boolean teacher) {
        return userService.login(id, password, teacher);
    }

    @RequestMapping("/logout")
    public LogResponse logout(int id, String token, boolean teacher) {
        return userService.logout(id, token, teacher);
    }

    @RequestMapping("/register")
    public RegisterResponse register(int id, String name, String password, boolean teacher) {
        return userService.register(id, name, password, teacher);
    }
}
