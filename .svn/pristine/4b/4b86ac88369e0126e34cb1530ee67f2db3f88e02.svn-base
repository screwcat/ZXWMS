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
import com.zx.emanage.cusmanage.persist.WmsCusCustomerLineCompanyDao;
import com.zx.emanage.cusmanage.service.IWmsCusCustomerLineCompanyService;
import com.zx.emanage.cusmanage.vo.WmsCusCustomerLineCompanySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCusCustomerLineCompany;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscuscustomerlinecompanyService")
public class WmsCusCustomerLineCompanyServiceImpl implements IWmsCusCustomerLineCompanyService
{
    private static Logger log = LoggerFactory.getLogger(WmsCusCustomerLineCompanyServiceImpl.class);

    @Autowired
    private WmsCusCustomerLineCompanyDao wmscuscustomerlinecompanyDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCusCustomerLineCompanySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        if (queryInfo.getWms_cus_customer_id() != null)
        {
            paramMap.put("wms_cus_customer_id", queryInfo.getWms_cus_customer_id());
        }
        List<Map<String, Object>> list = wmscuscustomerlinecompanyDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCusCustomerLineCompanySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscuscustomerlinecompanyDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscuscustomerlinecompanyDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCusCustomerLineCompany getInfoByPK(Integer wms_cus_customer_line_company_id)
    {
        return wmscuscustomerlinecompanyDao.get(wms_cus_customer_line_company_id);
    }

    @Override
    @Transactional
    public String save(WmsCusCustomerLineCompany bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscuscustomerlinecompanyDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCusCustomerLineCompany bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscuscustomerlinecompanyDao.update(bean); // update a record
                                                         // replace columns,
                                                         // nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCusCustomerLineCompany() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCusCustomerLineCompany> getListByEntity(WmsCusCustomerLineCompany queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCusCustomerLineCompany> beanList = wmscuscustomerlinecompanyDao.getListByEntity(queryInfo);
        return beanList;
    }
}
