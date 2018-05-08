package com.base.admin.service;

import com.base.admin.dao.SearchAdminDao;
import com.core.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchAdminService {

    @Autowired
    SearchAdminDao searchAdminDao;

    public PageInfo getPatientPage(String name, int page, int pageSize) throws Exception{
        PageInfo pageInfo = new PageInfo();
        int start = (page -1) * pageSize;
        int size = pageSize;
        pageInfo.setPageSize(pageSize);
        pageInfo.setCurrentPage(page);
        pageInfo.setTotalCount(searchAdminDao.qryPatientCount(name));
        pageInfo.setInfoList(searchAdminDao.qryPatientList(name, start, size));
        return pageInfo;
    }

    public PageInfo getCheckTemplatePage(String name, int page, int pageSize) throws Exception{
        PageInfo pageInfo = new PageInfo();
        int start = (page -1) * pageSize;
        int size = pageSize;
        pageInfo.setPageSize(pageSize);
        pageInfo.setCurrentPage(page);
        pageInfo.setTotalCount(searchAdminDao.qryCheckTemplateCount(name));
        pageInfo.setInfoList(searchAdminDao.qryCheckTemplateList(name, start, size));
        return pageInfo;
    }

    public boolean addCheckTemplate(String template_name, String content, String remark) throws Exception{
        return searchAdminDao.addCheckTemplate(template_name, content, remark);
    }

    public boolean templateDelete(String id) throws Exception{
        int num = searchAdminDao.delTemplate(id);
        if(num !=1)
            return false;
        return true;
    }

    public PageInfo getCheckPage(String id, int page, int pageSize) throws Exception{
        PageInfo pageInfo = new PageInfo();
        String patientName = searchAdminDao.qryPatientName(id);
        int start = (page -1) * pageSize;
        int size = pageSize;
        pageInfo.setPageSize(pageSize);
        pageInfo.setCurrentPage(page);
        pageInfo.setTotalCount(searchAdminDao.qryCheckCount(patientName));
        pageInfo.setInfoList(searchAdminDao.qryCheckList(patientName, start, size));
        return pageInfo;
    }

    public boolean checkAdd(String patientId, String content, String remark) throws Exception{
        String patientName = searchAdminDao.qryPatientName(patientId);
        return searchAdminDao.addCheck(patientName, content, remark);
    }

    public boolean checkDelete(String id) throws Exception{
        int num = searchAdminDao.delCheck(id);
        if(num !=1)
            return false;
        return true;
    }
}
