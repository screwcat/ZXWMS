package com.zx.emanage.cremanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreContractLender;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreContractLenderDao extends BaseDao<WmsCreContractLender> {
	public List<com.zx.emanage.util.gen.entity.WmsCreContractLender> getWmsCreContractLenderList();
	List<Map<String,Object>> getWmsCreContractLenderCards(Map<String,String> params);
	
}
