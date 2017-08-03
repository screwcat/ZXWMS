package com.zx.emanage.loanappro.service;

import java.util.Map;

import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanappro.vo.WmsCreApproSearchBeanVO;
import com.zx.emanage.loanfina.vo.WmsFinaCreCancelBeanVo;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsFinaCreLoanApp;
import com.zx.emanage.util.gen.vo.WmsCreCreditHeadVO;
import com.zx.sframe.util.vo.UserBean;

public interface IWmsCreCreditApproService
{
    /**
     * 终审审批信息导出
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getLoanApproApproveListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 终审审批信息列表分页
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getLoanApproApproveListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 终审面签信息导出
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getLoanApproInterViewListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 终审面签信息列表分页
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getLoanApproInterViewListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 合同签订信息导出
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getAgreePrintLoanListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 合同签订信息列表分页
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getAgreePrintLoanListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 审批查询信息导出
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getLoanApproSearchListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 审批查询信息列表
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getLoanApproSearchListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 信贷放款申请数据导出
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getMakeloansListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 信贷放款审批数据导出
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getMakeloanApprovalListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 信贷放款申请信息列表
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getMakeLoansListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 放款确认数据导出
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getPaymentConfirmationListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 放款确认列表分页
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getPaymentConfirmationListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 终审管理--房产模块-- 终审审批信息列表分页
     * 
     * @param queryInfo
     * @return baisong
     */
    public Map<String, Object> getHouseLoanApproApproveListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 终审管理--房产模块--终审审批信息列表分页--数据导出
     * 
     * @param queryInfo
     * @return baisong
     */
    public Map<String, Object> getHouseLoanApproApproveListWithoutPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                         UserBean user);

    /**
     * 
     * @Title: getHouseLoanApproVisaListWithPaging
     * @Description: TODO(终审管理房贷终审终审面签)
     * @param queryInfo
     * @param user
     * @return 
     * @author: baisong
     * @time:2017年2月21日 下午1:28:28
     * history:
     * 1、2017年2月21日 baisong 创建方法
     */
    public Map<String, Object> getHouseLoanApproVisaListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 终审管理--房产模块--签合同列表页面
     * 
     * @param queryInfo
     * @return baisong
     */
    public Map<String, Object> getAgreeLoanApproApproveListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);
    /**
     * 终审管理--房产模块--补充合同列表
     * 
     * @param queryInfo
     * @return jiaodelong
     */
    
    public Map<String, Object> getAgreeLoanApproApproveList(WmsCreApproSearchBeanVO queryInfo, UserBean user);
    

    /**
     * 终审管理--房产模块--签合同列表页面--数据导出
     * 
     * @param queryInfo
     * @return baisong
     */
    public Map<String, Object> getAgreeLoanListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 公证列表信息导出
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getNotarizationListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 公证列表信息分页
     * 
     * @param queryInfo
     * @param user
     * @return
     */
    public Map<String, Object> getNotarizationListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 他项列表信息导出
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getOthersListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 他项列表分页
     * 
     * @param queryInfo
     * @param user
     * @return
     */
    public Map<String, Object> getOthersListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 房贷放款申请数据导出
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getHousingMakeloansListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);
    /**
     * 车贷放款申请数据导出
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getCarMakeloansListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 房贷放款申请信息列表
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getHousingMakeLoansListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);
    /**
     * 车贷放款申请信息列表
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getCarMakeLoansListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 获取放款申请信息
     * 
     * @param entity
     * @return
     */
    public Map<String, Object> getWmsFinaCreLoanAppByEntity(WmsFinaCreLoanApp entity);

    /**
     * 信贷放款审批信息列表
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getMakeLoanApprovalListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 信贷放款审批保存--合同作废
     * 
     * @param
     * @return
     */
    public String makeLoanApprovalSaveCancel(WmsCreditWorkFlowVO approveWorkFlowVO, WmsFinaCreCancelBeanVo bean, WmsCreCreditHead hbean,UserBean user);
    /**
     * 信贷放款审批保存
     * 
     * @param
     * @return
     */
    public String makeLoanApprovalSave(WmsCreditWorkFlowVO approveWorkFlowVO);
    /**
     * 房贷放款审批信息列表
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getHouseMakeLoanApprovalListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);
    /**
     * 车贷放款审批信息列表
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getCarMakeLoanApprovalListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 房贷放款审批保存
     * 
     * @param
     * @return
     */
    public String makeLoanApprovalSaveForFd(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, UserBean user);
    /**
     * 车贷放款审批保存
     * 
     * @param
     * @return
     * baisong
     */
    public String makeLoanApprovalSaveForCar(WmsCarLoanWorkFlowVO wVo);

    /**
     * 房贷放款审批信息列表
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getHouseMakeloanApprovalListWithoutPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                         UserBean user);

    /**
     * 车贷放款审批信息列表
     * 
     * @param queryInfo
     * @return
     */
    public Map<String, Object> getCarMakeloanApprovalListWithoutPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                         UserBean user);
    /**
     * 终审复议信息列表
     * 
     * @param queryInfo
     * @return
     * @author hancd
     */
    public Map<String, Object> getLoanReviewRevisionWithPagingList(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 终审复议信息导出
     * 
     * @param queryInfo
     * @return
     * @author hancd
     */
    public Map<String, Object> getLoanReviewRevisionWithOutPagingList(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * 终审复议信息申请
     * 
     * @param queryInfo
     * @param user
     * @return
     */
    public String toWmsReconsideration(WmsCreApproSearchBeanVO queryInfo, UserBean user, WmsCreditWorkFlowVO aWorkFlowVO);

    /**
     * 实现信贷合同签订单据作废操作
     * 
     * @param approveWorkFlowVO
     * @return
     */
    public String creCheckConcludeAndSignApprove(UserBean user,WmsCreditWorkFlowVO approveWorkFlowVO);

    /**
     * 实现信贷终审-初审面签列表显示 主要显示所有符合初审面签要求的单据进行审批
     * 
     * @param queryInfo
     * @param user
     * @return
     */
    public Map<String, Object> getLoanApproInitialInterViewWithPagingList(WmsCreApproSearchBeanVO queryInfo,
                                                                          UserBean user);

    /**
     * 实现信贷终审-初审面签列表导出 主要导出所有符合初审面签要求的单据进行导出
     * 
     * @param queryInfo
     * @param user
     * @return
     */
    public Map<String, Object> getLoanApproInitialInterViewWithoutPagingList(WmsCreApproSearchBeanVO queryInfo,
                                                                             UserBean user);

    /**
     * 实现信贷终审-初审面签审批功能
     * 
     * @param approveWorkFlowVO
     * @return
     */
    public String loanApproInitialInter(WmsCreditWorkFlowVO approveWorkFlowVO, UserBean user);

    /**
     * 终审管理--车模块-- 终审审批信息列表分页
     * 
     * @param queryInfo
     * @return baisong
     */
    public Map<String, Object> getCarLoanApproApproveListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);
    /**
     * 终审管理--车模块-- 终审审批信息列表分页--导出excel
     * 
     * @param queryInfo
     * @return baisong
     */
    public Map<String, Object> getCarLoanApproApproveListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);
    /**
     * 终审管理--车贷模块--签合同列表页面
     * 
     * @param queryInfo
     * @return baisong
     */
    public Map<String, Object> getAgreeLoanApproApproveListWithPagingCar(WmsCreApproSearchBeanVO queryInfo, UserBean user);
    /**
     * 终审管理--车贷模块--签合同列表页面--数据导出
     * 
     * @param queryInfo
     * @return baisong
     */
    public Map<String, Object> getAgreeLoanListWithoutPagingCar(WmsCreApproSearchBeanVO queryInfo, UserBean user);
    /**
     * @Title:giveInfoToPTP
     * @description:
     * 传递客户信息给PTP
     * @param map
     * @param personnel_id
     * @return String
     * @author yangqiyu
     * @date 2015年9月30日 上午10:23
     */
    public String giveInfoToPTP(Map<String,Object> map,Integer personnel_id);
    /**
     * WMS对MOA接口，放款审批
     * @param userName 
     * 
     * @return jiaodelong
     */
	public void sendResultsLoanApprovalUp(String pass, String advice,
			Integer wms_cre_credit_head_id,Integer userId, String userName);
