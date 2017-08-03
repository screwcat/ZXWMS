package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zx.emanage.util.gen.entity.WmsInveWagerulesNonlocalHead;
import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;


@MyBatisRepository
public interface WmsInveWagerulesNonlocalHeadDao extends BaseDao<WmsInveWagerulesNonlocalHead> {
	//根据ID删除
	void deleteHead(@Param("id")Integer wms_inve_wagerules_nonlocal_head_id);
	
	//判断传入规则与已有规则是否重复
	List<Map<String, Object>> repeatRulesCount(Map<String, Object> paramMap);
	
}