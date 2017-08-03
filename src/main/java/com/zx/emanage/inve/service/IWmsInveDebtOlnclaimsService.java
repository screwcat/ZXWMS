package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveDebtOlnclaims;
import com.zx.emanage.inve.vo.WmsInveDebtOlnclaimsSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveDebtOlnclaimsService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveDebtOlnclaimsSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveDebtOlnclaimsSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveDebtOlnclaimsVO
	 * @author auto_generator
	 */	
	public WmsInveDebtOlnclaims getInfoByPK(Integer wms_inve_debt_olnclaims_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveDebtOlnclaims bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveDebtOlnclaims bean, UserBean user);
	
	/**
	 * @Title: changeCreditor 
	 * @Description: 债权调整
	 * @param transaMatch
	 * @param transaMatchNew
	 * @param wms_inve_debt_head_id
	 * @param taskId
	 * @param user
	 * @return String 
	 * @throws
	 * @author lvtu 
	 * @date 2015年10月27日 下午1:54:35
	 */
	public String changeCreditorSave(String transaMatch, String transMatchOld, String transaMatchNew, 
            Integer wms_inve_debt_head_id, String taskId, String surplus,
            String wms_inve_transa_id, String wms_inve_transa_prod_id, UserBean user);
	/**
	 * 将所有原债权信息插入到债权调整申请债权信息表
	 * @param List<WmsInveDebtOlnclaims>
	 * @return String
	 * @author yangqiyu
	 */
	String savelist(List<WmsInveDebtOlnclaims> list,UserBean user);
	
	/**
     * @Title: selectOlnclaimsByWmsInveDebtHeadId 
     * @Description: 初始化根据债权调整主表id查询债权调整表信息
     * @param paramMap
     * @return List<Map<String, Object>> 
     * @throws
     * @author wangihan 
     * @date 2015年11月26日
     */
    public Map<String, Object> selectOlnclaimsByWmsInveDebtHeadId(WmsInveDebtOlnclaimsSearchBeanVO queryInfo, UserBean user);
	
	/**
     * @Title: changeCreditorTempSave 
     * @Description: 债权调整暂存
     * @param changeCreditorVO user
     * @return String 
     * @throws
     * @author wangihan 
     * @date 2015年11月26日
     */
    public String changeCreditorTempSave(String transaMatch, String transMatchOld, String transaMatchNew, 
            Integer wms_inve_debt_head_id, String taskId, String surplus,
            String wms_inve_transa_id, String wms_inve_transa_prod_id, UserBean user);
	
}