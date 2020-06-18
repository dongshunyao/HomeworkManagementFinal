package com.bjtu.dsy.homeworkmanagement.controller;

import com.bjtu.dsy.homeworkmanagement.service.StudentService;
import com.bjtu.dsy.homeworkmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;
    private UserService userService;

    @Autowired
    public StudentController(StudentService studentService, UserService userService) {
        this.studentService = studentService;
        this.userService = userService;
    }
}
