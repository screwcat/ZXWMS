package com.zx.emanage.loanreview.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loanreview.vo.WmsCreRevCertificateModelSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevCertificateModel;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreRevCertificateModelService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreRevCertificateModelSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreRevCertificateModelSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevCertificateModelVO
     * @author auto_generator
     */
    public WmsCreRevCertificateModel getInfoByPK(Integer wms_cre_rev_certificate_model_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreRevCertificateModel bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreRevCertificateModel bean, UserBean user);

    /**
     * 通过主表单ID获得征信重要数据
     * 
     * @param primary key
     * @return WmsCreRevCertificateModelVO
     * @author zhubo
     */
    public WmsCreRevCertificateModel getInfoByFK(Integer wms_cre_credit_head_id);
}