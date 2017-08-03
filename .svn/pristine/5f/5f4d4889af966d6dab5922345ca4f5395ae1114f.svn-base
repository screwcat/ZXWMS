package com.zx.emanage.sysmanage.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.emanage.sysmanage.persist.IWmsSysDictDao;
import com.zx.emanage.sysmanage.vo.WmsSysDictSearchBeanVO;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.domain.WmsSysDict;
import com.zx.emanage.util.gen.vo.WmsSysDictVO;

/*
 * @version 2.0
 */

@Repository("wmssysdictDao")
public class WmsSysDictDaoImpl extends AbstractSimpleDao implements IWmsSysDictDao
{

    @Override
    public List<Map<String, Object>> getListWithoutPaging(WmsSysDictSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.queryForListByTemplate(SqlString.AUTOSINGLE2_WMS_SYS_DICT_LIST, paramMap);
    }

    @Override
    public List<Map<String, Object>> getListWithPaging(WmsSysDictSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.pagingForListByTemplate(SqlString.AUTOSINGLE2_WMS_SYS_DICT_LIST, queryInfo.getPagesize(),
                                            queryInfo.getPage(), paramMap);
    }

    @Override
    public int getListCountNum(WmsSysDictSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName", "%"+queryInfo.getSearchName()+"%"); }
         */
        return this.recordNumberForListByTemplate(SqlString.AUTOSINGLE2_WMS_SYS_DICT_LIST, paramMap);
    }

    @Override
    public WmsSysDictVO getInfoByPK(Integer wms_sys_dict_id)
    {
        return WmsSysDict.getRecordVOByPK(this, wms_sys_dict_id);
    }

    @Override
    public int addNewRecord(WmsSysDict bean)
    {
        return bean.insertRecord(this);
    }

    @Override
    public int updateRecordAll(WmsSysDict bean)
    {
        return bean.updateRecordAll(this);
    }

    @Override
    public int updateRecordCols(WmsSysDict bean)
    {
        return bean.updateRecordColumn(this);
    }

    @Override
    public int deleteRecordByPK(Integer wms_sys_dict_id)
    {
        return WmsSysDict.deleteRecordsByPK(this, wms_sys_dict_id);
    }

    @Override
    public int deleteRecordByDomain(WmsSysDict bean)
    {
        return bean.deleteRecordsByDomain(this);
    }

    @Override
    public List<WmsSysDict> getSingleTableListByAndMethod(WmsSysDict queryInfo, Boolean isExcludePKFlag)
    {
        return WmsSysDict.getSingleTableListByAndMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<WmsSysDict> getSingleTableListByOrMethod(WmsSysDict queryInfo, Boolean isExcludePKFlag)
    {
        return WmsSysDict.getSingleTableListByOrMethod(this, queryInfo, isExcludePKFlag);
    }
}