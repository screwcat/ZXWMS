package com.zx.emanage.remind.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreCreditNotaryWarnSearchBeanVO
 * 模块名称：
 * @Description: 内容摘要：
 * @author admin
 * @date 2016年12月7日
 * @version V1.0
 * history:
 * 1、2016年12月7日 admin 创建文件
 */
public class WmsCreCreditNotaryWarnSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;

    /** 单据编号 **/
	private String bill_code;
	
    /** 业务员姓名/短工号 **/
	private String salesman_name_str;

    /** 申请时间(起始) **/
	private String appl_date_begin;

    /** 申请时间(终止) **/
	private String appl_date_end;

    /** 客户姓名 **/
	private String customer_name;
	
	/** 客户姓名/共贷人姓名 **/
    private String customer_name_all;

    /** 联系电话 **/
	private String mobile_telephone;

    /** 到期时间(起始) **/
	private String end_time_begin;

    /** 到期时间(终止) **/
	private String end_time_end1;
	
	/** 到期时间 **/
	private String end_time;
	
    /** 单据状态 **/
	private String bill_status;
	
    /** 单据状态(不等于) **/
	private String bill_status_ne;
	
    /** 导入数据结果 **/
	private String result;
	
    /** 格式错误行数 **/
	private int err_num;
	
    /** 返回标志 成功：true 失败：false */
	private String flag;

    private String is_show_now;// 根据当前参数判断是否显示当前日期的数据
	
    /** 是否分页标识  0：不需要分页  1或空：不需要分页 **/
	private String need_paging_flag;
	
    /** 还款日期 **/
	private String current_repay_date;
	
    /** 产品id **/
	private String product_id;
	
    /** 单据状态in：逗号分隔 **/
	private String bill_status_arr;

	
    /** 生日日期 **/
	private String birthday_date;

    /** 还款状态 **/
	private String repay_status_ne;

    /** 产品名称 **/
	private String category_name;
	
    /** 产品id **/
	private String category_id;
	
    /** 还款状态 **/
    private String repay_status;
    
    /** 还款状态in：逗号分隔**/
    private String repay_status_arr;

    /** 申请时间开始 **/
    // private String create_timestamp_begin;
    //
    // /** 申请时间结束 **/
    // private String create_timestamp_end;

    /** 身份证号 **/
    private String id_card;
    
    /** 身份证号/共贷人身份证号 **/
    private String id_card_all;

    /** 团队经理 **/
    private String team_manager_name;

    /** 借款金额 开始**/
    private Integer appro_limit_str;

    /** 借款金额 结束**/
    private Integer appro_limit_end;

    /** 所属营业部 **/
    private String sales_epartment_name;

    /** 合同日期 开始**/
    private String protocol_date_begin;

    /** 合同日期 结束 **/
    private String protocol_date_end;

    /** 终止还款日期 开始**/
    private String borrow_end_date_begin;

    /** 终止还款日期 结束 **/
    private String borrow_end_date_end;

    /** 月还款 开始**/
    private String refund_limit_month_str;

    /** 月还款 结束 **/
    private String refund_limit_month_end;

    /** 打卡金额 开始**/
    private String loan_amount_str;

    /** 打卡金额 结束 **/
    private String loan_amount_end;

    /** 期数 **/
    private String borrow_deadline;

    /** 合同号 **/
    private String protocol_id_year_num;

    /** 业务状态--单据类型 **/
    private String bill_type;

    /** 次数 **/
    private String the_number;

    /** 合同金额 开始**/
    private String principal_lower_str;

    /** 合同金额 结束 **/
    private String principal_lower_end;

    /** 抵押状态 **/
    private String mort_flag;

	public String getBill_code() {
		return bill_code;
	}

	public void setBill_code(String bill_code) {
		this.bill_code = bill_code;
	}

	public String getSalesman_name_str() {
		return salesman_name_str;
	}

	public void setSalesman_name_str(String salesman_name_str) {
		this.salesman_name_str = salesman_name_str;
	}

	public String getAppl_date_begin() {
		return appl_date_begin;
	}

	public void setAppl_date_begin(String appl_date_begin) {
		this.appl_date_begin = appl_date_begin;
	}

	public String getAppl_date_end() {
		return appl_date_end;
	}

	public void setAppl_date_end(String appl_date_end) {
		this.appl_date_end = appl_date_end;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getMobile_telephone() {
		return mobile_telephone;
	}

	public void setMobile_telephone(String mobile_telephone) {
		this.mobile_telephone = mobile_telephone;
	}

	public String getEnd_time_begin() {
		return end_time_begin;
	}

	public void setEnd_time_begin(String end_time_begin) {
		this.end_time_begin = end_time_begin;
	}

	public String getEnd_time_end() {
		return end_time_end1;
	}

	public void setEnd_time_end(String end_time_end) {
		this.end_time_end1 = end_time_end;
	}

	public String getEnd_time_end1() {
		return end_time_end1;
	}

	public void setEnd_time_end1(String end_time_end1) {
		this.end_time_end1 = end_time_end1;
	}

	public String getBill_status() {
		return bill_status;
	}

	public void setBill_status(String bill_status) {
		this.bill_status = bill_status;
	}

	public String getBill_status_ne() {
		return bill_status_ne;
	}

	public void setBill_status_ne(String bill_status_ne) {
		this.bill_status_ne = bill_status_ne;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getErr_num() {
		return err_num;
	}

	public void setErr_num(int err_num) {
		this.err_num = err_num;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getIs_show_now() {
		return is_show_now;
	}

	public void setIs_show_now(String is_show_now) {
		this.is_show_now = is_show_now;
	}

	public String getNeed_paging_flag() {
		return need_paging_flag;
	}

	public void setNeed_paging_flag(String need_paging_flag) {
		this.need_paging_flag = need_paging_flag;
	}

	public String getCurrent_repay_date() {
		return current_repay_date;
	}

	public void setCurrent_repay_date(String current_repay_date) {
		this.current_repay_date = current_repay_date;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getBill_status_arr() {
		return bill_status_arr;
	}

	public void setBill_status_arr(String bill_status_arr) {
		this.bill_status_arr = bill_status_arr;
	}

	public String getBirthday_date() {
		return birthday_date;
	}

	public void setBirthday_date(String birthday_date) {
		this.birthday_date = birthday_date;
	}

	public String getRepay_status_ne() {
		return repay_status_ne;
	}

	public void setRepay_status_ne(String repay_status_ne) {
		this.repay_status_ne = repay_status_ne;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

    public String getRepay_status()
    {
        return repay_status;
    }

    public void setRepay_status(String repay_status)
    {
        this.repay_status = repay_status;
    }

    public String getRepay_status_arr()
    {
        return repay_status_arr;
    }

    public void setRepay_status_arr(String repay_status_arr)
    {
        this.repay_status_arr = repay_status_arr;
    }


    public String getId_card()
    {
        return id_card;
    }

    public String getTeam_manager_name()
    {
        return team_manager_name;
    }


    public void setId_card(String id_card)
    {
        this.id_card = id_card;
    }

    public void setTeam_manager_name(String team_manager_name)
    {
        this.team_manager_name = team_manager_name;
    }

    public String getSales_epartment_name()
    {
        return sales_epartment_name;
    }

    public void setSales_epartment_name(String sales_epartment_name)
    {
        this.sales_epartment_name = sales_epartment_name;
    }

    public Integer getAppro_limit_str()
    {
        return appro_limit_str;
    }

    public Integer getAppro_limit_end()
    {
        return appro_limit_end;
    }

    public void setAppro_limit_str(Integer appro_limit_str)
    {
        this.appro_limit_str = appro_limit_str;
    }

    public void setAppro_limit_end(Integer appro_limit_end)
    {
        this.appro_limit_end = appro_limit_end;
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

    public String getRefund_limit_month_str()
    {
        return refund_limit_month_str;
    }

    public void setRefund_limit_month_str(String refund_limit_month_str)
    {
        this.refund_limit_month_str = refund_limit_month_str;
    }

    public String getRefund_limit_month_end()
    {
        return refund_limit_month_end;
    }

    public void setRefund_limit_month_end(String refund_limit_month_end)
    {
        this.refund_limit_month_end = refund_limit_month_end;
    }

    public String getLoan_amount_str()
    {
        return loan_amount_str;
    }

    public void setLoan_amount_str(String loan_amount_str)
    {
        this.loan_amount_str = loan_amount_str;
    }

    public String getLoan_amount_end()
    {
        return loan_amount_end;
    }

    public void setLoan_amount_end(String loan_amount_end)
    {
        this.loan_amount_end = loan_amount_end;
    }

    public String getBorrow_deadline()
    {
        return borrow_deadline;
    }

    public void setBorrow_deadline(String borrow_deadline)
    {
        this.borrow_deadline = borrow_deadline;
    }

    public String getProtocol_id_year_num()
    {
        return protocol_id_year_num;
    }

    public void setProtocol_id_year_num(String protocol_id_year_num)
    {
        this.protocol_id_year_num = protocol_id_year_num;
    }

    public String getBill_type()
    {
        return bill_type;
    }

    public void setBill_type(String bill_type)
    {
        this.bill_type = bill_type;
    }

    public String getThe_number()
    {
        return the_number;
    }

    public void setThe_number(String the_number)
    {
        this.the_number = the_number;
    }

    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    public String getBorrow_end_date_begin()
    {
        return borrow_end_date_begin;
    }

    public void setBorrow_end_date_begin(String borrow_end_date_begin)
    {
        this.borrow_end_date_begin = borrow_end_date_begin;
    }

    public String getBorrow_end_date_end()
    {
        return borrow_end_date_end;
    }

    public void setBorrow_end_date_end(String borrow_end_date_end)
    {
        this.borrow_end_date_end = borrow_end_date_end;
    }

    public String getPrincipal_lower_str()
    {
        return principal_lower_str;
    }

    public void setPrincipal_lower_str(String principal_lower_str)
    {
        this.principal_lower_str = principal_lower_str;
    }

    public String getPrincipal_lower_end()
    {
        return principal_lower_end;
    }

    public void setPrincipal_lower_end(String principal_lower_end)
    {
        this.principal_lower_end = principal_lower_end;
    }

    public String getCustomer_name_all()
    {
        return customer_name_all;
    }

    public void setCustomer_name_all(String customer_name_all)
    {
        this.customer_name_all = customer_name_all;
    }

    public String getId_card_all()
    {
        return id_card_all;
    }

    public void setId_card_all(String id_card_all)
    {
        this.id_card_all = id_card_all;
    }

    public String getEnd_time()
    {
        return end_time;
    }

    public void setEnd_time(String end_time)
    {
        this.end_time = end_time;
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