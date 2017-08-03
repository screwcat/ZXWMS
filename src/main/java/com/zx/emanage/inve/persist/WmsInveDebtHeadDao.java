package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveDebtHead;

@MyBatisRepository
public interface WmsInveDebtHeadDao extends BaseDao<WmsInveDebtHead> {

	void updateRecord(Map<String, Object> paMap);
	/**
	 * 获取客户原债权
	 * @param map
	 * @return List<Map<String,Object>>
	 * @author yangqiyu
	 */
	List<Map<String,Object>> getCusOriginalClaims(Map<String,Object> map);
	/**
	 * 获取信息存入Excel中
	 * @param map
	 * @return List<Map<String,Object>>
	 * @author yangqiyu
	 */
	List<Map<String,Object>> giveInfoToExcel(Map<String,Object> map);
	/**
	 * 根据上单产品id获取债权匹配信息 
	 * @param Integer
	 * @return List<Map<String,Object>>
	 * @author yangqiyu
	 */
	List<Map<String,Object>> getMatchInfoByProd(Map<String,Object> map);
	/**
	 * 通过传递参数查询债权变更审核页面数据
	 * @param Map<String,Object>
	 * @return List<Map<String,Object>>
	 * @author yangqiyu
	 */
	List<Map<String,Object>> getAllFromWmsInveDebtHead(Map<String,Object> paramMap);
	/**
	 * 通过参数查询待修订的数据
	 * @param Map<String,Object>
	 * @return List<Map<String,Object>>
	 * @author yangqiyu
	 */
	List<Map<String,Object>> getUpdateWmsInveDebtHead(Map<String,Object> paramMap);
	/**
	 * 通过传递参数查询债权变更审核页面数据数
	 * @param paramMap
	 * @return int
	 * @author yangqiyu
	 */
	int findCountAllFromWmsInveDebtHead(Map<String, Object> paramMap);
	/**
	 * 获取债权变更原因 
	 * @param int
	 * @return Map
	 * @author yangqiyu
	 */
	String getDebtReason(Integer wms_inve_debt_head_id);
	/**
	 * @Title: searchList 
	 * @Description: 查询债权变更列表信息
	 * @param paramMap
	 * @return List<Map<String,Object>> 
	 * @throws
	 * @author lvtu 
	 * @date 2015年10月19日 下午1:18:22
	 */
	public List<Map<String, Object>> searchList(Map<String, Object> paramMap);
	
	/**
	 * @Title: searchExcelList 
	 * @Description: 债权变动确认和协议打印列表
	 * @param paramMap
	 * @return List<Map<String,Object>> 
	 * @throws
	 * @author lvtu 
	 * @date 2015年11月5日 下午3:46:33
	 */
	public List<Map<String, Object>> searchExcelList(Map<String, Object> paramMap);

	/**
	 * @Title: findListCount 
	 * @Description: 债权变动申请数量
	 * @param paramMap
	 * @return int 
	 * @throws
	 * @author lvtu 
	 * @date 2015年10月19日 下午1:37:10
	 */
	public int findListCount(Map<String, Object> paramMap);
	/**
	 * @Title :searchOneByObj
	 * @Description: 根据不完整信息查询完整对象信息
	 * @param wmsInveDebtHead
	 * @return wmsInveDebtHead 
	 * @author yangqiyu
	 */
	public WmsInveDebtHead searchOneByObj(WmsInveDebtHead wmsInveDebtHead);
	/**
	 * 获取债权调整申请列表显示的数据数
	 * @param paramMap
	 * @return
	 */
	int findCountCusOriginalClaims(Map<String, Object> paramMap);
	
	/**
	 * 根据债权调整申请主表ID查询全部
	 * @param Integer
	 * @return wmsInveDebtHead 
	 * @author jiaodelong
	 */
	public WmsInveDebtHead findHeadAllByID(Integer wms_inve_debt_head_id);
	
}