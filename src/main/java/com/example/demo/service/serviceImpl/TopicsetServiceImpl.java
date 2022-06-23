package com.example.demo.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.*;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.mapper.StudentsMapper;
import com.example.demo.mapper.TopicSetMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TopicsetService;
import com.example.demo.tools.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Admin
 */
@Service("TopicsetService")
public class TopicsetServiceImpl implements TopicsetService {

    private final TopicSetMapper topicSetMapper;

    @Autowired
    public TopicsetServiceImpl(TopicSetMapper topicSetMapper) {
        this.topicSetMapper = topicSetMapper;
    }

    @Override
    public int addTopicSet(String cid, int tisexam, Date tstarttime, Date tendtime, int tstatus, int tifcansee) {
        TopicSet topicSet = new TopicSet();
        topicSet.setCid(cid);
        topicSet.setTisexam(tisexam);
        topicSet.setTstarttime(tstarttime);
        topicSet.setTstaus(tstatus);
        topicSet.setTifcanse(tifcansee);
        return topicSetMapper.insert(topicSet);
    }

    @Override
    public int updateTopicSet(String cid,int tisexam, Date tstarttime, Date tendtime, int tstatus, int tifcansee) {
        QueryWrapper<TopicSet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid",cid);
        TopicSet topicSet = new TopicSet();
        topicSet.setTisexam(tisexam);
        topicSet.setTstarttime(tstarttime);
        topicSet.setTstaus(tstatus);
        topicSet.setTifcanse(tifcansee);
        return topicSetMapper.update(topicSet,queryWrapper);
    }

    @Override
    public int insertQuestionToTopicSet(String tid, int qtype, String qdescribtion, double qpoint, String qinput, String qoutput) {
        return 0;
    }

    @Override
    public List<TopicSet> showAllTopicSet(String cid) {
        QueryWrapper<TopicSet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid",cid);
        return topicSetMapper.selectList(queryWrapper);
    }

    @Override
    public int deleteTopicSet(String id) {
        return topicSetMapper.deleteById(id);
    }
}
