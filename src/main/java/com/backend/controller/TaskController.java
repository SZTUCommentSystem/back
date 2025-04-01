package com.backend.controller;


import com.backend.common.R;
import com.backend.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.entity.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务列表API控制器
 * 处理/home/taskList相关请求
 */

@RestController
@RequestMapping("/home")
public class TaskController {

    private final TaskService taskService;

    // 构造器注入（推荐方式）
    public TaskController(TaskService taskListService) {
        this.taskService = taskListService;
    }

    /**
     * 获取任务列表API
     * GET /home/taskList
     *
     * @return 统一格式的JSON响应：
     * {
     * "code": 200,
     * "msg": "success",
     * "data": [任务列表数据]
     * }
     */
    @GetMapping("/taskList")
    public R<List<Task>> getTaskList() {
        List<Task> tasks = taskService.getTaskList();
        return R.success(tasks);
    }

    /**
     * 根据id获取任务
     */
    @GetMapping("/taskDetail")
    public R<Task> getTaskDetail(@RequestParam("id") Integer taskId) {
        try {
            Task task = taskService.getTaskById(taskId);
            return R.success(task);
        } catch (RuntimeException e) {
            return R.error("任务不存在");
        }
    }

    /**
     * 根据ids获取任务
     */
    @GetMapping("/questionDetailList")
    public R<List<Task>> getTasksByIds(@RequestParam("ids") List<Integer> ids) {
        List<Task> tasks = taskService.getTasksByIds(ids);
        if (!tasks.isEmpty()) {
            return R.success(tasks);
        }
        return R.error("id错误");
    }

    /**
     * 提交作业接口
     */

    @PostMapping("/submitTask")
    public R<Task> submitTask(@RequestBody Task task) {
        try {
            if (task.getTitle() == null || task.getTitle().isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }
            taskService.insertTask(task);
            return R.success(null);
        } catch (Exception e) {
            return R.error("任务提交失败：");
        }
    }
}