package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.entity.Answerset;
import com.example.demo.entity.Question;
import com.example.demo.entity.TopicSet;
import com.example.demo.entity.course;
import com.example.demo.mapper.AnswerSetMapper;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.TopicSetMapper;
import com.example.demo.service.CourseService;
import com.example.demo.service.QuestionService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TopicsetService;
import com.example.demo.tools.MathUtils;
import com.example.demo.tools.RunCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lenovo
 */
@Controller
@Slf4j
@RequestMapping(value = "/Course",produces = "application/json; charset=UTF-8")
@CrossOrigin(origins = {"*"})
public class CourseController {

    private final CourseService courseService;
    private final StudentService studentService;
    private final TopicsetService topicsetService;
    private final QuestionService questionService;
    private final CourseMapper courseMapper;

    private final QuestionMapper questionMapper;

    private final TopicSetMapper topicSetMapper;

    private final AnswerSetMapper answerSetMapper;
    @Autowired
    public CourseController(QuestionService questionService, CourseService courseService, StudentService studentService, TopicsetService topicsetService, CourseMapper courseMapper, QuestionMapper questionMapper, TopicSetMapper topicSetMapper, AnswerSetMapper answerSetMapper) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.topicsetService = topicsetService;
        this.questionService = questionService;
        this.courseMapper = courseMapper;
        this.questionMapper = questionMapper;
        this.topicSetMapper = topicSetMapper;
        this.answerSetMapper = answerSetMapper;
    }


    /**??????????????????????????????
     * @param jsonRequest {cname:"?????????" , introduce:"????????????" , creator:"???????????????" , startTime:"??????????????????",endTime:"??????????????????"}
     * @return ??????int
     * @date  6.22 14:30
     *  */
    @RequestMapping(value = "/InsertCourse" ,produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int insertCourse(@RequestBody String jsonRequest) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String cid = MathUtils.getPrimaryKey();
        String account = jsonObject.getString("account");
        String cname  = jsonObject.getString("cname");
        String introduce =  jsonObject.getString("introduce");
        String creator =   jsonObject.getString("creator");
        String  startTime =   jsonObject.getString("startTime");
        String  endTime =   jsonObject.getString("endTime");
        int i = courseService.createCourse(cid,cname,introduce,creator,startTime,endTime);
        studentService.insertStudent(account,cid, 1);
        return i;
    }

    /**??????????????????,?????????????????????
     * @return List<course>
     * @date 6.29 15:30
     *
     * */
    @RequestMapping(value="/showPersonalCourseList",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public List<course> showPersonalCourseList() {
        return new ArrayList<>(courseMapper.selectList(null));
    }



    /**???????????????????????????,????????????
     * @param jsonRequest ????????????{account:""}
     * @return ??????List<course>
     * @date 6.22 14:30
     *
     * */
    @RequestMapping(value="/ShowPersonnelCourseList",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public List<course> showPersonnelCourseList(@RequestBody String jsonRequest) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String account = jsonObject.getString("account");
        return courseService.showPublicCourseList(account);
    }

    /**????????????????????????,???????????????
     * @param jsonRequest ????????????{account:""}
     * @return ??????List<course>
     * @date 6.29 14:30
     *
     * */
    @RequestMapping(value="/showCreateCourseList",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public List<course> showCreateCourseList(@RequestBody String jsonRequest) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String account = jsonObject.getString("account");
        return courseService.showCreateCourseList(account);
    }


    /**????????????,????????????????????????int
     * @param jsonRequest ????????????{account:"????????????"???cid:"??????id",type:"???????????????????????????" }
     * @return ??????List<course>
     * @date 6.22 14:30
     *
     * */
    @RequestMapping(value="/insertStudent",produces = "application/json")
    @ResponseBody
    public int insertStudent(@RequestBody String jsonRequest) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String account = jsonObject.getString("account");
        String cid = jsonObject.getString("cid");
        String type = jsonObject.getString("type");
        return studentService.insertStudent(account,cid, Integer.parseInt(type));
    }

    /**???????????? ???????????????????????????,
     * @param jsonRequest ????????????{account:"????????????"???cid:"??????id" }
     * @return ??????int(1?????????0??????)
     * @date 6.23 15:30
     * */
    @RequestMapping(value="/isTeacher",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int isTeacher(@RequestBody String jsonRequest) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String account = jsonObject.getString("account");
        String cid = jsonObject.getString("cid");
        return studentService.isTeacher(account,cid);
    }

    /**????????????????????????,????????????
     * @param jsonRequest {cid:"??????id" }
     * @return ??????List<map<???????????????>>
     * @date 6.23 15:30
     *
     * */
    @RequestMapping(value="/showAllStudent",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public String showAllStudent (@RequestBody String jsonRequest) throws JSONException, JsonProcessingException {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String cid = jsonObject.getString("cid");
        return studentService.showAllStudent(cid);
    }


    /**????????????????????????,?????????
     * @param jsonRequest {account:"??????" }
     * @return 0??????,???????????????
     * @date 6.23 15:30
     *
     * */
    @RequestMapping(value="/deleteStudentByAccount",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int deleteStudentByAccount(@RequestBody String jsonRequest) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String account = jsonObject.getString("account");
        return  studentService.deleteStudentByAccount(account);
    }



    /**????????????????????????,?????????????????????????????????????????????JSON??????
     * @param jsonRequest {tid???????????????id??????qtypt:"1?????????2?????????3?????????4??????",qdescribtion:"????????????"qpoint???????????????,qinput:"??????",qoutput:"??????"}
     * @return int 1?????? 0??????
     * @date 6.23 15:30
     *
     * */
    @RequestMapping(value="/insertQuestionToTopicSet",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int insertQuestionToTopicSet (@RequestBody String jsonRequest) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        log.info(String.valueOf(jsonObject));
        String tid = jsonObject.getString("tid");
        int qtype = Integer.parseInt(jsonObject.getString("qtype"));
        String qdescribtion = jsonObject.getString("qdescribtion");
        double qpoint = Double.parseDouble(jsonObject.getString("qpoint"));
        String qinput = jsonObject.getString("qinput");
        String qoutput =jsonObject.getString("qoutput");
        return questionService.insertQuestionToTopicSet(tid,qtype,qdescribtion,qpoint,qinput,qoutput);
    }

    /**????????????????????????,?????????????????????????????????????????????JSON??????
     * @param jsonRequest {tid???????????????id??????qtypt:"1?????????2?????????3?????????4??????",qdescribtion:"????????????"qpoint???????????????,qinput:"??????",qoutput:"??????"}
     * @return int 1?????? 0??????
     * @date 6.23 15:30
     *
     * */
    @RequestMapping(value="/insertQuestionByPhotoToTopicSet",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int insertQuestionByPhotoToTopicSet (@RequestBody String jsonRequest) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        log.info(String.valueOf(jsonObject));
        String tid = jsonObject.getString("tid");
        int qtype = Integer.parseInt(jsonObject.getString("qtype"));
        String qdescribtion = jsonObject.getString("qdescribtion");
        double qpoint = Double.parseDouble(jsonObject.getString("qpoint"));
        String qinput = jsonObject.getString("qinput");
        String qoutput =jsonObject.getString("qoutput");
        JSONObject jsonObjectInput = new JSONObject();
        jsonObjectInput.put("input", qinput);
        JSONObject jsonObjectOutput = new JSONObject();
        jsonObjectOutput.put("output", qoutput);
        return questionService.insertQuestionToTopicSet(tid,qtype,qdescribtion,qpoint,jsonObjectInput.toString(),jsonObjectOutput.toString());
    }



    /**???????????????????????????,?????????????????????????????????????????????????????????????????????????????????????????????
     * @param jsonRequest {id:"??????????????????"}
     * @return int 1?????? 0??????
     * @date 6.23 15:30
     *
     * */
    @RequestMapping(value="/deleteQuestionFromTopicSet",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int deleteQuestionFromTopicSet(@RequestBody String jsonRequest) throws JSONException{
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String id = jsonObject.getString("id");
        return  questionService.deleteQuestionFromTopicSet(id);
    }


    /**??????????????????????????????,??????????????????????????????Integer.parseInt????????????
     * @param jsonRequest {cid:"?????????",tisexam:"????????????1?????????0??????",
     * tstarttime:"?????????????????????",tendtime:"?????????????????????",SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
     * tstatus:"0???????????????1??????????????????2????????????",
     * tifcansee:"?????????????????????1.??????0??????"}
     * @return 0??????,??????1
     * @date 6.23 15:30
     *
     * */
    @RequestMapping(value="/addTopicSet",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int addTopicSet(@RequestBody String jsonRequest) throws JSONException{
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String cid = jsonObject.getString("cid");
        int tisexam = Integer.parseInt( jsonObject.getString("tisexam"));
        String tstarttime = jsonObject.getString("tstarttime");
        String tendtime = jsonObject.getString("tendtime");
        int tstatus = Integer.parseInt(jsonObject.getString("tstatus"));
        int tifcansee = Integer.parseInt(jsonObject.getString("tifcansee"));
        String tname = jsonObject.getString("tname");
        LocalDateTime start = LocalDateTime.parse(tstarttime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime end = LocalDateTime.parse(tendtime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return  topicsetService.addTopicSet(cid,tisexam,start,end,tstatus,tifcansee,tname);
    }

    /**?????????????????????,?????????????????????
     * @param jsonRequest {cid:"??????id" }
     * @return List<TopicSet>
     * @date 6.23 15:30
     *
     * */
    @RequestMapping(value="/showAllTopicSet",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public List<TopicSet> showAllTopicSet(@RequestBody String jsonRequest) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String cid = jsonObject.getString("cid");
        return topicsetService.showAllTopicSet(cid);
    }


    /**?????????????????????,?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     * ???????????????????????????????????????????????????
     * @param map {cid:"??????id" }
     * @return List<TopicSet>
     * @date 6.23 15:30
     *
     * */

    /**?????????????????????,?????????????????????
     * @param jsonRequest {id:"?????????????????????" }
     * @return ??????
     * @date 6.23 15:30
     *
     * */
    @RequestMapping(value="/deleteTopicSet",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int deleteTopicSet(@RequestBody String jsonRequest) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String id = jsonObject.getString("id");
        return  topicsetService.deleteTopicSet(id);
    }

    /**?????????????????????,???????????????????????????Integer.parseInt????????????
     * @param jsonRequest { cid:"??????????????????cid" tisexam:"????????????1?????????0??????",
     * tstarttime:"?????????????????????",tendtime:"?????????????????????",SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
     * tstatus:"0???????????????1??????????????????2????????????",
     * tifcansee:"?????????????????????1.??????0??????"}
     * @return 0??????,??????1
     * @date 6.23 15:30
     *
     * */
    @RequestMapping(value="/updateTopicSet",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int updateTopicSet(@RequestBody String jsonRequest) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String cid = jsonObject.getString("cid");
        int tisexam = Integer.parseInt( jsonObject.getString("tisexam"));
        String tstarttime = jsonObject.getString("tstarttime");
        String tendtime = jsonObject.getString("tendtime");
        int tstatus = Integer.parseInt(jsonObject.getString("tstatus"));
        int tifcansee = Integer.parseInt(jsonObject.getString("tifcansee"));
        LocalDateTime start = LocalDateTime.parse(tstarttime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime end = LocalDateTime.parse(tendtime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return  topicsetService.updateTopicSet(cid,tisexam,start,end,tstatus,tifcansee);
    }

    @RequestMapping(value="/insertAnswer",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int insertAnswer(@RequestBody String jsonRequest) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String tid = jsonObject.getString("tid");
        String account = jsonObject.getString("account");
        JSONArray questions = jsonObject.getJSONArray("questions");
        String point = jsonObject.getString("point");
        BigDecimal sumPoint = BigDecimal.valueOf(Long.parseLong(point));
        return courseService.insertToAnswerSet(tid,account, String.valueOf(questions),sumPoint);
    }


    @RequestMapping(value="/insertAnswerSet",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int insertAnswerSet(@RequestBody String jsonRequest) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String tid = jsonObject.getString("tid");
        String account = jsonObject.getString("account");
        JSONArray questions = jsonObject.getJSONArray("questions");
        BigDecimal sumPoint = BigDecimal.valueOf(0);
        for (int i = 0; i < questions.length(); i++) {
            JSONObject question = questions.getJSONObject(i);
            String outputExample = question.getString("output");
            String answer = question.getString("answer");
            if(outputExample.equals(answer)){
                question.put("mypoint",question.getString("point"));
                sumPoint = sumPoint.add(new BigDecimal(question.getString("point")));
            }
            else{
                question.put("mypoint","0");
                sumPoint = sumPoint.add(BigDecimal.valueOf(0));
            }
        }
       return courseService.insertToAnswerSet(tid,account, String.valueOf(questions),sumPoint);
    }


    /**
     * @author GYJ
     * @param jsonRequest ?????????code ,input,lang
     * */
    @RequestMapping(value="/getRunCodeResult",produces = "application/json")
    public String getRunCodeResult(String jsonRequest) throws JSONException, IOException {
        JSONObject jsonResult = new JSONObject(jsonRequest);
        String code = jsonResult.getString("code");
        String input = jsonResult.getString("input");
        String lang = jsonResult.getString("lang");
        return RunCode.getResult(code,input,lang);
    }


    /**??????????????????????????????,?????????????????????
     * @param jsonRequest {tid:""}
     * @return List<course>
     * @date 6.29 15:30
     *
     * */
    @RequestMapping(value="/showAllQuestion",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public List<Question> showAllQuestion(@RequestBody String jsonRequest) throws JSONException {
        JSONObject jsonResult = new JSONObject(jsonRequest);
        String tid = jsonResult.getString("tid");
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tid",tid).orderByAsc("qtype");
        return new ArrayList<>(questionMapper.selectList(queryWrapper));
    }

    @RequestMapping(value="/codeTest",produces = "application/json")
    @ResponseBody
    public String codeTest(@RequestBody String jsonRequest) throws JSONException, IOException {
        JSONObject jsonResult = new JSONObject(jsonRequest);
        log.info(jsonRequest);
        String code = jsonResult.getString("code");
        String input = jsonResult.getString("input");
        String lang = jsonResult.getString("lang");
        return RunCode.getResult(code, input, lang);
    }


    @RequestMapping(value="/updateSumPoint",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int updateSumPoint(@RequestBody String jsonRequest) throws JSONException {
        JSONObject jsonResult = new JSONObject(jsonRequest);
        String tid = jsonResult.getString("tid");
        String tsumPoint = jsonResult.getString("tsumpoint");
        log.info(tsumPoint);
        UpdateWrapper<TopicSet> update = new UpdateWrapper<>();
        update.eq("tid",tid).set("tsumpoint",tsumPoint);
        return topicSetMapper.update(null, update);
    }


    @RequestMapping(value = "getDataFromAnswerSet",produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public String getDataFromAnswerSet(@RequestBody String json) throws JSONException {
        JSONObject jsonResult = new JSONObject(json);
        String tid = jsonResult.getString("tid");
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
        JSONObject  result = new JSONObject();
        result.put("average",full.divide(BigDecimal.valueOf(number)));
        result.put("passingRate",BigDecimal.valueOf(pass/number));
        result.put("excellentRate",BigDecimal.valueOf(excellent/number));
        return result.toString();
    }

    @RequestMapping(value = "getAllAnswer",produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public List getAllAnswer(@RequestBody String json) throws JSONException{
        JSONObject jsonObject = new JSONObject(json);
        String tid = jsonObject.getString("tid");
        QueryWrapper<Answerset> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("account","apoint").eq("tid",tid);
        return answerSetMapper.selectList(queryWrapper);
    }


}
