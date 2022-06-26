package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.tools.MD5Utils;
import com.example.demo.tools.MailUtils;
import com.example.demo.tools.VerCodeGenerateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class CheckDuplicatesTests {

    @Autowired
    private UserService service;
    //	获得发件人信息
    @Test
    void sendMail() throws Exception {
        String voidMessage = VerCodeGenerateUtil.generateVerCode();
        String toEmail = "2034689681@qq.com";
        MailUtils.sendMail(toEmail,voidMessage);
    }

    @Test
    void mailAndAccount() throws Exception {
        String email = "2034689681@qq.com";
        String password = "123456";
        String account = "";
        User user = service.loginReturnUser(account, password,email);
        log.info(user.toString());
    }

    @Test
    void md5() throws Exception {
        //e10adc3949ba59abbe56e057f20f883e
        String password = "123456";
        String passwordByMd5 = MD5Utils.encryptByMd5(password);
        log.info(password +"\t\t"+passwordByMd5);
    }

    @Test
    void insertUserTest(){
        String email = "2652115010@qq.com";
        String account = "20201312";
        String password = "12345678";
        password = MD5Utils.encryptByMd5(password);
        service.insertUser(account,password,email);
    }

    @Test
    void loginTest() throws Exception {
        String email = "2652115010@qq.com";
        String account = "20201312";
        String password = "12345678";
        password = MD5Utils.encryptByMd5(password);
        service.loginReturnUser(email,password,email);
    }
}
