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
import com.zx.emanage.loanreview.persist.WmsCreRevPhoneContactDao;
import com.zx.emanage.loanreview.service.IWmsCreRevPhoneContactService;
import com.zx.emanage.loanreview.vo.WmsCreRevPhoneContactSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevPhoneContact;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevphonecontactService")
public class WmsCreRevPhoneContactServiceImpl implements IWmsCreRevPhoneContactService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevPhoneContactServiceImpl.class);

    @Autowired
    private WmsCreRevPhoneContactDao wmscrerevphonecontactDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreRevPhoneContactSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrerevphonecontactDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreRevPhoneContactSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrerevphonecontactDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrerevphonecontactDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreRevPhoneContact getInfoByPK(Integer wms_cre_rev_phone_contact_id)
    {
        return wmscrerevphonecontactDao.get(wms_cre_rev_phone_contact_id);
    }

    @Override
    @Transactional
    public String save(WmsCreRevPhoneContact bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevphonecontactDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreRevPhoneContact bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevphonecontactDao.update(bean); // update a record replace
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
     * WmsCreRevPhoneContact() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    @Override
    public List<WmsCreRevPhoneContact> getListByEntity(WmsCreRevPhoneContact queryInfo)
    {
        List<WmsCreRevPhoneContact> beanList = wmscrerevphonecontactDao.getListByEntity(queryInfo);
        return beanList;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreRevPhoneContact() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    @Override
    public List<WmsCreRevPhoneContact> getList(WmsCreRevPhoneContact queryInfo)
    {
        List<WmsCreRevPhoneContact> beanList = wmscrerevphonecontactDao.getList(queryInfo);
        return beanList;
    }

}
