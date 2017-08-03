package com.zx.emanage.inve.service;

import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInvePruductCategorySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategoryLog;
import com.zx.emanage.util.gen.entity.WmsInvePruductYearPaySpecial;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInvePruductCategoryService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsInvePruductCategorySearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsInvePruductCategorySearchBeanVO queryInfo);
    /**
     * Description :理财产品审核确认列表
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    public Map<String, Object> getListWithPagingAuditing(WmsInvePruductCategorySearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInvePruductCategoryVO
     * @author auto_generator
     */
    public WmsInvePruductCategory getInfoByPK(Integer wms_inve_pruduct_category_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsInvePruductCategory bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsInvePruductCategory bean, UserBean user,String user_ip);

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author wangyishun
     */
    public List<Map<String, Object>> getAllListWithoutPaging(UserBean user,String cktype, String category_name, Integer wms_inve_pruduct_category_id,String platform_user);

    /**
     * Description :为下拉框添加产品数据
     * 
     * @param null
     * @return record list
     * @author ry
     */
    public List<com.zx.emanage.util.gen.entity.WmsInvePruductCategory> getAllInvePruductCategory();

    /**
     * Description :为下拉框添加pos
     * 
     * @param null
     * @return record list
     * @author ry
     */
    public List<com.zx.emanage.util.gen.entity.WmsInvePos> getAllInvePos();

    /**
     * Description :理财产品保存 --产品限制表-产品期限表-产品奖励利率表--产品基本信息
     * 
     * @param null
     * @return record list
     * @author baisong
     */
    public String saveall(WmsInvePruductCategory bean, UserBean user,WmsInvePruductCategorySearchBeanVO BeanVO,WmsInvePruductYearPaySpecial paySpecial );
    /**
     * Description :理财产品更新 --产品限制表-产品期限表-产品奖励利率表--产品基本信息
     * 
     * @param null
     * @return record list
     * @author baisong
     */
    public String updateall(WmsInvePruductCategory bean, UserBean user,WmsInvePruductCategorySearchBeanVO BeanVO,WmsInvePruductYearPaySpecial paySpecial );
    /**
     * Description :残品确认保存审批意见方法
     * 
     * @param BeanVO
     * @return "success" or "error" or user defined
     * @author baisong
     */
    public String doSaveSp( UserBean user,WmsInvePruductCategorySearchBeanVO BeanVO);
    /**
     * Description :残品启用意见方法
     * 
     * @param BeanVO bean
     * @return "success" or "error" or user defined
     * @author baisong
     */
    public String doSaveQy( UserBean user,WmsInvePruductCategoryLog beanlog,WmsInvePruductCategory bean);
    
    /***
     * Description:获取理财产品编号
     * 
     * @return String
     * @author baisong
     */
    public String getLCCode();
    /**
     * @Title: getProductIsForbidden 
     * @Description: 查看产品是否禁用
     * @param wms_inve_pruduct_category_id
     * @return    
     * @return Map<String,Object>    
     * @throws
     * @author lvtu 
     * @date 2015年8月21日 下午3:44:46
     */
	public Map<String, Object> getProductIsForbidden(Integer wms_inve_pruduct_category_id);
	/**
	 * 根据传入的产品有效状态，查询相应的产品数据
	 * @param is_forbidden
	 * @return list
	 * @author hancd
	 * @date 2015年8月31日 下午20:18
	 */
	public List<WmsInvePruductCategory> getInvePruductCategoryInfo(String key);
	
	/**
	 * 检查产品名称是否已经存在
	 * @param bean
	 * @return
	 */
	public String isCheck(WmsInvePruductCategory bean);
	
	/**
	 * @Title: getInvePruductCategorys 
	 * @Description: 获取产品并按照产品类型分类
	 * @return Map<String,Object> 
	 * @throws
	 * @author lvtu 
	 * @date 2015年9月15日 上午9:42:49
	 */
	public Map<String, Object> getInvePruductCategorys();
	
	  /**
     * Description :获取全部启用产品
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    public  Map<String, Object> getAllProductListWithoutPaging(String cktype, String category_name, Integer wms_inve_pruduct_category_id);

    /**
     * @Title: getCategoryTypeByCategoryId
     * @Description: 根据产品id获得产品类型
     * @param categoryId 产品id 
     * @return 返回产品类型
     * @author: jinzhm
     * @time:2017年5月19日 上午9:29:43
     * history:
     * 1、2017年5月19日 jinzhm 创建方法
     */
    public Map<String, Object> getCategoryTypeByCategoryId(Integer categoryId);
}