package com.bjtu.dsy.homeworkmanagement.controller;

import com.bjtu.dsy.homeworkmanagement.service.TeacherService;
import com.bjtu.dsy.homeworkmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private UserService userService;
    private TeacherService teacherService;

    @Autowired
    public TeacherController(UserService userService, TeacherService teacherService) {
        this.userService = userService;
        this.teacherService = teacherService;
    }
}
