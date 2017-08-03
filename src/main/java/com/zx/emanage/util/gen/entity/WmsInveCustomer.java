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

public class WmsInveCustomer implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_inve_customer_id;

    private String id_card;

    private String cus_code;

    private String cus_name;

    private java.sql.Date cus_birthday;

    private String cus_birthday_str;

    private String cus_gender;

    private String cus_address;

    private String post_code;

    private String address_phone;

    private String mobile_phone;

    private String work_phone;

    private String cus_fax;

    private String contact_address;

    private String customer_email;

    private String salesman_name;

    private Integer salesman_id;

    private Integer create_user_id;

    private String create_user_name;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String enable_flag;

    private String salesman_city_code;

    private String salesman_city;

    private Integer salesman_dept_id;

    private Integer create_user_dept_id;
    
    private Integer costomer_id;
    
    private String customer_source;

    private boolean isExcludePKFlag;
    
    private Integer department_manager_id;
    
    private String department_manager_city_code;
    
    private Integer department_manager_dept_id; 
    
    private Integer vice_general_manager_id;
    
    private String vice_general_manager_city_code;
    
    private Integer vice_general_manager_dept_id; 
    
    private Integer general_manager_id;
    
    private String general_manager_city_code;
    
    private Integer general_manager_dept_id; 

    private String customer_num;
    /**
     * certificate_type（证件类型）
		1139（身份证）、1140（军官证）、1141（护照）
     */
    private Integer certificate_type;

    private String crm_create_timestamp;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_inve_customer_id" };

    private static String[] columnNameArr = { "wms_inve_customer_id", "id_card", "cus_code", "cus_name",
                                             "cus_birthday", "cus_birthday_str", "cus_gender", "cus_address",
                                             "post_code", "address_phone", "mobile_phone", "work_phone", "cus_fax",
                                             "contact_address", "customer_email", "salesman_name", "salesman_id",
                                             "create_user_id", "create_user_name", "create_timestamp",
                                             "create_timestamp_str", "last_update_user_id", "last_update_timestamp",
                                             "last_update_timestamp_str", "enable_flag", "salesman_city_code",
                                             "salesman_city", "salesman_dept_id", "create_user_dept_id","costomer_id","customer_source",
                                             "isExcludePKFlag", "department_manager_id", "department_manager_city_code", "department_manager_dept_id",
                                             "vice_general_manager_id","vice_general_manager_city_code","vice_general_manager_dept_id",
 "general_manager_id", "general_manager_city_code",
                                             "general_manager_dept_id", "customer_num", "crm_create_timestamp" };

    public Integer getWms_inve_customer_id()
    {
        return wms_inve_customer_id;
    }

    public void setWms_inve_customer_id(Integer obj)
    {
        wms_inve_customer_id = obj;
    }

    public String getId_card()
    {
        return id_card;
    }

    public void setId_card(String obj)
    {
        id_card = obj;
    }

    public String getCus_code()
    {
        return cus_code;
    }

    public void setCus_code(String obj)
    {
        cus_code = obj;
    }

    public String getCus_name()
    {
        return cus_name;
    }

    public void setCus_name(String obj)
    {
        cus_name = obj;
    }

    public java.sql.Date getCus_birthday()
    {
        return cus_birthday;
    }

    public void setCus_birthday(java.sql.Date obj)
    {
        cus_birthday = obj;
    }

    public String getCus_birthday_str()
    {
        return cus_birthday_str;
    }

    public void setCus_birthday_str(String obj)
    {
        cus_birthday_str = obj;
    }

    public String getCus_gender()
    {
        return cus_gender;
    }

    public void setCus_gender(String obj)
    {
        cus_gender = obj;
    }

    public String getCus_address()
    {
        return cus_address;
    }

    public void setCus_address(String obj)
    {
        cus_address = obj;
    }

    public String getPost_code()
    {
        return post_code;
    }

    public void setPost_code(String obj)
    {
        post_code = obj;
    }

    public String getAddress_phone()
    {
        return address_phone;
    }

    public void setAddress_phone(String obj)
    {
        address_phone = obj;
    }

    public String getMobile_phone()
    {
        return mobile_phone;
    }

    public void setMobile_phone(String obj)
    {
        mobile_phone = obj;
    }

    public String getWork_phone()
    {
        return work_phone;
    }

    public void setWork_phone(String obj)
    {
        work_phone = obj;
    }

    public String getCus_fax()
    {
        return cus_fax;
    }

    public void setCus_fax(String obj)
    {
        cus_fax = obj;
    }

    public String getContact_address()
    {
        return contact_address;
    }

    public void setContact_address(String obj)
    {
        contact_address = obj;
    }

    public String getCustomer_email()
    {
        return customer_email;
    }

    public void setCustomer_email(String obj)
    {
        customer_email = obj;
    }

    public String getSalesman_name()
    {
        return salesman_name;
    }

    public void setSalesman_name(String obj)
    {
        salesman_name = obj;
    }

    public Integer getSalesman_id()
    {
        return salesman_id;
    }

    public void setSalesman_id(Integer obj)
    {
        salesman_id = obj;
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

    public String getSalesman_city_code()
    {
        return salesman_city_code;
    }

    public void setSalesman_city_code(String obj)
    {
        salesman_city_code = obj;
    }

    public String getSalesman_city()
    {
        return salesman_city;
    }

    public void setSalesman_city(String obj)
    {
        salesman_city = obj;
    }

    public Integer getSalesman_dept_id()
    {
        return salesman_dept_id;
    }

    public void setSalesman_dept_id(Integer obj)
    {
        salesman_dept_id = obj;
    }

    public Integer getCreate_user_dept_id()
    {
        return create_user_dept_id;
    }

    public void setCreate_user_dept_id(Integer obj)
    {
        create_user_dept_id = obj;
    }

    public Integer getCostomer_id()
    {
        return costomer_id;
    }

    public void setCostomer_id(Integer costomer_id)
    {
        this.costomer_id = costomer_id;
    }

    public String getCustomer_source()
    {
        return customer_source;
    }

    public void setCustomer_source(String customer_source)
    {
        this.customer_source = customer_source;
    }

    public boolean getgetIsExcludePKFlag()
    {
        return isExcludePKFlag;
    }

    public void setgetIsExcludePKFlag(boolean obj)
    {
        isExcludePKFlag = obj;
    }
    
    public Integer getDepartment_manager_id() {
		return department_manager_id;
	}

	public void setDepartment_manager_id(Integer department_manager_id) {
		this.department_manager_id = department_manager_id;
	}

	public String getDepartment_manager_city_code() {
		return department_manager_city_code;
	}

	public void setDepartment_manager_city_code(String department_manager_city_code) {
		this.department_manager_city_code = department_manager_city_code;
	}

	public Integer getDepartment_manager_dept_id() {
		return department_manager_dept_id;
	}

	public void setDepartment_manager_dept_id(Integer department_manager_dept_id) {
		this.department_manager_dept_id = department_manager_dept_id;
	}

	public Integer getVice_general_manager_id() {
		return vice_general_manager_id;
	}

	public void setVice_general_manager_id(Integer vice_general_manager_id) {
		this.vice_general_manager_id = vice_general_manager_id;
	}

	public String getVice_general_manager_city_code() {
		return vice_general_manager_city_code;
	}

	public void setVice_general_manager_city_code(
			String vice_general_manager_city_code) {
		this.vice_general_manager_city_code = vice_general_manager_city_code;
	}

	public Integer getVice_general_manager_dept_id() {
		return vice_general_manager_dept_id;
	}

	public void setVice_general_manager_dept_id(Integer vice_general_manager_dept_id) {
		this.vice_general_manager_dept_id = vice_general_manager_dept_id;
	}

	public Integer getGeneral_manager_id() {
		return general_manager_id;
	}

	public void setGeneral_manager_id(Integer general_manager_id) {
		this.general_manager_id = general_manager_id;
	}

	public String getGeneral_manager_city_code() {
		return general_manager_city_code;
	}

	public void setGeneral_manager_city_code(String general_manager_city_code) {
		this.general_manager_city_code = general_manager_city_code;
	}

	public Integer getGeneral_manager_dept_id() {
		return general_manager_dept_id;
	}

	public void setGeneral_manager_dept_id(Integer general_manager_dept_id) {
		this.general_manager_dept_id = general_manager_dept_id;
	}
	
	public String getCustomer_num() {
		return customer_num;
	}

	public void setCustomer_num(String customer_num) {
		this.customer_num = customer_num;
	}

	public Integer getCertificate_type() {
		return certificate_type;
	}

	public void setCertificate_type(Integer certificate_type) {
		this.certificate_type = certificate_type;
	}

    public String getCrm_create_timestamp()
    {
        return crm_create_timestamp;
    }

    public void setCrm_create_timestamp(String crm_create_timestamp)
    {
        this.crm_create_timestamp = crm_create_timestamp;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_inve_customer_id", this.wms_inve_customer_id);
        paramMap.put("id_card", this.id_card);
        paramMap.put("cus_code", this.cus_code);
        paramMap.put("cus_name", this.cus_name);
        paramMap.put("cus_birthday", this.cus_birthday);
        paramMap.put("cus_birthday_str", this.cus_birthday_str);
        paramMap.put("cus_gender", this.cus_gender);
        paramMap.put("cus_address", this.cus_address);
        paramMap.put("post_code", this.post_code);
        paramMap.put("address_phone", this.address_phone);
        paramMap.put("mobile_phone", this.mobile_phone);
        paramMap.put("work_phone", this.work_phone);
        paramMap.put("cus_fax", this.cus_fax);
        paramMap.put("contact_address", this.contact_address);
        paramMap.put("customer_email", this.customer_email);
        paramMap.put("salesman_name", this.salesman_name);
        paramMap.put("salesman_id", this.salesman_id);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("enable_flag", this.enable_flag);
        paramMap.put("salesman_city_code", this.salesman_city_code);
        paramMap.put("salesman_city", this.salesman_city);
        paramMap.put("salesman_dept_id", this.salesman_dept_id);
        paramMap.put("create_user_dept_id", this.create_user_dept_id);
        paramMap.put("costomer_id", this.costomer_id);
        paramMap.put("customer_source",this.customer_source);
        paramMap.put("department_manager_id", this.department_manager_id);
        paramMap.put("department_manager_city_code", this.department_manager_city_code);
        paramMap.put("department_manager_dept_id", this.department_manager_dept_id);
        paramMap.put("vice_general_manager_id", this.vice_general_manager_id);
        paramMap.put("vice_general_manager_city_code", this.vice_general_manager_city_code);
        paramMap.put("vice_general_manager_dept_id", this.vice_general_manager_dept_id);
        paramMap.put("general_manager_id", this.general_manager_id);
        paramMap.put("general_manager_city_code", this.general_manager_city_code);
        paramMap.put("general_manager_dept_id", this.general_manager_dept_id);
        paramMap.put("customer_num", this.customer_num);
        paramMap.put("isExcludePKFlag", this.isExcludePKFlag);
        paramMap.put("crm_create_timestamp", this.crm_create_timestamp);
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