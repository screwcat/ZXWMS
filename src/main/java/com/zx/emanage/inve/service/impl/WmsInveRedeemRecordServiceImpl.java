package com.zx.emanage.inve.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.internal.LinkedTreeMap;
import com.zx.emanage.inve.persist.WmsInveRedeemRecordDao;
import com.zx.emanage.inve.service.IWmsInveRedeemRecordService;
import com.zx.emanage.util.gen.entity.WmsInveRedeemRecord;
import com.zx.sframe.util.DateUtil;

/*
 * @version 2.0
 */

@Service("wmsinveredeemrecordService")
public class WmsInveRedeemRecordServiceImpl implements IWmsInveRedeemRecordService {
	private static Logger log = LoggerFactory.getLogger(WmsInveRedeemRecordServiceImpl.class);

	@Autowired
	private WmsInveRedeemRecordDao wmsinveredeemrecordDao;

    /**
     * @Title: saveWmsInveRedeemRecordBatch
     * @Description:  PTP在还款时（最后一期 或提前全部还款）、回购 生成对应的赎回单据记录
     * @param WmsInveRedeemRecordList
     * @return 
     * @author: zhangyunfei
     * @time:2017年6月21日 下午5:43:15
     * @see com.zx.emanage.inve.service.IWmsInveRedeemRecordService#saveWmsInveRedeemRecordBatch(java.util.List)
     * history:
     * 1、2017年6月21日 zhangyunfei 创建方法
    */
    @Override
    public String saveWmsInveRedeemRecordBatch(List<LinkedTreeMap> list)
    {
        String result = "success";
        try
        {
            // 将treemap转成list
            List<WmsInveRedeemRecord> WmsInveRedeemRecordList = getWmsInveRedeemRecordList(list);
            wmsinveredeemrecordDao.saveWmsInveRedeemRecordBatch(WmsInveRedeemRecordList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result = "error";
        }
        return result;
    }

    private List<WmsInveRedeemRecord> getWmsInveRedeemRecordList(List<LinkedTreeMap> list)
    {
        List<WmsInveRedeemRecord> WmsInveRedeemRecordList = new ArrayList<WmsInveRedeemRecord>();
        WmsInveRedeemRecord wmsInveRedeemRecord = null;
        for (LinkedTreeMap treeMap : list)
        {
            wmsInveRedeemRecord = new WmsInveRedeemRecord();

            wmsInveRedeemRecord.setWms_inve_transa_id(Double.valueOf(treeMap.get("wms_inve_transa_id").toString()).intValue());
            wmsInveRedeemRecord.setPrincipal_amount(new BigDecimal(treeMap.get("principal_amount").toString()));
            wmsInveRedeemRecord.setRedeem_date(DateUtil.getDateAddDays(DateUtil.strDate(treeMap.get("redeem_date").toString(), null), 1));
            wmsInveRedeemRecord.setRedeem_company_reason(treeMap.get("redeem_company_reason").toString());
            wmsInveRedeemRecord.setManagement_fee(new BigDecimal(treeMap.get("management_fee").toString()));
            WmsInveRedeemRecordList.add(wmsInveRedeemRecord);
        }
        return WmsInveRedeemRecordList;
    }
}
