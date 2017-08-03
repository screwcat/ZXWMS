package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralRules;

@MyBatisRepository
public interface WmsInveCommissionGeneralRulesDao extends BaseDao<WmsInveCommissionGeneralRules> {
	 //根据职务编号获取对应信息
	 List<Map<String,Object>>  getList(Map<String,Object> map);
	 /**
     * findCountList:-职务编号---根据查询条件返回记录总条数
     * @author baisong
     * @param paramMaps
     * @return int
     */
	int findCountList(Map<String, Object> paramMaps);
}