package com.zx.emanage.cremanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.cremanage.persist.IWmsCreCreditLineCustomerDataAttachmentDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerDataAttachmentDao;
import com.zx.emanage.cremanage.service.IWmsCreCreditLineCustomerDataAttachmentService;
import com.zx.emanage.cremanage.vo.WmsCreCreditLineCustomerDataAttachmentSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCreCreditLineCustomerDataAttachment;
import com.zx.emanage.util.gen.vo.WmsCreCreditLineCustomerDataAttachmentVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlinecustomerdataattachmentService")
public class WmsCreCreditLineCustomerDataAttachmentServiceImpl implements
        IWmsCreCreditLineCustomerDataAttachmentService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineCustomerDataAttachmentServiceImpl.class);

    @Autowired
    private IWmsCreCreditLineCustomerDataAttachmentDao wmscrecreditlinecustomerdataattachmentDao;

    @Autowired
    private WmsCreCreditLineCustomerDataAttachmentDao wmscrecreditlinecustomerdataattachmentlistDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCustomerDataAttachmentSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (queryInfo.getWms_cre_credit_head_id() != null)
        {
            resMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        }
        // resMap.put("sortname", queryInfo.getSortname());
        // resMap.put("sortorder", queryInfo.getSortorder());
        resMap.put("Rows", wmscrecreditlinecustomerdataattachmentlistDao.search(resMap));
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCustomerDataAttachmentSearchBeanVO queryInfo)
    {
        GridDataBean bean = new GridDataBean(queryInfo.getPage(),
                                             wmscrecreditlinecustomerdataattachmentDao.getListCountNum(queryInfo),
                                             wmscrecreditlinecustomerdataattachmentDao.getListWithPaging(queryInfo));
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineCustomerDataAttachmentVO getInfoByPK(Integer wms_cre_credit_line_customer_data_attachment_id)
    {
        return wmscrecreditlinecustomerdataattachmentDao.getInfoByPK(wms_cre_credit_line_customer_data_attachment_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineCustomerDataAttachment bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinecustomerdataattachmentDao.addNewRecord(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineCustomerDataAttachment bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinecustomerdataattachmentDao.updateRecordAll(bean); // update
                                                                               // a
                                                                               // record
                                                                               // replace
                                                                               // all,
                                                                               // support
                                                                               // null
                                                                               // val
        // ret =
        // wmscrecreditlinecustomerdataattachmentDao.updateRecordCols(bean); //
        // update a record replace columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String delete(WmsCreCreditLineCustomerDataAttachment bean)
    {
        String resStr = "success";
        int ret = 0;
        try
        {
            ret = wmscrecreditlinecustomerdataattachmentDao.deleteRecordByDomain(bean); // nonsupport
                                                                                        // null
                                                                                        // val
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            resStr = "fkerror"; // default foreign key error
        }
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreCreditLineCustomerDataAttachment() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByAndMethod(WmsCreCreditLineCustomerDataAttachment queryInfo,
                                                Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getWms_cre_credit_line_customer_data_attachment_id() == null)
            {
                setAllPk = false;
            }
            if (!setAllPk)
            {
                log.info("**************please set pk into the queryInfo.**************");
                return "error";
            }
        }
        String resStr = "success";
        List<WmsCreCreditLineCustomerDataAttachment> beanList = wmscrecreditlinecustomerdataattachmentDao.getSingleTableListByAndMethod(queryInfo,
                                                                                                                                        isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "or" method, each checking, need new
     * WmsCreCreditLineCustomerDataAttachment() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByOrMethod(WmsCreCreditLineCustomerDataAttachment queryInfo, Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getWms_cre_credit_line_customer_data_attachment_id() == null)
            {
                setAllPk = false;
            }
            if (!setAllPk)
            {
                log.info("**************please set pk into the queryInfo.**************");
                return "error";
            }
        }
        String resStr = "success";
        List<WmsCreCreditLineCustomerDataAttachment> beanList = wmscrecreditlinecustomerdataattachmentDao.getSingleTableListByOrMethod(queryInfo,
                                                                                                                                       isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    @Override
    public Map<String, Object> getListWithoutPagingByIds(String wms_cre_credit_line_customer_data_attachment_ids)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        int[] iidsarr = null;
        if (wms_cre_credit_line_customer_data_attachment_ids != null)
        {
            String[] idsarr = wms_cre_credit_line_customer_data_attachment_ids.split(",");
            iidsarr = new int[idsarr.length];
            for (int i = 0; i < idsarr.length; i++)
            {
                iidsarr[i] = Integer.parseInt(idsarr[i]);
            }
        }
        resMap.put("Rows", wmscrecreditlinecustomerdataattachmentlistDao.searchByIds(iidsarr));
        return resMap;
    }
}
