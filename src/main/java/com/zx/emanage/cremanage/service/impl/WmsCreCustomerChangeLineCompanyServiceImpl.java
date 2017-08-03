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
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineCompanyDao;
import com.zx.emanage.cremanage.service.IWmsCreCustomerChangeLineCompanyService;
import com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineCompanySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineCompany;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecustomerchangelinecompanyService")
public class WmsCreCustomerChangeLineCompanyServiceImpl implements IWmsCreCustomerChangeLineCompanyService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCustomerChangeLineCompanyServiceImpl.class);

    @Autowired
    private WmsCreCustomerChangeLineCompanyDao wmscrecustomerchangelinecompanyDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCustomerChangeLineCompanySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        if (queryInfo.getWms_cre_credit_line_customer_change_head_id() != null
            && queryInfo.getWms_cre_credit_line_customer_change_head_id() != 0)
        {
            paramMap.put("wms_cre_credit_line_customer_change_head_id",
                         queryInfo.getWms_cre_credit_line_customer_change_head_id());
        }
        List<Map<String, Object>> list = wmscrecustomerchangelinecompanyDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCustomerChangeLineCompanySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecustomerchangelinecompanyDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecustomerchangelinecompanyDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCustomerChangeLineCompany getInfoByPK(Integer wms_cre_customer_change_line_company_id)
    {
        return wmscrecustomerchangelinecompanyDao.get(wms_cre_customer_change_line_company_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCustomerChangeLineCompany bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecustomerchangelinecompanyDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCustomerChangeLineCompany bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecustomerchangelinecompanyDao.update(bean); // update a
                                                               // record replace
                                                               // columns,
                                                               // nonsupport
                                                               // null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreCustomerChangeLineCompany() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCustomerChangeLineCompany> getListByEntity(WmsCreCustomerChangeLineCompany queryInfo,
                                                                  Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCustomerChangeLineCompany> beanList = wmscrecustomerchangelinecompanyDao.getListByEntity(queryInfo);
        return beanList;
    }
}
