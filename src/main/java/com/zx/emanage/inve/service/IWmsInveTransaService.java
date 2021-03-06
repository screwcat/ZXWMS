package com.zx.emanage.inve.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.internal.LinkedTreeMap;
import com.zx.emanage.inve.vo.WmsInveDebtHeadSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.emanage.inve.vo.WmsInveIncomeBillVo;
import com.zx.emanage.inve.vo.WmsInveSelectBillBeanVo;
import com.zx.emanage.inve.vo.WmsInveTransaCardSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveTransaSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveCustomer;
import com.zx.emanage.util.gen.entity.WmsInveCustomerCard;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaAtt;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveTransaService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsInveTransaSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsInveTransaSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getList(WmsInveTransaSearchBeanVO queryInfo,UserBean user);
    /**
     * 获取协议续期页面列表导出
     * 
     * @param queryInfo
     * @return Map
     * @author baisong
     */
    public Map<String, Object> getListRenewalExcel(WmsInveTransaSearchBeanVO queryInfo,UserBean user);

    
    /**
     * Description :【理财上单】-【金额管理】获取金额管理列表页数据
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     */
    public Map<String, Object> getListForJEGLWithPaging(WmsInveTransaSearchBeanVO queryInfo,UserBean user);
    /**
     * Description :【理财上单】-【金额管理】获取金额管理导出数据
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     */
    public Map<String, Object> getListForJEGLWithoutPaging(WmsInveTransaSearchBeanVO queryInfo,UserBean user);
    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInveTransaVO
     * @author auto_generator
     */
    public WmsInveTransa getInfoByPK(Integer wms_inve_transa_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author hancd
     */
    public String save(WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd, String fileArr, UserBean user,
                       String saveFlag,WmsInveCustomer wmsCustomer,WmsInveTransaSearchBeanVO beanVO);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsInveTransa bean, UserBean user);

    /**
     * @Title: updateForJEZF 
     * Description :【理财上单】财务管理>金额管理>金额支付
     * @param bean
     * @param itcardJSON
     * @param user
     * @param wmsTransaProd
     * @param ransaAtt
     * @param flagKey
     * @param taskId
     * @param wmsInveCustomerCard 客户收益卡信息
     * @return "success" or "error" or user defined
     * @date 2015年12月14日 14:56
     * @author hancd
     */
    public String updateForJEZF(WmsInveTransa bean, String itcardJSON, UserBean user,WmsInveTransaProd wmsTransaProd,WmsInveTransaSearchBeanVO beanvo, WmsInveCustomerCard wmsInveCustomerCard);
    
    /**
     * @Title: changeCategoryOrPaymentAccount
     * @Description: 金额支付修改金或修改产品后处理债权方法
     * @param origCategoryId 原始产品id
     * @param newCategoryId 新产品id
     * @param transaId 上单主键
     * @param productAccount 投资金额
     * @param actDateOfPayment 实际支付日期
     * @param user 登录用户信息
     * @return 是否匹配上债权（true表示匹配上）
     *      当不需要匹配时也返回true
     * @author: jinzhm
     * @time:2017年2月14日 下午2:32:28
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public boolean changeCategoryOrPaymentAccount(int origCategoryId, int newCategoryId, int transaId,
                                                  BigDecimal productAccount, Date actDateOfPayment, UserBean user);

    /**
     * @Title: confirmCreditMatched
     * @Description: 确认单据债权匹配占用是否正确
     * @param categoryId 产品id
     * @param transaId 上单主键
     * @param productAccount 投资金额
     * @param actDateOfPayment 实际支付时间
     * @param user 用户登录信息
     * @return 债权匹配是否成功
     * @author: jinzhm
     * @time:2017年2月14日 下午3:04:47
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public boolean confirmCreditMatched(int categoryId, int transaId, BigDecimal productAccount,
                                        Date actDateOfPayment, UserBean user);

    /**
     * @Title: releaseMatchedCredit
     * @Description: 释放已匹配的债权
     * @param transaId 上单主键
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年2月14日 下午3:53:39
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public void releaseMatchedCredit(int transaId, UserBean user);
    
    /**
     * @Title: matchHoldCredit
     * @Description: 债权匹配并占用债权
     * @param transaId 上单主键
     * @param categoryId 产品主键
     * @param productAccount 投资金额
     * @param actDateOfPayment 实际支付日期
     * @param user 登录用户信息
     * @return 是否匹配成功
     * @author: jinzhm
     * @time:2017年2月14日 下午4:57:41
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public boolean matchHoldCredit(int transaId, int categoryId, BigDecimal productAccount, Date actDateOfPayment,
                                   UserBean user);

    /**
     * Description :理财 债权转让及受让协议 后台查询数据 查询了很多表
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author baisong
     */
    public Map<String, Object> getMapForLc(Integer wms_inve_transa_id);

    /**
     * 获取债权匹配列表数据
     * 
     * @param queryInfo
     * @return Map
     * @author 张风山
     */
    public Map<String, Object> getListWithPagingForMatch(WmsInveTransaSearchBeanVO queryInfo,UserBean user);
    /**
     *	获取协议续期页面列表数据 
     * @param queryInfo
     * @return Map
     * @author baisong
     */
    public Map<String, Object> getListWithPagingForRenewal(WmsInveTransaSearchBeanVO queryInfo,UserBean user);
    /**
     * 获取债权选择列表数据
     * 
     * @param queryInfo
     * @return Map
     * @author 张风山
     */
    public Map<String, Object> getListWithPagingForChoose(WmsInveTransaSearchBeanVO queryInfo, UserBean user);

    /**
     * 理财查询列表
     * 
     * @param queryInfo
     * @return record list
     * @author zhubo
     */
    public Map<String, Object> getSearchFinancialListWithPaging(WmsInveTransaCardSearchBeanVO queryInfo, UserBean user);
    
    /**
     * 理财特批列表
     * 
     * @param queryInfo
     * @return record list
     * @author lvtu
     */
    public Map<String, Object> getApprovalFinancialListWithPaging(WmsInveTransaCardSearchBeanVO queryInfo, UserBean user);

    /**
     * 理财查询列表导出
     * 
     * @param queryInfo
     * @return record list
     * @author zhubo
     */
    public Map<String, Object> getSearchFinancialListWithoutPaging(WmsInveTransaCardSearchBeanVO queryInfo,
                                                                   UserBean user);

    /**
     * 对账报表列表
     * 
     * @param queryInfo
     * @return record list
     * @author zhubo
     */
    public Map<String, Object> getAmountCheckingReportWithPaging(WmsInveTransaCardSearchBeanVO queryInfo, UserBean user);

    /**
     * 对账报表列表导出
     * 
     * @param queryInfo
     * @return record list
     * @author zhubo
     */
    public Map<String, Object> getAmountCheckingReportWithoutPaging(WmsInveTransaCardSearchBeanVO queryInfo,
                                                                    UserBean user);

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author wangyishun
     */
    public Map<String, Object> getSdlcxx(Integer wms_inve_transa_id, Integer wms_inve_transa_prod_id);

    /**
     * 理财查询列表
     * 
     * @param queryInfo
     * @return record list
     * @author zhubo
     */
    public Map<String, Object> getRedeemApplyListWithPaging(WmsInveTransaCardSearchBeanVO queryInfo, UserBean user);

    /**
     * 理财查询列表导出
     * 
     * @param queryInfo
     * @return record list
     * @author zhubo
     */
    public Map<String, Object> getRedeemApplyListWithoutPaging(WmsInveTransaCardSearchBeanVO queryInfo, UserBean user);
    
    /**
     * @Title: getApprovalFinancialListWithoutPaging 
     * @Description: 理财特批列表查询
     * @param queryInfo
     * @param user
     * @return Map<String,Object> 
     * @throws
     * @author lvtu 
     * @date 2015年9月7日 下午2:25:31
     */
    public Map<String, Object> getApprovalFinancialListWithoutPaging(WmsInveTransaCardSearchBeanVO queryInfo, UserBean user);
    
    /**
     * @Title: getApprovalFinancialInfo 
     * @Description: 理财特批详细信息
     * @param wms_inve_transa_id
     * @param wms_inve_transa_prod_id
     * @param wms_inve_transa_protocol_id
     * @return Map<String,Object> 
     * @throws
     * @author lvtu 
     * @date 2015年9月8日 上午9:33:23
     */
	public Map<String, Object> getApprovalFinancialInfo(Integer wms_inve_transa_id, Integer wms_inve_transa_prod_id,
			Integer wms_inve_transa_protocol_id);

	/**
	 * @Title: getApprovalFinancialywInfo 
	 * @Description: 理财业务特批详细信息
	 * @param wms_inve_transa_id
	 * @param wms_inve_transa_prod_id
	 * @param wms_inve_transa_protocol_id
	 * @return Map<String,Object> 
	 * @throws
	 * @author lvtu 
	 * @date 2015年9月22日 上午10:39:33
	 */
	public Map<String, Object> getApprovalFinancialywInfo(Integer wms_inve_transa_id, Integer wms_inve_transa_prod_id,
			Integer wms_inve_transa_protocol_id);

    /**
     * 根据上单信息表主键获取相关信息--获取上单信息上单产品信息
     * 
     * @param queryInfo
     * @return Map
     * @author baisong
     */
    public Map<String, Object> getObjectInfo(WmsInveTransaCardSearchBeanVO queryInfo);
    /**
   	 * @Title:getFinancialSingleProcessView
   	 * Description :【理财上单】流程历程查看
   	 * @param  wms_inve_transa_id
   	 * @return Map
   	 * @date 2015年12月12日 上午10:02
   	 * @author hancd
   	 */
	public Map<String, Object> getFinancialSingleProcessView(
			String wms_inve_transa_id);
    /**
     * @Title: toBackforJEZF 
     * Description :【理财上单】财务管理>金额管理>支付退回
     * @param bean
     * @param itcardJSON
     * @param user
     * @param wmsTransaProd
     * @param ransaAtt
     * @param flagKey
     * @return "success" or "error" or user definedSS
     * @date 2015年12月14日 14:56
     * @author hancd
     */
	public String toBackforJEZF(WmsInveTransa bean, UserBean user,
			WmsInveDebtWorkFlowVO wDebtWorkFlowVO);
	/**
	 * 理财上单草稿作废
	 * @param  wms_inve_transa_id
	 * @date 2015年12月15日 上午11:29
	 * @author jiaodelong
	 */
	public String doCancel(String wms_inve_transa_id,String status,String taskId,String different,UserBean user,String adviceLC);
	 /**
     * Description :【理财上单】-【上单审核】获取审核列表
     * @param queryInfo
     * @return record list
     * @author hancd
     * @date 2015年12月15日 13:11
     */
	public Map<String, Object> getListForSDSHWithPaging(
			WmsInveTransaSearchBeanVO queryInfo, UserBean user);
    /**
     * Description :【理财上单】-【上单审核】获取审核导出
     * @param queryInfo
     * @return record list
     * @author hancd
     * @date 2015年12月15日 13:11
     */
	public Map<String, Object> getListForSDSHWithoutPaging(
			WmsInveTransaSearchBeanVO queryInfo, UserBean user);
	
	
	/**
	 * Description :【理财复核】列表显示
	 * @param queryInfo
	 * @return record list
	 * @author jiaodelong
	 */
	public Map<String, Object> getFinancialReturnpaginglist(WmsInveDebtHeadSearchBeanVO queryInfo,UserBean user);	
	/**
	 * Description :【理财复核】导出
	 * @param queryInfo
	 * @return record list
	 * @author jiaodelong
	 */
	public Map<String, Object> getFinancialReturnWithoutPaging(WmsInveDebtHeadSearchBeanVO queryInfo,UserBean user);	
    /**
     * @Title: toSingleNullifyforSDSH 
     * Description :【理财上单】财务管理>理财上单>上单审核>单据作废
     * @param bean
     * @param wDebtWorkFlowVO
     * @return "success" or "error" or user definedSS
     * @date 2015年12月14日 14:56
     * @author hancd
     */
	public String toSingleNullify(WmsInveTransa bean,WmsInveTransaProd wTransaProd,UserBean user,
			WmsInveDebtWorkFlowVO wDebtWorkFlowVO ,String debtkey);
  	 /**
  	  *@Title: getPrintProtocolWithPaginglist 
     * Description :【财务管理】-【理财上单】-【理财签约】(打印协议/客户确认)  获取签约列表数据
     * @param queryInfo
     * @return record list
     * @author hancd
     * @date 2015年12月15日 13:11
     */
	public Map<String, Object> getPrintProtocolWithPaginglist(
			WmsInveTransaSearchBeanVO queryInfo, UserBean user);
	 /**
     *@Title: getPrintProtocolWithoutPaginglist 
     * Description :【财务管理】-【理财上单】-【理财签约】(打印协议/客户确认)  获取签约列表导出
     * @param queryInfo
     * @return record list
     * @author hancd
     * @date 2015年12月15日 13:11
     */
	public Map<String, Object> getPrintProtocolWithoutPaginglist(
			WmsInveTransaSearchBeanVO queryInfo, UserBean user);
	/**
     *@Title: getPrintProtocolWithoutPaginglist 
     * Description :【财务管理】-【理财上单】-【理财签约】(客户确认)操作
     * @param request
     * @param wTransaProd
     * @param wDebtWorkFlowVO
     * @param fileArr
     * @return "success" or "error" or other
     * @author hancd
     * @date 2015年12月16日 12:11
     */
	public String toSingleConfirmforLCQY(WmsInveTransaProd wTransaProd,
			UserBean user, WmsInveDebtWorkFlowVO wDebtWorkFlowVO, String fileArr);
	
   	/**
   	 * @Description :上单管理--支付退回列表
   	 * @param queryInfo
   	 * @return record list
   	 * @author baisong
   	 * @date 2015-12-16
   	 */
	public Map<String, Object> getListWithPagingForPaidReturn(WmsInveTransaSearchBeanVO queryInfo,UserBean user);
	/**
	 * Description :上单管理--支付退回列表  导出
	 * @param queryInfo
   	 * @author baisong
   	 * @date 2015-12-16
	 */
	public Map<String, Object> getListWithoutPagingForPaidReturnExcel(WmsInveTransaSearchBeanVO queryInfo,UserBean user);
	
 	/**
   	 * @Description :上单管理--审核退回列表 
   	 * @param queryInfo
   	 * @return record list
   	 * @author baisong
   	 * @date 2015-12-16
   	 */
	public Map<String, Object> getListBackDirectorApproval(WmsInveTransaSearchBeanVO queryInfo,UserBean user);
	/**
	 * Description :上单管理--审核退回列表   导出
	 * @param queryInfo
   	 * @author baisong
   	 * @date 2015-12-16
	 */
	public Map<String, Object> getListBackDirectorApprovalExcel(WmsInveTransaSearchBeanVO queryInfo,UserBean user);
	/**
	 * Description :【复核退回】列表显示
	 * @param queryInfo
	 * @return record list
	 * @author jiaodelong
	 */
	public Map<String, Object> getreturnChecklist(WmsInveDebtHeadSearchBeanVO queryInfo,UserBean user);	
	/**
	 * Description :【复核退回】导出
	 * @param queryInfo
	 * @return record list
	 * @author jiaodelong
	 */
	public Map<String, Object> getreturncheckoutpaginglist(WmsInveDebtHeadSearchBeanVO queryInfo,UserBean user);
	 /**
     * Description :理财上单保存理财信息--有支付页面信息的
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author baisong
     */
    public String saveUpdate(WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd, String fileArr, UserBean user,
                       String saveFlag,WmsInveCustomer wmsCustomer,WmsInveTransaSearchBeanVO beanVO);
