package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lenovo
 */
@TableName("user")
@Data
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String account;

    private String password;

    private String email;

    private String uname;

    @TableField(value = "create_time" ,fill = FieldFill.INSERT)
    private LocalDateTime create_time;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime update_time;
}
