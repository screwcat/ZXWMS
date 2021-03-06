package com.zx.emanage.cremanage.vo;

import com.zx.platform.syscontext.vo.GridParamBean;


public class WmsApplyInfoSearchBeanVO extends GridParamBean{

    private static final long serialVersionUID = 1L;
    
    private Integer wms_cre_credit_head_id;// 单据主键

    private String customer_name;//客户姓名
    private String customer_age;//年龄
    private String mobile_telephone1;//联系电话
    private String city;//城市
    private double credit_limit;//借款额度
    private Integer max_repayment_time_limit;//贷款期限
    private String community_name;//小区名称
    private String houses_number;//名下几处房产
    private String house_type;//房产类型
    private double house_building_area;// 建筑面积
    private String house_address_more;//抵押房产地址
    
    //是否复式   
    private String is_compound;
    //购买时间   
    private String house_buy_date;
    //房屋房龄   
    private String house_age;
    //备注
    private String house_remark;
    //抵押标识 1一拆 2二拆
    private String mort_flag;
    
	public Integer getWms_cre_credit_head_id() {
		return wms_cre_credit_head_id;
	}
	public void setWms_cre_credit_head_id(Integer wms_cre_credit_head_id) {
		this.wms_cre_credit_head_id = wms_cre_credit_head_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_age() {
		return customer_age;
	}
	public void setCustomer_age(String customer_age) {
		this.customer_age = customer_age;
	}
	public String getMobile_telephone1() {
		return mobile_telephone1;
	}
	public void setMobile_telephone1(String mobile_telephone1) {
		this.mobile_telephone1 = mobile_telephone1;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getCredit_limit() {
		return credit_limit;
	}
	public void setCredit_limit(double credit_limit) {
		this.credit_limit = credit_limit;
	}
	public Integer getMax_repayment_time_limit() {
		return max_repayment_time_limit;
	}
	public void setMax_repayment_time_limit(Integer max_repayment_time_limit) {
		this.max_repayment_time_limit = max_repayment_time_limit;
	}
	public String getCommunity_name() {
		return community_name;
	}
	public void setCommunity_name(String community_name) {
		this.community_name = community_name;
	}
	public String getHouses_number() {
		return houses_number;
	}
	public void setHouses_number(String houses_number) {
		this.houses_number = houses_number;
	}
	public String getHouse_type() {
		return house_type;
	}
	public void setHouse_type(String house_type) {
		this.house_type = house_type;
	}
	public double getHouse_building_area() {
		return house_building_area;
	}
	public void setHouse_building_area(double house_building_area) {
		this.house_building_area = house_building_area;
	}
	public String getHouse_address_more() {
		return house_address_more;
	}
	public void setHouse_address_more(String house_address_more) {
		this.house_address_more = house_address_more;
	}
    public String getIs_compound() {
        return is_compound;
    }
    public void setIs_compound(String is_compound) {
        this.is_compound = is_compound;
    }
    public String getHouse_buy_date() {
        return house_buy_date;
    }
    public void setHouse_buy_date(String house_buy_date) {
        this.house_buy_date = house_buy_date;
    }
    public String getHouse_age() {
        return house_age;
    }
    public void setHouse_age(String house_age) {
        this.house_age = house_age;
    }
    public String getHouse_remark() {
        return house_remark;
    }
    public void setHouse_remark(String house_remark) {
        this.house_remark = house_remark;
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
