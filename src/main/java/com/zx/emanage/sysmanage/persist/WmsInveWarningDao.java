package com.zx.emanage.sysmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveWarningDao {
	
	public List<Map<String,Object>> getInfo(Map<String,Object> map);
	public int findCount(Map<String,Object> map);
	
	
	/**
	 * 获取统计数据
	 * @return
	 */
	public Map<String,Object> getSumInfo();
}
