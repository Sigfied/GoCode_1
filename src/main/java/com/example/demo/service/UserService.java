package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author GYJ
 */
public interface UserService  {

    User loginReturnUser(String account,String password,String email);

    //我怀疑这个地方有问题
    int updateUser(@RequestBody Map<String, Map<String, Object>> map);

    /**增加一个用户，表示注册
    * @param account 账号-学号
     * @param password 密码
     * @param email 邮箱
     * @return 返回值
    * */
    int insertUser(String account,String password,String email);
}
