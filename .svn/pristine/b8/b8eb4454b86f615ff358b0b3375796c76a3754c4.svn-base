package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCustomerDao;
import com.zx.emanage.inve.service.IWmsInveCustomerService;
import com.zx.emanage.util.gen.entity.WmsInveCustomer;
import com.zx.emanage.inve.vo.WmsInveCustomerSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecustomerService")
public class WmsInveCustomerServiceImpl implements IWmsInveCustomerService
{
    private static Logger log = LoggerFactory.getLogger(WmsInveCustomerServiceImpl.class);

    @Autowired
    private WmsInveCustomerDao wmsinvecustomerDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsInveCustomerSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinvecustomerDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsInveCustomerSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvecustomerDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvecustomerDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsInveCustomer getInfoByPK(Integer wms_inve_customer_id)
    {
        return wmsinvecustomerDao.get(wms_inve_customer_id);
    }

    @Override
    @Transactional
    public String save(WmsInveCustomer bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvecustomerDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsInveCustomer bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvecustomerDao.update(bean); // update a record replace
                                               // columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsInveCustomer() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsInveCustomer> getListByEntity(WmsInveCustomer queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsInveCustomer> beanList = wmsinvecustomerDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public Map<String, Object> getWmsInveCustomerByEntity(String id_card)
    {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id_card", id_card);
        return wmsinvecustomerDao.search(paramMap).size() == 0 ? null : wmsinvecustomerDao.search(paramMap).get(0);
    }
}
