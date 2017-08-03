package com.zx.emanage.cremanage.service;

import java.util.List;
import java.util.Map;

import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.workflow.util.WorkflowInfoHelp;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: IWmsLoanWorkFlowService
 * 模块名称：赎回流程接口和调整债权流程接口
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月29日
 * @version V1.0
 * history:
 * 1、2016年12月29日 baisong 创建文件
 */
public interface IWmsLoanWorkFlowService
{
    /**
     * 覆盖:赎回流程，债权调整流程，理财上单流程
     * 实现:流程启动公共方法
     * @param userId 用户ID
     * @param businessId 业务单据ID
     * @param salesman_dept_id 上单业务员部门ID
     * @param salesman_id 业务员ID
     * @param processDefinitionKey 流程实例key
     * @author hancd
     */
    public abstract String startFinancialWorkFlow(String userId, String businessId, String salesman_dept_id,String salesman_id,String processDefinitionKey);
    /**
     * 复议申请启动新房贷流程2016-9-26
     * 实现:流程启动公共方法  不同的PERFECTHOUSINGLOANPROCESS和 UPHOUSINGLOANPROCESS俩个版本流程都使用一个复议申请方法
     * @param userId 用户ID
     * @param businessId 业务单据ID
     * @param salesman_dept_id 上单业务员部门ID
     * @param salesman_id 业务员ID
     * @param processDefinitionKey 流程实例key
     * @author baisong
     */
    public abstract String againStartFinancialWorkFlow(WmsHouseCreditWorkFlowVO wDebtWorkFlowVO);

    /**
     * 覆盖:赎回流程，债权调整流程,理财上单流程
     * 实现公共流程获取IdList,taskList方法
     * @param userId 用户ID
     * @param processDefinitionKey 流程实例Key
     * @param invekey 任务节点key
     * 当invekey=1 代表贷款复核
     * 当invekey=2 代表重新申请
     * 当invekey=3 代表初评预估
     * 当invekey=4 代表办件验房
     * 当invekey=5 代表初评补件/办件补件
     * 当invekey=6 代表待贷款终审
     * 当invekey=7 代表待签订合同
     * 当invekey=8 代表待公正
     * 当invekey=9 代表待他项
     * 当invekey=10 代表待放款申请
     * 当invekey=11 代表待放款申请审批
     * 当invekey=12 代表待放款确认
     * @return Map
     * @author baisong
     */
    public abstract Map<String, Object> getWorkFlowToIdList(String processDefinitionKey,String userId,String invekey);
    /**
     * 覆盖:赎回流程，债权调整流程,理财上单流程
     * 实现公共流程获取IdList,taskList方法
     * @param userId 用户ID
     * @param processDefinitionKey 流程实例Key
     * @param invekey 任务节点key
     * 当invekey=5 代表初评补件/办件补件
     * @return Map
     * @author baisong
     */
    public abstract Map<String, Object> getWorkFlowToIdListNoUser(String processDefinitionKey,String userId,String invekey);
	/**
	 * @deprecated 房贷新旧流程 获取taskid 和idlist合并
	 * @param parmMap 旧流程参数
	 * @param userId
	 * @param invekey //流程
	 * @return map
	 * 	baisong
	 */
    public abstract Map<String, Object> setTaskList(Map<String, Object> parmMap,Integer userId,String invekey,String processDefinitionKey);
    /**
	 * 覆盖:赎回流程,债权调整流程,理财上单流程
	 * 将所有查询到结果集添加到页面显示列表 增添任务taskId属性
	 * @param list 查询业务数据集合
	 * @param idLists 查询流程业务主键集合
	 * @param taskIdLists 查询流程业务主键对应的流程任务集合
	 * @param processDefinitionKey 流程实例key
	 */
    public abstract List<Map<String,Object>> addTaskIdToList(List<Map<String, Object>> list, List<Integer> idLists,List<String> taskIdLists,String processDefinitionKey);
    
    /**
     * 覆盖:赎回流程，债权调整流程,理财上单流程 历程查看功能
     * @param processDefinitionKey 流程实例key
     * @param businessKey 每个单据主表ID
     * @author hancd
     */
    public abstract Map<String, Object> inveWorkFlowProcess(String processDefinitionKey,String businessKey);
    
