package com.zx.emanage.remind.service;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.emanage.loanappro.vo.WmsCreApproSearchBeanVO;
import com.zx.emanage.remind.vo.CombinedLoanBeanVO;
import com.zx.emanage.remind.vo.CreditMessageToRepayWarnBeanVO;
import com.zx.emanage.remind.vo.WmsCreCreditNotaryWarnSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditNotaryWarn;
import com.zx.sframe.util.vo.UserBean;

/*
 * WmsCreCreditNotaryWarnService
 */
public interface IWmsCreCreditNotaryWarnService {

    /**
     * Description : 查询列表(不带分页)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreCreditNotaryWarnSearchBeanVO queryInfo, UserBean user);

    /**
     * Description : 查询列表(带分页)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    public Map<String, Object> getListWithPaging(WmsCreCreditNotaryWarnSearchBeanVO queryInfo, UserBean user);

    /**
     * Description : 根据主键查询
     * 
     * @param primary key
     * @return WmsCreCreditNotaryWarn
     * @author administrator
     */
    public WmsCreCreditNotaryWarn getInfoByPK(Integer wms_cre_credit_notary_warn_id, UserBean user);

    /**
     * Description : 新增
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author administrator
     */
    public String save(WmsCreCreditNotaryWarn bean, UserBean user);

    /**
     * Description : 修改
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author administrator
     */
    public String update(WmsCreCreditNotaryWarn bean, UserBean user);
    
    /**
     * Description : 查询公证到期提醒列表(使用视图)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    public Map<String, Object> searchVNotaryWarnList(WmsCreCreditNotaryWarnSearchBeanVO queryInfo, UserBean user);
    
    /**
     * Description : 查询公证到期查询列表(使用视图)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    public Map<String, Object> searchVNotarySearchList(WmsCreCreditNotaryWarnSearchBeanVO queryInfo, UserBean user);
    
    /**
     * Description : 批量导入数据
     * 
     * @param queryInfo
     * @return String
     * @author administrator
     */
    public WmsCreCreditNotaryWarnSearchBeanVO importWmsCreCreditNotaryWarnData(HttpServletRequest request, HttpServletResponse response, 
    		WmsCreCreditNotaryWarnSearchBeanVO queryInfo, UserBean user);
	
    /**
     * 公证到期提醒删除数据（逻辑删） 
     * @param wms_cre_credit_notary_warn_id
     * @return
     */
	public String deleteInfo(Integer wms_cre_credit_notary_warn_id);
	
	    /**
     * Description : 查询还款提醒列表(使用视图)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    public Map<String, Object> searchRepaymentReminderList(WmsCreCreditNotaryWarnSearchBeanVO queryInfo, UserBean user);
    
    /**
     * Description : 查询还款查询列表(使用视图)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    public Map<String, Object> searchRepaymentSearchList(WmsCreCreditNotaryWarnSearchBeanVO queryInfo, UserBean user);
    
    
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
	public Map<String, Object> searchBirthdayReminderList(
			WmsCreCreditNotaryWarnSearchBeanVO queryInfo, UserBean user);
	
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
	public String getCreditMessageToRepayWarn(
			CreditMessageToRepayWarnBeanVO bean,
			UserBean user);

    /**
     * Description : 查询还款确认列表(使用视图)
     * @param queryInfo
     * @param user
     * @return Map<String, Object
     */
	public Map<String, Object> searchRepaymentConfirmationList(
			WmsCreCreditNotaryWarnSearchBeanVO queryInfo, UserBean user);

    /**
     * @Title: searchloansSearchList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param queryInfo
     * @param user
     * @return 
     * @author: jiaodelong
     * @time:2016年11月23日 上午9:44:47
     * history:
     * 1、2016年11月23日 jiaodelong 创建方法
    */
    public Map<String, Object> searchloansSearchList(WmsCreCreditNotaryWarnSearchBeanVO queryInfo, UserBean user);

    /**
     * @Title: getTeamManagerName
     * @Description: TODO(获取当前公正提醒表内所有的团队经理姓名)
     * @param request
     * @param queryInfo
     * @return 
     * @author: baisong
     * @time:2016年12月7日 下午5:23:22
     * history:
     * 1、2016年12月7日 baisong 创建方法
     */
    public Map<String, Object> getTeamManagerName(WmsCreCreditNotaryWarnSearchBeanVO bean, UserBean user);

    /**
    * 
    * @Title: searchloansSearchList
    * @Description: TODO(组合贷)
    * @param request
    * @param queryInfo
    * @return Map<String, Object>
    * @author: baisong
    * @time:2016年11月23日 上午9:48:04
    * history:
    * 1、2016年11月23日 baisong 创建方法
    */
    public Map<String, Object> combinedLoan(CombinedLoanBeanVO queryInfo, UserBean user);

    /**
     * @Title: getAgreeInfoListForZQ
     * @Description: TODO(展期状态查询列表)
     * @param queryInfo
     * @param user
     * @return 
     * @author: jiaodelong
     * @time:2017年2月20日 上午9:20:40
     * history:
     * 1、2017年2月20日 jiaodelong 创建方法
    */
    public Map<String, Object> getAgreeInfoListForZQ(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * @Title: getHouseCreditMessageToRepayWarn
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param bean
     * @param user
     * @return 
     * @author: admin
     * @time:2017年2月27日 下午2:13:04
     * history:
     * 1、2017年2月27日 admin 创建方法
    */
    String getHouseCreditMessageToRepayWarn(CreditMessageToRepayWarnBeanVO bean, UserBean user);

}
