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
import com.zx.emanage.loancheck.persist.WmsCreCreditLineCompanyConditionDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineCompanyConditionService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineCompanyConditionSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineApplicant;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCompanyCondition;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlinecompanyconditionService")
public class WmsCreCreditLineCompanyConditionServiceImpl implements IWmsCreCreditLineCompanyConditionService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineCompanyConditionServiceImpl.class);

    @Autowired
    private WmsCreCreditLineCompanyConditionDao wmscrecreditlinecompanyconditionDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCompanyConditionSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlinecompanyconditionDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCompanyConditionSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlinecompanyconditionDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlinecompanyconditionDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineCompanyCondition getInfoByPK(Integer wms_cre_credit_line_company_condition_id)
    {
        return wmscrecreditlinecompanyconditionDao.get(wms_cre_credit_line_company_condition_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineCompanyCondition bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinecompanyconditionDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineCompanyCondition bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinecompanyconditionDao.update(bean); // update a
                                                                // record
                                                                // replace
                                                                // columns,
                                                                // nonsupport
                                                                // null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    private List<WmsCreCreditLineCompanyCondition> getListByEntity(WmsCreCreditLineCompanyCondition queryInfo,
                                                                   Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLineCompanyCondition> beanList = wmscrecreditlinecompanyconditionDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public WmsCreCreditLineCompanyCondition getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmscrecreditlinecompanyconditionDao.getByFK(wms_cre_credit_head_id);
    }

    @Override
    public List<WmsCreCreditLineCompanyCondition> searchInfoByFKVIEW(Integer wms_cre_credit_head_id)
    {
        return wmscrecreditlinecompanyconditionDao.searchByFK(wms_cre_credit_head_id);
    }
}
