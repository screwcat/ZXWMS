package com.zx.emanage.inve.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.emanage.inve.persist.WmsInveInterimReportDao;
import com.zx.emanage.inve.service.WmsInveInterimReportService;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveInterimReportServiceImpl
 * 模块名称：临时报表导出
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年3月17日
 * @version V3.5
 * history:
 * 1、2017年3月17日 zhangmingjian 创建文件
 */
@Service
public class WmsInveInterimReportServiceImpl implements WmsInveInterimReportService
{
    @Autowired
    private WmsInveInterimReportDao wmsInveInterimReportDao;

    /**
            * 
            * @Title: InveInterimReportExport
            * @Description: 临时报表导出功能
            * @author: zhangmingjian
            * @time:2017年3月17日 上午10:44:54
            * history:
            * 1、2017年3月17日 zhangmingjian 创建方法
            */
    @Override
    public void InveInterimReportExport(String filename, Map<String, Object> listMap, String out_file_name, HttpServletResponse response)
    {

    }

    /**
     * 
     * @Title: selectCustomerInfoReport
     * @Description: 处于收益中单据客户信息详表
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月17日 下午2:39:09
     * history:
     * 1、2017年3月17日 zhangmingjian 创建方法
     */
    @Override
    public List<Map<String, Object>> selectCustomerInfoReport()
    {
        // TODO Auto-generated method stub
        return wmsInveInterimReportDao.selectCustomerInfoReport(null);
    }

    /**
     * 
     * @Title: selectProductInfoReport
     * @Description: 公益6号产品客户持有记录表
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月17日 下午2:39:15
     * history:
     * 1、2017年3月17日 zhangmingjian 创建方法
     */
    @Override
    public List<Map<String, Object>> selectProductInfoReport()
    {
        // TODO Auto-generated method stub
        return wmsInveInterimReportDao.selectProductInfoReport(null);
    }
    /**
     * 
     * @Title: selectTrainingReport
     * @Description: 培训部所需昨日签单情况统计表
     * @return 
     * @author: zhangmingjian
     * @time:2017年6月2日 下午1:45:05
     * history:
     * 1、2017年6月2日 zhangmingjian 创建方法
     */
    @Override
    public List<Map<String, Object>> selectTrainingReport(String query_date)
    {
    
        return wmsInveInterimReportDao.selectTrainingReport(query_date);
    }

}
