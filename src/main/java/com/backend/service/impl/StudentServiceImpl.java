package com.backend.service.impl;

import com.backend.entity.Student;
import com.backend.mapper.StudentMapper;
import com.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> getAllStudents() {
        return studentMapper.getAllStudents();
    }

    @Override
    public Student getStudentByStudentId(String studentId) {
        return studentMapper.getStudentByStudentId(studentId);
    }
}
