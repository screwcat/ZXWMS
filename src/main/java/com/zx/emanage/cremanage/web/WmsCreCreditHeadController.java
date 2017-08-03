package com.zx.emanage.cremanage.web;

/**
 * 版权所有：版权所有(C) 2014，沈阳新融金融信息服务有限公司
 * 文件名称: WmsCreCreditHeadController.java
 * 系统名称：
 * 模块名称：
 * 完成日期：
 * 作    者：
 * 内容摘要：  
 * 
 * 文件调用：
 * 修改记录：01
 * 修改时间：2014-11-27
 * 修 改 人:  hancd
 * 修改内容：增加能够删除信贷和房贷处于草稿状态下的单据 软删除
 * 关联BUG：
 * 修改方法：通过给定的主贷款ID和贷款标示cre_loan
 */

import java.util.HashMap;
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

import com.zx.emanage.cremanage.service.IWmsCarLoanWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.cremanage.service.IWmsCreHousingAttService;
import com.zx.emanage.cremanage.service.IWmsCreditWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCreCreditHeadHouseSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsCreCreditHeadSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsMoaHouseInfoVO;
import com.zx.emanage.sysmanage.service.IWmsCreHousingOperationLogService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsCreHousingOperationLog;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */
@Controller
public class WmsCreCreditHeadController {
	private static Logger log = LoggerFactory.getLogger(WmsCreCreditHeadController.class);

	@Autowired
	private IWmsCreCreditHeadService wmscrecreditheadService;

	@Autowired
	private IWmsCreditWorkFlowService creditWorkFlowService;

	@Autowired
	private IWmsCreHousingAttService wmscrehousingattService;

	@Autowired
	private IWmsCarLoanWorkFlowService wmsCarLoanWorkFlowService;

	@Autowired
	private IWmsCreHousingOperationLogService wmsCreHousingOperationLogService;

	/**
	 * Description :贷款查询列表导出功能
	 * 
	 * @param queryInfo
	 * @return record list
	 * @author wangyishun
	 */
	@RequestMapping(value = "/cremanage/wmscrecreditheadwithoutpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getListWithoutPaging(queryInfo, user);
	}

	/**
	 * Description : 实现信贷贷款复核导出功能
	 * 
	 * @param queryInfo
	 * @return record
	 * @author hancd
	 */
	@RequestMapping(value = "/cremanage/wmscrecreditheadcheckwithoutpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getCheckListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getCheckListWithoutPaging(queryInfo, user);
	}

	/**
	 * Description :房贷信息完善 房贷查询 带条件 无分页数据
	 * 
	 * @param queryInfo
	 * @return record list
	 * @author ry
	 */
	@RequestMapping(value = "/cremanage/wmscrehousecreditheadwithoutpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getHouseListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		queryInfo.setCre_type("2");
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getHouseListWithoutPaging(queryInfo, user);
	}

	/**
	 * 流水、信息、电审、征信数据导出
	 * 
	 * @param queryInfo
	 * @return
	 */
	@RequestMapping(value = "/cremanage/teamwithoutpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getTeamListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request, String flag_byk) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getTeamListWithoutPaging(queryInfo, user, flag_byk);
	}

	/**
	 * Description: 实现信贷:贷款复核 流水审批 信息审批 电审审批 证信审批 验点审批 页面查询数据显示功能
	 * 
	 * @param flag_byk
	 * @param flag_byk=0
	 *            代表信贷贷款复核
	 * @param flag_byk=1
	 *            代表信贷流水审批
	 * @param flag_byk=2
	 *            代表信贷信息审批
	 * @param flag_byk=3
	 *            代表信贷电审审批
	 * @param flag_byk=4
	 *            代表信贷证信审批
	 * @param flag_byk=5
	 *            代表信贷验点审批
	 * @author hancd
	 */
	@RequestMapping(value = "/cremanage/wmscrecreditheadwithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request, String flag_byk) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getListWithPaging(queryInfo, user, flag_byk);
	}

