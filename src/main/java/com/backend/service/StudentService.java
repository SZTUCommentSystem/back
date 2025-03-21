package com.backend.service;

import com.backend.entity.Student;

import java.util.List;

public interface StudentService {
    /**
     * 获取所有学生
     * @return 学生列表
     */
    List<Student> getAllStudents();
    /**
     * 根据 student_id 获取学生信息
     * @param studentId 学生学号
     * @return 学生对象
     */
    Student getStudentByStudentId(String studentId);
}

