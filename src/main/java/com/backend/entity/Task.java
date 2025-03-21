package com.backend.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 任务列表实体类
 * 映射数据库taskList表结构
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // 序列化时忽略null字段
public class Task {
    /**
     * 任务唯一标识（主键）
     */
    private Integer id;

    /**
     * 任务标题（最大长度255字符）
     */
    private String title;

    /**
     * 标签数组（JSON格式存储）
     * 示例：["标签1", "标签2"]
     */
    private List<String> tags;

    /**
     * 负责教师姓名（最大长度50字符）
     */
    private String teacher;

    /**
     * 关联题目ID数组（JSON格式存储）
     * 示例：[1,3]
     */
    private List<Integer> selectedQuestion;

    /**
     * 发布状态（0-未发布 1-已发布 2-已结束）
     */
    @JsonProperty("PublishStatus")
    private Integer PublishStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 是否作为下拉选项（默认false）
     * 对应数据库字段：IsDropList
     */
    @JsonProperty("IsDropList")
    private Boolean IsDropList = false; // 默认为 false (即 0)

    //    /**
//     * 记录创建时间（数据库自动生成）
//     */
//    private Date createTime;
//
//    /**
//     * 最后更新时间（数据库自动更新）
//     */
//    private Date updateTime;
// 自定义 JSON 输出 DeadLine 字段
    @JsonGetter("deadline")
    public List<String> getDeadLine() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String start = (startTime != null) ? sdf.format(startTime) : null;
        String end = (endTime != null) ? sdf.format(endTime) : null;

        return Arrays.asList(start, end);  // ✅ 替换 List.of()
    }

    @JsonSetter("deadline")
    public void setDeadLine(List<String> deadLine) {
        if (deadLine != null && deadLine.size() == 2) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                this.startTime = sdf.parse(deadLine.get(0));
                this.endTime = sdf.parse(deadLine.get(1));

                // 根据时间设置发布状态
                this.PublishStatus = getPublishStatus(this.startTime, this.endTime);

            } catch (Exception e) {
                // 处理日期解析异常
                e.printStackTrace();
            }
        }
    }

    // 根据当前时间和任务的起止时间计算发布状态
    private Integer getPublishStatus(Date startTime, Date endTime) {
        Date currentDate = new Date();  // 获取当前时间

        if (currentDate.before(startTime)) {
            // 当前时间早于任务开始时间，表示任务未发布
            return 0;  // 未发布
        } else if (currentDate.after(endTime)) {
            // 当前时间晚于任务结束时间，表示任务已结束
            return 2;  // 已结束
        } else {
            // 当前时间在任务的开始和结束之间，表示任务已发布
            return 1;  // 已发布
        }
    }
    /**
     * 任务简介（可选）
     */
    private String description;  // 新增字段：任务简介
}