	/**
	 * 实现贷款查询控制器
	 * 
	 * @param queryInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cremanage/wmscrecreditheadwithpaginglistforadd.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getListWithPagingforadd(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getListWithPagingforadd(queryInfo, user);
	}

	/**
	 * 实现房贷查询控制器
	 * 
	 * @param queryInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cremanage/wmscrehousecreditheadwithpaginglistforadd.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getListWithPagingforaddHouse(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		queryInfo.setCre_type("2");
		return wmscrecreditheadService.getListWithPagingforaddHouse(queryInfo, user);
	}

	/**
	 * 审批查询列表分页
	 * 
	 * @param queryInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cremanage/creditCheckWithPaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getCreditCheckListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getCreditCheckListWithPaging(queryInfo, user);
	}

	/**
	 * 审批查询数据导出
	 * 
	 * @param queryInfo
	 * @return
	 */
	@RequestMapping(value = "/cremanage/creditCheckWithoutPaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getCreditCheckListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo) {

		return wmscrecreditheadService.getCreditCheckListWithoutPaging(queryInfo);
	}

	/**
	 * 贷前跟踪数据导出
	 * 
	 * @param queryInfo
	 * @return
	 */
	@RequestMapping(value = "/cremanage/followCheckWithoutPaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getFollowCheckListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo) {

		return wmscrecreditheadService.getFollowCheckListWithoutPaging(queryInfo);
	}

