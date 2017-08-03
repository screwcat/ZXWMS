package com.zx.emanage.remind.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreCreditNotaryWarn;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreCreditNotaryWarnDao
 * 模块名称：
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月7日
 * @version V1.0
 * history:
 * 1、2016年12月7日 baisong 创建文件
 */
@MyBatisRepository
public interface WmsCreCreditNotaryWarnDao extends BaseDao<WmsCreCreditNotaryWarn> {
	
	                                /**
    * Description : 查询公证到期提醒列表(使用视图)
    * 
    * @param queryInfo
    * @return record list
    * @author administrator
    */
	List<Map<String, Object>> searchVNotaryWarnList(Map<String, Object> paramMap);
	
	                                /**
    * Description : 查询公证到期提醒列表(使用视图)
    * 
    * @param queryInfo
    * @return record list
    * @author administrator
    */
	int searchVNotaryWarnCount(Map<String, Object> paramMap);
	
	                                /**
    * Description : 获取单据编号(根据申请日期生成)
    * 
    * @param queryInfo
    * @return record String
    * @author administrator
    */
	String getBillCodeForNotaryWarn(Map<String, Object> paramMap);
	
	                                /**
    * 公证到期提醒数据删除
    * @param wms_cre_credit_notary_warn_id
    * @return
    */
	Integer deleteInfo(Integer wms_cre_credit_notary_warn_id);
	
	                                /**
    * Description : 查询还款列表(使用视图)
    * 
    * @param queryInfo
    * @return record list
    * @author administrator
    */
	List<Map<String, Object>> searchRepaymentList(Map<String, Object> paramMap);
	
	                                /**
    * Description : 查询还款列表数量(使用视图)
    * 
    * @param queryInfo
    * @return record list
    * @author administrator
    */
	int searchRepaymentCount(Map<String, Object> paramMap);
	
    /**
     * Description : 查询主表数据(使用视图)
     * @param wms_cre_credit_notary_warn_id
     * @return WmsCreCreditNotaryWarn
     */

	WmsCreCreditNotaryWarn getWmsCreCreditCotaryWarnInfo(
			Integer wms_cre_credit_notary_warn_id);
	
	
	                                /**
    * 
    * @Title: searchBirthdayReminderList
    * @Description: (查询生日提醒列表 视图)
    * @param request
    * @param queryInfo
    * @return
    * @author: baisong
    * @time:2016年11月15日 上午10:59:39 history: 1、2016年11月15日 baisong 创建方法
    */
	List<Map<String, Object>> searchBirthdayVNotaryWarnList(
			Map<String, Object> paramMap);

	                                /**
    * 
    * @Title: searchBirthdayReminderList
    * @Description: (查询生日提醒列表 视图)
    * @param request
    * @param queryInfo
    * @return
    * @author: baisong
    * @time:2016年11月15日 上午10:59:39 history: 1、2016年11月15日 baisong 创建方法
    */
	int searchBirthdayVNotaryWarnCount(Map<String, Object> paramMap);
	
	                                /**
    * 
    * @Title: sendMessageBirthday
    * @Description: (获取信息放款数据同步到还款提醒模块)
    * @param request
    * @param bean
    * @return String
    * @author: baisong
    * @time:2016年11月15日 下午1:30:40 history: 1、2016年11月15日 baisong 创建方法
    */
	List<WmsCreCreditNotaryWarn> getCreditMessageToRepayWarn(Map<String, Object> paramMap);
	
	                                /**
    * 
    * @Title: saveBatch
    * @Description: (批量保存)
    * @param mapList
    * @return int
    * @author: baisong
    * @time:2016年11月15日 下午1:30:40 
    * history: 1、2016年11月15日 baisong 创建方法
    */
	int saveBatch(List<WmsCreCreditNotaryWarn> mapList);

    /**
     * @Title: searchloansSearchList
     * @Description: TODO(贷款数据查询列表数据)
     * @param paramMap
     * @return List<Map<String, Object>>
     * @author: jiaodelong
     * @time:2016年11月23日 上午9:52:30
     * history:
     * 1、2016年11月23日 jiaodelong 创建方法
    */
    List<Map<String, Object>> searchloansSearchList(Map<String, Object> paramMap);

    /**
     * @Title: searchloansSearchCount
     * @Description: TODO(贷款数据查询条数)
     * @param paramMap
     * @return int
     * @author: jiaodelong
     * @time:2016年11月23日 上午9:57:12
     * history:
     * 1、2016年11月23日 jiaodelong 创建方法
    */
    int searchloansSearchCount(Map<String, Object> paramMap);

