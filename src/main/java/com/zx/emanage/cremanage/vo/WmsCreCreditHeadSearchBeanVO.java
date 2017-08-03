package com.zx.emanage.cremanage.vo;

import java.util.List;
import java.util.Map;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsCreCreditHeadSearchBeanVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;

    private String bill_code;// 单据编号

    private String salesman_name;// 业务员

    private String create_timestamp;// 申请时间
    
    private String create_timestamp1;// 申请时间区间
    
    private String create_timestamp_start;//申请时间开始
    
    private String create_timestamp_end;//申请时间结束

    private String id_card;// 身份证号

    private String flag;// 是否为贷款复核

    private String flag_water;// 流水审批 1

    private String flag_info;// 信息审批 2

    private String flag_phone;// 电审审批 3

    private String flag_certificate;// 征信审批 4

    private String flag_yd;// 验点审批5

    private String customer_name;// 客户姓名

    private String mobile_telephone;// 手机号码

    private String cre_type;// 产品类型

    private String city;// 所属城市

    private String bill_status;// 单据状态
    
    private String dept_city_sel;//所属城市

    private String dept_name_sel;//所属门店
    
    /** 房贷单据信息 **/
    private String houseLoanInfo;
    
    /** 保存类型 0:暂存 1:提交 **/
    private Integer type;
    
    /** 联系人信息 **/
    private String contactPeopleString;
    
    /** 删除的联系人id **/
    private Integer[] deleteContactIdArray;
    
    /** 房贷申请附件信息json字符串 **/
    private String attListJsonString;
    
    /** 是否需要分页 0:不需要 1:需要 **/
    private String is_need_paging;
    
    private Integer wms_cre_credit_head_id;
    
    /** 1:修改客户信息 2:修改房贷单据 3:放款申请 **/
    private String log_type;
    
    private java.sql.Timestamp now_timestamp;
    
    /** 客户id */
    private Integer wms_cre_credit_line_customer_change_head_id;
    
    /** 合同信息json字符串 **/
    private String wms_cre_appro_borrow_protocol_json;
    
    private String salesman_city_code;
    
    private String data_type_name;//图片大类
    
    private Integer[] deleteAttIds;//删除附件的主键
    
    private List<Map<String, Object>> wmsCreCreditHeadList;
    
    private Integer wms_cre_credit_group_id;
    
    private String repay_status_in;
    
    private String same_status;
    
    private String salesman_name_str;// 业务员/短工号

    private String is_finish;// 是否完善联系人 是否完善联系人 1完善 0/空 未完善
    
    private List<Map<String, Object>> list;
    
    private String dept_name;
    
    private Map<String, Object> map;
    
    /** 初评件数总计 **/
    private String intern_num_total;
    
    /** 验点件数总计 **/
    private String check_point_num_total;
    
    /** 放款件数总计 **/
    private String loans_num_total;
    
    /** 放款金额总计 **/
    private String loans_amount_total;
    
    /** 单据状态统计时间 **/
    private String statistics_time;

    /**抵押状态**/
    private String mort_flag;
    
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

    public String getCre_type()
    {
        return cre_type;
    }

    public void setCre_type(String cre_type)
    {
        this.cre_type = cre_type;
    }

    public String getFlag_water()
    {
        return flag_water;
    }

    public void setFlag_water(String flag_water)
    {
        this.flag_water = flag_water;
    }

    public String getFlag_info()
    {
        return flag_info;
    }

    public void setFlag_info(String flag_info)
    {
        this.flag_info = flag_info;
    }

    public String getFlag_phone()
    {
        return flag_phone;
    }

    public void setFlag_phone(String flag_phone)
    {
        this.flag_phone = flag_phone;
    }

    public String getFlag_certificate()
    {
        return flag_certificate;
    }

    public void setFlag_certificate(String flag_certificate)
    {
        this.flag_certificate = flag_certificate;
    }

    public String getBill_code()
    {
        return bill_code;
    }

    public void setBill_code(String bill_code)
    {
        this.bill_code = bill_code;
    }

    public String getCustomer_name()
    {
        return customer_name;
    }

    public void setCustomer_name(String customer_name)
    {
        this.customer_name = customer_name;
    }

    public String getMobile_telephone()
    {
        return mobile_telephone;
    }

    public void setMobile_telephone(String mobile_telephone)
    {
        this.mobile_telephone = mobile_telephone;
    }

    public String getSalesman_name()
    {
        return salesman_name;
    }

    public void setSalesman_name(String salesman_name)
    {
        this.salesman_name = salesman_name;
    }

    public String getCreate_timestamp()
    {
        return create_timestamp;
    }

    public void setCreate_timestamp(String create_timestamp)
    {
        this.create_timestamp = create_timestamp;
    }

    public String getId_card()
    {
        return id_card;
    }

    public void setId_card(String id_card)
    {
        this.id_card = id_card;
    }

    public String getFlag()
    {
        return flag;
    }

    public void setFlag(String flag)
    {
        this.flag = flag;
    }

    public String getFlag_yd()
    {
        return flag_yd;
    }

    public void setFlag_yd(String flag_yd)
    {
        this.flag_yd = flag_yd;
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

	public String getCreate_timestamp_start() {
		return create_timestamp_start;
	}

	public void setCreate_timestamp_start(String create_timestamp_start) {
		this.create_timestamp_start = create_timestamp_start;
	}

	public String getCreate_timestamp_end() {
		return create_timestamp_end;
	}

	public void setCreate_timestamp_end(String create_timestamp_end) {
		this.create_timestamp_end = create_timestamp_end;
	}

    public String getHouseLoanInfo() {
        return houseLoanInfo;
    }

    public void setHouseLoanInfo(String houseLoanInfo) {
        this.houseLoanInfo = houseLoanInfo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContactPeopleString() {
        return contactPeopleString;
    }

    public void setContactPeopleString(String contactPeopleString) {
        this.contactPeopleString = contactPeopleString;
    }

    public Integer[] getDeleteContactIdArray() {
        return deleteContactIdArray;
    }

    public void setDeleteContactIdArray(Integer[] deleteContactIdArray) {
        this.deleteContactIdArray = deleteContactIdArray;
    }

    public String getAttListJsonString() {
        return attListJsonString;
    }

    public void setAttListJsonString(String attListJsonString) {
        this.attListJsonString = attListJsonString;
    }

    public String getIs_need_paging() {
        return is_need_paging;
    }

    public void setIs_need_paging(String is_need_paging) {
        this.is_need_paging = is_need_paging;
    }

    public Integer getWms_cre_credit_head_id() {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer wms_cre_credit_head_id) {
        this.wms_cre_credit_head_id = wms_cre_credit_head_id;
    }

    public java.sql.Timestamp getNow_timestamp() {
        return now_timestamp;
    }

    public void setNow_timestamp(java.sql.Timestamp now_timestamp) {
        this.now_timestamp = now_timestamp;
    }

    public String getLog_type() {
        return log_type;
    }

    public void setLog_type(String log_type) {
        this.log_type = log_type;
    }

    public Integer getWms_cre_credit_line_customer_change_head_id() {
        return wms_cre_credit_line_customer_change_head_id;
    }

    public void setWms_cre_credit_line_customer_change_head_id(
            Integer wms_cre_credit_line_customer_change_head_id) {
        this.wms_cre_credit_line_customer_change_head_id = wms_cre_credit_line_customer_change_head_id;
    }

    public String getWms_cre_appro_borrow_protocol_json() {
        return wms_cre_appro_borrow_protocol_json;
    }

    public void setWms_cre_appro_borrow_protocol_json(
            String wms_cre_appro_borrow_protocol_json) {
        this.wms_cre_appro_borrow_protocol_json = wms_cre_appro_borrow_protocol_json;
    }

	public String getSalesman_city_code() {
		return salesman_city_code;
	}

	public void setSalesman_city_code(String salesman_city_code) {
		this.salesman_city_code = salesman_city_code;
	}

	public String getData_type_name() {
		return data_type_name;
	}

	public void setData_type_name(String data_type_name) {
		this.data_type_name = data_type_name;
	}

    public Integer[] getDeleteAttIds() {
        return deleteAttIds;
    }

    public void setDeleteAttIds(Integer[] deleteAttIds) {
        this.deleteAttIds = deleteAttIds;
    }

    public List<Map<String, Object>> getWmsCreCreditHeadList()
    {
        return wmsCreCreditHeadList;
    }

    public void setWmsCreCreditHeadList(List<Map<String, Object>> wmsCreCreditHeadList)
    {
        this.wmsCreCreditHeadList = wmsCreCreditHeadList;
    }

    public Integer getWms_cre_credit_group_id()
    {
        return wms_cre_credit_group_id;
    }

    public void setWms_cre_credit_group_id(Integer wms_cre_credit_group_id)
    {
        this.wms_cre_credit_group_id = wms_cre_credit_group_id;
    }

    public String getRepay_status_in()
    {
        return repay_status_in;
    }

    public void setRepay_status_in(String repay_status_in)
    {
        this.repay_status_in = repay_status_in;
    }

    public String getSame_status()
    {
        return same_status;
    }

    public void setSame_status(String same_status)
    {
        this.same_status = same_status;
    }

    public String getSalesman_name_str()
    {
        return salesman_name_str;
    }

    public void setSalesman_name_str(String salesman_name_str)
    {
        this.salesman_name_str = salesman_name_str;
    }

    public String getIs_finish()
    {
        return is_finish;
    }

    public void setIs_finish(String is_finish)
    {
        this.is_finish = is_finish;
    }

    /**
     * @return the list
     */
    public List<Map<String, Object>> getList()
    {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<Map<String, Object>> list)
    {
        this.list = list;
    }

    /**
     * @return the dept_name
     */
    public String getDept_name()
    {
        return dept_name;
    }

    public void setDept_name(String dept_name)
    {
        this.dept_name = dept_name;
    }

    public Map<String, Object> getMap()
    {
        return map;
    }

    public void setMap(Map<String, Object> map)
    {
        this.map = map;
    }

    /**
     * @return the intern_num_total
     */
    public String getIntern_num_total()
    {
        return intern_num_total;
    }

    /**
     * @param intern_num_total the intern_num_total to set
     */
    public void setIntern_num_total(String intern_num_total)
    {
        this.intern_num_total = intern_num_total;
    }

    /**
     * @return the check_point_num_total
     */
    public String getCheck_point_num_total()
    {
        return check_point_num_total;
    }

    /**
     * @param check_point_num_total the check_point_num_total to set
     */
    public void setCheck_point_num_total(String check_point_num_total)
    {
        this.check_point_num_total = check_point_num_total;
    }

    /**
     * @return the loans_num_total
     */
    public String getLoans_num_total()
    {
        return loans_num_total;
    }

    /**
     * @param loans_num_total the loans_num_total to set
     */
    public void setLoans_num_total(String loans_num_total)
    {
        this.loans_num_total = loans_num_total;
    }

    /**
     * @return the loans_amount_total
     */
    public String getLoans_amount_total()
    {
        return loans_amount_total;
    }

    /**
     * @param loans_amount_total the loans_amount_total to set
     */
    public void setLoans_amount_total(String loans_amount_total)
    {
        this.loans_amount_total = loans_amount_total;
    }

    /**
     * @return the statistics_time
     */
    public String getStatistics_time()
    {
        return statistics_time;
    }

    /**
     * @param statistics_time the statistics_time to set
     */
    public void setStatistics_time(String statistics_time)
    {
        this.statistics_time = statistics_time;
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