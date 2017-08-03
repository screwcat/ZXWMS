package com.zx.emanage.inve.persist;

import java.util.List;

import com.zx.emanage.inve.vo.WmsInveRedeemRecordVO;
import com.zx.emanage.util.gen.entity.WmsInveRedeemRecord;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveRedeemRecordDao extends BaseDao<WmsInveRedeemRecord> {

    /**
     * @Title: saveWmsInveRedeemRecordBatch
     * @Description: PTP在还款时（最后一期 或提前全部还款）、回购 生成对应的赎回单据记录
     * @param wmsInveRedeemRecordList 
     * @author: zhangyunfei
     * @time:2017年6月21日 下午5:48:29
     * history:
     * 1、2017年6月21日 zhangyunfei 创建方法
    */
    public void saveWmsInveRedeemRecordBatch(List<WmsInveRedeemRecord> wmsInveRedeemRecordList);

    /**
     * @Title: getWmsInveRedeemRecordLastDay
     * @Description:  查询出当前时间前一天的赎回记录表数据
     * @return 
     * @author: zhangyunfei
     * @time:2017年6月23日 上午10:06:13
     * history:
     * 1、2017年6月23日 zhangyunfei 创建方法
    */
    public List<WmsInveRedeemRecordVO> getWmsInveRedeemRecordLastDay();
	
}