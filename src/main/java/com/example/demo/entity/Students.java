package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author GYJ
 */
@Data
@TableName("students")
public class Students {
    /**外键，主键属性之一，用户账号*/
    @TableId
    private String account;
    /**外键，主键属性之一，课程id*/
    @TableId
    private String cid;
    /**用户在该课程中的类型*/
    private int stype;
}
