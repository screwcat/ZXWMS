package com.zx.emanage.sysmanage.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.emanage.sysmanage.persist.ISysDeptDao;
import com.zx.emanage.sysmanage.vo.SysDeptSearchBeanVO;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.domain.SysDept;
import com.zx.emanage.util.gen.vo.SysDeptVO;

/*
 * @version 2.0
 */

@Repository("sysdeptDao")
public class SysDeptDaoImpl extends AbstractSimpleDao implements ISysDeptDao
{

    @Override
    public List<Map<String, Object>> getListWithoutPaging(SysDeptSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.queryForListByTemplate(SqlString.AUTOSINGLE2_SYS_DEPT_LIST, paramMap);
    }

    @Override
    public List<Map<String, Object>> getListWithPaging(SysDeptSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.pagingForListByTemplate(SqlString.AUTOSINGLE2_SYS_DEPT_LIST, queryInfo.getPagesize(),
                                            queryInfo.getPage(), paramMap);
    }

    @Override
    public int getListCountNum(SysDeptSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName", "%"+queryInfo.getSearchName()+"%"); }
         */
        return this.recordNumberForListByTemplate(SqlString.AUTOSINGLE2_SYS_DEPT_LIST, paramMap);
    }

    @Override
    public SysDeptVO getInfoByPK(Integer id)
    {
        return SysDept.getRecordVOByPK(this, id);
    }

    @Override
    public SysDept getDomainByPK(Integer id)
    {
        return SysDept.getRecordDomainByPK(this, id);
    }

    @Override
    public int addNewRecord(SysDept bean)
    {
        return bean.insertRecord(this);
    }

    @Override
    public int updateRecordAll(SysDept bean)
    {
        return bean.updateRecordAll(this);
    }

    @Override
    public int updateRecordCols(SysDept bean)
    {
        return bean.updateRecordColumn(this);
    }

    @Override
    public int deleteRecordByPK(Integer id)
    {
        return SysDept.deleteRecordsByPK(this, id);
    }

    @Override
    public int deleteRecordByDomain(SysDept bean)
    {
        return bean.deleteRecordsByDomain(this);
    }

    @Override
    public List<SysDept> getSingleTableListByAndMethod(SysDept queryInfo, Boolean isExcludePKFlag)
    {
        return SysDept.getSingleTableListByAndMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<SysDept> getSingleTableListByOrMethod(SysDept queryInfo, Boolean isExcludePKFlag)
    {
        return SysDept.getSingleTableListByOrMethod(this, queryInfo, isExcludePKFlag);
    }
}