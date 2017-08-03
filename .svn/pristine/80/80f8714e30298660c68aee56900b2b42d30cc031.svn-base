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

import com.zx.emanage.inve.service.IWmsInveUserTodoService;
import com.zx.emanage.util.gen.entity.WmsInveUserTodo;
import com.zx.emanage.inve.vo.WmsInveUserTodoSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveUserTodoController {
	private static Logger log = LoggerFactory.getLogger(WmsInveUserTodoController.class);
	
	@Autowired
	private IWmsInveUserTodoService wmsinveusertodoService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinveusertodowithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveUserTodoSearchBeanVO queryInfo) {
		return wmsinveusertodoService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinveusertodowithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveUserTodoSearchBeanVO queryInfo) {
		return wmsinveusertodoService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveUserTodoVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinveusertodoinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveUserTodo getInfoByPK(Integer wms_inve_user_todo_id) {
		return wmsinveusertodoService.getInfoByPK(wms_inve_user_todo_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinveusertodosave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveUserTodo bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinveusertodoService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinveusertodoupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveUserTodo bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinveusertodoService.update(bean, user);
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
     * @Deprecated 验证当前登录用户是否存在待办事项
     * @param request
     * @param response
     * @return 返回map集合json字符串, 返回的参数是 type=0 代表没有待办事项, 返回 type=1表示存在待办事项
     * @author donghao
     * @date 2016年9月8日16:09:26
     */
    @RequestMapping(value="/inve/verifyCurrentUserIsExistenceMatter.do", method={RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> verifyCurrentUserIsExistenceMatter(HttpServletRequest request, HttpServletResponse response){
    	HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        Map<String, Object> resultMap = wmsinveusertodoService.findWmsInveUserToByUserId(user.getUserId());
    	return resultMap;
    }
    
    /**
     * 根据登录人查询用户代办事项信息
     * @param request
     * @return
     */
    @RequestMapping(value="/inve/getusertodobyloginuser.do", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> getUserTodoByLoginUser(HttpServletRequest request){
    	HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinveusertodoService.getUserTodoByLoginUser(user);
    }
}