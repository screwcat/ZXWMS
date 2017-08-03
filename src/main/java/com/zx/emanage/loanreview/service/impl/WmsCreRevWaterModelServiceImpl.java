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
import com.zx.emanage.loanreview.persist.WmsCreRevWaterModelDao;
import com.zx.emanage.loanreview.service.IWmsCreRevWaterModelService;
import com.zx.emanage.loanreview.vo.WmsCreRevWaterModelSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevWaterModel;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevwatermodelService")
public class WmsCreRevWaterModelServiceImpl implements IWmsCreRevWaterModelService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevWaterModelServiceImpl.class);

    @Autowired
    private WmsCreRevWaterModelDao wmscrerevwatermodelDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreRevWaterModelSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrerevwatermodelDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreRevWaterModelSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrerevwatermodelDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrerevwatermodelDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreRevWaterModel getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmscrerevwatermodelDao.get(wms_cre_credit_head_id);
    }

    @Override
    @Transactional
    public String save(WmsCreRevWaterModel bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevwatermodelDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreRevWaterModel bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevwatermodelDao.update(bean); // update a record replace
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
     * WmsCreRevWaterModel() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreRevWaterModel> getListByEntity(WmsCreRevWaterModel queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreRevWaterModel> beanList = wmscrerevwatermodelDao.getListByEntity(queryInfo);
        return beanList;
    }
}
