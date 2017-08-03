package com.zx.emanage.inve.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsPersonnelAchievementHisService;
import com.zx.emanage.inve.vo.WmsPersonnelAchievementHisSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsPersonnelAchievementHis;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsPersonnelAchievementHisController {
	private static Logger log = LoggerFactory.getLogger(WmsPersonnelAchievementHisController.class);
	
	@Autowired
	private IWmsPersonnelAchievementHisService wmspersonnelachievementhisService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmspersonnelachievementhiswithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsPersonnelAchievementHisSearchBeanVO queryInfo) {
		return wmspersonnelachievementhisService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmspersonnelachievementhiswithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsPersonnelAchievementHisSearchBeanVO queryInfo) {
		return wmspersonnelachievementhisService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsPersonnelAchievementHisVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmspersonnelachievementhisinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsPersonnelAchievementHis getInfoByPK(Integer wms_personnel_achievement_his_id) {
		return wmspersonnelachievementhisService.getInfoByPK(wms_personnel_achievement_his_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmspersonnelachievementhissave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsPersonnelAchievementHis bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmspersonnelachievementhisService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmspersonnelachievementhisupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsPersonnelAchievementHis bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmspersonnelachievementhisService.update(bean, user);
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
     * 
     * @Title: getPersonnelAchievementByPid
     * @Description: 通过人员Id和static_month查询员工本月 上季度 本季度业绩
     * @param queryInfo
     * @param request
     * @param response
     * @return 
     * @author: zhangyunfei
     * @time:2017年1月12日 下午2:47:42
     * history:
     * 1、2017年1月12日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/getPersonnelAchievementByPid.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getPersonnelAchievementByPid(WmsPersonnelAchievementHisSearchBeanVO queryInfo, HttpServletRequest request, HttpServletResponse response)
    {
        Map<String, Object> resultMap = wmspersonnelachievementhisService.getPersonnelAchievementByPid(queryInfo);

        return resultMap;
    }
}