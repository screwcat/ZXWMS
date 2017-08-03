package com.zx.emanage.reportmanage.persist;

import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveCommissionTeamPerformance;

@MyBatisRepository
public interface WmsInveCommissionTeamPerformanceDao extends BaseDao<WmsInveCommissionTeamPerformance> {
	/**
	 * Description :update method-修改单据并添加发放日期
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */
    int updateStatus(Map<String, Object> paramMap );
}