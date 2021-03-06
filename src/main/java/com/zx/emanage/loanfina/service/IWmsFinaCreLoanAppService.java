package com.zx.emanage.loanfina.service;

import java.util.Map;

import com.zx.emanage.cremanage.vo.WmsCreCreditHeadHouseSearchBeanVO;
import com.zx.emanage.loanfina.vo.WmsFinaCreCancelBeanVo;
import com.zx.emanage.loanfina.vo.WmsFinaCreLoanAppSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsFinaCreLoanApp;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * @ClassName: IWmsFinaCreLoanAppService
 * @Description: 内容摘要：放款申请相关Service接口
 * @author wangyihan
 * @date 2016年12月23日
 * @version V1.0
 * history:
 * 1、2016年12月23日 wangyihan 创建文件
 */
public interface IWmsFinaCreLoanAppService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsFinaCreLoanAppSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsFinaCreLoanAppSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsFinaCreLoanAppVO
     * @author auto_generator
     */
    public WmsFinaCreLoanApp getInfoByPK(Integer wms_fina_cre_loan_app);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String doSaveCancel(WmsFinaCreCancelBeanVo bean,UserBean user, WmsCreCreditHead hbean);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsFinaCreLoanApp bean, UserBean user, int wms_cre_credit_head_id, String taskId, String fileArr);

    /**
     * 房贷申请保存
     * 
     * @param bean
     * @param user
     * @param wms_cre_credit_head_id
     * @param taskId
     * @param fileArr
     * @return
     */
   // public String housingLoanAppSave(WmsFinaCreLoanApp bean, UserBean user, String taskId, String fileArr);
    public String housingLoanAppSave(WmsFinaCreLoanApp bean, UserBean user,WmsCreCreditHeadHouseSearchBeanVO beanvo);
    
    /**
     * 车贷申请保存
     * 
     * @param bean
     * @param user
     * @param wms_cre_credit_head_id
     * @param taskId
     * @param fileArr
     * @return
     */
    public String carLoanAppSave(WmsFinaCreLoanApp bean, UserBean user, String taskId, String fileArr);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsFinaCreLoanApp bean, UserBean user);
    
    /**
     * moa调用wms方法:放款申请结果保存
     * @param WmsFinaCreLoanAppSearchBeanVO bean
     * @return 
     */
    public Map<String, Object> sendLoanApprovalInfoUp(WmsFinaCreLoanAppSearchBeanVO queryInfo) throws Exception;
    
    /**
     * moa调用wms方法:放款申请结果保存(组合贷)
     * @param WmsFinaCreLoanAppSearchBeanVO bean
     * @return 
     */
    public Map<String, Object> sendLoanApprovalInfoUpAgain(WmsFinaCreLoanAppSearchBeanVO queryInfo) throws Exception;
    
/**
 * 
 * 修改审批单据
 * @param bean
 */
	public void updateWmsFinaCreLoanApp(WmsFinaCreLoanApp bean);
    
	/**
	 * 
	 * @Title: initCombineLoanInfo
	 * @Description: TODO(放款申请组合贷初始化信息)
	 * @param queryInfo
	 * @return 
	 * @author: wangyihan
	 * @time:2016年12月23日 下午4:15:16
	 * history:
	 * 1、2016年12月23日 wangyihan 创建方法
	 */
	public WmsFinaCreLoanAppSearchBeanVO initCombineLoanInfo(WmsFinaCreLoanAppSearchBeanVO queryInfo);

    /**
     * @Title: wmsFinaCreLoanAppSaveForPerfact
     * @Description: TODO(提交放款)
     * @param wms_cre_credit_head_id
     * @return 
     * @author: jiaodelong
     * @param bill_type 
     * @time:2017年2月27日 上午9:52:46
     * history:
     * 1、2017年2月27日 jiaodelong 创建方法
    */
    public Map<String, Object> wmsFinaCreLoanAppSaveForPerfact(WmsFinaCreLoanAppSearchBeanVO bean, UserBean user);

	
}