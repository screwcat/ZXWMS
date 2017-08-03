package com.zx.emanage.reportmanage.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/**
 * 客户还款情况统计VO
 * @author hancd
 *
 */
public class WmsHelpPlannersCustomerLoanRepaymentConditionsSearchBeanVO extends GridParamBean
{
    private static final long serialVersionUID = 1L;
    
    private String deptId;//部门名称ID
    private String storesId;//门店名称ID
    private String businessgroupId;//业务组名称ID
    private String personnel_Code;//业务元名称和短工号
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    public String getStoresId()
    {
        return storesId;
    }
    public void setStoresId(String storesId)
    {
        this.storesId = storesId;
    }
    public String getBusinessgroupId()
    {
        return businessgroupId;
    }
    public void setBusinessgroupId(String businessgroupId)
    {
        this.businessgroupId = businessgroupId;
    }
    public String getPersonnel_Code()
    {
        return personnel_Code;
    }
    public void setPersonnel_Code(String personnel_Code)
    {
        this.personnel_Code = personnel_Code;
    }
    
    
}
