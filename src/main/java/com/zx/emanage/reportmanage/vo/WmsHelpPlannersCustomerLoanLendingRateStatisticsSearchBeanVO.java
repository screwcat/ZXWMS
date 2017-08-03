package com.zx.emanage.reportmanage.vo;

import com.zx.platform.syscontext.vo.GridParamBean;
/**
 * 客户累计放款率统计表VO
 * @author hancd
 *
 */
public class WmsHelpPlannersCustomerLoanLendingRateStatisticsSearchBeanVO extends GridParamBean
{
    private static final long serialVersionUID = 1L;
    
    private String deptId;//部门名称ID
    private String storesId;//门店ID
    private String businessgroupId;//业务组ID
    private String personnel_Code;//业务员姓名/编号
    private String create_timestamp_begin;//统计时间 开始
    private String create_timestamp_end;//统计时间 结束
    private Double loan_amount_str_begin;//放款率 开始
    private Double loan_amount_str_end;//放款率  结束
    
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
    
    public String getCreate_timestamp_begin()
    {
        return create_timestamp_begin;
    }
    public void setCreate_timestamp_begin(String create_timestamp_begin)
    {
        this.create_timestamp_begin = create_timestamp_begin;
    }
    public String getCreate_timestamp_end()
    {
        return create_timestamp_end;
    }
    public void setCreate_timestamp_end(String create_timestamp_end)
    {
        this.create_timestamp_end = create_timestamp_end;
    }
    public Double getLoan_amount_str_begin()
    {
        return loan_amount_str_begin;
    }
    public void setLoan_amount_str_begin(Double loan_amount_str_begin)
    {
        this.loan_amount_str_begin = loan_amount_str_begin;
    }
    public Double getLoan_amount_str_end()
    {
        return loan_amount_str_end;
    }
    public void setLoan_amount_str_end(Double loan_amount_str_end)
    {
        this.loan_amount_str_end = loan_amount_str_end;
    }
    
}
