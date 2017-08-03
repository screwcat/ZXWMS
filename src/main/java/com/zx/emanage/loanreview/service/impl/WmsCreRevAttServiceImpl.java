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
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineContactSearchBeanVO;
import com.zx.emanage.loanreview.persist.WmsCreRevAttDao;
import com.zx.emanage.loanreview.service.IWmsCreRevAttService;
import com.zx.emanage.loanreview.vo.WmsCreRevAttSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead;
import com.zx.emanage.util.gen.entity.WmsCreRevAtt;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevattService")
public class WmsCreRevAttServiceImpl implements IWmsCreRevAttService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevAttServiceImpl.class);

    @Autowired
    private WmsCreRevAttDao wmscrerevattDao;
    
    @Autowired
    private WmsCreCreditLineCustomerChangeHeadDao wmsCreCreditLineCustomerChangeHeadDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreRevAttSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrerevattDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreRevAttSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrerevattDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrerevattDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreRevAtt getInfoByPK(Integer wms_cre_rev_att_id)
    {
        return wmscrerevattDao.get(wms_cre_rev_att_id);
    }

    @Override
    @Transactional
    public String save(WmsCreRevAtt bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevattDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreRevAtt bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevattDao.update(bean); // update a record replace columns,
                                            // nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreRevAtt() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    @Override
    public List<WmsCreRevAtt> getListByEntity(WmsCreRevAtt queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        List<WmsCreRevAtt> beanList = wmscrerevattDao.getListByEntity(queryInfo);
        return beanList;
    }
}
