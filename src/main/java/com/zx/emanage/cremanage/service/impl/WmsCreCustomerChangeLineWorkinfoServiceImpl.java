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
import com.zx.emanage.cremanage.persist.IWmsCreCustomerChangeLineWorkinfoDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineWorkinfoDao;
import com.zx.emanage.cremanage.service.IWmsCreCustomerChangeLineWorkinfoService;
import com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineWorkinfoSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCreCustomerChangeLineWorkinfo;
import com.zx.emanage.util.gen.vo.WmsCreCustomerChangeLineWorkinfoVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecustomerchangelineworkinfoService")
public class WmsCreCustomerChangeLineWorkinfoServiceImpl implements IWmsCreCustomerChangeLineWorkinfoService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCustomerChangeLineWorkinfoServiceImpl.class);

    @Autowired
    private IWmsCreCustomerChangeLineWorkinfoDao wmscrecustomerchangelineworkinfoDao;

    @Autowired
    private WmsCreCustomerChangeLineWorkinfoDao wmscrecustomerchangelineworkinfoDao_m;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCustomerChangeLineWorkinfoSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_line_customer_change_head_id",
                     queryInfo.getWms_cre_credit_line_customer_change_head_id());
        resMap.put("Rows", wmscrecustomerchangelineworkinfoDao_m.search(paramMap));
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCustomerChangeLineWorkinfoSearchBeanVO queryInfo)
    {
        GridDataBean bean = new GridDataBean(queryInfo.getPage(),
                                             wmscrecustomerchangelineworkinfoDao.getListCountNum(queryInfo),
                                             wmscrecustomerchangelineworkinfoDao.getListWithPaging(queryInfo));
        return bean.getGridData();
    }

    @Override
    public WmsCreCustomerChangeLineWorkinfoVO getInfoByPK(Integer wms_cre_customer_change_line_workinfo_id)
    {
        return wmscrecustomerchangelineworkinfoDao.getInfoByPK(wms_cre_customer_change_line_workinfo_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCustomerChangeLineWorkinfo bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecustomerchangelineworkinfoDao.addNewRecord(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCustomerChangeLineWorkinfo bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecustomerchangelineworkinfoDao.updateRecordAll(bean); // update
                                                                         // a
                                                                         // record
                                                                         // replace
                                                                         // all,
                                                                         // support
                                                                         // null
                                                                         // val
        // ret = wmscrecustomerchangelineworkinfoDao.updateRecordCols(bean); //
        // update a record replace columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String delete(WmsCreCustomerChangeLineWorkinfo bean)
    {
        String resStr = "success";
        int ret = 0;
        try
        {
            ret = wmscrecustomerchangelineworkinfoDao.deleteRecordByDomain(bean); // nonsupport
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
     * WmsCreCustomerChangeLineWorkinfo() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByAndMethod(WmsCreCustomerChangeLineWorkinfo queryInfo, Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getWms_cre_customer_change_line_workinfo_id() == null)
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
        List<WmsCreCustomerChangeLineWorkinfo> beanList = wmscrecustomerchangelineworkinfoDao.getSingleTableListByAndMethod(queryInfo,
                                                                                                                            isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "or" method, each checking, need new
     * WmsCreCustomerChangeLineWorkinfo() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByOrMethod(WmsCreCustomerChangeLineWorkinfo queryInfo, Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getWms_cre_customer_change_line_workinfo_id() == null)
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
        List<WmsCreCustomerChangeLineWorkinfo> beanList = wmscrecustomerchangelineworkinfoDao.getSingleTableListByOrMethod(queryInfo,
                                                                                                                           isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }
}
