package com.zx.emanage.loanreview.web;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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

import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.cremanage.service.IWmsCreCreditLineCustomerChangeHeadService;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreCreditHeadSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsCreFinalAuditAmountSearchBeanVO;
import com.zx.emanage.loanreview.service.IWmsCreRevWaterMainService;
import com.zx.emanage.loanreview.vo.WmsCreRevWaterMainSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsCreRevWaterMain;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreRevWaterMainController {
	private static Logger log = LoggerFactory.getLogger(WmsCreRevWaterMainController.class);

	@Autowired
	private IWmsCreRevWaterMainService wmscrerevwatermainService;

	@Autowired
	IWmsCreCreditLineCustomerChangeHeadService wmsCreCreditLineCustomerChangeHeadService;

	@Autowired
	private IWmsCreCreditHeadService wmscrecreditheadService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * 
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanreview/wmscrerevwatermainwithoutpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsCreRevWaterMainSearchBeanVO queryInfo) {
		return wmscrerevwatermainService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * 
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanreview/wmscrerevwatermainwithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsCreRevWaterMainSearchBeanVO queryInfo) {
		return wmscrerevwatermainService.getListWithPaging(queryInfo);
	}

	/**
	 * Description :get single-table information by primary key
	 * 
	 * @param primary
	 *            key
	 * @return WmsCreRevWaterMainVO
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanreview/wmscrerevwatermaininfobypk.do", method = { RequestMethod.GET })
	@ResponseBody
	public WmsCreRevWaterMain getInfoByPK(Integer wms_cre_rev_water_main_id) {
		return wmscrerevwatermainService.getInfoByPK(wms_cre_rev_water_main_id);
	}

	/**
	 * Description :保存信贷流水审核
	 * 
	 * @param dsls
	 *            对私流水信息字符串
	 * @param dgls
	 *            对公流水信息字符串
	 * @param wms_cre_credit_head_id
	 * @return 保存结果，success为成功，error为失败
	 * @author wangyishun
	 */
	@RequestMapping(value = "/loanreview/wmscrerevwatermainsave.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doSave(HttpServletRequest request, String dsls, String dgls, String zysj, String water_appro_eval, WmsCreditWorkFlowVO approveWorkFlowVO, String saveStatus) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrerevwatermainService.save(dsls, dgls, zysj, water_appro_eval, user, approveWorkFlowVO, saveStatus);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		// 保存日志
		if ("success".equals(result)) {
			String msg = "";
			if ("0".equals(saveStatus)) {
				msg = "贷前管理-信贷审核-流水审核组-暂存";
			} else {
				msg = "贷前管理-信贷审核-流水审核组-保存";
			}
			SysTools.saveLog(request, msg);
		}
		return result;
	}

	/**
	 * Description :保存房贷流水审核
	 * 
	 * @param dsls
	 *            对私流水信息字符串
	 * @param dgls
	 *            对公流水信息字符串
	 * @param wms_cre_credit_head_id
	 * @return 保存结果，success为成功，error为失败
	 * @author wangyishun
	 */
	@RequestMapping(value = "/loanreview/wmscrerevwatermainsaveforfd.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doSaveForFd(HttpServletRequest request, String dsls, String dgls, String water_appro_eval, WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, String saveStatus) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrerevwatermainService.saveForFd(dsls, dgls, water_appro_eval, user, approveHouseWorkFlowVO, saveStatus);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		// 保存日志
		if ("success".equals(result)) {
			String msg = "";
			if ("0".equals(saveStatus)) {
				msg = "贷前管理-房贷贷审核-流水审核组-暂存";
			} else {
				msg = "贷前管理-房贷审核-流水审核组-保存";
			}
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
	@RequestMapping(value = "/loanreview/wmscrerevwatermainupdate.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsCreRevWaterMain bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrerevwatermainService.update(bean, user);
		} catch (Exception e) {
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
	 * Description :获取指定贷款记录的主贷人和非主货人的集合
	 * 
	 * @param wms_cre_credit_head_id
	 *            贷款记录ID
	 * @return 指定贷款记录的主贷人和非主货人的集合
	 * @author wangyishun
	 */
	@RequestMapping(value = "/loanreview/wmscrecreditlinecustomerchangeheadlistwithoutpaging.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getWmsCreCreditLineCustomerChangeHeadListWithoutPaging(Integer wms_cre_credit_head_id) {
		return wmsCreCreditLineCustomerChangeHeadService.getWmsCreCreditLineCustomerChangeHeadListWithoutPaging(wms_cre_credit_head_id);
	}

	/**
	 * Description :信贷对私流水获取表单数据
	 * 
	 * @param primary
	 *            key
	 * @return WmsCreRevWaterMainVO
	 * @author wangyishun
	 */
	@RequestMapping(value = "/loanreview/wmscrerevwatermaininfobyfk.do", method = { RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> getInfoByFK(Integer wms_cre_credit_head_id, Integer wms_cre_credit_line_customer_change_head_id) {
		return wmscrerevwatermainService.getInfoByFK(wms_cre_credit_head_id, wms_cre_credit_line_customer_change_head_id);
	}

	/**
	 * Description :信贷对公获取表单数据
	 * 
	 * @param primary
	 *            key
	 * @return WmsCreRevWaterMainVO
	 * @author wangyishun
	 */
	@RequestMapping(value = "/loanreview/wmscrerevwatermaininfobyfkforadd.do", method = { RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> getInfoByFKForAdd(Integer wms_cre_credit_head_id, Integer wms_cre_credit_line_customer_change_head_id) {
		return wmscrerevwatermainService.getInfoByFKForAdd(wms_cre_credit_head_id, wms_cre_credit_line_customer_change_head_id);
	}

	/**
	 * 流水、信息、电审、征信、验点数据导出
	 * 
	 * @param queryInfo
	 * @return
	 */
	@RequestMapping(value = "/loanreview/teamwithoutpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getTeamListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request, String flag_byk) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getTeamListWithoutPaging(queryInfo, user, flag_byk);
	}

	/**
	 * 信贷流水审批列表显示
	 * 
	 * @param queryInfo
	 *            前台提交的查询条件VO
	 * @param flag_byk
	 * @param request
	 *            获取相应信息
	 * @return 查询结果
	 */
	@RequestMapping(value = "/loanreview/waterwithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getWaterListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request, String flag_byk) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getListWithPaging(queryInfo, user, flag_byk);
	}

	/**
	 * 房贷流水数据导出
	 * 
	 * @param queryInfo
	 * @return
	 */
	@RequestMapping(value = "/loanreview/teamwithoutpaginglistforfdls.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getTeamListWithoutPagingForFdLs(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getTeamListWithoutPagingForFdForAdd(queryInfo, user, "1");
	}

	/**
	 * 流水审核列表
	 * 
	 * @param queryInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/loanreview/houserunapprovalwithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getCreHousingCheckListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditheadService.getListWithPagingForFdForAdd(queryInfo, user, "1");
	}

	/**
	 * Description :信贷流水退件
	 * 
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author wangyishun
	 */
	@RequestMapping(value = "/loanreview/watertoback.do", method = { RequestMethod.POST })
	@ResponseBody
	public String waterToBack(HttpServletRequest request, WmsCreditWorkFlowVO approveWorkFlowVO) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrerevwatermainService.waterToBack(approveWorkFlowVO, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		// 保存日志
		if ("success".equals(result)) {
			String msg = "贷前管理-信贷审核-流水审核组-退件";
			SysTools.saveLog(request, msg); // 保存日志
		}
		return result;
	}

	/**
	 * Description :房贷流水退件
	 * 
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author wangyishun
	 */
	@RequestMapping(value = "/loanreview/watertobackforfd.do", method = { RequestMethod.POST })
	@ResponseBody
	public String waterToBackForFd(HttpServletRequest request, WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrerevwatermainService.waterToBackForFd(approveHouseWorkFlowVO, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		// 保存日志
		if ("success".equals(result)) {
			String msg = "贷前管理-房贷审核-流水审核组-退件";
			SysTools.saveLog(request, msg); // 保存日志
		}
		return result;
	}

	/**
	 * Description :获取审批情况
	 * 
	 * @param queryInfo
	 * @return record list
	 * @author wangyishun
	 */
	@RequestMapping(value = "/loanreview/getapproval.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public WmsCreCreditHead getApproval(Integer wms_cre_credit_head_id) {
		WmsCreCreditHead wcch = wmscrecreditheadService.getInfoByPK(wms_cre_credit_head_id);
		return wcch;
	}

	@RequestMapping(value = "/loanreview/getFinalAuditAmount.do", method = { RequestMethod.POST })
	@ResponseBody
	public BigDecimal getFinalAuditAmount(WmsCreFinalAuditAmountSearchBeanVO auditAmountSearchBeanVO) {
		BigDecimal appro_limit = wmscrecreditheadService.getFinalAuditAmount(auditAmountSearchBeanVO);
		return appro_limit;
	}
	public String encodeStr(String str) {  
        try {  
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
}