package com.zx.sframe.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.zx.platform.syscontext.PlatformGlobalVar;


/**
 * @ClassName: PoiUtilDetailTitle
 * @Description: POI导出工具类
 * @author WangShuai
 * @date 2016年12月2日
 * @version V1.0
 * history:
 * 1、2016年12月2日 WangShuai 创建文件
 */
@SuppressWarnings("deprecation")
public class PoiUtilDetailTitle {
	
		
		private HSSFCellStyle titleStyle = null;
		private HSSFCellStyle bodyStyle = null;
		
		  /**
		   * 初始化样式
		   */
		private void init(HSSFWorkbook wb) {
		          titleFont(wb);
		          bodyFont(wb);
		}

		/**
		 * 设置body样式
		 * 
		 */
		private void bodyFont(HSSFWorkbook wb) {
		          HSSFFont bodyFont = wb.createFont();
		          bodyFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		          bodyFont.setFontName("宋体");
		          bodyFont.setFontHeightInPoints((short) 9);
		          bodyStyle = wb.createCellStyle();
		          bodyStyle.setFont(bodyFont);
		          bodyStyle.setBorderTop((short)1);
		          bodyStyle.setBorderRight((short)1);
		          bodyStyle.setBorderBottom((short)1);
		          bodyStyle.setBorderLeft((short)1);
		          bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		          bodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		}
	      /**
	      * 设置标题样式
	      * 
	      */
	     private void titleFont(HSSFWorkbook wb) {
	               HSSFFont titleFont = wb.createFont();
	               titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	               titleFont.setFontName("宋体");
	               titleFont.setFontHeightInPoints((short) 12);
	               titleStyle = wb.createCellStyle();
	               titleStyle.setFont(titleFont);
	               titleStyle.setBorderBottom((short)1);
	               titleStyle.setBorderTop((short)1);
	               titleStyle.setBorderRight((short)1);
	               titleStyle.setBorderLeft((short)1);
	               
	               titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	               titleStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
	
	     }
	     
