package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.PmPersonnelOtherinfo;
import com.zx.emanage.util.gen.entity.WmsInveCommissionPerformanceHis;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveCommissionPerformanceHisDao extends BaseDao<WmsInveCommissionPerformanceHis> {
	
	List<Map<String, Object>> searchTeam(Map<String, Object> parameters);
	
	int findCountTeam(Map<String, Object> paramMaps);
	
	/**
	 * Description :检查是否存在  存在说明已验证
	 * @param bean
	 * @return "exist"
	 * @author zhangyunfei
	 */	
    List<PmPersonnelOtherinfo> findPmpersonnelOtherCountByPid(PmPersonnelOtherinfo bean);
    
    /**
   	 * Description :认证情况列表
   	 * @param bean
   	 * @return "Map"
   	 * @author zhangyunfei
   	 */	
	List<Map<String, Object>> getWmsInveTransaAuthListByPid(
			Map<String, Object> paramMap);

	/**
	 * @Title searchOldStock
	 * @Description 内容摘要:老产品个人佣金信息
	 * @param paramMap
	 * @return
	 * @author DongHao
	 * @Time 2016年11月8日13:28:29
	 * @history 1. 2016年11月8日13:28:44 DongHao 创建方法
	 */
	List<Map<String, Object>> searchOldStock(Map<String, Object> paramMap);

	/**
	 * @Title findCountOldStock
	 * @Description 内容摘要:老产品个人佣金信息统计
	 * @param paramMap
	 * @return
	 * @author DongHao
	 * @Time 2016年11月8日13:29:37
	 * @history 1. 2016年11月8日13:29:44 DongHao 创建方法
	 */
	int findCountOldStock(Map<String, Object> paramMap);

	List<Map<String, Object>> searchPerAdd(Map<String, Object> paramMap);

	int findCountPerAdd(Map<String, Object> paramMap);

}