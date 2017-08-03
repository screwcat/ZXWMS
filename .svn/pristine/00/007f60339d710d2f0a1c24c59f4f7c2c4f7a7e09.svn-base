package com.zx.emanage.sysmanage.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.internal.LinkedTreeMap;
import com.zx.emanage.sysmanage.vo.CrmCustomerInfoSearchBeanVO;
import com.zx.emanage.util.gen.entity.CrmCustomerInfo;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface ICrmCustomerInfoService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(CrmCustomerInfoSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(CrmCustomerInfoSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return CrmCustomerInfoVO
	 * @author auto_generator
	 */	
	public CrmCustomerInfo getInfoByPK(Integer costomer_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(CrmCustomerInfo bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(CrmCustomerInfo bean, UserBean user);
    /**
     * 通过传递参数调用平台接口 获取客户信息
     * @param bean
     * @return
     */
    public Map<String, Object> getInfoByBean(CrmCustomerInfo bean);
    /**
     * Description :通过查询条件查询相对应的显示信息
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     */
	public Map<String, Object> getsearchKHlist(
			CrmCustomerInfoSearchBeanVO queryInfo);
	
	/**
	 * 根据传入的参数获得客户是否存在理财单据信息(状态不是1.草稿，6.已完成，7.已终止）。
	 * 
	 * @param pm_personnel_id
	 *            原客户经理Id
	 * @param cus_ids
	 *            迁移客户集合（多个用户使用逗号分割）
	 * @return
	 * @author jinzhm
	 */
	public Map<String, Object> getCustomerDataMoa(String pm_personnel_id,
			String cus_ids);
	
	/**
	 * 接口：crm端审核通过客户迁移申请时，调用此接口。
	 * 根据原客户经理id和迁移客户集合查询上单信息(状态不是1.草稿，6.已完成，7.已终止），并修改上单信息的归属业务员为新客户经理Id（包括其他上级经理及部门）。
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
	 * 接口：crm端查看客户的存量信息。<br/>
	 * 当传入客户id是多个的时候，返回多个客户的存量信息；当传入客户id是空的时候，返回所有客户存量信息。
	 * @param cus_ids 客户集合，多个客户间用逗号隔开；或者是空；
	 * @return {ret_code: 000,ret_msg: '操作成功',data_list:[{customer_id:'1',stock_amount:'100000'}]}
	 * @author jinzhm
	 */
	public Map<String, Object> getCustomerStockMoa(String cus_ids);

    /**
     * @Title: getBankName
     * @Description: 根据银行卡号获得银行名称
     * @param card_no 银行卡号
     * @return 银行名称
     * @author: jinzhm
     * @throws UnsupportedEncodingException 
     * @throws IOException 
     * @throws ClientProtocolException 
     * @time:2017年2月28日 上午10:05:00
     * history:
     * 1、2017年2月28日 jinzhm 创建方法
     */
    public Map<String, Object> getBankName(String card_no) throws UnsupportedEncodingException,
                                                          ClientProtocolException, IOException;
}