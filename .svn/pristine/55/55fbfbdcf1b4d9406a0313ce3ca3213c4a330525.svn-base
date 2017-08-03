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
import com.zx.emanage.loancheck.persist.WmsCreCreditLineOtherDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineOtherService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineOtherSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineOther;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlineotherService")
public class WmsCreCreditLineOtherServiceImpl implements IWmsCreCreditLineOtherService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineOtherServiceImpl.class);

    @Autowired
    private WmsCreCreditLineOtherDao wmscrecreditlineotherDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineOtherSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlineotherDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineOtherSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlineotherDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlineotherDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineOther getInfoByPK(Integer wms_cre_credit_line_other)
    {
        return wmscrecreditlineotherDao.get(wms_cre_credit_line_other);
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineOther bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlineotherDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineOther bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlineotherDao.update(bean); // update a record replace
                                                     // columns, nonsupport null
                                                     // val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreCreditLineOther() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCreditLineOther> getListByEntity(WmsCreCreditLineOther queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLineOther> beanList = wmscrecreditlineotherDao.getListByEntity(queryInfo);
        return beanList;
    }
}
