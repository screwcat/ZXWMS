package com.zx.emanage.inve.web;

import java.util.HashMap;
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

import com.zx.emanage.inve.service.IWmsInveSalaryPreItemService;
import com.zx.emanage.inve.service.IWmsInveSalaryPreTotalService;
import com.zx.emanage.inve.service.IWmsInveWorkFlowService;
import com.zx.emanage.inve.vo.WmsInveSalaryPreTotalSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveSalarySetWorkFlowVO;
import com.zx.emanage.util.gen.entity.WmsInveSalaryPreTotal;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveSalaryPreTotalController
 * 模块名称：
 * @Description: 内容摘要：
 * @author DongHao
 * @date 2017年1月7日
 * @version V1.0
 * history:
 * 1、2017年1月7日 DongHao 创建文件
 */
@Controller
public class WmsInveSalaryPreTotalController {
	private static Logger log = LoggerFactory.getLogger(WmsInveSalaryPreTotalController.class);
	
	@Autowired
	private IWmsInveSalaryPreTotalService wmsinvesalarypretotalService;

    @Autowired
    private IWmsInveSalaryPreItemService wmsInveSalaryPreItemService;

    @Autowired
    private IWmsInveWorkFlowService aInveWorkFlowService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvesalarypretotalwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveSalaryPreTotalSearchBeanVO queryInfo) {
		return wmsinvesalarypretotalService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvesalarypretotalwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveSalaryPreTotalSearchBeanVO queryInfo) {
		return wmsinvesalarypretotalService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveSalaryPreTotalVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvesalarypretotalinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveSalaryPreTotal getInfoByPK(Integer wms_inve_salary_pre_total_id) {
		return wmsinvesalarypretotalService.getInfoByPK(wms_inve_salary_pre_total_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvesalarypretotalsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveSalaryPreTotal bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvesalarypretotalService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinvesalarypretotalupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveSalaryPreTotal bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvesalarypretotalService.update(bean, user);
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
     * @Title: getTeamLis
     * @Description: 获取当前登录人所属团队
     * @param request
     * @param response
     * @return 
     * @author: DongHao
     * @time:2017年1月7日 下午5:15:03
     * history:
     * 1、2017年1月7日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getTeamLis.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<Map<String, Object>> getTeamLis(String menu_id, HttpServletRequest request, HttpServletResponse response)
    {
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        List<Map<String, Object>> resultList = wmsinvesalarypretotalService.getTeamLis(user, menu_id);
        return resultList;
    }

    /**
     * 
     * @Title: getPerformanceSalarySettingInfos
     * @Description: 获取工资设定单列表信息
     * @param queryInfo
     * @param request
     * @param response
     * @return 
     * @author: DongHao
     * @time:2017年1月7日 下午5:52:45
     * history:
     * 1、2017年1月7日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getPerformanceSalarySettingInfos.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getPerformanceSalarySettingInfos(WmsInveSalaryPreTotalSearchBeanVO queryInfo, HttpServletRequest request, HttpServletResponse response)
    {
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        Map<String, Object> resultMap = wmsinvesalarypretotalService.getPerformanceSalarySettingInfos(user, queryInfo);
        return resultMap;
    }


    /**
     * 
     * @Title: getPerformanceSalaryAuditInfos
     * @Description: 获取待审核的单据
     * @param queryInfo
     * @param request
     * @param response
     * @return 
     * @author: DongHao
     * @time:2017年1月8日 下午1:53:08
     * history:
     * 1、2017年1月8日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getPerformanceSalaryAuditInfos.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getPerformanceSalaryAuditInfos(WmsInveSalaryPreTotalSearchBeanVO queryInfo, HttpServletRequest request, HttpServletResponse response)
    {
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        Map<String, Object> resultMap = wmsinvesalarypretotalService.getPerformanceSalaryAuditInfos(user, queryInfo);
        return resultMap;
    }

    /**
     * 
     * @Title: getDataStatus
     * @Description: 获取工资设定的单据状态
     * @param wms_inve_dict_id
     * @param request
     * @param response
     * @return 
     * @author: DongHao
     * @time:2017年1月12日 上午10:49:41
     * history:
     * 1、2017年1月12日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getDataStatus.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<Map<String, Object>> getDataStatus(String wms_sys_dict_id, HttpServletRequest request, HttpServletResponse response)
    {
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        List<Map<String, Object>> list = wmsinvesalarypretotalService.getDataStatus(wms_sys_dict_id, user);
        return list;
    }

    /**
     * 
     * @Title: inveSalarySetAuditInfos
     * @Description: 执行审核操作
     * @param vo
     * @param request
     * @param response
     * @return 
     * @author: DongHao
     * @time:2017年1月12日 下午3:16:48
     * history:
     * 1、2017年1月12日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/inveSalarySetAuditInfos.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> inveSalarySetAuditInfos(WmsInveSalarySetWorkFlowVO vo, HttpServletRequest request, HttpServletResponse response)
    {
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        Map<String, Object> resultMap = wmsinvesalarypretotalService.inveSalarySetAuditInfos(vo, user);
        return resultMap;
    }

    /**
     * 
     * @Title: getJobTime
     * @Description: 判断当前系统时间提交绩效工资设定单是否允许
     * @return 
     * @author: DongHao
     * @time:2017年1月17日 下午5:07:47
     * history:
     * 1、2017年1月17日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getJobTime.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getJobTime(HttpServletRequest request, HttpServletResponse response)
    {
        Map<String, Object> resultMap = wmsinvesalarypretotalService.getJobTime();
        return resultMap;
    }

    /**
     * 
     * @Title: getGeneralAdvice
     * @Description: 获取待修订状态的单据的驳回意见
     * @param wms_inve_salary_pre_total_id
     * @return 
     * @author: DongHao
     * @time:2017年1月4日 下午2:53:10
     * history:
     * 1、2017年1月4日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getGeneralAdvice.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getGeneralAdvice(String wms_inve_salary_pre_total_id)
    {
        Map<String, Object> resultMap = wmsinvesalarypretotalService.getGeneralAdvice(wms_inve_salary_pre_total_id);
        return resultMap;
    }
    
    /**
     * 
     * @Title: getSalarySetProcessInfos
     * @Description: 获取工资设定的流程历程信息
     * @param wms_inve_salary_pre_total_id 工资设定表的总表主键
     * @return 返回map集合流程历程信息
     * @author: DongHao
     * @time:2017年5月1日 下午10:23:48
     * history:
     * 1、2017年5月1日 DongHao 创建方法
     */
    @RequestMapping(value="/inve/getSalarySetProcessInfos.do", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getSalarySetProcessInfos(Integer wms_inve_salary_pre_total_id)
    {
        
        return wmsinvesalarypretotalService.getSalarySetProcessInfos(wms_inve_salary_pre_total_id);
    }

    /**
     * 
     * @Title: getPerformanceSalaryApproveInfosMoa
     * @Description: 主键查询单据的赎回详细信息和流程
     * @param wms_inve_salary_pre_total_ids
     * @param bill_attr 
     * @return 
     * @author: zhangyunfei
     * @time:2017年04月19日 上午10:49:14
     * history:
     * 1、2017年04月19日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/getPerformanceSalaryApproveInfosMoa.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getPerformanceSalaryApproveInfosMoa(String wms_inve_salary_pre_total_ids, String bill_attr)
    {
        Map<String, Object> map = new HashMap<>();
        try
        {
            // list结构 团队基础信息
            // --->团队开资详细信息(map(list))
            // ---->流程信息 (map(list))
            List<Map<String, Object>> list = wmsinvesalarypretotalService.getPerformanceSalaryApproveInfosMoa(wms_inve_salary_pre_total_ids, bill_attr);
            map.put("ret_data", list);
            map.put("ret_code", "000");
            map.put("ret_msg", "操作成功");
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            map.put("ret_code", "002");
            map.put("ret_msg", "操作失败，请联系客服");
        }

        return map;
    }

    /**
     * 
     * @Title: approvePerformanceSalaryPreMoa
     * @Description: 手持审批绩效工资
     * @param advice(审批意见)
     * @param is_pay(审核结果)
     * @param dept_ids(部门id集合)
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月24日 上午10:46:37
     * history:
     * 1、2017年4月24日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/approvePerformanceSalaryPreMoa.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> approvePerformanceSalaryPreMoa(String advice, String pass, String personnel_shortcode, String dept_ids)
    {
        Map<String, Object> map = new HashMap<>();
        String result = "";
        result = wmsInveSalaryPreItemService.approvePerformanceSalaryPreMoa(advice, pass, personnel_shortcode, dept_ids);
        if ("success".equals(result))
        {
            map.put("ret_data", "");
            map.put("ret_code", "000");
            map.put("ret_msg", "操作成功");
        }
        else
        {
            map.put("ret_data", "");
            map.put("ret_code", "002");
            map.put("ret_msg", "操作失败，请联系客服");
        }
        return map;
    }
}