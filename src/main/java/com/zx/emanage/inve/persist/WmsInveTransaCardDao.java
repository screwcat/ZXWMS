package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveTransaCard;

@MyBatisRepository
public interface WmsInveTransaCardDao extends BaseDao<WmsInveTransaCard>
{
    List<Map<String, Object>> getWmsInveTransaCard(Integer wms_inve_transa_id);

    /**
     * search:根据上单信息主键获取数据
     * 
     * @author ry
     * @param parameters
     * @return
     */
    List<Map<String, Object>> searchByWms_inve_transa_id(Map<String, Object> parameters);

    /**
     * update:金额到账时更新
     * 
     * @author ry
     * @param WmsInveTransaCard
     */
    int updateForJEDZ(WmsInveTransaCard WmsInveTransaCard);
    /**
     * @Title: deleteForInfo 
     * Description :根据上单信息表主键删除对应曾经存储的支付卡信息
     * @param wms_inve_transa_id
     * @author hancd
     * @date 2015年12月14日 15:27
     */
	void deleteForInfo(Integer wms_inve_transa_id);
	/**
	 * @Title:getAmountCheckingReport
	 * Description:【财务管理】【理财上单】【对账报表】 列表数据查询
	 * @param paramMap
	 * @return List
	 * @author hancd
	 * @date 2015年12月17日 10:07
	 */
	List<Map<String, Object>> getAmountCheckingReport(Map<String, Object> paramMap);
	/**
	 * @Title：findCountAmountCheckingReport
	 * Description:【财务管理】【理财上单】【对账报表】 列表数据数查询
	 * @param paramMap
	 * @return int
	 * @author hancd
	 * @date 2015年12月17日 10:09
	 */
	int findCountAmountCheckingReport(Map<String, Object> paramMap);
	/**
     * @Title:searchForJEDZ
     * Description :【财务管理】【理财上单】【金额到账】 列表显示查询
     * 
     * @param paramMap
     * @return record list
     * @author hancd
     * @date 2015年12月17日 15:16
     */
	List<Map<String, Object>> searchForJEDZ(Map<String, Object> paramMap);
	/**
	 * @Title：findCountAmountCheckingReport
	 * Description:【财务管理】【理财上单】【金额到账】 列表显示数据数
	 * @param paramMap
	 * @return int
	 * @author hancd
	 * @date 2015年12月17日 10:09
	 */
	int findCountForJEDZ(Map<String, Object> paramMap);

	/**
	 * @Title：getWmsInveTransaCardByWmsInveTransaId
	 * @Description: 根据上单信息表主键获取对应的支付信息
	 * @param wms_inve_transa_id
	 * @return 
	 * @author donghao
	 * @date 2016年9月1日10:37:04
	 */
	List<Map<String, Object>> getWmsInveTransaCardByWmsInveTransaId(Integer wms_inve_transa_id);

	/**
	 * @Deprecated 根据上单信息表主键获取对应的支付信息
	 * @param wms_inve_transa_id
	 * @return
	 * @author donghao
	 * @date 2016年9月2日16:24:20
	 */
	List<Map<String, Object>> getWmsInveTransaCardInfoByWmsInveTransaId(Integer wms_inve_transa_id);
}