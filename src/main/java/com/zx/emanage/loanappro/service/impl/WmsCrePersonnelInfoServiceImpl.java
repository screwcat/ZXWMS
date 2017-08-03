package com.zx.emanage.loanappro.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.loanappro.persist.WmsCrePersonnelInfoDao;
import com.zx.emanage.loanappro.service.IWmsCrePersonnelInfoService;
import com.zx.emanage.loanappro.vo.WmsCrePersonnelInfoSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCrePersonnelInfo;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrepersonnelinfoService")
public class WmsCrePersonnelInfoServiceImpl implements IWmsCrePersonnelInfoService
{
    private static Logger log = LoggerFactory.getLogger(WmsCrePersonnelInfoServiceImpl.class);

    @Autowired
    private WmsCrePersonnelInfoDao wmscrepersonnelinfoDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCrePersonnelInfoSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrepersonnelinfoDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCrePersonnelInfoSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrepersonnelinfoDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrepersonnelinfoDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCrePersonnelInfo getInfoByPK(Integer wms_cre_personnel_info_id)
    {
        return wmscrepersonnelinfoDao.get(wms_cre_personnel_info_id);
    }

    @Override
    @Transactional
    public String save(WmsCrePersonnelInfo bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrepersonnelinfoDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCrePersonnelInfo bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrepersonnelinfoDao.update(bean); // update a record replace
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
     * WmsCrePersonnelInfo() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCrePersonnelInfo> getListByEntity(WmsCrePersonnelInfo queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCrePersonnelInfo> beanList = wmscrepersonnelinfoDao.getListByEntity(queryInfo);
        return beanList;
    }
}
