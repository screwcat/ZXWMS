package com.zx.emanage.reportmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsHelpPlannersCustomerLoanRepaymentConditions;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 客户还款信息统计
 * @author hancd
 *
 */
@MyBatisRepository
public interface WmsHelpPlannersCustomerLoanRepaymentConditionsDao extends BaseDao<WmsHelpPlannersCustomerLoanRepaymentConditions>
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
}
