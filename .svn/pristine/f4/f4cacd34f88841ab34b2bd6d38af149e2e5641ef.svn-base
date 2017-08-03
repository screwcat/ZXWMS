package com.zx.emanage.cremanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.loanappro.vo.WmsCreCreditHeadVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreCreditHeadDao
 * 模块名称： 主表dao
 * @Description: 内容摘要：主表dao
 * @author baisong
 * @date 2016年12月29日
 * @version V1.0
 * history:
 * 1、2016年12月29日 baisong 创建文件
 */
@MyBatisRepository
public interface WmsCreCreditHeadDao extends BaseDao<WmsCreCreditHead>
{

    int saveByPk(WmsCreCreditHead mcch);

    List<Map<String, Object>> getListWithoutPaging(Map<String, Object> resMap);

    List<WmsCreCreditHead> getInfo(Map<String, Object> paramMap);
    
    List<Map<String, Object>> getFinalAuditAmount(Map<String, Object> paramMap);

    /**
     * 流水、信息�?电审、征信数据导�?
     * 
     * @param resMap
     * @return
     */
    List<Map<String, Object>> getTeamListWithoutPaging(Map<String, Object> resMap);

    /**
     * 房贷流水、办件数据导�?
     * 
     * @param resMap
     * @return
     */
    List<Map<String, Object>> getTeamListWithoutPagingForFd(Map<String, Object> resMap);

    /**
     * 审批查询数据导出
     * 
     * @param reMap
     * @return
     */
    List<Map<String, Object>> getCreditCheckListWithoutPaging(Map<String, Object> reMap);

    /**
     * 审批查询接口
     * 
     * @param resMap
     * @return
     */
    List<Map<String, Object>> getCreditCheckListWithPaging(Map<String, Object> resMap);

    /**
     * 查询审批查询总页�?
     * 
     * @param reMap
     * @return
     */
    int findCreditCheckCount(Map<String, Object> reMap);

    /**
     * 修改审批人主键�?审批人名称�?审批时间、审批结果字�?
     * 
     * @param params
     */
    public void updateRecord(Map<String, Object> params);

    /**
     * 暂存、重新提交时更新贷款主表
     * 
     * @param params
     */
    public void updateMCCHWhenUpdateOrReSub(Map<String, Object> params);

    /**
     * 修改审批人主键�?申请贷款金额 用于终审审批 面签修改申请金额 终审审批中修改申请金�?
     * 
     * @param params
     */
    public void updateLimit(Map<String, Object> params);

    /**
     * 贷前跟踪数据导出
     * 
     * @param reMap
     * @return
     */
    List<Map<String, Object>> getFollowCheckListWithoutPaging(Map<String, Object> reMap);

    /**
     * 房贷贷前跟踪数据导出
     * 
     * @param reMap
     * @return
     */
    List<Map<String, Object>> getFollowCheckListWithoutPagingForFd(Map<String, Object> reMap);
    /**
     * 车贷贷前跟踪数据导出
     * 
     * @param reMap
     * @return
     */
    List<Map<String, Object>> getFollowCheckListWithoutPagingForCar(Map<String, Object> reMap);

    /**
     * 贷前跟踪查询列表接口带分�?
     * 
     * @param resMap
     * @return
     */
    List<Map<String, Object>> getFollowCheckListWithPaging(Map<String, Object> resMap);

    /**
     * 查询贷前跟踪总页�?
     * 
     * @param resMap
     * @return
     */
    int findFollowCheckCount(Map<String, Object> resMap);

    /**
     * 贷前查询数据导出
     * 
     * @param resMap
     * @return
     */
    List<Map<String, Object>> getLoanCheckListWithoutPaging(Map<String, Object> resMap);

    /**
     * 贷前查询列表接口带分�?
     * 
     * @param resMap
     * @return
     */
    List<Map<String, Object>> getLoanCheckListWithPaging(Map<String, Object> resMap);

    /**
     * 查询贷前总页�?
     * 
     * @param resMap
     * @return
     */
    int findLoanCheckCount(Map<String, Object> resMap);

    /**
     * 判断四种审批状�?修改表单状�?
     * 
     * @param wms_cre_credit_head_id
     */
    WmsCreCreditHead findcheckcreditbillstaus(Integer wms_cre_credit_head_id);

