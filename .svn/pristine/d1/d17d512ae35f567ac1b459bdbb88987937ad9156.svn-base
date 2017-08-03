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
import com.zx.emanage.loancheck.persist.WmsCreCreditLineMortgageDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineMortgageService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineMortgageSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineMortgage;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlinemortgageService")
public class WmsCreCreditLineMortgageServiceImpl implements IWmsCreCreditLineMortgageService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineMortgageServiceImpl.class);

    @Autowired
    private WmsCreCreditLineMortgageDao wmscrecreditlinemortgageDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineMortgageSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlinemortgageDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineMortgageSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlinemortgageDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlinemortgageDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineMortgage getInfoByPK(Integer wms_cre_credit_line_mortgage_id)
    {
        return wmscrecreditlinemortgageDao.get(wms_cre_credit_line_mortgage_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineMortgage bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinemortgageDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineMortgage bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinemortgageDao.update(bean); // update a record
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
     * WmsCreCreditLineMortgage() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCreditLineMortgage> getListByEntity(WmsCreCreditLineMortgage queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLineMortgage> beanList = wmscrecreditlinemortgageDao.getListByEntity(queryInfo);
        return beanList;
    }
}
