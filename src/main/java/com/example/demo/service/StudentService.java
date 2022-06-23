package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface StudentService {

    /**
     * 判断是否为老师
     * @param account 学号
     * @param cid 课程号
     * @return 状态
     * */
    int isTeacher(String account ,String cid);

    /**
     * 返回学生信息列表，有代码规范问题暂时无法解决
     * @param cid 课程号
     * @return List<Map<account,uname>>
     * */
    List<Map> showAllStudent(String cid);

    /**
     * 删除学生
     * @param account 学号
     * @return 0失败，成功晚点测
     * */
    int deleteStudentByAccount(String account);

    /**添加学生
     * @param account 用户账号
     * @param cid 课程id
     * @param type 用户类型（区别于老师与学生）
     * @return 返
     * @date 6.22 14:30
     * */
    int insertStudent(String account,String cid,int type);
}
