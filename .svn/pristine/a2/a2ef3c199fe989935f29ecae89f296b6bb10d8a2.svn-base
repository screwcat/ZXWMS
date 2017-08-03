package com.zx.emanage.loanappro.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsCrePersonnelBillChange;
import com.zx.emanage.loanappro.vo.WmsCrePersonnelBillChangeSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCrePersonnelBillChangeService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsCrePersonnelBillChangeSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsCrePersonnelBillChangeSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCrePersonnelBillChangeVO
	 * @author auto_generator
	 */	
	public WmsCrePersonnelBillChange getInfoByPK(Integer wms_cre_personnel_bill_change_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsCrePersonnelBillChange bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsCrePersonnelBillChange bean, UserBean user);

    /**
     * @Title: updateLastUpdateTime
     * @Description: TODO(更新最后修改时间)
     * @param user_id
     * @return String
     * @author: jiaodelong
     * @time:2017年3月22日 下午1:40:03
     * history:
     * 1、2017年3月22日 jiaodelong 创建方法
    */
    public String updateLastUpdateTime(Integer user_id);
}