package com.base.admin.controller;

import com.base.admin.service.CoursewareService;
import com.core.util.Constant;
import com.core.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CoursewareController {

    @Autowired
    private CoursewareService coursewareService;

    @RequestMapping("admin/coursewarelist")
    public @ResponseBody
    Map noticeList(HttpServletRequest request,
                   @RequestBody Map<String, String> map) {
        Map<String, Object> retMap = new HashMap<>();
        String title = map.get("title");
        String currentPage = map.get("page");
        int page = 1;

        int pageSize = 10;
        if ( currentPage == null || currentPage.equals("") ) {
            page = 1;
        } else {
            page = Integer.valueOf(currentPage).intValue();
        }
        try{
            PageInfo pageInfo = coursewareService.getCoursewarePage(title, page, pageSize);
            retMap.put("pageInfo", pageInfo);
            retMap.put("flag", Constant.RESCODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("flag", Constant.RESCODE_EXCEPTION);
        }
        return retMap;
    }

    @RequestMapping("admin/courseware-add")
    public
    @ResponseBody Map coursewareAdd(HttpServletRequest request,
                                @RequestBody Map<String,String> map) {
        Map<String, Object> retMap = new HashMap<>();
        String title = map.get("title");
        String url = map.get("url");
        try {
            boolean addFlag = coursewareService.coursewareAdd(title, url);
            if(addFlag) {
                retMap.put("flag", Constant.RESCODE_SUCCESS);
            } else {
                retMap.put("flag", Constant.RESCODE_EXCEPTION);
                retMap.put("msg", "添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("flag", Constant.RESCODE_EXCEPTION);
            retMap.put("msg", "执行添加异常");
        }
        return retMap;
    }

    @RequestMapping("admin/courseware-delete")
    public @ResponseBody Map<String, Object> noticeDelete(HttpServletRequest request,
                                                          @RequestBody Map<String,String> map){
        Map<String, Object> retMap = new HashMap<>();
        String id = map.get("id");
        try {
            boolean deleteFlat = coursewareService.coursewareDelete(id);
            if(deleteFlat)
                retMap.put("flag", Constant.RESCODE_SUCCESS);
            else {
                retMap.put("flag", Constant.RESCODE_DELETE_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("flag", Constant.RESCODE_EXCEPTION);
        }
        return retMap;
    }

    @RequestMapping("admin/courseware-detail")
    public @ResponseBody Map<String, Object> coursewareDetail(HttpServletRequest request, @RequestBody Map<String,String> map){
        Map<String, Object> retMap = new HashMap<>();
        String id = map.get("id");
        try {
            Map<String, Object> coursewareInfo = coursewareService.getCoursewareInfo(id);
            retMap.put("flag", Constant.RESCODE_SUCCESS);
            retMap.put("coursewareInfo", coursewareInfo);
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("flag", Constant.RESCODE_EXCEPTION);
            retMap.put("msg", "查询异常");
        }
        return retMap;
    }

    @RequestMapping("admin/courseware-edit")
    public  @ResponseBody Map noticeEdit(HttpServletRequest request,
                                         @RequestBody Map<String,String> map) {
        Map<String, Object> retMap = new HashMap<>();
        String id = map.get("id");
        String title = map.get("title");
        String url = map.get("url");
        try {
            boolean addFlag = coursewareService.coursewareEdit(id, title, url);
            if(addFlag) {
                retMap.put("flag", Constant.RESCODE_SUCCESS);
            } else {
                retMap.put("flag", Constant.RESCODE_EXCEPTION);
                retMap.put("msg", "修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("flag", Constant.RESCODE_EXCEPTION);
            retMap.put("msg", "执行修改异常");
        }
        return retMap;
    }

}
