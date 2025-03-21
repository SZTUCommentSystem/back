package com.backend.service.impl;

import com.backend.common.R;
import com.backend.entity.User;
import com.backend.mapper.UserMapper;
import com.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public R<User> login(String username, String password) {
        // 1. 查询用户
        User user = userMapper.selectByUsername(username);

        // 2. 验证用户是否存在
        if (user == null) {
            return R.error("用户名不存在");
        }

        // 3. 验证密码（实际场景需加密比对）
        if (!user.getPassword().equals(password)) {
            return R.error("密码错误");
        }

        // 4. 生成并更新token（示例用UUID模拟）
//        String token = UUID.randomUUID().toString();
//        user.setToken(token);

        // 5. 组织返回数据
        return R.success("checkUser", user);
    }

    @Override
    public R<User> getUserInfoByToken(String token) {
        // 1. 根据 token 查询用户
        User user = userMapper.selectByToken(token);

        // 2. 验证用户是否存在
        if (user == null) {
            return R.error("获取用户信息失败");
        }

        // 3. 组织返回数据
        return R.success("checkUser", user);
    }
}
