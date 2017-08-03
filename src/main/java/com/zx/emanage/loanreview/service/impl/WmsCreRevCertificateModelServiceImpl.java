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
import com.zx.emanage.loanreview.persist.WmsCreRevCertificateModelDao;
import com.zx.emanage.loanreview.service.IWmsCreRevCertificateModelService;
import com.zx.emanage.loanreview.vo.WmsCreRevCertificateModelSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevCertificateModel;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevcertificatemodelService")
public class WmsCreRevCertificateModelServiceImpl implements IWmsCreRevCertificateModelService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevCertificateModelServiceImpl.class);

    @Autowired
    private WmsCreRevCertificateModelDao wmscrerevcertificatemodelDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreRevCertificateModelSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrerevcertificatemodelDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreRevCertificateModelSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrerevcertificatemodelDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrerevcertificatemodelDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreRevCertificateModel getInfoByPK(Integer wms_cre_rev_certificate_model_id)
    {
        return wmscrerevcertificatemodelDao.get(wms_cre_rev_certificate_model_id);
    }

    @Override
    @Transactional
    public String save(WmsCreRevCertificateModel bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevcertificatemodelDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreRevCertificateModel bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevcertificatemodelDao.update(bean); // update a record
                                                         // replace columns,
                                                         // nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreRevCertificateModel() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreRevCertificateModel> getListByEntity(WmsCreRevCertificateModel queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreRevCertificateModel> beanList = wmscrerevcertificatemodelDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public WmsCreRevCertificateModel getInfoByFK(Integer wms_cre_credit_head_id)
    {
        // 获取征信重要数据 模型表
        WmsCreRevCertificateModel bean = wmscrerevcertificatemodelDao.getInfoByFK(wms_cre_credit_head_id);
        return bean;
    }
}
