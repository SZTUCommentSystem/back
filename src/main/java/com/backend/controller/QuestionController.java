package com.backend.controller;

import com.backend.common.R;
import com.backend.entity.Question;
import com.backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/home")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 获取题目列表
     * @return
     */
    @GetMapping("/questionList")
    public R<List<Question>> getQuestionList() {
        List<Question> questions = questionService.getAllQuestions();
        return R.success(questions);
    }
    /**
     * 提交问题
     * @param question 提交的题目
     * @return 提交成功的响应
     */
    @PostMapping("/submitQuestion")
    public R<Question> submitQuestion(@RequestBody Question question) {
        // 保存题目和相关数据，并返回保存后的 Question 对象
        Question savedQuestion = questionService.saveQuestion(question);
        return R.success(savedQuestion);
    }

    /**
     * 根据 id 查询题目详情
     * @param id 题目 ID
     * @return 题目详情
     */
    @GetMapping("/questionDetail")
    public R<Question> getQuestionDetail(@RequestParam Long id) {
        Question question = questionService.getQuestionById(id);
        if (question == null) {
            return R.error("题目不存在");
        }
        return R.success(question);
    }
    /**
     * 根据题目 ID 列表获取题目详情
     * @param ids 题目 ID 列表
     * @return 题目详情
     */
    @PostMapping("/taskQuestionList")
    public R<List<Question>> getTaskQuestionList(@RequestParam List<Long> ids) {
        try {
            // 如果 ID 列表为空，返回错误信息
            if (ids == null || ids.isEmpty()) {
                return R.error("ID 列表不能为空");
            }

            // 获取所有题目
            List<Question> allQuestions = questionService.getAllQuestions();

            // 筛选出匹配的题目
            List<Question> filteredQuestions = allQuestions.stream()
                    .filter(question -> ids.contains(question.getId()))
                    .collect(Collectors.toList());

            // 如果没有找到匹配的题目，返回错误信息
            if (filteredQuestions.isEmpty()) {
                return R.error("没有找到匹配的题目");
            }

            // 返回成功响应
            return R.success(filteredQuestions);

        } catch (Exception e) {
            // 捕获异常并返回错误响应
            return R.error("查询失败：" + e.getMessage());
        }
    }

    /**
     * 根据 ID 删除题目
     * @param id 题目 ID
     * @return 删除成功或失败的响应
     */
    @DeleteMapping("/deleteQuestion")
    public R<Question> deleteQuestion(@RequestParam Long id) {
        try {

            // 删除操作
            boolean isDeleted = questionService.deleteQuestionById(id);
            if (isDeleted) {
                return R.success(null); // 删除成功，返回已删除的题目数据
            } else {
                return R.error("删除失败，题目不存在");
            }
        } catch (Exception e) {
            return R.error("删除失败：");
        }
    }
}
