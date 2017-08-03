package com.zx.emanage.inve.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInvePruductCategoryDao;
import com.zx.emanage.inve.persist.WmsInvePruductRebateWayDao;
import com.zx.emanage.inve.persist.WmsInvePruductYearPaySpecialDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.persist.WmsInveTransaIncomeDao;
import com.zx.emanage.inve.persist.WmsInveTransaLogDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.inve.persist.WmsInveTransaProtocolDao;
import com.zx.emanage.inve.persist.WmsInveTransaUpdateLogDao;
import com.zx.emanage.inve.service.IWmsInveTransaProtocolService;
import com.zx.emanage.inve.service.IWmsInveTransaUpdateLogService;
import com.zx.emanage.inve.util.CountIncome;
import com.zx.emanage.inve.util.CountIncomeFactory;
import com.zx.emanage.inve.vo.WmsInveTransaUpdateLogSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.emanage.util.gen.entity.WmsInveTransaUpdateLog;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvetransaupdatelogService")
public class WmsInveTransaUpdateLogServiceImpl implements IWmsInveTransaUpdateLogService {
	private static Logger log = LoggerFactory.getLogger(WmsInveTransaUpdateLogServiceImpl.class);

	@Autowired
	private WmsInveTransaUpdateLogDao wmsinvetransaupdatelogDao;
	@Autowired
    private WmsInveTransaProdDao wmsinvetransaprodDao;
	@Autowired
    private WmsInveTransaDao wmsinvetransaDao;
	
	@Autowired
    private WmsInveTransaProdDao wmsInveTransaProdDao;
    @Autowired
    private WmsInvePruductCategoryDao wmsInvePruductCategoryDao;// 产品信息表
    @Autowired
    private WmsInvePruductRebateWayDao wmsInvePruductRebateWayDao;
    @Autowired
    private WmsInveTransaProtocolDao wmsinvetransaprotocolDao;
    @Autowired
    WmsInveTransaIncomeDao wmsInveTransaIncomeDao;
    @Autowired
    WmsInveTransaLogDao wmsInveTransaLogDao;
    @Autowired
	private WmsInvePruductYearPaySpecialDao wmsInvePruductYearPaySpecialDao;
    @Autowired
    private IWmsInveTransaProtocolService wmsInveTransaProtocolService;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveTransaUpdateLogSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvetransaupdatelogDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveTransaUpdateLogSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvetransaupdatelogDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvetransaupdatelogDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveTransaUpdateLog getInfoByPK(Integer wms_inve_transa_update_log_id) {
		return wmsinvetransaupdatelogDao.get(wms_inve_transa_update_log_id);
	}
	
