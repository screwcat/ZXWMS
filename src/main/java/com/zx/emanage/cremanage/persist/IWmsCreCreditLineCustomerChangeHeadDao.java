package com.zx.emanage.cremanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.cremanage.vo.WmsCreCreditLineCustomerChangeHeadSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCreCreditLineCustomerChangeHead;
import com.zx.emanage.util.gen.vo.WmsCreCreditLineCustomerChangeHeadVO;

/*
 * @version 2.0
 */

public interface IWmsCreCreditLineCustomerChangeHeadDao
{

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithoutPaging(WmsCreCreditLineCustomerChangeHeadSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithPaging(WmsCreCreditLineCustomerChangeHeadSearchBeanVO queryInfo);

    /**
     * Description :get record list count num by vo queryInfo
     * 
     * @param queryInfo
     * @return records count num
     * @author auto_generator
     */
    public int getListCountNum(WmsCreCreditLineCustomerChangeHeadSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineCustomerChangeHeadVO
     * @author auto_generator
     */
    public WmsCreCreditLineCustomerChangeHeadVO getInfoByPK(Integer wms_cre_credit_line_customer_change_head_id);

    /**
     * Description :insert a record, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int addNewRecord(WmsCreCreditLineCustomerChangeHead bean);

    /**
     * Description :update a record replace all, need primary key, support null
     * val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordAll(WmsCreCreditLineCustomerChangeHead bean);

    /**
     * Description :update a record replace columns, need primary key,
     * nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordCols(WmsCreCreditLineCustomerChangeHead bean);

    /**
     * Description :delete record by primary key
     * 
     * @param pk
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByPK(Integer wms_cre_credit_line_customer_change_head_id);

    /**
     * Description :delete record by domain bean, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByDomain(WmsCreCreditLineCustomerChangeHead bean);

    /**
     * Description :get list by "and" method, need new
     * WmsCreCreditLineCustomerChangeHead() include query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return WmsCreCreditLineCustomerChangeHead list
     * @author auto_generator
     */
    public List<WmsCreCreditLineCustomerChangeHead> getSingleTableListByAndMethod(WmsCreCreditLineCustomerChangeHead queryInfo,
                                                                                  Boolean isExcludePKFlag);

    /**
     * Description :get list by "or" method, need new
     * WmsCreCreditLineCustomerChangeHead() include query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return WmsCreCreditLineCustomerChangeHead list
     * @author auto_generator
     */
    public List<WmsCreCreditLineCustomerChangeHead> getSingleTableListByOrMethod(WmsCreCreditLineCustomerChangeHead queryInfo,
                                                                                 Boolean isExcludePKFlag);

    public Integer saveWithKey(WmsCreCreditLineCustomerChangeHead wmscrecreditlinecustomerchangehead);
}