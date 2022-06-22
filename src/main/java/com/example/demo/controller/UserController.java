package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
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

    @Autowired
    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @RequestMapping("/loginReturnUser")
    @ResponseBody
    public User loginReturnUser(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account","20201307");
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
        return user;
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

    @RequestMapping("/InsertUserTest")
    @ResponseBody
    public int insertUserTest(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("account","20201312");
        return userMapper.update(new User(),updateWrapper);
    }

}
