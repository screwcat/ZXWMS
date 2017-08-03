package com.zx.emanage.loancheck.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.activiti.engine.ActivitiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.cremanage.persist.WmsCreHousingApprovalInfoDao;
import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.cremanage.service.IWmsHouseCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreCreditHeadSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loancheck.service.IWmsCreHousingCheckService;
import com.zx.emanage.loancheck.vo.WmsCreHousingCheckSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreHousingAssessment;
import com.zx.emanage.util.gen.entity.WmsCreHousingCheck;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreHousingCheckController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreHousingCheckController.class);

    @Autowired
    private IWmsCreHousingCheckService wmscrehousingcheckService;

    @Autowired
    private IWmsCreCreditHeadService wmscrecreditheadService;

    @Autowired
    private IWmsHouseCreditWorkFlowService approveHouseCreditWorkFlowService;
    @Autowired
    private IWmsLoanWorkFlowService   wmsLoanWorkFlowService;//新房贷贷款流程

    @Autowired
    private WmsCreHousingApprovalInfoDao wmsCreHousingApprovalInfoDao;// 审批信息表

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrehousingcheckwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                           RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreHousingCheckSearchBeanVO queryInfo)
    {
        return wmscrehousingcheckService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrehousingcheckwithpaginglist.do", method = { RequestMethod.GET,
                                                                                        RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreHousingCheckSearchBeanVO queryInfo)
    {
        return wmscrehousingcheckService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreHousingCheckVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrehousingcheckinfo.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public WmsCreHousingCheck getInfo(Integer wms_cre_credit_head_id)
    {
        WmsCreHousingCheck c = wmscrehousingcheckService.getInfo(wms_cre_credit_head_id);
        return wmscrehousingcheckService.getInfo(wms_cre_credit_head_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author wangyishun
     */
    @RequestMapping(value = "/loancheck/wmscrehousingchecksave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreHousingCheck bean,
                         WmsCreHousingAssessment wmsCreHousingAssessment, WmsHouseCreditWorkFlowVO vo, String fileArr,String fileArryf)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrehousingcheckService.save(bean, wmsCreHousingAssessment, user, vo, fileArr, fileArryf);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "贷前管理-房贷审核-办件审核组-审核";
            SysTools.saveLog(request, msg);
        }
        return result;
    }
    
    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author wangyishun
     */
    @RequestMapping(value = "/loancheck/wmscrehousingchecksaveForCompleted.do", method = { RequestMethod.POST })
    @ResponseBody
    public String WmsCreHousingCheckSaveForCompleted(HttpServletRequest request, WmsCreHousingCheck bean,
                         WmsCreHousingAssessment wmsCreHousingAssessment, WmsHouseCreditWorkFlowVO vo, String fileArr,String fileArryf)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrehousingcheckService.WmsCreHousingCheckSaveForCompleted(bean, wmsCreHousingAssessment, user, vo, fileArr, fileArryf);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "贷前管理-房贷审核-办件审核组-完善信息";
            SysTools.saveLog(request, msg);
        }
        return result;
    }

    /**
     * Description :update method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrehousingcheckupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreHousingCheck bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrehousingcheckService.update(bean, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        /*
         * // record log if("success".equals(result)){ String msg =
         * "log content"; SysTools.saveLog(request, msg); // record log method }
         */
        return result;
    }

    /**
     * 办件审核列表
     * 
     * @param queryInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/loancheck/crehousingbjcheckwithpaginglist.do", method = { RequestMethod.GET,
                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCreHousingBjCheckListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                                  HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getListWithPagingForFdForAdd(queryInfo, user, "2");
    }
    /**
     * 办件审核列表--完善
     * 
     * @param queryInfo
     * @param request
     * @return
     * @author baisong
     */
    @RequestMapping(value = "/loancheck/crehousingbjcheckwithpagingMakeuplist.do", method = { RequestMethod.GET,
                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCreHousingBjCheckListWithPagingMakeup(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                                  HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getListWithPagingForFdForAddMakeup(queryInfo, user, "2");
    }
   
    /**
     * 评估审核列表--车贷
     * 
     * @param queryInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/loancheck/creCarbjcheckwithpaginglist.do", method = { RequestMethod.GET,
                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCrecCarBjCheckListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                                  HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        //carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
        return wmscrecreditheadService.getListWithPagingForCDForAdd(queryInfo, user, "3");
    }
    /**
     * 评估审核列表--车贷
     * 
     * @param queryInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/loancheck/creCarbjcheckwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCrecCarBjCheckListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                                  HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        //carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
        return wmscrecreditheadService.getListWithoutPagingForCDForAdd(queryInfo, user, "3");
    }
    /**
     * 房产信息流程历程显示
     * 
     * @param wms_cre_credit_head_id
     */
    @RequestMapping(value = "/loancheck/houseCreditWorkFlowViewMoa.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getHouseCreditWorkFlowViewMoa(Integer wms_cre_credit_head_id)
    {
    	Map<String, Object> pMap=new HashMap<>();
    	Map<String, Object> map=wmscrecreditheadService.getHeadInfo(wms_cre_credit_head_id);
    	if("2".equals(map.get("edition_num").toString())){//新流程
    		if(map.get("version_number")!=null&&"2".equals(map.get("version_number").toString())){//version_number单据来源 最新流程
    			pMap=wmsLoanWorkFlowService.inveWorkFlowProcess(WorkflowConstantHelp.UPHOUSINGLOANPROCESS, wms_cre_credit_head_id.toString());
    		}else{
    			pMap=wmsLoanWorkFlowService.inveWorkFlowProcess(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS, wms_cre_credit_head_id.toString());
    		}
    	}else if("1".equals(map.get("edition_num").toString())){//旧流程
    		pMap=approveHouseCreditWorkFlowService.houseCreditWorkFlowView(String.valueOf(wms_cre_credit_head_id));
    	}
		return pMap;
       
    }
    /**
     * 房产信息流程历程显示
     * 
     * @param wms_cre_credit_head_id
     */
    @RequestMapping(value = "/loancheck/houseCreditWorkFlowView.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getHouseCreditWorkFlowView(Integer wms_cre_credit_head_id)
    {
    	Map<String, Object> pMap=new HashMap<>();
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        paraMap.put("sortname", "wms_cre_housing_approval_info");
        paraMap.put("sortorder", "asc");
        List<Map<String, Object>> approvalInfolist = wmsCreHousingApprovalInfoDao.search(paraMap);
        pMap.put("Rows", approvalInfolist);
        return pMap;
        /*Map<String, Object> map=wmscrecreditheadService.getHeadInfo(wms_cre_credit_head_id);
        // 查询审批信息
        if(map!=null&&map.get("is_workflow")!=null&&"0".equals(map.get("is_workflow").toString())){
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
            paraMap.put("sortname", "wms_cre_housing_approval_info");
            paraMap.put("sortorder", "asc");
            List<Map<String, Object>> approvalInfolist = wmsCreHousingApprovalInfoDao.search(paraMap);
            for (Map<String, Object> retMap : approvalInfolist)
            {
                String approveResult = "";
                if (retMap.get("approval_result") != null)
                {
                    approveResult = retMap.get("approval_result").toString();
                }
                if ("true".equals(approveResult))
                {
                    if ("未审批".equals(approveResult))
                    {
                        // 当出现审批结果为true,但审批意见为'未审批'，说明属于特批，其他审批环节系统自动完成,不需要显示。
                        continue;
                    }
                    else
                    {
                            // 当出现审批结果为true,审批意见为其他内容，说明属于正常审批通过。
                        retMap.put("approveResult", "审批通过");
                    }
                }
                else if ("noagree".equals(approveResult))
                {
                    // 当出现noagree的值时，都属于系统自动完成不需要显示
                    continue;
                }
                else if ("false".equals(approveResult))
                {
                    // 当出现false的值时,说明审批结果为不通过
                    retMap.put("approveResult", "审批不通过");
                }
                else if ("over".equals(approveResult))
                {
                    continue;
                }
                else if ("nullify".equals(approveResult))
                {
                    // 当出现false的值时,说明审批结果为不通过
                    retMap.put("approveResult", "已终止");
                }
                else if ("undo1".equals(approveResult))
                {
                    retMap.put("approveResult", "[客户撤销>[协议签订]]");
                }
                else if ("undo2".equals(approveResult))
                {
                    retMap.put("approveResult", "[客户撤销>[客户确认]]");
                }
                else if ("compe".equals(approveResult))
                {
                    retMap.put("approveResult", "已完成");
                }
                else if ("supply".equals(approveResult))
                {
                    retMap.put("approveResult", "退回");
                }
                else if ("overdue".equals(approveResult))
                {
                    retMap.put("approveResult", "过期");
                }
                else if ("cross".equals(approveResult))
                {
                    retMap.put("approveResult", "跨过签合同节点");
                }
                else if ("overtime".equals(approveResult))
                {
                    retMap.put("approveResult", "过期");
                }
                else if ("credit".equals(approveResult))
                {
                    retMap.put("approveResult", "授信流转");
                }
                else if ("makezs".equals(approveResult))
                {
                    retMap.put("approveResult", "补件-终审");
                }
                else if ("makepg".equals(approveResult))
                {
                    retMap.put("approveResult", "补件-评估");
                }
                else if (approveResult == null)
                {
                    retMap.put("approveResult", "");
                }
            }
            pMap.put("Rows", approvalInfolist);
        }
        else if("2".equals(map.get("edition_num").toString())){//新流程
        	//数据来源1为pc 2为移动端
            if("2".equals(map.get("version_number"))){//2016/5/10版本
            	pMap=wmsLoanWorkFlowService.inveWorkFlowProcess(WorkflowConstantHelp.UPHOUSINGLOANPROCESS, wms_cre_credit_head_id.toString());
            }else{//2016/2/10版本
            	pMap=wmsLoanWorkFlowService.inveWorkFlowProcess(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS, wms_cre_credit_head_id.toString());
            }
        }else if("1".equals(map.get("edition_num").toString())){//旧流程
        	pMap=approveHouseCreditWorkFlowService.houseCreditWorkFlowView(String.valueOf(wms_cre_credit_head_id));
        }
        return pMap;*/
       
    }
    /**
     * Description :房贷办件退件
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author wangyishun
     */
    @RequestMapping(value = "/loanreview/cardtoback.do", method = { RequestMethod.POST })
    @ResponseBody
    public String cardToBack(HttpServletRequest request, WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            try{
                result = wmscrehousingcheckService.cardToBack(approveHouseWorkFlowVO, user);
            }
            catch (ActivitiException ae)
            {
                // 老数据处理 流程差异
                log.error(ae.getMessage());
                result = "taskerror";
            }

        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "贷前管理-房贷审核-办件审核组-退件";
            SysTools.saveLog(request, msg);
        }
        return result;
    }
    /**
     * Description :车贷办件退件
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @RequestMapping(value = "/loanreview/cardtobackcar.do", method = { RequestMethod.POST })
    @ResponseBody
    public String cardToBackCar(HttpServletRequest request, WmsCarLoanWorkFlowVO wVo)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrehousingcheckService.cardToBackCar(wVo, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "贷前管理-车贷审核-办件审核组-退件";
            SysTools.saveLog(request, msg);
        }
        return result;
    }
    /**
     * moa上获取流程
     * 
     * @param wms_cre_credit_head_id
     */
    @RequestMapping(value = "/loancheck/gethouseCreditWorkFlowView.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getHouseWorkFlowView(Integer wms_cre_credit_head_id)
    {
    	Map<String, Object> pMap=new HashMap<>();
    	Map<String, Object> map=wmscrecreditheadService.getHeadInfo(wms_cre_credit_head_id);
    	if("2".equals(map.get("edition_num").toString())){//新流程
    		pMap=wmsLoanWorkFlowService.inveWorkFlowProcess(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS, wms_cre_credit_head_id.toString());
    	}else if("1".equals(map.get("edition_num").toString())){//旧流程
    		pMap=approveHouseCreditWorkFlowService.houseCreditWorkFlowView(String.valueOf(wms_cre_credit_head_id));
    	}
		return pMap;
    }
    
    /**
     * Description : 发送房产评估单基本信息详细信息 (MOA请求：接口文档3.40)
     */
    @RequestMapping(value = "/cremanage/sendHouseAssessmentBasicInfoOne.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> sendHouseAssessmentBasicInfoOne(WmsCreHousingCheckSearchBeanVO queryInfo) {
    	Map<String, Object> resMap = new HashMap<String, Object>();
    	
		try {
			queryInfo = this.wmscrehousingcheckService.sendHouseAssessmentBasicInfoOne(queryInfo);
            resMap.put("result", "success");
            resMap.put("message", "操作成功");
            resMap.put("flag", true);
        } catch(Exception e) {
            resMap.put("result", "error");
            resMap.put("flag", false);
            resMap.put("message", e.getMessage());
        }
		return resMap;
    }
    
    /**
     * Description : 发送房产评估单房产信息详细信息 (MOA请求：接口文档3.41)
     */
    @RequestMapping(value = "/cremanage/sendHouseAssessmentBasicInfoTwo.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> sendHouseAssessmentBasicInfoTwo(WmsCreHousingCheckSearchBeanVO queryInfo) {
    	Map<String, Object> resMap = new HashMap<String, Object>();
    	
		try {
			queryInfo = this.wmscrehousingcheckService.sendHouseAssessmentBasicInfoTwo(queryInfo);
            resMap.put("result", "success");
            resMap.put("message", "操作成功");
            resMap.put("flag", true);
        } catch(Exception e) {
            resMap.put("result", "error");
            resMap.put("flag", false);
            resMap.put("message", e.getMessage());
        }
		return resMap;
    }
    
    
    
}