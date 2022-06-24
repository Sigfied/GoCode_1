package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
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
@Slf4j
@RequestMapping(value = "/Login",produces = "application/json; charset=UTF-8")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController( UserService userService) {
        this.userService = userService;
    }

    /**根据用户账号返回一个User对象,通过测试
     * @param jsonRequest 用户信息
     * @return 返回一个User对象
     * @date 6.22 11:30
     * */
    @RequestMapping(value="/loginReturnUser" ,produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public User loginReturnUser(@RequestBody String jsonRequest) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String account = jsonObject.getString("account");
        String password = jsonObject.getString("password");
        log.debug(account + "\n" +password);
        return userService.loginReturnUser(account,password);
    }

    /**根据用户提交信息修改个人信息,注意：未测试
     * @param map 用户信息
     * @return 返回int若为1，则成功，否则失败
     * @date 6.22 11:30
     * */
    @RequestMapping("/UpdateUser")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int updateUser(@RequestBody Map<String, Map<String, Object>> map){
        return userService.updateUser(map);
    }

}
