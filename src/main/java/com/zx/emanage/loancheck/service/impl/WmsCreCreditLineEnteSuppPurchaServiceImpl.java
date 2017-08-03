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
import com.zx.emanage.loancheck.persist.WmsCreCreditLineEnteSuppPurchaDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineEnteSuppPurchaService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineEnteSuppPurchaSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineEnteSuppPurcha;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlineentesupppurchaService")
public class WmsCreCreditLineEnteSuppPurchaServiceImpl implements IWmsCreCreditLineEnteSuppPurchaService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineEnteSuppPurchaServiceImpl.class);

    @Autowired
    private WmsCreCreditLineEnteSuppPurchaDao wmscrecreditlineentesupppurchaDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineEnteSuppPurchaSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlineentesupppurchaDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineEnteSuppPurchaSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlineentesupppurchaDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlineentesupppurchaDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineEnteSuppPurcha getInfoByPK(Integer wms_cre_credit_line_ente_fixed_asset_equip_id)
    {
        return wmscrecreditlineentesupppurchaDao.get(wms_cre_credit_line_ente_fixed_asset_equip_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineEnteSuppPurcha bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlineentesupppurchaDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineEnteSuppPurcha bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlineentesupppurchaDao.update(bean); // update a record
                                                              // replace
                                                              // columns,
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
     * WmsCreCreditLineEnteSuppPurcha() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCreditLineEnteSuppPurcha> getListByEntity(WmsCreCreditLineEnteSuppPurcha queryInfo,
                                                                 Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLineEnteSuppPurcha> beanList = wmscrecreditlineentesupppurchaDao.getListByEntity(queryInfo);
        return beanList;
    }
}
