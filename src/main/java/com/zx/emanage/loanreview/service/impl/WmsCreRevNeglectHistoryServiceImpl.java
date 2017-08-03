package com.zx.emanage.loanreview.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.service.IWmsHouseCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanreview.persist.WmsCreRevNeglectHistoryDao;
import com.zx.emanage.loanreview.service.IWmsCreRevNeglectHistoryService;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsCreRevNeglectHistory;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.emanage.loanreview.vo.WmsCreRevNeglectHistorySearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevneglecthistoryService")
public class WmsCreRevNeglectHistoryServiceImpl implements IWmsCreRevNeglectHistoryService {
	private static Logger log = LoggerFactory.getLogger(WmsCreRevNeglectHistoryServiceImpl.class);

	@Autowired
	private WmsCreRevNeglectHistoryDao wmscrerevneglecthistoryDao;
	
	@Autowired
    private IWmsHouseCreditWorkFlowService houseCreditWorkFlowService;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private PmPersonnelDao pmpersonnelDao;// hr人员信息dao
    
    @Autowired
    private WmsCreCreditHeadDao wmscrecreditheadDao;
	@Autowired
	private IWmsLoanWorkFlowService   wmsLoanWorkFlowService;//新房贷贷款流程

	@Override
	public Map<String, Object> getListWithoutPaging(WmsCreRevNeglectHistorySearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmscrerevneglecthistoryDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsCreRevNeglectHistorySearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmscrerevneglecthistoryDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrerevneglecthistoryDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCreRevNeglectHistory getInfoByPK(Integer wms_cre_rev_neglect_history_id) {
		return wmscrerevneglecthistoryDao.get(wms_cre_rev_neglect_history_id);
	}

	@Override	
	@Transactional
	public String save(WmsCreRevNeglectHistory bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrerevneglecthistoryDao.save(bean); 
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCreRevNeglectHistory bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrerevneglecthistoryDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCreRevNeglectHistory() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreRevNeglectHistory> getListByEntity(WmsCreRevNeglectHistory queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreRevNeglectHistory> beanList = wmscrerevneglecthistoryDao.getListByEntity(queryInfo);
		return beanList;
	}
	
	/**
	 * 房贷审核忽略 
	 */
	@Transactional
	public String creHousingIgonre(Integer taskId, Integer wms_cre_credit_head_id, UserBean user, String flag) {
		
		Task task = taskService.createTaskQuery().taskId(String.valueOf(taskId)).singleResult();
		PmPersonnel person = pmpersonnelDao.get(user.getUserId());
		//忽略信息
		WmsCreRevNeglectHistory wmsCreRevNeglectHistory = new WmsCreRevNeglectHistory();
		wmsCreRevNeglectHistory.setWms_cre_credit_head_id(wms_cre_credit_head_id);
		wmsCreRevNeglectHistory.setApproval_link(task.getName());//审批环节
		wmsCreRevNeglectHistory.setApprovel_use_name(user.getRealname());//审批人
		wmsCreRevNeglectHistory.setApprovel_id(user.getUserId());//审批人ID
		wmsCreRevNeglectHistory.setApprovel_dept_id(user.getDeptId());//审批人部门ID
		wmsCreRevNeglectHistory.setApprovel_dept_name(user.getDeptSimpleName());//审批人部门
		wmsCreRevNeglectHistory.setPersonnel_postname(person.getPersonnel_postname());//审批人职务：角色职务
		wmsCreRevNeglectHistory.setApprovel_result("2");//审批结果：0代表不通过  1代表通过  2代表忽略
		wmsCreRevNeglectHistory.setApprovel_idea("忽略审核");//审批意见
		wmsCreRevNeglectHistory.setApprovel_datetime(new Timestamp(System.currentTimeMillis()));//审批时间
		wmsCreRevNeglectHistory.setCreate_datetime(new Timestamp(System.currentTimeMillis()));//单据创建时间
		wmsCreRevNeglectHistory.setEnable_flag("1");//数据状态:0代表失效  1代表有效
		int count = wmscrerevneglecthistoryDao.save(wmsCreRevNeglectHistory);
		if(count==0){
			throw new RuntimeException("保存失败");
		}
		//贷款主表流程状态修改
		Map<String, Object> paramMap = new HashMap<String ,Object>();
		paramMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
		if("ls".equals(flag)) {//流水
			paramMap.put("water_appro_result", "3");
			paramMap.put("water_appro_result_page", "3");
    	} else if("xd".equals(flag)) {//信调
    		paramMap.put("info_appro_result", "3");
    		paramMap.put("info_appro_result_page", "3");
    	} else if("ds".equals(flag)) {//电审
    		paramMap.put("phone_appro_result", "3");
    		paramMap.put("phone_appro_result_page", "3");
    	}
		int updCount = wmscrecreditheadDao.updateApproResult(paramMap);
		if(updCount==0){
			throw new RuntimeException("保存失败");
		}
		if(count > 0 && updCount > 0) {
			//插入成功执行流程
			WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO = new WmsHouseCreditWorkFlowVO();
			approveHouseWorkFlowVO.setTaskId(String.valueOf(taskId));
			approveHouseWorkFlowVO.setWms_cre_credit_head_id(String.valueOf(wms_cre_credit_head_id));
			approveHouseWorkFlowVO.setUserId(String.valueOf(user.getUserId()));
	        //数据来源1为pc 2为移动端
	        if("2".equals(approveHouseWorkFlowVO.getVersion_number())){//2016/5/10版本
	        	approveHouseWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.UPHOUSINGLOANPROCESS);
	        	approveHouseWorkFlowVO.setDebtkey("15");//电审
	        	approveHouseWorkFlowVO.setPass("true");
	        	approveHouseWorkFlowVO.setAdvice("忽略");
	        	wmsLoanWorkFlowService.publicApproval(approveHouseWorkFlowVO);
	        }else{
	        	houseCreditWorkFlowService.houseCreditApprovalIgonre(approveHouseWorkFlowVO);
	        }   
			return "success";
		}
		return "error";
	}
}
