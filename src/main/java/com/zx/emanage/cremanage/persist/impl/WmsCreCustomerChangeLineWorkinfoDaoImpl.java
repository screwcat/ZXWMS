package com.zx.emanage.cremanage.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.emanage.cremanage.persist.IWmsCreCustomerChangeLineWorkinfoDao;
import com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineWorkinfoSearchBeanVO;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.domain.WmsCreCustomerChangeLineWorkinfo;
import com.zx.emanage.util.gen.vo.WmsCreCustomerChangeLineWorkinfoVO;

/*
 * @version 2.0
 */

@Repository("wmscrecustomerchangelineworkinfoDao")
public class WmsCreCustomerChangeLineWorkinfoDaoImpl extends AbstractSimpleDao implements
        IWmsCreCustomerChangeLineWorkinfoDao
{

    @Override
    public List<Map<String, Object>> getListWithoutPaging(WmsCreCustomerChangeLineWorkinfoSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 判断如果不为�?
        if (queryInfo.getWms_cre_credit_line_customer_change_head_id() != null)
        {
            paramMap.put("wms_cre_credit_line_customer_change_head_id",
                         queryInfo.getWms_cre_credit_line_customer_change_head_id());
        }

        paramMap.put("sortname", "wms_cre_customer_change_line_workinfo_id");
        paramMap.put("sortorder", "asc");
        return this.queryForListByTemplate(SqlString.AUTOSINGLE2_WMS_CRE_CUSTOMER_CHANGE_LINE_WORKINFO_LIST, paramMap);
    }

    @Override
    public List<Map<String, Object>> getListWithPaging(WmsCreCustomerChangeLineWorkinfoSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.pagingForListByTemplate(SqlString.AUTOSINGLE2_WMS_CRE_CUSTOMER_CHANGE_LINE_WORKINFO_LIST,
                                            queryInfo.getPagesize(), queryInfo.getPage(), paramMap);
    }

    @Override
    public int getListCountNum(WmsCreCustomerChangeLineWorkinfoSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName", "%"+queryInfo.getSearchName()+"%"); }
         */
        return this.recordNumberForListByTemplate(SqlString.AUTOSINGLE2_WMS_CRE_CUSTOMER_CHANGE_LINE_WORKINFO_LIST,
                                                  paramMap);
    }

    @Override
    public WmsCreCustomerChangeLineWorkinfoVO getInfoByPK(Integer wms_cre_customer_change_line_workinfo_id)
    {
        return WmsCreCustomerChangeLineWorkinfo.getRecordVOByPK(this, wms_cre_customer_change_line_workinfo_id);
    }

    @Override
    public int addNewRecord(WmsCreCustomerChangeLineWorkinfo bean)
    {
        return bean.insertRecord(this);
    }

    @Override
    public int updateRecordAll(WmsCreCustomerChangeLineWorkinfo bean)
    {
        return bean.updateRecordAll(this);
    }

    @Override
    public int updateRecordCols(WmsCreCustomerChangeLineWorkinfo bean)
    {
        return bean.updateRecordColumn(this);
    }

    @Override
    public int deleteRecordByPK(Integer wms_cre_customer_change_line_workinfo_id)
    {
        return WmsCreCustomerChangeLineWorkinfo.deleteRecordsByPK(this, wms_cre_customer_change_line_workinfo_id);
    }

    @Override
    public int deleteRecordByDomain(WmsCreCustomerChangeLineWorkinfo bean)
    {
        return bean.deleteRecordsByDomain(this);
    }

    @Override
    public List<WmsCreCustomerChangeLineWorkinfo> getSingleTableListByAndMethod(WmsCreCustomerChangeLineWorkinfo queryInfo,
                                                                                Boolean isExcludePKFlag)
    {
        return WmsCreCustomerChangeLineWorkinfo.getSingleTableListByAndMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<WmsCreCustomerChangeLineWorkinfo> getSingleTableListByOrMethod(WmsCreCustomerChangeLineWorkinfo queryInfo,
                                                                               Boolean isExcludePKFlag)
    {
        return WmsCreCustomerChangeLineWorkinfo.getSingleTableListByOrMethod(this, queryInfo, isExcludePKFlag);
    }
}