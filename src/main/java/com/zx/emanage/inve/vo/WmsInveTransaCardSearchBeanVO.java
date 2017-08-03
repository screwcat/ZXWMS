package com.zx.emanage.inve.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsInveTransaCardSearchBeanVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;
    
    private String  bill_source;//单据来源

    private String cus_name;// 客户姓名

    private String category_name;// 理财产品

    private String id_card;// 身份证号

    private String data_status;// 单据状态

    private String account_status;// 金额状态

    private String create_timestamp_begin;//支付时间起始

    private String create_timestamp_end;//支付时间截止

    private String bill_code;// 协议编号

    private String pos_code;// POS机主键

    private String wms_inve_transa_id;//上单信息表主键

    private String card_owner_name;// 支付表卡主姓名

    private String bank_of_deposit_name;// 银行卡所属银行

    private String mobile_phone;// 电话

    private String salesman_name;// 业务员
    
    private String create_user_name;//录入员
    
    private String date_of_payment_begin;
    
    private String date_of_payment_end;
    
    private String pay_type;
    
    private String menu_id;
    
    private String bel_salesman_id_id;
    
    private String contract_begin;
    
    private String contract_end;
    
    
    public String getBel_salesman_id_id() {
		return bel_salesman_id_id;
	}

	public void setBel_salesman_id_id(String bel_salesman_id_id) {
		this.bel_salesman_id_id = bel_salesman_id_id;
	}

	public String getBill_source() {
		return bill_source;
	}

	public void setBill_source(String bill_source) {
		this.bill_source = bill_source;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getSalesman_name() {
		return salesman_name;
	}

	public void setSalesman_name(String salesman_name) {
		this.salesman_name = salesman_name;
	}

	public String getCus_name()
    {
        return cus_name;
    }

    public void setCus_name(String cus_name)
    {
        this.cus_name = cus_name;
    }

    public String getCategory_name()
    {
        return category_name;
    }

    public void setCategory_name(String category_name)
    {
        this.category_name = category_name;
    }

    public String getId_card()
    {
        return id_card;
    }

    public void setId_card(String id_card)
    {
        this.id_card = id_card;
    }

    public String getData_status()
    {
        return data_status;
    }

    public void setData_status(String data_status)
    {
        this.data_status = data_status;
    }

    public String getAccount_status()
    {
        return account_status;
    }

    public void setAccount_status(String account_status)
    {
        this.account_status = account_status;
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

    public String getWms_inve_transa_id()
    {
        return wms_inve_transa_id;
    }

    public void setWms_inve_transa_id(String wms_inve_transa_id)
    {
        this.wms_inve_transa_id = wms_inve_transa_id;
    }

    public String getBill_code()
    {
        return bill_code;
    }

    public void setBill_code(String bill_code)
    {
        this.bill_code = bill_code;
    }

    public String getPos_code()
    {
        return pos_code;
    }

    public void setPos_code(String pos_code)
    {
        this.pos_code = pos_code;
    }

    public String getBank_of_deposit_name()
    {
        return bank_of_deposit_name;
    }

    public void setBank_of_deposit_name(String bank_of_deposit_name)
    {
        this.bank_of_deposit_name = bank_of_deposit_name;
    }

    public String getCard_owner_name()
    {
        return card_owner_name;
    }

    public void setCard_owner_name(String card_owner_name)
    {
        this.card_owner_name = card_owner_name;
    }

	public String getMobile_phone() {
		return mobile_phone;
	}

	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}

	public String getCreate_user_name() {
		return create_user_name;
	}

	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}

	public String getDate_of_payment_begin() {
		return date_of_payment_begin;
	}

	public void setDate_of_payment_begin(String date_of_payment_begin) {
		this.date_of_payment_begin = date_of_payment_begin;
	}

	public String getDate_of_payment_end() {
		return date_of_payment_end;
	}

	public void setDate_of_payment_end(String date_of_payment_end) {
		this.date_of_payment_end = date_of_payment_end;
	}

	public String getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

    /**
     * @return the contract_begin
     */
    public String getContract_begin()
    {
        return contract_begin;
    }

    /**
     * @param contract_begin the contract_begin to set
     */
    public void setContract_begin(String contract_begin)
    {
        this.contract_begin = contract_begin;
    }

    /**
     * @return the contract_end
     */
    public String getContract_end()
    {
        return contract_end;
    }

    /**
     * @param contract_end the contract_end to set
     */
    public void setContract_end(String contract_end)
    {
        this.contract_end = contract_end;
    }
    
	
}