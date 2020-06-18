package com.bjtu.dsy.homeworkmanagement.mapper;

import com.bjtu.dsy.homeworkmanagement.model.Submit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SubmitMapper {
    @Select("SELECT * FROM submit WHERE homework_id = #{homeworkId} AND student_id = #{studentId};")
    Submit getSubmit(int homeworkId, int studentId);

    @Update("UPDATE submit SET title = #{title}, content = #{content} WHERE id = #{id};")
    void update(int id, String title, String content);

    @Insert("INSERT INTO submit(student_id, homework_id, title, content) VALUES (#{studentId}, #{homeworkId}, #{title}, #{content});")
    void insert(int studentId, int homeworkId, String title, String content);
}
