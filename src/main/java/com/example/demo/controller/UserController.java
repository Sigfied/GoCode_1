package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.tools.MD5Utils;
import com.example.demo.tools.MailUtils;
import com.example.demo.tools.VerCodeGenerateUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lenovo
 */
@Controller
@Slf4j
@RequestMapping(value = "/Login",produces = "application/json; charset=UTF-8")
public class UserController {

    private final UserService userService;

    private final HashMap<String, String> userVoidMessageMap = new HashMap<>(8);


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
        String email = jsonObject.getString("email");
        log.debug(account + "\n" +password);
        password = MD5Utils.encryptByMd5(password);
        User user = userService.loginReturnUser(account,password,email);
        if (user == null) {
            user = new User();
            user.setUname("null");
        }
        return  user;
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


    /**根据用户邮箱发送一封邮件含有验证码
     * @param message 邮箱
     * @return 返回验证码
     * @date 6.22 11:30
     * */
    @RequestMapping(value="/getVoidMessage" ,produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public String getVoidMessage(@RequestBody String message) throws Exception {
        JSONObject jsonObject = new JSONObject(message);
        String email = jsonObject.getString("email");
        String voidMessage = VerCodeGenerateUtil.generateVerCode();
        MailUtils.sendMail(email,voidMessage);
        userVoidMessageMap.put(email,voidMessage);
        return voidMessage;
    }


    /**根据用户账号返回一个User对象,通过测试
     * @param jsonRequest 用户信息
     * @return 返回一个User对象
     * @date 6.22 11:30
     * */
    @RequestMapping(value="/insertUser" ,produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int insertUser(@RequestBody String jsonRequest) throws Exception {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String account = jsonObject.getString("account");
        String password = jsonObject.getString("password");
        String email = jsonObject.getString("email");
        String voidMessage = jsonObject.getString("voidMessage");
        //从map中获取临时保存的邮箱-验证码键值对
        String voidMessageInMap = userVoidMessageMap.get(email);
        password = MD5Utils.encryptByMd5(password);
        if(voidMessage.equals(voidMessageInMap)){
           return userService.insertUser(account, password, email);
        }else {
            return 404;
        }
    }
}
