package com.zx.emanage.loanfina.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsFinaCreRealrepayInfo;

@MyBatisRepository
public interface WmsFinaCreRealrepayInfoDao extends BaseDao<WmsFinaCreRealrepayInfo> {
	/***
	 * 根据id获取明细表数据
	 * @param wms_cre_credit_head_id
	 * @return
	 * baisong
	 */
	public List<Map<String,Object>> getsum (Integer wms_cre_credit_head_id);
	/**
	 * @Title: getInitRealrepayInfoList 
	 * @Description: (从台帐和还款表查询应还款信息表的初始化数据 ) 
	 * @param wms_cre_credit_head_id
	 * @return    
	 * @return List<WmsFinaCreRealrepayInfo>    返回类型
	 * @throws
	 * @author lvtu
	 */
	public List<WmsFinaCreRealrepayInfo> getInitRealrepayInfoList(int wms_cre_credit_head_id);
	/**
	 * @Title: addBatchRealrepayInfo 
	 * @Description: (批量插入应还款信息表) 
	 * @param wmsFinaCreRealrepayInfoList
	 * @return    
	 * @return int    返回类型
	 * @throws
	 * @author lvtu
	 */
	public int addBatchRealrepayInfo(List<WmsFinaCreRealrepayInfo> wmsFinaCreRealrepayInfoList);

	 /***
	 * 根据id获取明细表数据并求和
	 * @param wms_cre_credit_head_id
	 * @return
	 * baisong
	 */
	public Map<String,Object> getSumval(Integer wms_cre_credit_head_id);
	/**
	 * 根据提供数据查询应还款利息信息
	 * @param parameters
	 * @return
	 */
	public List<Map<String, Object>> searchByInfo(Map<String, Object> parameters);
	/**
	 * 根据提供的应还款相关信息更新数据
	 * @param t
	 */
	public void updateRecord(WmsFinaCreRealrepayInfo t);
	/**
	 * 根据提供的贷款ID和已还期数
	 * @param wCreRealrepayInfo
	 */
	public void updateForYHCS(WmsFinaCreRealrepayInfo wCreRealrepayInfo);
}