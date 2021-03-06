package com.zx.emanage.inve.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInveSetSalesLimitService;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;
/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveSetSalesLimitController
 * 模块名称：设定全集团当日销售限额
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年5月10日
 * @version V3.5
 * history:
 * 1、2017年5月10日 zhangmingjian 创建文件
 */
@Controller
public class WmsInveSetSalesLimitController
{
    @Autowired
    private IWmsInveSetSalesLimitService wmsInveSetSalesLimitServiceImpl;
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
    @RequestMapping(value="/inve/selectSalesLimitInfo.do")
    @ResponseBody
    public Map<String,Object> selectSalesLimitInfo(Map<String,Object> map,HttpServletRequest request){
        SimpleDateFormat sd  = new SimpleDateFormat("yyyy-MM-dd");
        map.put("create_timestamp", sd.format(new Date()));
        return wmsInveSetSalesLimitServiceImpl.selectSalesLimitInfo(map);
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
    @RequestMapping(value="/inve/saveSalesLimitInfo.do")
    @ResponseBody
    public Integer saveSalesLimitInfo(Map<String,Object> map,HttpServletRequest request,String limit_amount){
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        map.put("create_user_id", user.getUserId());
        map.put("limit_amount", StringUtils.isBlank(limit_amount)?null:limit_amount);
        SimpleDateFormat sd  = new SimpleDateFormat("yyyy-MM-dd");
        map.put("create_timestamp", sd.format(new Date()));
        return wmsInveSetSalesLimitServiceImpl.saveSalesLimitInfo(map);
    };
}
