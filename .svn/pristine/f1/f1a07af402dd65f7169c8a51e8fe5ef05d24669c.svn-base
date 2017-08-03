package com.zx.emanage.cremanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsCreHousingAgainAppl;

@MyBatisRepository
public interface WmsCreHousingAgainApplDao extends BaseDao <WmsCreHousingAgainAppl> {

	/**
	 * 复议申请详细页面初始化
	 */
	Map<String, Object> reviewRevisionOfHousingDisp(Map<String, Object> paramMap);
	
	/**
	 * 复议申请详细页面保存同步数据
	 */
	void reviewRevisionOfHousingDataSynchronization(Map<String, Object> paramMap);
	
	/**
	 * 复议申请信息查询
	 */
	List<Map<String, Object>> getAgainDictDataByCode(Map<String, Object> paramMap);
	
}