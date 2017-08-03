package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveRedeemRecordVO;
import com.zx.emanage.util.gen.entity.WmsInveRedeemDetail;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveRedeemDetailDao extends BaseDao<WmsInveRedeemDetail>
{
    /**
     * 查询赎回明细
     * 
     * @param wms_inve_redeem_detail_id
     * @return baisong
     */
    List<WmsInveRedeemDetail> getListbypk(Integer wms_inve_redeem_id);

    /**
     * 查询赎回明细 返回一个
     * 
     * @param wms_inve_redeem_detail_id
     * @return baisong
     */
    WmsInveRedeemDetail getOnebypk(WmsInveRedeemDetail wmsInveRedeemDetail);

    /**
     * 赎回修改中使用删除
     * 
     * @param wms_inve_redeem_id
     */
    void deleteForId(Integer wms_inve_redeem_id);

    /**
     * 赎回修改时获取赎回单据表与明细表信息
     * 
     * @param wms_inve_redeem_id
     * @return
     */
    List<Map<String, Object>> getRedeemAllInfo(Integer wms_inve_redeem_id);

    /**
     * @Title: saveWmsInveRedeemDetailPTP
     * @Description: 保存ptp赎回明细表信息
     * @param wmsInveRedeemRecordVO 
     * @author: zhangyunfei
     * @time:2017年6月23日 下午3:03:15
     * history:
     * 1、2017年6月23日 zhangyunfei 创建方法
    */
    public void saveWmsInveRedeemDetailPTP(WmsInveRedeemRecordVO wmsInveRedeemRecordVO);
    

}