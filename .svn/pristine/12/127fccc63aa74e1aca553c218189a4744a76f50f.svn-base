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
import com.zx.emanage.loancheck.persist.WmsCreCreditLineCustomerVehicleDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineCustomerVehicleService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineCustomerVehicleSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerVehicle;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlinecustomervehicleService")
public class WmsCreCreditLineCustomerVehicleServiceImpl implements IWmsCreCreditLineCustomerVehicleService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineCustomerVehicleServiceImpl.class);

    @Autowired
    private WmsCreCreditLineCustomerVehicleDao wmscrecreditlinecustomervehicleDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCustomerVehicleSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlinecustomervehicleDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCustomerVehicleSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlinecustomervehicleDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlinecustomervehicleDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineCustomerVehicle getInfoByPK(Integer wms_cre_credit_line_customer_vehicle_id)
    {
        return wmscrecreditlinecustomervehicleDao.get(wms_cre_credit_line_customer_vehicle_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineCustomerVehicle bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinecustomervehicleDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineCustomerVehicle bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinecustomervehicleDao.update(bean); // update a
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
     * WmsCreCreditLineCustomerVehicle() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCreditLineCustomerVehicle> getListByEntity(WmsCreCreditLineCustomerVehicle queryInfo,
                                                                  Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLineCustomerVehicle> beanList = wmscrecreditlinecustomervehicleDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public Map<String, Object> getListByFKWithoutPaging(Integer wms_cre_credit_head_id)
    {
        List<Map<String, Object>> list = wmscrecreditlinecustomervehicleDao.searchByFK(wms_cre_credit_head_id);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }
}
