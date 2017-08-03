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
import com.zx.emanage.loancheck.persist.WmsCreCreditLineEnteFixedAssetEquipDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineEnteFixedAssetEquipService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineEnteFixedAssetEquipSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineEnteFixedAssetEquip;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlineentefixedassetequipService")
public class WmsCreCreditLineEnteFixedAssetEquipServiceImpl implements IWmsCreCreditLineEnteFixedAssetEquipService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineEnteFixedAssetEquipServiceImpl.class);

    @Autowired
    private WmsCreCreditLineEnteFixedAssetEquipDao wmscrecreditlineentefixedassetequipDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineEnteFixedAssetEquipSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlineentefixedassetequipDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineEnteFixedAssetEquipSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlineentefixedassetequipDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlineentefixedassetequipDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineEnteFixedAssetEquip getInfoByPK(Integer wms_cre_credit_line_ente_fixed_asset_equip_id)
    {
        return wmscrecreditlineentefixedassetequipDao.get(wms_cre_credit_line_ente_fixed_asset_equip_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineEnteFixedAssetEquip bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlineentefixedassetequipDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineEnteFixedAssetEquip bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlineentefixedassetequipDao.update(bean); // update a
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
     * WmsCreCreditLineEnteFixedAssetEquip() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCreditLineEnteFixedAssetEquip> getListByEntity(WmsCreCreditLineEnteFixedAssetEquip queryInfo,
                                                                      Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLineEnteFixedAssetEquip> beanList = wmscrecreditlineentefixedassetequipDao.getListByEntity(queryInfo);
        return beanList;
    }
}
