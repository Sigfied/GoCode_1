package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lenovo
 */
@TableName("user")
@Data
public class User {

    @TableId
    private String account;
    private String password;
    private String uid;
    private String uname;
}
