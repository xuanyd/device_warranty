package com.base.admin.service;

import com.base.admin.dao.LinksDao;
import com.core.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinksService {

    @Autowired
    private LinksDao linksDao;

    public PageInfo getLinksPage() throws Exception{
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurrentPage(1);
        pageInfo.setTotalCount(linksDao.qryLinksCount());
        pageInfo.setInfoList(linksDao.qryLinksList());
        return pageInfo;
    }

    public boolean linkDelete(String id) throws Exception{
        int num = linksDao.delLinks(id);
        if(num !=1)
            return false;
        return true;
    }

    public boolean linkAdd(String name, String url) throws Exception{
        return linksDao.addLink(name, url);
    }
}
