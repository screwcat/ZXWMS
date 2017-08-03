package com.zx.emanage.inve.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInvePosDao;
import com.zx.emanage.inve.service.IWmsInvePosService;
import com.zx.emanage.util.gen.entity.WmsInvePos;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.inve.vo.WmsInvePosSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinveposService")
public class WmsInvePosServiceImpl implements IWmsInvePosService
{
    private static Logger log = LoggerFactory.getLogger(WmsInvePosServiceImpl.class);

    @Autowired
    private WmsInvePosDao wmsinveposDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsInvePosSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getEnable_flag() != null)
        {
            paramMap.put("enable_flag", queryInfo.getEnable_flag());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinveposDao.search(paramMap);
        Map<String,Object> entity = new HashMap<String, Object>();
        entity.put("wms_inve_pos_id", "-1");
        entity.put("pos_code","请选择");
        list.add(0, entity);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    /**
     * 理财POS机查询（分页）
     */
    public Map<String, Object> getListWithPaging(WmsInvePosSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getEnable_flag() != null) {
            paramMap.put("enable_flag", queryInfo.getEnable_flag());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinveposDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinveposDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsInvePos getInfoByPK(Integer wms_inve_pos_id)
    {
        return wmsinveposDao.get(wms_inve_pos_id);
    }

    @Override
    @Transactional
    public String save(WmsInvePos bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinveposDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsInvePos bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinveposDao.update(bean); // update a record replace columns,
                                          // nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsInvePos() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsInvePos> getListByEntity(WmsInvePos queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsInvePos> beanList = wmsinveposDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public List<WmsInvePos> getAllInvePosInfo()
    {
        List<WmsInvePos> list = wmsinveposDao.getAllInvePosInfo();
        WmsInvePos entity = new WmsInvePos();
        entity.setWms_inve_pos_id(-1);
        entity.setPos_code("请选择");
        list.add(0, entity);
        return list;
    }

    @Override
    @Transactional
    public String doChangePos(String bean, UserBean user)
    {
        String resStr = "success";
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
        List<WmsInvePos> list = JsonUtil.jsonArrayToList(bean, WmsInvePos.class);
        try
        {
            for (WmsInvePos wipos : list)
            {
                if (wipos.getWms_inve_pos_id() != null)
                {
                    wipos.setLast_update_user_id(user.getUserId());
                    wipos.setLast_update_timestamp(sysTime);
                    wipos.setCreate_timestamp(null);
                    wipos.setCreate_user_id(null);
                    wipos.setCreate_user_name(null);
                    wmsinveposDao.update(wipos);
                }
                else
                {
                    wipos.setCreate_timestamp(sysTime);
                    wipos.setCreate_user_id(user.getUserId());
                    wipos.setCreate_user_name(user.getRealname());
                    wipos.setLast_update_user_id(null);
                    wipos.setLast_update_timestamp(null);
                    wmsinveposDao.save(wipos);
                }
            }
        }
        catch (Exception e)
        {
            resStr = "error";
        }
        return resStr;
    }
}
