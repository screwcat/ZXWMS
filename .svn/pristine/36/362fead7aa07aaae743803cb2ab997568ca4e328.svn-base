package com.zx.emanage.loanfina.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.loanfina.persist.WmsFinaCrePeriodRepayDao;
import com.zx.emanage.loanfina.service.IWmsFinaCrePeriodRepayService;
import com.zx.emanage.loanfina.vo.WmsFinaCrePeriodRepaySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsFinaCrePeriodRepay;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepay;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsfinacreperiodrepayService")
public class WmsFinaCrePeriodRepayServiceImpl implements IWmsFinaCrePeriodRepayService
{
    private static Logger log = LoggerFactory.getLogger(WmsFinaCrePeriodRepayServiceImpl.class);

    @Autowired
    private WmsFinaCrePeriodRepayDao wmsfinacreperiodrepayDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsFinaCrePeriodRepaySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsfinacreperiodrepayDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsFinaCrePeriodRepaySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsfinacreperiodrepayDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsfinacreperiodrepayDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsFinaCrePeriodRepay getInfoByPK(Integer wms_fina_cre_period_pay_id)
    {
        return wmsfinacreperiodrepayDao.get(wms_fina_cre_period_pay_id);
    }

    @Override
    @Transactional
    public String save(WmsFinaCrePeriodRepay bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsfinacreperiodrepayDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsFinaCrePeriodRepay bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsfinacreperiodrepayDao.update(bean); // update a record replace
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
     * WmsFinaCrePeriodRepay() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsFinaCrePeriodRepay> getListByEntity(WmsFinaCrePeriodRepay queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsFinaCrePeriodRepay> beanList = wmsfinacreperiodrepayDao.getListByEntity(queryInfo);
        return beanList;
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineCustomerInfoVO
     * @author baisong
     */
    @Override
    public WmsFinaCrePeriodRepay getInfoByFK(Integer wms_cre_credit_line_customer_info_id)
    {
        return wmsfinacreperiodrepayDao.getinfobyid(wms_cre_credit_line_customer_info_id);
    }

	@Override
	public Map<String, Object> getinfoforphone(Integer wms_cre_credit_head_id) {
		Map<String, Object> map=new HashMap<>();
		List<Map<String, Object>> list=wmsfinacreperiodrepayDao.getinfoforphone(wms_cre_credit_head_id);
		map.put("Rows", list);
		return map;
	}
}
