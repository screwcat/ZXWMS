package com.zx.emanage.inve.persist;

import java.util.List;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveCommissionSpecialRulesPt;

@MyBatisRepository
public interface WmsInveCommissionSpecialRulesPtDao extends BaseDao<WmsInveCommissionSpecialRulesPt> {
	/**
	 * @Title: getPmPersonnels 
	 * @Description: 查询人员信息
	 * @param personnelIds
	 * @return List<WmsInveCommissionSpecialRulesPt> 
	 * @throws
	 * @author lvtu 
	 * @date 2015年9月16日 下午4:18:24
	 */
	public List<WmsInveCommissionSpecialRulesPt> getPmPersonnels(List<String> personnelIds);
	
	/**
	 * @Title: delPecialRulesPts 
	 * @Description: 删除
	 * @param wms_inve_commission_special_rules_id
	 * @return int 
	 * @throws
	 * @author lvtu 
	 * @date 2015年9月16日 下午4:31:03
	 */
	public int delPecialRulesPts(Integer wms_inve_commission_special_rules_id);
	
	/**
	 * @Title: addBatch 
	 * @Description: 批量添加
	 * @param pecialRulesPts void 
	 * @throws
	 * @author lvtu 
	 * @date 2015年9月16日 下午4:32:46
	 */
	public void addBatch(List<WmsInveCommissionSpecialRulesPt> pecialRulesPts);
	
	
	/**
	 * @Title: deletePperson 
	 * @Description: 删除所有对应佣金人员奖励的人员信息
	 * @param Wms_inve_commission_personnel_reward_id
	 * @return int 
	 * @throws
	 * @author baisong
	 * @date 2015年9月16日 下午4:31:03
	 */
	public int deletePerson(Integer wms_inve_commission_personnel_reward_id);
	
	
	/**
	 * @Title: getPersonnelsByPer 
	 * @Description: 查询人员信息
	 * @param wms_inve_commission_personnel_reward_id
	 * @return List<WmsInveCommissionSpecialRulesPt> 
	 * @throws
	 * @author baisong 
	 * @date 2015年9月18日 下午4:18:24
	 */
	public List<WmsInveCommissionSpecialRulesPt> getPersonnelsByPer(Integer wms_inve_commission_personnel_reward_id);
}