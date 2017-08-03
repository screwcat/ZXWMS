package com.zx.emanage.cusmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.cusmanage.vo.WmsCusCustomerHeadSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCusCustomerHead;
import com.zx.emanage.util.gen.vo.WmsCusCustomerHeadVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCusCustomerHeadDao
{

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithoutPaging(WmsCusCustomerHeadSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithPaging(WmsCusCustomerHeadSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    // public List<Map<String, Object>>
    // getListforAddWithPaging(WmsCusCustomerHeadSearchBeanVO queryInfo,Integer
    // userId);

    /**
     * Description :get record list count num by vo queryInfo
     * 
     * @param queryInfo
     * @return records count num
     * @author auto_generator
     */
    public int getListCountNum(WmsCusCustomerHeadSearchBeanVO queryInfo);

    /**
     * Description :get record list count num by vo queryInfo or user
     * 
     * @param queryInfo
     * @param user
     * @return records count num
     * @author auto_generator
     */
    public int getListforAddCountNum(WmsCusCustomerHeadSearchBeanVO queryInfo, Integer userId);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCusCustomerHeadVO
     * @author auto_generator
     */
    public WmsCusCustomerHeadVO getInfoByPK(Integer wms_cus_customer_id);

    /**
     * Description :insert a record, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int addNewRecord(WmsCusCustomerHead bean);

    /**
     * Description :update a record replace all, need primary key, support null
     * val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordAll(WmsCusCustomerHead bean);

    /**
     * Description :update a record replace columns, need primary key,
     * nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordCols(WmsCusCustomerHead bean);

    /**
     * Description :delete record by primary key
     * 
     * @param pk
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByPK(Integer wms_cus_customer_id);

    /**
     * Description :delete record by domain bean, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByDomain(WmsCusCustomerHead bean);

    /**
     * Description :get list by "and" method, need new WmsCusCustomerHead()
     * include query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return WmsCusCustomerHead list
     * @author auto_generator
     */
    public List<WmsCusCustomerHead> getSingleTableListByAndMethod(WmsCusCustomerHead queryInfo, Boolean isExcludePKFlag);

    /**
     * Description :get list by "or" method, need new WmsCusCustomerHead()
     * include query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return WmsCusCustomerHead list
     * @author auto_generator
     */
    public List<WmsCusCustomerHead> getSingleTableListByOrMethod(WmsCusCustomerHead queryInfo, Boolean isExcludePKFlag);

    public List<Map<String, Object>> getListforAddWithPaging(WmsCusCustomerHeadSearchBeanVO queryInfo, Integer userId);

    /**
     * 新增并返回新增后的id.
     * 
     * @param bean
     * @return id
     */
    public int saveByPk(WmsCusCustomerHead bean);

    public List<Map<String, Object>> getListWithoutPagingforadd(WmsCusCustomerHeadSearchBeanVO queryInfo, Integer userId);

    public Map<String, Object> getInfoMapByPK(Integer wms_cus_customer_id);
}