package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * @ClassName WmsInveExpiredCustomerDao
 * @Description 内容摘要: 到期客户查询数据的接口类
 * @author DongHao
 * @date 2016年11月24日
 * @version V1.0
 * @history 1. 2016年11月24日 DongHao 创建文件
 */
@SuppressWarnings("rawtypes")
@MyBatisRepository
public interface WmsInveExpiredCustomerDao extends BaseDao{
	
	/**
	 * @Title findExpiredCustomer
	 * @Description 内容摘要: 根据条件查询到期客户信息
	 * @param paramMap
	 *            查询条件参数
	 * @return 返回list列表
	 * @date 2016年11月24日
	 * @version V1.0
	 * @history 1. 2016年11月24日 DongHao 创建文件
	 */
	public List<Map<String, Object>> findExpiredCustomer(Map<String, Object> paramMap);

	/**
	 * @Title findExpiredCustomerCount
	 * @Description 内容摘要: 根据条件查询到期客户信息数量
	 * @param paramMap
	 *            查询条件参数
	 * @return 返回int类型整型变量值
	 * @date 2016年11月24日
	 * @version V1.0
	 * @history 1. 2016年11月24日 DongHao 创建文件
	 */
	public int findExpiredCustomerCount(Map<String, Object> paramMap);

}