    /**
     * @Title: searchRepaymentConfirmationList
     * @Description: TODO(还款确认列表查询)
     * @param paramMap
     * @return 
     * @author: jiaodelong
     * @time:2016年11月29日 下午5:17:43
     * history:
     * 1、2016年11月29日 jiaodelong 创建方法
    */
    List<Map<String, Object>> searchRepaymentConfirmationList(Map<String, Object> paramMap);

    /**
     * @Title: searchRepaymentConfirmationCount
     * @Description: TODO(还款确认条数查询)
     * @param paramMap
     * @return 
     * @author: jiaodelong
     * @time:2016年11月29日 下午5:20:17
     * history:
     * 1、2016年11月29日 jiaodelong 创建方法
    */
    int searchRepaymentConfirmationCount(Map<String, Object> paramMap);

    /**
     * @Title: getTeamManagerName
     * @Description: TODO(获取当前公正提醒表内所有的团队经理姓名) 
     * @author: baisong
     * @time:2016年12月7日 下午5:27:23
     * history:
     * 1、2016年12月7日 baisong 创建方法
    */
    List<Map<String, Object>> getTeamManagerName(Map<String, Object> map);

    /**
     * 
     * @Title: getList
     * @Description: TODO(根据主键查询多条)
     * @param map
     * @return 
     * @author: baisong
     * @time:2017年1月5日 下午3:46:45
     * history:
     * 1、2017年1月5日 baisong 创建方法
     */
    List<WmsCreCreditNotaryWarn> getList(Map<String, Object> map);

    /**
     * @Title: getGroupALlList
     * @Description: TODO(根据组合贷编号获取当前组合中的所有单据)
     * @param paraMap
     * @return 
     * @author: baisong
     * @time:2017年1月22日 下午1:07:39
     * history:
     * 1、2017年1月22日 baisong 创建方法
    */
    List<Map<String, Object>> getGroupALlList(Map<String, Object> paraMap);

    /**
     * @Title: getAgreeInfoListForZQ
     * @Description: TODO(展期单据列表查询)
     * @param paramMap
     * @return 
     * @author: jiaodelong
     * @time:2017年2月20日 上午9:51:10
     * history:
     * 1、2017年2月20日 jiaodelong 创建方法
    */
    List<Map<String, Object>> getAgreeInfoListForZQ(Map<String, Object> paramMap);

    /**
     * @Title: findCountAgreeInfoListForZQ
     * @Description: TODO(展期单据列表查询条数)
     * @param paramMap
     * @return 
     * @author: jiaodelong
     * @time:2017年2月20日 上午9:51:31
     * history:
     * 1、2017年2月20日 jiaodelong 创建方法
    */
    int findCountAgreeInfoListForZQ(Map<String, Object> paramMap);

    /**
     * @Title: getAll
     * @Description: TODO(根据ID查询全部)
     * @param wms_cre_credit_notary_warn_id
     * @return 
     * @author: jiaodelong
     * @time:2017年2月20日 下午3:09:12
     * history:
     * 1、2017年2月20日 jiaodelong 创建方法
    */
    Map<String, Object> getAll(Integer wms_cre_credit_notary_warn_id);

    /**
     * @Title: updateRepaystatus
     * @Description: TODO(根据headid修改还款状态)
     * @param warn
     * @return 
     * @author: jiaodelong
     * @time:2017年2月27日 下午1:35:56
     * history:
     * 1、2017年2月27日 jiaodelong 创建方法
    */
    int updateRepaystatus(WmsCreCreditNotaryWarn warn);

    /**
    * 
    * @Title: sendMessageBirthday
    * @Description: (获取信息放款数据同步到还款提醒模块)
    * @param request
    * @param bean
    * @return String
    * @author: baisong
    * @time:2016年11月15日 下午1:30:40 history: 1、2016年11月15日 baisong 创建方法
    */
    List<WmsCreCreditNotaryWarn> getHosueCreditMessageToRepayWarn(Map<String, Object> paramMap);

    /**
     * @Title: updateInfoForHeadId
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param wmsCreCreditNotaryWarn 
     * @author: jiaodelong
     * @time:2017年3月9日 下午4:28:59
     * history:
     * 1、2017年3月9日 jiaodelong 创建方法
    */
    void updateInfoForHeadId(WmsCreCreditNotaryWarn wmsCreCreditNotaryWarn);
}