	@Override	
	@Transactional
    public String save(WmsInveTransaUpdateLog bean, UserBean user, Integer product_deadlinel)
    {
        String resStr = "success";
        int ret = 0;
        // 修改上单产品信息表
        WmsInveTransaProd wmsInveTransaProd = new WmsInveTransaProd();
        wmsInveTransaProd.setWms_inve_transa_id(bean.getWms_inve_transa_id());
        wmsInveTransaProd.setCreate_timestamp(bean.getChange_single_time());
        wmsinvetransaprodDao.update(wmsInveTransaProd);
        // 修改上单信息表
        WmsInveTransa wmsInveTransa = new WmsInveTransa();
        wmsInveTransa.setWms_inve_transa_id(bean.getWms_inve_transa_id());
        wmsInveTransa.setCreate_timestamp(bean.getChange_single_time());
        // jinzm 修改，支付日期都设置成上单日期
        // wmsInveTransa.setDate_of_payment(bean.getChange_date_of_payment());
        wmsInveTransa.setDate_of_payment(new Date(bean.getChange_single_time().getTime()));
        wmsinvetransaDao.update(wmsInveTransa);
        // 修改上单协议表
        WmsInveTransaProtocol wmsInveTransaProtocol = new WmsInveTransaProtocol();
        wmsInveTransaProtocol.setWms_inve_transa_id(bean.getWms_inve_transa_id());
        // 查询有效协议：每张理财单据存在系统中的有效协议只存在一条
        wmsInveTransaProtocol.setWms_inve_redeem_id(0);
        List<WmsInveTransaProtocol> list = wmsinvetransaprotocolDao.getListByEntity(wmsInveTransaProtocol);
        if (list != null && list.size() > 0)
        {
            WmsInveTransaProtocol wmsInveTransaProtocol2 = list.get(0);
            wmsInveTransaProtocol.setWms_inve_transa_id(null);
            wmsInveTransaProtocol.setWms_inve_transa_protocol_id(wmsInveTransaProtocol2.getWms_inve_transa_protocol_id());
            // jinzm 修改，支付日期都设置成上单日期
            // wmsInveTransaProtocol.setDate_of_payment(bean.getChange_date_of_payment());
            wmsInveTransaProtocol.setDate_of_payment(new Date(bean.getChange_single_time().getTime()));
            // jinzhiming修改，修改日期后设置end_of_date的时候没有考虑到2010-6-07-01或之后的单据问题
            // wmsInveTransaProtocol.setEnd_of_date((java.sql.Date)
            // DateUtil.setDatebyCalendar(bean.getChange_date_of_payment(),
            // product_deadlinel));
            // if(bean.getChange_date_of_payment().compareTo(DateUtil.strDate(CountIncome.MAGIC_DATE_OF_PAYMENT_STR,
            // null))>=0){
            // 查询上单单据信息
            wmsInveTransa = wmsinvetransaDao.get(bean.getWms_inve_transa_id());
            // 获得上单单据的原始上单日期，如果没有原始上单日期就是null
            java.util.Date oldDateOfPayment = StringUtil.isEmpty(wmsInveTransa.getDate_of_payment_str()) ? null
                                                                                                        : DateUtil.strDate(wmsInveTransa.getDate_of_payment_str(),
                                                                                                                           null);
            
            // 要修改成的支付时间和原始上单支付时间都大于等于2016-07-01的时候，才给到期时间-1天
            if (DateUtil.getDate10(new Date(bean.getChange_single_time().getTime()))
                        .compareTo(DateUtil.strDate(CountIncome.MAGIC_DATE_OF_PAYMENT_STR, null)) >= 0
                && oldDateOfPayment != null
                && oldDateOfPayment.compareTo(DateUtil.strDate(CountIncome.MAGIC_DATE_OF_PAYMENT_STR, null)) >= 0)
            {
                // 将支付日期向后移月份为产品期限月份，之后因为是2016-07-01或之后的单据，需要-1天，之后将util.Date转成sqlDate
                wmsInveTransaProtocol.setEnd_of_date(com.zx.platform.syscontext.util.DateUtil.utilDate2SqlDate(DateUtil.getDateAddDays(DateUtil.setDatebyCalendar(DateUtil.getDate10(new Date(
                                                                                                                                                                                              bean.getChange_single_time()
                                                                                                                                                                                                  .getTime())),
                                                                                                                                                                  product_deadlinel),
                                                                                                                                       -1)));
            }
            else
            {
                // 将支付日期向后移月份为产品期限月份
                wmsInveTransaProtocol.setEnd_of_date(com.zx.platform.syscontext.util.DateUtil.utilDate2SqlDate(DateUtil.setDatebyCalendar(DateUtil.getDate10(new Date(
                                                                                                                                                                      bean.getChange_single_time()
                                                                                                                                                                          .getTime())),
                                                                                                                                          product_deadlinel)));
            }
            // jinzm 修改，支付日期都设置成上单日期
            // wmsInveTransaProtocol.setCreate_timestamp(new
            // Timestamp(bean.getChange_date_of_payment().getTime()));
            wmsInveTransaProtocol.setCreate_timestamp(bean.getChange_single_time());
            wmsinvetransaprotocolDao.updateforback(wmsInveTransaProtocol);
        }
        bean.setCreate_user_id(user.getUserId());
        bean.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
        bean.setEnable_flag("1");
        ret = wmsinvetransaupdatelogDao.save(bean);

        /*
         * /从新计算收益信息和日志
         */
        // 协议
        WmsInveTransaProtocol paramProtocol = new WmsInveTransaProtocol();
        paramProtocol.setWms_inve_transa_id(bean.getWms_inve_transa_id());
        // 查询有效协议：每张理财单据存在系统中的有效协议只存在一条
        paramProtocol.setWms_inve_redeem_id(0);
        WmsInveTransaProtocol protocol = wmsinvetransaprotocolDao.getListByEntity(paramProtocol).get(0);

        if (ret == 0)
        {
            resStr = "error";
        }

        // jinzhm修改，数据处理时调用方法修改
        CountIncomeFactory.getCountIncome(protocol).reGenerateIncomeAndLog(protocol, user);

        return resStr;
    }

