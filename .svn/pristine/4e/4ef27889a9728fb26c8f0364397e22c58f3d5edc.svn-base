package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveCommissionFloatingNew;

@MyBatisRepository
public interface WmsInveCommissionFloatingNewDao extends BaseDao<WmsInveCommissionFloatingNew> {
	
    /**
     * 获取浮动工资内容
     * @return list
     */
    List<Map<String, Object>> getList(Map<String, Object> paramMap);
    
    /**
     * 根据主表id删除对应的浮动信息 
     * @return int
     */
    int deletebykey(Integer wms_inve_commission_general_rules_new_id);
    
    
    
}