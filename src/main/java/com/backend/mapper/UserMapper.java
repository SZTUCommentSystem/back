package com.backend.mapper;
import com.backend.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户（包含关联角色）
     */
    @Select("SELECT u.user_id, u.username, u.password, u.token " +
            "FROM users u " +
            "WHERE u.username = #{username}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "roles", column = "user_id",
                    many = @Many(select = "com.backend.mapper.RoleMapper.getRolesByUserId"))
    })
    User selectByUsername(String username);

    @Select("SELECT u.user_id, u.username, u.password, u.token " +
            "FROM users u " +
            "WHERE u.token = #{token}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "roles", column = "user_id",
                    many = @Many(select = "com.backend.mapper.RoleMapper.getRolesByUserId"))
    })
    User selectByToken(String token);
}
