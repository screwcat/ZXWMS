package com.zx.emanage.inve.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.emanage.inve.persist.WmsInveGainCouponDao;
import com.zx.emanage.inve.service.WmsInveGainCouponService;
import com.zx.emanage.inve.vo.WmsInveGainCouponVO;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveGainCouponServiceImpl
 * 模块名称：发放增益券
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年7月29日
 * @version V3.5
 * history:
 * 1、2017年7月29日 zhangmingjian 创建文件
 */
@Service
public class WmsInveGainCouponServiceImpl implements WmsInveGainCouponService
{
    @Autowired
    private WmsInveGainCouponDao wmsInveGainCouponDao;

    /**
     * 
     * @Title: selectWmsInveTransaById
     * @Description:  根据单据编号查询单据信息
     * @param wms_inve_transa_id
     * @return 
     * @author: zhangmingjian
     * @time:2017年7月29日 下午2:17:30
     * history:
     * 1、2017年7月29日 zhangmingjian 创建方法
     */
    @Override
    public Map<String, Object> selectWmsInveTransaById(Integer wms_inve_transa_id)
    {
     Map<String,Object> map =    wmsInveGainCouponDao.selectWmsInveTransaById(wms_inve_transa_id);
     map.put("id_card", null);
        return map;
    }

    /**
     * 
     * @Title: saveGainGouponInfo
     * @Description:  保存增益券发放信息 
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年7月29日 下午2:17:41
     * history:
     * 1、2017年7月29日 zhangmingjian 创建方法
     */
    @Override
    public Integer saveGainGouponInfo(WmsInveGainCouponVO bean,UserBean user)
    {
        Map<String,Object> map = wmsInveGainCouponDao.selectWmsInveTransaById(bean.getWms_inve_transa_id_holder());
        
        map.put("create_user_id", user.getUserId());
        map.put("gain_coupon_code",bean.getGain_coupon_code());
        if(map.get("closing_date")!=null){
            map.put("closing_date", DateUtil.date2String(DateUtil.strDate(map.get("closing_date").toString(), "yyyy年MM月dd日"), "yyyy-MM-dd"));
        }
        return wmsInveGainCouponDao.saveGainGouponInfo(map);
    }
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
    @Override
    public Integer validateGainCouponCode(String gain_coupon_code)
    {
        
        return wmsInveGainCouponDao.validateGainCouponCode(gain_coupon_code);
    }
    /**
     * 
     * @Title: getGainCouponInfo
     * @Description: 校验是否显示增益券页面
     * @param wms_inve_transa_id
     * @return 
     * @author: zhangmingjian
     * @time:2017年7月31日 上午11:07:28
     * history:
     * 1、2017年7月31日 zhangmingjian 创建方法
     */
    @Override
    public Integer getGainCouponInfo(Integer wms_inve_transa_id)
    {
        return wmsInveGainCouponDao.getGainCouponInfo(wms_inve_transa_id);
    }
    
    public static void main(String[] args)
    {
        String dateStr = "2017年07月09日";
        
        System.out.println(DateUtil.date2String(DateUtil.strDate(dateStr, "yyyy年MM月dd日"), "yyyy-MM-dd"));
        
    }

}
