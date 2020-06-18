package com.bjtu.dsy.homeworkmanagement.service;

import com.bjtu.dsy.homeworkmanagement.mapper.HomeworkMapper;
import com.bjtu.dsy.homeworkmanagement.mapper.StudentMapper;
import com.bjtu.dsy.homeworkmanagement.mapper.SubmitMapper;
import com.bjtu.dsy.homeworkmanagement.mapper.TeacherMapper;
import com.bjtu.dsy.homeworkmanagement.model.Homework;
import com.bjtu.dsy.homeworkmanagement.model.Submit;
import com.bjtu.dsy.homeworkmanagement.model.User;
import com.bjtu.dsy.homeworkmanagement.util.response.TeacherResponse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {
    private TeacherMapper teacherMapper;
    private HomeworkMapper homeworkMapper;
    private SubmitMapper submitMapper;
    private StudentMapper studentMapper;

    @Autowired
    public TeacherService(TeacherMapper teacherMapper, HomeworkMapper homeworkMapper, SubmitMapper submitMapper, StudentMapper studentMapper) {
        this.teacherMapper = teacherMapper;
        this.homeworkMapper = homeworkMapper;
        this.submitMapper = submitMapper;
        this.studentMapper = studentMapper;
    }

    public HomeworkListResponse homeworkList(int id, String token) {
        User user = teacherMapper.getTeacher(id);
        if (user == null || (!user.getToken().equals(token))) {
            return new HomeworkListResponse(-1, "身份验证错误！", null);
        }

        List<HomeworkListResponse.info> infoList = new ArrayList<>();
        List<Homework> homeworkList = homeworkMapper.getHomeworkByTeacherId(id);
        for (Homework homework : homeworkList) {
            infoList.add(new HomeworkListResponse.info(homework.getId(), homework.getTitle(), submitMapper.submitCount(homework.getId())));
        }
        return new HomeworkListResponse(0, "成功！", infoList);
    }

    public SubmitListResponse submitList(int id, int homeworkId, String token) {
        User user = teacherMapper.getTeacher(id);
        if (user == null || (!user.getToken().equals(token))) {
            return new SubmitListResponse(-1, "身份验证错误！", null);
        }

        List<SubmitListResponse.info> infoList = new ArrayList<>();
        List<Submit> submitList = submitMapper.getSubmitByHomeworkId(homeworkId);
        for (Submit submit : submitList) {
            SubmitListResponse.info it = new SubmitListResponse.info();
            it.setSubmitId(submit.getId());
            it.setStudentId(submit.getStudentId());
            it.setStudentName(studentMapper.getStudent(submit.getStudentId()).getName());
            int type;
            if (submit.getComment() == null || submit.getComment().equals("")) {
                type = 2;
            } else {
                type = 3;
            }
            it.setType(type);
            infoList.add(it);
        }
        return new SubmitListResponse(0, "成功！", infoList);
    }

    public SubmitInfoResponse submitInfo(int id, int submitId, String token) {
        User user = teacherMapper.getTeacher(id);
        if (user == null || (!user.getToken().equals(token))) {
            SubmitInfoResponse response = new SubmitInfoResponse();
            response.setState(-1);
            response.setMessage("身份验证错误！");
            return response;
        }

        Submit submit = submitMapper.getSubmitById(submitId);
        SubmitInfoResponse response = new SubmitInfoResponse(0,
                "成功！",
                submitId, submit.getStudentId(),
                0,
                submit.getTitle(),
                submit.getContent(),
                submit.getScore(),
                submit.getComment());
        int type;
        if (submit.getComment() == null || submit.getComment().equals("")) {
            type = 2;
        } else {
            type = 3;
        }
        response.setType(type);
        return response;
    }

    public ReviewResponse review(int id, int submitId, int score, String comment, String token) {
        User user = teacherMapper.getTeacher(id);
        if (user == null || (!user.getToken().equals(token))) {
            return new ReviewResponse(-1, "身份验证错误！");
        }
        submitMapper.review(submitId, score, comment);
        return new ReviewResponse(0, "成功！");
    }

    public AddHomeworkResponse addHomework(int id, String title, String content, String token) {
        User user = teacherMapper.getTeacher(id);
        if (user == null || (!user.getToken().equals(token))) {
            return new AddHomeworkResponse(-1, "身份验证错误！");
        }
        homeworkMapper.addHomework(id, title, content);
        return new AddHomeworkResponse(0, "成功！");
    }
}
