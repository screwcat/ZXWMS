package com.zx.emanage.inve.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInvePruductCategoryDao;
import com.zx.emanage.inve.persist.WmsInvePruductYearPaySpecialDao;
import com.zx.emanage.inve.persist.WmsInveTransaIncomeDao;
import com.zx.emanage.inve.persist.WmsInveTransaLogDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.inve.persist.WmsInveTransaProtocolDao;
import com.zx.emanage.inve.persist.WmsInveTransaSpecialappChangeDao;
import com.zx.emanage.inve.service.IWmsInveTransaSpecialappChangeService;
import com.zx.emanage.inve.vo.WmsInveTransaSpecialappChangeSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInvePruductYearPaySpecial;
import com.zx.emanage.util.gen.entity.WmsInveTransaIncome;
import com.zx.emanage.util.gen.entity.WmsInveTransaLog;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.emanage.util.gen.entity.WmsInveTransaSpecialappChange;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvetransaspecialappchangeService")
public class WmsInveTransaSpecialappChangeServiceImpl implements IWmsInveTransaSpecialappChangeService {
	private static Logger log = LoggerFactory.getLogger(WmsInveTransaSpecialappChangeServiceImpl.class);

	@Autowired
	private WmsInveTransaSpecialappChangeDao wmsinvetransaspecialappchangeDao;

	@Autowired
	private WmsInveTransaProdDao wmsInveTransaProdDao;
	
	@Autowired
	private WmsInvePruductCategoryDao wmsInvePruductCategoryDao;
	
	@Autowired
	private WmsInveTransaProtocolDao wmsInveTransaProtocolDao;
	
	@Autowired
	private WmsInveTransaIncomeDao wmsInveTransaIncomeDao;
	
	@Autowired
	private WmsInveTransaLogDao wmsInveTransaLogDao;
	
