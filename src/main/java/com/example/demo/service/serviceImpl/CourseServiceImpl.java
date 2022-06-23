package com.example.demo.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Students;
import com.example.demo.entity.course;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.mapper.StudentsMapper;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GYj
 */
@Service("CourseService")
public class CourseServiceImpl implements CourseService {
    private final StudentsMapper studentsMapper;
    private final CourseMapper courseMapper;

    @Autowired
    public CourseServiceImpl(StudentsMapper studentsMapper, CourseMapper courseMapper) {
        this.studentsMapper = studentsMapper;
        this.courseMapper = courseMapper;
    }

    @Override
    public List<course> getAllCourses() {
        QueryWrapper<course> queryWrapper = new QueryWrapper<>();
        List<course> listCourse = new ArrayList<>();
        return null;
    }

    @Override
    public int createCourse(String cid, String cname, String introduce, String creator, String  startTime, String  endTime) {
        LocalDateTime start = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime end = LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        course courseNew = new course();
        courseNew.setCid(cid);
        courseNew.setCcreator(creator);
        courseNew.setCname(cname);
        courseNew.setCintroduce(introduce);
        courseNew.setCstarttime(start);
        courseNew.setCendtime(end);
        return courseMapper.insert(courseNew);
    }

    @Override
    public List<course> showPublicCourseList(String account) {
        QueryWrapper<Students> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        List<course> courses = new ArrayList<>();
        for( Students students: studentsMapper.selectList(queryWrapper)){
            QueryWrapper<course> queryWrapperc = new QueryWrapper<>();
            queryWrapperc.eq("cid",students.getCid());
            courses.addAll(courseMapper.selectList(queryWrapperc));
        }
        return courses;
    }



    @Override
    public course searchCourseWithCid(String cid) {
        QueryWrapper<course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid",cid);
        return courseMapper.selectOne(queryWrapper);
    }




}
