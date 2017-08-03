package com.zx.emanage.util.gen.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.math.BigDecimal;

import com.zx.sframe.util.mybatis.BaseEntity;

/*
 * @version 2.0
 */

public class WmsCreRevInfoMain implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_rev_info_main_id;

    private Integer wms_cre_credit_head_id;

    private Integer wms_cre_credit_line_customer_change_head_id;

    private String customer_name;

    private String customer_prop;

    private String priv_court_case;

    private String comp_court_case;

    private String is_crime_record;

    private String is_car_record;

    private String is_re_loan;

    private java.math.BigDecimal last_loan_money;

    private String repayment_status;

    private java.sql.Date last_apply_date;

    private String last_apply_date_str;

    private java.sql.Date last_clean_date;

    private String last_clean_date_str;

    private String overdue_status;

    private String is_real_name_tel1;

    private String is_real_name_tel2;

    private String real_name_tel1;

    private String real_name_tel2;

    private java.sql.Date phone_access_date1;

    private String phone_access_date1_str;

    private java.sql.Date phone_access_date2;

    private String phone_access_date2_str;

    private String phone_remark;

    private String apply_name;

    private String id_card;

    private String owner_name;

    private String co_owner_name;

    private String house_card_id;

    private String house_address;

    private String obligee_name;

    private java.math.BigDecimal mortgage_amount;

    private String agree_limit_time;

    private String other_remark;

    private String estate_board;

    private String board_remark;

    private String insurance_remark;

    private Integer create_user_id;

    private String create_user_name;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String enable_flag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_rev_info_main_id" };

    private static String[] columnNameArr = { "wms_cre_rev_info_main_id", "wms_cre_credit_head_id",
                                             "wms_cre_credit_line_customer_change_head_id", "customer_name",
                                             "customer_prop", "priv_court_case", "comp_court_case", "is_crime_record",
                                             "is_car_record", "is_re_loan", "last_loan_money", "repayment_status",
                                             "last_apply_date", "last_apply_date_str", "last_clean_date",
                                             "last_clean_date_str", "overdue_status", "is_real_name_tel1",
                                             "is_real_name_tel2", "real_name_tel1", "real_name_tel2",
                                             "phone_access_date1", "phone_access_date1_str", "phone_access_date2",
                                             "phone_access_date2_str", "phone_remark", "apply_name", "id_card",
                                             "owner_name", "co_owner_name", "house_card_id", "house_address",
                                             "obligee_name", "mortgage_amount", "agree_limit_time", "other_remark",
                                             "estate_board", "board_remark", "insurance_remark", "create_user_id",
                                             "create_user_name", "create_timestamp", "create_timestamp_str",
                                             "last_update_user_id", "last_update_timestamp",
                                             "last_update_timestamp_str", "enable_flag" };

    public Integer getWms_cre_rev_info_main_id()
    {
        return wms_cre_rev_info_main_id;
    }

    public void setWms_cre_rev_info_main_id(Integer obj)
    {
        wms_cre_rev_info_main_id = obj;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
    }

    public Integer getWms_cre_credit_line_customer_change_head_id()
    {
        return wms_cre_credit_line_customer_change_head_id;
    }

    public void setWms_cre_credit_line_customer_change_head_id(Integer obj)
    {
        wms_cre_credit_line_customer_change_head_id = obj;
    }

    public String getCustomer_name()
    {
        return customer_name;
    }

    public void setCustomer_name(String obj)
    {
        customer_name = obj;
    }

    public String getCustomer_prop()
    {
        return customer_prop;
    }

    public void setCustomer_prop(String obj)
    {
        customer_prop = obj;
    }

    public String getPriv_court_case()
    {
        return priv_court_case;
    }

    public void setPriv_court_case(String obj)
    {
        priv_court_case = obj;
    }

    public String getComp_court_case()
    {
        return comp_court_case;
    }

    public void setComp_court_case(String obj)
    {
        comp_court_case = obj;
    }

    public String getIs_crime_record()
    {
        return is_crime_record;
    }

    public void setIs_crime_record(String obj)
    {
        is_crime_record = obj;
    }

    public String getIs_car_record()
    {
        return is_car_record;
    }

    public void setIs_car_record(String obj)
    {
        is_car_record = obj;
    }

    public String getIs_re_loan()
    {
        return is_re_loan;
    }

    public void setIs_re_loan(String obj)
    {
        is_re_loan = obj;
    }

    public java.math.BigDecimal getLast_loan_money()
    {
        return last_loan_money;
    }

    public void setLast_loan_money(java.math.BigDecimal obj)
    {
        last_loan_money = obj;
    }

    public String getRepayment_status()
    {
        return repayment_status;
    }

    public void setRepayment_status(String obj)
    {
        repayment_status = obj;
    }

    public java.sql.Date getLast_apply_date()
    {
        return last_apply_date;
    }

    public void setLast_apply_date(java.sql.Date obj)
    {
        last_apply_date = obj;
    }

    public String getLast_apply_dateStr()
    {
        return last_apply_date_str;
    }

    public void setLast_apply_dateStr(String obj)
    {
        last_apply_date_str = obj;
    }

    public java.sql.Date getLast_clean_date()
    {
        return last_clean_date;
    }

    public void setLast_clean_date(java.sql.Date obj)
    {
        last_clean_date = obj;
    }

    public String getLast_clean_dateStr()
    {
        return last_clean_date_str;
    }

    public void setLast_clean_dateStr(String obj)
    {
        last_clean_date_str = obj;
    }

    public String getOverdue_status()
    {
        return overdue_status;
    }

    public void setOverdue_status(String obj)
    {
        overdue_status = obj;
    }

    public String getIs_real_name_tel1()
    {
        return is_real_name_tel1;
    }

    public void setIs_real_name_tel1(String obj)
    {
        is_real_name_tel1 = obj;
    }

    public String getIs_real_name_tel2()
    {
        return is_real_name_tel2;
    }

    public void setIs_real_name_tel2(String obj)
    {
        is_real_name_tel2 = obj;
    }

    public String getReal_name_tel1()
    {
        return real_name_tel1;
    }

    public void setReal_name_tel1(String obj)
    {
        real_name_tel1 = obj;
    }

    public String getReal_name_tel2()
    {
        return real_name_tel2;
    }

    public void setReal_name_tel2(String obj)
    {
        real_name_tel2 = obj;
    }

    public java.sql.Date getPhone_access_date1()
    {
        return phone_access_date1;
    }

    public void setPhone_access_date1(java.sql.Date obj)
    {
        phone_access_date1 = obj;
    }

    public String getPhone_access_date1Str()
    {
        return phone_access_date1_str;
    }

    public void setPhone_access_date1Str(String obj)
    {
        phone_access_date1_str = obj;
    }

    public java.sql.Date getPhone_access_date2()
    {
        return phone_access_date2;
    }

    public void setPhone_access_date2(java.sql.Date obj)
    {
        phone_access_date2 = obj;
    }

    public String getPhone_access_date2Str()
    {
        return phone_access_date2_str;
    }

    public void setPhone_access_date2Str(String obj)
    {
        phone_access_date2_str = obj;
    }

    public String getPhone_remark()
    {
        return phone_remark;
    }

    public void setPhone_remark(String obj)
    {
        phone_remark = obj;
    }

    public String getApply_name()
    {
        return apply_name;
    }

    public void setApply_name(String obj)
    {
        apply_name = obj;
    }

    public String getId_card()
    {
        return id_card;
    }

    public void setId_card(String obj)
    {
        id_card = obj;
    }

    public String getOwner_name()
    {
        return owner_name;
    }

    public void setOwner_name(String obj)
    {
        owner_name = obj;
    }

    public String getCo_owner_name()
    {
        return co_owner_name;
    }

    public void setCo_owner_name(String obj)
    {
        co_owner_name = obj;
    }

    public String getHouse_card_id()
    {
        return house_card_id;
    }

    public void setHouse_card_id(String obj)
    {
        house_card_id = obj;
    }

    public String getHouse_address()
    {
        return house_address;
    }

    public void setHouse_address(String obj)
    {
        house_address = obj;
    }

    public String getObligee_name()
    {
        return obligee_name;
    }

    public void setObligee_name(String obj)
    {
        obligee_name = obj;
    }

    public java.math.BigDecimal getMortgage_amount()
    {
        return mortgage_amount;
    }

    public void setMortgage_amount(java.math.BigDecimal obj)
    {
        mortgage_amount = obj;
    }

    public String getAgree_limit_time()
    {
        return agree_limit_time;
    }

    public void setAgree_limit_time(String obj)
    {
        agree_limit_time = obj;
    }

    public String getOther_remark()
    {
        return other_remark;
    }

    public void setOther_remark(String obj)
    {
        other_remark = obj;
    }

    public String getEstate_board()
    {
        return estate_board;
    }

    public void setEstate_board(String obj)
    {
        estate_board = obj;
    }

    public String getBoard_remark()
    {
        return board_remark;
    }

    public void setBoard_remark(String obj)
    {
        board_remark = obj;
    }

    public String getInsurance_remark()
    {
        return insurance_remark;
    }

    public void setInsurance_remark(String obj)
    {
        insurance_remark = obj;
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

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_rev_info_main_id", this.wms_cre_rev_info_main_id);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("wms_cre_credit_line_customer_change_head_id", this.wms_cre_credit_line_customer_change_head_id);
        paramMap.put("customer_name", this.customer_name);
        paramMap.put("customer_prop", this.customer_prop);
        paramMap.put("priv_court_case", this.priv_court_case);
        paramMap.put("comp_court_case", this.comp_court_case);
        paramMap.put("is_crime_record", this.is_crime_record);
        paramMap.put("is_car_record", this.is_car_record);
        paramMap.put("is_re_loan", this.is_re_loan);
        paramMap.put("last_loan_money", this.last_loan_money);
        paramMap.put("repayment_status", this.repayment_status);
        paramMap.put("last_apply_date", this.last_apply_date);
        paramMap.put("last_apply_date_str", this.last_apply_date_str);
        paramMap.put("last_clean_date", this.last_clean_date);
        paramMap.put("last_clean_date_str", this.last_clean_date_str);
        paramMap.put("overdue_status", this.overdue_status);
        paramMap.put("is_real_name_tel1", this.is_real_name_tel1);
        paramMap.put("is_real_name_tel2", this.is_real_name_tel2);
        paramMap.put("real_name_tel1", this.real_name_tel1);
        paramMap.put("real_name_tel2", this.real_name_tel2);
        paramMap.put("phone_access_date1", this.phone_access_date1);
        paramMap.put("phone_access_date1_str", this.phone_access_date1_str);
        paramMap.put("phone_access_date2", this.phone_access_date2);
        paramMap.put("phone_access_date2_str", this.phone_access_date2_str);
        paramMap.put("phone_remark", this.phone_remark);
        paramMap.put("apply_name", this.apply_name);
        paramMap.put("id_card", this.id_card);
        paramMap.put("owner_name", this.owner_name);
        paramMap.put("co_owner_name", this.co_owner_name);
        paramMap.put("house_card_id", this.house_card_id);
        paramMap.put("house_address", this.house_address);
        paramMap.put("obligee_name", this.obligee_name);
        paramMap.put("mortgage_amount", this.mortgage_amount);
        paramMap.put("agree_limit_time", this.agree_limit_time);
        paramMap.put("other_remark", this.other_remark);
        paramMap.put("estate_board", this.estate_board);
        paramMap.put("board_remark", this.board_remark);
        paramMap.put("insurance_remark", this.insurance_remark);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("enable_flag", this.enable_flag);
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