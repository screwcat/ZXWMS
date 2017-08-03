package com.zx.emanage.cremanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsCreCarpHousingCustomerInfo;

@MyBatisRepository
public interface WmsCreCarpHousingCustomerInfoDao extends BaseDao<WmsCreCarpHousingCustomerInfo> {
	/**
	 * @Title: searchHCHBYMccid 
	 * @Description: 抵押车产信息
	 * @param paramMap
	 * @return    
	 * @return List<Map<String,Object>>    
	 * @throws
	 * @author lvtu 
	 * @date 2015年7月29日 下午5:03:20
	 */
	List<Map<String, Object>> searchCarHBYMccid(Map<String, Object> paramMap);

	void deleteByMap(Map<String, Object> map);
	/**
	 * @Title: updateHouseSituationAndIsHouseLoan 
	 * @Description: 更新用户的房产归属和是否有贷款
	 * @param creCarpHousingCustomerInfo    
	 * @return void    
	 * @throws
	 * @author lvtu 
	 * @date 2015年8月1日 下午2:14:12
	 */
	void updateHouseSituationAndIsHouseLoan(WmsCreCarpHousingCustomerInfo creCarpHousingCustomerInfo);
	
}