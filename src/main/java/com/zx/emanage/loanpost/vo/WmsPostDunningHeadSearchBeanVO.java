package com.zx.emanage.loanpost.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsPostDunningHeadSearchBeanVO extends GridParamBean {
	 
	private static final long serialVersionUID = 1L;
	private String protocol_code;//合同编号
	private String debtor_name;//客户名称
	private String debtor_tel;//联系电话
	private String salesman_name;//业务员
	private String cre_type;//贷款产品类型
	private String final_dunning_name;//催缴人
	private String home_dunning_status;//上门催缴状态
	
	
	private java.sql.Date dunning_datetime;
	private String dunning_names;
	private String dunning_remarks;
	private String dunning_ids;
	private String dunning_deptIds;
	private String pass;
	private String advice;
	
	private String repay_status;//还款状态
    private String dunning_name;//贷后专员即催缴人
    private String dunning_status;//催缴状态
    
    private String post_dunning_cj_code;//催缴单编号
    private java.sql.Date current_repay_date_start;//应还款日期查询开始值
    private java.sql.Date current_repay_date_end;//应还款日期查询结束值

	
	public String getPost_dunning_cj_code() {
		return post_dunning_cj_code;
	}
	public void setPost_dunning_cj_code(String post_dunning_cj_code) {
		this.post_dunning_cj_code = post_dunning_cj_code;
	}
	public java.sql.Date getCurrent_repay_date_start() {
		return current_repay_date_start;
	}
	public void setCurrent_repay_date_start(java.sql.Date current_repay_date_start) {
		this.current_repay_date_start = current_repay_date_start;
	}
	public java.sql.Date getCurrent_repay_date_end() {
		return current_repay_date_end;
	}
	public void setCurrent_repay_date_end(java.sql.Date current_repay_date_end) {
		this.current_repay_date_end = current_repay_date_end;
	}
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
    public String getFinal_dunning_name()
    {
        return final_dunning_name;
    }
    public void setFinal_dunning_name(String final_dunning_name)
    {
        this.final_dunning_name = final_dunning_name;
    }
    
    public String getHome_dunning_status()
    {
        return home_dunning_status;
    }
    public void setHome_dunning_status(String home_dunning_status)
    {
        this.home_dunning_status = home_dunning_status;
    }
    public java.sql.Date getDunning_datetime()
    {
        return dunning_datetime;
    }
    public void setDunning_datetime(java.sql.Date dunning_datetime)
    {
        this.dunning_datetime = dunning_datetime;
    }
    public String getDunning_names()
    {
        return dunning_names;
    }
    public void setDunning_names(String dunning_names)
    {
        this.dunning_names = dunning_names;
    }
    public String getDunning_remarks()
    {
        return dunning_remarks;
    }
    public void setDunning_remarks(String dunning_remarks)
    {
        this.dunning_remarks = dunning_remarks;
    }
    public String getPass()
    {
        return pass;
    }
    public void setPass(String pass)
    {
        this.pass = pass;
    }
    public String getAdvice()
    {
        return advice;
    }
    public void setAdvice(String advice)
    {
        this.advice = advice;
    }
    public String getDunning_ids()
    {
        return dunning_ids;
    }
    public void setDunning_ids(String dunning_ids)
    {
        this.dunning_ids = dunning_ids;
    }
    public String getDunning_deptIds()
    {
        return dunning_deptIds;
    }
    public void setDunning_deptIds(String dunning_deptIds)
    {
        this.dunning_deptIds = dunning_deptIds;
    }
	public String getRepay_status() {
		return repay_status;
	}
	public void setRepay_status(String repay_status) {
		this.repay_status = repay_status;
	}
	public String getDunning_name() {
		return dunning_name;
	}
	public void setDunning_name(String dunning_name) {
		this.dunning_name = dunning_name;
	}
	public String getDunning_status() {
		return dunning_status;
	}
	public void setDunning_status(String dunning_status) {
		this.dunning_status = dunning_status;
	}
    
}