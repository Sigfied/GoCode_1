package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

/**
 * @author GYJ
 */
@TableName("Course")
@Data
public class course {
    /**课程id*/
    @TableId
    private String cid;
    /**课程开始时间*/
    private DateTimeLiteralExpression.DateTime cstarttime;
    /**课程结束时间*/
    private DateTimeLiteralExpression.DateTime cendtime;
    /**课程名*/
    private String cname;
    /**课程介绍*/
    private String cintroduce;
    /**课程创建者*/
    private String ccreator;
}
