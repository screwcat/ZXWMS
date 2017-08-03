package com.zx.emanage.workflow.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveRedeemPrincipalDetailDao;
import com.zx.emanage.inve.persist.WmsInveTransaApprovalInfoDao;
import com.zx.emanage.inve.persist.WmsInveTransaCardDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.util.gen.entity.WmsInveRedeemPrincipalDetail;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaApprovalInfo;
import com.zx.emanage.util.gen.entity.WmsInveTransaCard;
import com.zx.sframe.util.GlobalVal;
/** 
 * execute方法的参数DelegateExecution execution可以在流程中各个结点之间传递流程变量。  
 *自动执行  
	<serviceTask>元素，可以实现自动活动，语法如下所示：  
	<serviceTask id="serviceTaskId" name="serviceTaskName"  
	activiti:class="org.shirdrn.workflow.activiti.gateway.ServiceTaskClass"/>  
	其中，activiti:class属性为该结点对应的处理类，该类要求实现org.activiti.engine.delegate.JavaDelegate接口  
 */
@Service("workserviceflowService")
@Transactional
public class WorkserviceflowServiceImpl implements JavaDelegate{  
	
    private final Logger log = Logger.getLogger(WorkserviceflowServiceImpl.class.getName());  
 
	@Override  
    public void execute(DelegateExecution execution) throws Exception {  
        Thread.sleep(10000);  
        log.info("variavles=" + execution.getVariables());  
        execution.setVariable("支付倒计时作废", "支付倒计时作废。");  
       // System.out.println("variavles=" +execution.getVariables());
       // System.out.println("businessKey=" +execution.getBusinessKey());
        WmsInveTransaApprovalInfoDao wmsInveTransaApprovalInfoDao = (WmsInveTransaApprovalInfoDao) GlobalVal.ctx.getBean("wmsInveTransaApprovalInfoDao");
        WmsInveTransaDao wmsInveTransaDao=(WmsInveTransaDao) GlobalVal.ctx.getBean("wmsInveTransaDao");
        WmsInveTransaCardDao wmsInveTransaCardDao = (WmsInveTransaCardDao) GlobalVal.ctx.getBean("wmsInveTransaCardDao");
        WmsInveRedeemPrincipalDetailDao wmsInveRedeemPrincipalDetailDao =  (WmsInveRedeemPrincipalDetailDao) GlobalVal.ctx.getBean("wmsInveRedeemPrincipalDetailDao");
        Timestamp systime=new Timestamp(System.currentTimeMillis());
        //待支付的单据定时作废,作废的同时需要释放续单本金的金额
        Integer wms_inve_transa_id = Integer.valueOf(execution.getBusinessKey());
        WmsInveTransa wmsInveTransaInFo = wmsInveTransaDao.get(wms_inve_transa_id);
        int remainTime=(int) (wmsInveTransaInFo.getLast_update_timestamp().getTime() + 1000 * 60 * 60 * 24 - System.currentTimeMillis());
        if(remainTime <=0){
    	   //支付超时支付信息失效
        	List<Map<String, Object>> wmsInveTransaCardMap = wmsInveTransaCardDao.getWmsInveTransaCardInfoByWmsInveTransaId(wms_inve_transa_id);
        	Timestamp  sysTime = new Timestamp(System.currentTimeMillis());
        	for(Map<String, Object> map : wmsInveTransaCardMap){
        		
        		if("3".equals("" + map.get("pay_type"))){
        			if("1".equals("" + map.get("is_valid"))){
        				int wms_inve_redeem_principal_detail_id = (int)map.get("wms_inve_redeem_principal_detail_id");
                		
                		WmsInveRedeemPrincipalDetail wmsInveRedeemPrincipalDetail = new WmsInveRedeemPrincipalDetail();
                		wmsInveRedeemPrincipalDetail.setWms_inve_redeem_principal_detail_id(wms_inve_redeem_principal_detail_id);
                		wmsInveRedeemPrincipalDetail.setUsed_income_amount(new BigDecimal("" + map.get("act_account")));
                		wmsInveRedeemPrincipalDetailDao.updateToUsedIncomeAmount(wmsInveRedeemPrincipalDetail);
        			}
        		}
        		
        		WmsInveTransaCard wmsInveTransaCard = new WmsInveTransaCard();
        		wmsInveTransaCard.setIs_valid("0");
            	wmsInveTransaCard.setWms_inve_transa_card_id((int)map.get("wms_inve_transa_card_id"));
            	wmsInveTransaCard.setLast_update_user_id(0);
            	wmsInveTransaCard.setLast_update_timestamp(sysTime);
                wmsInveTransaCard.setEnable_flag("0");
            	wmsInveTransaCardDao.update(wmsInveTransaCard);
        	}
        }
        //上单信息表
        //WmsInveTransa wmsInveTransa= wmsInveTransaDao.get(Integer.valueOf(execution.getBusinessKey()));
        WmsInveTransa wmsInveTransa=new WmsInveTransa();
        wmsInveTransa.setWms_inve_transa_id(Integer.valueOf(execution.getBusinessKey()));
        wmsInveTransa.setData_status("7");//7已终止
        wmsInveTransa.setLast_update_user_id(0);//上次修改人 0标示系统自动作废
        wmsInveTransa.setLast_update_timestamp(systime);//上次修改时间
        wmsInveTransaDao.update(wmsInveTransa);
        //上单流程审批表
        WmsInveTransaApprovalInfo wmsInveTransaApprovalInfo=new WmsInveTransaApprovalInfo();
        wmsInveTransaApprovalInfo.setWms_inve_transa_id(Integer.valueOf(execution.getBusinessKey()));
        wmsInveTransaApprovalInfo.setRevoke_id(0);//上单撤销操作人id
        wmsInveTransaApprovalInfo.setRevoke_name("WMS系统");//上单撤销操作人名称
        wmsInveTransaApprovalInfo.setRevoke_advice("支付超时系统自动撤销");//上单撤销理由
        wmsInveTransaApprovalInfo.setRevoke_time(systime);
        wmsInveTransaApprovalInfoDao.update(wmsInveTransaApprovalInfo);
        //wmsInveTransaApprovalInfoDao.updateAdvice(wmsInveTransaApprovalInfo);
        
        log.info("支付倒计时作废");  
    }  
}  