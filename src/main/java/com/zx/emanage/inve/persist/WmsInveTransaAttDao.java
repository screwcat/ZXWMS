package com.zx.emanage.inve.persist;

import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveTransaAtt;

@MyBatisRepository
public interface WmsInveTransaAttDao extends BaseDao<WmsInveTransaAtt> {
	 /**
     * 根据上单ID删除附件
     * 
     * @param wms_inve_transa_id 上单ID
     */
    void delete(Map<String,Object> param);
	/**
	 * title:deleteForAtt
	 * Description:因为这个表别人也要公用此表，所以删除信息应给定上单信息表主键和资料类别大类
	 * @param param
	 * @author hancd
	 * @date 2015年12月15日 10:51
	 */
	void deleteForAtt(Map<String, Object> param);
	
	
	Map<String, Object> searchAtt(String wms_inve_transa_prod_id);
	
}