package com.zx.emanage.cremanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.cremanage.persist.WmsCreHousingCustomerHouseDao;
import com.zx.emanage.cremanage.service.IWmsCreHousingCustomerHouseService;
import com.zx.emanage.cremanage.vo.WmsCreHousingCustomerHouseSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreHousingCustomerHouse;
import com.zx.emanage.util.gen.vo.WmsCreCreditLineCustomerChangeHeadVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrehousingcustomerhouseService")
public class WmsCreHousingCustomerHouseServiceImpl implements IWmsCreHousingCustomerHouseService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreHousingCustomerHouseServiceImpl.class);

    @Autowired
    private WmsCreHousingCustomerHouseDao wmscrehousingcustomerhouseDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreHousingCustomerHouseSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrehousingcustomerhouseDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreHousingCustomerHouseSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrehousingcustomerhouseDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrehousingcustomerhouseDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreHousingCustomerHouse getInfoByPK(Integer id)
    {
        return wmscrehousingcustomerhouseDao.get(id);
    }

    @Override
    @Transactional
    public String save(WmsCreHousingCustomerHouse bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrehousingcustomerhouseDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreHousingCustomerHouse bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrehousingcustomerhouseDao.update(bean); // update a record
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
     * WmsCreHousingCustomerHouse() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreHousingCustomerHouse> getListByEntity(WmsCreHousingCustomerHouse queryInfo,
                                                             Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreHousingCustomerHouse> beanList = wmscrehousingcustomerhouseDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public Map<String, Object> getHCHInfoListWithoutPagingByMccid(WmsCreHousingCustomerHouseSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        List<Map<String, Object>> list = wmscrehousingcustomerhouseDao.searchHCHBYMccid(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

	@Override
	public Map<String, Object> getwmscrehousingcustomerhouseinfo(Integer wms_cre_credit_head_id) {
		return wmscrehousingcustomerhouseDao.getwmscrehousingcustomerhouseinfo(wms_cre_credit_head_id);
	}
}
