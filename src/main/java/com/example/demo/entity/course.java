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
@TableName("Course")
@Data
public class course {
    @TableId(value = "id")
    private Long id;
    /**课程id*/
    private String cid;
    /**课程开始时间*/
    private LocalDateTime cstarttime;
    /**课程结束时间*/
    private LocalDateTime cendtime;
    /**课程名*/
    private String cname;
    /**课程介绍*/
    private String cintroduce;
    /**课程创建者*/
    private String ccreator;

    @TableField(value = "create_time" ,fill = FieldFill.INSERT)
    private LocalDateTime create_time;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime update_time;
}
