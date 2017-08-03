package com.zx.emanage.sysmanage.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.emanage.sysmanage.persist.ISysFunctionDao;
import com.zx.emanage.sysmanage.vo.SysFunctionSearchBeanVO;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.domain.SysFunction;
import com.zx.emanage.util.gen.vo.SysFunctionVO;

/*
 * @version 2.0
 */

@Repository("sysfunctionDao")
public class SysFunctionDaoImpl extends AbstractSimpleDao implements ISysFunctionDao
{

    @Override
    public List<Map<String, Object>> getListWithoutPaging(SysFunctionSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.queryForListByTemplate(SqlString.AUTOSINGLE2_SYS_FUNCTION_LIST, paramMap);
    }

    @Override
    public List<Map<String, Object>> getListWithPaging(SysFunctionSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.pagingForListByTemplate(SqlString.AUTOSINGLE2_SYS_FUNCTION_LIST, queryInfo.getPagesize(),
                                            queryInfo.getPage(), paramMap);
    }

    @Override
    public int getListCountNum(SysFunctionSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName", "%"+queryInfo.getSearchName()+"%"); }
         */
        return this.recordNumberForListByTemplate(SqlString.AUTOSINGLE2_SYS_FUNCTION_LIST, paramMap);
    }

    @Override
    public SysFunctionVO getInfoByPK(Integer id)
    {
        return SysFunction.getRecordVOByPK(this, id);
    }

    @Override
    public SysFunction getDomainByPK(Integer id)
    {
        return SysFunction.getRecordDomainByPK(this, id);
    }

    @Override
    public int addNewRecord(SysFunction bean)
    {
        return bean.insertRecord(this);
    }

    @Override
    public int updateRecordAll(SysFunction bean)
    {
        return bean.updateRecordAll(this);
    }

    @Override
    public int updateRecordCols(SysFunction bean)
    {
        return bean.updateRecordColumn(this);
    }

    @Override
    public int deleteRecordByPK(Integer id)
    {
        return SysFunction.deleteRecordsByPK(this, id);
    }

    @Override
    public int deleteRecordByDomain(SysFunction bean)
    {
        return bean.deleteRecordsByDomain(this);
    }

    @Override
    public List<SysFunction> getSingleTableListByAndMethod(SysFunction queryInfo, Boolean isExcludePKFlag)
    {
        return SysFunction.getSingleTableListByAndMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<SysFunction> getSingleTableListByOrMethod(SysFunction queryInfo, Boolean isExcludePKFlag)
    {
        return SysFunction.getSingleTableListByOrMethod(this, queryInfo, isExcludePKFlag);
    }
}