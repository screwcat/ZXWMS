package com.zx.emanage.loanfina.web;

import java.util.List;
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

import com.zx.emanage.loanfina.service.IWmsFinaCrePosService;
import com.zx.emanage.util.gen.entity.WmsFinaCrePos;
import com.zx.emanage.loanfina.vo.WmsFinaCrePosSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsFinaCrePosController {
	private static Logger log = LoggerFactory.getLogger(WmsFinaCrePosController.class);
	
	@Autowired
	private IWmsFinaCrePosService wmsfinacreposService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanfina/wmsfinacreposwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsFinaCrePosSearchBeanVO queryInfo) {
		return wmsfinacreposService.getListWithoutPaging(queryInfo);
	}

	/**
	 * @Title: getListWithPaging 
	 * @Description: 财务POS机查询（分页）
	 * @param queryInfo
	 * @return    
	 * @return Map<String,Object>    
	 * @throws
	 * @author lvtu 
	 * @date 2015年6月29日 下午4:03:50
	 */
	@RequestMapping(value = "/loanfina/wmsfinacreposwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsFinaCrePosSearchBeanVO queryInfo) {
		return wmsfinacreposService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :通过财务部POS机主键，获取相应信息
	 * @param primary key 
	 * @return WmsFinaCrePosVO
	 * @author hancd
	 */	
	@RequestMapping(value = "/loanfina/wmsfinacreposinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsFinaCrePos getInfoByPK(Integer wms_fina_cre_pos_id) {
		return wmsfinacreposService.getInfoByPK(wms_fina_cre_pos_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanfina/wmsfinacrepossave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsFinaCrePos bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinacreposService.save(bean, user);
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
	 * @Title: doPosChange 
	 * @Description: TODO(新增/修改财务POS机信息) 
	 * @param request
	 * @param beanJSON
	 * @return    
	 * @return String    返回类型
	 * @throws
	 */
	@RequestMapping(value = "/loanfina/wmsfinacreposchange.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doPosChange(HttpServletRequest request, String beanJSON) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinacreposService.doChangePos(beanJSON, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		return result;
	}	

	/**
	 * Description :update method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanfina/wmsfinacreposupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsFinaCrePos bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinacreposService.update(bean, user);
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
	 * Description :获取实现财务还款POS机信息
	 * @param queryInfo
	 * @return record list
	 * @author hancd
	 */
	@RequestMapping(value = "/loanfina/getIDByEmpty.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<WmsFinaCrePos> getIDByEmpty(String isEmpty, String isAll) {
		return wmsfinacreposService.getIDByEmpty(isEmpty,isAll);
	}
}