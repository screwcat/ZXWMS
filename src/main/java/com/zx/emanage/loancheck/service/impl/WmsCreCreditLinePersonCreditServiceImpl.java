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
import com.zx.emanage.loancheck.persist.WmsCreCreditLinePersonCreditDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLinePersonCreditService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLinePersonCreditSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLinePersonCredit;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlinepersoncreditService")
public class WmsCreCreditLinePersonCreditServiceImpl implements IWmsCreCreditLinePersonCreditService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLinePersonCreditServiceImpl.class);

    @Autowired
    private WmsCreCreditLinePersonCreditDao wmscrecreditlinepersoncreditDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLinePersonCreditSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlinepersoncreditDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLinePersonCreditSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlinepersoncreditDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlinepersoncreditDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLinePersonCredit getInfoByPK(Integer wms_cre_credit_line_person_credit_id)
    {
        return wmscrecreditlinepersoncreditDao.get(wms_cre_credit_line_person_credit_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLinePersonCredit bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinepersoncreditDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLinePersonCredit bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinepersoncreditDao.update(bean); // update a record
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
     * WmsCreCreditLinePersonCredit() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCreditLinePersonCredit> getListByEntity(WmsCreCreditLinePersonCredit queryInfo,
                                                               Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLinePersonCredit> beanList = wmscrecreditlinepersoncreditDao.getListByEntity(queryInfo);
        return beanList;
    }
}
