package com.zx.emanage.inve.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.WmsInveGainCouponService;
import com.zx.emanage.inve.vo.WmsInveGainCouponVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;
/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveGainCouponController
 * 模块名称：发放增益券
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年7月29日
 * @version V3.5
 * history:
 * 1、2017年7月29日 zhangmingjian 创建文件
 */
@Controller
public class WmsInveGainCouponController
{
    @Autowired
    private WmsInveGainCouponService wmsInveGainCouponServiceImpl;
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
    @RequestMapping(value="/inve/selectWmsInveTransaById.do")
    @ResponseBody
    public Map<String,Object> selectWmsInveTransaById(Integer wms_inve_transa_id){
        
        return wmsInveGainCouponServiceImpl.selectWmsInveTransaById(wms_inve_transa_id);
    };
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
    @RequestMapping(value="/inve/saveGainGouponInfo.do")
    @ResponseBody
    public Integer  saveGainGouponInfo(WmsInveGainCouponVO bean,HttpServletRequest request){
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        
        return wmsInveGainCouponServiceImpl.saveGainGouponInfo(bean,user);
    };
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
    @RequestMapping(value="/inve/validateGainCouponCode.do")
    @ResponseBody
    public Integer validateGainCouponCode(String gain_coupon_code)
    {
        
        return wmsInveGainCouponServiceImpl.validateGainCouponCode(gain_coupon_code);
    }
    /**
     * 
     * @Title: validateGainCouponCode
     * @Description: 校验是否显示增益券页面
     * @param wms_inve_transa_id
     * @return 
     * @author: zhangmingjian
     * @time:2017年7月29日 上午8:50:16
     * history:
     * 1、2017年7月29日 zhangmingjian 创建方法
     */
    @RequestMapping(value="/inve/getGainCouponInfo.do")
    @ResponseBody
    public Integer getGainCouponInfo(Integer wms_inve_transa_id)
    {
        
        return wmsInveGainCouponServiceImpl.getGainCouponInfo(wms_inve_transa_id);
    }
 
}
