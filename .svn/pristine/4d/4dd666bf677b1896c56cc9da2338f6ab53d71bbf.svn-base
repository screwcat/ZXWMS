package com.zx.emanage.reportmanage.web;
/**
 * 版权所有：版权所有(C) 2015，卓信金控
 * 文件名称: WmsHelpPlannersCustomerLoanLendingRateStatisticsController.java
 * 系统名称：WMS
 * 模块名称：客户累计放款率统计模块
 * 完成日期：
 * 作    者：
 * 内容摘要：主要实现根据系统中业务员所对应的客户累计放款率的统计模块
 * 
 * 文件调用：
 * 修改记录：
 * 修改时间：
 * 修 改 人: 
 * 修改内容：
 * 关联BUG：
 * 修改方法：
 */
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.reportmanage.service.IWmsHelpPlannersCustomerLoanLendingRateStatisticsService;
import com.zx.emanage.reportmanage.vo.WmsHelpPlannersCustomerLoanLendingRateStatisticsSearchBeanVO;
import com.zx.emanage.reportmanage.vo.WmsHelpPlannersCustomerLoanRepaymentConditionsSearchBeanVO;
/**
 * 客户累计放款率统计表控制器Controller
 * @author hancd
 *
 */
@Controller
public class WmsHelpPlannersCustomerLoanLendingRateStatisticsController
{
    private static Logger log = LoggerFactory.getLogger(WmsHelpPlannersCustomerLoanLendingRateStatisticsController.class);
    @Autowired
    private IWmsHelpPlannersCustomerLoanLendingRateStatisticsService wmsHelpPlannersCustomerLoanLendingRateStatisticsService;

    /**
     * Description :根据筛选条件导出客户信息放款率统计表信息
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/reportmanage/wmsHelpPlannersCustomerLoanLendingRateStatisticsWithoutList.do", method = {RequestMethod.GET,RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsHelpPlannersCustomerLoanLendingRateStatisticsSearchBeanVO queryInfo)
    {
        return wmsHelpPlannersCustomerLoanLendingRateStatisticsService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :根据筛选条件获取客户信息放款率统计表信息
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/reportmanage/wmsHelpPlannersCustomerLoanLendingRateStatisticsWithList.do", method = { RequestMethod.GET,RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsHelpPlannersCustomerLoanLendingRateStatisticsSearchBeanVO queryInfo)
    {
        return wmsHelpPlannersCustomerLoanLendingRateStatisticsService.getListWithPaging(queryInfo);
    }
    /**
     * Descrption:根据前台查询条件,进行显示系统提示消息
     * @param queryInfo
     * @author baisong
     */
    @RequestMapping(value="/reportmanage/getDataViewListStatistics.do",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> getDataViewList(WmsHelpPlannersCustomerLoanLendingRateStatisticsSearchBeanVO queryInfo){
        return wmsHelpPlannersCustomerLoanLendingRateStatisticsService.getDataViewList(queryInfo);
    }
    /**
     * Descrption:查询详情
     * @param queryInfo
     * @author baisong
     */
    @RequestMapping(value="/reportmanage/searchDetails.do",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> searchDetails(Integer salesman_id){
        return wmsHelpPlannersCustomerLoanLendingRateStatisticsService.searchDetails(salesman_id);
    }
}
