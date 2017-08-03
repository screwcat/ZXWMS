package com.zx.emanage.inve.service;

import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveGainCouponVO;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveGainCouponService
 * 模块名称：发放增益券
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年7月29日
 * @version V3.5
 * history:
 * 1、2017年7月29日 zhangmingjian 创建文件
 */
public interface WmsInveGainCouponService
{
    /**
     * 
     * @Title: selectWmsInveTransaById
     * @Description: 根据单据编号查询单据信息
     * @param wms_inve_transa_id
     * @return 
     * @author: zhangmingjian
     * @time:2017年7月29日 上午8:52:30
     * history:
     * 1、2017年7月29日 zhangmingjian 创建方法
     */
    public Map<String,Object> selectWmsInveTransaById(Integer wms_inve_transa_id);
    /**
     * 
     * @Title: saveGainGouponInfo
     * @Description: 保存增益券发放信息
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年7月29日 上午8:49:16
     * history:
     * 1、2017年7月29日 zhangmingjian 创建方法
     */
    public Integer  saveGainGouponInfo(WmsInveGainCouponVO bean,UserBean user);
    /**
     * 
     * @Title: validateGainCouponCode
     * @Description: 校验增益券编号是否重复
     * @param code
     * @return 
     * @author: zhangmingjian
     * @time:2017年7月29日 上午8:50:16
     * history:
     * 1、2017年7月29日 zhangmingjian 创建方法
     */
    public Integer validateGainCouponCode(String gain_coupon_code);

    /**
     * 
     * @Title: getGainCouponInfo
     * @Description: 校验是否显示增益券页面
     * @return 
     * @author: zhangmingjian
     * @time:2017年7月31日 上午10:49:08
     * history:
     * 1、2017年7月31日 zhangmingjian 创建方法
     */
    public Integer getGainCouponInfo(Integer wms_inve_transa_id);
}
