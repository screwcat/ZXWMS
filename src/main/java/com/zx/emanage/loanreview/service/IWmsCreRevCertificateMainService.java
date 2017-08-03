package com.zx.emanage.loanreview.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.loanreview.vo.WmsCreRevCertificateMainSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevCertificateMain;
import com.zx.emanage.util.gen.entity.WmsCreRevCertificateModel;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreRevCertificateMainService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreRevCertificateMainSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreRevCertificateMainSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevCertificateMainVO
     * @author auto_generator
     */
    public WmsCreRevCertificateMain getInfoByPK(Integer wms_cre_rev_certificate_main_id);

    /**
     * Description :存储房贷征信信息
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author zhubo
     */
    public String saveFd(String zxsh, WmsHouseCreditWorkFlowVO vo, UserBean user, boolean isCommit);

    /**
     * Description :存储信贷征信信息
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String saveXd(String zxsh, WmsCreRevCertificateModel bean, WmsCreditWorkFlowVO approveWorkFlowVO,
                         UserBean user, boolean isCommit);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreRevCertificateMain bean, UserBean user);

    /**
     * Description :获取特殊说明信息
     * 
     * @param primary key
     * @return WmsCreRevCertificateMain
     * @author zhubo
     */
    public List<WmsCreRevCertificateMain> getInfoByEntity(WmsCreRevCertificateMain queryInfo);

    /**
     * Description :信贷退件
     * 
     * @param approveWorkFlowVO
     * @param user
     * @return
     */
    public String creditCertificateToBack(WmsCreditWorkFlowVO approveWorkFlowVO, UserBean user);

    /**
     * Description :房贷退件
     * 
     * @param approveWorkFlowVO
     * @param user
     * @return
     */
    public String houseCertificateToBack(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, UserBean user);
}