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
import com.zx.emanage.loanreview.persist.WmsCreRevInfoCriminalDao;
import com.zx.emanage.loanreview.service.IWmsCreRevInfoCriminalService;
import com.zx.emanage.loanreview.vo.WmsCreRevInfoCriminalSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevInfoCriminal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevinfocriminalService")
public class WmsCreRevInfoCriminalServiceImpl implements IWmsCreRevInfoCriminalService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevInfoCriminalServiceImpl.class);

    @Autowired
    private WmsCreRevInfoCriminalDao wmscrerevinfocriminalDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreRevInfoCriminalSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrerevinfocriminalDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreRevInfoCriminalSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrerevinfocriminalDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrerevinfocriminalDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreRevInfoCriminal getInfoByPK(Integer wms_cre_rev_info_criminal_id)
    {
        return wmscrerevinfocriminalDao.get(wms_cre_rev_info_criminal_id);
    }

    @Override
    @Transactional
    public String save(WmsCreRevInfoCriminal bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevinfocriminalDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreRevInfoCriminal bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevinfocriminalDao.update(bean); // update a record replace
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
     * WmsCreRevInfoCriminal() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreRevInfoCriminal> getListByEntity(WmsCreRevInfoCriminal queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreRevInfoCriminal> beanList = wmscrerevinfocriminalDao.getListByEntity(queryInfo);
        return beanList;
    }
}
