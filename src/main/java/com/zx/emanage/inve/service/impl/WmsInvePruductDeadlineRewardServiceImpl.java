package com.zx.emanage.inve.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInvePruductDeadlineDao;
import com.zx.emanage.inve.persist.WmsInvePruductDeadlineRewardDao;
import com.zx.emanage.inve.service.IWmsInvePruductDeadlineRewardService;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineOther;
import com.zx.emanage.util.gen.entity.WmsInvePruductDeadline;
import com.zx.emanage.util.gen.entity.WmsInvePruductDeadlineReward;
import com.zx.emanage.util.gen.entity.WmsInvePruductReturn;
import com.zx.emanage.inve.vo.WmsInvePruductDeadlineRewardSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvepruductdeadlinerewardService")
public class WmsInvePruductDeadlineRewardServiceImpl implements IWmsInvePruductDeadlineRewardService
{
    private static Logger log = LoggerFactory.getLogger(WmsInvePruductDeadlineRewardServiceImpl.class);

    @Autowired
    private WmsInvePruductDeadlineRewardDao wmsinvepruductdeadlinerewardDao;

    @Autowired
    private WmsInvePruductDeadlineDao wmsinvepruductdeadlineDao;// 产品期限表

    @Override
    public Map<String, Object> getListWithoutPaging(WmsInvePruductDeadlineRewardSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinvepruductdeadlinerewardDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsInvePruductDeadlineRewardSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvepruductdeadlinerewardDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvepruductdeadlinerewardDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsInvePruductDeadlineReward getInfoByPK(Integer wms_inve_pruduct_deadline_reward_id)
    {
        return wmsinvepruductdeadlinerewardDao.get(wms_inve_pruduct_deadline_reward_id);
    }

    @Override
    @Transactional
    public String save(WmsInvePruductDeadlineReward bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvepruductdeadlinerewardDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsInvePruductDeadlineReward bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvepruductdeadlinerewardDao.update(bean); // update a record
                                                            // replace columns,
                                                            // nonsupport null
                                                            // val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsInvePruductDeadlineReward() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsInvePruductDeadlineReward> getListByEntity(WmsInvePruductDeadlineReward queryInfo,
                                                               Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsInvePruductDeadlineReward> beanList = wmsinvepruductdeadlinerewardDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public List<List<Map<String, Object>>> getListVal(Integer wms_inve_pruduct_category_id)
    {
        List<Map<String, Object>> list = wmsinvepruductdeadlineDao.getListforlc(wms_inve_pruduct_category_id);
        List<List<Map<String, Object>>> bigList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++)
        {
            Map<String, Object> map = list.get(i);
            int num = (Integer.parseInt(map.get("wms_inve_pruduct_deadline_id").toString()));
            // paramMap.put("wms_inve_pruduct_deadline_id",
            // deadline.getWms_inve_pruduct_deadline_id());
            List<Map<String, Object>> listMap = wmsinvepruductdeadlinerewardDao.getListVal(num);
            bigList.add(listMap);
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        return bigList;
    }

    @Override
    public Map<String, Object> showValCheck(Integer wms_inve_pruduct_deadline_id)
    {

        List<WmsInvePruductDeadlineReward> mcclcaList = wmsinvepruductdeadlinerewardDao.showValCheck(wms_inve_pruduct_deadline_id);
        // 装载数据，返回到前台页面
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("jllv", mcclcaList);
        return resMap;
    }

    @Override
    public Map<String, Object> showValbycategory(Integer wms_inve_pruduct_deadline_id)
    {
        WmsInvePruductDeadlineReward wmsInvePruductDeadlineReward = new WmsInvePruductDeadlineReward();
        List<WmsInvePruductDeadlineReward> mcclcaList = wmsinvepruductdeadlinerewardDao.showValbycategory(wms_inve_pruduct_deadline_id);
        // 装载数据，返回到前台页面
        Map<String, Object> resMap = new HashMap<String, Object>();
        for (int i = 0; i < mcclcaList.size(); i++)
        {
            wmsInvePruductDeadlineReward = mcclcaList.get(i);
            if (wmsInvePruductDeadlineReward.getProduct_deadline_month() == 6)
            {
                resMap.put("reward_interest6", wmsInvePruductDeadlineReward.getReward_interest());
            }
            if (wmsInvePruductDeadlineReward.getProduct_deadline_month() == 12)
            {
                resMap.put("reward_interest12", wmsInvePruductDeadlineReward.getReward_interest());
            }
        }
        return resMap;
    }
}
