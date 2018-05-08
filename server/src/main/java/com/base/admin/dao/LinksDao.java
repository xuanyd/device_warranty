package com.base.admin.dao;

import com.core.dao.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LinksDao {

    @Autowired
    private IBaseDao baseDao;

    public int qryLinksCount() throws Exception {
        String sql = "select count(1) from t_n_links where 1 = 1 ";
        return this.baseDao.getSqlCount(sql, null);
    }

    public List<Map<String, Object>> qryLinksList() throws Exception{
        String sql = " select id, name, url from t_n_links ";
        return this.baseDao.queryForList(sql, null);
    }

    public boolean addLink(String name, String url) throws Exception{
        Map<String, Object> params = new HashMap<>();
        String sql = " insert into t_n_links (name, url) values (:name, :url) ";
        params.put("name", name);
        params.put("url", url);
        int addC = this.baseDao.update(sql, params);
        if(addC > 0)
            return true;
        return false;
    }

    public int delLinks(String id) throws Exception {
        Map<String, Object> params = new HashMap<>();
        String sql = " delete from  t_n_links where id=:id";
        params.put("id", id);
        return this.baseDao.update(sql, params);
    }
}
