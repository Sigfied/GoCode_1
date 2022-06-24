package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author GYJ
 */
@Data
@TableName("answerset")
public class Answerset {
    @TableId(value = "id")
    private Long id;
    /**题目集id*/
    private String tid;
    /**用户账号*/
    private String account;
    /**错题序列，以JSON格式存储，需要转为JSON*/
    private String questions;
    /**该同学在本次题目集中的得分*/
    private BigDecimal apoint;

    @TableField(value = "create_time" ,fill = FieldFill.INSERT)
    private LocalDateTime create_time;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime update_time;
}
