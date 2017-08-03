package com.zx.emanage.inve.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.emanage.inve.persist.WmsInveSetSalesLimitDao;
import com.zx.emanage.inve.service.IWmsInveSetSalesLimitService;
/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveSetSalesLimitServiceImpl
 * 模块名称：设定全集团当日销售限额
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年5月10日
 * @version V3.5
 * history:
 * 1、2017年5月10日 zhangmingjian 创建文件
 */
@Service
public class WmsInveSetSalesLimitServiceImpl implements IWmsInveSetSalesLimitService
{
    @Autowired
    private WmsInveSetSalesLimitDao wmsInveSetSalesLimitDao;
    /**
     * 
     * @Title: selectSalesLimitInfo
     * @Description: 查询全集团当日销售限额
     * @param map
     * @return 
     * @author: zhangmingjian
     * @time:2017年5月10日 上午10:20:35
     * history:
     * 1、2017年5月10日 zhangmingjian 创建方法
     */
    public Map<String,Object> selectSalesLimitInfo(Map<String,Object> map){
        
        
        return wmsInveSetSalesLimitDao.selectSalesLimitInfo(map);
    };
    /**
     * 
     * @Title: saveSalesLimitInfo
     * @Description: 设定全集团当日销售限额
     * @param map
     * @return 
     * @author: zhangmingjian
     * @time:2017年5月10日 上午10:21:13
     * history:
     * 1、2017年5月10日 zhangmingjian 创建方法
     */
    public Integer saveSalesLimitInfo(Map<String,Object> map){
        wmsInveSetSalesLimitDao.updateSalesLimitInfo(map);
        return wmsInveSetSalesLimitDao.saveSalesLimitInfo(map);
    };
}
