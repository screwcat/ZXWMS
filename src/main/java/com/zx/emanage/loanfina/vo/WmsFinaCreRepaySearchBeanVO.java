package com.zx.emanage.loanfina.vo;

import java.sql.Date;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsFinaCreRepaySearchBeanVO extends GridParamBean {

    private static final long serialVersionUID = 1L;
    private String protocol_code;//合同编号
    private String debtor_name;//客户姓名
    private String debtor_tel;//联系电话
    private String salesman_name;//业务员
    private String cre_type;//贷款产品
    private String repay_status;//还款状态
    private String home_dunning_status;//上门催缴状态
    private String dunning_name;//贷后专员即催缴人
    private String dunning_status;//催缴状态
    private Date repayment_date_start;//实际还款日期start
    private Date repayment_date_end;//实际还款日期end
    private Date current_repay_date_start;//应还款日期start
    private Date current_repay_date_end;//应还款日期end
    
    private Integer wms_cre_credit_group_id;// 组合贷组建

    private Integer wms_cre_credit_head_id;// 组建

    //贷后专员
    public String getProtocol_code()
    {
        return protocol_code;
    }
    public void setProtocol_code(String protocol_code)
    {
        this.protocol_code = protocol_code;
    }
    public String getDebtor_name()
    {
        return debtor_name;
    }
    public void setDebtor_name(String debtor_name)
    {
        this.debtor_name = debtor_name;
    }
    public String getDebtor_tel()
    {
        return debtor_tel;
    }
    public void setDebtor_tel(String debtor_tel)
    {
        this.debtor_tel = debtor_tel;
    }
    public String getSalesman_name()
    {
        return salesman_name;
    }
    public void setSalesman_name(String salesman_name)
    {
        this.salesman_name = salesman_name;
    }
    public String getCre_type()
    {
        return cre_type;
    }
    public void setCre_type(String cre_type)
    {
        this.cre_type = cre_type;
    }
    public String getRepay_status()
    {
        return repay_status;
    }
    public void setRepay_status(String repay_status)
    {
        this.repay_status = repay_status;
    }
    public String getHome_dunning_status()
    {
        return home_dunning_status;
    }
    public void setHome_dunning_status(String home_dunning_status)
    {
        this.home_dunning_status = home_dunning_status;
    }
    public String getDunning_name()
    {
        return dunning_name;
    }
    public void setDunning_name(String dunning_name)
    {
        this.dunning_name = dunning_name;
    }
    public String getDunning_status()
    {
        return dunning_status;
    }
    public void setDunning_status(String dunning_status)
    {
        this.dunning_status = dunning_status;
    }
	public Date getRepayment_date_start() {
		return repayment_date_start;
	}
	public void setRepayment_date_start(Date repayment_date_start) {
		this.repayment_date_start = repayment_date_start;
	}
	public Date getRepayment_date_end() {
		return repayment_date_end;
	}
	public void setRepayment_date_end(Date repayment_date_end) {
		this.repayment_date_end = repayment_date_end;
	}
	public Date getCurrent_repay_date_start() {
		return current_repay_date_start;
	}
	public void setCurrent_repay_date_start(Date current_repay_date_start) {
		this.current_repay_date_start = current_repay_date_start;
	}
	public Date getCurrent_repay_date_end() {
		return current_repay_date_end;
	}
	public void setCurrent_repay_date_end(Date current_repay_date_end) {
		this.current_repay_date_end = current_repay_date_end;
	}

    public Integer getWms_cre_credit_group_id()
    {
        return wms_cre_credit_group_id;
    }

    public void setWms_cre_credit_group_id(Integer wms_cre_credit_group_id)
    {
        this.wms_cre_credit_group_id = wms_cre_credit_group_id;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer wms_cre_credit_head_id)
    {
        this.wms_cre_credit_head_id = wms_cre_credit_head_id;
    }

}