package com.zx.emanage.cremanage.vo;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreditSynWorkFlowVO
 * 模块名称：同步流程类
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 baisong 创建文件
 */
public class WmsCreditSynWorkFlowVO
{
    private String pass;// 审批结果

    private String advice;// 审批意见

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

    public String getBusinessId()
    {
        return businessId;
    }

    public void setBusinessId(String businessId)
    {
        this.businessId = businessId;
    }

    public String getDebtkey()
    {
        return debtkey;
    }

    public void setDebtkey(String debtkey)
    {
        this.debtkey = debtkey;
    }

    public String getProcessDefinitionKey()
    {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey)
    {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getEdition_num()
    {
        return edition_num;
    }

    public void setEdition_num(String edition_num)
    {
        this.edition_num = edition_num;
    }

    public String getVersion_number()
    {
        return version_number;
    }

    public void setVersion_number(String version_number)
    {
        this.version_number = version_number;
    }


}
