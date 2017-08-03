package com.zx.emanage.cremanage.vo;

/**
 * 车贷公共VO类
 * @author hancd
 * @version 1.5
 */
public class WmsCarLoanWorkFlowVO
{
    private Integer wms_cre_credit_head_id;// 主表单的id

    private String pass;// 审批结果

    private String advice;// 审批意见

    private String userId;// 登录用户id

    private String taskId;// 任务节点id

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer wms_cre_credit_head_id)
    {
        this.wms_cre_credit_head_id = wms_cre_credit_head_id;
    }

    public String getAdvice()
    {
        return advice;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public void setAdvice(String advice)
    {
        this.advice = advice;
    }

    
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
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
}
