package com.zx.emanage.loanappro.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loanappro.vo.WmsSysPropertyPropertySearchBeanVO;
import com.zx.emanage.loanappro.vo.WmsSysPropertySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsSysProperty;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsSysPropertyService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsSysPropertySearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsSysPropertySearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsSysPropertyVO
     * @author auto_generator
     */
    public WmsSysProperty getInfoByPK(Integer wms_sys_property_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsSysProperty bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsSysProperty bean, UserBean user);

    /**
     * Description :查询属性 for 签合同
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    public Map<String, Object> searchforhouse(String idss, Integer wms_cre_credit_head_id);

    /**
     * 传入cre_type,cre_loan_type,region_number 的值要获取的属性表条件 贷款类型 贷款具体类型 地区编码
     * 
     * @param map
     * @return Map<String,String>
     * @author baisong
     */
    public Map<String, Object> getforNewProtocol(UserBean user, String protocol_type, Integer wms_cre_credit_head_id);
    
    /**
     * 签合同-出借人信息
     */
    public List<Map<String, Object>> searchLenderInfo(WmsSysPropertySearchBeanVO queryInfo, UserBean user);

	public Map<String, Object> getPerfectContract(UserBean user,WmsSysPropertyPropertySearchBeanVO queryInfo);
/**
 * 
 * 合同完善产品变动初始化信息
 * @param user
 * @param protocol_type
 * @param wms_cre_credit_head_id
 * @param borrow_deadline 
 * @return Map
 */
	public Map<String, Object> getCreLoanTypeChange(UserBean user,WmsSysPropertyPropertySearchBeanVO queryInfo);
/**
 * 
 * 获取合同利率
 * @param user
 * @param protocol_type
 * @param wms_cre_credit_head_id
 * @param borrow_deadline
 * @param appro_limit 
 * @return
 */
public Map<String, Object> getBorrowDeadlineChange(UserBean user, WmsSysPropertyPropertySearchBeanVO queryInfo);

/**
 * 贷款申请 贷款终审获取利率和期数等相关信息
 * @param queryInfo
 * @return record list
 * @author baisong
 */
public Map<String, Object> getprotocolProperty(UserBean user,WmsSysPropertyPropertySearchBeanVO bean);

/**
 * @Title: getPaymentContractType
 * @Description: TODO(还款方式查询)
 * @param cre_loan_type
 * @return 
 * @author: jiaodelong
 * @time:2016年12月29日 下午2:29:33
 * history:
 * 1、2016年12月29日 jiaodelong 创建方法
*/
public String getPaymentContractType(Integer cre_loan_type); 
}