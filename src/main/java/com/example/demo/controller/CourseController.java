package com.example.demo.controller;

import com.example.demo.entity.course;
import com.example.demo.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author lenovo
 */
@Controller
@RequestMapping(value = "/Course",produces = "application/json; charset=UTF-8")
public class CourseController {

    private final CourseMapper courseMapper;

    @Autowired
    public CourseController(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @RequestMapping(value = "/courseTest" ,produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public List<course> courseTest(){
       return courseMapper.selectByMap(null);
    }
}
