package com.zx.emanage.reportmanage.persist;

import java.util.List;
import java.util.Map;









import com.zx.emanage.reportmanage.vo.WmsInveCustomerMonthlyIncomeBeanVo;
import com.zx.emanage.util.gen.entity.WmsInveCustomerMonthlyIncome;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;
/**
 * 版权所有：版权所有(C) 2015，卓信金控
 * 文件名称: WmsInveCustomerMonthlyIncomeDao.java
 * 系统名称：WMS
 * 模块名称：理财客户每月收益报表
 * 完成日期：
 * 作    者：
 * 内容摘要：
 * 
 * 文件调用：
 * 修改记录：
 * 修改时间：
 * 修 改 人: 
 * 修改内容：
 * 关联BUG：
 * 修改方法：
 */
@MyBatisRepository
public interface WmsInveCustomerMonthlyIncomeDao extends BaseDao<WmsInveCustomerMonthlyIncome>{
	/**
	 * 获取报表需要的数据 每月收益--报表
	 * @return List
	 * @author hancd
	 */
    List<Map<String ,Object>> getMapInfo(Map<String, Object> paMap);//获取报表需要的数据 每月收益
    /**
	 * 获取报表需要的数据 每月收益--报表数
	 * @return List
	 * @author hancd
	 */
	int getMapInfoCount(Map<String, Object> paramMap);//获取报表需要的数据 每月收益 数
	/**
	 * 获取报表需要的数据 每月收益--明细
	 * @return List
	 * @author hancd
	 */
	List<Map<String, Object>> getInveCustomerMonthlyIncomeListDetailInfo(
			Map<String, Object> paramMap);
	/**
	 * 获取报表需要的数据 每月收益--明细数
	 * @return List
	 * @author hancd
	 */
	int getInveCustomerMonthlyIncomeListDetailInfoCount(
			Map<String, Object> paramMap);
	/**
	 * 统计这个月要发放的收益总额 和奖励总额 数据
	 * @param paramMap
	 * @return
	 */
	Map<String,Object> getMapInfoo(Map<String, Object> paramMap);
}
