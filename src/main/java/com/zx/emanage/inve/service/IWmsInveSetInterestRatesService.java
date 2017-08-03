package com.zx.emanage.inve.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface IWmsInveSetInterestRatesService
{
    
    
    public List<Integer> selectRoleInfo(HttpServletRequest request);
    /**
     * 
     * @Title: saveRateInfo
     * @Description: 保存利率
     * @param map
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月24日 下午1:24:00
     * history:
     * 1、2017年4月24日 zhangmingjian 创建方法
     */
    public int saveRateInfo(Map<String,Object> map);
    /**
     * 
     * @Title: selectRateInfo
     * @Description: 查询利率
     * @param map
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月24日 下午1:25:07
     * history:
     * 1、2017年4月24日 zhangmingjian 创建方法
     */
    public Map<String,Object> selectRateInfo(Map<String,Object> map);
}
