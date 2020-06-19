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

    public User getUser(int id, boolean teacher) {
        User user;
        if (teacher) {
            user = teacherMapper.getTeacher(id);
        } else {
            user = studentMapper.getStudent(id);
        }
        return user;
    }

    public LogResponse login(int id, String password, boolean teacher) {
        User user = getUser(id, teacher);
        if (user == null) {
            return new LogResponse(-1, id, teacher, "用户不存在！", "");
        }
        if (!user.getPassword().equals(password)) {
            return new LogResponse(-2, id, teacher, "密码错误！", "");
        }

        TokenUtil.setToken(user);
        if (teacher) {
            teacherMapper.updateToken(user);
        } else {
            studentMapper.updateToken(user);
        }
        return new LogResponse(0, id, teacher, "登陆成功！", user.getToken());
    }

    public LogResponse logout(int id, String token, boolean teacher) {
        User user = getUser(id, teacher);
        if (user == null) {
            return new LogResponse(-1, id, teacher, "用户不存在！", "");
        }
        if (!user.getToken().equals(token)) {
            return new LogResponse(-100, id, teacher, "身份验证错误！", "");
        }

        user.setToken("");
        if (teacher) {
            teacherMapper.updateToken(user);
        } else {
            studentMapper.updateToken(user);
        }
        return new LogResponse(0, id, teacher, "登出成功！", "");
    }

    public RegisterResponse register(int id, String name, String password, boolean teacher) {
        User user = getUser(id, teacher);
        if (user != null) {
            return new RegisterResponse(-1, "用户已存在！");
        }

        if (teacher) {
            teacherMapper.register(id, name, password);
        } else {
            studentMapper.register(id, name, password);
        }
        return new RegisterResponse(0, "注册成功！");
    }
}
