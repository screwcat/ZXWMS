package com.zx.emanage.loanappro.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreContractLenderDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineContactDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineHouseinfoDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingPaymentDao;
import com.zx.emanage.loanappro.persist.WmsCreApproBorrowProtocolDao;
import com.zx.emanage.loanappro.persist.WmsCreCreditServiceTypeDao;
import com.zx.emanage.loanappro.persist.WmsSysPropertyDao;
import com.zx.emanage.loanappro.service.IWmsSysPropertyService;
import com.zx.emanage.loanappro.vo.WmsSysPropertyPropertySearchBeanVO;
import com.zx.emanage.loanappro.vo.WmsSysPropertySearchBeanVO;
import com.zx.emanage.remind.persist.WmsCreCreditNotaryWarnDao;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.util.gen.WmsHelp;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsCreCreditServiceType;
import com.zx.emanage.util.gen.entity.WmsSysProperty;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsSysPropertyServiceImpl
 * 模块名称：字典表
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 baisong 创建文件
 */
@Service("wmssyspropertyService")
public class WmsSysPropertyServiceImpl implements IWmsSysPropertyService
{
    private static Logger log = LoggerFactory.getLogger(WmsSysPropertyServiceImpl.class);

    @Autowired
    private WmsSysPropertyDao wmssyspropertyDao;

    @Autowired
    private WmsCreCreditHeadDao wmsCreCreditHeadDao;// 贷款主表

    @Autowired
    private WmsCreCreditLineCustomerChangeHeadDao wmsCreCreditLineCustomerChangeHeadDao;// 贷款信息变更表

    @Autowired
    private WmsCreCustomerChangeLineContactDao wmsCreCustomerChangeLineContactDao;// 贷款信息联系人表

    @Autowired
    private WmsCreCreditHeadDao wmscrecreditheadDao_m;// 信贷款主表

    @Autowired
    private WmsCreCustomerChangeLineHouseinfoDao wmsCreCustomerChangeLineHouseinfoDao;// 信用贷款客户房产信息变更表
    
    @Autowired
    private WmsCreContractLenderDao wmsCreContractLenderDao;
    
    @Autowired
    private WmsCreApproBorrowProtocolDao wmsCreApproBorrowProtocolDao;
    
    @Autowired
    private WmsCreCreditNotaryWarnDao wmscrecreditnotarywarnDao;
    
    @Autowired
    private WmsCreCreditServiceTypeDao wmsCreCreditServiceTypeDao;
    
    @Autowired
    private WmsCreHousingPaymentDao wmscrehousingpaymentDao;
    
