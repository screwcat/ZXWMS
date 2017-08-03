package com.zx.sframe.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.base.JRBaseReport;
import net.sf.jasperreports.engine.export.FontKey;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.PdfFont;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;

import com.alibaba.fastjson.JSONObject;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

public class JasperUtil {

	public static final String PRINT_TYPE = "print";
	public static final String PDF_TYPE = "pdf";
	public static final String EXCEL_TYPE = "excel";
	public static final String HTML_TYPE = "html";
	public static final String WORD_TYPE = "word";

	public static void prepareReport(JasperReport jasperReport, String type) {
		/*
		 * 如果导出的是excel，则需要去掉周围的margin
		 */
		if ("excel".equals(type))
			try {
				Field margin = JRBaseReport.class
						.getDeclaredField("leftMargin");
				margin.setAccessible(true);
				margin.setInt(jasperReport, 0);
				margin = JRBaseReport.class.getDeclaredField("topMargin");
				margin.setAccessible(true);
				margin.setInt(jasperReport, 0);
				margin = JRBaseReport.class.getDeclaredField("bottomMargin");
				margin.setAccessible(true);
				margin.setInt(jasperReport, 0);
				Field pageHeight = JRBaseReport.class
						.getDeclaredField("pageHeight");
				pageHeight.setAccessible(true);
				pageHeight.setInt(jasperReport, 2147483647);
			} catch (Exception exception) {
			}
	}

