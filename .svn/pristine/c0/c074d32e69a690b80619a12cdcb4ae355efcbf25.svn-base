package com.zx.emanage.loancheck.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineHouseRecordDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineHouseRecordService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineHouseRecordSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineHouseRecord;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlinehouserecordService")
public class WmsCreCreditLineHouseRecordServiceImpl implements IWmsCreCreditLineHouseRecordService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineHouseRecordServiceImpl.class);

    @Autowired
    private WmsCreCreditLineHouseRecordDao wmscrecreditlinehouserecordDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineHouseRecordSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlinehouserecordDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineHouseRecordSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlinehouserecordDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlinehouserecordDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineHouseRecord getInfoByPK(Integer wms_cre_credit_line_house_record_id)
    {
        return wmscrecreditlinehouserecordDao.get(wms_cre_credit_line_house_record_id);
    }

    @Override
    public WmsCreCreditLineHouseRecord getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmscrecreditlinehouserecordDao.getInfoByFK(wms_cre_credit_head_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineHouseRecord bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinehouserecordDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineHouseRecord bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinehouserecordDao.update(bean); // update a record
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
     * WmsCreCreditLineHouseRecord() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCreditLineHouseRecord> getListByEntity(WmsCreCreditLineHouseRecord queryInfo,
                                                              Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLineHouseRecord> beanList = wmscrecreditlinehouserecordDao.getListByEntity(queryInfo);
        return beanList;
    }

}
