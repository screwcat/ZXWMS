package com.zx.emanage.util.gen.entity;

import com.zx.sframe.util.mybatis.BaseEntity;

/*
 * @version 2.0
 */

public class WmsInveClerkProtocol implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_clerk_protocol_id;
	
	private String prot_code;
	
	private Integer wms_inve_transa_id;
	
    private Integer wms_inve_redeem_id;

    private String protocol_type;

	private String is_order_extend;
	
	private String sign_place;
	
    private String sign_place_postcode;

	private java.sql.Timestamp begin_of_date;
	
	private String begin_of_date_str;
	
	private String a_name;
	
	private String a_id_card;
	
    private String a_contact_address;

	private String a_bank_number;
	
	private String a_bank_info;
	
	private java.math.BigDecimal product_account;
	
	private String product_account_upper;
	
	private String category_name;
	
	private String product_deadline;
	
	private String a_data;
	
	private String b_address;
	
	private String b_name;
	
	private java.sql.Timestamp end_of_date;
	
	private String end_of_date_str;
	
	private String expect_interest;
	
	private java.sql.Timestamp act_end_of_date;
	
	private String act_end_of_date_str;
	
	private Integer create_user_id;
	
	private String create_user_name;
	
	private Integer create_user_dept_id;
	
	private java.sql.Timestamp create_timestamp;
	
	private String create_timestamp_str;
	
	private Integer last_update_user_id;
	
	private java.sql.Timestamp last_update_timestamp;
	
	private String last_update_timestamp_str;
	
	private String enable_flag;
	
    private String finnished_type;
	
    private String main_protocol_version;

    private String sub_protocol_version;

    private String inter_protocol_version;

    private String inter_protocol_merge_version;

    private String m2m_protocol_version;

    private String bel_salesman_id;

    private String prot_type;

    private String customer_signature_path;
  
	public Integer getWms_inve_clerk_protocol_id () {
		return wms_inve_clerk_protocol_id;
	}
	
	public void setWms_inve_clerk_protocol_id (Integer obj) {
		wms_inve_clerk_protocol_id = obj;
	}
	
	public String getProt_code () {
		return prot_code;
	}
	
	public void setProt_code (String obj) {
		prot_code = obj;
	}
	
	public Integer getWms_inve_transa_id () {
		return wms_inve_transa_id;
	}
	
	public void setWms_inve_transa_id (Integer obj) {
		wms_inve_transa_id = obj;
	}
	
    public Integer getWms_inve_redeem_id()
    {
        return wms_inve_redeem_id;
    }

    public void setWms_inve_redeem_id(Integer wms_inve_redeem_id)
    {
        this.wms_inve_redeem_id = wms_inve_redeem_id;
    }

    public String getProtocol_type()
    {
        return protocol_type;
    }

    public void setProtocol_type(String protocol_type)
    {
        this.protocol_type = protocol_type;
    }

    public String getIs_order_extend()
    {
		return is_order_extend;
	}
	
	public void setIs_order_extend (String obj) {
		is_order_extend = obj;
	}
	
	public String getSign_place () {
		return sign_place;
	}
	
	public void setSign_place (String obj) {
		sign_place = obj;
	}
	
    public String getSign_place_postcode()
    {
        return sign_place_postcode;
    }

    public void setSign_place_postcode(String sign_place_postcode)
    {
        this.sign_place_postcode = sign_place_postcode;
    }

    public java.sql.Timestamp getBegin_of_date()
    {
		return begin_of_date;
	}
	
	public void setBegin_of_date (java.sql.Timestamp obj) {
		begin_of_date = obj;
	}
	
	public String getBegin_of_date_str () {
		return begin_of_date_str;
	}
	
	public void setBegin_of_date_str (String obj) {
		begin_of_date_str = obj;
	}
	
	public String getA_name () {
		return a_name;
	}
	
	public void setA_name (String obj) {
		a_name = obj;
	}
	
	public String getA_id_card () {
		return a_id_card;
	}
	
	public void setA_id_card (String obj) {
		a_id_card = obj;
	}
	
    public String getA_contact_address()
    {
        return a_contact_address;
    }

    public void setA_contact_address(String a_contact_address)
    {
        this.a_contact_address = a_contact_address;
    }

    public String getA_bank_number()
    {
		return a_bank_number;
	}
	
	public void setA_bank_number (String obj) {
		a_bank_number = obj;
	}
	
	public String getA_bank_info () {
		return a_bank_info;
	}
	
	public void setA_bank_info (String obj) {
		a_bank_info = obj;
	}
	
	public java.math.BigDecimal getProduct_account () {
		return product_account;
	}
	
	public void setProduct_account (java.math.BigDecimal obj) {
		product_account = obj;
	}
	
	public String getProduct_account_upper () {
		return product_account_upper;
	}
	
	public void setProduct_account_upper (String obj) {
		product_account_upper = obj;
	}
	
	public String getCategory_name () {
		return category_name;
	}
	
	public void setCategory_name (String obj) {
		category_name = obj;
	}
	
	public String getProduct_deadline () {
		return product_deadline;
	}
	
	public void setProduct_deadline (String obj) {
		product_deadline = obj;
	}
	
	public String getA_data () {
		return a_data;
	}
	
	public void setA_data (String obj) {
		a_data = obj;
	}
	
	public String getB_address () {
		return b_address;
	}
	
	public void setB_address (String obj) {
		b_address = obj;
	}
	
	public String getB_name () {
		return b_name;
	}
	
	public void setB_name (String obj) {
		b_name = obj;
	}
	
	public java.sql.Timestamp getEnd_of_date () {
		return end_of_date;
	}
	
	public void setEnd_of_date (java.sql.Timestamp obj) {
		end_of_date = obj;
	}
	
	public String getEnd_of_date_str () {
		return end_of_date_str;
	}
	
	public void setEnd_of_date_str (String obj) {
		end_of_date_str = obj;
	}
	
	public String getExpect_interest () {
		return expect_interest;
	}
	
	public void setExpect_interest (String obj) {
		expect_interest = obj;
	}
	
	public java.sql.Timestamp getAct_end_of_date () {
		return act_end_of_date;
	}
	
	public void setAct_end_of_date (java.sql.Timestamp obj) {
		act_end_of_date = obj;
	}
	
	public String getAct_end_of_date_str () {
		return act_end_of_date_str;
	}
	
	public void setAct_end_of_date_str (String obj) {
		act_end_of_date_str = obj;
	}
	
	public Integer getCreate_user_id () {
		return create_user_id;
	}
	
	public void setCreate_user_id (Integer obj) {
		create_user_id = obj;
	}
	
	public String getCreate_user_name () {
		return create_user_name;
	}
	
	public void setCreate_user_name (String obj) {
		create_user_name = obj;
	}
	
	public Integer getCreate_user_dept_id () {
		return create_user_dept_id;
	}
	
	public void setCreate_user_dept_id (Integer obj) {
		create_user_dept_id = obj;
	}
	
	public java.sql.Timestamp getCreate_timestamp () {
		return create_timestamp;
	}
	
	public void setCreate_timestamp (java.sql.Timestamp obj) {
		create_timestamp = obj;
	}
	
	public String getCreate_timestamp_str () {
		return create_timestamp_str;
	}
	
	public void setCreate_timestamp_str (String obj) {
		create_timestamp_str = obj;
	}
	
	public Integer getLast_update_user_id () {
		return last_update_user_id;
	}
	
	public void setLast_update_user_id (Integer obj) {
		last_update_user_id = obj;
	}
	
	public java.sql.Timestamp getLast_update_timestamp () {
		return last_update_timestamp;
	}
	
	public void setLast_update_timestamp (java.sql.Timestamp obj) {
		last_update_timestamp = obj;
	}
	
	public String getLast_update_timestamp_str () {
		return last_update_timestamp_str;
	}
	
	public void setLast_update_timestamp_str (String obj) {
		last_update_timestamp_str = obj;
	}
	
	public String getEnable_flag () {
		return enable_flag;
	}
	
	public void setEnable_flag (String obj) {
		enable_flag = obj;
	}

    public String getFinnished_type()
    {
        return finnished_type;
    }

    public void setFinnished_type(String finnished_type)
    {
        this.finnished_type = finnished_type;
    }

    public String getMain_protocol_version()
    {
        return main_protocol_version;
    }

    public void setMain_protocol_version(String main_protocol_version)
    {
        this.main_protocol_version = main_protocol_version;
    }

    public String getSub_protocol_version()
    {
        return sub_protocol_version;
    }

    public void setSub_protocol_version(String sub_protocol_version)
    {
        this.sub_protocol_version = sub_protocol_version;
    }

    public String getInter_protocol_version()
    {
        return inter_protocol_version;
    }

    public void setInter_protocol_version(String inter_protocol_version)
    {
        this.inter_protocol_version = inter_protocol_version;
    }

    public String getInter_protocol_merge_version()
    {
        return inter_protocol_merge_version;
    }

    public void setInter_protocol_merge_version(String inter_protocol_merge_version)
    {
        this.inter_protocol_merge_version = inter_protocol_merge_version;
    }

    public String getM2m_protocol_version()
    {
        return m2m_protocol_version;
    }

    public void setM2m_protocol_version(String m2m_protocol_version)
    {
        this.m2m_protocol_version = m2m_protocol_version;
    }

    public String getBel_salesman_id()
    {
        return bel_salesman_id;
    }

    public void setBel_salesman_id(String bel_salesman_id)
    {
        this.bel_salesman_id = bel_salesman_id;
    }

    public String getProt_type()
    {
        return prot_type;
    }

    public void setProt_type(String prot_type)
    {
        this.prot_type = prot_type;
    }

    public String getCustomer_signature_path()
    {
        return customer_signature_path;
    }

    public void setCustomer_signature_path(String customer_signature_path)
    {
        this.customer_signature_path = customer_signature_path;
    }
}