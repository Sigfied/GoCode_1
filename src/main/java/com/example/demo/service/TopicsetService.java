package com.example.demo.service;

import com.example.demo.entity.Favorites;
import com.example.demo.entity.TopicSet;

import java.util.Date;
import java.util.List;

/**
 * @author lenovo
 */
public interface TopicsetService {
    /**老师给课程添加题目集,注意：未测试，使用Integer.parseInt没有校验
     * @param cid:"课程号",tisexam:"是否考试1考试，0题目",
     * @param tisexam:"是否是考试1是，0否"
     * @param tstarttime:"题目集开发时间"
     * @param tendtime:"题目集结束时间",SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
     * @param tstatus:"0：未开放，1：正在开发，2：已结束",
     * @param tifcansee:"结束后是否可视1.可，0，否"
     * @return 0失败,成功1
     * @date 6.23 15:30
     *
     * */
    int addTopicSet(String cid, int tisexam, Date tstarttime, Date tendtime, int tstatus, int tifcansee);


    /**修改题目集信息,注意：未测试，使用Integer.parseInt没有校验
     * @param cid:"课程号",tisexam:"是否考试1考试，0题目",
     * @param tisexam:"是否考试1考试，0题目",
     * @param tstarttime:"题目集开放时间",SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
     * @param tendtime:"题目集结束时间",SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
     * @param tstatus:"0：未开放，1：正在开发，2：已结束",
     * @param tifcansee:"结束后是否可视1.可，0，否"
     * @return 0失败,成功1
     * @date 6.23 15:30
     *
     * */
    int updateTopicSet(String cid,int tisexam, Date tstarttime, Date tendtime, int tstatus, int tifcansee);

    /**增加题目到题目集,注意：未测试，未完成，json对应的包未导入，json格式有疑问
     * @param  tid：“题目集id”，
     * @param qtype:"1单选，2多选，3填空，4编程",
     * @param qdescribtion:"题目描述"
     * @param qpoint：“分数”,
     * @param qinput:"输入",
     * @param qoutput:"输出"
     * @return int 1成功 0失败
     * @date 6.23 15:30
     *
     * */
    int insertQuestionToTopicSet(String tid,int qtype,String qdescribtion,double qpoint,String qinput,String qoutput);

    /**展示所有题目集,注意：未测试，
     * @param cid 课程id
     * @return List<TopicSet>
     * @date 6.23 15:30
     *
     * */
    List<TopicSet> showAllTopicSet(String cid);

    /**删除一个题目集,注意：未测试，
     * @param id {id:"题目集唯一标识" }
     * @return List<TopicSet>
     * @date 6.23 15:30
     *
     * */
    int deleteTopicSet(String id);
}
