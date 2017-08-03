package com.zx.emanage.sysmanage.persist;

import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveTaxpointRules;

@MyBatisRepository
public interface WmsInveTaxpointRulesDao extends BaseDao<WmsInveTaxpointRules> {
	/**
	 * 添加
	 * 
	 */
	Integer save(WmsInveTaxpointRules bean);
	/**
	 * 删除
	 * 
	 */
	Integer delete(String wms_inve_taxPoint_rules_id);
	/**
	 * 从字典表中查询出固定值
	 * 
	 */
	Map<String,Object> getTax_fixed();
	/**
	 * 
	 *  
	 */
	Integer clear();
}