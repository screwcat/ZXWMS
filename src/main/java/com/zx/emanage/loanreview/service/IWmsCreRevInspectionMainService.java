package com.zx.emanage.loanreview.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsCreRevInspectionMain;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.loanreview.vo.WmsCreRevInspectionMainSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreRevInspectionMainService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreRevInspectionMainSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreRevInspectionMainSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevInspectionMainVO
     * @author auto_generator
     */
    public WmsCreRevInspectionMain getInfoByPK(Integer wms_cre_rev_inspection_main_id);

    /**
     * Description :验点审批存储
     * 
     * @param bean 验点主表数据的获取
     * @param approveWorkFlowVO 工作流需要工具VO
     * @param ydfamliy 验点家庭背景数据
     * @param fileArr 验点环节上传的附件
     * @param flag 代表是否暂存(flag==0暂存 flag==1保存)
     * @return "success" or "error" or user defined
     * @author hancd
     */
    public String save(WmsCreRevInspectionMain bean, WmsCreditWorkFlowVO approveWorkFlowVO, String ydfamliy,
                       Integer flag, UserBean user, String fileArrs);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreRevInspectionMain bean, UserBean user);

    /**
     * 实现信贷 验点退件处理
     * 
     * @param approveWorkFlowVO
     * @param user
     * @return
     * @author hancd
     */
    public String creditydToBack(WmsCreditWorkFlowVO approveWorkFlowVO, UserBean user);

    /**
     * 实现 信贷 验点数据的读取
     * 
     * @param wms_cre_credit_head_id
     * @return
     */
    public Map<String, Object> getInfoByFK(Integer wms_cre_credit_head_id);
}