    /**
	 * 涵盖:赎回流程，债权调整流程,理财上单流程
	 * 实现所有流程所有节点的审批操作同时改变所有操作后的单据状态变化
	 * processDefinitionKey=financialRedemptionProcess;//赎回流程
	 * @param debtkey 任务节点key
			当debtkey=1 代表复核
	 *      当debtkey=2 代表重新申请
	 * 		当debtkey=3 代表初评预估
	 * 		当debtkey=4 代表办件验房
     * 		当debtkey=5 代表贷款终审
     * 		当debtkey=6 代表放款准备合同
     * 		当debtkey=7 代表放款准备公证
     * 		当debtkey=8 代表放款准备他项
     * 		当debtkey=9 代表待放款审核--放款申请
     * 		当debtkey=10 代表放款审核
     * 		当debtkey=11 代表放款
     * 		当debtkey=12 代表补件
     * @return 
	 * @author baisong
	 */
    public abstract void publicApproval(WmsHouseCreditWorkFlowVO wDebtWorkFlowVO);

    /**
     * 
     * @Title: publicApproval
     * @Description: TODO(状态流转控制类)
     * @param wDebtStatusVO
     * @return 
     * @author: baisong
     * @time:2017年2月17日 下午2:35:19
     * history:
     * 1、2017年2月17日 baisong 创建方法
     */
    public abstract Map<String, Object> publicApprovalStatus(WmsHouseCreditWorkFlowVO wDebtStatusVO);
	/**
	 * 房贷流程中草稿 完善退回 补件 流程控制
	 * @param userId
	 * @param wms_cre_credit_head_id
	 * @param key  标示哪个节点调用此方法
	 * baisong
	 */
    public abstract void houseCreditInquiryTreatment(String userId, String wms_cre_credit_head_id, String key);
    
	/**
	 * @deprecated 房贷新旧流程 获取taskid 和idlist合并--给补件
	 * @param parmMap 旧流程参数
	 * @param userId
	 * @param invekey //流程
	 * @return map
	 * 	baisong
	 */
    public abstract Map<String, Object> setTaskListBJ(Map<String, Object> parmMap,Integer userId,String invekey );
    
	/**
	 * @deprecated 房贷新流程 获取taskid 和idlist合并
	 * @param parmMap
	 * @param userId
	 * @param invekey //流程
	 * @return map
	 * 	baisong
	 */
    public abstract Map<String, Object> setTaskListVerTwo(Map<String, Object> parmMap,Integer userId,String invekey );
	/**
	 * 签订合同他项公正作废
	 * @param userId
	 * @param wms_cre_credit_head_id
	 * @param key
	 */
    public String houseCncel(String userId, String wms_cre_credit_head_id, String key);
    /**
	 * 获取某个节点的taskid
	 * @param userId 人员id
	 * @param BusinessKey  单据主键
	 * @param processDefinitionKey 流程key
	 * @param taskName 流程节点名称
	 * @return  List<WorkflowInfoHelp>
	 * @date 2016/5/10
	 * @author baisong
	 */
    public  List<WorkflowInfoHelp> getTaskId(String userId, String wms_cre_credit_head_id, String processDefinitionKey,String taskName);
    /**
	 * 涵盖:赎回流程，债权调整流程,理财上单流程
	 * 实现所有流程所有节点的审批操作同时改变所有操作后的单据状态变化
	 * processDefinitionKey=financialRedemptionProcess;//赎回流程
	 * @param debtkey 任务节点key
			当debtkey=1 代表复核
	 *      当debtkey=2 代表重新申请
	 * 		当debtkey=3 代表初评预估
	 * 		当debtkey=4 代表办件验房
     * 		当debtkey=5 代表贷款终审
     * 		当debtkey=6 代表放款准备合同
     * 		当debtkey=7 代表放款准备公证
     * 		当debtkey=8 代表放款准备他项
     * 		当debtkey=9 代表待放款审核--放款申请
     * 		当debtkey=10 代表放款审核
     * 		当debtkey=11 代表放款
     * 		当debtkey=12 代表补件
     * @return 
	 * @author baisong
	 */
    public abstract String publicApprovalPhone(WmsHouseCreditWorkFlowVO wDebtWorkFlowVO);
    /**
     * 实现公共流程获取IdList,taskList方法
     * @param userId 用户ID
     * @param processDefinitionKey 流程实例Key
     * @param invekey 任务节点key
     * 当invekey=0 代表获取所有流程的退件原因
     * @param  String ver版本
     * @return Map
     * @author baisong
     */
    public Map<String, Object> getWorkFlowToIdListNoUserVerTwo(String processDefinitionKey,String userId,String invekey,String ver);
    /**
     * 获取流程版本号
     * @param taskId
     * @return String 
     * @author  baisong
     * @date 2016-10-17
     */
    public String getVersion_(String taskId);
}
