package com.example.demo.service;

import com.example.demo.entity.User;

/**
 * @author GYJ
 */
public interface UserService  {

    /**根据用户账号返回一个User对象
     * @param account 用户账号
     * @return 返回一个User对象
    * */
    User getUserByAccount(String account);
}
