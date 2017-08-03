package com.zx.emanage.telUserLoanInfo.service;

import java.util.Map;

import com.zx.emanage.telUserLoanInfo.vo.TelUserInfoBeanVO;



public interface ItelUserLoanInfoService {
	
	/**
	 * 根据用户的客户姓名 身份证号码  手机号码查询用户借贷信息
	 * @param map
	 * @return
	 * baisong
	 */
	public Map<String ,Object> getInfo(TelUserInfoBeanVO telUserInfoBeanVO);
	
	/***
	 * 根据客户名称 身份证 联系电话 查询借贷情况--金额统计
	 * @param map
	 * @return Map
	 * baisong
	 */
	public Map<String ,Object> searchisum(TelUserInfoBeanVO telUserInfoBeanVO);
	
	/***
	 * 根据客户名称 身份证 联系电话 查询借贷情况--还款中单据数量
	 * @param map
	 * @return Map
	 * baisong
	 */
	public Map<String ,Object> searchnum(TelUserInfoBeanVO telUserInfoBeanVO);
	/***
	 * 根据客户名称 身份证 联系电话 查询借贷情况--期还款台账
	 * @param map
	 * @return Map
	 * baisong
	 */
	public Map<String, Object> searchPeriodRepay(TelUserInfoBeanVO telUserInfoBeanVO) ;
}