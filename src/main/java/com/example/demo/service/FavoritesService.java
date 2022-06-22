package com.example.demo.service;

import com.example.demo.entity.Favorites;

import java.util.List;

/**
 * @author lenovo
 */
public interface FavoritesService {
    /**
     * 添加一个新的收藏夹，需要前端提供随机的收藏夹id
     * @param favoritesName 收藏夹名称
     * @param account 用户账号
     * @return 状态
     * */
    int insertFavorites(String favoritesName,String account);

    /**返回该用户所有的收藏夹列表
     * @param account 用户账户
     * @return 返回该用户所有的收藏夹列表
     * */
    List<Favorites> queryFavorites(String account);


}
