package com.zx.emanage.inve.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
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

import com.zx.emanage.inve.persist.WmsInveCreditDao;
import com.zx.emanage.inve.persist.WmsInveCreditSplitDetailDao;
import com.zx.emanage.inve.persist.WmsInveCreditSplitHeadDao;
import com.zx.emanage.inve.persist.WmsInveCreditSplitSpecDao;
import com.zx.emanage.inve.service.IWmsInveCreditService;
import com.zx.emanage.inve.vo.WmsInveCreditSearchBeanVO;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.util.gen.entity.WmsInveCredit;
import com.zx.emanage.util.gen.entity.WmsInveCreditSplitDetail;
import com.zx.emanage.util.gen.entity.WmsInveCreditSplitHead;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.PoiUtilDetailTitle;
import com.zx.sframe.util.UploadFileUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecreditService")
public class WmsInveCreditServiceImpl implements IWmsInveCreditService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCreditServiceImpl.class);

	@Autowired
	private WmsInveCreditDao wmsinvecreditDao;

    @Autowired
    private CommonsMultipartResolver multipartResolver;

    @Autowired
    private WmsInveCreditSplitSpecDao wmsinvecreditsplitspecDao;

    @Autowired
    private WmsInveCreditSplitHeadDao wmsinvecreditsplitheadDao;

    @Autowired
    private WmsInveCreditSplitDetailDao wmsinvecreditsplitdetailDao;
	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCreditSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecreditDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCreditSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        if (queryInfo.getWms_inve_credit_split_spec_id() != null)
        {
            paramMap.put("wms_inve_credit_split_spec_id", queryInfo.getWms_inve_credit_split_spec_id());
        }
        List<Map<String, Object>> list = wmsinvecreditDao.searchSplitData(paramMap);
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecreditDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

    /**
     * 
     * @Title: searchSpecList
     * @Description: 获取最新的拆分规则表
     * @param queryInfo
     * @return 
     * @author: Guanxu
     * @time:2016年12月20日 下午1:40:31
     * @see com.zx.emanage.inve.service.IWmsInveCreditService#searchSpecList(com.zx.emanage.inve.vo.WmsInveCreditSearchBeanVO)
     * history:
     * 1、2016年12月20日 Guanxu 创建方法
     */
    @Override
    public List<Map<String, Object>> searchSpecList(WmsInveCreditSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<Map<String, Object>> list = wmsinvecreditDao.searchSpec(paramMap);
        return list;
    }

	@Override
	public WmsInveCredit getInfoByPK(Integer wms_inve_credit_id) {
		return wmsinvecreditDao.get(wms_inve_credit_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCredit bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecreditDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCredit bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecreditDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCredit() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCredit> getListByEntity(WmsInveCredit queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCredit> beanList = wmsinvecreditDao.getListByEntity(queryInfo);
		return beanList;
	}

    /**
     * @Title: importWmsInveCreditInfo
     * @Description:债权数据导入
     * 1、保存上传数据到指定目录
     * 2、保存数据导债权表
     * 3、指定债权表数据code和拆分表code进行数据拆分
     * @param request
     * @param response
     * @param user
     * @return 
     * @author: Guanxu
     * @time:2016年12月14日 下午6:06:19
     * @see com.zx.emanage.inve.service.IWmsInveCreditService#importWmsInveCreditInfo(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2016年12月14日 Guanxu 创建方法
    */
    @Override
    public Map<String, Object> importWmsInveCreditInfo(HttpServletRequest request, HttpServletResponse response, UserBean user)
    {

        String result = "";
        Boolean flag = true;
        Map<String, Object> map = new HashMap<>();

        // 债权list
        List<WmsInveCredit> creditList = new ArrayList<WmsInveCredit>();
        String code = CodeNoUtil.getWmsInveCreditCode();

        if (this.multipartResolver != null && this.multipartResolver.isMultipart(request))
        {
            if (request instanceof MultipartHttpServletRequest)
            {
                MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
                Map<String, MultipartFile> fileMap = mRequest.getFileMap();

                for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet())
                {
                    MultipartFile mf = entity.getValue();
                    long fileSize = mf.getSize();
                    if (fileSize > 60 * 1024 * 1024)
                    {
                        result = "上传附件不能超过60M！";
                        flag = false;
                        break;
                    }
                    String srcFileName = mf.getOriginalFilename();// 获取文件名称
                    String postfix = "";
                    if (srcFileName.lastIndexOf(".") > -1)
                    {
                        postfix = srcFileName.substring(srcFileName.lastIndexOf(".") + 1).toLowerCase();
                        if (!("xls".equals(postfix) || "xlsx".equals(postfix)))
                        {
                            result = "不允许上传模板以外的格式文件！";
                            flag = false;
                            break;
                        }
                    }

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");// 文件上传
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 文件上传
                    try
                    {
                        srcFileName = df.format(new Timestamp(System.currentTimeMillis())) + "_" + user.getUserId() + "_" + srcFileName;
                        UploadFileUtil.uploadFile(srcFileName, mf.getInputStream(), request, "credit");
                    }
                    catch (IOException e2)
                    {
                        result = "文件上传失败";
                        flag = false;
                        break;
                    }
                    
                    if(!flag){
                        map.put("flag", flag);
                        map.put("result", result);
                        return map;
                    }

                    // Excel 2007获取方法
                    Workbook book = null;

                    try
                    {
                        if ("xls".equals(postfix))
                        {
                            book = new HSSFWorkbook(mf.getInputStream());

                        }
                        else if ("xlsx".equals(postfix))
                        {
                            book = new XSSFWorkbook(mf.getInputStream());
                        }
                    }
                    catch (IOException e1)
                    {
                        result = e1.getMessage();
                        e1.printStackTrace();
                    }

                    // 读取表格的第一个sheet页
                    Sheet sheet = book.getSheetAt(0);
                    Row row;
                    // 总行
                    int totalRows = sheet.getLastRowNum();

                    for (int i = 3; i <= totalRows; i++)
                    {
                        row = sheet.getRow(i);
                        // 如果下一行单据编号为空则返回
                        if ("".equals(getValueFromCell(row, 4)))
                        {
                            continue;
                        }

                        WmsInveCredit bean = new WmsInveCredit();
                        bean.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
                        bean.setCreate_user_id(user.getUserId());
                        bean.setLast_update_user_id(user.getUserId());
                        bean.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
                        bean.setEnable_flag("1");
                        bean.setCredit_code(code);
                        bean.setCredit_city(getValueFromCell(row, 1));
                        bean.setCredit_name(getValueFromCell(row, 2));
                        bean.setCredit_id_card(getValueFromCell(row, 3));
                        bean.setCredit_amount(new BigDecimal(getValueFromCell(row, 4)).intValue());
                        bean.setHouse_area(getValueFromCell(row, 5));
                        bean.setBegin_of_date(getValueFromCell(row, 6));
                        bean.setEnd_of_date(getValueFromCell(row, 7));
                        creditList.add(bean);
                    }
                }
            }
        }
        // 批量插入数据
        wmsinvecreditDao.savebatch(creditList);
        // 进行债权拆分
        splitWmsInveCredit(code, user);
        map.put("flag", true);
        map.put("result", "提交成功");
        return map;
    }

    /**
     * @Title: splitWmsInveCredit
     * @Description: 对指定债权进行拆分
     * @param code 
     * @author: Guanxu
     * @time:2016年12月14日 下午6:38:15
     * history:
     * 1、2016年12月14日 Guanxu 创建方法
    */
    private void splitWmsInveCredit(String code, UserBean user)
    {
        // 数据准备：查询债权表进拆分表数据，获取拆分配置表的最新数据
        WmsInveCredit bean = new WmsInveCredit();
        bean.setCredit_code(code);
        List<WmsInveCredit> creditList = wmsinvecreditDao.getListByEntity(bean);
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", "wms_inve_credit_split_spec_id");
        paramMap.put("sortorder", "asc");
        List<Map<String,Object>>  list = wmsinvecreditsplitspecDao.search(paramMap);
        List<WmsInveCreditSplitHead> headList = new ArrayList<>();
        // 进行债权包的地区拆分
        for (WmsInveCredit creditBean : creditList)
        {
            BigDecimal surplus_amount = null;
            BigDecimal org_surplus_amount = null;
            for (Map<String, Object> map : list)
            {
                if (surplus_amount == null)
                {
                    surplus_amount = new BigDecimal(creditBean.getCredit_amount()).multiply((BigDecimal) map.get("split_times"));
                    org_surplus_amount = new BigDecimal(creditBean.getCredit_amount());
                }
                if (surplus_amount.compareTo(BigDecimal.ZERO) <= 0)
                {
                    break;
                }
                WmsInveCreditSplitHead head = new WmsInveCreditSplitHead();
                head.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
                head.setCreate_user_id(user.getUserId());
                head.setLast_update_user_id(user.getUserId());
                head.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
                head.setEnable_flag("1");
                head.setWms_inve_credit_id(creditBean.getWms_inve_credit_id());
                head.setWms_inve_credit_split_spec_id((Integer) map.get("wms_inve_credit_split_spec_id"));
                // 债权总金额*匹配系数*拆分比例/100
                BigDecimal org_split_amount = new BigDecimal(creditBean.getCredit_amount()).multiply((BigDecimal) map.get("split_times")).multiply((BigDecimal) map.get("split_rate")).divide(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_UP);
                if (org_surplus_amount.compareTo(org_split_amount) < 0)
                {
                    org_split_amount = org_surplus_amount;
                }
                if (surplus_amount.compareTo(org_split_amount) < 0)
                {
                    org_split_amount = surplus_amount;
                }
                head.setCredit_split_total_amount(org_split_amount.intValue());
                headList.add(head);
                surplus_amount = surplus_amount.subtract(org_split_amount);
            }
        }
        // 批量插入数据
        wmsinvecreditsplitheadDao.savebatch(headList);
        // 对债权包进行内部二次拆分
        paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", "wms_inve_credit_split_head_id");
        paramMap.put("sortorder", "asc");
        paramMap.put("credit_code", code);
        List<Map<String, Object>> org_headList = wmsinvecreditsplitheadDao.search(paramMap);
        List<WmsInveCreditSplitDetail> detailList = new ArrayList<>();
        for (Map<String, Object> map : org_headList)
        {
            Integer credit_split_total_amount = (Integer) map.get("credit_split_total_amount");
            //拆分金额大于50万
            if(credit_split_total_amount.compareTo(Integer.valueOf(50))>0){
                Integer quotient_amount = credit_split_total_amount.intValue() / 10;
                Integer remainder_amount = credit_split_total_amount.intValue() % 10;
                for (int i = 0; i < quotient_amount; i++)
                {
                    WmsInveCreditSplitDetail detailBean = new WmsInveCreditSplitDetail();
                    detailBean.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
                    detailBean.setCreate_user_id(user.getUserId());
                    detailBean.setLast_update_user_id(user.getUserId());
                    detailBean.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
                    detailBean.setEnable_flag("1");
                    detailBean.setCredit_split_amount(10);
                    detailBean.setWms_inve_credit_split_head_id((Integer) map.get("wms_inve_credit_split_head_id"));
                    detailList.add(detailBean);
                }
                if (remainder_amount > 0)
                {
                    WmsInveCreditSplitDetail detailBean = new WmsInveCreditSplitDetail();
                    detailBean.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
                    detailBean.setCreate_user_id(user.getUserId());
                    detailBean.setLast_update_user_id(user.getUserId());
                    detailBean.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
                    detailBean.setEnable_flag("1");
                    detailBean.setCredit_split_amount(remainder_amount);
                    detailBean.setWms_inve_credit_split_head_id((Integer) map.get("wms_inve_credit_split_head_id"));
                    detailList.add(detailBean);
                }
            }
            // 拆分金额<=10万
            else if (credit_split_total_amount.compareTo(Integer.valueOf(10)) <= 0)
            {
                Integer amount_total = 0;
                // 拆分金额/10*3
                for (int i = 0; i < 3; i++)
                {
                    Integer amount_one = 0;
                    if (i == 2)
                    {
                        amount_one = credit_split_total_amount - amount_total;
                    }
                    else
                    {
                        amount_one = new BigDecimal(credit_split_total_amount).divide(new BigDecimal("10"), 8, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("3")).setScale(0, BigDecimal.ROUND_UP).intValue();
                    }
                    amount_total = amount_one + amount_total;
                    WmsInveCreditSplitDetail detailBean = new WmsInveCreditSplitDetail();
                    detailBean.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
                    detailBean.setCreate_user_id(user.getUserId());
                    detailBean.setLast_update_user_id(user.getUserId());
                    detailBean.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
                    detailBean.setEnable_flag("1");
                    detailBean.setCredit_split_amount(amount_one);
                    detailBean.setWms_inve_credit_split_head_id((Integer) map.get("wms_inve_credit_split_head_id"));
                    detailList.add(detailBean);
                    if (amount_total >= credit_split_total_amount)
                    {
                        break;
                    }
                }

            }
            // 50<=拆分金额<10万
            else
            {
                Integer quotient_amount = new BigDecimal(credit_split_total_amount).divide(new BigDecimal("5"), 0, BigDecimal.ROUND_HALF_UP).intValue();
                for (int i = 0; i < 5; i++)
                {
                    WmsInveCreditSplitDetail detailBean = new WmsInveCreditSplitDetail();
                    detailBean.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
                    detailBean.setCreate_user_id(user.getUserId());
                    detailBean.setLast_update_user_id(user.getUserId());
                    detailBean.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
                    detailBean.setWms_inve_credit_split_head_id((Integer) map.get("wms_inve_credit_split_head_id"));
                    detailBean.setEnable_flag("1");
                    if (i == 4)
                    {
                        detailBean.setCredit_split_amount(credit_split_total_amount - quotient_amount * 4);
                    }
                    else
                    {
                        detailBean.setCredit_split_amount(quotient_amount);
                    }

                    detailList.add(detailBean);
                }
            }
        }
        // 批量插入数据
        wmsinvecreditsplitdetailDao.savebatch(detailList);
    }

    // 获取对应单元格的值
    private String getValueFromCell(Row row, int num)
    {
        if (row.getCell(num) == null)
        {
            return null;
        }
        else
        {
            if ("".equals(row.getCell(num).toString()))
            {
                return "";
            }
        }
        Cell cell = row.getCell(num);
        switch (cell.getCellType())
        {
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell))
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    double value = cell.getNumericCellValue();
                    java.util.Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                    return sdf.format(date);
                }
                else
                {
                    return row.getCell(num).toString();
                }
            default:
                return row.getCell(num).toString();
        }
    }

    // 判断单元格是否为空
    private boolean cellValueIsNull(Row row, int num)
    {
        if (row.getCell(num) == null)
        {
            return true;
        }
        else
        {
            if ("".equals(row.getCell(num).toString()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @Title: exportWmsInveCreditInfo
     * @Description: 导出最后拆分的债权信息
     * @return 
     * @author: Guanxu
     * @time:2016年12月15日 下午3:19:19
     * @see com.zx.emanage.inve.service.IWmsInveCreditService#exportWmsInveCreditInfo()
     * history:
     * 1、2016年12月15日 Guanxu 创建方法
    */
    @Override
    public Map<String, Object> exportWmsInveCreditInfo()
    {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStamp = sdf.format(new java.util.Date());
        String filename = "债权拆分_" + timeStamp + ".xls";
        List<Map<String, Object>> itsheets = new ArrayList<Map<String, Object>>();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<Map<String, Object>> list = wmsinvecreditDao.searchSpec(paramMap);
        for (Map<String, Object> map : list)
        {
            Map<String, Object> perMap = new HashMap<String, Object>();
            perMap.put("sheetName", map.get("split_city"));
            perMap.put("titleType", 2);
            perMap = getTitle(perMap);
            paramMap = new HashMap<String, Object>();
            paramMap.put("wms_inve_credit_split_spec_id", map.get("wms_inve_credit_split_spec_id"));
            List<Map<String, Object>> datalist = wmsinvecreditDao.searchSplitData(paramMap);
            List<List<Object>> datalist_t = changeMapToList(datalist);
            List<Map<String, Object>> leftdata = wmsinvecreditDao.searchSplitDataSum(paramMap);
            perMap.put("datalist", datalist_t);
            perMap.put("leftdata", leftdata);
            itsheets.add(perMap);
        }
        // 进行两个sheet的处理与导出
        PoiUtilDetailTitle poiUtilDetailTitle = new PoiUtilDetailTitle();
        poiUtilDetailTitle.createAndExportWbToFile4credit(filename, itsheets);

        Map<String, Object> res = new HashMap<String, Object>();
        res.put("out_file_name", filename);

        return res;

    }

    /**
     * @Title: changeMapToList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param datalist
     * @return 
     * @author: Guanxu
     * @time:2016年12月15日 下午6:48:58
     * history:
     * 1、2016年12月15日 Guanxu 创建方法
    */
    private List<List<Object>> changeMapToList(List<Map<String, Object>> datalist)
    {
        List<List<Object>> list_result = new ArrayList<>();
        for (Map<String, Object> map : datalist)
        {
            List<Object>  list= new ArrayList<>();
            list.add(map.get("credit_city"));
            list.add(map.get("credit_name"));
            list.add(map.get("credit_id_card"));
            list.add(map.get("credit_amount"));
            list.add(map.get("house_area"));
            list.add(map.get("begin_of_date"));
            list.add(map.get("end_of_date"));
            list.add(map.get("credit_split_amount"));
            list.add(map.get("cus_name"));
            list.add(map.get("inve_amount"));
            list.add(map.get("date_of_payment"));
            list.add(map.get("financial_bill_code"));
            list_result.add(list);
        }
        return list_result;
    }

    /**
     * @Title: getTitle
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @return 
     * @author: Guanxu
     * @time:2016年12月15日 下午5:17:30
     * history:
     * 1、2016年12月15日 Guanxu 创建方法
    */
    private Map<String, Object> getTitle(Map<String, Object> perMap)
    {
        StringBuilder titleString = new StringBuilder();
        titleString.append("抵押包拆分明细台帐:");
        titleString.append("序号,抵押包来源, 抵押包姓名,      身份证号          ,抵押金额（万元）,平米数, 起始日期,终止日期,额度（万元）,理财客户姓名,签单金额,签单日期,合同编号");
        perMap.put("titleString", titleString.toString());
        return perMap;
    }
}
