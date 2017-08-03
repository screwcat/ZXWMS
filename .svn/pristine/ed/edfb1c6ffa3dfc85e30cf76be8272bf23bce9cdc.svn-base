package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveCompay;

@MyBatisRepository
public interface WmsInveCompayDao extends BaseDao<WmsInveCompay> {
	
	public List<WmsInveCompay> searcompay();
	
    /**
     * 获取公司下拉列表
     */
    List<Map<String, Object>> getCompanySelectForSalary(Map<String, Object> paramMap);
    
    /**
     * 根据公司ID获取部门下拉列表
     */
    List<Map<String, Object>> getDeptSelectByCompanyIdForSalary(Map<String, Object> paramMap);
    
    /**
     * 根据部门ID获取团队下拉列表
     */
    List<Map<String, Object>> getTeamSelectByDeptIdForSalary(Map<String, Object> paramMap);

	public List<Map<String, Object>> getAllCompany(Map<String, Object> paramMap);
    
}