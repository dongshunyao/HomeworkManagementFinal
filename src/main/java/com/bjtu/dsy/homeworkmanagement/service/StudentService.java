package com.bjtu.dsy.homeworkmanagement.service;

import com.bjtu.dsy.homeworkmanagement.mapper.HomeworkMapper;
import com.bjtu.dsy.homeworkmanagement.mapper.StudentMapper;
import com.bjtu.dsy.homeworkmanagement.mapper.SubmitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private StudentMapper studentMapper;
    private HomeworkMapper homeworkMapper;
    private SubmitMapper submitMapper;

    @Autowired
    public StudentService(StudentMapper studentMapper, HomeworkMapper homeworkMapper, SubmitMapper submitMapper) {
        this.studentMapper = studentMapper;
        this.homeworkMapper = homeworkMapper;
        this.submitMapper = submitMapper;
    }
}
