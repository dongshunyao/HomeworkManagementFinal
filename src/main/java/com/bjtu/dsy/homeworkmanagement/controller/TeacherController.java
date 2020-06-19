package com.bjtu.dsy.homeworkmanagement.controller;

import com.bjtu.dsy.homeworkmanagement.service.TeacherService;
import com.bjtu.dsy.homeworkmanagement.util.response.TeacherResponse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "*")
public class TeacherController {
    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @RequestMapping("/homework_list")
    public HomeworkListResponse homeworkList(int id, String token) {
        return teacherService.homeworkList(id, token);
    }

    @RequestMapping("/submit_list")
    public SubmitListResponse submitList(int id, int homeworkId, String token) {
        return teacherService.submitList(id, homeworkId, token);
    }

    @RequestMapping("/submit_info")
    public SubmitInfoResponse submitInfo(int id, int submitId, String token) {
        return teacherService.submitInfo(id, submitId, token);
    }

    @RequestMapping("/review")
    public ReviewResponse review(int id, int submitId, int score, String comment, String token) {
        return teacherService.review(id, submitId, score, comment, token);
    }

    @RequestMapping("/add_homework")
    public AddHomeworkResponse addHomework(int id, String title, String content, String token) {
        return teacherService.addHomework(id, title, content, token);
    }
}
