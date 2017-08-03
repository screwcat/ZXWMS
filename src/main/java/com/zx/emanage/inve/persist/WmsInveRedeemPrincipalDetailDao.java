package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveRedeemRecordVO;
import com.zx.emanage.util.gen.entity.WmsInveRedeemPrincipalDetail;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveRedeemPrincipalDetailDao extends BaseDao<WmsInveRedeemPrincipalDetail> {
	 /**
     * 查询赎回本金表详细
     * @param paramMap
     * @return zhangyunfei
     */
	 List<Map<String, Object>> findListByWmsInveRedeemDetailId(Map<String, Object> paramMap);
	 
	 /**
     * 赎回修改中使用删除
     * 
     * @param wms_inve_redeem_detail_id
     */
    void deleteByDetailId(Integer wms_inve_redeem_detail_id);

    /**
     * 根据赎回本金表的id和对象中存在的试用金额进行修改
     * @param wmsInveRedeemPrincipalDetail
     */
	void updateToUsedIncomeAmount(WmsInveRedeemPrincipalDetail wmsInveRedeemPrincipalDetail);

	/**
	 * 此方法是将根据传入的对象中的id修改对应的试用金额字段的值(修改值的主要逻辑是将原有的使用金额的值+加上传入的使用金额的值)
	 * @param wmsInveRedeemPrincipalDetail
	 * @author donghao
	 * @date 2016年9月18日17:08:42
	 */
	void updatePlusUsedIncomeAmount(WmsInveRedeemPrincipalDetail wmsInveRedeemPrincipalDetail);

    /**
     * @Title: saveWmsInveRedeemPrincipalDetailPTP
     * @Description: 保存ptp赎回本金明细表信息
     * @param wmsInveRedeemRecordVO 
     * @author: zhangyunfei
     * @time:2017年6月23日 下午3:10:47
     * history:
     * 1、2017年6月23日 zhangyunfei 创建方法
    */
    public void saveWmsInveRedeemPrincipalDetailPTP(WmsInveRedeemRecordVO wmsInveRedeemRecordVO);

    /**
     * @Title: updateBatch
     * @Description: 批量更新赎回本金明细表
     * @param listBean 
     * @author: zhangyunfei
     * @time:2017年6月24日 下午5:51:07
     * history:
     * 1、2017年6月24日 admin 创建方法
    */
    public void updateBatch(List<WmsInveRedeemPrincipalDetail> listBean);

}