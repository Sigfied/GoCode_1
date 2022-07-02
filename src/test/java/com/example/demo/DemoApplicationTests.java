package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FavoritesService favoritesService;

    @Autowired
    private StudentService studentService;
    @Autowired
    private QuestionService questionService;

    @Autowired
    private TopicsetService topicsetService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    void insertFavoritesTest(){
        favoritesService.insertFavorites("喜欢","20201307");
    }

    @Test
    void queryFavoritesTest(){
       log.info(favoritesService.queryFavorites("20201307").toString());
    }

    @Test
    void showAllStudentTest() throws JsonProcessingException { System.out.println("here is showAllStudentTest"+studentService.showAllStudent("001"));}

    @Test
    void isTeacherTest(){System.out.println("here is isTeacherTest"+ studentService.isTeacher("20201307","001"));}

    @Test
    void deleteStudentByAccount(){System.out.println("here is isTeacherTest"+studentService.deleteStudentByAccount("202013202"));}

    @Test
    void insertStudnet(){System.out.println("here is insertStudnet"+studentService.insertStudent("20201312","001",0));}

    @Test
    void insertQuestionTest(){
        String qinput = "{\"questionA\":\"A\",\"questionB\":\"B\",\"questionC\":\"C\",\"questionD\":\"D\"}";
        String qoutput = "{\"output\":\"A\"}";
        questionService.insertQuestionToTopicSet("1195271125",1,"测试第二题",1.5,qinput,qoutput);
    }
    @Test
    void insertTopicTest(){
        topicsetService.addTopicSet("001",1, LocalDateTime.now(),LocalDateTime.now(),2,1,"第二次作业集");
    }

    @Test
    void showPublicCourseListTest(){
         courseService.showPublicCourseList("20201307");
    }
    @Test
    void showAllStudent() throws JsonProcessingException {
        studentService.showAllStudent("001");
    }
    @Test
    void searchCourseWithCid(){
        courseService.searchCourseWithCid("001");
    }

    @Test
    void loginReturnUser(){
        userService.loginReturnUser("20201307","123456","");
    }

    @Test
    public void insertAnswerSet() throws JSONException {
        String jsonRequest = "\n" +
                "{\n" +
                "\t\"tid\": \"1195271125\",\n" +
                "\t\"account\": \"20201307\",\n" +
                "\t\"questions\": [{\n" +
                "\t\t\"qtype\": \"1\",\n" +
                "\t\t\"qid\": \"3472032195\",\n" +
                "\t\t\"describtion\": \"测试第一题\",\n" +
                "\t\t\"input\": {\n" +
                "\t\t\t\"questionA\": \"A\",\n" +
                "\t\t\t\"questionB\": \"B\",\n" +
                "\t\t\t\"questionC\": \"C\",\n" +
                "\t\t\t\"questionD\": \"D\"\n" +
                "\t\t},\n" +
                "\t\t\"output\": \"A\",\n" +
                "\t\t\"point\": \"1.5\",\n" +
                "\t\t\"answer\": \"A\",\n" +
                "\t\t\"mypoint\": \"\"\n" +
                "\t}, {\n" +
                "\t\t\"qtype\": \"3\",\n" +
                "\t\t\"qid\": \"1845455304\",\n" +
                "\t\t\"describtion\": \"测试第二题\",\n" +
                "\t\t\"input\": {\n" +
                "\t\t\t\"questionA\": \"11 12 13\",\n" +
                "\t\t\t\"questionB\": \"36 25 12\",\n" +
                "\t\t\t\"questionC\": \"59 264 26\",\n" +
                "\t\t\t\"questionD\": \"D\"\n" +
                "\t\t},\n" +
                "\t\t\"output\":  \"A\",\n" +
                "\t\t\"point\": \"1.5\",\n" +
                "\t\t\"answer\": \"B\",\n" +
                "\t\t\"mypoint\": \"\"\n" +
                "\t}]\n" +
                "}";
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String tid = jsonObject.getString("tid");
        String account = jsonObject.getString("account");
        JSONArray questions = jsonObject.getJSONArray("questions");
        BigDecimal sumPoint = BigDecimal.valueOf(0);
        for (int i = 0; i < questions.length(); i++) {
            JSONObject question = questions.getJSONObject(i);
            String outputExample = question.getString("output");
            String answer = question.getString("answer");
            if(outputExample.equals(answer)){
                question.put("mypoint",question.getString("point"));
                sumPoint = sumPoint.add(new BigDecimal(question.getString("point")));
            }
            else{
                question.put("mypoint","0");
                sumPoint = sumPoint.add(BigDecimal.valueOf(0));
            }
            log.debug(question.getString("point"));
        }
        log.info(String.valueOf(sumPoint));
        //courseService.insertToAnswerSet(tid,account, String.valueOf(questions),sumPoint);
    }
}