	    /**
	     * createAndExportWb
	     * 创建并导出wb
	     * @param filename 导出文件名称
	     * @param itsheets
	     * 是一个Map 的list 内容有
	     * name ： sheet的名称
	     * titleType ：sheet中title的类型，值有 3（3级表头），2（2级表头）,他同时也对应表头的级别
	     * titleString ：sheet中title的串（没处理1级，有功夫再写，1级的也简单）
	     * 3级表头字符串如下（使用 ,  :  _  =   @ 来进行分级处理，用List太麻烦，用数组没办法处理，只能这么做啦）
	     * 例如：  TA:TA1=TA11@TA12_TA2,TB
	     * 2级表头字符串如下（使用 ,  :  _ 来进行分级处理）例如： TA:TA1_TA2,TB
	     * 
	     * datalist ： 对应的数据，要求数据中不会出现null，或者少值，因为这样会出现串列的问题  List<List<Object>>
	     * @param response 
	     * void
	     * @exception 
	     * @since  1.0.0
	     */
	    @SuppressWarnings({ "unchecked" })
		public void createAndExportWb(String filename,List<Map<String,Object>> itsheets,HttpServletResponse response){
	    	HSSFWorkbook wb=new HSSFWorkbook();
			 init(wb);
	    	 for(Map<String,Object> stmap :itsheets){//循环处理sheet
	    		 String name=(String) stmap.get("name");
	    		 int titleType=(int) stmap.get("titleType");
	    		 String titleString=(String) stmap.get("titleString");
            List<List<Object>> datalist = (List<List<Object>>) stmap.get("datalist");
	    		 
	    		 HSSFSheet sheet=wb.createSheet(name);//创建sheet
	    		 doSheetFillIn(sheet,titleType,titleString,datalist);//处理sheet的主方法
	    	 }
	    	 doExportExcel(response,filename,wb);
	     }
	    
	    
	    /**
	     * @Title: createAndExportWbToFile
	     * @Description: 创建报表导出到文件
	     * @param filename
	     * @param itsheets 
	     * @author: WangShuai
	     * @time:2016年12月1日 下午2:35:47
	     * history:
	     * 1、2016年12月1日 WangShuai 创建方法
	    */
	    @SuppressWarnings({ "unchecked" })
		public void createAndExportWbToFile(String filename,List<Map<String,Object>> itsheets){
	    	HSSFWorkbook wb=new HSSFWorkbook();
			 init(wb);
	    	 for(Map<String,Object> stmap :itsheets){//循环处理sheet
	    		 String name=(String) stmap.get("name");
	    		 int titleType=(int) stmap.get("titleType");
	    		 String titleString=(String) stmap.get("titleString");
	    		 List<List<Object>> datalist=(List<List<Object>>) stmap.get("datalist");
	    		 
	    		 HSSFSheet sheet=wb.createSheet(name);//创建sheet
	    		 doSheetFillIn(sheet,titleType,titleString,datalist);//处理sheet的主方法
	    	 }
	    	 //导出报表到文件
	    	 doExportExcelToFile(filename,wb);
	     }

		
		/**
		 * @Title: doExportExcelToFile
		 * @Description: 导出文件到Excel
		 * @param filename
		 * @param wb 
		 * @author: WangShuai
		 * @time:2016年12月1日 下午2:37:06
		 * history:
		 * 1、2016年12月1日 WangShuai 创建方法
		*/
		private void doExportExcelToFile(String filename, HSSFWorkbook wb) {
			//输出文件起始路径
			String outFilePath = PlatformGlobalVar.SYS_PROPERTIES.get("realUploadCatalog");
			
			OutputStream out=null;  
			 try {  
				 	//输出文件路径
		        	outFilePath=outFilePath+"/"+filename;
		        	//设置文件输入流
		        	out=new FileOutputStream(outFilePath);
		            //写入输出流
		        	wb.write(out);  
		            out.flush(); 
			 
			 } 
			 catch (Exception e) 
			 {  
	           e.printStackTrace();  
			 } finally {  
				 //如果OUT输出流为空
	           if (out!=null)
	           {
	        	   try 
	        	   {
	        		   out.close();
	        	   } 
	        	   catch (IOException e) 
	        	   {
	        		   e.printStackTrace();
	        	   }
	        	}  
			 }  
			
			
			
		}

		/**
		 * doSheetFillIn
		 * 处理sheet
		 * @param sheet
		 * @param titleType
		 * @param titleString
		 * @param datalist 
		 * void
		 * @exception 
		 * @since  1.0.0
		 */
		private void doSheetFillIn(HSSFSheet sheet, int titleType, String titleString,
				List<List<Object>> datalist) {
			//3级表头
			if(3==titleType){
				//处理3级表头
				doSheetFillIn_Three(sheet,titleString);
			}
			//2级表头
			else if(2==titleType)
			{
				//处理2级表头
				doSheetFillIn_Two(sheet,titleString);
			}
			//1即表头
			else if(1==titleType){
				//处理1级表头
				doSheetFillIn_One(sheet,titleString);
			}
			//填写数据
			doFillInSheetData(sheet,titleType,datalist);
		}

		
		

		
		
		/**
		 * doSheetFillIn_One
		 * 处理1级表头
		 * @param sheet
		 * @param titleString 
		 * void
		 * @exception 
		 * @since  1.0.0
		 */
		private void doSheetFillIn_One(HSSFSheet sheet, String titleString) {
			HSSFRow row1 = sheet.createRow(0);
			String[]  headers=titleString.split(",");
			
			for(short i = 0; i < headers.length; i++){//i是headers的索引，也是Excel的索引
				HSSFCell cellT = row1.createCell(i);
				cellT.setCellStyle(titleStyle);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cellT.setCellValue(text);
			}
		}

