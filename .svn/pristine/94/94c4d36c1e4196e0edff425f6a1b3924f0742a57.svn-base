package com.zx.emanage.remind.persist;

import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsCreCreditRepayHistory;

@MyBatisRepository
public interface WmsCreCreditRepayHistoryDao extends BaseDao <WmsCreCreditRepayHistory> {
/**
 * 根据id查询还款金额
 * @param integer
 * @return WmsCreCreditRepayHistory
 */
	WmsCreCreditRepayHistory forIsnewGetRepayPrincipal(
			Integer id);

	/**
	 * 还款提醒信息修改初始化
	 */
	Map<String, Object> editRepaymentInfoDisp(Map<String, Object> paramMap);
/**
 * 查询数据库是否包含is_new的数据
 * @param wms_cre_credit_notary_warn_id
 * @return
 */
	int getHistoryCount(Integer wms_cre_credit_notary_warn_id);
/**
 * 修改is_new为1的历史表信息
 * @param history
 * @return
 */
Integer updateInfoForIsNew(WmsCreCreditRepayHistory history);
	
	/**
	 * 还款序号生成
	 */
	String getRepayHistoryCode(Map<String, Object> paramMap);

/**
 * 查询最新历史
 * @param wms_cre_credit_notary_warn_id
 * @return
 */
WmsCreCreditRepayHistory getRepayHistory(Integer wms_cre_credit_notary_warn_id);

/**
 * @Title: searchPrincipal
 * @Description: TODO(查询还款金额)
 * @param wms_cre_credit_notary_warn_id
 * @return 
 * @author: jiaodelong
 * @time:2017年1月11日 下午4:02:56
 * history:
 * 1、2017年1月11日 jiaodelong 创建方法
*/
String searchPrincipal(Integer wms_cre_credit_notary_warn_id);
		
}