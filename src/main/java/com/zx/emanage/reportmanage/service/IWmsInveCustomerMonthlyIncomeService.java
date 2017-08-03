package com.zx.emanage.reportmanage.service;

import java.util.Map;

import com.zx.emanage.reportmanage.vo.WmsInveCustomerMonthlyIncomeBeanVo;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2015，卓信金控
 * 文件名称: IWmsInveCustomerMonthlyIncomeService.java
 * 系统名称：WMS
 * 模块名称：理财客户每月收益报表
 * 完成日期：
 * 作    者：
 * 内容摘要：
 * 
 * 文件调用：
 * 修改记录：
 * 修改时间：
 * 修 改 人: 
 * 修改内容：
 * 关联BUG：
 * 修改方法：
 */
public interface IWmsInveCustomerMonthlyIncomeService {

	Map<String, Object> getMapInfo(WmsInveCustomerMonthlyIncomeBeanVo queryInfo,UserBean user);

	Map<String, Object> getMapInfoListWithoutPaging(WmsInveCustomerMonthlyIncomeBeanVo queryInfo);

	Map<String, Object> geToalCountInfo(
			WmsInveCustomerMonthlyIncomeBeanVo queryInfo);

	Map<String,Object> getInveCustomerMonthlyIncomeListDetailInfo(
			WmsInveCustomerMonthlyIncomeBeanVo queryInfo);
}
