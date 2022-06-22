package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author GYJ
 */
public interface UserService  {

    User loginReturnUser(@RequestBody Map<String, Map<String, Object>> map);

    int UpdateUser(@RequestBody Map<String, Map<String, Object>> map);

}