    /**
     * 房贷贷前跟踪列表记录数查�?
     */
    int findCountForFd(Map<String, Object> paramMaps);

    /**
     * 房贷贷前跟踪列表查询
     */
    List<Map<String, Object>> searchForFd(Map<String, Object> parameters);
    /**
     * 车贷贷前跟踪列表查询
     */
    List<Map<String, Object>> searchForCar(Map<String, Object> parameters);
    /**
     * 才车贷贷前跟踪列表记录数查�?
     */
    int findCountForCar(Map<String, Object> paramMaps);
    /**
     * 房贷贷前跟踪列表记录数查�?
     */
    int findCountForFdForAdd(Map<String, Object> paramMaps);

    /**
     * 房贷贷前跟踪列表查询
     */
    List<Map<String, Object>> searchForFdForAdd(Map<String, Object> parameters);

    /**
     * 房贷合同自动带出�?查询
     */
    Map<String, Object> searchforborrow(Integer wms_cre_credit_head_id);

    /**
     * 贷款复核列表查询
     * 
     * @param resMap
     * @return
     */
    List<Map<String, Object>> getRecheckListWithPaging(Map<String, Object> resMap);

    /**
     * 贷款复核列表记录数查�?
     */
    int findCountForRecheck(Map<String, Object> paramMaps);

    /**
     * 贷款复核数据导出
     * 
     * @param resMap
     * @return
     */
    List<Map<String, Object>> getRecheckListWithoutPaging(Map<String, Object> resMap);

    /**
     * 贷前�?��列表查询
     * 
     * @param resMap
     * @return
     */
    List<Map<String, Object>> getLoanBeforeReturnListWithPaging(Map<String, Object> resMap);

    /**
     * 贷前�?��列表记录数查�?
     */
    int findCountForLoanBeforeReturn(Map<String, Object> paramMaps);

    /**
     * 更新四个模型计算结果
     */
    int updateModel(Map<String, Object> paramMaps);

    /**
     * 房贷完善信息列表
     * 
     * @param paramMap
     * @return zhubo
     */
    List<Map<String, Object>> recheckForHouse(Map<String, Object> paramMap);
    
    /**
     * 车贷完善信息列表
     * 
     * @param paramMap
     * @return zhubo
     */
    List<Map<String, Object>> recheckForCar(Map<String, Object> paramMap);
    
    List<Map<String, Object>> recheckForCar0(Map<String, Object> paramMap);

    /**
     * 房贷完善信息总数
     * 
     * @param resMap
     * @return zhubo
     */
    int findCountRecheck(Map<String, Object> resMap);

    /**
     * 房贷完善信息列表导出
     * 
     * @param paramMap
     * @return zhubo
     */
    List<Map<String, Object>> recheckForHouseWithout(Map<String, Object> paramMap);

    /**
     * 房贷办件列表查询
     */
    List<Map<String, Object>> searchForFdCardForAdd(Map<String, Object> paramMap);

    /**
     * 房贷办件列表记录数查�?
     */
    int findCountForFdCardForAdd(Map<String, Object> paramMap);
    
    /**
     * 房贷办件列表查询--完善
     */
    List<Map<String, Object>> searchForFdCardForAddMakeup(Map<String, Object> paramMap);

    /**
     * 房贷办件列表记录数查�?--完善
     */
    int findCountForFdCardForAddMakeup(Map<String, Object> paramMap);

    /**
     * 实现信贷复核查询列表
     * 
     * @param paramMap
     * @return
     * @author hancd
     */
    List<Map<String, Object>> searchforCreditCheck(Map<String, Object> paramMap);

    /**
     * 实现信贷复核查询数量
     * 
     * @param paramMap
     * @return
     * @author hancd
     */
    int findCountforCreditCheck(Map<String, Object> paramMap);

    /**
     * 实现验点数据导出
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getYdListWithoutPaging(Map<String, Object> paramMap);

    /**
     * 房贷办件数据导出
     * 
     * @param resMap
     * @return
     */
    List<Map<String, Object>> getCardListWithoutPagingForFd(Map<String, Object> resMap);

