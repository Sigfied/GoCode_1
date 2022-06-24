package com.example.demo.tools;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 对数据进行Base64加密
 * @author GYJ
 */
public class Base64Util {

    public static String encode(byte[] bytes) {
        return new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8);
    }

    public static String encode(String str) {
        byte[] b = null;
        String s = null;
        b = str.getBytes(StandardCharsets.UTF_8);
        s = new String(Base64.getEncoder().encode(b), StandardCharsets.UTF_8);
        return s;
    }

    /** 瑙ｅ瘑
     *
     * @param s
     * @return result 鐢ㄤ簬瑙ｅ紑base64鍔犲瘑鐨勫瓧绗︿覆
     */
    public static String decode(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            try {
                b = Base64.getDecoder().decode(s);
                result = new String(b, StandardCharsets.UTF_8);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
