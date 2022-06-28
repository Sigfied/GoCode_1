package com.example.demo.tools.photoTools;

import java.nio.charset.StandardCharsets;

import static java.net.URLEncoder.encode;

/**
 * 文档版面分析与识别
 */
public class DocAnalysis {
    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String docAnalysis(String filePath) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/doc_analysis";
        try {
            // 本地文件路径
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = encode(imgStr, StandardCharsets.UTF_8);

            String param = "language_type=" + "CHN_ENG" + "&result_type=" + "big" + "&image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth("lKVoqQI90xjc61bvbNgkM1iY","OyRQ4d8M8hqy6LFrkpqMDoAlBo0fGodv");

            String result = HttpUtil.post(url, accessToken, param);
            //System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}