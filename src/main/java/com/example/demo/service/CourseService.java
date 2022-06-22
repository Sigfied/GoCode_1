package com.example.demo.service;

import com.example.demo.entity.course;

import java.util.List;

/**
 * @author GYJ
 */
public interface CourseService {
    /**查看个人的所有课程
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
}
