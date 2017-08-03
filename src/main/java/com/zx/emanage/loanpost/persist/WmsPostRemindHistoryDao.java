package com.zx.emanage.loanpost.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsPostRemindHistory;

@MyBatisRepository
public interface WmsPostRemindHistoryDao extends BaseDao<WmsPostRemindHistory> {
	/**
	 * @Title: updatePhoneRemindHistory 
	 * @Description: (更新电话提醒历史记录) 
	 * @param paramMap
	 * @return    
	 * @return int    返回类型
	 * @throws
	 * @author lvtu
	 */
	public int updatePhoneRemindHistory(Map<String, Object> paramMap);
	/**
	 * 
	 * @Title: updatePhoneRemindHistoryno3 
	 * @Description: (电话提醒记录更新延期的情况) 
	 * @param paramMap
	 * @return    
	 * @return int    返回类型
	 * @throws
	 * @author lvtu
	 */
	public int updatePhoneRemindHistoryno3(Map<String, Object> paramMap);
	/**
	 * 
	 * @Title: remindHistoryCount 
	 * @Description: (当前还款期提醒记录数量) 
	 * @param paramMap
	 * @return    
	 * @return int    返回类型
	 * @throws
	 * @author lvtu
	 */
	public Map<String, Object> getRemindHistoryCountAndRes(Map<String, Object> paramMap);
	/**
	 * 
	 * @Title: remindHistoryTheSecondID 
	 * @Description: (当前还款期提醒记录最大ID) 
	 * @param paramMap
	 * @return    
	 * @return int    返回类型
	 * @throws
	 * @author lvtu
	 */
	public long getRemindHistoryTheSecondID(Map<String, Object> paramMap);
	/**
	 * 
	 * @Title: getInfoListWithPaging 
	 * @Description: (电话提醒查看详细) 
	 * @param Id
	 * @return    
	 * @return Map<String,Object>    返回类型
	 * @throws
	 * @author lvtu
	 */
	public List<Map<String, Object>> getInfoListWithPaging(long Id);
	/***
	 * 根据wms_cre_credit_head_id查询多条还款历史
	 * @param wms_cre_credit_head_id
	 * @return list
	 * baisong
	 */
	public List<WmsPostRemindHistory> getlist(Integer wms_cre_credit_head_id);
	/**
	 * 
	 * @Title: getInfoListCount 
	 * @Description: (查询历史提醒记录条数) 
	 * @param Id
	 * @return    
	 * @return int    返回类型
	 * @throws
	 * @author lvtu
	 */
	public int getInfoListCount(long Id);

}