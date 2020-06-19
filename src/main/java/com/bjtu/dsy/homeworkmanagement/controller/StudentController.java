package com.bjtu.dsy.homeworkmanagement.controller;

import com.bjtu.dsy.homeworkmanagement.service.StudentService;
import com.bjtu.dsy.homeworkmanagement.util.response.StudentResponse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/homework_list")
    public HomeworkListResponse homeworkList(int id, String token) {
        return studentService.homeworkList(id, token);
    }

    @RequestMapping("/homework_info")
    public HomeworkInfoResponse homeworkInfo(int id, int homeworkId, String token) {
        return studentService.homeworkInfo(id, homeworkId, token);
    }

    @RequestMapping("/submit")
    public SubmitResponse submit(int id, int homeworkId, String title, String content, String token) {
        return studentService.submit(id, homeworkId, title, content, token);
    }
}