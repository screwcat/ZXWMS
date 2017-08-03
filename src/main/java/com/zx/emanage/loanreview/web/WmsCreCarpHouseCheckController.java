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

import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanreview.service.IWmsCreCarpHouseCheckService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreCarpHouseAssessment;
import com.zx.emanage.util.gen.entity.WmsCreCarpHouseCheck;
import com.zx.emanage.util.gen.entity.WmsCreHousingAssessment;
import com.zx.emanage.util.gen.entity.WmsCreHousingCheck;
import com.zx.emanage.loanreview.vo.WmsCreCarpHouseCheckSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCarpHouseCheckController {
	private static Logger log = LoggerFactory.getLogger(WmsCreCarpHouseCheckController.class);
	
	@Autowired
	private IWmsCreCarpHouseCheckService wmscrecarphousecheckService;

	/**
	 * Description :get record list  车贷办件查询列表导出excel
	 * @param queryInfo
	 * @return record list
	 * @author baisong
	 */
	@RequestMapping(value = "/loanreview/wmscrecarphousecheckwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsCreCarpHouseCheckSearchBeanVO queryInfo,HttpServletRequest request) {
		HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecarphousecheckService.getListWithoutPaging(queryInfo,user);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging--车贷办件
	 * @param queryInfo
	 * @return record list
	 * @author baisong
	 */
	@RequestMapping(value = "/loanreview/wmscrecarphousecheckwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsCreCarpHouseCheckSearchBeanVO queryInfo,HttpServletRequest request) {
		HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecarphousecheckService.getListWithPaging(queryInfo,user);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCarpHouseCheckVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanreview/wmscrecarphousecheckinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsCreCarpHouseCheck getInfoByPK(Integer wms_cre_carp_house_check_id) {
		return wmscrecarphousecheckService.getInfoByPK(wms_cre_carp_house_check_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanreview/wmscrecarphousechecksave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsCreCarpHouseCheck bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecarphousecheckService.save(bean, user);
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
	@RequestMapping(value = "/loanreview/wmscrecarphousecheckupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsCreCarpHouseCheck bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecarphousecheckService.update(bean, user);
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
     * Description :add method--车贷办件保存更新
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @RequestMapping(value = "/loancheck/wmscrecarchecksavenew.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCarpHouseCheck bean,
    		WmsCreCarpHouseAssessment wmsCreCarpHouseAssessment, WmsCarLoanWorkFlowVO wVo, String fileArr)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecarphousecheckService.save(bean, wmsCreCarpHouseAssessment, user, wVo, fileArr);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "贷前管理-车贷审核-办件审核组-审核保存";
            SysTools.saveLog(request, msg);
        }
        return result;
    }
    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreHousingCheckVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecarpcheckinfo.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCarpHouseCheck getInfo(Integer wms_cre_credit_head_id)
    {
    	WmsCreCarpHouseCheck c = wmscrecarphousecheckService.getInfo(wms_cre_credit_head_id);
        return wmscrecarphousecheckService.getInfo(wms_cre_credit_head_id);
    }

}