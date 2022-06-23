package com.example.demo.controller;

import com.example.demo.entity.TopicSet;
import com.example.demo.entity.course;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.mapper.StudentsMapper;
import com.example.demo.service.CourseService;
import com.example.demo.service.QuestionService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TopicsetService;
import com.example.demo.tools.MathUtils;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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


    /**创建新课程,注意：未测试
     * @param map {cname:"课程名" , introduce:"课程简介" , creator:"创建者姓名" , startTime:"课程开始时间",endTime:"课程结束时间"}
     * @return 返回int
     * @date 6.22 14:30
     *
     * */
    @RequestMapping(value = "/InsertCourse" ,produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int insertCourse(@RequestBody Map<String, Map<String, Object>> map){
        String cid = MathUtils.getPrimaryKey();
        String cname  =  map.get("cname").get("cname").toString();
        String introduce =  map.get("introduce").get("introduce").toString();
        String creator =  map.get("creator").get("creator").toString();
        String  startTime =  map.get("startTime").get("startTime").toString();
        String  endTime =  map.get("endTime").get("endTime").toString();
        return courseService.createCourse(cid,cname,introduce,creator,startTime,endTime);
    }


    /**展示用户所有所选课,注意：未测试
     * @param map 用户信息{account:""}
     * @return 返回List<course>
     * @date 6.22 14:30
     *
     * */
    @RequestMapping(value="/ShowPersonnelCourseList",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public List<course> showPersonnelCourseList(@RequestBody Map<String, Map<String, Object>> map){
        String account = map.get("account").get("account").toString();
        return courseService.showPublicCourseList(account);
    }


    /**插入学生,注意：返回值不是int
     * @param map 用户信息{account:"学生学号"，cid:"学生id",type:"在这个课里面的位置" }
     * @return 返回List<course>
     * @date 6.22 14:30
     *
     * */
    @RequestMapping(value="/insertStudent",produces = "application/json")
    @ResponseBody
    public int insertStudent(@RequestBody Map<String, Map<String, Object>> map){
        String account = map.get("account").get("account").toString();
        String cid = map.get("cid").get("cid").toString();
        String type = map.get("type").get("type").toString();
        return studentService.insertStudent(account,cid, Integer.parseInt(type));
    }

    /**返回所有 当前账户是否是老师,
     * @param map 用户信息{account:"学生学号"，cid:"课程id" }
     * @return 返回int(1老师，0学生)
     * @date 6.23 15:30
     *
     * */
    @RequestMapping(value="/isTeacher",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int isTeacher(@RequestBody Map<String, Map<String, Object>> map){
        String account = map.get("account").get("account").toString();
        String cid = map.get("cid").get("cid").toString();
        return studentService.isTeacher(account,cid);
    }

    /**老师查看所有学生,注意：有warning
     * @param map {cid:"课程id" }
     * @return 返回List<map<学号，姓名>>
     * @date 6.23 15:30
     *
     * */
    @RequestMapping(value="/showAllStudent",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public List<Map> showAllStudent (@RequestBody Map<String, Map<String, Object>> map){
        String cid = map.get("cid").get("cid").toString();
        return studentService.showAllStudent(cid);
    }


    /**老师删除某个学生,注意：
     * @param map {account:"学号" }
     * @return 0失败,成功晚点测
     * @date 6.23 15:30
     *
     * */
    @RequestMapping(value="/deleteStudentByAccount",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int deleteStudentByAccount(@RequestBody Map<String, Map<String, Object>> map){
        String account = map.get("account").get("account").toString();
        return  studentService.deleteStudentByAccount(account);
    }



    /**增加题目到题目集,注意：未测试，未完成，json对应的包未导入，json格式有疑问
     * @param map {tid：“题目集id”，qtypt:"1单选，2多选，3填空，4编程",qdescribtion:"题目描述"
     *           qpoint：“分数”,qinput:"输入",qoutput:"输出"}
     * @return int 1成功 0失败
     * @date 6.23 15:30
     *
     * */
    @RequestMapping(value="/insertQuestionToTopicSet",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int insertQuestionToTopicSet (@RequestBody Map<String, Map<String, Object>> map){
        String tid = map.get("tid").get("tid").toString();
        int qtypt = Integer.parseInt(map.get("qtypt").get("qtypt").toString());
        String qdescribtion = map.get("qdescribtion").get("qdescribtion").toString();
        double qpoint = Double.parseDouble(map.get("qpoint").get("qpoint").toString());
        String qinput = map.get("qinput").get("qinput").toString();
        String qoutput = map.get("qoutput").get("qoutput").toString();
        return  0;
    }

    /**删除题目从题目集内,注意：未测试，题目的唯一标识应该是有的，在得到所有题目的方法内
     * @param map {id:"题目唯一标识"}
     * @return int 1成功 0失败
     * @date 6.23 15:30
     *
     * */
    @RequestMapping(value="/deleteQuestionFromTopicSet",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int deleteQuestionFromTopicSet(@RequestBody Map<String, Map<String, Object>> map){
        String id = map.get("id").get("id").toString();
        return  questionService.deleteQuestionFromTopicSet(id);
    }

    /**修改题目从题目集内,注意：未测试，未编码
     * @param map {id:"题目唯一标识"，tid：“题目集id”,qdescribtion:"题目描述"
     *      qpoint：“分数”,qinput:"输入",qoutput:"输出"}}
     * @return int 1成功 0失败
     * @date 6.23 15:30
     *
     * */


    /**老师给课程添加题目集,注意：未测试，使用Integer.parseInt没有校验
     * @param map {cid:"课程号",tisexam:"是否考试1考试，0题目",
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
    public int addTopicSet(@RequestBody Map<String, Map<String, Object>> map){
        String cid = map.get("cid").get("cid").toString();
        int tisexam = Integer.parseInt(map.get("tisexam").get("tisexam").toString());
        String tstarttime = map.get("tstarttime").get("tstarttime").toString();
        String tendtime = map.get("tendtime").get("tendtime").toString();
        int tstatus = Integer.parseInt(map.get("tstatus").get("tstatus").toString());
        int tifcansee = Integer.parseInt(map.get("tifcansee").get("tifcansee").toString());


        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = df1.parse(tstarttime);
            date2 = df1.parse(tendtime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  topicsetService.addTopicSet(cid,tisexam,date1,date2,tstatus,tifcansee);
    }

    /**展示所有题目集,注意：未测试，
     * @param map {cid:"课程id" }
     * @return List<TopicSet>
     * @date 6.23 15:30
     *
     * */
    @RequestMapping(value="/showAllTopicSet",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public List<TopicSet> showAllTopicSet(@RequestBody Map<String, Map<String, Object>> map){
        String cid = map.get("cid").get("cid").toString();
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
     * @param map {id:"题目集唯一标识" }
     * @return 未知
     * @date 6.23 15:30
     *
     * */
    @RequestMapping(value="/deleteTopicSet",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int deleteTopicSet(@RequestBody Map<String, Map<String, Object>> map){
        String id = map.get("id").get("id").toString();
        return  topicsetService.deleteTopicSet(id);
    }

    /**修改题目集信息,注意：未测试，使用Integer.parseInt没有校验
     * @param map { cid:"被修改题目集cid" tisexam:"是否考试1考试，0题目",
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
    public int updateTopicSet(@RequestBody Map<String, Map<String, Object>> map){
        String cid = map.get("cid").get("cid").toString();
        int tisexam = Integer.parseInt(map.get("tisexam").get("tisexam").toString());
        String tstarttime = map.get("tstarttime").get("tstarttime").toString();
        String tendtime = map.get("tendtime").get("tendtime").toString();
        int tstatus = Integer.parseInt(map.get("tstatus").get("tstatus").toString());
        int tifcansee = Integer.parseInt(map.get("tifcansee").get("tifcansee").toString());


        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = df1.parse(tstarttime);
            date2 = df1.parse(tendtime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  topicsetService.updateTopicSet(cid,tisexam,date1,date2,tstatus,tifcansee);
    }


    //增：insertStudent  删：  改：  查：showPersonnelCourseList
/*

//错误位置的测试代码请不要学习
    @RequestMapping(value = "/InsertCourseTest" ,produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int insertCourse(){
        return courseService.createCourse("004","操作系统","2020级操作系统","罗老师","2022-06-18 15:32:12","2022-07-29 15:32:17");
    }


//没有注释的测试，不明所以
    @RequestMapping(value = "/courseTest" ,produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public List<course> courseTest(){
       return courseMapper.selectByMap(null);
    }
*/

}
