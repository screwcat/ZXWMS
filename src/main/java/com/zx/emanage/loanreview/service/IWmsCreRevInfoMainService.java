package com.zx.emanage.loanreview.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.loanreview.vo.WmsCreRevInfoMainSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevInfoMain;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreRevInfoMainService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreRevInfoMainSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreRevInfoMainSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevInfoMainVO
     * @author auto_generator
     */
    public WmsCreRevInfoMain getInfoByPK(Integer wms_cre_rev_info_main_id);

    /**
     * 信贷-信息审批组审批数据保存
     * 
     * @param bean 当前审批人
     * @param grInfo 个人信息全部信息
     * @param qyInfo 企业信息全部信息
     * @param flag flag==0代表暂存 flag==1代表提交
     * @return "success" or "error" or user defined
     * @author hancd
     */
    public String save(WmsCreRevInfoMain bean, UserBean user, String grInfo, String qyInfo,
                       WmsCreditWorkFlowVO approveWorkFlowVO, Integer flag, String InfoImData);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreRevInfoMain bean, UserBean user);

    /**
     * 信贷-信调审批-个人信息组数据显示
     * 
     * @param wms_cre_credit_head_id
     * @param wms_cre_credit_line_customer_change_head_id
     * @return
     */
    public Map<String, Object> getInfoByFKForAddgr(Integer wms_cre_credit_head_id,
                                                   Integer wms_cre_credit_line_customer_change_head_id);

    /**
     * 信贷-信调审批-企业信息组数据显示
     * 
     * @param wms_cre_credit_head_id
     * @param wms_cre_credit_line_customer_change_head_id
     * @return
     */
    public Map<String, Object> getInfoByFKForAddqy(Integer wms_cre_credit_head_id,
                                                   Integer wms_cre_credit_line_customer_change_head_id);

    /**
     * 房贷-信调审批-信息审批保存
     * 
     * @param bean 获取信息主表的实体
     * @param user 当前审批user
     * @param grInfo 个人信息
     * @param qyInfo 企业信息
     * @param approveHouseWorkFlowVO 房贷流程实体类
     */
    public String saveHouse(WmsCreRevInfoMain bean, UserBean user, String grInfo, String qyInfo,
                            WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, Integer flag);

    /**
     * 实现信贷 信调退件处理
     * 
     * @param approveWorkFlowVO
     * @param user
     * @return
     * @author hancd
     */
    public String infoToBack(WmsCreditWorkFlowVO approveWorkFlowVO, UserBean user);

    /**
     * 实现房贷 信调退件处理
     * 
     * @param approveHouseWorkFlowVO
     * @param user
     * @return str
     * @author hancd
     */
    public String houseInfoToBack(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, UserBean user);
}