	/**
	 * 贷前跟踪列表
	 * 
	 * @param queryInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cremanage/followCheckWithPaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getFollowCheckListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getFollowCheckListWithPaging(queryInfo, user);
	}

	/**
	 * 贷前查询
	 * 
	 * @param queryInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cremanage/loanCheckWithPaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getLoanCheckListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getLoanCheckListWithPaging(queryInfo, user);
	}

	/**
	 * 贷前查询数据导出
	 * 
	 * @param queryInfo
	 * @return
	 */
	@RequestMapping(value = "/cremanage/loanCheckWithoutPaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getLoanCheckListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo) {
		return wmscrecreditheadService.getLoanCheckListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get single-table information by primary key
	 * 
	 * @param primary
	 *            key
	 * @return WmsCreCreditHeadVO
	 * @author auto_generator
	 */
	@RequestMapping(value = "/cremanage/wmscrecreditheadinfobypk.do", method = { RequestMethod.GET })
	@ResponseBody
	public WmsCreCreditHead getInfoByPK(Integer wms_cre_credit_head_id) {
		return wmscrecreditheadService.getInfoByPK(wms_cre_credit_head_id);
	}

	/**
	 * 贷款复核审批
	 * 
	 * @param wmsCreHeadIds贷款单据主键集合
	 * @param wmsTaskIds
	 *            每条单据对应的流程节点taskID集合
	 * @param wmsCreditLimits
	 *            每条单据申请额度的集合
	 * @param wmsCreditCreLoanTypes
	 *            每条单据信贷产品种类集合
	 * @param request
	 */
	@RequestMapping(value = "/cremanage/creditCheckApproveSave.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getCreditCheckApproveSave(HttpServletRequest request, String wmsCreHeadIds, String wmsTaskIds, String wmsCreditLimits, String wmsCreditCreLoanTypes, String pass, String advice) {
		HttpSession session = request.getSession();
		// 获取当前审批人
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		String result = "";
		try {
			result = wmscrecreditheadService.creditApproveSave(wmsCreHeadIds, wmsTaskIds, wmsCreditLimits, wmsCreditCreLoanTypes, pass, advice, String.valueOf(user.getUserId()));
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		if ("OK".equals(result)) {
			String msg = "贷款复核--复核审批";
			SysTools.saveLog(request, msg);
		}
		return result;
	}

	/**
	 * 实现流程历程查看功能 贷款复核查看
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/cremanage/approvalProcessView.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getApprovalProcessView(Integer wms_cre_credit_head_id) {
		return creditWorkFlowService.checkApprovalProcess(wms_cre_credit_head_id);
	}

	/**
	 * 保存 贷款申请信息
	 * 
	 * @param request
	 * @param mcch
	 *            贷款申请信息
	 * @param mccclc
	 *            客户联系人 信息
	 * @param clientId
	 *            选择的客户 ID
	 * @param attachment
	 *            上传地址
	 * @param modifyJsonCus
	 *            修改后的数据
	 * @param isComOrZC
	 *            暂存还是提交
	 * @return
	 */
	@RequestMapping(value = "/cremanage/wmscrecreditheadsave.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsCreCreditHead mcch, String linkmaninfo, String personArr, String fileArr, String modifyJsonCus, String isComOrZC) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
		try {
			result = wmscrecreditheadService.saveAll(mcch, fileArr, linkmaninfo, user, personArr, modifyJsonCus, isComOrZC);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		if ("OK".equals(result)) {
			String msg = "贷款管理-信用贷款-贷款申请";
			SysTools.saveLog(request, msg);
		}
		return result;
	}

	/**
	 * 更新 贷款申请信息
	 * 
	 * @param request
	 * @param mcch
	 *            贷款申请信息
	 * @param mccclc
	 *            客户联系人 信息
	 * @param clientId
	 *            选择的客户 ID
	 * @param attachment
	 *            上传地址
	 * @param modifyJsonCus
	 *            修改后的数据
	 * @param isComOrZC
	 *            暂存还是提交
	 * @return
	 */
	@RequestMapping(value = "/cremanage/wmscrecreditheadupdate.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doUpdateCredit(HttpServletRequest request, WmsCreCreditHead mcch, String linkmaninfo, String personArr, String fileArr, String modifyJsonCus, String isComOrZC) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
		try {
			result = wmscrecreditheadService.updateCredit(mcch, fileArr, linkmaninfo, user, personArr, modifyJsonCus, isComOrZC);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		if ("OK".equals(result)) {
			String msg = "贷款管理-信用贷款-贷款申请";
			SysTools.saveLog(request, msg);
		}
		return result;
	}

	/**
	 * 保存 房贷申请信息
	 * 
	 * @param request
	 * @param mcch
	 *            房贷申请信息
	 * @param mccclc
	 *            客户联系人 信息
	 * @param clientId
	 *            选择的客户 ID
	 * @param zdrID
	 *            主贷人ID
	 * @param mcclhid
	 *            抵押房产信息的房产ID
	 * @param modifyJsonCus
	 *            修改后的数据
	 * @return result
	 * @author ry
	 */
	@RequestMapping(value = "/cremanage/wmscrecreditheadhousecresave.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doHouseCreSave(HttpServletRequest request, WmsCreCreditHead mcch, String linkmaninfo, String personArr, String modifyJsonCus, String house_use, String zdrID, String mcclhid, String fileArr, String fileArrkh, String isComOrZC, Integer[] deleteAttIds) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
		try {
			result = wmscrecreditheadService.saveHouseCreAll(mcch, linkmaninfo, user, personArr, modifyJsonCus, house_use, zdrID, mcclhid, fileArr, fileArrkh, isComOrZC, deleteAttIds);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		if ("OK".equals(result)) {
			String msg = "贷款管理-房屋贷款-贷款申请";
			SysTools.saveLog(request, msg);
		}
		return result;
	}

	/**
	 * 更新 房贷申请信息
	 * 
	 * @param request
	 * @param mcch
	 *            房贷申请信息
	 * @param mccclc
	 *            客户联系人 信息
	 * @param clientId
	 *            选择的客户 ID
	 * @param zdrID
	 *            主贷人ID
	 * @param mcclhid
	 *            抵押房产信息的房产ID
	 * @param modifyJsonCus
	 *            修改后的数据
	 * @return result
	 * @author ry 2016-02-24日对方法进行优化 把参数换成类
	 */
	@RequestMapping(value = "/cremanage/wmscrecreditheadhousecreupdate.do", method = { RequestMethod.POST })
	@ResponseBody
	/*
	 * public String doHouseCreUpdate(HttpServletRequest request,
	 * WmsCreCreditHead mcch, String linkmaninfo, String personArr, String
	 * modifyJsonCus, String house_use, String zdrID, String mcclhid, String
	 * fileArr, String isComOrZC)
	 */
	public String doHouseCreUpdate(HttpServletRequest request, WmsCreCreditHead mcch, WmsCreCreditHeadHouseSearchBeanVO bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
		try {
			/*
			 * result = wmscrecreditheadService.updateHouseCreAll(mcch,
			 * linkmaninfo, user, personArr, modifyJsonCus, house_use, zdrID,
			 * mcclhid, fileArr, isComOrZC);
			 */
			result = wmscrecreditheadService.updateHouseCreAll(mcch, user, bean);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		if ("OK".equals(result)) {
			String msg = "贷款管理-房屋贷款-贷款申请";
			SysTools.saveLog(request, msg);
		}
		return result;
	}

	/**
	 * Description :getList
	 * 
	 * @param org_wms_cre_credit_head_id
	 * @return record list
	 * @author
	 */
	@RequestMapping(value = "/cremanage/wmscrecredheaddiffphonelist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getDiffPhoneListById(String org_wms_cre_credit_head_id) {
		return wmscrecreditheadService.getDiffPhoneListById(org_wms_cre_credit_head_id);
	}

	/**
	 * 复核退回列表分页
	 * 
	 * @param queryInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cremanage/recheckreturnwithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getRecheckReturnListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getRecheckReturnListWithPaging(queryInfo, user);
	}

	/**
	 * 完善退回列表分页
	 * 
	 * @param queryInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cremanage/completereturnwithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getCompleteReturnListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getCompleteReturnListWithPaging(queryInfo, user);
	}

	/**
	 * 复核退回数据导出
	 * 
	 * @param queryInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cremanage/recheckreturnlistwithoutpaging.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getRecheckListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getRecheckReturnListWithoutPaging(queryInfo, user);
	}

	/**
	 * 完善退回数据导出
	 * 
	 * @param queryInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cremanage/completereturnlistwithoutpaging.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getCompletesReturnListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getCompleteReturnListWithoutPaging(queryInfo, user);
	}

	/**
	 * 信贷贷前退回列表分页
	 * 
	 * @param queryInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cremanage/loanbeforereturnwithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getLoanBeforeReturnListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getLoanBeforeReturnListWithPaging(queryInfo, user);
	}

	/**
	 * 房贷贷前退回列表分页
	 * 
	 * @param queryInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cremanage/housebeforereturnwithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getHouseBeforeReturnListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getHouseBeforeReturnListWithPaging(queryInfo, user);
	}

	/**
	 * 信贷贷前退回列表导出
	 * 
	 * @param queryInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cremanage/loanbeforereturnlistwithoutpaging.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getLoanBeforeReturnListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getLoanBeforeReturnListWithoutPaging(queryInfo, user);
	}

	/**
	 * 房贷贷前退回列表导出
	 * 
	 * @param queryInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cremanage/housebeforereturnlistwithoutpaging.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getHouseBeforeReturnListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getHouseBeforeReturnListWithoutPaging(queryInfo, user);
	}

	/**
	 * 信贷复议修订列表显示
	 * 
	 * @param queryInfo
	 * @param request
	 * @author hancd
	 */
	@RequestMapping(value = "/cremanage/loanReviewRevisionReturnWithPaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getloanReviewRevisionReturnListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getLoanReviewReturnListWithPaging(queryInfo, user);
	}

	/**
	 * 复议修订数据导出
	 * 
	 * @param queryInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cremanage/getLoanReviewReturnWithoutPagingList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getLoanReviewReturnWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getLoanReviewReturnWithoutPaging(queryInfo, user);
	}

	/**
	 * 修改记录：01 贷款查询中实现对草稿状态(包括信贷和房贷)的单据进行删除操作 软删除
	 * 
	 * @param wms_cre_credit_head_id
	 *            贷款单据ID
	 * @param cre_type
	 *            区分房贷还是信贷
	 * @return
	 * @author hancd
	 */
	@RequestMapping(value = "/cremanage/wmscrecreditheadinfofordelete.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doDelete(HttpServletRequest request, Integer wms_cre_credit_head_id, Integer cre_type) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
		try {
			result = wmscrecreditheadService.deleteByPK(wms_cre_credit_head_id, cre_type);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		if ("success".equals(result)) {
			String msg = "贷款管理-贷款查询-暂存删除";
			SysTools.saveLog(request, msg);
		}
		return result;
	}

	/**
	 * 实现复议修订单据的重新提交和暂存功能
	 * 
	 * @param request
	 * @param mcch
	 *            贷款申请信息
	 * @param mccclc
	 *            客户联系人 信息
	 * @param clientId
	 *            选择的客户 ID
	 * @param attachment
	 *            上传地址
	 * @param modifyJsonCus
	 *            修改后的数据
	 * @param isComOrZC
	 *            暂存还是提交
	 * @return
	 */
	@RequestMapping(value = "/cremanage/wmscrecreditheadreviewRevisionCredit.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doReviewRevisionCredit(HttpServletRequest request, WmsCreCreditHead mcch, String linkmaninfo, String personArr, String fileArr, String modifyJsonCus, String isComOrZC, String taskId) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
		try {
			result = wmscrecreditheadService.ReviewRevisionCredit(mcch, fileArr, linkmaninfo, user, personArr, modifyJsonCus, isComOrZC, taskId);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		if ("OK".equals(result)) {
			String msg = "贷款管理-信用贷款-复议修订";
			SysTools.saveLog(request, msg);
		}
		return result;
	}

	/**
	 * @Description: 查询客户的房贷信息（如果多条显示最后一条）
	 * @param queryInfo
	 * @param request
	 * @return
	 * @return Map<String,Object>
	 * @throws @author
	 *             lvtu
	 * @date 2015年7月2日 下午5:19:02
	 */
	@RequestMapping(value = "/cremanage/getwmscrecreditheadinfobycusid.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getWmsCrecreditHeadInfoByCusid(Integer wms_cus_customer_id) {
		return wmscrecreditheadService.getWmsCrecreditHeadInfoByCusid(wms_cus_customer_id);
	}

	/**
	 * @Description: 根据ID查询贷款单信息
	 * @param queryInfo
	 * @param request
	 * @return
	 * @return Map<String,Object>
	 * @throws @author
	 *             lvtu
	 * @date 2015年7月2日 下午5:19:02
	 */
	@RequestMapping(value = "/cremanage/getwmscrecreditheadinfobyid.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getWmsCusCreditHeadInfo(Integer wms_cre_credit_head_id) {
		return wmscrecreditheadService.getWmsCusCreditHeadInfo(wms_cre_credit_head_id);
	}

	/**
	 * 实现车贷复核查询
	 * 
	 * @param queryInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cremanage/wmscrecarcreditheadwithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getCarCreditWithPagingList(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		queryInfo.setCre_type("3");
		return wmscrecreditheadService.getCarCreditWithPagingList(queryInfo, user);
	}

	/**
	 * 实现车贷复核查询
	 * 
	 * @param queryInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cremanage/wmscrecarcreditheadwithoutpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getCarCreditWithOutPagingList(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		queryInfo.setCre_type("3");
		return wmscrecreditheadService.getCarCreditWithoutPagingList(queryInfo, user);
	}

	/**
	 * 复核退回数据导出
	 * 
	 * @param queryInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cremanage/carrecheckreturnlistwithoutpaging.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getCarRecheckListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getCarRecheckReturnListWithoutPaging(queryInfo, user);
	}

	/**
	 * @Title: doCarCreSave
	 * @Description: 车贷申请
	 * @param request
	 * @param mcch
	 * @param linkmaninfo
	 * @param personArr
	 * @param modifyJsonCus
	 * @param house_use
	 * @param zdrID
	 * @param mcclhid
	 * @param fileArr
	 * @param isComOrZC
	 * @return
	 * @return String
	 * @throws @author
	 *             lvtu
	 * @date 2015年7月28日 下午3:19:21
	 */
	@RequestMapping(value = "/cremanage/wmscrecreditheadcarcresave.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doCarCreSave(HttpServletRequest request, WmsCreCreditHead mcch, String linkmaninfo, String personArr, String modifyJsonCus, String house_situation, String zdrID, String mcclhid, String mcclcid, String fileArr, String isComOrZC, String is_house_loan) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
		try {
			result = wmscrecreditheadService.saveCarCreAll(mcch, linkmaninfo, user, personArr, modifyJsonCus, house_situation, zdrID, mcclhid, mcclcid, fileArr, isComOrZC, is_house_loan);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		if ("OK".equals(result)) {
			String msg = "贷款管理-车辆贷款-贷款申请";
			SysTools.saveLog(request, msg);
		}
		return result;
	}

	@RequestMapping(value = "/cremanage/wmscrecreditheadcarcreupdate.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doCarCreUpdate(HttpServletRequest request, WmsCreCreditHead mcch, String linkmaninfo, String personArr, String modifyJsonCus, String house_situation, String zdrID, String mcclhid, String mcclcid, String fileArr, String isComOrZC, String is_house_loan, String taskId) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
		try {
			result = wmscrecreditheadService.updateCarCreAll(mcch, linkmaninfo, user, personArr, modifyJsonCus, house_situation, zdrID, mcclhid, mcclcid, fileArr, isComOrZC, is_house_loan, taskId);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		if ("OK".equals(result)) {
			String msg = "贷款管理-房屋贷款-信息修改";
			SysTools.saveLog(request, msg);
		}
		return result;
	}

	/**
	 * @Description: 根据ID获取房贷流程历程信息
	 * @param queryInfo
	 * @param request
	 * @return
	 * @return Map<String,Object>
	 * @throws @author
	 *             lvtu
	 * @date 2015年7月2日 下午5:19:02
	 */
	@RequestMapping(value = "/cremanage/getcarLoanWorkFlowView.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getcarLoanWorkFlowView(String wms_cre_credit_head_id) {
		return wmsCarLoanWorkFlowService.getcarLoanWorkFlowView(wms_cre_credit_head_id);
	}

	@RequestMapping(value = "/cremanage/carrecheckreturnwithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getCarRecheckReturnListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getCarRecheckReturnListWithPaging(queryInfo, user);
	}

	/**
	 * @Title: getCarRecheckReturnListWithPaging
	 * @Description: 车贷贷前退回
	 * @param queryInfo
	 * @param request
	 * @return
	 * @return Map<String,Object>
	 * @throws @author
	 *             lvtu
	 * @date 2015年8月1日 下午3:37:25
	 */
	@RequestMapping(value = "/cremanage/carbeforereturnwithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getCarBeforeReturnListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getCarBeforeReturnListWithPaging(queryInfo, user);
	}

	@RequestMapping(value = "/cremanage/carbeforereturnlistwithoutpaging.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getCarBeforeReturnListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getCarBeforeReturnListWithoutPaging(queryInfo, user);
	}

	/**
	 * Description :【新房贷】流程历程查看
	 * 
	 * @param wms_inve_transa_id
	 * @return Map
	 * @date 2016年2月25日 上午10:02
	 * @author baisong
	 */
	@RequestMapping(value = "/loancheck/getPerfectHousingLoanProcessView.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getFinancialSingleProcessView(String wms_cre_credit_head_id) {
		return wmscrecreditheadService.getPerfectHousingLoanProcessView(wms_cre_credit_head_id);
	}

	/**
	 * Description : 根据主键获取主表业务员信息
	 * 
	 * @param wms_inve_transa_id
	 * @return Map
	 * @date 2016年3月7日 上午10:02
	 * @author baisong
	 */
	@RequestMapping(value = "/loancheck/getHeadSalesman.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getHeadSalesman(Integer wms_cre_credit_head_id) {
		return wmscrecreditheadService.getHeadSalesman(wms_cre_credit_head_id);
	}

	/**
	 * 保存房贷申请
	 * 
	 * @author administrator
	 */
	@RequestMapping(value = "/loancheck/saveHouseLoan.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> saveHouseLoan(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		String result = "success";
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);

		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			resMap = wmscrecreditheadService.saveHouseLoan(queryInfo, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}

		resMap.put("result", result);

		return resMap;
	}

	/**
	 * 获取房贷单据信息完善列表
	 * 
	 * @author administrator
	 */
	@RequestMapping(value = "/loancheck/getHouseLoanCompletedList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getHouseLoanCompletedList(queryInfo, user);
	}

	/**
	 * moa调用wms方法:房产核查结果保存
	 * 
	 * @param request
	 * @param WmsMoaHouseInfoVO
	 *            bean
	 * @return
	 */
	@RequestMapping(value = "/cremanage/savePropertyVerificationInfoUp.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> savePropertyVerificationInfoUp(HttpServletRequest request, WmsMoaHouseInfoVO bean) {
		Map<String, Object> map = new HashMap<String, Object>();
		String result = "success";

		try {
			try {
				map = wmscrecreditheadService.savePropertyVerificationInfoUp(bean);
				map.put("result", result);
				map.put("flag", true);
				map.put("message", "调用成功");
			} catch (ActivitiException ae) {
				// 判断是否是流程错误
				log.error(ae.getMessage());
				result = "taskerror";
				map.put("flag", false);
				map.put("result", result);
				map.put("message", ae.getMessage());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
			map.put("flag", false);
			map.put("result", result);
			map.put("message", e.getMessage());
		}
		return map;
	}

	/**
	 * 
	 * @Title: ClaimOperUp
	 * @Description: 发送房产核查领用状态
	 * @param request
	 * @param bean
	 * @return
	 * @author: ZhangWei
	 * @time:2017年6月1日 上午10:36:48 history: 1、2017年6月1日 ZhangWei 创建方法
	 */
	@RequestMapping(value = "/wms/sendClaimOperUp.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> ClaimOperUp(HttpServletRequest request, WmsCreHousingOperationLog bean) {
		Map<String, Object> map = new HashMap<String, Object>();
		String result = "success";
		try {
			map = wmsCreHousingOperationLogService.ClaimOperUp(bean);
            map.put("flag", true);
            map.put("result", result);
		} catch (ActivitiException ae) {
			// 判断是否是流程错误
			log.error(ae.getMessage());
			result = "taskerror";
			map.put("flag", false);
			map.put("result", result);
			map.put("message", ae.getMessage());
		}
		return map;
	}

	/**
	 * Description : 单据作废
	 * 
	 * @param Integer
	 * @return Map<String, Object>
	 * @author jiaodelong * 流程节点标识 debtkey 房产核查为4放款申请为9放款审核为10
	 */
	@RequestMapping(value = "/wms/setDocumentVoidUp.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> deleteWmsMessage(String advice, Integer wms_cre_credit_head_id, String pass, String debtkey, Integer personnelId, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String result = "success";
		try {
			try {
				wmscrecreditheadService.DocumentVoidUp(advice, wms_cre_credit_head_id, pass, debtkey, personnelId);
				map.put("result", result);
				map.put("message", "调用成功");
				map.put("flag", true);
			} catch (ActivitiException ae) {
				// 判断是否是流程错误
				log.error(ae.getMessage());
				result = "taskerror";
				map.put("flag", false);
				map.put("result", result);
				map.put("message", ae.getMessage());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
			map.put("result", result);
			map.put("flag", false);
			map.put("message", e.getMessage());
		}
		return map;
	}

	/**
	 * Description : 查询贷款产品
	 * 
	 * @param
	 * @return
	 * @author jiaodelong
	 */
	@RequestMapping(value = "/sysmanage/getwmscrecredit.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public WmsCreCreditHead getwmscrecredit(Integer wms_cre_credit_head_id) {
		return wmscrecreditheadService.getwmscrecredit(wms_cre_credit_head_id);
	}

	/**
	 * Description : 获取流程的版本号
	 * 
	 * @param String
	 * @return
	 * @author baisong
	 */
	@RequestMapping(value = "/sysmanage/getWorkFlowVersion.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getWorkFlowVersion(WmsHouseCreditWorkFlowVO wDebtWorkFlowVO) {
		return wmscrecreditheadService.getWorkFlowVersion(wDebtWorkFlowVO);
	}

	/**
	 * 
	 * @Title: getTaskInfo
	 * @Description: (根据业务主键id获取task信息)
	 * @param wDebtWorkFlowVO
	 * @return
	 * @author: baisong
	 * @time:2016年12月19日 下午1:57:46 history: 1、2016年12月19日 baisong 创建方法
	 */
	@RequestMapping(value = "/sysmanage/getTaskInfo.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getTaskInfo(WmsHouseCreditWorkFlowVO wDebtWorkFlowVO) {
		return wmscrecreditheadService.getTaskInfo(wDebtWorkFlowVO);
	}

	/**
	 * 
	 * @Title: getwmscrecredit
	 * @Description: TODO(查询贷款主表信息)
	 * @param wms_cre_credit_head_id
	 * @return
	 * @author: jiaodelong
	 * @time:2016年12月26日 下午3:53:16 history: 1、2016年12月26日 jiaodelong 创建方法
	 */
	@RequestMapping(value = "/loanappro/getWmsCreCreditHeadinfo.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> wmsPerfectContract(Integer wms_cre_credit_head_id) {
		return wmscrecreditheadService.wmsPerfectContract(wms_cre_credit_head_id);
	}

	/**
	 * 
	 * @Title: getWmsCreCreditHeadList
	 * @Description: 查询贷款主表list
	 * @param queryInfo
	 * @return
	 * @author: wangyihan
	 * @time:2016年12月28日 上午10:25:59 history: 1、2016年12月28日 wangyihan 创建方法
	 */
	@RequestMapping(value = "/cremanage/getWmsCreCreditHeadList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getWmsCreCreditHeadList(HttpServletRequest request, WmsCreCreditHeadSearchBeanVO queryInfo) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息

		queryInfo = wmscrecreditheadService.getWmsCreCreditHeadList(queryInfo, user);

		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("list", queryInfo.getWmsCreCreditHeadList());
		return resMap;
	}

	/**
	 * 
	 * @Title: getLoginUserInfo
	 * @Description: 获取当前登录人信息
	 * @param request
	 * @param queryInfo
	 * @return
	 * @author: wangyihan
	 * @time:2017年2月21日 上午10:15:14 history: 1、2017年2月21日 wangyihan 创建方法
	 */
	@RequestMapping(value = "/cremanage/getLoginUserInfo.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public UserBean getLoginUserInfo(HttpServletRequest request, WmsCreCreditHeadSearchBeanVO queryInfo) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
		return user;
	}
	
	/**
	 * 
	 * @Title: searchLoanNumberStatisticsList
	 * @Description: 查询件数统计列表
	 * @param request
	 * @param queryInfo
	 * @return 
	 * @author: wangyihan
	 * @time:2017年6月15日 下午1:15:55
	 * history:
	 * 1、2017年6月15日 wangyihan 创建方法
	 */
	@RequestMapping(value = "/cremanage/searchLoanNumberStatisticsList.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> searchLoanNumberStatisticsList(HttpServletRequest request, WmsCreCreditHeadSearchBeanVO queryInfo) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
        queryInfo = this.wmscrecreditheadService.searchLoanNumberStatisticsList(queryInfo, user);
        return queryInfo.getMap();
    }
	
	/**
     * 
     * @Title: searchLoanNumberTotal
     * @Description: 查询件数统计总数
     * @param request
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2017年6月15日 下午1:15:55
     * history:
     * 1、2017年6月15日 wangyihan 创建方法
     */
    @RequestMapping(value = "/cremanage/searchLoanNumberTotal.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> searchLoanNumberTotal(HttpServletRequest request, WmsCreCreditHeadSearchBeanVO queryInfo) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
        queryInfo = this.wmscrecreditheadService.searchLoanNumberTotal(queryInfo, user);
        return queryInfo.getMap();
    }
    
    /**
     * 
     * @Title: billStatusStaticsDisp
     * @Description: 系统单据状态统计初始化
     * @param request
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2017年7月6日 下午1:15:55
     * history:
     * 1、2017年7月6日 wangyihan 创建方法
     */
    @RequestMapping(value = "/cremanage/billStatusStaticsDisp.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> billStatusStaticsDisp(HttpServletRequest request, WmsCreCreditHeadSearchBeanVO queryInfo) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
        queryInfo = this.wmscrecreditheadService.billStatusStaticsDisp(queryInfo, user);
        return queryInfo.getMap();
    }
    
    /**
     * 
     * @Title: searchBillStatusStaticsByTime
     * @Description: 根据日期查询上单、放款数汇总
     * @param request
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2017年7月6日 下午1:15:55
     * history:
     * 1、2017年7月6日 wangyihan 创建方法
     */
    @RequestMapping(value = "/cremanage/searchBillStatusStaticsByTime.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> searchBillStatusStaticsByTime(HttpServletRequest request, WmsCreCreditHeadSearchBeanVO queryInfo) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
        queryInfo = this.wmscrecreditheadService.searchBillStatusStaticsByTime(queryInfo, user);
        return queryInfo.getMap();
    }
    
    

}