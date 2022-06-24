package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author GYJ
 */
public interface UserService  {

    User loginReturnUser(String account,String password);

    //我怀疑这个地方有问题
    int updateUser(@RequestBody Map<String, Map<String, Object>> map);

}
