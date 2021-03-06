package com.zx.emanage.inve.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCurrentRateDao;
import com.zx.emanage.inve.persist.WmsInveProtocolRenewalDao;
import com.zx.emanage.inve.persist.WmsInvePruductCategoryDao;
import com.zx.emanage.inve.persist.WmsInvePruductRebateWayDao;
import com.zx.emanage.inve.persist.WmsInvePruductReturnDao;
import com.zx.emanage.inve.persist.WmsInvePruductYearPaySpecialDao;
import com.zx.emanage.inve.persist.WmsInveRedeemDao;
import com.zx.emanage.inve.persist.WmsInveRedeemDetailDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.persist.WmsInveTransaIncomeDao;
import com.zx.emanage.inve.persist.WmsInveTransaLogDao;
import com.zx.emanage.inve.persist.WmsInveTransaMatchDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdRewardDao;
import com.zx.emanage.inve.persist.WmsInveTransaProtocolDao;
import com.zx.emanage.inve.persist.WmsInveTransaSpecialappChangeDao;
import com.zx.emanage.inve.service.IWmsInveTransaProtocolService;
import com.zx.emanage.inve.service.IWmsInveWorkFlowService;
import com.zx.emanage.inve.util.CountIncomeFactory;
import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.emanage.inve.vo.WmsInveTransaProtocolSearchBeanVO;
import com.zx.emanage.loanappro.persist.WmsSysPropertyDao;
import com.zx.emanage.loanappro.service.IWmsSysPropertyService;
import com.zx.emanage.sysmanage.persist.SysWebUserDao;
import com.zx.emanage.util.gen.entity.SysWebUser;
import com.zx.emanage.util.gen.entity.WmsInveCurrentRate;
import com.zx.emanage.util.gen.entity.WmsInveProtocolRenewal;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInvePruductRebateWay;
import com.zx.emanage.util.gen.entity.WmsInvePruductReturn;
import com.zx.emanage.util.gen.entity.WmsInvePruductYearPaySpecial;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveRedeemDetail;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaIncome;
import com.zx.emanage.util.gen.entity.WmsInveTransaLog;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.util.gen.entity.WmsInveTransaProdReward;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.emanage.util.gen.entity.WmsSysProperty;
import com.zx.emanage.util.gen.vo.WmsInveRedeemPrincipalDetailSearchBeanVO;
import com.zx.emanage.util.gen.vo.WmsInveRedeemVO;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvetransaprotocolService")
public class WmsInveTransaProtocolServiceImpl implements IWmsInveTransaProtocolService
{
    private static Logger log = LoggerFactory.getLogger(WmsInveTransaProtocolServiceImpl.class);

    @Autowired
    private WmsInveTransaProtocolDao wmsinvetransaprotocolDao;

    @Autowired
    private WmsInveTransaDao wmsinvetransaDao;

    @Autowired
    private WmsInveTransaProdDao wmsInveTransaProdDao;

    @Autowired
    WmsInveTransaIncomeDao wmsInveTransaIncomeDao;

    @Autowired
    WmsInveTransaLogDao wmsInveTransaLogDao;

    @Autowired
    WmsInveTransaProdRewardDao wmsInveTransaProdRewardDao;

    @Autowired
    private WmsInvePruductReturnDao wmsinvepruductreturnDao;

    @Autowired
    WmsInveRedeemDao wmsInveRedeemDao;// 赎回表 理财——赎回单据表

    @Autowired
    private WmsInveRedeemDetailDao wmsinveredeemdetailDao;// 赎回明细表

    @Autowired
    private IWmsInveWorkFlowService approveInveWorkFlowService;

    @Autowired
    private IWmsInveWorkFlowService aInveWorkFlowService;

    @Autowired
    private SysWebUserDao syswebuserDao;// 外网用户名密码表

    @Autowired
    private WmsInveTransaDao wmsInveTransaDao;// 上单信息表
    
    @Autowired
    private WmsInvePruductCategoryDao wmsInvePruductCategoryDao;// 产品信息表
    
    @Autowired
    private WmsInvePruductRebateWayDao wmsInvePruductRebateWayDao;
    
    @Autowired
    private WmsInveProtocolRenewalDao wmsInveProtocolRenewalDao;
    
    @Autowired
    private WmsInvePruductYearPaySpecialDao wmsInvePruductYearPaySpecialDao;
    @Autowired
    private WmsInvePruductYearPaySpecialDao wmsinvepruductyearpayspecialDao;
    
    @Autowired
    private WmsInveTransaSpecialappChangeDao wmsInveTransaSpecialappChangeDao;// 修改年化利率

    @Autowired
    private WmsInveTransaMatchDao wmsInveTransaMatchDao;
    
    @Autowired
    private  WmsInveTransaMatchDao wmsinvetransamatchDao;//债权匹配表
    
    @Autowired
    private IWmsInveWorkFlowService wmsInveWorkFlowService;
    
    @Autowired
    private IWmsSysPropertyService wmsSysPropertyService;

    @Autowired
    private WmsInveCurrentRateDao wmsInveCurrentRateDao;
    
