package com.backend.controller;

import com.backend.common.R;
import com.backend.entity.Student;
import com.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 获取学生列表
     * @return 学生列表
     */
    @GetMapping("/studentList")
    public R<List<Student>> getStudentList() {
        List<Student> students = studentService.getAllStudents();
        return R.success(students);
    }

    /**
     * 根据 id (实际查询 student_id) 获取单个学生信息
     * @param id 前端传递的 id (实际为 student_id)
     * @return 学生信息
     */
    @GetMapping("/studentInfo")
    public R<Student> getStudentInfo(@RequestParam("id") String id) {
        // 实际查询 student_id
        Student student = studentService.getStudentByStudentId(id);
        if (student != null) {
            return R.success(student);
        } else {
            return R.error("学生不存在");
        }
    }
}
