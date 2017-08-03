package com.zx.emanage.reportmanage.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.typeconverter.Convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.reportmanage.persist.WmsInveCustomerMonthlyIncomeDao;
import com.zx.emanage.reportmanage.service.IWmsInveCustomerMonthlyIncomeService;
import com.zx.emanage.reportmanage.vo.WmsInveCustomerMonthlyIncomeBeanVo;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.SysUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2015，卓信金控
 * 文件名称: WmsInveCustomerMonthlyIncomeServiceImpl.java
 * 系统名称：WMS
 * 模块名称：理财客户每月收益报表
 * 完成日期：
 * 作    者：
 * 内容摘要：
 * 
 * 文件调用：
 * 修改记录：
 * 修改时间：
 * 修 改 人: 
 * 修改内容：
 * 关联BUG：
 * 修改方法：
 */

@Service("wmsInveCustomerMonthlyIncomeService")
public class WmsInveCustomerMonthlyIncomeServiceImpl implements IWmsInveCustomerMonthlyIncomeService
{

    @Autowired
    private WmsInveCustomerMonthlyIncomeDao wmsInveCustomerMonthlyIncomeDao;

    @Autowired
    private WmsInveTransaProdDao wmsInveTransaProdDao;

    /**
     * 获取报表需要的数据 每月收益--报表
     * @return List
     * @author hancd
     */
    @Override
    public Map<String, Object> getMapInfo(WmsInveCustomerMonthlyIncomeBeanVo queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {// 单据编号 模糊
            paramMap.put("bill_code", queryInfo.getBill_code());
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1"))
        {// 产品名称 精确查询
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {// 客户姓名 模糊
            paramMap.put("cus_name", queryInfo.getCus_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getSigning_date_begin()))
        {// 签单日期范围起始 精确查询
            paramMap.put("signing_date_begin", queryInfo.getSigning_date_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getSigning_date_end()))
        {// 签单日期范围结束 精确查询
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
        {// 是否查询有奖利率数据
            paramMap.put("is_bonus_rate", queryInfo.getIs_bonus_rate());
        }
        if (queryInfo.getIs_extent_rate() != null)
        {// 是否查询有公益收益
            paramMap.put("is_extent_rate", queryInfo.getIs_extent_rate());
        }
        if (queryInfo.getIs_query_data_type() != null)
        {
            paramMap.put("is_query_data_type", queryInfo.getIs_query_data_type());
        }
        // 判断支付状态是否为空
        if (queryInfo.getIs_query_pay_status() != null)
        {
            paramMap.put("is_query_pay_status", queryInfo.getIs_query_pay_status());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsInveCustomerMonthlyIncomeDao.getMapInfo(paramMap);
        for (Map<String, Object> map : list)
        {
            map.put("user_id", user.getUserId());
        }
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmsInveCustomerMonthlyIncomeDao.getMapInfoCount(paramMap), list);
        return bean.getGridData();
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
        String strNowDate = formate.format(new Date());
        try
        {
            Date nowDate = formate.parse(strNowDate);
            Date returnDate = formate.parse(returnDateStr);
            return returnDate.compareTo(nowDate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return 0;
    }

    /**
    * 获取报表需要的数据 每月收益--报表导出
    * @return List
    * @author hancd
    */
    @Override
    public Map<String, Object> getMapInfoListWithoutPaging(WmsInveCustomerMonthlyIncomeBeanVo queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<>();
        // 协议编号 模糊
        if (StringUtil.isNotBlank(queryInfo.getProt_code()))
        {
            paramMap.put("prot_code", SysUtil.getSqlLikeParam(queryInfo.getProt_code()));
        }
        // 产品名称 精确查询
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1"))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        // 客户姓名 模糊
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysUtil.getSqlLikeParam(queryInfo.getCus_name()));
        }
        // 签单日期范围起始 精确查询
        if (StringUtil.isNotBlank(queryInfo.getSigning_date_begin()))
        {
            paramMap.put("signing_date_begin", queryInfo.getSigning_date_begin());
        }
        // 签单日期范围结束 精确查询
        if (StringUtil.isNotBlank(queryInfo.getSigning_date_end()))
        {
            paramMap.put("signing_date_end", queryInfo.getSigning_date_end());
        }
        // 应支付时间范围起始 精确查询
        if (StringUtil.isNotBlank(queryInfo.getReturn_date_begin()))
        {
            paramMap.put("return_date_begin", queryInfo.getReturn_date_begin());
        }
        // 应支付时间范围结束 精确查询
        if (StringUtil.isNotBlank(queryInfo.getReturn_date_end()))
        {
            paramMap.put("return_date_end", queryInfo.getReturn_date_end());
        }
        // 应支付时间范围结束 精确查询
        if (queryInfo.getReturn_date() != null && !"".equals(queryInfo.getReturn_date()))
        {
            if (Convert.toDate(queryInfo.getReturn_date()).after(new Date(System.currentTimeMillis())))
            {// 用户输入的日期不能大于当前时间
                return null;
            }
            else
            {
                paramMap.put("return_date", Convert.toDate(queryInfo.getReturn_date()));
            }
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsInveCustomerMonthlyIncomeDao.getMapInfo(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> geToalCountInfo(WmsInveCustomerMonthlyIncomeBeanVo queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {// 单据编号 模糊
            paramMap.put("bill_code", queryInfo.getBill_code());
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1"))
        {// 产品名称 精确查询
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {// 客户姓名 模糊
            paramMap.put("cus_name", queryInfo.getCus_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getSigning_date_begin()))
        {// 签单日期范围起始
         // 精确查询
            paramMap.put("signing_date_begin", queryInfo.getSigning_date_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getSigning_date_end()))
        {// 签单日期范围结束
         // 精确查询
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
            }
        }
        if (queryInfo.getIs_bonus_rate() != null)
        {// 是否查询有奖利率数据
            paramMap.put("is_bonus_rate", queryInfo.getIs_bonus_rate());
        }
        if (queryInfo.getIs_extent_rate() != null)
        {// 是否查询有公益收益
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
        Map<String, Object> resutlMap = wmsInveCustomerMonthlyIncomeDao.getMapInfoo(paramMap);
        if (resutlMap == null)
        {
            return null;
        }
        BigDecimal byyfjbsy = new BigDecimal(resutlMap.get("payable_basic_income") == null ? "0" : resutlMap.get("payable_basic_income").toString());// 本月应付基本收益
        BigDecimal byyfjlsy = new BigDecimal(resutlMap.get("payable_reward_income") == null ? "0" : resutlMap.get("payable_reward_income").toString());// 本月应付奖励收益
        BigDecimal byyfzsy = new BigDecimal(resutlMap.get("payable_total_income") == null ? "0" : resutlMap.get("payable_total_income").toString());// 本月应付总收益

        paramMap.put("byyfjbsy", byyfjbsy);
        paramMap.put("byyfjlsy", byyfjlsy);
        paramMap.put("byyfzsy", byyfzsy);
        return paramMap;
    }

    @Override
    public Map<String, Object> getInveCustomerMonthlyIncomeListDetailInfo(WmsInveCustomerMonthlyIncomeBeanVo queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("wms_inve_transa_prod_id", queryInfo.getWms_inve_transa_prod_id());
        List<Map<String, Object>> list = wmsInveCustomerMonthlyIncomeDao.getInveCustomerMonthlyIncomeListDetailInfo(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmsInveCustomerMonthlyIncomeDao.getInveCustomerMonthlyIncomeListDetailInfoCount(paramMap), list);
        return bean.getGridData();
    }

    /**
     * map中的值转换成bigdecimal
     * @param ob
     * @return
     */
    private static BigDecimal ObjectToDecimal(Object ob)
    {
        BigDecimal bd = new BigDecimal(0);
        if (ob == null)
        {
            return bd;
        }
        bd = new BigDecimal(ob.toString());
        return bd;
    }

}
