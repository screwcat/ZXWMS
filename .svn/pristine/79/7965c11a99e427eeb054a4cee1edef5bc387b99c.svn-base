package com.zx.emanage.loanfina.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.loanfina.vo.WmsFinaCreMortgageListSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsFinaCreMortgageList;

@MyBatisRepository
public interface WmsFinaCreMortgageListDao extends BaseDao<WmsFinaCreMortgageList> {
	/**
	 * 根据给定条件，显示符合条件的抵押物信息
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> getwmsfinacremortgagelistbySelect(
			Map<String, Object> paramMap);
	/**
	 * 根据给定条件，显示符合条件的抵押物信息数据数
	 * @param paramMap
	 * @return
	 */
	int findFinacremortgagelistbyCount(Map<String, Object> paramMap);
	
	  List<Map<String, Object>> getList(WmsFinaCreMortgageListSearchBeanVO bean);
	  /**
	     * findCount:根据查询条件返回记录总条数. <br/>
	     * 
	     * @author Administrator
	     * @param paramMaps
	     * @return
	     * @since JDK 1.6
	     */
	    int findnCount(WmsFinaCreMortgageListSearchBeanVO bean);
}