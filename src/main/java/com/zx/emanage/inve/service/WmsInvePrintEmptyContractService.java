package com.zx.emanage.inve.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInvePrintEmptyContractService
 * 模块名称：空合同打印
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年3月20日
 * @version V3.5
 * history:
 * 1、2017年3月20日 zhangmingjian 创建文件
 */
public interface WmsInvePrintEmptyContractService
{

    /**
     * 
     * @Title: selectPrintEmptyContractList
     * @Description: 查询空合同打印列表数据
     * @param map
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月20日 上午10:40:59
     * history:
     * 1、2017年3月20日 zhangmingjian 创建方法
     */
    public List<Map<String, Object>> selectPrintEmptyContractList(Map<String, Object> map);
}
