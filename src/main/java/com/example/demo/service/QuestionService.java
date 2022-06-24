package com.example.demo.service;

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

    /**添加一道题到题目集内
     * @param tid 题目集id
     * @param qdescribtion 题目描述
     * @param qpoint 题目的分数
     * @param qtype 题目类型（此处枚举，选择题，填空题，编程题）
     * @param qinput 输入样例（选择题的输入样例为选项，填空题的数据样例可以为空）
     * @param qoutput 输出样例（选择题的输出样例为答案，填空题的输出样例为答案）
     * @return int 1成功 0失败
     * */
    int insertQuestionToTopicSet(String tid,int qtype,String qdescribtion,double qpoint, String qinput, String qoutput);
}
