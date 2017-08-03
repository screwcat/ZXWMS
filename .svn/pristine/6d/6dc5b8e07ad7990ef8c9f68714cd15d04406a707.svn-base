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
import com.zx.emanage.loancheck.persist.WmsCreCreditLineEnteFixedAssetVehicleDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineEnteFixedAssetVehicleService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineEnteFixedAssetVehicleSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineEnteFixedAssetVehicle;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlineentefixedassetvehicleService")
public class WmsCreCreditLineEnteFixedAssetVehicleServiceImpl implements IWmsCreCreditLineEnteFixedAssetVehicleService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineEnteFixedAssetVehicleServiceImpl.class);

    @Autowired
    private WmsCreCreditLineEnteFixedAssetVehicleDao wmscrecreditlineentefixedassetvehicleDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineEnteFixedAssetVehicleSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlineentefixedassetvehicleDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineEnteFixedAssetVehicleSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlineentefixedassetvehicleDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlineentefixedassetvehicleDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineEnteFixedAssetVehicle getInfoByPK(Integer wms_cre_credit_line_ente_fixed_asset_vehicle_id)
    {
        return wmscrecreditlineentefixedassetvehicleDao.get(wms_cre_credit_line_ente_fixed_asset_vehicle_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineEnteFixedAssetVehicle bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlineentefixedassetvehicleDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineEnteFixedAssetVehicle bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlineentefixedassetvehicleDao.update(bean); // update a
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
     * WmsCreCreditLineEnteFixedAssetVehicle() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCreditLineEnteFixedAssetVehicle> getListByEntity(WmsCreCreditLineEnteFixedAssetVehicle queryInfo,
                                                                        Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLineEnteFixedAssetVehicle> beanList = wmscrecreditlineentefixedassetvehicleDao.getListByEntity(queryInfo);
        return beanList;
    }
}
