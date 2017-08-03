package com.zx.emanage.sysmanage.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.emanage.sysmanage.persist.IWmsSysDictDataDao;
import com.zx.emanage.sysmanage.vo.WmsSysDictDataSearchBeanVO;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.domain.WmsSysDictData;
import com.zx.emanage.util.gen.vo.WmsSysDictDataVO;

/*
 * @version 2.0
 */

@Repository("wmssysdictdataDao")
public class WmsSysDictDataDaoImpl extends AbstractSimpleDao implements IWmsSysDictDataDao
{

    @Override
    public List<Map<String, Object>> getListWithoutPaging(WmsSysDictDataSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.queryForListByTemplate(SqlString.AUTOSINGLE2_WMS_SYS_DICT_DATA_LIST, paramMap);
    }

    @Override
    public List<Map<String, Object>> getListWithPaging(WmsSysDictDataSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.pagingForListByTemplate(SqlString.AUTOSINGLE2_WMS_SYS_DICT_DATA_LIST, queryInfo.getPagesize(),
                                            queryInfo.getPage(), paramMap);
    }

    @Override
    public int getListCountNum(WmsSysDictDataSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName", "%"+queryInfo.getSearchName()+"%"); }
         */
        return this.recordNumberForListByTemplate(SqlString.AUTOSINGLE2_WMS_SYS_DICT_DATA_LIST, paramMap);
    }

    @Override
    public WmsSysDictDataVO getInfoByPK(Integer wms_sys_dict_data_id)
    {
        return WmsSysDictData.getRecordVOByPK(this, wms_sys_dict_data_id);
    }

    @Override
    public int addNewRecord(WmsSysDictData bean)
    {
        return bean.insertRecord(this);
    }

    @Override
    public int updateRecordAll(WmsSysDictData bean)
    {
        return bean.updateRecordAll(this);
    }

    @Override
    public int updateRecordCols(WmsSysDictData bean)
    {
        return bean.updateRecordColumn(this);
    }

    @Override
    public int deleteRecordByPK(Integer wms_sys_dict_data_id)
    {
        return WmsSysDictData.deleteRecordsByPK(this, wms_sys_dict_data_id);
    }

    @Override
    public int deleteRecordByDomain(WmsSysDictData bean)
    {
        return bean.deleteRecordsByDomain(this);
    }

    @Override
    public List<WmsSysDictData> getSingleTableListByAndMethod(WmsSysDictData queryInfo, Boolean isExcludePKFlag)
    {
        return WmsSysDictData.getSingleTableListByAndMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<WmsSysDictData> getSingleTableListByOrMethod(WmsSysDictData queryInfo, Boolean isExcludePKFlag)
    {
        return WmsSysDictData.getSingleTableListByOrMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<WmsSysDictDataVO> getDictDataByDictId(Integer wms_sys_dict_id)
    {
        // TODO Auto-generated method stub
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_sys_dict_id", wms_sys_dict_id);
        return qryObjList(SqlString.AUTOSINGLE2_WMS_SYS_DICT_DATA_DICTDATALISTBYDICTID, paramMap,
                          WmsSysDictDataVO.class);
    }

    @Deprecated
    public List<WmsSysDictDataVO> getDictDataByDictCode(String dict_code)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("dict_code", dict_code);
        return qryObjList(SqlString.AUTOSINGLE2_WMS_SYS_DICT_DATA_DICTDATALISTBYDICTCODE, paramMap,
                          WmsSysDictDataVO.class);
    }
}