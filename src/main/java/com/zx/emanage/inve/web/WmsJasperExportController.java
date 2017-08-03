package com.zx.emanage.inve.web;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.WmsJasperExportService;

/**
 * 报表导出controller
 * @author zhangyunfei
 *
 */
@Controller
public class WmsJasperExportController
{

  @Autowired
  private WmsJasperExportService wmsJasperExportService;

  /**
   * 理财佣金导出 jasperreport
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value={"/export/transferConfirmationformExport.dos"}, method={RequestMethod.GET, RequestMethod.POST})
  @ResponseBody
  public Map<String,Object> transferConfirmationformExport(HttpServletRequest request, HttpServletResponse response,String export_month){
	  Map<String,Object> map = null;
	
	  map = wmsJasperExportService.transferConfirmationformExport(request,response,export_month);
	  
	  return map;
  }

  
  /**
   * 理财佣金导出	poi
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value={"/export/transferConfirmationformExportPoi.dos"}, method={RequestMethod.GET, RequestMethod.POST})
  @ResponseBody
  public void transferConfirmationformExportPoi(HttpServletRequest request, HttpServletResponse response,String export_month){
	
	  wmsJasperExportService.transferConfirmationformExportPoi(request,response,export_month);
	  
  }
}