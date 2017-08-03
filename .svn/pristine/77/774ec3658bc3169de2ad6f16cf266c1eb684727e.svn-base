package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInvePruductDeadlineDao;
import com.zx.emanage.inve.service.IWmsInvePruductDeadlineService;
import com.zx.emanage.util.gen.entity.WmsInvePruductDeadline;
import com.zx.emanage.inve.vo.WmsInvePruductDeadlineSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvepruductdeadlineService")
public class WmsInvePruductDeadlineServiceImpl implements IWmsInvePruductDeadlineService
{
    private static Logger log = LoggerFactory.getLogger(WmsInvePruductDeadlineServiceImpl.class);

    @Autowired
    private WmsInvePruductDeadlineDao wmsinvepruductdeadlineDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsInvePruductDeadlineSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinvepruductdeadlineDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsInvePruductDeadlineSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvepruductdeadlineDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvepruductdeadlineDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsInvePruductDeadline getInfoByPK(Integer wms_inve_pruduct_deadline_id)
    {
        return wmsinvepruductdeadlineDao.get(wms_inve_pruduct_deadline_id);
    }

    @Override
    @Transactional
    public String save(WmsInvePruductDeadline bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvepruductdeadlineDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsInvePruductDeadline bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvepruductdeadlineDao.update(bean); // update a record replace
                                                      // columns, nonsupport
                                                      // null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsInvePruductDeadline() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsInvePruductDeadline> getListByEntity(WmsInvePruductDeadline queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsInvePruductDeadline> beanList = wmsinvepruductdeadlineDao.getListByEntity(queryInfo);
        return beanList;
    }

    /**
     * Description :理财产品 期限表
     * 
     * @param queryInfo
     * @return Map
     * @author baisong
     */
    @Override
    public Map<String, Object> getListforlc(Integer wms_inve_pruduct_category_id)
    {
        List<Map<String, Object>> list = wmsinvepruductdeadlineDao.getListforlc(wms_inve_pruduct_category_id);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }
}
