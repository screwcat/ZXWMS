package com.zx.emanage.reportmanage.web;

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

import com.zx.emanage.reportmanage.service.IWmsInveWagePerformanceService;
import com.zx.emanage.util.gen.entity.WmsInveWagePerformance;
import com.zx.emanage.reportmanage.vo.WmsInveWagePerformanceSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveWagePerformanceController {
	private static Logger log = LoggerFactory.getLogger(WmsInveWagePerformanceController.class);
	
	@Autowired
	private IWmsInveWagePerformanceService wmsinvewageperformanceService;

	/**
	 * Description :实现理财工资导出功能
	 * @param queryInfo
	 * @return record list
	 * @author hancd
	 */
	@RequestMapping(value = "/reportmanage/wmsinvewageperformancewithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveWagePerformanceSearchBeanVO queryInfo) {
		return wmsinvewageperformanceService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :实现理财工资列表显示功能
	 * @param queryInfo
	 * @return record list
	 * @author hancd
	 */
	@RequestMapping(value = "/reportmanage/wmsinvewageperformancewithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveWagePerformanceSearchBeanVO queryInfo) {
		return wmsinvewageperformanceService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :通过单据主键查询相关一条数据信息
	 * @param primary key 
	 * @return WmsInveWagePerformanceVO
	 * @author hancd
	 */	
	@RequestMapping(value = "/reportmanage/wmsinvewageperformanceinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveWagePerformance getInfoByPK(Integer wms_inve_wage_performance_id) {
		return wmsinvewageperformanceService.getInfoByPK(wms_inve_wage_performance_id);
	}	

}