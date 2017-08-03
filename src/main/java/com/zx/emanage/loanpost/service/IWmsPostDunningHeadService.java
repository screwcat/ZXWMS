package com.zx.emanage.loanpost.service;

import java.util.List;
import java.util.Map;

import com.zx.emanage.loanfina.vo.WmsFinaCreRepaySearchBeanVO;
import com.zx.emanage.loanpost.vo.WmsAllocationSearchBeanVO;
import com.zx.emanage.loanpost.vo.WmsPostDunningDetailedSearchBeanVO;
import com.zx.emanage.loanpost.vo.WmsPostDunningHeadSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevPhoneMain;
import com.zx.emanage.util.gen.entity.WmsPostDunningDetailed;
import com.zx.emanage.util.gen.entity.WmsPostDunningHead;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsPostDunningHeadService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsPostDunningHeadSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsPostDunningHeadSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsPostDunningHeadVO
	 * @author auto_generator
	 */	
	public WmsPostDunningHead getInfoByPK(Integer wms_post_dunning_head_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsPostDunningHead bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsPostDunningHead bean, UserBean user);
	/**
	 * Description : 电话催缴   催缴单信息保存更新
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */	
	public String saveUpdate(WmsPostDunningHead bean, UserBean user,WmsPostDunningDetailed beanDetailed,WmsPostDunningDetailedSearchBeanVO wmsPostDunningDetailedSearchBeanVO);
	/**
	 * Description : 上门催缴   催缴单信息保存更新
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */	
	public String doSaveUpdateVisit(WmsPostDunningHead bean, UserBean user,WmsPostDunningDetailed beanDetailed,WmsPostDunningDetailedSearchBeanVO wmsPostDunningDetailedSearchBeanVO);
   
	/**
     *  Description :获取催缴人员信息  
     * @param bean
     * @return
     * @author baisong
     */
	public List<Map<String,Object>> getDictDataByDictIdEmpty();
	/**
     *  Description :查询催缴单据的数量
     * @param paramMap
     * @return
     * baisong
     */
	public int seachCount(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user) ;
   /**
	 *  Description :贷后管理-催缴管理 --单据分配
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */	
	public String allocationNnumber(WmsAllocationSearchBeanVO wmsAllocationSearchBeanVO,WmsFinaCreRepaySearchBeanVO queryInfo);
	/**
     * Description :获取催缴情况审核界面列表
     * @param queryInfo
     * @return Map<String, Object> 
     * @author hancd
     */
    public Map<String, Object> getPostDunningReviewListWithPaging(WmsPostDunningHeadSearchBeanVO queryInfo,UserBean user);
    /**
     * Description:通过催缴单ID获取相关关联信息
     * @param wms_post_dunning_head_id
     * @return
     */
    public Map<String, Object> getWmsPostDunningHeadInfoByPK(Integer wms_post_dunning_head_id);
    /**
     * Description:催缴审核单据保存
     * @param bean
     * @param user
     * @param flag  0代表暂存  1代表提交
     * @return
     */
    public String reviewUpdate(WmsPostDunningHead bean,WmsPostDunningHeadSearchBeanVO queryInfo, UserBean user,String flag);
    /**
     * Description:催缴单审核页面数据导出
     * @param queryInfo
     * @param user
     * @return
     */
    public Map<String, Object> getReviewWithoutPagingList(WmsPostDunningHeadSearchBeanVO queryInfo, UserBean user);
    /**
     * Description:上门催缴跟踪页面数据显示
     * @param queryInfo
     * @param user
     * @return
     */
    
    public Map<String, Object> getPostDunningHomeListWithPaging(WmsPostDunningHeadSearchBeanVO queryInfo, UserBean user);
    /**
     * Description:上门催缴跟踪页面数据导出
     * @param queryInfo
     * @param user
     * @return
     */
    public Map<String, Object> getHomeWithoutPagingList(WmsPostDunningHeadSearchBeanVO queryInfo, UserBean user);
    /**
     * Description:上门催缴跟踪催缴确认保存
     * @param bean
     * @param bean1
     * @param user
     * @param flag
     * @param taskId
     * @return
     */
    public String doHomeSave(WmsPostDunningHead bean,WmsPostDunningHeadSearchBeanVO queryInfo, UserBean user, String flag,
                             String taskId);
    
    
    /***
     * Description:判断是否进行过电话催缴提交操作
     * @param taskId
     * @return String
     * @author baisong
     */
    public String ischeck(WmsPostDunningHead bean);
    /***
	 * 获取催缴主表信息
	 * @param queryInfo
	 * @param isExcludePKFlag
	 * @return
	 */
	public List<WmsPostDunningHead> getListByEntity(WmsPostDunningHead bean, Boolean isExcludePKFlag);
    /**
     * 获取催缴单历史数据信息
     * @param wms_post_dunning_head_id
     * @return
     */
    public Map<String, Object> getWmspostDunningheadInfoByPK(Integer wms_post_dunning_head_id, UserBean user);
	/**
	 * 获取催缴单历史信息--逾期还款确认驳回单据使用
	 * baisong
	 */
    public Map<String, Object> getWmspostDunningheadInfoByHK(Integer wms_post_dunning_head_id, UserBean user);
    
	public Map<String, Object> getPostLoanSearchListWithPaging(
			WmsPostDunningHeadSearchBeanVO queryInfo, UserBean user);

	public Map<String, Object> getPostLoanSearchWithoutPagingList(
			WmsPostDunningHeadSearchBeanVO queryInfo, UserBean user);

	public WmsCreRevPhoneMain getTelTeamAdvice(Integer wms_cre_credit_head_id);
	/**
     * Description :逾期确认查询列表 催缴状态弹出页面保存
     * @param WmsPostDunningHead 
     * @return "success" or "error" or user defined
     * @author baisong
     */ 
    public String doUpdateForReject(WmsPostDunningHead bean,UserBean user);
    
    /**
	 * @Title: getReminderSearchList 
	 * @Description: (催缴单查询) 
	 * @param paramMap
	 * @return    
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 * @author lvtu
	 */
	public Map<String, Object> getReminderSearchList(WmsPostDunningHeadSearchBeanVO queryInfo,UserBean user);
	public Map<String, Object> getReminderSearchListWithoutPage(WmsPostDunningHeadSearchBeanVO queryInfo,UserBean user);
	
	/**
	 * @Title: getReminderListBychid 
	 * @Description: 获取催缴单列表（贷后查询-查看-催缴单列表）
	 * @param wms_cre_credit_head_id
	 * @return    
	 * @return Map<String,Object>    返回类型
	 * @throws
	 * @author lvtu
	 */
	public Map<String, Object> getReminderListBychid(Integer wms_cre_credit_head_id);
	/**
	 * @Title: getProcessIngofTracking 
	 * @Description: 跟踪处理结果（贷后查询-查看-跟踪处理结果）
	 * @param wms_cre_credit_head_id
	 * @return    
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 * @author lvtu
	 */
	public List<Map<String, Object>> getProcessIngofTracking(Integer wms_cre_credit_head_id);
}

