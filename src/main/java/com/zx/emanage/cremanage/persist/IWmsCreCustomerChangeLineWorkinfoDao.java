package com.zx.emanage.cremanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineWorkinfoSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCreCustomerChangeLineWorkinfo;
import com.zx.emanage.util.gen.vo.WmsCreCustomerChangeLineWorkinfoVO;

/*
 * @version 2.0
 */

public interface IWmsCreCustomerChangeLineWorkinfoDao
{

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithoutPaging(WmsCreCustomerChangeLineWorkinfoSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithPaging(WmsCreCustomerChangeLineWorkinfoSearchBeanVO queryInfo);

    /**
     * Description :get record list count num by vo queryInfo
     * 
     * @param queryInfo
     * @return records count num
     * @author auto_generator
     */
    public int getListCountNum(WmsCreCustomerChangeLineWorkinfoSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCustomerChangeLineWorkinfoVO
     * @author auto_generator
     */
    public WmsCreCustomerChangeLineWorkinfoVO getInfoByPK(Integer wms_cre_customer_change_line_workinfo_id);

    /**
     * Description :insert a record, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int addNewRecord(WmsCreCustomerChangeLineWorkinfo bean);

    /**
     * Description :update a record replace all, need primary key, support null
     * val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordAll(WmsCreCustomerChangeLineWorkinfo bean);

    /**
     * Description :update a record replace columns, need primary key,
     * nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordCols(WmsCreCustomerChangeLineWorkinfo bean);

    /**
     * Description :delete record by primary key
     * 
     * @param pk
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByPK(Integer wms_cre_customer_change_line_workinfo_id);

    /**
     * Description :delete record by domain bean, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByDomain(WmsCreCustomerChangeLineWorkinfo bean);

    /**
     * Description :get list by "and" method, need new
     * WmsCreCustomerChangeLineWorkinfo() include query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return WmsCreCustomerChangeLineWorkinfo list
     * @author auto_generator
     */
    public List<WmsCreCustomerChangeLineWorkinfo> getSingleTableListByAndMethod(WmsCreCustomerChangeLineWorkinfo queryInfo,
                                                                                Boolean isExcludePKFlag);

    /**
     * Description :get list by "or" method, need new
     * WmsCreCustomerChangeLineWorkinfo() include query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return WmsCreCustomerChangeLineWorkinfo list
     * @author auto_generator
     */
    public List<WmsCreCustomerChangeLineWorkinfo> getSingleTableListByOrMethod(WmsCreCustomerChangeLineWorkinfo queryInfo,
                                                                               Boolean isExcludePKFlag);
}