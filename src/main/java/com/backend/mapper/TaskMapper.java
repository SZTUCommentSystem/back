package com.backend.mapper;

import com.backend.config.MyBatisJsonTypeHandler;
import org.apache.ibatis.annotations.*;
import com.backend.entity.Task;

import java.util.List;
import java.util.Map;

/**
 * 任务列表数据访问层
 * 提供数据库操作接口
 */
@Mapper
public interface TaskMapper {

    /**
     * 查询所有任务列表
     *
     * @return 任务实体集合
     * <p>
     * 结果映射说明：
     * - 处理字段名到属性的映射（下划线转驼峰）
     * - 处理JSON类型字段的转换
     */
    @Results({
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            // 处理JSON字段（使用自定义类型处理器）
            @Result(property = "tags", column = "tags",
                    typeHandler = MyBatisJsonTypeHandler.class),
            @Result(property = "selectedQuestion", column = "selectedQuestion",
                    typeHandler = MyBatisJsonTypeHandler.class)
    })
    @Select("SELECT id, title, tags, teacher, selectedQuestion, PublishStatus, IsDropList, start_time, end_time FROM backend.task")
    List<Task> selectAllTasks();


    /**
     * 通过主键ID查询任务详情
     *
     * @param id 任务ID
     * @return 完整任务实体（包含JSON字段）
     * <p>
     * 结果映射说明：
     * - 字段名映射（下划线转驼峰）
     * - JSON字段特殊处理
     */
    @Results({
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "tags", column = "tags",
                    typeHandler = MyBatisJsonTypeHandler.class),
            @Result(property = "selectedQuestion", column = "selectedQuestion",
                    typeHandler = MyBatisJsonTypeHandler.class)
    })
    @Select("SELECT * FROM backend.task WHERE id = #{id}")
    Task selectTaskById(Integer id);

    /**
     * 根据多个ID查询任务列表
     *
     * @param ids 任务ID数组
     * @return 任务列表
     */
    @Results({
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "tags", column = "tags",
                    typeHandler = MyBatisJsonTypeHandler.class),
            @Result(property = "selectedQuestion", column = "selectedQuestion",
                    typeHandler = MyBatisJsonTypeHandler.class)
    })
    List<Task> selectTasksByIds(List<Integer> ids);

    /**
     * 插入作业信息到数据库
     *
     * @param
     * @return
     */
    void insertTask(Task task);
}