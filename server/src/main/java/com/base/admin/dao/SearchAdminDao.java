package com.base.admin.dao;

import com.core.dao.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SearchAdminDao {

    @Autowired
    private IBaseDao baseDao;

    public List<Map<String,Object>> qryPatientList(String name, int start, int size) throws Exception{
        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("size", size);
        String sql = " select " +
                " id, visit_department, sex, study_item, patient_name, report_date_time " +
                " from patinfo where 1 = 1 ";
        if(name != null && !name.equals("")) {
            params.put("name", name);
            sql += " and patient_name like " + "'%" + name + "%'";;
        }
        sql += " order by report_name desc limit :start, :size ";
        return this.baseDao.queryForList(sql, params);
    }

    public int qryPatientCount(String name) throws Exception{
        Map<String, Object> params = new HashMap<>();
        String sql = "select count(1) from patinfo where 1 = 1 ";
        if (name  != null && !name.equals("")) {
            params.put("name", name);
            sql += " and patient_name like " + "'%" + name + "%'";
        }
        return this.baseDao.getSqlCount(sql, params);
    }

    public List<Map<String,Object>> qryCheckTemplateList(String name, int start, int size) throws Exception{
        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("size", size);
        String sql = " select " +
                " id, template_name, content, remark " +
                " from t_check_template where 1 = 1 ";
        if(name != null && !name.equals("")) {
            params.put("name", name);
            sql += " and template_name like " + "'%" + name + "%'";
        }
        sql += " limit :start, :size ";
        return this.baseDao.queryForList(sql, params);
    }

    public int qryCheckTemplateCount(String name) throws Exception{
        Map<String, Object> params = new HashMap<>();
        String sql = "select 1 from t_check_template where 1 = 1 ";
        if (name  != null && !name.equals("")) {
            params.put("name", name);
            sql += " and template_name like " + "'%" + name + "%'";
        }
        return this.baseDao.getSqlCount(sql, params);
    }

    public boolean addCheckTemplate(String template_name, String content, String remark) throws Exception{
        Map<String, Object> params = new HashMap<>();
        String sql = " insert into t_check_template (template_name, content, remark) values (:template_name, :content, :remark) ";
        params.put("template_name", template_name);
        params.put("content", content);
        params.put("remark", remark);
        int addC = this.baseDao.update(sql, params);
        if(addC > 0)
            return true;
        return false;
    }

    public int delTemplate(String id) throws Exception{
        Map<String, Object> params = new HashMap<>();
        String sql = " delete from t_check_template where id=:id";
        params.put("id", id);
        return this.baseDao.update(sql, params);
    }

    public int qryCheckCount(String name) throws Exception{
        Map<String, Object> params = new HashMap<>();
        String sql = "select count(1) from t_check_info where 1 = 1 ";
        if (name  != null && !name.equals("")) {
            params.put("name", name);
            sql += " and patient_name = :name";
        }
        return this.baseDao.getSqlCount(sql, params);
    }

    public List<Map<String,Object>> qryCheckList(String name, int start, int size) throws Exception{
        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("size", size);
        String sql = " select " +
                " id, patient_name, check_info, check_date, remark " +
                " from t_check_info where 1 = 1 ";
        if(name != null && !name.equals("")) {
            params.put("name", name);
            sql += " and patient_name = :name ";
        }
        sql += " order by check_date desc limit :start, :size ";
        return this.baseDao.queryForList(sql, params);
    }

    public String qryPatientName(String id) throws Exception{
        Map<String, Object> params = new HashMap<>();
        String sql = "select patient_name from patinfo where id=:id";
        params.put("id", id);
        return baseDao.queryForMap(sql, params).get("patient_name").toString();
    }

    public boolean addCheck(String patientName, String content, String remark) throws Exception {
        Map<String, Object> params = new HashMap<>();
        String sql = " insert into t_check_info (patient_name, check_info, check_date, remark) values (:patientName, :content, sysdate(), :remark) ";
        params.put("patientName", patientName);
        params.put("content", content);
        params.put("remark", remark);
        int addC = this.baseDao.update(sql, params);
        if(addC > 0)
            return true;
        return false;
    }

    public int delCheck(String id) throws Exception{
        Map<String, Object> params = new HashMap<>();
        String sql = " delete from t_check_info where id=:id";
        params.put("id", id);
        return this.baseDao.update(sql, params);
    }
}
