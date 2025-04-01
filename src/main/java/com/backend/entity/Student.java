package com.backend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

// 该类表示学生实体，使用 Lombok 注解自动生成 getter、setter、构造函数等方法
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    // 日志记录器，后续可用于记录该类相关的日志信息
    // 若要使用，需引入相应的日志依赖和配置
    // private static final Logger logger = LoggerFactory.getLogger(Student.class);

    /**
     * 学生的唯一标识，作为主键，采用自增策略生成
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 学生的姓名，不能为空
     */
    @Column(nullable = false)
    private String name;

    /**
     * 学生的学号，不能为空
     */
    @Column(nullable = false)
    private String studentId;

    /**
     * 学生所在的班级，由于 "class" 是 Java 关键字，使用 @JsonProperty 和 @Column 注解进行处理
     */
    @JsonProperty("class")
    @Column(nullable = false, name = "class")
    private String className; // "class" 是 Java 关键字，避免冲突改名

    /**
     * 学生的状态，使用枚举类型表示，不能为空
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    /**
     * 学生的成绩，不能为空
     */
    @Column(nullable = false)
    private int score;

    /**
     * 学生的状态枚举，包含未提交、已提交、已批改三种状态
     */
    public enum Status {
        未提交, 已提交, 已批改
    }
}