/**
 * 根据上单ID 查询单据状态（用于判断是否显示支付TAB页）
 * 
 * @param wms_inve_transa_id
 * @return
 */
	public String doSearchStatus(String wms_inve_transa_id);

public Map<String, Object> getExtendapplylistwithpaging(
		WmsInveTransaCardSearchBeanVO queryInfo, UserBean user);

public Map<String, Object> getExtendapplylistwithoutpaging(
		WmsInveTransaCardSearchBeanVO queryInfo, UserBean user);

public Map<String, Object> getInfoByPK4Extend(Integer wms_inve_transa_id);

	/**
	 * Description :理财上单--根据选择的业务员信息进行获取对应的各个总的信息
	 * @param personne_id 人员表信息主键
	 * @param concurrent_post_deptid 兼职部门id
	 * @return
	 * @author donghao
	 * @param concurrent_post_deptid 
	 * @date 2016年8月10日15:00:21
	 */
	public Map<String, Object> findPersonnelInformationByPersonnelId(int personne_id, int concurrent_post_deptid);
	
	/**
	 * 根据传入的参数获得客户是否存在理财单据信息。
	 * 
	 * @param pm_personnel_id
	 *            原客户经理Id
	 * @param cus_ids
	 *            迁移客户集合（多个用户使用逗号分割）
	 * @return 返回理财单据信息(状态不是1.草稿，6.已完成，7.已终止）。<br/>
	 *         返回格式为（影响佣金范围固定设置成影响开单奖及存量奖）：<br/>
	 *         {ret_code:"",ret_msg:"",data_list:[{cus_name:"",category_name:"",
	 *         date_of_payment
	 *         :"",commission_range:"3"},{cus_name:"",category_name
	 *         :"",date_of_payment:"",commission_range:"3"}]}
	 * @author jinzhm
	 */
	public Map<String, Object> getCustomerDataMoa(String pm_personnel_id,
			String cus_ids);

	/**
	 * 归属公司
	 */
	public static final int BELONG_COMPANY = 0;
	/**
	 * 归属个人
	 */
	public static final int BELONG_PERSONNEL = 1;
	/**
	 * 客户迁移数据类型：1：个人到公司，2：公司到个人，3：个人到个人
	 */
	public static final String DATA_TYPE_1 = "1";
	/**
	 * 客户迁移数据类型：1：个人到公司，2：公司到个人，3：个人到个人
	 */
	public static final String DATA_TYPE_2 = "2";
	/**
	 * 客户迁移数据类型：1：个人到公司，2：公司到个人，3：个人到个人
	 */
	public static final String DATA_TYPE_3 = "3";
	
	/**
	 * 根据原客户经理id和迁移客户集合查询上单信息(状态不是1.草稿，6.已完成，7.已终止），并修改上单信息的归属业务员为新客户经理Id。
	 * 
	 * @param pm_personnel_id
	 *            原客户经理Id
	 * @param cus_ids
	 *            迁移客户集合（多个用户使用逗号分割）
	 * @param new_pm_personnel_id
	 *            新客户经理Id
	 * @param user
	 *            登录用户信息
	 * @return
	 * @author jinzhm
	 */
	public Map<String, Object> changeCustomerDataMoa(String pm_personnel_id,
			List<LinkedTreeMap> cusIdList, String new_pm_personnel_id, UserBean user);

	/**
	 * @Deprecated 获取产品信息
	 * @return
	 * @author donghao
	 * @date 2016年8月26日10:43:08
	 */
	public List<WmsInvePruductCategory> findCategoryAll();

	/**
	 * @Deprecated 根据条件查询客户赎回中的单据信息
	 * @param queryInfo
	 * @return
	 * @author donghao
	 * @date 2016年8月26日14:01:37
	 */
	public Map<String, Object> findWmsInveTransaBills(WmsInveSelectBillBeanVo queryInfo);
	
	/**
     * @Deprecated 上单单据作废时需要将支付信息设置成未生效,同时如果是续单本金类型的数据,需要将试用金额设置回去
     * @param wms_inve_transa_id
     * @param user
     * @author donghao
     * @date 2016年9月2日16:31:37
     */
	public void wmsInveTransaInvalid(int wms_inve_transa_id, UserBean user);
	
	/**
	 * @Deprecated 根据上单信息表主键获取对应的上单信息
	 * @param wms_inve_transa_id
	 * @return
	 * @author donghao
	 * @date 2016年9月5日10:23:00
	 */
	public WmsInveTransa getWmsInveTransaByWmsInveTransaId(int wms_inve_transa_id);
	
	/**
	 * 根据传入的客户id集合返回客户存量信息。<br/>
	 * 当传入客户id是多个的时候，返回多个客户的存量信息；当传入客户id是空的时候，返回所有客户存量信息。
	 * @param cus_ids 客户集合，多个客户间用逗号隔开；或者是空；
	 * @return 客户存量信息{ret_code: 000,ret_msg: '操作成功',data_list:[{customer_id:'1',stock_amount:'100000'}]}
	 * @author jinzhm
	 */
	public Map<String, Object> getCustomerStockMoa(String cus_ids);

	/**
	 * 根据传入的理财上单主键，查询客户电话号码，发送验证码通知客户超频即将到期
	 * @param wms_inve_transa_id
	 * @return
	 */
	public String reSendMessageNotification(String wms_inve_transa_id);

	/**
	 * 释放掉指定的单据的金额信息
	 * @param wms_inve_transa_id
	 * @param wms_inve_redeem_principal_detail_id
	 * @param user
	 */
	public void wmsInveTransaInvalidByWmsInveRedeemPrincipalDetailId(Integer wms_inve_transa_id,Integer wms_inve_redeem_principal_detail_id, UserBean user);

	/**
	 * 重新生成收益和交易记录
	 * 当transaIds有值时，逗号隔开后生成传入的值
	 * 当transaIds没有值时，生成所有收益中的收益
	 * @param transaIds
	 * @author 金志明
	 * @date 2016年10月12日 下午4:32:25
	 */
	public void reGenerateIncomeAndLog(String transaIds);

	    /**
     * @Title getHistoryCustomerBankInfo
     * @Description 根据客户的姓名获取客户存在系统中的收益卡信息
     * @param cus_name 客户名称
     * @param id_card 证件号码
     * @return 返回list集合
     * @author DongHao
     * @date 2016年11月1日11:19:43
     */
	public List<Map<String, Object>> getHistoryCustomerBankInfo(
			String cus_name, String id_card);

	/**
	 * @Title: findCardnoAndIdCard
	 * @Description: 查询该人员的收益卡号和身份证号
	 * @param wms_inve_transa_id
	 * @return 
	 * @author: zhangyunfei
	 * @time:2017年1月31日 下午3:52:32
	 * history:
	 * 1、2017年1月31日 zhangyunfei 创建方法
	*/
	Map<String, String> findCardnoAndIdCard(String wms_inve_transa_id);

    /**
     * @Title: handleYearPublicIncome
     * @Description: 处理年付公益收益；将年付按年给公益收益改成按月给
     * @author: jinzhm
     * @time:2016年11月17日 上午8:23:51
     * history:
     * 1、2016年11月17日 jinzhm 创建方法
     */
    public void handleYearPublicIncome();

    /**
     * 
     * @Title: setWmsInveCustomer
     * @Description: 设置客户信息
     * @param wmsInveCustomer
     * @param wmsInveTransa
     * @param user
     * @param sysTime
     * @return 
     * @author: DongHao
     * @time:2017年2月10日 下午6:16:51
     * history:
     * 1、2017年2月10日 DongHao 创建方法
     */
    public WmsInveCustomer setWmsInveCustomer(WmsInveCustomer wmsInveCustomer, WmsInveTransa wmsInveTransa, UserBean user, Timestamp sysTime);

    /**
     * @Title: setWmsInveTransaForUpdate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param wmsInveTransa
     * @param wmsInveCustomer
     * @param wmsInveTransaProd
     * @param saveFlag
     * @param sysTime
     * @param user
     * @return 
     * @author: DongHao
     * @time:2017年2月11日 上午9:09:34
     * history:
     * 1、2017年2月11日 DongHao 创建方法
    */
    public WmsInveTransa setWmsInveTransaForUpdate(WmsInveTransa wmsInveTransa, WmsInveCustomer wmsInveCustomer, WmsInveTransaProd wmsInveTransaProd,
                                                   String saveFlag, Timestamp sysTime, UserBean user, String data_status);

    /**
     * @Title: setWmsInveTransaForAdd
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param wmsInveTransa
     * @param wmsInveCustomer
     * @param wmsInveTransaProd
     * @param saveFlag
     * @param sysTime
     * @param user
     * @return 
     * @author: DongHao
     * @time:2017年2月11日 上午9:09:47
     * history:
     * 1、2017年2月11日 DongHao 创建方法
    */
    public WmsInveTransa setWmsInveTransaForAdd(WmsInveTransa wmsInveTransa, WmsInveCustomer wmsInveCustomer, WmsInveTransaProd wmsInveTransaProd,
                                                String saveFlag, Timestamp sysTime, UserBean user);

    /**
     * @Title: setWmsInveTransaProdForUpdate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param wmsInveTransaProd
     * @param user
     * @param sysTime
     * @return 
     * @author: DongHao
     * @time:2017年2月11日 上午9:11:06
     * history:
     * 1、2017年2月11日 DongHao 创建方法
    */
    public WmsInveTransaProd setWmsInveTransaProdForUpdate(WmsInveTransaProd wmsInveTransaProd, UserBean user, Timestamp sysTime);

    /**
     * @Title: setWmsInveTransaProdForAdd
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param wmsInveTransaProd
     * @param wmsInveTransa
     * @param user
     * @param sysTime
     * @return 
     * @author: DongHao
     * @time:2017年2月11日 上午9:11:10
     * history:
     * 1、2017年2月11日 DongHao 创建方法
    */
    public WmsInveTransaProd setWmsInveTransaProdForAdd(WmsInveTransaProd wmsInveTransaProd, WmsInveTransa wmsInveTransa, UserBean user,
                                                        Timestamp sysTime);

    /**
     * @Title: setWmsInveTransaAtt
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param wmsInveTransaAtt
     * @param wmsInveTransa
     * @return 
     * @author: DongHao
     * @time:2017年2月11日 上午9:12:18
     * history:
     * 1、2017年2月11日 DongHao 创建方法
    */
    public WmsInveTransaAtt setWmsInveTransaAtt(WmsInveTransaAtt wmsInveTransaAtt, WmsInveTransa wmsInveTransa);

    /**
     * @Title: isFinancialEmployee
     * @Description: 校验用户是否拥有财务柜员角色
     * @param user 登录用户信息
     * @return 返回是否拥有财务柜员角色标记
     * @author: jinzhm
     * @time:2017年2月8日 上午8:22:10
     * history:
     * 1、2017年2月8日 jinzhm 创建方法
     */
    public Map<String, Object> isFinancialEmployee(UserBean user);

    /**
     * @Title: getLcInfoByTransaId
     * @Description:  通过主键查询理财相关信息 
     * @param wms_inve_transa_id
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月14日 上午10:37:04
     * history:
     * 1、2017年2月14日 Administrator 创建方法
    */
    public Map<String, Object> getLcInfoByTransaId(Integer wms_inve_transa_id);
    
    
    /**
     * 
     * @Title: getAdjustShortMessage_grid
     * @Description: 根据id查询上单客户短信设置信息
     * @param id
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月14日 上午11:46:49
     * history:
     * 1、2017年3月14日 zhangmingjian 创建方法
     */
    public List<Map<String, Object>> getAdjustShortMessage_grid(String id);
    
    /**
     * 
     * @Title: adjustShortMessage_update
     * @Description: 更新批量短信设置信息
     * @param request
     * @param data
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月16日 上午10:20:47
     * history:
     * 1、2017年3月16日 zhangmingjian 创建方法
     */
    public boolean adjustShortMessage_update(HttpServletRequest request, List<Map<String,Object>> data);
    /**
     * 
     * @Title: savePayVouchersInfo
     * @Description: 保存补传凭证附件
     * @param request
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月16日 下午5:04:19
     * history:
     * 1、2017年3月16日 zhangmingjian 创建方法
     */
    public boolean savePayVouchersInfo(List<Map<String,Object>> data);

    /**
     * @Title: verifyIsShareholderBill
     * @Description: 根据单据id验证验证该单据是否是股东单据(是股东单据返回false,不是股东单据返回true)
     * @param wms_inve_transa_id
     * @return 
     * @author: zhangyunfei
     * @time:2017年3月24日 下午3:56:32
     * history:
     * 1、2017年2月2日 Administrator 创建方法
    */
    public boolean verifyIsShareholderBill(Integer wms_inve_transa_id);

    /**
     * @Title: getInveIncomeBillInfos
     * @Description: 收益账单信息表
     * @param queryInfo 查询信息对象
     * @param user 当前登录系统的用户
     * @return 返回map集合信息
     * @author: DongHao
     * @time:2017年3月31日 上午9:20:39
     * history:
     * 1、2017年3月31日 DongHao 创建方法
    */
    public Map<String, Object> getInveIncomeBillInfos(WmsInveIncomeBillVo queryInfo, UserBean user);

    /**
     * @Title: getWmsInveTransaDataStatus
     * @Description: 获取收益账单信息单据状态
     * @return  返回list集合
     * @author: DongHao
     * @time:2017年3月31日 上午11:56:50
     * history:
     * 1、2017年3月31日 DongHao 创建方法
    */
    public List<Map<String, Object>> getWmsInveTransaDataStatus();

    /**
     * @Title: findIncomeMonth
     * @Description: 获取收益账单中的收益月份
     * @return 返回list集合
     * @author: DongHao
     * @time:2017年3月31日 下午1:10:25
     * history:
     * 1、2017年3月31日 DongHao 创建方法
    */
    public Map<String, Object> getMaxIncomeMonth();

    /**
     * @Title: verifyBillIdCardIsConsistent
     * @Description: 根据传入的ids进行获取单据,并且进行判断是否有效证件一致
     * @param ids 上单表主键ids字符串(多个以逗号分隔)
     * @return 验证有效证件一致返回true,不一致返回false
     * @author: DongHao
     * @time:2017年3月31日 下午3:34:23
     * history:
     * 1、2017年3月31日 DongHao 创建方法
    */
    public boolean verifyBillIdCardIsConsistent(String ids);

    /**
     * @Title: getCategoryTotalBillInfos
     * @Description:  根据单据id进行获取对应的单据的收益信息
     * @param ids 上单表主键(多个单据id以","分隔)
     * @param return_date 收益月份
     * @param type 获取数据的类型(1表示已获收益, 2表示待获收益)
     * @return 返回数据集合
     * @author: DongHao
     * @time:2017年4月1日 上午10:03:27
     * history:
     * 1、2017年4月1日 DongHao 创建方法
    */
    public List<Map<String, Object>> getCategoryTotalBillInfos(String ids,String return_date, Integer type);

    /**
     * @Title: getYueFenTotalBillInfos
     * @Description: 获取按照月份进行统计的单据的收益信息
     * @param return_date 收益月份
     * @param type 查询数据的类型(1表示获取已获收益,2表示待获收益)
     * @param ids 上单表主键字符串(多个以","分隔)
     * @return 返回数据集合
     * @author: DongHao
     * @time:2017年4月1日 下午3:44:10
     * history:
     * 1、2017年4月1日 DongHao 创建方法
    */
    public List<Map<String, Object>> getYueFenTotalBillInfos(String return_date, Integer type, String ids);

    /**
     * @Title: getCustomerTotalBillInfos
     * @Description: 获取客户收益总信息
     * @param return_date 收益日期
     * @param ids 上单表主键字符串类型(多个主键以","号分隔)
     * @return 返回map客户收益账单信息
     * @author: DongHao
     * @time:2017年4月5日 下午4:54:31
     * history:
     * 1、2017年4月5日 DongHao 创建方法
    */
    public Map<String, Object> getCustomerTotalBillInfos(String return_date, String ids);

    /**
     * @Title: verifyIsExistCredit
     * @Description: 验证债权每日销售限额是否可用
     * @param wms_inve_transa_id 上单表主键
     * @param user 当前登录系统的人员信息对象
     * @return 返回布尔类型的变量,可以使用返回true,否则返回false
     * @author: DongHao
     * @time:2017年3月1日 上午12:55:43
     * history:
     * 1、2017年3月1日 DongHao 创建方法
    */
    public boolean verifyIsExistCredit(Integer wms_inve_transa_id, UserBean user);

    /**
     * @Title: printCustomerIncomeBillpdf
     * @Description: 打印预览客户收益账单
     * @param return_date(收益月份)
     * @param ids(字符串数组)
     * @author: zhangyunfei
     * @time:2017年4月13日 上午9:32:38
     * history:
     * 1、2017年4月13日 Administrator 创建方法
    */
    public void printCustomerIncomeBillpdf(String return_date, String ids, HttpServletResponse response);

    /**
     * @Title: verifyIsAllGroupSaleLimit
     * @Description: 金额支付时验证全集团销售限额是否可以进行支付
     * @param wmsInveTransa 上单信息对象
     * @param wmsTransaProd 上单产品信息对象
     * @param user 当前登录人信息
     * @return 返回布尔类型的值(允许支付返回true,否则返回false)
     * @author: DongHao
     * @time:2017年5月10日 上午10:23:02
     * history:
     * 1、2017年5月10日 DongHao 创建方法
    */
    public boolean verifyIsAllGroupSaleLimit(WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsTransaProd, UserBean user);
    
    /**
     * 
     * @Title: getRole
     * @Description: 获取角色
     * @return 角色信息
     * @author: zhangmingjian
     * @time:2017年5月8日 下午5:46:17
     * history:
     * 1、2017年5月8日 zhangmingjian 创建方法
     */
    public Map<String, Object> getRole(Integer user_id);

    /**
     * @Title: exportFinancialTransa
     * @Description: 理财财务主管导出签单报表
     * @param queryBean 查询条件
     * @param response 响应信息
     * @author: jinzhm
     * @time:2017年5月10日 上午10:50:30
     * history:
     * 1、2017年5月10日 jinzhm 创建方法
     */
    public void exportFinancialTransa(WmsInveTransaCardSearchBeanVO queryBean, HttpServletResponse response);

    /**
     * @Title: exportTransa
     * @Description: 理财柜员主管导出签单报表
     * @param queryInfo 查询条件
     * @param response 响应信息
     * @author: jinzhm
     * @time:2017年5月11日 上午9:26:35
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
     */
    public void exportTransa(WmsInveTransaCardSearchBeanVO queryInfo, HttpServletResponse response);

    /**
     * 
     * @Title: wmsVerifyCustomerByPtp
     * @Description: 用于验证登录ptp的客户是否满足在wms的投资要求
     * @param costomer_id crm客户id
     * @param month_num 投资要求月数
     * @return 返回布尔类型的变量:如果客户在wms系统中满足投资要求返回true,否则返回false
     * @author: DongHao
     * @time:2017年6月19日 上午9:00:08
     * history:
     * 1、2017年6月19日 DongHao 创建方法
     */
    public boolean wmsVerifyCustomerByPtp(Integer costomer_id, Integer month_num);
}