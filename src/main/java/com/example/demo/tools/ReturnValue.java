package com.example.demo.tools;

import lombok.Data;

/**
 * @author GYJ
 * TODO 声明返回值的类型
 * @Date  2022-06-26 11:25
 */
@Data
public class ReturnValue <T> {
    /**返回数据包装于此*/
    private T data;
    /**返回的状态码解释*/
    private String msg;
    /**状态码*/
    private int code;
}
