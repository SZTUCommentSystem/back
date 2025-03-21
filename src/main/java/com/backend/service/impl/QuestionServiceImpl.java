package com.backend.service.impl;

import com.backend.entity.Question;
import com.backend.mapper.QuestionMapper;
import com.backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<Question> getAllQuestions() {
        return questionMapper.getAllQuestions();
    }
    @Override
    public Question saveQuestion(Question question) {
        // 保存问题到数据库，并获取生成的 question_id
        questionMapper.insertQuestion(question);

        // 确保 question_id 已正确生成
        Long questionId = question.getId();
        if (questionId == null) {
            throw new RuntimeException("Failed to generate question ID");
        }

        // 保存 tags、imgs 和 comments
        if (question.getTags() != null && !question.getTags().isEmpty()) {
            questionMapper.insertTags(questionId, question.getTags());
        }
        if (question.getImgs() != null && !question.getImgs().isEmpty()) {
            questionMapper.insertImgs(questionId, question.getImgs());
        }
        if (question.getDisplayComments() != null && !question.getDisplayComments().isEmpty()) {
            questionMapper.insertComments(questionId, question.getDisplayComments());
        }

        // 返回保存后的 Question 对象
        return question;
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionMapper.getQuestionById(id);
    }
    @Override
    public boolean deleteQuestionById(Long id) {
        try {
            // 调用 Mapper 删除题目
            questionMapper.deleteQuestionById(id);
            return true;  // 如果删除成功，返回 true
        } catch (Exception e) {
            return false;  // 如果出现异常，返回 false
        }
    }
}
