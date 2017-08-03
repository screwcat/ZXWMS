package com.zx.emanage.cremanage.service;

import java.util.Map;

import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreCarpHousingAttSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsMoaHouseInfoVO;
import com.zx.emanage.cremanage.vo.WmsSearchHosuingVO;
import com.zx.emanage.util.gen.entity.WmsCreCarpHousingAtt;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCarpHousingAttService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsCreCarpHousingAttSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsCreCarpHousingAttSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCarpHousingAttVO
	 * @author auto_generator
	 */	
	public WmsCreCarpHousingAtt getInfoByPK(Integer wms_cre_carp_housing_att_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsCreCarpHousingAtt bean, UserBean user);
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save( UserBean user,WmsCreCarpHousingAttSearchBeanVO beanvo);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsCreCarpHousingAtt bean, UserBean user);
	/**
	 * @Title: doSQSave 
	 * @Description: 贷款复核
	 * @param wmsCarLoanWorkFlowVO
	 * @param fileArr
	 * @param user
	 * @return    
	 * @return String    
	 * @throws
	 * @author lvtu 
	 * @date 2015年7月30日 上午11:34:44
	 */
	public String doSQSave(WmsCarLoanWorkFlowVO wmsCarLoanWorkFlowVO, String fileArr, UserBean user);

	public String doSQSaveBatch(String wmsCreHeadIds, String wmsTaskIds, String wmsCreditLimits, String wmsCreditCreLoanTypes, String pass,
			String advice, String valueOf);
	
	/**
	 * moa 调用wms方法 申请 办件 补件等
	 * @param request
	 * @param wmsCarLoanWorkFlowVO
	 * @param fileArr
	 * @return 
	 */
	public	Map<String,Object> wmsMoaSave(WmsMoaHouseInfoVO bean);
	
	/**
	 * moa 调用wms方法 办件查询列表 获取流程task  idList
	 * @param request
	 * @param wmsCarLoanWorkFlowVO
	 * @param fileArr
	 * @return 
	 */
	public	Map<String,Object> getHosuingIdList(String personnel_id );
	
	/**
	 * moa 调用wms方法  获取单据的退件原因从流程历史中获取
	 * @param request
	 * @param param
	 * @param 
	 * @return Map<String,Object> 
	 */
	public	Map<String,Object> getHosuingWorkInfo(String param );
	
	
	/**
	 * moa 调用wms方法 查询列表 获取流程idlist 第二版本
	 * @param request
	 * @param wmsCarLoanWorkFlowVO
	 * @param personnel_id
	 * @param invekey
	 * @return 
	 */
	public	Map<String,Object> getHosuingIdList(WmsSearchHosuingVO wmsSearchHosuingVO);
	//*************************移动端第二版本方法开始**************************//
	/**
	 * moa 调用wms方法 申请 办件 补件等
	 * @param request
	 * @param wmsCarLoanWorkFlowVO
	 * @param fileArr
	 * @return 
	 */
	public	Map<String,Object> wmsMoaSaveUp(WmsMoaHouseInfoVO bean,String customer_info);
	//*************************移动端第二版本方法结束**************************//

    /**
     * @Title: wmsBizMoaSaveUp
     * @Description: 调用wms方法 申请 补录等
     * @param bean
     * @param customer_info
     * @return 
     * @author: baisong
     * @time:2017年3月22日 上午11:41:06
     * history:
     * 1、2017年3月22日 baisong 创建方法
    */
    public Map<String, Object> wmsBizMoaSaveUp(WmsMoaHouseInfoVO bean, String customer_info);

    /**
     * @Title: postBizDeleteBill
     * @Description: 手机端贷款单据删除(3.2.35 手机端贷款单据删除)
     * @param bean
     * @return 
     * @author: baisong
     * @time:2017年4月6日 下午2:25:54
     * history:
     * 1、2017年4月6日 baisong 创建方法
    */
    public Map<String, Object> postBizDeleteBill(WmsMoaHouseInfoVO bean);
}