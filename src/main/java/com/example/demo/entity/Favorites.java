package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author GYJ
 */
@Data
@TableName("favorites")
public class Favorites {
    @TableId(value = "id")
    private Long id;
    /**收藏夹ID*/
    private String fid;
    /**收藏夹名称*/
    private String fname;
    /**用户账号*/
    private String account;

    @TableField(value = "create_time" ,fill = FieldFill.INSERT)
    private LocalDateTime create_time;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime update_time;
}
