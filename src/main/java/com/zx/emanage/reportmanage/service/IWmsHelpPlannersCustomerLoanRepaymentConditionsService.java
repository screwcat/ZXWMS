package com.zx.emanage.reportmanage.service;

import java.util.Map;

import com.zx.emanage.reportmanage.vo.WmsHelpPlannersCustomerLoanRepaymentConditionsSearchBeanVO;

public interface IWmsHelpPlannersCustomerLoanRepaymentConditionsService
{
    /**
     * Description :导出客户还款情况信息统计表
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     */
    public Map<String, Object> getListWithoutPaging(WmsHelpPlannersCustomerLoanRepaymentConditionsSearchBeanVO queryInfo);

    /**
     * Description :根据条件限制客户还款情况信息统计表
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     */
    public Map<String, Object> getListWithPaging(WmsHelpPlannersCustomerLoanRepaymentConditionsSearchBeanVO queryInfo);
    /**
     * Descrption:根据前台查询条件,进行显示系统提示消息
     * @param queryInfo
     * @author hancd
     */
    public Map<String, Object> getDataViewList(WmsHelpPlannersCustomerLoanRepaymentConditionsSearchBeanVO queryInfo);
  
}
