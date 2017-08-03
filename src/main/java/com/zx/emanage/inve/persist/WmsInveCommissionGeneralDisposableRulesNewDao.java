package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralDisposableRulesNew;

@MyBatisRepository
public interface WmsInveCommissionGeneralDisposableRulesNewDao extends BaseDao<WmsInveCommissionGeneralDisposableRulesNew> {
	
    /**
     * 获取上单当月给予一次性佣金与满不同月数佣金内容
     * @return list
     */
    List<Map<String, Object>> getList(Map<String, Object> paramMap);
    
    /**
     * 根据主表id删除对应的佣金内容
     * @return int
     */
    int deletebykey(Integer wms_inve_commission_general_rules_new_id);
    
    void saveAll(List<WmsInveCommissionGeneralDisposableRulesNew> list);
    
}