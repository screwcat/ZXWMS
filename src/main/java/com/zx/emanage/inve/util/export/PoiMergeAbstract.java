package com.zx.emanage.inve.util.export;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: PoiMergeAbstract
 * 模块名称：导出数据中具有合并单元格需求的处理抽象类
 * @Description: 内容摘要：
 *      导出数据中具有合并单元格
 *      <b>当前只处理具有一个sheet页的模板，多个sheet页没有处理</b>
 *      <b>只处理行合并</b>
 * @author jinzhm
 * @date 2017年5月11日
 * @version V1.0
 * history:
 * 1、2017年5月11日 jinzhm 创建文件
 */
public abstract class PoiMergeAbstract
{

    protected Workbook book;

    protected InputStream is;

    protected OutputStream os;

    public static final String SHEET_NAME = "SHEET_NAME";

    public static final String START_ROW_NUM = "START_ROW_NUM";

    public static final String DATA_LIST = "DATA_LIST";

    /**
     * @Title: export
     * @Description: 导出
     * @param templateFileName 模板文件名称
     * @param exportFileName 导出文件名称
     * @param dataMap 数据集合
     * @param response 响应信息
     * @author: jinzhm
     * @time:2017年5月11日 下午3:51:44
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
     */
    public void export(String templateFileName, String exportFileName, Map<String, Object> dataMap,
                       HttpServletResponse response)
    {
        // 模板路径
        String templateFilePath = PoiMergeAbstract.class.getResource("/").getPath() + "reportTemplate/"
                                  + templateFileName;
        // 创建工作薄
        createWorkBook(templateFilePath);
        // 写数据
        writeData(dataMap);
        // 导出excel
        exportExcel(exportFileName, response);
    }

    /**
     * @Title: writeData
     * @Description: 写入数据
     * @param dataMap 数据集合
     * @author: jinzhm
     * @time:2017年5月11日 下午3:51:31
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
     */
    @SuppressWarnings("unchecked")
    protected void writeData(Map<String, Object> dataMap)
    {
        // 获得第一个sheet页对象
        Sheet sheet = book.getSheetAt(0);
        int startRowNum = getStartRowNum(dataMap);
        Map<String, Object> labelMap = getLabels(sheet, startRowNum);

        // 取出模板中设置的标签
        List<String> labels = (List<String>) labelMap.get("labels");
        // 行高度
        short rowHeight = (short) labelMap.get("rowHeight");

        // 处理行数
        int rowCount = startRowNum;

        // 获得要导出的数据集合
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) dataMap.get(DATA_LIST);
        
        // 如果数据集合是空集合
        if (dataList.isEmpty())
        {
            Row row = sheet.createRow(rowCount);// 创建行
            row.setHeight(rowHeight);
            for (int labelIndex = 0; labelIndex < labels.size(); labelIndex++)
            {
                Cell cell = row.createCell(labelIndex);// 创建单元格
                cell.setCellValue("");
            }
            return;
        }

