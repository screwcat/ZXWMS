package com.zx.emanage.loancheck.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loancheck.vo.WmsCreCreditLineHouseRecordSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineHouseRecord;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCreditLineHouseRecordService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineHouseRecordSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreCreditLineHouseRecordSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineHouseRecordVO
     * @author auto_generator
     */
    public WmsCreCreditLineHouseRecord getInfoByPK(Integer wms_cre_credit_line_house_record_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreCreditLineHouseRecord bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreCreditLineHouseRecord bean, UserBean user);

    /**
     * * 通过主表单ID，获取对应的档案调动记录
     * 
     * @param wms_cre_credit_head_id
     * @return
     */
    public WmsCreCreditLineHouseRecord getInfoByFK(Integer wms_cre_credit_head_id);
}