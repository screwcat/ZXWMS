package com.zx.emanage.cremanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineHouseinfoSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCreCustomerChangeLineHouseinfo;
import com.zx.emanage.util.gen.vo.WmsCreCustomerChangeLineHouseinfoVO;

/*
 * @version 2.0
 */

public interface IWmsCreCustomerChangeLineHouseinfoDao
{

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithoutPaging(WmsCreCustomerChangeLineHouseinfoSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithPaging(WmsCreCustomerChangeLineHouseinfoSearchBeanVO queryInfo);

    /**
     * Description :get record list count num by vo queryInfo
     * 
     * @param queryInfo
     * @return records count num
     * @author auto_generator
     */
    public int getListCountNum(WmsCreCustomerChangeLineHouseinfoSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCustomerChangeLineHouseinfoVO
     * @author auto_generator
     */
    public WmsCreCustomerChangeLineHouseinfoVO getInfoByPK(Integer wms_cre_customer_change_line_houseinfo_id);

    /**
     * Description :insert a record, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int addNewRecord(WmsCreCustomerChangeLineHouseinfo bean);

    /**
     * Description :update a record replace all, need primary key, support null
     * val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordAll(WmsCreCustomerChangeLineHouseinfo bean);

    /**
     * Description :update a record replace columns, need primary key,
     * nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordCols(WmsCreCustomerChangeLineHouseinfo bean);

    /**
     * Description :delete record by primary key
     * 
     * @param pk
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByPK(Integer wms_cre_customer_change_line_houseinfo_id);

    /**
     * Description :delete record by domain bean, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByDomain(WmsCreCustomerChangeLineHouseinfo bean);

    /**
     * Description :get list by "and" method, need new
     * WmsCreCustomerChangeLineHouseinfo() include query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return WmsCreCustomerChangeLineHouseinfo list
     * @author auto_generator
     */
    public List<WmsCreCustomerChangeLineHouseinfo> getSingleTableListByAndMethod(WmsCreCustomerChangeLineHouseinfo queryInfo,
                                                                                 Boolean isExcludePKFlag);

    /**
     * Description :get list by "or" method, need new
     * WmsCreCustomerChangeLineHouseinfo() include query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return WmsCreCustomerChangeLineHouseinfo list
     * @author auto_generator
     */
    public List<WmsCreCustomerChangeLineHouseinfo> getSingleTableListByOrMethod(WmsCreCustomerChangeLineHouseinfo queryInfo,
                                                                                Boolean isExcludePKFlag);
}