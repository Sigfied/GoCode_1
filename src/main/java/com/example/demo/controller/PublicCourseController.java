package com.example.demo.controller;

import com.example.demo.entity.course;
import com.example.demo.service.CourseService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author flz
 */
@Controller
@RequestMapping(value = "/PublicCourse",produces = "application/json; charset=UTF-8")
public class PublicCourseController {

    private final CourseService courseService;

    @Autowired
    public PublicCourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**根据数据返回公共课程列表,通过测试
     * @param map 模糊查询内容{tip:“..”}
     * @return 返回一个List<course>对象
     * @date 6.22 14:30
     *
     * */
    @RequestMapping(value="/ShowPublicCourseList" ,produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public List<course> showPublicCourseList(@RequestBody String jsonRequest) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String account = jsonObject.getString("account");
        return courseService.showPublicCourseList(account);
    }

    /**用户提交课程id查询到课,通过测试
     * @param map 课程id{cid:".."}
     * @return 返回course
     * @date 6.22 14:30
     *
     * */
    @RequestMapping(value="/SearchCourseWithCid",produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = {"*"})
    public course searchCourseWithCid(@RequestBody Map<String, Map<String, Object>> map){
        String cid = map.get("cid").get("cid").toString();
        return courseService.searchCourseWithCid(cid);
    }

    //修改完成usercontroller，CourseController,以及配套sercive，impl
    //添加了返回用户信息，添加了修改用户信息，查询公共课列表，根据id查询课，展示用户所选课

}
