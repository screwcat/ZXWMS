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
import com.zx.emanage.loanreview.persist.WmsCreRevWaterPrivPassbookDao;
import com.zx.emanage.loanreview.service.IWmsCreRevWaterPrivPassbookService;
import com.zx.emanage.loanreview.vo.WmsCreRevWaterPrivPassbookSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevWaterPrivPassbook;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevwaterprivpassbookService")
public class WmsCreRevWaterPrivPassbookServiceImpl implements IWmsCreRevWaterPrivPassbookService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevWaterPrivPassbookServiceImpl.class);

    @Autowired
    private WmsCreRevWaterPrivPassbookDao wmscrerevwaterprivpassbookDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreRevWaterPrivPassbookSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrerevwaterprivpassbookDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreRevWaterPrivPassbookSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrerevwaterprivpassbookDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrerevwaterprivpassbookDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreRevWaterPrivPassbook getInfoByPK(Integer wms_cre_rev_water_priv_passbook_id)
    {
        return wmscrerevwaterprivpassbookDao.get(wms_cre_rev_water_priv_passbook_id);
    }

    @Override
    @Transactional
    public String save(WmsCreRevWaterPrivPassbook bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevwaterprivpassbookDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreRevWaterPrivPassbook bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevwaterprivpassbookDao.update(bean); // update a record
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
     * WmsCreRevWaterPrivPassbook() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreRevWaterPrivPassbook> getListByEntity(WmsCreRevWaterPrivPassbook queryInfo,
                                                             Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreRevWaterPrivPassbook> beanList = wmscrerevwaterprivpassbookDao.getListByEntity(queryInfo);
        return beanList;
    }
}
