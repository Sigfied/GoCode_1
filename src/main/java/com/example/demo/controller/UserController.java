package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lenovo
 */
@Controller
@RequestMapping(value = "/Login",produces = "application/json; charset=UTF-8")
public class UserController {

    private final UserMapper userMapper;

    @Autowired
    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @RequestMapping("/loginTest")
    @ResponseBody
    public int loginTest(){
        System.out.println("Logging");
        return 0;
    }

    @RequestMapping("/loginReturnUser")
    @ResponseBody
    public User loginReturnUser(){
        User user = userMapper.selectById("20201307");
        System.out.println(user);
        return user;
    }

}
