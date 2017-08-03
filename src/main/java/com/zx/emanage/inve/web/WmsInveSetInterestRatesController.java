package com.zx.emanage.inve.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInveSetInterestRatesService;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveSetInterestRatesController
 * 模块名称：设定央行利率
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年4月24日
 * @version V3.5
 * history:
 * 1、2017年4月24日 zhangmingjian 创建文件
 */
@Controller
public class WmsInveSetInterestRatesController
{
    @Autowired
    IWmsInveSetInterestRatesService wmsInveSetInterestRatesServiceImpl;
    
    /**
     * 
     * @Title: selectRoleInfo
     * @Description: 校验角色
     * @param request
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月24日 下午1:57:58
     * history:
     * 1、2017年4月24日 zhangmingjian 创建方法
     */
    @RequestMapping(value="/inve/selectRoleInfo.do")
    @ResponseBody
    public List<Integer> selectRoleInfo(HttpServletRequest request){
        
        return wmsInveSetInterestRatesServiceImpl.selectRoleInfo(request);
    };
    /**
     * 
     * @Title: saveRateInfo
     * @Description: 保存利率
     * @param request
     * @param rate
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月24日 下午2:02:12
     * history:
     * 1、2017年4月24日 zhangmingjian 创建方法
     */
    @RequestMapping(value="/inve/saveRateInfo.do")
    @ResponseBody
    public int saveRateInfo(HttpServletRequest request,String rate){
        Map<String,Object> map = new HashMap<>();
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); 
        map.put("user_id", user.getUserId());
        map.put("current_rate", rate);
        return wmsInveSetInterestRatesServiceImpl.saveRateInfo(map);
    };
    /**
     * 
     * @Title: selectRoleInfo
     * @Description: 查询利率
     * @param request
     * @param map
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月24日 下午2:02:30
     * history:
     * 1、2017年4月24日 zhangmingjian 创建方法
     */
    @RequestMapping(value="/inve/selectRateInfo.do")
    @ResponseBody
    public Map<String,Object> selectRateInfo(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        
        return wmsInveSetInterestRatesServiceImpl.selectRateInfo(map);
    };
    
    
}
