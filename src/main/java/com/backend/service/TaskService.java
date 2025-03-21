package com.backend.service;

import com.backend.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 任务列表服务层接口
 * 定义业务逻辑方法
 */

public interface TaskService {
    /**
     * 获取所有任务列表
     * @return 包含任务详情的列表
     */
    List<Task> getTaskList();

    /**
     * 根据ID获取完整任务数据
     * @param id 任务唯一标识
     * @return 任务实体（包含所有字段）
     */
    Task getTaskById(Integer id);

    /**
     * 根据多个ID获取任务列表
     * @param ids 任务ID数组
     * @return 任务列表
     */
    List<Task> getTasksByIds(List<Integer> ids);

    /**
     * 提交作业
     * @param
     * @return 提交结果，成功返回true，失败返回false
     */
    void insertTask(Task task);
}
