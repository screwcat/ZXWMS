package com.zx.emanage.cremanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineContactSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCreCustomerChangeLineContact;
import com.zx.emanage.util.gen.vo.WmsCreCustomerChangeLineContactVO;

/*
 * @version 2.0
 */

public interface IWmsCreCustomerChangeLineContactDao
{

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithoutPaging(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithPaging(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo);

    /**
     * Description :get record list count num by vo queryInfo
     * 
     * @param queryInfo
     * @return records count num
     * @author auto_generator
     */
    public int getListCountNum(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCustomerChangeLineContactVO
     * @author auto_generator
     */
    public WmsCreCustomerChangeLineContactVO getInfoByPK(Integer wms_cre_customer_change_line_contact_id);

    /**
     * Description :insert a record, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int addNewRecord(WmsCreCustomerChangeLineContact bean);

    /**
     * Description :update a record replace all, need primary key, support null
     * val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordAll(WmsCreCustomerChangeLineContact bean);

    /**
     * Description :update a record replace columns, need primary key,
     * nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordCols(WmsCreCustomerChangeLineContact bean);

    /**
     * Description :delete record by primary key
     * 
     * @param pk
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByPK(Integer wms_cre_customer_change_line_contact_id);

    /**
     * Description :delete record by domain bean, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByDomain(WmsCreCustomerChangeLineContact bean);

    /**
     * Description :get list by "and" method, need new
     * WmsCreCustomerChangeLineContact() include query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return WmsCreCustomerChangeLineContact list
     * @author auto_generator
     */
    public List<WmsCreCustomerChangeLineContact> getSingleTableListByAndMethod(WmsCreCustomerChangeLineContact queryInfo,
                                                                               Boolean isExcludePKFlag);

    /**
     * Description :get list by "or" method, need new
     * WmsCreCustomerChangeLineContact() include query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return WmsCreCustomerChangeLineContact list
     * @author auto_generator
     */
    public List<WmsCreCustomerChangeLineContact> getSingleTableListByOrMethod(WmsCreCustomerChangeLineContact queryInfo,
                                                                              Boolean isExcludePKFlag);

}