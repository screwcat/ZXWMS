package com.zx.emanage.sysmanage.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.emanage.sysmanage.persist.ISysUserMenuFuncDao;
import com.zx.emanage.sysmanage.vo.SysUserMenuFuncSearchBeanVO;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.domain.SysUserMenuFunc;
import com.zx.emanage.util.gen.vo.SysUserMenuFuncVO;

/*
 * @version 2.0
 */

@Repository("sysusermenufuncDao")
public class SysUserMenuFuncDaoImpl extends AbstractSimpleDao implements ISysUserMenuFuncDao
{

    @Override
    public List<Map<String, Object>> getListWithoutPaging(SysUserMenuFuncSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.queryForListByTemplate(SqlString.AUTOSINGLE2_SYS_USER_MENU_FUNC_LIST, paramMap);
    }

    @Override
    public List<Map<String, Object>> getListWithPaging(SysUserMenuFuncSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.pagingForListByTemplate(SqlString.AUTOSINGLE2_SYS_USER_MENU_FUNC_LIST, queryInfo.getPagesize(),
                                            queryInfo.getPage(), paramMap);
    }

    @Override
    public int getListCountNum(SysUserMenuFuncSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName", "%"+queryInfo.getSearchName()+"%"); }
         */
        return this.recordNumberForListByTemplate(SqlString.AUTOSINGLE2_SYS_USER_MENU_FUNC_LIST, paramMap);
    }

    @Override
    public SysUserMenuFuncVO getInfoByPK(Integer id)
    {
        return SysUserMenuFunc.getRecordVOByPK(this, id);
    }

    @Override
    public SysUserMenuFunc getDomainByPK(Integer id)
    {
        return SysUserMenuFunc.getRecordDomainByPK(this, id);
    }

    @Override
    public int addNewRecord(SysUserMenuFunc bean)
    {
        return bean.insertRecord(this);
    }

    @Override
    public int updateRecordAll(SysUserMenuFunc bean)
    {
        return bean.updateRecordAll(this);
    }

    @Override
    public int updateRecordCols(SysUserMenuFunc bean)
    {
        return bean.updateRecordColumn(this);
    }

    @Override
    public int deleteRecordByPK(Integer id)
    {
        return SysUserMenuFunc.deleteRecordsByPK(this, id);
    }

    @Override
    public int deleteRecordByDomain(SysUserMenuFunc bean)
    {
        return bean.deleteRecordsByDomain(this);
    }

    @Override
    public List<SysUserMenuFunc> getSingleTableListByAndMethod(SysUserMenuFunc queryInfo, Boolean isExcludePKFlag)
    {
        return SysUserMenuFunc.getSingleTableListByAndMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<SysUserMenuFunc> getSingleTableListByOrMethod(SysUserMenuFunc queryInfo, Boolean isExcludePKFlag)
    {
        return SysUserMenuFunc.getSingleTableListByOrMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<Map<String, Object>> getSysUserMenuCheck(Integer user_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("user_id", user_id);
        return this.queryForListByTemplate(SqlString.SYSMANAGE_SYSUSER_SYS_MENU_GET_USER_MENU_CHECK, paramMap);
    }

    @Override
    public List<Map<String, Object>> getSysUserFuncCheck(Integer user_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("user_id", user_id);
        return this.queryForListByTemplate(SqlString.SYSMANAGE_SYSUSER_SYS_FUNC_ROLE_GET_USER_FUNC_CHECK, paramMap);
    }

    @Override
    public void insertBatch(List<SysUserMenuFunc> userMenuList)
    {
        this.insertBatch(userMenuList, SqlString.SYSMANAGE_SYSUSER_SYS_USER_MENU_FUNC_INSERT);
    }
}