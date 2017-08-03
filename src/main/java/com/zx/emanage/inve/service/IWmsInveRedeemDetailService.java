package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveRedeemDetail;
import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.emanage.inve.vo.WmsInveRedeemDetailSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveRedeemDetailService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsInveRedeemDetailSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author ry
     */
    public Map<String, Object> getListWithoutPagingBywiri(WmsInveRedeemDetailSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsInveRedeemDetailSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInveRedeemDetailVO
     * @author auto_generator
     */
    public WmsInveRedeemDetail getInfoByPK(Integer wms_inve_redeem_detail_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsInveRedeemDetail bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsInveRedeemDetail wms_inve_redeem_detail, UserBean user, WmsInveDebtWorkFlowVO wDebtWorkFlowVO,
                         WmsInveRedeem wInveRedeem,String redeemGridData);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInveRedeemDetailVO
     * @author auto_generator
     */
    public List<WmsInveRedeemDetail> getListByPK(Integer wms_inve_redeem_id);

    /**
     * Description :通过赎回单据主键查询出赎回明细与单据表信息
     * 
     * @param primary key
     * @return map
     * @author zhubo
     */
    public List<Map<String, Object>> getRedeemAllInfo(Integer wms_inve_redeem_id);
    /**
     * Description :doUpdateProduct 领导审核修改--产品变更
     * 
     * @param request
     * @param wms_inve_redeem_detail 赎回明细表
     * @param aInveWorkFlowVO 赎回流程
     * @param wms_inve_redeem_data 赎回主表
     * @return "success" or "error" or user defined
     * @author baisong
     */
    public String updateProduct(String wms_inve_redeem_detail, UserBean user, WmsInveDebtWorkFlowVO wDebtWorkFlowVO,
                         WmsInveRedeem wInveRedeem);
    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public  Map<String,Object> doUpdateforphone(String user_id,WmsInveDebtWorkFlowVO wDebtWorkFlowVO,String wms_inve_redeem_id);
/**
 * 
 * 理财特批
 * @param wms_inve_redeem_detail
 * @param user
 * @param wDebtWorkFlowVO
 * @param wInveRedeem
 * @param redeemGridData 
 * @return
 */
	public String specialRedemptionUpdate(WmsInveRedeemDetail wms_inve_redeem_detail,
			UserBean user, WmsInveDebtWorkFlowVO wDebtWorkFlowVO,
			WmsInveRedeem wInveRedeem, String redeemGridData);
}