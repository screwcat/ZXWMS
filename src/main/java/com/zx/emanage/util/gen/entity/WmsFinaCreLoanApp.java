package com.zx.emanage.util.gen.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.zx.sframe.util.mybatis.BaseEntity;

/*
 * @version 2.0
 */

public class WmsFinaCreLoanApp implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_fina_cre_loan_app;

    private Integer wms_cre_credit_head_id;

    private java.math.BigDecimal notarial_fee;

    private java.math.BigDecimal platform_fee;

    private java.math.BigDecimal loan_amount;

    private java.sql.Date loan_date;

    private String loan_date_str;

    private String remark;

    private java.sql.Date create_date;

    private String create_date_str;

    private java.math.BigDecimal deduction_of_interest;

    private java.math.BigDecimal bridge_amount;

    private java.math.BigDecimal b_service_fees;

    private java.math.BigDecimal fees;

    private String fees_detail;

    private java.math.BigDecimal house_area;

    private String loan_amount_caps;

    private String receive_bank;

    private String bank_of_deposit;

    private String card_no;

    private Integer create_user_id;

    private String create_user_name;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String enable_flag;
    //他项费
    private java.math.BigDecimal it_cost_fee;
    //加急费
    private java.math.BigDecimal expedited_fee;
    private java.math.BigDecimal  gps_installation_fee;
    
    
    private String  cre_type_name;
    
    //公正是否完成 0未完成1完成
    private int notary_is_finish;
    
    //他项是否完成 0未完成1完成
    private int he_is_finish;
    
    private Integer  loan_approval_user_id;
    
    private String loan_approval_user_name;
    
    private java.sql.Timestamp loan_approval_timestamp;
    
    private String loan_approval_advice;
    
    private String  loan_approval_result;

    private java.sql.Timestamp notary_is_finish_time;

    private java.math.BigDecimal he_is_amount;

    private java.sql.Timestamp he_is_finish_time;

    private java.math.BigDecimal check_pay;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_fina_cre_loan_app", "enable_flag" };

    private static String[] columnNameArr = { "wms_fina_cre_loan_app", "wms_cre_credit_head_id", "notarial_fee",
                                             "platform_fee", "loan_amount", "loan_date", "loan_date_str", "remark",
                                             "create_date", "create_date_str", "deduction_of_interest",
                                             "bridge_amount", "b_service_fees", "fees", "fees_detail", "house_area",
                                             "loan_amount_caps", "receive_bank", "bank_of_deposit", "card_no",
                                             "create_user_id", "create_user_name", "create_timestamp",
                                             "create_timestamp_str", "last_update_user_id", "last_update_timestamp",
                                             "last_update_timestamp_str", "enable_flag", "it_cost_fee", "expedited_fee" };

    public Integer getWms_fina_cre_loan_app()
    {
        return wms_fina_cre_loan_app;
    }

    public void setWms_fina_cre_loan_app(Integer obj)
    {
        wms_fina_cre_loan_app = obj;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
    }

    public java.math.BigDecimal getNotarial_fee()
    {
        return notarial_fee;
    }

    public void setNotarial_fee(java.math.BigDecimal obj)
    {
        notarial_fee = obj;
    }

    public java.math.BigDecimal getPlatform_fee()
    {
        return platform_fee;
    }

    public void setPlatform_fee(java.math.BigDecimal obj)
    {
        platform_fee = obj;
    }

    public java.math.BigDecimal getLoan_amount()
    {
        return loan_amount;
    }

    public void setLoan_amount(java.math.BigDecimal obj)
    {
        loan_amount = obj;
    }

    public java.sql.Date getLoan_date()
    {
        return loan_date;
    }

    public void setLoan_date(java.sql.Date obj)
    {
        loan_date = obj;
    }

    public String getLoan_dateStr()
    {
        return loan_date_str;
    }

    public void setLoan_dateStr(String obj)
    {
        loan_date_str = obj;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String obj)
    {
        remark = obj;
    }

    public java.sql.Date getCreate_date()
    {
        return create_date;
    }

    public void setCreate_date(java.sql.Date obj)
    {
        create_date = obj;
    }

    public String getCreate_dateStr()
    {
        return create_date_str;
    }

    public void setCreate_dateStr(String obj)
    {
        create_date_str = obj;
    }

    public java.math.BigDecimal getDeduction_of_interest()
    {
        return deduction_of_interest;
    }

    public void setDeduction_of_interest(java.math.BigDecimal obj)
    {
        deduction_of_interest = obj;
    }

    public java.math.BigDecimal getBridge_amount()
    {
        return bridge_amount;
    }

    public void setBridge_amount(java.math.BigDecimal obj)
    {
        bridge_amount = obj;
    }

    public java.math.BigDecimal getB_service_fees()
    {
        return b_service_fees;
    }

    public void setB_service_fees(java.math.BigDecimal obj)
    {
        b_service_fees = obj;
    }

    public java.math.BigDecimal getFees()
    {
        return fees;
    }

    public void setFees(java.math.BigDecimal obj)
    {
        fees = obj;
    }

    public String getFees_detail()
    {
        return fees_detail;
    }

    public void setFees_detail(String obj)
    {
        fees_detail = obj;
    }

    public java.math.BigDecimal getHouse_area()
    {
        return house_area;
    }

    public void setHouse_area(java.math.BigDecimal obj)
    {
        house_area = obj;
    }

    public String getLoan_amount_caps()
    {
        return loan_amount_caps;
    }

    public void setLoan_amount_caps(String obj)
    {
        loan_amount_caps = obj;
    }

    public String getReceive_bank()
    {
        return receive_bank;
    }

    public void setReceive_bank(String obj)
    {
        receive_bank = obj;
    }

    public String getBank_of_deposit()
    {
        return bank_of_deposit;
    }

    public void setBank_of_deposit(String obj)
    {
        bank_of_deposit = obj;
    }

    public String getCard_no()
    {
        return card_no;
    }

    public void setCard_no(String obj)
    {
        card_no = obj;
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

    public java.sql.Timestamp getCreate_timestamp()
    {
        return create_timestamp;
    }

    public void setCreate_timestamp(java.sql.Timestamp obj)
    {
        create_timestamp = obj;
    }

    public String getCreate_timestampStr()
    {
        return create_timestamp_str;
    }

    public void setCreate_timestampStr(String obj)
    {
        create_timestamp_str = obj;
    }

    public Integer getLast_update_user_id()
    {
        return last_update_user_id;
    }

    public void setLast_update_user_id(Integer obj)
    {
        last_update_user_id = obj;
    }

    public java.sql.Timestamp getLast_update_timestamp()
    {
        return last_update_timestamp;
    }

    public void setLast_update_timestamp(java.sql.Timestamp obj)
    {
        last_update_timestamp = obj;
    }

    public String getLast_update_timestampStr()
    {
        return last_update_timestamp_str;
    }

    public void setLast_update_timestampStr(String obj)
    {
        last_update_timestamp_str = obj;
    }

    public String getEnable_flag()
    {
        return enable_flag;
    }

    public void setEnable_flag(String obj)
    {
        enable_flag = obj;
    }
    
    public java.math.BigDecimal getIt_cost_fee() {
		return it_cost_fee;
	}

	public void setIt_cost_fee(java.math.BigDecimal it_cost_fee) {
		this.it_cost_fee = it_cost_fee;
	}

	public java.math.BigDecimal getExpedited_fee() {
		return expedited_fee;
	}

	public void setExpedited_fee(java.math.BigDecimal expedited_fee) {
		this.expedited_fee = expedited_fee;
	}

	public java.math.BigDecimal getGps_installation_fee() {
		return gps_installation_fee;
	}

	public void setGps_installation_fee(java.math.BigDecimal gps_installation_fee) {
		this.gps_installation_fee = gps_installation_fee;
	}

	public String getCre_type_name() {
		return cre_type_name;
	}

	public void setCre_type_name(String cre_type_name) {
		this.cre_type_name = cre_type_name;
	}
	

	public int getNotary_is_finish() {
        return notary_is_finish;
    }

    public void setNotary_is_finish(int notary_is_finish) {
        this.notary_is_finish = notary_is_finish;
    }

    public int getHe_is_finish() {
        return he_is_finish;
    }

    public void setHe_is_finish(int he_is_finish) {
        this.he_is_finish = he_is_finish;
    }
    
    

    public Integer getLoan_approval_user_id() {
		return loan_approval_user_id;
	}

	public String getLoan_approval_user_name() {
		return loan_approval_user_name;
	}

	public java.sql.Timestamp getLoan_approval_timestamp() {
		return loan_approval_timestamp;
	}

	public String getLoan_approval_advice() {
		return loan_approval_advice;
	}

	public void setLoan_approval_user_id(Integer loan_approval_user_id) {
		this.loan_approval_user_id = loan_approval_user_id;
	}

	public void setLoan_approval_user_name(String loan_approval_user_name) {
		this.loan_approval_user_name = loan_approval_user_name;
	}

	public void setLoan_approval_timestamp(
			java.sql.Timestamp loan_approval_timestamp) {
		this.loan_approval_timestamp = loan_approval_timestamp;
	}

	public void setLoan_approval_advice(String loan_approval_advice) {
		this.loan_approval_advice = loan_approval_advice;
	}

	public String getLoan_approval_result() {
		return loan_approval_result;
	}

	public void setLoan_approval_result(String loan_approval_result) {
		this.loan_approval_result = loan_approval_result;
	}

    public java.sql.Timestamp getNotary_is_finish_time()
    {
        return notary_is_finish_time;
    }

    public void setNotary_is_finish_time(java.sql.Timestamp notary_is_finish_time)
    {
        this.notary_is_finish_time = notary_is_finish_time;
    }

    public java.math.BigDecimal getHe_is_amount()
    {
        return he_is_amount;
    }

    public void setHe_is_amount(java.math.BigDecimal he_is_amount)
    {
        this.he_is_amount = he_is_amount;
    }

    public java.sql.Timestamp getHe_is_finish_time()
    {
        return he_is_finish_time;
    }

    public void setHe_is_finish_time(java.sql.Timestamp he_is_finish_time)
    {
        this.he_is_finish_time = he_is_finish_time;
    }

    public java.math.BigDecimal getCheck_pay()
    {
        return check_pay;
    }

    public void setCheck_pay(java.math.BigDecimal check_pay)
    {
        this.check_pay = check_pay;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_fina_cre_loan_app", this.wms_fina_cre_loan_app);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("notarial_fee", this.notarial_fee);
        paramMap.put("platform_fee", this.platform_fee);
        paramMap.put("loan_amount", this.loan_amount);
        paramMap.put("loan_date", this.loan_date);
        paramMap.put("loan_date_str", this.loan_date_str);
        paramMap.put("remark", this.remark);
        paramMap.put("create_date", this.create_date);
        paramMap.put("create_date_str", this.create_date_str);
        paramMap.put("deduction_of_interest", this.deduction_of_interest);
        paramMap.put("bridge_amount", this.bridge_amount);
        paramMap.put("b_service_fees", this.b_service_fees);
        paramMap.put("fees", this.fees);
        paramMap.put("fees_detail", this.fees_detail);
        paramMap.put("house_area", this.house_area);
        paramMap.put("loan_amount_caps", this.loan_amount_caps);
        paramMap.put("receive_bank", this.receive_bank);
        paramMap.put("bank_of_deposit", this.bank_of_deposit);
        paramMap.put("card_no", this.card_no);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("enable_flag", this.enable_flag);
        paramMap.put("it_cost_fee", this.it_cost_fee);
        paramMap.put("expedited_fee", this.expedited_fee);
    }

    /**
     * return the columns map
     */
    public Map<String, Object> getInfoMap()
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        this.putInMap(paramMap);
        return paramMap;
    }

    /**
     * remove default value and pk if it is null
     */
    private Map<String, Object> dealWithMap(Map<String, Object> paramMap)
    {
        Set<String> set = new HashSet<String>();
        for (String colName : defaultValColArr)
        {
            set.add(colName);
        }
        for (String colName : pkColArr)
        {
            set.add(colName);
        }
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext())
        {
            String colName = iterator.next();
            if (paramMap.get(colName) == null)
            {
                paramMap.remove(colName);
            }
        }
        return paramMap;
    }
}