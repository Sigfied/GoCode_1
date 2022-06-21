package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

@Data
@TableName("topicset")
public class TopicSet {
    @TableId
    private String tid;
    private String cid;
    private int tisexam;
    private DateTimeLiteralExpression.DateTime tstarttime;
    private DateTimeLiteralExpression.DateTime tendtime;
    private int tstaus;
    private float taverage;
    private float texcellentrate;
    private float tpassingrate;
    private int tifcanse;
}
