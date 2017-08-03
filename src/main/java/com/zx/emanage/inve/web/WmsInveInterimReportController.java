package com.zx.emanage.inve.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zx.emanage.inve.service.WmsInveInterimReportService;
import com.zx.sframe.util.XlsUtil;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveInterimReportController
 * 模块名称：临时报表导出
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年3月17日
 * @version V3.5
 * history:
 * 1、2017年3月17日 zhangmingjian 创建文件
 */
@Controller
public class WmsInveInterimReportController
{
    @Autowired
    private WmsInveInterimReportService wmsInveInterimReportServiceImpl;

    /**
     * 
     * @Title: InveInterimReportExport
     * @Description: 导出报表
     * @author: zhangmingjian
     * @time:2017年3月17日 上午11:11:22
     * history:
     * 1、2017年3月17日 zhangmingjian 创建方法
     */
    @RequestMapping(value = "/inve/interimReportExport.do")
    @ResponseBody
    public void InveInterimReportExport(HttpServletRequest request, HttpServletResponse response, Integer num)
    {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> data = new ArrayList<>();
        Integer maxnum = 0;
        String out_file_name = "";
        String sheet_name = "";
        String columnStr = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        String datestr = format.format(new Date());
        String title = "";
        
        Integer flag = 0;
        // 如果num==1
        if (num == 1)
        {

            columnStr = "{ 'first_dept_name' : '一级部门', 'second_dept_name' : '二级部门', 'bill_code' : '单据编号', 'bel_salesman_name' : '客户经理', 'bel_department_manager_name' : '部门经理', 'bel_center_manager' : '中分总经理', 'bel_vice_general_manager_name' : '副总经理', 'bel_general_manager_name' : '总经理', 'financial_bill_code' : '合同编号', 'cus_name' : '客户姓名', 'date_of_payment' : '签单时间', 'product_account' : '签单金额', 'category_name' : '签单产品', 'product_deadline' : '产品期数', 'card_no' : '银行卡号', 'bank_of_deposit_pro' : '省 ', 'bank_of_deposit_city' : '市 ', 'bank_of_deposit' : '银行', 'bank_branch' : '支行', 'id_card' : '身份证号', 'type' : '单据类型', 'remark' : '备注' }";
            sheet_name = "Sheet1";
            out_file_name = "截止" + datestr + "收益中数据_平台版本";
            data = wmsInveInterimReportServiceImpl.selectCustomerInfoReport();

        }
        else if (num == 2)
        {

            columnStr = "{'cus_name':'客户姓名','product_account':'签单金额','category_name':'签单产品','date_of_payment':'签单时间','end_of_date':'协议到期日','salesman_name':'客户经理','income_day':'持有公益6号产品天数'}";
            sheet_name = "公益6号产品持有记录单";
            out_file_name = "持有公益6号产品客户清单_截止至" + datestr + "_平台版本";
            data = wmsInveInterimReportServiceImpl.selectProductInfoReport();

        }  
        else if (num == 3)
        {
            String query_date = request.getParameter("query_date");
            title = "数据截止"+query_date + " 24时";
            columnStr = "{'query_date':'日期','accruing_amounts':'昨日开单冠军及累计金额','accruing_number':'昨日件数冠军级累计件数','positive_amount':'转正人员名单及金额','three_million_amount':'过300万人员名单','five_million_amount':'过500万人员名单','ten_million_amount':'过千万人员名单'}";
            sheet_name = "每日数据";
            out_file_name = query_date+"培训部口径数据_平台版本";
            data = wmsInveInterimReportServiceImpl.selectTrainingReport(query_date);
            flag = 1;
            maxnum =1;
        }
        map.put(sheet_name, data);
        org.apache.poi.ss.usermodel.Workbook wb = XlsUtil.createExcel(sheet_name, data, JSON.parseObject(columnStr, LinkedHashMap.class), null, false,maxnum,title);
        try
        {
            XlsUtil.exportExcel(out_file_name, response, wb);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    };
    

}
