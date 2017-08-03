package com.zx.emanage.cremanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsCreCarpCarsCustomerInfo;

@MyBatisRepository
public interface WmsCreCarpCarsCustomerInfoDao extends BaseDao<WmsCreCarpCarsCustomerInfo> {
	Map<String,Object> getInfo(Map<String,Object> paramMap);
	/**
	 * @Title: searchCarHBYMccid 
	 * @Description: 抵押车产信息
	 * @param paramMap
	 * @return List<Map<String,Object>>    
	 * @throws
	 * @author lvtu 
	 * @date 2015年7月29日 下午6:31:17
	 */
	List<Map<String, Object>> searchCarCBYMccid(Map<String, Object> paramMap);
	
	void deleteByMap(Map<String, Object> map);
	/**
	 * @Title: searchCarInfoExistCount 
	 * @Description: 车贷抵押车产重复性判断
	 * @param map
	 * @return Integer    
	 * @throws
	 * @author lvtu 
	 * @date 2015年8月4日 下午3:36:09
	 */
	Integer searchCarInfoExistCount(Map<String, Object> map);
}