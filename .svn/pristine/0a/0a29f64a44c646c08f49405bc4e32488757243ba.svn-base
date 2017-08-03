package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralNetRulesNew;

@MyBatisRepository
public interface WmsInveCommissionGeneralNetRulesNewDao extends BaseDao<WmsInveCommissionGeneralNetRulesNew> {
	
    List<Map<String, Object>> getList(Map<String, Object> map);
    
    int findCountList(Map<String, Object> paramMap);
    
    /**
     * 根据主表id删除对应的月付佣金
     * @return int
     */
    int deletebykey(Integer wms_inve_commission_general_rules_new_id);
    
    
    
}