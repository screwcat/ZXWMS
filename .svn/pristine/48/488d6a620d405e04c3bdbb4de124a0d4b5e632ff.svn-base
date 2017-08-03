package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveTransaProtocolDao extends BaseDao<WmsInveTransaProtocol>
{

    WmsInveTransaProtocol getByPk(int wms_inve_transa_prod_id);

    /**
     * 获取客户上单信息
     * 
     * @param paramMap
     * @return zhubo
     */
    public List<Map<String, Object>> getTotalInvestAmount(int wms_inve_customer_id);

    /**
     * 获取赎回申请信息
     * 
     * @param paramMap
     * @return zhubo
     */
    public List<Map<String, Object>> getRedeemApply(Map<String, Object> paramMap);
    
    /**
     * 获取赎回申请信息
     * 
     * @param paramMap
     * @return zhangyunfei
     */
    public List<Map<String, Object>> getRedeemApplyByRedeemId(Map<String, Object> paramMap);

    /**
     * //修改 赎回--理财协议
     * 
     * @param t
     * @return baisong
     */
    int updateforback(WmsInveTransaProtocol t);

    public List<WmsInveTransaProtocol> getListByEntityIsNull(WmsInveTransaProtocol wmsInveTransaProtocol);

    /**
     * 获取协议信息
     * 
     * @param paramMap
     * @return zhubo
     */
    public Map<String, Object> getProtocolData(Map<String, Object> paramMap);

    /**
     * 根据合同编号查询合同内容 baisong
     */
    public List<Map<String, Object>> getInfoByCode(String prot_code);
    /**
     * 暂时不启用2015-01-26 wms_inve_transa_id = $.query.get('wms_inve_transa_id');
     * wms_inve_transa_prod_id = $.query.get('wms_inve_transa_prod_id');查询合同编号
     * baisong
     */
    // public List<Map<String, Object>> getCodeById(Map<String,Object> paramMap)
    // ;
    /**
     * @Title: getAllProtocolForCategoryAndIDCard 
     * @Description: 获取客户的该产品的所有上单信息
     * @param paramMap
     * @return List<WmsInveTransaProtocol> 
     * @throws
     * @author lvtu 
     * @date 2015年8月31日 下午5:16:31
     */
    public List<WmsInveTransaProtocol> getAllProtocolForCategoryAndIDCard(Map<String,Object> paramMap);
    
    /**
     * @Title: setTransaProtocolFailure 
     * @Description: 将原始合同(协议)变为失效
     * @param parseInt void 
     * @throws
     * @author lvtu 
     * @date 2015年11月4日 下午2:02:09
     */
	public void setTransaProtocolFailure(int parseInt);
	
	
	/**
	 * 将原始合同(协议)变为失效
	 * @author jiaodelong 
	 * @param wms_inve_transa_prod_id
	 * @return Integer
	 * @date 2015年12月4日 下午2:02:09
	 */
	public Integer updateProtocolRedeem(@Param("prod_id")Integer wms_inve_transa_prod_id);
	
	/**
	 * 根据条件查询
	 * @date 2016-04-21
     * @param WmsInveTransaProtocol  
     * @return WmsInveTransaProtocol
     * @exception 
     * @author jiaodelong
	 */
	public WmsInveTransaProtocol getProtocolByCondition(WmsInveTransaProtocol protocol);
	
	/**
	 * 根据客户id集合查询所有收益中单据投资金额总和；如果传入客户id集合为空则返回所有客户收益中单据投资金额总和。
	 * @param paramMap map中key=cusIdList的客户id集合
	 * @return
	 */
	public List<Map<String, Object>> getCostomerAmount(Map<String, Object> paramMap);
}