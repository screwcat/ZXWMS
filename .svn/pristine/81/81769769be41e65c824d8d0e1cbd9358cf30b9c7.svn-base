package com.zx.emanage.sysmanage.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.emanage.sysmanage.persist.ISysRoleMenuFunctionDao;
import com.zx.emanage.sysmanage.vo.SysRoleMenuFunctionSearchBeanVO;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.domain.SysRoleMenuFunction;
import com.zx.emanage.util.gen.vo.SysRoleMenuFunctionVO;

/*
 * @version 2.0
 */

@Repository("sysrolemenufunctionDao")
public class SysRoleMenuFunctionDaoImpl extends AbstractSimpleDao implements ISysRoleMenuFunctionDao
{

    @Override
    public List<Map<String, Object>> getListWithoutPaging(SysRoleMenuFunctionSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.queryForListByTemplate(SqlString.AUTOSINGLE2_SYS_ROLE_MENU_FUNCTION_LIST, paramMap);
    }

    @Override
    public List<Map<String, Object>> getListWithPaging(SysRoleMenuFunctionSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.pagingForListByTemplate(SqlString.AUTOSINGLE2_SYS_ROLE_MENU_FUNCTION_LIST, queryInfo.getPagesize(),
                                            queryInfo.getPage(), paramMap);
    }

    @Override
    public int getListCountNum(SysRoleMenuFunctionSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName", "%"+queryInfo.getSearchName()+"%"); }
         */
        return this.recordNumberForListByTemplate(SqlString.AUTOSINGLE2_SYS_ROLE_MENU_FUNCTION_LIST, paramMap);
    }

    @Override
    public SysRoleMenuFunctionVO getInfoByPK(Integer id)
    {
        return SysRoleMenuFunction.getRecordVOByPK(this, id);
    }

    @Override
    public SysRoleMenuFunction getDomainByPK(Integer id)
    {
        return SysRoleMenuFunction.getRecordDomainByPK(this, id);
    }

    @Override
    public int addNewRecord(SysRoleMenuFunction bean)
    {
        return bean.insertRecord(this);
    }

    @Override
    public int updateRecordAll(SysRoleMenuFunction bean)
    {
        return bean.updateRecordAll(this);
    }

    @Override
    public int updateRecordCols(SysRoleMenuFunction bean)
    {
        return bean.updateRecordColumn(this);
    }

    @Override
    public int deleteRecordByPK(Integer id)
    {
        return SysRoleMenuFunction.deleteRecordsByPK(this, id);
    }

    @Override
    public int deleteRecordByDomain(SysRoleMenuFunction bean)
    {
        return bean.deleteRecordsByDomain(this);
    }

    @Override
    public List<SysRoleMenuFunction> getSingleTableListByAndMethod(SysRoleMenuFunction queryInfo,
                                                                   Boolean isExcludePKFlag)
    {
        return SysRoleMenuFunction.getSingleTableListByAndMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<SysRoleMenuFunction> getSingleTableListByOrMethod(SysRoleMenuFunction queryInfo, Boolean isExcludePKFlag)
    {
        return SysRoleMenuFunction.getSingleTableListByOrMethod(this, queryInfo, isExcludePKFlag);
    }
}