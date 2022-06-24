package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author GYj
 */
@Data
@TableName("question")
public class Question {
    @TableId(value = "id")
    private Long id;

    /**题目Id*/
    private String qid;
    /**所在题目集Id*/
    private String tid;
    /**题目类型标识*/
    private Integer qtype;
    /**题目描述*/
    private String qdescribtion;
    /**题目分值*/
    private BigDecimal qpoint;
    /**题目输入样例，或者选项*/
    private String qinput;
    /**题目输出样例，或者答案啊*/
    private String qoutput;

    @TableField(value = "create_time" ,fill = FieldFill.INSERT)
    private LocalDateTime create_time;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime update_time;
}
