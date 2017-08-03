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
import com.zx.emanage.loanreview.persist.WmsCreRevWaterRepayLoanLineDao;
import com.zx.emanage.loanreview.service.IWmsCreRevWaterRepayLoanLineService;
import com.zx.emanage.loanreview.vo.WmsCreRevWaterRepayLoanLineSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevWaterRepayLoanLine;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevwaterrepayloanlineService")
public class WmsCreRevWaterRepayLoanLineServiceImpl implements IWmsCreRevWaterRepayLoanLineService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevWaterRepayLoanLineServiceImpl.class);

    @Autowired
    private WmsCreRevWaterRepayLoanLineDao wmscrerevwaterrepayloanlineDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreRevWaterRepayLoanLineSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrerevwaterrepayloanlineDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreRevWaterRepayLoanLineSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrerevwaterrepayloanlineDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrerevwaterrepayloanlineDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreRevWaterRepayLoanLine getInfoByPK(Integer wms_cre_rev_water_repay_loan_line_id)
    {
        return wmscrerevwaterrepayloanlineDao.get(wms_cre_rev_water_repay_loan_line_id);
    }

    @Override
    @Transactional
    public String save(WmsCreRevWaterRepayLoanLine bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevwaterrepayloanlineDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreRevWaterRepayLoanLine bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevwaterrepayloanlineDao.update(bean); // update a record
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
     * WmsCreRevWaterRepayLoanLine() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreRevWaterRepayLoanLine> getListByEntity(WmsCreRevWaterRepayLoanLine queryInfo,
                                                              Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreRevWaterRepayLoanLine> beanList = wmscrerevwaterrepayloanlineDao.getListByEntity(queryInfo);
        return beanList;
    }
}
