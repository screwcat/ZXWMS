package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInvePruductCategoryDao extends BaseDao<WmsInvePruductCategory>
{

    List<Map<String, Object>> searchAll(Map<String, Object> paramMap);

    /**
     * 获取所有理财产品
     * 
     * @return list
     */
    List<WmsInvePruductCategory> getAllInvePruductCategory();

    /**
     * 根据产品名称查询出所有的产品
     * 
     * @return baisong
     */
    List<WmsInvePruductCategory> getAllInvePruductCategoryByName(WmsInvePruductCategory bean);

    List<Map<String, Object>> searchAuditing(Map<String, Object> paramMap);
    /**
     * findCount:根据查询条件返回记录总条数. <br/>
     * 
     * @author Administrator
     * @param paramMaps
     * @return
     * @since JDK 1.6
     */
    int findCountAuditing(Map<String, Object> paramMaps);

	/**
	 * @Title: getProductIsForbidden 
	 * @Description: 查看产品是否禁用
	 * @param wms_inve_pruduct_category_id
	 * @return    
	 * @return Map<String,Object>    
	 * @throws
	 * @author lvtu 
	 * @date 2015年8月21日 下午3:45:52
	 */
	Map<String, Object> getProductIsForbidden(Integer wms_inve_pruduct_category_id);
	/**
	 * 根据传入产品状态，查询相应的产品数据
	 * @param is_forbidden
	 * @return
	 */
	List<WmsInvePruductCategory> getInvePruductCategoryInfo(Map<String,Object> is_forbidden);
	/**
	 * 查询启用和禁用状态的产品
	 * @param is_forbidden
	 * @return
	 */
	List<WmsInvePruductCategory> getListforLC(WmsInvePruductCategory bean);
	
	/**
     * Description :获取全部启用产品--产品变更
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    List<Map<String, Object>> getAllProductList(Map<String, Object> paramMap);

    /**
     * @Deprecated 获取产品的信息
     * @return 只返回产品id和产品名称
     * @author donghao
     * @date 2016年8月26日10:45:29
     */
	List<WmsInvePruductCategory> findCategoryAll();
}