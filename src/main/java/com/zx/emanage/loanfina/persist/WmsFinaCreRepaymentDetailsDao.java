package com.zx.emanage.loanfina.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepaymentDetails;

@MyBatisRepository
public interface WmsFinaCreRepaymentDetailsDao extends BaseDao<WmsFinaCreRepaymentDetails> {

	List<Map<String, Object>> getListBySearchforDetails(Map<String, Object> paramMap);

	int getListBySearchforDetailsCounts(Map<String, Object> resMap);
	
}