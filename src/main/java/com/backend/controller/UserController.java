package com.backend.controller;
import com.backend.entity.User;
import com.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.backend.common.R;
import java.util.Map;
@RestController
@RequestMapping()
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 测试
     * @return
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot!"; // 返回测试数据
    }
    /**
     * 用户登录接口
     * @param params 包含username和password的JSON对象
     * @return R<User> 响应结果
     */
    @PostMapping("/user/login")
    public R<User> loginAPI(@RequestBody Map<String, String> params) {
        return userService.login(
                params.get("username"),
                params.get("password")
        );
    }
    /**
     * 获取用户信息接口
     * @param token 请求头中的token
     * @return R<User> 包含用户信息的响应结果
     */
    @GetMapping("/user/info")
    public R<User> getUserInfoAPI(@RequestHeader("token") String token) {
        return userService.getUserInfoByToken(token);
    }
}