package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.*;
import com.example.demo.mapper.*;
import com.example.demo.tools.ExcelFileToJson;
import com.example.demo.tools.MathUtils;
import com.example.demo.tools.RunCode;
import com.example.demo.tools.photoTools.DocAnalysis;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class ExcelFileToJsonTests {

    @Autowired
    private TopicSetMapper topicSetMapper;

    @Autowired
    private QuestionMapper questionMapper;


    @Autowired
    private StudentsMapper studentsMapper ;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private AnswerSetMapper answerSetMapper;

    @Test
    void ExcelFileToJsonTest() throws JSONException {
       JSONArray jsonArray =  ExcelFileToJson.tryExclTranslateToJson("F:\\questions.xls");
       String tid = "8692599914";
       List<Question> questionList = new ArrayList<>();
       for (int i = 0; i < jsonArray.length(); i++) {
            Question question = new Question();
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            if(jsonObject!=null) {
                question.setQid(MathUtils.getPrimaryKey());
                question.setQtype(Integer.valueOf(jsonObject.getString("type")));
                question.setQdescribtion(jsonObject.getString("description"));
                question.setQinput(jsonObject.getString("input"));
                question.setQoutput(jsonObject.getString("output"));
                question.setTid(tid);
                question.setQpoint(new BigDecimal(jsonObject.getString("point")));
                questionList.add(question);
            }
       }
       log.info(questionList.toString());
       int i = questionMapper.insertBatchSomeColumn(questionList);
       log.info("插入条数："+i);
    }

    @Test
    void RunCodeTest() throws Exception {
        String code = "#include <stdio.h>\n" +
                "#define MAXN 20\n" +
                " \n" +
                "void CountOff( int n, int m, int out[] );\n" +
                " \n" +
                "int main()\n" +
                "{\n" +
                "    int out[MAXN], n, m;\n" +
                "    int i;\n" +
                " \n" +
                "    scanf(\"%d %d\", &n, &m);\n" +
                "    CountOff( n, m, out );   \n" +
                "    for ( i = 0; i < n; i++ )\n" +
                "        printf(\"%d \", out[i]);\n" +
                "    printf(\"\\n\");\n" +
                " \n" +
                "    return 0;\n" +
                "}\n" +
                "void CountOff( int n, int m, int out[] )\n" +
                "{\n" +
                "    int a[MAXN],i,k=0,count=n,j=0;\n" +
                "    for(i=0;i<n;i++)    \n" +
                "    {\n" +
                "        a[i]= i;\n" +
                "    }\n" +
                "    for(;count!=0;i++)\n" +
                "    {\n" +
                "        if(a[i%n]!=-1) \n" +
                "        {\n" +
                "        k++;\n" +
                "        if(k==m)\n" +
                "        {\n" +
                "        out[i%n]=n-count+1; \n" +
                "        a[i%n]=-1;\n" +
                "        count--; \n" +
                "        k=0; \n" +
                "        }    \n" +
                "        }\n" +
                "    }    \n" +
                "}\n";
        String input = "11 3";
        String lang = "c";
        log.info(RunCode.getResult(code, input, lang));
    }

    @Test
    void photoToolsTest() throws Exception {
        String filePath = "E:\\桌面\\实验报告\\2.png";
        String result = DocAnalysis.docAnalysis(filePath);
        log.info(result);
        JSONObject jsonObject = new JSONObject(result);
        JSONArray array = jsonObject.getJSONArray("results");
        List<String> results = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject words = (JSONObject) array.get(i);
            JSONObject words2 = words.getJSONObject("words");
            results.add(words2.getString("word"));
        }
       results.forEach(System.out::println);
    }



    @Test
    void showCreateCourseList(){
        QueryWrapper<Students> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account","20201307");
        queryWrapper.eq("stype",1);
        List<course> courses = new ArrayList<>();
        for( Students students: studentsMapper.selectList(queryWrapper)){
            QueryWrapper<course> queryWrapperc = new QueryWrapper<>();
            queryWrapperc.eq("cid",students.getCid());
            courses.addAll(courseMapper.selectList(queryWrapperc));
        }
    }


    @Test
     void statisticalResults() {
        String tid = "1374029871";
        int pass = 0;
        int excellent = 0;
        double number = 0;
        BigDecimal full = BigDecimal.valueOf(0);
        QueryWrapper<Answerset> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tid",tid);
        QueryWrapper<TopicSet> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("tid",tid);
        BigDecimal sumCount= topicSetMapper.selectOne(queryWrapper2).getTsumpoint();
        for( Answerset answerset: answerSetMapper.selectList(queryWrapper)){
            full = full.add(answerset.getApoint()) ;
            number++;
            if(answerset.getApoint().compareTo(sumCount.multiply(BigDecimal.valueOf(0.6 ))) > 0){
                pass++;
            }
            if(answerset.getApoint().compareTo(sumCount.multiply(BigDecimal.valueOf(0.8))) > 0){
                excellent++;
            }
        }
        TopicSet topicSet = new TopicSet();
        topicSet.setTaverage(full.divide(BigDecimal.valueOf(number)));
        topicSet.setTpassingrate(BigDecimal.valueOf(pass/number));
        topicSet.setTexcellentrate(BigDecimal.valueOf(excellent/number));
        topicSetMapper.update(topicSet,queryWrapper2);
    }


}
