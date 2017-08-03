package com.zx.emanage.cremanage.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.emanage.cremanage.persist.IWmsCreCustomerChangeLineHouseinfoDao;
import com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineHouseinfoSearchBeanVO;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.domain.WmsCreCustomerChangeLineHouseinfo;
import com.zx.emanage.util.gen.vo.WmsCreCustomerChangeLineHouseinfoVO;

/*
 * @version 2.0
 */

@Repository("wmscrecustomerchangelinehouseinfoDao")
public class WmsCreCustomerChangeLineHouseinfoDaoImpl extends AbstractSimpleDao implements
        IWmsCreCustomerChangeLineHouseinfoDao
{

    @Override
    public List<Map<String, Object>> getListWithoutPaging(WmsCreCustomerChangeLineHouseinfoSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (queryInfo.getWms_cre_credit_line_customer_change_head_id() != null)
        {
            paramMap.put("wms_cre_credit_line_customer_change_head_id",
                         queryInfo.getWms_cre_credit_line_customer_change_head_id());
        }

        paramMap.put("sortname", "wms_cre_customer_change_line_houseinfo_id");
        paramMap.put("sortorder", "asc");
        return this.queryForListByTemplate(SqlString.AUTOSINGLE2_WMS_CRE_CUSTOMER_CHANGE_LINE_HOUSEINFO_LIST, paramMap);
    }

    @Override
    public List<Map<String, Object>> getListWithPaging(WmsCreCustomerChangeLineHouseinfoSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.pagingForListByTemplate(SqlString.AUTOSINGLE2_WMS_CRE_CUSTOMER_CHANGE_LINE_HOUSEINFO_LIST,
                                            queryInfo.getPagesize(), queryInfo.getPage(), paramMap);
    }

    @Override
    public int getListCountNum(WmsCreCustomerChangeLineHouseinfoSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName", "%"+queryInfo.getSearchName()+"%"); }
         */
        return this.recordNumberForListByTemplate(SqlString.AUTOSINGLE2_WMS_CRE_CUSTOMER_CHANGE_LINE_HOUSEINFO_LIST,
                                                  paramMap);
    }

    @Override
    public WmsCreCustomerChangeLineHouseinfoVO getInfoByPK(Integer wms_cre_customer_change_line_houseinfo_id)
    {
        return WmsCreCustomerChangeLineHouseinfo.getRecordVOByPK(this, wms_cre_customer_change_line_houseinfo_id);
    }

    @Override
    public int addNewRecord(WmsCreCustomerChangeLineHouseinfo bean)
    {
        return bean.insertRecord(this);
    }

    @Override
    public int updateRecordAll(WmsCreCustomerChangeLineHouseinfo bean)
    {
        return bean.updateRecordAll(this);
    }

    @Override
    public int updateRecordCols(WmsCreCustomerChangeLineHouseinfo bean)
    {
        return bean.updateRecordColumn(this);
    }

    @Override
    public int deleteRecordByPK(Integer wms_cre_customer_change_line_houseinfo_id)
    {
        return WmsCreCustomerChangeLineHouseinfo.deleteRecordsByPK(this, wms_cre_customer_change_line_houseinfo_id);
    }

    @Override
    public int deleteRecordByDomain(WmsCreCustomerChangeLineHouseinfo bean)
    {
        return bean.deleteRecordsByDomain(this);
    }

    @Override
    public List<WmsCreCustomerChangeLineHouseinfo> getSingleTableListByAndMethod(WmsCreCustomerChangeLineHouseinfo queryInfo,
                                                                                 Boolean isExcludePKFlag)
    {
        return WmsCreCustomerChangeLineHouseinfo.getSingleTableListByAndMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<WmsCreCustomerChangeLineHouseinfo> getSingleTableListByOrMethod(WmsCreCustomerChangeLineHouseinfo queryInfo,
                                                                                Boolean isExcludePKFlag)
    {
        return WmsCreCustomerChangeLineHouseinfo.getSingleTableListByOrMethod(this, queryInfo, isExcludePKFlag);
    }
}