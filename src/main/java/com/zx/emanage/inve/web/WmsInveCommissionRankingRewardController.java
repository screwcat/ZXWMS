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

import com.zx.emanage.inve.service.IWmsInveCommissionRankingRewardService;
import com.zx.emanage.util.gen.entity.WmsInveCommissionRankingReward;
import com.zx.emanage.inve.vo.WmsInveCommissionRankingRewardSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveCommissionRankingRewardController {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionRankingRewardController.class);
	
	@Autowired
	private IWmsInveCommissionRankingRewardService wmsinvecommissionrankingrewardService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommissionrankingrewardwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionRankingRewardSearchBeanVO queryInfo) {
		return wmsinvecommissionrankingrewardService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommissionrankingrewardwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveCommissionRankingRewardSearchBeanVO queryInfo) {
		return wmsinvecommissionrankingrewardService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionRankingRewardVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommissionrankingrewardinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveCommissionRankingReward getInfoByPK(Integer wms_inve_commission_ranking_reward_id) {
		return wmsinvecommissionrankingrewardService.getInfoByPK(wms_inve_commission_ranking_reward_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommissionrankingrewardsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveCommissionRankingReward bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissionrankingrewardService.save(bean, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		/*
		// record log	
		if("success".equals(result)){
			String msg = "log content";
			SysTools.saveLog(request, msg); // record log method
		}
		*/
		return result;
	}

	/**
	 * Description :update method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommissionrankingrewardupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveCommissionRankingReward bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissionrankingrewardService.update(bean, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		/*			
		// record log	
		if("success".equals(result)){
			String msg = "log content";
			SysTools.saveLog(request, msg); // record log method
		}
		*/
		return result;
	}	
	
	/**
	 * Description :根据奖励方式获取奖励规则 
	 * @param WmsInveCommissionRankingRewardSearchBeanVO
	 * @return Map<String,Object>
	 * @author baisong
	 */	
	@RequestMapping(value = "/inve/getInfoByMethod.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> getInfoByMethod(WmsInveCommissionRankingRewardSearchBeanVO queryInfo) {
		return wmsinvecommissionrankingrewardService.getInfoByMethod(queryInfo);
	}
}