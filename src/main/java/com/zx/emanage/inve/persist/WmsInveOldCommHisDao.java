package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveOldCommHis;

@MyBatisRepository
public interface WmsInveOldCommHisDao extends BaseDao<WmsInveOldCommHis> {

	/**
	 * @Title: getWmsInvePerOldCommByCondition
	 * @Description: 根据月份查询个人老佣金 导出报表
	 * @param paramMap
	 * @return 
	 * @author: zhangyunfei
	 * @time:2016年11月9日 下午2:36:11
	*/
	List<Map<String, Object>> getWmsInvePerOldCommByCondition(
			Map<String, Object> paramMap);

	/**
	 * @Title: getWmsInvePerOldCommByCondition
	 * @Description: 根据月份查询团队老佣金 导出报表
	 * @param paramMap
	 * @return 
	 * @author: zhangyunfei
	 * @time:2016年11月9日 下午2:36:11
	*/
	List<Map<String, Object>> getWmsInvetemOldCommByCondition(
			Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: getWmsInveCommissionShareHolderByCondition
	 * @Description: 根据查询条件获取股东佣金信息
	 * @param paramMap 查询条件参数
	 * @return 返回数据集合
	 * @author: DongHao
	 * @time:2017年6月1日 上午12:52:25
	 * history:
	 * 1、2017年6月1日 DongHao 创建方法
	 */
    List<Map<String, Object>> getWmsInveCommissionShareHolderByCondition(Map<String, Object> paramMap);

    /**
     * 
     * @Title: getWmsInveCommissionShareHolderPersonnelInfos
     * @Description: 获取股东单据数据中的人员id
     * @param paramMap 查询条件参数
     * @return 返回人员id数据集合
     * @author: DongHao
     * @time:2017年6月1日 上午1:25:44
     * history:
     * 1、2017年6月1日 DongHao 创建方法
     */
    List<Map<String, Object>> getWmsInveCommissionShareHolderPersonnelInfos(Map<String, Object> paramMap);
	
}