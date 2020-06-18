package com.bjtu.dsy.homeworkmanagement.model;

import lombok.Data;

@Data
public class Submit {
    int id;
    int studentId;
    int homeworkId;
    String title;
    String content;
    int score;
    String comment;
}
