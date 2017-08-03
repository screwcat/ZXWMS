package com.zx.emanage.inve.service;

import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveExpiredCustomerVo;
import com.zx.sframe.util.vo.UserBean;

/**
 * @ClassName IWmsInveExpiredCustomerService
 * @Description 内容摘要:到期客户一览表逻辑接口类
 * @author DongHao
 * @date 2016年11月24日
 * @version V1.0
 * @history 1. 2016年11月24日 DongHao 创建文件
 */
public interface IWmsInveExpiredCustomerService {
	
	/**
	 * @Title findExpiredCustomer
	 * @Description 内容摘要: 根据条件获取到期客户的数据
	 * @param user
	 *            登录用户对象
	 * @param wmsInveExpiredCustomerVo
	 *            查询条件对象
	 * @return 返回map集合
	 * @author DongHao
	 * @date 2016年11月24日
	 * @history 1. 2016年11月24日 DongHao 创建文件
	 */
	Map<String, Object> findExpiredCustomer(UserBean user, WmsInveExpiredCustomerVo wmsInveExpiredCustomerVo);

	/**
	 * @Title findExportExpiredCustomerExcel
	 * @Description 内容摘要: 根据条件获取到期客户的数据
	 * @param user
	 *            用户对象
	 * @param wmsInveExpiredCustomerVo
	 *            查询条件对象
	 * @return 返回map集合
	 * @author DongHao
	 * @date 2016年11月24日
	 * @history 1. 2016年11月24日 DongHao 创建文件
	 */
	Map<String, Object> findExportExpiredCustomerExcel(UserBean user, WmsInveExpiredCustomerVo wmsInveExpiredCustomerVo);

}
