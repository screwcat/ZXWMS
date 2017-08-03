package com.zx.emanage.cremanage.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.emanage.cremanage.persist.IWmsCreCustomerChangeLineContactDao;
import com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineContactSearchBeanVO;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.domain.WmsCreCustomerChangeLineContact;
import com.zx.emanage.util.gen.vo.WmsCreCustomerChangeLineContactVO;

/*
 * @version 2.0
 */

@Repository("wmscrecustomerchangelinecontactDao")
public class WmsCreCustomerChangeLineContactDaoImpl extends AbstractSimpleDao implements
        IWmsCreCustomerChangeLineContactDao
{

    @Override
    public List<Map<String, Object>> getListWithoutPaging(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (queryInfo.getWms_cre_credit_line_customer_change_head_id() != null)
        {
            paramMap.put("wms_cre_credit_line_customer_change_head_id",
                         queryInfo.getWms_cre_credit_line_customer_change_head_id());
        }

        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.queryForListByTemplate(SqlString.AUTOSINGLE2_WMS_CRE_CUSTOMER_CHANGE_LINE_CONTACT_LIST, paramMap);
    }

    @Override
    public List<Map<String, Object>> getListWithPaging(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.pagingForListByTemplate(SqlString.AUTOSINGLE2_WMS_CRE_CUSTOMER_CHANGE_LINE_CONTACT_LIST,
                                            queryInfo.getPagesize(), queryInfo.getPage(), paramMap);
    }

    @Override
    public int getListCountNum(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName", "%"+queryInfo.getSearchName()+"%"); }
         */
        return this.recordNumberForListByTemplate(SqlString.AUTOSINGLE2_WMS_CRE_CUSTOMER_CHANGE_LINE_CONTACT_LIST,
                                                  paramMap);
    }

    @Override
    public WmsCreCustomerChangeLineContactVO getInfoByPK(Integer wms_cre_customer_change_line_contact_id)
    {
        return WmsCreCustomerChangeLineContact.getRecordVOByPK(this, wms_cre_customer_change_line_contact_id);
    }

    @Override
    public int addNewRecord(WmsCreCustomerChangeLineContact bean)
    {
        return bean.insertRecord(this);
    }

    @Override
    public int updateRecordAll(WmsCreCustomerChangeLineContact bean)
    {
        return bean.updateRecordAll(this);
    }

    @Override
    public int updateRecordCols(WmsCreCustomerChangeLineContact bean)
    {
        return bean.updateRecordColumn(this);
    }

    @Override
    public int deleteRecordByPK(Integer wms_cre_customer_change_line_contact_id)
    {
        return WmsCreCustomerChangeLineContact.deleteRecordsByPK(this, wms_cre_customer_change_line_contact_id);
    }

    @Override
    public int deleteRecordByDomain(WmsCreCustomerChangeLineContact bean)
    {
        return bean.deleteRecordsByDomain(this);
    }

    @Override
    public List<WmsCreCustomerChangeLineContact> getSingleTableListByAndMethod(WmsCreCustomerChangeLineContact queryInfo,
                                                                               Boolean isExcludePKFlag)
    {
        return WmsCreCustomerChangeLineContact.getSingleTableListByAndMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<WmsCreCustomerChangeLineContact> getSingleTableListByOrMethod(WmsCreCustomerChangeLineContact queryInfo,
                                                                              Boolean isExcludePKFlag)
    {
        return WmsCreCustomerChangeLineContact.getSingleTableListByOrMethod(this, queryInfo, isExcludePKFlag);
    }
}