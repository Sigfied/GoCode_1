package com.example.demo.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Students;
import com.example.demo.entity.course;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.StudentsMapper;
import com.example.demo.service.CourseService;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Admin
 */
@Service("QuestionService")
public class QuestionServiceImpl implements QuestionService {

    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionServiceImpl(QuestionMapper questionMapper){
        this.questionMapper = questionMapper;
    }

    @Override
    public int deleteQuestionFromTopicSet(String id) {
        return questionMapper.deleteById(id);
    }
}
