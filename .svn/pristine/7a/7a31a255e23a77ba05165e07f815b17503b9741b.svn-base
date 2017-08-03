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
import com.zx.emanage.loanreview.persist.WmsCreRevPhoneModelDao;
import com.zx.emanage.loanreview.service.IWmsCreRevPhoneModelService;
import com.zx.emanage.loanreview.vo.WmsCreRevPhoneModelSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevPhoneModel;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevphonemodelService")
public class WmsCreRevPhoneModelServiceImpl implements IWmsCreRevPhoneModelService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevPhoneModelServiceImpl.class);

    @Autowired
    private WmsCreRevPhoneModelDao wmscrerevphonemodelDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreRevPhoneModelSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrerevphonemodelDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreRevPhoneModelSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrerevphonemodelDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrerevphonemodelDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreRevPhoneModel getInfoByPK(Integer wms_cre_rev_phone_model_id)
    {
        return wmscrerevphonemodelDao.get(wms_cre_rev_phone_model_id);
    }

    @Override
    @Transactional
    public String save(WmsCreRevPhoneModel bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevphonemodelDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreRevPhoneModel bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevphonemodelDao.update(bean); // update a record replace
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
     * WmsCreRevPhoneModel() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreRevPhoneModel> getListByEntity(WmsCreRevPhoneModel queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreRevPhoneModel> beanList = wmscrerevphonemodelDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public WmsCreRevPhoneModel getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmscrerevphonemodelDao.getbyfk(wms_cre_credit_head_id);
    }
}
