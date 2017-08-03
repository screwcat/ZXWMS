package com.zx.emanage.inve.service;



import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public  interface WmsJasperExportService
{
  /**
   * 理财佣金导出jasperreport
   * @param export_month  
   */
  public Map<String,Object> transferConfirmationformExport(HttpServletRequest request,HttpServletResponse response,String export_month);
  
  /**
   * 理财佣金导出poi
   * @param export_month  
   */
  public void transferConfirmationformExportPoi(HttpServletRequest request,HttpServletResponse response,String export_month);
}