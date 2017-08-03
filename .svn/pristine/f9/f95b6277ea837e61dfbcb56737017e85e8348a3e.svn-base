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

import com.zx.emanage.inve.service.IWmsInveSalaryPreItemService;
import com.zx.emanage.inve.vo.WmsInveSalaryPreItemSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveSalaryPreTotalSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveSalarySetWorkFlowVO;
import com.zx.emanage.util.gen.entity.WmsInveSalaryPreItem;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveSalaryPreItemController {
	private static Logger log = LoggerFactory.getLogger(WmsInveSalaryPreItemController.class);
	
	@Autowired
	private IWmsInveSalaryPreItemService wmsinvesalarypreitemService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvesalarypreitemwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveSalaryPreItemSearchBeanVO queryInfo) {
		return wmsinvesalarypreitemService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvesalarypreitemwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveSalaryPreItemSearchBeanVO queryInfo) {
		return wmsinvesalarypreitemService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveSalaryPreItemVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvesalarypreiteminfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveSalaryPreItem getInfoByPK(Integer wms_inve_salary_pre_item_id) {
		return wmsinvesalarypreitemService.getInfoByPK(wms_inve_salary_pre_item_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvesalarypreitemsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveSalaryPreItem bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvesalarypreitemService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinvesalarypreitemupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveSalaryPreItem bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvesalarypreitemService.update(bean, user);
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
     * @Title: getPerformanceSalaryPreItemsByDeptId
     * @Description: 根据部门id查询该部门下员工业绩详细列表
     * @param queryInfo
     * @param request
     * @param response
     * @return 
     * @author: zhangyunfei
     * @time:2017年1月11日 上午11:15:14
     * history:
     * 1、2017年1月11日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/getPerformanceSalaryPreItemsByDeptId.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getPerformanceSalaryPreItemsByDeptId(WmsInveSalaryPreItemSearchBeanVO queryInfo, HttpServletRequest request, HttpServletResponse response)
    {
        Map<String, Object> resultMap = wmsinvesalarypreitemService.getPerformanceSalaryPreItemsByDeptId(queryInfo);

        return resultMap;
    }

    /**
     * 
     * @Title: updatePerformanceSalaryPreItemsById
     * @Description: 根据主键更新工资前提配置表是否开支和绩效工资（批量更新）
     * @param request
     * @param gridData
     * @return 
     * @author: zhangyunfei
     * @time:2017年1月12日 下午2:45:11
     * history:
     * 1、2017年1月12日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/updatePerformanceSalaryPreItemsById.do", method = { RequestMethod.POST })
    @ResponseBody
    public String updatePerformanceSalaryPreItemsById(HttpServletRequest request, String gridData, String wms_inve_salary_pre_total_id, String create_user_id, WmsInveSalaryPreTotalSearchBeanVO wmsInveSalaryPreTotalVO)
    {
        String result = "";
        try
        {
            UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
            result = wmsinvesalarypreitemService.updatePerformanceSalaryPreItemsById(gridData, user, wms_inve_salary_pre_total_id, create_user_id, wmsInveSalaryPreTotalVO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        return result;
    }

    /**
     * 
     * @Title: /inve/approvePerformanceSalaryPre.do
     * @Description: 工资前提配置审批操作
     * @param request
     * @param gridData
     * @return 
     * @author: zhangyunfei
     * @time:2017年1月12日 下午2:45:11
     * history:
     * 1、2017年1月12日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/approvePerformanceSalaryPre.do", method = { RequestMethod.POST })
    @ResponseBody
    public String approvePerformanceSalaryPre(HttpServletRequest request, String gridData, WmsInveSalarySetWorkFlowVO vo, WmsInveSalaryPreTotalSearchBeanVO wmsInveSalaryPreTotalVO)
    {
        String result = "";
        try
        {
            UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
            result = wmsinvesalarypreitemService.approvePerformanceSalaryPre(gridData, vo, user, wmsInveSalaryPreTotalVO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        return result;
    }
}