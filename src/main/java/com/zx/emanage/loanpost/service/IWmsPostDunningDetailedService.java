package com.zx.emanage.loanpost.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsPostDunningDetailed;
import com.zx.emanage.loanpost.vo.WmsPostDunningDetailedSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsPostDunningDetailedService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsPostDunningDetailedSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsPostDunningDetailedSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsPostDunningDetailedVO
	 * @author auto_generator
	 */	
	public WmsPostDunningDetailed getInfoByPK(Integer wms_post_dunning_detailed_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsPostDunningDetailed bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsPostDunningDetailed bean, UserBean user);
	/**
     * Description :通过催缴单主键获取相关明细表信息
     * @param wms_post_dunning_head_id
     * @return map
     * @author hancd
     */
    public List<WmsPostDunningDetailed> getWmsPostDunningDetailedInfoByPK(Integer wms_post_dunning_head_id);
	/***
	 * 查询催缴详情
	 * @param queryInfo
	 * @param isExcludePKFlag
	 * @return
	 */
	public List<WmsPostDunningDetailed> getListByEntity(WmsPostDunningDetailed queryInfo, Boolean isExcludePKFlag);
}