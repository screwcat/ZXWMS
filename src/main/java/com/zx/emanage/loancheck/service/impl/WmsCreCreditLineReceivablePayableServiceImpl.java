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
import com.zx.emanage.loancheck.persist.WmsCreCreditLineReceivablePayableDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineReceivablePayableService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineReceivablePayableSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineReceivablePayable;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlinereceivablepayableService")
public class WmsCreCreditLineReceivablePayableServiceImpl implements IWmsCreCreditLineReceivablePayableService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineReceivablePayableServiceImpl.class);

    @Autowired
    private WmsCreCreditLineReceivablePayableDao wmscrecreditlinereceivablepayableDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineReceivablePayableSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlinereceivablepayableDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineReceivablePayableSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlinereceivablepayableDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlinereceivablepayableDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineReceivablePayable getInfoByPK(Integer wms_cre_credit_line_receivable_payable_id)
    {
        return wmscrecreditlinereceivablepayableDao.get(wms_cre_credit_line_receivable_payable_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineReceivablePayable bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinereceivablepayableDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineReceivablePayable bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinereceivablepayableDao.update(bean); // update a
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

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreCreditLineReceivablePayable() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCreditLineReceivablePayable> getListByEntity(WmsCreCreditLineReceivablePayable queryInfo,
                                                                    Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLineReceivablePayable> beanList = wmscrecreditlinereceivablepayableDao.getListByEntity(queryInfo);
        return beanList;
    }
}
