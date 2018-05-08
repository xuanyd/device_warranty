package com.base.admin.service;

import com.base.admin.dao.NoticeDao;
import com.core.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    public PageInfo getNoticePage(String title, int page, int pageSize) throws Exception{
        PageInfo pageInfo = new PageInfo();
        int start = (page -1) * pageSize;
        int size = pageSize;
        pageInfo.setPageSize(pageSize);
        pageInfo.setCurrentPage(page);
        pageInfo.setTotalCount(noticeDao.qryNoticeCount(title));
        pageInfo.setInfoList(noticeDao.qryNoticeList(title, start, size));
        return pageInfo;
    }

    public boolean noticeAdd(String noticeType, String title, String content) throws Exception{
        return noticeDao.addNotice(noticeType, title, content);
    }

    public Map<String,Object> getNoticeInfo(String id) throws Exception{
        return noticeDao.qryNoticeInfo(id);
    }

    public boolean noticeEdit(String id, String noticeType, String title, String content) throws Exception{
        return noticeDao.modifyNotice(id, noticeType, title, content);
    }

    public boolean noticeDelete(String id) throws Exception{
        int num = noticeDao.delNotice(id);
        if(num !=1)
            return false;
        return true;
    }
}
