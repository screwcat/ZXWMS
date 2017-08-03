package com.zx.sframe.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.alibaba.fastjson.JSON;


public class XlsUtil {
	
	    /**
     * 列名
     */
	public static final String IS_COLUMN = "IS_COLUMN";
	
	    /**
     * 属性值
     */
	public static final String IS_VALAUE = "IS_VALAUE";
	
	    /**
     * 底部
     */
	public static final String IS_BOTTOM = "IS_BOTTOM";
	
	    /**
     * 
     * @param sheetName sheet名称
     * @param list 数据结合（可以是实体类或map）
     * @param columns LinkedHashMap:键值对（key:map的key或实体类属性、value：列名）
     * @param data Map：键值对（key:需要处理列名称，value:map集合（value：值、text：转换文字））
     * @param isShowTotal 是否显示合计
     * @return
     */
	public static <T> Workbook createExcel(String sheetName,List<T> list,
			LinkedHashMap<String, String> columns,Map<String, Map<String, String>> data,Boolean isShowTotal,Integer flag,String tital){
		int topLine = 1;
		List<Map<String, Object>> maps = beanToMap(list);
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet(sheetName);
        CellStyle colStyle = null;
        CellStyle valStyle = null;
        // 设置样式
        if(flag==0){
             colStyle = getColummCellStyle(wb,IS_COLUMN);
             valStyle = getColummCellStyle(wb,IS_VALAUE);
        }else{
             colStyle = getColummCellStyle1(wb,IS_COLUMN);
             valStyle = getColummCellStyle1(wb,IS_VALAUE);
        }
            
        int index = 0;
        Iterator<String> keys = columns.keySet().iterator();		
        Number[] total = new Number[columns.size()];
        short rownumber = 0;
        if (StringUtils.isNotBlank(tital))
        {
            CellStyle colStyle1 = getColummCellStyle(wb,IS_COLUMN);
            Row row1 = (HSSFRow) sheet.createRow(0);  
            Cell cell2 = row1.createCell(0);  
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,columns.size()-1));//合并单元格
            colStyle1.setAlignment(CellStyle.ALIGN_RIGHT);
            cell2.setCellStyle(colStyle1); 
            cell2.setCellValue(tital);
            rownumber =1;
            topLine =2;
        }
        
        
        
        
        while(keys.hasNext()){
            Row row;
            if (index == 0) {
                row = sheet.createRow(rownumber);
            }else{
                row = sheet.getRow(rownumber);
            }
            
            // 处理列头
        	String key = keys.next();
        	sheet.setColumnWidth((short) index, (short) (30.7 * 200));
        	String columnName = columns.get(key);
        	Cell cell = row.createCell(index);
            cell.setCellValue(columnName);
            cell.setCellStyle(colStyle);
            // 处理数据
        	for(int i=0;i<maps.size();i++){
        		Map<String, Object> map = maps.get(i);
        		Row vRow;
    			if (index == 0) {
    				vRow = sheet.createRow((short) i+topLine);
    			}else{
    				vRow = sheet.getRow(i+topLine);
    			}
    			Cell c = vRow.createCell(index);
    			//动态设置行高
//    			vRow.setHeightInPoints(17*flag);
    			String v = map.get(key)==null?"":map.get(key).toString();
    			if(data != null && data.get(key) != null){
    				Map<String, String> d = (Map<String, String>) data.get(key);
    				v = d.get(v)==null?v:d.get(v);
    			}
                c.setCellValue(v);
                c.setCellStyle(valStyle);
                if(isShowTotal != null && isShowTotal){
	                if(NumberUtils.isNumber(v)){
	                	Number num = total[index]==null?0:total[index];
	                	if(NumberUtils.isDigits(v)){
	                		total[index] = num.longValue()+Long.parseLong(v);
	                	}else{
	                		total[index] = num.doubleValue()+Double.parseDouble(v);
	                	}
	                }
                }
        	}
            // 处理合计
        	if(isShowTotal != null && isShowTotal){
        		Row bRow;
    			if (index == 0) {
    				bRow = sheet.createRow((short) maps.size()+topLine);
    			}else{
    				bRow = sheet.getRow( maps.size()+topLine);
    			}
        		if(total[index] == null){
        			if(index == 0 ){
        				Cell c = bRow.createCell(index);
                        c.setCellValue("合计:");
        				c.setCellStyle(valStyle);
        			}else{
        				Cell c = bRow.createCell(index);
        				c.setCellValue(" ");
        				c.setCellStyle(valStyle);
        			}
        		}else{
        			Cell c = bRow.createCell(index);
        			c.setCellValue(total[index].toString());
                    c.setCellStyle(valStyle);
        		}
        	}
        	index++;
        }
        return wb;
	}
	
	/**
	 * 
	 * @param wb
	 * @param flag
	 * @return
	 */
	private static CellStyle getColummCellStyle(Workbook wb,String flag){
		CellStyle cs = wb.createCellStyle();
		Font f = wb.createFont();
		f.setFontHeightInPoints((short) 10);
	    f.setColor(IndexedColors.BLACK.getIndex());
	    if(IS_COLUMN.equals(flag)){
	    	f.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    	cs.setFillForegroundColor(HSSFColor.RED.index);
	    }
	    cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);
       
        return cs;
	}
	//培训部报表excel格式
	   private static CellStyle getColummCellStyle1(Workbook wb,String flag){
	        CellStyle cs = wb.createCellStyle();
	        Font f = wb.createFont();
	        f.setColor(IndexedColors.BLACK.getIndex());
	        if(IS_COLUMN.equals(flag)){
	            f.setBoldweight(Font.BOLDWEIGHT_BOLD);
	            cs.setFillForegroundColor(HSSFColor.RED.index);
	        }
	        cs.setFont(f);
	        cs.setBorderLeft(CellStyle.BORDER_THIN);
	        cs.setBorderRight(CellStyle.BORDER_THIN);
	        cs.setBorderTop(CellStyle.BORDER_THIN);
	        cs.setBorderBottom(CellStyle.BORDER_THIN);
	        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	        cs.setAlignment(CellStyle.ALIGN_CENTER);
	        cs.setWrapText(true); 
	        
	        return cs;
	    }
	    /**
     * 实体类集合转换map集合
     * @param list
     * @return
     */
	public static <T> List<Map<String, Object>> beanToMap(List<T> list){
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		for(T t : list){
			Map<String, Object> result = beanToMap(t);
			if(result != null){
				maps.add(result);
			}
		}
		return maps;
	}
	
	    /**
     * 实体类转换map
     * @param t
     * @return
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> Map<String, Object> beanToMap(T t){
		Map<String, Object> result = null;
		try {
			result = (Map<String, Object>) t;
		} catch (Exception e) {
			result = new HashMap<String, Object>();
			Class clazz = t.getClass();
			try {
				Field[] fields = clazz.getDeclaredFields();
				for(Field f : fields){
					f.setAccessible(true);
					Object value = f.get(t);
					result.put(f.getName(), value);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	
    /**
     * json转LinkedHashMap
     * @param json
     * @return  LinkedHashMap<String,Object>
     */
	public static LinkedHashMap<String,String> jsonToLinkedHashMap(String jsonStr){
		LinkedHashMap<String,String> jo = JSON.parseObject(jsonStr,LinkedHashMap.class);
	 return jo;
	}
	public static List<Map> jsonToListMap(String jsonStr){
		List<Map> list = JSON.parseArray(jsonStr, Map.class);
		return list;
	}
	public static JSON stringToJson(String jsonStr){
		JSON json = JSON.parseObject(jsonStr);
		return json;
	}
	
	    /**
     * 导出excel
     * @param fileName 文件名称
     * @param response 
     * @param wb excel工作博
     * @throws IOException
     */
	public static void exportExcel(String fileName,HttpServletResponse response,Workbook wb) throws IOException{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        export(response, is);
        
	}
	
	    /**
     * 导出
     * @param response
     * @param is 输入流
     * @throws IOException
     */
	public static void export(HttpServletResponse response,InputStream is) throws IOException{
		ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try 
        {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;

            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) 
            {
                bos.write(buff, 0, bytesRead);
            }
        }
        catch (final IOException e) 
        {
            throw e;
        } 
        finally 
        {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
            if (is != null)
            	is.close();
        }
	}
	
}
