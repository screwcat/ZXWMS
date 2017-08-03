package com.zx.emanage.cusmanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.cusmanage.persist.IWmsCusCustomerLineHouseinfoDao;
import com.zx.emanage.cusmanage.persist.WmsCusCustomerLineHouseinfoDao;
import com.zx.emanage.cusmanage.service.IWmsCusCustomerLineHouseinfoService;
import com.zx.emanage.cusmanage.vo.WmsCusCustomerLineHouseinfoSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCusCustomerLineHouseinfo;
import com.zx.emanage.util.gen.vo.WmsCusCustomerLineHouseinfoVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscuscustomerlinehouseinfoService")
public class WmsCusCustomerLineHouseinfoServiceImpl implements IWmsCusCustomerLineHouseinfoService
{
    private static Logger log = LoggerFactory.getLogger(WmsCusCustomerLineHouseinfoServiceImpl.class);

    @Autowired
    private IWmsCusCustomerLineHouseinfoDao wmscuscustomerlinehouseinfoDao;

    @Autowired
    private WmsCusCustomerLineHouseinfoDao wmscuscustomerlinehouseinfoDao2;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCusCustomerLineHouseinfoSearchBeanVO queryInfo)
    {

        Map<String, Object> resMap = new HashMap<String, Object>();
        if (queryInfo.getWms_cus_customer_id() != null)
        {
            resMap.put("wms_cus_customer_id", queryInfo.getWms_cus_customer_id());
        }
        if (queryInfo.getWms_cus_customer_line_houseinfo_id() != null)
        {
            resMap.put("wms_cus_customer_line_houseinfo_id", queryInfo.getWms_cus_customer_line_houseinfo_id());
        }
        resMap.put("sortname", "wms_cus_customer_line_houseinfo_id");
        resMap.put("sortorder", queryInfo.getSortorder());
        resMap.put("Rows", wmscuscustomerlinehouseinfoDao2.search(resMap));
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCusCustomerLineHouseinfoSearchBeanVO queryInfo)
    {
        GridDataBean bean = new GridDataBean(queryInfo.getPage(),
                                             wmscuscustomerlinehouseinfoDao.getListCountNum(queryInfo),
                                             wmscuscustomerlinehouseinfoDao.getListWithPaging(queryInfo));
        return bean.getGridData();
    }

    @Override
    public WmsCusCustomerLineHouseinfoVO getInfoByPK(Integer wms_cus_customer_line_houseinfo_id)
    {
        return wmscuscustomerlinehouseinfoDao.getInfoByPK(wms_cus_customer_line_houseinfo_id);
    }

    @Override
    @Transactional
    public String save(WmsCusCustomerLineHouseinfo bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscuscustomerlinehouseinfoDao.addNewRecord(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCusCustomerLineHouseinfo bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscuscustomerlinehouseinfoDao.updateRecordAll(bean); // update a
                                                                    // record
                                                                    // replace
                                                                    // all,
                                                                    // support
                                                                    // null val
        // ret = wmscuscustomerlinehouseinfoDao.updateRecordCols(bean); //
        // update a record replace columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String delete(WmsCusCustomerLineHouseinfo bean)
    {
        String resStr = "success";
        int ret = 0;
        try
        {
            ret = wmscuscustomerlinehouseinfoDao.deleteRecordByDomain(bean); // nonsupport
                                                                             // null
                                                                             // val
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            resStr = "fkerror"; // default foreign key error
        }
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCusCustomerLineHouseinfo() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByAndMethod(WmsCusCustomerLineHouseinfo queryInfo, Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getWms_cus_customer_line_houseinfo_id() == null)
            {
                setAllPk = false;
            }
            if (!setAllPk)
            {
                log.info("**************please set pk into the queryInfo.**************");
                return "error";
            }
        }
        String resStr = "success";
        List<WmsCusCustomerLineHouseinfo> beanList = wmscuscustomerlinehouseinfoDao.getSingleTableListByAndMethod(queryInfo,
                                                                                                                  isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "or" method, each checking, need new
     * WmsCusCustomerLineHouseinfo() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByOrMethod(WmsCusCustomerLineHouseinfo queryInfo, Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getWms_cus_customer_line_houseinfo_id() == null)
            {
                setAllPk = false;
            }
            if (!setAllPk)
            {
                log.info("**************please set pk into the queryInfo.**************");
                return "error";
            }
        }
        String resStr = "success";
        List<WmsCusCustomerLineHouseinfo> beanList = wmscuscustomerlinehouseinfoDao.getSingleTableListByOrMethod(queryInfo,
                                                                                                                 isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }
}
