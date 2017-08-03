package com.zx.emanage.reportmanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import jodd.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.emanage.reportmanage.persist.WmsHelpPlannersCustomerLoanLendingRateStatisticsDao;
import com.zx.emanage.reportmanage.service.IWmsHelpPlannersCustomerLoanLendingRateStatisticsService;
import com.zx.emanage.reportmanage.vo.WmsHelpPlannersCustomerLoanLendingRateStatisticsSearchBeanVO;
import com.zx.emanage.reportmanage.vo.WmsHelpPlannersCustomerLoanRepaymentConditionsSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.platform.syscontext.vo.GridDataBean;
/**
 * 客户累计放款率统计表接口实现类ServiceImpl
 * @author hancd
 *
 */
@Service("wmshelpplannerscustomerloanlendingratestatisticsservice")
public class WmsHelpPlannersCustomerLoanLendingRateStatisticsServiceImpl implements IWmsHelpPlannersCustomerLoanLendingRateStatisticsService
{
    private static Logger log = LoggerFactory.getLogger(WmsHelpPlannersCustomerLoanLendingRateStatisticsServiceImpl.class);
    @Autowired
    private WmsHelpPlannersCustomerLoanLendingRateStatisticsDao wmsHelpPlannersCustomerLoanLendingRateStatisticsDao;
    
    @Override
    public Map<String, Object> getListWithoutPaging(WmsHelpPlannersCustomerLoanLendingRateStatisticsSearchBeanVO queryInfo)
    {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
        //部门名称ID
        if(StringUtil.isNotBlank(queryInfo.getDeptId())&&!queryInfo.getDeptId().equals("-2"))
        {
            paramMap.put("deptId", queryInfo.getDeptId());
        }
        //门店名称ID
        if(StringUtil.isNotBlank(queryInfo.getStoresId())&&!queryInfo.getStoresId().equals("-2"))
        {
            paramMap.put("storesId", queryInfo.getStoresId());
        }
        //业务组名称ID
        if(StringUtil.isNotBlank(queryInfo.getBusinessgroupId())&& !queryInfo.getBusinessgroupId().equals("-2")){
            paramMap.put("businessgroupId", queryInfo.getBusinessgroupId());
        }
        //业务员姓名以及编号
        if(StringUtil.isNotBlank(queryInfo.getPersonnel_Code()))
        {
            paramMap.put("personnel_Code", SysTools.getSqlLikeParam(queryInfo.getPersonnel_Code()));
        }
        //统计时间区间
        if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()) && StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        else if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()) && StringUtil.isBlank(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", "2999-01-01");
        }
        //客户放款率区间
        if(queryInfo.getLoan_amount_str_begin()!=null&&queryInfo.getLoan_amount_str_end()!=null){
 		   if(queryInfo.getLoan_amount_str_begin() >= 0 && (queryInfo.getLoan_amount_str_end() > 0 && queryInfo.getLoan_amount_str_end() <= 100))
 		   {
 		       paramMap.put("loan_amount_str_begin", queryInfo.getLoan_amount_str_begin());
 		       paramMap.put("loan_amount_str_end", queryInfo.getLoan_amount_str_end());
 		   }
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("Rows", wmsHelpPlannersCustomerLoanLendingRateStatisticsDao.getListWithoutPaging(paramMap));
        return paramMap;

    }

    @Override
    public Map<String, Object> getListWithPaging(WmsHelpPlannersCustomerLoanLendingRateStatisticsSearchBeanVO queryInfo)
    {
       Map<String,Object>   paramMap = new HashMap<String, Object>();
       //部门名称ID
       if(StringUtil.isNotBlank(queryInfo.getDeptId())&&!queryInfo.getDeptId().equals("-2"))
       {
           paramMap.put("deptId", queryInfo.getDeptId());
       }
       //门店名称ID
       if(StringUtil.isNotBlank(queryInfo.getStoresId())&&!queryInfo.getStoresId().equals("-2"))
       {
           paramMap.put("storesId", queryInfo.getStoresId());
       }
       //业务组名称ID
       if(StringUtil.isNotBlank(queryInfo.getBusinessgroupId())&& !queryInfo.getBusinessgroupId().equals("-2")){
           paramMap.put("businessgroupId", queryInfo.getBusinessgroupId());
       }
       //业务员姓名以及编号
       if(StringUtil.isNotBlank(queryInfo.getPersonnel_Code()))
       {
           paramMap.put("personnel_Code", SysTools.getSqlLikeParam(queryInfo.getPersonnel_Code()));
       }
       //统计时间区间
       if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()) && StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()))
       {
           paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
           paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
       }
       else if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()) && StringUtil.isBlank(queryInfo.getCreate_timestamp_end()))
       {
           paramMap.put("create_timestamp_end", "2999-01-01");;
       }
       //客户放款率区间
       if(queryInfo.getLoan_amount_str_begin()!=null&&queryInfo.getLoan_amount_str_end()!=null){
		   if(queryInfo.getLoan_amount_str_begin() >= 0 && (queryInfo.getLoan_amount_str_end() > 0 && queryInfo.getLoan_amount_str_end() <= 100))
		   {
		       paramMap.put("loan_amount_str_begin", queryInfo.getLoan_amount_str_begin());
		       paramMap.put("loan_amount_str_end", queryInfo.getLoan_amount_str_end());
		   }
       }
       paramMap.put("sortname", queryInfo.getSortname());
       paramMap.put("sortorder", queryInfo.getSortorder());
       paramMap.put("pagesize", queryInfo.getPagesize());
       paramMap.put("offset", queryInfo.getOffset());
       List<Map<String,Object>> list =wmsHelpPlannersCustomerLoanLendingRateStatisticsDao.search_r(paramMap);
       GridDataBean<Map<String,Object>> bean = new GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsHelpPlannersCustomerLoanLendingRateStatisticsDao.findCount_r(paramMap), list);
       return bean.getGridData();
    }
    /**
     * Descrption:根据前台查询条件,进行显示系统提示消息
     * @param queryInfo
     * @author baisong
     */
    @Override
    public Map<String, Object> getDataViewList(WmsHelpPlannersCustomerLoanLendingRateStatisticsSearchBeanVO queryInfo)
    {
        Map<String,Object> paramMap = new HashMap<>();
        //部门名称ID
        if(StringUtil.isNotBlank(queryInfo.getDeptId())&&!queryInfo.getDeptId().equals("-2"))
        {
            paramMap.put("deptId", queryInfo.getDeptId());
        }
        //门店名称ID
        if(StringUtil.isNotBlank(queryInfo.getStoresId())&&!queryInfo.getStoresId().equals("-2"))
        {
            paramMap.put("storesId", queryInfo.getStoresId());
        }
        //业务组名称ID
        if(StringUtil.isNotBlank(queryInfo.getBusinessgroupId())&& !queryInfo.getBusinessgroupId().equals("-2")){
            paramMap.put("businessgroupId", queryInfo.getBusinessgroupId());
        }
        Map<String,Object> list =wmsHelpPlannersCustomerLoanLendingRateStatisticsDao.searchByDataView(paramMap);
        return list;
    }
	/**
	 * 查询详情
	 * baisong
	 */
	@Override
	public Map<String, Object> searchDetails(Integer salesman_id) {
		 Map<String,Object>   paramMap = new HashMap<String, Object>();
		 paramMap.put("salesman_id", salesman_id);
		 List<Map<String,Object>> list =wmsHelpPlannersCustomerLoanLendingRateStatisticsDao.searchDetails(paramMap);
		 paramMap.clear();
		 paramMap.put("Rows", list);
		 return paramMap;
	}
   
}
