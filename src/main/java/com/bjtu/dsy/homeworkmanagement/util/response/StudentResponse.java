package com.bjtu.dsy.homeworkmanagement.util.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class StudentResponse {
    @Data
    @AllArgsConstructor
    public static class HomeworkListResponse {
        int state;
        String message;
        List<HomeworkListResponse.info> list;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class info {
            int homeworkId;
            String teacherName;
            String title;
            int type;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeworkInfoResponse {
        int state;
        String message;
        int homeworkId;
        String homeworkTitle;
        String homeworkContent;
        int type;
        String submitTitle;
        String submitContent;
        int score;
        String comment;
    }

    @Data
    @AllArgsConstructor
    public static class SubmitResponse {
        int state;
        String message;
    }
}
