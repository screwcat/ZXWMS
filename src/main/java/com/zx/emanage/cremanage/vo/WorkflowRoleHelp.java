package com.zx.emanage.cremanage.vo;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WorkflowRoleHelp
 * 模块名称：
 * @Description: 内容摘要：房贷中需要定义的固定值
 * @author baisong
 * @date 2016年12月22日
 * @version V1.0
 * history:
 * 1、2016年12月22日 baisong 创建文件
 */
public class WorkflowRoleHelp
{

	//下面为了发送短信等获取人员使用
    //--------------流程角色定义---------------
	// --房贷流程--
	//初审评估节点的角色
    public static final String HOUSE_MQZZG = "mqzzg";
    //办件房产核查节点的角色
    public static final String HOUSE_DQBJFHY = "dqbjfhy";
    //贷款终审节点的角色
    public static final String HOUSE_YWBMZG = "ywbmzg";
    //合同签订节点的角色
    public static final String HOUSE_HTZY = "htzy";
    //放款申请节点的角色
    public static final String HOUSE_GLBZL = "glbzl";
    // 放款审核节点的角色 办件过期
    public static final String HOUSE_GLBJL = "glbjl";
    
    //电审节点的角色
    public static final String HOUSE_DQDSFHY = "dqdsfhy";
    //征信节点的角色
    public static final String HOUSE_DQZXFHY = "dqzxfhy";
    
}
