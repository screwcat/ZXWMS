package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInveTransaSalesmanHis;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 理财上单业务人员历史表对应dao
 * @author Administrator
 *
 */
@MyBatisRepository
public interface WmsInveTransaSalesmanHisDao extends BaseDao<WmsInveTransaSalesmanHis>{

	/**
	 * 批量插入理财上单业务人员历史信息
	 * @param paramList
	 * @return
	 */
	public int batchSaveWmsInveTransaSalesmanHis(List<Map<String, Object>> paramList);
	
}
