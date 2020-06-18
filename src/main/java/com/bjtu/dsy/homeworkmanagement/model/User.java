package com.bjtu.dsy.homeworkmanagement.model;

import lombok.Data;

@Data
public class User {
    int id;
    String name;
    String password;
    String token;
}
