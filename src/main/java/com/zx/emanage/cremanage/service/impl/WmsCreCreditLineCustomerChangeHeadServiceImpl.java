package com.zx.emanage.cremanage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.IWmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineCompanyDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineHouseinfoDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineWorkinfoDao;
import com.zx.emanage.cremanage.persist.WmsCusCustomerChangeChildDao;
import com.zx.emanage.cremanage.service.IWmsCreCreditLineCustomerChangeHeadService;
import com.zx.emanage.cremanage.vo.WmsCreCreditLineCustomerChangeHeadSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCreCreditLineCustomerChangeHead;
import com.zx.emanage.util.gen.vo.WmsCreCreditLineCustomerChangeHeadVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlinecustomerchangeheadService")
public class WmsCreCreditLineCustomerChangeHeadServiceImpl implements IWmsCreCreditLineCustomerChangeHeadService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineCustomerChangeHeadServiceImpl.class);

    @Autowired
    private IWmsCreCreditLineCustomerChangeHeadDao wmscrecreditlinecustomerchangeheadDao;

    @Autowired
    private WmsCreCreditLineCustomerChangeHeadDao wmscrecreditlinecustomerchangeheadlistDao;

    @Autowired
    private WmsCreCustomerChangeLineWorkinfoDao wmsCreCustomerChangeLineWorkinfoDao;
    
    @Autowired
    private WmsCusCustomerChangeChildDao wmscuscustomerchangechilddao;

    @Autowired
    private WmsCreCustomerChangeLineHouseinfoDao wmscrecustomerchangelinehouseinfoDao;
    
    @Autowired
    private WmsCreCustomerChangeLineWorkinfoDao wmscrecustomerchangelineworkinfoDao;

    @Autowired
    private WmsCreCustomerChangeLineCompanyDao wmscrecustomerchangelinecompanydao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCustomerChangeHeadSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (queryInfo.getWms_cre_credit_head_id() != null)
        {
            resMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        }
        resMap.put("Rows", wmscrecreditlinecustomerchangeheadlistDao.search(resMap));
        resMap.put("sortname", queryInfo.getSortname());
        resMap.put("sortorder", queryInfo.getSortorder());
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCustomerChangeHeadSearchBeanVO queryInfo)
    {
        GridDataBean bean = new GridDataBean(queryInfo.getPage(),
                                             wmscrecreditlinecustomerchangeheadDao.getListCountNum(queryInfo),
                                             wmscrecreditlinecustomerchangeheadDao.getListWithPaging(queryInfo));
        return bean.getGridData();
    }

    @Override
    public com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead getInfoByPK(Integer wms_cre_credit_line_customer_change_head_id)
    {
        return wmscrecreditlinecustomerchangeheadlistDao.get(wms_cre_credit_line_customer_change_head_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineCustomerChangeHead bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinecustomerchangeheadDao.addNewRecord(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineCustomerChangeHead bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinecustomerchangeheadDao.updateRecordAll(bean); // update
                                                                           // a
                                                                           // record
                                                                           // replace
                                                                           // all,
                                                                           // support
                                                                           // null
                                                                           // val
        // ret = wmscrecreditlinecustomerchangeheadDao.updateRecordCols(bean);
        // // update a record replace columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String delete(WmsCreCreditLineCustomerChangeHead bean)
    {
        String resStr = "success";
        int ret = 0;
        try
        {
            ret = wmscrecreditlinecustomerchangeheadDao.deleteRecordByDomain(bean); // nonsupport
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

    @Override
    public java.util.Map<String, Object> getInfoListByTEL(Integer wms_cre_credit_head_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (wms_cre_credit_head_id != null)
        {
            paramMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        }
        // 获取多条贷款人电话信息
        List<Map<String, Object>> list = wmscrecreditlinecustomerchangeheadlistDao.searchByTel(paramMap);
        List<Map<String, Object>> tels = new ArrayList<>();
        // 需要重新组合信息
        for (Map<String, Object> map : list)
        {
            // 获取用户的第一个手机号
            Map<String, Object> e1 = new HashMap<>();
            e1.put("user_tel", map.get("mobile_telephone1"));
            e1.put("wms_cre_credit_line_customer_change_head_id",
                   map.get("wms_cre_credit_line_customer_change_head_id"));
            // 获取用户的第二个手机号
            Map<String, Object> e2 = new HashMap<>();
            if (!map.get("mobile_telephone2").equals("无") && !map.get("mobile_telephone2").equals("没有"))
            {
                e2.put("user_tel", map.get("mobile_telephone2"));
                e2.put("wms_cre_credit_line_customer_change_head_id",
                       map.get("wms_cre_credit_line_customer_change_head_id"));
                tels.add(e2);
            }
            tels.add(e1);
        }
        paramMap.put("Rows", tels);
        return paramMap;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreCreditLineCustomerChangeHead() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByAndMethod(WmsCreCreditLineCustomerChangeHead queryInfo, Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getWms_cre_credit_line_customer_change_head_id() == null)
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
        List<WmsCreCreditLineCustomerChangeHead> beanList = wmscrecreditlinecustomerchangeheadDao.getSingleTableListByAndMethod(queryInfo,
                                                                                                                                isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "or" method, each checking, need new
     * WmsCreCreditLineCustomerChangeHead() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByOrMethod(WmsCreCreditLineCustomerChangeHead queryInfo, Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getWms_cre_credit_line_customer_change_head_id() == null)
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
        List<WmsCreCreditLineCustomerChangeHead> beanList = wmscrecreditlinecustomerchangeheadDao.getSingleTableListByOrMethod(queryInfo,
                                                                                                                               isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    /**
     * Description :获取指定贷款记录的主贷人和非主货人的集合
     * 
     * @param wms_cre_credit_head_id 贷款记录ID
     * @return 指定贷款记录的主贷人和非主货人的集合
     * @author wangyishun
     */
    public Map<String, Object> getWmsCreCreditLineCustomerChangeHeadListWithoutPaging(Integer wms_cre_credit_head_id)
    {
        List<com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead> customers = wmscrecreditlinecustomerchangeheadlistDao.getWmsCreCreditLineCustomerChangeHeadListWithoutPaging(wms_cre_credit_head_id);
        int count = customers.size();
        Iterator<com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead> ite = customers.iterator();
        Map<String, Object> resultMap = new HashMap<>();
        while (ite.hasNext())
        {
            com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead item = ite.next();
            if ("1".equals((item.getIs_major())))
            {
                resultMap.put("major", item);
                ite.remove();
                break;
            }
        }
        resultMap.put("notMajor", customers);
        resultMap.put("count", count);
        return resultMap;
    }

    @Override
    public Map<String, Object> getInfoMapByPK(Integer wms_cre_credit_line_customer_change_head_id)
    {
        Map<String, Object> mapAll = new HashMap<String, Object>();
        mapAll.putAll(wmscrecreditlinecustomerchangeheadlistDao.get(wms_cre_credit_line_customer_change_head_id)
                                                               .getInfoMap());
        if (wmsCreCustomerChangeLineWorkinfoDao.get(wms_cre_credit_line_customer_change_head_id) != null)
        {
            mapAll.putAll(wmsCreCustomerChangeLineWorkinfoDao.get(wms_cre_credit_line_customer_change_head_id)
                                                             .getInfoMap());
        }
        return mapAll;
    }
    
    /**
     * 根据客户id查询所有客户信息(客户、子女、房产、工作、公司)
     * @author administrator
     */
    @Override
    public Map<String, Object> searchAllCustomerInfo(WmsCreCreditLineCustomerChangeHeadSearchBeanVO queryInfo, UserBean user) {
        
        Map<String, Object> resMap = new HashMap<String, Object>();
        
        Integer wms_cre_credit_line_customer_change_head_id = queryInfo.getWms_cre_credit_line_customer_change_head_id();
        
        //客户信息
        com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead customerInfo = 
                wmscrecreditlinecustomerchangeheadlistDao.get(wms_cre_credit_line_customer_change_head_id);
        
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_line_customer_change_head_id", wms_cre_credit_line_customer_change_head_id);
        paramMap.put("enable_flag", "1");
        paramMap.put("sortname", "");
        paramMap.put("offset", "");
        
        //子女信息
        List<Map<String, Object>> childInfo = wmscuscustomerchangechilddao.search(paramMap);
        
        //房产信息
        List<Map<String, Object>> houseInfo = wmscrecustomerchangelinehouseinfoDao.search(paramMap);
        
        //工作信息
        List<Map<String, Object>> workInfo = wmscrecustomerchangelineworkinfoDao.search(paramMap);
        
        //公司信息
        List<Map<String, Object>> companyInfo = wmscrecustomerchangelinecompanydao.search(paramMap);
        
        resMap.put("customerInfo", customerInfo);
        resMap.put("childInfo", childInfo);
        resMap.put("houseInfo", houseInfo);
        resMap.put("workInfo", workInfo);
        resMap.put("companyInfo", companyInfo);
        
        return resMap;
    }
    
    /**
     * 根据客户id删除所有客户信息(逻辑删除)
     * @author administrator
     */
    public Map<String, Object> wmsCustomerAllInfoDelete(WmsCreCreditLineCustomerChangeHeadSearchBeanVO queryInfo, UserBean user) {
        log.debug("--------------------根据客户id删除所有客户信息(逻辑删除)开始--------------------");
        
        Map<String, Object> resMap = new HashMap<String, Object>();
        Integer wms_cre_credit_line_customer_change_head_id = queryInfo.getWms_cre_credit_line_customer_change_head_id();
        this.wmscrecreditlinecustomerchangeheadlistDao.wmsCustomerAllInfoDelete(wms_cre_credit_line_customer_change_head_id);
        
        log.debug("--------------------根据客户id删除所有客户信息(逻辑删除)结束--------------------");
        return resMap;
    }

	@Override
	public WmsCreCreditLineCustomerChangeHeadVO getKHXX(Integer wms_cre_credit_head_id) {
        WmsCreCreditLineCustomerChangeHeadVO vo = wmscrecreditlinecustomerchangeheadlistDao.getKHXX(wms_cre_credit_head_id);
        return vo;
	}
    
    
    
}
