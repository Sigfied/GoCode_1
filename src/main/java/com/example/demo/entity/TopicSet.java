package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("topicset")
public class TopicSet {
    @TableId(value = "id")
    private Long id;

    private String tid;

    private String cid;

    private int tisexam;

    private LocalDateTime tstarttime;

    private LocalDateTime tendtime;

    private int tstatus;

    private float taverage;

    private float texcellentrate;

    private float tpassingrate;

    private int tifcansee;

    private String tname;

    @TableField(value = "create_time" ,fill = FieldFill.INSERT)
    private LocalDateTime create_time;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime update_time;
}
