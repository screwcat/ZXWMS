package com.zx.emanage.inve.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInveOperSituationResultService;
/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveOperSituationResultController
 * 模块名称：开资情况审核结果
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年4月17日
 * @version V3.5
 * history:
 * 1、2017年4月17日 zhangmingjian 创建文件
 */
@Controller
public class WmsInveOperSituationResultController
{
    @Autowired
    IWmsInveOperSituationResultService wmsInveOperSituationResultService;
    
    /**
     * 
     * @Title: selectSalaryPreItemByMonth
     * @Description: 查询工资设定列表
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月17日 下午6:22:20
     * history:
     * 1、2017年4月17日 zhangmingjian 创建方法
     */
    @RequestMapping(value="/inve/selectSalaryPreItemByMonth.do")
    @ResponseBody
    public  Map<String,Object> selectSalaryPreItemByMonth(String statics_month){
        Map<String,Object> map = new HashMap<>();
        map.put("statics_month", statics_month);
        map.put("Rows", wmsInveOperSituationResultService.selectSalaryPreItemByMonth(map));
        return map;
    };
    /**
     * 
     * @Title: selectSalaryPreItemByMonthAndDeptId
     * @Description: 查询工资设定列表信息（按部门id查询）
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月17日 下午6:23:22
     * history:
     * 1、2017年4月17日 zhangmingjian 创建方法
     */
    @RequestMapping(value="/inve/selectSalaryPreItemByMonthAndDeptId.do")
    @ResponseBody
    public Map<String,Object> selectSalaryPreItemByMonthAndDeptId(String statics_month,String dept_id){
        Map<String,Object> map = new HashMap<>();
        map.put("statics_month", statics_month);
        map.put("dept_id", dept_id);
        map.put("Rows", wmsInveOperSituationResultService.selectSalaryPreItemByMonthAndDeptId(map));
        return map;
    };
    
    /**
     * 
     * @Title: selectDictInfo
     * @Description: 查询数据字典
     * @param dict_id
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月14日 上午9:31:45
     * history:
     * 1、2017年4月14日 zhangmingjian 创建方法
     */

    @RequestMapping(value = "/inve/selectDataStateDictInfo.do")
    @ResponseBody
    public List<Map<String, Object>> selectDictInfo(String dict_id)
    {
        return wmsInveOperSituationResultService.selectDictInfo(dict_id);
    };
}
