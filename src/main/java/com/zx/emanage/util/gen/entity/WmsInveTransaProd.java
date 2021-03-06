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

public class WmsInveTransaProd implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_inve_transa_prod_id;

    private Integer wms_inve_transa_id;

    private Integer wms_inve_pruduct_category_id;

    private String category_name;

    private Integer product_deadline;

    private String product_interest;

    private java.math.BigDecimal product_account;

    private String is_finish;

    private Integer create_user_id;

    private String create_user_name;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String enable_flag;

    private String card_owner_name;

    private String bank_of_deposit;

    private String card_no;

    private String product_deadline_name;

    private java.math.BigDecimal reward_interest;

    private java.math.BigDecimal expect_interest_account;

    private Integer wms_inve_pruduct_deadline_id;

    private java.math.BigDecimal org_product_account;

    private boolean isExcludePKFlag;

    private String bank_of_deposit_pro;

    private String bank_of_deposit_city;

    private String bank_branch;

    private String category_code;

    private String category_type;

    private Integer is_allopatry;// 是否匹配异地债权

    private String is_protocol_finish;

    private String is_customer_confirmation;

    private String redeem_sms_code;

    private String category_rebate_way;

    private String bank_of_deposit_code;

    private String bank_of_deposit_pro_code;

    private String bank_of_deposit_city_code;

    private Integer wms_inve_customer_card_id;

    private Integer buy_month_limit;

    private String has_paper_protocol;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_inve_transa_prod_id" };

    private static String[] columnNameArr = { "wms_inve_transa_prod_id", "wms_inve_transa_id", "wms_inve_pruduct_category_id", "category_name", "product_deadline", "product_interest", "product_account", "is_finish", "create_user_id", "create_user_name", "create_timestamp", "create_timestamp_str", "last_update_user_id", "last_update_timestamp", "last_update_timestamp_str", "enable_flag", "card_owner_name", "bank_of_deposit", "card_no", "product_deadline_name", "reward_interest", "expect_interest_account", "wms_inve_pruduct_deadline_id", "org_product_account", "isExcludePKFlag", "bank_of_deposit_pro", "bank_of_deposit_city", "bank_branch", "category_code", "category_type", "category_rebate_way", "wms_inve_customer_card_id" };

    public Integer getWms_inve_transa_prod_id()
    {
        return wms_inve_transa_prod_id;
    }

    public void setWms_inve_transa_prod_id(Integer obj)
    {
        wms_inve_transa_prod_id = obj;
    }

    public Integer getWms_inve_transa_id()
    {
        return wms_inve_transa_id;
    }

    public void setWms_inve_transa_id(Integer obj)
    {
        wms_inve_transa_id = obj;
    }

    public Integer getWms_inve_pruduct_category_id()
    {
        return wms_inve_pruduct_category_id;
    }

    public void setWms_inve_pruduct_category_id(Integer obj)
    {
        wms_inve_pruduct_category_id = obj;
    }

    public String getCategory_name()
    {
        return category_name;
    }

    public void setCategory_name(String obj)
    {
        category_name = obj;
    }

    public Integer getProduct_deadline()
    {
        return product_deadline;
    }

    public void setProduct_deadline(Integer obj)
    {
        product_deadline = obj;
    }

    public String getProduct_interest()
    {
        return product_interest;
    }

    public void setProduct_interest(String obj)
    {
        product_interest = obj;
    }

    public java.math.BigDecimal getProduct_account()
    {
        return product_account;
    }

    public void setProduct_account(java.math.BigDecimal obj)
    {
        product_account = obj;
    }

    public String getIs_finish()
    {
        return is_finish;
    }

    public void setIs_finish(String obj)
    {
        is_finish = obj;
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

    public String getCreate_timestamp_str()
    {
        return create_timestamp_str;
    }

    public void setCreate_timestamp_str(String obj)
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

    public String getLast_update_timestamp_str()
    {
        return last_update_timestamp_str;
    }

    public void setLast_update_timestamp_str(String obj)
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

    public String getCard_owner_name()
    {
        return card_owner_name;
    }

    public void setCard_owner_name(String obj)
    {
        card_owner_name = obj;
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

    public String getProduct_deadline_name()
    {
        return product_deadline_name;
    }

    public void setProduct_deadline_name(String obj)
    {
        product_deadline_name = obj;
    }

    public java.math.BigDecimal getReward_interest()
    {
        return reward_interest;
    }

    public void setReward_interest(java.math.BigDecimal obj)
    {
        reward_interest = obj;
    }

    public java.math.BigDecimal getExpect_interest_account()
    {
        return expect_interest_account;
    }

    public void setExpect_interest_account(java.math.BigDecimal obj)
    {
        expect_interest_account = obj;
    }

    public Integer getWms_inve_pruduct_deadline_id()
    {
        return wms_inve_pruduct_deadline_id;
    }

    public void setWms_inve_pruduct_deadline_id(Integer obj)
    {
        wms_inve_pruduct_deadline_id = obj;
    }

    public java.math.BigDecimal getOrg_product_account()
    {
        return org_product_account;
    }

    public void setOrg_product_account(java.math.BigDecimal obj)
    {
        org_product_account = obj;
    }

    public boolean getgetIsExcludePKFlag()
    {
        return isExcludePKFlag;
    }

    public void setgetIsExcludePKFlag(boolean obj)
    {
        isExcludePKFlag = obj;
    }

    public String getBank_of_deposit_pro()
    {
        return bank_of_deposit_pro;
    }

    public void setBank_of_deposit_pro(String bank_of_deposit_pro)
    {
        this.bank_of_deposit_pro = bank_of_deposit_pro;
    }

    public String getBank_of_deposit_city()
    {
        return bank_of_deposit_city;
    }

    public void setBank_of_deposit_city(String bank_of_deposit_city)
    {
        this.bank_of_deposit_city = bank_of_deposit_city;
    }

    public String getBank_branch()
    {
        return bank_branch;
    }

    public void setBank_branch(String bank_branch)
    {
        this.bank_branch = bank_branch;
    }

    public String getCategory_code()
    {
        return category_code;
    }

    public void setCategory_code(String category_code)
    {
        this.category_code = category_code;
    }

    public String getCategory_type()
    {
        return category_type;
    }

    public void setCategory_type(String category_type)
    {
        this.category_type = category_type;
    }

    public Integer getIs_allopatry()
    {
        return is_allopatry;
    }

    public void setIs_allopatry(Integer is_allopatry)
    {
        this.is_allopatry = is_allopatry;
    }

    public String getIs_protocol_finish()
    {
        return is_protocol_finish;
    }

    public void setIs_protocol_finish(String is_protocol_finish)
    {
        this.is_protocol_finish = is_protocol_finish;
    }

    public String getIs_customer_confirmation()
    {
        return is_customer_confirmation;
    }

    public void setIs_customer_confirmation(String is_customer_confirmation)
    {
        this.is_customer_confirmation = is_customer_confirmation;
    }

    public String getRedeem_sms_code()
    {
        return redeem_sms_code;
    }

    public void setRedeem_sms_code(String redeem_sms_code)
    {
        this.redeem_sms_code = redeem_sms_code;
    }

    public String getCategory_rebate_way()
    {
        return category_rebate_way;
    }

    public void setCategory_rebate_way(String category_rebate_way)
    {
        this.category_rebate_way = category_rebate_way;
    }

    public String getBank_of_deposit_code()
    {
        return bank_of_deposit_code;
    }

    public void setBank_of_deposit_code(String bank_of_deposit_code)
    {
        this.bank_of_deposit_code = bank_of_deposit_code;
    }

    public String getBank_of_deposit_pro_code()
    {
        return bank_of_deposit_pro_code;
    }

    public void setBank_of_deposit_pro_code(String bank_of_deposit_pro_code)
    {
        this.bank_of_deposit_pro_code = bank_of_deposit_pro_code;
    }

    public String getBank_of_deposit_city_code()
    {
        return bank_of_deposit_city_code;
    }

    public void setBank_of_deposit_city_code(String bank_of_deposit_city_code)
    {
        this.bank_of_deposit_city_code = bank_of_deposit_city_code;
    }

    public Integer getWms_inve_customer_card_id()
    {
        return wms_inve_customer_card_id;
    }

    public void setWms_inve_customer_card_id(Integer wms_inve_customer_card_id)
    {
        this.wms_inve_customer_card_id = wms_inve_customer_card_id;
    }

    public Integer getBuy_month_limit()
    {
        return buy_month_limit;
    }

    public void setBuy_month_limit(Integer buy_month_limit)
    {
        this.buy_month_limit = buy_month_limit;
    }

    public String getHas_paper_protocol()
    {
        return has_paper_protocol;
    }

    public void setHas_paper_protocol(String has_paper_protocol)
    {
        this.has_paper_protocol = has_paper_protocol;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_inve_transa_prod_id", this.wms_inve_transa_prod_id);
        paramMap.put("wms_inve_transa_id", this.wms_inve_transa_id);
        paramMap.put("wms_inve_pruduct_category_id", this.wms_inve_pruduct_category_id);
        paramMap.put("category_name", this.category_name);
        paramMap.put("product_deadline", this.product_deadline);
        paramMap.put("product_interest", this.product_interest);
        paramMap.put("product_account", this.product_account);
        paramMap.put("is_finish", this.is_finish);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("enable_flag", this.enable_flag);
        paramMap.put("card_owner_name", this.card_owner_name);
        paramMap.put("bank_of_deposit", this.bank_of_deposit);
        paramMap.put("card_no", this.card_no);
        paramMap.put("product_deadline_name", this.product_deadline_name);
        paramMap.put("reward_interest", this.reward_interest);
        paramMap.put("expect_interest_account", this.expect_interest_account);
        paramMap.put("wms_inve_pruduct_deadline_id", this.wms_inve_pruduct_deadline_id);
        paramMap.put("org_product_account", this.org_product_account);
        paramMap.put("isExcludePKFlag", this.isExcludePKFlag);
        paramMap.put("bank_of_deposit_pro", this.bank_of_deposit_pro);
        paramMap.put("bank_of_deposit_city", this.bank_of_deposit_city);
        paramMap.put("bank_branch", this.bank_branch);
        paramMap.put("category_code", this.category_code);
        paramMap.put("category_type", this.category_type);
        paramMap.put("category_rebate_way", this.category_rebate_way);
        paramMap.put("wms_inve_customer_card_id", this.wms_inve_customer_card_id);
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