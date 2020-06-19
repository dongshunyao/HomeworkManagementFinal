package com.bjtu.dsy.homeworkmanagement.service;

import com.bjtu.dsy.homeworkmanagement.mapper.HomeworkMapper;
import com.bjtu.dsy.homeworkmanagement.mapper.StudentMapper;
import com.bjtu.dsy.homeworkmanagement.mapper.SubmitMapper;
import com.bjtu.dsy.homeworkmanagement.mapper.TeacherMapper;
import com.bjtu.dsy.homeworkmanagement.model.Homework;
import com.bjtu.dsy.homeworkmanagement.model.Submit;
import com.bjtu.dsy.homeworkmanagement.model.User;
import com.bjtu.dsy.homeworkmanagement.util.response.StudentResponse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private StudentMapper studentMapper;
    private HomeworkMapper homeworkMapper;
    private SubmitMapper submitMapper;
    private TeacherMapper teacherMapper;

    @Autowired
    public StudentService(StudentMapper studentMapper, HomeworkMapper homeworkMapper, SubmitMapper submitMapper, TeacherMapper teacherMapper) {
        this.studentMapper = studentMapper;
        this.homeworkMapper = homeworkMapper;
        this.submitMapper = submitMapper;
        this.teacherMapper = teacherMapper;
    }

    public HomeworkListResponse homeworkList(int id, String token) {
        User user = studentMapper.getStudent(id);
        if (user == null || (!user.getToken().equals(token))) {
            return new HomeworkListResponse(-1, "身份验证错误！", null);
        }

        List<HomeworkListResponse.info> infoList = new ArrayList<>();
        List<Homework> homeworkList = homeworkMapper.getAllHomework();
        for (Homework homework : homeworkList) {
            HomeworkListResponse.info it = new HomeworkListResponse.info();
            it.setHomeworkId(homework.getId());
            it.setTeacherName(teacherMapper.getTeacher(homework.getTeacherId()).getName());
            it.setTitle(homework.getTitle());
            int type = 1;
            Submit submit = submitMapper.getSubmit(homework.getId(), user.getId());
            if (submit != null) {
                if (submit.getComment() == null || submit.getComment().equals("")) {
                    type = 2;
                } else {
                    type = 3;
                }
            }
            it.setType(type);
            infoList.add(it);
        }
        return new HomeworkListResponse(0, "成功！", infoList);
    }

    public HomeworkInfoResponse homeworkInfo(int id, int homeworkId, String token) {
        User user = studentMapper.getStudent(id);
        if (user == null || (!user.getToken().equals(token))) {
            HomeworkInfoResponse response = new HomeworkInfoResponse();
            response.setState(-1);
            response.setMessage("身份验证错误！");
            return response;
        }

        Homework homework = homeworkMapper.getHomework(homeworkId);
        Submit submit = submitMapper.getSubmit(homeworkId, user.getId());
        HomeworkInfoResponse infoResponse = new HomeworkInfoResponse();
        infoResponse.setState(0);
        infoResponse.setMessage("成功");
        infoResponse.setHomeworkId(homework.getId());
        infoResponse.setHomeworkTitle(homework.getTitle());
        infoResponse.setHomeworkContent(homework.getContent());
        if (submit == null) {
            infoResponse.setType(1);
        } else if (submit.getComment() == null || submit.getComment().equals("")) {
            infoResponse.setType(2);
            infoResponse.setSubmitTitle(submit.getTitle());
            infoResponse.setSubmitContent(submit.getContent());
        } else {
            infoResponse.setType(3);
            infoResponse.setSubmitTitle(submit.getTitle());
            infoResponse.setSubmitContent(submit.getContent());
            infoResponse.setScore(submit.getScore());
            infoResponse.setComment(submit.getComment());
        }
        return infoResponse;
    }

    public SubmitResponse submit(int id, int homeworkId, String title, String content, String token) {
        User user = studentMapper.getStudent(id);
        if (user == null || (!user.getToken().equals(token))) {
            return new SubmitResponse(-1, "身份验证错误！");
        }

        Submit submit = submitMapper.getSubmit(homeworkId, id);
        if (submit == null) {
            submitMapper.insert(id, homeworkId, title, content);
        } else {
            submitMapper.update(submit.getId(), title, content);
        }
        return new SubmitResponse(0, "成功！");
    }
}
