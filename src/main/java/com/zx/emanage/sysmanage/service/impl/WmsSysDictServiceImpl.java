package com.zx.emanage.sysmanage.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.loanappro.persist.WmsCreApproBorrowProtocolDao;
import com.zx.emanage.loanappro.persist.WmsSysPropertyDao;
import com.zx.emanage.loanfina.persist.WmsFinaCrePeriodRepayDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreRealrepayInfoDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreRepayDao;
import com.zx.emanage.sysmanage.persist.IWmsSysDictDao;
import com.zx.emanage.sysmanage.service.IWmsSysDictService;
import com.zx.emanage.sysmanage.vo.WmsDataRevisionBean;
import com.zx.emanage.sysmanage.vo.WmsSysDictSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsSysDict;
import com.zx.emanage.util.gen.entity.WmsCreApproBorrowProtocol;
import com.zx.emanage.util.gen.entity.WmsFinaCrePeriodRepay;
import com.zx.emanage.util.gen.entity.WmsFinaCreRealrepayInfo;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepay;
import com.zx.emanage.util.gen.vo.WmsSysDictVO;
import com.zx.sframe.util.SysUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmssysdictService")
public class WmsSysDictServiceImpl implements IWmsSysDictService
{
    private static Logger log = LoggerFactory.getLogger(WmsSysDictServiceImpl.class);

