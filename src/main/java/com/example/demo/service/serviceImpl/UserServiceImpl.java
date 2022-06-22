package com.example.demo.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author GYJ
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User loginReturnUser(Map<String, Map<String, Object>> map) {
        String id = map.get("id").get("id").toString();
        String password = map.get("password").get("password").toString();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id).eq("password",password);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public int UpdateUser(Map<String, Map<String, Object>> map) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        String account = map.get("id").get("id").toString();
        String password = map.get("password").get("password").toString();
        String id = map.get("account").get("account").toString();
        String uname = map.get("uname").get("uname").toString();
        updateWrapper.eq("account",account).eq("password",password);
        User user = new User();
        user.setAccount(account);
        user.setUname(uname);
        userMapper.update(user,updateWrapper);
        return userMapper.update(new User(),updateWrapper);
    }

}
