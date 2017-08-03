package com.zx.emanage.loancheck.service;

import java.util.Map;

import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loancheck.vo.WmsCreHousingCheckSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreHousingCheck;
import com.zx.sframe.util.vo.UserBean;

public interface IWmsCreHousingCertificateService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreHousingCheckSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreHousingCheckSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreHousingCheckVO
     * @author auto_generator
     */
    public WmsCreHousingCheck getInfo(Integer wms_cre_housing_check_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(UserBean user, WmsHouseCreditWorkFlowVO vo, String fileArr);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreHousingCheck bean, UserBean user);
}
