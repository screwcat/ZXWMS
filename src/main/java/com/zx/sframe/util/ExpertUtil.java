package com.zx.sframe.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
 * @ClassName: ExpertUtil
 * @Description: 内容摘要:利用模板导出excel
 * @author DongHao
 * @date 2016年11月24日
 * @version V1.0
 * @history 1. 2016年11月24日 DongHao 创建文件
 */
public class ExpertUtil {

    /**
     * 操作excel的工作薄
     */
    private Workbook workBook = null;

    /**
     * 输入流
     */
    private InputStream is = null;

    /**
     * 输出流
     */
    private OutputStream os = null;

    /**
     * @Title expertExcel
     * @Description 内容摘要：根据传入合理的参数进行导出excel文件 
     *          {注意：
     *              1.在组合listMap参数时,存放每个sheet页面的数据时,map的key一定要与对应的excel模板的sheet名称一致 ,否则程序无法区分那个数据是属于那个sheet页面的。
     *              2.在组合listMap参数时,存放每个sheet页面的标题时,存放的是一个map数据,并且map的key一定要与对应的excel模板的sheet名称一致。
     *              3.Excel中如果存在序号列,则将标签设置成index。 
     *              4.excel模板中设置的标签一定要与结果集中的key一致.
     *              5.该工具类中新增加合并列和合并行的功能.
     *                (1). 需要合并行或者列时需要在数据集合listMap中增加mergeMap参数
     *                (2). mergeMap中设置的参数如下：
     *                     mergeMap.put("start", 0);//合并列的时候表示开始列,合并行的时候表示开始行
     *                     mergeMap.put("end", 3);//合并列的时候表示截止合并的列,合并行的时候表示截止行
     *                     mergeMap.put("type", 0);//type类型为0:表示合并列;1:表示合并行
     *                     mergeMap.put("cellIndex", 0);//合并行时需要传入从第几列开始合并
     *                     mergeMap.put("indexStr", "合计");//表示需要触发合并列时需要包含的信息进行合并
     *                (3). 需要合并行或者列的操作,需要传mergeMap参数,如果不需要合并则不用传入该参数
     *          }
     * @param filename 模板名称
     * @param map 数据集合{
     *                      该数据中包括三种数据：
     *                      1.将每个sheet页面的数据添加到map集合时,map的key存放的是每个sheet名称;
     *                      2.每个sheet页面的标题数据;
     *                      3.需要合并信息的数据对象;
     *                  }
     * @param out_file_name 导出的文件名称
     * @param titleInMapKeyValue excel每个页面的sheet页面的title,存放在数据集合中的key
     * @param startReadRowNum 写入excel数据行数
     * @param response response对象
     * @author DongHao
     * @Time 2016年11月24日 17:42:45
     * @history 1. 2016年11月24日17:43:16 DongHao 创建方法
     */
    public void expertExcel(String filename, Map<String, Object> map, String out_file_name, String titleInMapKeyValue, int startReadRowNum, HttpServletResponse response)
    {
        String filepath = PoiExportUtil.class.getResource("/").getPath();
        filepath = filepath + "reportTemplate/";

        // 模板路径
        String in_file_path = filepath + filename;

        try
        {

            out_file_name = new String(out_file_name.getBytes("GBK"), "ISO8859_1");

            response.setHeader("content-disposition", "attachment; filename=" + out_file_name);
            response.setContentType("application/msexcel");

            os = response.getOutputStream();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        workBook = createWorkBook(in_file_path);

        expert(map, titleInMapKeyValue, startReadRowNum);

        close();
    }

    /**
     * @Title createWorkBook
     * @Description 内容摘要：根据模板的路径创建不同版本的excel工作薄
     * @param filepath
     *            模板路径
     * @return 返回excel工作薄
     * @author DongHao
     * @Time 2016年11月24日 17:42:45
     * @history 1. 2016年11月24日17:43:16 DongHao 创建方法
     */
    private Workbook createWorkBook(String filepath)
    {
        Workbook workbook = null;
        boolean flag = true;
        // 为了兼容不同版本的excel,所以分别创建两个不同的workbook
        // 当创建HSSFWorkbook时如果报错则将flag设置成flalse
        try
        {
            is = new BufferedInputStream(new FileInputStream(filepath));
            workbook = new HSSFWorkbook(is);
        }
        catch (Exception e)
        {
            flag = false;
        }
        finally
        {
            try
            {
                if (is != null)
                {
                    is.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        // 如果flag=false则进行创建XSSFWorkbook
        // 这里又重新创建一遍is是因为如果创建HSSFWorkbook时报错,在poi里面会对is进行关闭,所以这里重新创建一遍is
        if (!flag)
        {
            try
            {
                is = new BufferedInputStream(new FileInputStream(filepath));
                workbook = new XSSFWorkbook(is);
            }
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }

        return workbook;
    }

    /**
     * @Title expert
     * @Description 内容摘要：该方法主要是执行导出excel的主要逻辑
     * @param listMap
     *            数据集合
     * @param titleInMapKeyValue
     *            title所在集合中的key值
     * @param startReadRowNum
     *            开始写入数据的行号
     * @author DongHao
     * @Time 2016年11月24日 17:42:45
     * @history 1. 2016年11月24日17:43:16 DongHao 创建方法
     */
    @SuppressWarnings("unchecked")
    private void expert(Map<String, Object> listMap, String titleInMapKeyValue, int startReadRowNum)
    {
        // 获取模板中一共有多少个sheet页面
        int sheets = workBook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++)
        {
            Sheet sheet = workBook.getSheetAt(i);
            // 获取数据集合中的title数据
            Map<String, Object> titles = (Map<String, Object>) listMap.get(titleInMapKeyValue);
            if (titleInMapKeyValue != null && !"".equals(titleInMapKeyValue))
            {
                // 获取设置title的列数
                int cellNum = titles.containsKey(sheet.getSheetName() + "_title_cell") ? Integer.parseInt(String.valueOf(titles.get(sheet.getSheetName()
                                                                                                                                    + "_title_cell")))
                                                                                      : 0;
                // 根据sheet名称获取对应的title数据中的值
                setSheetTitle(0, sheet, titles.get(sheet.getSheetName()) == null ? ""
                                                                                : titles.get(sheet.getSheetName())
                                                                                        .toString(), cellNum);
            }
            // 获取sheet页面中指定的行数单元格的标签、样式、类型数据
            Map<String, Object> resultMap = getLabels(sheet, startReadRowNum);
            // 取出模板中设置的标签
            List<String> labels = (List<String>) resultMap.get("labels");

            // 根据sheet名称获取数据集合中的数据值
            List<Map<String, Object>> list = (List<Map<String, Object>>) listMap.get(sheet.getSheetName());
            int rowIndex = startReadRowNum;
            Row row = null;
            
            //从数据集合中取出合并的信息
            Map<String, Object>  mergeMap = null;
            
            try
            {
                mergeMap = (Map<String, Object>) listMap.get("mergeMap");
            }
            catch (Exception e)
            {
                mergeMap = null;
            }
            
            if (list != null && list.size() > 0)
            {
                // 遍历结果集合
                for (int j = 0; j < list.size(); j++)
                {

                    row = sheet.createRow(rowIndex);// 创建行
                    row.setHeight((short) resultMap.get("rowHeight"));
                    Map<String, Object> map = list.get(j);// 获取一行的数据

                    for (int k = 0; k < labels.size(); k++)
                    {// 遍历标签集合

                        Cell cell = row.createCell(k);// 创建单元格

                        if ("null".equals(labels.get(k)))
                        {// 判断标签是否等于"null"
                         // 如果是则跳出本次循环
                            continue;
                        }

                        if ("index".equals(labels.get(k)))
                        {// 判断循环出的标签名称是否与index相等,相等则说明表格中存在需要列,则设置序号否则跳出本次循环
                            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                            cell.setCellValue(j + 1);
                            continue;
                        }
                        // 判断根据标签重结果集中获取的数据是否为空
                        if (map.get(labels.get(k)) != null)
                        {
                            // 设置单元的类型和样式(此处的类型和样式都是获取模板中的设置的类型和样式)
                            cell.setCellType(((List<Integer>) resultMap.get("types")).get(k));
                            cell.setCellStyle(((List<CellStyle>) resultMap.get("styles")).get(k));
                            // 判断获取的数据是否是字符串类型
                            if (map.get(labels.get(k)) instanceof String)
                            {
                                String value = map.get(labels.get(k)).toString();
                                
                                //判断合并信息是否为空
                                if(mergeMap != null && mergeMap.size() > 0)
                                {
                                    int type = Integer.parseInt(mergeMap.get("type").toString());
                                    
                                    String indexStr = mergeMap.get("indexStr").toString();
                                    
                                    int start = Integer.parseInt(mergeMap.get("start").toString());
                                    
                                    int end = Integer.parseInt(mergeMap.get("end").toString());
                                    
                                    if(type == 0)
                                    {
                                        //判断值是否包含合计两个子  如果包含合计两个字则进行单元格合并
                                        if(value.contains(indexStr))
                                        {
                                            sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, start, end));  
                                        }
                                    }
                                    else if(type == 1)
                                    {
                                        
                                        int cellIndex = Integer.parseInt(mergeMap.get("cellIndex").toString());
                                        
                                        //判断值是否包含合计两个子  如果包含合计两个字则进行单元格合并
                                        if(value.contains(indexStr))
                                        {
                                            sheet.addMergedRegion(new CellRangeAddress(start, end, cellIndex, cellIndex));  
                                        }
                                    }
                                }
                                
                                cell.setCellValue(map.get(labels.get(k)).toString());
                            }
                            // 判断获取的数据是否是数字类型
                            if (map.get(labels.get(k)) instanceof Number)
                            {

                                if (new BigDecimal(map.get(labels.get(k)).toString()).compareTo(BigDecimal.ZERO) == 0)
                                {
                                    cell.setCellValue(0);
                                }
                                else
                                {
                                    cell.setCellValue(new BigDecimal(map.get(labels.get(k)).toString()).doubleValue());
                                }
                            }
                        }
                        else
                        {
                            cell.setCellType(((List<Integer>) resultMap.get("types")).get(k));
                            cell.setCellStyle(((List<CellStyle>) resultMap.get("styles")).get(k));
                        }
                    }

                    rowIndex++;
                }
            }
            else
            {
                row = sheet.createRow(rowIndex);// 创建行
                row.setHeight((short) resultMap.get("rowHeight"));
                for (int labelIndex = 0; labelIndex < labels.size(); labelIndex++)
                {
                    Cell cell = row.createCell(labelIndex);// 创建单元格
                    cell.setCellValue("");
                }
            }
        }
        try
        {
            workBook.write(os);
            os.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * @Title setSheetTitle
     * @Description 内容摘要：设置每个sheet页的title
     * @param rowIndex
     *            设置title的行号
     * @param sheet
     *            设置的sheet页面sheet对象
     * @param titleName
     *            设置的标题名称
     * @param cellNum
     *            列号
     * @author DongHao
     * @Time 2016年11月24日 17:42:45
     * @history 1. 2016年11月24日17:43:16 DongHao 创建方法
     */
    private void setSheetTitle(int rowIndex, Sheet sheet, String titleName, int cellNum)
    {
        if (titleName != null && !"".equals(titleName))
        {
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(cellNum);
            cell.setCellType(cell.getCellType());
            cell.setCellStyle(cell.getCellStyle());
            cell.setCellValue(titleName);
        }
    }

    /**
     * @Title getLabels
     * @Description 内容摘要：根据当前传入的sheet对象获取excel模板中指定的行中写的标签,同时获取对应标签所设置的单元格的格式
     * @param sheet
     *            sheet对象
     * @param startReadRowNum
     *            开始写入数据的行号
     * @return map集合,集合中分别存放的是模板中设置的标签,以及模板中对每个标签需要显示的数据设置的样式和类型
     * @author DongHao
     * @Time 2016年11月24日 17:42:45
     * @history 1. 2016年11月24日17:43:16 DongHao 创建方法
     */
    private Map<String, Object> getLabels(Sheet sheet, int startReadRowNum)
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
            {// 如果获取到的标签值为空则跳出本次循环
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
     * @Title close
     * @Description 内容摘要：关闭操作excel时所使用的输入输出流以及excel的工作薄
     * @author DongHao
     * @Time 2016年11月24日 17:42:45
     * @history 1. 2016年11月24日17:43:16 DongHao 创建方法
     */
    private void close()
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
            if (workBook != null)
            {
                workBook.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
