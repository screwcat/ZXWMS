package com.zx.emanage.reportmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveCommissionPerformance;

@MyBatisRepository
public interface WmsInveCommissionPerformanceDao extends BaseDao<WmsInveCommissionPerformance> {
	/**
	 * Description :update method-修改单据并添加发放日期
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */
    int updateStatus(Map<String, Object> paramMap );
	/**
	 * Description :update method-修改单据并添加发放日期
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */
    List<Map<String, Object>>  getDetails(Map<String, Object> paramMap  );
    
    /**
     * search:根据传人的条件动态生成sql语句，如需分页需要在sql中加入offset、pagesize变量 <br/>--佣金发放查询
     * 
     * @author baisong
     * @param parameters
     * @return
     * @since JDK 1.6
     */
    List<Map<String, Object>> searchAll(Map<String, Object> parameters);
    /**
     * findCount:根据查询条件返回记录总条数. <br/>--佣金发放查询
     * 
     * @author baisong
     * @param paramMaps
     * @return
     * @since JDK 1.6
     */
    int findCountAll(Map<String, Object> paramMaps);
    
    List<Map<String, Object>> searchForExport(Map<String, Object> paramMap);
    
    List<Map<String, Object>> searchForExportCommissionPaymentQuery(Map<String, Object> paramMap);
    
}