package com.base.admin.dao;

import com.core.dao.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CoursewareDao {

    @Autowired
    private IBaseDao baseDao;

    public int qryCoursewareCount(String title) throws Exception{
        Map<String, Object> params = new HashMap<>();
        String sql = "select count(1) from t_n_courseware where 1 = 1 ";
        if (title  != null && !title.equals("")) {
            params.put("title", title);
            sql += " and title like " + "'%" + title + "%'";
        }
        return this.baseDao.getSqlCount(sql, params);
    }

    public List<Map<String,Object>> qryCoursewareList(String title, int start, int size) throws Exception{
        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("size", size);
        String sql = " select id, title, url from t_n_courseware where 1 = 1 ";
        if(title != null && !title.equals("")) {
            params.put("title", title);
            sql += " and title like " + "'%" + title + "%'";;
        }
        sql += " limit :start, :size ";
        return this.baseDao.queryForList(sql, params);
    }

    public boolean addCourseware(String title, String url) throws Exception{
        Map<String, Object> params = new HashMap<>();
        String sql = " insert into t_n_courseware (title, url) values (:title, :url) ";
        params.put("title", title);
        params.put("url", url);
        int addC = this.baseDao.update(sql, params);
        if(addC > 0)
            return true;
        return false;
    }

    public int delCourseware(String id) throws Exception{
        Map<String, Object> params = new HashMap<>();
        String sql = " delete from t_n_courseware where id=:id";
        params.put("id", id);
        return this.baseDao.update(sql, params);
    }

    public Map<String,Object> qryCoursewareInfo(String id) throws  Exception{
        Map<String, Object> params = new HashMap<>();
        String sql = " select * from t_n_courseware where id = :id ";
        params.put("id", id);
        return this.baseDao.queryForMap(sql, params);
    }

    public boolean modifyCourseware(String id, String title, String url) throws Exception{
        Map<String, Object> params = new HashMap<>();
        String sql = " update t_n_courseware set title = :title, url = :url where id = :id";
        params.put("id", id);
        params.put("title", title);
        params.put("url", url);
        int updateC = this.baseDao.update(sql, params);
        if(updateC >0)
            return true;
        return false;
    }
}
