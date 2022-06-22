package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author GYJ
 */
@Data
@TableName("students")
public class Students {
    @TableId(value = "id")
    private Long id;
    /**外键，主键属性之一，用户账号*/
    private String account;
    /**外键，主键属性之一，课程id*/
    private String cid;
    /**用户在该课程中的类型*/
    private int stype;

    @TableField(value = "create_time" ,fill = FieldFill.INSERT)
    private LocalDateTime create_time;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime update_time;
}
