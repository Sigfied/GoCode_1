package com.example.demo.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Favorites;
import com.example.demo.mapper.FavoritesMapper;
import com.example.demo.service.FavoritesService;
import com.example.demo.tools.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GYJ
 */
@Service("FavoritesService")
public class FavoritesServiceImpl implements FavoritesService {
   private final FavoritesMapper favoritesMapper;

   @Autowired
    public FavoritesServiceImpl(FavoritesMapper favoritesMapper) {
        this.favoritesMapper = favoritesMapper;
    }


    /**
     * 添加一个新的收藏夹，提供随机的收藏夹id
     * 这里我使用了tools包下的随机数
     * @param favoritesName 收藏夹名称
     * @param account       用户账号
     */
    @Override
    public int insertFavorites(String favoritesName, String account) {
        String favoritesId = MathUtils.getPrimaryKey();
        Favorites favorites = new Favorites();
        favorites.setAccount(account);
        favorites.setFid(favoritesId);
        favorites.setFname(favoritesName);
        return favoritesMapper.insert(favorites);
    }

    /**
     * 返回该用户所有的收藏夹列表
     *
     * @param account 用户账户
     * @return 返回该用户所有的收藏夹列表
     */
    @Override
    public List<Favorites> queryFavorites(String account) {
        QueryWrapper<Favorites> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        return favoritesMapper.selectList(queryWrapper);
    }
}
