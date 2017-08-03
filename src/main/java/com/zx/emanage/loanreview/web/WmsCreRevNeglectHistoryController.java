package com.zx.emanage.loanreview.web;

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

import com.zx.emanage.loanreview.service.IWmsCreRevNeglectHistoryService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreRevNeglectHistory;
import com.zx.emanage.loanreview.vo.WmsCreRevNeglectHistorySearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreRevNeglectHistoryController {
	private static Logger log = LoggerFactory.getLogger(WmsCreRevNeglectHistoryController.class);
	
	@Autowired
	private IWmsCreRevNeglectHistoryService wmscrerevneglecthistoryService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanreview/wmscrerevneglecthistorywithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsCreRevNeglectHistorySearchBeanVO queryInfo) {
		return wmscrerevneglecthistoryService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanreview/wmscrerevneglecthistorywithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsCreRevNeglectHistorySearchBeanVO queryInfo) {
		return wmscrerevneglecthistoryService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreRevNeglectHistoryVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanreview/wmscrerevneglecthistoryinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsCreRevNeglectHistory getInfoByPK(Integer wms_cre_rev_neglect_history_id) {
		return wmscrerevneglecthistoryService.getInfoByPK(wms_cre_rev_neglect_history_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanreview/wmscrerevneglecthistorysave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsCreRevNeglectHistory bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrerevneglecthistoryService.save(bean, user);
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
	@RequestMapping(value = "/loanreview/wmscrerevneglecthistoryupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsCreRevNeglectHistory bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrerevneglecthistoryService.update(bean, user);
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
     * @Title: getCreHousingIgonre 
     * @Description: 房贷审核忽略
     * @return    
     * @return Map<String,Object>    
     * @throws
     * @author lvtu 
     * @date 2015年7月7日 上午9:51:36
     */
    @RequestMapping(value = "/loanreview/houserunapprovalignore.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String creHousingIgonre(HttpServletRequest request, Integer taskId, Integer wms_cre_credit_head_id, String flag) {
    	String result = "";
        try {
        	HttpSession session = request.getSession();
            UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
            // 
            result = wmscrerevneglecthistoryService.creHousingIgonre(taskId, wms_cre_credit_head_id, user, flag);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = "error";
        }
        if ("success".equals(result)) {
        	String lcz = "";
        	if("ls".equals(flag)) {
        		lcz = "流水审核组";
        	} else if("xd".equals(flag)) {
        		lcz = "信息调查组";
        	} else if("ds".equals(flag)) {
        		lcz = "电审审核组";
        	}
            String msg = "贷前管理-房贷审核-"+lcz+"-忽略";
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }
}