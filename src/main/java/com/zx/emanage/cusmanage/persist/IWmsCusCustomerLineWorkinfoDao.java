package com.zx.emanage.cusmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.cusmanage.vo.WmsCusCustomerLineWorkinfoSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCusCustomerLineWorkinfo;
import com.zx.emanage.util.gen.vo.WmsCusCustomerLineWorkinfoVO;

/*
 * @version 2.0
 */

public interface IWmsCusCustomerLineWorkinfoDao
{

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithoutPaging(WmsCusCustomerLineWorkinfoSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithPaging(WmsCusCustomerLineWorkinfoSearchBeanVO queryInfo);

    /**
     * Description :get record list count num by vo queryInfo
     * 
     * @param queryInfo
     * @return records count num
     * @author auto_generator
     */
    public int getListCountNum(WmsCusCustomerLineWorkinfoSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCusCustomerLineWorkinfoVO
     * @author auto_generator
     */
    public WmsCusCustomerLineWorkinfoVO getInfoByPK(Integer wms_cus_customer_line_workinfo_id);

    /**
     * Description :insert a record, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int addNewRecord(WmsCusCustomerLineWorkinfo bean);

    /**
     * Description :update a record replace all, need primary key, support null
     * val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordAll(WmsCusCustomerLineWorkinfo bean);

    /**
     * Description :update a record replace columns, need primary key,
     * nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordCols(WmsCusCustomerLineWorkinfo bean);

    /**
     * Description :delete record by primary key
     * 
     * @param pk
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByPK(Integer wms_cus_customer_line_workinfo_id);

    /**
     * Description :delete record by domain bean, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByDomain(WmsCusCustomerLineWorkinfo bean);

    /**
     * Description :get list by "and" method, need new
     * WmsCusCustomerLineWorkinfo() include query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return WmsCusCustomerLineWorkinfo list
     * @author auto_generator
     */
    public List<WmsCusCustomerLineWorkinfo> getSingleTableListByAndMethod(WmsCusCustomerLineWorkinfo queryInfo,
                                                                          Boolean isExcludePKFlag);

    /**
     * Description :get list by "or" method, need new
     * WmsCusCustomerLineWorkinfo() include query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return WmsCusCustomerLineWorkinfo list
     * @author auto_generator
     */
    public List<WmsCusCustomerLineWorkinfo> getSingleTableListByOrMethod(WmsCusCustomerLineWorkinfo queryInfo,
                                                                         Boolean isExcludePKFlag);
}