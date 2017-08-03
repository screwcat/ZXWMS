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
import com.zx.emanage.loancheck.persist.WmsCreCreditLineMediInsuDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineMediInsuService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineMediInsuSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineMediInsu;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlinemediinsuService")
public class WmsCreCreditLineMediInsuServiceImpl implements IWmsCreCreditLineMediInsuService
{
    @Autowired
    private WmsCreCreditLineMediInsuDao wmscrecreditlinemediinsuDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineMediInsuSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlinemediinsuDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineMediInsuSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlinemediinsuDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlinemediinsuDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineMediInsu getInfoByPK(Integer wms_cre_credit_line_medi_insu_id)
    {
        return wmscrecreditlinemediinsuDao.get(wms_cre_credit_line_medi_insu_id);
    }

    @Override
    public Map<String, Object> getInfoByFK(Integer wms_cre_credit_head_id)
    {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        List<Map<String, Object>> list = wmscrecreditlinemediinsuDao.getInfoByFK(parmMap);
        parmMap.put("Rows", list);
        return parmMap;
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineMediInsu bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinemediinsuDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineMediInsu bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinemediinsuDao.update(bean); // update a record
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
     * WmsCreCreditLineMediInsu() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCreditLineMediInsu> getListByEntity(WmsCreCreditLineMediInsu queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLineMediInsu> beanList = wmscrecreditlinemediinsuDao.getListByEntity(queryInfo);
        return beanList;
    }

}
