package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveTransaMatch;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;

@MyBatisRepository
public interface WmsInveTransaMatchDao extends BaseDao<WmsInveTransaMatch>
{
    /**
     * 用于理财 协议中获取匹配债权表中匹配信息 baisong
     **/
    public List<Map<String, Object>> searchforlc(Map<String, Object> hashMap);

    /**
     * 用于理财 协议中获取匹配债权表中匹配信息---赎回 baisong
     **/
    public List<Map<String, Object>> searchforlcsh(Integer wms_inve_transa_prod_id);

    /**
     * 赎回时更新匹配信息 张风山
     **/
    public int updateRedeem(WmsInveTransaMatch wmsInveTransaMatch);

    /**
     * 在修改匹配规则时删除原来得匹配信息 张风山
     **/
    int deleteForMatchRuleChange(Integer wms_inve_transa_prod_id);

    /**
     * 查询所有已经匹配债权的理财产品 张风山
     **/
    List<Integer> selectAllProdForMatch();

    /**
     * 获取卓房宝匹配的房贷债权 张风山
     **/
    List<WmsInveTransaMatch> getMatchForHouse(Integer wms_inve_transa_prod_id);
    /**
     * 获取卓房宝匹配的车贷债权 hancd
     **/
	public List<WmsInveTransaMatch> getMatchForCar(Integer wms_inve_transa_prod_id);
    /**
     * 获取已经匹配的所有债权 还款主键 和 占用债权总额 
     */
	public List<Map<String,Object>> getListByMacthRepay(
			WmsInveTransaMatch wMatch);
	
	/**
	 * @Title: deleteById 
	 * @Description: 删除
	 * @param wms_inve_transa_match void 
	 * @throws
	 * @author lvtu 
	 * @date 2015年10月27日 下午2:49:51
	 */
	public void deleteById(Integer wms_inve_transa_match);
	
	/**
	 * @Description: 根据上单产品主键修改债权的有效性（0：有效；其他失效）
	 * 
	 * @author jiaodelong
	 * @date 2015年11月30日 下午15:15
	 */
	public Integer updateRedeemByProdid(@Param("Prodid")Integer Prodid);

	/**
	 * @Description:根据上单产品表id更新债权匹配表协议id
	 * @author wangyihan
     * @date 2015年12月3日
	 */
	public void updateAgreementIdForMatch(Map<String, Object> paramMap);
	
    /**
     * 债权匹配表中根据债权调整的对应协议表id查询对应信息
     * @date 2015-12-3
     * @param wms_inve_transa_protocol_id  
     * @return record list
     * @exception 
     * @author baisong
     */
	List<Map<String, Object>> getMapByProtocolid(Map<String, Object> paramMap);
	
	
}