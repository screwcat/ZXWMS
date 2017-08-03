package com.zx.emanage.sysmanage.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.emanage.sysmanage.persist.ISysMenuDao;
import com.zx.emanage.sysmanage.vo.SysMenuSearchBeanVO;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.domain.SysMenu;
import com.zx.emanage.util.gen.vo.SysMenuVO;

/*
 * @version 2.0
 */

@Repository("sysmenuDao")
public class SysMenuDaoImpl extends AbstractSimpleDao implements ISysMenuDao
{

    @Override
    public List<Map<String, Object>> getListWithoutPaging(SysMenuSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.queryForListByTemplate(SqlString.AUTOSINGLE2_SYS_MENU_LIST, paramMap);
    }

    @Override
    public List<Map<String, Object>> getListWithPaging(SysMenuSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.pagingForListByTemplate(SqlString.AUTOSINGLE2_SYS_MENU_LIST, queryInfo.getPagesize(),
                                            queryInfo.getPage(), paramMap);
    }

    @Override
    public int getListCountNum(SysMenuSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName", "%"+queryInfo.getSearchName()+"%"); }
         */
        return this.recordNumberForListByTemplate(SqlString.AUTOSINGLE2_SYS_MENU_LIST, paramMap);
    }

    @Override
    public SysMenuVO getInfoByPK(Integer id)
    {
        return SysMenu.getRecordVOByPK(this, id);
    }

    @Override
    public SysMenu getDomainByPK(Integer id)
    {
        return SysMenu.getRecordDomainByPK(this, id);
    }

    @Override
    public int addNewRecord(SysMenu bean)
    {
        return bean.insertRecord(this);
    }

    @Override
    public int updateRecordAll(SysMenu bean)
    {
        return bean.updateRecordAll(this);
    }

    @Override
    public int updateRecordCols(SysMenu bean)
    {
        return bean.updateRecordColumn(this);
    }

    @Override
    public int deleteRecordByPK(Integer id)
    {
        return SysMenu.deleteRecordsByPK(this, id);
    }

    @Override
    public int deleteRecordByDomain(SysMenu bean)
    {
        return bean.deleteRecordsByDomain(this);
    }

    @Override
    public List<SysMenu> getSingleTableListByAndMethod(SysMenu queryInfo, Boolean isExcludePKFlag)
    {
        return SysMenu.getSingleTableListByAndMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<SysMenu> getSingleTableListByOrMethod(SysMenu queryInfo, Boolean isExcludePKFlag)
    {
        return SysMenu.getSingleTableListByOrMethod(this, queryInfo, isExcludePKFlag);
    }
}