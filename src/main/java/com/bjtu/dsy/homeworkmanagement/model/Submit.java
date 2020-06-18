package com.bjtu.dsy.homeworkmanagement.model;

import lombok.Data;

import java.util.Date;

@Data
public class Submit {
    int id;
    int studentId;
    int homeworkId;
    int teacherId;
    String title;
    String content;
    Date createTime;
    int score;
    String comment;
}
