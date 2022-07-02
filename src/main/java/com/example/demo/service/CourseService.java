package com.example.demo.service;

import com.example.demo.entity.course;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author GYJ
 */
public interface CourseService {
    /**查看个人的所有课程???????????????????????????
     * @return 返回个人所有课程的列表
     * */
    List<course> getAllCourses();

    /**
     * 创建一个课程
     * @param cid 课程id
     * @param cname 课程名
     * @param introduce 课程介绍
     * @param creator 课程创建者
     * @param startTime 课程开始时间
     * @param endTime 课程结束时间
     * @return 创建一个Course
     * */
    int createCourse(String cid, String cname, String introduce, String creator, String startTime,String endTime);

    /**展示用户所选课,注意：未测试
     * @param account 用户信息{account:""}
     * @return 返回List<course>
     * @date 6.22 14:30
     *
     * */
     List<course> showPublicCourseList(String account) ;


    /**
     *
     * @param cid 课程id
     * @return 返回course对象
     * */
    course searchCourseWithCid(String cid);

    /**
     * @param tid 题目集id
     * @param account 提交记录的学生账号
     * @param questionJson String 有关本次题目集的所有答题的记录
     * @param apoint BigDecimal 用户本次答题的分数
     * @return 0 1
     * */
    int insertToAnswerSet(String tid, String account, String questionJson, BigDecimal apoint );

    /**返回我创建的所有课程
     * @param account 题目集id
     * @return 0 1
     * */
     List<course> showCreateCourseList(String account);
}
