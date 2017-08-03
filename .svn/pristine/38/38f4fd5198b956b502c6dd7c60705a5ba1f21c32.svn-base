package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveCommissionCategoryNew;

@MyBatisRepository
public interface WmsInveCommissionCategoryNewDao extends BaseDao<WmsInveCommissionCategoryNew> {
	
    /***
     * 批量新增
     * @param list
     */
    public void saveAll(List<WmsInveCommissionCategoryNew> list);
    
    /***
     * 批量删除
     * @param int
     */
    public void deleteAll(Integer wms_inve_commission_general_rules_new_id);
    
    /***
     * 查询对应产品信息
     * @param int
     * @return  list
     */
    List<Map<String, Object>> getList(Map<String, Object> paramMap); 
    
    /***
     * 根据员工状态和产品idlist判断当前产品是否存在当前员工状态的单据
     * @param list
     */
    public List<Map<String, Object>> isCheck(Map<String, Object> map);
    
    
    
}