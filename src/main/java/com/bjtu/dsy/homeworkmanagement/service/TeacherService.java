package com.bjtu.dsy.homeworkmanagement.service;

import com.bjtu.dsy.homeworkmanagement.mapper.HomeworkMapper;
import com.bjtu.dsy.homeworkmanagement.mapper.SubmitMapper;
import com.bjtu.dsy.homeworkmanagement.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    private TeacherMapper teacherMapper;
    private HomeworkMapper homeworkMapper;
    private SubmitMapper submitMapper;

    @Autowired
    public TeacherService(TeacherMapper teacherMapper, HomeworkMapper homeworkMapper, SubmitMapper submitMapper) {
        this.teacherMapper = teacherMapper;
        this.homeworkMapper = homeworkMapper;
        this.submitMapper = submitMapper;
    }
}
