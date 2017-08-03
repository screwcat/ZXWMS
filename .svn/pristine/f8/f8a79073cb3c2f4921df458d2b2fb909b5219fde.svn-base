package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveTransaProdRewardDao;
import com.zx.emanage.inve.service.IWmsInveTransaProdRewardService;
import com.zx.emanage.util.gen.entity.WmsInveTransaProdReward;
import com.zx.emanage.inve.vo.WmsInveTransaProdRewardSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvetransaprodrewardService")
public class WmsInveTransaProdRewardServiceImpl implements IWmsInveTransaProdRewardService
{
    private static Logger log = LoggerFactory.getLogger(WmsInveTransaProdRewardServiceImpl.class);

    @Autowired
    private WmsInveTransaProdRewardDao wmsinvetransaprodrewardDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsInveTransaProdRewardSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinvetransaprodrewardDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsInveTransaProdRewardSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransaprodrewardDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransaprodrewardDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsInveTransaProdReward getInfoByPK(Integer wms_inve_transa_prod_reward_id)
    {
        return wmsinvetransaprodrewardDao.get(wms_inve_transa_prod_reward_id);
    }

    @Override
    @Transactional
    public String save(WmsInveTransaProdReward bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvetransaprodrewardDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsInveTransaProdReward bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvetransaprodrewardDao.update(bean); // update a record
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
     * WmsInveTransaProdReward() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsInveTransaProdReward> getListByEntity(WmsInveTransaProdReward queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsInveTransaProdReward> beanList = wmsinvetransaprodrewardDao.getListByEntity(queryInfo);
        return beanList;
    }
}
