package com.zx.emanage.loancheck.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineCustomerHouseDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineCustomerHouseService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineCustomerHouseSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerHouse;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlinecustomerhouseService")
public class WmsCreCreditLineCustomerHouseServiceImpl implements IWmsCreCreditLineCustomerHouseService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineCustomerHouseServiceImpl.class);

    @Autowired
    private WmsCreCreditLineCustomerHouseDao wmscrecreditlinecustomerhouseDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCustomerHouseSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlinecustomerhouseDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCustomerHouseSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlinecustomerhouseDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlinecustomerhouseDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineCustomerHouse getInfoByPK(Integer wms_cre_credit_line_customer_hous_id)
    {
        return wmscrecreditlinecustomerhouseDao.get(wms_cre_credit_line_customer_hous_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineCustomerHouse bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinecustomerhouseDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineCustomerHouse bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinecustomerhouseDao.update(bean); // update a record
                                                             // replace columns,
                                                             // nonsupport null
                                                             // val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreCreditLineCustomerHouse() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCreditLineCustomerHouse> getListByEntity(WmsCreCreditLineCustomerHouse queryInfo,
                                                                Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLineCustomerHouse> beanList = wmscrecreditlinecustomerhouseDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public Map<String, Object> getListByFKWithoutPaging(Integer wms_cre_credit_head_id)
    {
        List<Map<String, Object>> list = wmscrecreditlinecustomerhouseDao.searchByFK(wms_cre_credit_head_id);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }
}
