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
import com.zx.emanage.loanreview.persist.WmsCreRevInfoContactDao;
import com.zx.emanage.loanreview.service.IWmsCreRevInfoContactService;
import com.zx.emanage.loanreview.vo.WmsCreRevInfoContactSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevInfoContact;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevinfocontactService")
public class WmsCreRevInfoContactServiceImpl implements IWmsCreRevInfoContactService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevInfoContactServiceImpl.class);

    @Autowired
    private WmsCreRevInfoContactDao wmscrerevinfocontactDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreRevInfoContactSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrerevinfocontactDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreRevInfoContactSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrerevinfocontactDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrerevinfocontactDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreRevInfoContact getInfoByPK(Integer wms_cre_rev_info_contact_id)
    {
        return wmscrerevinfocontactDao.get(wms_cre_rev_info_contact_id);
    }

    @Override
    @Transactional
    public String save(WmsCreRevInfoContact bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevinfocontactDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreRevInfoContact bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevinfocontactDao.update(bean); // update a record replace
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
     * WmsCreRevInfoContact() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreRevInfoContact> getListByEntity(WmsCreRevInfoContact queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreRevInfoContact> beanList = wmscrerevinfocontactDao.getListByEntity(queryInfo);
        return beanList;
    }
}
