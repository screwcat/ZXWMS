package com.zx.emanage.reportmanage.service;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.emanage.reportmanage.vo.WmsInveAchievementReportVO;
import com.zx.sframe.util.vo.UserBean;

/**
 * @ClassName: IWmsInveAchievementReportService
 * @Description: 报表处理服务类
 * @author WangShuai
 * @date 2016年12月2日
 * @version V1.0
 * history:
 * 1、2016年12月2日 WangShuai 创建文件
 */
public interface IWmsInveAchievementReportService {

	
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
	Map<String, Object> getWmsinveAchievementAllColumns(
			WmsInveAchievementReportVO wmsInveAchievementReportVO) throws ParseException;

	
	/**
	 * getAchievementPerStatisticswithpaginglist
	 * 胡哦去个人统计信息
	 * @param wmsInveAchievementReportVO
	 * @param user 
	 * @return 
	 * Map<String,Object>
	 * @throws ParseException 
	 * @exception 
	 * @since  1.0.0
	 */
	Map<String, Object> getAchievementPerStatisticswithpaginglist(
			WmsInveAchievementReportVO wmsInveAchievementReportVO, UserBean user) throws ParseException;

	
	/**
	 * getAchievementTeamStatisticswithpaginglist
	 * 获取团队统计信息
	 * @param wmsInveAchievementReportVO
	 * @param user 
	 * @return 
	 * Map<String,Object>
	 * @throws ParseException 
	 * @exception 
	 * @since  1.0.0
	 */
	Map<String, Object> getAchievementTeamStatisticswithpaginglist(
			WmsInveAchievementReportVO wmsInveAchievementReportVO, UserBean user) throws ParseException;


	
	/**
	 * doAchievementStaticticsExportPoi
	 * 进行报表导出
	 * @param request
	 * @param response
	 * @param wmsInveAchievementReportVO 
	 * void
	 * @param user 
	 * @return 
	 * @throws ParseException 
	 * @exception 
	 * @since  1.0.0
	 */
	Map<String, Object> doAchievementStaticticsExportPoi(HttpServletRequest request,
			HttpServletResponse response,
			WmsInveAchievementReportVO wmsInveAchievementReportVO, UserBean user) throws ParseException;


	/**
	 * @Title: doachievementDayStaStaticticsExportPoi
	 * @Description: 日报表导出
	 * @param request
	 * @param response
	 * @param wmsInveAchievementReportVO
	 * @return
	 * @throws ParseException 
	 * @author: WangShuai
	 * @param user 
	 * @time:2016年11月23日 下午2:55:35
	 * history:
	 * 1、2016年11月23日 WangShuai 创建方法
	*/
	Map<String, Object> doachievementDayStaStaticticsExportPoi(HttpServletRequest request,
			HttpServletResponse response,
			WmsInveAchievementReportVO wmsInveAchievementReportVO, UserBean user) throws ParseException;


	/**
	 * @Title: getCheckDateToStatic
	 * @Description: 判断查询时间是否进行日报表统计
	 * @param wmsInveAchievementReportVO
	 * @param user
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2016年12月15日 下午5:22:16
	 * history:
	 * 1、2016年12月15日 WangShuai 创建方法
	*/
	Map<String, Object> getCheckDateToStatic(
			WmsInveAchievementReportVO wmsInveAchievementReportVO, UserBean user) throws ParseException;
	
}
