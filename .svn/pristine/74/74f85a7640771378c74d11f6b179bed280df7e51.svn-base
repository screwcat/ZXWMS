package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveDebtOlnclaims;

@MyBatisRepository
public interface WmsInveDebtOlnclaimsDao extends BaseDao<WmsInveDebtOlnclaims> {

	int savebymap(WmsInveDebtOlnclaims wmsInveDebtOlnclaims);
    
	List<Map<String, Object>> getListByMap(WmsInveDebtOlnclaims debtOlnclaims);
	
	/**
	 * 根据债权调整主表id查询债权调整表信息
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> selectOlnclaimsByWmsInveDebtHeadId(Map<String, Object> paramMap);
	
	
	/**
	 * 更新债权调整表状态
	 * @param wms_inve_debt_head_id
	 */
	void deleteOlnclaimsRecordsByWmsInveDebtHeadId(Integer wms_inve_debt_head_id);
	
	/**
	 * 债权调整暂存后更新还款信息表状态
	 */
	void updateWmsFinaCreRepayStatus(Map<String, Object> paramMap);
	
}