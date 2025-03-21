package com.backend.mapper;

import com.backend.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    /**
     * 查询所有学生
     * @return 学生列表
     */
    @Select("SELECT id, name, student_id, class, status, score FROM student_list")
    @Results({
            @Result(property = "className", column = "class")
    })
    List<Student> getAllStudents();
    /**
     * 根据 student_id 查询单个学生
     * @return 学生信息
     */
    @Select("SELECT id, name, student_id, class, status, score FROM student_list WHERE student_id = #{studentId}")
    @Results({
            @Result(property = "className", column = "class")
    })
    Student getStudentByStudentId(String studentId);
}