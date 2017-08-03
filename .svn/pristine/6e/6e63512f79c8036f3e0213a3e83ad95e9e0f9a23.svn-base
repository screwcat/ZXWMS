package com.zx.emanage.inve.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.emanage.inve.vo.WmsInveCreditSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveCredit;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveCreditService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveCreditSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveCreditSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCreditVO
	 * @author auto_generator
	 */	
	public WmsInveCredit getInfoByPK(Integer wms_inve_credit_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveCredit bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveCredit bean, UserBean user);

    /**
     * @Title: importWmsInveCreditInfo
     * @Description: 债权导入并拆分
     * @param request
     * @param response
     * @param user
     * @return 
     * @author: Guanxu
     * @time:2016年12月14日 下午6:05:43
     * history:
     * 1、2016年12月14日 Guanxu 创建方法
    */
    public Map<String, Object> importWmsInveCreditInfo(HttpServletRequest request, HttpServletResponse response, UserBean user);

    /**
     * @Title: exportWmsInveCreditInfo
     * @Description: 债权拆分后导出
     * @return 
     * @author: Guanxu
     * @time:2016年12月15日 下午3:12:00
     * history:
     * 1、2016年12月15日 Guanxu 创建方法
    */
    public Map<String, Object> exportWmsInveCreditInfo();

    /**
     * @Title: searchSpecList
     * @Description:获取最新的拆分规则表
     * @param queryInfo
     * @return 
     * @author: Guanxu
     * @time:2016年12月20日 下午1:39:25
     * history:
     * 1、2016年12月20日 Guanxu 创建方法
    */
    List<Map<String, Object>> searchSpecList(WmsInveCreditSearchBeanVO queryInfo);
}