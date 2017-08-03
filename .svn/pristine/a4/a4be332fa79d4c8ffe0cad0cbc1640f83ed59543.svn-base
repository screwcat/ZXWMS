package com.zx.emanage.loanfina.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepaymentHistory;
import com.zx.emanage.util.gen.entity.WmsPostRemindHistory;

@MyBatisRepository
public interface WmsFinaCreRepaymentHistoryDao extends BaseDao<WmsFinaCreRepaymentHistory> {
	/***
	 * 根据wms_cre_credit_head_id查询多条还款历史
	 * @param wms_cre_credit_head_id
	 * @return list
	 * baisong
	 */
	public List<WmsPostRemindHistory> getlist(Integer wms_cre_credit_head_id);
	/***
	 * 根据wms_cre_credit_head_id查询多条逾期历史
	 * @param wms_cre_credit_head_id
	 * @return list
	 * baisong
	 */
	public List<Map<String, Object>> searchHistory(Integer wms_cre_credit_head_id);
	/**
	 * 根据贷款主表ID和期数 查询对应期数的还款历史
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> searchByRepaymentforIdorNO(
			Map<String, Object> paramMap);
}