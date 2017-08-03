package com.zx.emanage.workflow.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.IdentityLink;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.workflow.service.impl.WorkflowServiceImpl;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.emanage.workflow.util.WorkflowHistoryInfoHelp;
import com.zx.emanage.workflow.util.WorkflowSearchInfoHelp;
import com.zx.sframe.util.GlobalVal;

/**
 * 流程  监听器---新房贷流程 #####代码现在作废
 * 
 * @author baisong
 */
@Service
@Transactional
public class BeforeHousingAppProcessor implements ExecutionListener
{
    private static final long serialVersionUID = 1L;
	@Override
	public void notify(DelegateExecution arg0) throws Exception {
		 //delegateTask.setVariable( "pass", "true" );
		//Thread.sleep(10000); 
    	String	businessKey =arg0.getProcessBusinessKey();
    	// 流程历程查看
        Map<String, Object> paMap = new HashMap<String, Object>();
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 设置流程实例key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
        workflowSearchInfoHelp.setFinish(true);// 查询完成
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
        arg0.setVariable( "pass", pass);
	}
}