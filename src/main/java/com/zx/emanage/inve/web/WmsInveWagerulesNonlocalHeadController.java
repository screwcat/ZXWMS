package com.zx.emanage.inve.web;

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

import com.zx.emanage.inve.service.IWmsInveCompayService;
import com.zx.emanage.inve.service.IWmsInveWagerulesNonlocalHeadService;
import com.zx.emanage.inve.vo.WmsInveWagerulesNonlocalHeadSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveWagerulesNonlocalLvSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveCompay;
import com.zx.emanage.util.gen.entity.WmsInveWagerulesNonlocalHead;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveWagerulesNonlocalHeadController {
	private static Logger log = LoggerFactory.getLogger(WmsInveWagerulesNonlocalHeadController.class);
	
	@Autowired
	private IWmsInveWagerulesNonlocalHeadService wmsinvewagerulesnonlocalheadService;
	
	@Autowired
	private IWmsInveCompayService iwmsinvecompayservice;
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvewagerulesnonlocalheadwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveWagerulesNonlocalHeadSearchBeanVO queryInfo) {
		return wmsinvewagerulesnonlocalheadService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :理财薪资规则列表显示
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/getFinancialSalaryRulePagingList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveWagerulesNonlocalHead queryInfo,HttpServletRequest request) {
		return wmsinvewagerulesnonlocalheadService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveWagerulesNonlocalHeadVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvewagerulesnonlocalheadinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveWagerulesNonlocalHead getInfoByPK(Integer wms_inve_wagerules_nonlocal_head_id) {
		return wmsinvewagerulesnonlocalheadService.getInfoByPK(wms_inve_wagerules_nonlocal_head_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvewagerulesnonlocalheadsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveWagerulesNonlocalHead bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvewagerulesnonlocalheadService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinvewagerulesnonlocalheadupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveWagerulesNonlocalHead bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvewagerulesnonlocalheadService.update(bean, user);
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
     * Description :根据提供的所要查询的公司ID，获取所有公司名称以及ID
     * @param isEmpty 代表请选择
     * @param isAll 代表全部
     * @author jiaodelong
     */
    @RequestMapping(value = "/inve/getAllSysCompantyWithList.do", method = {RequestMethod.GET})
    @ResponseBody
    public List<WmsInveCompay> getAllSysDeptWithList(String isEmpty,String isAll){
        List<WmsInveCompay> CompayList =iwmsinvecompayservice.getCompayListList(isEmpty,isAll);
        return CompayList;
    }
    
    /**
     * Description :保存薪资等级设置
     * @return string
     * @author jiaodelong
     * @data 2016年1月14日 15:08
     * 
     */
    @RequestMapping(value = "/inve/saveFinancialSalaryRule.do", method = {RequestMethod.POST})
    @ResponseBody
    public String saveFinancialSalaryRule(HttpServletRequest request,WmsInveWagerulesNonlocalLvSearchBeanVO beanlv,String flag){
    	String result = "";
	    HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        
        try
        {
            result = wmsinvewagerulesnonlocalheadService.saveFinancialSalaryRule(beanlv,user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "产品管理-理财配置管理-理财薪资规则";
            SysTools.saveLog(request, msg);
        }
        return result;
    }
    
    /**
     * Description :修改薪资等级设置
     * @return string
     * @author jiaodelong
     * @data 2016年1月14日 15:08
     * 
     */
    @RequestMapping(value = "/inve/updateFinancialSalaryRule.do", method = {RequestMethod.POST})
    @ResponseBody
    public String updateFinancialSalaryRule(HttpServletRequest request,WmsInveWagerulesNonlocalLvSearchBeanVO beanlv,String flag){
    	String result = "";
	    HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        
        try
        {
            result = wmsinvewagerulesnonlocalheadService.updateFinancialSalaryRule(beanlv,user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "产品管理-理财配置管理-理财薪资规则";
            SysTools.saveLog(request, msg);
        }
        return result;
    }
    
    /**
     * Description :理财薪资规则初始化
     * @return string
     * @author jiaodelong
     * @data 2016年1月14日 15:10
     * 
     */
    @RequestMapping(value = "/inve/initFinancialSalaryRule.do", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> initFinancialSalaryRule(HttpServletRequest request,WmsInveWagerulesNonlocalHead bean){
        return wmsinvewagerulesnonlocalheadService.getFinancialSalaryRuleAll(bean);
    }
    /**
     * Description 删除理财规则
     * @return string
     * @author jiaodelong
     * @data 2016年1月14日 15:08
     * 
     */
    @RequestMapping(value = "/inve/deleteFinancialSalaryRule.do", method = {RequestMethod.POST})
    @ResponseBody
    public String deleteFinancialSalaryRule(HttpServletRequest request,Integer wms_inve_wagerules_nonlocal_head_id){
    	String rs = "";
    	rs = wmsinvewagerulesnonlocalheadService.deleteFinancialSalaryRule(wms_inve_wagerules_nonlocal_head_id);
        return rs;
    }
    
    /**
     * Description :理财薪资规则复制
     * @return string
     * @author jiaodelong
     * @data 2016年1月14日 15:08
     * 
     */
    @RequestMapping(value = "/inve/copyFinancialSalaryRule.do", method = {RequestMethod.POST})
    @ResponseBody
    public String copyFinancialSalaryRule(HttpServletRequest request,Integer wms_inve_wagerules_nonlocal_head_id){
    	String result = "";
	    HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        
        try
        {
            result = wmsinvewagerulesnonlocalheadService.copyFinancialSalaryRule(wms_inve_wagerules_nonlocal_head_id,user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "产品管理-理财配置管理-理财薪资规则";
            SysTools.saveLog(request, msg);
        }
        return result;
    }
    
}