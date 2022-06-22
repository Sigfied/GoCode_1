package com.example.demo.controller;

import com.example.demo.entity.course;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.mapper.StudentsMapper;
import com.example.demo.service.CourseService;
import com.example.demo.tools.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author lenovo
 */
@Controller
@RequestMapping(value = "/Course",produces = "application/json; charset=UTF-8")
public class CourseController {

    private final CourseMapper courseMapper;
    private final CourseService courseService;
    private final StudentsMapper studentsMapper;

    @Autowired
    public CourseController(CourseMapper courseMapper, CourseService courseService, StudentsMapper studentsMapper) {
        this.courseMapper = courseMapper;
        this.courseService = courseService;
        this.studentsMapper = studentsMapper;
    }

    @RequestMapping(value = "/courseTest" ,produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public List<course> courseTest(){
       return courseMapper.selectByMap(null);
    }

    @RequestMapping(value = "/InsertCourseTest" ,produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public int insertCourse(){
        return courseService.createCourse("004","操作系统","2020级操作系统","罗老师","2022-06-18 15:32:12","2022-07-29 15:32:17");
    }

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


    /**展示用户所选课,注意：未测试
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


    @RequestMapping(value="/insertStudent",produces = "application/json")
    @ResponseBody
    public int insertStudent(@RequestBody Map<String, Map<String, Object>> map){
        String account = map.get("account").get("account").toString();
        String cid = map.get("cid").get("cid").toString();
        String type = map.get("type").get("type").toString();
        return courseService.insertStudent(account,cid, Integer.parseInt(type));
    }

}
