package com.zx.emanage.inve.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInveDebtHeadService;
import com.zx.emanage.inve.service.IWmsInveTransaService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveDebtHead;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.emanage.inve.vo.WmsInveDebtHeadSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveDebtHeadController {
	private static Logger log = LoggerFactory.getLogger(WmsInveDebtHeadController.class);
	
	@Autowired
	private IWmsInveDebtHeadService wmsinvedebtheadService;
	
	@Autowired
	private IWmsInveTransaService wmsInveTransaService;
	/**
	 * Description :债权变动确认
	 * @param queryInfo
	 * @return record list
	 * @author lvtu
	 */
	@RequestMapping(value = "/inve/getWmsinvedebtheadwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveDebtHeadSearchBeanVO queryInfo,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsinvedebtheadService.getListWithPaging(queryInfo,user);
	}
	
	/**
	 * Description :获取确认 导出
	 * @param queryInfo
	 * @return record list
	 * @author lvtu
	 */
	@RequestMapping(value = "/inve/wmsinvedebtheadwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveDebtHeadSearchBeanVO queryInfo,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsinvedebtheadService.getListWithoutPaging(queryInfo,user);
	}
	/**
	 * Description :债权调整/协议签订 列表显示
	 * @param queryInfo
	 * @return record list
	 * @author lvtu
	 */
	@RequestMapping(value = "/inve/getWmsinvedebtheadgetListPrintProtocollist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListPrintProtocol(WmsInveDebtHeadSearchBeanVO queryInfo,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsinvedebtheadService.getListPrintProtocol(queryInfo,user);
	}
	/**
	 * Description :债权调整/协议签订 列表导出
	 * @param queryInfo
	 * @return record list
	 * @author jiaodelong
	 * @date 2015年11月27日 下午 15:57
	 */
	@RequestMapping(value = "/inve/getWmsinvedebtheadWithoutListPrintProtocollist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getWithoutListPrintProtocol(WmsInveDebtHeadSearchBeanVO queryInfo,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsinvedebtheadService.getWithoutListPrintProtocol(queryInfo,user);
	}
	/**
	 * 获取【债权变动申请】列表数据显示
	 * @param queryInfo
	 * @author yangqiyu
	 * @return Map<String,Object> 
	 */
	@RequestMapping(value = "/inve/getDebtInfoWithPagingList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getDebtInfoWithPagingList(WmsInveDebtHeadSearchBeanVO queryInfo,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsinvedebtheadService.getDebtInfoWithPagingList(queryInfo,user);
	}
	/**
	 * Description :获取【债权变动申请】列表数据导出
	 * @param queryInfo
	 * @return Map
	 * @author yangqiyu
	 */
	@RequestMapping(value="/inve/getDebtInfoWithoutPagingList.do",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> getDebtInfoWithoutPagingList(WmsInveDebtHeadSearchBeanVO queryInfo,HttpServletRequest request){
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsinvedebtheadService.getDebtInfoWithoutPagingList(queryInfo,user);
	}
	/**
	 * 将变更修订内容导出到Excel
	 * @param Map<String,Object>
	 * @return Map<String,Object>
	 * @author yangqiyu
	 */
	@RequestMapping(value="/inve/getallfromwmsinvedebtheadtoExcel.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getAllFromWmsInveDebtHeadToExcel(WmsInveDebtHeadSearchBeanVO queryInfo,HttpServletRequest request){
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsinvedebtheadService.getAllFromWmsInveDebtHeadToExcel(queryInfo,user);
	} 
	/**
	 * 财务管理-理财债权变动-债权变动审核
	 * 获取【债权调整审核】列表数据显示
	 * @param Map<String,Object>
	 * @return list
	 * @author yangqiyu
	 */
	@RequestMapping(value="/inve/getallfromwmsinvedebthead.do",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getAllFromWmsInveDebtHead(WmsInveDebtHeadSearchBeanVO queryInfo,HttpServletRequest request){
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsinvedebtheadService.getAllFromWmsInveDebtHead(queryInfo,user);
	}
	/**
	 * 财务管理-理财债券变动-债权变动审核 
	 * 获取【债权调整审核】列表数据导出
	 * @param queryInfo
	 * @param request
	 * @return map
	 * @author yangqiyu
	 */
	@RequestMapping(value="/inve/getallwithoutwmsinvedebthead.do",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getAllWithoutWmsInveDebtHead(WmsInveDebtHeadSearchBeanVO queryInfo,HttpServletRequest request){
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsinvedebtheadService.getAllWithoutWmsInveDebtHead(queryInfo,user);
	}
	/**
	 * 财务管理-理财债权变动
	 * 获取查看债权调整申请单所有相关信息显示
	 * @author yangqiyu
	 */
	@RequestMapping(value="/inve/getDebtInfoList.do",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getDebtInfoList(Integer wms_inve_debt_head_id,HttpServletRequest request,String olnclaims){
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsinvedebtheadService.getDebtInfoList(wms_inve_debt_head_id,user,olnclaims);
	}
	/**
	 * 【债权调整修订】列表数据显示
	 * @param WmsInveDebtHeadSearchBeanVO 
	 * @return Map
	 * @author yangqiyu
	 */
	@RequestMapping(value="/inve/getupdatewmsinvedebthead.do",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getUpdateWmsInveDebtHead(WmsInveDebtHeadSearchBeanVO queryInfo,HttpServletRequest request){
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsinvedebtheadService.getUpdateWmsInveDebtHead(queryInfo,user);
	}
	/**
	 * 【债权调整修订】列表数据导出
	 * @param WmsInveDebtHeadSearchBeanVO 
	 * @return Map
	 * @author yangqiyu
	 */
	@RequestMapping(value="/inve/getupdatewmsinvedebtheadExcel.do",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getUpdateWmsInveDebtHeadExcel(WmsInveDebtHeadSearchBeanVO queryInfo,HttpServletRequest request){
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsinvedebtheadService.getUpdateWmsInveDebtHeadExcel(queryInfo,user);
	}
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveDebtHeadVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvedebtheadinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveDebtHead getInfoByPK(Integer wms_inve_debt_head_id) {
		return wmsinvedebtheadService.getInfoByPK(wms_inve_debt_head_id);
	}	

	/**
	 * Description :【债权变动申请】 保存操作
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author yangqiyu
	 */	
	@RequestMapping(value = "/inve/wmsinvedebtheadsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveDebtHead bean,String beanJSON) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvedebtheadService.save(bean, user,beanJSON);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}	
		if("success".equals(result)){
			String msg = "财务管理-理财债权变动-债权变更申请  保存操作";
			SysTools.saveLog(request, msg);
		}
		return result;
	}
	/**
	 * 通过产品获取债权匹配信息
	 * @param Integer
	 * @return Map
	 * @author yangqiyu
	 */
	@RequestMapping(value = "/inve/getmatchinfobyprod.do", method = {RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> getMatchInfoByProd(Integer wms_inve_transa_prod_id,HttpServletRequest request){	
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsinvedebtheadService.getMatchInfoByProd(wms_inve_transa_prod_id,user);
	}
	/**
	 * 【债权变更修订】 重新提交 
	 * @param WmsInveDebtHead
	 * @author yangqiyu
	 */
	@RequestMapping(value="/inve/updateclaims.do",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String updateClaims(HttpServletRequest request,WmsInveDebtHead wmsInveDebtHead,String taskId){
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		String result= "";
		try {
			result = wmsinvedebtheadService.updateClaims(wmsInveDebtHead,user,taskId);
		} catch (Exception e) {
			log.error(e.getMessage());
			result="error";
		}
		if("success".equals(result)){
			String msg = "财务管理-理财债权变动-债权修订";
			SysTools.saveLog(request, msg); 
		}
		return result;
	}
	/**
	 * 【债权变更修订】取消调整 
	 * @param Integer
	 * @author yangqiyu
	 */
	@RequestMapping(value="/inve/giveupwmsinvedebthead.do",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String giveUpWmsInveDebtHead(HttpServletRequest request,String wms_inve_debt_head_id,String taskId){
		HttpSession session = request.getSession();
		UserBean user =(UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		String result = "";
		try {
			result = wmsinvedebtheadService.giveUpWmsInveDebtHead(wms_inve_debt_head_id,taskId,user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		if("success".equals(result)){
			String msg = "log content";
			SysTools.saveLog(request, msg); 
		}
		return result;
	}
	/**
	 * 实现债权调整三级审批信息提交保存操作
	 * @param wmsInveDebtWorkFlowVO
	 * @param request
	 * @return "success" or "error"
	 * @author yangqiyu
	 */
	@RequestMapping(value="/inve/wmsinveapprovalopinion.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String approvalOpinion(HttpServletRequest request,WmsInveDebtWorkFlowVO wmsInveDebtWorkFlowVO){
		String result="";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvedebtheadService.approvalOpinion(wmsInveDebtWorkFlowVO, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		if("success".equals(result)){
			String msg = "理财管理-债权调整-债权调整审批";
			SysTools.saveLog(request, msg); 
		}
		return result;
	}
	/**
	 * @Title: specialApprovalSave 
	 * @Description: 债权变动特批
	 * @param request
	 * @param bean
	 * @return String 
	 * @throws
	 * @author lvtu 
	 * @date 2015年10月19日 下午4:23:30
	 */
	@RequestMapping(value = "/inve/wmsinvedebtheadspecialapprovalsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String specialApprovalSave(HttpServletRequest request, WmsInveDebtHead bean,String taskId) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvedebtheadService.approvalSave(null,bean, user,"special",taskId);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		// record log	
		if("success".equals(result)){
			String msg = "财务管理-债权变动调整-特批";
			SysTools.saveLog(request, msg); // record log method
		}
		return result;
	}
	
	/**
	 * @Title: normalApprovalSave 
	 * @Description: 债权调整财务确认
	 * @param request
	 * @param bean
	 * @return String 
	 * @throws
	 * @author lvtu 
	 * @date 2015年10月21日 上午10:39:35
	 */
	@RequestMapping(value = "/inve/wmsinvedebtheadnormalapprovalsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String normalApprovalSave(HttpServletRequest request, WmsInveDebtHead bean,String taskId) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvedebtheadService.approvalSave(null, bean, user, "normal",taskId);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		// record log	
		if("success".equals(result)){
			String msg = "财务管理-债权变动调整-财务确认";
			SysTools.saveLog(request, msg); // record log method
		}
		return result;
	}
	
	/**
	 * @Title: dyxyApprovalSave 
	 * @Description: 【协议签订】
	 * @param request
	 * @param bean
	 * @return String 
	 * @throws
	 * @author lvtu 
	 * @date 2015年10月28日 下午3:37:36
	 */
	@RequestMapping(value = "/inve/wmsinvedebtheaddyxysave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String dyxyApprovalSave(HttpServletRequest request,WmsInveTransaProtocol Protocolbean, WmsInveDebtHead bean,String taskId,Integer headid) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			bean.setWms_inve_debt_head_id(headid);
			result = wmsinvedebtheadService.approvalSave(Protocolbean, bean, user, "xyqd",taskId);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		// record log	
		if("success".equals(result)){
			String msg = "财务管理-债权变动调整-协议签订";
			SysTools.saveLog(request, msg); // record log method
		}
		return result;
	}
	/**
	 * 债权变动流程历程查看
	 * @param  wms_inve_debt_head_id
	 * @return Map
	 * @author hancd
	 */
	@RequestMapping(value="/inve/getApprovalProcessView.do",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getApprovalProcessView(String wms_inve_debt_head_id){
		return wmsinvedebtheadService.getApprovalProcessView(wms_inve_debt_head_id);
	}
}