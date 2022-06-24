package com.example.demo.controller;

import com.example.demo.entity.TopicSet;
import com.example.demo.entity.course;
import com.example.demo.service.CourseService;
import com.example.demo.service.QuestionService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TopicsetService;
import com.example.demo.tools.MathUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author lenovo
 */
@Controller
@RequestMapping(value = "/Course",produces = "application/json; charset=UTF-8")
public class CourseController {

    private final CourseService courseService;
    private final StudentService studentService;
    private final TopicsetService topicsetService;
    private final QuestionService questionService;

    @Autowired
    public CourseController( QuestionService questionService, CourseService courseService, StudentService studentService,TopicsetService topicsetService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.topicsetService = topicsetService;
        this.questionService = questionService;
    }


    /**创建新课程，通过测试
     * @param jsonRequest {cname:"课程名" , introduce:"课程简介" , creator:"创建者姓名" , startTime:"课程开始时间",endTime:"课程结束时间"}
     * @return 返回int
     * @date  6.22 14:30
     *  */
    @RequestMapping(value = "/InsertCourse" ,produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int insertCourse(@RequestBody String jsonRequest) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String cid = MathUtils.getPrimaryKey();
        String cname  = jsonObject.getString("cname");
        String introduce =  jsonObject.getString("introduce");
        String creator =   jsonObject.getString("creator");
        String  startTime =   jsonObject.getString("startTime");
        String  endTime =   jsonObject.getString("endTime");
        return courseService.createCourse(cid,cname,introduce,creator,startTime,endTime);
    }


    /**展示用户所有所选课,通过测试
     * @param jsonRequest 用户信息{account:""}
     * @return 返回List<course>
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


    /**插入学生,注意：返回值不是int
     * @param jsonRequest 用户信息{account:"学生学号"，cid:"学生id",type:"在这个课里面的位置" }
     * @return 返回List<course>
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

    /**返回所有 当前账户是否是老师,
     * @param jsonRequest 用户信息{account:"学生学号"，cid:"课程id" }
     * @return 返回int(1老师，0学生)
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

    /**老师查看所有学生,通过测试
     * @param jsonRequest {cid:"课程id" }
     * @return 返回List<map<学号，姓名>>
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


    /**老师删除某个学生,注意：
     * @param jsonRequest {account:"学号" }
     * @return 0失败,成功晚点测
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



    /**增加题目到题目集,通过测试，需要传递一个包装好的JSON格式
     * @param jsonRequest {tid：“题目集id”，qtypt:"1单选，2多选，3填空，4编程",qdescribtion:"题目描述"qpoint：“分数”,qinput:"输入",qoutput:"输出"}
     * @return int 1成功 0失败
     * @date 6.23 15:30
     *
     * */
    @RequestMapping(value="/insertQuestionToTopicSet",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int insertQuestionToTopicSet (@RequestBody String jsonRequest) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String tid = jsonObject.getString("tid");
        int qtype = Integer.parseInt(jsonObject.getString("qtype"));
        String qdescribtion = jsonObject.getString("qdescribtion");
        double qpoint = Double.parseDouble(jsonObject.getString("qpoint"));
        String qinput = jsonObject.getString("qinput");
        String qoutput =jsonObject.getString("qoutput");
        questionService.insertQuestionToTopicSet(tid,qtype,qdescribtion,qpoint,qinput,qoutput);
        return  0;
    }

    /**删除题目从题目集内,注意：未测试，题目的唯一标识应该是有的，在得到所有题目的方法内
     * @param jsonRequest {id:"题目唯一标识"}
     * @return int 1成功 0失败
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

    /**
     * 修改题目从题目集内,注意：未测试，未编码
     * @param map {id:"题目唯一标识"，tid：“题目集id”,qdescribtion:"题目描述"
     *      qpoint：“分数”,qinput:"输入",qoutput:"输出"}}
     * @return int 1成功 0失败
     * @date 6.23 15:30
     *
     * */


    /**老师给课程添加题目集,注意：通过测试，使用Integer.parseInt没有校验
     * @param jsonRequest {cid:"课程号",tisexam:"是否考试1考试，0题目",
     * tstarttime:"题目集开发时间",tendtime:"题目集结束时间",SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
     * tstatus:"0：未开放，1：正在开发，2：已结束",
     * tifcansee:"结束后是否可视1.可，0，否"}
     * @return 0失败,成功1
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
        LocalDateTime start = LocalDateTime.parse(tstarttime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime end = LocalDateTime.parse(tendtime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return  topicsetService.addTopicSet(cid,tisexam,start,end,tstatus,tifcansee,tname);
    }

    /**展示所有题目集,注意：未测试，
     * @param jsonRequest {cid:"课程id" }
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


    /**展示一个题目集,注意：未测试，这里，其实展示所有题目集就已经有了，前端可以拿的到是不是不用写，
     * 如果不用写，那么前面显然有重复代码
     * @param map {cid:"课程id" }
     * @return List<TopicSet>
     * @date 6.23 15:30
     *
     * */

    /**删除一个题目集,注意：未测试，
     * @param jsonRequest {id:"题目集唯一标识" }
     * @return 未知
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

    /**修改题目集信息,注意：未测试，使用Integer.parseInt没有校验
     * @param jsonRequest { cid:"被修改题目集cid" tisexam:"是否考试1考试，0题目",
     * tstarttime:"题目集开发时间",tendtime:"题目集结束时间",SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
     * tstatus:"0：未开放，1：正在开发，2：已结束",
     * tifcansee:"结束后是否可视1.可，0，否"}
     * @return 0失败,成功1
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

    @RequestMapping(value="/insertAnswerSet",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int insertAnswerSet(@RequestBody String jsonRequest) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String tid = jsonObject.getString("tid");
        String account = jsonObject.getString("account");
        JSONArray questions = jsonObject.getJSONArray("questions");
        double sumPoint = 0;
        for (int i = 0; i < questions.length(); i++) {
            JSONObject question = questions.getJSONObject(i);
            String outputExample = question.getString("output");
            String answer = question.getString("answer");
            if(outputExample.equals(answer)){
                question.put("mypoint",question.getString("point"));
            }
            else{
                question.put("mypoint","0");
            }
            sumPoint +=Double.parseDouble(question.getString("point"));
        }
        BigDecimal sum = BigDecimal.valueOf(sumPoint);
       return courseService.insertToAnswerSet(tid,account, String.valueOf(questions),sum);
    }

}
