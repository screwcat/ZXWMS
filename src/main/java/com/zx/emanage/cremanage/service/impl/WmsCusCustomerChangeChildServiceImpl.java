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
import com.zx.emanage.cremanage.persist.WmsCusCustomerChangeChildDao;
import com.zx.emanage.cremanage.service.IWmsCusCustomerChangeChildService;
import com.zx.emanage.cremanage.vo.WmsCusCustomerChangeChildSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCusCustomerChangeChild;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscuscustomerchangechildService")
public class WmsCusCustomerChangeChildServiceImpl implements IWmsCusCustomerChangeChildService
{
    private static Logger log = LoggerFactory.getLogger(WmsCusCustomerChangeChildServiceImpl.class);

    @Autowired
    private WmsCusCustomerChangeChildDao wmscuscustomerchangechildDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCusCustomerChangeChildSearchBeanVO queryInfo)
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
        List<Map<String, Object>> list = wmscuscustomerchangechildDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCusCustomerChangeChildSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscuscustomerchangechildDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscuscustomerchangechildDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCusCustomerChangeChild getInfoByPK(Integer wms_cus_customer_change_child_id)
    {
        return wmscuscustomerchangechildDao.get(wms_cus_customer_change_child_id);
    }

    @Override
    @Transactional
    public String save(WmsCusCustomerChangeChild bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscuscustomerchangechildDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCusCustomerChangeChild bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscuscustomerchangechildDao.update(bean); // update a record
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
     * WmsCusCustomerChangeChild() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCusCustomerChangeChild> getListByEntity(WmsCusCustomerChangeChild queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCusCustomerChangeChild> beanList = wmscuscustomerchangechildDao.getListByEntity(queryInfo);
        return beanList;
    }
}
