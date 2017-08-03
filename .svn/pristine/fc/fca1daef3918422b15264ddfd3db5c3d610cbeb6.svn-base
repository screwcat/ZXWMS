package com.zx.emanage.loancheck.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineBankStreamSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineBankStream;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCreditLineBankStreamService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineBankStreamSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreCreditLineBankStreamSearchBeanVO queryInfo);

    /**
     * Description :查询审批数据
     * 
     * @param wms_cre_credit_head_id,query_type,query_tb
     * @return map list
     * @author auto_generator
     */
    public Map<String, Object> doQuery(int wms_cre_credit_head_id, String query_type, String query_tb);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineBankStreamVO
     * @author auto_generator
     */
    public WmsCreCreditLineBankStream getInfoByPK(Integer wms_cre_credit_line_bank_stream_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreCreditLineBankStream bean, UserBean user);

    /**
     * Description :保存审批数据
     * 
     * @param divwt1_data
     *            ,divwt2_data,divwt3_data,divwt4_data,wms_cre_credit_head_id
     *            ,user
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String doSubSave(String divwt1_data, String divwt2_data, String divwt3_data, String divwt4_data,
                            int wms_cre_credit_head_id, UserBean user, WmsCreditWorkFlowVO approveWorkFlowVO);

    /**
     * Description :保存审批数据
     * 
     * @param divwt1_data
     *            ,divwt2_data,divwt3_data,divwt4_data,wms_cre_credit_head_id
     *            ,user
     * @return "success" or "error" or user defined
     * @author wangyishun
     */
    public String doSubSaveFd(String divwt1_data, String divwt2_data, String divwt3_data, String divwt4_data,
                              UserBean user, WmsHouseCreditWorkFlowVO vo);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreCreditLineBankStream bean, UserBean user);
}