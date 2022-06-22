package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author lenovo
 */
@Controller
@RequestMapping(value = "/Login",produces = "application/json; charset=UTF-8")
public class UserController {

    private final UserMapper userMapper;

    private final UserService userService;

    @Autowired
    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }
    /**根据用户账号返回一个User对象,注意：未测试
     * @param map 用户信息
     * @return 返回一个User对象
     * @date 6.22 11:30
     *
     * */
    @RequestMapping(value="/loginReturnUser" ,produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public User loginReturnUser(@RequestBody Map<String, Map<String, Object>> map){
        return userService.loginReturnUser(map);
    }

    /**根据用户提交信息修改个人信息,注意：未测试
     * @param map 用户信息
     * @return 返回int若为1，则成功，否则失败
     * @date 6.22 11:30
     *
     * */
    @RequestMapping("/UpdateUser")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int UpdateUser(@RequestBody Map<String, Map<String, Object>> map){
        return userService.UpdateUser(map);
    }

}
