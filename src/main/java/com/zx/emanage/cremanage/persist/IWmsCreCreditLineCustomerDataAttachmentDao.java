package com.zx.emanage.cremanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.cremanage.vo.WmsCreCreditLineCustomerDataAttachmentSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCreCreditLineCustomerDataAttachment;
import com.zx.emanage.util.gen.vo.WmsCreCreditLineCustomerDataAttachmentVO;

/*
 * @version 2.0
 */

public interface IWmsCreCreditLineCustomerDataAttachmentDao
{

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithoutPaging(WmsCreCreditLineCustomerDataAttachmentSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithPaging(WmsCreCreditLineCustomerDataAttachmentSearchBeanVO queryInfo);

    /**
     * Description :get record list count num by vo queryInfo
     * 
     * @param queryInfo
     * @return records count num
     * @author auto_generator
     */
    public int getListCountNum(WmsCreCreditLineCustomerDataAttachmentSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineCustomerDataAttachmentVO
     * @author auto_generator
     */
    public WmsCreCreditLineCustomerDataAttachmentVO getInfoByPK(Integer wms_cre_credit_line_customer_data_attachment_id);

    /**
     * Description :insert a record, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int addNewRecord(WmsCreCreditLineCustomerDataAttachment bean);

    /**
     * Description :update a record replace all, need primary key, support null
     * val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordAll(WmsCreCreditLineCustomerDataAttachment bean);

    /**
     * Description :update a record replace columns, need primary key,
     * nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordCols(WmsCreCreditLineCustomerDataAttachment bean);

    /**
     * Description :delete record by primary key
     * 
     * @param pk
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByPK(Integer wms_cre_credit_line_customer_data_attachment_id);

    /**
     * Description :delete record by domain bean, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByDomain(WmsCreCreditLineCustomerDataAttachment bean);

    /**
     * Description :get list by "and" method, need new
     * WmsCreCreditLineCustomerDataAttachment() include query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return WmsCreCreditLineCustomerDataAttachment list
     * @author auto_generator
     */
    public List<WmsCreCreditLineCustomerDataAttachment> getSingleTableListByAndMethod(WmsCreCreditLineCustomerDataAttachment queryInfo,
                                                                                      Boolean isExcludePKFlag);

    /**
     * Description :get list by "or" method, need new
     * WmsCreCreditLineCustomerDataAttachment() include query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return WmsCreCreditLineCustomerDataAttachment list
     * @author auto_generator
     */
    public List<WmsCreCreditLineCustomerDataAttachment> getSingleTableListByOrMethod(WmsCreCreditLineCustomerDataAttachment queryInfo,
                                                                                     Boolean isExcludePKFlag);
}