    /**
     * 复议修订信息导出
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getLoanReviewReturnListWithoutPaging(Map<String, Object> paramMap);

    /**
     * 复议修订信息列表分页
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getLoanReviewReturnListWithPaging(Map<String, Object> paramMap);

    /**
     * 复议修订信息总记录
     * 
     * @param paramMap
     * @return
     */
    int getLoanReviewReturnCount(Map<String, Object> paramMap);

    /**
     * 贷款查询 对草稿状态的数据进行软删除 即逻辑删除
     * 
     * @param paramMap
     * @return
     */
    int deleteByPK(Map<String, Object> paramMap);

    /**
     * 实现验点审批数据显示
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getYdListWithPaging(Map<String, Object> paramMap);

    /**
     * 实现验点审批数据数
     * 
     * @param paramMap
     * @return
     */
    int findCountforYdListWithPaging(Map<String, Object> paramMap);
    
    /**
     * 根据ID查询退单信息
     * 
     * @param params
     */
    public Map<String, Object> getWmsCreCredReturnInfoById(int wms_cre_credit_head_id);
    
    /**
     * @Title: getWmsCrecreditHeadInfoByCusid 
     * @Description: 查询客户的房贷信息（如果多条显示最后一条）
     * @param wms_cus_customer_id
     * @return    
     * @return Map<String,Object>    
     * @throws
     * @author lvtu 
     * @date 2015年7月2日 下午5:31:35
     */
	Map<String, Object> getWmsCrecreditHeadInfoByCusid(Integer wms_cus_customer_id);
	
	/**
	 * @Title: getWmsCusCreditHeadInfo 
	 * @Description: 根据ID查询贷款单信息
	 * @param wms_cre_credit_head_id
	 * @return    
	 * @return Map<String,Object>    
	 * @throws
	 * @author lvtu 
	 * @date 2015年7月4日 上午10:40:51
	 */
	Map<String, Object> getWmsCusCreditHeadInfo(Integer wms_cre_credit_head_id);
	
	/**
	 * @Title: updateApproResult 
	 * @Description: 更新审批结果展示 
	 * @param paramMap
	 * @return    
	 * @return int    
	 * @throws
	 * @author lvtu 
	 * @date 2015年7月7日 下午1:50:55
	 */
	public int updateApproResult(Map<String, Object> paramMap);
	
	
	 /**
     * 车贷评估列表查询
     */
    List<Map<String, Object>> searchForCdCardForAdd(Map<String, Object> paramMap);

    /**
     * 车贷评估列表记录数查�?
     */
    int findCountForCdCardForAdd(Map<String, Object> paramMap);
    /**
     * @Title: findCountRecheck0 
     * @Description: 信息复核数量
     * @param paramMap
     * @return    
     * @return int    
     * @throws
     * @author lvtu 
     * @date 2015年8月3日 下午2:00:08
     */
	int findCountRecheck0(Map<String, Object> paramMap);
	/**
	 * 更新  房贷新流程使用
	 * @param wmsCreCreditHead
	 * @return
	 * baisong
	 */
	 int updateforhouse(WmsCreCreditHead wmsCreCreditHead);
	/**
     * 房产初评列表查询
     */
    List<Map<String, Object>> housePreLiminaryAssessmentList(Map<String, Object> paramMap);
    
    /**
     * 房产初评列表查询
     */
    int housePreLiminaryAssessmentCount(Map<String, Object> paramMap);
    
    /**
     * @param paramMap 包含hprocess_time(区分时间点)与wms_cre_credit_head_id
     * 根据主键查询贷款主表(返回类型为map 包含新旧流程区分标识1:旧流程 2:新流程)
     */
    Map<String, Object> searchWmsCreCreditHeadByMap(Map<String, Object> paramMap);
    
    
    Map<String, Object> getHeadSalesman(Integer wms_cre_credit_head_id);
    
    void updateWmsCreCreditHead(WmsCreCreditHead wmsCreCreditHead);
    
    /**
     * 获取房贷单据信息完善列表
     */
    List<Map<String, Object>> getHouseLoanCompletedList(Map<String, Object> paramMap);
    
