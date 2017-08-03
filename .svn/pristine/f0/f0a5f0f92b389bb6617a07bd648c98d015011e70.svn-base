package com.zx.emanage.reportmanage.web;
/**
 * 版权所有：版权所有(C) 2015，卓信金控
 * 文件名称: WmsHelpPlannersCustomerLoanRepaymentConditionsController.java
 * 系统名称：WMS
 * 模块名称：客户还款情况统计模块
 * 完成日期：
 * 作    者：
 * 内容摘要：主要实现根据系统中业务员所对应的客户还款情况进行统计显示功能  
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.reportmanage.service.IWmsHelpPlannersCustomerLoanRepaymentConditionsService;
import com.zx.emanage.reportmanage.vo.WmsHelpPlannersCustomerLoanLendingRateStatisticsSearchBeanVO;
import com.zx.emanage.reportmanage.vo.WmsHelpPlannersCustomerLoanRepaymentConditionsSearchBeanVO;

/**
 * 客户还款情况统计控制器
 * @author hancd
 *
 */
@Controller
public class WmsHelpPlannersCustomerLoanRepaymentConditionsController
{
    private static Logger log = LoggerFactory.getLogger(WmsHelpPlannersCustomerLoanRepaymentConditionsController.class);
    @Autowired
    private IWmsHelpPlannersCustomerLoanRepaymentConditionsService wmsHelpPlannersCustomerLoanRepaymentConditionsService;
    
    /**
     * Description :导出符合条件所有客户还款情况信息表数据
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     */
    @RequestMapping(value = "/reportmanage/wmshelpplannerscustomerloanrepaymentconditionswithoutpaginglist.do", method = {RequestMethod.GET,RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsHelpPlannersCustomerLoanRepaymentConditionsSearchBeanVO queryInfo)
    {
        return wmsHelpPlannersCustomerLoanRepaymentConditionsService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :获取根据不同条件进行筛选，查询出相应所有客户还款情况数据列表
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     */
    @RequestMapping(value = "/reportmanage/wmshelpplannerscustomerloanrepaymentconditionswithpaginglist.do", method = { RequestMethod.GET,RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsHelpPlannersCustomerLoanRepaymentConditionsSearchBeanVO queryInfo)
    {
        return wmsHelpPlannersCustomerLoanRepaymentConditionsService.getListWithPaging(queryInfo);
    }
    /**
     * Descrption:根据前台查询条件,进行显示系统提示消息
     * @param queryInfo
     * @author hancd
     */
    @RequestMapping(value="/reportmanage/getDataViewList.do",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> getDataViewList(WmsHelpPlannersCustomerLoanRepaymentConditionsSearchBeanVO queryInfo){
        return wmsHelpPlannersCustomerLoanRepaymentConditionsService.getDataViewList(queryInfo);
    }
}
