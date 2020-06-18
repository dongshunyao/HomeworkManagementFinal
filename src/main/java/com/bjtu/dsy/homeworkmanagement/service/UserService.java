package com.bjtu.dsy.homeworkmanagement.service;

import com.bjtu.dsy.homeworkmanagement.mapper.StudentMapper;
import com.bjtu.dsy.homeworkmanagement.mapper.TeacherMapper;
import com.bjtu.dsy.homeworkmanagement.model.User;
import com.bjtu.dsy.homeworkmanagement.util.TokenUtil;
import com.bjtu.dsy.homeworkmanagement.util.response.UserResponse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private TeacherMapper teacherMapper;
    private StudentMapper studentMapper;

    @Autowired
    public UserService(TeacherMapper teacherMapper, StudentMapper studentMapper) {
        this.teacherMapper = teacherMapper;
        this.studentMapper = studentMapper;
    }

    public User getUser(int id, boolean isTeacher) {
        User user;
        if (isTeacher) {
            user = teacherMapper.getTeacher(id);
        } else {
            user = studentMapper.getStudent(id);
        }
        return user;
    }

    public LogResponse login(int id, String password, boolean isTeacher) {
        User user = getUser(id, isTeacher);
        if (user == null) {
            return new LogResponse(-1, id, isTeacher, "用户不存在！", "");
        }
        if (!user.getPassword().equals(password)) {
            return new LogResponse(-2, id, isTeacher, "密码错误！", "");
        }

        TokenUtil.setToken(user);
        if (isTeacher) {
            teacherMapper.updateToken(user);
        } else {
            studentMapper.updateToken(user);
        }
        return new LogResponse(0, id, isTeacher, "登陆成功！", user.getToken());
    }

    public LogResponse logout(int id, String token, boolean isTeacher) {
        User user = getUser(id, isTeacher);
        if (user == null) {
            return new LogResponse(-1, id, isTeacher, "用户不存在！", "");
        }
        if (!user.getToken().equals(token)) {
            return new LogResponse(-100, id, isTeacher, "身份验证错误！", "");
        }

        user.setToken("");
        if (isTeacher) {
            teacherMapper.updateToken(user);
        } else {
            studentMapper.updateToken(user);
        }
        return new LogResponse(0, id, isTeacher, "登出成功！", "");
    }

    public RegisterResponse register(int id, String name, String password, boolean isTeacher) {
        User user = getUser(id, isTeacher);
        if (user != null) {
            return new RegisterResponse(-1, "用户已存在！");
        }

        if (isTeacher) {
            teacherMapper.register(id, name, password);
        } else {
            studentMapper.register(id, name, password);
        }
        return new RegisterResponse(0, "注册成功！");
    }
}
