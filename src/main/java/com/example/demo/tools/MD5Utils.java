package com.example.demo.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * TODO 实现MD5加密用户的密码
 * @author lenovo
 */
public class MD5Utils {
    /**
     * 加密
     * @param context 明文
     */
    public static String encryptByMd5(String context) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            //update处理
            md.update(context.getBytes());
            //调用该方法完成计算
            byte [] entryContext = md.digest();
            int i;
            StringBuilder buf = new StringBuilder();
            //做相应的转化（十六进制）
            for (byte b : entryContext) {
                i = b;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.substring(8,23);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return  null;
        }
    }
}