    /**
     * @Title: wmsInveTransaUpdateLogValidate
     * @Description: 验证数据处理功能中支付时间调整是否可以进行
     * @param bean 数据处理数据对象
     * @return 如果可以进行调整返回success
     * @author: jinzhm
     * @time:2017年1月5日 下午5:29:53
     * @see com.zx.emanage.inve.service.IWmsInveTransaUpdateLogService#wmsInveTransaUpdateLogValidate(com.zx.emanage.util.gen.entity.WmsInveTransaUpdateLog)
     * history:
     * 1、2017年1月5日 jinzhm 创建方法
    */
    @Override
    public String wmsInveTransaUpdateLogValidate(WmsInveTransaUpdateLog bean)
    {
        // 查询上单信息
        WmsInveTransa transa = wmsinvetransaDao.get(bean.getWms_inve_transa_id());
        // 获得支付时间
        Date dateOfPayment = transa.getDate_of_payment();
        Date changeDate = new Date(bean.getChange_single_time().getTime());
        Date nowDate = new java.sql.Date(DateUtil.getDate10(new java.util.Date()).getTime());



        // 获得上单月份的结账时间配置信息
        Map<String, Object> dateOfPaymentRuleMap = wmsinvetransaupdatelogDao.getTransaJobTime(DateUtil.date2String(dateOfPayment,
                                                                                                                   "yyyy-MM"));
        // 当月改当月判断
        // 如果有上单月份的结账时间配置信息
        if (dateOfPaymentRuleMap != null)
        {
            // 获得结账日期
            Date ruleDate = (Date) dateOfPaymentRuleMap.get("job_date");
            // 上单当月已经结账不能修改
            if (nowDate.compareTo(ruleDate) > 0)
            {
                return "error";
            }
        }

        // 查找要修改到日期的结账时间配置信息
        Map<String, Object> changeDateRuleMap = wmsinvetransaupdatelogDao.getTransaJobTime(DateUtil.date2String(changeDate,
                                                                                                                "yyyy-MM"));

        // 如果要修改到的日期月份有结账配置信息
        if (changeDateRuleMap != null)
        {
            // 获得结账日期
            Date ruleDate = (Date) changeDateRuleMap.get("job_date");
            // 如果当前时间和上单时间小于等于结账时间
            if (nowDate.compareTo(ruleDate) > 0 || dateOfPayment.compareTo(ruleDate) > 0)
            {
                return "error";
            }
        }

        return "success";
    }

    @Override
	@Transactional
	public String update(WmsInveTransaUpdateLog bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvetransaupdatelogDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveTransaUpdateLog() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveTransaUpdateLog> getListByEntity(WmsInveTransaUpdateLog queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveTransaUpdateLog> beanList = wmsinvetransaupdatelogDao.getListByEntity(queryInfo);
		return beanList;
	}

	@Override
	public List<WmsInveTransaUpdateLog> getInfo(WmsInveTransaUpdateLog bean, UserBean user) {
        bean.setCreate_user_id(user.getUserId());
        List<WmsInveTransaUpdateLog> beanList = wmsinvetransaupdatelogDao.getListByEntity(bean);
		return beanList;
	}
}