    /**
     * 获取房贷单据信息完善列表条数
     */
    int getHouseLoanCompletedCount(Map<String, Object> paramMap);
    
    /**
     * 作废相关字段更新
     */
    void updateForDocumentVoidUp(Map<String, Object> paramMap);
/**
 * 
 * 根据headID修改贷款主表单据状态
 * @param head
 */
	void updateBillStatus(WmsCreCreditHead head);
/**
 * 查询房产核查缴费列表
 * @param paramMap
 * @return
 */
List<Map<String, Object>> wmscrecustomerchangelinehouseinfowithpaginglist(Map<String, Object> paramMap);
/**
 * 
 * 查询房产核查缴费列表count
 * @param paramMap
 * @return
 */
int wmsCreCustomerChangeLineHouseInfoWithPagingCount(
		Map<String, Object> paramMap);

List<Map<String,Object>> wmscrecustomerchangelinehouseinfowithpaginglistout(Map<String, Object> paramMap);
/**
 * 
 * 复议申请列表查询
 * @param paramMap
 * @return
 */
List<Map<String, Object>> getReviewRevisionOfHousingList(
		Map<String, Object> paramMap);

/**
 * 
 * 复议申请列表导出
 * @param paramMap
 * @return
 */
List<Map<String, Object>> getReviewRevisionOfHousingListOut(
		Map<String, Object> paramMap);
/**
 * 
 * 复议申请列表数量
 * @param paramMap
 * @return
 */
int getReviewRevisionOfHousingListCount(Map<String, Object> paramMap);

/**
 * 根据人的id和菜单id获取当前人对当前菜单的权限
 * @param salesman_id
 * @param menu_id
 * baisong 2016-8-23
 */
Map<String, Object>	queryChildrenDeptInfo(Map<String, Object> paramMap);

    /**
     * 
     * @Title: getWmsCreCreditHeadList
     * @Description: 查询贷款主表信息
     * @param paramMap
     * @return 
     * @author: wangyihan
     * @time:2016年12月28日 上午10:41:39
     * history:
     * 1、2016年12月28日 wangyihan 创建方法
     */
    List<Map<String, Object>> getWmsCreCreditHeadList(Map<String, Object> paramMap);

    /**
     * @Title: updateGroupId
     * @Description: TODO(组合贷保存)
     * @param head 
     * @author: jiaodelong
     * @time:2016年12月28日 下午2:17:25
     * history:
     * 1、2016年12月28日 jiaodelong 创建方法
    */
    void updateGroupId(WmsCreCreditHead head);
    /**
     * 
     * @Title: getGroupHead
     * @Description: TODO(组合贷查询相关组合贷单据（状态一致的）)
     * @param wms_cre_credit_head_id
     * @return list
     * @author: baisong
     * @time:2016年12月28日 上午10:31:28
     * history:
     * 1、2016年12月28日 baisong 创建方法
     */
    List<WmsCreCreditHead> getGroupHead(Integer wms_cre_credit_head_id);

    /**
     * 
     * @Title: judgeGroupCredit
     * @Description: TODO(判断是否组合贷都打印了合同)
     * @param map
     * @return  List<WmsCreCreditHead>
     * @author: baisong
     * @time:2016年12月28日 上午11:07:38
     * history:
     * 1、2016年12月28日 baisong 创建方法
     */
    List<WmsCreCreditHead> judgeGroupCredit(Map<String, Object> map);

    /**
     * 
     * @Title: getGroupHeadByRepaymentType
     * @Description: (获取组合贷单据情况--返回还款方式)
     * @param wms_cre_credit_head_id
     * @return  List<Map<String, Object>>
     * @author: baisong
     * @time:2016年12月28日 上午11:07:38
     * history:
     * 1、2016年12月28日 baisong 创建方法
     */
    List<Map<String, Object>> getGroupHeadByRepaymentType(Integer wms_cre_credit_head_id);

    /**
     * 
     * @Title: getGroupHeadByRepaymentType
     * @Description: (获取组合贷单据情况--返回版本号)
     * @param map
     * @return  List<Map<String, Object>>
     * @author: baisong
     * @time:2016年12月28日 上午11:07:38
     * history:
     * 1、2016年12月28日 baisong 创建方法
     */
    List<Map<String, Object>> getGroupHeadByNotariza(Map<String, Object> map);

