package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.FavoritesService;
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

}
