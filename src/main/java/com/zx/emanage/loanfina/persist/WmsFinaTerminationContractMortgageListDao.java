package com.zx.emanage.loanfina.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsFinaTerminationContractMortgageList;

@MyBatisRepository
public interface WmsFinaTerminationContractMortgageListDao extends BaseDao<WmsFinaTerminationContractMortgageList> {
	 /**
     * 查询查询列表---终止合同确认
     * 
     * @param params
     *baisong
     */
    List<Map<String, Object>> searchforaffirm(Map<String, Object> params);
}