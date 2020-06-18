package com.bjtu.dsy.homeworkmanagement.mapper;

import com.bjtu.dsy.homeworkmanagement.model.Homework;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface HomeworkMapper {
    @Select("SELECT * FROM homework;")
    List<Homework> getAllHomework();

    @Select("SELECT * FROM homework WHERE id = #{id};")
    Homework getHomework(int id);

    @Select("SELECT * FROM homework WHERE teacher_id = #{teacherId};")
    List<Homework> getHomeworkByTeacherId(int teacherId);

    @Insert("INSERT INTO homework(teacher_id, title, content) VALUES (#{teacherId}, #{title}, #{content});")
    void addHomework(int teacherId, String title, String content);
}
