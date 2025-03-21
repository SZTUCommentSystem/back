package com.backend.service;

import com.backend.entity.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions();
    // 保存题目
    Question saveQuestion(Question question);

    /**
     * 根据 id 查询题目详情
     * @param id 题目 ID
     * @return 题目对象
     */
    Question getQuestionById(Long id);

    boolean deleteQuestionById(Long id);
}
