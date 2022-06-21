package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author GYj
 */
@Data
@TableName("question")
public class Question {
    /**题目Id*/
    @TableId
    private String qid;
    /**所在题目集Id*/
    private String tid;
    /**题目类型标识*/
    private Integer qtype;
    /**题目描述*/
    private String qdescribtion;
    /**题目分值*/
    private Float qpoint;
    /**题目输入样例，或者选项*/
    private String qinput;
    /**题目输出样例，或者答案啊*/
    private String qoutput;
}
