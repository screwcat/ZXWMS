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

import com.zx.emanage.inve.service.IWmsInveCommissionGeneralRulesNewService;
import com.zx.emanage.inve.vo.WmsInveCommissionGeneralRulesNewSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralRulesNew;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveCommissionGeneralRulesNewController {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionGeneralRulesNewController.class);
	
	@Autowired
	private IWmsInveCommissionGeneralRulesNewService wmsinvecommissiongeneralrulesnewService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommissiongeneralrulesnewwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionGeneralRulesNewSearchBeanVO queryInfo) {
		return wmsinvecommissiongeneralrulesnewService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommissiongeneralrulesnewwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveCommissionGeneralRulesNewSearchBeanVO queryInfo) {
		return wmsinvecommissiongeneralrulesnewService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionGeneralRulesNewVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommissiongeneralrulesnewinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveCommissionGeneralRulesNew getInfoByPK(Integer wms_inve_commission_general_rules_new_id) {
		return wmsinvecommissiongeneralrulesnewService.getInfoByPK(wms_inve_commission_general_rules_new_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommissiongeneralrulesnewsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveCommissionGeneralRulesNew bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissiongeneralrulesnewService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinvecommissiongeneralrulesnewupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveCommissionGeneralRulesNew bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissiongeneralrulesnewService.update(bean, user);
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
     * Description :获取一般佣金规则所属公司下拉列表id87  --产品对应人员页面中也有使用 获取人员分组信息 字典表id89 baisong
     * @param WmsInveCommissionGeneralRulesSearchBeanVO
     * @return map
     * @author wangyihan
     * @date 2015/12/11
     */ 
    @RequestMapping(value = "/inve/wmsinvecommissiongeneralrulesnewinfo.do", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getBelongCompanyList(WmsInveCommissionGeneralRulesNewSearchBeanVO beanvo) {
        return wmsinvecommissiongeneralrulesnewService.getBelongCompanyList(beanvo);
    }
	
	/**
     * Description :获取一般佣金规则信息-根据beanvo中参数不同获取不同类型的信息
     * @param WmsInveCommissionGeneralRulesSearchBeanVO
     * @return map
     * @author wangyihan
     * @date 2015/12/11
     */ 
    @RequestMapping(value = "/inve/wmsinvecommissiongeneralrulesnewinfo.do", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> getInfo(WmsInveCommissionGeneralRulesNewSearchBeanVO beanvo) {
        return wmsinvecommissiongeneralrulesnewService.getInfo(beanvo);
    }
    
    /**
     * Description :保存规则信息
     * @param WmsInveCommissionGeneralRulesNewSearchBeanVO WmsInveCommissionGeneralRulesNew HttpServletRequest
     * @return String
     * @author baisong
     * @date 2015/9/17
     */ 
    @RequestMapping(value = "/inve/saveRules.do", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String saveRules(HttpServletRequest request, WmsInveCommissionGeneralRulesNew bean, WmsInveCommissionGeneralRulesNewSearchBeanVO beanVO) {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        try {
            result = wmsinvecommissiongeneralrulesnewService.saveRules(user, bean, beanVO);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = "error";
        }       
        // record log   
        if("success".equals(result)){
            String msg = "系统管理--理财佣金一般规则(新)--保存";
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }
    
    
	
}