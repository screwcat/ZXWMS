package com.zx.emanage.loanfina.service;

import java.text.ParseException;
import java.util.Map;

import com.zx.emanage.loanfina.vo.WmsFinaCreAdvanceBeanVO;
import com.zx.emanage.loanfina.vo.WmsFinaCreCancelBeanVo;
import com.zx.emanage.loanfina.vo.WmsFinaCreLoanAppSearchBeanVO;
import com.zx.emanage.loanfina.vo.WmsFinaCrePeriodRepaySearchBeanVO;
import com.zx.emanage.loanfina.vo.WmsFinaCreRepaySearchBeanVO;
import com.zx.emanage.loanfina.vo.WmsFinaGetInfoStopBeanVO;
import com.zx.emanage.loanpost.vo.WmsPostLoanWorkFlowBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreApproBorrowProtocol;
import com.zx.emanage.util.gen.entity.WmsCreApproServiceProtocol;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsFinaCreLoanApp;
import com.zx.emanage.util.gen.entity.WmsFinaCrePeriodRepay;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepay;
import com.zx.emanage.util.gen.entity.WmsFinaTerminationContract;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsFinaCreRepayService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user);

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPagingforadv(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user);

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author wangyishun
     */
    public Map<String, Object> getListWithoutPagingForAdd(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author wangyishun
     */
    public Map<String, Object> getListWithPaging(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    public Map<String, Object> getListWithPagingforadv(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    public Map<String, Object> getListWithPagingforyuqi(WmsFinaCreRepaySearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author wangyishun
     */
    public Map<String, Object> getListWithPagingForAdd(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsFinaCreRepayVO
     * @author hancd
     */
    public Map<String, Object> getInfoByPK(Integer wms_fina_cre_pay_id);

    /**
     * @param wms_cre_credit_head_id
     * @return
     */
    public Map<String, Object> getBorrowAndLoanAppByPK(Integer wms_cre_credit_head_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsFinaCreRepay bean, UserBean user);

    /**
     * Description :终止合同确认 --废弃
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author baisong
     */
    public String update(WmsFinaCreRepay bean, UserBean user,WmsFinaTerminationContract tc);
    /**
     * Description :终止合同确认 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author baisong
     */
    public String update_new(WmsFinaCreRepay bean, UserBean user,WmsFinaTerminationContract tc);

    /**
     * 逾期还款确认信息获取
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author baisong
     */
    public WmsFinaCrePeriodRepaySearchBeanVO getInfoByFK(Integer wms_cre_credit_head_id);

    /**
     * 正常还款确认信息获取
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author baisong
     */
    public Map<String,Object> getInfoByFKForNor(Integer wms_cre_credit_head_id);

    /**
     * 更新还款台账和贷款还款信息表(逾期还款确认)
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author baisong
     */

    public String updateInfo(WmsFinaCrePeriodRepaySearchBeanVO bean, UserBean user);

    /**
     * 更新还款台账和贷款还款信息表(正常还款确认)
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author baisong
     */

    public String updateInfoForNor(WmsFinaCrePeriodRepaySearchBeanVO bean, UserBean user);

    /**
     * 更新提前台账和贷款还款信息表(提前还款确认)
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author baisong
     */

    public String updateInfoForAdv(WmsFinaCreAdvanceBeanVO bean, UserBean user,
                                   WmsPostLoanWorkFlowBeanVO wmsPostLoanWorkFlowBeanVO);

    /**
     * 查询正常查询列表
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author baisong
     */
    public Map<String, Object> getListWithPagingfornormal(WmsFinaCreRepaySearchBeanVO queryInfo);

    /**
     * 正常还款导出excel
     * 
     * @param wms_cre_credit_head_id
     * @return Map<String,Object> baisong
     */
    public Map<String, Object> getListWithoutPagingfornormal(WmsFinaCreRepaySearchBeanVO queryInfo);

    /**
     * 逾期还款导出excel
     * 
     * @param wms_cre_credit_head_id
     * @return Map<String,Object> baisong
     */
    public Map<String, Object> getListWithoutPagingforyuqi(WmsFinaCreRepaySearchBeanVO queryInfo);

    /**
     * 实现贷款结清查询列表
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getListWithoutPagingForClear(WmsFinaCreRepaySearchBeanVO queryInfo);

    /**
     * 实现放款确认保存
     * 
     * @param user
     * @param bean
     * @param bean1
     * @param wms_cre_credit_head_id
     * @param taskId
     * @param cre_type
     * @return
     */
    public String periodAndCreRepaySave(UserBean user, Integer wms_cre_credit_head_id, String taskId, String cre_type, String edition_num) throws ParseException;
    /**
     * 实现放款确认保存--车贷
     * 
     * @param user
     * @param bean
     * @param bean1
     * @param wms_cre_credit_head_id
     * @param taskId
     * @param cre_type
     * @return
     */
    public String periodAndCreRepaySaveCar(UserBean user, Integer wms_cre_credit_head_id, String taskId, String cre_type)throws ParseException;
    
    /**
     * 实现放款确认保存--合同作废
     * 
     * @param user
     * @param bean
     * @param bean1
     * @param wms_cre_credit_head_id
     * @param taskId
     * @param cre_type
     * @return
     */
    public String periodAndCreRepaySaveCancel(UserBean user,WmsFinaCreCancelBeanVo bean, WmsCreCreditHead hbean,String cre_type)throws ParseException;
   
    /**
     * 实现放款确认保存--单据作废---车贷
     * 
     * @param user
     * @param bean
     * @param bean1
     * @param wms_cre_credit_head_id
     * @param taskId
     * @param cre_type
     * @return
     */
    public String carPeriodAndCreRepaySaveCancel(UserBean user,WmsFinaCreCancelBeanVo bean, WmsCreCreditHead hbean)throws ParseException;
   
    /**
     * 服务管理--贷后管理--贷后查询导出--电话催缴--excel
     * @param queryInfo
     * @param user
     * @return
     * @author baisong
     */
    public Map<String, Object> getPostLoanSearchWithoutPagingListCommissionerexcel(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user);
    /**
     * 服务管理--贷后管理--贷后查询导出--上门催缴--excel
     * @param queryInfo
     * @param user
     * @return
     * @author baisong
     */
    public Map<String, Object> getPostLoanSearchWithoutPagingListexcel(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user);
    /**
     * 服务管理--贷后管理--贷后查询-查看客户信息
     * @param wms_cre_credit_head_id
     * @param wms_fina_cre_pay_id
     * @return list
     * @author hancd
     */
    public Map<String, Object> getWmsFinaCreRepayBypk(UserBean user, Integer wms_cre_credit_head_id, Integer wms_fina_cre_pay_id);
    /**
     * 贷后专员查询贷款单据--电话催缴
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    public Map<String, Object> getListWithoutPagingCommissioner(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user);
    /**
     * 贷后专员查询贷款单据--上门催缴列表 
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    public Map<String, Object> postLoanVisitSearchWithPagingList(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user);
    /**
     * 贷后专员查询贷款单据--贷后专员分配
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    public Map<String, Object> getListWithoutPagingAllocation(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user);
    /**
     * 贷后专员查询贷款单据--贷后专员分配--导出excel--催缴分配
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    public Map<String, Object> getListWithoutAllocation(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user);
    /**
     * 清空数据库贷款还款表中的 贷后专员信息 id  name  deptid
     * @param wms_cre_credit_head_id
     * @return
     * baisong
     */
    public String udpatenull(Integer wms_fina_cre_pay_id);
    
    
    /**
     * 新增获取房贷债权详情接口
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    public Map<String, Object> getInfoforPTPhosue(String idList, UserBean user);
    /**
     * 新增获取信贷债权详情接口
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    public Map<String, Object> getInfoforPTP(String idList, UserBean user);
    

    /**
     * 终止合同审核列表
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    public Map<String, Object> getListWithPagingforstop(WmsFinaGetInfoStopBeanVO queryInfo);
    /**
     * 终止合同确认列表
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    public Map<String, Object> getListWithPagingforaffirm(WmsFinaGetInfoStopBeanVO queryInfo);
    /**
     * 终止合同审核列表excel
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    public Map<String, Object> getListWithPagingforstopexcel(WmsFinaGetInfoStopBeanVO queryInfo);
    /**
     * 终止合同确认列表excel
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    public Map<String, Object> getListWithPagingforaffirmexcel(WmsFinaGetInfoStopBeanVO queryInfo);
    /**
     * 终止合同审核 获取页面数据
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    public Map<String, Object> getInfoforstop(Integer wms_cre_credit_head_id);
    /**
     * 实现结清证明打印功能
     * @param wms_cre_credit_head_id
     * @return
     */
	public Map<String, Object> getWmsPostforClearById(
			Integer wms_cre_credit_head_id);
	  /**
     * 房贷单据作废
     * 
     * @param user
     * @param bean
     * @param bean1
     * @param wms_cre_credit_head_id
     * @param taskId
     * @param cre_type
     * @return
     */
    public String houseCancel(UserBean user,WmsFinaCreCancelBeanVo bean, WmsCreCreditHead hbean,String cre_type)throws ParseException;
   
    /**
     * 贷款还款信息表初始化数据
     * 
     * @param user
     * @param bean
     * @param wms_cre_credit_head_id
     * @param borrowProtocol
     * @param wmsCreCreditHead
     * @param wmsFinaCreLoanApp
     * @return
     */
    public WmsFinaCreRepay setWmsFinaCreRepay(UserBean user, int wms_cre_credit_head_id,
                                               WmsCreApproBorrowProtocol borrowProtocol,
                                               WmsCreCreditHead wmsCreCreditHead, WmsFinaCreLoanApp wmsFinaCreLoanApp,
                                               WmsCreApproServiceProtocol wmsCreApproServiceProtocol);
    
    /**
     * 期还款台账表初始化数据
     * 
     * @param user
     * @param bean1
     * @param wms_cre_credit_head_id
     * @param borrowProtocol
     * @param wmsFinaCreLoanApp
     * @param wmsCreCreditHead
     * @return
     * @throws ParseException 
     */
    public WmsFinaCrePeriodRepay setWmsFinaCrePeriodRepay(UserBean user, int wms_cre_credit_head_id,
                                                           WmsCreApproBorrowProtocol borrowProtocol,
                                                           WmsFinaCreLoanApp wmsFinaCreLoanApp,
                                                           WmsCreCreditHead wmsCreCreditHead, int i,
                                                           WmsFinaCreRepay bean);
    
    /**
     * @Title: setWmsFinaCreRealrepayInfo 
     * @Description: (初始化应还款信息表，并返回初始化插入信息条数) 
     * @param wms_cre_credit_head_id
     * @return    
     * @return int    返回类型
     * @throws
     * @author lvtu
     */
    public int setWmsFinaCreRealrepayInfo(int wms_cre_credit_head_id);
    
    /**
     * 
     * @Title: housingMakeLoansSaveForCobine
     * @Description: discrpition：放款申请组合贷保存
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2016年12月26日 下午5:03:03
     * history:
     * 1、2016年12月26日 wangyihan 创建方法
     */
    public WmsFinaCreLoanAppSearchBeanVO housingMakeLoansSaveForCobine(WmsFinaCreLoanAppSearchBeanVO queryInfo, UserBean user);
    
    /**
     * 
     * @Title: searchCombineLoanList
     * @Description: 查询组合贷单据
     * @param paramMap
     * @return 
     * @author: wangyihan
     * @time:2017年1月10日 下午2:03:30
     * history:
     * 1、2017年1月10日 wangyihan 创建方法
     */
    public WmsFinaCreLoanAppSearchBeanVO searchCombineLoanList(WmsFinaCreLoanAppSearchBeanVO queryInfo, UserBean user);
    
    /**
     * 
     * @Title: getListWithoutPagingForAdd
     * @Description: TODO(提前还款申请判断是否是组合贷)
     * @param queryInfo
     * @param request
     * @return 
     * @author: baisong
     * @time:2017年1月16日 上午10:42:52
     * history:
     * 1、2017年1月16日 baisong 创建方法
     */
    public Map<String, Object> getGroupTpye(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user);

    /**
     * @Title: houseToBack
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param user
     * @param bean
     * @param hbean
     * @param cre_type
     * @return 
     * @author: admin
     * @time:2017年2月28日 上午9:49:59
     * history:
     * 1、2017年2月28日 admin 创建方法
    */
    public String houseToBack(UserBean user, WmsFinaCreCancelBeanVo bean, WmsCreCreditHead hbean, String cre_type);
    
}