	/**
	 * 导出excel
	 */
	public static void exportExcel(List<JasperPrint> jasperPrintList,
			String defaultFilename, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			
			 if ((defaultFilename.trim() != null) && (defaultFilename != null)){
				 defaultFilename = defaultFilename + ".xls";
			 }else {
			    	defaultFilename = "jasper.xls";
			 }
			String[] sheetNames = {"新增奖及税费","存量奖","管理提成奖","老产品个人佣金","老产品团队佣金","打款表"};		    


			JRXlsExporter exporter = new JRXlsExporter();
			ByteArrayOutputStream oStream = new ByteArrayOutputStream();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,jasperPrintList);		//jasperPrintList
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);	//true设置表格分页
			exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,sheetNames);				//sheetName数组
			//exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);				
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oStream);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporter.exportReport();
			byte[] bytes = oStream.toByteArray();
			response.setContentType("application/vnd.ms-excel");

			String fileName = new String(defaultFilename.getBytes("GBK"), "ISO8859_1");
		    response.setHeader("Content-disposition", "attachment; filename="+fileName);
			
			response.setContentLength(bytes.length);
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 导出word
	 */
	public static void exportWord(JasperPrint jasperPrint,
			String defaultFilename, HttpServletRequest request,
			HttpServletResponse response) {
		// to do
	}

	/**
	 * 导出报表 fileurl 文件路径
	 */
	public static Map<String, Object> export(Map<String, Object> map,
			String type, List<String> listUrl, String defaultFilename,
			HttpServletRequest request, HttpServletResponse response) {
		String result = "success";
		try {
			
			String fileurl ="";
			List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();

			for(int i =0;i<listUrl.size();i++){
				
				fileurl = JasperUtil.class.getClassLoader().getResource("/").getPath()+ "/jasper/" + listUrl.get(i);
				File file = new File(fileurl);
				InputStream jr = new FileInputStream(file);
				JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jr);
				jasperReport.getFields();
				JasperPrint jasperPrint = JasperFillManager.fillReport(
						jasperReport, map, new JREmptyDataSource());
				jasperPrintList.add(jasperPrint);
			}
			
			if ("excel".equals(type))
				exportExcel(jasperPrintList, defaultFilename, request, response);
		} catch (Exception e) {
			result = "error";

			e.printStackTrace();
		}
		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("result", result);
		return rmap;
	}

	/**
	 * 获取文件路径
	 * 
	 * @return
	 */
	public static String returnBasePath() {
		try {
			String path = SysUtil.class.getResource("/").toURI().getPath();
			return new File(path).getParentFile().getParentFile()
					.getCanonicalPath();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	   * 导出报表
	   * fileurl 文件路径
	   * --贷款端 --合同
	   */
	  public static Map<String, Object> exportDK(Map<String, Object> map, String type, String fileurl, String defaultFilename, HttpServletRequest request, HttpServletResponse response)
	  {
	    String result = "success";
	    try {
	      fileurl = JasperUtil.class.getClassLoader().getResource("/").getPath()+"/jasper/"+fileurl;
	      File file = new File(fileurl);
	      InputStream jr = new FileInputStream(file);
	      JasperReport jasperReport = (JasperReport)JRLoader.loadObject(jr);
	      jasperReport.getFields();

	      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, new JREmptyDataSource());

	      if ("excel".equals(type))
	        exportExcel(jasperPrint, defaultFilename, request, response);
	      else if ("pdf".equals(type))
	        exportPdf(jasperPrint, defaultFilename, request, response);
	      else if ("word".equals(type))
	        exportWord(jasperPrint, defaultFilename, request, response);
	    }
	    catch (Exception e) {
	      result = "error";

	      e.printStackTrace();
	    }
	    Map<String,Object> rmap = new HashMap<String,Object>();
	    rmap.put("result", result);
	    return rmap;
	  }
	  /**
	   * 导出excel
	   */
	  public static void exportExcel(JasperPrint jasperPrint, String defaultFilename, HttpServletRequest request, HttpServletResponse response)
	  {
		  //to do
	  }

	  /**
	   * 导出pdf
	   */
	  public static void exportPdf(JasperPrint jasperPrint, String defaultFilename, HttpServletRequest request, HttpServletResponse response)
	  {
	    response.setContentType("application/pdf");
	    String defaultname = null;
	    if ((defaultFilename.trim() != null) && (defaultFilename != null))
	      defaultname = defaultFilename + ".pdf";
	    else {
	      defaultname = "jasper.pdf";
	    }
	    try
	    {
	      String fileName = new String(defaultname.getBytes("GBK"), "ISO8859_1");
	      response.setHeader("Content-disposition", "attachment; filename=" + 
	        fileName);

	      ServletOutputStream ouputStream = response.getOutputStream();
	      byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
	      PdfReader pdfReader = new PdfReader(b);
	      PdfStamper pdfStamper = new PdfStamper(pdfReader, ouputStream);//生成pdf文件流

	      /**
	       * 打印印章 begin
	       */
	     /* Image image = Image.getInstance("E:\\work\\jasper_ireport\\sz.png");//印张图片
	      image.setAbsolutePosition(400.0F, 500.0F);
	      int pageSize = 1;
	      for (int i = 1; i <= pageSize; i++)
	      {
	        PdfContentByte under = pdfStamper.getUnderContent(i);

	        under.addImage(image);
	      }
	      //=======end========
	      
	      //加水印
	      addWatermark(pdfStamper, "www.xrszg.com");*/

	      
	      pdfStamper.close();

	      //导出pdf
	      JasperExportManager.exportReportToPdfStream(jasperPrint, 
	        ouputStream);

	      ouputStream.flush();
	      ouputStream.close();
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	  }

    /**
       * 
       * @Title: doprint
       * @Description: 封装好jasperPrintList后  打印预览
       * @param jasperPrintList
       * @param response
       * @throws Exception 
       * @author: zhangyunfei
       * @time:2017年2月11日 下午2:55:25
       * history:
       * 1、2017年2月11日 Administrator 创建方法
       */
    public static void doprint(List<JasperPrint> jasperPrintList, HttpServletResponse response) throws Exception
    {
        if (jasperPrintList.size() > 0)
        {
            OutputStream out = null;
            out = response.getOutputStream(); // 获取页面输出流
            response.setContentType("application/pdf; charset=utf-8"); // 设置页面类型及编码
            response.setDateHeader("Expires", 0); // 清除页面缓存
            response.setHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode("合同预览.pdf", "utf-8")); // 设置文件名
            JRPdfExporter exporter = new JRPdfExporter();
            Map fontsMap = new HashMap();

            fontsMap.put(new FontKey("宋体", true, false), new PdfFont("STSong-Light", "UniGB-UCS2-H", true, true, false));
            exporter.setParameter(JRExporterParameter.FONT_MAP, fontsMap);
            // exporter.setParameter(JRExporterParameter.JASPER_PRINT, jPrint);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList); // jasperPrintList
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
            exporter.exportReport();
            if (out != null)
            {
                out.flush();
            }
        }
    }
    
    @SuppressWarnings("deprecation")
    public static Map<String, String> doExport(List<JasperPrint> jasperPrintList, String fileName, String bill_code) throws Exception
    {

        String filePath = fileName;
        
        File file = new File(filePath);
        FileOutputStream fos = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
        exporter.exportReport();
        byte[] bytes = baos.toByteArray();
        
        try
        {
            fos = new FileOutputStream(file);
            fos.write(bytes);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (fos != null)
            {
                fos.close();
            }
        }

        // 调用up系统实现文件上传功能
        ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(Charset.forName(HTTP.UTF_8));// 设置中文请求

        HttpPost httpPost = new HttpPost(GlobalVal.SERVER_UP_URL + "/wms/uploadCreditProtocol.do");
        FileBody bin = new FileBody(file);
        StringBody fileNameBody = new StringBody(fileName, contentType);
        StringBody billCodeBody = new StringBody(bill_code, contentType);
        builder.addPart("bill_code", billCodeBody);
        builder.addPart("file_name", fileNameBody);
        builder.addPart("files", bin);
        HttpEntity entity = builder.build();
        httpPost.setEntity(entity);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(httpPost);

        InputStreamReader reader = new InputStreamReader(response.getEntity().getContent(), "UTF-8");
        BufferedReader rd = new BufferedReader(reader);

        String result_str = rd.readLine();
        JSONObject obj = JSONObject.parseObject(result_str);

        // 上传到文件服务器结束之后需要将本地生成的合同文件删除
        file.delete();

        String file_url = obj.get("file_url").toString();
        file_url = file_url.substring(file_url.indexOf("upload"), file_url.length());

        Map<String, String> resMap = new HashMap<String, String>();
        resMap.put("file_name", fileName);
        resMap.put("file_url", GlobalVal.SERVER_UPLOAD_URL + "/" + file_url);
        return resMap;
    }

    /**
     * @Title: doprintWord
     * @Description: 导出word （测试） 
     * @param jasperPrintList
     * @param response
     * @throws Exception 
     * @author: zhangyunfei
     * @time:2017年3月8日 上午11:57:04
     * history:
     * 1、2017年3月8日 Administrator 创建方法
     */
    public static void doprintWord(List<JasperPrint> jasperPrintList, HttpServletResponse response) throws Exception
    {
        OutputStream out = null;
        out = response.getOutputStream(); // 获取页面输出流

        response.setContentType("application/msword; charset=utf-8"); // 设置页面类型及编码
        response.setDateHeader("Expires", 0); // 清除页面缓存
        response.setHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode("合同预览.doc", "utf-8")); // 设置文件名
        JRDocxExporter exporter = new JRDocxExporter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList); // jasperPrintList
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
        exporter.exportReport();        
        out.flush();
        out.close();
    }

}