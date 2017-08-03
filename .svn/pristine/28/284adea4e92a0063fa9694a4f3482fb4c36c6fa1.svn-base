package com.zx.emanage.cremanage.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.emanage.cremanage.persist.IWmsCreCreditLineCustomerDataAttachmentDao;
import com.zx.emanage.cremanage.vo.WmsCreCreditLineCustomerDataAttachmentSearchBeanVO;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.domain.WmsCreCreditLineCustomerDataAttachment;
import com.zx.emanage.util.gen.vo.WmsCreCreditLineCustomerDataAttachmentVO;

/*
 * @version 2.0
 */

@Repository("wmscrecreditlinecustomerdataattachmentDao")
public class WmsCreCreditLineCustomerDataAttachmentDaoImpl extends AbstractSimpleDao implements
        IWmsCreCreditLineCustomerDataAttachmentDao
{

    @Override
    public List<Map<String, Object>> getListWithoutPaging(WmsCreCreditLineCustomerDataAttachmentSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (queryInfo.getWms_cre_credit_head_id() != null)
        {
            paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        }

        paramMap.put("sortname", "wms_cre_credit_line_customer_data_attachment_id");
        paramMap.put("sortorder", "asc");
        return this.queryForListByTemplate(SqlString.AUTOSINGLE2_WMS_CRE_CREDIT_LINE_CUSTOMER_DATA_ATTACHMENT_LIST,
                                           paramMap);
    }

    @Override
    public List<Map<String, Object>> getListWithPaging(WmsCreCreditLineCustomerDataAttachmentSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.pagingForListByTemplate(SqlString.AUTOSINGLE2_WMS_CRE_CREDIT_LINE_CUSTOMER_DATA_ATTACHMENT_LIST,
                                            queryInfo.getPagesize(), queryInfo.getPage(), paramMap);
    }

    @Override
    public int getListCountNum(WmsCreCreditLineCustomerDataAttachmentSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName", "%"+queryInfo.getSearchName()+"%"); }
         */
        return this.recordNumberForListByTemplate(SqlString.AUTOSINGLE2_WMS_CRE_CREDIT_LINE_CUSTOMER_DATA_ATTACHMENT_LIST,
                                                  paramMap);
    }

    @Override
    public WmsCreCreditLineCustomerDataAttachmentVO getInfoByPK(Integer wms_cre_credit_line_customer_data_attachment_id)
    {
        return WmsCreCreditLineCustomerDataAttachment.getRecordVOByPK(this,
                                                                      wms_cre_credit_line_customer_data_attachment_id);
    }

    @Override
    public int addNewRecord(WmsCreCreditLineCustomerDataAttachment bean)
    {
        return bean.insertRecord(this);
    }

    @Override
    public int updateRecordAll(WmsCreCreditLineCustomerDataAttachment bean)
    {
        return bean.updateRecordAll(this);
    }

    @Override
    public int updateRecordCols(WmsCreCreditLineCustomerDataAttachment bean)
    {
        return bean.updateRecordColumn(this);
    }

    @Override
    public int deleteRecordByPK(Integer wms_cre_credit_line_customer_data_attachment_id)
    {
        return WmsCreCreditLineCustomerDataAttachment.deleteRecordsByPK(this,
                                                                        wms_cre_credit_line_customer_data_attachment_id);
    }

    @Override
    public int deleteRecordByDomain(WmsCreCreditLineCustomerDataAttachment bean)
    {
        return bean.deleteRecordsByDomain(this);
    }

    @Override
    public List<WmsCreCreditLineCustomerDataAttachment> getSingleTableListByAndMethod(WmsCreCreditLineCustomerDataAttachment queryInfo,
                                                                                      Boolean isExcludePKFlag)
    {
        return WmsCreCreditLineCustomerDataAttachment.getSingleTableListByAndMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<WmsCreCreditLineCustomerDataAttachment> getSingleTableListByOrMethod(WmsCreCreditLineCustomerDataAttachment queryInfo,
                                                                                     Boolean isExcludePKFlag)
    {
        return WmsCreCreditLineCustomerDataAttachment.getSingleTableListByOrMethod(this, queryInfo, isExcludePKFlag);
    }
}