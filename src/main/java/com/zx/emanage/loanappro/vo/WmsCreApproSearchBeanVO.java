package com.zx.emanage.loanappro.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

public class WmsCreApproSearchBeanVO extends GridParamBean
{
    private static final long serialVersionUID = 1L;
    /** 是否分页标识  0：不需要分页  1或空：不需要分页 **/
    private String need_paging_flag;
    
    private String biaozhi;

    private String bill_code;// 单据编号
    
    private String create_timestamp;// 申请时间
    
    private String begin_time;
    
    private String end_time;

    private String create_timestamp1;// 申请时间区间

    private String salesman_name;// 业务员

    private String id_card;// 身份证号

    private String cre_type;// 贷款产品类型

    private String protocol_id_year;// 协议编号_年

    private String protocol_id_num;// 协议编号_编号

    private String protocol_id_year_num; // 合同编号

    private String debtor_name;// 债务人姓名

    private String debtor_tel;// 债务人联系电话

    private String create_user_id;// 单据原始录单员ID

    private String customer_name;// 客户姓名

    private String city;// 城市

    private String bill_status;// 单据状态

    private String loan_amount_str_begin;

    private String loan_amount_str_end;

    private String credit_limit_begin;

    private String credit_limit_end;

    private String repay_status;

    private String create_timestamp_begin;// 申请时间开始时间段

    private String create_timestamp_end;// 申请时间结束时间段
    
    private String dept_city_sel;//所属城市

    private String dept_name_sel;//所属门店

    private String salesman_city_code;
    
    private String customer_name_history;
    
    private String id_card_history;
    
    private String bill_code_search;// 单据编号

    // 1或者空分页 0不分页
    private String is_page;// 是否分页
    
    private String bill_type;
    
    private String protocol_date_begin;
    
    private String protocol_date_end;

    private String cre_loan_type;

    private String mort_flag;

    public String getSalesman_city_code() {
		return salesman_city_code;
	}

	public void setSalesman_city_code(String salesman_city_code) {
		this.salesman_city_code = salesman_city_code;
	}

	public String getDept_city_sel() {
		return dept_city_sel;
	}

	public void setDept_city_sel(String dept_city_sel) {
		this.dept_city_sel = dept_city_sel;
	}

	public String getDept_name_sel() {
		return dept_name_sel;
	}

	public void setDept_name_sel(String dept_name_sel) {
		this.dept_name_sel = dept_name_sel;
	}

	public String getCreate_timestamp1()
    {
        return create_timestamp1;
    }

    public void setCreate_timestamp1(String create_timestamp1)
    {
        this.create_timestamp1 = create_timestamp1;
    }

    public String getProtocol_id_year()
    {
        return protocol_id_year;
    }

    public void setProtocol_id_year(String protocol_id_year)
    {
        this.protocol_id_year = protocol_id_year;
    }

    public String getProtocol_id_num()
    {
        return protocol_id_num;
    }

    public void setProtocol_id_num(String protocol_id_num)
    {
        this.protocol_id_num = protocol_id_num;
    }

    public String getProtocol_id_year_num()
    {
        return protocol_id_year_num;
    }

    public void setProtocol_id_year_num(String protocol_id_year_num)
    {
        this.protocol_id_year_num = protocol_id_year_num;
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

    public String getCre_type()
    {
        return cre_type;
    }

    public void setCre_type(String cre_type)
    {
        this.cre_type = cre_type;
    }

    public String getBill_code()
    {
        return bill_code;
    }

    public void setBill_code(String bill_code)
    {
        this.bill_code = bill_code;
    }

    public String getCreate_timestamp()
    {
        return create_timestamp;
    }

    public void setCreate_timestamp(String create_timestamp)
    {
        this.create_timestamp = create_timestamp;
    }

    public String getSalesman_name()
    {
        return salesman_name;
    }

    public void setSalesman_name(String salesman_name)
    {
        this.salesman_name = salesman_name;
    }

    public String getId_card()
    {
        return id_card;
    }

    public void setId_card(String id_card)
    {
        this.id_card = id_card;
    }

    public String getCreate_user_id()
    {
        return create_user_id;
    }

    public void setCreate_user_id(String create_user_id)
    {
        this.create_user_id = create_user_id;
    }

    public String getCustomer_name()
    {
        return customer_name;
    }

    public void setCustomer_name(String customer_name)
    {
        this.customer_name = customer_name;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getBill_status()
    {
        return bill_status;
    }

    public void setBill_status(String bill_status)
    {
        this.bill_status = bill_status;
    }

    public String getLoan_amount_str_begin()
    {
        return loan_amount_str_begin;
    }

    public void setLoan_amount_str_begin(String loan_amount_str_begin)
    {
        this.loan_amount_str_begin = loan_amount_str_begin;
    }

    public String getLoan_amount_str_end()
    {
        return loan_amount_str_end;
    }

    public void setLoan_amount_str_end(String loan_amount_str_end)
    {
        this.loan_amount_str_end = loan_amount_str_end;
    }

    public String getCredit_limit_begin()
    {
        return credit_limit_begin;
    }

    public void setCredit_limit_begin(String credit_limit_begin)
    {
        this.credit_limit_begin = credit_limit_begin;
    }

    public String getCredit_limit_end()
    {
        return credit_limit_end;
    }

    public void setCredit_limit_end(String credit_limit_end)
    {
        this.credit_limit_end = credit_limit_end;
    }

    public String getRepay_status()
    {
        return repay_status;
    }

    public void setRepay_status(String repay_status)
    {
        this.repay_status = repay_status;
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

    public String getBiaozhi()
    {
        return biaozhi;
    }

    public void setBiaozhi(String biaozhi)
    {
        this.biaozhi = biaozhi;
    }

    public String getBegin_time()
    {
        return begin_time;
    }

    public String getEnd_time()
    {
        return end_time;
    }

    public void setBegin_time(String begin_time)
    {
        this.begin_time = begin_time;
    }

    public void setEnd_time(String end_time)
    {
        this.end_time = end_time;
    }

    public String getCustomer_name_history()
    {
        return customer_name_history;
    }

    public String getId_card_history()
    {
        return id_card_history;
    }

    public void setCustomer_name_history(String customer_name_history)
    {
        this.customer_name_history = customer_name_history;
    }

    public void setId_card_history(String id_card_history)
    {
        this.id_card_history = id_card_history;
    }

    public String getBill_code_search()
    {
        return bill_code_search;
    }

    public void setBill_code_search(String bill_code_search)
    {
        this.bill_code_search = bill_code_search;
    }

    public String getBill_type()
    {
        return bill_type;
    }

    public void setBill_type(String bill_type)
    {
        this.bill_type = bill_type;
    }

    public String getIs_page()
    {
        return is_page;
    }

    public void setIs_page(String is_page)
    {
        this.is_page = is_page;
    }

    public String getProtocol_date_begin()
    {
        return protocol_date_begin;
    }

    public void setProtocol_date_begin(String protocol_date_begin)
    {
        this.protocol_date_begin = protocol_date_begin;
    }

    public String getProtocol_date_end()
    {
        return protocol_date_end;
    }

    public void setProtocol_date_end(String protocol_date_end)
    {
        this.protocol_date_end = protocol_date_end;
    }

    public String getNeed_paging_flag()
    {
        return need_paging_flag;
    }

    public void setNeed_paging_flag(String need_paging_flag)
    {
        this.need_paging_flag = need_paging_flag;
    }

    public String getCre_loan_type()
    {
        return cre_loan_type;
    }

    public void setCre_loan_type(String cre_loan_type)
    {
        this.cre_loan_type = cre_loan_type;
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
