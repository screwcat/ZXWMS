package com.zx.emanage.cremanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineHouseinfoSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCreCustomerChangeLineHouseinfo;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.vo.WmsCreCreditHeadVO;
import com.zx.emanage.util.gen.vo.WmsCreCustomerChangeLineHouseinfoVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCustomerChangeLineHouseinfoService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreCustomerChangeLineHouseinfoSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     * @param user 
     */
    public Map<String, Object> getListWithPaging(WmsCreCreditHeadVO queryInfo, UserBean user);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCustomerChangeLineHouseinfoVO
     * @author auto_generator
     */
    public WmsCreCustomerChangeLineHouseinfoVO getInfoByPK(Integer wms_cre_customer_change_line_houseinfo_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreCustomerChangeLineHouseinfo bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreCustomerChangeLineHouseinfo bean, UserBean user);

    /**
     * Description :delete method
     * 
     * @param pk
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String delete(WmsCreCustomerChangeLineHouseinfo bean);
/**
 * 
 *房产缴费核查导出
 * @param queryInfo
 * @param user
 * @return
 */
	public Map<String, Object> getListWithPagingout(
			WmsCreCreditHeadVO queryInfo, UserBean user);
}