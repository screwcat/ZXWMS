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
import com.zx.emanage.loancheck.persist.WmsCreCreditLineEnteFixedAssetHouseDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineEnteFixedAssetHouseService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineEnteFixedAssetHouseSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineEnteFixedAssetHouse;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlineentefixedassethouseService")
public class WmsCreCreditLineEnteFixedAssetHouseServiceImpl implements IWmsCreCreditLineEnteFixedAssetHouseService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineEnteFixedAssetHouseServiceImpl.class);

    @Autowired
    private WmsCreCreditLineEnteFixedAssetHouseDao wmscrecreditlineentefixedassethouseDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineEnteFixedAssetHouseSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlineentefixedassethouseDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineEnteFixedAssetHouseSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlineentefixedassethouseDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlineentefixedassethouseDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineEnteFixedAssetHouse getInfoByPK(Integer wms_cre_credit_line_ente_fixed_asset_house_id)
    {
        return wmscrecreditlineentefixedassethouseDao.get(wms_cre_credit_line_ente_fixed_asset_house_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineEnteFixedAssetHouse bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlineentefixedassethouseDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineEnteFixedAssetHouse bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlineentefixedassethouseDao.update(bean); // update a
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
     * WmsCreCreditLineEnteFixedAssetHouse() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCreditLineEnteFixedAssetHouse> getListByEntity(WmsCreCreditLineEnteFixedAssetHouse queryInfo,
                                                                      Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLineEnteFixedAssetHouse> beanList = wmscrecreditlineentefixedassethouseDao.getListByEntity(queryInfo);
        return beanList;
    }
}
