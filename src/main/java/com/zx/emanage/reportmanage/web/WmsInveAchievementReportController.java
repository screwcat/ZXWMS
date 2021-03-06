package com.zx.emanage.reportmanage.web;

import java.text.ParseException;
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

import com.zx.emanage.reportmanage.service.IWmsInveAchievementReportService;
import com.zx.emanage.reportmanage.vo.WmsInveAchievementReportVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * @ClassName: WmsInveAchievementReportController
 * @Description: 业绩报表 Controller
 * @author WangShuai
 * @date 2016年12月2日
 * @version V1.0
 * history:
 * 1、2016年12月2日 WangShuai 创建文件
 */
@Controller
public class WmsInveAchievementReportController {
	private static Logger log = LoggerFactory.getLogger(WmsInveAchievementReportController.class);
	@Autowired
	IWmsInveAchievementReportService wmsInveAchievementReportService;
	
	
	/**
	 * getWmsinveAchievementAllColumns
	 * 获取表头
	 * @param wmsInveAchievementReportVO
	 * @return
	 * @throws ParseException 
	 * Map<String,Object>
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/reportmanage/getWmsinveAchievementAllColumns.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getWmsinveAchievementAllColumns(WmsInveAchievementReportVO wmsInveAchievementReportVO) throws ParseException {
		return wmsInveAchievementReportService.getWmsinveAchievementAllColumns(wmsInveAchievementReportVO);
	}

	
	
	/**
	 * wmsInveAchievementPerStatisticswithpaginglist
	 * 获取个人统计
	 * @param wmsInveAchievementReportVO
	 * @return
	 * @throws ParseException 
	 * Map<String,Object>
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/reportmanage/wmsInveAchievementPerStatisticswithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> wmsInveAchievementPerStatisticswithpaginglist(WmsInveAchievementReportVO wmsInveAchievementReportVO,HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
    	UserBean user=(UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsInveAchievementReportService.getAchievementPerStatisticswithpaginglist(wmsInveAchievementReportVO,user);
	}
	
	/**
	 * wmsInveAchievementTeamStatisticswithpaginglist
	 * 获取团队统计信息
	 * @param wmsInveAchievementReportVO
	 * @return
	 * @throws ParseException 
	 * Map<String,Object>
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/reportmanage/wmsInveAchievementTeamStatisticswithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> wmsInveAchievementTeamStatisticswithpaginglist(WmsInveAchievementReportVO wmsInveAchievementReportVO,HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
    	UserBean user=(UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsInveAchievementReportService.getAchievementTeamStatisticswithpaginglist(wmsInveAchievementReportVO,user);
	}
	
	
	
	/**
	 * @Title: wmsInveAchievementCheckDateToStatic
	 * @Description: 判断时间是否进行日报表统计
	 * @param wmsInveAchievementReportVO
	 * @param request
	 * @return
	 * @throws ParseException 
	 * @author: WangShuai
	 * @time:2016年12月15日 下午5:23:03
	 * history:
	 * 1、2016年12月15日 WangShuai 创建方法
	*/
    @RequestMapping(value = "/export/achievementStaticticsCheckDateToStatic.do", method = { RequestMethod.GET,
                                                                                           RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> wmsInveAchievementCheckDateToStatic(WmsInveAchievementReportVO wmsInveAchievementReportVO,HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
    	UserBean user=(UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsInveAchievementReportService.getCheckDateToStatic(wmsInveAchievementReportVO,user);
	}
	 
	/**
	 * achievementStaticticsExportPoi
	 * 业绩导出
	 * @param request
	 * @param response
	 * @param wmsInveAchievementReportVO
	 * @throws ParseException 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
    @RequestMapping(value = { "/export/achievementStaticticsExportPoi.do" }, method = { RequestMethod.GET,
                                                                                       RequestMethod.POST })
	  @ResponseBody
	  public Map<String,Object> achievementStaticticsExportPoi(HttpServletRequest request, HttpServletResponse response,WmsInveAchievementReportVO wmsInveAchievementReportVO) throws ParseException{
		HttpSession session = request.getSession();
    	UserBean user=(UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsInveAchievementReportService.doAchievementStaticticsExportPoi(request,response,wmsInveAchievementReportVO,user);
	  }
	
	
	
	/**
	 * achievementDayStaStaticticsExportPoi
	 * 业绩报表每日汇总导出
	 * @param request
	 * @param response
	 * @param wmsInveAchievementReportVO
	 * @throws ParseException 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
    @RequestMapping(value = { "/export/achievementDayStaStaticticsExportPoi.do" }, method = { RequestMethod.GET,
                                                                                             RequestMethod.POST })
	  @ResponseBody
	  public Map<String, Object> achievementDayStaStaticticsExportPoi(HttpServletRequest request, HttpServletResponse response,WmsInveAchievementReportVO wmsInveAchievementReportVO) throws ParseException{
		 HttpSession session = request.getSession();
	     UserBean user=(UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		 return wmsInveAchievementReportService.doachievementDayStaStaticticsExportPoi(request,response,wmsInveAchievementReportVO,user);
		  
	  }
}
