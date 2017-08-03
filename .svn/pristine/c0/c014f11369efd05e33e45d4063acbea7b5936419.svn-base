package com.zx.emanage.inve.web;

import java.util.List;
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

import com.zx.emanage.inve.exception.InveTransSysException;
import com.zx.emanage.inve.exception.InveTransaConcurrentException;
import com.zx.emanage.inve.service.IWmsInveDebtOlnclaimsService;
import com.zx.emanage.inve.vo.WmsInveDebtOlnclaimsSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveDebtOlnclaims;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveDebtOlnclaimsController {
	private static Logger log = LoggerFactory.getLogger(WmsInveDebtOlnclaimsController.class);
	
	@Autowired
	private IWmsInveDebtOlnclaimsService wmsinvedebtolnclaimsService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvedebtolnclaimswithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveDebtOlnclaimsSearchBeanVO queryInfo) {
		return wmsinvedebtolnclaimsService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvedebtolnclaimswithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveDebtOlnclaimsSearchBeanVO queryInfo) {
		return wmsinvedebtolnclaimsService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveDebtOlnclaimsVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvedebtolnclaimsinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveDebtOlnclaims getInfoByPK(Integer wms_inve_debt_olnclaims_id) {
		return wmsinvedebtolnclaimsService.getInfoByPK(wms_inve_debt_olnclaims_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvedebtolnclaimssave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveDebtOlnclaims bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvedebtolnclaimsService.save(bean, user);
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
	 * Description : 债权变更申请  添加信息到变更主表
	 * @param json
	 * @return "success" or "error" or user defined
	 * @author yangqiyu
	 */	
	@RequestMapping(value = "/inve/wmsinvedebtolnclaimssavelist.do", method = {RequestMethod.POST})
	@ResponseBody
	public String saveforjson(HttpServletRequest request, String beanJSON) {
		String result = "";
		HttpSession session = request.getSession();
		List<WmsInveDebtOlnclaims> list = JsonUtil.jsonArrayToList(beanJSON,WmsInveDebtOlnclaims.class);
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvedebtolnclaimsService.savelist(list, user);
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
	@RequestMapping(value = "/inve/wmsinvedebtolnclaimsupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveDebtOlnclaims bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvedebtolnclaimsService.update(bean, user);
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
	 * @Title: changeCreditor 
	 * @Description: 债权调整
	 * @param request
	 * @param bean
	 * @return String 
	 * @throws
	 * @author lvtu 
	 * @date 2015年10月27日 下午1:33:35
	 */
	@RequestMapping(value = "/inve/wmsinvedebtolnclaimschangecreditor.do", method = {RequestMethod.POST})
	@ResponseBody
	public String changeCreditorSave(HttpServletRequest request, String transaMatch, 
            String transMatchOld, String transaMatchNew, Integer wms_inve_debt_head_id,
            String taskId, String surplus, String wms_inve_transa_id, String wms_inve_transa_prod_id) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
		    result = wmsinvedebtolnclaimsService.changeCreditorSave(transaMatch, 
                    transMatchOld, transaMatchNew, wms_inve_debt_head_id, taskId, 
                    surplus, wms_inve_transa_id, wms_inve_transa_prod_id, user);
		} catch (InveTransaConcurrentException e) {
            log.error(e.getMessage());
            result = "is_occupy";
        } catch (InveTransSysException e) {
            log.error(e.getMessage());
            result = "not_full";
        } catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		if("success".equals(result)){
			String msg = "理财债权变动-协议打印-债权调整";
			SysTools.saveLog(request, msg); 
		}
		return result;
	}
	
	/**
     * @Title: searchWmsInveDebtOlnclaimsList
     * @Description: 初始化根据债权调整主表id查询债权调整表信息
     * @param requset
     * @return Map<String, Object> 
     * @throws
     * @author wangihan 
     * @date 2015年11月26日
     */
    @RequestMapping(value = "/inve/selectOlnclaimsByWmsInveDebtHeadId.do", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> searchWmsInveDebtOlnclaimsList(HttpServletRequest request, HttpServletResponse res, 
            WmsInveDebtOlnclaimsSearchBeanVO queryInfo) {
        log.debug("--------------------初始化根据债权调整主表id查询债权调整表信息处理开始--------------------");
        
        queryInfo.setWms_inve_debt_head_id(Integer.parseInt(request.getParameter("wms_inve_debt_head_id").toString()));
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        
        log.debug("--------------------初始化根据债权调整主表id查询债权调整表信息处理结束--------------------");
        return wmsinvedebtolnclaimsService.selectOlnclaimsByWmsInveDebtHeadId(queryInfo, user);
    }
	
	/**
     * @Title: changeCreditorTempSave 
     * @Description: 债权调整暂存
     * @param HttpServletRequest request, String transaMatch, String transaMatchNew, Integer wms_inve_debt_head_id,
              String taskId, String surplus, String wms_inve_transa_id, String wms_inve_transa_prod_id
     * @return String 
     * @throws
     * @author wangihan 
     * @date 2015年11月26日
     */
    @RequestMapping(value = "/inve/wmsinvedebtolnclaimschangecreditorTempSave.do", method = {RequestMethod.POST})
    @ResponseBody
    public String changeCreditorTempSave(HttpServletRequest request, String transaMatch, 
            String transMatchOld, String transaMatchNew, Integer wms_inve_debt_head_id,
            String taskId, String surplus, String wms_inve_transa_id, String wms_inve_transa_prod_id) {
        log.debug("--------------------债权调整暂存处理开始--------------------");
        
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        try {
            result = wmsinvedebtolnclaimsService.changeCreditorTempSave(transaMatch, 
                    transMatchOld, transaMatchNew, wms_inve_debt_head_id, taskId, 
                    surplus, wms_inve_transa_id, wms_inve_transa_prod_id, user);
        } catch (InveTransaConcurrentException e) {
            log.error(e.getMessage());
            result = "is_occupy";
        } catch (InveTransSysException e) {
            log.error(e.getMessage());
            result = "not_full";
        } catch (Exception e) {
            log.error(e.getMessage());
            result = "error";
        }
        if("success".equals(result)){
            String msg = "理财债权变动-协议打印-债权暂存调整";
            SysTools.saveLog(request, msg); 
        }
        
        log.debug("--------------------债权调整暂存处理结束--------------------");
        return result;
    }
	
}