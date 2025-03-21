package com.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "question_list")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;  // 问题类型
    private String title; // 问题标题
    private String description; // 描述

    @ElementCollection
    @CollectionTable(name = "question_tags", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "tag")
    private List<String> tags; // 标签

    @ElementCollection
    @CollectionTable(name = "question_imgs", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "url")
    private List<Img> imgs; // 图片

    @ElementCollection
    @CollectionTable(name = "question_comments", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "comment")
    private List<String> displayComments; // 评论
}
