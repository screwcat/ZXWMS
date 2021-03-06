package com.zx.emanage.cremanage.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.zx.emanage.cremanage.vo.WmsCreCreditCopyBeanVO;
import com.zx.emanage.cremanage.vo.WmsCreCreditHeadHouseSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsCreCreditHeadSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsCreFinalAuditAmountSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsMoaHouseInfoVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: IWmsCreCreditHeadService
 * 模块名称：贷款主表
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 baisong 创建文件
 */

public interface IWmsCreCreditHeadService
{
    /**
     * Description :贷款查询数据导出功能
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     */
    public Map<String, Object> getListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);

    /**
     * 1.实现信贷贷款复核导出功能
     * 
     * @param 内容描述：实现查询贷款复核出的数据进行数据分离导出
     * @param 修改时间：20141121
     * @param queryInfo
     * @return record
     * @author hancd
     */
    public Map<String, Object> getCheckListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);

    /**
     * Description 房贷完善 房贷查询 无分�?带条件数�?
     * 
     * @param queryInfo
     * @return record list
     * @author ry
     */
    public Map<String, Object> getHouseListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);

    /**
     * Description :getList
     * 
     * @param org_wms_cre_credit_head_id
     * @return record list
     * @author
     */
    public Map<String, Object> getDiffPhoneListById(String org_wms_cre_credit_head_id);

    /**
     * 流水、信息�?电审、征信数据导�?
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getTeamListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user,
                                                        String flag_byk);

    /**
     * 房贷流水、办件�?证信数据导出
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getTeamListWithoutPagingForFdForAdd(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                                   UserBean bean, String housekey);

    /**
     * Description: 实现信贷:贷款复核 流水审批 信息审批 电审审批 证信审批 验点审批 页面查询数据显示功能
     * 
     * @param flag_byk
     * @param flag_byk=0 代表信贷贷款复核
     * @param flag_byk=1 代表信贷流水审批
     * @param flag_byk=2 代表信贷信息审批
     * @param flag_byk=3 代表信贷电审审批
     * @param flag_byk=4 代表信贷证信审批
     * @param flag_byk=5 代表信贷验点审批
     * @author hancd
     */
    public Map<String, Object> getListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user, String flag_byk);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author wangyishun
     */
    public Map<String, Object> getListWithPagingForFd(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);
    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author wangyishun
     */
    public Map<String, Object> getListWithPagingForCar(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);

    /**
     * Description :房贷查询列表分页
     * 
     * @param queryInfo housekey
     * @return
     * @author zhubo
     */
    public Map<String, Object> getListWithPagingForFdForAdd(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user,String housekey);
    /**
     * Description :房贷查询列表分页-完善
     * 
     * @param queryInfo housekey
     * @return
     * @author baisong
     */
    public Map<String, Object> getListWithPagingForFdForAddMakeup(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user,String housekey);
	  /**
     * Description :车贷查询列表分页
     * 
     * @param queryInfo housekey
     * @return
     * @author baisong
     */
    public Map<String, Object> getListWithPagingForCDForAdd(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user,
                                                            String housekey);
	  /**
     * Description :车贷查询列表分页-导出
     * 
     * @param queryInfo housekey
     * @return
     * @author baisong
     */
    public Map<String, Object> getListWithoutPagingForCDForAdd(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user,
                                                            String housekey);

    /**
     * 审批查询数据导出
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getCreditCheckListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo);

    /**
     * 审批查询列表分页
     * 
     * @param queryInfo
     * @param user
     */
    public Map<String, Object> getCreditCheckListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);

    /**
     * 跟踪查询数据导出
     * 
     * @param queryInfo
     * @return
     */

    public Map<String, Object> getFollowCheckListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo);

    /**
     * 房贷跟踪查询数据导出
     * 
     * @param queryInfo
     * @return
     */

    public Map<String, Object> getFollowCheckListWithoutPagingForFd(WmsCreCreditHeadSearchBeanVO queryInfo);
    /**
     * 车贷跟踪查询数据导出
     * 
     * @param queryInfo
     * @return
     */

    public Map<String, Object> getFollowCheckListWithoutPagingForCar(WmsCreCreditHeadSearchBeanVO queryInfo);

    /**
     * 贷前跟踪查询列表分页
     * 
     * @param queryInfo
     * @param user
     * @return
     */
    public Map<String, Object> getFollowCheckListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);

    /**
     * 贷前查询数据导出
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getLoanCheckListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo);

    /**
     * 贷前查询列表分页
     * 
     * @param queryInfo
     * @param user
     * @return
     */
    public Map<String, Object> getLoanCheckListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditHeadVO
     * @author auto_generator
     */
    public WmsCreCreditHead getInfoByPK(Integer wms_cre_credit_head_id);
    
    
    public BigDecimal getFinalAuditAmount(WmsCreFinalAuditAmountSearchBeanVO auditAmountSearchBeanVO);

    /**
     * 保存 贷款申请信息
     * 
     * @param mcch
     * @param mcclcda
     * @param mccclc
     * @param user
     * @return
     */
    public String saveAll(WmsCreCreditHead mcch, String attachment, String mccclc, UserBean user, String clientId,
                          String modifyJsonCus, String isComOrZC);

    /**
     * 更新 贷款申请信息
     * 
     * @param mcch
     * @param mcclcda
     * @param mccclc
     * @param user
     * @return
     */
    public String updateCredit(WmsCreCreditHead mcch, String attachment, String mccclc, UserBean user, String clientId,
                               String modifyJsonCus, String isComOrZC);

    /**
     * 更新 房贷申请信息
     * 
     * @param request
     * @param mcch 房贷申请信息
     * @param mccclc 客户联系�?信息
     * @param clientId 选择的客�?ID
     * @param zdrID 主贷人ID
     * @param mcclhid 抵押房产信息的房产ID
     * @param modifyJsonCus 修改后的数据
     * @param fileArr
     * @return result
     * @author ry
     */
    /*public String updateHouseCreAll(WmsCreCreditHead mcch, String mccclc, UserBean user, String clientId,
                                    String modifyJsonCus, String house_use, String zdrID, String mcclhid,
                                    String fileArr, String isComOrZC);*/
    
    public String updateHouseCreAll(WmsCreCreditHead mcch,UserBean user,WmsCreCreditHeadHouseSearchBeanVO bean);

    /**
     * 保存 房贷申请信息
     * 
     * @param request
     * @param mcch 房贷申请信息
     * @param mccclc 客户联系�?信息
     * @param clientId 选择的客�?ID
     * @param zdrID 主贷人ID
     * @param mcclhid 抵押房产信息的房产ID
     * @param modifyJsonCus 修改后的数据
     * @param fileArr
     * @return result
     * @author ry
     */
    public String saveHouseCreAll(WmsCreCreditHead mcch, String mccclc, UserBean user, String clientId,
                                  String modifyJsonCus, String house_use, String zdrID, String mcclhid, String fileArr, String fileArrkh,
                                  String isComOrZC, Integer[] deleteAttIds);

    /**
     * 提交贷款审批操作
     * 
     * @param wms_credit_head_ids 贷款表单id集合
     * @param pass 审批结果
     * @param advice 审批意见
     * @param user_id 用户id
     * @param taskIds 流程节点id集合
     * @param wmsCreditLimits 申请贷款金额集合
     * @param wmsCreditCreLoanTypes 信贷产品类型
     * @return
     */
    public String creditApproveSave(String wms_credit_head_ids, String taskids, String wmsCreditLimits,
                                    String wmsCreditCreLoanTypes, String pass, String advice, String user_id);

    /**
     * 贷款查询 显示
     * 
     * @param queryInfo
     * @param user
     * @return
     */
    public Map<String, Object> getListWithPagingforadd(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);

    /**
     * 房贷查询 显示
     * 
     * @param queryInfo
     * @param user
     * @return
     */
    public Map<String, Object> getListWithPagingforaddHouse(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);
    
    /**
     * 车贷查询 显示
     * 
     * @param queryInfo
     * @param user
     * @return
     */
    public Map<String, Object> getCarCreditWithPagingList(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);
    /**
     * 车贷查询 显示
     * 
     * @param queryInfo
     * @param user
     * @return
     */
    public Map<String, Object> getCarCreditWithoutPagingList(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);

    /**
     * 复核退回列表分页
     * 
     * @param queryInfo
     * @param user
     * @return
     * @author zhubo
     */
    public Map<String, Object> getRecheckReturnListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);

    /**
     * 房贷完善�?��列表显示
     * 
     * @param queryInfo
     * @param user
     * @return
     * @author zhubo
     */
    public Map<String, Object> getCompleteReturnListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);

    /**
     * 复核退回数据导出
     * 
     * @param queryInfo
     * @param user
     * @return
     * @author zhubo
     */
    public Map<String, Object> getRecheckReturnListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);

    /**
     * 房贷完善列表的导�?
     * 
     * @param queryInfo
     * @param user
     * @return
     * @author zhubo
     */
    public Map<String, Object> getCompleteReturnListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);

    /**
     * 信贷贷前�?��列表显示
     * 
     * @param queryInfo
     * @param user
     * @return
     * @author zhubo
     */
    public Map<String, Object> getLoanBeforeReturnListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);

    /**
     * 房贷贷前�?��列表显示
     * 
     * @param queryInfo
     * @param user
     * @return
     * @author zhubo
     */
    public Map<String, Object> getHouseBeforeReturnListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);

    /**
     * 信贷贷前�?��列表导出
     * 
     * @param queryInfo
     * @param user
     * @return
     * @author zhubo
     */
    public Map<String, Object> getLoanBeforeReturnListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                                    UserBean user);

    /**
     * 房贷贷前�?��列表导出
     * 
     * @param queryInfo
     * @param user
     * @return
     * @author zhubo
     */
    public Map<String, Object> getHouseBeforeReturnListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                                     UserBean user);

    /**
     * 房贷办件数据导出
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getCardListWithoutPagingForFdForAdd(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                                   UserBean bean, String housekey);

    /**
     * 复议修订信息导出
     * 
     * @param queryInfo,user
     * @return
     * @author zhubo
     */
    public Map<String, Object> getLoanReviewReturnWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);

    /**
     * 复议修订信息列表分页
     * 
     * @param queryInfo,user
     * @return
     * @author zhubo
     */
    public Map<String, Object> getLoanReviewReturnListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);

    /**
     * 实现对信贷和房贷经过暂存的数据进行删除操作 软删除
     * 
     * @param wms_cre_credit_head_id
     * @param cre_loan
     * @return
     */
    public String deleteByPK(Integer wms_cre_credit_head_id, Integer cre_loan);

    /**
     * 实现信贷复议修订单据提交和暂存功能
     * 
     * @param mcch
     * @param fileArr
     * @param linkmaninfo
     * @param user
     * @param personArr
     * @param modifyJsonCus
     * @param isComOrZC
     * @param taskId
     * @return
     */
    public String ReviewRevisionCredit(WmsCreCreditHead mcch, String fileArr, String linkmaninfo, UserBean user,
                                       String personArr, String modifyJsonCus, String isComOrZC, String taskId);
    /**
     * @Title: getWmsCrecreditHeadInfoByCusid 
     * @Description: 查询客户的房贷信息（如果多条显示最后一条）
     * @param wms_cus_customer_id
     * @return    
     * @return Map<String,Object>    
     * @throws
     * @author lvtu 
     * @date 2015年7月2日 下午5:29:11
     */
	public Map<String, Object> getWmsCrecreditHeadInfoByCusid(Integer wms_cus_customer_id);
	
	/**
	 * @Description: 根据ID查询贷款单信息
	 * @param queryInfo
	 * @param request
	 * @return    
	 * @return Map<String,Object>    
	 * @throws
	 * @author lvtu 
	 * @date 2015年7月2日 下午5:19:02
	 */
	public Map<String, Object> getWmsCusCreditHeadInfo(Integer wms_cre_credit_head_id);
	
	/**
	 * @Title: saveCarCreAll 
	 * @Description: 车贷申请
	 * @param mcch
	 * @param linkmaninfo
	 * @param user
	 * @param personArr
	 * @param modifyJsonCus
	 * @param house_situation
	 * @param zdrID
	 * @param mcclhid
	 * @param mcclcid
	 * @param fileArr
	 * @param isComOrZC
	 * @return    
	 * @return String    
	 * @throws
	 * @author lvtu 
	 * @date 2015年7月28日 下午3:27:48
	 */
	public String saveCarCreAll(WmsCreCreditHead mcch, String linkmaninfo, UserBean user, String personArr, String modifyJsonCus,
			String house_situation, String zdrID, String mcclhid, String mcclcid, String fileArr, String isComOrZC, String is_house_loan);
	/**
	 * @Title: updateCarCreAll 
	 * @Description: 车贷信息修改
	 * @param mcch
	 * @param linkmaninfo
	 * @param user
	 * @param personArr
	 * @param modifyJsonCus
	 * @param house_situation
	 * @param zdrID
	 * @param mcclhid
	 * @param mcclcid
	 * @param fileArr
	 * @param isComOrZC
	 * @param is_house_loan
	 * @return    
	 * @return String    
	 * @throws
	 * @author lvtu 
	 * @date 2015年7月31日 下午2:40:33
	 */
	public String updateCarCreAll(WmsCreCreditHead mcch, String linkmaninfo, UserBean user, String personArr, String modifyJsonCus,
			String house_situation, String zdrID, String mcclhid, String mcclcid, String fileArr, String isComOrZC, String is_house_loan, String taskId);
	/**
	 * @Title: getCarRecheckReturnListWithPaging 
	 * @Description: 车贷复核退回
	 * @param queryInfo
	 * @param user
	 * @return    
	 * @return Map<String,Object>    
	 * @throws
	 * @author lvtu 
	 * @date 2015年7月30日 下午4:36:33
	 */
	public Map<String, Object> getCarRecheckReturnListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);
	public Map<String, Object> getCarRecheckReturnListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);
	/**
	 * @Title: getBeforeListWithoutPaging 
	 * @Description: 车贷贷前退回不分页/分页
	 * @param queryInfo
	 * @param user
	 * @return    
	 * @return Map<String,Object>    
	 * @throws
	 * @author lvtu 
	 * @date 2015年8月1日 下午3:38:47
	 */
	public Map<String, Object> getCarBeforeReturnListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);

	public Map<String, Object> getCarBeforeReturnListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);
	
	/**
	 * Description: 房产初评列表页查询
	 * @param queryInfo
	 * @param user
	 * @author wangyihan 
	 * @return
	 */
    public Map<String, Object> gethousePreliminaryAssessmentListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);
    
    /**
     * Description: 房产初评列表页查询
     * @param queryInfo
     * @param user
     * @author wangyihan 
     * @return
     */
    public Map<String, Object> gethousePreliminaryAssessmentListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);
    /**
  	 * Description :【新房贷】流程历程查看
  	 * @param  wms_inve_transa_id
  	 * @return Map
  	 * @date 2016年2月25日 上午10:02
  	 * @author baisong
  	 */
    public Map<String, Object> getPerfectHousingLoanProcessView(String wms_cre_credit_head_id);
    /**
  	 * Description :根据主键获取主表信息--现用于流程历程区分
  	 * @param  wms_inve_transa_id
  	 * @return Map
  	 * @date 2016年2月25日 上午10:02
  	 * @author baisong
  	 */
    public Map<String, Object> getHeadInfo(Integer wms_cre_credit_head_id);
    /**
  	 * Description : 根据主键获取主表业务员信息
  	 * @param  wms_inve_transa_id
  	 * @return Map
  	 * @date 2016年3月7日 上午10:02
  	 * @author baisong
  	 */
    public Map<String, Object> getHeadSalesman(Integer wms_cre_credit_head_id);
    
    /**
     * 保存房贷申请
     * @date 2016.5.11
     */
    public Map<String, Object> saveHouseLoan(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);
    
    /**
     * 获取房贷单据信息完善列表
     */
    public Map<String, Object> getHouseLoanCompletedList(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);
    
    /**
     * moa调用wms方法:房产核查结果保存
     * @param request
     * @param WmsMoaHouseInfoVO
     * @return 
     */
    public Map<String, Object> savePropertyVerificationInfoUp(WmsMoaHouseInfoVO bean);
    
    
    /**
     * moa调用wms方法:单据作废
     * @param debtkey 
     * @param pass 
     * @param personnelId 
     * @param advice，wms_cre_credit_head_id
     * @return 
     */
	public void DocumentVoidUp(String advice, Integer wms_cre_credit_head_id, String pass, String debtkey, Integer personnelId);
    
    
	/**
     * 方法用途：用于发送消息 ，消息中心消息，短信息，极光推送消息
     * @param Map<String,Object> map 当前参数中会传递多个数据 标示发送消息的情况和内容 
     * @param map中  message_center 如果值为1则发送消息中心消息
     * @param map中  short_message  如果值为1则发送短信息消息
     * @param map中  quart_message  如果值为1则发送极光消息
     * @param map中其他参数自定义
     * @return Map<String, Object> map  成功、失败
     * @author baisong
     */
	public Map<String, Object>   getUserAndSendInfo(Map<String,Object> map);
	
	/**
     * 保存修改日志
     * @author Administrator
     * @param WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonGenerationException 
     */
    public void saveHouseLoanLog(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user) throws JsonGenerationException, JsonMappingException, IOException;
