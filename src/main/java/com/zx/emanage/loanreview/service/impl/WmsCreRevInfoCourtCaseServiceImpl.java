package com.zx.emanage.loanreview.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.loanreview.persist.WmsCreRevInfoCourtCaseDao;
import com.zx.emanage.loanreview.service.IWmsCreRevInfoCourtCaseService;
import com.zx.emanage.loanreview.vo.WmsCreRevInfoCourtCaseSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevInfoCourtCase;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevinfocourtcaseService")
public class WmsCreRevInfoCourtCaseServiceImpl implements IWmsCreRevInfoCourtCaseService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevInfoCourtCaseServiceImpl.class);

    @Autowired
    private WmsCreRevInfoCourtCaseDao wmscrerevinfocourtcaseDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreRevInfoCourtCaseSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrerevinfocourtcaseDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreRevInfoCourtCaseSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrerevinfocourtcaseDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrerevinfocourtcaseDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreRevInfoCourtCase getInfoByPK(Integer wms_cre_rev_info_court_case_id)
    {
        return wmscrerevinfocourtcaseDao.get(wms_cre_rev_info_court_case_id);
    }

    @Override
    @Transactional
    public String save(WmsCreRevInfoCourtCase bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevinfocourtcaseDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreRevInfoCourtCase bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevinfocourtcaseDao.update(bean); // update a record replace
                                                      // columns, nonsupport
                                                      // null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreRevInfoCourtCase() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreRevInfoCourtCase> getListByEntity(WmsCreRevInfoCourtCase queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreRevInfoCourtCase> beanList = wmscrerevinfocourtcaseDao.getListByEntity(queryInfo);
        return beanList;
    }
}
