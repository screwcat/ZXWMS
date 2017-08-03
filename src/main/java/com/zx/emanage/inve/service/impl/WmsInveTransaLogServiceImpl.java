package com.zx.emanage.inve.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveRedeemDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.persist.WmsInveTransaIncomeDao;
import com.zx.emanage.inve.persist.WmsInveTransaLogDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.inve.service.IWmsInveTransaLogService;
import com.zx.emanage.inve.util.CountIncome;
import com.zx.emanage.inve.vo.WmsInveTransaLogSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveTransaLog;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvetransalogService")
public class WmsInveTransaLogServiceImpl implements IWmsInveTransaLogService
{
    private static Logger log = LoggerFactory.getLogger(WmsInveTransaLogServiceImpl.class);

    @Autowired
    private WmsInveTransaLogDao wmsinvetransalogDao;

    @Autowired
    private WmsInveTransaProdDao wmsInveTransaProdDao;
    
    @Autowired
    private WmsInveTransaIncomeDao wmsInveTransaIncomeDao;
    
    @Autowired
    private WmsInveTransaDao wmsInveTransaDao;
    
    @Autowired
    private WmsInveRedeemDao wmsInveRedeemDao;

    /**
     * 实现查看理财产品信息 查看日志返回的显示信息
     */
    @Override
    public Map<String, Object> getListWithoutPaging(WmsInveTransaLogSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("wms_inve_transa_id", queryInfo.getWms_inve_transa_id());
        paramMap.put("change_date", DateUtil.getDate10(new Date()));
        
        //查找完全赎回时间。如果单据完全赎回，即在当日之后的所有交易日志不显示。
        Map<String, Object> redeemDateMap = wmsInveTransaIncomeDao.getWmsTransaFullRedeemInfo(queryInfo.getWms_inve_transa_id());
        
        List<Map<String, Object>> wmsInveTransaLogMapList = new LinkedList<Map<String, Object>>();
        
        // 根据上单表主键 日期变化 查看该条上单信息的日志信息变化
        List<Map<String, Object>> wmsInveTransaLogList = wmsinvetransalogDao.search(paramMap);
        for (int i = 0; i < wmsInveTransaLogList.size(); i++)
        {
            Map<String, Object> wmsInveTransaLog = wmsInveTransaLogList.get(i);

            if (wmsInveTransaLog.get("product_interest_account") != null)
            {
                // 当金额是大于等于0的时候才显示减号，当金额是小于0的时候是加号
                if (new BigDecimal(wmsInveTransaLog.get("product_interest_account").toString()).compareTo(BigDecimal.ZERO) >= 0)
                {
                    // 收益变化
                    wmsInveTransaLog.put("product_interest_account_str",
                                         "-"
                                                 + getDecimalFormat(new BigDecimal(
                                                                                   wmsInveTransaLog.get("product_interest_account")
                                                                                                   .toString())));
                }
                else
                {
                    wmsInveTransaLog.put("product_interest_account_str",
                                         "+"
                                                 + getDecimalFormat(new BigDecimal(
                                                                                   wmsInveTransaLog.get("product_interest_account")
                                                                                                   .toString()).abs()));
                }
			}
			else
			{
				// 收益变化
				wmsInveTransaLog.put("product_interest_account_str", "-" + getDecimalFormat(new BigDecimal(0)));
			}
			
			// 本金变化
			if (CountIncome.TRANSA_START.equals(wmsInveTransaLog.get("operate_item").toString()))
			{
				// 应该显示:理财上单时候，系统收取的金额
				WmsInveTransaProd wInveTransaProd = wmsInveTransaProdDao.getForJEGL((Integer) wmsInveTransaLog
						.get("wms_inve_transa_id"));
				wmsInveTransaLog.put("product_account_str", "+"
						+ getDecimalFormat(new BigDecimal(wInveTransaProd.getOrg_product_account().toString())));
			}
			else if (CountIncome.TRANSA_END.equals(wmsInveTransaLog.get("operate_item").toString()))
			{
				wmsInveTransaLog.put("product_account_str", "");
				wmsInveTransaLog.put("product_interest_account_str", "");
			}
			else if (CountIncome.TRANSA_REDEEM.equals(wmsInveTransaLog.get("operate_item").toString()))
			{
                wmsInveTransaLog.put("product_account_str",
                                     "-"
                                             + getDecimalFormat(new BigDecimal(
                                                                               wmsInveTransaLog.get("product_account")
                                                                                               .toString())));
				/*
				 * 格式化备注 
				 */
				if (String.valueOf(wmsInveTransaLog.get("remark")) != null
						&& String.valueOf(wmsInveTransaLog.get("remark")).indexOf("&lt;") > -1)
				{
					String billCode = String.valueOf(wmsInveTransaLog.get("remark")).substring(
							String.valueOf(wmsInveTransaLog.get("remark")).indexOf("&lt;") + 4,
							String.valueOf(wmsInveTransaLog.get("remark")).indexOf("&gt;"));// 截取获得billcode
					Map<String, Object> pMap = new HashMap<String, Object>();
					pMap.put("bill_code", billCode);
					Map<String, Object> redeemMap = wmsInveRedeemDao.queryRedeemByBillCode(pMap);

					StringBuilder builder = new StringBuilder();
					builder.append(String.valueOf(wmsInveTransaLog.get("remark")).substring(0,
							String.valueOf(wmsInveTransaLog.get("remark")).indexOf("&lt;")));
					builder.append("<a href='javascript:inveRedeemInfo(" + redeemMap.get("wms_inve_redeem_id") + ","
							+ wmsInveTransaLog.get("wms_inve_transa_id") + ",\""
							+ DateUtil.date2String((Date) redeemMap.get("redeem_date"), "yyyy-MM-dd") + "\",\"已完成\")'>");
					builder.append(String.valueOf(wmsInveTransaLog.get("remark")).substring(
							String.valueOf(wmsInveTransaLog.get("remark")).indexOf("&lt;")));
					builder.append("</a>");
					wmsInveTransaLog.put("remark", builder.toString());
				}
			}
			else if (CountIncome.TRANSA_EXTEND.equals(wmsInveTransaLog.get("operate_item").toString()))
			{
                wmsInveTransaLog.put("product_account_str",
                                     "-"
                                             + getDecimalFormat(new BigDecimal(wmsInveTransaLog.get("product_account")
                                                                                               .toString())));

				wmsInveTransaLog.put("product_interest_account_str", "");
				/*
				 * 格式化备注 
				 */
				if (String.valueOf(wmsInveTransaLog.get("remark")) != null
						&& String.valueOf(wmsInveTransaLog.get("remark")).indexOf("&lt;") > -1)
				{
					String billCode = String.valueOf(wmsInveTransaLog.get("remark")).substring(
							String.valueOf(wmsInveTransaLog.get("remark")).indexOf("&lt;") + 4,
							String.valueOf(wmsInveTransaLog.get("remark")).indexOf("&gt;"));//截取获得billcode
					Map<String, Object> pMap = new HashMap<String, Object>();
					pMap.put("bill_code", billCode);
					Map<String, Object> extendMap = wmsInveTransaDao.queryNewTransaForAutoExtend(pMap);//通过billcode获得续期后新单据的上单主键和上单产品主键
					
					StringBuilder builder = new StringBuilder();
					builder.append(String.valueOf(wmsInveTransaLog.get("remark")).substring(0,
							String.valueOf(wmsInveTransaLog.get("remark")).indexOf("&lt;")));
					builder.append("<a href='javascript:inveTransaInfo(" + extendMap.get("wms_inve_transa_id") + ","
							+ extendMap.get("wms_inve_transa_prod_id") + ",0,0)'>");
					builder.append(String.valueOf(wmsInveTransaLog.get("remark")).substring(
							String.valueOf(wmsInveTransaLog.get("remark")).indexOf("&lt;")));
					builder.append("</a>");
					wmsInveTransaLog.put("remark", builder.toString());
				}
			}
			else
			{
				wmsInveTransaLog.put("product_account_str", "-" + getDecimalFormat(new BigDecimal(0)));
			}
            
            // //jinzhiming修改，目前在当月最后一天查看收益信息和交易详情信息没有统一。收益信息显示未支付，但交易详情中存在支付收益或理财结束日志。
            // //修改成当月最后一天查看，收益信息显示未支付，交易详情中不显示日志。
            // //这里进行判断，如果当天有支付收益日志的话，不显示。
            // if (CountIncome.TRANSA_INCOME.equals(wmsInveTransaLog.get(
            // "operate_item").toString())
            // && DateUtil.getDate10((Date) paramMap.get("change_date"))
            // .compareTo(
            // (Date) wmsInveTransaLog.get("change_date")) == 0) {
            // continue;
            // }
            //
            // //如果单据完全赎回，即在完全赎回操作时间之后的所有交易日志不显示。
            // //如果有完全赎回操作，且如果完全赎回操作时间小于或等于收益日期，应该不显示之后交易日志。
            // if (!(redeemDateMap != null
            // &&
            // CountIncome.TRANSA_INCOME.equals(wmsInveTransaLog.get("operate_item")
            // .toString()) && ((Date) redeemDateMap
            // .get("redeem_date")).compareTo((Date) wmsInveTransaLog
            // .get("change_date")) <= 0)) {
            // wmsInveTransaLogMapList.add(wmsInveTransaLog);
            // }
            wmsInveTransaLogMapList.add(wmsInveTransaLog);
        }

        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", wmsInveTransaLogMapList);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsInveTransaLogSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransalogDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransalogDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsInveTransaLog getInfoByPK(Integer wms_inve_transa_log_id)
    {
        return wmsinvetransalogDao.get(wms_inve_transa_log_id);
    }

    @Override
    @Transactional
    public String save(WmsInveTransaLog bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvetransalogDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsInveTransaLog bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvetransalogDao.update(bean); // update a record replace
                                                // columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsInveTransaLog() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsInveTransaLog> getListByEntity(WmsInveTransaLog queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsInveTransaLog> beanList = wmsinvetransalogDao.getListByEntity(queryInfo);
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
            DecimalFormat fmt = new DecimalFormat("##,###,###,###,###.00");
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
}
