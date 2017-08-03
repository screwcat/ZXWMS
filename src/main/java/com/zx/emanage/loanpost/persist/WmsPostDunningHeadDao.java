package com.zx.emanage.loanpost.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsPostDunningHead;

@MyBatisRepository
public interface WmsPostDunningHeadDao extends BaseDao<WmsPostDunningHead> {
    //实现通过传入参数，实现对催缴单据状态的修改
    void updateReocod(Map<String,Object>paramap);
    /**
     *  Description :催缴单据分配-催缴人员分配
     * @param paramMap
     * @return
     * baisong
     */
    List<Map<String, Object>> seachInfoCount();
    /**
     *  Description :查询催缴单据的数量
     * @param 
     * @return int
     * baisong
     */
    int seachCount(Map<String,Object> paramMap);
    /**
     *  Description :查询催缴人信息 根据id查询
     * @param paramMap
     * @return
     * baisong
     */
    Map<String, Object> seachInfo(Integer id);
    /**
     *  Description :查询催缴单据信息 根据id查询
     * @param id
     * @return
     * baisong
     */
    Map<String, Object> seachInfobyid(Integer id); 
    /**
     * Description :通过筛选条件，获取催缴单据审核列表数据
     * @param map
     * @return list
     * @author hancd
     */
    List<Map<String, Object>> searchforDunningReview(Map<String, Object> paramMap);
    /**
     *  Description : 通过筛选条件，获取催缴单据审核列表数据数
     *  @param map
     *  @return 
     *  @author hancd
     **/
    int findCountDunningReview(Map<String, Object> paramMap);
    /**
     *  Description :通过催缴单ID，获取所有相关催缴单关联数据
     *  @param wms_post_dunning_head_id
     *  @return 
     *  @author hancd
     * */
    Map<String, Object> getWmsPostDunningHeadInfoByPK(Integer wms_post_dunning_head_id);
    /**
     *  Description :通过筛选条件，获取催缴单页面列表数据进行导出
     *  @param paramMap
     *  @return 
     *  @author hancd
     * */
    List<Map<String, Object>> searchforDunningReviewWithout(Map<String, Object> paramMap);
    /**
     * Description :通过筛选条件，获取上门催缴列表数据显示
     *  @param paramMap
     *  @return 
     *  @author hancd
     * */
    List<Map<String, Object>> searchforDunningHome(Map<String, Object> paramMap);
    /**
     * Description :通过筛选条件，获取上门催缴显示数据数
     *  @param paramMap
     *  @return 
     *  @author hancd
     * */
    int findCountDunningHome(Map<String, Object> paramMap);
    /**
     * Description :通过筛选条件，获取上门催缴数据导出
     * @param paramMap
     * @return 
     * @author hancd
     * */
    List<Map<String, Object>> searchforDunningHomeWithout(Map<String, Object> paramMap);
    /**
     * 通过催缴单查询到后的数据
     * @param paramMap
     * @return
     */
	List<Map<String, Object>> searchforPostLoan(Map<String, Object> paramMap);
	/**
	 * 通过催缴单查询出数量
	 * @param paramMap
	 * @return
	 */
	int findCountforPostLoan(Map<String, Object> paramMap);
	  /**
     * 通过id查询催缴状态不是0的数据
     * @param paramMap
     * @return
     */
	List<WmsPostDunningHead> getListById(WmsPostDunningHead wmsPostDunningHead);
	/**
	 * @Title: getReminderSearchList 
	 * @Description: (催缴单查询) 
	 * @param paramMap
	 * @return    
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 * @author lvtu
	 */
	List<Map<String, Object>> getReminderSearchList(Map<String, Object> paramMap);
	/**
	 * @Title: getReminderSearchListSize 
	 * @Description: (催缴单查询列表的数量) 
	 * @param paramMap
	 * @return    
	 * @return int    返回类型
	 * @throws
	 * @author lvtu
	 */
	int getReminderSearchListSize(Map<String, Object> paramMap);
	/**
	 * @Title: getOrgRepayPrincipalAndInterest 
	 * @Description: (根据催缴单ID获取应还款本金和应还款利息) 
	 * @param wms_post_dunning_head_id
	 * @return    
	 * @return Map<String,Object>    返回类型
	 * @throws
	 * @author lvtu
	 */
	Map<String, Object> getOrgRepayPrincipalAndInterest(Integer wms_cre_credit_head_id);
	
	/**
	 * @Title: getReminderSearchList 
	 * @Description: 获取催缴单列表（贷后查询-查看-催缴单列表）
	 * @param paramMap
	 * @return    
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 * @author lvtu
	 */
	List<Map<String, Object>> getReminderListBychid(Integer wms_cre_credit_head_id);
	/**
	 * @Title: getList4 
	 * @Description: <!-- 查询催缴主表  催缴状态为待上门催缴的4 -->
	 * @param wms_cre_credit_head_id  
	 * @return List<WmsPostDunningHead>   返回类型
	 * @throws 
	 * @author baisong
	 */
	List<WmsPostDunningHead> getList4(Integer wms_cre_credit_head_id);
}