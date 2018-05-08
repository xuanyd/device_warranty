package com.base.admin.controller;

import com.base.admin.service.SearchAdminService;
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
public class SearchAdminController {

    @Autowired
    SearchAdminService searchAdminService;

    @RequestMapping("admin/search/patientlist")
    public @ResponseBody
    Map noticeList(HttpServletRequest request,
                   @RequestBody Map<String, String> map) {
        Map<String, Object> retMap = new HashMap<>();
        String name = map.get("name");
        String currentPage = map.get("page");
        int page = 1;

        int pageSize = 10;
        if ( currentPage == null || currentPage.equals("") ) {
            page = 1;
        } else {
            page = Integer.valueOf(currentPage).intValue();
        }
        try{
            PageInfo pageInfo = searchAdminService.getPatientPage(name, page, pageSize);
            retMap.put("pageInfo", pageInfo);
            retMap.put("flag", Constant.RESCODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("flag", Constant.RESCODE_EXCEPTION);
        }
        return retMap;
    }

    @RequestMapping("admin/search/checktemplate-list")
    public @ResponseBody
    Map checktemplateList(HttpServletRequest request,
                   @RequestBody Map<String, String> map) {
        Map<String, Object> retMap = new HashMap<>();
        String name = map.get("name");
        String currentPage = map.get("page");
        String pageSizeStr = map.get("pageSize");
        int page = 1;
        int pageSize = 10;
        if ( pageSizeStr == null || pageSizeStr.equals("") ) {
            pageSize = 10;
        } else {
            pageSize = Integer.valueOf(pageSizeStr).intValue();
        }
        if ( currentPage == null || currentPage.equals("") ) {
            page = 1;
        } else {
            page = Integer.valueOf(currentPage).intValue();
        }
        try{
            PageInfo pageInfo = searchAdminService.getCheckTemplatePage(name, page, pageSize);
            retMap.put("pageInfo", pageInfo);
            retMap.put("flag", Constant.RESCODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("flag", Constant.RESCODE_EXCEPTION);
        }
        return retMap;
    }

    @RequestMapping("admin/search/checktemplate-add")
    public
    @ResponseBody Map templateAdd(HttpServletRequest request,
                                @RequestBody Map<String,String> map) {
        Map<String, Object> retMap = new HashMap<>();
        String template_name = map.get("template_name");
        String content = map.get("content");
        String remark = map.get("remark");
        try {
            boolean addFlag = searchAdminService.addCheckTemplate(template_name, content, remark);
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

    @RequestMapping("admin/search/checktemplate-delete")
    public @ResponseBody Map<String, Object> templateDelete(HttpServletRequest request,
                                                          @RequestBody Map<String,String> map){
        Map<String, Object> retMap = new HashMap<>();
        String id = map.get("id");
        try {
            boolean deleteFlat = searchAdminService.templateDelete(id);
            if(deleteFlat)
                retMap.put("flag", Constant.RESCODE_SUCCESS);
            else {
                retMap.put("flag", Constant.RESCODE_DELETE_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("flag", Constant.RESCODE_EXCEPTION);
            retMap.put("msg", "查询异常");
        }
        return retMap;
    }

    @RequestMapping("admin/search/check-list")
    public @ResponseBody
    Map checkList(HttpServletRequest request,
                          @RequestBody Map<String, String> map) {
        Map<String, Object> retMap = new HashMap<>();
        String id = map.get("id");
        String currentPage = map.get("page");
        int page = 1;
        int pageSize = 10;
        if ( currentPage == null || currentPage.equals("") ) {
            page = 1;
        } else {
            page = Integer.valueOf(currentPage).intValue();
        }
        try{
            PageInfo pageInfo = searchAdminService.getCheckPage(id, page, pageSize);
            retMap.put("pageInfo", pageInfo);
            retMap.put("flag", Constant.RESCODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("flag", Constant.RESCODE_EXCEPTION);
        }
        return retMap;
    }

    @RequestMapping("admin/search/check-add")
    public
    @ResponseBody Map checkAdd(HttpServletRequest request,
                                @RequestBody Map<String,String> map) {
        Map<String, Object> retMap = new HashMap<>();
        String patientId = map.get("patientId");
        String content = map.get("content");
        String remark = map.get("remark");
        try {
            boolean addFlag = searchAdminService.checkAdd(patientId, content, remark);
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

    @RequestMapping("admin/search/check-delete")
    public @ResponseBody Map<String, Object> checkDelete(HttpServletRequest request,
                                                          @RequestBody Map<String,String> map){
        Map<String, Object> retMap = new HashMap<>();
        String id = map.get("id");
        try {
            boolean deleteFlat = searchAdminService.checkDelete(id);
            if(deleteFlat)
                retMap.put("flag", Constant.RESCODE_SUCCESS);
            else {
                retMap.put("flag", Constant.RESCODE_DELETE_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("flag", Constant.RESCODE_EXCEPTION);
            retMap.put("msg", "查询异常");
        }
        return retMap;
    }

}
