package com.zx.emanage.inve.service;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.emanage.util.gen.vo.WmsInveRedeemVO;
import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.emanage.inve.vo.WmsInveTransaProtocolSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveTransaProtocolService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsInveTransaProtocolSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsInveTransaProtocolSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInveTransaProtocolVO
     * @author auto_generator
     */
    public WmsInveTransaProtocol getInfoByPK(Integer wms_inve_transa_protocol_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsInveTransaProtocol bean, UserBean user, String xqxy, String flag);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsInveTransaProtocol bean, UserBean user);

    /**
     * 通过上单信息表ID获得赎回申请信息
     * 
     * @param wms_inve_transa_id
     * @return map
     * @author zhubo
     */
    public Map<String, Object> getRedeemApply(String wms_inve_transa_id);
    
   
    /**
     * 通过赎回金额  赎回日期计算收益 管理费
     * @param 	WmsInveRedeemVO
     * @return 	map
     * @author 	zhangyunfei
     * @date 	2016年08月10日
     */
    public Map<String, Object> getRedeemDueIncome(WmsInveRedeemVO wmsInveRedeemVO);


    /**
     * 赎回-理财协议保存 baisong
     * 
     * @param bean
     * @param user
     * @return
     */
    public String saveBack(WmsInveTransaProtocol bean, UserBean user, WmsInveDebtWorkFlowVO wDebtWorkFlowVO);

    /**
     * 通过上单信息表ID与赎回单据表ID获得赎回申请信息
     * 
     * @param wms_inve_transa_id
     * @return map
     * @author zhubo
     */
    public Map<String, Object> getProtocolData(String checkedInveTransaIDs, String wms_inve_redeem_id);

    /**
     * 根据合同编号查询合同内容 baisong
     */
    public Map<String, Object> getInfoByCode(String prot_code);

    /**
     * 暂时不启用2015-01-26 wms_inve_transa_id = $.query.get('wms_inve_transa_id');
     * wms_inve_transa_prod_id = $.query.get('wms_inve_transa_prod_id');查询合同编号
     * baisong
     */
    // public Map<String, Object> getCodeById(Integer
    // wms_inve_transa_prod_id,Integer wms_inve_transa_id,Integer
    // wms_inve_redeem_id) ;
    /**
     * 根据协议主键，查询相应协议详细信息
     * 
     * @param wms_inve_transa_protocol_id
     * @return
     */
    public Map<String, Object> getWmsInveTransaPro(Integer wms_inve_transa_protocol_id);
    
    public void SethandleIncomeAndLogInfo(WmsInveTransaProtocol bean,UserBean user);
    
    public void SethandleIncomeAndLogInfoSH(WmsInveTransaProtocol bean,UserBean user);
    /**
     * Description :理财协议签订  新 假设   先定义此方法
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author hancd
     * @date 2015年12月16日 14:18
     */
	public String toSave(WmsInveTransaProtocol bean, UserBean user,WmsInveDebtWorkFlowVO workFlowVO);
}