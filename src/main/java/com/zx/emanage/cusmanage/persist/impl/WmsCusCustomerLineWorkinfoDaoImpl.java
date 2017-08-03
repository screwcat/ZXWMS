package com.zx.emanage.cusmanage.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.emanage.cusmanage.persist.IWmsCusCustomerLineWorkinfoDao;
import com.zx.emanage.cusmanage.vo.WmsCusCustomerLineWorkinfoSearchBeanVO;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.domain.WmsCusCustomerLineWorkinfo;
import com.zx.emanage.util.gen.vo.WmsCusCustomerLineWorkinfoVO;

/*
 * @version 2.0
 */

@Repository("wmscuscustomerlineworkinfoDao")
public class WmsCusCustomerLineWorkinfoDaoImpl extends AbstractSimpleDao implements IWmsCusCustomerLineWorkinfoDao
{

    @Override
    public List<Map<String, Object>> getListWithoutPaging(WmsCusCustomerLineWorkinfoSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.queryForListByTemplate(SqlString.AUTOSINGLE2_WMS_CUS_CUSTOMER_LINE_WORKINFO_LIST, paramMap);
    }

    @Override
    public List<Map<String, Object>> getListWithPaging(WmsCusCustomerLineWorkinfoSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.pagingForListByTemplate(SqlString.AUTOSINGLE2_WMS_CUS_CUSTOMER_LINE_WORKINFO_LIST,
                                            queryInfo.getPagesize(), queryInfo.getPage(), paramMap);
    }

    @Override
    public int getListCountNum(WmsCusCustomerLineWorkinfoSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName", "%"+queryInfo.getSearchName()+"%"); }
         */
        return this.recordNumberForListByTemplate(SqlString.AUTOSINGLE2_WMS_CUS_CUSTOMER_LINE_WORKINFO_LIST, paramMap);
    }

    @Override
    public WmsCusCustomerLineWorkinfoVO getInfoByPK(Integer wms_cus_customer_line_workinfo_id)
    {
        return WmsCusCustomerLineWorkinfo.getRecordVOByPK(this, wms_cus_customer_line_workinfo_id);
    }

    @Override
    public int addNewRecord(WmsCusCustomerLineWorkinfo bean)
    {
        return bean.insertRecord(this);
    }

    @Override
    public int updateRecordAll(WmsCusCustomerLineWorkinfo bean)
    {
        return bean.updateRecordAll(this);
    }

    @Override
    public int updateRecordCols(WmsCusCustomerLineWorkinfo bean)
    {
        return bean.updateRecordColumn(this);
    }

    @Override
    public int deleteRecordByPK(Integer wms_cus_customer_line_workinfo_id)
    {
        return WmsCusCustomerLineWorkinfo.deleteRecordsByPK(this, wms_cus_customer_line_workinfo_id);
    }

    @Override
    public int deleteRecordByDomain(WmsCusCustomerLineWorkinfo bean)
    {
        return bean.deleteRecordsByDomain(this);
    }

    @Override
    public List<WmsCusCustomerLineWorkinfo> getSingleTableListByAndMethod(WmsCusCustomerLineWorkinfo queryInfo,
                                                                          Boolean isExcludePKFlag)
    {
        return WmsCusCustomerLineWorkinfo.getSingleTableListByAndMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<WmsCusCustomerLineWorkinfo> getSingleTableListByOrMethod(WmsCusCustomerLineWorkinfo queryInfo,
                                                                         Boolean isExcludePKFlag)
    {
        return WmsCusCustomerLineWorkinfo.getSingleTableListByOrMethod(this, queryInfo, isExcludePKFlag);
    }
}