    @Autowired
    private IWmsSysDictDao wmssysdictDao;
    @Autowired
	private CommonsMultipartResolver multipartResolver;
	@Autowired
	private WmsCreApproBorrowProtocolDao wmsCreApproBorrowProtocolDao;
	@Autowired
	private WmsFinaCreRepayDao wmsFinaCreRepayDao;//还款信息
	@Autowired
	private WmsSysPropertyDao wmsSysPropertyDao;//属性表
	@Autowired
	private WmsFinaCrePeriodRepayDao wmsFinaCrePeriodRepayDao;//台账
	@Autowired
	private WmsFinaCreRealrepayInfoDao wmsFinaCreRealrepayInfoDao;//应还款信息表
    @Override
    public Map<String, Object> getListWithoutPaging(WmsSysDictSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", wmssysdictDao.getListWithoutPaging(queryInfo));
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsSysDictSearchBeanVO queryInfo)
    {
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), wmssysdictDao.getListCountNum(queryInfo),
                                             wmssysdictDao.getListWithPaging(queryInfo));
        return bean.getGridData();
    }

    @Override
    public WmsSysDictVO getInfoByPK(Integer wms_sys_dict_id)
    {
        return wmssysdictDao.getInfoByPK(wms_sys_dict_id);
    }

    @Override
    @Transactional
    public String save(WmsSysDict bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmssysdictDao.addNewRecord(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsSysDict bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmssysdictDao.updateRecordAll(bean); // update a record replace
                                                   // all, support null val
        // ret = wmssysdictDao.updateRecordCols(bean); // update a record
        // replace columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String delete(WmsSysDict bean)
    {
        String resStr = "success";
        int ret = 0;
        try
        {
            ret = wmssysdictDao.deleteRecordByDomain(bean); // nonsupport null
                                                            // val
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            resStr = "fkerror"; // default foreign key error
        }
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsSysDict() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByAndMethod(WmsSysDict queryInfo, Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getWms_sys_dict_id() == null)
            {
                setAllPk = false;
            }
            if (!setAllPk)
            {
                log.info("**************please set pk into the queryInfo.**************");
                return "error";
            }
        }
        String resStr = "success";
        List<WmsSysDict> beanList = wmssysdictDao.getSingleTableListByAndMethod(queryInfo, isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "or" method, each checking, need new
     * WmsSysDict() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByOrMethod(WmsSysDict queryInfo, Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getWms_sys_dict_id() == null)
            {
                setAllPk = false;
            }
            if (!setAllPk)
            {
                log.info("**************please set pk into the queryInfo.**************");
                return "error";
            }
        }
        String resStr = "success";
        List<WmsSysDict> beanList = wmssysdictDao.getSingleTableListByOrMethod(queryInfo, isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }
    /**
     * 实现导入处理逾期数据功能
     */
    @Override
    public String fileforexcel(HttpServletRequest request, HttpSession session,
    		HttpServletResponse response) {
    	String result = "";
    	System.out.println("*****************************数据已接收等待处理************************");
    	if (this.multipartResolver != null
				&& this.multipartResolver.isMultipart(request)) {
			if (request instanceof MultipartHttpServletRequest) {

				MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;

				Map<String, MultipartFile> fileMap = mRequest.getFileMap();
				boolean flag=true;
				for (Map.Entry<String, MultipartFile> entity : fileMap
						.entrySet()) {
					MultipartFile mf = entity.getValue();
					long fileSize = mf.getSize();
					if (fileSize > 60 * 1024 * 1024) {
						result = "maxsizeerror";
					}
					String srcFileName = mf.getOriginalFilename();// 获取文件名称
					String postfix = "";
					if (srcFileName.lastIndexOf(".") > -1) {
						postfix = srcFileName.substring(
								srcFileName.lastIndexOf(".") + 1).toLowerCase();
						if ("exe".equals(postfix) || "cmd".equals(postfix)
								|| "bat".equals(postfix)) {
							result = "filetypeerror";
						}
					}
					System.out.println("*****************************数据已过滤完毕待读取数据*******************************");
					// 构造 Workbook 对象，mf.getInputStream() 是传入文件路径(获得Excel工作区)
					List<WmsDataRevisionBean> list = new ArrayList<WmsDataRevisionBean>();
						// Excel 2007获取方法
						Workbook book;
				}
				if(flag){
						result="success";
						System.out.println("*****************************数据已接收,数据处理成功************************");
					}else{
						result="error";
						System.out.println("*****************************数据已接收,数据处理失败************************");
					}	
			}
		}
    	return "error";
	}
    /**
     * 实现逾期数据的具体操作
     * @param listSearchforRepay
     */
    private void getData(List<WmsFinaCreRepay> listSearchforRepay){
    	//查询出所更新的数据对应的台账主键

    	for(WmsFinaCreRepay finarepay:listSearchforRepay){
			 WmsFinaCrePeriodRepay wCrePeriodRepay = new WmsFinaCrePeriodRepay();
			 wCrePeriodRepay.setWms_cre_credit_head_id(finarepay.getWms_cre_credit_head_id());
			 wCrePeriodRepay.setCurrent_repay_date(finarepay.getCurrent_repay_date());//开始逾期的日期
			 //查询出开始逾期的台账信息
			 List<WmsFinaCrePeriodRepay> listSearchforUnPeriod = wmsFinaCrePeriodRepayDao.getListByEntity(wCrePeriodRepay);
			 //查询出现在应还的台账信息
			 List<WmsFinaCrePeriodRepay> listSearchforYiPeriod = wmsFinaCrePeriodRepayDao.getListByYiPeriod(wCrePeriodRepay);
				
			    BigDecimal Total_repayment =new BigDecimal(0);
				BigDecimal Is_total_repayment=new BigDecimal(0);
				BigDecimal un_total_repayment=new BigDecimal(0);
				BigDecimal Adjustment_amount=new BigDecimal(0);
				BigDecimal Org_repay_principal=new BigDecimal(0);
				BigDecimal Repay_principal=new BigDecimal(0);
				BigDecimal Un_repay_principal=new BigDecimal(0);
				BigDecimal Org_repay_interest=new BigDecimal(0);
				BigDecimal Repay_interest=new BigDecimal(0);
				BigDecimal Un_repay_interest=new BigDecimal(0);
				BigDecimal Total_derate=new BigDecimal(0);
				BigDecimal Cur_late_fee=new BigDecimal(0);
				BigDecimal Total_cur_late_fee=new BigDecimal(0);
				BigDecimal Bq_cur_late_fee=new BigDecimal(0);
				Integer repay_no_count = 0;
				String repay_no_detail="";
			 for(int i=listSearchforUnPeriod.get(0).getRepay_no();i<=listSearchforYiPeriod.get(0).getRepay_no();i++){
				 //获取对应应还款信息表中的信息数据
				 WmsFinaCreRealrepayInfo w1 = new WmsFinaCreRealrepayInfo();
				 w1.setWms_cre_credit_head_id(finarepay.getWms_cre_credit_head_id());
				 w1.setRepay_no(String.valueOf(i));
				 //查询对应的期数的应还款信息表中的数据
				 List<WmsFinaCreRealrepayInfo> listInfos=wmsFinaCreRealrepayInfoDao.getListByEntity(w1);
				 WmsFinaCrePeriodRepay w2 =new WmsFinaCrePeriodRepay();
				 w2.setWms_cre_credit_head_id(finarepay.getWms_cre_credit_head_id());
				 w2.setRepay_no(i);
				 List<WmsFinaCrePeriodRepay>listInfoss = wmsFinaCrePeriodRepayDao.getListByEntity(w2);
				 //根据应还款对应每一期的信息进行加和处理
					//调整额加和
				 	Adjustment_amount=Adjustment_amount.add(listInfos.get(0).getAdjustment_amount());
					//应还本金加和
				 	Org_repay_principal=Org_repay_principal.add(listInfoss.get(0).getOrg_repay_principal());
					//本期已还本金和
				 	//Repay_principal=Repay_principal.add(listInfos.get(0).getRepay_principal());
					//未还本金加和
				 	//Un_repay_principal=Un_repay_principal.add(listInfos.get(0).getUn_repay_principal());
					//应还利息加和
				 	Org_repay_interest=Org_repay_interest.add(listInfoss.get(0).getOrg_repay_interest());
					//本期已还利息加和
				 	//Repay_interest=Repay_interest.add(listInfos.get(0).getRepay_interest());
					//未还利息加和
				 	//Un_repay_interest=Un_repay_interest.add(listInfos.get(0).getUn_repay_interest());
				 	repay_no_count++;
				 	repay_no_detail =repay_no_detail +"" +i +"#";
			 }
			 //本期应还款总额:应还本金和 + 应还利息和  + 总滞纳金
			 Total_repayment=Org_repay_principal.add(Org_repay_interest).add(new BigDecimal(finarepay.getTotal_late_fee()));
					
			 //未还款总额:未还本金和+未还利息和 + 还款信息表中总滞纳金
			 //（应还本金总金额-记录中已还本金）+ （应还利息总金额-记录中已还利息）+ 滞纳金总金额
			 WmsFinaCreRealrepayInfo c = new WmsFinaCreRealrepayInfo();
			 c.setWms_cre_credit_head_id(finarepay.getWms_cre_credit_head_id());
			 c.setRepay_no(listSearchforUnPeriod.get(0).getRepay_no().toString());
			 List<WmsFinaCreRealrepayInfo> ccs=wmsFinaCreRealrepayInfoDao.getListByEntity(c);
			 
			 un_total_repayment=Org_repay_principal.subtract(ccs.get(0).getRepay_principal()).add(Org_repay_interest.subtract(ccs.get(0).getRepay_interest())).add(new BigDecimal(finarepay.getTotal_late_fee()));
			 
			 //已还款总额:本期总还款中-未还款总额
			 Is_total_repayment=Total_repayment.subtract(un_total_repayment);

			 //本期欠总滞纳金:还款信息表的总滞纳金
			 Total_cur_late_fee=new BigDecimal(finarepay.getTotal_late_fee());
			 
			 //本期滞纳金=总滞纳金
			 
			 Bq_cur_late_fee=Total_cur_late_fee;
			 
			 //本期未还本金
			 Un_repay_principal=Org_repay_principal.subtract(ccs.get(0).getRepay_principal());
			 //本期未还利息
			 Un_repay_interest=Org_repay_interest.subtract(ccs.get(0).getRepay_interest());
			 
			 //本期已还本金
			 Repay_principal=ccs.get(0).getRepay_principal();
			 //本期已还利息
			 Repay_interest=ccs.get(0).getRepay_interest();
			 
			 //已还滞纳金
			 Cur_late_fee =ccs.get(0).getCur_late_fee();
			 
			 //历史减免额
			 Total_derate =finarepay.getTotal_derate();//应该等于该还款信息表中记录的减免额
			 
			 WmsFinaCreRealrepayInfo w = new WmsFinaCreRealrepayInfo();
			 
			 w.setWms_cre_credit_head_id(finarepay.getWms_cre_credit_head_id());
			 
			 w.setRepay_no(listSearchforUnPeriod.get(0).getRepay_no().toString());	
			 
			 w.setTotal_repayment(Total_repayment);//本期总共要应还金额
            
			 w.setIs_total_repayment(Is_total_repayment);//已还总金额
			 
			 w.setun_total_repayment(un_total_repayment);//未还总金额
			 
			 w.setAdjustment_amount(Adjustment_amount);//调整额和
			 
			 w.setOrg_repay_principal(Org_repay_principal);//本期应还的本金
            
			 w.setRepay_principal(Repay_principal);//本期已还本金
			 
			 w.setUn_repay_principal(Un_repay_principal);//本期未还本金
			 
			 w.setOrg_repay_interest(Org_repay_interest);//本期应还的利息
			 
			 w.setRepay_interest(Repay_interest);//本期已还利息
			 
			 w.setUn_repay_interest(Un_repay_interest);//本期未还利息
			 
			 w.setTotal_cur_late_fee(Total_cur_late_fee);//滞纳金总额
			 
			 w.setCur_late_fee(Cur_late_fee);//已还滞纳金
			 
			 w.setTotal_derate(Total_derate);//历史减免额
			 
			 w.setBq_cur_late_fee(Bq_cur_late_fee);//本期应还滞纳金
			 
			 w.setRepay_no_count(repay_no_count);//合并几期的数
			 
			 w.setRepay_no_detail(repay_no_detail);//明细信息  
			 //还需要变更本期累加的还款总额  还款本金  还款利息

            wmsFinaCreRealrepayInfoDao.updateRecord(w);
		 }
    }
    
    //把获取的excel中的数据变成字符串类型
	private String getCellValue(Row row,Integer i){  
		return row.getCell(i).toString();
	}
	//把获取的excel中的时间由2015/08/09 转换成2015-08-09
	private String getCellValueDate(Row row,Integer i){
		DateFormat format= new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String value="";
		try {
			return value=formatter.format(format.parse(row.getCell(i).toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	/**
	 * 导入excel并解析
	 * @param request
	 * @param session
	 * @param response
	 * @return String
	 * @date 2016-10-20
	 * @author baisong
	 */
    @Override
    public String excelFileToMysql(HttpServletRequest request, HttpSession session,
    		HttpServletResponse response) {
    	String result = "";
    	CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
    	System.out.println("*****************************数据已接收等待处理************************");
    	if (multipartResolver != null
				&& multipartResolver.isMultipart(request)) {
			if (request instanceof MultipartHttpServletRequest) {

				MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;

				Map<String, MultipartFile> fileMap = mRequest.getFileMap();
				boolean flag=true;
				for (Map.Entry<String, MultipartFile> entity : fileMap
						.entrySet()) {
					MultipartFile mf = entity.getValue();
					long fileSize = mf.getSize();
					if (fileSize > 60 * 1024 * 1024) {
						result = "maxsizeerror";
					}
					String srcFileName = mf.getOriginalFilename();// 获取文件名称
					String postfix = "";
					if (srcFileName.lastIndexOf(".") > -1) {
						postfix = srcFileName.substring(
								srcFileName.lastIndexOf(".") + 1).toLowerCase();
						if ("exe".equals(postfix) || "cmd".equals(postfix)
								|| "bat".equals(postfix)) {
							result = "filetypeerror";
						}
					}
					System.out.println("*****************************数据已过滤完毕待读取数据*******************************");
					// 构造 Workbook 对象，mf.getInputStream() 是传入文件路径(获得Excel工作区)
					List<WmsDataRevisionBean> list = new ArrayList<WmsDataRevisionBean>();
						// Excel 2007获取方法
						Workbook book;
						try {
							book = new XSSFWorkbook(mf.getInputStream());
							// 读取表格的第一个sheet页  
							Sheet sheet = book.getSheetAt(0);
							Row row;
							//总行
							int totalRows = sheet.getLastRowNum();
 							for(int i=1;i<=totalRows;i++){
								row =sheet.getRow(i);
								if(row.getCell(0)==null||row.getCell(1)==null||row.getCell(2)==null){
									continue;
								}
								//以上单据可以没有 合同金额  贷款类型  合同签订金额  开始还款日期 还款结束日期 违约日期 还款日 实际还款日期
								WmsDataRevisionBean w = new WmsDataRevisionBean();
								w.setProtocol_code(getCellValue(row, 0));//合同编号
								w.setDebtor_name(getCellValue(row,1));//客户姓名
								w.setDebtor_tel(getCellValue(row, 2));//客户电话
								//w.setCre_loan_type(getCellValue(row,3));//贷款类型
								w.setProtocol_creat_date(getCellValueDate(row,4));//合同签订日期
								//w.setProtocol_amount(new BigDecimal(getCellValue(row,5)));//合同签订金额
								//w.setRepay_begin_date(getCellValueDate(row, 6));//开始还款日期
								//w.setRepay_end_date(getCellValueDate(row,7));//还款结束日期
								//w.setDefault_date(getCellValueDate(row, 8));//违约日期
								//w.setDay(getCellValue(row, 9));//还款日
								//w.setRepay_date(getCellValueDate(row, 10));//实际还款日期
								w.setRepay_period(getCellValue(row, 11));//已还期数
								w.setT_repay_interest(new BigDecimal(getCellValue(row, 12)));//实际应还本金
								w.setT_repay_principal(new BigDecimal(getCellValue(row, 13)));//实际应还利息
								w.setRepay_principal(new BigDecimal(getCellValue(row, 14)));//已还本金
								w.setRepay_interest(new BigDecimal(getCellValue(row, 15)));//已还利息
								w.setUn_pay_principal(new BigDecimal(getCellValue(row, 12)).subtract(new BigDecimal(getCellValue(row, 14))));//剩余本金
								w.setUn_pay_interest(new BigDecimal(getCellValue(row, 13)).subtract(new BigDecimal(getCellValue(row, 15))));//剩余利息
								//w.setCur_overdue_day(getCellValue(row, 18));//本期逾期天数
								//w.setCur_late_fee(new BigDecimal(getCellValue(row, 19)));//本期滞纳金额
								///w.setLiquidated_damages((new BigDecimal(getCellValue(row,5)).multiply(new BigDecimal(0.005))).setScale(2,BigDecimal.ROUND_HALF_DOWN));//日滞纳金
								w.setDerate(new BigDecimal(getCellValue(row, 21)));//减免额
								list.add(w);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
				if(flag){
						result="success";
						System.out.println("*****************************数据已接收,数据处理成功************************");
					}else{
						result="error";
						System.out.println("*****************************数据已接收,数据处理失败************************");
					}	
			}
		}
    	return result;
	}
}
