package com.bjtu.dsy.homeworkmanagement.mapper;

import com.bjtu.dsy.homeworkmanagement.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TeacherMapper {
    @Select("SELECT * FROM teacher WHERE id = #{id};")
    User getTeacher(int id);

    @Update("UPDATE teacher SET token = #{token} WHERE id = #{id};")
    void updateToken(User user);

    @Insert("INSERT INTO teacher(id, name, password) VALUES (#{id}, #{name}, #{password});")
    void register(int id, String name, String password);
}
