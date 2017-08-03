package com.zx.emanage.inve.service;

import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveSalaryPreTotalSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveSalarySetWorkFlowVO;
import com.zx.emanage.util.gen.entity.WmsInveSalaryPreTotal;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: IWmsInveSalaryPreTotalService
 * 模块名称：
 * @Description: 内容摘要：
 * @author DongHao
 * @date 2017年1月7日
 * @version V1.0
 * history:
 * 1、2017年1月7日 DongHao 创建文件
 */
public interface IWmsInveSalaryPreTotalService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveSalaryPreTotalSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveSalaryPreTotalSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveSalaryPreTotalVO
	 * @author auto_generator
	 */	
	public WmsInveSalaryPreTotal getInfoByPK(Integer wms_inve_salary_pre_total_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveSalaryPreTotal bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveSalaryPreTotal bean, UserBean user);

    /**
     * @Title: getTeamLis
     * @Description: 获取所属团队信息
     * @param user
     * @return 
     * @author: DongHao
     * @time:2017年1月7日 下午5:18:53
     * history:
     * 1、2017年1月7日 DongHao 创建方法
    */
    public List<Map<String, Object>> getTeamLis(UserBean user, String menu_id);

    /**
     * @Title: getPerformanceSalarySettingInfos
     * @Description: 获取工资设定单列表信息
     * @param user
     * @param queryInfo
     * @return 
     * @author: DongHao
     * @time:2017年1月7日 下午5:54:06
     * history:
     * 1、2017年1月7日 DongHao 创建方法
    */
    public Map<String, Object> getPerformanceSalarySettingInfos(UserBean user, WmsInveSalaryPreTotalSearchBeanVO queryInfo);

    /**
     * @Title: getPerformanceSalaryAuditInfos
     * @Description: 获取待审核的单据
     * @param user
     * @param queryInfo
     * @return 
     * @author: DongHao
     * @time:2017年1月9日 上午9:12:53
     * history:
     * 1、2017年1月9日 DongHao 创建方法
    */
    public Map<String, Object> getPerformanceSalaryAuditInfos(UserBean user, WmsInveSalaryPreTotalSearchBeanVO queryInfo);

    /**
     * @Title: getDataStatus
     * @Description: 获取工资设定状态信息
     * @param wms_sys_dict_id
     * @param user
     * @return 
     * @author: DongHao
     * @time:2017年1月12日 上午10:50:04
     * history:
     * 1、2017年1月12日 DongHao 创建方法
    */
    public List<Map<String, Object>> getDataStatus(String wms_sys_dict_id, UserBean user);

    /**
     * @Title: inveSalarySetAuditInfos
     * @Description: 工资设定单执行审核操作
     * @param vo
     * @param user
     * @return 
     * @author: DongHao
     * @time:2017年1月12日 下午3:20:48
     * history:
     * 1、2017年1月12日 DongHao 创建方法
    */
    public Map<String, Object> inveSalarySetAuditInfos(WmsInveSalarySetWorkFlowVO vo, UserBean user);

    /**
     * @Title: getJobTime
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @return 
     * @author: DongHao
     * @time:2017年1月17日 下午5:08:54
     * history:
     * 1、2017年1月17日 DongHao 创建方法
    */
    public Map<String, Object> getJobTime();

    /**
     * @Title: getGeneralAdvice
     * @Description: 根据绩效工资设定总表获取待修订状态的审批意见
     * @param wms_inve_salary_pre_total_id
     * @return 
     * @author: DongHao
     * @time:2017年1月4日 下午2:53:50
     * history:
     * 1、2017年1月4日 DongHao 创建方法
    */
    public Map<String, Object> getGeneralAdvice(String wms_inve_salary_pre_total_id);
    
    /**
     * @Title: getSalaryPendingApproval
     * @Description: 根据当前登录人的id获取绩效工资待我审批的单据
     * @param personnel_id 当前登录moa系统的人员id
     * @return 返回待我审批的绩效工资的单据
     * @author: DongHao
     * @time:2017年4月10日 上午2:13:59
     * history:
     * 1、2017年4月10日 DongHao 创建方法
    */
    public List<Map<String, Object>> getSalaryPendingApproval(Integer personnel_id);
    
    /**
     * @Title: getSalaryBusinessInfos
     * @Description: 根据查询条件参数进行获取绩效工资的相关业务单据信息
     * @param paramMap 查询参数map集合
     * @return 返回绩效工资的相关业务单据信息
     * @author: DongHao
     * @time:2017年4月10日 上午7:46:58
     * history:
     * 1、2017年4月10日 DongHao 创建方法
    */
    public List<Map<String, Object>> getSalaryBusinessInfos(Map<String, Object> paramMap);

    /**
     * 
     * @Title: getSalarySetProcessInfos
     * @Description: 获取工资设定的流程历程信息
     * @param wms_inve_salary_pre_total_id 工资设定表的总表主键
     * @return 返回map集合流程历程信息
     * @author: DongHao
     * @time:2017年5月1日 下午10:23:48
     * history:
     * 1、2017年5月1日 DongHao 创建方法
     */
    public Map<String, Object> getSalarySetProcessInfos(Integer wms_inve_salary_pre_total_id);

    /**
     * @Title: getPerformanceSalaryApproveInfosMoa
     * @Description: 获取绩效工资审核单据详情
     * @param wms_inve_salary_pre_total_ids 单据主键集合 以逗号分隔
     * @param bill_attr
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月19日 上午10:58:47
     * history:
     * 1、2017年4月19日 Administrator 创建方法
    */
    public List<Map<String, Object>> getPerformanceSalaryApproveInfosMoa(String wms_inve_salary_pre_total_ids, String bill_attr);

    /**
     * @Title: reSubmitSalarySetProcess
     * @Description: 工资设定流程重新提交申请待修订状态的单据
     * @param userId 当前登录人id
     * @param wms_inve_salary_pre_total_id 工资设定业务id
     * @param create_user_id 创建人id
     * @author: DongHao
     * @time:2017年4月1日 下午9:07:56
     * history:
     * 1、2017年4月1日 DongHao 创建方法
    */
    public void reSubmitSalarySetProcess(String userId, String wms_inve_salary_pre_total_id, String create_user_id);
}