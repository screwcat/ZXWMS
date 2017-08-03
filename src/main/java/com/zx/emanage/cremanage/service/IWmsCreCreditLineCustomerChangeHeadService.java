package com.zx.emanage.cremanage.service;

import java.util.Map;

import com.zx.emanage.cremanage.vo.WmsCreCreditLineCustomerChangeHeadSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCreCreditLineCustomerChangeHead;
import com.zx.emanage.util.gen.vo.WmsCreCreditLineCustomerChangeHeadVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCreditLineCustomerChangeHeadService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCustomerChangeHeadSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCustomerChangeHeadSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineCustomerChangeHeadVO
     * @author auto_generator
     */
    public com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead getInfoByPK(Integer wms_cre_credit_line_customer_change_head_id);

    /**
     * Description :查询客户变更信息和工作变更信息
     * 
     * @param wms_cre_credit_line_customer_change_head_id
     * @return map
     * @author ry
     */
    public Map<String, Object> getInfoMapByPK(Integer wms_cre_credit_line_customer_change_head_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreCreditLineCustomerChangeHead bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreCreditLineCustomerChangeHead bean, UserBean user);

    /**
     * Description :delete method
     * 
     * @param pk
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String delete(WmsCreCreditLineCustomerChangeHead bean);

    /**
     * 实现自动带出主带人的手机号
     * 
     * @param wms_cre_credit_head_id
     * @return
     */
    public Map<String, Object> getInfoListByTEL(Integer wms_cre_credit_head_id);

    /**
     * Description :获取指定贷款记录的主贷人和非主货人的集合
     * 
     * @param wms_cre_credit_head_id 贷款记录ID
     * @return 指定贷款记录的主贷人和非主货人的集合
     * @author wangyishun
     */
    public Map<String, Object> getWmsCreCreditLineCustomerChangeHeadListWithoutPaging(Integer wms_cre_credit_head_id);
    
    /**
     * 根据客户id查询所有客户信息(客户、子女、房产、工作、公司)
     * @author administrator
     */
    public Map<String, Object> searchAllCustomerInfo(WmsCreCreditLineCustomerChangeHeadSearchBeanVO queryInfo, UserBean user);
    
    /**
     * 根据客户id删除所有客户信息(逻辑删除)
     * @author administrator
     */
    public Map<String, Object> wmsCustomerAllInfoDelete(WmsCreCreditLineCustomerChangeHeadSearchBeanVO queryInfo, UserBean user);
    /**
     * 
     * 根据上单ID查询客户信息
     * @param wms_cre_credit_head_id
     * @return
     */
    public WmsCreCreditLineCustomerChangeHeadVO getKHXX(Integer wms_cre_credit_head_id);
    
}