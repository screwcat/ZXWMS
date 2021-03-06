package com.zx.emanage.loanappro.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanappro.vo.WmsCreApproBorrowProtocolSearchBeanVO;
import com.zx.emanage.loanappro.vo.WmsCreApproBorrowProtocolSearchConditionBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreApproBorrowProtocol;
import com.zx.emanage.util.gen.entity.WmsCreApproProtocolSecured;
import com.zx.emanage.util.gen.entity.WmsCreApproServiceProtocol;
import com.zx.emanage.util.gen.entity.WmsCrePersonnelInfo;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreApproBorrowProtocolService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreApproBorrowProtocolSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreApproBorrowProtocolSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreApproBorrowProtocolVO
     * @author auto_generator
     */
    public WmsCreApproBorrowProtocol getInfoByPK(Integer wms_cre_appro_id);

    /**
     * 借款合同信息保存
     * 
     * @param
     * @return
     * @author 张风山
     */
    public String save(WmsCreApproBorrowProtocol bean, UserBean user);

    /**
     * 借款合同信息保存
     * 
     * @param
     * @return
     * @author 张风山
     */
    public String saveBorrow(WmsCreApproBorrowProtocol bean, UserBean user,
                             WmsCreApproServiceProtocol wmsCreApproServiceProtocol,
                             WmsCreditWorkFlowVO approveWorkFlowVO);
    /**
     * 借款合同信息保存--车贷
     * 
     * @param
     * @return
     * @author baisong
     */
    public String saveBorrowCar(WmsCreApproBorrowProtocol bean, UserBean user,
                             WmsCreApproServiceProtocol wmsCreApproServiceProtocol,
                             WmsCarLoanWorkFlowVO wVo);
    /**
     * 借款合同信息更新
     * 
     * @param
     * @return
     * @author 张风山
     */
    public String update(WmsCreApproBorrowProtocol bean, UserBean user,
                         WmsCreApproServiceProtocol wmsCreApproServiceProtocol, WmsCreditWorkFlowVO approveWorkFlowVO);

    /**
     * 根据贷款id查询对应的借款合同的信息
     * 
     * @param
     * @return Map
     * @author 张风山
     */
    public Map<String, Object> selectAllByWmsCreCreditHeadId(String wms_cre_credit_head_id, UserBean user);

    /**
     * 根据合同编号查询对应的借款合同的信息
     * 
     * @param
     * @return Map
     * @author 张风山
     */
    public Map<String, Object> selectAllByProtocolId(String judgeString, UserBean user);

    /**
     * 借款合同信息查询--房产终审--签合同
     * 
     * @param
     * @return
     * @author baisong
     */
    public Map<String, Object> searchobjforhouse(Integer wms_cre_credit_head_id);
    /**
     * -车贷--放款申请
     * 
     * @param
     * @return
     * @author baisong
     */
    public Map<String, Object> searchobjforCar(Integer wms_cre_credit_head_id);

    /**
     * 通过wms_cre_credit_head_id获取相应合同信息
     * 
     * @param wms_cre_credit_head_id
     * @return WmsCreApproBorrowProtocol
     * @author zhubo
     */
    public WmsCreApproBorrowProtocol getBorrowProtocolByFK(Integer wms_cre_credit_head_id);
    /**
     * Description :通过wms_cre_credit_head_id获取对应的合同信息
     * 
     * @param primary wms_cre_credit_head_id
     * @return int
     * @author baisong
     */
    public int getNumberByFK(Integer wms_cre_credit_head_id);

    /**
     * 借款合同信息保存--房产终审--签合同
     * 
     * @param
     * @return
     * @author baisong
     */
    public String saveforhouse(WmsCreApproBorrowProtocol bean, UserBean user, WmsCrePersonnelInfo person,
                               WmsCreApproServiceProtocol service, String person_name1, String person_identity_id1,
                               String person_name2, String person_identity_id2,
                               WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, String tablevalue, String comnumval,
                               WmsCreApproProtocolSecured wmsCreApproProtocolSecured);
    
    
    /**
	   * 债权转让及三方服务协议书下载
	   */

	public Map<String, Object> creditExport(HttpServletRequest request, HttpServletResponse response,WmsCreApproBorrowProtocolSearchBeanVO beanVO) ;
  /**
   * 借款合同信息查询--ireport模板使用
   * 
   * @param
   * @return
   * @author baisong
   * @date 20160831
   */
	public Map<String, Object> searchinfoforhouse(Integer wms_cre_credit_head_id);
	
/**
 * 
 * 合同信息完善
 * @param user
 * @param vo
 * @return
 * @author jiaodelong
 */
	public String perfectContractSave(UserBean user,WmsCreApproBorrowProtocolSearchBeanVO vo);
	/**
	 * 
	 * 获取合同表是否保存过
	 * @param wms_cre_credit_head_id
	 * @param cre_loan_type 
	 * @return
	 */
	public Map<String, Object> wmsPerfectContractCount(Integer wms_cre_credit_head_id);
	
	/**
	 * 
	 * 获取合同表是否保存过
	 * @param UserBean user, WmsCreApproBorrowProtocolSearchBeanVO vo
	 * @return 
	 */
	public Map<String, Object> getApproLimit(UserBean user, WmsCreApproBorrowProtocolSearchBeanVO vo);
	
	/**
	 * 查询合同列表
	 * @param queryInfo
	 * @return 
	 */
	Map<String, Object> getProtocolList(UserBean user, WmsCreApproBorrowProtocolSearchConditionBeanVO queryInfo);
	
	/**
	 * 导出合同相关excel表格
	 * @param response , search_params 
	 * @return 
	 */
	void protocolExportExcel(UserBean user, HttpServletResponse response,String search_params);
	
	/**
	 * 
	 * @Title: getBorrowProtocolDetails
	 * @Description: TODO(根据单据主表id获取合同信息)
	 * @param wms_cre_credit_head_id
	 * @return 
	 * @author: handongchun
	 * @time:2017年3月27日 下午2:25:11
	 * history:
	 * 1、2017年3月27日 handongchun 创建方法
	 */
	Map<String, Object> getBorrowProtocolDetails(UserBean user, int wms_cre_credit_head_id);

	/**
	 * 
	 * @Title: findPreviousBorrowProtocol
	 * @Description: TODO(根据手动输入的还款用户名和债务人身份证号匹配合同)
	 * @param map
	 * @return 
	 * @author: handongchun
	 * @time:2017年5月23日 下午1:26:28
	 * history:
	 * 1、2017年5月23日 handongchun 创建方法
	 */
    Map<String, String> findPreviousBorrowProtocol(Map<String,String> map);

    /**
     * @Title: validateProtocolIdYearNum
     * @Description: TODO(验证合同编号是否重复)
     * @param user
     * @param protocol_id_year_num
     * @return 
     * @author: handongchun
     * @param bill_status 
     * @time:2017年7月20日 上午11:14:00
     * history:
     * 1、2017年7月20日 handongchun 创建方法
    */
    String validateProtocolIdYearNum(UserBean user, int wms_cre_credit_head_id,String protocol_id_year_num, String bill_status);
}
