package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.FavoritesService;
import com.example.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void showAllStudentTest(){ System.out.println("here is showAllStudentTest"+studentService.showAllStudent("001"));}

    @Test
    void isTeacherTest(){System.out.println("here is isTeacherTest"+ studentService.isTeacher("20201307","001"));}

    @Test
    void deleteStudentByAccount(){System.out.println("here is isTeacherTest"+studentService.deleteStudentByAccount("202013202"));}

    @Test
    void insertStudnet(){System.out.println("here is insertStudnet"+studentService.insertStudent("20201312","001",0));}



}
