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
    public User loginReturnUser(String account,String password,String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //这里有一个嵌套查询，使用了and语句的嵌套
        queryWrapper.eq("password",password)
                .and(userQueryWrapper -> userQueryWrapper.eq("account", account).or().eq("email",email));
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public int updateUser(Map<String, Map<String, Object>> map) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        String account = map.get("account").get("account").toString();
        String password = map.get("password").get("password").toString();
        String uname = map.get("uname").get("uname").toString();
        updateWrapper.eq("account",account).eq("password",password);
        User user = new User();
        user.setAccount(account);
        user.setUname(uname);
        userMapper.update(user,updateWrapper);
        return userMapper.update(new User(),updateWrapper);
    }

    @Override
    public int insertUser(String account, String password, String email) {
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setEmail(email);
        return userMapper.insert(user);
    }

}