    /**
     * @Title: updateCombindeLoan
     * @Description: TODO(修改主表信息)
     * @param bean 
     * @author: jiaodelong
     * @time:2016年12月30日 下午6:03:34
     * history:
     * 1、2016年12月30日 jiaodelong 创建方法
    */
    Integer updateCombindeLoan(WmsCreCreditHeadVO bean);

    /**
     * @Title: getHeadIdListForHeadId
     * @Description: TODO(根据headId查询组合贷所有的headid)
     * @param wms_cre_credit_head_id
     * @return 
     * @author: jiaodelong
     * @time:2017年1月4日 下午1:00:57
     * history:
     * 1、2017年1月4日 jiaodelong 创建方法
    */
    List<Integer> getHeadIdListForHeadId(Integer wms_cre_credit_head_id);

    /**
     * @Title: updateGroupId
     * @Description: TODO(根据合同编号修改主表合同ID)
     * @param bill_code_group
     * @return 
     * @author: jiaodelong
     * @time:2017年1月6日 下午3:14:02
     * history:
     * 1、2017年1月6日 jiaodelong 创建方法
    */
    Integer updateGroupId(String bill_code_group);

    /**
     * 
     * @Title: getGroupHeadByRepaymentType
     * @Description: (获取组合贷单据情况--返回版本号)
     * @param map
     * @return  List<Map<String, Object>>
     * @author: baisong
     * @time:2016年12月28日 上午11:07:38
     * history:
     * 1、2016年12月28日 baisong 创建方法
     */
    List<Map<String, Object>> getGroupHeadByApp(Map<String, Object> map);

    /**
     * @Title: selectZSJE
     * @Description: TODO(查询终审金额)
     * @param integer
     * @return 
     * @author: jiaodelong
     * @time:2017年1月13日 下午6:59:29
     * history:
     * 1、2017年1月13日 jiaodelong 创建方法
    */
    WmsCreCreditHead selectZSJE(Integer integer);

    /**
     * @Title: getGroupIdForHeadId
     * @Description: TODO(根据主表ID查询组合贷ID)
     * @param wms_cre_credit_head_id
     * @return 
     * @author: jiaodelong
     * @time:2017年1月16日 上午9:54:27
     * history:
     * 1、2017年1月16日 jiaodelong 创建方法
    */
    Integer getGroupIdForHeadId(Integer wms_cre_credit_head_id);

    /**
     * @Title: getHeadIdList
     * @Description: TODO(根据主表ID查询组合贷ID)
     * @param wms_cre_credit_head_id
     * @return 
     * @author: jiaodelong
     * @time:2017年1月16日 上午10:34:31
     * history:
     * 1、2017年1月16日 jiaodelong 创建方法
    */
    List<Integer> getHeadIdList(Integer wms_cre_credit_head_id);

    /**
     * 
     * @Title: getGroupHeadByRepaymentType
     * @Description: (获取组合贷单据情况--返回版本号)
     * @param map
     * @return  List<Map<String, Object>>
     * @author: baisong
     * @time:2016年12月28日 上午11:07:38
     * history:
     * 1、2016年12月28日 baisong 创建方法
     */
    List<Map<String, Object>> getGroupType(Map<String, Object> map);

    /**
     * @Title: getApproLimitGroup
     * @Description: TODO(获取组合贷金额)
     * @param wms_cre_credit_head_id
     * @return 
     * @author: jiaodelong
     * @time:2017年1月18日 下午12:06:00
     * history:
     * 1、2017年1月18日 jiaodelong 创建方法
    */
    Map<String, Object> getApproLimitGroup(Integer wms_cre_credit_head_id);

    /**
     * @Title: getHeadIdListForHeadIdInAllStatus
     * @Description: TODO(根据headId查询所有组合贷ID)
     * @param wms_cre_credit_head_id
     * @return 
     * @author: jiaodelong
     * @time:2017年1月22日 上午11:41:05
     * history:
     * 1、2017年1月22日 jiaodelong 创建方法
    */
    List<Integer> getHeadIdListForHeadIdInAllStatus(Integer wms_cre_credit_head_id);

