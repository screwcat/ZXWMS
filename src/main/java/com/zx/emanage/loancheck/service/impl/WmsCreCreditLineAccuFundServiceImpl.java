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
import com.zx.emanage.loancheck.persist.WmsCreCreditLineAccuFundDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineAccuFundService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineAccuFundSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineAccuFund;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlineaccufundService")
public class WmsCreCreditLineAccuFundServiceImpl implements IWmsCreCreditLineAccuFundService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineAccuFundServiceImpl.class);

    @Autowired
    private WmsCreCreditLineAccuFundDao wmscrecreditlineaccufundDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineAccuFundSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlineaccufundDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineAccuFundSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlineaccufundDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlineaccufundDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineAccuFund getInfoByPK(Integer wms_cre_credit_line_accu_fund_id)
    {
        return wmscrecreditlineaccufundDao.get(wms_cre_credit_line_accu_fund_id);
    }

    @Override
    public Map<String, Object> searchInfoByFK(Integer wms_cre_credit_head_id)
    {
        Map<String, Object> parmMap = new HashMap<>();
        List<Map<String, Object>> list = wmscrecreditlineaccufundDao.searchInfoByFK(wms_cre_credit_head_id);
        parmMap.put("Rows", list);
        return parmMap;
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineAccuFund bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlineaccufundDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineAccuFund bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlineaccufundDao.update(bean); // update a record
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
     * WmsCreCreditLineAccuFund() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCreditLineAccuFund> getListByEntity(WmsCreCreditLineAccuFund queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLineAccuFund> beanList = wmscrecreditlineaccufundDao.getListByEntity(queryInfo);
        return beanList;
    }

}
