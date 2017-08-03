package com.zx.emanage.reportmanage.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.zx.emanage.reportmanage.vo.WmsProviderForAppVO;

/**
 * @ClassName: WmsProviderForAppService
 * @Description: wms为客户端提供接口类
 * @author WangShuai
 * @date 2017年1月3日
 * @version V1.0
 * history:
 * 1、2017年1月3日 WangShuai 创建文件
 */
public interface WmsProviderForAppService {

	/**
	 * @Title: getPersonnelcomm_dept_sta_v197
	 * @Description: 接口18，根据客户经理汇总
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年1月3日 下午4:00:44
	 * history:
	 * 1、2017年1月3日 WangShuai 创建方法
	*/
	Map<String,Object> getPersonnelcomm_dept_sta_v197(
			WmsProviderForAppVO wmsProviderForAppVO);

	/**
	 * @Title: getPersonnelcomm_comm_item_sta_v197
	 * @Description: 接口19，自己的个人佣金或某一客户经理的个人佣金
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年1月4日 上午10:39:45
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
	Map<String, Object> getPersonnelcomm_comm_item_sta_v197(
			WmsProviderForAppVO wmsProviderForAppVO);

	/**
	 * @Title: getPersonnel_comm_inve_sta_v197
	 * @Description: 接口20，用户查看每一单带来的佣金情况；
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年1月4日 上午11:11:27
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
	Map<String, Object> getPersonnel_comm_inve_sta_v197(
			WmsProviderForAppVO wmsProviderForAppVO);

	/**
	 * @Title: getAch_center_sta_v197
	 * @Description: 接口21，用户查看各个中心的佣金情况
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2017年1月4日 下午2:42:47
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
	Map<String, Object> getAch_center_sta_v197(
			WmsProviderForAppVO wmsProviderForAppVO) throws ParseException;

	/**
	 * @Title: getAch_dept_sta_v197
	 * @Description: 接口22，按部门汇总业绩
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2017年1月4日 下午4:12:23
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
	Map<String, Object> getAch_dept_sta_v197(
			WmsProviderForAppVO wmsProviderForAppVO) throws ParseException;

	/**
	 * @Title: getAch_team_sta_v197
	 * @Description: 接口23，按团队汇总业绩
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2017年1月4日 下午4:33:21
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
	Map<String, Object> getAch_team_sta_v197(
			WmsProviderForAppVO wmsProviderForAppVO) throws ParseException;

	/**
	 * @Title: getAch_personnel_sta_v197
	 * @Description: 接口24，按个人汇总业绩
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2017年1月4日 下午5:27:27
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
	Map<String, Object> getAch_personnel_sta_v197(
			WmsProviderForAppVO wmsProviderForAppVO) throws ParseException;

	/**
	 * @Title: getAch_inve_sta_v197
	 * @Description: 接口汇总，按单据展现信息
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2017年1月5日 上午10:42:13
	 * history:
	 * 1、2017年1月5日 WangShuai 创建方法
	*/
	Map<String, Object> getAch_inve_sta_v197(
			WmsProviderForAppVO wmsProviderForAppVO) throws ParseException;

	/**
	 * @Title: getAch_vice_sta_v197
	 * @Description: 副总统计
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2017年1月5日 下午2:30:55
	 * history:
	 * 1、2017年1月5日 WangShuai 创建方法
	*/
	Map<String, Object> getAch_vice_sta_v197(
			WmsProviderForAppVO wmsProviderForAppVO) throws ParseException;

}
