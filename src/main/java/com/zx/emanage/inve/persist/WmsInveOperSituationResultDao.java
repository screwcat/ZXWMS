package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveOperSituationResultDao
 * 模块名称：开资情况审核结果表
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年4月17日
 * @version V3.5
 * history:
 * 1、2017年4月17日 zhangmingjian 创建文件
 */
@MyBatisRepository
public interface WmsInveOperSituationResultDao
{
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
    public List<Map<String,Object>> selectSalaryPreItemByMonth(Map<String,Object> map);
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
    public List<Map<String,Object>> selectSalaryPreItemByMonthAndDeptId(Map<String,Object> map);
    /**
     * 
     * @Title: selectDictInfo
     * @Description: 查询数据字典
     * @param dict_id
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月19日 下午7:33:46
     * history:
     * 1、2017年4月19日 zhangmingjian 创建方法
     */
    public List<Map<String,Object>> selectDictInfo(String dict_id);

}
