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
import com.zx.emanage.loanreview.persist.WmsCreRevInfoCarDao;
import com.zx.emanage.loanreview.service.IWmsCreRevInfoCarService;
import com.zx.emanage.loanreview.vo.WmsCreRevInfoCarSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevInfoCar;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevinfocarService")
public class WmsCreRevInfoCarServiceImpl implements IWmsCreRevInfoCarService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevInfoCarServiceImpl.class);

    @Autowired
    private WmsCreRevInfoCarDao wmscrerevinfocarDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreRevInfoCarSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrerevinfocarDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreRevInfoCarSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrerevinfocarDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrerevinfocarDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreRevInfoCar getInfoByPK(Integer wms_cre_rev_info_car_id)
    {
        return wmscrerevinfocarDao.get(wms_cre_rev_info_car_id);
    }

    @Override
    @Transactional
    public String save(WmsCreRevInfoCar bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevinfocarDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreRevInfoCar bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevinfocarDao.update(bean); // update a record replace
                                                // columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreRevInfoCar() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreRevInfoCar> getListByEntity(WmsCreRevInfoCar queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreRevInfoCar> beanList = wmscrerevinfocarDao.getListByEntity(queryInfo);
        return beanList;
    }
}
