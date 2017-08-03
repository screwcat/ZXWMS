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
import com.zx.emanage.loanreview.persist.WmsCreRevInfoModelDao;
import com.zx.emanage.loanreview.service.IWmsCreRevInfoModelService;
import com.zx.emanage.loanreview.vo.WmsCreRevInfoModelSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevInfoModel;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevinfomodelService")
public class WmsCreRevInfoModelServiceImpl implements IWmsCreRevInfoModelService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevInfoModelServiceImpl.class);

    @Autowired
    private WmsCreRevInfoModelDao wmscrerevinfomodelDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreRevInfoModelSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrerevinfomodelDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreRevInfoModelSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrerevinfomodelDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrerevinfomodelDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreRevInfoModel getInfoByPK(Integer wms_cre_rev_info_model_id)
    {
        return wmscrerevinfomodelDao.get(wms_cre_rev_info_model_id);
    }

    @Override
    public WmsCreRevInfoModel getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmscrerevinfomodelDao.getInfoByFK(wms_cre_credit_head_id);
    }

    @Override
    @Transactional
    public String save(WmsCreRevInfoModel bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevinfomodelDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreRevInfoModel bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevinfomodelDao.update(bean); // update a record replace
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
     * WmsCreRevInfoModel() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreRevInfoModel> getListByEntity(WmsCreRevInfoModel queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreRevInfoModel> beanList = wmscrerevinfomodelDao.getListByEntity(queryInfo);
        return beanList;
    }
}
