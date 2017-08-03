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
import com.zx.emanage.loancheck.persist.WmsCreCreditLineCompanyConditionInvestorDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineCompanyConditionInvestorService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineCompanyConditionInvestorSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCompanyConditionInvestor;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlinecompanyconditioninvestorService")
public class WmsCreCreditLineCompanyConditionInvestorServiceImpl implements
        IWmsCreCreditLineCompanyConditionInvestorService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineCompanyConditionInvestorServiceImpl.class);

    @Autowired
    private WmsCreCreditLineCompanyConditionInvestorDao wmscrecreditlinecompanyconditioninvestorDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCompanyConditionInvestorSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlinecompanyconditioninvestorDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCompanyConditionInvestorSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlinecompanyconditioninvestorDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlinecompanyconditioninvestorDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineCompanyConditionInvestor getInfoByPK(Integer wms_cre_credit_line_company_condition_investor_id)
    {
        return wmscrecreditlinecompanyconditioninvestorDao.get(wms_cre_credit_line_company_condition_investor_id);
    }

    @Override
    public Map<String, Object> searchInfoByPK(Integer wms_cre_credit_line_company_condition_id)
    {
        Map<String, Object> paramMap = new HashMap<>();
        List<Map<String, Object>> list = wmscrecreditlinecompanyconditioninvestorDao.searchInfoByFK(wms_cre_credit_line_company_condition_id);
        paramMap.put("Rows", list);
        return paramMap;
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineCompanyConditionInvestor bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinecompanyconditioninvestorDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineCompanyConditionInvestor bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinecompanyconditioninvestorDao.update(bean); // update
                                                                        // a
                                                                        // record
                                                                        // replace
                                                                        // columns,
                                                                        // nonsupport
                                                                        // null
                                                                        // val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreCreditLineCompanyConditionInvestor() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCreditLineCompanyConditionInvestor> getListByEntity(WmsCreCreditLineCompanyConditionInvestor queryInfo,
                                                                           Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLineCompanyConditionInvestor> beanList = wmscrecreditlinecompanyconditioninvestorDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public Map<String, Object> getListByFKWithoutPaging(Integer wms_cre_credit_head_id)
    {
        List<Map<String, Object>> list = wmscrecreditlinecompanyconditioninvestorDao.searchByFK(wms_cre_credit_head_id);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

}
