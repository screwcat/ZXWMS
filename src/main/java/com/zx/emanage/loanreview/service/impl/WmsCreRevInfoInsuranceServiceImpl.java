package com.zx.emanage.loanreview.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.loanreview.persist.WmsCreRevInfoInsuranceDao;
import com.zx.emanage.loanreview.service.IWmsCreRevInfoInsuranceService;
import com.zx.emanage.loanreview.vo.WmsCreRevInfoInsuranceSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevInfoInsurance;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevinfoinsuranceService")
public class WmsCreRevInfoInsuranceServiceImpl implements IWmsCreRevInfoInsuranceService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevInfoInsuranceServiceImpl.class);

    @Autowired
    private WmsCreRevInfoInsuranceDao wmscrerevinfoinsuranceDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreRevInfoInsuranceSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrerevinfoinsuranceDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreRevInfoInsuranceSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrerevinfoinsuranceDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrerevinfoinsuranceDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreRevInfoInsurance getInfoByPK(Integer wms_cre_rev_info_insurance_id)
    {
        return wmscrerevinfoinsuranceDao.get(wms_cre_rev_info_insurance_id);
    }

    @Override
    @Transactional
    public String save(WmsCreRevInfoInsurance bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevinfoinsuranceDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreRevInfoInsurance bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevinfoinsuranceDao.update(bean); // update a record replace
                                                      // columns, nonsupport
                                                      // null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreRevInfoInsurance() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreRevInfoInsurance> getListByEntity(WmsCreRevInfoInsurance queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreRevInfoInsurance> beanList = wmscrerevinfoinsuranceDao.getListByEntity(queryInfo);
        return beanList;
    }
}
