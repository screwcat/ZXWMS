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
import com.zx.emanage.loancheck.persist.WmsCreCreditLineInventoryGoodMaterialDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineInventoryGoodMaterialService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineInventoryGoodMaterialSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineInventoryGoodMaterial;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlineinventorygoodmaterialService")
public class WmsCreCreditLineInventoryGoodMaterialServiceImpl implements IWmsCreCreditLineInventoryGoodMaterialService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineInventoryGoodMaterialServiceImpl.class);

    @Autowired
    private WmsCreCreditLineInventoryGoodMaterialDao wmscrecreditlineinventorygoodmaterialDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineInventoryGoodMaterialSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlineinventorygoodmaterialDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineInventoryGoodMaterialSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlineinventorygoodmaterialDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlineinventorygoodmaterialDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineInventoryGoodMaterial getInfoByPK(Integer wms_cre_credit_line_inventory_goods_materials_id)
    {
        return wmscrecreditlineinventorygoodmaterialDao.get(wms_cre_credit_line_inventory_goods_materials_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineInventoryGoodMaterial bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlineinventorygoodmaterialDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineInventoryGoodMaterial bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlineinventorygoodmaterialDao.update(bean); // update a
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
     * WmsCreCreditLineInventoryGoodMaterial() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCreditLineInventoryGoodMaterial> getListByEntity(WmsCreCreditLineInventoryGoodMaterial queryInfo,
                                                                        Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLineInventoryGoodMaterial> beanList = wmscrecreditlineinventorygoodmaterialDao.getListByEntity(queryInfo);
        return beanList;
    }
}