	@Autowired
	private WmsInvePruductYearPaySpecialDao wmsInvePruductYearPaySpecialDao;
	
	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveTransaSpecialappChangeSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvetransaspecialappchangeDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveTransaSpecialappChangeSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvetransaspecialappchangeDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvetransaspecialappchangeDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveTransaSpecialappChange getInfoByPK(Integer wms_inve_transa_specialapp_change_id) {
		return wmsinvetransaspecialappchangeDao.get(wms_inve_transa_specialapp_change_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveTransaSpecialappChange bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		//存储客户理财单特批变更信息
		bean.setEnable_flag("1");
		if(bean.getWms_inve_transa_specialapp_change_id() == null) {
			ret = wmsinvetransaspecialappchangeDao.save(bean);
		} else {
			ret = wmsinvetransaspecialappchangeDao.update(bean);
		}
		
		
		//备份对应ID的理财上单产品信息
		WmsInveTransaProd prod = wmsInveTransaProdDao.get(bean.getWms_inve_transa_prod_id());
		wmsInveTransaProdDao.saveBak(prod);
		
		/*//重新计算预期收益金额（预计收益，只读，数据来源：该值 = 上单/追单金额 * (年化利率 / 12 * 期限时间 + 奖励利率)）
		BigDecimal expectInterestAccount = new BigDecimal(0);
		expectInterestAccount = prod.getProduct_account().multiply(bean.getProduct_interest().divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_DOWN)
				.multiply(new BigDecimal(prod.getProduct_deadline() - bean.getPay_period())) .divide(new BigDecimal(12), 6, BigDecimal.ROUND_HALF_DOWN)
				.add(prod.getReward_interest().divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_DOWN)));*/
		
		//修改对应ID的理财上单产品信息
		prod.setProduct_interest(bean.getProduct_interest().toString());
//		prod.setExpect_interest_account(expectInterestAccount);
		prod.setCreate_user_id(user.getUserId());
		prod.setCreate_timestamp(new Timestamp(new Date().getTime()));
		prod.setLast_update_user_id(null);
		prod.setLast_update_timestamp(null);
		wmsInveTransaProdDao.update(prod);
		
		//重新计算收益和日志表信息
		resStr = handleIncomeAndLogInfo(bean, user);
		
		if (ret == 0) {
			resStr = "error";
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
     */
	private String handleIncomeAndLogInfo(WmsInveTransaSpecialappChange bean, UserBean user) {
    	try {
			//获取产品信息
			WmsInvePruductCategory category = wmsInvePruductCategoryDao.get(bean.getWms_inve_pruduct_category_id());
			//付息方式:1 月付  2 年付
			int categoryInterestPayMethod = category.getCategory_interest_pay_method();
			
			//查询该单据的收益和日志信息
			WmsInveTransaIncome transaIncome = new WmsInveTransaIncome();
			transaIncome.setWms_inve_transa_id(bean.getWms_inve_transa_id());
			List<WmsInveTransaIncome> wmsInveTransaIncomes = wmsInveTransaIncomeDao.getListByEntity(transaIncome);
			
			WmsInveTransaLog transaLog = new WmsInveTransaLog();
			transaLog.setWms_inve_transa_id(bean.getWms_inve_transa_id());
			List<WmsInveTransaLog> wmsInveTransaLogs = wmsInveTransaLogDao.getListByEntity(transaLog);
			
			//修改利率时间（当前时间）
			Date updDate = new Date();
			
			if(categoryInterestPayMethod == 1) { //1： 月付 
				
				int i = 0;
				//遍历并调整相应的收益
				for(WmsInveTransaIncome wmsInveTransaIncome : wmsInveTransaIncomes) {
					//从修改利率的月份开始计算
					if(DateUtil.before(updDate, wmsInveTransaIncome.getReturn_date())) {
						//增加利率部分的收益
						BigDecimal incomeAdd = new BigDecimal(0);
						//获取修改当月
						if(DateUtil.getLastDayOfMonth(updDate).compareTo(wmsInveTransaIncome.getReturn_date()) == 0) {
							//修改月的增加部分的收益 = 本金*（新年化利率/12 - 产品月利率）/100 *到月底剩余的天数/该月总天数
							incomeAdd = getIncomeAdd(wmsInveTransaIncome.getProduct_account(), bean.getProduct_interest(), category.getBasic_monthly_rate(), 
									DateUtil.getDaysOfInterval(updDate), DateUtil.getDaysOfMonth(updDate));
							
						} else if(!DateUtil.isLastDayOfMonth(wmsInveTransaIncome.getReturn_date())) { //最后一个月
							//最后一个月的增加部分的收益 = 本金*（新年化利率/12 - 产品月利率）/100 *收益的天数/该月总天数
							incomeAdd = getIncomeAdd(wmsInveTransaIncome.getProduct_account(), bean.getProduct_interest(), category.getBasic_monthly_rate(), 
									(DateUtil.getDaysOfIntervalBegin(wmsInveTransaIncome.getReturn_date())), DateUtil.getDaysOfMonth(wmsInveTransaIncome.getReturn_date()));
							
						} else {
							incomeAdd = getIncomeAdd(wmsInveTransaIncome.getProduct_account(), bean.getProduct_interest(), category.getBasic_monthly_rate(), 
									DateUtil.getDaysOfMonth(wmsInveTransaIncome.getReturn_date()), DateUtil.getDaysOfMonth(wmsInveTransaIncome.getReturn_date()));
						}
						
						//修改收益信息
						wmsInveTransaIncome.setProduct_interest_account(wmsInveTransaIncome.getProduct_interest_account().add(incomeAdd));
						wmsInveTransaIncome.setPayable_basic_income(wmsInveTransaIncome.getPayable_basic_income().add(incomeAdd));
						wmsInveTransaIncome.setLast_update_user_id(user.getUserId());
						wmsInveTransaIncome.setLast_update_timestamp(new Timestamp(updDate.getTime()));
						//修改日志信息
						WmsInveTransaLog wmsInveTransaLog = wmsInveTransaLogs.get(i + 1);
						wmsInveTransaLog.setProduct_interest_account(wmsInveTransaLog.getProduct_interest_account().add(incomeAdd));
						wmsInveTransaLog.setRemark("产品原基础月利率为：" + category.getBasic_monthly_rate() + "；特批修改产品年化利率为：" + bean.getProduct_interest() + 
								"关联协议编号 &lt;" + bean.getProt_code() + "&gt;" + "#" + bean.getWms_inve_transa_protocol_id() + "#"
								+ bean.getWms_inve_transa_id() + "#" + bean.getWms_inve_transa_prod_id());
						wmsInveTransaLog.setLast_update_user_id(user.getUserId());
						wmsInveTransaLog.setLast_update_timestamp(new Timestamp(updDate.getTime()));
						
						wmsInveTransaIncomeDao.update(wmsInveTransaIncome);
						wmsInveTransaLogDao.update(wmsInveTransaLog);
						
					}
					
					i++;
				}
				
			} else if(categoryInterestPayMethod == 2) { //2： 年付
				WmsInveTransaProtocol wmsInveTransaProtocol = wmsInveTransaProtocolDao.get(bean.getWms_inve_transa_protocol_id());
				
				//年付特产品利率
				WmsInvePruductYearPaySpecial paySpecial = new WmsInvePruductYearPaySpecial();
				paySpecial.setWms_inve_pruduct_category_id(category.getWms_inve_pruduct_category_id());
				paySpecial.setEnable_flag("1");
				WmsInvePruductYearPaySpecial special = wmsInvePruductYearPaySpecialDao.getListByEntity(paySpecial).get(0);
				
				//支付时间
				Date startDate = wmsInveTransaProtocol.getDate_of_payment();
				//结束日期
				Date endDate = wmsInveTransaProtocol.getEnd_of_date();
				//初始化收益(默认年化收益)
				BigDecimal returnRate = category.getCategory_return_rate();
				int year = 1;
				//初始化收益(默认年化收益)
				BigDecimal returnRateNew = new BigDecimal(0);
				
				for(WmsInveTransaIncome wmsInveTransaIncome : wmsInveTransaIncomes) {
					
					boolean b = false;
					//赎回开始到一年或者最后的天数
					int incomedays = 1;
					int yearDays = 1;
					
					//增加利率部分的收益
					BigDecimal incomeAdd = new BigDecimal(0);
					
					if(special == null) {
						if(paySpecial !=  null && paySpecial.getFirst_year_interest_rate() != null && year == 1) {
							returnRate = paySpecial.getFirst_year_interest_rate();
						} else if(paySpecial !=  null && paySpecial.getSecond_year_interest_rate() != null && year == 2) {
							returnRate = paySpecial.getSecond_year_interest_rate();
						} 
					}
					
					//比较初始上单满一年时间和赎回时间
					if(DateUtil.before(updDate, DateUtil.setDatebyCalendar(wmsInveTransaProtocol.getDate_of_payment(), 12)) && year == 1) {
						//第一年赎回
						returnRateNew = bean.getProduct_interest();
						incomedays = DateUtil.getBetweenDays(updDate, DateUtil.setDatebyCalendar(startDate, 12));
						
						yearDays = DateUtil.getBetweenDays(startDate, DateUtil.setDatebyCalendar(startDate, 12));
						
						b = true;
					} 
					
					if(DateUtil.before(DateUtil.setDatebyCalendar(wmsInveTransaProtocol.getDate_of_payment(), 12), updDate) && year == 2) {
						//第二年赎回
						returnRateNew = bean.getProduct_interest();
						incomedays = DateUtil.getBetweenDays(updDate, endDate);
						
						yearDays = DateUtil.getBetweenDays(startDate, endDate);
						
						b = true;
					}
					
					
					//增加部分的收益
					incomeAdd = getIncomeAddForYear(wmsInveTransaIncome.getProduct_account(), returnRateNew, returnRate,  
							incomedays, yearDays);
					
					//修改收益表
					wmsInveTransaIncome.setLast_update_user_id(user.getUserId());
					wmsInveTransaIncome.setLast_update_timestamp(new Timestamp(new Date().getTime()));
					wmsInveTransaIncome.setProduct_interest_account(wmsInveTransaIncome.getProduct_interest_account().add(incomeAdd));
					wmsInveTransaIncome.setPayable_basic_income(wmsInveTransaIncome.getPayable_basic_income().add(incomeAdd));
					
					//修改日志表
					wmsInveTransaLogs.get(year).setLast_update_user_id(user.getUserId());
					wmsInveTransaLogs.get(year).setLast_update_timestamp(new Timestamp(new Date().getTime()));
					wmsInveTransaLogs.get(year).setProduct_interest_account(wmsInveTransaLogs.get(year).getProduct_interest_account().add(incomeAdd));
					
					if(b) {
			            
						wmsInveTransaLogs.get(year).setRemark("产品原年化利率为：" + returnRate + "；特批修改产品年化利率为：" + returnRateNew + 
								"关联协议编号 &lt;" + bean.getProt_code() + "&gt;" + "#" + bean.getWms_inve_transa_protocol_id() + "#"
								+ bean.getWms_inve_transa_id() + "#" + bean.getWms_inve_transa_prod_id());
						wmsInveTransaLogs.get(year).setLast_update_user_id(user.getUserId());
						wmsInveTransaLogs.get(year).setLast_update_timestamp(new Timestamp(new Date().getTime()));
					}
					
					wmsInveTransaIncomeDao.update(wmsInveTransaIncome);
					wmsInveTransaLogDao.update(wmsInveTransaLogs.get(year));
		            
		            startDate = DateUtil.setDatebyCalendar(startDate, 12);
					if(startDate.compareTo(DateUtil.setDatebyCalendar(wmsInveTransaProtocol.getDate_of_payment(), 12)) != 0) { //不是第一年
						startDate = DateUtil.getDateAddDays(startDate, 1);
					}
					year++;
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return "success";
    }
	
	//获取增加部分的收益(增加部分的收益 = 本金*（新年化利率/12 - 产品月利率） *该月收益的天数/(该月总天数*100))
    private BigDecimal getIncomeAdd(BigDecimal productAccount, BigDecimal newInterest, BigDecimal monthlyRate, int incomeDays, int monthDays) {
    	BigDecimal decimal = new BigDecimal(0);
    	//修改月的
    	decimal = productAccount.multiply(newInterest.divide(new BigDecimal(12), 6, BigDecimal.ROUND_HALF_DOWN)
				.subtract(monthlyRate)).multiply(new BigDecimal(incomeDays))
				.divide(new BigDecimal(monthDays * 100), 6, BigDecimal.ROUND_HALF_DOWN);
    	return setScale(decimal);
    }
    
  //获取增加部分的收益(增加部分的收益 = 本金*（新年化利率 - 旧利率） *该年收益的天数/(该年总天数*100))
    private BigDecimal getIncomeAddForYear(BigDecimal productAccount, BigDecimal newInterest, BigDecimal yearRate, int incomeDays, int yearDays) {
    	BigDecimal decimal = new BigDecimal(0);
    	//修改月的
    	decimal = productAccount.multiply(newInterest.subtract(yearRate)).multiply(new BigDecimal(incomeDays))
				.divide(new BigDecimal(yearDays * 100), 6, BigDecimal.ROUND_HALF_DOWN);
    	return setScale(decimal);
    }
    
    
    private BigDecimal setScale(BigDecimal bigDecimal) {
		if(bigDecimal == null) {
			bigDecimal = new BigDecimal(0);
		}
		return bigDecimal.setScale(6, BigDecimal.ROUND_HALF_UP);
	}

	@Override
	@Transactional
	public String update(WmsInveTransaSpecialappChange bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvetransaspecialappchangeDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveTransaSpecialappChange() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	@SuppressWarnings("unused")
	private List<WmsInveTransaSpecialappChange> getListByEntity(WmsInveTransaSpecialappChange queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveTransaSpecialappChange> beanList = wmsinvetransaspecialappchangeDao.getListByEntity(queryInfo);
		return beanList;
	}
}
