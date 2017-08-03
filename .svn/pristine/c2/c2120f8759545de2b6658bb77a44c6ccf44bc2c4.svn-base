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
import com.zx.emanage.loanreview.persist.WmsCreRevInfoCompDao;
import com.zx.emanage.loanreview.service.IWmsCreRevInfoCompService;
import com.zx.emanage.loanreview.vo.WmsCreRevInfoCompSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevInfoComp;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevinfocompService")
public class WmsCreRevInfoCompServiceImpl implements IWmsCreRevInfoCompService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevInfoCompServiceImpl.class);

    @Autowired
    private WmsCreRevInfoCompDao wmscrerevinfocompDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreRevInfoCompSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrerevinfocompDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreRevInfoCompSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrerevinfocompDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrerevinfocompDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreRevInfoComp getInfoByPK(Integer wms_cre_rev_info_comp_id)
    {
        return wmscrerevinfocompDao.get(wms_cre_rev_info_comp_id);
    }

    @Override
    @Transactional
    public String save(WmsCreRevInfoComp bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevinfocompDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreRevInfoComp bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevinfocompDao.update(bean); // update a record replace
                                                 // columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreRevInfoComp() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreRevInfoComp> getListByEntity(WmsCreRevInfoComp queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreRevInfoComp> beanList = wmscrerevinfocompDao.getListByEntity(queryInfo);
        return beanList;
    }
}
