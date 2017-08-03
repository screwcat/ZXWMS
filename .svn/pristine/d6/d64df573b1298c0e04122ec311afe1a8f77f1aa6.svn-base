package com.zx.emanage.telUserLoanInfo.persist;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsCreCreditNotificationMessage;

@MyBatisRepository
public interface WmsCreCreditNotificationMessageDao extends BaseDao <WmsCreCreditNotificationMessage> {

	void updateMessageFlag(@Param("messageid")Integer wms_cre_credit_notification_message_id);

	void updateMessageStatus(Map<String,Object> map);

    /**
     * @Title: updateMessageStatusForFour
     * @Description: TODO(修改通知中心状态)
     * @param user_id 
     * @author: jiaodelong
     * @time:2017年3月17日 上午10:35:06
     * history:
     * 1、2017年3月17日 jiaodelong 创建方法
    */
	void updateMessageStatusForFour(Map<String, Object> map);

    /**
     * @Title: getBizDeleteMessageUp
     * @Description: TODO(3.2.4     删除通知中心信息)
     * @param wms_cre_credit_notification_message_id 
     * @author: jiaodelong
     * @time:2017年3月17日 上午10:41:59
     * history:
     * 1、2017年3月17日 jiaodelong 创建方法
    */
    void getBizDeleteMessageUp(int wms_cre_credit_notification_message_id);

}