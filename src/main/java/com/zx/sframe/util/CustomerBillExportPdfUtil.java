package com.zx.sframe.util;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: ITextExportPdfUtil
 * 模块名称：
 * @Description: 由于表格比较复杂  使用itext打印预览pdf
 * @author zhangyunfei
 * @date 2017年4月12日
 * @version V1.0
 * history:
 * 1、2017年4月12日 Administrator 创建文件
 */
 
 
 
public class CustomerBillExportPdfUtil {
    
    public static Font font = null;
	
    /**
     * @Title: doexport
     * @Description: 导出客户收益账单
     * @param cBillObtainedMonth(按月份统计--已获收益账单)
     * @param cBillUnObtainedMonth(按月份统计--待获收益账单)
     * @param cBillObtainedProduct(按产品统计--已获收益账单)
     * @param cBillUnObtainedProduct(按产品统计--待获收益账单)
     * @param cmap(客户信息)
     * @param ids(上单主键集合)
     * @param response 
     * @author: zhangyunfei
     * @time:2017年4月13日 上午9:46:10
     * history:
     * 1、2017年4月13日 Administrator 创建方法
     */
	@SuppressWarnings("unchecked")
    public static void doExport(List<Map<String, Object>> cBillObtainedMonth, List<Map<String, Object>> cBillUnObtainedMonth, List<Map<String, Object>> cBillObtainedProduct, List<Map<String, Object>> cBillUnObtainedProduct, Map<String, Object> cmap, List<String> ids, HttpServletResponse response)
    {
        try
        {
            // 创建BaseFont对象，由于itext不支持中文，所以需要进行字体的设置(指明字体，编码方式,是否嵌入)
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); // 中文处理
            // 创建Font对象，将基础字体对象，字体大小，字体风格
            font = new Font(bfChinese, 7, Font.NORMAL);
            // font1 标题字体样式
            Font font1 = new Font(bfChinese, 15, Font.BOLD);
            // 创建一个document对象
            // Document document = new Document();
            Document document = new Document(PageSize.A4, 0, 0, 30, 30);

            ByteArrayOutputStream bf = new ByteArrayOutputStream();
            // 创建一个PdfWriter实例，将文件输出流指向一个文件
            PdfWriter.getInstance(document, bf);
            // 生成客户信息table
            PdfPTable tableCustomer = setTableCustomer(cmap);
            // 按月份统计----已获收益(1已获收益标识)
            PdfPTable tableCBillObtainedMonth = setTableBillByMonth(cBillObtainedMonth, 1);
            // 按月份统计----待获收益(2待获收益标识)
            PdfPTable tablecBillUnObtainedMonth = setTableBillByMonth(cBillUnObtainedMonth, 2);
            // 按产品统计----已获收益(1已获收益标识)
            PdfPTable tablecBillObtainedProduct = setTableBillByProduct(cBillObtainedProduct, ids, 1);
            // 按产品统计----待获收益(2待获收益标识)
            PdfPTable tablecBillUnObtainedProduct = setTableBillByProduct(cBillUnObtainedProduct, ids, 2);

            document.open();
            Paragraph title = new Paragraph("客户收益账单\n\n", font1);
            // 设置title对其方式
            title.setAlignment(Paragraph.ALIGN_CENTER);
            // title放到document中(按表格顺序)
            document.add(title);
            // tableCustomer(客户table)放到document中
            document.add(tableCustomer);
            // document中换行
            document.add(new Paragraph("\n", font));
            // 按月份统计
            document.add(tableCBillObtainedMonth);
            document.add(new Paragraph("\n", font));
            document.add(tablecBillUnObtainedMonth);
            // 按产品统计
            document.add(new Paragraph("\n", font));
            document.add(tablecBillObtainedProduct);
            document.add(new Paragraph("\n", font));
            document.add(tablecBillUnObtainedProduct);

            document.close();// 关闭文档。

            // 设置浏览器预览文件的格式为PDF
            response.setContentType("application/pdf;charset=utf-8");
            response.setContentLength(bf.size());
            ServletOutputStream out = response.getOutputStream();
            bf.writeTo(out);// 输出预览文件
            out.flush();

       } catch (Exception e) {  
           e.printStackTrace();  
        }
	}

    /**
     * 
     * @Title: setTableCustomer
     * @Description: 客户信息table
     * @param map
     * @return
     * @throws DocumentException 
     * @author: zhangyunfei
     * @time:2017年4月12日 下午4:59:02
     * history:
     * 1、2017年4月12日 Administrator 创建方法
     */
    private static PdfPTable setTableCustomer(Map<String, Object> cmap) throws DocumentException
    {
        // 初始化一个6列的表格
        PdfPTable table = new PdfPTable(6);
        // 设置各列的宽度
        table.setTotalWidth(new float[] { 80, 150, 80, 150, 80, 150 });
        /**
         *  向table中添加cell(td)
         *  getPDFCell方法参数(cell标题,对其方式,cell高度,cell颜色)
         */
        table.addCell(getPDFCell("客户姓名", Element.ALIGN_CENTER, 15, null));
        table.addCell(getPDFCell(cmap.get("cus_name").toString(), Element.ALIGN_CENTER, 15, null));
        table.addCell(getPDFCell("有效证件", Element.ALIGN_CENTER, 15, null));
        table.addCell(getPDFCell(cmap.get("id_card").toString(), Element.ALIGN_CENTER, 15, null));
        table.addCell(getPDFCell("收益月份", Element.ALIGN_CENTER, 15, null));
        table.addCell(getPDFCell(cmap.get("return_date").toString(), Element.ALIGN_CENTER, 15, null));
        // 由于是6列的table 自动换行
        table.addCell(getPDFCell("投资金额", Element.ALIGN_CENTER, 15, null));
        table.addCell(getPDFCell(cmap.get("product_account_num").toString() + "万元", Element.ALIGN_CENTER, 15, null));
        table.addCell(getPDFCell("已获收益", Element.ALIGN_CENTER, 15, null));
        table.addCell(getPDFCell(cmap.get("already_income").toString() + "元", Element.ALIGN_CENTER, 15, null));
        table.addCell(getPDFCell("待获收益", Element.ALIGN_CENTER, 15, null));
        table.addCell(getPDFCell(cmap.get("stay_income").toString() + "元", Element.ALIGN_CENTER, 15, null));

        return table;
    }

    /**
     * @Title: setTableBillByMonth
     * @Description: 根据list生成按月份统计的客户收益账单table
     * @param list 按月份统计的客户收益账单集合
     * @param flag (1已获 2待获)
     * @return
     * @throws DocumentException 
     * @author: zhangyunfei
     * @time:2017年4月13日 上午9:57:51
     * history:
     * 1、2017年4月13日 Administrator 创建方法
     */
    private static PdfPTable setTableBillByMonth(List<Map<String, Object>> list, int flag) throws DocumentException
    {
        // 初始化一个6列表格
        PdfPTable table = new PdfPTable(6);
        // 设置各列的宽度
        table.setTotalWidth(new float[] { 100, 180, 80, 80, 150, 100 });
        // 定义表格顶部的标题栏(根据传入的flag选则生成标题)
        PdfPCell cellTop = null;
        if (flag == 1)
        {
            cellTop = getPDFCell("   已获收益明细-按月份统计", Element.ALIGN_LEFT, 15, null);
        }
        else
        {
            cellTop = getPDFCell("   待获收益明细-按月份统计", Element.ALIGN_LEFT, 15, null);
        }
        // 表格标题栏为一整行,合并6列
        cellTop.setColspan(6);
        // topcell放到table中
        table.addCell(cellTop);

        table.addCell(getPDFCell("收益月份", Element.ALIGN_CENTER, 15, new Color(128, 128, 128)));
        table.addCell(getPDFCell("所投产品信息", Element.ALIGN_CENTER, 15, new Color(128, 128, 128)));
        table.addCell(getPDFCell("收益类型", Element.ALIGN_CENTER, 15, new Color(128, 128, 128)));
        // 根据传入的flag选则收益金额和收益卡标题(已获/待获)
        if (flag == 1)
        {
            table.addCell(getPDFCell("收益金额", Element.ALIGN_CENTER, 15, new Color(128, 128, 128)));
            table.addCell(getPDFCell("客户收益卡", Element.ALIGN_CENTER, 15, new Color(128, 128, 128)));
        }
        else
        {
            table.addCell(getPDFCell("预计收益金额", Element.ALIGN_CENTER, 15, new Color(128, 128, 128)));
            table.addCell(getPDFCell("预计客户收益卡", Element.ALIGN_CENTER, 15, new Color(128, 128, 128)));
        }

        table.addCell(getPDFCell("实际收益日期", Element.ALIGN_CENTER, 15, new Color(128, 128, 128)));

        // 生成表格标题行之后 生成客户收益账单内容
        setTableMonth(table, list);
        // 返回生成好的table
        return table;
    }

    /**
     * @Title: setTableMonth
     * @Description: 生成客户收益账单按产品统计table的内容(按月份统计)
     * @param table(按产品统计的table 包含了标题)
     * @param list 账单集合
     * @return
     * @throws DocumentException 
     * @author: zhangyunfei
     * @time:2017年4月13日 上午10:07:28
     * history:
     * 1、2017年4月13日 Administrator 创建方法
     */
    private static PdfPTable setTableMonth(PdfPTable table, List<Map<String, Object>> list) throws DocumentException
    {
        // 遍历按产品统计的账单集合
        for (Map<String, Object> map : list)
        {
            List<Map<String, Object>> childLis = (List<Map<String, Object>>) map.get("data");
            // 如果childLis为空 不生成对应产品的账单内容
            if (childLis != null && childLis.size() > 0)
            {
                // 收益月份
                String return_date = map.get("return_date").toString();
                // 统计行 收益金额(统计之和)
                BigDecimal count = (BigDecimal) map.get("count");
                // 统计行名称
                String fisrtName = "";
                // index用于合并行标识 只有index=0时候合并，只合并一次
                int index1 = 0;// 合并首列
                int index2 = 0;// 合并末列
                // 遍历childLis账单集合
                for (Map<String, Object> childMap : childLis)
                {
                    // 第一列
                    PdfPCell celli1 = null;
                    // 第六列(末列)
                    PdfPCell celli6 = null;
                    // index1=0 合并第一列单元格 行数为childLis.size()
                    if (index1 == 0)
                    {
                        // 统计行名称赋值
                        fisrtName = return_date;
                        // 获取cell1
                        celli1 = getPDFCell(return_date, Element.ALIGN_CENTER, 15, null);
                        celli1.setRowspan(childLis.size());
                        table.addCell(celli1);
                        // index1++ 只有第1行首列合并 只合并一次
                        index1++;
                    }
                    // 将各列按顺序放到table中
                    table.addCell(getPDFCell(childMap.get("sigend_info").toString(), Element.ALIGN_CENTER, 15, null));
                    table.addCell(getPDFCell(childMap.get("income_type").toString(), Element.ALIGN_CENTER, 15, null));
                    table.addCell(getPDFCell(childMap.get("payable_basic_income").toString(), Element.ALIGN_CENTER, 15, null));
                    table.addCell(getPDFCell(childMap.get("income_card").toString(), Element.ALIGN_CENTER, 15, null));

                    // index2=0 合并末列单元格 行数为childLis.size()
                    if (index2 == 0)
                    {
                        celli6 = getPDFCell(childMap.get("act_return_date") == null ? "" : childMap.get("act_return_date").toString(), Element.ALIGN_CENTER, 15, null);
                        celli6.setRowspan(childLis.size());
                        table.addCell(celli6);
                        // index2++ 只有第1行末列合并 只合并一次
                        index2++;
                    }
                }
                // 将统计行标题放到table底部
                PdfPCell cellBtm = getPDFCell("   " + fisrtName + "合计", Element.ALIGN_LEFT, 10, new Color(217, 217, 217));
                cellBtm.setColspan(3);
                table.addCell(cellBtm);
                // 将统计行金额放到table第四个cell中
                table.addCell(getPDFCell(count.toString(), Element.ALIGN_CENTER, 10, new Color(217, 217, 217)));
                table.addCell(getPDFCell("", Element.ALIGN_CENTER, 10, new Color(217, 217, 217)));
                table.addCell(getPDFCell("", Element.ALIGN_CENTER, 10, new Color(217, 217, 217)));
                // 初始化index 下一产品重新合并
                index1 = 0;
                index2 = 0;
            }

        }

        return table;
    }
    /**
     * @Title: setTableBillByProduct
     * @Description: 根据list生成按产品统计的客户收益账单table
     * @param list 按月份统计的客户收益账单集合
     * @param ids 上单主键list
     * @param flag (1已获 2待获)
     * @return
     * @throws DocumentException 
     * @author: zhangyunfei
     * @time:2017年4月13日 上午9:57:51
     * history:
     * 1、2017年4月13日 Administrator 创建方法
     */
    private static PdfPTable setTableBillByProduct(List<Map<String, Object>> list, List<String> ids, int flag) throws DocumentException
    {
        // 初始化一个6列表格
        PdfPTable table = new PdfPTable(6);
        table.setTotalWidth(new float[] { 180, 100, 80, 80, 150, 100 });
        PdfPCell cellTop = null;
        // 定义表格顶部的标题栏(根据传入的flag选则生成标题)
        if (flag == 1)
        {
            cellTop = getPDFCell("   已获收益明细-按产品统计", Element.ALIGN_LEFT, 15, null);
        }
        else
        {
            cellTop = getPDFCell("   待获收益明细-按产品统计", Element.ALIGN_LEFT, 15, null);
        }
        // 表格标题栏为一整行,合并6列
        cellTop.setColspan(6);
        // topcell放到table中
        table.addCell(cellTop);

        table.addCell(getPDFCell("所投产品信息", Element.ALIGN_CENTER, 15, new Color(128, 128, 128)));
        table.addCell(getPDFCell("收益月份", Element.ALIGN_CENTER, 15, new Color(128, 128, 128)));
        table.addCell(getPDFCell("收益类型", Element.ALIGN_CENTER, 15, new Color(128, 128, 128)));
        // 根据传入的flag选则收益金额和收益卡标题(已获/待获)
        if (flag == 1)
        {
            table.addCell(getPDFCell("收益金额", Element.ALIGN_CENTER, 15, new Color(128, 128, 128)));
            table.addCell(getPDFCell("客户收益卡", Element.ALIGN_CENTER, 15, new Color(128, 128, 128)));
        }
        else
        {
            table.addCell(getPDFCell("预计收益金额", Element.ALIGN_CENTER, 15, new Color(128, 128, 128)));
            table.addCell(getPDFCell("预计客户收益卡", Element.ALIGN_CENTER, 15, new Color(128, 128, 128)));
        }

        table.addCell(getPDFCell("实际收益日期", Element.ALIGN_CENTER, 15, new Color(128, 128, 128)));
        // 生成表格标题行之后 生成客户收益账单内容
        setTableProduct(table, list, ids);

        return table;
    }

    /**
     * @Title: setTableProduct
     * @Description: 生成客户收益账单按产品统计table的内容(按产品统计)
     * @param table(按产品统计的table 包含了标题)
     * @param list 账单集合
     * @param ids  上单主键list
     * @return
     * @throws DocumentException 
     * @author: zhangyunfei
     * @time:2017年4月13日 上午10:07:28
     * history:
     * 1、2017年4月13日 Administrator 创建方法
     */
    private static PdfPTable setTableProduct(PdfPTable table, List<Map<String, Object>> list, List<String> ids) throws DocumentException
    {
        // 遍历 上单主键集合，根据上单主键区分各产品来生成多组账单
        for (String id : ids)
        {
            // 遍历按产品统计的账单集合
            for (Map<String, Object> map : list)
            {
                List<Map<String, Object>> childLis = (List<Map<String, Object>>) map.get("data");
                // 如果childLis为空 不生成对应产品的账单内容
                if (childLis != null && childLis.size() > 0)
                {
                    String wms_inve_transa_id = map.get("wms_inve_transa_id").toString();
                    // 统计行 收益金额(统计之和)
                    BigDecimal count = (BigDecimal) map.get("count");
                    // 统计行名称
                    String fisrtName = "";
                    // index用于合并行标识 只有index=0时候合并，只合并一次
                    int index = 0;
                    // 同一种产品
                    if (id.equals(wms_inve_transa_id.toString()))
                    {
                        // 遍历childLis账单集合
                        for (Map<String, Object> childMap : childLis)
                        {
                            // 第一列的cell
                            PdfPCell celli1 = null;
                            // index=0 合并第一列单元格 行数为childLis.size()
                            if (index == 0)
                            {
                                // 统计行名称赋值
                                fisrtName = childMap.get("sigend_info").toString();
                                // 获取cell1
                                celli1 = getPDFCell(childMap.get("sigend_info").toString(), Element.ALIGN_CENTER, 15, null);
                                // 合并行
                                celli1.setRowspan(childLis.size());
                                table.addCell(celli1);
                                // index++ 只有第行列合并 只合并一次
                                index++;
                            }

                            // 将各列按顺序放到table中
                            table.addCell(getPDFCell(childMap.get("return_date").toString(), Element.ALIGN_CENTER, 15, null));
                            table.addCell(getPDFCell(childMap.get("income_type").toString(), Element.ALIGN_CENTER, 15, null));
                            table.addCell(getPDFCell(childMap.get("payable_basic_income").toString(), Element.ALIGN_CENTER, 15, null));
                            table.addCell(getPDFCell(childMap.get("income_card").toString(), Element.ALIGN_CENTER, 15, null));
                            table.addCell(getPDFCell(childMap.get("act_return_date") == null ? "" : childMap.get("act_return_date").toString(), Element.ALIGN_CENTER, 15, null));

                        }

                        // 将统计行标题放到table底部
                        PdfPCell cellBtm = getPDFCell("   " + fisrtName + "合计", Element.ALIGN_LEFT, 10, new Color(217, 217, 217));
                        // 合并三列作为 统计行标题cell
                        cellBtm.setColspan(3);
                        table.addCell(cellBtm);
                        // 将统计行金额放到table第四个cell中
                        table.addCell(getPDFCell(count.toString(), Element.ALIGN_CENTER, 10, new Color(217, 217, 217)));
                        table.addCell(getPDFCell("", Element.ALIGN_CENTER, 10, new Color(217, 217, 217)));
                        table.addCell(getPDFCell("", Element.ALIGN_CENTER, 10, new Color(217, 217, 217)));
                        // 初始化index 下一产品重新合并
                        index = 0;
                    }
                }

            }
        }
        // 返回生成 包含标题 内容 底部标题的table
        return table;
    }

    /**
     * @Title: getPDFCell
     * @Description: 获取指定内容与字体的单元格
     * @param string cell名称
     * @param align  对其方式
     * @param height cell高度
     * @param color  cell背景颜色
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月13日 上午10:11:47
     * history:
     * 1、2017年4月13日 Administrator 创建方法
     */
    public static PdfPCell getPDFCell(String string, int align, int height, Color color)
    {
        // 创建单元格对象，将内容与字体放入段落中作为单元格内容
        PdfPCell cell = new PdfPCell(new Paragraph(string, font));
        // 水平对其方式
        cell.setHorizontalAlignment(align);
        // 垂直居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        // 设置cell背景颜色
        if (color != null)
        {
            cell.setBackgroundColor(color);

        }
        // 设置最小单元格高度
        cell.setMinimumHeight(height);
        return cell;
    }
}
