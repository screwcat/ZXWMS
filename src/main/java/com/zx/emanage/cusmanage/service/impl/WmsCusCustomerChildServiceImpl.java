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
import com.zx.emanage.cusmanage.persist.WmsCusCustomerChildDao;
import com.zx.emanage.cusmanage.service.IWmsCusCustomerChildService;
import com.zx.emanage.cusmanage.vo.WmsCusCustomerChildSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCusCustomerChild;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscuscustomerchildService")
public class WmsCusCustomerChildServiceImpl implements IWmsCusCustomerChildService
{
    private static Logger log = LoggerFactory.getLogger(WmsCusCustomerChildServiceImpl.class);

    @Autowired
    private WmsCusCustomerChildDao wmscuscustomerchildDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCusCustomerChildSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        if (queryInfo.getWms_cus_customer_id() != null)
        {
            paramMap.put("wms_cus_customer_id", queryInfo.getWms_cus_customer_id());
        }
        List<Map<String, Object>> list = wmscuscustomerchildDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCusCustomerChildSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscuscustomerchildDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscuscustomerchildDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCusCustomerChild getInfoByPK(Integer wms_cus_customer_child_id)
    {
        return wmscuscustomerchildDao.get(wms_cus_customer_child_id);
    }

    @Override
    @Transactional
    public String save(WmsCusCustomerChild bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscuscustomerchildDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCusCustomerChild bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscuscustomerchildDao.update(bean); // update a record replace
                                                   // columns, nonsupport null
                                                   // val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCusCustomerChild() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCusCustomerChild> getListByEntity(WmsCusCustomerChild queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String resStr = "success";
        List<WmsCusCustomerChild> beanList = wmscuscustomerchildDao.getListByEntity(queryInfo);
        return beanList;
    }
}
