package com.zx.emanage.cremanage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.IWmsCreCustomerChangeLineHouseinfoDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineHouseinfoDao;
import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.cremanage.service.IWmsCreCustomerChangeLineHouseinfoService;
import com.zx.emanage.cremanage.service.IWmsHouseCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineHouseinfoSearchBeanVO;
import com.zx.emanage.cusmanage.persist.WmsCusCustomerLineHouseinfoDao;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.WmsHelp;
import com.zx.emanage.util.gen.domain.WmsCreCustomerChangeLineHouseinfo;
import com.zx.emanage.util.gen.vo.WmsCreCreditHeadVO;
import com.zx.emanage.util.gen.vo.WmsCreCustomerChangeLineHouseinfoVO;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecustomerchangelinehouseinfoService")
public class WmsCreCustomerChangeLineHouseinfoServiceImpl implements IWmsCreCustomerChangeLineHouseinfoService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCustomerChangeLineHouseinfoServiceImpl.class);

    @Autowired
    private IWmsCreCustomerChangeLineHouseinfoDao wmscrecustomerchangelinehouseinfoDao;

    @Autowired
    private WmsCreCustomerChangeLineHouseinfoDao wmscrecustomerchangelinehouseinfoDao_m;

    @Autowired
    private WmsCusCustomerLineHouseinfoDao wmscuscustomerlinehouseinfoDao;
    
    @Autowired
    private WmsCreCreditHeadDao wmsCreCreditHeadDao;
    
    @Autowired
    private IWmsLoanWorkFlowService wmsLoanWorkFlowService;
    @Autowired
    private IWmsHouseCreditWorkFlowService houseCreditWorkFlowService;

    @Autowired
    private IWmsCreCreditHeadService wmsCreCreditHeadService;
    
    

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCustomerChangeLineHouseinfoSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_line_customer_change_head_id",
                     queryInfo.getWms_cre_credit_line_customer_change_head_id());
        paramMap.put("wms_cre_customer_change_line_houseinfo_id",
                     queryInfo.getWms_cre_customer_change_line_houseinfo_id());
        List<Map<String, Object>> list=wmscrecustomerchangelinehouseinfoDao_m.search(paramMap);
       
        /*if( list!=null&&list.size()==0){
        	 paramMap.put("wms_cus_customer_id",
                     queryInfo.getWms_cre_credit_line_customer_change_head_id());
        	 paramMap.put("sortname", "wms_cus_customer_line_houseinfo_id");
        	 paramMap.put("sortorder", queryInfo.getSortorder());
        	 list= wmscuscustomerlinehouseinfoDao.searchInfo(paramMap);
        }*/
        resMap.put("Rows", list);
        resMap.put("sortname", queryInfo.getSortname());
        resMap.put("sortorder", queryInfo.getSortorder());
        resMap.put("wms_cus_customer_id", "wms_cus_customer_id");
        return resMap;
    }

	/**
	 * 房产核查缴费- 2016-9-26
	 * 查询列表
	 * @author jiaodelong
	 */
    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditHeadVO queryInfo, UserBean user)
    {
    	
        Map<String, Object> paramMap = new HashMap<>();

    	 if (StringUtil.isNotBlank(queryInfo.getBill_code()))
         {
             paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
         }
    	  if (queryInfo.getCreate_timestamp_begin() != null && !"".equals(queryInfo.getCreate_timestamp_begin()))
          {
              paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
          }
          if (queryInfo.getCreate_timestamp_end() != null && !"".equals(queryInfo.getCreate_timestamp_end()))
          {
              paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
          }                   
         if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
         {
             paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
             paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
         }
         if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
         {
             paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
         }
         if (StringUtil.isNotBlank(queryInfo.getMobile_telephone1()))
         {
             paramMap.put("mobile_telephone1",SysTools.getSqlLikeParam(queryInfo.getMobile_telephone1()));
         }
        if (StringUtil.isNotBlank(queryInfo.getMort_flag()))
        {
            paramMap.put("mort_flag", queryInfo.getMort_flag());// 抵押状态
        }
         paramMap.put("sortname", queryInfo.getSortname());
         paramMap.put("sortorder", queryInfo.getSortorder());
         paramMap.put("offset", queryInfo.getOffset());
         paramMap.put("pagesize", queryInfo.getPagesize());
        // paramMap.put("citynum", user.getUser_regionNumber());
        // 开发模式 1为开发模式 其他为正常权限模式
        if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode")))
        {
            paramMap.put("salesman_id", user.getUserId());// 登陆人id
            paramMap.put("menu_url", WmsHelp.MENU_URL_FCJF_LIST);
            paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
        }
         List<Map<String, Object>> list = wmsCreCreditHeadDao.wmscrecustomerchangelinehouseinfowithpaginglist(paramMap);
         GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                 queryInfo.getPage(),
                 wmsCreCreditHeadDao.wmsCreCustomerChangeLineHouseInfoWithPagingCount(paramMap),
                 list);
         return bean.getGridData();
    }

    @Override
    public WmsCreCustomerChangeLineHouseinfoVO getInfoByPK(Integer wms_cre_customer_change_line_houseinfo_id)
    {
        return wmscrecustomerchangelinehouseinfoDao.getInfoByPK(wms_cre_customer_change_line_houseinfo_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCustomerChangeLineHouseinfo bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecustomerchangelinehouseinfoDao.addNewRecord(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCustomerChangeLineHouseinfo bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecustomerchangelinehouseinfoDao.updateRecordAll(bean); // update
                                                                          // a
                                                                          // record
                                                                          // replace
                                                                          // all,
                                                                          // support
                                                                          // null
                                                                          // val
        // ret = wmscrecustomerchangelinehouseinfoDao.updateRecordCols(bean); //
        // update a record replace columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String delete(WmsCreCustomerChangeLineHouseinfo bean)
    {
        String resStr = "success";
        int ret = 0;
        try
        {
            ret = wmscrecustomerchangelinehouseinfoDao.deleteRecordByDomain(bean); // nonsupport
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
     * WmsCreCustomerChangeLineHouseinfo() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByAndMethod(WmsCreCustomerChangeLineHouseinfo queryInfo, Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getWms_cre_customer_change_line_houseinfo_id() == null)
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
        List<WmsCreCustomerChangeLineHouseinfo> beanList = wmscrecustomerchangelinehouseinfoDao.getSingleTableListByAndMethod(queryInfo,
                                                                                                                              isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "or" method, each checking, need new
     * WmsCreCustomerChangeLineHouseinfo() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByOrMethod(WmsCreCustomerChangeLineHouseinfo queryInfo, Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getWms_cre_customer_change_line_houseinfo_id() == null)
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
        List<WmsCreCustomerChangeLineHouseinfo> beanList = wmscrecustomerchangelinehouseinfoDao.getSingleTableListByOrMethod(queryInfo,
                                                                                                                             isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }
	/**
	 * 房产核查缴费- 2016-9-26
	 * 列表导出excel
	 * @author jiaodelong
	 */
	@Override
	public Map<String, Object> getListWithPagingout(WmsCreCreditHeadVO queryInfo, UserBean user) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (queryInfo.getCreate_timestamp_begin() != null && !"".equals(queryInfo.getCreate_timestamp_begin()))
        {
             paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if (queryInfo.getCreate_timestamp_end() != null && !"".equals(queryInfo.getCreate_timestamp_end()))
        {
             paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone1()))
        {
            paramMap.put("mobile_telephone1",SysTools.getSqlLikeParam(queryInfo.getMobile_telephone1()));
        }
        if (StringUtil.isNotBlank(queryInfo.getMort_flag()))
        {
            paramMap.put("mort_flag", queryInfo.getMort_flag());// 抵押状态
        }
        paramMap.put("citynum", user.getUser_regionNumber());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        // 开发模式 1为开发模式 其他为正常权限模式
        if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode")))
        {
            paramMap.put("salesman_id", user.getUserId());// 登陆人id
            paramMap.put("menu_url", WmsHelp.MENU_URL_FCJF_LIST);
            paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
        }
        paramMap.put("Rows", wmsCreCreditHeadDao.wmscrecustomerchangelinehouseinfowithpaginglist(paramMap));
        return paramMap;
	}
}
