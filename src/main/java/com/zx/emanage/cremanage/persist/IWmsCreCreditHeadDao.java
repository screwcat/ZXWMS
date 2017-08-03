package com.zx.emanage.cremanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.cremanage.vo.WmsCreCreditHeadSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCreCreditHead;
import com.zx.emanage.util.gen.vo.WmsCreCreditHeadVO;

/*
 * @version 2.0
 */

public interface IWmsCreCreditHeadDao
{

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo);

    /**
     * Description :get record list count num by vo queryInfo
     * 
     * @param queryInfo
     * @return records count num
     * @author auto_generator
     */
    public int getListCountNum(WmsCreCreditHeadSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditHeadVO
     * @author auto_generator
     */
    public WmsCreCreditHeadVO getInfoByPK(Integer wms_cre_credit_head_id);

    /**
     * Description :insert a record, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int addNewRecord(WmsCreCreditHead bean);

    /**
     * Description :update a record replace all, need primary key, support null
     * val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordAll(WmsCreCreditHead bean);

    /**
     * Description :update a record replace columns, need primary key,
     * nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordCols(WmsCreCreditHead bean);

    /**
     * Description :delete record by primary key
     * 
     * @param pk
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByPK(Integer wms_cre_credit_head_id);

    /**
     * Description :delete record by domain bean, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByDomain(WmsCreCreditHead bean);

    /**
     * Description :get list by "and" method, need new WmsCreCreditHead()
     * include query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return WmsCreCreditHead list
     * @author auto_generator
     */
    public List<WmsCreCreditHead> getSingleTableListByAndMethod(WmsCreCreditHead queryInfo, Boolean isExcludePKFlag);

    /**
     * Description :get list by "or" method, need new WmsCreCreditHead() include
     * query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return WmsCreCreditHead list
     * @author auto_generator
     */
    public List<WmsCreCreditHead> getSingleTableListByOrMethod(WmsCreCreditHead queryInfo, Boolean isExcludePKFlag);

    /**
     * 新增并返回新增后的id.
     * 
     * @param bean
     * @return id
     */
    public int saveByPk(WmsCreCreditHead mcch);
}