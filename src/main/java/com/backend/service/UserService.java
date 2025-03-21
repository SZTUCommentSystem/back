package com.backend.service;
import com.backend.entity.User;
import com.backend.common.R;
public interface UserService {
    /**
     * 用户登录验证
     * @param username 用户名
     * @param password 密码（明文）
     * @return 包含用户信息和token的R对象
     */
    R<User> login(String username, String password);

    R<User> getUserInfoByToken(String token);
}
