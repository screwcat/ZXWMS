package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.SysDeptData;
import com.zx.emanage.util.gen.entity.WmsInveCompay;
import com.zx.emanage.inve.vo.WmsInveCompaySearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveCompayService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveCompaySearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveCompaySearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCompayVO
	 * @author auto_generator
	 */	
	public WmsInveCompay getInfoByPK(Integer wms_inve_compay_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveCompay bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveCompay bean, UserBean user);
	  /**
     * 提供所有公司ID,获取相对应的公司ID以及公司名称
     * @param isEmpty 代表"请选择"
     * @param isAll 代表 "全部"
     * @return
     */
    public List<WmsInveCompay> getCompayListList(String isEmpty, String isAll);
}