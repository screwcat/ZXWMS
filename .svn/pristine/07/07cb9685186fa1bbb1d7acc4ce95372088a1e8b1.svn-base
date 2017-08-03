package com.zx.emanage.remind.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreCreditSendMessageLog;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreCreditSendMessageLogDao extends BaseDao <WmsCreCreditSendMessageLog> {
	/**
	 * 
	 * @Title: sendMessageBirthdayCount
	 * @Description: (生日发送祝福短信息数量)
	 * @param request
	 * @param bean
	 * @return
	 * @author: baisong
	 * @time:2016年11月15日 下午1:30:40 history: 1、2016年11月15日 baisong 创建方法
	 */
	public List<Map<String, Object>> sendMessageBirthdayCount(
			Map<String, Object> map);

	/**
	 * 
	 * @Title: sendMessageBirthday
	 * @Description: (生日发送祝福短信息)
	 * @param request
	 * @param bean
	 * @return
	 * @author: baisong
	 * @time:2016年11月15日 下午1:30:40 history: 1、2016年11月15日 baisong 创建方法
	 */
	public String sendMessageBirthday(Map<String, Object> map);

	// 批量保存 2016-11-15 baisong
	int saveBatch(List<WmsCreCreditSendMessageLog> logList);
}