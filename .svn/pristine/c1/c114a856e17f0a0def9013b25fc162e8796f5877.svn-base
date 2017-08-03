package com.zx.emanage.cusmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.cusmanage.vo.WmsCusCustomerLineHouseinfoSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCusCustomerLineHouseinfo;
import com.zx.emanage.util.gen.vo.WmsCusCustomerLineHouseinfoVO;

/*
 * @version 2.0
 */

public interface IWmsCusCustomerLineHouseinfoDao
{

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithoutPaging(WmsCusCustomerLineHouseinfoSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithPaging(WmsCusCustomerLineHouseinfoSearchBeanVO queryInfo);

    /**
     * Description :get record list count num by vo queryInfo
     * 
     * @param queryInfo
     * @return records count num
     * @author auto_generator
     */
    public int getListCountNum(WmsCusCustomerLineHouseinfoSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCusCustomerLineHouseinfoVO
     * @author auto_generator
     */
    public WmsCusCustomerLineHouseinfoVO getInfoByPK(Integer wms_cus_customer_line_houseinfo_id);

    /**
     * Description :insert a record, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int addNewRecord(WmsCusCustomerLineHouseinfo bean);

    /**
     * Description :update a record replace all, need primary key, support null
     * val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordAll(WmsCusCustomerLineHouseinfo bean);

    /**
     * Description :update a record replace columns, need primary key,
     * nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordCols(WmsCusCustomerLineHouseinfo bean);

    /**
     * Description :delete record by primary key
     * 
     * @param pk
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByPK(Integer wms_cus_customer_line_houseinfo_id);

    /**
     * Description :delete record by domain bean, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByDomain(WmsCusCustomerLineHouseinfo bean);

    /**
     * Description :get list by "and" method, need new
     * WmsCusCustomerLineHouseinfo() include query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return WmsCusCustomerLineHouseinfo list
     * @author auto_generator
     */
    public List<WmsCusCustomerLineHouseinfo> getSingleTableListByAndMethod(WmsCusCustomerLineHouseinfo queryInfo,
                                                                           Boolean isExcludePKFlag);

    /**
     * Description :get list by "or" method, need new
     * WmsCusCustomerLineHouseinfo() include query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return WmsCusCustomerLineHouseinfo list
     * @author auto_generator
     */
    public List<WmsCusCustomerLineHouseinfo> getSingleTableListByOrMethod(WmsCusCustomerLineHouseinfo queryInfo,
                                                                          Boolean isExcludePKFlag);
}