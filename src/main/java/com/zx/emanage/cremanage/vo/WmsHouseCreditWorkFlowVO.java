package com.zx.emanage.cremanage.vo;

import java.math.BigDecimal;

/**
 * 
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsHouseCreditWorkFlowVO
 * 模块名称：实现房贷公有化参数构造VO
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月29日
 * @version V1.0
 * history:
 * 1、2016年12月29日 baisong 创建文件
 */
public class WmsHouseCreditWorkFlowVO
{
    private String wms_cre_credit_head_id;

    private String pass;// 审批结果

    private String advice;// 审批意见

    private String yadvice;// 原advice 只应用在流水和电审

    private String userId;// 登录用户ID

    private String taskId;// 任务节点ID.
    
	//代表各流程主表
	private String businessId;
	
    //代表审批的环节操作key
    private String debtkey;
    
    private String processDefinitionKey;//流程实例key
    
    private String status;//单据状态
    
    private String edition_num;//房贷流程版本号 1为老流程  2为新流程
    
    private String version_number;//数据来源1为pc 2为移动端
    
    private String 	approval_user_name; //'审批人姓名'
    
    private String  approval_user_deptId; //'审批人部门'
    
    private String  approval_user_postNub; //'审批人职务'
    
    private String  data_type_name;//当前页面显示图片的类别
    
    private String  credit_confirm_result;//授信确认 credit_confirm   1：普通贷（默认值）0：授信贷
    
    private String  city_code;//城市编码
    
    private Integer[] deleteAttIds;//删除附件的主键
    /**是否存在流程：1存在 0不存在**/
    private String is_workflow;


    // 单据状态
    private String bill_status;


    // 是否面签1 面签 0否
    private String is_visa;

    // 是否重新核查1是0否
    private String is_again_appl;

    private BigDecimal check_pay;// 核查金额
    public String getYadvice()
    {
        return yadvice;
    }

    public void setYadvice(String yadvice)
    {
        this.yadvice = yadvice;
    }

    public String getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(String wms_cre_credit_head_id)
    {
        this.wms_cre_credit_head_id = wms_cre_credit_head_id;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public String getAdvice()
    {
        return advice;
    }

    public void setAdvice(String advice)
    {
        this.advice = advice;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getTaskId()
    {
        return taskId;
    }

    public void setTaskId(String taskId)
    {
        this.taskId = taskId;
    }

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getDebtkey() {
		return debtkey;
	}

	public void setDebtkey(String debtkey) {
		this.debtkey = debtkey;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEdition_num() {
		return edition_num;
	}

	public void setEdition_num(String edition_num) {
		this.edition_num = edition_num;
	}

	public String getVersion_number() {
		return version_number;
	}

	public void setVersion_number(String version_number) {
		this.version_number = version_number;
	}

	public String getApproval_user_name() {
		return approval_user_name;
	}

	public void setApproval_user_name(String approval_user_name) {
		this.approval_user_name = approval_user_name;
	}

	public String getApproval_user_deptId() {
		return approval_user_deptId;
	}

	public void setApproval_user_deptId(String approval_user_deptId) {
		this.approval_user_deptId = approval_user_deptId;
	}

	public String getApproval_user_postNub() {
		return approval_user_postNub;
	}

	public void setApproval_user_postNub(String approval_user_postNub) {
		this.approval_user_postNub = approval_user_postNub;
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

	public String getCredit_confirm_result() {
		return credit_confirm_result;
	}

	public void setCredit_confirm_result(String credit_confirm_result) {
		this.credit_confirm_result = credit_confirm_result;
	}

	public String getCity_code() {
		return city_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

    public String getIs_workflow()
    {
        return is_workflow;
    }

    public void setIs_workflow(String is_workflow)
    {
        this.is_workflow = is_workflow;
    }

    public String getBill_status()
    {
        return bill_status;
    }

    public void setBill_status(String bill_status)
    {
        this.bill_status = bill_status;
    }

    public String getIs_visa()
    {
        return is_visa;
    }

    public void setIs_visa(String is_visa)
    {
        this.is_visa = is_visa;
    }

    public String getIs_again_appl()
    {
        return is_again_appl;
    }

    public void setIs_again_appl(String is_again_appl)
    {
        this.is_again_appl = is_again_appl;
    }

    public BigDecimal getCheck_pay()
    {
        return check_pay;
    }

    public void setCheck_pay(BigDecimal check_pay)
    {
        this.check_pay = check_pay;
    }

}
