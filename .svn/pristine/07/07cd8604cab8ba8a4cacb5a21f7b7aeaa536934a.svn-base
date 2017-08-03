package com.zx.emanage.loanreview.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.loanreview.persist.WmsCreRevInspectionFamilyDao;
import com.zx.emanage.loanreview.service.IWmsCreRevInspectionFamilyService;
import com.zx.emanage.util.gen.entity.WmsCreRevInspectionFamily;
import com.zx.emanage.loanreview.vo.WmsCreRevInspectionFamilySearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevinspectionfamilyService")
public class WmsCreRevInspectionFamilyServiceImpl implements IWmsCreRevInspectionFamilyService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevInspectionFamilyServiceImpl.class);

    @Autowired
    private WmsCreRevInspectionFamilyDao wmscrerevinspectionfamilyDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreRevInspectionFamilySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrerevinspectionfamilyDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreRevInspectionFamilySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrerevinspectionfamilyDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrerevinspectionfamilyDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreRevInspectionFamily getInfoByPK(Integer wms_cre_rev_inspection_family_id)
    {
        return wmscrerevinspectionfamilyDao.get(wms_cre_rev_inspection_family_id);
    }

    @Override
    @Transactional
    public String save(WmsCreRevInspectionFamily bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevinspectionfamilyDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreRevInspectionFamily bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevinspectionfamilyDao.update(bean); // update a record
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
     * WmsCreRevInspectionFamily() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreRevInspectionFamily> getListByEntity(WmsCreRevInspectionFamily queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreRevInspectionFamily> beanList = wmscrerevinspectionfamilyDao.getListByEntity(queryInfo);
        return beanList;
    }
}
