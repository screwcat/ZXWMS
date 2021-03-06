package com.zx.emanage.util.gen.vo;

import java.io.Serializable;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsCreCreditHeadVO extends GridParamBean implements Serializable 
{

    private static final long serialVersionUID = 1L;

    private Integer wms_cre_credit_head_id;

    private String bill_code;

    private String credit_purpose;

    private Double max_repayment_limit_per_month;

    private Double credit_limit;

    private Integer max_repayment_time_limit;

    private String enter_file_way;

    private String payroll_payment_way;

    private String data_type;

    private String is_complete;

    private String reference_type;

    private String is_other_corporation_done;

    private String is_to_public_stream;

    private String is_house_certificate_original;

    private String is_check;

    private String person_condition;

    private Integer salesman_id;

    private String salesman_code;

    private String salesman_name;

    private String bill_status;

    private Integer create_user_id;

    private String create_user_name;

    private String create_timestamp;

    private Integer last_update_user_id;

    private String last_update_timestamp;

    private String enable_flag;
    
    private String customer_name;
    
    private String mobile_telephone1;
    
    private String create_timestamp_begin;
    
    private String create_timestamp_end;
    
    private String create_timestamp_JFbegin;
    
    private String create_timestamp_JFend;
    
    private String mort_flag;

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
    }

    public String getBill_code()
    {
        return bill_code;
    }

    public void setBill_code(String obj)
    {
        bill_code = obj;
    }

    public String getCredit_purpose()
    {
        return credit_purpose;
    }

    public void setCredit_purpose(String obj)
    {
        credit_purpose = obj;
    }

    public Double getMax_repayment_limit_per_month()
    {
        return max_repayment_limit_per_month;
    }

    public void setMax_repayment_limit_per_month(Double obj)
    {
        max_repayment_limit_per_month = obj;
    }

    public Double getCredit_limit()
    {
        return credit_limit;
    }

    public void setCredit_limit(Double obj)
    {
        credit_limit = obj;
    }

    public Integer getMax_repayment_time_limit()
    {
        return max_repayment_time_limit;
    }

    public void setMax_repayment_time_limit(Integer obj)
    {
        max_repayment_time_limit = obj;
    }

    public String getEnter_file_way()
    {
        return enter_file_way;
    }

    public void setEnter_file_way(String obj)
    {
        enter_file_way = obj;
    }

    public String getPayroll_payment_way()
    {
        return payroll_payment_way;
    }

    public void setPayroll_payment_way(String obj)
    {
        payroll_payment_way = obj;
    }

    public String getData_type()
    {
        return data_type;
    }

    public void setData_type(String obj)
    {
        data_type = obj;
    }

    public String getIs_complete()
    {
        return is_complete;
    }

    public void setIs_complete(String obj)
    {
        is_complete = obj;
    }

    public String getReference_type()
    {
        return reference_type;
    }

    public void setReference_type(String obj)
    {
        reference_type = obj;
    }

    public String getIs_other_corporation_done()
    {
        return is_other_corporation_done;
    }

    public void setIs_other_corporation_done(String obj)
    {
        is_other_corporation_done = obj;
    }

    public String getIs_to_public_stream()
    {
        return is_to_public_stream;
    }

    public void setIs_to_public_stream(String obj)
    {
        is_to_public_stream = obj;
    }

    public String getIs_house_certificate_original()
    {
        return is_house_certificate_original;
    }

    public void setIs_house_certificate_original(String obj)
    {
        is_house_certificate_original = obj;
    }

    public String getIs_check()
    {
        return is_check;
    }

    public void setIs_check(String obj)
    {
        is_check = obj;
    }

    public String getPerson_condition()
    {
        return person_condition;
    }

    public void setPerson_condition(String obj)
    {
        person_condition = obj;
    }

    public Integer getSalesman_id()
    {
        return salesman_id;
    }

    public void setSalesman_id(Integer obj)
    {
        salesman_id = obj;
    }

    public String getSalesman_code()
    {
        return salesman_code;
    }

    public void setSalesman_code(String obj)
    {
        salesman_code = obj;
    }

    public String getSalesman_name()
    {
        return salesman_name;
    }

    public void setSalesman_name(String obj)
    {
        salesman_name = obj;
    }

    public String getBill_status()
    {
        return bill_status;
    }

    public void setBill_status(String obj)
    {
        bill_status = obj;
    }

    public Integer getCreate_user_id()
    {
        return create_user_id;
    }

    public void setCreate_user_id(Integer obj)
    {
        create_user_id = obj;
    }

    public String getCreate_user_name()
    {
        return create_user_name;
    }

    public void setCreate_user_name(String obj)
    {
        create_user_name = obj;
    }

    public String getCreate_timestamp()
    {
        return create_timestamp;
    }

    public void setCreate_timestamp(String obj)
    {
        create_timestamp = obj;
    }

    public Integer getLast_update_user_id()
    {
        return last_update_user_id;
    }

    public void setLast_update_user_id(Integer obj)
    {
        last_update_user_id = obj;
    }

    public String getLast_update_timestamp()
    {
        return last_update_timestamp;
    }

    public void setLast_update_timestamp(String obj)
    {
        last_update_timestamp = obj;
    }

    public String getEnable_flag()
    {
        return enable_flag;
    }

    public void setEnable_flag(String obj)
    {
        enable_flag = obj;
    }

	public String getCustomer_name() {
		return customer_name;
	}

	public String getMobile_telephone1() {
		return mobile_telephone1;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public void setMobile_telephone1(String mobile_telephone1) {
		this.mobile_telephone1 = mobile_telephone1;
	}

	public String getCreate_timestamp_begin() {
		return create_timestamp_begin;
	}

	public String getCreate_timestamp_end() {
		return create_timestamp_end;
	}

	public void setCreate_timestamp_begin(String create_timestamp_begin) {
		this.create_timestamp_begin = create_timestamp_begin;
	}

	public void setCreate_timestamp_end(String create_timestamp_end) {
		this.create_timestamp_end = create_timestamp_end;
	}

	public String getCreate_timestamp_JFbegin() {
		return create_timestamp_JFbegin;
	}

	public String getCreate_timestamp_JFend() {
		return create_timestamp_JFend;
	}

	public void setCreate_timestamp_JFbegin(String create_timestamp_JFbegin) {
		this.create_timestamp_JFbegin = create_timestamp_JFbegin;
	}

	public void setCreate_timestamp_JFend(String create_timestamp_JFend) {
		this.create_timestamp_JFend = create_timestamp_JFend;
	}

    public String getMort_flag()
    {
        return mort_flag;
    }

    public void setMort_flag(String mort_flag)
    {
        this.mort_flag = mort_flag;
    }
    
}