    /**
     * @Title: updateDelete
     * @Description: 贷款单据删除
     * @param wmscrecredithead 
     * @author: updateDelete
     * @time:2017年4月6日 下午2:39:25
     * history:
     * 1、2017年4月6日 baisong 创建方法
    */
    void updateDelete(WmsCreCreditHead wmscrecredithead);

    /**
     * @Title: updateLimitAll
     * @Description: 终审金额根据实际传递参数更改 传递值为null时 终审金额更改为null
     * @param infoMapAppro 
     * @author: baisong
     * @time:2017年5月18日 上午11:02:04
     * history:
     * 1、2017年5月18日 baisong 创建方法
    */
    void updateLimitAll(Map<String, Object> infoMapAppro);
    
    /**
     * 
     * @Title: updateApproUserId
     * @Description: 更新领取人
     * @param wmscrecredithead 
     * @author: ZhangWei
     * @time:2017年6月8日 上午11:22:32
     * history:
     * 1、2017年6月8日 ZhangWei 创建方法
     */
    void updateApproUserId(WmsCreCreditHead wmscrecredithead);
    
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
    List<Map<String, Object>> searchLoanNumberStatisticsList(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: searchLoanNumberStatisticsList
     * @Description: 查询件数统计列表数量
     * @param request
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2017年6月15日 下午1:15:55
     * history:
     * 1、2017年6月15日 wangyihan 创建方法
     */
    Integer searchLoanNumberStatisticsListCount(Map<String, Object> paramMap);
    

    /**
     * @Title: getStatusCount
     * @Description: 获取对应单据状态的数量 
     * @param paramMap
     * @return 
     * @author: baisong
     * @time:2017年6月13日 下午5:02:44
     * history:
     * 1、2017年6月13日 baisong 创建方法
    */
    List<Map<String, Object>> getStatusCount(Map<String, String> paramMap);
    
    /**
     * 
     * @Title: billStatusStaticsDisp
     * @Description: 上单、放款总件数、金额汇总
     * @param request
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2017年7月6日 下午1:15:55
     * history:
     * 1、2017年7月6日 wangyihan 创建方法
     */
    Map<String, Object> loansStatisticsAll(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: billStatusStaticsDisp
     * @Description: 单据状态统计
     * @param request
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2017年7月6日 下午1:15:55
     * history:
     * 1、2017年7月6日 wangyihan 创建方法
     */
    List<Map<String, Object>> loansStatistics(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: searchBillStatusStaticsByYear
     * @Description: 上单、放款数汇总(年)
     * @param request
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2017年7月6日 下午1:15:55
     * history:
     * 1、2017年7月6日 wangyihan 创建方法
     */
    List<Map<String, Object>> searchBillStatusStaticsByYear(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: searchBillStatusStaticsByMonth
     * @Description: 上单、放款数汇总(月)
     * @param request
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2017年7月6日 下午1:15:55
     * history:
     * 1、2017年7月6日 wangyihan 创建方法
     */
    List<Map<String, Object>> searchBillStatusStaticsByMonth(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: searchBillStatusStaticsByDay
     * @Description: 上单、放款数汇总(日)
     * @param request
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2017年7月6日 下午1:15:55
     * history:
     * 1、2017年7月6日 wangyihan 创建方法
     */
    List<Map<String, Object>> searchBillStatusStaticsByDay(Map<String, Object> paramMap);

    /**
     * @Title: getGroupIdForHeadIdStatus
     * @Description: 查询组合贷限定状态
     * @param map
     * @return 
     * @author: baisong
     * @time:2017年7月28日 下午5:06:30
     * history:
     * 1、2017年7月28日 baisong 创建方法
    */
    List<Map<String, Object>> getGroupIdForHeadIdStatus(Map<String, Object> map);

    /**
     * @Title: getGroupInfo
     * @Description: 查询组合贷限定状态
     * @param mapGroup
     * @return 
     * @author: baisong
     * @time:2017年7月28日 下午5:43:48
     * history:
     * 1、2017年7月28日 baisong 创建方法
    */
    List<Map<String, Object>> getGroupInfo(Map<String, Object> mapGroup);
}