/**
 * 
 * 很据ID查询上单表
 * @param wms_cre_credit_head_id
 * @return
 */
	public WmsCreCreditHead getwmscrecredit(Integer wms_cre_credit_head_id);
    
    
    /**
     * Description : 获取流程的版本号
     * 
     * @param String
     * @return 
     * @author baisong
     * @date 2016-10-17
     */
	public Map<String,Object> getWorkFlowVersion(WmsHouseCreditWorkFlowVO wDebtWorkFlowVO);
	
    /**
     * 根据菜单url获取当前人的权限
     * 
     * @return
     * @author baisong
     * @date 2016-10-19
     */
	public String queryChildrenDeptInfo(Map<String,Object> map);

    /**
     * 
     * @Title: getTaskInfo
     * @Description: (根据业务主键id获取task信息)
     * @param wDebtWorkFlowVO
     * @return 
     * @author: baisong
     * @time:2016年12月19日 下午1:57:46
     * history:
     * 1、2016年12月19日 baisong 创建方法
     */
    public Map<String, Object> getTaskInfo(WmsHouseCreditWorkFlowVO wDebtWorkFlowVO);

    /**
     * @Title: wmsPerfectContract
     * @Description: TODO(查询贷款主表信息)
     * @param wms_cre_credit_head_id
     * @return 
     * @author: jiaodelong
     * @time:2016年12月26日 下午3:55:09
     * history:
     * 1、2016年12月26日 jiaodelong 创建方法
    */
    public Map<String, Object> wmsPerfectContract(Integer wms_cre_credit_head_id);
    
    /**
     * @Title: copyHeadTableInfo
     * @Description: (组合贷复制所有表)
     * @param wDebtWorkFlowVO
     * @return 
     * @author: baisong
     * @time:2016年12月23日 下午4:53:09
     * history:
     * 1、2016年12月23日 baisong 创建方法
     */
    public Map<String, Object> copyHeadTableInfo(WmsCreCreditCopyBeanVO queryInfo, UserBean user);
    
    /**
     * 
     * @Title: getWmsCreCreditHeadList
     * @Description: 查询贷款主表list
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2016年12月28日 上午10:25:59
     * history:
     * 1、2016年12月28日 wangyihan 创建方法
     */
    public WmsCreCreditHeadSearchBeanVO getWmsCreCreditHeadList(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);

    /**
     * @Title: sendInfoAsynchronous
     * @Description:  发送消息-短信、极光、消息中心
     * @author: baisong
     * @time:2017年4月13日 上午11:00:16
     * history:
     * 1、2017年4月13日 baisong 创建方法
     */
    public void sendInfoAsynchronous(Map<String, Object> sendMap);
    
    /**
     * 
     * @Title: searchLoanNumberStatisticsList
     * @Description: 查询件数统计列表
     * @param request
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2017年6月15日 下午1:15:55
     * history:
     * 1、2017年6月15日 wangyihan 创建方法
     */
    public WmsCreCreditHeadSearchBeanVO searchLoanNumberStatisticsList(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);
    
    /**
     * 
     * @Title: searchLoanNumberTotal
     * @Description: 查询件数统计总数
     * @param request
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2017年6月15日 下午1:15:55
     * history:
     * 1、2017年6月15日 wangyihan 创建方法
     */
    public WmsCreCreditHeadSearchBeanVO searchLoanNumberTotal(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);
    
    /**
     * 
     * @Title: billStatusStaticsDisp
     * @Description: 系统单据状态统计初始化
     * @param request
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2017年7月6日 下午1:15:55
     * history:
     * 1、2017年7月6日 wangyihan 创建方法
     */
    public WmsCreCreditHeadSearchBeanVO billStatusStaticsDisp(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);
    
    /**
     * 
     * @Title: searchBillStatusStaticsByTime
     * @Description: 根据日期查询上单、放款数汇总
     * @param request
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2017年7月6日 下午1:15:55
     * history:
     * 1、2017年7月6日 wangyihan 创建方法
     */
    public WmsCreCreditHeadSearchBeanVO searchBillStatusStaticsByTime(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user);
    
    
    
}