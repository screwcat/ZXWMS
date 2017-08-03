package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveAttDao;
import com.zx.emanage.inve.service.IWmsInveAttService;
import com.zx.emanage.util.gen.entity.WmsInveAtt;
import com.zx.emanage.inve.vo.WmsInveAttSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinveattService")
public class WmsInveAttServiceImpl implements IWmsInveAttService
{
    private static Logger log = LoggerFactory.getLogger(WmsInveAttServiceImpl.class);

    @Autowired
    private WmsInveAttDao wmsinveattDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsInveAttSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinveattDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsInveAttSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinveattDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinveattDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsInveAtt getInfoByPK(Integer wms_inve_att_id)
    {
        return wmsinveattDao.get(wms_inve_att_id);
    }

    @Override
    @Transactional
    public String save(WmsInveAtt bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinveattDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsInveAtt bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinveattDao.update(bean); // update a record replace columns,
                                          // nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsInveAtt() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsInveAtt> getListByEntity(WmsInveAtt queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsInveAtt> beanList = wmsinveattDao.getListByEntity(queryInfo);
        return beanList;
    }
}
