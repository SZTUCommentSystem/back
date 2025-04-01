package com.backend.controller;

import com.backend.entity.User;
import com.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.backend.common.R;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 该控制器类处理与用户相关的请求
@RestController
@RequestMapping()
public class UserController {

    // 自动注入用户服务类
    @Autowired
    private UserService userService;

    // 创建日志记录器
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 测试接口，用于验证Spring Boot服务是否正常运行
     * 该接口会返回一个简单的问候语
     * @return 包含问候语的字符串
     */
    @GetMapping("/hello")
    public String hello() {
        // 记录进入测试接口的日志
        logger.info("进入测试接口 /hello");
        try {
            // 准备返回的测试数据
            String response = "Hello from Spring Boot!";
            // 记录成功返回测试数据的日志
            logger.info("成功返回测试数据: {}", response);
            return response; // 返回测试数据
        } catch (Exception e) {
            // 记录测试接口出现异常的日志
            logger.error("测试接口出现异常: {}", e.getMessage());
            return "Error occurred in test API";
        }
    }

    /**
     * 用户登录接口，接收包含用户名和密码的JSON对象
     * 该接口会调用用户服务类的登录方法进行登录验证
     * @param params 包含username和password的JSON对象
     * @return R<User> 包含登录结果的响应对象
     */
    @PostMapping("/user/login")
    public R<User> loginAPI(@RequestBody Map<String, String> params) {
        // 记录进入用户登录接口的日志
        logger.info("进入用户登录接口 /user/login");
        try {
            // 从请求参数中获取用户名和密码
            String username = params.get("username");
            String password = params.get("password");
            // 记录接收到的用户名和密码日志
            logger.info("接收到的用户名: {}, 密码: {}", username, password);
            // 调用服务类的登录方法进行登录验证
            R<User> result = userService.login(username, password);
            // 记录登录结果日志
            logger.info("登录结果: {}", result);
            return result;
        } catch (Exception e) {
            // 记录用户登录接口出现异常的日志
            logger.error("用户登录接口出现异常: {}", e.getMessage());
            return R.error("登录接口出现异常");
        }
    }

    /**
     * 获取用户信息接口，接收请求头中的token
     * 该接口会调用用户服务类的方法根据token获取用户信息
     * @param token 请求头中的token
     * @return R<User> 包含用户信息的响应结果
     */
    @GetMapping("/user/info")
    public R<User> getUserInfoAPI(@RequestHeader("token") String token) {
        // 记录进入获取用户信息接口的日志
        logger.info("进入获取用户信息接口 /user/info");
        try {
            // 调用服务类的方法根据token获取用户信息
            R<User> result = userService.getUserInfoByToken(token);
            // 记录获取用户信息的结果日志
            logger.info("获取用户信息的结果: {}", result);
            return result;
        } catch (Exception e) {
            // 记录获取用户信息接口出现异常的日志
            logger.error("获取用户信息接口出现异常: {}", e.getMessage());
            return R.error("获取用户信息接口出现异常");
        }
    }
}