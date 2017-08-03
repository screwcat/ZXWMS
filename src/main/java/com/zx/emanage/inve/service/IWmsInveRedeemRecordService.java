package com.zx.emanage.inve.service;

import java.util.List;

import com.google.gson.internal.LinkedTreeMap;

/*
 * @version 2.0
 */

public interface IWmsInveRedeemRecordService {

    /**
     * @Title: saveWmsInveRedeemRecordBatch
     * @Description:  PTP在还款时（最后一期 或提前全部还款）、回购 生成对应的赎回单据记录
     * @param wmsInveRedeemRecordList
     * @return 
     * @author: zhangyunfei
     * @time:2017年6月21日 下午5:40:56
     * history:
     * 1、2017年6月21日 admin 创建方法
    */
    public String saveWmsInveRedeemRecordBatch(List<LinkedTreeMap> list);
}