package com.base.admin.service;

import com.base.admin.dao.CoursewareDao;
import com.core.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CoursewareService {

    @Autowired
    private CoursewareDao coursewareDao;

    public PageInfo getCoursewarePage(String title, int page, int pageSize) throws Exception{
        PageInfo pageInfo = new PageInfo();
        int start = (page -1) * pageSize;
        int size = pageSize;
        pageInfo.setPageSize(pageSize);
        pageInfo.setCurrentPage(page);
        pageInfo.setTotalCount(coursewareDao.qryCoursewareCount(title));
        pageInfo.setInfoList(coursewareDao.qryCoursewareList(title, start, size));
        return pageInfo;
    }

    public boolean coursewareAdd(String title, String url) throws Exception{
        return coursewareDao.addCourseware(title, url);
    }

    public Map<String,Object> getCoursewareInfo(String id) throws Exception{
        return coursewareDao.qryCoursewareInfo(id);
    }

    public boolean coursewareEdit(String id, String title, String url) throws Exception{
        return coursewareDao.modifyCourseware(id, title, url);
    }

    public boolean coursewareDelete(String id) throws Exception{
        int num = coursewareDao.delCourseware(id);
        if(num !=1)
            return false;
        return true;
    }
}
