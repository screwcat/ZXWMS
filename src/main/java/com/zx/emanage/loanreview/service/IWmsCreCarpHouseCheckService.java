package com.zx.emanage.loanreview.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsCreCarpHouseAssessment;
import com.zx.emanage.util.gen.entity.WmsCreCarpHouseCheck;
import com.zx.emanage.util.gen.entity.WmsCreHousingAssessment;
import com.zx.emanage.util.gen.entity.WmsCreHousingCheck;
import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanreview.vo.WmsCreCarpHouseCheckSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCarpHouseCheckService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsCreCarpHouseCheckSearchBeanVO queryInfo,UserBean user);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsCreCarpHouseCheckSearchBeanVO queryInfo,UserBean user);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCarpHouseCheckVO
	 * @author auto_generator
	 */	
	public WmsCreCarpHouseCheck getInfoByPK(Integer wms_cre_carp_house_check_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsCreCarpHouseCheck bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsCreCarpHouseCheck bean, UserBean user);
	
	  /**
     * Description :add method--验房单保存
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author baisong
     */
    public String save(WmsCreCarpHouseCheck bean, WmsCreCarpHouseAssessment wmsCreCarpHouseAssessment, UserBean user,
    		WmsCarLoanWorkFlowVO wVo, String fileArr);
    
    /**
     * Description :get 验房单获取信息
     * 
     * @param primary key
     * @return WmsCreHousingCheckVO
     * @author baisong
     */
    public WmsCreCarpHouseCheck getInfo(Integer wms_cre_housing_check_id);
}