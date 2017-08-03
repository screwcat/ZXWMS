package com.zx.emanage.workflow.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;  

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveTransaApprovalInfoDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.sysmanage.persist.SysUserDao;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaApprovalInfo;
import com.zx.emanage.workflow.service.IWorkflowService;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.emanage.workflow.util.WorkflowHistoryInfoHelp;
import com.zx.emanage.workflow.util.WorkflowSearchInfoHelp;
import com.zx.sframe.util.GlobalVal;
/** 
 * execute方法的参数DelegateExecution execution可以在流程中各个结点之间传递流程变量。  
 *自动执行  
	<serviceTask>元素，可以实现自动活动，语法如下所示：  
	<serviceTask id="serviceTaskId" name="serviceTaskName"  
	activiti:class="org.shirdrn.workflow.activiti.gateway.ServiceTaskClass"/>  
	其中，activiti:class属性为该结点对应的处理类，该类要求实现org.activiti.engine.delegate.JavaDelegate接口  
	
	新房贷流程---此代码已作废
	baisong
 */
@Service
@Transactional
public class WorkserviceflownullifyServiceImpl implements JavaDelegate{  
	
    private final Logger log = Logger.getLogger(WorkserviceflownullifyServiceImpl.class.getName());  
 
	@Override  
    public void execute(DelegateExecution execution) throws Exception {  
        Thread.sleep(1000);  
        log.info("variavles=" + execution.getVariables());  
    	String	businessKey =execution.getBusinessKey();
    	// 流程历程查看
        Map<String, Object> paMap = new HashMap<String, Object>();
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 设置流程实例key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
        //workflowSearchInfoHelp.setFinish(true);// 查询完成
        workflowSearchInfoHelp.setBusinessKey(businessKey);//流程中单据主键
        IWorkflowService workflowService=(IWorkflowService) GlobalVal.ctx.getBean("workflowService");
        // 读取流程返回的结果
        List<WorkflowHistoryInfoHelp> workflowHistoryInfoHelps = workflowService.findPackageHistoricTaskInstances(workflowSearchInfoHelp);
        String pass ="true";
        for(WorkflowHistoryInfoHelp workhep : workflowHistoryInfoHelps ){
		    if(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_QDHT.equals(workhep.getTaskName())){//合同签订
		    	if("nullify".equals(workhep.getApproveResult())){
		    		pass="nullify";
		    	}
		    }
			if(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_GZ.equals(workhep.getTaskName())){//公正
				if("nullify".equals(workhep.getApproveResult())){
		    		pass="nullify";
		    	}			   
			}
			if(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_TX.equals(workhep.getTaskName())){//他想
				if("nullify".equals(workhep.getApproveResult())){
		    		pass="nullify";
		    	} 
			}
	   }
        execution.setVariable( "pass", pass);
    }  
}  