package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.util.gen.entity.WmsInveDebtHead;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.emanage.inve.vo.WmsInveDebtHeadSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveDebtHeadService {
	/**
	 * Description :获取债权调整协议打印 确认 导出
	 * @param queryInfo
	 * @return record list
	 * @author lvtu
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveDebtHeadSearchBeanVO queryInfo,UserBean user);
	
	/**
	 * Description :债权变动确认
	 * @param queryInfo
	 * @return record list
	 * @author lvtu
	 */
	public Map<String, Object> getListWithPaging(WmsInveDebtHeadSearchBeanVO queryInfo,UserBean user);	

	/**
	 * Description :协议打印列表
	 * @param queryInfo
	 * @return record list
	 * @author jiaodelong
	 */
	public Map<String, Object> getListPrintProtocol(WmsInveDebtHeadSearchBeanVO queryInfo,UserBean user);	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveDebtHeadVO
	 * @author auto_generator
	 */	
	public WmsInveDebtHead getInfoByPK(Integer wms_inve_debtadjust_head_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveDebtHead bean, UserBean user,String beanJSON);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveDebtHead bean, UserBean user);
	/**
	 * Description :根据上单产品id获取债权匹配信息
	 * @param wms_inve_transa_prod_id
	 * @return  Map<String,Object>
	 * @author yangqiyu
	 */
	public Map<String,Object> getMatchInfoByProd(Integer wms_inve_transa_prod_id,UserBean user);
	/**
	 * 通过传递参数查询债权变更审核页面数据
	 * @param queryInfo
	 * @param user
	 * @return Map<String,Object>
	 * @author yangqiyu
	 */
	public Map<String, Object> getAllFromWmsInveDebtHead(WmsInveDebtHeadSearchBeanVO queryInfo, UserBean user);
	/**
	 * 通过传递参数查询待修订的数据
	 * @param queryInfo
	 * @param user
	 * @return Map<String,Object>
	 * @author yangqiyu
	 */
	public Map<String,Object> getUpdateWmsInveDebtHead(WmsInveDebtHeadSearchBeanVO queryInfo, UserBean user);
	/**
	 * 通过传递参数查询债权变更审核页面导出数据
	 * @param queryInfo
	 * @param user
	 * @return Map<String,Object>
	 * @author yangqiyu
	 */
	
	public Map<String, Object> getAllWithoutWmsInveDebtHead(WmsInveDebtHeadSearchBeanVO queryInfo, UserBean user);
	/**
	 * 将变更修订内容导出到Excel
	 * @param Map<String,Object>
	 * @return Map<String,Object>
	 * @author yangqiyu
	 */
	public Map<String, Object> getAllFromWmsInveDebtHeadToExcel(WmsInveDebtHeadSearchBeanVO queryInfo,UserBean user);
	/**
	 * 【债权变更修订】取消修订
	 * @param String
	 * @author yangqiyu
	 */
	public String giveUpWmsInveDebtHead(String wms_inve_debt_head_id,String taskId,UserBean user);
	/**
	 * 【债权调整修订】 提交
	 * @param WmsInveDebtHead
	 * @param user
	 * @author yangqiyu
	 */
	public String updateClaims(WmsInveDebtHead wmsInveDebtHead,UserBean user,String taskId);
	/**
	 * 流程债权变更审批 
	 * @param UserBean WmsInveDebtWorkFlowVO
	 * @return "success" or "error"
	 * @author yangqiyu
	 */
	public String approvalOpinion(WmsInveDebtWorkFlowVO wmsInveDebtWorkFlowVO,UserBean user);

	/**
	 * @Title: specialApprovalSave 
	 * @Description: 债权变动、匹配、打印协议
	 * @param bean
	 * @param user
	 * @return String 
	 * @throws
	 * @author lvtu 
	 * @date 2015年10月19日 下午4:28:08
	 */
	public String approvalSave( WmsInveTransaProtocol Protocolbean,WmsInveDebtHead bean, UserBean user, String type,String taskId);
	/**
	 * @Title:getDebtInfoList
	 * @Description:财务管理-理财债权变动-债权变动审核审批页面债权信息展示
	 * @param wms_inve_debt_head_id
	 * @param user
	 * @return
	 * @author yangqiyu
	 * @date 2015年10月28日 下午17:24
	 */
	public Map<String, Object> getDebtInfoList(Integer wms_inve_debt_head_id,
			UserBean user,String olnclaims);
	/**
	 * 查询待修订数据导出到Excel
	 * @param queryInfo
	 * @param user
	 * @return Map<String,Object>
	 * @author yangqiyu
	 */
	public Map<String, Object> getUpdateWmsInveDebtHeadExcel(WmsInveDebtHeadSearchBeanVO queryInfo, UserBean user);
	/**
	 * 实现查看债权调整流程历程
	 * @param wms_inve_debt_head_id
	 * @return
	 */
	public Map<String, Object> getApprovalProcessView(String wms_inve_debt_head_id);
	/**
	 * 获取【债权变动申请】列表数据显示
	 * @param queryInfo
	 * @param user
	 * @return
	 */
	public Map<String, Object> getDebtInfoWithPagingList(
			WmsInveDebtHeadSearchBeanVO queryInfo, UserBean user);
	/**
	 * Description :获取【债权变动申请】列表数据导出
	 * @param queryInfo
	 * @return Map
	 * @author yangqiyu
	 */
	public Map<String, Object> getDebtInfoWithoutPagingList(
			WmsInveDebtHeadSearchBeanVO queryInfo, UserBean user);
	/**
	 * Description :债权调整/协议签订 列表导出
	 * @param queryInfo
	 * @return record list
	 * @author jiaodelong
	 * @date 2015年11月27日 下午 15:52
	 */
	public Map<String, Object> getWithoutListPrintProtocol(
			WmsInveDebtHeadSearchBeanVO queryInfo, UserBean user);
}