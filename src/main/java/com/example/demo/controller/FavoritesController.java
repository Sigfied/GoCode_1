package com.example.demo.controller;

import com.example.demo.entity.Favorites;
import com.example.demo.service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author lenovo
 */
@Controller
@RequestMapping(value = "/Favorites",produces = "application/json; charset=UTF-8")
public class FavoritesController {
    private final FavoritesService favoritesService;

    @Autowired
    public FavoritesController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }


    /**通过测试
     * */
    @RequestMapping("/insertFavorites")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int insertFavorites(@RequestBody Map<String, Map<String, Object>> map){
        String favoritesName = map.get("favoritesName").get("favoritesName").toString();
        String account = map.get("account").get("account").toString();
        return favoritesService.insertFavorites(favoritesName,account);
    }


    /**
     * 通过测试
     * */
    @RequestMapping("/queryFavorites")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public List<Favorites> queryFavorites(@RequestBody Map<String, Map<String, Object>> map){
        String account = map.get("account").get("account").toString();
        return favoritesService.queryFavorites(account);
    }

}
