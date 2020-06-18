package com.bjtu.dsy.homeworkmanagement.util.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class TeacherResponse {
    @Data
    @AllArgsConstructor
    public static class HomeworkListResponse {
        int state;
        String message;
        List<TeacherResponse.HomeworkListResponse.info> list;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class info {
            int homeworkId;
            String title;
            int submitCount;
        }
    }

    @Data
    @AllArgsConstructor
    public static class SubmitListResponse {
        int state;
        String message;
        List<TeacherResponse.SubmitListResponse.info> list;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class info {
            int submitId;
            int studentId;
            String studentName;
            int type;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SubmitInfoResponse {
        int state;
        String message;
        int submitId;
        int studentId;
        int type;
        String submitTitle;
        String submitContent;
        int score;
        String comment;
    }

    @Data
    @AllArgsConstructor
    public static class ReviewResponse {
        int state;
        String message;
    }

    @Data
    @AllArgsConstructor
    public static class AddHomeworkResponse {
        int state;
        String message;
    }
}
