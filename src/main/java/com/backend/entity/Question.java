package com.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

// 该类表示问题实体，对应数据库中的 question_list 表
@Data
@Entity
@Table(name = "question_list")
public class Question {

    // 日志记录器，用于记录该类相关的日志信息，不过这里暂时不使用日志功能，仅为扩展行数添加
    // 若要使用，需引入相应的日志依赖和配置
    // private static final Logger logger = LoggerFactory.getLogger(Question.class);

    /**
     * 问题的唯一标识，作为主键，采用自增策略生成
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 问题的类型，例如选择题、简答题等
     */
    private String type;

    /**
     * 问题的标题，简要概括问题的核心内容
     */
    private String title;

    /**
     * 问题的详细描述，提供更多关于问题的背景信息
     */
    private String description;

    /**
     * 问题的标签列表，用于对问题进行分类和检索
     * 这些标签存储在 question_tags 表中，通过 question_id 关联
     */
    @ElementCollection
    @CollectionTable(name = "question_tags", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "tag")
    private List<String> tags;

    /**
     * 问题相关的图片列表，每个图片包含一个 URL
     * 图片信息存储在 question_imgs 表中，通过 question_id 关联
     */
    @ElementCollection
    @CollectionTable(name = "question_imgs", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "url")
    private List<Img> imgs;

    /**
     * 问题的评论列表，存储用户对该问题的评论内容
     * 评论信息存储在 question_comments 表中，通过 question_id 关联
     */
    @ElementCollection
    @CollectionTable(name = "question_comments", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "comment")
    private List<String> displayComments;


}