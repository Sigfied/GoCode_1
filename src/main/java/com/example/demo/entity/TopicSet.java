package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
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

    private BigDecimal taverage;

    private BigDecimal texcellentrate;

    private BigDecimal tpassingrate;

    private int tifcansee;

    private String tname;

    @TableField(value = "create_time" ,fill = FieldFill.INSERT)
    private LocalDateTime create_time;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime update_time;

    private BigDecimal tsumpoint;
}
