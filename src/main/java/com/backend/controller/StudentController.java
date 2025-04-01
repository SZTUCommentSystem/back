package com.backend.controller;

import com.backend.common.R;
import com.backend.entity.Student;
import com.backend.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 学生控制器类，处理与学生相关的请求
@RestController
@RequestMapping("/home")
public class StudentController {

    // 自动注入学生服务类
    @Autowired
    private StudentService studentService;

    // 创建日志记录器
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    /**
     * 获取学生列表
     * 该方法会调用学生服务类的 getAllStudents 方法获取所有学生信息
     * 并将结果封装在通用返回类 R 中返回
     * @return 包含学生列表的通用返回对象
     */
    @GetMapping("/studentList")
    public R<List<Student>> getStudentList() {
        // 记录开始获取学生列表的日志
        logger.info("开始获取学生列表");
        try {
            // 调用服务类方法获取学生列表
            List<Student> students = studentService.getAllStudents();
            // 记录成功获取学生列表的日志
            logger.info("成功获取学生列表，共 {} 条记录", students.size());
            return R.success(students);
        } catch (Exception e) {
            // 记录获取学生列表失败的日志
            logger.error("获取学生列表失败: {}", e.getMessage());
            return R.error("获取学生列表失败");
        }
    }

    /**
     * 根据 id (实际查询 student_id) 获取单个学生信息
     * 该方法会调用学生服务类的 getStudentByStudentId 方法根据学生ID获取学生信息
     * 并将结果封装在通用返回类 R 中返回
     * @param id 前端传递的 id (实际为 student_id)
     * @return 包含学生信息的通用返回对象
     */
    @GetMapping("/studentInfo")
    public R<Student> getStudentInfo(@RequestParam("id") String id) {
        // 记录开始获取学生信息的日志
        logger.info("开始获取学生信息，学生ID: {}", id);
        // 实际查询 student_id
        Student student = studentService.getStudentByStudentId(id);
        if (student != null) {
            // 记录成功获取学生信息的日志
            logger.info("成功获取学生信息，学生姓名: {}", student.getName());
            return R.success(student);
        } else {
            // 记录学生不存在的日志
            logger.info("学生不存在，学生ID: {}", id);
            return R.error("学生不存在");
        }
    }
}