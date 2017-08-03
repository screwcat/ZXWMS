package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInveCustomerRemoveHis;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveCustomerRemoveHisDao {
	
	/**
	 * 批量插入crm业务员变更信息
	 * @return
	 */
	public int batchSaveWmsInveCustomerRemoveHis(List<WmsInveCustomerRemoveHis> paramList);
	
	/**
	 * @Title: queryWmsInveCustomerRemoveHisInCompany
	 * @Description: TODO 查询某一个客户归属到公司的记录
	 * @param paramMap
	 * @return 
	 * @author: jinzhiming
	 * @time:2016年11月4日 下午3:34:36
	 * history:
	 * 1、2016年11月4日 jinzhiming 创建方法
	 */
	public List<WmsInveCustomerRemoveHis> queryWmsInveCustomerRemoveHisInCompany(Map<String, Object> paramMap);
	
	/**
	 * @Title: batchUpdateEndOfDateInCompany
	 * @Description: TODO 修改某一客户所属公司的有效时间
	 * @param paramMap 
	 * @author: jinzhiming
	 * @time:2016年11月4日 下午3:40:16
	 * history:
	 * 1、2016年11月4日 jinzhiming 创建方法
	*/
	public void updateEndOfDateInCompany(WmsInveCustomerRemoveHis removeHis);
}