/**
 * 复议列表查询
 * @param queryInfo
 * @return
 */
	public Map<String, Object> reviewRevisionOfHousingList(
			WmsCreCreditHeadVO queryInfo);
	
	/**
	 * 复议列表导出
	 * @param queryInfo
	 * @return
	 */
		public Map<String, Object> reviewRevisionOfHousingListOut(
				WmsCreCreditHeadVO queryInfo);

    /**
     * @Title: getSupplementAgreeLoanListWithoutPaging
     * @Description: TODO(合同补充导出)
     * @param queryInfo
     * @param user
     * @return 
     * @author: jiaodelong
     * @time:2016年12月20日 下午2:45:11
     * history:
     * 1、2016年12月20日 jiaodelong 创建方法
    */
    public Map<String, Object> getSupplementAgreeLoanListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * @Title: getcombinedLoanList
     * @Description: TODO(组合贷获取列表信息)
     * @param queryInfo
     * @param user
     * @return 
     * @author: jiaodelong
     * @time:2016年12月27日 下午1:59:03
     * history:
     * 1、2016年12月27日 jiaodelong 创建方法
    */
    public Map<String, Object> getcombinedLoanList(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * @Title: getcombinedLoanListForIdCard
     * @Description: TODO(根据身份证号组合贷获取列表信息)
     * @param queryInfo
     * @param user
     * @return 
     * @author: jiaodelong
     * @time:2016年12月27日 下午4:56:21
     * history:
     * 1、2016年12月27日 jiaodelong 创建方法
    */
    public Map<String, Object> getcombinedLoanListForIdCard(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * @Title: getcombinedLoanListAll
     * @Description: TODO(获取历史数据列表不含分页)
     * @param queryInfo
     * @param user
     * @return 
     * @author: jiaodelong
     * @time:2017年1月3日 下午3:22:47
     * history:
     * 1、2017年1月3日 jiaodelong 创建方法
    */
    public Map<String, Object> getcombinedLoanListAll(WmsCreApproSearchBeanVO queryInfo, UserBean user);

    /**
     * @Title: sendResultsLoanApprovalUpAgain
     * @Description: TODO(放款审批接口)
     * @param pass
     * @param advice
     * @param group_list
     * @param userId
     * @param userName 
     * @author: jiaodelong
     * @time:2017年1月12日 下午3:22:00
     * history:
     * 1、2017年1月12日 jiaodelong 创建方法
    */
    public void sendResultsLoanApprovalUpAgain(String pass, String advice, String group_list, Integer userId, String userName);
}
