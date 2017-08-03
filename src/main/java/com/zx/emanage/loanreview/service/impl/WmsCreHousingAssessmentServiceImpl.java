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
import com.zx.emanage.loanreview.persist.WmsCreHousingAssessmentDao;
import com.zx.emanage.loanreview.service.IWmsCreHousingAssessmentService;
import com.zx.emanage.loanreview.vo.WmsCreHousingAssessmentSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreHousingAssessment;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrehousingassessmentService")
public class WmsCreHousingAssessmentServiceImpl implements IWmsCreHousingAssessmentService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreHousingAssessmentServiceImpl.class);

    @Autowired
    private WmsCreHousingAssessmentDao wmscrehousingassessmentDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreHousingAssessmentSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrehousingassessmentDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreHousingAssessmentSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrehousingassessmentDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrehousingassessmentDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreHousingAssessment getInfoByPK(Integer wms_cre_housing_assessment_id)
    {
        return wmscrehousingassessmentDao.get(wms_cre_housing_assessment_id);
    }

    @Override
    public WmsCreHousingAssessment getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmscrehousingassessmentDao.getInfoByFK(wms_cre_credit_head_id);
    }

    @Override
    @Transactional
    public String save(WmsCreHousingAssessment bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrehousingassessmentDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreHousingAssessment bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrehousingassessmentDao.update(bean); // update a record
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
     * WmsCreHousingAssessment() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreHousingAssessment> getListByEntity(WmsCreHousingAssessment queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreHousingAssessment> beanList = wmscrehousingassessmentDao.getListByEntity(queryInfo);
        return beanList;
    }
}
