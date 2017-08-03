package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveInterimReportDao
 * 模块名称：临时报表
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年3月17日
 * @version V3.5
 * history:
 * 1、2017年3月17日 zhangmingjian 创建文件
 */

@MyBatisRepository
public interface WmsInveInterimReportDao
{
    /**
     * 
     * @Title: selectCustomerInfoReport
     * @Description: 处于收益中单据客户信息详表
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月17日 上午10:41:36
     * history:
     * 1、2017年3月17日 zhangmingjian 创建方法
     */
    public List<Map<String, Object>> selectCustomerInfoReport(Map<String, Object> map);
    
    /**
     * 
     * @Title: selectProductInfoReport
     * @Description: 公益6号产品客户持有记录表
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月17日 上午10:42:01
     * history:
     * 1、2017年3月17日 zhangmingjian 创建方法
     */
    public List<Map<String, Object>> selectProductInfoReport(Map<String, Object> map);
    
    /**
     * 
     * @Title: selectTrainingReport
     * @Description: 培训部所需昨日签单情况统计表
     * @return 
     * @author: zhangmingjian
     * @time:2017年6月2日 下午1:44:23
     * history:
     * 1、2017年6月2日 zhangmingjian 创建方法
     */
    public List<Map<String,Object>> selectTrainingReport(String query_date);
}
