package com.zx.emanage.loanappro.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.loanappro.persist.WmsCreApproComDebtorDao;
import com.zx.emanage.loanappro.service.IWmsCreApproComDebtorService;
import com.zx.emanage.loanappro.vo.WmsCreApproComDebtorSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreApproComDebtor;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscreapprocomdebtorService")
public class WmsCreApproComDebtorServiceImpl implements IWmsCreApproComDebtorService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreApproComDebtorServiceImpl.class);

    @Autowired
    private WmsCreApproComDebtorDao wmscreapprocomdebtorDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreApproComDebtorSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscreapprocomdebtorDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreApproComDebtorSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscreapprocomdebtorDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscreapprocomdebtorDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreApproComDebtor getInfoByPK(Integer wms_cre_appro_com_debtor_id)
    {
        return wmscreapprocomdebtorDao.get(wms_cre_appro_com_debtor_id);
    }

    @Override
    @Transactional
    public String save(WmsCreApproComDebtor bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscreapprocomdebtorDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreApproComDebtor bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscreapprocomdebtorDao.update(bean); // update a record replace
                                                    // columns, nonsupport null
                                                    // val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreApproComDebtor() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreApproComDebtor> getListByEntity(WmsCreApproComDebtor queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreApproComDebtor> beanList = wmscreapprocomdebtorDao.getListByEntity(queryInfo);
        return beanList;
    }
}