		/**
		 * doSheetFillIn_Two
		 * 2级表头处理
		 * @param sheet
		 * @param titleString 
		 * 字符串样例     2级表头字符串如下（使用 ,  :  _ 来进行分级处理）例如： TA:TA1_TA2,TB 
		 * void
		 * @exception 
		 * @since  1.0.0
		 */
		private void doSheetFillIn_Two(HSSFSheet sheet, String titleString) {
			
			HSSFRow row1 = sheet.createRow(0);
			HSSFRow row2 = sheet.createRow(1);
			String[]  headers=titleString.split(",");
			//i是headers的索引，n是Excel的索引
			for(short i = 0, n = 0; i < headers.length; i++)
			{
				HSSFCell cellT = row1.createCell(n);
				cellT.setCellStyle(titleStyle);
				HSSFRichTextString text = null;
				//2级标题
				if(headers[i].contains(":"))
				{
					//子标题的分割
					String[] temp = headers[i].split(":");
					text = new HSSFRichTextString(temp[0]);
					String[] childlv1 = temp[1].split("_");
					sheet.addMergedRegion(new Region(0, n, 0, (short) (n + childlv1.length -1)));//2级标题的时候可以直接根据2子标题的个数来合并
					short tempI = n;
					//循环补充父标题的空格
					for(int j = 0; j < childlv1.length -1; j++)
					{
						HSSFCell cellTitleBlank = row1.createCell(++tempI);
						cellTitleBlank.setCellStyle(titleStyle);
					}
					//循环插入自标题的内容
					for(int j = 0; j < childlv1.length; j++)
					{
						HSSFCell cellChild = row2.createCell(n++);
						cellChild.setCellStyle(titleStyle);
						cellChild.setCellValue(new HSSFRichTextString(childlv1[j]));	
					}
				}
				//1级标题
				else
				{
					HSSFCell cell2 = row2.createCell(n);
					cell2.setCellStyle(bodyStyle);
					text = new HSSFRichTextString(headers[i]);
					//没有子标题的时候自己独占两行
					sheet.addMergedRegion(new Region(0, n, 1, n));
					n++;
				}
				cellT.setCellValue(text);
			}
		}

		
		/**
		 * doSheetFillIn_Three
		 * 3级表头处理
		 * @param sheet
		 * @param titleString 
		 * * 3级表头字符串如下（使用 ,  :  _  =   @ 来进行分级处理，用List太麻烦，用数组没办法处理，只能这么做啦）
	     * 例如：  TA:TA1=TA11@TA12_TA2,TB
		 * void
		 * @exception 
		 * @since  1.0.0
		 */
		private void doSheetFillIn_Three(HSSFSheet sheet, String titleString) {
			HSSFRow row1 = sheet.createRow(0);
			HSSFRow row2 = sheet.createRow(1);
			HSSFRow row3 = sheet.createRow(2);
			String[]  headers=titleString.split(",");
			
			for(short i = 0, n = 0; i < headers.length; i++){//i是headers的索引，n是Excel的索引
				HSSFCell cellT = row1.createCell(n);
				cellT.setCellStyle(titleStyle);
				HSSFRichTextString text = null;
				if(headers[i].contains(":")){//有2级标题
					if(headers[i].contains("=")){//有3级标题	TA:TA1=TA11@TA12_TA2
						//确定3级标题的个数，确定1级标题的总长度，同时也是把各级标题分解开
						String[] temp = headers[i].split(":");						//分级1级标题temp[0]是标题文字，temp[1]是子标题
						text = new HSSFRichTextString(temp[0]);
						
						String[] childlv2=temp[1].split("_");						//获取temp2级标题的数组
						int ttlength=0;
						short row2index=n;
						short row3index=n;
						
						for(int k=0;k<childlv2.length;k++){							//循环计算全部的2级标题对应的子标题总数
							String childlv2_1=childlv2[k];							//取到2级标题的第一个
							HSSFRichTextString textLV2 = null;
							HSSFCell cellChildlv2 = row2.createCell(row2index++);	//根据row2的index进行循环
							cellChildlv2.setCellStyle(titleStyle);
							
							//一层层的向下取，取到3级，并向下进行补充
							if(childlv2_1.contains("=")){//2级子节点，有3级子节点
								String[] childlv2_all=childlv2_1.split("=");
								textLV2=new HSSFRichTextString(childlv2_all[0]);
								String childlv3_1=childlv2_all[1];
								if(childlv3_1.contains("@")){//这里说明2级子节点有多个3级子节点，那么2级子节点就需要合并，同时为
									String[] childlv3_all=childlv3_1.split("@");
									ttlength=ttlength+childlv3_all.length;
									//开始写3级节点
									for(String childlv3Text : childlv3_all){
										HSSFCell cellChildlv3 = row3.createCell(row3index++);
										cellChildlv3.setCellStyle(titleStyle);
										cellChildlv3.setCellValue(new HSSFRichTextString(childlv3Text));
										n++;//进行EXCEL索引叠加
									}
									//这里进行2级节点的合并，因为有多个
									sheet.addMergedRegion(new Region(1, row2index, 1, (short)(row2index+childlv3_all.length-1)));
									//补充2级节点的空cell
									for(int x=0;x<childlv3_all.length-1;x++){
										HSSFCell cellChildlv2Blank = row2.createCell(++row2index);
										cellChildlv2Blank.setCellStyle(titleStyle);
									}
								}else{//这里说明2级子节点只有一个3级子节点，那么就不用合并和补充空格啦
									ttlength=ttlength+1;
									//写入3级节点的cell
									HSSFCell cellChildlv3 = row3.createCell(row3index++);
									cellChildlv3.setCellStyle(titleStyle);
									cellChildlv3.setCellValue(new HSSFRichTextString(childlv3_1));
									n++;//Excel索引节点的递增
								}
							}else{//2级子几点没有3及子节点
								textLV2=new HSSFRichTextString(childlv2_1);
								ttlength=ttlength+1;
								//这个2级节点没有子节点，那么就要合并3row
								sheet.addMergedRegion(new Region(1, row3index, 2, row3index));
								//补充3row的cell空格
								HSSFCell cellChildlv3Blank = row3.createCell(row3index++);
								cellChildlv3Blank.setCellStyle(titleStyle);
								n++;//进行Excel的索引递增，避免写到一个格子里面去
							}
							cellChildlv2.setCellValue(textLV2);
						}
						//进行3层总长度的cell合并
						sheet.addMergedRegion(new Region(0, (short)(n-ttlength), 0, (short) (n-1)));
						//插入第一行的补充的空格
						short tr1 = n;
						for(int j = 0; j < ttlength -1; j++){//循环补充父标题的空格,因为已经定义啦一个cell所以要减1
							HSSFCell cellTitleBlank = row1.createCell(++tr1);//因为开始已经定义啦一个cell所以就是 ++tr1
							cellTitleBlank.setCellStyle(titleStyle);
						}
						
					}else{//只有2级标题
						String[] temp = headers[i].split(":");//子标题的分割
						text = new HSSFRichTextString(temp[0]);
						String[] childlv2 = temp[1].split("_");
						//只有2及标题，那么1级标题要占2行，2级标题占1行
						sheet.addMergedRegion(new Region(0, n, 1, (short) (n + childlv2.length -1)));
						short tr1 = n;
						short tr2 = n;
						short tr3 = n;
						//对对应的空行进行补充,第一行
						for(int j = 0; j < childlv2.length -1; j++){//循环补充父标题的空格,因为已经定义啦一个cell所以要减1
							HSSFCell cellTitleBlank = row1.createCell(++tr1);//因为开始已经定义啦一个cell所以就是 ++tr1
							cellTitleBlank.setCellStyle(titleStyle);
						}
						//对第二行进行补充空格，从头开始
						for(int k=0;k < childlv2.length; k++){//未定义cell，所以不减1
							HSSFCell cellTitleBlank = row2.createCell(tr2++);//之前未进行定义，所以是tr2++
							cellTitleBlank.setCellStyle(titleStyle);
						}
						//插入子行信息
						for(int j = 0; j < childlv2.length; j++){//循环插入子标题的内容
							HSSFCell cellChild = row3.createCell(tr3++);
							cellChild.setCellStyle(titleStyle);
							cellChild.setCellValue(new HSSFRichTextString(childlv2[j]));	
							n++;//这里进行啦EXCEL的索引递增，不然会都写到一个格子里面去
						}
						
					}
				}else{//只有1级标题
					HSSFCell cell2 = row2.createCell(n);
					cell2.setCellStyle(titleStyle);
					HSSFCell cell3 = row3.createCell(n);
					cell3.setCellStyle(titleStyle);
					text = new HSSFRichTextString(headers[i]);
					sheet.addMergedRegion(new Region(0, n, 2, n));//没有子标题的时候自己独占两行
					n++;
				}
				cellT.setCellValue(text);
			}
			
		}
		/**
		 * doFillInSheetData
		 * 填写数据
		 * @param sheet 
		 * @param beginRow  开始行数
		 * @param datalist  数据列表，进行循环写入即可
		 * void
		 * @exception 
		 * @since  1.0.0
		 */
		private void doFillInSheetData(HSSFSheet sheet, int beginRow,List<List<Object>> datalist) {
			if(null != datalist && datalist.size() > 0){
				 int beginNum=beginRow;//设定起始地行数
				 HSSFRow row = null;
				 HSSFCell cell = null;
				 for(int i=0; i<datalist.size(); i++){//循环数据
					 row = sheet.createRow(beginNum++);
					 
					 List<Object> data=datalist.get(i);
					
					 int j=0;
					 for(Object en :data){
                    cell = row.createCell(j++);
                    cell.setCellStyle(bodyStyle);
                    if (en instanceof Integer)
                    {
                        cell.setCellType(cell.CELL_TYPE_NUMERIC);

                        cell.setCellValue((Integer) en);
                    }
                    else if (en instanceof BigDecimal)
                    {
                        cell.setCellType(cell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(((BigDecimal) en).doubleValue());
                    }
                    else
                    {
                        cell.setCellValue(new HSSFRichTextString(String.valueOf(en)));
                    }
				 }
            }
        }
		}
		
		/**
		 * doExportExcel
		 * 进行excel导出
		 * @param response
		 * @param filename，文件名称
		 * @param wb 
		 * void
		 * @exception 
		 * @since  1.0.0
		 */
		private void doExportExcel(HttpServletResponse response,String filename,HSSFWorkbook wb){
			
			OutputStream out=null;  
	        try 
	        {  
	        	filename = new String(filename.getBytes("GBK"), "ISO8859_1");//中文处理
	        	response.setHeader("content-disposition", "attachment; filename="+filename);  
	            response.setContentType("application/msexcel");  
	            out=response.getOutputStream();  
	            wb.write(out);  
	            out.flush(); 
	       } catch (Exception e) {  
	           e.printStackTrace();  
	       } finally {  
	    	   //如果输出为空
	           if (out!=null)
	           {
	        	   try 
	        	   {
	        		   out.close();
	        	   } 
	        	   catch (IOException e) 
	        	   {
	        		   e.printStackTrace();
	        	   }
	        	}  
	       }  
			
		}

    /**
     * @Title: createAndExportWbToFile4credit
     * @Description: 导出数据
     * @param filename
     * @param itsheets 
     * @author: Guanxu
     * @time:2016年12月15日 下午5:35:46
     * history:
     * 1、2016年12月15日 Guanxu 创建方法
    */
    public void createAndExportWbToFile4credit(String filename, List<Map<String, Object>> itsheets)
    {
        HSSFWorkbook wb = new HSSFWorkbook();
        init(wb);
        for (Map<String, Object> stmap : itsheets)
        {// 循环处理sheet
            String name = (String) stmap.get("sheetName");
            String titleString = (String) stmap.get("titleString");
            List<List<Object>> datalist = (List<List<Object>>) stmap.get("datalist");
            List<Map<String, Object>> leftlist = (List<Map<String, Object>>) stmap.get("leftdata");
            HSSFSheet sheet = wb.createSheet(name);// 创建sheet
            sheet.autoSizeColumn((short) 0); // 自动调整列宽
            doSheetFillIn(sheet, titleString, datalist, leftlist);// 处理sheet的主方法
        }
        // 导出报表到文件
        doExportExcelToFile(filename, wb);

    }


    /**
     * @Title: doSheetFillIn
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param sheet
     * @param titleString
     * @param datalist 
     * @author: Guanxu
     * @time:2016年12月15日 下午5:38:35
     * history:
     * 1、2016年12月15日 Guanxu 创建方法
    */
    private void doSheetFillIn(HSSFSheet sheet, String titleString, List<List<Object>> datalist, List<Map<String, Object>> leftlist)
    {
        HSSFRow row1 = sheet.createRow(0);
        HSSFRow row2 = sheet.createRow(1);
        String[] headers = titleString.split(":");
        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 12));
        HSSFCell cellT = row1.createCell(0);
        HSSFRichTextString text = new HSSFRichTextString(headers[0]);
        cellT.setCellStyle(titleStyle);
        cellT.setCellValue(text);
        String[] title_two = headers[1].split(",");
        for (int i = 0; i < title_two.length; i++)
        {
            HSSFCell cell_tmp = row2.createCell(i);
            cell_tmp.setCellStyle(titleStyle);
            HSSFRichTextString text_tmp = new HSSFRichTextString(title_two[i]);
            cell_tmp.setCellValue(text_tmp);
        }
        doFillInSheetData(sheet, datalist, leftlist);
    }

    /**
     * @Title: doFillInSheetData
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param sheet
     * @param datalist 
     * @author: Guanxu
     * @time:2016年12月15日 下午5:55:10
     * history:
     * 1、2016年12月15日 Guanxu 创建方法
    */
    private void doFillInSheetData(HSSFSheet sheet, List<List<Object>> datalist, List<Map<String, Object>> leftlist)
    {
        int begin_row = 2;
        // 先将单元格进行合并
        for (Map<String, Object> map : leftlist)
        {
            Long row_count = (Long) map.get("detail_count");
            int end_row = (int) (begin_row + row_count);

            for (int i = 0; i < 8; i++)
            {
                int start = begin_row;
                int end = end_row - 1;
                sheet.addMergedRegion(new Region(start, (short) i, end, (short) i));// 首行、首列\最后一行、最后一列

            }

            begin_row = (int) (begin_row + row_count);
        }
        begin_row = 2;
        
        for (int i = 0; i < datalist.size(); i++)
        {

            List<Object> list = datalist.get(i);
            HSSFRow row = sheet.createRow(begin_row+i);
            HSSFCell cell = row.createCell(0);
            cell.setCellStyle(bodyStyle);
            for (int j = 0; j < list.size(); j++)
            {
                sheet.autoSizeColumn((short) j);
                cell = row.createCell(j + 1);
                cell.setCellStyle(bodyStyle);
                if (list.get(j) instanceof Integer)
                {
                    cell.setCellType(cell.CELL_TYPE_NUMERIC);

                    cell.setCellValue((Integer) list.get(j));
                }
                else
                {
                    cell.setCellValue(new HSSFRichTextString(list.get(j) == null ? "" : String.valueOf(list.get(j))));
                }
            }
        }
        
        begin_row = 2;
        // 用于记录序号的
        int row_num = 1;
        for (Map<String, Object> map : leftlist)
        {
            Long row_count = (Long) map.get("detail_count");
            int start = begin_row;
            HSSFRow row = sheet.getRow(start);
            HSSFCell cell = row.createCell(0);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(new HSSFRichTextString("" + (row_num++)));
            begin_row = (int) (begin_row + row_count);
        }

    }
	     
}
