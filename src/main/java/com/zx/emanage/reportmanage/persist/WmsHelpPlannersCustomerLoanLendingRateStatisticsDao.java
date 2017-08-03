package com.zx.emanage.reportmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsHelpPlannersCustomerLoanLendingRateStatistics;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 *  客户累计放款率统计表Dao
 * @author hancd
 *
 */
@MyBatisRepository
public interface WmsHelpPlannersCustomerLoanLendingRateStatisticsDao extends BaseDao<WmsHelpPlannersCustomerLoanLendingRateStatistics>
{

	 /**
     * 实现导出客户还款请款信息表数据
     * @param paramMap
     * @return
     */
    public java.util.List<Map<String,Object>> getListWithoutPaging(Map<String, Object> paramMap);
    /**
     * 获取系统统计信息
     * @param paramMap
     * @return
     */
    public Map<String, Object> searchByDataView(Map<String, Object> paramMap);
	 /**
     * 实现导出客户还款请款信息表数据
     * @param paramMap
     * @return
     */
    public java.util.List<Map<String,Object>> getListWithoutPaging_r(Map<String, Object> paramMap);
    /**
     * 获取系统统计信息
     * @param paramMap
     * @return
     */
    public Map<String, Object> searchByDataView_r(Map<String, Object> paramMap);
    /**
     * search:根据传人的条件动态生成sql语句，如需分页需要在sql中加入offset、pagesize变量 <br/>
     * 
     * 
     * @param parameters
     * @return
     * baisong
     */
    List<Map<String, Object>> search_r(Map<String, Object> parameters);
    /**
     * findCount:根据查询条件返回记录总条数. <br/>
     * 
     *
     * @param paramMaps
     * @return
     * @since 
     * baisong
     */
    int findCount_r(Map<String, Object> paramMaps);

    /**
     * 获取详细信息
     * @param paramMap
     * @return
     * baisong
     */
    public List<Map<String,Object>> searchDetails(Map<String, Object> paramMap);

}
