package com.zx.emanage.inve.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.emanage.inve.persist.WmsInvePrintEmptyContractDao;
import com.zx.emanage.inve.service.WmsInvePrintEmptyContractService;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInvePrintEmptyContractServiceImpl
 * 模块名称：空合同打印
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年3月20日
 * @version V3.5
 * history:
 * 1、2017年3月20日 zhangmingjian 创建文件
 */
@Service
public class WmsInvePrintEmptyContractServiceImpl implements WmsInvePrintEmptyContractService
{
    @Autowired
    private WmsInvePrintEmptyContractDao wmsInvePrintEmptyContractDao;

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
    @Override
    public List<Map<String, Object>> selectPrintEmptyContractList(Map<String, Object> map)
    {
        return wmsInvePrintEmptyContractDao.selectPrintEmptyContractList(map);
    }

}
