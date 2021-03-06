package com.zx.emanage.inve.service;

import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.emanage.inve.vo.WmsInveSalarySetWorkFlowVO;

/**
 * 赎回流程接口和调整债权流程接口
 * 
 * @author hancd
 */
public interface IWmsInveWorkFlowService
{
    /**
     * 覆盖:赎回流程，债权调整流程，理财上单流程
     * 实现:流程启动公共方法
     * @param userId 用户ID
     * @param businessId 业务单据ID
     * @param salesman_dept_id 上单业务员部门ID
     * @param salesman_id 业务员ID
     * @param processDefinitionKey 流程实例key
     * @param wms_inve_transa_id 上单信息表主键ID
     * @author hancd
     */
    public abstract String startFinancialWorkFlow(String userId, String businessId, String salesman_dept_id,String salesman_id,String processDefinitionKey,Integer... wms_inve_transa_id);

    /**
     * 覆盖:赎回流程，债权调整流程,理财上单流程
     * 实现公共流程获取IdList,taskList方法
     * @param userId 用户ID
     * @param processDefinitionKey 流程实例Key
     * processDefinitionKey=financialRedemptionProcess;//赎回流程
     * @param invekey 任务节点key
     * 当invekey=1 待修订 
     * 当invekey=2 待回款以及特批
     * 当invekey=3 代表三级审批
     * processDefinitionKey=debtAdjustmentProcess;//债权调整流程
     * @param invekey 任务节点key
     * 当invekey=1 代表三级审批
     * 当invekey=2 代表修订
     * 当invekey=4 代表确认和特批申请 
     * 当invekey=5 代表债权调整和协议打印
     * processDefinitionKey=financialSingleProcess;//理财上单流程
     * @param invekey 任务节点key
     * 当invekey=1 代表待复核
     * 当invekey=2 代表待支付
     * 当invekey=3 代表待审核
     * 当invekey=4 代表待签约/待确认
     * 当invekey=5 代表待复核修订
     * 当invekey=6 代表待支付退回
     * 当invekey=7 代表待审核退回
     * 当invekey=8 代表待退单申请
     * 当invekey=9 代表待退单确认
     * @return Map
     */
    public abstract Map<String, Object> getWorkFlowToIdList(String processDefinitionKey,String userId,String invekey);

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
	 *       当debtkey=1 代表团队经理审批
	 *       当debtkey=2 代表副总经理审批
	 *       当debtkey=3 代表总经理审批
	 *       当debtkey=4 代表修订
	 *       当debtkey=5 代表确认/回款
	 *       当debtkey=6 代表特批申请
	 * processDefinitionKey=debtAdjustmentProcess;//债权调整流程 
	 * @param debtkey 任务节点key  
	 *       当debtkey=1 代表团队经理审批
	 *       当debtkey=2 代表副总经理审批
	 *       当debtkey=3 代表总经理审批
	 *       当debtkey=4 代表修订
	 *       当debtkey=5 代表确认/回款
	 *       当debtkey=6 代表特批申请
	 *       当debtkey=7 代表债权调整
	 *       当debtkey=8 代表协议签订
     * processDefinitionKey=financialSingleProcess;//理财上单流程
     * @param debtkey 任务节点key
     *      当debtkey=1 代表复核
	 *      当debtkey=2 代表支付
	 * 		当debtkey=3 代表主管确认
	 * 		当debtkey=4 代表协议签订
     * 		当debtkey=5 代表客户确认
     * 		当debtkey=6 代表复核修订
     * 		当debtkey=7 代表支付退回
     * 		当debtkey=8 代表审核退回
     * 		当debtkey=9 代表退单申请
     * 		当debtkey=10 代表退单确认
     * @return 
	 * @author hancd
	 */
    public abstract void publicApproval(WmsInveDebtWorkFlowVO wDebtWorkFlowVO,Integer... wms_inve_transa_id);

    /**
     * 
     * @Title: startSalarSetProcess
     * @Description: 启动工资设定流程
     * @param userId 当前登录人id
     * @param businessId 工资——前提配置总表主键
     * @param create_user_id 当前单据创建人id
     * @return 
     * @author: DongHao
     * @time:2017年1月8日 下午5:04:02
     * history:
     * 1、2017年1月8日 DongHao 创建方法
     */
    public abstract String startSalarSetProcess(String userId, String businessId, String create_user_id);

    /**
     * 
     * @Title: getSalarSetWorkFlowToIdList
     * @Description: 
     * @param userId
     * @param invekey
     * @return 
     * @author: DongHao
     * @time:2017年1月8日 下午5:33:20
     * history:
     * 1、2017年1月8日 DongHao 创建方法
     */
    public abstract Map<String, Object> getSalarSetWorkFlowToIdList(String userId, String invekey);

    /**
     * 
     * @Title: inveSalarySetAudit
     * @Description: 执行审批操作
     * @param vo 
     * @author: DongHao
     * @time:2017年1月27日 下午1:10:59
     * history:
     * 1、2017年1月27日 DongHao 创建方法
     */
    public abstract void inveSalarySetAudit(WmsInveSalarySetWorkFlowVO vo);

    /**
     * 
     * @Title: getSignedProcessTaskId
     * @Description: 撤单
     * @param wms_inve_transa_id
     * @param taskName
     * @return 
     * @author: DongHao
     * @time:2017年2月13日 下午6:43:48
     * history:
     * 1、2017年2月13日 DongHao 创建方法
     */
    public abstract String getSignedProcessTaskId(String wms_inve_transa_id, String taskName);

    /**
     * @Title: getSalarySetProcessInfos
     * @Description: 根据流程名称和业务主键进行查询流程历程信息
     * @param invesalaryset 流程名称
     * @param wms_inve_salary_pre_total_id 业务主键
     * @return 返回流程流程信息
     * @author: DongHao
     * @time:2017年5月1日 下午10:28:42
     * history:
     * 1、2017年5月1日 DongHao 创建方法
    */
    public abstract Map<String, Object> getSalarySetProcessInfos(String invesalaryset, Integer wms_inve_salary_pre_total_id);
    
    /**
     * 
     * @Title: startPadRedeemProcess
     * @Description: pad端启动赎回流程
     * @param userId 当前登录人id
     * @param businessId 当前赎回单据id
     * @param processDefinitionKey 流程名称
     * @author: DongHao
     * @time:2017年6月1日 下午3:13:36
     * history:
     * 1、2017年6月1日 DongHao 创建方法
     */
    public abstract void startPadRedeemProcess(String userId, String businessId, String processDefinitionKey);
}
