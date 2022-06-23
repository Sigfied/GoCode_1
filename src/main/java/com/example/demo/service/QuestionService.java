package com.example.demo.service;

import com.example.demo.entity.course;

import java.util.List;

/**
 * @author Admin
 */
public interface QuestionService {

    /**删除题目从题目集内,注意：未测试，题目的唯一标识应该是有的，在得到所有题目的方法内
     * @param id:"题目唯一标识"
     * @return int 1成功 0失败
     * @date 6.23 15:30
     *
     * */
    int deleteQuestionFromTopicSet(String id);
}
