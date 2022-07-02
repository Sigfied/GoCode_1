package com.example.demo.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Students;
import com.example.demo.entity.User;
import com.example.demo.mapper.StudentsMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Admin
 */
@Service("StudentService")
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private final StudentsMapper studentsMapper;
    @Autowired
    private final UserMapper userMapper;

    @Autowired
    public StudentServiceImpl(StudentsMapper studentsMapper,UserMapper userMapper) {
        this.studentsMapper = studentsMapper;
        this.userMapper = userMapper;
    }

    @Override
    public int isTeacher(String account, String cid) {
        QueryWrapper<Students> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account).eq("cid",cid);
        log.info(account + "\t" + cid);
        int i  = studentsMapper.selectOne(queryWrapper).getStype();
        log.info(String.valueOf(i));
        return i;
    }

    @Override
    public int insertStudent(String account, String cid, int type) {
        Students student = new Students();
        student.setCid(cid);
        student.setAccount(account);
        student.setStype(type);
        int i;
        try {
            i = studentsMapper.insert(student);
        }
        catch (Exception e) {
            return 0;
        }
        return i;
    }

    @Data
    private static class UserStudent{
        String account;
        String name;
    }
    @Override
    @ResponseBody
    public String showAllStudent(String cid) throws JsonProcessingException {
        QueryWrapper<Students> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid",cid);
        List<UserStudent> list = new ArrayList<>();
        for(Students students:studentsMapper.selectList(queryWrapper)){
            QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("account",students.getAccount());
            UserStudent userStudent = new UserStudent();
            userStudent.setName(userMapper.selectOne(queryWrapper1).getUname());
            userStudent.setAccount(students.getAccount());
            list.add(userStudent);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(list);
    }

    @Override
    public int deleteStudentByAccount(String account) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("account", account);
        return studentsMapper.deleteByMap(map);
    }
}
