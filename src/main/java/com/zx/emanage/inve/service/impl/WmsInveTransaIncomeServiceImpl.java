package com.zx.emanage.inve.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.typeconverter.Convert;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.persist.WmsInveTransaIncomeDao;
import com.zx.emanage.inve.persist.WmsInveTransaLogDao;
import com.zx.emanage.inve.service.IWmsInveTransaIncomeService;
import com.zx.emanage.inve.util.CountIncome;
import com.zx.emanage.inve.util.CountIncomeFactory;
import com.zx.emanage.inve.util.IncomeGlobal;
import com.zx.emanage.inve.util.IncomeUtil;
import com.zx.emanage.inve.util.credit.CreditUtils;
import com.zx.emanage.inve.vo.WmsInveTransaIncomeImportVO;
import com.zx.emanage.inve.vo.WmsInveTransaIncomeSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveTransaLogImportVO;
import com.zx.emanage.reportmanage.persist.WmsInveCustomerMonthlyIncomeDao;
import com.zx.emanage.reportmanage.vo.WmsInveCustomerMonthlyIncomeBeanImportVo;
import com.zx.emanage.reportmanage.vo.WmsInveCustomerMonthlyIncomeBeanVo;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.SysDeptDao;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInveTransaIncome;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.ExpertUtil;
import com.zx.sframe.util.UploadFileUtil;
import com.zx.sframe.util.ValidFormat;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvetransaincomeService")
public class WmsInveTransaIncomeServiceImpl implements IWmsInveTransaIncomeService
{
    private static Logger log = LoggerFactory.getLogger(WmsInveTransaIncomeServiceImpl.class);

    @Autowired
    private WmsInveTransaIncomeDao wmsinvetransaincomeDao;

    @Autowired
    private WmsInveTransaLogDao wmsinvetransalogDao;

    @Autowired
    private WmsInveCustomerMonthlyIncomeDao wmsInveCustomerMonthlyIncomeDao;

    @Autowired
    private CommonsMultipartResolver multipartResolver;

    @Autowired
    private PmPersonnelDao pmPersonnelDao;

    @Autowired
    private WmsInveTransaDao wmsInveTransaDao;

    @Autowired
    private SysDeptDao sysDeptDao;

    /**
     * @Title: computeCategoryIncome
     * @Description: 计算某个产品投资多少金额时的收益情况
     * @param categoryId 产品id
     * @param productAccount 投资金额（单位：万元）
     * @return 返回收益情况
     * @author: jinzhm
     * @time:2017年2月21日 下午12:59:18
     * @see com.zx.emanage.inve.service.IWmsInveTransaIncomeService#computeCategoryIncome(int, int)
     * history:
     * 1、2017年2月21日 jinzhm 创建方法
    */
    @Override
    public Map<String, Object> computeCategoryIncome(int categoryId, int productAccount)
    {
        // 获得产品信息
        WmsInvePruductCategory category = IncomeUtil.getWmsInvePruductCategoryDao().get(categoryId);

        // 计算开始时间和结束时间
        java.util.Date startDate = DateUtil.getDate10(new java.util.Date());
        java.util.Date endDate = CreditUtils.getProtocolEndDate(startDate, category.getCategory_deadline());

        // 计算获得收益情况
        List<Map<String, Object>> incomeResultList = CountIncomeFactory.getCountIncome(categoryId)
                                                                       .computeIncome(categoryId,
                                                                                      new BigDecimal(productAccount).multiply(new BigDecimal(
                                                                                                                                             10000)),
                                                                                      startDate, endDate);

        // 总收益，总正常收益，总奖励收益
        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal dueTotalIncome = BigDecimal.ZERO;
        BigDecimal bonusTotalIncome = BigDecimal.ZERO;

        List<Map<String, Object>> incomeList = new ArrayList<Map<String, Object>>();
        Map<String, Object> incomeMap = null;
        // 循环所有收益情况
        for (int i = 0; i < incomeResultList.size(); i++)
        {
            incomeMap = new HashMap<String, Object>();
            // 正常收益
            BigDecimal dueIncome = (BigDecimal) incomeResultList.get(i).get("dueIncome");
            // 奖励收益
            BigDecimal bonusIncome = (BigDecimal) incomeResultList.get(i).get("bonusIncome");
			// 收益日期
            String returnDate = DateUtil.date2String((java.util.Date) incomeResultList.get(i).get("returnDate"),
                                                     "MM月dd日");

            incomeMap.put("dueIncome", dueIncome.toString() + "元");
            incomeMap.put("bonusIncome",
                          (bonusIncome.compareTo(BigDecimal.ZERO) == 0 ? "0.00" : bonusIncome.toString()) + "元");
            incomeMap.put("returnDate", returnDate);
            // 计算汇总金额
            totalIncome = totalIncome.add(dueIncome).add(bonusIncome);
            dueTotalIncome = dueTotalIncome.add(dueIncome);
            bonusTotalIncome = bonusTotalIncome.add(bonusIncome);
            incomeList.add(incomeMap);
        }
        // 设置返回结果数据
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("incomeList", incomeList);
        resultMap.put("totalIncome", totalIncome.toString() + "元");
        resultMap.put("dueTotalIncome", dueTotalIncome.toString() + "元");
        resultMap.put("bonusTotalIncome",
                      (bonusTotalIncome.compareTo(BigDecimal.ZERO) == 0 ? "0.00" : bonusTotalIncome.toString()) + "元");
        return resultMap;
    }