        // 循环所有数据
        for (Map<String, Object> map : dataList)
        {
            // 创建一行数据
            Row row = sheet.createRow(rowCount);
            // 设置行高度
            row.setHeight(rowHeight);
            // 获得不需要合并的集合
            List<Map<String, Object>> unMergeList = (List<Map<String, Object>>) map.get(getUnMergeListKeyName());
            // 要合并的行数
            int mergeCount = unMergeList.size();
            // 循环模板中设置的标签
            for (int i = 0; i < labels.size(); i++)
            {
                // 获得标签
                String label = labels.get(i);

                // 判断标签是否等于"null"
                if ("null".equals(labels.get(i)))
                {
                    // 如果是则跳出本次循环
                    continue;
                }

                // 获得单元格类型及样式
                int types = ((List<Integer>) labelMap.get("types")).get(i);
                CellStyle styles = ((List<CellStyle>) labelMap.get("styles")).get(i);

                // 是不需要合并的标签
                if (isExceptMergeLabel(labels.get(i)))
                {
                    // 如果不需要合并的数据大于1条，需要合并单元格其他单元格
                    if (unMergeList.size() > 1)
                    {
                        // 不合并的行对象
                        Row unMergeRow = null;
                        // 循环所有不需要合并的数据
                        for (int k = 0; k < unMergeList.size(); k++)
                        {
                            // 获得一条不需要合并的数据
                            Map<String, Object> unMergeMap = unMergeList.get(k);
                            // 如果是处理第一条，直接用之前创建的要合并单元格的行对象
                            if (k == 0)
                            {
                                unMergeRow = row;
                            }
                            // 如果不是处理第一条，通过在sheet页中获得，获得不到创建
                            else
                            {
                                unMergeRow = sheet.getRow(rowCount + k) == null ? sheet.createRow(rowCount + k)
                                                                               : sheet.getRow(rowCount + k);
                            }
                            // 设置行高度
                            unMergeRow.setHeight(rowHeight);
                            // 创建单元格
                            Cell cell = unMergeRow.createCell(i);
                            // 设置值
                            setCellValue(unMergeMap, label, types, styles, cell);
                        }
                    }
                    // 不需要合并
                    else
                    {
                        // 在行内创建单元格
                        Cell cell = row.createCell(i);
                        // 设置值
                        setCellValue(map, label, types, styles, cell);
                    }
                }
                // 是需要行合并的标签
                else
                {
                    // 如果支付方式多于一种，需要合并单元格
                    if (unMergeList.size() > 1)
                    {
                        // 创建合并单元格对象
                        CellRangeAddress cra = new CellRangeAddress(rowCount, rowCount + mergeCount - 1, i, i);
                        sheet.addMergedRegion(cra);
                    }
                    // 在行内创建单元格
                    Cell cell = row.createCell(i);
                    // 设置值
                    setCellValue(map, label, types, styles, cell);
                }
            }
            // 进入下次循环之前，将处理行号加上本次合并的行数
            rowCount += mergeCount;
        }
    }

    /**
     * @Title: setCellValue
     * @Description: 设置单元格值
     * @param map 数据集合
     * @param label 标签
     * @param types 单元格类型
     * @param styles 单元格样式
     * @param cell 单元格
     * @author: jinzhm
     * @time:2017年5月11日 下午2:32:03
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
     */
    private void setCellValue(Map<String, Object> map, String label, int types, CellStyle styles, Cell cell)
    {
        // 判断根据标签重结果集中获取的数据是否为空
        if (map.get(label) != null)
        {
            // 设置单元的类型和样式(此处的类型和样式都是获取模板中的设置的类型和样式)
            cell.setCellType(types);
            cell.setCellStyle(styles);
            // 判断获取的数据是否是字符串类型
            if (map.get(label) instanceof String)
            {
                cell.setCellValue(map.get(label).toString());
            }
            // 判断获取的数据是否是数字类型
            if (map.get(label) instanceof Number)
            {

                if (new BigDecimal(map.get(label).toString()).compareTo(BigDecimal.ZERO) == 0)
                {
                    cell.setCellValue(0);
                }
                else
                {
                    cell.setCellValue(new BigDecimal(map.get(label).toString()).doubleValue());
                }
            }
        }
        else
        {
            cell.setCellType(types);
            cell.setCellStyle(styles);
        }
    }

    /**
     * @Title: exportExcel
     * @Description: 导出excel
     * @param exportFileName 导出文件名称
     * @param response 响应信息
     * @author: jinzhm
     * @time:2017年5月11日 上午11:22:51
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
     */
    protected void exportExcel(String exportFileName, HttpServletResponse response)
    {
        try
        {
            exportFileName = new String(exportFileName.getBytes("GBK"), "ISO8859_1");
            response.setHeader("content-disposition", "attachment; filename=" + exportFileName);
            response.setContentType("application/msexcel");

            os = response.getOutputStream();
            book.write(os);
            os.flush();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        closeAll();
    }

    /**
     * @Title getLabels
     * @Description 根据当前传入的sheet对象获取excel模板中指定的行中写的标签,同时获取对应标签所设置的单元格的格式
     * @param sheet
     *            sheet对象
     * @param startReadRowNum
     *            开始写入数据的行号
     * @return map集合,集合中分别存放的是模板中设置的标签,以及模板中对每个标签需要显示的数据设置的样式和类型
     * @author DongHao
     * @Time 2016年11月24日 17:42:45
     * @history 1. 2016年11月24日17:43:16 DongHao 创建方法
     */
    protected Map<String, Object> getLabels(Sheet sheet, int startReadRowNum)
    {
        Map<String, Object> map = new HashMap<String, Object>();

        List<String> labels = new ArrayList<String>();// 标签集合
        List<CellStyle> styles = new ArrayList<CellStyle>();// 单元格的样式集合
        List<Integer> types = new ArrayList<Integer>();

        Row row = sheet.getRow(startReadRowNum);// 获取指定行
        map.put("rowHeight", row.getHeight());

        for (int i = 0; i < row.getLastCellNum(); i++)
        {
            Cell cell = row.getCell(i);
            if (StringUtils.isBlank(cell.getStringCellValue()))
            {
                CellStyle stye = cell.getCellStyle();
                styles.add(stye);
                labels.add("null");
                types.add(cell.getCellType());
            }
            else
            {
                CellStyle stye = cell.getCellStyle();
                styles.add(stye);
                labels.add(cell.getStringCellValue());
                types.add(cell.getCellType());
            }
        }
        map.put("labels", labels);
        map.put("styles", styles);
        map.put("types", types);
        return map;
    }

    /**
     * @Title: createWorkBook
     * @Description: 根据地址创建工作薄
     * @param filePath excel模板地址
     * @return 工作薄对象
     * @author: jinzhm
     * @time:2017年5月11日 下午3:50:44
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
     */
    public Workbook createWorkBook(String filePath)
    {
        try
        {
            is = new BufferedInputStream(new FileInputStream(filePath));
            book = new HSSFWorkbook(is);
        }
        catch (Exception e)
        {
            try
            {
                is = new BufferedInputStream(new FileInputStream(filePath));
                book = new XSSFWorkbook(is);
            }
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }


        return book;
    }

    /**
     * @Title: closeStream
     * @Description: 关闭流资源
     * @author: jinzhm
     * @time:2017年5月11日 下午3:50:24
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
     */
    public void closeStream()
    {
        try
        {
            if (is != null)
            {
                is.close();
            }
            if (os != null)
            {
                os.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @Title: closeAll
     * @Description: 关闭所有资源
     * @author: jinzhm
     * @time:2017年5月11日 下午3:50:15
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
     */
    public void closeAll()
    {
        closeStream();
        try
        {
            if (book != null)
            {
                book.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @Title: getStartRowNum
     * @Description: 获得开始写入数据和读取模板标签行号
     * @param dataMap 导出相关设置数据集合
     * @return 没有设置默认返回第一行
     * @author: jinzhm
     * @time:2017年5月11日 上午11:30:31
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
     */
    public int getStartRowNum(Map<String, Object> dataMap)
    {
        if (dataMap.get(START_ROW_NUM) == null)
        {
            return 1;
        }
        else
        {
            try
            {
                return Integer.parseInt(String.valueOf(dataMap.get(START_ROW_NUM)));
            }
            catch (NumberFormatException e)
            {
                return 1;
            }
        }
    }

    /**
     * @Title: getNeedMergeListKeyName
     * @Description: 获得需要合并的集合在map中的key
     * @return map中的key
     * @author: jinzhm
     * @time:2017年5月11日 下午3:44:30
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
     */
    protected abstract String getUnMergeListKeyName();

    /**
     * @Title: getExceptMergeLabel
     * @Description: 获得不需要合并的标签名称
     * @return 标签名称，多个使用英文逗号隔开
     * @author: jinzhm
     * @time:2017年5月11日 下午3:44:55
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
     */
    protected abstract String getExceptMergeLabel();

    /**
     * @Title: isExceptMergeLabel
     * @Description: 判断是不是不合并的标签
     * @param label 标签名称
     * @return true表示是不合并的标签
     * @author: jinzhm
     * @time:2017年5月11日 下午2:22:22
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
     */
    private boolean isExceptMergeLabel(String label)
    {
        if (("," + getExceptMergeLabel() + ",").indexOf("," + label + ",") > -1)
        {
            return true;
        }
        return false;
    }
}
