package com.zx.emanage.loanpost.vo;

/**
 * 公共VO类
 * 
 * @author HANCD
 */
public class WmsPostLoanWorkFlowBeanVO
{
    
    private Integer wms_cre_credit_head_id;// 主表单的id

    private String pass;// 审批结果

    private String advice;// 审批意见

    private Integer user_id;// 登录用户id

    private String taskId;// 任务节点id

    private Integer wms_post_dunning_head_id;//催缴单的id
    /**
     * 1:代表电话催缴  
     * 2:代表电催确认  
     * 3:代表催缴分配 
     * 4:代表上门催缴  
     * 5:代表催缴成功确认
     */
    private String dunningKey;//代表催缴流程各个节点:
    
    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer wms_cre_credit_head_id)
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

    public Integer getUser_id()
    {
        return user_id;
    }

    public void setUser_id(Integer user_id)
    {
        this.user_id = user_id;
    }

    public String getTaskId()
    {
        return taskId;
    }

    public void setTaskId(String taskId)
    {
        this.taskId = taskId;
    }

    public Integer getWms_post_dunning_head_id()
    {
        return wms_post_dunning_head_id;
    }

    public void setWms_post_dunning_head_id(Integer wms_post_dunning_head_id)
    {
        this.wms_post_dunning_head_id = wms_post_dunning_head_id;
    }

    public String getDunningKey()
    {
        return dunningKey;
    }

    public void setDunningKey(String dunningKey)
    {
        this.dunningKey = dunningKey;
    }

}