    @Override
    public Map<String, Object> getListWithoutPaging(WmsInveTransaIncomeSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("wms_inve_transa_id", queryInfo.getWms_inve_transa_id());
        List<Map<String, Object>> wmsInveTransaIncomeList = wmsinvetransaincomeDao.search(paramMap);

        List<Map<String, Object>> wmsInveTransaIncomeMapList = new LinkedList<Map<String, Object>>();

        for (int i = 0; i < wmsInveTransaIncomeList.size(); i++)
        {
            Map<String, Object> wmsInveTransaIncome = wmsInveTransaIncomeList.get(i);
            wmsInveTransaIncome.put("product_interest_account2", null);

            // 如果是公益收益，没支付之前不显示
            // 公益收益，续期不需要支付，未支付，已终止状态不需要显示
            if (CountIncome.PAY_TYPE_PUBLIC.equals(String.valueOf(wmsInveTransaIncome.get("pay_type")))
                && (IncomeGlobal.PAY_STATUS_EXTEND_NO_NEED_PAY.equals(String.valueOf(wmsInveTransaIncome.get("pay_status")))
                    || CountIncome.PAY_STATUS_NOT_PAY.equals(String.valueOf(wmsInveTransaIncome.get("pay_status"))) || CountIncome.PAY_STATUS_TERMINATE.equals(String.valueOf(wmsInveTransaIncome.get("pay_status")))))
            {
                continue;
            }

            if (CountIncome.PAY_STATUS_ALREADY_PAY.equals(wmsInveTransaIncome.get("pay_status")))
            {
                wmsInveTransaIncome.put("pay_status", "已支付");
                wmsInveTransaIncome.put("product_interest_account2",
                                        getDecimalFormat(new BigDecimal(
                                                                        wmsInveTransaIncome.get("product_interest_account")
                                                                                           .toString())));
            }
            else if (CountIncome.PAY_STATUS_TERMINATE.equals(wmsInveTransaIncome.get("pay_status")))
            {
                wmsInveTransaIncome.put("pay_status", "已终止");
                wmsInveTransaIncome.put("product_interest_account2", null);
                wmsInveTransaIncome.put("act_return_date", null);
            }
            else if (CountIncome.PAY_STATUS_EXTEND_ALREADY_PAY.equals(wmsInveTransaIncome.get("pay_status")))
            {
                wmsInveTransaIncome.put("pay_status", "已支付");
                wmsInveTransaIncome.put("product_interest_account2",
                                        getDecimalFormat(new BigDecimal(
                                                                        wmsInveTransaIncome.get("product_interest_account")
                                                                                           .toString())));
            }
            else
            {
                wmsInveTransaIncome.put("pay_status", "未支付");
                wmsInveTransaIncome.put("product_interest_account2", null);
                wmsInveTransaIncome.put("act_return_date", null);
            }
            wmsInveTransaIncomeMapList.add(wmsInveTransaIncome);
        }
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", wmsInveTransaIncomeMapList);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsInveTransaIncomeSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransaincomeDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransaincomeDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsInveTransaIncome getInfoByPK(Integer wms_inve_transa_income_id)
    {
        return wmsinvetransaincomeDao.get(wms_inve_transa_income_id);
    }

    @Override
    @Transactional
    public String save(WmsInveTransaIncome bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvetransaincomeDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsInveTransaIncome bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvetransaincomeDao.update(bean); // update a record replace
                                                   // columns, nonsupport null
                                                   // val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsInveTransaIncome() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsInveTransaIncome> getListByEntity(WmsInveTransaIncome queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsInveTransaIncome> beanList = wmsinvetransaincomeDao.getListByEntity(queryInfo);
        return beanList;
    }

    // 格式化小数
    private String getDecimalFormat(BigDecimal str)
    {
        String outStr = "";
        if (str.doubleValue() == 0)
        {
            outStr = "0.00";
        }
        else
        {
            BigDecimal str1 = str;
            DecimalFormat fmt = new DecimalFormat("##,###,###,###,##0.00");
            try
            {
                outStr = fmt.format(str1);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return outStr;
    }

    /**
     * 该方法概述：
     * 主要实现逻辑：更新调整金额是更新到基本收益上还是更新到公益收益上。
     *          如果数据中存在公益收益和基本收益则将调整金额更新到基本收益上。
     *          如果数据中只有公益收益则将调整金额更新到公益收益上。
     * 1. 根据wms_inve_transa_id和return_date获取客户收益信息。
     * 2. 判断获取的信息集合中是否存在支付类型为2(月底支付收益类型),则将调整金额更新的到当前记录上面。
     * 3. 如果获取的信息集合中不存在支付类型为2(月底支付收益类型)的数据,则将调整金额更新到公益收益记录上。
     * @param wmsInveTransaIncome
     * @param user	
     */
    @Override
    @Transactional
    public Map<String, Object> updateInveTransaIncome(WmsInveTransaIncome wmsInveTransaIncome, UserBean user)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 1. 根据对象中的wms_inve_transa_id和return_date获取对应的客户收益信息
        List<WmsInveTransaIncome> list = wmsinvetransaincomeDao.getWmsInveTransaIncomeByWmsInveTransaIdAndReturnDate(wmsInveTransaIncome);

        WmsInveTransaIncome newIncome = null;
        // 2. 判断获取的收益信息中是否存在支付类型为2(月底支付收益类型)的数据,如果存在则将当前记录取出,并且将该记录中的调整金额进行修改
        for (WmsInveTransaIncome income : list)
        {
            if ("2".equals(income.getPay_type()) && "0".equals(income.getPay_status()))
            {
                newIncome = income;
            }
        }
        // 判断对象是否为null,如果为空则说明数据中不存在支付类型为2的数据
        // 则遍历数据判断是否存在公益收益的数据,如果存在则取出数据,并将调整金额更新到公益收益数据上
        if (newIncome == null)
        {
            for (WmsInveTransaIncome income : list)
            {
                if ("3".equals(income.getPay_type()) && "0".equals(income.getPay_status()))
                {
                    newIncome = income;
                }
            }
        }

        /*
         * 判断对象是否等于null
         * 1.如果对象==null说明数据中不存在数据或者不存在支付类型是2,3的数据,则返回error
         * 2.如果对象!=null说明更新对应的数据
         * */
        if (newIncome != null)
        {
            newIncome.setAdjust_datetime(new Timestamp(System.currentTimeMillis()));
            newIncome.setPm_personnel_id(user.getUserId());
            newIncome.setAdjust_amount(wmsInveTransaIncome.getAdjust_amount());
            newIncome.setAdjust_remark(wmsInveTransaIncome.getAdjust_remark());

            int index = wmsinvetransaincomeDao.update(newIncome);

            if (index > 0)
            {
                resultMap.put("result", "success");
            }
            else
            {
                resultMap.put("result", "error");
            }
        }
        else
        {
            resultMap.put("result", "error");
        }

        return resultMap;
    }

    /**
     * 导出客户每月收益报表
     * @param queryInfo
     * @param response
     * @author donghao
     * @date 2016年10月10日17:31:21
     */
    @Override
    public void expertWmsInveTransaIncomeInfo(WmsInveCustomerMonthlyIncomeBeanVo queryInfo, HttpServletResponse response)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
        String dateName = "";
        Map<String, Object> listMap = new HashMap<String, Object>();
        if (queryInfo.getReturn_date() != null && !"".equals(queryInfo.getReturn_date()))
        {
            dateName = format.format(Convert.toDate(queryInfo.getReturn_date()));
        }
        else
        {
            dateName = format.format(new java.util.Date());
        }

        List<Map<String, Object>> incomeTotalList = getIncomeTotalList(queryInfo);
        List<Map<String, Object>> playMoneyList = getPlayMoneyList(queryInfo);

        Map<String, Object> titleMap = new HashMap<String, Object>();
        titleMap.put("收益总表", dateName + "客户收益总表");
        titleMap.put("收益总表_title_cell", 2);
        titleMap.put("打款表", dateName + "客户收益打款表");

        listMap.put("titles", titleMap);

        listMap.put("收益总表", incomeTotalList);
        listMap.put("打款表", playMoneyList);

        try
        {
            String out_file_name = dateName + "收益报表_平台版本.xls";
            ExpertUtil eu = new ExpertUtil();
            eu.expertExcel("收益报表模板.xls", listMap, out_file_name, "titles", 2, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /**
     * 获取打款表数据
     * 
     * @param wmsInveTransaIncome
     * @return
     * @author donghao
     * @date 2016年10月10日17:54:02
     */
    private List<Map<String, Object>> getPlayMoneyList(WmsInveCustomerMonthlyIncomeBeanVo queryInfo)
    {
        Map<String, Object> paramMap = getParamsMap(queryInfo);
        List<Map<String, Object>> list = wmsinvetransaincomeDao.getPlayMoneyList(paramMap);
        return list;
    }

    /**
     * 获取收益总表数据
     * 
     * @param wmsInveTransaIncome
     * @return
     * @author donghao
     * @date 2016年10月10日17:52:36
     */
    private List<Map<String, Object>> getIncomeTotalList(WmsInveCustomerMonthlyIncomeBeanVo queryInfo)
    {
        Map<String, Object> paramMap = getParamsMap(queryInfo);
        paramMap.put("sortname", "w2.date_of_payment");
        paramMap.put("sortorder", "desc");
        List<Map<String, Object>> list = wmsinvetransaincomeDao.getIncomeTotalList(paramMap);
        return list;
    }

    /**
     * 根据出入的对象封装map参数
     * 
     * @param queryInfo
     * @return
     * @author donghao
     * @date 2016年10月11日10:51:36
     */
    private Map<String, Object> getParamsMap(WmsInveCustomerMonthlyIncomeBeanVo queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        { // 单据编号
            paramMap.put("bill_code", queryInfo.getBill_code());
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1"))
        {// 产品名称
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {// 客户姓名 模糊
            paramMap.put("cus_name", queryInfo.getCus_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getSigning_date_begin()))
        {// 签单日期范围起始
            paramMap.put("signing_date_begin", queryInfo.getSigning_date_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getSigning_date_end()))
        {// 签单日期范围结束
            paramMap.put("signing_date_end", queryInfo.getSigning_date_end());
        }
        if (queryInfo.getReturn_date() != null && !"".equals(queryInfo.getReturn_date()))
        {// 应支付时间范围结束 精确查询
            if (Convert.toDate(queryInfo.getReturn_date()).after(new Date(System.currentTimeMillis())))
            {// 用户输入的日期不能大于当前时间
                return null;
            }
            else
            {
                paramMap.put("return_date", Convert.toDate(queryInfo.getReturn_date()));
                if (returnDateCompertoNowDate(queryInfo.getReturn_date()) == -1)
                {
                    paramMap.put("date_before", 1);// 说明是历史数据
                }
                else
                {
                    paramMap.put("date_before", 2);// 说明是当前数据
                }
            }
        }
        if (queryInfo.getIs_bonus_rate() != null)
        {
            paramMap.put("is_bonus_rate", queryInfo.getIs_bonus_rate());
        }
        if (queryInfo.getIs_extent_rate() != null)
        {
            paramMap.put("is_extent_rate", queryInfo.getIs_extent_rate());
        }
        if (queryInfo.getIs_query_data_type() != null)
        {
            paramMap.put("is_query_data_type", queryInfo.getIs_query_data_type());
        }
        if (queryInfo.getIs_query_pay_status() != null)
        {
            paramMap.put("is_query_pay_status", queryInfo.getIs_query_pay_status());
        }
        return paramMap;
    }

    /**
     * 
     * @Title: returnDateCompertoNowDate
     * @Description: 比较时间大小
     * @param returnDateStr 收益日期字符串类型,格式 yyyy-MM
     * @return 返回整型数字,如果收益月份小于当前时间的月份则返回-1,相等返回0,大于返回1
     * @author: DongHao
     * @time:2016年11月15日 下午4:42:21
     * history:
     * 1、2016年11月15日 DongHao 创建方法
     */
    private int returnDateCompertoNowDate(String returnDateStr)
    {
        SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM");
        String strNowDate = formate.format(new java.util.Date());
        try
        {
            java.util.Date nowDate = formate.parse(strNowDate);
            java.util.Date returnDate = formate.parse(returnDateStr);
            return returnDate.compareTo(nowDate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 
     * @Title: importWmsCreCreditNotaryWarnData
     * @Description: 批量导入客户收益
     * @param request
     * @param response
     * @param queryInfo
     * @param user
     * @return 
     * @author: jinzm
     * @time:2016年12月1日 下午3:49:16
     * history:
     * 1、2016年12月1日 Administrator 创建方法
     */
    @Override
    @Transactional
    public WmsInveCustomerMonthlyIncomeBeanImportVo importWmsInveTransaIncomeInfo(HttpServletRequest request,
                                                                                  HttpServletResponse response,
                                                                                  WmsInveCustomerMonthlyIncomeBeanImportVo queryInfo,
                                                                                  UserBean user)
    {
        String result = "";
        int err_num = 0;
        Boolean flag = true;

        // 收益信息错误提示内容
        StringBuilder builder = new StringBuilder();
        // 验证使用的集合
        List<Map<String, Object>> validateMapList = new ArrayList<Map<String, Object>>();
        // 验证使用map
        Map<String, Object> validateMap = null;

        // 客户收益list
        List<WmsInveTransaIncomeImportVO> incomeList = new ArrayList<WmsInveTransaIncomeImportVO>();
        // 日志list
        List<WmsInveTransaLogImportVO> logList = new ArrayList<WmsInveTransaLogImportVO>();

        // 导入日期(通过模板标题拼接)
        Date return_date = null;

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
                        srcFileName = df.format(new Timestamp(System.currentTimeMillis())) + "_" + user.getUserId()
                                      + "_" + srcFileName;
                        UploadFileUtil.uploadFile(srcFileName, mf.getInputStream(), request);
                    }
                    catch (IOException e2)
                    {
                        result = "文件上传失败";
                        flag = false;
                        break;
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

                    // sheet表标题
                    String title = sheet.getRow(0).getCell(2).toString();
                    // 截取remark
                    String remark = sheet.getRow(0).getCell(2).toString().substring(0, title.length() - 2);
                    // 拼接查询月份
                    title = title.substring(0, 4) + "-" + title.substring(5, 7) + "-01";

                    try
                    {
                        return_date = new java.sql.Date(format.parse(title).getTime());
                    }
                    catch (ParseException e)
                    {
                        result = "导入日期有错误";
                        e.printStackTrace();
                    }

                    for (int i = 2; i <= totalRows; i++)
                    {
                        row = sheet.getRow(i);

                        // 在模板中查找支付状态
                        String payStatus = row.getCell(17).toString().trim();

                        // 如果导入模板中支付状态是未支付，需要修改库中支付状态为已支付
                        if ("未支付".equals(payStatus))
                        {
                            // 如果下一行单据编号为空则返回
                            if ("".equals(getValueFromCell(row, 1)) || null == getValueFromCell(row, 1))
                            {
                                continue;
                            }

                            // 签单金额格式校验
                            if (cellValueIsNull(row, 4) || !ValidFormat.isPositiveDecimal(getValueFromCell(row, 4)))
                            {
                                result = "第" + (i + 1) + "行签单金额填写不正确！";
                                flag = false;
                                break;
                            }

                            // 应付总收益格式校验
                            if (cellValueIsNull(row, 16) || !ValidFormat.isPositiveDecimal(getValueFromCell(row, 16)))
                            {
                                result = "第" + (i + 1) + "行应付总收益填写不正确！";
                                flag = false;
                                break;
                            }

                            // 未支付状态下获得后面隐藏的收益表主键（如果是正常收益和公益收益合并的收益信息会用逗号隔开两个id）
                            String transaIncomeIds = row.getCell(18).toString().trim();
                            builder.append(transaIncomeIds + ",");

                            // 给验证map赋值
                            validateMap = new HashMap<String, Object>();
                            // 要导入的收益表主键id
                            validateMap.put("transaIncomeIds", transaIncomeIds);
                            // 收益金额
                            validateMap.put("income", getRoundingByTwoReturnBigDecimal(row, 16));
                            // 理财单据编号
                            validateMap.put("bill_code", row.getCell(1).toString().trim());
                            validateMapList.add(validateMap);

                            // 上单日志表
                            WmsInveTransaLogImportVO wmsInveTransaLogImportVO = new WmsInveTransaLogImportVO();
                            // 封装wmsInveTransaLog
                            wmsInveTransaLogImportVO.setBill_code(row.getCell(1).toString());
                            wmsInveTransaLogImportVO.setProduct_account(getRoundingByTwoReturnBigDecimal(row, 4));
                            wmsInveTransaLogImportVO.setProduct_interest_account(getRoundingByTwoReturnBigDecimal(row,
                                                                                                                  16));
                            wmsInveTransaLogImportVO.setRemark(remark);
                            wmsInveTransaLogImportVO.setOperate_item("支付收益");
                            wmsInveTransaLogImportVO.setCreate_user_id(user.getUserId());
                            wmsInveTransaLogImportVO.setCreate_user_name(user.getRealname());
                            logList.add(wmsInveTransaLogImportVO);
                        }
                    }
                }
            }
        }

        if (flag && validateMapList.size() == 0)
        {
            flag = false;
            result = "导入文件中没有未支付状态的记录！";
        }

        if (flag)
        {
            StringBuilder resBuilder = new StringBuilder();
            // 循环要验证的集合
            for (int i = 0; i < validateMapList.size(); i++)
            {
                validateMap = validateMapList.get(i);
                Map<String, Object> resultMap = wmsinvetransaincomeDao.validateTransaIncomeStatusAndIncome(validateMap);
                if (resultMap == null)
                {
                    resBuilder.append(validateMap.get("bill_code") + "，");
                }
                else
                {
                    if (!"0".equals(resultMap.get("pay_status")))
                    {
                        resBuilder.append(validateMap.get("bill_code") + "，");
                    }
                }
            }
            // 如果有收益存在问题
            if (resBuilder.length() > 0)
            {
                resBuilder.append("以上理财单据编号的收益存在问题！");
                result = resBuilder.toString();
                flag = false;
            }
        }

        if (flag)
        {

            // 批量更新理财收益的支付状态及实际支付时间
            String transaIncomeIds = builder.toString().substring(0, builder.length() - 1);
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("transaIncomeIds", transaIncomeIds);
            paramMap.put("last_update_user_id", user.getUserId());
            paramMap.put("return_date", return_date);
            wmsinvetransaincomeDao.updateIncomePayStatusAndActReturnDateByBillCode(paramMap);

            // 批量保存日志表
            wmsinvetransalogDao.saveImportWmsInveTransaLog(logList);
        }
        queryInfo.setResult(result);
        queryInfo.setErr_num(err_num);
        queryInfo.setFlag(flag.toString());
        return queryInfo;
    }

    // 获取对应单元格的值
    private String getValueFromCell(Row row, int num)
    {
        if (row == null)
        {
            return null;
        }
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
        return row.getCell(num).toString();
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

    // 保留两位小数
    private BigDecimal getRoundingByTwoReturnBigDecimal(Row row, int num)
    {
        if (row.getCell(num) == null)
        {
            return null;
        }
        else
        {
            if ("".equals(row.getCell(num).toString()))
            {
                return null;
            }
        }
        String value = row.getCell(num).toString();
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * @Title: getCusIncomeWithCustomerSummary
     * @Description: 根据客户经理id和查询月份分页查询按客户维度汇总的客户经理的所有客户收益信息
     * @param page 要查询页数
     * @param page_size 每页显示计数数
     * @param statics_month 查询月份
     * @param salesman_id 客户经理id
     * @param cus_name 客户姓名
     * @return 根据客户经理id和查询月份分页查询按客户维度汇总的客户经理的所有客户收益信息，和
     * @author: jinzhm
     * @time:2017年1月3日 下午3:09:04
     * @see com.zx.emanage.inve.service.IWmsInveTransaIncomeService#getCusIncomeWithCustomerSummary(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer)
     * history:
     * 1、2017年1月3日 jinzhm 创建方法
    */
    @Override
    public Map<String, Object> getCusIncomeWithCustomerSummary(Integer page, Integer page_size, String statics_month,
                                                               String salesman_id, String cus_name)
    {
        // 查询条件封装
        Map<String, Object> paramMap = new HashMap<String, Object>();

        // 封装查询条件
        paramMap.put("offset", getOffset(page, page_size));
        paramMap.put("page_size", getPageSize(page_size));
        paramMap.put("statics_month", statics_month);
        paramMap.put("salesman_id", salesman_id);
        // 如果有值就传入查询
        if (!StringUtil.isEmpty(cus_name))
        {
            paramMap.put("cus_name", "%" + cus_name + "%");
        }
        // 查询客户收益信息按客户汇总
        List<Map<String, Object>> cusIncomeList = null;

        // 如果是查询是当前月数据
        if (DateUtil.date2String(DateUtil.getDate10(new java.util.Date()), "yyyy-MM").equals(statics_month))
        {
            cusIncomeList = wmsinvetransaincomeDao.getCusIncomeWithCustomerSummaryRealTime(paramMap);
        }
        // 如果查询是查询历史月份
        else
        {
            cusIncomeList = wmsinvetransaincomeDao.getCusIncomeWithCustomerSummary(paramMap);
        }

        // 查询客户经理姓名，短工号，团队名称等信息
        Map<String, Object> resMap = pmPersonnelDao.getPersonnelInfoById(paramMap);

        if (resMap == null)
        {
            resMap = new HashMap<String, Object>();
        }
        // 封装返回数据
        resMap.put("income_list", cusIncomeList);
        return resMap;
    }

    /**
     * @Title: getCusIncomeWithTransaSummary
     * @Description: 根据查询月份和客户id查询某个客户下单据收益信息
     * @param page 要查询页数
     * @param page_size 每页显示计数数
     * @param statics_month 查询月份
     * @param cus_id 客户经理id
     * @return 按客户维度汇总的某个客户经理的客户收益信息
     * @author: jinzhm
     * @time:2017年1月3日 下午4:33:55
     * @see com.zx.emanage.inve.service.IWmsInveTransaIncomeService#getCusIncomeWithTransaSummary(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
     * history:
     * 1、2017年1月3日 jinzhm 创建方法
    */
    @Override
    public Map<String, Object> getCusIncomeWithTransaSummary(Integer page, Integer page_size, String statics_month,
                                                             String cus_id)
    {
        // 查询条件封装
        Map<String, Object> paramMap = new HashMap<String, Object>();

        // 封装查询条件
        paramMap.put("offset", getOffset(page, page_size));
        paramMap.put("page_size", getPageSize(page_size));
        paramMap.put("statics_month", statics_month);
        paramMap.put("cus_id", cus_id);
        // 查询客户收益信息按客户汇总
        List<Map<String, Object>> cusIncomeList = null;
        // 如果查询是当前月份查询
        if (DateUtil.date2String(DateUtil.getDate10(new java.util.Date()), "yyyy-MM").equals(statics_month))
        {
            cusIncomeList = wmsinvetransaincomeDao.getCusIncomeWithTransaSummaryRealTime(paramMap);
        }
        // 是历史查询
        else
        {
            cusIncomeList = wmsinvetransaincomeDao.getCusIncomeWithTransaSummary(paramMap);
        }

        Map<String, Object> resMap = null;
        // 如果有查询到收益信息
        if (!cusIncomeList.isEmpty())
        {
            Map<String, Object> cusIncomeMap = cusIncomeList.get(0);
            paramMap.put("salesman_id", cusIncomeMap.get("salesman_id"));
            // 查询客户经理姓名，短工号，团队名称等信息
            resMap = pmPersonnelDao.getPersonnelInfoById(paramMap);
            if (resMap == null)
            {
                resMap = new HashMap<String, Object>();
            }
            else
            {
                resMap.put("cus_name", cusIncomeMap.get("cus_name"));
            }
        }
        else
        {
            resMap = new HashMap<String, Object>();
        }

        // 封装返回数据
        resMap.put("income_list", cusIncomeList);
        return resMap;
    }

    /**
     * @Title: getCusIncomeWithSalesmanSummary
     * @Description: 查询某个月团队下所有客户经理维度汇总的收益信息集合
     * @param page 要查询页数
     * @param page_size 每页显示计数数
     * @param statics_month 查询月份
     * @param team_id 团队id（多个用英文逗号隔开）
     * @param salesman_name 客户经理姓名或短工号
     * @return 返回某个月团队下所有客户经理维度汇总的收益信息集合
     * @author: jinzhm
     * @time:2017年1月3日 下午5:44:11
     * @see com.zx.emanage.inve.service.IWmsInveTransaIncomeService#getCusIncomeWithSalesmanSummary(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
     * history:
     * 1、2017年1月3日 jinzhm 创建方法
    */
    @Override
    public Map<String, Object> getCusIncomeWithSalesmanSummary(Integer page, Integer page_size, String statics_month,
                                                               String team_ids, String salesman_name)
    {
        // 查询条件封装
        Map<String, Object> paramMap = new HashMap<String, Object>();

        // 封装查询条件
        paramMap.put("offset", getOffset(page, page_size));
        paramMap.put("page_size", getPageSize(page_size));
        paramMap.put("statics_month", statics_month);
        paramMap.put("team_ids", CollectionUtils.arrayToList(team_ids.split(",")));
        // 如果有值就传入查询
        if (!StringUtil.isEmpty(salesman_name))
        {
            paramMap.put("salesman_name", salesman_name);
        }
        // 查询客户收益信息按客户汇总
        List<Map<String, Object>> cusIncomeList = wmsinvetransaincomeDao.getCusIncomeWithSalesmanSummary(paramMap);

        // 设置团队名称
        Map<String, Object> resMap = new HashMap<String, Object>();
        // 封装返回数据
        resMap.put("income_list", cusIncomeList);
        return resMap;
    }

    /**
     * @Title: setDatebyCalendar
     * @Description: 给传入日期增加月份
     * @param sDate 日期
     * @param i 要增加的月份
     * @return 增加月份后的日期
     * @author: jinzhm
     * @time:2017年2月11日 下午4:34:33
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
     */
    private Date setDatebyCalendar(java.util.Date sDate, int i)
    {
        java.sql.Date date1;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(sDate);
        calendar.add(Calendar.MONTH, +i);
        java.util.Date date_ = calendar.getTime();
        date1 = new java.sql.Date(date_.getTime());
        return date1;
    }

    /**
     * @Title: getPageSize
     * @Description: 获得每页显示页数
     * @param page_size 每页显示页数
     * @return 每页显示页数
     * @author: jinzhm
     * @time:2017年1月4日 上午11:08:04
     * history:
     * 1、2017年1月4日 jinzhm 创建方法
     */
    private int getPageSize(Integer page_size)
    {
        // 如果每页显示页数传入是0或是null，返回10
        if (page_size == null || page_size == 0)
        {
            return 10;
        }
        return page_size;
    }

    /**
     * @Title: getOffset
     * @Description: 根据页码和每页查询数获得查询开始位置
     * @param page 页码
     * @param page_size 每月查询数
     * @return 返回查询开始位置
     * @author: jinzhm
     * @time:2017年1月3日 下午4:37:13
     * history:
     * 1、2017年1月3日 jinzhm 创建方法
     */
    private int getOffset(Integer page, Integer page_size)
    {
        if (page == null || page == 0)
        {
            page = 1;
        }
        page_size = getPageSize(page_size);
        int offset = (page - 1) * page_size;
        return offset > 0 ? offset : 0;
    }

    /**
     * @Title: computeCategoryIncome
     * @Description: 根据合同生效日期进行收益计算
     * @param wms_inve_pruduct_category_id
     * @param product_account
     * @param date
     * @return 
     * @author: Administrator
     * @time:2017年3月27日 下午5:16:12
     * @see com.zx.emanage.inve.service.IWmsInveTransaIncomeService#computeCategoryIncome(int, int, java.util.Date)
     * history:
     * 1、2017年3月27日 Administrator 创建方法
    */
    @Override
    public Map<String, Object> computeCategoryIncome(int categoryId, int productAccount, java.util.Date date)
    {

        // 获得产品信息
        WmsInvePruductCategory category = IncomeUtil.getWmsInvePruductCategoryDao().get(categoryId);

        // 计算开始时间和结束时间
        java.util.Date startDate = DateUtil.getDate10(date);
        java.util.Date endDate = CreditUtils.getProtocolEndDate(startDate, category.getCategory_deadline());

        // 计算获得收益情况
        List<Map<String, Object>> incomeResultList = CountIncomeFactory.getCountIncome(categoryId).computeIncome(categoryId, new BigDecimal(productAccount).multiply(new BigDecimal(10000)), startDate, endDate);

        // 总收益，总正常收益，总奖励收益
        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal dueTotalIncome = BigDecimal.ZERO;
        BigDecimal bonusTotalIncome = BigDecimal.ZERO;

        List<Map<String, Object>> incomeList = new ArrayList<Map<String, Object>>();
        Map<String, Object> incomeMap = null;
        // 循环所有收益情况
        for (int i = 0; i < incomeResultList.size(); i++)
        {
            incomeMap = new HashMap<String, Object>();
            // 正常收益
            BigDecimal dueIncome = (BigDecimal) incomeResultList.get(i).get("dueIncome");
            // 奖励收益
            BigDecimal bonusIncome = (BigDecimal) incomeResultList.get(i).get("bonusIncome");
            // 收益日期
            String returnDate = DateUtil.date2String((java.util.Date) incomeResultList.get(i).get("returnDate"),
                                                     "yyyy-MM-dd");

            incomeMap.put("dueIncome", dueIncome.toString());
            incomeMap.put("bonusIncome", (bonusIncome.compareTo(BigDecimal.ZERO) == 0 ? "0.00" : bonusIncome.toString()));
            incomeMap.put("returnTotalIncome", dueIncome.add(bonusIncome).toString());
            incomeMap.put("returnDate", returnDate);
            // 计算汇总金额
            totalIncome = totalIncome.add(dueIncome).add(bonusIncome);
            dueTotalIncome = dueTotalIncome.add(dueIncome);
            bonusTotalIncome = bonusTotalIncome.add(bonusIncome);
            incomeList.add(incomeMap);
        }
        // 设置返回结果数据
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("incomeList", incomeList);
        resultMap.put("totalIncome", totalIncome.toString() + "元");
        resultMap.put("dueTotalIncome", dueTotalIncome.toString() + "元");
        resultMap.put("bonusTotalIncome", (bonusTotalIncome.compareTo(BigDecimal.ZERO) == 0 ? "0.00" : bonusTotalIncome.toString()) + "元");
        return resultMap;

    }

    /**
     * @Title: getIncomeListGtRedeemDate
     * @Description: 筛选集合中日期大于赎回日期的数据
     * @param incomeList
     * @param redeem_date 
     * @author: zhangyunfei
     * @time:2017年3月27日 下午6:18:28
     * @see com.zx.emanage.inve.service.IWmsInveTransaIncomeService#getIncomeListGtRedeemDate(java.util.List, java.util.Date)
     * history:
     * 1、2017年3月27日 Administrator 创建方法
    */
    @Override
    public List<Map<String, Object>> getIncomeListGtRedeemDate(List<Map<String, Object>> incomeList, java.util.Date redeem_date)
    {
        List<Map<String, Object>> tList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < incomeList.size(); i++)
        {
            if (!DateUtil.before(DateUtil.strDate((String) incomeList.get(i).get("returnDate"), null), redeem_date))
            {
                tList.add(incomeList.get(i));
            }
        }

        return tList;
    }

    /**
     * @Title: calTotal_Income
     * @Description: 计算客户收益总和
     * @param cIMap 
     * @author: Administrator
     * @time:2017年3月28日 上午11:50:01
     * @see com.zx.emanage.inve.service.IWmsInveTransaIncomeService#calTotal_Income(java.util.Map)
     * history:
     * 1、2017年3月28日 Administrator 创建方法
    */
    @Override
    public void calTotal_Income(Map<String, Object> cIMap)
    {
        BigDecimal total_income = BigDecimal.ZERO;
        List<Map<String, Object>> incomeList = (List<Map<String, Object>>) cIMap.get("incomeList");
        for (int i = 0; i < incomeList.size(); i++)
        {
            total_income = total_income.add(new BigDecimal(incomeList.get(i).get("returnTotalIncome").toString()));
        }
        cIMap.put("total_income", total_income + "元");
    }

}
