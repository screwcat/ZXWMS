package com.zx.emanage.cusmanage.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.emanage.cusmanage.persist.IWmsCusCustomerLineHouseinfoDao;
import com.zx.emanage.cusmanage.vo.WmsCusCustomerLineHouseinfoSearchBeanVO;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.domain.WmsCusCustomerLineHouseinfo;
import com.zx.emanage.util.gen.vo.WmsCusCustomerLineHouseinfoVO;

/*
 * @version 2.0
 */

@Repository("wmscuscustomerlinehouseinfoDao")
public class WmsCusCustomerLineHouseinfoDaoImpl extends AbstractSimpleDao implements IWmsCusCustomerLineHouseinfoDao
{

    @Override
    public List<Map<String, Object>> getListWithoutPaging(WmsCusCustomerLineHouseinfoSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (queryInfo.getWms_cus_customer_id() != null)
        {
            paramMap.put("wms_cus_customer_id", queryInfo.getWms_cus_customer_id());
        }

        paramMap.put("sortname", "wms_cus_customer_line_houseinfo_id");
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.queryForListByTemplate(SqlString.AUTOSINGLE2_WMS_CUS_CUSTOMER_LINE_HOUSEINFO_LIST, paramMap);
    }

    @Override
    public List<Map<String, Object>> getListWithPaging(WmsCusCustomerLineHouseinfoSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.pagingForListByTemplate(SqlString.AUTOSINGLE2_WMS_CUS_CUSTOMER_LINE_HOUSEINFO_LIST,
                                            queryInfo.getPagesize(), queryInfo.getPage(), paramMap);
    }

    @Override
    public int getListCountNum(WmsCusCustomerLineHouseinfoSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName", "%"+queryInfo.getSearchName()+"%"); }
         */
        return this.recordNumberForListByTemplate(SqlString.AUTOSINGLE2_WMS_CUS_CUSTOMER_LINE_HOUSEINFO_LIST, paramMap);
    }

    @Override
    public WmsCusCustomerLineHouseinfoVO getInfoByPK(Integer wms_cus_customer_line_houseinfo_id)
    {
        return WmsCusCustomerLineHouseinfo.getRecordVOByPK(this, wms_cus_customer_line_houseinfo_id);
    }

    @Override
    public int addNewRecord(WmsCusCustomerLineHouseinfo bean)
    {
        return bean.insertRecord(this);
    }

    @Override
    public int updateRecordAll(WmsCusCustomerLineHouseinfo bean)
    {
        return bean.updateRecordAll(this);
    }

    @Override
    public int updateRecordCols(WmsCusCustomerLineHouseinfo bean)
    {
        return bean.updateRecordColumn(this);
    }

    @Override
    public int deleteRecordByPK(Integer wms_cus_customer_line_houseinfo_id)
    {
        return WmsCusCustomerLineHouseinfo.deleteRecordsByPK(this, wms_cus_customer_line_houseinfo_id);
    }

    @Override
    public int deleteRecordByDomain(WmsCusCustomerLineHouseinfo bean)
    {
        return bean.deleteRecordsByDomain(this);
    }

    @Override
    public List<WmsCusCustomerLineHouseinfo> getSingleTableListByAndMethod(WmsCusCustomerLineHouseinfo queryInfo,
                                                                           Boolean isExcludePKFlag)
    {
        return WmsCusCustomerLineHouseinfo.getSingleTableListByAndMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<WmsCusCustomerLineHouseinfo> getSingleTableListByOrMethod(WmsCusCustomerLineHouseinfo queryInfo,
                                                                          Boolean isExcludePKFlag)
    {
        return WmsCusCustomerLineHouseinfo.getSingleTableListByOrMethod(this, queryInfo, isExcludePKFlag);
    }
}