package com.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * 角色实体类（数据库映射对象）
 *
 * @date 2025-03-06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    private Integer id;      // 对应 SQL 中的 role_id
    private String name;     // 对应 SQL 中的 role_name
    private Integer people;  // 对应 SQL 中的 people
}