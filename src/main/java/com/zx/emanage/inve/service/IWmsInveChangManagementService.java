package com.zx.emanage.inve.service;

import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveChangManagementBeanVO;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: IWmsInveChangManagementService
 * 模块名称：变更申请
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年4月11日
 * @version V3.5
 * history:
 * 1、2017年4月11日 zhangmingjian 创建文件
 */
public interface IWmsInveChangManagementService
{
    /**
     * 
     * @Title: getBillNumber
     * @Description: 获取单据编号
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月11日 上午10:15:37
     * history:
     * 1、2017年4月11日 zhangmingjian 创建方法
     */
    public String getBillNumber();

    /**
     * 
     * @Title: selectBillInfo
     * @Description:查询单据列表
     * @param map
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月11日 下午1:14:32
     * history:
     * 1、2017年4月11日 zhangmingjian 创建方法
     */
    public List<Map<String, Object>> selectBillInfo(WmsInveChangManagementBeanVO bean);

    /**
     * 
     * @Title: saveChangeAppInfo
     * @Description: 保存变更单据信息
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月12日 下午1:59:58
     * history:
     * 1、2017年4月12日 zhangmingjian 创建方法
     */
    public String saveChangeAppInfo(WmsInveChangManagementBeanVO bean);

    /**
     * 
     * @Title: selectDictInfo
     * @Description: 查询数据字典数据
     * @param dict_id
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月14日 上午9:27:36
     * history:
     * 1、2017年4月14日 zhangmingjian 创建方法
     */
    public List<Map<String, Object>> selectDictInfo(String dict_id);

    /**
     * 
     * @Title: selectChangeAppList
     * @Description: 变更申请单据列表
     * @param personnel_id
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月14日 下午3:06:42
     * history:
     * 1、2017年4月14日 zhangmingjian 创建方法
     */
    public Map<String, Object> selectChangeAppList(WmsInveChangManagementBeanVO bean);
    /**
     * 
     * @Title: selectChangeAppInfoById
     * @Description: 根据id查询变更单据信息
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月16日 上午10:26:26
     * history:
     * 1、2017年4月16日 zhangmingjian 创建方法
     */
    public Map<String,Object> selectChangeAppInfoById(WmsInveChangManagementBeanVO bean);
}
