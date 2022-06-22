package com.example.demo.service;

/**
 * @author lenovo
 */
public interface FavoritesService {
    /**
     * 添加一个新的收藏夹，需要前端提供随机的收藏夹id
     * @param favoritesName 收藏夹名称
     * @param account 用户账号
     * */
    int insertFavorites(String favoritesName,String account);
}
