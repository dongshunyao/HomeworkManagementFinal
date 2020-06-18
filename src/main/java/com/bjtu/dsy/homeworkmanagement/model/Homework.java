package com.bjtu.dsy.homeworkmanagement.model;

import lombok.Data;

@Data
public class Homework {
    int id;
    int teacherId;
    String title;
    String content;
}
