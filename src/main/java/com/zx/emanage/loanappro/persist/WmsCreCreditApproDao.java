package com.zx.emanage.loanappro.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsFinaCreLoanApp;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreCreditApproDao extends BaseDao<WmsCreCreditHead>
{
    /**
     * 终审审批信息导出
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getLoanApproApproveListWithoutPaging(Map<String, Object> paramMap);

    /**
     * 终审审批信息列表分页
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getLoanApproApproveListWithPaging(Map<String, Object> paramMap);

    /**
     * 终审审批信息总记录
     * 
     * @param paramMap
     * @return
     */
    int getLoanApproApproveCount(Map<String, Object> paramMap);

    /**
     * 终审面签信息导出
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getLoanApproInterViewListWithoutPaging(Map<String, Object> paramMap);

    /**
     * 终审面签信息列表分页
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getLoanApproInterViewListWithPaging(Map<String, Object> paramMap);

    /**
     * 终审面签信息总记录
     * 
     * @param paramMap
     * @return
     */
    int getLoanApproInterViewCount(Map<String, Object> paramMap);

    /**
     * 合同签订信息导出
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getAgreePrintLoanListWithoutPaging(Map<String, Object> paramMap);

    /**
     * 合同签订信息列表分页
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getAgreePrintLoanListWithPaging(Map<String, Object> paramMap);

    /**
     * 合同签订信息总记录数
     * 
     * @param paramMap
     * @return
     */
    int getAgreePrintLoanCount(Map<String, Object> paramMap);

    /**
     * 终审审批查询信息导出
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getLoanApproSearchListWithoutPaging(Map<String, Object> paramMap);

    /**
     * 终审审批查询信息列表带分页
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getLoanApproSearchListWithPaging(Map<String, Object> paramMap);

    /**
     * 终审审批查询信息总记录
     * 
     * @param paramMap
     * @return
     */
    int getLoanApproSearchCount(Map<String, Object> paramMap);

    /**
     * 放款申请数据导出
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getMakeLoansListWithoutPaging(Map<String, Object> paramMap);

    /**
     * 放款申请列表
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getMakeLoansListWithPaging(Map<String, Object> paramMap);

    /**
     * 放款申请总记录数
     * 
     * @param paramMap
     * @return
     */
    int getMakeLoansCount(Map<String, Object> paramMap);

    /**
     * 放款确认列表信息导出
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getPaymentConfirmationListWithoutPaging(Map<String, Object> paramMap);

    /**
     * 放款确认列表分页
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getPaymentConfirmationListWithPaging(Map<String, Object> paramMap);

    /**
     * 放款确认总页数
     * 
     * @param paramMap
     * @return
     */
    int getPaymentConfirmationCount(Map<String, Object> paramMap);

    /**
     * 公证列表分页
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getNotarizationListWithPaging(Map<String, Object> paramMap);

    /**
     * 公证列表数据导出
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getNotarizationListWithoutPaging(Map<String, Object> paramMap);

    /**
     * 公证列表总记录
     * 
     * @param paramMap
     * @return
     */
    int getNotarizationCount(Map<String, Object> paramMap);

    /**
     * 他项列表分页
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getOthersListWithPaging(Map<String, Object> paramMap);

    /**
     * 他项列表信息导出
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getOthersListWithoutPaging(Map<String, Object> paramMap);

    /**
     * 他项列表总记录数
     * 
     * @param paramMap
     * @return
     */
    int getOthersCount(Map<String, Object> paramMap);

    /**
     * 房贷放款申请数据导出
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getHousingMakeLoansListWithoutPaging(Map<String, Object> paramMap);
    /**
     * 车贷放款申请数据导出
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getCaringMakeLoansListWithoutPaging(Map<String, Object> paramMap);
    /**
     * 车贷放款申请数据导出
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getCarMakeLoansListWithoutPaging(Map<String, Object> paramMap);

    /**
     * 房贷放款申请列表
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getHousingMakeLoansListWithPaging(Map<String, Object> paramMap);
    /**
     * 车贷放款申请列表
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getCaringMakeLoansListWithPaging(Map<String, Object> paramMap);
    /**
     * 车贷放款申请列表
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getCarMakeLoansListWithPaging(Map<String, Object> paramMap);

    /**
     * 房贷放款申请总记录数
     * 
     * @param paramMap
     * @return
     */
    int getHousingMakeLoansCount(Map<String, Object> paramMap);
    /**
     * 车贷放款申请总记录数
     * 
     * @param paramMap
     * @return
     */
    int getCaringMakeLoansCount(Map<String, Object> paramMap);
    /**
     * 车贷放款申请总记录数
     * 
     * @param paramMap
     * @return
     */
    int getCarMakeLoansCount(Map<String, Object> paramMap);

    /**
     * 终审审批信息列表分页----房产终审--签合同
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> searchforhouse(Map<String, Object> paramMap);

    /**
     * 终审审批信息总记录---房产终审--签合同
     * 
     * @param paramMap
     * @return
     */
    int findCountforhouse(Map<String, Object> paramMap);

    /**
     * 获取放款申请信息
     * 
     * @param entity
     * @return
     */
    Map<String, Object> getWmsFinaCreLoanAppByEntity(WmsFinaCreLoanApp entity);

    /**
     * 获取符合复议条件的所有单据信息
     * 
     * @param paramMap
     * @return
     * @author hancd
     */
    List<Map<String, Object>> getLoanReviewRevision(Map<String, Object> paramMap);

    /**
     * 获取符合复议条件的所有单据数量
     * 
     * @param paramMap
     * @return
     * @author hancd
     */
    int getLoanReviewRevisionCount(Map<String, Object> paramMap);

    /**
     * 获取符合复议条件的所有单据进行导出
     * 
     * @param paramMap
     * @return
     * @author hancd
     */
    List<Map<String, Object>> getLoanReviewRevisionWithOutPagingList(Map<String, Object> paramMap);

    /**
     * 终审审批信息列表分页----房产终审--签合同
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> searchforhouseprotocol(Map<String, Object> paramMap);
    
    /**
     * 终审审批信息列表分页----房产终审--补签合同
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> searchforhouseprotocolList(Map<String, Object> paramMap);

    /**
     * 终审审批信息总记录---房产终审--签合同
     * 
     * @param paramMap
     * @return
     */
    int findCountforhouseProtocol(Map<String, Object> paramMap);

    /**
     * 信贷 初审面签 查询符合条件数据
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> searchForcsyd(Map<String, Object> paramMap);

    /**
     * 信贷 初审面签 查询符合条件数据总数
     * 
     * @param paramMap
     * @return
     */
    int findCountForcsyd(Map<String, Object> paramMap);

    /**
     * 信贷 初审面签 导出所有符合条件数据
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getLoanApproInitialInterViewWithoutPagingList(Map<String, Object> paramMap);
    /**
     * 终审审批信息列表分页----车终审
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> searchforcar(Map<String, Object> paramMap);

    /**
     * 终审审批信息总记录---车终审
     * 
     * @param paramMap
     * @return
     */
    int findCountforcar(Map<String, Object> paramMap);
    /**
     * 终审审批信息列表分页----车贷终审--签合同
     * 
     * @param paramMap
     */
    List<Map<String, Object>> searchforcarprotocol(Map<String, Object> paramMap);

    /**
     * 终审审批信息总记录---车贷终审--签合同
     * 
     * @param paramMap
     * @return
     */
    int findCountforcarProtocol(Map<String, Object> paramMap);
    
    /**
     * 获取放款申请客户姓名,电话1
     * 
     * @param paramMap
     * @return  list
     */
    List<Map<String,Object>> getNameAndPhone1(Map<String,Object> paramMap);
    /**
     * 获取放款申请客户姓名,电话1
     * 
     * @param paramMap
     * @return  list
     */
    List<Map<String,Object>> getNameAndPhone2(Map<String,Object> paramMap);
    /**
     *获取放款申请客户联系人姓名电话 
     * @param paramMap
     * return list
     */
    List<Map<String,Object>> getNameAndPhone3(Map<String,Object> paramMap);

	int findCountforhouseprotocolList(Map<String, Object> paramMap);

	 List<Map<String,Object>> searchforhouseprotocolPageOut(Map<String, Object> paramMap);

    /**
     * @Title: getcombinedLoanList
     * @Description: TODO(组合贷列表查询)
     * @param paramMap
     * @return 
     * @author: jiaodelong
     * @time:2016年12月27日 下午2:12:31
     * history:
     * 1、2016年12月27日 jiaodelong 创建方法
    */
    List<Map<String, Object>> getcombinedLoanList(Map<String, Object> paramMap);

    /**
     * @Title: findCountforgetcombinedLoan
     * @Description: TODO(组合贷列表数量查询)
     * @param paramMap
     * @return 
     * @author: jiaodelong
     * @time:2016年12月27日 下午2:12:48
     * history:
     * 1、2016年12月27日 jiaodelong 创建方法
    */
    int findCountforgetcombinedLoan(Map<String, Object> paramMap);

    /**
     * @Title: getcombinedLoanListForIdCard
     * @Description: TODO(根据身份证号组合贷获取列表信息)
     * @param paramMap
     * @return 
     * @author: jiaodelong
     * @time:2016年12月27日 下午4:58:43
     * history:
     * 1、2016年12月27日 jiaodelong 创建方法
    */
    List<Map<String, Object>> getcombinedLoanListForIdCard(Map<String, Object> paramMap);

    /**
     * @Title: findCountForGetCombinedLoanListForIdCard
     * @Description: TODO(根据身份证号组合贷获取列表数量)
     * @param paramMap
     * @return 
     * @author: jiaodelong
     * @time:2016年12月27日 下午4:58:52
     * history:
     * 1、2016年12月27日 jiaodelong 创建方法
    */
    int findCountForGetCombinedLoanListForIdCard(Map<String, Object> paramMap);

    /**
     * @Title: getcombinedLoanListAll
     * @Description: TODO(列表查询)
     * @param paramMap
     * @return 
     * @author: jiaodelong
     * @time:2017年1月3日 下午3:32:03
     * history:
     * 1、2017年1月3日 jiaodelong 创建方法
    */
    List<Map<String, Object>> getcombinedLoanListAll(Map<String, Object> paramMap);

    /**
     * @Title: findCountforgetcombinedLoanAll
     * @Description: TODO(列表查询总数)
     * @param paramMap
     * @return 
     * @author: jiaodelong
     * @time:2017年1月3日 下午3:32:35
     * history:
     * 1、2017年1月3日 jiaodelong 创建方法
    */
    int findCountforgetcombinedLoanAll(Map<String, Object> paramMap);

    /**
     * @Title: searchforhousevisa
     * @Description: TODO(终审面签-房贷列表)
     * @param paramMap
     * @return 
     * @author: baisong
     * @time:2017年2月21日 下午1:38:33
     * history:
     * 1、2017年2月21日 baisong 创建方法
    */
    List<Map<String, Object>> searchforhousevisa(Map<String, Object> paramMap);

    /**
     * @Title: findCountforhousevisa
     * @Description: TODO(终审面签*-房贷-总数)
     * @param paramMap
     * @return 
     * @author: baisong
     * @time:2017年2月21日 下午1:38:56
     * history:
     * 1、2017年2月21日 baisong 创建方法
    */
    int findCountforhousevisa(Map<String, Object> paramMap);
}
