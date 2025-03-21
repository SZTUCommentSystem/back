package com.backend.mapper;
import com.backend.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {
    /**
     * 根据角色ID获取详情
     * @param
     */
    @Select("SELECT r.role_id AS id, r.role_name AS name, r.people " +
            "FROM roles r " +
            "JOIN user_roles ur ON r.role_id = ur.role_id " +
            "WHERE ur.user_id = #{userId}")
    List<Role> getRolesByUserId(Integer userId);
}