package com.zx.emanage.inve.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveInterimReportService
 * 模块名称：临时报表
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年3月17日
 * @version V3.5
 * history:
 * 1、2017年3月17日 zhangmingjian 创建文件
 */
public interface WmsInveInterimReportService
{
    /**
     * 
     * @Title: InveInterimReportExport
     * @Description: 临时报表导出功能 
     * @author: zhangmingjian
     * @time:2017年3月17日 上午10:43:51
     * history:
     * 1、2017年3月17日 zhangmingjian 创建方法
     */
   public void  InveInterimReportExport(String filename,Map<String,Object> listMap,String out_file_name,HttpServletResponse response);
   
   
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
   public List<Map<String,Object>> selectCustomerInfoReport();
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
   public List<Map<String,Object>> selectProductInfoReport();
   
   
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