    @Override
    public Map<String, Object> getListWithoutPaging(WmsInveTransaProtocolSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinvetransaprotocolDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsInveTransaProtocolSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransaprotocolDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransaprotocolDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsInveTransaProtocol getInfoByPK(Integer wms_inve_transa_protocol_id)
    {
        return wmsinvetransaprotocolDao.get(wms_inve_transa_protocol_id);
    }

    /**
     * 理财协议保存 baisong
     */
    @Override
    @Transactional
    public String save(WmsInveTransaProtocol bean, UserBean user, String xqxy, String flag)
    {
        String resStr = "success";
        int ret = 0;
        bean.setWms_inve_redeem_id(0);
        bean.setEnable_flag("1");
        // ***********判断合同是否发生并发现象****************
        List<WmsInveTransaProtocol> protocollist = wmsinvetransaprotocolDao.getListByEntityIsNull(bean);
        if (protocollist.size() > 0)
        {
            resStr = "exist";
            return resStr;
        }
        if(xqxy!=null&&!"".equals(xqxy)){//判断是否续期协议
            WmsInveProtocolRenewal wmsInveProtocolRenewal = new WmsInveProtocolRenewal();
            wmsInveProtocolRenewal.setWms_inve_protocol_renewal_id(Integer.valueOf(xqxy));
            wmsInveProtocolRenewal.setEnable_flag("0");
            ret = wmsInveProtocolRenewalDao.update(wmsInveProtocolRenewal);
            if (ret == 0)
            {
                resStr = "error";
            }
        }
        // *****************************
        setUser(bean, user);// 生成账号密码
        bean.setCreate_user_id(user.getUserId());
        bean.setCreate_user_name(user.getRealname());
        bean.setEnable_flag("1");
        bean.setCreate_timestamp(new Timestamp(new Date().getTime()));
        bean.setLast_update_timestamp(new Timestamp(new Date().getTime()));
        ret = wmsinvetransaprotocolDao.save(bean);
        
        //债权匹配变增加协议ID
        Map<String, Object> matchMap = new HashMap<String, Object>();
        matchMap.put("wms_inve_transa_protocol_id", bean.getWms_inve_transa_protocol_id());
        matchMap.put("wms_inve_transa_prod_id", bean.getWms_inve_transa_prod_id());
        wmsInveTransaMatchDao.updateAgreementIdForMatch(matchMap);


        WmsInveTransa wmsinvetransa = new WmsInveTransa();
        wmsinvetransa.setWms_inve_transa_id(bean.getWms_inve_transa_id());
        wmsinvetransa.setData_status("4");
        wmsinvetransaDao.updateInve_transaForJEZF(wmsinvetransa);
        
        
        
        if (ret == 0)
        {
            resStr = "error";
        }
        
        //如果债权调整只操作收益日志（不重新生成日志和收益）
        if(flag != null && "agreementPrint".equals(flag)) {
            // 根据上单信息表ID和时间查询收益日志信息，并作修改
            Map<String, Object> paramap = new HashMap<String, Object>();
            paramap.put("wms_inve_transa_id", bean.getWms_inve_transa_id());
            paramap.put("param_date", new Date());
            List<WmsInveTransaLog> wmsInveTransaLoglistsaLog = wmsInveTransaLogDao.getWmsInveTransaLog(paramap);
            if (wmsInveTransaLoglistsaLog != null && wmsInveTransaLoglistsaLog.size() > 0)
            {
                WmsInveTransaLog wmsInveTransaLog = wmsInveTransaLoglistsaLog.get(0);
                wmsInveTransaLog.setRemark("关联协议编号 &lt;" + bean.getProt_code() + "&gt;#" + bean.getWms_inve_transa_protocol_id() + "#" + bean.getWms_inve_transa_id() + "#" + bean.getWms_inve_transa_prod_id());
                wmsInveTransaLogDao.update(wmsInveTransaLog);
            }
        } else {
            // 保存客户收益信息和收益日志信息
            //resStr = getSy(bean, user);
            resStr = handleIncomeAndLogInfo(bean, user);
        }
        
        return resStr;
    }
    
    
    /**
     * @Title: handleIncomeAndLogInfo 
     * @Description: 处理收益和日志信息
     * @param bean
     * @param user
     * @return String 
     * @throws
     * @author lvtu 
     * @date 2015年8月28日 上午9:12:39
     */
    private String handleIncomeAndLogInfo(WmsInveTransaProtocol bean, UserBean user)
    {
        CountIncomeFactory.getCountIncome(bean).getIncomeAndLog(bean, user);
        return "success";
    }
    /**
     * @Title: handleIncomeAndLogInfo 
     * @Description: 处理收益和日志信息
     * @param bean
     * @param user
     * @return String 
     * @throws
     * @author lvtu 
     * @date 2015年8月28日 上午9:12:39
     */
    private String handleIncomeAndLogInfoSH(WmsInveTransaProtocol bean, UserBean user)
    {
        CountIncomeFactory.getCountIncome(bean).getIncomeAndLogForRedeem(bean, user);
        return "success";
    }
    
//    /**
//     * 封装累计存量收益计算和日志生成所需要的数据
//     * @param bean
//     * @param category
//     * @param rebateWays
//     * @param paramMap 要传入到计算收益中的paramMap
//     */
    // private void packageTotalAccountDataForIncomeAndLog(
    // WmsInveTransaProtocol bean, WmsInvePruductCategory category,
    // List<WmsInvePruductRebateWay> rebateWays,
    // Map<String, Object> paramMap) {
    // Map<String, Object> map = new HashMap<String, Object>();
    // map.put("b_id_card", bean.getB_id_card());
    // map.put("wms_inve_pruduct_category_id",
    // category.getWms_inve_pruduct_category_id());
    // //获取所有该用户该产品的正常单据
    // List<WmsInveTransaProtocol> wmsInveTransaProtocols =
    // wmsinvetransaprotocolDao.getAllProtocolForCategoryAndIDCard(map);
    // //该客户该产品的总投资金额
    // BigDecimal totalMoney = new BigDecimal(0);
    // for(WmsInveTransaProtocol protocol : wmsInveTransaProtocols) {
    // totalMoney = totalMoney.add(protocol.getProduct_account());
    // }
    //
    // // WmsInvePruductRebateWay pruductRebateWay = null;
    // List<WmsInvePruductRebateWay> pruductRebateWays = new
    // ArrayList<WmsInvePruductRebateWay>();
    //
    // //判断累积存量
    // for(WmsInvePruductRebateWay wmsInvePruductRebateWay : rebateWays) {
    // BigDecimal start = new
    // BigDecimal(wmsInvePruductRebateWay.getCustomer_stock_begin()).multiply(new
    // BigDecimal(10000));
    // BigDecimal end = new
    // BigDecimal(wmsInvePruductRebateWay.getCustomer_stock_end()).multiply(new
    // BigDecimal(10000));
    // if(totalMoney.compareTo(start) >= 0 && totalMoney.compareTo(end) <=0) {
    // //jinzhiming修改，因为满足累计存量条件不只会有一个
    // // pruductRebateWay = wmsInvePruductRebateWay;
    // pruductRebateWays.add(wmsInvePruductRebateWay);
    // }
    // }
    //
    //
    // List<Map<String, Object>> updRebateWayIncomeAndLogList = new
    // ArrayList<Map<String, Object>>();
    //
    // //jinzhiming修改，因为满足满月存量的条件可能不只一个，调整下面满足多条件满足的情况
    // if(!pruductRebateWays.isEmpty()){
    // /*
    // * updRebateWayIncomeAndLogList数据格式:
    // * [
    // *
    // {'full_month':3,'bonus_return_rate':2,'updWmsInveTransaIncome':[...],'updWmsInveTransaLog':[...]},
    // *
    // {'full_month':6,'bonus_return_rate':3,'updWmsInveTransaIncome':[...],'updWmsInveTransaLog':[...]}
    // * ]
    // * 表示存量金额且满月（3）满足条件的收入信息和日志信息
    // * 和
    // * 存量金额且满月（6）满足条件的收入信息和日志信息
    // */
    //
    // List<WmsInveTransaIncome> updWmsInveTransaIncome = null;
    // List<WmsInveTransaLog> updWmsInveTransaLog = null;
    //
    // Map<String, Object> rebateWayParamMap = null;
    //
    // Map<String, Object> params = new HashMap<String, Object>();
    // for (WmsInvePruductRebateWay wmsInvePruductRebateWay : pruductRebateWays)
    // {
    // updWmsInveTransaIncome = new ArrayList<WmsInveTransaIncome>();
    // updWmsInveTransaLog = new ArrayList<WmsInveTransaLog>();
    // rebateWayParamMap = new HashMap<String, Object>();
    //
    // params.put("full_month", wmsInvePruductRebateWay.getFull_month());
    // params.put("date_of_payment", bean.getDate_of_payment());
    //
    // for(WmsInveTransaProtocol protocol : wmsInveTransaProtocols) {
    // params.put("wms_inve_transa_id", protocol.getWms_inve_transa_id());
    // WmsInveTransaIncome wmsInveTransaIncome =
    // wmsInveTransaIncomeDao.getWmsInveTransaIncome(params);
    //
    // if(wmsInveTransaIncome != null) {
    // rebateWayParamMap = new HashMap<String, Object>();
    // updWmsInveTransaIncome.add(wmsInveTransaIncome);
    // params.put("change_date", wmsInveTransaIncome.getReturn_date());
    // List<WmsInveTransaLog> wmsInveTransaLoglistsaLog =
    // wmsInveTransaLogDao.getWmsInveTransaLog(params);
    // if(!wmsInveTransaLoglistsaLog.isEmpty()){
    // updWmsInveTransaLog.add(wmsInveTransaLoglistsaLog.get(0));
    // }
    // }
    // }
    // if(updWmsInveTransaIncome != null && updWmsInveTransaIncome.size() >0) {
    // rebateWayParamMap.put("updWmsInveTransaIncome", updWmsInveTransaIncome);
    // }
    // if(updWmsInveTransaLog != null && updWmsInveTransaLog.size() >0) {
    // rebateWayParamMap.put("updWmsInveTransaLog", updWmsInveTransaLog);
    // }
    // if (!rebateWayParamMap.isEmpty()) {
    // //
    // rebateWayParamMap.put("full_month",wmsInvePruductRebateWay.getFull_month());
    // rebateWayParamMap.put("bonus_return_rate",
    // wmsInvePruductRebateWay.getBonus_rate());
    // updRebateWayIncomeAndLogList.add(rebateWayParamMap);
    // }
    // }
    // }
    //
    // //不管pruductRebateWays有没有数据都传入，避免做null判断和出现空指针
    // paramMap.put("wmsInvePruductRebateWay",
    // pruductRebateWays);//这里传入的产品利率设置-累计存量信息是满足金额条件的。如果投资金额不满足条件则传入的空集合。
    // paramMap.put("updRebateWayIncomeAndLogList",
    // updRebateWayIncomeAndLogList);
//    }

    /**
     * 保存客户收益信息和收益日志信息
     * 
     * @return "success" or "repeat"
     * @author 张风山
     */
    @Transactional
    public String getSy(WmsInveTransaProtocol bean, UserBean user)
    {
        WmsInveTransaProd wmsInveTransaProd = wmsInveTransaProdDao.get(bean.getWms_inve_transa_prod_id());
        Calendar nowcalendar = Calendar.getInstance();
        // 总收益
        BigDecimal zsy = new BigDecimal(0);
        zsy = (bean.getProduct_account().multiply((new BigDecimal(wmsInveTransaProd.getProduct_interest()).divide(new BigDecimal(12), 8,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(wmsInveTransaProd.getProduct_deadline()))).add(wmsInveTransaProd.getReward_interest()))).divide(new BigDecimal(
                                                                                                                                                                                                                                             100));
        // 当前日期
        int year = nowcalendar.get(Calendar.YEAR);
        int month = nowcalendar.get(Calendar.MONTH) + 1;
        int day = nowcalendar.get(Calendar.DATE);
        
        String today = "" + year + "-" + month + "-" + day + "";
         
        int logFlag = 0;
        try
        {
            // 保存日志信息的第一条
            WmsInveTransaLog wmsInveTransaLogFirst = new WmsInveTransaLog();
            wmsInveTransaLogFirst.setWms_inve_transa_id(bean.getWms_inve_transa_id());
            wmsInveTransaLogFirst.setProduct_interest_account(new BigDecimal(0));
            wmsInveTransaLogFirst.setCreate_user_id(user.getUserId());
            wmsInveTransaLogFirst.setCreate_user_name(user.getRealname());
            wmsInveTransaLogFirst.setCreate_timestamp(new Timestamp(new Date().getTime()));
            wmsInveTransaLogFirst.setLast_update_timestamp(new Timestamp(new Date().getTime()));
            wmsInveTransaLogFirst.setChange_date(new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd")).parse(today)
                                                                                                       .getTime()));
            wmsInveTransaLogFirst.setProduct_account(bean.getProduct_account());
            wmsInveTransaLogFirst.setOperate_item("理财上单");
            wmsInveTransaLogFirst.setRemark("关联协议编号 &lt;" + bean.getProt_code() + "&gt;" + "#"
                                            + bean.getWms_inve_transa_protocol_id() + "#"
                                            + bean.getWms_inve_transa_id() + "#" + bean.getWms_inve_transa_prod_id());
            wmsInveTransaLogDao.save(wmsInveTransaLogFirst);
            Date syDate = (new SimpleDateFormat("yyyy-MM-dd")).parse(today);
            // 期数标识
            int monthFlag = 0;
            while (compareDate(calculateEndDate(bean.getEnd_of_date(), 1), syDate) >= 0)
            {
                Date eDate = calculateEndDate(syDate, 1);
                Date lastDate = getTheLastDayForMonth(eDate);
                // 月收益天数
                int syts = calInterval(syDate, lastDate, "d");
                // 正常收益
                BigDecimal sy1 = new BigDecimal(0);
                // 奖励收益
                BigDecimal sy2 = new BigDecimal(0);
                // 整月天数
                Calendar cal = Calendar.getInstance();
                cal.setTime(lastDate);
                int sytsFlag = cal.get(Calendar.DATE);
                syDate = calculateEndDate(syDate, syts);
                if (compareDate(calculateEndDate(bean.getEnd_of_date(), 1), syDate) < 0)
                {
                    lastDate = calculateEndDate(syDate, -1 * syts);
                    syts = calInterval(lastDate, calculateEndDate(bean.getEnd_of_date(), 1), "d");
                    lastDate = calculateEndDate(lastDate, syts);
                }
                if (syts < sytsFlag)
                {
                    // 不是整月的收益
                    sy1 = (new BigDecimal(syts).multiply(bean.getProduct_account()).multiply(new BigDecimal(wmsInveTransaProd.getProduct_interest()))).divide((new BigDecimal(30).multiply(new BigDecimal(12).multiply(new BigDecimal(100)))),
                                                                                                                                              2,
                                                                                                                                              BigDecimal.ROUND_DOWN);
                    if (compareDate(bean.getEnd_of_date(), lastDate) > 0)
                    {
                        monthFlag = 0;
                    }
                    else
                    {
                        monthFlag = monthFlag + 1;
                    }
                }
                else if (syts == sytsFlag)
                {
                    // 整月收益
                    sy1 = (bean.getProduct_account().multiply(new BigDecimal(wmsInveTransaProd.getProduct_interest()))).divide((new BigDecimal(
                                                                                                                               12).multiply(new BigDecimal(
                                                                                                                                                           100))),
                                                                                                               2,
                                                                                                               BigDecimal.ROUND_DOWN);
                    monthFlag = monthFlag + 1;
                }
                if (monthFlag == bean.getProduct_deadline())
                {
                    lastDate = bean.getEnd_of_date();
                }
                // 计算奖励收益
                List<WmsInveTransaProdReward> wmsInveTransaProdRewardList = wmsInveTransaProdRewardDao.selectForSy(bean.getWms_inve_transa_prod_id());
                if (wmsInveTransaProdRewardList != null && wmsInveTransaProdRewardList.size() != 0)
                {
                    int[] deadlineMonthArray = new int[12];
                    Map<Integer, BigDecimal> rewardInterestMap = new HashMap<Integer, BigDecimal>();

                    for (int i = 0; i < wmsInveTransaProdRewardList.size(); i++)
                    {
                        WmsInveTransaProdReward wmsInveTransaProdReward = wmsInveTransaProdRewardList.get(i);
                        deadlineMonthArray[i] = wmsInveTransaProdReward.getProduct_deadline_month();
                        rewardInterestMap.put(wmsInveTransaProdReward.getProduct_deadline_month(),
                                              wmsInveTransaProdReward.getReward_interest());
                    }
                    Arrays.sort(deadlineMonthArray);
                    for (int j = deadlineMonthArray.length - 1; j >= 0; j--)
                    {
                        if (deadlineMonthArray[j] != 0 && monthFlag % deadlineMonthArray[j] == 0 && monthFlag != 0)
                        {
                            sy2 = (bean.getProduct_account().multiply(rewardInterestMap.get(deadlineMonthArray[j]))).divide(new BigDecimal(
                                                                                                                                           100),
                                                                                                                            0,
                                                                                                                            BigDecimal.ROUND_DOWN);
                            break;
                        }
                    }
                }
                // 保存收益表信息
                WmsInveTransaIncome wmsInveTransaIncome = new WmsInveTransaIncome();
                wmsInveTransaIncome.setWms_inve_transa_id(bean.getWms_inve_transa_id());
                wmsInveTransaIncome.setWms_inve_transa_prod_id(bean.getWms_inve_transa_prod_id());
                wmsInveTransaIncome.setWms_inve_pruduct_category_id(wmsInveTransaProd.getWms_inve_pruduct_category_id());
                wmsInveTransaIncome.setProduct_interest_account(sy1.add(sy2));
                wmsInveTransaIncome.setReturn_date(new java.sql.Date(lastDate.getTime()));
                wmsInveTransaIncome.setAct_return_date(new java.sql.Date(lastDate.getTime()));
                wmsInveTransaIncome.setProduct_account(bean.getProduct_account());
                wmsInveTransaIncome.setCreate_user_id(user.getUserId());
                wmsInveTransaIncome.setCreate_user_name(user.getRealname());
                wmsInveTransaIncome.setCreate_timestamp(new Timestamp(new Date().getTime()));
                wmsInveTransaIncome.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                if (monthFlag == bean.getProduct_deadline())
                {
                    wmsInveTransaIncome.setProduct_interest_account(zsy);
                }
                wmsInveTransaIncomeDao.save(wmsInveTransaIncome);
                // 保存日志表信息
                WmsInveTransaLog wmsInveTransaLog = new WmsInveTransaLog();
                wmsInveTransaLog.setWms_inve_transa_id(bean.getWms_inve_transa_id());
                wmsInveTransaLog.setChange_date(new java.sql.Date(lastDate.getTime()));
                wmsInveTransaLog.setProduct_interest_account(sy1.add(sy2));
                wmsInveTransaLog.setCreate_user_id(user.getUserId());
                wmsInveTransaLog.setCreate_user_name(user.getRealname());
                wmsInveTransaLog.setCreate_timestamp(new Timestamp(new Date().getTime()));
                wmsInveTransaLog.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                wmsInveTransaLog.setProduct_account(new BigDecimal(0));
                wmsInveTransaLog.setOperate_item("支付收益");
                if (monthFlag == bean.getProduct_deadline())
                {
                    wmsInveTransaLog.setChange_date(bean.getEnd_of_date());
                    wmsInveTransaLog.setProduct_account(bean.getProduct_account());
                    wmsInveTransaLog.setProduct_interest_account(zsy);
                    wmsInveTransaLog.setOperate_item("理财结束");
                }
                wmsInveTransaLogDao.save(wmsInveTransaLog);
                logFlag++;
                zsy = zsy.subtract(sy1.add(sy2));
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return "success";
    }

    @Override
    @Transactional
    public String update(WmsInveTransaProtocol bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvetransaprotocolDao.update(bean); // update a record replace
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
     * WmsInveTransaProtocol() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsInveTransaProtocol> getListByEntity(WmsInveTransaProtocol queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsInveTransaProtocol> beanList = wmsinvetransaprotocolDao.getListByEntity(queryInfo);
        return beanList;
    }

    /**
     * 根据起始日期和间隔时间计算结束日期
     * 
     * @author 张风山
     */
    public static Date calculateEndDate(Date sDate, int days)
    {
        // 将开始时间赋给日历实例
        Calendar sCalendar = Calendar.getInstance();
        sCalendar.setTime(sDate);
        // 计算结束时间
        sCalendar.add(Calendar.DATE, days);
        // 返回Date类型结束时间
        return sCalendar.getTime();
    }

    /**
     * 计算两个日期的时间间隔
     * 
     * @author 张风山
     */
    private static int calInterval(Date sDate, Date eDate, String type)
    {
        // 时间间隔，初始为0
        int interval = 0;

        /* 比较两个日期的大小，如果开始日期更大，则交换两个日期 */
        // 标志两个日期是否交换过
        boolean reversed = false;
        if (compareDate(sDate, eDate) > 0)
        {
            Date dTest = sDate;
            sDate = eDate;
            eDate = dTest;
            // 修改交换标志
            reversed = true;
        }
        /* 将两个日期赋给日历实例，并获取年、月、日相关字段值 */
        Calendar sCalendar = Calendar.getInstance();
        sCalendar.setTime(sDate);
        int sYears = sCalendar.get(Calendar.YEAR);
        int sMonths = sCalendar.get(Calendar.MONTH);
        int sDays = sCalendar.get(Calendar.DAY_OF_YEAR);
        Calendar eCalendar = Calendar.getInstance();
        eCalendar.setTime(eDate);
        int eYears = eCalendar.get(Calendar.YEAR);
        int eMonths = eCalendar.get(Calendar.MONTH);
        int eDays = eCalendar.get(Calendar.DAY_OF_YEAR);
        // 年
        if (cTrim(type).equals("Y") || cTrim(type).equals("y"))
        {
            interval = eYears - sYears;
            if (eMonths < sMonths)
            {
                --interval;
            }
        }
        // 月
        else if (cTrim(type).equals("M") || cTrim(type).equals("m"))
        {
            interval = 12 * (eYears - sYears);
            interval += (eMonths - sMonths);
        }
        // 日
        else if (cTrim(type).equals("D") || cTrim(type).equals("d"))
        {
            interval = 365 * (eYears - sYears);
            interval += (eDays - sDays);
            // 除去闰年天数
            while (sYears < eYears)
            {
                if (isLeapYear(sYears))
                {
                    --interval;
                }
                ++sYears;
            }
        }
        // 如果开始日期更大，则返回负值
        if (reversed)
        {
            interval = -interval;
        }
        // 返回计算结果
        return interval;
    }

    /**
     * 判定某个年份是否是闰年
     * 
     * @author 张风山
     */
    private static boolean isLeapYear(int year)
    {
        return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
    }

    /**
     * 字符串去除两头空格，如果为空，则返回""，如果不空，则返回该字符串去掉前后空格
     * 
     * @author 张风山
     */
    public static String cTrim(String tStr)
    {
        String ttStr = "";
        if (tStr == null)
        {
        }
        else
        {
            ttStr = tStr.trim();
        }
        return ttStr;
    }

    /**
     * 比较两个Date类型的日期大小
     * 
     * @param sDate开始时间
     * @param eDate结束时间
     * @return result返回结果(0--相同 1--前者大 2--后者大)
     */
    private static int compareDate(Date sDate, Date eDate)
    {
        int result = 0;
        // 将开始时间赋给日历实例
        Calendar sC = Calendar.getInstance();
        sC.setTime(sDate);
        // 将结束时间赋给日历实例
        Calendar eC = Calendar.getInstance();
        eC.setTime(eDate);
        // 比较
        result = sC.compareTo(eC);
        // 返回结果
        return result;
    }

    /**
     * 获取指定日期当月的最后一天的日期
     * 
     * @author 张风山
     */
    private static Date getTheLastDayForMonth(Date cDay1)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(cDay1);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        Date lastDate = cDay1;
        lastDate.setDate(lastDay);
        return lastDate;
    }

    

    @Override
    public Map<String, Object> getRedeemApply(String wms_inve_transa_id)
    {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_inve_transa_id", wms_inve_transa_id);
        //paramMap.put("checkedInveTransaIDs", checkedInveTransaIDs.split(","));
        // 上单产品信息表、上单协议表 获取赎回申请信息
        String  over_date_value="";
        List<Map<String, Object>> list = wmsinvetransaprotocolDao.getRedeemApply(paramMap);
        if(list!=null&&list.size()>0){
            for (int i = 0; i < list.size(); i++)
            {
                BigDecimal bonus_rate = new BigDecimal(0);// 奖励利率
                Map<String, Object> map = list.get(i);
                // 对年付产品年收益判断 如果是不同年的收益不同会获取产品年付特表中第一年第二年的年收益
                // category_interest_pay_method 付息方式:1 月付 2 年付
                // --category_type产品类型:1:信用类产品 2：房贷抵押类产品 3：车贷抵押类产品--Name Code
                // Data Type Length Precision Primary Foreign Key Mandatory
                // 产品期限 category_deadline==24
                if ("2".equals(map.get("category_interest_pay_method").toString()) && ("2".equals(map.get("category_type").toString()) || "3".equals(map.get("category_type").toString())) && "24".equals(map.get("category_deadline").toString()) && "1".equals(map.get("exceed_year").toString()))
                {// 判断理财是否超过了一年
                    WmsInvePruductYearPaySpecial wmsinvepruductyearpayspecial = new WmsInvePruductYearPaySpecial();
                    wmsinvepruductyearpayspecial.setWms_inve_pruduct_category_id(Integer.valueOf(map.get("wms_inve_pruduct_category_id").toString()));
                    List<WmsInvePruductYearPaySpecial> listPS = wmsinvepruductyearpayspecialDao.getListByEntity(wmsinvepruductyearpayspecial);
                    if (listPS != null && listPS.size() > 0)
                    {
                        wmsinvepruductyearpayspecial = listPS.get(0);
                        if (wmsinvepruductyearpayspecial.getSecond_year_interest_rate() != null && !"".equals(wmsinvepruductyearpayspecial.getSecond_year_interest_rate()))
                        {
                            list.get(i).put("product_interest", wmsinvepruductyearpayspecial.getSecond_year_interest_rate());// 年收益
                        }
                    }
                }
                else if ("1".equals(map.get("category_interest_pay_method").toString()))
                {// 月付
                    // 俩个时间月份差
                    int monthNum = 0;
                    try
                    {
                        // monthNum=Integer.valueOf(map.get("month_num").t);
                        monthNum = DateUtil.getMonthNum(map.get("date_of_payment_str").toString(), map.get("now_time").toString());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    if ("1".equals(map.get("category_rebate_way").toString()))
                    {// 返利方式--满月
                        WmsInvePruductRebateWay rebateWay = new WmsInvePruductRebateWay();
                        rebateWay.setWms_inve_pruduct_category_id(Integer.valueOf(map.get("wms_inve_pruduct_category_id").toString()));
                        rebateWay.setEnable_flag("1");
                        List<WmsInvePruductRebateWay> rebateWays = wmsInvePruductRebateWayDao.getListByEntity(rebateWay);
                        for (WmsInvePruductRebateWay rebate : rebateWays)
                        {
                            if (rebate.getFull_month() == monthNum)
                            {
                                bonus_rate = rebate.getBonus_rate();
                            }
                        }
                    }
                    else if ("2".equals(map.get("category_rebate_way").toString()))
                    {// 返利方式--满月存量
                        Map<String, Object> map1 = new HashMap<String, Object>();
                        map.put("b_id_card", map.get("b_id_card"));
                        map.put("wms_inve_pruduct_category_id", Integer.valueOf(map.get("wms_inve_pruduct_category_id").toString()));
                        // 获取所有该用户该产品的正常单据
                        List<WmsInveTransaProtocol> wmsInveTransaProtocols = wmsinvetransaprotocolDao.getAllProtocolForCategoryAndIDCard(map);
                        // 该客户该产品的总投资金额
                        BigDecimal totalMoney = new BigDecimal(0);
                        for (WmsInveTransaProtocol protocol : wmsInveTransaProtocols)
                        {
                            totalMoney = totalMoney.add(protocol.getProduct_account());
                        }
                        WmsInvePruductRebateWay rebateWay = new WmsInvePruductRebateWay();
                        rebateWay.setWms_inve_pruduct_category_id(Integer.valueOf(map.get("wms_inve_pruduct_category_id").toString()));
                        rebateWay.setEnable_flag("1");
                        List<WmsInvePruductRebateWay> rebateWays = wmsInvePruductRebateWayDao.getListByEntity(rebateWay);
                        // 判断累积存量
                        for (WmsInvePruductRebateWay wmsInvePruductRebateWay : rebateWays)
                        {
                            // 数据库为万元，要乘10000
                            BigDecimal start = new BigDecimal(wmsInvePruductRebateWay.getCustomer_stock_begin() * 10000);
                            BigDecimal end = new BigDecimal(wmsInvePruductRebateWay.getCustomer_stock_end() * 10000);
                            if (totalMoney.compareTo(start) >= 0 && totalMoney.compareTo(end) <= 0 && wmsInvePruductRebateWay.getFull_month() == monthNum)
                            {
                                bonus_rate = wmsInvePruductRebateWay.getBonus_rate();
                            }
                        }
                    }

                }

                over_date_value = map.get("over_date_value").toString();
                map.put("bonus_rate", bonus_rate);// 奖励利率
            }
        }
     
 
        Calendar today = Calendar.getInstance();
        // prompt_date是赎回申请时显示的提示在该日期前完成赎回操作，为本月的倒数第二天
        paramMap.put("prompt_date", today.getActualMaximum(Calendar.DAY_OF_MONTH) - 1);
      
        paramMap.put("protocolInfo", list);
        
        return paramMap;
    }

    
    //年付  第一年年付利率
    public void getWmsInvePruductYearInterest(Map<String, Object> map,int period) {
        WmsInvePruductYearPaySpecial wmsinvepruductyearpayspecial = new WmsInvePruductYearPaySpecial();
        wmsinvepruductyearpayspecial.setWms_inve_pruduct_category_id(Integer.valueOf(map.get("wms_inve_pruduct_category_id").toString()));
        List<WmsInvePruductYearPaySpecial> listPS = wmsinvepruductyearpayspecialDao.getListByEntity(wmsinvepruductyearpayspecial);
        if (listPS != null && listPS.size() > 0)
        {
            wmsinvepruductyearpayspecial = listPS.get(0);
            if (period == 1)
            {
                if (wmsinvepruductyearpayspecial.getFirst_year_interest_rate() != null && !"".equals(wmsinvepruductyearpayspecial.getFirst_year_interest_rate()))
                {
                    map.put("product_interest", wmsinvepruductyearpayspecial.getFirst_year_interest_rate());// 第一年收益
                }
            }
            else if (period == 2)
            {
                if (wmsinvepruductyearpayspecial.getSecond_year_interest_rate() != null && !"".equals(wmsinvepruductyearpayspecial.getSecond_year_interest_rate()))
                {
                    map.put("product_interest", wmsinvepruductyearpayspecial.getSecond_year_interest_rate());// 第二年收益
                }
            }
        }
    }
    
    // 限制表利率
    public void getWmsinvepruductreturnInterest(Map<String, Object> map,int period,Date redeem_date) {
        List<WmsInvePruductReturn> beans = wmsinvepruductreturnDao.getReturnInfo((int) map.get("wms_inve_pruduct_category_id"));

        Timestamp buy_time;
        buy_time = new Timestamp(DateUtil.strDate(map.get("date_of_payment").toString(), null).getTime());
        
        int buy_days = DateUtil.getBetweenDays((Date) buy_time, redeem_date);
        int begin; // 限制信息起始天数
        int end; // 限制信息终止天数
       
        if (beans.size() != 0)
        {
            // 获取产品赎回限制的最大结束天数与最小开始天数
            WmsInvePruductReturn wmsInvePruductReturn = wmsinvepruductreturnDao.getBeginAndEnd((int) map.get("wms_inve_pruduct_category_id"));
            int minBegin = wmsInvePruductReturn.getDeadline_begin_date() != null ? wmsInvePruductReturn.getDeadline_begin_date(): 0;
            int maxEnd = wmsInvePruductReturn.getDeadline_end_date() != null ? wmsInvePruductReturn.getDeadline_end_date(): 36500;           
            
            if (minBegin > buy_days)
            {
                getWmsInvePruductYearInterest(map,period);
            }else if (maxEnd < buy_days){
                getWmsInvePruductYearInterest(map,period);
            }else{              
                for (WmsInvePruductReturn bean : beans){
                    begin = bean.getDeadline_begin_date() != null ? bean.getDeadline_begin_date() : 0;
                    end = bean.getDeadline_end_date() != null ? bean.getDeadline_end_date() : 36500;
                    // 判断是否在可赎回限制天数内
                  if (begin <= buy_days & buy_days <= end){
                      map.put("management_fee", bean.getManagement_fee());
                      map.put("product_interest", bean.getProduct_interest());
                  }
                }
            }
        }
        else
        {
            if ("2".equals(map.get("category_interest_pay_method").toString()))
            {
                getWmsInvePruductYearInterest(map, period);
            }
        }
    }
    
    /**
     * 获取特殊产品（卓年宝）奖励利率
     * 
     * @param old_wms_inve_transa_id 单据原始id
     * @return bonus_rate奖励利率
     */
    private BigDecimal getSpecialBonusRate(String old_wms_inve_transa_id)
    {
        BigDecimal bonusRate = BigDecimal.ZERO;
        WmsSysPropertyDao propertyDao = (WmsSysPropertyDao) GlobalVal.ctx.getBean("wmsSysPropertyDao");
        WmsSysProperty property = propertyDao.get(5004);// 获得卓年宝产品奖励利率
        String specialTransaId = property.getProperty_value();// 卓年宝产品奖励1.5%的单据id
        if (!StringUtil.isEmpty(specialTransaId))
        {
            String[] transaIdArr = specialTransaId.split(",");
            for (int i = 0; i < transaIdArr.length; i++)
            {
                if (transaIdArr[i].equals(old_wms_inve_transa_id))
                {
                    bonusRate = new BigDecimal("1.5");
                    return bonusRate;
                }
            }
            bonusRate = new BigDecimal("1");
            return bonusRate;
        }
        return bonusRate;
    }
    
    //获取奖励利率
    public void getBonus_rate(Map<String, Object> map,WmsInveRedeemVO wmsInveRedeemVO,int flag) {
        BigDecimal bonus_rate = new BigDecimal(0);// 奖励利率

        Calendar cal_redeem_date = Calendar.getInstance();
        // 赎回时间
        Date redeem_date = DateUtil.strDate(wmsInveRedeemVO.getRedeem_date_str(), null);
        cal_redeem_date.setTime(redeem_date);
        Calendar pay_date = Calendar.getInstance();
        // 真实的购买时间
        Date date_of_payment = new Timestamp(DateUtil.strDate(map.get("date_of_payment").toString(), null).getTime());
        pay_date.setTime(date_of_payment);

        // 拼remark备注
        String remark = "";
        // 存满期数备注 初始化为空
        map.put("remark_stock", "");
        // 支付日期和申请赎回日期(当前日期)月份差
        int monthNum = 0;

        if (flag == 1)
        {
            monthNum = (int) map.get("product_deadline");
        }
        else
        {
            monthNum = DateUtil.getMonths(map.get("date_of_payment").toString(), wmsInveRedeemVO.getRedeem_date_str());
        }

        /*
         * 判断是不是卓年宝产品且满1年(卓年宝产品Id=8)
         * 满足条件 特殊单据给奖励利率(1.5奖励)  非特殊单据给1
         * 不满足给0
         */
        if (8 == Integer.valueOf(map.get("wms_inve_pruduct_category_id").toString()))
        {
            // 说明是续期单据
            if (map.get("old_wms_inve_transa_id") != null)
            {
                if (monthNum == 12)
                {
                    bonus_rate = getSpecialBonusRate(map.get("old_wms_inve_transa_id").toString());
                    map.put("bonus_rate", bonus_rate);
                    remark = "," + cal_redeem_date.get(Calendar.YEAR) + "年" + (cal_redeem_date.get(Calendar.MONTH) + 1) + "月存满一年奖" + bonus_rate.setScale(1) + "%收益";
                    map.put("remark", remark);
                    map.put("remark_stock", "存满一年");
                    return;
                }
            }
            map.put("remark", remark);
            bonus_rate = BigDecimal.ZERO;
            map.put("bonus_rate", bonus_rate);
            return;
        }
        // 非卓年宝产品
        if ("1".equals(map.get("category_rebate_way").toString()))
        {// 返利方式--满月
            WmsInvePruductRebateWay rebateWay = new WmsInvePruductRebateWay();
            rebateWay.setWms_inve_pruduct_category_id(Integer.valueOf(map.get("wms_inve_pruduct_category_id").toString()));
            rebateWay.setEnable_flag("1");
            List<WmsInvePruductRebateWay> rebateWays = wmsInvePruductRebateWayDao.getListByEntity(rebateWay);
            for (WmsInvePruductRebateWay rebate : rebateWays)
            {
                // 判断赎回日期和到期日期是否跨月 跨月则不给奖励(上月已经给过)
                if (rebate.getFull_month() == monthNum)
                {
                    // 跨年
                    if ((pay_date.get(Calendar.MONTH) + monthNum) >= 12 && (pay_date.get(Calendar.MONTH) + monthNum) % 12 == cal_redeem_date.get(Calendar.MONTH))
                    {
                        bonus_rate = rebate.getBonus_rate();
                        // 未跨年
                    }
                    else if ((pay_date.get(Calendar.MONTH) + monthNum) < 12 && (pay_date.get(Calendar.MONTH) + monthNum) == cal_redeem_date.get(Calendar.MONTH))
                    {
                        bonus_rate = rebate.getBonus_rate();
                    }

                    // 单纯为了拼接remark字符串
                    if (monthNum == 6)
                    {
                        remark = "," + cal_redeem_date.get(Calendar.YEAR) + "年" + (cal_redeem_date.get(Calendar.MONTH) + 1) + "月存满半年奖" + bonus_rate.setScale(2) + "%收益";
                        map.put("remark_stock", "存满半年");
                    }
                    else if (monthNum == 12)
                    {
                        remark = "," + cal_redeem_date.get(Calendar.YEAR) + "年" + (cal_redeem_date.get(Calendar.MONTH) + 1) + "月存满一年奖" + bonus_rate.setScale(2) + "%收益";
                        map.put("remark_stock", "存满一年");
                    }
                    else
                    {
                        remark = "," + cal_redeem_date.get(Calendar.YEAR) + "年" + (cal_redeem_date.get(Calendar.MONTH) + 1) + "月存满" + monthNum + "个月奖" + bonus_rate.setScale(2) + "%收益";
                        map.put("remark_stock", "存满" + monthNum + "个月");
                    }
                }
            }
        }
        else if ("2".equals(map.get("category_rebate_way").toString()))
        {// 返利方式--满月存量
            Map<String, Object> map1 = new HashMap<String, Object>();
            map.put("b_id_card", map.get("b_id_card"));
            map.put("wms_inve_pruduct_category_id", Integer.valueOf(map.get("wms_inve_pruduct_category_id").toString()));
            // 获取所有该用户该产品的正常单据
            List<WmsInveTransaProtocol> wmsInveTransaProtocols = wmsinvetransaprotocolDao.getAllProtocolForCategoryAndIDCard(map);
            // 该客户该产品的总投资金额
            BigDecimal totalMoney = new BigDecimal(0);
            for (WmsInveTransaProtocol protocol : wmsInveTransaProtocols)
            {
                totalMoney = totalMoney.add(protocol.getProduct_account());
            }
            WmsInvePruductRebateWay rebateWay = new WmsInvePruductRebateWay();
            rebateWay.setWms_inve_pruduct_category_id(Integer.valueOf(map.get("wms_inve_pruduct_category_id").toString()));
            rebateWay.setEnable_flag("1");
            List<WmsInvePruductRebateWay> rebateWays = wmsInvePruductRebateWayDao.getListByEntity(rebateWay);
            // 判断累积存量
            for (WmsInvePruductRebateWay wmsInvePruductRebateWay : rebateWays)
            {
                // 数据库为万元，要乘10000
                BigDecimal start = new BigDecimal(wmsInvePruductRebateWay.getCustomer_stock_begin() * 10000);
                BigDecimal end = new BigDecimal(wmsInvePruductRebateWay.getCustomer_stock_end() * 10000);
                if (totalMoney.compareTo(start) >= 0 && totalMoney.compareTo(end) <= 0 && wmsInvePruductRebateWay.getFull_month() == monthNum)
                {
                    // 跨年
                    if ((pay_date.get(Calendar.MONTH) + monthNum) >= 12 && (pay_date.get(Calendar.MONTH) + monthNum) % 12 == cal_redeem_date.get(Calendar.MONTH))
                    {
                        bonus_rate = wmsInvePruductRebateWay.getBonus_rate();
                        // 未跨年
                    }
                    else if ((pay_date.get(Calendar.MONTH) + monthNum) < 12 && (pay_date.get(Calendar.MONTH) + monthNum) == cal_redeem_date.get(Calendar.MONTH))
                    {
                        bonus_rate = wmsInvePruductRebateWay.getBonus_rate();
                    }
                    // 单纯为了拼接remark字符串
                    if (monthNum == 6)
                    {
                        remark = "," + cal_redeem_date.get(Calendar.YEAR) + "年" + (cal_redeem_date.get(Calendar.MONTH) + 1) + "月存满半年奖" + bonus_rate.setScale(2) + "%收益";
                        map.put("remark_stock", "存满半年");
                    }
                    else if (monthNum == 12)
                    {
                        remark = "," + cal_redeem_date.get(Calendar.YEAR) + "年" + (cal_redeem_date.get(Calendar.MONTH) + 1) + "月存满一年奖" + bonus_rate.setScale(2) + "%收益";
                        map.put("remark_stock", "存满一年");
                    }
                    else
                    {
                        remark = "," + cal_redeem_date.get(Calendar.YEAR) + "年" + (cal_redeem_date.get(Calendar.MONTH) + 1) + "月存满" + monthNum + "个月奖" + bonus_rate.setScale(2) + "%收益";
                        map.put("remark_stock", "存满" + monthNum + "个月");
                    }
                }
            }
        }
        map.put("remark", remark);

        map.put("bonus_rate", bonus_rate);// 奖励利率
    }
    
    //判断支付日期是否在2016年7月1号之后
    public int cal_incomeDay(int incomday,Date old_date_of_payment) {
        Date magic_date = DateUtil.strDate("2016-07-01", null);
        // Date date_of_payment = DateUtil.strDate(old_date_of_payment, null);

        if (!DateUtil.before(old_date_of_payment, magic_date))
        {
            return incomday - 1;
        }
        return incomday;
    }
    
    //根据赎回日期与购买日期相差的月份倒推到期日期
    public Date cal_endDate(Date endDate,Integer product_deadline,int months) {

        return DateUtil.getDateMinusMonths(endDate, 12 * ((product_deadline - (months + 1)) / 12));
    }
     
    
    //判断赎回当天单据是否到期
    private void getOverDateValue(Map<String, Object> map,WmsInveRedeemVO wmsInveRedeemVO) {
        Date endDate = DateUtil.strDate(map.get("end_of_date").toString(), null);
        Date redeem_date = DateUtil.strDate(wmsInveRedeemVO.getRedeem_date_str(), null);
        if (endDate.compareTo(redeem_date) < 0)
        {
            map.put("is_over_date", "1");
        }
        else
        {
            map.put("is_over_date", "0");
        }
    }
    
    //判断赎回当天单据是否到期
    private void getIsOrderRedeem(Map<String, Object> map,WmsInveRedeemVO wmsInveRedeemVO) {
        Date now = DateUtil.getDate10(new Date());
        Date redeem_date = DateUtil.strDate(wmsInveRedeemVO.getRedeem_date_str(), null);
        if (now.compareTo(redeem_date) < 0)
        {
            map.put("is_order_redeem", "1");
        }
        else
        {
            map.put("is_order_redeem", "0");
        }
    }
    /**
     * @Title: cal_due_income 
     * @Description: 计算收益
     * @author zhangyunfei
     * @date 2016年8月14日 
     */
    @Override
    @Transactional
    public Map<String, Object> getRedeemDueIncome(WmsInveRedeemVO wmsInveRedeemVO){

        // WmsSysProperty wmsSysProperty =
        // wmsSysPropertyService.getInfoByPK(5001);
        WmsInvePruductCategory category = wmsInvePruductCategoryDao.get(44);

        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_inve_transa_id", wmsInveRedeemVO.getWms_inve_transa_id());
        //上单产品信息表、上单协议表 获取赎回申请信息(默认状态下  月付:basic_monthly_rate*12  年付:category_return_rate)
        List<Map<String, Object>> list = wmsinvetransaprotocolDao.getRedeemApply(paramMap);
        if(list!=null&&list.size()>0){
            for (int i = 0; i < list.size(); i++)
            {
                // 判断是否到期
                Map<String, Object> map = list.get(i);
                // 获取is_over_date(是否到期)
                getOverDateValue(map, wmsInveRedeemVO);
                // 获取预约状态
                getIsOrderRedeem(map, wmsInveRedeemVO);
                map.put("old_wms_inve_transa_id", wmsInveRedeemVO.getOld_wms_inve_transa_id());
                if ("2".equals(map.get("category_interest_pay_method").toString()))
                { // 付息方式: 1 月付 2 年付
                    getYearDueIncome(map, wmsInveRedeemVO, category);
                }
                else if ("1".equals(map.get("category_interest_pay_method").toString()))
                {
                    getMonthDueIncome(map, wmsInveRedeemVO, category);
                }
                // 产品到期 封装到期参数
                if ("1".equals(map.get("is_over_date").toString()))
                {
                    map.put("product_interest", category.getCategory_return_rate().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP)); // 产品年收益
                    map.put("is_enable_return", "1");
                    map.put("bonus_rate", new BigDecimal(0));// 奖励利率
                }
                map.put("public_interest", category.getCategory_return_rate().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP)); // 公益收益
            }
        }
        paramMap.put("protocolInfo", list);
        return paramMap;
    }
    
    /**
     * @Title: cal_due_income 
     * @Description: 计算年付收益
     * @author zhangyunfei
     * @date 2016年8月14日 
     */
    public void getYearDueIncome(Map<String, Object> map,WmsInveRedeemVO wmsInveRedeemVO,WmsInvePruductCategory category) {
        // 公益收益信息
        int days_extend_income = 0;// 公益天数
        BigDecimal extend_income_rate = new BigDecimal(0);// 公益利率
        //基本收益信息
        int days_of_basic_income = 0; // 基本收益天数
        BigDecimal basic_inceom_rate = new BigDecimal(0);// 基本利率
        // 奖励收益信息
        BigDecimal reward_income_rate = new BigDecimal(0);// 奖励利率
    
        int incomeDay = 0;
        int publicDay = 0;
        BigDecimal management_fee = new BigDecimal(0);

        BigDecimal due_income = new BigDecimal(0);
        BigDecimal public_income = new BigDecimal(0);
        // 活期收益天数
        int currentDay = 0;
        // 活期收益
        BigDecimal current_income = new BigDecimal(0);
        Calendar cal_current_date = Calendar.getInstance();
        BigDecimal current_income_rate = new BigDecimal(0);// 活期收益利率
        BigDecimal sum_current_income = BigDecimal.ZERO; // 活期收益总和

        List<WmsInveRedeemPrincipalDetailSearchBeanVO> listgridVal = JsonUtil.jsonArrayToList(wmsInveRedeemVO.getRedeem_amount_grid(), WmsInveRedeemPrincipalDetailSearchBeanVO.class);

        // 赎回日期与支付日期相差的月份
        int months = DateUtil.getMonths(map.get("date_of_payment").toString(), wmsInveRedeemVO.getRedeem_date_str());

        Calendar cal_redeem_date = Calendar.getInstance();
        // 赎回时间
        Date redeem_date = DateUtil.strDate(wmsInveRedeemVO.getRedeem_date_str(), null);
        cal_redeem_date.setTime(redeem_date);

        Calendar pay_date = Calendar.getInstance();
        Calendar end_of_date = Calendar.getInstance();

        // 真实的购买时间
        Timestamp buy_time = new Timestamp(DateUtil.strDate(map.get("date_of_payment").toString(), null).getTime());
        Date endDate = DateUtil.strDate(map.get("end_of_date").toString(), null);
        pay_date.setTime((Date) buy_time);
        DateFormat ddf = new SimpleDateFormat("yyyy-MM-dd");
        Date old_pay_date = wmsInveRedeemVO.getOld_date_of_payment();
        Calendar magic_date = Calendar.getInstance();
        Calendar old_pay_date_calendar = Calendar.getInstance();
        old_pay_date_calendar.setTime(old_pay_date);
        try {
            magic_date.setTime(ddf.parse("2016-07-01"));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        
        //用于计算自然年
        Date buy_date = DateUtil.strDate(map.get("date_of_payment").toString(), null);
        // 2016-07-01之前 计算自然年天数需要把购买时间+1天 (上单当天无收益)
        if (old_pay_date_calendar.compareTo(magic_date) < 0)
        {
            buy_date = DateUtil.getDateAddDays(buy_date, 1);
        }

        end_of_date.setTime(endDate);
        // 付息方式: 1 月付 2 年付
         // 当月上单当月赎回
        if (pay_date.get(Calendar.YEAR) == cal_redeem_date.get(Calendar.YEAR) && pay_date.get(Calendar.MONTH) == cal_redeem_date.get(Calendar.MONTH))
        {
            // 未到期赎回(老单据自动续期 需要给上一年的收益) 未到期当月赎回
            // if("0".equals(map.get("over_date_value").toString())){//0为未结束
            if (!DateUtil.before(endDate, redeem_date))
            {
                // 续期单据1 为续期 0(针对2015年之前的单据)
                if ("1".equals(wmsInveRedeemVO.getBill_source().toString()))
                {

                    incomeDay = DateUtil.getBetweenDays((Date) buy_time, redeem_date);

                    days_of_basic_income = incomeDay;// 设置基本上收益天数

                    getWmsInvePruductYearInterest(map, ((int) map.get("category_deadline")) / 12);

                    for (int i = 0; i < listgridVal.size(); i++)
                    {

                        WmsInveRedeemPrincipalDetailSearchBeanVO redeemBasic = listgridVal.get(i);
                        // 续期单据上一年的收益 principal_amount*365/365*product_interest
                        due_income = redeemBasic.getPrincipal_amount().multiply(new BigDecimal(10000)).multiply(new BigDecimal(map.get("product_interest").toString()).divide(new BigDecimal(100), 8, RoundingMode.HALF_UP));

                        getWmsinvepruductreturnInterest(map, 1, redeem_date);
                        // 本年收益
                        // incomeDay/(buy_time,endDate)*principal_amount*product_interest
                        // 两年期
                        // incomeDay/(buy_time+12,endDate-12)*principal_amount*product_interest
                        due_income = due_income.add(new BigDecimal(incomeDay).divide(new BigDecimal(DateUtil.getBetweenDays(buy_date, DateUtil.getDateMinusMonths(endDate, 12 * (((Integer) map.get("product_deadline") - 1) / 12))) + 1), 8, RoundingMode.HALF_UP).multiply(redeemBasic.getPrincipal_amount().multiply(new BigDecimal(10000))).multiply(new BigDecimal(map.get("product_interest").toString()).divide(new BigDecimal(100), 8, RoundingMode.HALF_UP))).setScale(2, RoundingMode.UP);

                        // 设置基本收益信息和基本利率
                        basic_inceom_rate = new BigDecimal(map.get("product_interest").toString());
                        // 基本收益
                        redeemBasic.setPayable_basic_income(due_income);
                        // 奖励收益
                        redeemBasic.setPayable_reward_income(new BigDecimal(0));
                        // 公益收益
                        redeemBasic.setPublic_amount(new BigDecimal(0));

                        if (map.get("management_fee") != null)
                        {
                            management_fee = redeemBasic.getPrincipal_amount().multiply(new BigDecimal(10000)).multiply(new BigDecimal(map.get("management_fee").toString()).divide(new BigDecimal(100), 8, RoundingMode.HALF_UP)).setScale(2, RoundingMode.UP);
                        }
                        redeemBasic.setManagement_fee(management_fee);
                    }

                }
                else
                {
                    // 查询年付限制表
                    getWmsinvepruductreturnInterest(map, 1, redeem_date);
                    incomeDay = DateUtil.getBetweenDays((Date) buy_time, redeem_date);

                    days_of_basic_income = incomeDay;// 设置基本上收益天数

                    for (int i = 0; i < listgridVal.size(); i++)
                    {

                        WmsInveRedeemPrincipalDetailSearchBeanVO redeemBasic = listgridVal.get(i);
                        // 非续期单据 当月上单当月赎回
                        // 本年收益
                        // incomeDay/(buy_time,endDate)*principal_amount*product_interest
                        // 两年期
                        // incomeDay/(buy_time+12,endDate-12)*principal_amount*product_interest
                        due_income = new BigDecimal(incomeDay).divide(new BigDecimal(DateUtil.getBetweenDays(buy_date, DateUtil.getDateMinusMonths(endDate, 12 * (((Integer) map.get("product_deadline") - 1) / 12))) + 1), 8, RoundingMode.HALF_UP).multiply(redeemBasic.getPrincipal_amount().multiply(new BigDecimal(10000))).multiply(new BigDecimal(map.get("product_interest").toString()).divide(new BigDecimal(100), 8, RoundingMode.HALF_UP)).setScale(2, RoundingMode.UP);

                        // 设置基本利率
                        basic_inceom_rate = new BigDecimal(map.get("product_interest").toString());
                        // 基本收益
                        redeemBasic.setPayable_basic_income(due_income);
                        // 奖励收益
                        redeemBasic.setPayable_reward_income(new BigDecimal(0));
                        // 公益收益
                        redeemBasic.setPublic_amount(new BigDecimal(0));

                        if (map.get("management_fee") != null)
                        {
                            management_fee = redeemBasic.getPrincipal_amount().multiply(new BigDecimal(10000)).multiply(new BigDecimal(map.get("management_fee").toString()).divide(new BigDecimal(100), 8, RoundingMode.HALF_UP)).setScale(2, RoundingMode.UP);
                        }
                        redeemBasic.setManagement_fee(management_fee);
                    }
                }
            }
         //跨年(到期/未到期)  一年期跨年当月属于到期 /两年期跨年当月(到期/未到期)
         //当月赎回(2015.7.1日之前单据(赎回当天有收益) 年不等 月等  赎回日>=上单日   2015.7.1之后单据赎回当天无收益 赎回日=到期日)
        }
        else if ((!(old_pay_date_calendar.compareTo(magic_date) >= 0 && pay_date.get(Calendar.DAY_OF_MONTH) == 1) && (pay_date.get(Calendar.YEAR) != cal_redeem_date.get(Calendar.YEAR) && pay_date.get(Calendar.MONTH) == cal_redeem_date.get(Calendar.MONTH) && pay_date.get(Calendar.DAY_OF_MONTH) <= cal_redeem_date.get(Calendar.DAY_OF_MONTH))) || DateUtil.getBetweenDays(endDate, redeem_date) == 0)
        {
            // } else if (endDate.compareTo(redeem_date)>=0 && )
            // || DateUtil.getBetweenDays(endDate, redeem_date) == 0){
            // 未到期赎回 (多年期产品)
            if (DateUtil.before(redeem_date, endDate))
            {
                // getDatePlusMonths() 购买日期n个月后的日期
                incomeDay = DateUtil.getBetweenDays(DateUtil.getDatePlusMonths((Date) buy_time, 12 * (months / 12)), redeem_date);

                days_of_basic_income = incomeDay;// 设置基本上收益天数

                // due_income = 365*(n/12)+ incomeDay*(n/12+1);
                getWmsinvepruductreturnInterest(map, months / 12, redeem_date);
                // 上一年利率
                BigDecimal pre_product_interest = new BigDecimal(map.get("product_interest").toString());
                getWmsinvepruductreturnInterest(map, months / 12 + 1, redeem_date);
                // 本年利率
                BigDecimal cur_product_interest = new BigDecimal(map.get("product_interest").toString());

                for (int i = 0; i < listgridVal.size(); i++)
                {

                    WmsInveRedeemPrincipalDetailSearchBeanVO redeemBasic = listgridVal.get(i);
                    // 上一年应得收益 principal_amount*365/365*pre_product_interest
                    due_income = redeemBasic.getPrincipal_amount().multiply(new BigDecimal(10000)).multiply(pre_product_interest.divide(new BigDecimal(100), 8, RoundingMode.HALF_UP));

                    // 两年期第二年
                    // incomeDay/(buy_time+12,endDate-12)*principal_amount*product_interest(自然年计算
                    // 根据 到期时间 倒推)
                    due_income = due_income.add(new BigDecimal(incomeDay).divide(new BigDecimal(DateUtil.getBetweenDays(DateUtil.getDatePlusMonths(buy_date, 12 * (months / 12)),
                    // DateUtil.getDatePlusMonths(endDate,12*(months/12)))), 8,
                    // RoundingMode.HALF_UP)
                                                                                                                        DateUtil.getDateMinusMonths(endDate, 12 * (((Integer) map.get("product_deadline") - months) / 12 - 1))) + 1), 8, RoundingMode.HALF_UP).multiply(redeemBasic.getPrincipal_amount().multiply(new BigDecimal(10000))).multiply(cur_product_interest.divide(new BigDecimal(100), 8, RoundingMode.HALF_UP))).setScale(2, RoundingMode.UP);

                    // 设置基本收益信息和基本利率
                    basic_inceom_rate = new BigDecimal(map.get("product_interest").toString());
                    // 基本收益
                    redeemBasic.setPayable_basic_income(due_income);
                    // 奖励收益
                    redeemBasic.setPayable_reward_income(new BigDecimal(0));
                    // 公益收益
                    redeemBasic.setPublic_amount(new BigDecimal(0));

                    if (map.get("management_fee") != null)
                    {
                        management_fee = redeemBasic.getPrincipal_amount().multiply(new BigDecimal(10000)).multiply(new BigDecimal(map.get("management_fee").toString()).divide(new BigDecimal(100), 8, RoundingMode.HALF_UP)).setScale(2, RoundingMode.UP);
                    }
                    redeemBasic.setManagement_fee(management_fee);
                }
            }
            else
            {
                // 当天到期当天赎回
                if (DateUtil.getBetweenDays(endDate, redeem_date) == 0)
                {
                    // 到期单据当天赎回
                    if (old_pay_date_calendar.compareTo(magic_date) >= 0)
                    {
                        // 2017之后的单据，到期日赎回时天数使用减法，老单据直接写死365天
                        // income 计算 (buy_time+n*12) --- redeem_date
                        incomeDay = DateUtil.getBetweenDays(DateUtil.getDatePlusMonths((Date) buy_time, 12 * ((int) map.get("category_deadline") / 12 - 1)), redeem_date);

                        days_of_basic_income = incomeDay;// 设置基本上收益天数

                        getWmsinvepruductreturnInterest(map, months / 12 + 1, redeem_date);

                    }
                    else
                    {
                        // 2016-07-01之前的单据
                        incomeDay = DateUtil.getBetweenDays(DateUtil.getDatePlusMonths((Date) buy_time, 12 * (months / 12 - 1)), redeem_date);

                        days_of_basic_income = incomeDay;// 设置基本上收益天数

                        getWmsInvePruductYearInterest(map, ((int) map.get("category_deadline")) / 12);
                    }

                    for (int i = 0; i < listgridVal.size(); i++)
                    {

                        WmsInveRedeemPrincipalDetailSearchBeanVO redeemBasic = listgridVal.get(i);
                        // 计息天数+1
                        due_income = new BigDecimal(incomeDay).divide(new BigDecimal(DateUtil.getBetweenDays(DateUtil.getDatePlusMonths(buy_date, (12 * (months / 12 - 1)) < 0 ? 0 : (12 * (months / 12 - 1))), cal_endDate(endDate, (Integer) map.get("product_deadline"), months)) + 1), 8, RoundingMode.HALF_UP).multiply(redeemBasic.getPrincipal_amount().multiply(new BigDecimal(10000))).multiply(new BigDecimal(map.get("product_interest").toString()).divide(new BigDecimal(100), 8, RoundingMode.HALF_UP)).setScale(2, RoundingMode.UP);

                        // 设置基本收益信息和基本利率
                        basic_inceom_rate = new BigDecimal(map.get("product_interest").toString());
                        // 基本收益
                        redeemBasic.setPayable_basic_income(due_income);
                        // 奖励收益
                        redeemBasic.setPayable_reward_income(new BigDecimal(0));
                        // 公益收益
                        redeemBasic.setPublic_amount(new BigDecimal(0));
                    }
                    // 单据到期 非当天 未跨月赎回
                }
                else if (end_of_date.get(Calendar.MONTH) == cal_redeem_date.get(Calendar.MONTH) && DateUtil.getBetweenDays(endDate, redeem_date) != 0)
                {

                    getWmsInvePruductYearInterest(map, ((int) map.get("category_deadline")) / 12);
                    // 到期单据本月赎回
                    publicDay = DateUtil.getBetweenDays(endDate, redeem_date);

                    // 结束天数
                    publicDay = cal_incomeDay(publicDay, wmsInveRedeemVO.getOld_date_of_payment());

                    days_extend_income = publicDay;// 设置基本上收益天数

                    for (int i = 0; i < listgridVal.size(); i++)
                    {

                        WmsInveRedeemPrincipalDetailSearchBeanVO redeemBasic = listgridVal.get(i);
                        // 到期单据上一年的收益 principal_amount*365/365*product_interest
                        due_income = redeemBasic.getPrincipal_amount().multiply(new BigDecimal(10000)).multiply(new BigDecimal(map.get("product_interest").toString()).divide(new BigDecimal(100), 8, RoundingMode.UP)).setScale(2, RoundingMode.UP);

                        public_income = new BigDecimal(publicDay).divide(new BigDecimal(365), 8, RoundingMode.HALF_UP)
                                                                 .multiply(redeemBasic.getPrincipal_amount()
                                                                                      .multiply(new BigDecimal(10000)))
                                                                 .multiply(category.getCategory_return_rate()
                                                                                   .divide(new BigDecimal(100), 8,
                                                                                           RoundingMode.HALF_UP))
                                                                 .setScale(2, RoundingMode.UP);

                        // 设置基本收益信息和基本利率
                        extend_income_rate = category.getCategory_return_rate().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
                        // 设置基本收益信息和基本利率
                        basic_inceom_rate = new BigDecimal(map.get("product_interest").toString());
                        // 基本收益
                        redeemBasic.setPayable_basic_income(due_income);
                        // 奖励收益
                        redeemBasic.setPayable_reward_income(new BigDecimal(0));
                        // 公益收益
                        redeemBasic.setPublic_amount(public_income);
                    }
                }
            }
         }else{

            // 未到期 (到期月赎回)
            // if("0".equals(map.get("over_date_value").toString())){
            if (!DateUtil.before(endDate, redeem_date))
            {
                // 结束天数
                incomeDay = DateUtil.getBetweenDays(DateUtil.getDatePlusMonths((Date) buy_time, 12 * (months / 12)), redeem_date);

                days_of_basic_income = incomeDay;// 设置基本上收益天数

                // incomeDay*(n/12+1);
                getWmsinvepruductreturnInterest(map, months / 12 + 1, redeem_date);

                for (int i = 0; i < listgridVal.size(); i++)
                {

                    WmsInveRedeemPrincipalDetailSearchBeanVO redeemBasic = listgridVal.get(i);
                    // 一年期
                    // incomeDay/(buy_time,endDate)*principal_amount*product_interest
                    // 两年期
                    // incomeDay/(buy_time+12,endDate-12)*principal_amount*product_interest
                    due_income = new BigDecimal(incomeDay).divide(new BigDecimal(DateUtil.getBetweenDays(DateUtil.getDatePlusMonths(buy_date, 12 * (months / 12)), cal_endDate(endDate, (Integer) map.get("product_deadline"), months)) + 1), 8, RoundingMode.HALF_UP).multiply(redeemBasic.getPrincipal_amount().multiply(new BigDecimal(10000))).multiply(new BigDecimal(map.get("product_interest").toString()).divide(new BigDecimal(100), 8, RoundingMode.HALF_UP)).setScale(2, RoundingMode.UP);

                    // 设置基本收益信息和基本利率
                    basic_inceom_rate = new BigDecimal(map.get("product_interest").toString());
                    // 基本收益
                    redeemBasic.setPayable_basic_income(due_income);
                    // 奖励收益
                    redeemBasic.setPayable_reward_income(new BigDecimal(0));
                    // 公益收益
                    redeemBasic.setPublic_amount(new BigDecimal(0));

                    if (map.get("management_fee") != null)
                    {
                        management_fee = redeemBasic.getPrincipal_amount().multiply(new BigDecimal(10000)).multiply(new BigDecimal(map.get("management_fee").toString()).divide(new BigDecimal(100), 8, RoundingMode.HALF_UP)).setScale(2, RoundingMode.UP);
                    }
                    redeemBasic.setManagement_fee(management_fee);
                }

            }
            else
            {
                // 判断赎回日是否在到期日的一个月之后 单据处于活期收益期
                if (DateUtil.before(DateUtil.getDatePlusMonths(endDate, 1), redeem_date))
                {
                    // 获取当前生效的活期产品对象
                    WmsInveCurrentRate wmsInveCurrentRate = wmsInveCurrentRateDao.getWmsInveCurrentRateEnable();
                    // 获取活期收益利率
                    if (wmsInveCurrentRate != null)
                    {
                        current_income_rate = wmsInveCurrentRate.getCurrent_rate();
                    }

                    cal_current_date.setTime(DateUtil.getDatePlusMonths(endDate, 1));
                    // 赎回日所在月份和(到期日+一个月)所在月份相同 需要给一部分公益收益+一部分活期收益
                    if (cal_redeem_date.get(Calendar.YEAR) == cal_current_date.get(Calendar.YEAR) && cal_redeem_date.get(Calendar.MONTH) == cal_current_date.get(Calendar.MONTH))
                    {
                        // 公益收益天数
                        publicDay = cal_current_date.get(Calendar.DAY_OF_MONTH);
                        // 活期收益天数=(1号到赎回日天数)-到期天数(当月)
                        currentDay = cal_redeem_date.get(Calendar.DAY_OF_MONTH) - publicDay;
                    }
                    // 赎回日所在月份和(到期日+一个月)所在月份不相同 此时只需要给 活期收益
                    else
                    {
                        // 到期日小于2017-03-01 给2017-04-01至赎回日的活期收益
                        if (DateUtil.before(endDate, DateUtil.strDate("2017-03-01", null)))
                        {
                            currentDay = DateUtil.getBetweenDays(DateUtil.strDate("2017-04-01", null), redeem_date) + 1;
                        }
                        // 到期日期>=2017-03-01 给赎回日至到期日+一个月的活期收益
                        else
                        {
                            currentDay = DateUtil.getBetweenDays(DateUtil.getDatePlusMonths(endDate, 1), redeem_date);
                        }
                    }
                    // 判断赎回当天是否有收益 无收益需要把currentDay-1
                    currentDay = cal_incomeDay(currentDay, wmsInveRedeemVO.getOld_date_of_payment());
                }
                else
                {
                    publicDay = DateUtil.getDaysOfIntervalBegin(redeem_date);
                    
                    publicDay = cal_incomeDay(publicDay, wmsInveRedeemVO.getOld_date_of_payment());
                }
               
                days_extend_income = publicDay;

                // 到期单据跨月赎回
                // publicDay*(公益利率)
                for (int i = 0; i < listgridVal.size(); i++)
                {

                    WmsInveRedeemPrincipalDetailSearchBeanVO redeemBasic = listgridVal.get(i);
                    public_income = new BigDecimal(publicDay).divide(new BigDecimal(365), 8, RoundingMode.HALF_UP)
                                                             .multiply(redeemBasic.getPrincipal_amount()
                                                                                  .multiply(new BigDecimal(10000)))
                                                             .multiply(category.getCategory_return_rate()
                                                                               .divide(new BigDecimal(100), 8,
                                                                                       RoundingMode.HALF_UP))
                                                             .setScale(2, RoundingMode.UP);
                    // 活期收益
                    current_income = new BigDecimal(currentDay).divide(new BigDecimal(365), 8, RoundingMode.HALF_UP).multiply(redeemBasic.getPrincipal_amount().multiply(new BigDecimal(10000))).multiply(current_income_rate.divide(new BigDecimal(100), 8, RoundingMode.HALF_UP)).setScale(2, RoundingMode.UP);
                    // 计算活期收益总和
                    sum_current_income = sum_current_income.add(current_income);
                    
                    // 设置基本收益信息和基本利率
                    extend_income_rate = category.getCategory_return_rate().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
                    // 基本收益
                    redeemBasic.setPayable_basic_income(new BigDecimal(0));
                    // 奖励收益
                    redeemBasic.setPayable_reward_income(new BigDecimal(0));
                    // 公益收益
                    redeemBasic.setPublic_amount(public_income);
                    // 活期收益
                    redeemBasic.setCurrent_income(current_income);
                }
            }
         }  
         
        // 公益
        map.put("days_extend_income", days_extend_income);
        map.put("extend_income_rate", extend_income_rate);
        // 基本收益信息
        map.put("days_of_basic_income", days_of_basic_income);
        map.put("basic_inceom_rate", basic_inceom_rate);
        // 奖励收益信息
        map.put("reward_income_rate", reward_income_rate);
        // 活期收益
        map.put("days_of_current_income", currentDay);
        map.put("current_income_rate", current_income_rate);
        map.put("current_income", sum_current_income);
        // 拼接年化产品remark
        String remark = cal_redeem_date.get(Calendar.YEAR) + "年" + (cal_redeem_date.get(Calendar.MONTH) + 1) + "月" + map.get("category_name") + "收益";
        map.put("remark", remark);

        map.put("listgridVal", listgridVal);
    }
    
    /**
     * @Title: cal_due_income 
     * @Description: 计算月付收益
     * @author zhangyunfei
     * @date 2016年8月14日 
     */
    public void getMonthDueIncome(Map<String, Object> map,WmsInveRedeemVO wmsInveRedeemVO,WmsInvePruductCategory category) {
        // 老产品自动续期 同月赎回标识为1 其他为0
        int flag = 0;
        getBonus_rate(map, wmsInveRedeemVO, flag);
        BigDecimal due_income = new BigDecimal(0);
        BigDecimal public_income = new BigDecimal(0);

        BigDecimal bonus_rate_income = new BigDecimal(0);
        BigDecimal management_fee = new BigDecimal(0);

        // 公益收益信息
        int days_extend_income = 0;// 公益天数
        BigDecimal extend_income = new BigDecimal(0);// 公益收益
        BigDecimal extend_income_rate = new BigDecimal(0);// 公益利率
        //基本收益信息
        int days_of_basic_income = 0; // 基本收益天数
        BigDecimal basic_inceom_rate = new BigDecimal(0);// 基本利率
        // 奖励收益信息
        BigDecimal reward_income_rate = new BigDecimal(0);// 奖励利率
        int buy_days;
        int incomeDay = 0;
        // 公益收益天数
        int publicDay = 0;
        // 活期收益天数
        int currentDay = 0;
        // 活期收益
        BigDecimal current_income = new BigDecimal(0);
        Calendar cal_current_date = Calendar.getInstance();
        // 单据处于活期标识
        int current_flag = 0;

        // 奖励收益信息
        BigDecimal current_income_rate = new BigDecimal(0);// 活期收益利率
        BigDecimal sum_current_income = BigDecimal.ZERO; // 活期收益总和

        Timestamp buy_time;
        // 赎回时间
        Calendar cal_redeem_date = Calendar.getInstance();
        Date redeem_date = DateUtil.strDate(wmsInveRedeemVO.getRedeem_date_str(), null);
        cal_redeem_date.setTime(redeem_date);

        Calendar pay_date = Calendar.getInstance();
    
        Calendar end_of_date = Calendar.getInstance();
        Date endDate = DateUtil.strDate(map.get("end_of_date").toString(), null);
        
        List<WmsInveRedeemPrincipalDetailSearchBeanVO> listgridVal = JsonUtil.jsonArrayToList(wmsInveRedeemVO.getRedeem_amount_grid(), WmsInveRedeemPrincipalDetailSearchBeanVO.class);

        List<WmsInvePruductReturn> beans;
        if (!DateUtil.before(endDate, redeem_date))
        { // 判断是否理财结束 (未结束)
            // 获取赎回限制表信息
            beans = wmsinvepruductreturnDao.getReturnInfo((int) map.get("wms_inve_pruduct_category_id"));
            buy_time =new Timestamp(DateUtil.strDate(map.get("date_of_payment").toString(), null).getTime());
            buy_days =DateUtil.getBetweenDays((Date) buy_time,redeem_date);
            pay_date.setTime((Date) buy_time);
          
            // 判断赎回日期与购买日期是否在当前月
            if (pay_date.get(Calendar.YEAR) == cal_redeem_date.get(Calendar.YEAR)
                && pay_date.get(Calendar.MONTH) == cal_redeem_date.get(Calendar.MONTH))
            {
                // 续期单据1 为续期 0(针对2015年之前的单据)
                if ("1".equals(wmsInveRedeemVO.getBill_source().toString()))
                {
                    incomeDay = cal_redeem_date.get(Calendar.DAY_OF_MONTH);
                    // 老产品自动续期 同月赎回标识为1
                    flag = 1;
                    getBonus_rate(map, wmsInveRedeemVO, flag);
                }
                else
                {
                    incomeDay = buy_days;
                }
            }else{
                incomeDay = cal_incomeDay(cal_redeem_date.get(Calendar.DAY_OF_MONTH), wmsInveRedeemVO.getOld_date_of_payment());

            }
            days_of_basic_income = incomeDay;//设置基本上收益天数 
            
            getWmsinvepruductreturnInterest(map,1,redeem_date);
            
            for (int i = 0; i < listgridVal.size(); i++)
            {
                WmsInveRedeemPrincipalDetailSearchBeanVO redeemBasic = listgridVal.get(i);

                due_income = new BigDecimal(incomeDay).divide(new BigDecimal(DateUtil.getDaysofMonths(redeem_date)), 8, RoundingMode.HALF_UP).multiply(redeemBasic.getPrincipal_amount().multiply(new BigDecimal(10000))).multiply(new BigDecimal(map.get("product_interest").toString()).divide(new BigDecimal(100), 8, RoundingMode.HALF_UP).divide(new BigDecimal(12), 8, RoundingMode.HALF_UP)).setScale(2, RoundingMode.UP);
                // ***********作废 基本收益
                // payable_basic_income =
                // payable_basic_income.add(due_income).setScale(2,RoundingMode.UP);

                basic_inceom_rate = new BigDecimal(map.get("product_interest").toString()).divide(new BigDecimal(12), 8, RoundingMode.HALF_UP);// 设置基本收益利率信息

                // 奖励收益
                bonus_rate_income = new BigDecimal(map.get("bonus_rate").toString()).divide(new BigDecimal(100), 8, RoundingMode.HALF_UP).multiply(redeemBasic.getPrincipal_amount().multiply(new BigDecimal(10000))).setScale(2, RoundingMode.UP);

                // ***********作废
                // payable_reward_income =
                // payable_reward_income.add(bonus_rate_income).setScale(2,RoundingMode.UP);//设置奖励收益信息

                reward_income_rate = new BigDecimal(map.get("bonus_rate").toString());// 设置奖励利率

                // 原有due_income是基本收益+奖励 现在基本收益/奖励分开 due_income没用
                // due_income =
                // due_income.add(bonus_rate_income).setScale(2,RoundingMode.UP);
                // redeemBasic.setIncome_amount(due_income);

                // 基本收益
                redeemBasic.setPayable_basic_income(due_income);
                // 奖励收益
                redeemBasic.setPayable_reward_income(bonus_rate_income);

                redeemBasic.setPublic_amount(new BigDecimal(0));

                if (map.get("management_fee") != null)
                {
                    management_fee = redeemBasic.getPrincipal_amount().multiply(new BigDecimal(10000)).multiply(new BigDecimal(map.get("management_fee").toString()).divide(new BigDecimal(100), 8, RoundingMode.HALF_UP)).setScale(2, RoundingMode.UP);
                }
                redeemBasic.setManagement_fee(management_fee);
            }
        }
        else
        {
            end_of_date.setTime(endDate);
            // 月付超期当月赎回
            if (end_of_date.get(Calendar.YEAR) == cal_redeem_date.get(Calendar.YEAR) && end_of_date.get(Calendar.MONTH) == cal_redeem_date.get(Calendar.MONTH))
            {
                // 正常收益天数
                incomeDay = end_of_date.get(Calendar.DAY_OF_MONTH);

                days_of_basic_income = incomeDay;// 设置基本收益天数
                // publicDay(公益天数)
                publicDay = DateUtil.getBetweenDays(endDate, redeem_date);
                // 结束天数
                publicDay = cal_incomeDay(publicDay, wmsInveRedeemVO.getOld_date_of_payment());

                days_extend_income = publicDay;// 设置公益收益天数

                for (int i = 0; i < listgridVal.size(); i++)
                {

                    WmsInveRedeemPrincipalDetailSearchBeanVO redeemBasic = listgridVal.get(i);

                    due_income = new BigDecimal(incomeDay).divide(new BigDecimal(DateUtil.getDaysofMonths(redeem_date)), 8, RoundingMode.HALF_UP).multiply(redeemBasic.getPrincipal_amount().multiply(new BigDecimal(10000))).multiply(new BigDecimal(map.get("product_interest").toString()).divide(new BigDecimal(100), 8, RoundingMode.HALF_UP).divide(new BigDecimal(12), 8, RoundingMode.HALF_UP)).setScale(2, RoundingMode.UP);
                    // ***************作废 设置基本收益信息
                    // payable_basic_income=payable_basic_income.add(due_income).setScale(2,RoundingMode.UP);//设置基本收益信息
                    basic_inceom_rate = new BigDecimal(map.get("product_interest").toString()).divide(new BigDecimal(12), 8, RoundingMode.HALF_UP);// 设置基本收益利率信息

                    public_income = new BigDecimal(publicDay).divide(new BigDecimal(365), 8, RoundingMode.HALF_UP).multiply(redeemBasic.getPrincipal_amount().multiply(new BigDecimal(10000))).multiply(category.getCategory_return_rate().divide(new BigDecimal(100), 8, RoundingMode.HALF_UP)).setScale(2, RoundingMode.UP);

                    // ****************作废 设置公益信息
                    extend_income_rate = category.getCategory_return_rate().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);

                    // 奖励收益
                    bonus_rate_income = new BigDecimal(map.get("bonus_rate").toString()).divide(new BigDecimal(100), 8, RoundingMode.HALF_UP).multiply(redeemBasic.getPrincipal_amount().multiply(new BigDecimal(10000))).setScale(2, RoundingMode.UP);

                    // ***************** 作废
                    // payable_reward_income=payable_reward_income.add(bonus_rate_income).setScale(2,RoundingMode.UP);//设置奖励收益信息
                    reward_income_rate = new BigDecimal(map.get("bonus_rate").toString()); // 设置奖励利率
                    // ********基本收益 奖励 分开
                    // due_income =
                    // due_income.add(bonus_rate_income).setScale(2,RoundingMode.UP);
                    // redeemBasic.setIncome_amount(due_income);

                    // 基本收益
                    redeemBasic.setPayable_basic_income(due_income);
                    // 奖励收益
                    redeemBasic.setPayable_reward_income(bonus_rate_income);
                    // 公益收益
                    redeemBasic.setPublic_amount(public_income);
                }

            }
            // 月付超期跨月赎回
            else
            {
                // 判断赎回日是否在到期日的一个月之后 单据处于活期收益期
                if (DateUtil.before(DateUtil.getDatePlusMonths(endDate, 1), redeem_date))
                {
                    // 获取当前生效的活期产品对象
                    WmsInveCurrentRate wmsInveCurrentRate = wmsInveCurrentRateDao.getWmsInveCurrentRateEnable();
                    // 获取活期收益利率
                    if(wmsInveCurrentRate!=null){
                        current_income_rate = wmsInveCurrentRate.getCurrent_rate();
                    }

                    cal_current_date.setTime(DateUtil.getDatePlusMonths(endDate, 1));
                    // 赎回日所在月份和(到期日+一个月)所在月份相同 需要给一部分公益收益+一部分活期收益
                    if (cal_redeem_date.get(Calendar.YEAR) == cal_current_date.get(Calendar.YEAR) && cal_redeem_date.get(Calendar.MONTH) == cal_current_date.get(Calendar.MONTH))
                    {
                        // 公益收益天数
                        publicDay = cal_current_date.get(Calendar.DAY_OF_MONTH);
                        // 活期收益天数=(1号到赎回日天数)-到期天数(当月)
                        currentDay = cal_redeem_date.get(Calendar.DAY_OF_MONTH) - publicDay;
                    }
                    // 赎回日所在月份和(到期日+一个月)所在月份不相同 此时只需要给 活期收益
                    else{
                        // 到期日小于2017-03-01
                        // 给2017-04-01至赎回日的活期收益(公益6给2017-05-01至赎回日的活期收益)
                        if (DateUtil.before(endDate, DateUtil.strDate("2017-03-01", null)))
                        {
                            if (wmsInveRedeemVO.getCategory_name().equals("公益6号"))
                            {
                                currentDay = DateUtil.getBetweenDays(DateUtil.strDate("2017-05-01", null), redeem_date) + 1;
                            }
                            else
                            {
                                currentDay = DateUtil.getBetweenDays(DateUtil.strDate("2017-04-01", null), redeem_date) + 1;
                            }
                        }
                        // 到期日期>=2017-03-01 给赎回日至到期日+一个月的活期收益
                        else
                        {
                            currentDay = DateUtil.getBetweenDays(DateUtil.getDatePlusMonths(endDate, 1), redeem_date);
                        }
                    }
                    // 判断赎回当天是否有收益 无收益需要把currentDay-1
                    currentDay = cal_incomeDay(currentDay, wmsInveRedeemVO.getOld_date_of_payment());
                    // 单据处于活期标识
                    current_flag = 1;
                }
                // 赎回日小于等于到期日+1一个月 这时只给公益收益
                else
                {
                    // publicDay(公益天数)
                    publicDay = cal_redeem_date.get(Calendar.DAY_OF_MONTH);
                    // 结束天数
                    publicDay = cal_incomeDay(publicDay, wmsInveRedeemVO.getOld_date_of_payment());
                }


                days_extend_income = publicDay;// 设置公益天数

                for (int i = 0; i < listgridVal.size(); i++)
                {

                    WmsInveRedeemPrincipalDetailSearchBeanVO redeemBasic = listgridVal.get(i);
                    // 公益收益
                    public_income = new BigDecimal(publicDay).divide(new BigDecimal(365), 8, RoundingMode.HALF_UP).multiply(redeemBasic.getPrincipal_amount().multiply(new BigDecimal(10000))).multiply(category.getCategory_return_rate().divide(new BigDecimal(100), 8, RoundingMode.HALF_UP)).setScale(2, RoundingMode.UP);
                    // 活期收益
                    current_income = new BigDecimal(currentDay).divide(new BigDecimal(365), 8, RoundingMode.HALF_UP).multiply(redeemBasic.getPrincipal_amount().multiply(new BigDecimal(10000))).multiply(current_income_rate.divide(new BigDecimal(100), 8, RoundingMode.HALF_UP)).setScale(2, RoundingMode.UP);
                    // 计算活期收益总和
                    sum_current_income = sum_current_income.add(current_income);
                    // ***************作废 设置公益信息
                    // extend_income=extend_income.add(public_income).setScale(2,RoundingMode.UP);
                    extend_income_rate = category.getCategory_return_rate().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
                    // *********** 作废
                    // redeemBasic.setIncome_amount(new BigDecimal(0));

                    // 基本收益
                    redeemBasic.setPayable_basic_income(new BigDecimal(0));
                    // 奖励收益
                    redeemBasic.setPayable_reward_income(new BigDecimal(0));
                    // 公益收益
                    redeemBasic.setPublic_amount(public_income);
                    // 活期收益
                    redeemBasic.setCurrent_income(current_income);
                }
            }
        }
        //公益
        map.put("days_extend_income", days_extend_income);
        map.put("extend_income", extend_income);
        map.put("extend_income_rate", extend_income_rate);
        //基本收益信息
        map.put("days_of_basic_income", days_of_basic_income);
        //map.put("payable_basic_income", payable_basic_income);
        map.put("basic_inceom_rate", basic_inceom_rate);
        // 奖励收益信息
        map.put("reward_income_rate", reward_income_rate);
        //map.put("payable_reward_income", payable_reward_income);
        
        // map.put("due_income", due_income);
        // map.put("income_day", incomeDay+publicDay); //收益天数
        // 活期收益
        map.put("days_of_current_income", currentDay);
        map.put("current_income_rate", current_income_rate);
        map.put("current_income", sum_current_income);

        //拼接remark字符串 月付(日期+正常收益,日期+奖励)
        String remark = cal_redeem_date.get(Calendar.YEAR) + "年" + (cal_redeem_date.get(Calendar.MONTH) + 1) + "月客户正常收益";
        //拼接后的remark 放到map中
        map.put("remark", remark + map.get("remark"));
        map.put("listgridVal", listgridVal);
    }
    
    
    /**
     * 赎回---理财协议保存 baisong
     */
    @Override
    @Transactional
    public String saveBack(WmsInveTransaProtocol bean, UserBean user, WmsInveDebtWorkFlowVO wDebtWorkFlowVO)
    {
        String resStr = "success";
        int ret = 0;
        setUser(bean, user);// 生成账号密码
        int idval = bean.getWms_inve_redeem_id();
        WmsInveTransaProtocol updatebean = new WmsInveTransaProtocol();
        updatebean.setWms_inve_redeem_id(bean.getWms_inve_redeem_id());
        updatebean.setWms_inve_transa_id(bean.getWms_inve_transa_id());
        updatebean.setWms_inve_transa_prod_id(bean.getWms_inve_transa_prod_id());
        updatebean.setLast_update_timestamp(new Timestamp(new Date().getTime()));
        ret = wmsinvetransaprotocolDao.updateforback(updatebean);

        bean.setWms_inve_redeem_id(0);
        // ***********判断合同是否发生并发现象****************
        List<WmsInveTransaProtocol> protocollist = wmsinvetransaprotocolDao.getListByEntityIsNull(bean);
        if (protocollist.size() > 0)
        {
            resStr = "exist";
            return resStr;
        }
        // ******************************************
        bean.setCreate_user_id(user.getUserId());
        bean.setCreate_user_name(user.getRealname());
        bean.setEnable_flag("1");
        bean.setCreate_timestamp(new Timestamp(new Date().getTime()));
        bean.setLast_update_timestamp(new Timestamp(new Date().getTime()));
        ret = wmsinvetransaprotocolDao.save(bean);
        //把协议id赋值到债权匹配表 2015-12-4
        /*WmsInveTransaMatch wMatch = new WmsInveTransaMatch();
        wMatch.setWms_inve_transa_prod_id(bean.getWms_inve_transa_prod_id());
        List<WmsInveTransaMatch> wmsInveTransaMatchlist=wmsinvetransamatchDao.getListByEntity(wMatch);
        for(WmsInveTransaMatch wmsInveTransaMatch:wmsInveTransaMatchlist){
            wmsInveTransaMatch.setWms_inve_transa_protocol_id(bean.getWms_inve_transa_protocol_id());//协议表id
            wmsinvetransamatchDao.update(wmsInveTransaMatch);//更新
        }*/
        //债权匹配变增加协议ID
        Map<String, Object> matchMap = new HashMap<String, Object>();
        matchMap.put("wms_inve_transa_protocol_id", bean.getWms_inve_transa_protocol_id());
        matchMap.put("wms_inve_transa_prod_id", bean.getWms_inve_transa_prod_id());
        wmsInveTransaMatchDao.updateAgreementIdForMatch(matchMap);

        WmsInveTransa wmsinvetransa = new WmsInveTransa();
        wmsinvetransa.setWms_inve_transa_id(bean.getWms_inve_transa_id());
        wmsinvetransa.setData_status("4");
        wmsinvetransaDao.updateInve_transaForJEZF(wmsinvetransa);
        bean.setWms_inve_redeem_id(idval);
        WmsInveRedeemDetail wmsInveRedeemDetail = new WmsInveRedeemDetail();
        wmsInveRedeemDetail.setWms_inve_redeem_id(idval);
        wmsInveRedeemDetail.setIs_fully_redeemed("0");
        wmsInveRedeemDetail.setWms_inve_transa_id(bean.getWms_inve_transa_id());
        wmsInveRedeemDetail.setWms_inve_transa_prod_id(bean.getWms_inve_transa_prod_id());
        WmsInveRedeemDetail detail = wmsinveredeemdetailDao.getOnebypk(wmsInveRedeemDetail);
        detail.setIs_protocol_finish("1"); // 设置合同已经打印成功
        wmsinveredeemdetailDao.update(detail);
        wmsInveRedeemDetail.setWms_inve_transa_id(null);
        wmsInveRedeemDetail.setWms_inve_transa_prod_id(null);
        List<WmsInveRedeemDetail> listDetail = wmsinveredeemdetailDao.getListByEntity(wmsInveRedeemDetail);
        for (int i = 0; i < listDetail.size(); i++)
        {
            wmsInveRedeemDetail = listDetail.get(i);
            if (wmsInveRedeemDetail.getIs_protocol_finish().equals("0"))
            {
                break;
            }
            if (i == listDetail.size() - 1)
            {
                WmsInveRedeem wmsInveRedeem = new WmsInveRedeem();
                wmsInveRedeem.setWms_inve_redeem_id(idval);
                wmsInveRedeem.setIs_protocol_finish("1");
                wmsInveRedeemDao.update(wmsInveRedeem);
            }
        }

        WmsInveRedeemDetail wmsInveRedeemDetailSearch = new WmsInveRedeemDetail();
        wmsInveRedeemDetailSearch.setWms_inve_redeem_id(bean.getWms_inve_redeem_id());
        List<WmsInveRedeemDetail> wmsInveRedeemDetailList = wmsinveredeemdetailDao.getListByEntity(wmsInveRedeemDetailSearch);
        List<String> wmsInveTransaIdList = new ArrayList<>();
        for (int i = 0; i < wmsInveRedeemDetailList.size(); i++)
        {
            // 赎回明细中对应的单据完全赎回，则更新匹配和协议的赎回单据id
            WmsInveRedeemDetail redeemDetail = wmsInveRedeemDetailList.get(i);
            if (redeemDetail.getIs_fully_redeemed().equals("1"))
            {
                wmsInveTransaIdList.add(redeemDetail.getWms_inve_transa_id().toString());
            }

        }
        WmsInveRedeem wmsInveRedeem = wmsInveRedeemDao.get(idval);
        //打印协议后，查看回款是否完成  债权是否完成  协议已经打印成功
        if (wmsInveRedeem.getPayback_date() != null && wmsInveRedeem.getIs_payback().equals("1") &&wmsInveRedeem.getIs_finish().equals("1") && wmsInveRedeem.getIs_protocol_finish().equals("1"))
        {
            wDebtWorkFlowVO.setBusinessId("5");
            wDebtWorkFlowVO.setUserID(user.getUserId().toString());
            wDebtWorkFlowVO.setwInveTransaIds(wmsInveTransaIdList);
            aInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
        }
        if (ret == 0)
        {
            resStr = "error";
        }
        //保存客户收益信息和收益日志信息
        //resStr = getSyUpdate(bean, user, detail);
        resStr = handleIncomeAndLogInfoSH(bean, user);
        return resStr;
    }

    /**
     * 实现保存(客户赎回后)客户收益信息和收益日志信息方法
     * 
     * @param bean
     * @param user
     * @param wmsInveRedeemDetail
     * @return
     */
    @Transactional
    public String getSyUpdate(WmsInveTransaProtocol bean, UserBean user, WmsInveRedeemDetail wmsInveRedeemDetail)
    {
        WmsInveTransaProd wmsInveTransaProd = wmsInveTransaProdDao.get(bean.getWms_inve_transa_prod_id());
        // 获取日志的基本信息
        WmsInveTransaLog wmsInveTransaLog = new WmsInveTransaLog();
        wmsInveTransaLog.setWms_inve_transa_id(bean.getWms_inve_transa_id());
        // 根据上单信息表主键获取正常获得收益日志的信息
        List<WmsInveTransaLog> wmsInveTransaLogList = wmsInveTransaLogDao.getListByEntity(wmsInveTransaLog);
        wmsInveTransaLog.setCreate_user_id(user.getUserId());
        wmsInveTransaLog.setCreate_user_name(user.getRealname());
        wmsInveTransaLog.setCreate_timestamp(new Timestamp(new Date().getTime()));
        wmsInveTransaLog.setLast_update_timestamp(new Timestamp(new Date().getTime()));
        wmsInveTransaLog.setChange_date(new java.sql.Date(new Date().getTime()));
        wmsInveTransaLog.setOperate_item("提前赎回");

        // 根据客户上单信息表主键和上单产品表主键获取 属于该可以的相关正常收益信息
        WmsInveTransaIncome wmsInveTransaIncome = new WmsInveTransaIncome();
        wmsInveTransaIncome.setWms_inve_transa_id(bean.getWms_inve_transa_id());
        wmsInveTransaIncome.setWms_inve_transa_prod_id(bean.getWms_inve_transa_prod_id());
        List<WmsInveTransaIncome> incomeList = wmsInveTransaIncomeDao.getListByEntity(wmsInveTransaIncome);

        // 根据上单产品主键获取产品奖励利率信息
        List<WmsInveTransaProdReward> wmsInveTransaProdRewardList = wmsInveTransaProdRewardDao.selectForSy(bean.getWms_inve_transa_prod_id());

        // 赎回后 剩余的金额
        BigDecimal account = wmsInveRedeemDetail.getProduct_account().subtract(wmsInveRedeemDetail.getRedeem_amount());

        // 期数标识
        int monthFlag = 0;
        int num = 0;
        if (incomeList.size() > wmsInveTransaProdRewardList.size())
        {
            num = 1;
        }
        int sum = -1;

        for (int i = 0; i < incomeList.size(); i++)
        {
            boolean val = false;
            wmsInveTransaIncome = (WmsInveTransaIncome) incomeList.get(i);
            WmsInveTransaIncome wmsInveTransaIncome1 = new WmsInveTransaIncome();
            if (i == 0)
            {
                if ((!wmsInveTransaIncome.getReturn_date().before(new Date())))
                {
                    val = true;
                }
            }
            else if (i > 0)
            {
                wmsInveTransaIncome1 = (WmsInveTransaIncome) incomeList.get(i - 1);
                if ((!wmsInveTransaIncome.getReturn_date().before(new Date()))
                    && (wmsInveTransaIncome1.getReturn_date().before(new Date())))
                {
                    val = true;
                }
            }
            // 获取奖励利率
            BigDecimal rewardInterest = new BigDecimal(0);
            if (incomeList.size() > bean.getProduct_deadline())
            {// 如果查询出来的条数大于期数说明不是整月理财
                if (i == 7)
                {
                    rewardInterest = wmsInveTransaProdRewardList.get(0).getReward_interest();
                }
                else if (i == 13)
                {
                    rewardInterest = wmsInveTransaProdRewardList.get(1).getReward_interest();
                }
            }
            else
            {
                if (i == 6)
                {
                    rewardInterest = wmsInveTransaProdRewardList.get(0).getReward_interest();
                }
                else if (i == 12)
                {
                    rewardInterest = wmsInveTransaProdRewardList.get(1).getReward_interest();
                }
            }
            // 获取相关信息 记录到日志
            WmsInveTransaLog transaLog = new WmsInveTransaLog();
            if (val)
            {
                for (int j = 0; j < wmsInveTransaLogList.size(); j++)
                {
                    transaLog = wmsInveTransaLogList.get(j);
                    // 返回结果(0--相同 1--前者大 2--后者大)
                    int bb = compareDate(transaLog.getChange_date(), wmsInveTransaIncome.getReturn_date());
                    if (bb == 0)
                    {// 如果日志的日期等于收益的日期
                        sum = j;
                        break;
                    }
                }
                // 月利率+奖励利率
                BigDecimal interest = new BigDecimal(wmsInveTransaProd.getProduct_interest())
                                                       .divide(new BigDecimal(12), 8, BigDecimal.ROUND_DOWN)
                                                       .add(rewardInterest);

                BigDecimal account_interest = new BigDecimal(0);
                if (account.compareTo(new BigDecimal(0)) > 0)
                {
                    // 赎回后,剩余本金可获得每月收益
                    account_interest = account.multiply(interest).divide(new BigDecimal(100), 0, BigDecimal.ROUND_DOWN);
                }
                // 当月一天的利率
                // BigDecimal day_interest
                // =wmsInveTransaProd.getProduct_interest().divide(new
                // BigDecimal(getDateInt(new
                // Date())*12),8,BigDecimal.ROUND_DOWN);
                BigDecimal day_interest = new BigDecimal(wmsInveTransaProd.getProduct_interest())
                                                           .divide(new BigDecimal(100).multiply(new BigDecimal(360)),
                                                                   8, BigDecimal.ROUND_DOWN);
                int daynum = 0;
                if (i > 0)
                {
                    daynum = calInterval(wmsInveTransaIncome1.getReturn_date(), new Date(), "d");// 协议支付日期bean.getDate_of_payment()
                }
                else if (i == 0)
                {
                    daynum = calInterval(wmsInveTransaLogList.get(0).getChange_date(), new Date(), "d");
                }
                // 赎回金额当月获得的利息
                // BigDecimal
                // redeem_amount_interest=wmsInveRedeemDetail.getRedeem_amount().multiply(day_interest.multiply(new
                // BigDecimal(daynum)).divide(new
                // BigDecimal(100),2,BigDecimal.ROUND_DOWN));
                // 获取当月赎回的收益=赎回金额*每天的利率*符合的天数
                BigDecimal redeem_amount_interest = (wmsInveRedeemDetail.getRedeem_amount().multiply(day_interest).multiply(new BigDecimal(
                                                                                                                                           daynum))).divide(new BigDecimal(
                                                                                                                                                                           1),
                                                                                                                                                            0,
                                                                                                                                                            BigDecimal.ROUND_HALF_UP);
                wmsInveTransaIncome.setProduct_interest_account(account_interest);
                wmsInveTransaIncomeDao.update(wmsInveTransaIncome);
                // 日志表记录当月的收益
                transaLog = wmsInveTransaLogList.get(sum);
                transaLog.setProduct_interest_account(wmsInveTransaIncome.getProduct_interest_account());// 赎回当月的收益
                wmsInveTransaLogDao.update(transaLog);// 保存日志表
                // transaLog.setProduct_account(wmsInveRedeemDetail.getRedeem_amount());//赎回的本金变化
                // 赎回产品应收管理费
                BigDecimal glf = wmsInveRedeemDetail.getRedeem_amount()
                                                    .multiply(wmsInveRedeemDetail.getManagement_fee_rate())
                                                    .divide(new BigDecimal(100), 2, BigDecimal.ROUND_DOWN);
                // 赎回金额---应得本金
                wmsInveTransaLog.setProduct_account(wmsInveRedeemDetail.getRedeem_amount());
                // 赎回金额的收益
                wmsInveTransaLog.setProduct_interest_account(redeem_amount_interest);
                // 日志表保存收益和赎回金额等 管理费等
                wmsInveTransaLog.setRemark("赎回金额的收益:"
                                           + redeem_amount_interest.divide(new BigDecimal(1), 2, BigDecimal.ROUND_DOWN)
                                           + "(客户收取) " + "赎回金额的管理费:" + glf + "元(客户支出)" + "关联协议编号 &lt;"
                                           + bean.getProt_code() + "&gt;" + "#" + bean.getWms_inve_transa_protocol_id()
                                           + "#" + bean.getWms_inve_transa_id() + "#"
                                           + bean.getWms_inve_transa_prod_id() + "#" + bean.getWms_inve_redeem_id());

            }
            else if (wmsInveTransaIncome.getReturn_date().after(new Date()))
            {
                // 赎回后 剩余的金额
                // BigDecimal
                // account=wmsInveRedeemDetail.getProduct_account().subtract(wmsInveRedeemDetail.getRedeem_amount());
                if (i == incomeList.size() - 1)
                {
                    // 相差天数
                    int daynum = calInterval(wmsInveTransaIncome1.getReturn_date(),
                                             wmsInveTransaIncome.getReturn_date(), "d");
                    // 月利率
                    BigDecimal roduct_interest = new BigDecimal(wmsInveTransaProd.getProduct_interest()).divide(new BigDecimal(12), 8,
                                                                                                BigDecimal.ROUND_DOWN);
                    // 当月一天的利率
                    BigDecimal day_interest = roduct_interest.divide(new BigDecimal(
                                                                                    getDateInt(wmsInveTransaIncome.getReturn_date())),
                                                                     8, BigDecimal.ROUND_DOWN);
                    // 最后一个月的总收益
                    BigDecimal zlxi = day_interest.multiply(account).multiply(new BigDecimal(daynum))
                                                  .add(account.multiply(rewardInterest));
                    wmsInveTransaIncome.setProduct_interest_account(zlxi.divide(new BigDecimal(100), 0,
                                                                                BigDecimal.ROUND_DOWN));
                    // 日志表
                    transaLog = wmsInveTransaLogList.get(sum);
                    transaLog.setProduct_interest_account(wmsInveTransaIncome.getProduct_interest_account());// 赎回当月的收益
                    transaLog.setProduct_account(account);// 本金的变化 到期 剩余金额回归
                }
                else
                {
                    // 剩余部分的奖励利率
                    BigDecimal interest = new BigDecimal(wmsInveTransaProd.getProduct_interest())
                                                           .divide(new BigDecimal(12), 8, BigDecimal.ROUND_DOWN)
                                                           .add(rewardInterest);
                    // 赎回后 剩余的金额
                    // BigDecimal
                    // account=wmsInveRedeemDetail.getProduct_account().subtract(wmsInveRedeemDetail.getRedeem_amount());
                    wmsInveTransaIncome.setProduct_interest_account(account.multiply(interest)
                                                                           .divide(new BigDecimal(100), 0,
                                                                                   BigDecimal.ROUND_DOWN));
                    // 日志表
                    transaLog = wmsInveTransaLogList.get(sum);
                    transaLog.setProduct_interest_account(wmsInveTransaIncome.getProduct_interest_account());// 赎回当月的收益
                }
                wmsInveTransaIncomeDao.update(wmsInveTransaIncome);
                wmsInveTransaLogDao.update(transaLog);// 保存日志表
            }
            sum += 1;
            monthFlag++;
        }
        wmsInveTransaLogDao.save(wmsInveTransaLog);
        return "success";
    }

    private int getDateInt(Date cdate)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(cdate);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);// 获取当月天数
        return lastDay;
    }

    @Override
    public Map<String, Object> getProtocolData(String checkedInveTransaIDs, String wms_inve_redeem_id)
    {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Calendar today = Calendar.getInstance();
        String[] idArr = checkedInveTransaIDs.split(",");
        // 获取当月的倒数第2天
        paramMap.put("prompt_date", today.getActualMaximum(Calendar.DAY_OF_MONTH) - 1);

        for (int i = 0; i < idArr.length; i++)
        {
            paramMap.put("wms_inve_transa_id", idArr[i]);
            // 获取未赎回过的
            paramMap.put("wms_inve_redeem_id", 0);
            Map<String, Object> map = wmsinvetransaprotocolDao.getProtocolData(paramMap);
            if (map == null)
            {
                // 获取赎回过的
                paramMap.put("wms_inve_redeem_id", wms_inve_redeem_id);
                map = wmsinvetransaprotocolDao.getProtocolData(paramMap);
            }
            list.add(map);
        }
        paramMap.put("protocolInfo", list);
        return paramMap;
    }

    /**
     * 生成a-z 0-10的数组
     * 
     * @return char[]
     */
    public char[] getChar()
    {
        char[] cLit = new char[62];
        char mword = 'a';
        char bword = '0';
        for (int i = 0; i < 36; i++)
        {
            if (i < 26)
            {
                cLit[i] = mword;
                mword++;
            }
            else
            {
                cLit[i] = bword;
                bword++;
            }
        }
        return cLit;
    }

    /**
     * 自动生成账号密码
     * 
     * @author baisong 使用方法 getString("username",6) 第一个参数是只能输入 username 或者
     *         username 第一个参数是代表输入的位数
     */
    public String getString(String sb, int number)
    {
        String result = "";
        char[] r = getChar();
        Random rr = new Random();
        char[] ch = new char[number];
        for (int i = 0; i < number; i++)
        {
            int num = rr.nextInt(36);
            ch[i] = r[num];
        }
        if (sb.equals("username"))
        {
            StringBuilder username = new StringBuilder();
            username.append("88");
            result = username.append(ch).toString();
        }

        if (sb.equals("password"))
        {
            StringBuilder password = new StringBuilder();
            result = password.append(ch).toString();
        }

        return result;
    }

    /**
     * 自动生成外网账号密码
     * 
     * @param bean
     * @param user
     */
    @Transactional
    private void setUser(WmsInveTransaProtocol bean, UserBean user)
    {
        // 根据用户id查询是否存在用户名密码
        List<SysWebUser> sysWebUserlist = syswebuserDao.getById(bean.getB_id_card());
        if (sysWebUserlist.size() > 0)
        {
            return;
        }
        // 根据用户名查询是否存在用户名避免重复
        SysWebUser sysWebUser = syswebuserDao.getByUserName(getString("username", 6));
        while (sysWebUser != null)
        {
            sysWebUser = syswebuserDao.getByUserName(getString("username", 6));
        }
        WmsInveTransa wmsInveTransa = wmsinvetransaDao.get(bean.getWms_inve_transa_id());
        String password = getString("password", 6);// 密码
        String username = wmsInveTransa.getMobile_phone();// 用户名
        SysWebUser sys = new SysWebUser();
        sys.setUser_name(username);
        sys.setPassword(password);
        sys.setReal_name(bean.getB_name());
        sys.setId_card(bean.getB_id_card());
        sys.setCreate_timestamp(new Timestamp(new Date().getTime()));
        sys.setCreate_user_id(user.getUserId());
        sys.setCreate_user_name(user.getRealname());
        sys.setCreate_user_name(sys.getCreate_timestamp_str());
        sys.setLast_update_user_id(sys.getLast_update_user_id());
        sys.setEnable_flag("1");
        syswebuserDao.save(sys);
        // 发送短信暂时不需要 baisong20141114
        String srcStr = "您可以次日通过手机查看实时债权匹配情况。网站地址 " +PlatformGlobalVar.SYS_PROPERTIES.get("ekp_login_url_address")+"   登陆账号："+username+"，密码："+password;
        //sendMsg(wmsInveTransa.getMobile_phone(), srcStr);
        
        /*MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>(); form.add("interface_num", "SMS_OUT_001");
        form.add("sys_num", "ZSH"); form.add("user_id", "0");
        form.add("mobile", wmsInveTransa.getMobile_phone()); 
        String srcStr = "您可以通过手机查看实时债权匹配情况。网站地址 " +PlatformGlobalVar.SYS_PROPERTIES.get("ekp_login_url_address")+"登陆账号："+username+"，密码："+password; 
        try { 
            form.add("content", srcStr); 
        } catch (Exception e)  { 
            e.printStackTrace(); 
        }
        form.add("num", ""); 
        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> temp = restTemplate.postForObject(PlatformGlobalVar.SYS_PROPERTIES.get("ekp_login_url")+"/modi/restful/simple", form, Map.class);
        */
    }
    
    private void sendMsg(String mobile, String content) {
        HttpPost httpPost = new HttpPost(PlatformGlobalVar.SYS_PROPERTIES.get("ekp_login_url") + "/modi/restful/simple");
         List<NameValuePair> nvps = new ArrayList<NameValuePair>();
         nvps.add(new BasicNameValuePair("interface_num", "SMS_OUT_001"));
         nvps.add(new BasicNameValuePair("sys_num", "ZSH"));
         nvps.add(new BasicNameValuePair("user_id", "0"));
        
         nvps.add(new BasicNameValuePair("mobile", mobile));
         nvps.add(new BasicNameValuePair("content", content));
         nvps.add(new BasicNameValuePair("num", ""));
         
         /*Map<String, Object> mm = new HashMap<String, Object>();
         
         mm.put("fullname", "孙立强");
         mm.put("company", "互联网中心/IT部");
         mm.put("aa", "aa");
         Gson gson = new GsonBuilder().create();
         String jon = gson.toJson(mm, mm.getClass());
         nvps.add(new BasicNameValuePair("param", jon));*/
         CloseableHttpClient httpclient = null;
         CloseableHttpResponse response = null;
         
         try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            httpclient = HttpClients.createDefault();
            response = httpclient.execute(httpPost);

            // do something useful with the response body
            HttpEntity entity = response.getEntity();
            // String str = EntityUtils.toString(entity);
            // System.out.println(str);
            // and ensure it is fully consumed
            EntityUtils.consume(entity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                response.close();
                httpclient.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        

    }

    /**
     * 根据合同编号查询合同内容 baisong
     */
    @Override
    public Map<String, Object> getInfoByCode(String prot_code)
    {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = wmsinvetransaprotocolDao.getInfoByCode(prot_code);
        map = list.get(0);
        BigDecimal bd = (BigDecimal) map.get("product_account");
        map.put("product_account", getDecimalFormat(bd));// 上单金额
        return map;
    }

    public String getDecimalFormat(BigDecimal str)
    {
        String outStr = "";
        if (str.compareTo(new BigDecimal(0)) > 0)
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

    /**
     * 不启用2015-01-26 wms_inve_transa_id = $.query.get('wms_inve_transa_id');
     * wms_inve_transa_prod_id = $.query.get('wms_inve_transa_prod_id');查询合同编号
     * baisong
     */
    /*
     * @Override public Map<String, Object> getCodeById(Integer
     * wms_inve_transa_prod_id,Integer wms_inve_transa_id,Integer
     * wms_inve_redeem_id) { // Map<String, Object> map=new HashMap<>();
     * Map<String,Object> paramMap =new HashMap();
     * paramMap.put("wms_inve_transa_prod_id", wms_inve_transa_prod_id);
     * paramMap.put("wms_inve_transa_id", wms_inve_transa_id);
     * if(wms_inve_redeem_id==null){ wms_inve_redeem_id=0; }
     * paramMap.put("wms_inve_redeem_id", wms_inve_redeem_id); List<Map<String,
     * Object>> list=wmsinvetransaprotocolDao.getCodeById(paramMap); Map<String,
     * Object> map=list.get(0); return map; }
     */
    /**
     * 根据协议主键 查询出相应的协议数据
     */
    @Override
    public Map<String, Object> getWmsInveTransaPro(Integer wms_inve_transa_protocol_id)
    {   Map<String, Object> map=   wmsinvetransaprotocolDao.get(wms_inve_transa_protocol_id).getInfoMap();
        BigDecimal bd = (BigDecimal) map.get("product_account");
        map.put("product_account_lower", getDecimalFormat(bd));// 上单金额
        return map;
    }
    /**
     * 暂时实现金额支付阶段  直接能够计算收益以及日志信息
     * @param bean
     * @param user
     */
    @Override
    public void SethandleIncomeAndLogInfo(WmsInveTransaProtocol bean,UserBean user){
        this.handleIncomeAndLogInfo(bean, user);
    }
    /**
     * 暂时实现金额支付阶段  直接能够计算收益以及日志信息赎回
     * @param bean
     * @param user
     */
    @Override
    public void SethandleIncomeAndLogInfoSH(WmsInveTransaProtocol bean,
 UserBean user)
    {
        this.handleIncomeAndLogInfoSH(bean, user);
    }
    /**
     * Description :理财协议签订  新 假设   先定义此方法
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author hancd
     * @date 2015年12月16日 14:18
     */
    @Override
    public String toSave(WmsInveTransaProtocol bean, UserBean user,WmsInveDebtWorkFlowVO workFlowVO) {
        String result = "success";
        WmsInveTransa wmsInveTransa = wmsinvetransaDao.get(bean.getWms_inve_transa_id());
        WmsInveTransaProd wmsTransaProd = wmsInveTransaProdDao.get(bean.getWms_inve_transa_prod_id());
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        
        WmsInveTransaProd wmsInveTransaProd = new WmsInveTransaProd();
        wmsInveTransaProd.setWms_inve_transa_prod_id(bean.getWms_inve_transa_prod_id());
        wmsInveTransaProd.setIs_protocol_finish("1");
        wmsInveTransaProdDao.update(wmsInveTransaProd);
        //调用流程
        workFlowVO.setBusinessId(wmsInveTransa.getWms_inve_transa_id().toString());
        workFlowVO.setPass("true");
        workFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
        workFlowVO.setDebtkey("4");
        workFlowVO.setUserID(user.getUserId().toString());
        wmsInveWorkFlowService.publicApproval(workFlowVO);
        return result;
    }
    /**
     * 设置日期 参数int i是为了方法的公共使用 主要的目的就是计算的月份上有差异对月份进行加减
     * 
     * @param wmsFinaCreLoanApp
     * @return
     */
    private java.sql.Date setDatebyCalendar(Date sDate, int i)
    {

        java.sql.Date date1;
          Calendar  calendar=new GregorianCalendar();   
          calendar.setTime(sDate);
          calendar.add(Calendar.MONTH, +i);
          java.util.Date date_=calendar.getTime();
          date1=new java.sql.Date(date_.getTime()); 
          return date1;
    }
    
    public static void main(String[] main){
        DateFormat ddf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            int i = DateUtil.getBetweenDays(ddf.parse("2015-05-10"), ddf.parse("2015-06-02"));
            System.out.println(i);
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 获取当前日期
    private java.sql.Date getNow()
    {
        Calendar nowcalendar = Calendar.getInstance();
        return new java.sql.Date(nowcalendar.getTimeInMillis());
    }
}