package com.zx.emanage.inve.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.emanage.inve.persist.WmsInveOperSituationResultDao;
import com.zx.emanage.inve.service.IWmsInveOperSituationResultService;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveOperSituationResultServiceImpl
 * 模块名称：开资情况审核结果表
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年4月17日
 * @version V3.5
 * history:
 * 1、2017年4月17日 zhangmingjian 创建文件
 */
@Service
public class WmsInveOperSituationResultServiceImpl implements IWmsInveOperSituationResultService
{
    @Autowired
    private WmsInveOperSituationResultDao wmsInveOperSituationResultDao;
    /**
     * 
     * @Title: selectSalaryPreItemByMonth
     * @Description: 查询工资设定列表
     * @return  
     * @author: zhangmingjian
     * @time:2017年4月17日 下午6:28:09
     * history:
     * 1、2017年4月17日 zhangmingjian 创建方法
     */
    @Override
    public List<Map<String, Object>> selectSalaryPreItemByMonth(Map<String,Object> map)
    {
        // TODO Auto-generated method stub
        return wmsInveOperSituationResultDao.selectSalaryPreItemByMonth( map);
    }
    /**
     * 
     * @Title: selectSalaryPreItemByMonthAndDeptId
     * @Description: 查询工资设定列表信息（按部门id查询）
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月17日 下午6:28:30
     * history:
     * 1、2017年4月17日 zhangmingjian 创建方法
     */
    @Override
    public List<Map<String, Object>> selectSalaryPreItemByMonthAndDeptId(Map<String,Object> map)
    {
        return wmsInveOperSituationResultDao.selectSalaryPreItemByMonthAndDeptId(map);
        
    }
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
    @Override
    public List<Map<String, Object>> selectDictInfo(String dict_id)
    {
        // TODO Auto-generated method stub
        return wmsInveOperSituationResultDao.selectDictInfo(dict_id);
    }
    
}
