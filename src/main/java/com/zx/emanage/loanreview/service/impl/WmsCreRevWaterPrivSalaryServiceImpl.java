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
import com.zx.emanage.loanreview.persist.WmsCreRevWaterPrivSalaryDao;
import com.zx.emanage.loanreview.service.IWmsCreRevWaterPrivSalaryService;
import com.zx.emanage.loanreview.vo.WmsCreRevWaterPrivSalarySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevWaterPrivSalary;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevwaterprivsalaryService")
public class WmsCreRevWaterPrivSalaryServiceImpl implements IWmsCreRevWaterPrivSalaryService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevWaterPrivSalaryServiceImpl.class);

    @Autowired
    private WmsCreRevWaterPrivSalaryDao wmscrerevwaterprivsalaryDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreRevWaterPrivSalarySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrerevwaterprivsalaryDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreRevWaterPrivSalarySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrerevwaterprivsalaryDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrerevwaterprivsalaryDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreRevWaterPrivSalary getInfoByPK(Integer wms_cre_rev_water_priv_salary_id)
    {
        return wmscrerevwaterprivsalaryDao.get(wms_cre_rev_water_priv_salary_id);
    }

    @Override
    @Transactional
    public String save(WmsCreRevWaterPrivSalary bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevwaterprivsalaryDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreRevWaterPrivSalary bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevwaterprivsalaryDao.update(bean); // update a record
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
     * WmsCreRevWaterPrivSalary() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreRevWaterPrivSalary> getListByEntity(WmsCreRevWaterPrivSalary queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreRevWaterPrivSalary> beanList = wmscrerevwaterprivsalaryDao.getListByEntity(queryInfo);
        return beanList;
    }
}
