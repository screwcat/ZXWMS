package com.zx.emanage.cusmanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.cusmanage.persist.IWmsCusCustomerLineWorkinfoDao;
import com.zx.emanage.cusmanage.service.IWmsCusCustomerLineWorkinfoService;
import com.zx.emanage.cusmanage.vo.WmsCusCustomerLineWorkinfoSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCusCustomerLineWorkinfo;
import com.zx.emanage.util.gen.vo.WmsCusCustomerLineWorkinfoVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscuscustomerlineworkinfoService")
public class WmsCusCustomerLineWorkinfoServiceImpl implements IWmsCusCustomerLineWorkinfoService
{
    private static Logger log = LoggerFactory.getLogger(WmsCusCustomerLineWorkinfoServiceImpl.class);

    @Autowired
    private IWmsCusCustomerLineWorkinfoDao wmscuscustomerlineworkinfoDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCusCustomerLineWorkinfoSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", wmscuscustomerlineworkinfoDao.getListWithoutPaging(queryInfo));
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCusCustomerLineWorkinfoSearchBeanVO queryInfo)
    {
        GridDataBean bean = new GridDataBean(queryInfo.getPage(),
                                             wmscuscustomerlineworkinfoDao.getListCountNum(queryInfo),
                                             wmscuscustomerlineworkinfoDao.getListWithPaging(queryInfo));
        return bean.getGridData();
    }

    @Override
    public WmsCusCustomerLineWorkinfoVO getInfoByPK(Integer wms_cus_customer_line_workinfo_id)
    {
        return wmscuscustomerlineworkinfoDao.getInfoByPK(wms_cus_customer_line_workinfo_id);
    }

    @Override
    @Transactional
    public String save(WmsCusCustomerLineWorkinfo bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscuscustomerlineworkinfoDao.addNewRecord(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCusCustomerLineWorkinfo bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscuscustomerlineworkinfoDao.updateRecordAll(bean); // update a
                                                                   // record
                                                                   // replace
                                                                   // all,
                                                                   // support
                                                                   // null val
        // ret = wmscuscustomerlineworkinfoDao.updateRecordCols(bean); // update
        // a record replace columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String delete(WmsCusCustomerLineWorkinfo bean)
    {
        String resStr = "success";
        int ret = 0;
        try
        {
            ret = wmscuscustomerlineworkinfoDao.deleteRecordByDomain(bean); // nonsupport
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
     * WmsCusCustomerLineWorkinfo() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByAndMethod(WmsCusCustomerLineWorkinfo queryInfo, Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getWms_cus_customer_line_workinfo_id() == null)
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
        List<WmsCusCustomerLineWorkinfo> beanList = wmscuscustomerlineworkinfoDao.getSingleTableListByAndMethod(queryInfo,
                                                                                                                isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "or" method, each checking, need new
     * WmsCusCustomerLineWorkinfo() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByOrMethod(WmsCusCustomerLineWorkinfo queryInfo, Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getWms_cus_customer_line_workinfo_id() == null)
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
        List<WmsCusCustomerLineWorkinfo> beanList = wmscuscustomerlineworkinfoDao.getSingleTableListByOrMethod(queryInfo,
                                                                                                               isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }
}
