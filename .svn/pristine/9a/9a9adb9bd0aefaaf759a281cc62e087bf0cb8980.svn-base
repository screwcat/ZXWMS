package com.zx.emanage.inve.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCommionRecordDao;
import com.zx.emanage.inve.persist.WmsInveOldCommBaseDataSpecialHisDao;
import com.zx.emanage.inve.persist.WmsInveOldCommHisDao;
import com.zx.emanage.inve.service.WmsJasperExportService;
import com.zx.sframe.util.ExpertUtil;
import com.zx.sframe.util.JasperUtil;

@Service("wmsJasperExportService")
public class WmsJasperExportServiceImpl implements WmsJasperExportService{
	
	@Autowired
	private WmsInveCommionRecordDao wmsinvecommionrecordDao;
	
	@Autowired
	private WmsInveOldCommBaseDataSpecialHisDao wmsInveOldCommBaseDataSpecialHisDao;
	
	@Autowired
	WmsInveOldCommHisDao wmsInveOldCommHisDao;
  /**
   * 理财佣金导出jasperreport
  */
  @Override
  @Transactional
  public Map<String,Object> transferConfirmationformExport(HttpServletRequest request,HttpServletResponse response,String export_month) {
	
        Map<String, Object> map = null;
	 //导出报表类型
	String type = "excel";
	    
	//jasper文件路径
	String tax_url = "wmsInveTaxation.jasper";			//新增税费jasper
	String stock_url = "wmsInveStock.jasper";			//存量jasper
	String mag_com_url = "wmsInveManagment.jasper";		//管理提成jasper
	String pen_com_url = "wmsInvePen.jasper";			//老产品个人佣金jasper
	String tem_com_url = "wmsInveTeam.jasper";			//老产品团队佣金jasper
	String payment_url = "wmsInvePayment.jasper";		//打款表jasper
	List<String> listUrl = new ArrayList<String>();
	listUrl.add(tax_url);
	listUrl.add(stock_url);
	listUrl.add(mag_com_url);
	listUrl.add(pen_com_url);
	listUrl.add(tem_com_url);
	listUrl.add(payment_url);

	//生成报表文件名
	String defaultFilename =export_month+"佣金报表_平台版本";
	    

	Map<String,Object> pmap = new HashMap<String,Object>();
	
	
	Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("export_month", export_month);
	
    List<Map<String,Object>> tList = wmsinvecommionrecordDao.getWmsInveTaxationByCondition(paramMap);
    List<Map<String,Object>> sList = wmsinvecommionrecordDao.getWmsInveStockByCondition(paramMap);
    List<Map<String,Object>> mList = wmsinvecommionrecordDao.getWmsInveCommionByCondition(paramMap);
    List<Map<String,Object>> pyList = wmsinvecommionrecordDao.getWmsInvePaymentByCondition(paramMap);

    pmap.put("tax_title", export_month+"佣金_新增奖及税费");
    pmap.put("stock_title", export_month+"佣金_存量奖");
    pmap.put("mag_title", export_month+"佣金_管理提成奖");
    pmap.put("pen_title", export_month+"佣金_老产品个人佣金");
    pmap.put("team_title", export_month+"佣金_老产品团队佣金");

	pmap.put("tList", tList);
	pmap.put("sList", sList);
	pmap.put("mList", mList);
	pmap.put("pList", new ArrayList<String>());
	pmap.put("tcList", new ArrayList<String>());
	pmap.put("pyList", pyList);
	//下载
	return JasperUtil.export(pmap, type, listUrl, defaultFilename, request, response);
  }
  
  
  /**
   * 理财佣金导出poi
  */
  @Override
  @Transactional
  public void transferConfirmationformExportPoi(HttpServletRequest request,HttpServletResponse response,String export_month) {
      
        String date = "";
      
        if(export_month != null && !"".equals(export_month))
        {
            String[] dateArray = export_month.split("-");
            date = dateArray[0] + "年" + dateArray[1] + "月";
        }
        
      
        Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("export_month", export_month);
		
	    List<Map<String,Object>> tList = wmsinvecommionrecordDao.getWmsInveTaxationByCondition(paramMap);
	    List<Map<String,Object>> sList = wmsinvecommionrecordDao.getWmsInveStockByCondition(paramMap);
	    List<Map<String,Object>> mList = wmsinvecommionrecordDao.getWmsInveCommionByCondition(paramMap);
	    List<Map<String,Object>> pyList = wmsinvecommionrecordDao.getWmsInvePaymentByCondition(paramMap);
	    List<Map<String,Object>> cmList = wmsInveOldCommHisDao.getWmsInvePerOldCommByCondition(paramMap);
	    List<Map<String,Object>> cbList = wmsInveOldCommBaseDataSpecialHisDao.getWmsInveOldCommBaseDataByCondition(paramMap);
	    List<Map<String,Object>> shareList = getCommissionShareHolderInfos(paramMap);

	    //sheet 参数list
	    Map<String,Object> listMap = new HashMap<String,Object>();   
	    
	    Map<String, Object> titleMap = new HashMap<String, Object>();
	    titleMap.put("新增奖及税费", export_month+"佣金_新增奖及税费");
		titleMap.put("存量奖", export_month+"佣金_存量奖");
		titleMap.put("管理提成奖", export_month+"佣金_管理提成奖");
		titleMap.put("老产品个人佣金", export_month+"佣金_老产品个人佣金");
		titleMap.put("老产品团队佣金", export_month+"佣金_老产品团队佣金");
		titleMap.put("股东单据提成", date+"股东单佣金表");
	    
		listMap.put("新增奖及税费", tList);
	    listMap.put("存量奖", sList);
	    listMap.put("管理提成奖", mList);
	    listMap.put("老产品个人佣金", cmList);
	    listMap.put("老产品团队佣金", cmList);
	    listMap.put("部门经理留原团队业绩管理提成", cbList);
	    listMap.put("股东单据提成", shareList);
	    listMap.put("打款表", pyList);
	    listMap.put("titles", titleMap);
	    
	    Map<String, Object> mergeMap = new HashMap<String, Object>();
	    mergeMap.put("start", 0);//合并列的时候表示开始列,合并行的时候表示开始行
	    mergeMap.put("end", 3);//合并列的时候表示截止合并的列,合并行的时候表示截止行
	    mergeMap.put("type", 0);//type类型为0:表示合并列;1:表示合并行
	    mergeMap.put("cellIndex", 0);//合并行时需要传入从第几列开始合并
	    mergeMap.put("indexStr", "合计");//表示需要触发合并列时需要包含的信息进行合并
	    listMap.put("mergeMap", mergeMap);
	    
	    
    	try {
//    		String out_file_name = export_month +"佣金报表_平台版本.xls";
//			PoiExportUtil.doexport("佣金报表模板.xls", listMap,out_file_name,response);
    		String out_file_name = export_month +"佣金报表_平台版本.xls";
    		ExpertUtil eu = new ExpertUtil();
    		eu.expertExcel("佣金报表模板.xls", listMap, out_file_name, "titles", 2, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

  }

  /**
   * 
   * @Title: getCommissionShareHolderInfos
   * @Description: 组合股东单据信息数据
   * @param paramMap 查询参数集合
   * @return 返回list数据集合 
   * @author: DongHao
   * @time:2017年6月1日 上午1:23:54
   * history:
   * 1、2017年6月1日 DongHao 创建方法
   */
  private List<Map<String, Object>> getCommissionShareHolderInfos(Map<String, Object> paramMap)
  {
      //定义结果集合
      List<Map<String, Object>> resLis = new ArrayList<Map<String, Object>>();
      
      //根据查询条件参数进行查询股东单据信息表中的业务人员id,此操作是为了组合报表的格式
      List<Map<String, Object>> personnelIds = wmsInveOldCommHisDao.getWmsInveCommissionShareHolderPersonnelInfos(paramMap);
      
      //定义汇总的总金额
      BigDecimal total_account = BigDecimal.ZERO;
      
      //定义投资金额汇总
      BigDecimal total_product_account = BigDecimal.ZERO;
      
      //遍历人员id集合进行获取
      for(Map<String, Object> map : personnelIds)
      {
          //进行总金额计算
          total_account = total_account.add(new BigDecimal(map.get("total_account").toString()));
          
          //进行投资金额进行汇总
          total_product_account = total_product_account.add(new BigDecimal(map.get("product_account").toString()));
          
          paramMap.put("salesman_id", (Integer)map.get("salesman_id"));
          //根据查询条件参数进行获取数据
          List<Map<String, Object>> lis = wmsInveOldCommHisDao.getWmsInveCommissionShareHolderByCondition(paramMap);
          
          //定义每个人的合计金额
          Map<String, Object> tatolMap = new HashMap<String, Object>();
          tatolMap.put("salesman_name", map.get("salesman_name").toString() + " 合计");
          tatolMap.put("deal_num", "");
          tatolMap.put("cus_name", "");
          tatolMap.put("date_of_payment", "");
          tatolMap.put("product_account", "");
          tatolMap.put("category_name", "");
          
          tatolMap.put("product_deadline", "");
          tatolMap.put("comm_rate", "");
          tatolMap.put("comm_account", new BigDecimal(map.get("total_account").toString()));
          
          lis.add(tatolMap);
          
          resLis.addAll(lis);
          
      }
      
      //设置所有股东单据进行汇总
      Map<String, Object> tatolMap = new HashMap<String, Object>();
      tatolMap.put("salesman_name", "合计");
      tatolMap.put("deal_num", "");
      tatolMap.put("cus_name", "");
      tatolMap.put("date_of_payment", "");
      tatolMap.put("product_account", total_product_account);
      tatolMap.put("category_name", "——");
      tatolMap.put("product_deadline", "——");
      tatolMap.put("comm_rate", "——");
      tatolMap.put("comm_account", total_account);
      
      resLis.add(tatolMap);
      
      return resLis;
  }
}