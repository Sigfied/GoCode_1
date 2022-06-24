package com.example.demo.tools;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author 20201307-郭煜杰
 */
@Slf4j
public
class RunCode {
    static final String APP_ID = "1177";
    static final String APP_SECRET = "d49816f7e61055d364ac97f59c63b4c7";

    static final String ERROR = "error";
    static final String MSG = "msg";

    static final int DEFAULT_DIGITS = 32;

    /**
     这里的code 要用于计算sign值，必须是还原成明文。
     部分 sign 异常原因是因为 code 值为 base64加密串或encodeURIComponent未被还原造成的
     */
    public static String getResult(String code , String input , String lang) throws IOException, JSONException {
        String sign = sign(code,input);
        FormBody body = new FormBody.Builder()
                .add("appId", APP_ID)
                .add("sign", sign)
                .add("lang", lang)
                .add("code", Objects.requireNonNull(URLEncoder.encode(Base64Util.encode(code))))
                .add("input", Objects.requireNonNull(URLEncoder.encode(Base64Util.encode(input))))
                .build();
        String result = post(body);
        log.info("result:\t"+result);
        JSONObject resultJson = new JSONObject(result);
        if(Integer.parseInt(resultJson.get(ERROR).toString()) != 0){
            return resultJson.getString("msg");
        }
        JSONObject dataJson =  new JSONObject((String) resultJson.get("data"));
        if(resultJson.get(MSG) != null){
            log.info((String) dataJson.get("result"));
            return dataJson.get("result").toString();
        }
        else{
            return  resultJson.getString("msg");
        }
    }

    static String sign(String code,String input) {
        return md5(code + input + APP_SECRET);
    }

    static String md5(String str) {
        /*
          Spring 中建议直接使用 DigestUtils.md5DigestAsHex() 计算md5值
         */
        try {
            byte[] secretBytes;
            secretBytes = MessageDigest.getInstance("md5").digest(str.getBytes());

            String md5code = new BigInteger(1, secretBytes).toString(16);
            for (int i = 0; i < DEFAULT_DIGITS - md5code.length(); i++) {
                md5code = "0" + md5code;
            }
            return md5code;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
    }

    static String post(FormBody body) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://jsrun.net/api/run/v2").post(body).build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
