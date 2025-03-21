package com.backend.service.impl;

import com.backend.mapper.TaskMapper;
import com.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.backend.entity.Task;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private final TaskMapper taskMapper;
    @Autowired // 构造器注入（推荐方式）
    public TaskServiceImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    /**
     * 获取所有任务
     * @return
     */
    @Override
    public List<Task> getTaskList() {
        return taskMapper.selectAllTasks();
    }

    /**
     * 根据id获取任务
     * @param id 任务唯一标识
     * @return
     */
    @Override
    public Task getTaskById(Integer id) {
        // 直接调用Mapper查询
        Task task = taskMapper.selectTaskById(id);
        // 空值处理建议（可根据业务需求调整）
        if (task == null) {
            throw new RuntimeException(); // ➕ 推荐自定义异常
        }
        return task;
    }

    /**
     * 根据ids获取任务
     * @param ids 任务唯一标识
     * @return
     */
    @Override
    public List<Task> getTasksByIds(List<Integer> ids) {
        return taskMapper.selectTasksByIds(ids);
    }
    /**
     * 提交作业
     * @param
     * @return
     */
    @Override
    public void insertTask(Task task) {
        taskMapper.insertTask(task);
    }
    // 定时更新任务状态，每天00:00执行
    @Scheduled(cron = "0 0 0 * * ?")  // 每天00:00执行
    public void updateTaskStatuses() {
        List<Task> tasks = taskMapper.selectAllTasks();
        Date currentDate = new Date();

        for (Task task : tasks) {
            if (task.getStartTime() != null && task.getEndTime() != null) {
                task.setPublishStatus(getPublishStatus(task.getStartTime(), task.getEndTime(), currentDate));
                taskMapper.insertTask(task);
            }
        }
    }

    private Integer getPublishStatus(Date startTime, Date endTime, Date currentDate) {
        if (currentDate.before(startTime)) {
            return 0;  // 未发布
        } else if (currentDate.after(endTime)) {
            return 2;  // 已结束
        } else {
            return 1;  // 已发布
        }
    }
}