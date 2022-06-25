package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Question;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author GYJ
 */
@Repository
public interface QuestionMapper extends BaseMapper<Question> {
    /**
     * 批量插入（mysql）
     * @param entityList 实体集合
     * @return 插入的条数
     */
    Integer insertBatchSomeColumn(Collection<Question> entityList);
}
