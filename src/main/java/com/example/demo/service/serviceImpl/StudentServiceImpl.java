package com.example.demo.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Favorites;
import com.example.demo.entity.Students;
import com.example.demo.entity.User;
import com.example.demo.entity.course;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.mapper.StudentsMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentService;
import com.example.demo.tools.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Admin
 */
@Service("StudentService")
public class StudentServiceImpl implements StudentService {

    private final StudentsMapper studentsMapper;
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
        return studentsMapper.selectOne(queryWrapper).getStype();
    }

    @Override
    public int insertStudent(String account, String cid, int type) {
        Students student = new Students();
        student.setCid(cid);
        student.setAccount(account);
        student.setStype(type);
        return studentsMapper.insert(student);
    }

    @Override
    public List<Map> showAllStudent(String cid) {
        QueryWrapper<Students> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid",cid);
        List<Map> list = new ArrayList<>();
        for(Students students:studentsMapper.selectList(queryWrapper)){
            QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("account",students.getAccount());
            Map<String,String> map = new HashMap<>(1);
            map.put(students.getAccount(),userMapper.selectOne(queryWrapper1).getUname());
            list.add(map);
        }
        return list;
    }

    @Override
    public int deleteStudentByAccount(String account) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("account", account);
        return studentsMapper.deleteByMap(map);
    }
}
