package com.zx.emanage.loanappro.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loanappro.vo.WmsCreApproAppealPostilSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreApproAppealPostil;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineApplicant;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreApproAppealPostilService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreApproAppealPostilSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreApproAppealPostilSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreApproAppealPostilVO
     * @author auto_generator
     */
    public WmsCreApproAppealPostil getInfoByPK(Integer wms_cre_appro_appeal_postil_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreApproAppealPostil bean, UserBean user, int wms_cre_credit_head_id);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreApproAppealPostil bean, UserBean user);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineApplicantVO
     * @author auto_generator
     */
    public Map<String, Object> getInfoByFK(Integer wms_cre_credit_head_id);
}