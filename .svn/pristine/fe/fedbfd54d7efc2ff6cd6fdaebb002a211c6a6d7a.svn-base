package com.zx.emanage.loanfina.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepaymentDetailsAtt;

@MyBatisRepository
public interface WmsFinaCreRepaymentDetailsAttDao extends BaseDao<WmsFinaCreRepaymentDetailsAtt> {
	/**
	 * @Title: addbath 
	 * @Description: 批量插入附件信息
	 * @param wmsFinaCreRepaymentDetailsAtts
	 * @return    
	 * @return int    
	 * @throws
	 * @author lvtu 
	 * @date 2015年6月30日 下午5:50:26
	 */
	public int addbath(List<WmsFinaCreRepaymentDetailsAtt> wmsFinaCreRepaymentDetailsAtts);
	/**
	 * @Title: getWmsFinaCreRepaymentDetailsAtt 
	 * @Description: 获取WmsFinaCreRepaymentDetailsAtt信息
	 * @param wms_fina_cre_repayment_details_id
	 * @return    
	 * @return List<Map<String,Object>>    
	 * @throws
	 * @author lvtu 
	 * @date 2015年7月1日 上午11:49:52
	 */
	public List<Map<String, Object>> getWmsFinaCreRepaymentDetailsAtt(Integer wms_fina_cre_repayment_details_id);
	/**
	 * @Title: delbath 
	 * @Description: 删除多个
	 * @param param    
	 * @return void    
	 * @throws
	 * @author lvtu 
	 * @date 2015年7月1日 下午1:45:10
	 */
	public void delbath(List<Integer> detailIdList);
}