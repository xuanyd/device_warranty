package com.base.admin.service;

import com.base.admin.dao.VedioDao;
import com.core.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class VedioService {

    @Autowired
    private VedioDao vedioDao;

    public PageInfo getVedioPage(String title, int page, int pageSize) throws Exception{
        PageInfo pageInfo = new PageInfo();
        int start = (page -1) * pageSize;
        int size = pageSize;
        pageInfo.setPageSize(pageSize);
        pageInfo.setCurrentPage(page);
        pageInfo.setTotalCount(vedioDao.qryVedioCount(title));
        pageInfo.setInfoList(vedioDao.qryVedioList(title, start, size));
        return pageInfo;
    }

    public boolean vedioAdd(String title, String url) throws Exception{
        return vedioDao.addVedio(title, url);
    }

    public Map<String,Object> getVedioInfo(String id) throws Exception{
        return vedioDao.qryVedioInfo(id);
    }

    public boolean vedioEdit(String id, String title, String url) throws Exception{
        return vedioDao.modifyVedio(id, title, url);
    }

    public boolean vedioDelete(String id) throws Exception{
        int num = vedioDao.delVedio(id);
        if(num !=1)
            return false;
        return true;
    }
}
