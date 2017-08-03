package com.zx.emanage.cremanage.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.emanage.cremanage.persist.IWmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.vo.WmsCreCreditLineCustomerChangeHeadSearchBeanVO;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.domain.WmsCreCreditLineCustomerChangeHead;
import com.zx.emanage.util.gen.vo.WmsCreCreditLineCustomerChangeHeadVO;

/*
 * @version 2.0
 */

@Repository("wmscrecreditlinecustomerchangeheadDao")
public class WmsCreCreditLineCustomerChangeHeadDaoImpl extends AbstractSimpleDao implements
        IWmsCreCreditLineCustomerChangeHeadDao
{

    @Override
    public List<Map<String, Object>> getListWithoutPaging(WmsCreCreditLineCustomerChangeHeadSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (queryInfo.getWms_cre_credit_head_id() != null)
        {
            paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        }

        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.queryForListByTemplate(SqlString.AUTOSINGLE2_WMS_CRE_CREDIT_LINE_CUSTOMER_CHANGE_HEAD_LIST,
                                           paramMap);
    }

    @Override
    public List<Map<String, Object>> getListWithPaging(WmsCreCreditLineCustomerChangeHeadSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getWms_cre_credit_head_id())){
         * paramMap.put("wms_cre_credit_head_id",
         * SysTools.getSqlLikeParam(queryInfo.getWms_cre_credit_head_id())); }
         */

        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.pagingForListByTemplate(SqlString.AUTOSINGLE2_WMS_CRE_CREDIT_LINE_CUSTOMER_CHANGE_HEAD_LIST,
                                            queryInfo.getPagesize(), queryInfo.getPage(), paramMap);
    }

    @Override
    public int getListCountNum(WmsCreCreditLineCustomerChangeHeadSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getWms_cre_credit_head_id())){
         * paramMap
         * .put("wms_cre_credit_head_id",queryInfo.getWms_cre_credit_head_id());
         * }
         */
        return this.recordNumberForListByTemplate(SqlString.AUTOSINGLE2_WMS_CRE_CREDIT_LINE_CUSTOMER_CHANGE_HEAD_LIST,
                                                  paramMap);
    }

    @Override
    public WmsCreCreditLineCustomerChangeHeadVO getInfoByPK(Integer wms_cre_credit_line_customer_change_head_id)
    {
        return WmsCreCreditLineCustomerChangeHead.getRecordVOByPK(this, wms_cre_credit_line_customer_change_head_id);
    }

    @Override
    public int addNewRecord(WmsCreCreditLineCustomerChangeHead bean)
    {
        return bean.insertRecord(this);
    }

    @Override
    public int updateRecordAll(WmsCreCreditLineCustomerChangeHead bean)
    {
        return bean.updateRecordAll(this);
    }

    @Override
    public int updateRecordCols(WmsCreCreditLineCustomerChangeHead bean)
    {
        return bean.updateRecordColumn(this);
    }

    @Override
    public int deleteRecordByPK(Integer wms_cre_credit_line_customer_change_head_id)
    {
        return WmsCreCreditLineCustomerChangeHead.deleteRecordsByPK(this, wms_cre_credit_line_customer_change_head_id);
    }

    @Override
    public int deleteRecordByDomain(WmsCreCreditLineCustomerChangeHead bean)
    {
        return bean.deleteRecordsByDomain(this);
    }

    @Override
    public List<WmsCreCreditLineCustomerChangeHead> getSingleTableListByAndMethod(WmsCreCreditLineCustomerChangeHead queryInfo,
                                                                                  Boolean isExcludePKFlag)
    {
        return WmsCreCreditLineCustomerChangeHead.getSingleTableListByAndMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<WmsCreCreditLineCustomerChangeHead> getSingleTableListByOrMethod(WmsCreCreditLineCustomerChangeHead queryInfo,
                                                                                 Boolean isExcludePKFlag)
    {
        return WmsCreCreditLineCustomerChangeHead.getSingleTableListByOrMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public Integer saveWithKey(WmsCreCreditLineCustomerChangeHead wmscrecreditlinecustomerchangehead)
    {
        return this.insert(wmscrecreditlinecustomerchangehead, "wms_cre_credit_line_customer_change_head",
                           "wms_cre_credit_line_customer_change_head_id");
    }
}