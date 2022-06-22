package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/loginReturnUser")
    @GetMapping
    @ResponseBody
    public User loginReturnUser(){
      return userService.getUserByAccount("20201307");
    }

    @RequestMapping(value = "/loginTest" ,produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public User loginTest(@RequestBody Map<String, Map<String, Object>> map){
        String account = map.get("account").get("account").toString();
        String password = map.get("password").get("password").toString();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account).eq("password",password);
        return userMapper.selectOne(queryWrapper);
    }

    @RequestMapping("/UpdateUserTest")
    @ResponseBody
    public int insertUserTest(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("account","20201312");
        return userMapper.update(new User(),updateWrapper);
    }

}
