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
import com.zx.emanage.loancheck.persist.WmsCreCreditLineCallListDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineCallListService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineCallListSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCallList;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlinecalllistService")
public class WmsCreCreditLineCallListServiceImpl implements IWmsCreCreditLineCallListService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineCallListServiceImpl.class);

    @Autowired
    private WmsCreCreditLineCallListDao wmscrecreditlinecalllistDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCallListSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlinecalllistDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCallListSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlinecalllistDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlinecalllistDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineCallList getInfoByPK(Integer wms_cre_credit_line_call_list_id)
    {
        return wmscrecreditlinecalllistDao.get(wms_cre_credit_line_call_list_id);
    }

    /**
     * 实现通话记录信息的获取
     */
    @Override
    public Map<String, Object> searchInfoByFK(Integer wms_cre_credit_head_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<Map<String, Object>> list = wmscrecreditlinecalllistDao.searchInfoByFK(wms_cre_credit_head_id);
        paramMap.put("Rows", list);
        return paramMap;
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineCallList bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinecalllistDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineCallList bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinecalllistDao.update(bean); // update a record
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
     * WmsCreCreditLineCallList() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCreditLineCallList> getListByEntity(WmsCreCreditLineCallList queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLineCallList> beanList = wmscrecreditlinecalllistDao.getListByEntity(queryInfo);
        return beanList;
    }

}
