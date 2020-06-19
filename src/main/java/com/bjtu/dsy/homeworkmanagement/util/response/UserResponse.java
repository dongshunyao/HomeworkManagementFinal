package com.bjtu.dsy.homeworkmanagement.util.response;

import lombok.AllArgsConstructor;
import lombok.Data;

public class UserResponse {
    @Data
    @AllArgsConstructor
    public static class LogResponse {
        int state;
        int id;
        boolean teacher;
        String message;
        String token;
    }

    @Data
    @AllArgsConstructor
    public static class RegisterResponse {
        int state;
        String message;
    }
}