    @Override
    public Map<String, Object> getListWithoutPaging(WmsSysPropertySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmssyspropertyDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsSysPropertySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmssyspropertyDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmssyspropertyDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsSysProperty getInfoByPK(Integer wms_sys_property_id)
    {
        return wmssyspropertyDao.get(wms_sys_property_id);
    }

    @Override
    @Transactional
    public String save(WmsSysProperty bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmssyspropertyDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsSysProperty bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmssyspropertyDao.update(bean); // update a record replace
                                              // columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsSysProperty() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsSysProperty> getListByEntity(WmsSysProperty queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsSysProperty> beanList = wmssyspropertyDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public Map<String, Object> searchforhouse(String protocol_type, Integer wms_cre_credit_head_id)
    {
        // protocol_type为合同编号 页面上会有标识 （ protocol_type="1";//合同类型编号 ）
        Map<String, Object> map = new HashMap();
        // 从贷款申请表中获取相应的值
        map = getInfoforborrow(map, wms_cre_credit_head_id, protocol_type);
        List<Integer> list = new ArrayList<Integer>();

        // 如果合同类型为1和2 则执行下面代码
        if (protocol_type.equals("1") || protocol_type.equals("2"))
        {
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(5);// 原来5为4
            // 添加共同的list
            setList(list, 0);
            List<WmsSysProperty> map1 = wmssyspropertyDao.searchforhouse(list);
            WmsSysProperty wms = (WmsSysProperty) map1.get(0);
            map.put("compensate", wms.getProperty_value());// 提前还款利率 小写
            map.put("compensate_caps", wms.getProperty_value_caps());// 大写
            WmsSysProperty wms1 = (WmsSysProperty) map1.get(1);
            map.put("yuqi_damages", wms1.getProperty_value());// 逾期还款利率 小写
            map.put("yuqi_damages_caps", wms1.getProperty_value_caps());// 大写
            WmsSysProperty wms2 = (WmsSysProperty) map1.get(2);
            map.put("consult_service_cost", wms2.getProperty_value());// 咨询服务费标准//
                                                                      // 小写
            WmsSysProperty wms3 = (WmsSysProperty) map1.get(3);
            map.put("service_cost_month", wms3.getProperty_value());// 居间服务费标准
            setContact(map, wms_cre_credit_head_id);// 如果有保人 设置保人姓名
            setmap(map, map1, 0, protocol_type); // 设置共同的参数
        }
        // 如果合同类型为3和4 则执行下面代码
        if (protocol_type.equals("3") || protocol_type.equals("4"))
        {
            list.add(2);
            list.add(4);// 原来4为3
            list.add(5);
            // 添加共同的list
            setList(list, 0);
            List<WmsSysProperty> map1 = wmssyspropertyDao.searchforhouse(list);

            WmsSysProperty wms = (WmsSysProperty) map1.get(0);
            map.put("yuqi_damages", wms.getProperty_value());// 逾期还款利率 小写
            map.put("yuqi_damages_caps", wms.getProperty_value_caps());// 大写
            WmsSysProperty wms1 = (WmsSysProperty) map1.get(1);
            map.put("consult_service_cost", wms1.getProperty_value());// 咨询服务费标准
            WmsSysProperty wms2 = (WmsSysProperty) map1.get(2);
            map.put("service_cost_month", wms2.getProperty_value());// 居间服务费标准
            setContact(map, wms_cre_credit_head_id);// 如果有保人 设置保人姓名
            setmap(map, map1, 1, protocol_type); // 设置共同的参数
        }
        if (protocol_type.equals("6") || protocol_type.equals("7"))
        {
            list.add(1);
            list.add(2);// 借款用于 信贷为资金周转
            list.add(3);
            if (protocol_type.equals("6"))
            {
                // 添加共同的list
                setList(list, 2);
            }
            else if (protocol_type.equals("7"))
            {
                // 添加共同的list
                setList(list, 3);
            }
            List<WmsSysProperty> map1 = wmssyspropertyDao.searchforhouse(list);
            WmsSysProperty wms = (WmsSysProperty) map1.get(0);
            map.put("compensate", wms.getProperty_value());// 提前还款利率 小写
            map.put("compensate_caps", wms.getProperty_value_caps());// 大写
            WmsSysProperty wms1 = (WmsSysProperty) map1.get(1);
            map.put("yuqi_damages", wms1.getProperty_value());// 逾期还款利率 小写
            map.put("yuqi_damages_caps", wms1.getProperty_value_caps());// 大写
            WmsSysProperty wms2 = (WmsSysProperty) map1.get(2);
            map.put("consult_service_cost", wms2.getProperty_value());// 咨询服务费标准//
                                                                      // 小写
            setContact(map, wms_cre_credit_head_id);// 如果有保人 设置保人姓名
            setmap(map, map1, 1, protocol_type); // 设置共同的参数
        }
        // 如果合同类型为5 则执行下面代码
        if (protocol_type.equals("5"))
        {
            // list.add(3);
            list.add(6);// 借款用于 信贷为资金周转
            list.add(7);
            setCreLoanTypeVal(map, list);// 判断信贷类型
            // 添加共同的list
            setList(list, 4);
            List<WmsSysProperty> map1 = wmssyspropertyDao.searchforhouse(list);
            /*
             * WmsSysProperty wms = (WmsSysProperty) map1.get(0);
             * map.put("consult_service_cost", wms.getProperty_value());//
             * 咨询服务费标准
             */
            /*
             * WmsSysProperty wms1 = (WmsSysProperty) map1.get(1);
             * map.put("service_cost_month", wms1.getProperty_value());//
             * 居间服务费标准利率
             */
            WmsSysProperty wms0 = (WmsSysProperty) map1.get(0);
            map.put("borrow_purpose", wms0.getProperty_value());// 信贷借款用涂
            WmsSysProperty wms1 = (WmsSysProperty) map1.get(1);
            map.put("yuqi_damages", wms1.getProperty_value());// 逾期还款利率 小写
            map.put("yuqi_damages_caps", wms1.getProperty_value_caps());// 大写
            setmap(map, map1, 2, protocol_type); // 设置共同的参数
        }

        // 合同编号自动生成
        String yearnum = CodeNoUtil.getProCreCode();// 合同编号
        map.put("protocol_id_year_num", yearnum);
        String code[] = yearnum.split("年第");
        if (code.length > 1)
        {
            String protocol_id_year = code[0];
            String num = code[1];
            String protocol_id_num = num.substring(0, num.length() - 1);
            map.put("protocol_id_year", protocol_id_year);// 合同编号 年
            map.put("protocol_id_num", protocol_id_num);// 合同编号 号
        }
        return map;
    }

    /**
     * 设置共同的参数
     * 
     * @param 参数int i 是为了获取到对应的list内容 （因为合同不通获取的条数不通 此i是为了更改list.get的index（序号））
     * @return
     */
    private void setmap(Map<String, Object> map, List<WmsSysProperty> map1, int i, String protocol_type)
    {
        int number = (int) map.get("cre_loan_type");
        WmsSysProperty wms4 = (WmsSysProperty) map1.get(4 - i);
        map.put("creditor_name", wms4.getProperty_value());// 债权人姓名
        WmsSysProperty wms5 = (WmsSysProperty) map1.get(5 - i);
        map.put("creditor_identity_id", wms5.getProperty_value());// 债权人身份证
        WmsSysProperty wms6 = (WmsSysProperty) map1.get(6 - i);
        map.put("creditor_address", wms6.getProperty_value());// 债权人通讯地址
        WmsSysProperty wms7 = (WmsSysProperty) map1.get(7 - i);
        map.put("creditor_tel", wms7.getProperty_value());// 债权人联系电话
        WmsSysProperty wms8 = (WmsSysProperty) map1.get(8 - i);
        map.put("borrow_interest", wms8.getProperty_value());// 借款月利率
        WmsSysProperty wms9 = (WmsSysProperty) map1.get(9 - i);
        map.put("owner", wms9.getProperty_value());// 甲方
        if (!protocol_type.equals("5") && !protocol_type.equals("8"))
        {// 如果不是信贷合同
            WmsSysProperty wms10 = (WmsSysProperty) map1.get(10 - i);
            map.put("weiyuejin", wms10.getProperty_value());// 违约金利率
        }
        else if (protocol_type.equals("5") || protocol_type.equals("8"))
        {// 如果是信贷合同 俩段都判断是为了 以后可能还会有新的合同类型
            if (number != 283 && number != 284)
            {
                WmsSysProperty wms10 = (WmsSysProperty) map1.get(10 - i);
                map.put("service_cost_month", wms10.getProperty_value());// 居间服务费标准利率
                WmsSysProperty wms11 = (WmsSysProperty) map1.get(11 - i);
                map.put("weiyuejin", wms11.getProperty_value());// 违约金利率
                WmsSysProperty wms12 = (WmsSysProperty) map1.get(12 - i);
                map.put("consult_service_cost", wms12.getProperty_value());// 咨询服务费标准
                                                                           // */
            }
            else
            {
                WmsSysProperty wms11 = (WmsSysProperty) map1.get(10 - i);
                map.put("weiyuejin", wms11.getProperty_value());// 违约金利率
                WmsSysProperty wms12 = (WmsSysProperty) map1.get(11 - i);
                map.put("consult_service_cost", wms12.getProperty_value());// 咨询服务费标准
                                                                           // */
                WmsSysProperty wms10 = (WmsSysProperty) map1.get(12 - i);
                map.put("service_cost_month", wms10.getProperty_value());// 居间服务费标准利率
            }
        }
        if (protocol_type.equals("6") || protocol_type.equals("7"))
        {
            WmsSysProperty wms11 = (WmsSysProperty) map1.get(11 - i);
            map.put("service_cost_month", wms11.getProperty_value());// 居间服务费标准利率
                                                                     // 卓还贷和卓合贷
        }

    }

    /**
     * @param 共同的添加list
     * @param 参数int i是区分信贷和房贷的违约金利率不通 查询数据库对应的id就不通
     * @return
     */
    private void setList(List<Integer> list, int i)
    {
        list.add(8);// 债权人姓名
        list.add(9);// 债权人身份
        list.add(10);// 债权人通讯地址
        list.add(11);// 债权人联系电话
        list.add(12);// 借款月利率
        list.add(13);// 甲方
        if (i == 0)
        {
            list.add(19);// 违约金利率
        }
        else if (i == 1)
        {
            list.add(20);// 违约金利率
        }
        else if (i == 2)
        {
            list.add(19);// 违约金利率
            list.add(21);// 居间服务费利率卓合贷合同该数值=1.77%
        }
        else if (i == 3)
        {
            list.add(19);// 违约金利率
            list.add(22);// 居间服务费利率卓还贷合同该数值=0.77%
        }
        else if (i == 4)
        {
            list.add(20);// 违约金利率
            list.add(33);// //信贷咨询服务费利率
        }
    }

    /**
     * 设置日期（开始日期 ，结束日期，签订合同日期等）参数int i是为了方法的公共使用 主要的目的就是计算的月份上有差异对月份进行加减
     * 
     * @param wmsFinaCreLoanApp
     * @return
     */
    private Date setDatebyCalendar(int i)
    {
        java.sql.Date date1;
        Calendar calendar = new GregorianCalendar();
        if (calendar.get(Calendar.DAY_OF_MONTH) - 1 == 0)
        {
            calendar.add(Calendar.MONTH, +1 + i);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            java.util.Date date_ = calendar.getTime();
            date1 = new java.sql.Date(date_.getTime());
        }
        else
        {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            calendar.add(Calendar.MONTH, +1 + i);
            java.util.Date date_ = calendar.getTime();
            date1 = new java.sql.Date(date_.getTime());
        }
        return date1;
    }

    /**
     * 设置还款日
     * 
     * @param wmsFinaCreLoanApp
     * @return
     */
    private int setreply()
    {
        int day = 0;
        Calendar calendar = new GregorianCalendar();
        if (calendar.get(Calendar.DAY_OF_MONTH) - 1 == 0)
        {
            day = 31;
        }
        else
        {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            day = calendar.get(Calendar.DAY_OF_MONTH);
        }
        return day;
    }

    // 获取当前日期
    private Date getNow()
    {
        Calendar nowcalendar = Calendar.getInstance();
        return new Date(nowcalendar.getTimeInMillis());
    }

    // 从数据库中获取需要带出的属性 贷款主表 和贷款信息变更表
    private Map<String, Object> getInfoforborrow(Map<String, Object> map, Integer wms_cre_credit_head_id,
                                                 String protocol_type)
    {
        // 获取贷款主表中的信息 和主贷人的信息
        map = wmsCreCreditHeadDao.searchforborrow(wms_cre_credit_head_id);
        if (map != null)
        {
            // 如果期数为空则默认给个期数
            if ("".equals(map.get("max_repayment_time_limit")) || map.get("max_repayment_time_limit") == null)
            {
                map.put("max_repayment_time_limit", WmsHelp.BORROW_DEADLINE);
            }
        }
        // 如果不是信贷合同需要获取房产信息
        if (!protocol_type.equals("5"))
        {
            map = searchformortgage(map, wms_cre_credit_head_id, protocol_type);
        }
        // 获取共同贷款人的信息 共同贷款人可以有多个
        List<Map<String, Object>> list = wmsCreCreditLineCustomerChangeHeadDao.searchforborrow(wms_cre_credit_head_id);
        if (list.size() != 0)
        {
            Map<String, Object> mapChange = list.get(0);
            // 共同债务人姓名
            map.put("com_debtor_name", mapChange.get("customer_name"));
            // 共同债务人身份证
            map.put("com_debtor_identity_id", mapChange.get("id_card"));
            // 地址等于省份加上城市加上区加上剩下的
           /* String address = mapChange.get("current_address_province") + "" + mapChange.get("current_address_city")
                             + mapChange.get("current_address_district") + mapChange.get("current_address_more");*/
            String address ="";
            if(!"".equals(mapChange.get("current_address_province"))&&mapChange.get("current_address_province")!=null){
                address += mapChange.get("current_address_province") + "省";
            }
    		if(!"".equals(mapChange.get("current_address_city"))&&mapChange.get("current_address_city")!=null){
                address += mapChange.get("current_address_city") + "市";
    		}
    		if(!"".equals(mapChange.get("current_address_district"))&&mapChange.get("current_address_district")!=null){
                address += mapChange.get("current_address_district") + "区";
    		}
    		if(!"".equals(mapChange.get("current_address_more"))&&mapChange.get("current_address_more")!=null){
    			address += mapChange.get("current_address_more");
    		}
    		
            // 共同债务人地址
            map.put("com_debtor_address", address);
            // 共同债务人电话
            map.put("com_debtor_tel", mapChange.get("mobile_telephone1"));
            // 共同债务人固定联系电话
            map.put("com_debtor_fixed_line", mapChange.get("fixed_telephone"));
        }
        map.put("refund_day", setreply());
        // 设置合同签订日期等
        map.put("protocol_date", getNow());
        // 开始时间等
        map.put("borrow_begin_date", getNow());
        // 设置 还款结束日期 map.get("max_repayment_time_limit")获取 信贷主表中的申请最长还款期限
        map.put("borrow_end_date", setDatebyCalendar((int) map.get("max_repayment_time_limit") - 1));
        // 设置 计划还款日期（借据）
        map.put("plan_borrow_date", setDatebyCalendar(0));
        // 设置借款期限
        map.put("borrow_deadline", map.get("max_repayment_time_limit"));
        // 设置借款用涂
        map.put("borrow_purpose", map.get("credit_purpose"));
        BigDecimal lower = new BigDecimal(0);
        if (protocol_type.equals("5"))
        {// 如果是信贷合同
         // 合同页面取面签金额
            lower = (BigDecimal) map.get("visa_limit");
        }
        else
        {
            // 合同页面取终审决策金额
            lower = (BigDecimal) map.get("appro_limit");
        }
        // 由于之前存入的单位为万元 需要转换成元
        BigDecimal lower1 = new BigDecimal(0);
        if (protocol_type.equals("5"))
        {// 如果是信贷合同
         // 合同页面取面签金额
            lower1 = (BigDecimal) map.get("visa_limit");
        }
        else
        {
            // 合同页面取终审决策金额
            lower1 = (BigDecimal) map.get("appro_limit");
        }
        BigDecimal principal_lower = new BigDecimal(0);
        if (protocol_type.equals("31") || protocol_type.equals("32") || protocol_type.equals("33"))
        {// 车贷单位是元
        	principal_lower = lower1;
        }else{
        	principal_lower = lower1.multiply(new BigDecimal(10000));
        }
        // 设置借款本金小写
        map.put("principal_lower", principal_lower);
        // 设置债务人姓名
        map.put("debtor_name", map.get("customer_name"));
        // 设置债务人身份证
        map.put("debtor_identity_id", map.get("id_card"));
        // 地址等于省份加上城市加上区加上剩下的
        /*String address = map.get("current_address_province") + "省" + map.get("current_address_city") + "市"
                         + map.get("current_address_district") + "区" + map.get("current_address_more");*/
        String address ="";
        if(!"".equals(map.get("current_address_province"))&&map.get("current_address_province")!=null){
            address += map.get("current_address_province") + "省";
        }
		if(!"".equals(map.get("current_address_city"))&&map.get("current_address_city")!=null){
            address += map.get("current_address_city") + "市";
		}
		if(!"".equals(map.get("current_address_district"))&&map.get("current_address_district")!=null){
            address += map.get("current_address_district") + "区";
		}
		if(!"".equals(map.get("current_address_more"))&&map.get("current_address_more")!=null){
			address += map.get("current_address_more");
		}
        // 设置债务人通讯地址
        map.put("debtor_address", address);
        // 设置债务人联系电话
        map.put("debtor_tel", map.get("mobile_telephone1"));
        // 设置债务人固定电话
        map.put("debtor_fixed_line", map.get("fixed_telephone"));
        return map;
    }

    // 从数据库中获取需要带出的属性 贷款主表 和贷款信息变更表--房贷合同完善
    private Map<String, Object> getInfoforborrowhouse(Map<String, Object> map, Integer wms_cre_credit_head_id,
                                                 String protocol_type)
    {
        // 获取贷款主表中的信息 和主贷人的信息
        map = wmsCreCreditHeadDao.searchforborrow(wms_cre_credit_head_id);
        if (map != null)
        {
            // 如果期数为空则默认给个期数
            if ("".equals(map.get("max_repayment_time_limit")) || map.get("max_repayment_time_limit") == null)
            {
                map.put("max_repayment_time_limit", WmsHelp.BORROW_DEADLINE);
            }
        }
        // 需要获取房产信息
        map = searchformortgage(map, wms_cre_credit_head_id, protocol_type);
        // 获取共同贷款人的信息 共同贷款人可以有多个
        List<Map<String, Object>> list = wmsCreCreditLineCustomerChangeHeadDao.searchforborrow(wms_cre_credit_head_id);
        if (list.size() != 0)
        {
            Map<String, Object> mapChange = list.get(0);
            // 共同债务人姓名
            map.put("com_debtor_name", mapChange.get("customer_name"));
            // 共同债务人身份证
            map.put("com_debtor_identity_id", mapChange.get("id_card"));
            // 地址等于省份加上城市加上区加上剩下的
           /* String address = mapChange.get("current_address_province") + "" + mapChange.get("current_address_city")
                             + mapChange.get("current_address_district") + mapChange.get("current_address_more");*/
            String address ="";
            if(!"".equals(mapChange.get("current_address_province"))&&mapChange.get("current_address_province")!=null){
                address += mapChange.get("current_address_province") + "省";
            }
    		if(!"".equals(mapChange.get("current_address_city"))&&mapChange.get("current_address_city")!=null){
                address += mapChange.get("current_address_city") + "市";
    		}
    		if(!"".equals(mapChange.get("current_address_district"))&&mapChange.get("current_address_district")!=null){
                address += mapChange.get("current_address_district") + "区";
    		}
    		if(!"".equals(mapChange.get("current_address_more"))&&mapChange.get("current_address_more")!=null){
    			address += mapChange.get("current_address_more");
    		}
    		
            // 共同债务人地址
            map.put("com_debtor_address", address);
            // 共同债务人电话
            map.put("com_debtor_tel", mapChange.get("mobile_telephone1"));
            // 共同债务人固定联系电话
            map.put("com_debtor_fixed_line", mapChange.get("fixed_telephone"));
        }
        map.put("refund_day", setreply());
        // 设置合同签订日期等
        map.put("protocol_date", getNow());
        // 开始时间等
        map.put("borrow_begin_date", getNow());
        // 设置 还款结束日期 map.get("max_repayment_time_limit")获取 信贷主表中的申请最长还款期限
        map.put("borrow_end_date", setDatebyCalendar((int) map.get("max_repayment_time_limit") - 1));
        // 设置 计划还款日期（借据）
        map.put("plan_borrow_date", setDatebyCalendar(0));
        // 设置借款期限
        map.put("borrow_deadline", map.get("max_repayment_time_limit"));
        // 设置借款用涂
        map.put("borrow_purpose", map.get("credit_purpose"));
        // 合同页面取终审决策金额
        BigDecimal principal_lower = new BigDecimal(0);
        principal_lower = ((BigDecimal) map.get("appro_limit")).multiply(new BigDecimal(10000));
   
        // 设置借款本金小写
        map.put("principal_lower", principal_lower);
        // 设置债务人姓名
        map.put("debtor_name", map.get("customer_name"));
        // 设置债务人身份证
        map.put("debtor_identity_id", map.get("id_card"));
        // 地址等于省份加上城市加上区加上剩下的
        String address ="";
        if(!"".equals(map.get("current_address_province"))&&map.get("current_address_province")!=null){
            address += map.get("current_address_province") + "省";
        }
		if(!"".equals(map.get("current_address_city"))&&map.get("current_address_city")!=null){
            address += map.get("current_address_city") + "市";
		}
		if(!"".equals(map.get("current_address_district"))&&map.get("current_address_district")!=null){
            address += map.get("current_address_district") + "区";
		}
		if(!"".equals(map.get("current_address_more"))&&map.get("current_address_more")!=null){
			address += map.get("current_address_more");
		}
        // 设置债务人通讯地址
        map.put("debtor_address", address);
        // 设置债务人联系电话
        map.put("debtor_tel", map.get("mobile_telephone1"));
        // 设置债务人固定电话
        map.put("debtor_fixed_line", map.get("fixed_telephone"));
        // v2.1.6房产核查缴费
        String payment_amount = getPaymentAmount(wms_cre_credit_head_id);
        map.put("payment_amount", payment_amount);
        return map;
    }

    // 设置保人信息 房贷有保人 信贷没有保人
    private Map<String, Object> setContact(Map<String, Object> map, Integer wms_cre_credit_head_id)
    {
        List<Map<String, Object>> list = wmsCreCustomerChangeLineContactDao.searchforborrow(wms_cre_credit_head_id);
        if (list.size() != 0)
        {
            Map<String, Object> mapChange = list.get(0);
            // 从联系人信息表中获取保人姓名
            map.put("person_name", mapChange.get("contact_name"));
        }
        return map;
    }

    private Map<String, Object> searchformortgage(Map<String, Object> map, Integer wms_cre_credit_head_id,
                                                  String protocol_type)
    {
        Map<String, Object> momap = wmsCreCustomerChangeLineHouseinfoDao.searchformortgage(wms_cre_credit_head_id);
        if (momap != null)
        {
            map.put("attach_number", 1);// 购买时间
            map.put("mortgage_name", "房产");// 购买时间
            map.put("buy_date", momap.get("house_buy_date"));// 购买时间
            if(!"".equals(momap.get("house_building_area"))&& momap.get("house_building_area")!=null){
                map.put("house_area", "" + momap.get("house_building_area"));// 面积+
                                                                             // "㎡"
            }
            if(momap.get("transaction_price")!=null){
            	 map.put("estimate_value",
                         ""
                                 + ((BigDecimal) momap.get("transaction_price")).divide(new BigDecimal(10000), 2,
 BigDecimal.ROUND_HALF_UP) + "万元");// 评估价值
            }else{
            	map.put("estimate_value","0");
            }
           
            BigDecimal lower = new BigDecimal(0);
            if (protocol_type.equals("5"))
            {// 如果是信贷合同
             // 抵押价值取面签金额
                lower = ((BigDecimal) map.get("visa_limit")).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
            }
            else
            {
                // 抵押价值取终审决策金额
                lower = ((BigDecimal) map.get("appro_limit")).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
            }
            map.put("mortgage_value", lower.doubleValue() + "万元");// 抵押价值
            map.put("mortgage_value_str", lower.doubleValue() * 10000);// 抵押价值
            map.put("house_type", momap.get("house_type"));// 房产类型
            map.put("house_certificate_number", momap.get("house_no"));// 房产权证号码
            map.put("house_roll_number", momap.get("house_vol_no"));// 房产卷号
            /* String explicit_address = "" + momap.get("house_address_province") + "省" + momap.get("house_address_city")
                                       + "市" + momap.get("house_address_district") + "区"
                                       + momap.get("house_address_more");*/
            String explicit_address ="";
            if(!"".equals(momap.get("house_address_province"))&&momap.get("house_address_province")!=null){
                explicit_address += momap.get("house_address_province") + "省";
            }
    		if(!"".equals(momap.get("house_address_city"))&&momap.get("house_address_city")!=null){
                explicit_address += momap.get("house_address_city") + "市";
    		}
    		if(!"".equals(momap.get("house_address_district"))&&momap.get("house_address_district")!=null){
                explicit_address += momap.get("house_address_district") + "区";
    		}
    		if(!"".equals(momap.get("house_address_more"))&&momap.get("house_address_more")!=null){
    			explicit_address += momap.get("house_address_more");
    		}
            map.put("explicit_address", explicit_address);// 详细地址
        }
        return map;
    }

    private void setCreLoanTypeVal(Map<String, Object> map, List<Integer> list)
    {
        int number = (int) map.get("cre_loan_type");
        if (number == 110)
        {
            list.add(14); // 居间服务费月利率-卓英贷
        }
        else if (number == 111)
        {
            list.add(15); // 居间服务费月利率-卓楼贷
        }
        else if (number == 112)
        {
            list.add(16); // 居间服务费月利率-卓薪贷
        }
        else if (number == 113)
        {
            list.add(17); // 居间服务费月利率-卓业贷
        }
        else if (number == 256)
        {
            list.add(18); // 居间服务费月利率-卓金贷
        }
        else if (number == 283)
        {
            list.add(34); // 居间服务费月利率-卓营贷
        }
        else if (number == 284)
        {
            list.add(35); // 居间服务费月利率-特色贷
        }
    }

    /**
     * 传入cre_type,cre_loan_type,region_number 的值要获取的属性表条件 贷款类型 贷款具体类型 地区编码
     * 
     * @param map
     * @return Map<String,String>
     * @author baisong
     */
    @Override
    public Map<String, Object> getforNewProtocol(UserBean user, String protocol_type, Integer wms_cre_credit_head_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap = getInfoforborrow(paramMap, wms_cre_credit_head_id, protocol_type);// 获取贷款表中的一些信息并做相应的处理
        if (paramMap.get("cre_type").equals("2"))
        {// 如果是房贷贷款的小类型是protocol_type 信贷则是贷款主表的中的字段
            paramMap.put("cre_loan_type", protocol_type);
            setContact(paramMap, wms_cre_credit_head_id);// 如果有保人 设置保人姓名
        }
        if (paramMap.get("cre_type").equals("3"))
        {// 如果是车贷贷款的小类型是protocol_type 车贷则是贷款主表的中的字段
            paramMap.put("cre_loan_type", protocol_type);
            setContact(paramMap, wms_cre_credit_head_id);// 如果有保人 设置保人姓名
        }
        paramMap.put("region_number", user.getUser_regionNumber());// 设置区域编码
        /*
         * Map<String, Object> map = new HashMap<String, Object>();
         * map.put("cre_type",paramMap.get("cre_type"));
         * map.put("cre_loan_type",paramMap.get("cre_loan_type"));
         * map.put("region_number",paramMap.get("region_number"));
         */
        WmsSysProperty wmsSysProperty = new WmsSysProperty();
        List<WmsSysProperty> list = wmssyspropertyDao.getforNewProtocol(paramMap);
        for (int i = 0; i < list.size(); i++)
        {
            wmsSysProperty = list.get(i);
            paramMap.put(wmsSysProperty.getProperty_column(), wmsSysProperty.getProperty_value());
        }
        // 具体区分单据是否循环贷 如果是循环贷 按照新的执行利率进行赋值 否则照常
        WmsCreCreditHead wCreditHead = wmscrecreditheadDao_m.get(wms_cre_credit_head_id);
        if (785 == wCreditHead.getCre_loan_type())
        {// 785 新房贷1号 先息后本 还款期数为3/6期 因为期数不同平台费率不同所以判断获取
         // 咨询服务费新房1号-3个月, '3.5','consult_service_cost'
         // 咨询服务费新房1号-6个月, '5','consult_service_cost1'
        	if(6==(wCreditHead.getMax_repayment_time_limit())){
        		paramMap.put("consult_service_cost", paramMap.get("consult_service_cost1"));
        	}
        }
        if (785 == wCreditHead.getCre_loan_type())
        {// 785 新房贷1号 786 新房贷2号 新房贷获取利息方式不同
        	if(wCreditHead.getLoan_interest_rate()==null){
                paramMap.put("service_cost_month", 0);// 2016-6-28 baisong
                                                      // 因手机版发布
                                                      // 客户信息完善时如果不填写完善中的利率则把服务利率变成0
        	}else{
        		paramMap.put("service_cost_month",wCreditHead.getLoan_interest_rate().subtract(new BigDecimal(paramMap.get("borrow_interest").toString())));
        	}
        }else if(786==wCreditHead.getCre_loan_type()){
        	paramMap.put("service_cost_month",0);
            if (wCreditHead.getLoan_interest_rate() == null)
            {// 2016-6-28 baisong 因手机版发布 客户信息完善时如果不填写完善中的利率合同利率变成1
        		paramMap.put("borrow_interest",1);
        	}else{
        		paramMap.put("borrow_interest",wCreditHead.getLoan_interest_rate());
        	}
        }
        if ((wCreditHead.getIs_cycles() != null && wCreditHead.getIs_cycles().equals("1")))
        {// 说明是循环贷客户
            paramMap.put("service_cost_month",
                         wCreditHead.getLending_rates_execution_cycles().subtract(new BigDecimal(1)));
        }
        // 合同编号自动生成
        String yearnum = CodeNoUtil.getProCreCode();// 合同编号
        paramMap.put("protocol_id_year_num", yearnum);
        String code[] = yearnum.split("年第");
        if (code.length > 1)
        {
            String protocol_id_year = code[0];
            String num = code[1];
            String protocol_id_num = num.substring(0, num.length() - 1);
            paramMap.put("protocol_id_year", protocol_id_year);// 合同编号 年
            paramMap.put("protocol_id_num", protocol_id_num);// 合同编号 号
        }
        paramMap.put("first_refund_date", setCurrent_repay_date(changeDate(new Date(System.currentTimeMillis())), 0));// 第一期还款时间
        paramMap.put("second_refund_date", setCurrent_repay_date(changeDate(new Date(System.currentTimeMillis())), 1));// 第二期还款时间
        if("3".equals(wCreditHead.getCre_type())){
        	paramMap.put("borrow_purpose", wCreditHead.getCredit_purpose());
        }
        return paramMap;
    }
    
    /**
     * 设置本期应还款日
     * 
     * @param wmsFinaCreLoanApp
     * @return
     */
    private Date setCurrent_repay_date(Date date, int i)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        java.sql.Date date1 = new Date(date.getTime());
        if (calendar.get(Calendar.DAY_OF_MONTH) - 1 == 0)
        {
            calendar.add(Calendar.MONTH, +1 + i);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            java.util.Date date_ = calendar.getTime();
            date1 = new java.sql.Date(date_.getTime());
        }
        else
        {
            calendar.add(Calendar.DAY_OF_MONTH, 0);
            calendar.add(Calendar.MONTH, +1 + i);
            java.util.Date date_ = calendar.getTime();
            date1 = new java.sql.Date(date_.getTime());
        }
        return date1;
    }
    
    /**
     * 改变日期
     * 
     * @param wmsFinaCreLoanApp
     * @return
     */
    private Date changeDate(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        java.sql.Date date1 = new Date(date.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        java.util.Date date_ = calendar.getTime();
        date1 = new java.sql.Date(date_.getTime());
        return date1;
    }
    
    /**
     * 签合同-出借人信息
     */
    @Override
    public List<Map<String, Object>> searchLenderInfo(WmsSysPropertySearchBeanVO queryInfo, UserBean user) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("lender_name", queryInfo.getLender_name());
        List<Map<String, Object>> lenderList = new ArrayList<Map<String, Object>>();
        lenderList = this.wmsCreContractLenderDao.search(paramMap);
        return lenderList;
    }
    
    /**
     * 
     * @Title: getPerfectContract
     * @Description: TODO(合同信息完善初始化)
     * @param user
     * @param queryInfo
     * @return Map<String, Object>
     * @author: jiaodelong
     * @time:2017年4月28日 上午9:49:41
     * @see com.zx.emanage.loanappro.service.IWmsSysPropertyService#getPerfectContract(com.zx.sframe.util.vo.UserBean, com.zx.emanage.loanappro.vo.WmsSysPropertyPropertySearchBeanVO)
     * history:
     * 1、2017年4月28日 jiaodelong 创建方法
     */
    @Override
    public Map<String, Object> getPerfectContract(UserBean user,  WmsSysPropertyPropertySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //如果不是新增单据
        if(!queryInfo.getBill_type().equals("01") && queryInfo.getWms_cre_credit_head_id() ==null){
//          根据还款表id去关联表查询headid
            WmsCreCreditServiceType type = new WmsCreCreditServiceType();
            type.setPre_wms_cre_credit_notary_warn_id(queryInfo.getWms_cre_credit_notary_warn_id());
            List<WmsCreCreditServiceType> ListType = wmsCreCreditServiceTypeDao.getListByEntity(type);
//            如果没有headid则去还款表取数据，有headid数据正常取
            if(ListType.size() != 0){
                if(ListType.get(0).getWms_cre_credit_head_id() != null){
                    Integer wms_cre_credit_head_id=ListType.get(0).getWms_cre_credit_head_id();
                    paramMap = wmsCreApproBorrowProtocolDao.getPerfectContractInfo(wms_cre_credit_head_id);
                    
                    WmsCreCreditServiceType wmsCreCreditServiceType = new WmsCreCreditServiceType();
                    wmsCreCreditServiceType.setWms_cre_credit_head_id(queryInfo.getWms_cre_credit_head_id());
                    // 获取贷款表和还款提醒表的中间表
                    List<WmsCreCreditServiceType> list = wmsCreCreditServiceTypeDao.getListByEntity(wmsCreCreditServiceType);
                    paramMap.put("status", 1);
                    paramMap.put("the_number", list.get(0).getThe_number());
                    String appro_limit_val = (String) paramMap.get("appro_limit");
                    if(!appro_limit_val.equals("") && appro_limit_val != null){
                        paramMap.put("appro_limit",new BigDecimal(appro_limit_val).divide(new BigDecimal(10000)));
                    }
                    // 查出终审金额，前台修改金额不能大于终审金额，用于比较
                    Map<String, Object> mapZS = new HashMap<String, Object>();
                    mapZS = getInfoforborrowhouse(paramMap, wms_cre_credit_head_id, "2");
                    paramMap.put("appro_limit_quanju", mapZS.get("appro_limit"));
                    // 字段转换
                    if("1".equals(paramMap.get("payment_contract_type"))){
                        paramMap.put("service_cost_month", paramMap.get("service_refund_principal_month_lower"));
                    }
                    WmsCreCreditHead head = wmsCreCreditHeadDao.get(wms_cre_credit_head_id);
                    paramMap.put("salesman_shortcode", head.getSalesman_shortcode());
                }
            }else{
                paramMap = wmscrecreditnotarywarnDao.getAll(queryInfo.getWms_cre_credit_notary_warn_id());
                // 判断是否是组合贷
                if (paramMap.get("bill_code_group") != null && !"".equals(paramMap.get("bill_code_group")))
                {
                    paramMap.put("isCombin", "true");
                }
                paramMap.put("status", 2);
                // 处理次数问题
//                if (paramMap != null && paramMap.get("the_number") != null && !paramMap.get("the_number").equals(""))
//                {
//                    paramMap.put("the_number", (Integer.valueOf(paramMap.get("the_number").toString())) + 1);
//                }
//                else
//                {
//                    paramMap.put("the_number", 1);
//                }
//                if(paramMap.get("category_name") == null){
//                    paramMap.put("category_name", 785);
//                }

                if (paramMap.get("category_name") == null)
                {
                    paramMap.put("category_name", 785);
                }
                else
                {
                    String b = paramMap.get("category_name").toString();
                    double i = Double.valueOf(b);
                    paramMap.put("category_name", (int) i);
                    if ((int) i == -1)
                    {
                        paramMap.put("category_name", 785);
                    }
                }
                // 产品配置表
                Map<String,Object> mapProtocolProperty=new HashMap<>();
                mapProtocolProperty.put("cre_loan_type", paramMap.get("category_name"));// 获取贷款产品
                // 判断是否存在城市编码
                if (paramMap.get("salesman_city_code") != null && StringUtil.isNotEmpty(paramMap.get("salesman_city_code").toString()))
                {
                    mapProtocolProperty.put("regionNumber", paramMap.get("salesman_city_code"));// 获取贷款产品
                }
                else
                {
                    mapProtocolProperty.put("regionNumber", user.getUser_regionNumber());// 获取贷款产品
                }
                // 产品配置表获取产品对应信息
                List<Map<String, Object>> Property = wmssyspropertyDao.getprotocolPropertyApply(mapProtocolProperty);
                Map<String,Object> retMap=Property.get(0);
                
                mapProtocolProperty.put("borrow_deadline", paramMap.get("borrow_deadline"));// 期限
                mapProtocolProperty.put("credit_limit", Integer.parseInt((String) paramMap.get("appro_limit"))/10000);// 获取申请贷款额度
                mapProtocolProperty.put("payment_contract_type", retMap.get("payment_contract_type"));// 获取属性表还款类型
                mapProtocolProperty.put("cre_loan_type", paramMap.get("category_name"));// 获取贷款产品
                // 产品配置表获取产品对应信息
                List<Map<String, Object>> retmapProtocolProperty = wmssyspropertyDao.getprotocolPropertyApply(mapProtocolProperty);
                if(retmapProtocolProperty!=null&&retmapProtocolProperty.size()>0){
                    Map<String,Object> retMap2=retmapProtocolProperty.get(0);
                   //配置表获取
                    paramMap.put("borrow_interest", retMap2.get("borrow_interest"));// 贷款利率
                    paramMap.put("service_cost_month", retMap2.get("platform_fee"));// 平台费
                    paramMap.put("consult_service_cost", retMap2.get("protocol_fees"));// 手续费率
                    paramMap.put("interest_type", retMap2.get("interest_type"));// 利息类型

                }
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("cre_type", 2);// 贷款类型
                map.put("cre_loan_type", paramMap.get("category_name"));// 产品
                map.put("region_number", user.getUser_regionNumber());// 区域编码
                // 查询属性表一些固定值
                WmsSysProperty wmsSysProperty = new WmsSysProperty();
                List<WmsSysProperty> list = wmssyspropertyDao.getforNewProtocol(map);
                for (int i = 0; i < list.size(); i++)
                {
                    wmsSysProperty = list.get(i);
                    paramMap.put(wmsSysProperty.getProperty_column(), wmsSysProperty.getProperty_value());
                }
                paramMap.put("borrow_begin_date",new Date(System.currentTimeMillis()));//签订时间(默认当前时间) 
            }
        }else{
            Integer wms_cre_credit_head_id=queryInfo.getWms_cre_credit_head_id();
            //还款方式
            if(queryInfo.getProtocol_type() == null){
                queryInfo.setProtocol_type("2");
            }
            String protocol_type=queryInfo.getProtocol_type();
            
            // 具体区分单据是否循环贷 如果是循环贷 按照新的执行利率进行赋值 否则照常
            WmsCreCreditHead wCreditHead = wmscrecreditheadDao_m.get(wms_cre_credit_head_id);

            //根据headId查询组合贷所有的headid
            List<Integer> headIdList = wmscrecreditheadDao_m.getHeadIdListForHeadId(wms_cre_credit_head_id);
            //根据headIdList去合同表获取保存过合同的信息
            Map<String, Object> headIdListMap = new HashMap<String, Object>();
            headIdListMap.put("headIdList", headIdList);

            // 判断是初次加载，还是已经保存过
            Integer count = wmsCreApproBorrowProtocolDao.getPerfectContractCount(wms_cre_credit_head_id);
            if(count > 0){
                paramMap = wmsCreApproBorrowProtocolDao.getPerfectContractInfo(wms_cre_credit_head_id);
                // 判断是否是组合贷
                if (wCreditHead != null && wCreditHead.getWms_cre_credit_group_id() != null && !"".equals(wCreditHead.getWms_cre_credit_group_id()))
                {
                    paramMap.put("isCombin", "true");
                }
                  //如果是组合贷给组合贷标志（1是 0不是）
                if(wCreditHead.getWms_cre_credit_group_id() != null){
                    paramMap.put("is_group", 1);
                }else{
                    paramMap.put("is_group", 0);
                }
                paramMap.put("status", 1);
                paramMap.put("salesman_shortcode", wCreditHead.getSalesman_shortcode());
                String appro_limit_val = (String) paramMap.get("appro_limit");
                if(!appro_limit_val.equals("") && appro_limit_val != null){
                    paramMap.put("appro_limit",new BigDecimal(appro_limit_val).divide(new BigDecimal(10000)));
                }
                // 查出终审金额，前台修改金额不能大于终审金额，用于比较
                Map<String, Object> mapZS = new HashMap<String, Object>();
                mapZS = getInfoforborrowhouse(paramMap, wms_cre_credit_head_id, protocol_type);
                paramMap.put("appro_limit_quanju", mapZS.get("appro_limit"));
                // 字段转换
                if("1".equals(paramMap.get("payment_contract_type"))){
                    paramMap.put("service_cost_month", paramMap.get("service_refund_principal_month_lower"));
                }
                WmsCreCreditServiceType wmsCreCreditServiceType = new WmsCreCreditServiceType();
                wmsCreCreditServiceType.setWms_cre_credit_head_id(wms_cre_credit_head_id);
                // 获取贷款表和还款提醒表的中间表
                List<WmsCreCreditServiceType> list = wmsCreCreditServiceTypeDao.getListByEntity(wmsCreCreditServiceType);
                //如果是展期数据，需要显示产期次数
                if(!queryInfo.getBill_type().equals("01")){
                    if (list.size() > 0)
                    {
                        paramMap.put("the_number", list.get(0).getThe_number());
                    }
                    else
                    {
                        paramMap.put("the_number", "");
                    }
                }
//                WmsCreCreditServiceType ServiceType=new WmsCreCreditServiceType();
//                ServiceType.setWms_cre_credit_head_id(Integer.parseInt(paramMap.get("wms_cre_credit_head_id").toString()));
//                //还款提醒表和贷款主表中间表
//                List<WmsCreCreditServiceType> typeService= wmsCreCreditServiceTypeDao.getListByEntity(ServiceType);
//                //展期续期需要增加次数
//                if (paramMap != null && paramMap.get("the_number") != null && !queryInfo.getBill_type().equals("01"))
//                {
//                    paramMap.put("the_number", (Integer.valueOf(paramMap.get("the_number").toString())) + 1);
//                }else if(queryInfo.getBill_type().equals("01")){
//                    
//                }
//                else if(typeService.size() == 0){
//                    
//                }
//                else
//                {
//                    paramMap.put("the_number", 1);
//                }
                // 获取贷款区域编码
                if (StringUtil.isNotBlank(queryInfo.getRegionNumber()))
                {
                }
                // 单据主键不为空
                else if (queryInfo.getWms_cre_credit_head_id() != null)
                {
                    WmsCreCreditHead wHead = wmscrecreditheadDao_m.get(queryInfo.getWms_cre_credit_head_id());
                    if (wHead != null)
                    {
                        queryInfo.setRegionNumber(wHead.getCreate_user_city_code());
                    }
                }
                // 其他都没有查询登陆人
                else if (user != null && user.getUser_regionNumber() != null)
                {
                    queryInfo.setRegionNumber(user.getUser_regionNumber());
                }
                //获取利率类型
                // 产品配置表
                Map<String,Object> mapProtocolProperty=new HashMap<>();
                mapProtocolProperty.put("borrow_deadline", paramMap.get("borrow_deadline"));// 获取贷款主表期限
                mapProtocolProperty.put("credit_limit", paramMap.get("appro_limit"));// 获取申请贷款额度
                mapProtocolProperty.put("payment_contract_type", paramMap.get("payment_contract_type"));// 获取属性表还款类型
                mapProtocolProperty.put("cre_loan_type", queryInfo.getProtocol_type());// 获取贷款产品
                mapProtocolProperty.put("regionNumber", queryInfo.getRegionNumber());// 区域编码
                // 产品配置表获取产品对应信息
                List<Map<String, Object>> retmapProtocolProperty = wmssyspropertyDao.getprotocolPropertyApply(mapProtocolProperty);
                if(retmapProtocolProperty!=null&&retmapProtocolProperty.size()>0){
                    Map<String,Object> retMap=retmapProtocolProperty.get(0);
                    paramMap.put("interest_type", retMap.get("interest_type"));// 利息类型
                }
            //如果没保存过就初始化
            }else{
               
                paramMap = getInfoforborrowhouse(paramMap, wms_cre_credit_head_id, protocol_type);// 获取贷款表中的一些信息并做相应的处理
                //如果是组合贷给组合贷标志（1是 0不是）
                if(wCreditHead.getWms_cre_credit_group_id() != null){
                    paramMap.put("is_group", 1);
                }else{
                    paramMap.put("is_group", 0);
                }
                // 如果有保人 设置保人姓名
                paramMap.put("cre_loan_type", protocol_type);
                setContact(paramMap, wms_cre_credit_head_id);

                // paramMap.put("region_number", user.getUser_regionNumber());//
                // 设置区域编码

                // 获取贷款区域编码
                if (StringUtil.isNotBlank(queryInfo.getRegionNumber()))
                {
                    paramMap.put("regionNumber", queryInfo.getRegionNumber());
                }
                // 单据主键不为空
                else if (queryInfo.getWms_cre_credit_head_id() != null)
                {
                    WmsCreCreditHead wHead = wmscrecreditheadDao_m.get(queryInfo.getWms_cre_credit_head_id());
                    if (wHead != null)
                    {
                        paramMap.put("regionNumber", wHead.getCreate_user_city_code());
                        queryInfo.setRegionNumber(wHead.getCreate_user_city_code());
                        // 判断是否是组合贷
                        if (wHead != null && wHead.getWms_cre_credit_group_id() != null && !"".equals(wHead.getWms_cre_credit_group_id()))
                        {
                            paramMap.put("isCombin", "true");
                        }
                    }
                }
                // 其他都没有查询登陆人
                else if (user != null && user.getUser_regionNumber() != null)
                {
                    paramMap.put("regionNumber", user.getUser_regionNumber());
                    queryInfo.setRegionNumber(user.getUser_regionNumber());
                }
                
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("cre_type", paramMap.get("cre_type"));// 贷款类型
                map.put("cre_loan_type", paramMap.get("cre_loan_type"));// 产品
                map.put("region_number", paramMap.get("regionNumber"));// 区域编码
                // 查询属性表一些固定值
                WmsSysProperty wmsSysProperty = new WmsSysProperty();
                List<WmsSysProperty> list = wmssyspropertyDao.getforNewProtocol(map);
                for (int i = 0; i < list.size(); i++)
                {
                    wmsSysProperty = list.get(i);
                    paramMap.put(wmsSysProperty.getProperty_column(), wmsSysProperty.getProperty_value());
                }
                // 产品配置表
                Map<String,Object> mapProtocolProperty=new HashMap<>();
                mapProtocolProperty.put("borrow_deadline", paramMap.get("max_repayment_time_limit"));// 获取贷款主表期限
                mapProtocolProperty.put("credit_limit", paramMap.get("appro_limit"));// 获取申请贷款额度
                mapProtocolProperty.put("payment_contract_type", paramMap.get("payment_contract_type"));// 获取属性表还款类型
                mapProtocolProperty.put("cre_loan_type", queryInfo.getProtocol_type());// 获取贷款产品
                mapProtocolProperty.put("regionNumber", queryInfo.getRegionNumber());// 区域编码
                // 产品配置表获取产品对应信息
                List<Map<String, Object>> retmapProtocolProperty = wmssyspropertyDao.getprotocolPropertyApply(mapProtocolProperty);
               
               
                if(retmapProtocolProperty!=null&&retmapProtocolProperty.size()>0){
                    Map<String,Object> retMap=retmapProtocolProperty.get(0);
                    // 第一次初始化页面，手续费率不在配置表获取，在贷款主表获取,如果主表没有则去配置表获取
                    if (wCreditHead.getLoan_interest_rate() != null && !wCreditHead.getLoan_interest_rate().equals(""))
                    {
                        paramMap.put("borrow_interest", wCreditHead.getLoan_interest_rate());// 贷款利率
                    }
                    else
                    {
                        paramMap.put("borrow_interest", retMap.get("borrow_interest"));// 贷款利率
                    }
                    paramMap.put("service_cost_month", retMap.get("platform_fee"));// 平台费
                    paramMap.put("consult_service_cost", retMap.get("protocol_fees"));// 手续费率
                    paramMap.put("interest_type", retMap.get("interest_type"));// 利息类型

                }
                // if(785==wCreditHead.getCre_loan_type()){//785 新房贷1号 先息后本
                // 还款期数为3/6期 因为期数不同平台费率不同所以判断获取
                // //咨询服务费新房1号-3个月, '3.5','consult_service_cost'
                // //咨询服务费新房1号-6个月, '5','consult_service_cost1'
//                      if(6==(wCreditHead.getMax_repayment_time_limit())){
//                          paramMap.put("consult_service_cost", paramMap.get("consult_service_cost1"));
//                      }
//                    }
                if ((wCreditHead.getIs_cycles() != null && wCreditHead.getIs_cycles().equals("1")))
                {// 说明是循环贷客户
                    paramMap.put("service_cost_month",
                                 wCreditHead.getLending_rates_execution_cycles().subtract(new BigDecimal(1)));
                }
                // 合同编号自动生成
                // String yearnum = CodeNoUtil.getProCreCode();// 合同编号
//                    paramMap.put("protocol_id_year_num", yearnum);
                // String code[] = yearnum.split("年第");
//                    if (code.length > 1)
//                    {
//                        String protocol_id_year = code[0];
//                        String num = code[1];
//                        String protocol_id_num = num.substring(0, num.length() - 1);
                // paramMap.put("protocol_id_year", protocol_id_year);//合同编号 年
                // paramMap.put("protocol_id_num", protocol_id_num);//合同编号 号
//                    }
                paramMap.put("first_refund_date", setCurrent_repay_date(changeDate(new Date(System.currentTimeMillis())), 0));// 第一期还款时间
                paramMap.put("second_refund_date", setCurrent_repay_date(changeDate(new Date(System.currentTimeMillis())), 1));// 第二期还款时间
                paramMap.put("status", 0);
                // 查出终审金额，前台修改金额不能大于终审金额，用于比较
                Map<String, Object> mapZS = new HashMap<String, Object>();
                mapZS = getInfoforborrowhouse(paramMap, wms_cre_credit_head_id, protocol_type);
                paramMap.put("appro_limit_quanju", mapZS.get("appro_limit"));
                
                //2017年1月4日添加
                //焦德龙
                //初始化的时候判断是不是组合贷，如果是增加初始化项
                //如果是组合贷
                if(wCreditHead.getWms_cre_credit_group_id() != null){
                    Map<String, Object> headList = new HashMap<String, Object>();
                    //根据headId查询组合贷所有的headid
                    List<Integer> head = wmscrecreditheadDao_m.getHeadIdListForHeadIdInAllStatus(wms_cre_credit_head_id);
                    headList.put("headIdList", head);
                    paramMap.put("isCombin", "true");
                   List<Map<String,Object>> Groupprotocol = wmsCreApproBorrowProtocolDao.getGroupSaveInfo(headList);
                   //如果不为空往页面map赋值
                   if(Groupprotocol!=null&&Groupprotocol.size()>0){
                        Map<String,Object> retMap=Groupprotocol.get(0);
                        
                        paramMap.put("creditor_name", retMap.get("creditor_name"));
                        
                        paramMap.put("debtor_name", retMap.get("debtor_name"));
                        paramMap.put("debtor_identity_id", retMap.get("debtor_identity_id"));
                        paramMap.put("debtor_tel", retMap.get("debtor_tel"));
                        paramMap.put("debtor_address", retMap.get("debtor_address"));
                        
                        paramMap.put("com_debtor_name", retMap.get("com_debtor_name"));
                        paramMap.put("com_debtor_identity_id", retMap.get("com_debtor_identity_id"));
                        paramMap.put("com_debtor_tel", retMap.get("com_debtor_tel"));
                        paramMap.put("com_debtor_address", retMap.get("com_debtor_address"));
                        
                        paramMap.put("borrow_purpose", retMap.get("borrow_purpose"));
                        paramMap.put("borrow_begin_date", retMap.get("borrow_begin_date"));
                        paramMap.put("first_refund_date", retMap.get("first_refund_date"));
                        paramMap.put("second_refund_date", retMap.get("second_refund_date"));
                        paramMap.put("debtor_loan_name", retMap.get("debtor_loan_name"));
                        paramMap.put("debtor_loan_number", retMap.get("debtor_loan_number"));
                        paramMap.put("debtor_loan_bank", retMap.get("debtor_loan_bank"));
                        //抵押合同
                        paramMap.put("protocol_date", retMap.get("protocol_date"));
                        paramMap.put("explicit_address", retMap.get("explicit_address"));
//                            paramMap.put("debtor_name_fcdyqlr", retMap.get("debtor_name_fcdyqlr"));
                        paramMap.put("house_area", retMap.get("house_area"));
                        paramMap.put("house_certificate_number", retMap.get("house_certificate_number"));
                        paramMap.put("house_roll_number", retMap.get("house_roll_number"));
                        //还款事项提醒函
                        paramMap.put("refund_name", retMap.get("refund_name"));
                        paramMap.put("refund_number", retMap.get("refund_number"));
                        paramMap.put("refund_bank", retMap.get("refund_bank"));
                        paramMap.put("refund_day", retMap.get("refund_day"));
                        //借据
                        paramMap.put("plan_borrow_date", retMap.get("plan_borrow_date"));
                        //咨询管理居间服务
                        paramMap.put("sign_place", retMap.get("sign_place"));
                        //借款合同补充协议
                        paramMap.put("person_name", retMap.get("person_name"));
                        paramMap.put("person_identity_id", retMap.get("person_identity_id"));
                        paramMap.put("a_c_relation", retMap.get("a_c_relation"));
                        paramMap.put("second_house_address", retMap.get("second_house_address"));
                        paramMap.put("c_house_address", retMap.get("c_house_address"));
                        //签过合同1
                        paramMap.put("is_protocol", 1);
                    }else{
                        //没签过合同
                        paramMap.put("is_protocol", 0);
                    }
                }
              
            }
            //签合同的组合贷金额合计
            int appro_limit_counum = 0;
            //签合同的组合贷最高利率
//            int borrow_interest_top = 0;
            BigDecimal borrow_interest_top = new BigDecimal("0");
            
            
            List<Map<String,Object>> GroupList = new  ArrayList<Map<String,Object>>();
            if(headIdList.size() > 0){
              //根据headidList查询所有的组合贷上单信息(E,M)
                GroupList = wmsCreApproBorrowProtocolDao.getheadListByProtocolMap(headIdListMap);
            }
            if(GroupList.size() > 0){
                //循环之后金额相加,求出最高利率
                for(int i=0;i<GroupList.size();i++){
                    appro_limit_counum += ((BigDecimal) GroupList.get(i).get("appro_limit")).intValue();
                    //如果查出的利率大于最高利率，就代替
                    if((new BigDecimal(GroupList.get(i).get("borrow_interest").toString())).compareTo(borrow_interest_top) > 0){
                        borrow_interest_top = (new BigDecimal(GroupList.get(i).get("borrow_interest").toString()));
                    }
                    //如果本条单据打印过合同
                    if(Integer.parseInt(GroupList.get(i).get("wms_cre_credit_head_id").toString()) == wms_cre_credit_head_id){
                        paramMap.put("this_protocol", 1);
                    }
                }
                paramMap.put("noone", 0);
                // 组合贷信息
                paramMap.put("groupList", GroupList);
             
            }else{
                //组合贷 任何一条单据都没打印过合同
                paramMap.put("noone", 1);
            }
            paramMap.put("appro_limit_counum", appro_limit_counum);
            paramMap.put("borrow_interest_top", borrow_interest_top);
            //如果签合同的数量<组合贷的数量，前台相加金额
            if(GroupList.size() < headIdList.size()){
                paramMap.put("is_last", 1);
            }else{//已经打印所有合同
                paramMap.put("is_last", 0); 
            }
        }
       
        return paramMap;
    }

	@Override
	public Map<String, Object> getCreLoanTypeChange(UserBean user,WmsSysPropertyPropertySearchBeanVO queryInfo) {
		 	Map<String, Object> paramMap = new HashMap<String, Object>();
		 // 产品配置表
            Map<String,Object> mapProtocolProperty=new HashMap<>();
            mapProtocolProperty.put("cre_loan_type", queryInfo.getProtocol_type());// 获取贷款产品
        // 获取贷款区域编码
        if (StringUtil.isNotBlank(queryInfo.getRegionNumber()))
        {
            mapProtocolProperty.put("regionNumber", queryInfo.getRegionNumber());
        }
        // 单据主键不为空
        else if (queryInfo.getWms_cre_credit_head_id() != null)
        {
            WmsCreCreditHead wHead = wmscrecreditheadDao_m.get(queryInfo.getWms_cre_credit_head_id());
            if (wHead != null)
            {
                mapProtocolProperty.put("regionNumber", wHead.getCreate_user_city_code());
                queryInfo.setRegionNumber(wHead.getCreate_user_city_code());
            }
        }
        // 其他都没有查询登陆人
        else if (user != null && user.getUser_regionNumber() != null)
        {
            mapProtocolProperty.put("regionNumber", user.getUser_regionNumber());
            queryInfo.setRegionNumber(user.getUser_regionNumber());
        }
            // 产品配置表获取产品对应信息
        List<Map<String, Object>> Property = wmssyspropertyDao.getprotocolPropertyApply(mapProtocolProperty);
            Map<String,Object> retMap=Property.get(0);
            
            mapProtocolProperty.put("borrow_deadline", queryInfo.getBorrow_deadline());// 期限
            mapProtocolProperty.put("credit_limit", queryInfo.getAppro_limit());// 获取申请贷款额度
            mapProtocolProperty.put("payment_contract_type", retMap.get("payment_contract_type"));// 获取属性表还款类型
            mapProtocolProperty.put("cre_loan_type", queryInfo.getProtocol_type());// 获取贷款产品
            // 产品配置表获取产品对应信息
        List<Map<String, Object>> retmapProtocolProperty = wmssyspropertyDao.getprotocolPropertyApply(mapProtocolProperty);
            if(retmapProtocolProperty!=null&&retmapProtocolProperty.size()>0){
                Map<String,Object> retMap2=retmapProtocolProperty.get(0);
               //配置表获取
                paramMap.put("borrow_interest", retMap2.get("borrow_interest"));// 贷款利率
                paramMap.put("service_cost_month", retMap2.get("platform_fee"));// 平台费
                paramMap.put("consult_service_cost", retMap2.get("protocol_fees"));// 手续费率
                paramMap.put("interest_type", retMap2.get("interest_type"));// 手续费率
                paramMap.put("payment_contract_type", retMap.get("payment_contract_type"));// 还款方式

            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cre_type", 2);// 贷款类型
            map.put("cre_loan_type",  queryInfo.getProtocol_type());// 产品
        map.put("region_number", queryInfo.getRegionNumber());// 区域编码
            // 查询属性表一些固定值
            WmsSysProperty wmsSysProperty = new WmsSysProperty();
            List<WmsSysProperty> list = wmssyspropertyDao.getforNewProtocol(map);
            for (int i = 0; i < list.size(); i++)
            {
                wmsSysProperty = list.get(i);
                paramMap.put(wmsSysProperty.getProperty_column(), wmsSysProperty.getProperty_value());
            }
	        return paramMap;
	}

	@Override
	public Map<String, Object> getBorrowDeadlineChange(UserBean user,WmsSysPropertyPropertySearchBeanVO queryInfo) {
 		Map<String, Object> paramMap = new HashMap<String, Object>();
// 		Integer wms_cre_credit_head_id=queryInfo.getWms_cre_credit_head_id();
	    String protocol_type=queryInfo.getProtocol_type();
	    Integer borrow_deadline=queryInfo.getBorrow_deadline();
	    BigDecimal appro_limit=queryInfo.getAppro_limit();
        // 获取贷款区域编码
        if (StringUtil.isNotBlank(queryInfo.getRegionNumber()))
        {
            paramMap.put("regionNumber", queryInfo.getRegionNumber());
        }
        // 单据主键不为空
        else if (queryInfo.getWms_cre_credit_head_id() != null)
        {
            WmsCreCreditHead wHead = wmscrecreditheadDao_m.get(queryInfo.getWms_cre_credit_head_id());
            if (wHead != null)
            {
                paramMap.put("regionNumber", wHead.getCreate_user_city_code());
                queryInfo.setRegionNumber(wHead.getCreate_user_city_code());
            }
        }
        // 其他都没有查询登陆人
        else if (user != null && user.getUser_regionNumber() != null)
        {
            paramMap.put("regionNumber", user.getUser_regionNumber());
            queryInfo.setRegionNumber(user.getUser_regionNumber());
        }
        // paramMap = getInfoforborrowhouse(paramMap, wms_cre_credit_head_id,
        // protocol_type);// 获取贷款表中的一些信息并做相应的处理
        // paramMap.put("region_number", user.getUser_regionNumber());// 设置区域编码
 		Map<String, Object> map = new HashMap<String, Object>();
        map.put("cre_type", 2);// 贷款类型
        map.put("cre_loan_type", protocol_type);// 合同类型
        map.put("region_number", queryInfo.getRegionNumber());// 区域编码
        // 查询属性表一些固定值
         WmsSysProperty wmsSysProperty = new WmsSysProperty();
         List<WmsSysProperty> list = wmssyspropertyDao.getforNewProtocol(map);
        for (int i = 0; i < list.size(); i++)
         {
             wmsSysProperty = list.get(i);
             paramMap.put(wmsSysProperty.getProperty_column(), wmsSysProperty.getProperty_value());
         }
        // 产品配置表
// 		if(protocol_type.equals("785")){
// 			protocol_type ="1";
// 		}else{
// 			protocol_type ="2";
// 		}
         if(queryInfo != null&&appro_limit.compareTo(new BigDecimal(0))>0){
 		    Map<String,Object> mapProtocolProperty=new HashMap<>();
 	        BigDecimal appro_limit_val = appro_limit.divide(new BigDecimal(10000));
            mapProtocolProperty.put("borrow_deadline", borrow_deadline);// 获取贷款主表期限
            mapProtocolProperty.put("credit_limit", appro_limit_val);// 获取申请贷款额度
            mapProtocolProperty.put("payment_contract_type", paramMap.get("payment_contract_type"));// 获取属性表还款类型
 	        mapProtocolProperty.put("cre_loan_type", queryInfo.getProtocol_type());
            mapProtocolProperty.put("regionNumber", queryInfo.getRegionNumber());// 区域编码
            // 产品配置表获取产品对应信息
            List<Map<String, Object>> retmapProtocolProperty = wmssyspropertyDao.getprotocolPropertyApply(mapProtocolProperty);
 	        if(retmapProtocolProperty!=null&&retmapProtocolProperty.size()>0){
 	        	Map<String,Object> retMap=retmapProtocolProperty.get(0);
                paramMap.put("borrow_interest", retMap.get("borrow_interest"));// 贷款利率
                paramMap.put("consult_service_cost", retMap.get("protocol_fees"));// 手续费率
                paramMap.put("service_cost_month", retMap.get("platform_fee"));// 平台费
 	        }
 		}
		return paramMap;
	}
	
    /**
     * 
     * @Title: getprotocolProperty
     * @Description:获取贷款产品利率
     * @param user
     * @param queryInfo
     * @return 
     * @author: baisong
     * @time:2017年1月4日 上午11:26:51
     * @see com.zx.emanage.loanappro.service.IWmsSysPropertyService#getprotocolProperty(com.zx.sframe.util.vo.UserBean, com.zx.emanage.loanappro.vo.WmsSysPropertyPropertySearchBeanVO)
     * history:
     * 1、2017年1月4日 baisong 创建方法
     */
	@Override
	public Map<String, Object> getprotocolProperty(UserBean user,WmsSysPropertyPropertySearchBeanVO queryInfo) {
 		Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, Object> mapProtocolProperty = new HashMap<>();
        mapProtocolProperty.put("borrow_deadline", queryInfo.getBorrow_deadline());// 获取贷款主表期限
        mapProtocolProperty.put("credit_limit", queryInfo.getCredit_limit());// 获取申请贷款额度
        mapProtocolProperty.put("payment_contract_type", queryInfo.getPayment_contract_type());// 获取属性表还款类型
        mapProtocolProperty.put("cre_loan_type", queryInfo.getCre_loan_type());// 获取贷款产品
        // 获取贷款区域编码
        if (StringUtil.isNotBlank(queryInfo.getRegionNumber()))
        {
            mapProtocolProperty.put("regionNumber", queryInfo.getRegionNumber());  
        }
        // 单据主键不为空
        else if (queryInfo.getWms_cre_credit_head_id() != null)
        {
            WmsCreCreditHead wHead = wmscrecreditheadDao_m.get(queryInfo.getWms_cre_credit_head_id());
            if (wHead != null)
            {
                mapProtocolProperty.put("regionNumber", wHead.getCreate_user_city_code());
            }
        }
        // 其他都没有查询登陆人
        else if (user != null && user.getUser_regionNumber() != null)
        {
            mapProtocolProperty.put("regionNumber", user.getUser_regionNumber());
        }
        // 产品配置表获取产品对应信息
        List<Map<String, Object>> retmapProtocolProperty = wmssyspropertyDao.getprotocolPropertyApply(mapProtocolProperty);
        if (retmapProtocolProperty != null && retmapProtocolProperty.size() == 1)
        {
            Map<String, Object> retMap = retmapProtocolProperty.get(0);
            paramMap.put("borrow_interest", retMap.get("borrow_interest"));// 贷款利率
            paramMap.put("protocol_fees", retMap.get("protocol_fees"));// 手续费率
            paramMap.put("service_cost_month", retMap.get("platform_fee"));// 平台费
            paramMap.put("payment_contract_type", retMap.get("payment_contract_type"));// 还款类型

        }
		return paramMap;
	}

    /**
     * @Title: getPaymentContractType
     * @Description: TODO(查询还款方式)
     * @param cre_loan_type
     * @return Integer
     * @author: jiaodelong
     * @time:2016年12月29日 下午2:29:52
     * @see com.zx.emanage.loanappro.service.IWmsSysPropertyService#getPaymentContractType(java.lang.Integer)
     * history:
     * 1、2016年12月29日 jiaodelong 创建方法
    */
    @Override
    public String getPaymentContractType(Integer cre_loan_type)
    {
        return wmssyspropertyDao.getPaymentContractType(cre_loan_type);
    }
    
    /**
     * v2.1.6
     * @Title: getPaymentAmount
     * @Description: TODO(获取房产核查缴费)
     * @param wms_cre_credit_head_id
     * @return 
     * @author: handongchun
     * @time:2017年7月6日 下午2:44:49
     * history:
     * 1、2017年7月6日 handongchun 创建方法
     */
    private String getPaymentAmount(int wms_cre_credit_head_id){
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        List<Map<String, Object>> search = wmscrehousingpaymentDao.search(parameters);
        if(search == null || search.size() == 0){
            return "0";
        }
        String payment_amount = search.get(0).get("payment_amount").toString();
        return payment_amount;
    }
}
