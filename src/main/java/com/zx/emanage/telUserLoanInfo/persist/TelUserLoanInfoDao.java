package com.zx.emanage.telUserLoanInfo.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsFinaCreRepay;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;




@MyBatisRepository
public interface TelUserLoanInfoDao extends BaseDao<WmsFinaCreRepay> {

	/***
	 * 根据客户名称 身份证 联系电话 查询借贷情况--单据具体情况
	 * @param map
	 * @return Map
	 * baisong
	 */
	public List<Map<String,Object>> searchinfoloan(Map map);
	/***
	 * 根据客户名称 身份证 联系电话 查询借贷情况--借贷金额
	 * @param map
	 * @return Map
	 * baisong
	 */
	public Map<String,Object> searchisum(Map map);
	/***
	 * 根据客户名称 身份证 联系电话 查询借贷情况--还款中的数据
	 * @param map
	 * @return int
	 * baisong
	 */
	public int searchnum(Map map);
	/***
	 * 根据客户名称 身份证 联系电话 查询借贷情况--获取期还款台账中的数据
	 * @param map
	 * @return Map
	 * baisong
	 */
	public List<Map<String,Object>> searchPeriodRepay(Map map);
}