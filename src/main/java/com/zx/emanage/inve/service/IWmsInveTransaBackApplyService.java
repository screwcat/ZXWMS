package com.zx.emanage.inve.service;

import java.util.Map;



import com.zx.emanage.util.gen.entity.WmsInveTransaBackApply;
import com.zx.emanage.inve.vo.WmsInveTransaBackApplySearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveTransaSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveTransaBackApplyService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveTransaBackApplySearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveTransaBackApplySearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveTransaBackApplyVO
	 * @author auto_generator
	 */	
	public WmsInveTransaBackApply getInfoByPK(Integer wms_inve_transa_back_apply_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveTransaBackApply bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveTransaBackApply bean, UserBean user);
	
	/**
     * Description :退单申请页面初始化
     * @param paramMap user
     * @return Map
     * @author wangyihan
     */
    public Map<String, Object> singleApplicationDisp(Map<String, Object> paramMap, UserBean user);
    
    /**
     * Description: 退单申请页面保存
     * @param paramMap user
     * @return String
     * @author wangyihan
     */
    public String singleApplicationSave(WmsInveTransaBackApply bean, String taskId, UserBean user);
	
	/**
     * 获取退单确认列表数据
     * 
     * @param queryInfo
     * @return Map
     * @author wangyihan
     */
    public Map<String, Object> searchSingleConfirmationList(WmsInveTransaSearchBeanVO queryInfo, UserBean user);
    
    /**
     * 退单确认列表数据导出
     * 
     * @param queryInfo user
     * @return Map
     * @author wangyihan
     */
    public Map<String, Object> searchSingleConfirmationListForExport(WmsInveTransaSearchBeanVO queryInfo, UserBean user);
    
    /**
     * 退单确认页面获取退单详情数据(与退单申请为同一页面但数据来源不同)
     * 
     * @param queryInfo user
     * @return Map
     * @author wangyihan
     */
    public Map<String, Object> getBackApplyDataForConfirm(WmsInveTransaBackApplySearchBeanVO queryInfo, UserBean user);
    
    /**
     * 退单确认页面撤销退单
     * 
     * @param queryInfo user
     * @return String
     * @author wangyihan
     */
    public String revocationTransaBackApply(WmsInveTransaBackApplySearchBeanVO queryInfo, UserBean user);
    
    /**
     * 退单确认页面审核
     * 
     * @param queryInfo user
     * @return String
     * @author wangyihan
     */
    public String approvalTransaApply(WmsInveTransaBackApplySearchBeanVO queryInfo, UserBean user);
    
    /**
     * 获取退单退回列表数据
     * 
     * @param queryInfo user
     * @return Map
     * @author wangyihan
     */
    public Map<String, Object> searchTransaBackReturnList(WmsInveTransaSearchBeanVO queryInfo, UserBean user);
    
    /**
     * 退单退回列表数据导出
     * 
     * @param queryInfo user
     * @return Map
     * @author wangyihan
     */
    public Map<String, Object> searchTransaBackReturnListForExport(WmsInveTransaSearchBeanVO queryInfo, UserBean user);
	
}