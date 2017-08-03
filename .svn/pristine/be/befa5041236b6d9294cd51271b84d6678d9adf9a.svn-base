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
import com.zx.emanage.loancheck.persist.WmsCreCreditLineCourtCaseStatusDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineCourtCaseStatusService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineCourtCaseStatusSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCourtCaseStatus;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlinecourtcasestatusService")
public class WmsCreCreditLineCourtCaseStatusServiceImpl implements IWmsCreCreditLineCourtCaseStatusService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineCourtCaseStatusServiceImpl.class);

    @Autowired
    private WmsCreCreditLineCourtCaseStatusDao wmscrecreditlinecourtcasestatusDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCourtCaseStatusSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlinecourtcasestatusDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCourtCaseStatusSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlinecourtcasestatusDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlinecourtcasestatusDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineCourtCaseStatus getInfoByPK(Integer wms_cre_credit_line_court_case_status_id)
    {
        return wmscrecreditlinecourtcasestatusDao.get(wms_cre_credit_line_court_case_status_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineCourtCaseStatus bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinecourtcasestatusDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineCourtCaseStatus bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinecourtcasestatusDao.update(bean); // update a
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
     * WmsCreCreditLineCourtCaseStatus() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCreditLineCourtCaseStatus> getListByEntity(WmsCreCreditLineCourtCaseStatus queryInfo,
                                                                  Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLineCourtCaseStatus> beanList = wmscrecreditlinecourtcasestatusDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public Map<String, Object> getListByFK(Integer wms_cre_credit_head_id)
    {
        List<Map<String, Object>> list = wmscrecreditlinecourtcasestatusDao.searchByFK(wms_cre_credit_head_id);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }
}
