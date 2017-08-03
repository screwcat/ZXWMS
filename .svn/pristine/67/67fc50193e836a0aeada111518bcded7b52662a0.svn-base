package com.zx.emanage.loanfina.service.impl;
/**
 * 版权所有 ：版权所有(C) 2014，沈阳新融金融信息服务有限公司
 * 文件名称 : WmsFinaCreRepaymentHistoryServiceImpl.Java
 * 系统名称 ：WMS
 * 模块名称 ：财务管理
 * 完成日期 ：2014-05-12 
 * 作    者    ：HANCD
 * 内容摘要 ：
*/
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.loanappro.persist.WmsSysPropertyDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreMortgageListDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreOverdueHistoryDao;
import com.zx.emanage.loanfina.persist.WmsFinaCrePeriodRepayDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreRealrepayInfoDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreRepayDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreRepaymentDetailsAttDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreRepaymentDetailsDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreRepaymentHistoryDao;
import com.zx.emanage.loanfina.service.IWmsFinaCreRepaymentHistoryService;
import com.zx.emanage.loanfina.vo.WmsFinaCreRepaymentHistorySearchBeanVO;
import com.zx.emanage.loanfina.vo.WmsFinaDrawBeanVO;
import com.zx.emanage.loanpost.persist.WmsPostDunningCommissionDao;
import com.zx.emanage.loanpost.persist.WmsPostDunningHeadDao;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsFinaCreMortgageList;
import com.zx.emanage.util.gen.entity.WmsFinaCreOverdueHistory;
import com.zx.emanage.util.gen.entity.WmsFinaCrePeriodRepay;
import com.zx.emanage.util.gen.entity.WmsFinaCreRealrepayInfo;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepay;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepaymentDetails;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepaymentDetailsAtt;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepaymentHistory;
import com.zx.emanage.util.gen.entity.WmsInvePos;
import com.zx.emanage.util.gen.entity.WmsPostDunningCommission;
import com.zx.emanage.util.gen.entity.WmsPostDunningHead;
import com.zx.emanage.util.gen.entity.WmsPostRemindHistory;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsfinacrerepaymenthistoryService")
public class WmsFinaCreRepaymentHistoryServiceImpl implements IWmsFinaCreRepaymentHistoryService {
	private static Logger log = LoggerFactory.getLogger(WmsFinaCreRepaymentHistoryServiceImpl.class);

	@Autowired
	private WmsFinaCreRepaymentHistoryDao wmsfinacrerepaymenthistoryDao;//还款历史表
	@Autowired
	private WmsFinaCreRealrepayInfoDao wmsFinaCreRealrepayInfoDao;//应还款表
	@Autowired
	private WmsFinaCreRepaymentDetailsDao wmsFinaCreRepaymentDetailsDao;//还款明细表
	@Autowired
	private WmsFinaCreRepayDao wmsFinaCreRepayDao;//还款信息表
	@Autowired
	private WmsSysPropertyDao sysPropertyDao;//属性表
	@Autowired
	private WmsFinaCrePeriodRepayDao wmsFinaCrePeriodRepayDao;//台账信息表
	@Autowired
	private WmsFinaCreMortgageListDao wmsFinaCreMortgageListDao;//抵押物清单
	@Autowired
	private WmsFinaCreOverdueHistoryDao wmsfinacreoverduehistoryDao;//逾期历史
	@Autowired
	private WmsPostDunningHeadDao wmsPostDunningHeadDao;//催缴主表
    @Autowired
    private PmPersonnelDao pmpersonnelDao;
    @Autowired
    private WmsPostDunningCommissionDao wmsPostDunningCommissionDao;//提成表
    @Autowired
    private WmsFinaCreRepaymentDetailsAttDao wmsFinaCreRepaymentDetailsAttDao;//附件
	
	@Override
	public Map<String, Object> getListWithoutPaging(WmsFinaCreRepaymentHistorySearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsfinacrerepaymenthistoryDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsFinaCreRepaymentHistorySearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsfinacrerepaymenthistoryDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsfinacrerepaymenthistoryDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsFinaCreRepaymentHistory getInfoByPK(Integer wms_fina_cre_repayment_history_id) {
		return wmsfinacrerepaymenthistoryDao.get(wms_fina_cre_repayment_history_id);
	}
	/**
	 * 财务管理-还款管理-正常还款确认-还款信息保存
	 * @param bean 还款历史entity
	 * @param user 当前登录UserBean
	 * @param queryInfo
	 * @author hancd
	 */
	@Override	
	@Transactional
	public String save(String detailIds, String beanJSON, WmsFinaCreRepaymentHistory bean, UserBean user,WmsFinaCreRepaymentHistorySearchBeanVO queryInfo) {
		String resStr = "success";
		int ret = 0;
		long systime =System.currentTimeMillis();
		/*****************************************************************************保存还款信息历史数据************************************************/
		bean=setWmsFinaCreRepaymentHistoryBean(bean,user,systime,queryInfo);
		ret = wmsfinacrerepaymenthistoryDao.save(bean);
		/*****************************************************************************保存明细表信息数据**************************************************/
		ret=setWmsFinaCreRepaymentDetailsBean(bean,systime,ret);
		//先删除对应附件信息
		if(detailIds != null && detailIds.length() > 0) {
			detailIds = detailIds.replaceAll("\\[|\\]", "");
			String[] param = detailIds.split(",");
			List<Integer> detailIdList = new ArrayList<Integer>();
			for(int i = 0; i < param.length; i++) {
				if(!"".equals(param[i]) && param[i] != null) {
					detailIdList.add(Integer.parseInt(param[i]));
				}
				
			}
			if(detailIdList != null && detailIdList.size() > 0) {
				wmsFinaCreRepaymentDetailsAttDao.delbath(detailIdList);
			}
			
		}
		//再保存附件信息
		if(beanJSON != null && beanJSON.length() > 0) {
			List<WmsFinaCreRepaymentDetailsAtt> wmsFinaCreRepaymentDetailsAtts = JsonUtil.jsonArrayToList(beanJSON, WmsFinaCreRepaymentDetailsAtt.class);
			if(wmsFinaCreRepaymentDetailsAtts != null && wmsFinaCreRepaymentDetailsAtts.size() > 0) {
				for(WmsFinaCreRepaymentDetailsAtt wmsFinaCreRepaymentDetailsAtt : wmsFinaCreRepaymentDetailsAtts) {
					wmsFinaCreRepaymentDetailsAtt.setCreate_user_id(user.getUserId());
					wmsFinaCreRepaymentDetailsAtt.setCreate_user_name(user.getRealname());
					wmsFinaCreRepaymentDetailsAtt.setCreate_timestamp(new java.sql.Timestamp(System.currentTimeMillis()));
				}
				wmsFinaCreRepaymentDetailsAttDao.addbath(wmsFinaCreRepaymentDetailsAtts);
			}
		}
		/****************************************************************************更新抵押物信息表数据*************************************************/
		if(queryInfo.getThis_collateral_ids()!=null && !bean.getThis_collateral_ids().equals("")){
				String [] collateral_ids=bean.getThis_collateral_ids().split("#");
				for(int i=0;i<collateral_ids.length;i++){
					WmsFinaCreMortgageList wCreMortgageList = new WmsFinaCreMortgageList();
					wCreMortgageList.setWms_fina_cre_mortgage_list_id(Integer.valueOf(collateral_ids[i]));
					wCreMortgageList.setStrike_balance_status(1);//冲账状态:1代表此抵押物已经冲账
					wmsFinaCreMortgageListDao.update(wCreMortgageList);
				}
		}
		/****************************************************************************更新本期应还还款信息表数据*******************************************/
		//根据条件查询上一期是否存在调整额 存在扣除
		WmsFinaCreRealrepayInfo wInfo = new WmsFinaCreRealrepayInfo();
		wInfo.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
		wInfo.setRepay_no(queryInfo.getRepay_no().toString());
		List<WmsFinaCreRealrepayInfo> list =wmsFinaCreRealrepayInfoDao.getListByEntity(wInfo);
		if(list.size()!=0&&list!=null){//说明存在上一期
			//处理调整额
			if(list.get(0).getAdjustment_amount().compareTo(new BigDecimal(0))!=0){//说明存在
				wInfo.setAdjustment_amount(new BigDecimal(0));
				wmsFinaCreRealrepayInfoDao.updateRecord(wInfo);	
			}
		}
		WmsFinaCreRealrepayInfo t = new WmsFinaCreRealrepayInfo();
		t.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());//还款信息表主键
		t.setRepay_no(String.valueOf(bean.getRepay_no()));//对应现在的所处的还款期数
		List<WmsFinaCreRealrepayInfo> listNew =wmsFinaCreRealrepayInfoDao.getListByEntity(t);
		t.setRepay_principal(listNew.get(0).getRepay_principal().add(bean.getThis_principal()));//递增本期已还还款本金
		t.setRepay_interest(listNew.get(0).getRepay_interest().add(bean.getThis_interest()));//递增本机已还还款利息
		t.setUn_repay_principal(listNew.get(0).getOrg_repay_principal().subtract(t.getRepay_principal()));//递减本期未还本金
		t.setUn_repay_interest(listNew.get(0).getOrg_repay_interest().subtract(t.getRepay_interest()));//递减本期未还利息
		t.setAdjustment_amount(bean.getAdjustment_amount());//调整额
		t.setTotal_derate(listNew.get(0).getTotal_derate().add(bean.getThis_amount_relief()));//不论是否有滞纳金 只要减免额中填写我们就记录到历史应还款表中对应的历史减免额中
		//应还款表的本期滞纳金和总滞纳金进行处理
		//本次分配的滞纳金不存在
		if(bean.getThis_late_fees().compareTo(new BigDecimal(0))==0){
					if(bean.getThis_amount_relief().compareTo(new BigDecimal(0))==0){//本次不存在减免额
						t.setBq_cur_late_fee(listNew.get(0).getBq_cur_late_fee());
						t.setTotal_cur_late_fee(listNew.get(0).getTotal_cur_late_fee());
					}else{
						if(bean.getThis_amount_relief().compareTo(listNew.get(0).getBq_cur_late_fee())==0
								||bean.getThis_amount_relief().compareTo(listNew.get(0).getBq_cur_late_fee())==-1){
							t.setBq_cur_late_fee(listNew.get(0).getBq_cur_late_fee().subtract(bean.getThis_amount_relief()));
							t.setTotal_cur_late_fee(listNew.get(0).getTotal_cur_late_fee().subtract(bean.getThis_amount_relief()));
						}else{
							t.setBq_cur_late_fee(listNew.get(0).getBq_cur_late_fee().subtract(listNew.get(0).getBq_cur_late_fee()));
							t.setTotal_cur_late_fee(listNew.get(0).getTotal_cur_late_fee().subtract(listNew.get(0).getTotal_cur_late_fee()));
						}
					}
				}
			//本次分配的滞纳金存在
			else{
					if(bean.getThis_amount_relief().compareTo(new BigDecimal(0))==0){//本次不存在减免额
						t.setBq_cur_late_fee(listNew.get(0).getBq_cur_late_fee().subtract(bean.getThis_late_fees()));
						t.setTotal_cur_late_fee(listNew.get(0).getTotal_cur_late_fee().subtract(bean.getThis_late_fees()));
					}else{
						//本次分配的滞纳金+本次减免额<=本期滞纳金金额 
						if(bean.getThis_late_fees().add(bean.getThis_amount_relief()).compareTo(listNew.get(0).getBq_cur_late_fee())==0
								||bean.getThis_late_fees().add(bean.getThis_amount_relief()).compareTo(listNew.get(0).getBq_cur_late_fee())==-1){
							t.setBq_cur_late_fee(listNew.get(0).getBq_cur_late_fee().subtract(bean.getThis_late_fees().add(bean.getThis_amount_relief())));
							t.setTotal_cur_late_fee(listNew.get(0).getTotal_cur_late_fee().subtract(bean.getThis_late_fees().add(bean.getThis_amount_relief())));
							t.setTotal_derate(listNew.get(0).getTotal_derate().add(bean.getThis_amount_relief()));
						}
						//本次分配的滞纳金+本次减免额 > 本期滞纳金金额 
						else{
							java.math.BigDecimal  qsybqznj=listNew.get(0).getBq_cur_late_fee().subtract(bean.getThis_late_fees());
							t.setBq_cur_late_fee(listNew.get(0).getBq_cur_late_fee().subtract(bean.getThis_late_fees().add(qsybqznj)));
							t.setTotal_cur_late_fee(listNew.get(0).getTotal_cur_late_fee().subtract(bean.getThis_late_fees().add(qsybqznj)));
							//t.setTotal_derate(listNew.get(0).getTotal_derate().add(qsybqznj));
						}
					}
				}
			t.setIs_total_repayment(t.getRepay_interest().add(t.getRepay_principal()).add(t.getBq_cur_late_fee()));//本期已还总额
			t.setun_total_repayment(listNew.get(0).getTotal_repayment().subtract(t.getIs_total_repayment()));//本期未还总额
			//如果正常还款  已还本金+已还利息 =  应还本金+应还利息   这样这期算结清
			if(t.getRepay_principal().add(t.getRepay_interest()).compareTo(listNew.get(0).getOrg_repay_principal().add(listNew.get(0).getOrg_repay_interest()))==0){
				 //把本期的历史减免移入到下一期,
				 //把本期剩余的总滞纳金移入下一期，把本期总滞纳金置零，
				 //把本期剩余滞纳金移入下一期，把本期滞纳金置零
				 WmsFinaCreRealrepayInfo wInfo2 = new WmsFinaCreRealrepayInfo();
				 wInfo2.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
				 wInfo2.setRepay_no(String.valueOf(bean.getRepay_no()+1));
				 wInfo2.setTotal_derate(t.getTotal_derate());
				 wInfo2.setTotal_cur_late_fee(t.getTotal_cur_late_fee());
				 wInfo2.setBq_cur_late_fee(t.getBq_cur_late_fee());
				 wmsFinaCreRealrepayInfoDao.updateRecord(wInfo2);
				 t.setTotal_cur_late_fee(new BigDecimal(0));
				 t.setBq_cur_late_fee(new BigDecimal(0));
			}
		wmsFinaCreRealrepayInfoDao.updateRecord(t);
		/**********************************************************************************更新还款信息表**************************************************/
		WmsFinaCreRepay wRepay = new WmsFinaCreRepay();
		wRepay.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
		List<WmsFinaCreRepay>listRepay=wmsFinaCreRepayDao.getListByEntity(wRepay);
		WmsFinaCreRepay wmsFinaCreRepay=new WmsFinaCreRepay();
		wmsFinaCreRepay.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());//贷款信息表主键
		wmsFinaCreRepay.setRepay_principal(listRepay.get(0).getRepay_principal().add(bean.getThis_principal()));//已还本金
		wmsFinaCreRepay.setRepay_interest(listRepay.get(0).getRepay_interest().add(bean.getThis_interest()));//已还利息
		wmsFinaCreRepay.setUn_pay_principal(listRepay.get(0).getPrincipal().subtract(wmsFinaCreRepay.getRepay_principal()));//剩余本金
		wmsFinaCreRepay.setUn_pay_interest(listRepay.get(0).getInterest().subtract(wmsFinaCreRepay.getRepay_interest()));//剩余利息
		wmsFinaCreRepay.setMatching_creditor((wmsFinaCreRepay.getUn_pay_principal().add(wmsFinaCreRepay.getUn_pay_interest())).multiply(new BigDecimal(sysPropertyDao.get(23).getProperty_value())));//可匹配的债权
		wmsFinaCreRepay.setTotal_derate(listRepay.get(0).getTotal_derate().add(bean.getThis_amount_relief()));//累加历史减免额
		
		//**********2015-5-30****已交滞纳金金额:减免+本次分配的滞纳金金额
		wmsFinaCreRepay.setTotal_pay_late_fee(listRepay.get(0).getTotal_pay_late_fee().add(bean.getThis_late_fees()).add(bean.getThis_amount_relief()));
		//总滞纳金更新:总滞纳金-(本次减免+本次分配的滞纳金金额)
		wmsFinaCreRepay.setTotal_late_fee(listRepay.get(0).getTotal_late_fee()-bean.getThis_late_fees().intValue()-bean.getThis_amount_relief().intValue());		
		//还款信息表中的本期滞纳金=记录中的总滞纳金金额 - 本期分配的滞纳金金额-本次减免
		wmsFinaCreRepay.setCur_late_fee(new BigDecimal(listRepay.get(0).getTotal_late_fee()).subtract(bean.getThis_late_fees().add(bean.getThis_amount_relief())));//本期滞纳金更新
		//当本期已经还款完毕  已还本金和已还利息之和等于应还本金和应还利息之和 就算还清
		if((t.getRepay_principal().add(t.getRepay_interest())).compareTo(listNew.get(0).getOrg_repay_principal().add(listNew.get(0).getOrg_repay_interest()))==0){
			//当每一期结清本金和利息的时候  判断缴已交纳滞纳金总额能够满足几天逾期天数 需要做减法
			//int day=wmsFinaCreRepay.getTotal_pay_late_fee().intValue()/wmsFinaCreRepay.getLiquidated_damages().intValue();
			//wmsFinaCreRepay.setCur_overdue_day(wmsFinaCreRepay.getCur_overdue_day()-day);//本期逾期天数
			//wmsFinaCreRepay.setTotal_overdue_day(wmsFinaCreRepay.getTotal_overdue_day()-day);//总逾期天数
			//判断是否已经结清:只有本期期数为等于借款期数 并且  滞纳金等于0  才算结清
			if(bean.getRepay_no()==listRepay.get(0).getBorrow_deadline()){//本期期数==还款期限并且滞纳金等于0才能算结清 说明还完本期后 结清单据
				wmsFinaCreRepay.setClean_up_date(new java.sql.Date(System.currentTimeMillis()));//结清日期
				wmsFinaCreRepay.setRepay_status("3");//还款信息状态
				wmsFinaCreRepay.setClean_up_status("1");//结清状态
			}else if(bean.getRepay_no()==listRepay.get(0).getBorrow_deadline()&& listRepay.get(0).getTotal_late_fee()!=0){
				//说明虽然期数内的本金和利息还了 ，但是还剩下滞纳金未还，所以不能把该单据结清
				wmsFinaCreRepay.setRepay_period(listRepay.get(0).getBorrow_deadline());//已经还清台账
				wmsFinaCreRepay.setUn_pay_period(0);//未还为0
				wmsFinaCreRepay.setCurrent_repay_date(new java.sql.Date(System.currentTimeMillis()));//应还款日期为台账最后日期
				wmsFinaCreRepay.setRepay_status("4");//状态变成贷账
			}else{
				wmsFinaCreRepay.setRepay_period(listRepay.get(0).getRepay_period()+1);//已还期数+1
				wmsFinaCreRepay.setUn_pay_period(listRepay.get(0).getUn_pay_period()-1);//未还期数-1
				wmsFinaCreRepay.setCur_overdue_type("4");//本期逾期类型
				WmsFinaCrePeriodRepay w = new WmsFinaCrePeriodRepay();
				w.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
				w.setRepay_no(bean.getRepay_no()+1);
				List<WmsFinaCrePeriodRepay>li=wmsFinaCrePeriodRepayDao.getListByEntity(w);
				wmsFinaCreRepay.setCurrent_repay_date(li.get(0).getCurrent_repay_date());//更新成下一期的还款日
				wmsFinaCreRepay.setRepay_status("1");//还款状态:本期内正常还款
			}
			wmsFinaCreRepay.setLast_update_user_id(user.getUserId());//最近修改人
			wmsFinaCreRepay.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));//最近更新时间
		}
		wmsFinaCreRepayDao.updateByEntity(wmsFinaCreRepay);
		/**********************************************************************************更新台账信息****************************************************/
		//只要用户还清本期的本金和利息就可以进行更新台账操作  本期已还本金和利息等于本期应还的本金和利息 就可以更新台账信息
		if((t.getRepay_principal().add(t.getRepay_interest())).compareTo(listNew.get(0).getOrg_repay_principal().add(listNew.get(0).getOrg_repay_interest()))==0){
			//设置本期的台账信息
			WmsFinaCrePeriodRepay wPeriodRepay = new WmsFinaCrePeriodRepay();
			wPeriodRepay.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
			wPeriodRepay.setRepay_no(bean.getRepay_no());
			List<WmsFinaCrePeriodRepay> listPeriodRepay=wmsFinaCrePeriodRepayDao.getListByEntity(wPeriodRepay);
			wPeriodRepay.setRepay_principal(listPeriodRepay.get(0).getOrg_repay_principal());//本期实际还本金
			wPeriodRepay.setRepay_interest(listPeriodRepay.get(0).getOrg_repay_interest());//本期实际还利息
			wPeriodRepay.setRepay_total(wPeriodRepay.getRepay_principal().add(wPeriodRepay.getRepay_interest()));//本期实际还款总额=本期应还本金+本期应还利息
			wPeriodRepay.setCur_overdue_day(0);//逾期天数
			wPeriodRepay.setCur_late_fee(wmsFinaCreRepay.getCur_late_fee().subtract(bean.getThis_amount_relief()));//滞纳金金额
			wPeriodRepay.setIs_upload("2");//是否上传
			wPeriodRepay.setIs_overdue("0");//是否逾期
			wPeriodRepay.setDerate(bean.getThis_amount_relief());//减免额
			wPeriodRepay.setLast_update_user_id(user.getUserId());//更新人
			wPeriodRepay.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));//更新时间
			wPeriodRepay.setRepay_date(new java.sql.Date(System.currentTimeMillis()));//更新本期实际还款时间
			//根据期数和贷款主键 更新台账数据
			wmsFinaCrePeriodRepayDao.update(wPeriodRepay);
		}
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}
	/**
	 * 财务管理-还款管理-逾期还款确认-还款信息保存
	 * @param bean 还款历史entity
	 * @param user 当前登录UserBean
	 * @param queryInfo
	 * @author baisong
	 */
	@Override	
	@Transactional
	public String doSaveOverdue(WmsFinaCreRepaymentHistory bean, UserBean user,WmsFinaCreRepaymentHistorySearchBeanVO queryInfo,WmsPostDunningCommission wCommission) {
		String resStr = "success";
		int ret = 0;
		//实际逾期天数
		int sDays =0;
		//多算的逾期天数
		int dDays =0;
		//实际逾期滞纳金
		BigDecimal s_Damages=new BigDecimal(0);
		//多算的逾期滞纳金
		BigDecimal d_Damages=new BigDecimal(0);
		long systime =System.currentTimeMillis();
		if(queryInfo.getRepay_date()!=null&&!"".equals(queryInfo.getRepay_date())){
			systime =queryInfo.getRepay_date().getTime();
		}
		/*****************************************************************************保存还款信息历史数据************************************************/
		bean=setWmsFinaCreRepaymentHistoryBean(bean,user,systime,queryInfo);
		ret = wmsfinacrerepaymenthistoryDao.save(bean);
		/*****************************************************************************保存明细表信息数据**************************************************/
		ret=setWmsFinaCreRepaymentDetailsBean(bean,systime,ret);
		/*****************************************************************************保存催缴主表信息数据**************************************************/
		if(wCommission != null) {
			Map<String,Object> paramap = new HashMap<>();
            paramap.put("wms_post_dunning_head_id",wCommission.getWms_post_dunning_head_id());
            paramap.put("financial_confirmation_reminder_time", new Date(systime));
            paramap.put("dunning_status", "6");//催缴已完成
			wmsPostDunningHeadDao.updateReocod(paramap);
		}
		/****************************************************************************更新抵押物信息表数据*************************************************/
		if(queryInfo.getThis_collateral_ids()!=null&&!"".equals(queryInfo.getThis_collateral_ids())){
			String [] collateral_ids=bean.getThis_collateral_ids().split("#");
			for(int i=0;i<collateral_ids.length;i++){
				WmsFinaCreMortgageList wCreMortgageList = new WmsFinaCreMortgageList();
				wCreMortgageList.setWms_fina_cre_mortgage_list_id(Integer.valueOf(collateral_ids[i]));
				wCreMortgageList.setStrike_balance_status(1);//冲账状态:1代表此抵押物已经冲账
				wmsFinaCreMortgageListDao.update(wCreMortgageList);
			}
		}
		/****************************************************************************更新本期应还还款信息表数据*******************************************/
		//根据条件查询上一期是否存在调整额 存在扣除
		WmsFinaCreRealrepayInfo wInfo = new WmsFinaCreRealrepayInfo();
		wInfo.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
		wInfo.setRepay_no(queryInfo.getRepay_no().toString());
		List<WmsFinaCreRealrepayInfo> list =wmsFinaCreRealrepayInfoDao.getListByEntity(wInfo);
		if(list.size()!=0&&list!=null){//说明存在上一期
			if(list.get(0).getAdjustment_amount().compareTo(new BigDecimal(0))!=0){//说明存在
				wInfo.setAdjustment_amount(new BigDecimal(0));
				wmsFinaCreRealrepayInfoDao.updateRecord(wInfo);
			}
			//**********2015-5-30****处理上期滞纳金
			if(list.get(0).getTotal_cur_late_fee().compareTo(new BigDecimal(0))!=0){//说明存在总滞纳金需要把总滞纳金放入到本期滞纳金中
				wInfo.setBq_cur_late_fee(list.get(0).getBq_cur_late_fee().add(list.get(0).getTotal_cur_late_fee()));
				wmsFinaCreRealrepayInfoDao.updateRecord(wInfo);
			}	
		}
		//应还款表
		WmsFinaCreRealrepayInfo t = new WmsFinaCreRealrepayInfo();
		t.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());//还款信息表主键
		t.setRepay_no(String.valueOf(bean.getRepay_no()));//对应现在的所处的还款期数
		List<WmsFinaCreRealrepayInfo> listNew =wmsFinaCreRealrepayInfoDao.getListByEntity(t);
		if(listNew==null||listNew.size()==0){
			resStr = "error";
			return resStr;
		}
		///查询贷款还款信息表
		WmsFinaCreRepay wRepay = new WmsFinaCreRepay();
		wRepay.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
		List<WmsFinaCreRepay> listRepay=wmsFinaCreRepayDao.getListByEntity(wRepay);
		if(listRepay==null||listRepay.size()==0){
			resStr = "error";
			return resStr;
		}
		//当存在实际还款日期时需要重新计算滞纳金额和逾期天数
		if(queryInfo.getRepay_date()!=null&&!"".equals(queryInfo.getRepay_date())){//存在实际还款额
			if(listRepay!=null&&listRepay.size()>0){
				//实际逾期天数
				sDays =	calInterval(listRepay.get(0).getCurrent_repay_date(),queryInfo.getRepay_date(),"D");//应还款时间和实际还款时间的差
				//多算的逾期天数
				dDays =	calInterval(queryInfo.getRepay_date(),getNow(),"D");//实际还款时间和当前时间的天数差
				//实际逾期滞纳金
				s_Damages=listRepay.get(0).getLiquidated_damages().multiply(new BigDecimal(sDays));
				//多算的逾期滞纳金
				d_Damages=listRepay.get(0).getLiquidated_damages().multiply(new BigDecimal(dDays));
				boolean value=false;//判断是否结清
				if(listNew.get(0).getUn_repay_interest().compareTo(bean.getThis_interest().add(new BigDecimal(100)))<=0&&listRepay.get(0).getUn_pay_period()>1){
					value=true;
				}else if(listRepay.get(0).getUn_pay_period()==1&&listRepay.get(0).getTotal_late_fee().intValue()<=(bean.getThis_late_fees().add(d_Damages)).intValue()){
					value=true;
				}else{
					value=false;
				}
				if(value){
					listRepay.get(0).setCur_overdue_day(sDays);//本期逾期天数
					listRepay.get(0).setCur_late_fee(s_Damages);//本期滞纳金额
					listRepay.get(0).setTotal_late_fee((new BigDecimal(listRepay.get(0).getTotal_late_fee()).subtract(d_Damages)).intValue());//总应滞纳金额
					listRepay.get(0).setTotal_overdue_day(listRepay.get(0).getTotal_overdue_day()-dDays);//总逾期天数 
					//wmsFinaCreRepayDao.update(wRepay);
					
					if(listNew!=null&&listNew.size()>0){
						listNew.get(0).setTotal_cur_late_fee(listNew.get(0).getTotal_cur_late_fee().subtract(d_Damages));//滞纳金总额
						listNew.get(0).setBq_cur_late_fee(listNew.get(0).getBq_cur_late_fee().subtract(d_Damages));//本期应还滞纳金
						listNew.get(0).setTotal_repayment(listNew.get(0).getTotal_repayment().subtract(d_Damages));//本期应还总额
						listNew.get(0).setun_total_repayment(listNew.get(0).getun_total_repayment().subtract(d_Damages));//本期未还总额
						//wmsFinaCreRealrepayInfoDao.update(t);
					}
					if(s_Damages.compareTo(bean.getThis_late_fees())>0){
						//不处理
					}else{
						bean.setAdjustment_amount(bean.getThis_late_fees().subtract(s_Damages));//上期调整额
						bean.setThis_late_fees(s_Damages);//本期滞纳金
					}
				}else{
					sDays =0;
					dDays =0;
					//实际逾期滞纳金
					s_Damages=new BigDecimal(0);
					//多算的逾期滞纳金
					d_Damages=new BigDecimal(0);
				}
				if(sDays==0){
					t.setTotal_cur_late_fee(listNew.get(0).getTotal_cur_late_fee().subtract(listNew.get(0).getBq_cur_late_fee()));//重新计算滞纳金
					t.setBq_cur_late_fee(new BigDecimal(0));//本期滞纳金
					listNew.get(0).setTotal_cur_late_fee(listNew.get(0).getTotal_cur_late_fee().subtract(listNew.get(0).getBq_cur_late_fee()));//重新计算滞纳金
					listNew.get(0).setBq_cur_late_fee(new BigDecimal(0));//本期滞纳金
				}
			}		
		}
		/*WmsFinaCreRealrepayInfo t = new WmsFinaCreRealrepayInfo();
		t.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());//还款信息表主键
		t.setRepay_no(String.valueOf(bean.getRepay_no()));//对应现在的所处的还款期数
		List<WmsFinaCreRealrepayInfo> listNew =wmsFinaCreRealrepayInfoDao.getListByEntity(t);*/
		t.setRepay_principal(listNew.get(0).getRepay_principal().add(bean.getThis_principal()));//递增本期已还还款本金
		t.setRepay_interest(listNew.get(0).getRepay_interest().add(bean.getThis_interest()));//递增本机已还还款利息
		t.setUn_repay_principal(listNew.get(0).getOrg_repay_principal().subtract(t.getRepay_principal()));//递减本期未还本金
		t.setUn_repay_interest(listNew.get(0).getOrg_repay_interest().subtract(t.getRepay_interest()));//递减本期未还利息
		t.setTotal_derate(listNew.get(0).getTotal_derate().add(bean.getThis_amount_relief()));//历史减免额累加

		//应还款表的本期滞纳金和总滞纳金进行处理
		//本次分配的滞纳金不存在
		if(bean.getThis_late_fees().compareTo(new BigDecimal(0))==0){
			if(bean.getThis_amount_relief().compareTo(new BigDecimal(0))==0){//本次不存在减免额
				t.setBq_cur_late_fee(listNew.get(0).getBq_cur_late_fee());
				t.setTotal_cur_late_fee(listNew.get(0).getTotal_cur_late_fee());
			}else{
				if(bean.getThis_amount_relief().compareTo(listNew.get(0).getBq_cur_late_fee())==0
						||bean.getThis_amount_relief().compareTo(listNew.get(0).getBq_cur_late_fee())==-1){
					t.setBq_cur_late_fee(listNew.get(0).getBq_cur_late_fee().subtract(bean.getThis_amount_relief()));
					t.setTotal_cur_late_fee(listNew.get(0).getTotal_cur_late_fee().subtract(bean.getThis_amount_relief()));
				}else{
					t.setBq_cur_late_fee(listNew.get(0).getBq_cur_late_fee().subtract(listNew.get(0).getBq_cur_late_fee()));
					t.setTotal_cur_late_fee(listNew.get(0).getTotal_cur_late_fee().subtract(listNew.get(0).getTotal_cur_late_fee()));
				}
			}
		}
		//本次分配的滞纳金存在
		else{
			if(bean.getThis_amount_relief().compareTo(new BigDecimal(0))==0){//本次不存在减免额
				t.setBq_cur_late_fee(listNew.get(0).getBq_cur_late_fee().subtract(bean.getThis_late_fees()));
				t.setTotal_cur_late_fee(listNew.get(0).getTotal_cur_late_fee().subtract(bean.getThis_late_fees()));
			}else{
				//本次分配的滞纳金+本次减免额<=本期滞纳金金额 
				if(bean.getThis_late_fees().add(bean.getThis_amount_relief()).compareTo(listNew.get(0).getBq_cur_late_fee())==0
						||bean.getThis_late_fees().add(bean.getThis_amount_relief()).compareTo(listNew.get(0).getBq_cur_late_fee())==-1){
					t.setBq_cur_late_fee(listNew.get(0).getBq_cur_late_fee().subtract(bean.getThis_late_fees().add(bean.getThis_amount_relief())));
					t.setTotal_cur_late_fee(listNew.get(0).getTotal_cur_late_fee().subtract(bean.getThis_late_fees().add(bean.getThis_amount_relief())));
					t.setTotal_derate(listNew.get(0).getTotal_derate().add(bean.getThis_amount_relief()));
				}
				//本次分配的滞纳金+本次减免额 > 本期滞纳金金额 
				else{
					java.math.BigDecimal  qsybqznj=listNew.get(0).getBq_cur_late_fee().subtract(bean.getThis_late_fees());
					t.setBq_cur_late_fee(listNew.get(0).getBq_cur_late_fee().subtract(bean.getThis_late_fees().add(qsybqznj)));
					t.setTotal_cur_late_fee(listNew.get(0).getTotal_cur_late_fee().subtract(bean.getThis_late_fees().add(qsybqznj)));
					//t.setTotal_derate(listNew.get(0).getTotal_derate().add(qsybqznj));
				}
			}
		}
		t.setIs_total_repayment(t.getRepay_interest().add(t.getRepay_principal()).add(bean.getThis_late_fees()).add(bean.getThis_amount_relief()));//本期已还总额
		t.setun_total_repayment(listNew.get(0).getTotal_repayment().subtract(t.getIs_total_repayment()));//本期未还总额	
		
		/*if(queryInfo.getRepay_date()!=null&&!"".equals(queryInfo.getRepay_date())){//存在实际还款日期
			if(s_Damages.compareTo(bean.getThis_late_fees())<0){//0是等于 1是大于 -1是小于
				bean.setThis_late_fees(s_Damages);//如果实际滞纳金小于分配的滞纳金则修改分配的滞纳金 把多余的加到调整额中 (需要把减免额计算进去)
			}
		}*/
		t.setCur_late_fee(bean.getThis_late_fees());//本期缴纳滞纳金//t.setCur_late_fee(bean.getThis_amount_relief());//把减免额加到已还滞纳金里面
		/*if(bean.getThis_amount_relief().compareTo(t.getBq_cur_late_fee())>0){//如果减免额大于滞纳金  则将剩余的减免额加到调整额中
			//t.setAdjustment_amount(bean.getAdjustment_amount().add(bean.getThis_amount_relief()).subtract(t.getBq_cur_late_fee()).add(d_Damages));//调整额
			/*if(t.getTotal_cur_late_fee().compareTo(new BigDecimal(0))>0){//滞纳金总额>0 标识还有未还的滞纳金 会将多余的部分钱先分配到滞纳金总额中
				if(t.getAdjustment_amount().compareTo(t.getTotal_cur_late_fee())>0){
					t.setAdjustment_amount(t.getAdjustment_amount().subtract(t.getTotal_cur_late_fee()));
					t.setTotal_cur_late_fee(new BigDecimal(0));
				}else{
					t.setTotal_cur_late_fee(t.getTotal_cur_late_fee().subtract(t.getAdjustment_amount()));//总滞纳金减去调整额  为本期剩余总滞纳金
					t.setAdjustment_amount(new BigDecimal(0));//调整额
				}
			}*/
		//}else{
		if(listNew.get(0).getRepay_no_count()!=null&&!"".equals(listNew.get(0).getRepay_no_count())){
			if(listNew.get(0).getRepay_no_count()>0){
				t.setAdjustment_amount(new BigDecimal(0));//调整额
			}else{
				t.setAdjustment_amount(bean.getAdjustment_amount());//调整额
			}
		}else{
			t.setAdjustment_amount(bean.getAdjustment_amount());//调整额
		}
		
		//}
		if(t.getTotal_cur_late_fee().compareTo(new BigDecimal(0))>0){//滞纳金总额>0 标识还有未还的滞纳金 会将多余的部分钱先分配到滞纳金总额中
			if(t.getAdjustment_amount().compareTo(t.getTotal_cur_late_fee())>0){
				t.setAdjustment_amount(t.getAdjustment_amount().subtract(t.getTotal_cur_late_fee()));
				t.setTotal_cur_late_fee(new BigDecimal(0));
			}else{
				t.setTotal_cur_late_fee(t.getTotal_cur_late_fee().subtract(t.getAdjustment_amount()));//总滞纳金减去调整额  为本期剩余总滞纳金
				t.setAdjustment_amount(new BigDecimal(0));//调整额
			}
		}
		/**********************************************************************************逾期历史表**************************************/
		WmsFinaCreOverdueHistory whistory=new  WmsFinaCreOverdueHistory();//逾期历史表
		whistory.setRepayment_history_code(bean.getRepayment_history_code());//还款流水号
		whistory.setWms_fina_cre_realrepay_info_id(listNew.get(0).getWms_fina_cre_realrepay_info_id());//应还还款表主键
		if(queryInfo.getRepay_date()!=null&&!"".equals(queryInfo.getRepay_date())){//存在实际还款额
			whistory.setOverdue_date(listRepay.get(0).getCurrent_repay_date()+"-"+queryInfo.getRepay_date());//逾期日期=本期应还款日期
		}else{
			whistory.setOverdue_date(listRepay.get(0).getCurrent_repay_date()+"-"+new java.sql.Date(System.currentTimeMillis()));//逾期日期=本期应还款日期
		}
		whistory.setCur_overdue_day(new BigDecimal(listRepay.get(0).getCur_overdue_day()));//逾期天数=本期逾期天数
		whistory.setLate_fees(listNew.get(0).getTotal_cur_late_fee());//滞纳金
		whistory.setIs_late_fees(t.getCur_late_fee());//已还滞纳金

		whistory.setRelief_late_fees(bean.getThis_amount_relief());//减免滞纳金

		whistory.setEnable_flag("1");
		whistory.setWms_fina_cre_repayment_history_id(bean.getWms_fina_cre_repayment_history_id());//还款历史表主键
		whistory.setWms_cre_credit_head_id(t.getWms_cre_credit_head_id());//贷款信息表主键
		whistory.setRepay_no(new Integer(t.getRepay_no()));//逾期期数
		ret = wmsfinacreoverduehistoryDao.save(whistory);
		if (ret == 0) {
			resStr = "error";
			return resStr;
		}
		/**********************************************************************************更新还款信息表**************************************************/
		WmsFinaCreRepay wmsFinaCreRepay=new WmsFinaCreRepay();
		wmsFinaCreRepay.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());//贷款信息表主键
		wmsFinaCreRepay.setRepay_principal(listRepay.get(0).getRepay_principal().add(bean.getThis_principal()));//已还本金
		wmsFinaCreRepay.setRepay_interest(listRepay.get(0).getRepay_interest().add(bean.getThis_interest()));//已还利息
		wmsFinaCreRepay.setUn_pay_principal(listRepay.get(0).getPrincipal().subtract(wmsFinaCreRepay.getRepay_principal()));//剩余本金
		wmsFinaCreRepay.setUn_pay_interest(listRepay.get(0).getInterest().subtract(wmsFinaCreRepay.getRepay_interest()));//剩余利息
		wmsFinaCreRepay.setMatching_creditor((wmsFinaCreRepay.getUn_pay_principal().add(wmsFinaCreRepay.getUn_pay_interest())).multiply(new BigDecimal(sysPropertyDao.get(23).getProperty_value())));//可匹配的债权
		//**********2015-5-30****已交滞纳金金额:减免+本次分配的滞纳金金额
		wmsFinaCreRepay.setTotal_pay_late_fee(listRepay.get(0).getTotal_pay_late_fee().add(bean.getThis_late_fees()).add(bean.getThis_amount_relief()));//已缴纳滞纳金额
		//总滞纳金更新:总滞纳金-(本次减免+本次分配的滞纳金金额)
		wmsFinaCreRepay.setTotal_late_fee(listRepay.get(0).getTotal_late_fee()-bean.getThis_late_fees().intValue()-bean.getThis_amount_relief().intValue());		
		//还款信息表中的本期滞纳金=记录中的总滞纳金金额 - 本期分配的滞纳金金额-本次减免
		wmsFinaCreRepay.setCur_late_fee(new BigDecimal(listRepay.get(0).getTotal_late_fee()).subtract(bean.getThis_late_fees().add(bean.getThis_amount_relief())));//本期滞纳金更新
		//当本期已经还款完毕
		//if(t.getIs_total_repayment().compareTo(listNew.get(0).getTotal_repayment())==0){
		boolean val=false;
		if(t.getUn_repay_interest().compareTo(new BigDecimal(100))<=0&&listRepay.get(0).getUn_pay_period()>1){
			val=true;
		//}else if(listRepay.get(0).getUn_pay_period()==1&&wmsFinaCreRepay.getTotal_late_fee().intValue()<=0){
		}else if(listRepay.get(0).getUn_pay_period()==1&&t.getUn_repay_interest().intValue()<=listRepay.get(0).getRepay_period()){
			val=true;
		}else{
			val=false;
		}
		/**********************************************************************************更新台账信息****************************************************/
		if(val){
			WmsFinaCrePeriodRepay wPeriodRepay = new WmsFinaCrePeriodRepay();
			wPeriodRepay.setWms_fina_cre_period_pay_id(listNew.get(0).getWms_fina_cre_period_pay_id());
			wPeriodRepay.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
			wPeriodRepay.setRepay_no(bean.getRepay_no());
			if(queryInfo.getRepay_date()!=null&&!"".equals(queryInfo.getRepay_date())){
				wPeriodRepay.setCur_overdue_day(sDays);//本期逾期天数
				wPeriodRepay.setCur_late_fee(s_Damages);//本期滞纳金
			}else{
				//wPeriodRepay.setCur_overdue_day(listRepay.get(0).getCur_overdue_day());//本期逾期天数
				//wPeriodRepay.setCur_late_fee(listRepay.get(0).getCur_late_fee());//本期滞纳金
				wPeriodRepay.setCur_overdue_day(wmsFinaCreRepay.getCur_overdue_day());//本期逾期天数
				wPeriodRepay.setCur_late_fee(wmsFinaCreRepay.getCur_late_fee());//本期滞纳金
			}
			wPeriodRepay.setIs_upload("2");//是否上传
			wPeriodRepay.setLast_update_user_id(user.getUserId());//更新人
			wPeriodRepay.setLast_update_timestamp(new Timestamp(systime));//更新时间
			if(queryInfo.getRepay_date()!=null&&!"".equals(queryInfo.getRepay_date())){//存在实际还款额
				wPeriodRepay.setRepay_date(queryInfo.getRepay_date());
			}else{
				wPeriodRepay.setRepay_date(new java.sql.Date(System.currentTimeMillis()));//更新本期实际还款时间
			}
			ret =wmsFinaCrePeriodRepayDao.updateforv(wPeriodRepay);
			if (ret == 0) {
				resStr = "error";
				return resStr;
			}
		}
		//if(t.getUn_repay_interest().compareTo(new BigDecimal(100))<=0&&listRepay.get(0).getUn_pay_period()>1){//判断未还利息是否小于100  如果小于100则 本期结清
		if(val){	
		//获取对应的期还款台账表信息
			//WmsFinaCrePeriodRepay periodRepay=wmsFinaCrePeriodRepayDao.get(listNew.get(0).getWms_fina_cre_period_pay_id());//台账表主键
			//BigDecimal	repay_no=t.getRepay_principal().divide(periodRepay.getOrg_repay_principal());//计算此次还款一共换了多少期
			BigDecimal	repay_no=new BigDecimal(0);
			if(listNew.get(0).getRepay_no_count()==null||"".equals(listNew.get(0).getRepay_no_count())){
				repay_no=new BigDecimal(1);
			}else{
				repay_no=new BigDecimal(listNew.get(0).getRepay_no_count());//计算此次还款一共换了多少期
			}
			WmsFinaCreRealrepayInfo t1 = new WmsFinaCreRealrepayInfo();
			t1.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());//还款信息表主键
			t1.setRepay_no(String.valueOf(bean.getRepay_no()+repay_no.intValue()));//对应现在的所处的还款期数
			List<WmsFinaCreRealrepayInfo> listNew1 =wmsFinaCreRealrepayInfoDao.getListByEntity(t1);
			for(int i=1;i<repay_no.intValue();i++){//循环已还的每一期数据
				WmsFinaCreRealrepayInfo realrepayInfo = new WmsFinaCreRealrepayInfo();
				realrepayInfo.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());//还款信息表主键
				realrepayInfo.setRepay_no(String.valueOf(bean.getRepay_no()+i));//对应现在的所处的还款期数
				List<WmsFinaCreRealrepayInfo> realrepaylist =wmsFinaCreRealrepayInfoDao.getListByEntity(realrepayInfo);
				if(realrepaylist!=null&&realrepaylist.size()>0){
					realrepaylist.get(0).setun_total_repayment(new BigDecimal(0));//本期未还总额
					realrepaylist.get(0).setIs_total_repayment(realrepaylist.get(0).getTotal_repayment());//本期已还总额
					if(i==repay_no.intValue()-1){
						realrepaylist.get(0).setAdjustment_amount(bean.getAdjustment_amount());//上期调整额	
					}else{
						realrepaylist.get(0).setAdjustment_amount(new BigDecimal(0));//上期调整额
					}
					realrepaylist.get(0).setRepay_principal(realrepaylist.get(0).getRepay_principal());//已还本金
					realrepaylist.get(0).setUn_repay_principal(new BigDecimal(0));//未还本金
					realrepaylist.get(0).setRepay_interest(realrepaylist.get(0).getRepay_interest());//已还利息
					realrepaylist.get(0).setUn_repay_interest(new BigDecimal(0));//未还利息
					realrepaylist.get(0).setTotal_cur_late_fee(new BigDecimal(0));//滞纳金总额
					realrepaylist.get(0).setCur_late_fee(new BigDecimal(0));//已还滞纳金
					realrepaylist.get(0).setTotal_derate(t.getTotal_derate());//历史减免额
					realrepaylist.get(0).setBq_cur_late_fee(new BigDecimal(0));//本期应还滞纳金
					//期还款台账
					WmsFinaCrePeriodRepay wPeriodRepay = new WmsFinaCrePeriodRepay();
					wPeriodRepay.setWms_fina_cre_period_pay_id(realrepaylist.get(0).getWms_fina_cre_period_pay_id());//台账表主键
					wPeriodRepay.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
					wPeriodRepay.setRepay_date(new java.sql.Date(systime));//实际还款日期.
					wPeriodRepay.setRepay_total(realrepaylist.get(0).getTotal_repayment());//实际还款总额
					wPeriodRepay.setRepay_principal(realrepaylist.get(0).getRepay_principal());//实际还款本金
					wPeriodRepay.setRepay_interest(realrepaylist.get(0).getRepay_interest());//实际还款利息
					wPeriodRepay.setIs_overdue("1");//是否逾期
					wPeriodRepay.setCur_overdue_day(0);//逾期天数
					wPeriodRepay.setCur_late_fee(new BigDecimal(0));//滞纳金额
					wPeriodRepay.setDerate(new BigDecimal(0));//滞纳金额
					wPeriodRepay.setIs_upload("2");//是否上传
					wPeriodRepay.setLast_update_user_id(user.getUserId());//更新人
					wPeriodRepay.setLast_update_timestamp(new Timestamp(systime));//更新时间
					if(queryInfo.getRepay_date()!=null&&!"".equals(queryInfo.getRepay_date())){//存在实际还款额
						wPeriodRepay.setRepay_date(queryInfo.getRepay_date());
					}else{
						wPeriodRepay.setRepay_date(new java.sql.Date(System.currentTimeMillis()));//更新本期实际还款时间
					}
					wmsFinaCrePeriodRepayDao.update(wPeriodRepay);//期还款台账
					wmsFinaCreRealrepayInfoDao.update(realrepaylist.get(0));//应还款表
				}
			}
			wmsFinaCreRepay.setRepay_period(listRepay.get(0).getRepay_period()+repay_no.intValue());//已还期数+1
			wmsFinaCreRepay.setUn_pay_period(listRepay.get(0).getUn_pay_period()-repay_no.intValue());//未还期数-1
			wmsFinaCreRepay.setCur_overdue_type("4");//本期逾期类型
			wmsFinaCreRepay.setCur_overdue_day(0);//本期逾期天数
			wmsFinaCreRepay.setTotal_derate(t.getTotal_derate());//总减免额
			wmsFinaCreRepay.setCur_late_fee(t.getTotal_cur_late_fee());//本期滞纳金金额--未还的滞纳金
			//当每一期结清本金和利息的时候  判断缴已交纳滞纳金总额能够满足几天逾期天数 需要做减法
			/*int day=wmsFinaCreRepay.getTotal_pay_late_fee().intValue()/wmsFinaCreRepay.getLiquidated_damages().intValue();
			wmsFinaCreRepay.setCur_overdue_day(wmsFinaCreRepay.getCur_overdue_day()-day);//本期逾期天数
			wmsFinaCreRepay.setTotal_overdue_day(wmsFinaCreRepay.getTotal_overdue_day()-day);//总逾期天数
			*/
			//判断是否已经结清
			//if(bean.getRepay_no()==listRepay.get(0).getBorrow_deadline()){//本期期数==还款期限 说明还完本期后 结清单据
			if(wmsFinaCreRepay.getUn_pay_period()==0){//如果未还期数等于0 则为结清状态
				if(wmsFinaCreRepay.getTotal_late_fee()>0){//说明滞纳金为还完，台账本金利息已还完
					wmsFinaCreRepay.setRepay_status("4");//单据状态变成贷账状态
				}else{
					wmsFinaCreRepay.setClean_up_date(new java.sql.Date(systime));//结清日期
					wmsFinaCreRepay.setRepay_status("3");//还款信息状态
					wmsFinaCreRepay.setClean_up_status("1");//结清状态					
				}
			}else{
				WmsFinaCrePeriodRepay w = new WmsFinaCrePeriodRepay();
				w.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
				w.setRepay_no(wmsFinaCreRepay.getRepay_period()+1);
				List<WmsFinaCrePeriodRepay>li=wmsFinaCrePeriodRepayDao.getListByEntity(w);
				if(li!=null&&li.size()>0){
					wmsFinaCreRepay.setCurrent_repay_date(li.get(0).getCurrent_repay_date());//更新成下一期的还款日	
				}	
				wmsFinaCreRepay.setRepay_status("1");//还款状态:本期内正常还款	
			}			
			//如果是本期还款完成则处理应还款表信息
			if(listNew1!=null&&listNew1.size()>0){
				t1=listNew1.get(0);
				t1.setTotal_cur_late_fee(t1.getTotal_cur_late_fee().add(t.getTotal_cur_late_fee()));//本期未还的需要加到下期总滞纳金
				t1.setBq_cur_late_fee(t1.getBq_cur_late_fee().add(t.getTotal_cur_late_fee()));//本期未还的需要加到下期总滞纳金
				t1.setUn_repay_interest(t1.getUn_repay_interest().add(t.getUn_repay_interest()));//本期未还的利息需要添加到下期未还利息中
				t1.setOrg_repay_interest(t1.getOrg_repay_interest().add(t.getUn_repay_interest()));//本期未还的利息需要添加到下期利息中
				t1.setTotal_repayment(t1.getTotal_repayment().add(t1.getTotal_cur_late_fee()).add(t.getUn_repay_interest()));//下期期应还总额
				t1.setun_total_repayment(t1.getun_total_repayment().add(t1.getTotal_cur_late_fee()).add(t.getUn_repay_interest()));//下期未还总额
				t1.setTotal_derate(t.getTotal_derate());//历史减免额
				t.setTotal_cur_late_fee(new BigDecimal(0));
				t.setBq_cur_late_fee(new BigDecimal(0));
				wmsFinaCreRealrepayInfoDao.updateRecord(t1);
			}
			wmsFinaCreRepay.setLast_update_user_id(user.getUserId());//最近修改人
			wmsFinaCreRepay.setLast_update_timestamp(new Timestamp(systime));//最近更新时间
		}
		/*if(queryInfo.getRepay_date()!=null&&!"".equals(queryInfo.getRepay_date())){//存在实际还款额
			WmsFinaCrePeriodRepay w = new WmsFinaCrePeriodRepay();
			w.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
			if(wmsFinaCreRepay.getRepay_period()==null||"".equals(wmsFinaCreRepay.getRepay_period())){
				resStr = "errornull";//期数为空
				return resStr;	
			}
			w.setRepay_no(wmsFinaCreRepay.getRepay_period()+1);
			List<WmsFinaCrePeriodRepay>li=wmsFinaCrePeriodRepayDao.getListByEntity(w);
			if(li!=null&&li.size()>0){
				wmsFinaCreRepay.setCurrent_repay_date(li.get(0).getCurrent_repay_date());//更新成下一期的还款日	
			}		
		}*/
		wmsFinaCreRepayDao.updateByEntity(wmsFinaCreRepay);
		if(wCommission!=null){//提成信息保存
			wCommission.setCreate_user_id(user.getUserId());//创建人ID
			wCommission.setCreate_user_name(user.getRealname());//创建人name
			wCommission.setCreate_user_datetime(new Timestamp(systime));//创建时间
			wCommission.setLast_update_datetime(new Timestamp(systime));//更新时间
			wCommission.setEnable_flag("1");//数据状态
			ret =wmsPostDunningCommissionDao.save(wCommission);
			if (ret == 0) {
				resStr = "error";
			}
			if(queryInfo.getWms_post_dunning_head_id1()!=null&&!"".equals(queryInfo.getWms_post_dunning_head_id1())){//判断id是否为空
				wCommission.setWms_post_dunning_head_id(queryInfo.getWms_post_dunning_head_id1());//id
				wCommission.setBelonging("1");//电话催缴
				wCommission.setReality_return_amount(queryInfo.getReality_return_amount1());
				wCommission.setReality_return_ratio(queryInfo.getReality_return_ratio1());
				wCommission.setReality_return_ommission(queryInfo.getReality_return_ommission1());
				wCommission.setWms_post_dunning_commission_id(null);
				ret =wmsPostDunningCommissionDao.save(wCommission);
				if (ret == 0) {
					resStr = "error";
				}
			}
		}
		wmsFinaCreRealrepayInfoDao.updateRecord(t);//应还款表
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}
	
	/**
	 * 财务管理-还款管理-逾期还款确认-还款信息保存--暂时保留 废弃代码
	 * @param bean 还款历史entity
	 * @param user 当前登录UserBean
	 * @param queryInfo
	 * @author baisong
	 */
	//@Override	
	@Transactional
	public String doSaveOverdueOld(WmsFinaCreRepaymentHistory bean, UserBean user,WmsFinaCreRepaymentHistorySearchBeanVO queryInfo) {
		String resStr = "success";
		int ret = 0;
		long systime =System.currentTimeMillis();

		/*****************************************************************************保存还款信息历史数据************************************************/
		bean=setWmsFinaCreRepaymentHistoryBean(bean,user,systime,queryInfo);
		ret = wmsfinacrerepaymenthistoryDao.save(bean);
		/*****************************************************************************保存明细表信息数据**************************************************/
		ret=setWmsFinaCreRepaymentDetailsBean(bean,systime,ret);
		/****************************************************************************更新抵押物信息表数据*************************************************/
		if(queryInfo.getThis_collateral_ids()!=null){
			String [] collateral_ids=bean.getThis_collateral_ids().split("#");
			for(int i=0;i<collateral_ids.length;i++){
				WmsFinaCreMortgageList wCreMortgageList = new WmsFinaCreMortgageList();
				wCreMortgageList.setWms_fina_cre_mortgage_list_id(Integer.valueOf(collateral_ids[i]));
				wCreMortgageList.setStrike_balance_status(1);//冲账状态:1代表此抵押物已经冲账
				wmsFinaCreMortgageListDao.update(wCreMortgageList);
			}
		}
		/****************************************************************************更新本期应还还款信息表数据*******************************************/
		//更新应还款信息表
		//根据条件查询上一期是否存在调整额 存在扣除
		WmsFinaCreRealrepayInfo wInfo = new WmsFinaCreRealrepayInfo();
		wInfo.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
		wInfo.setRepay_no(bean.getRepay_no().toString());
		List<WmsFinaCreRealrepayInfo> list =wmsFinaCreRealrepayInfoDao.getListByEntity(wInfo);
		if(list.get(0).getAdjustment_amount().compareTo(new BigDecimal(0))!=0){//说明存在
			wInfo.setAdjustment_amount(new BigDecimal(0));
			wmsFinaCreRealrepayInfoDao.updateRecord(wInfo);
		}
		//更新本期应还还款信息表数据
		WmsFinaCreRealrepayInfo t = new WmsFinaCreRealrepayInfo();
		
		t.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());//还款信息表主键
		t.setRepay_no(String.valueOf(bean.getRepay_no()));//对应现在的所处的还款期数
		List<WmsFinaCreRealrepayInfo> listNew =wmsFinaCreRealrepayInfoDao.getListByEntity(t);
		WmsFinaCreRealrepayInfo t1 = new WmsFinaCreRealrepayInfo();
		t1.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());//还款信息表主键
		t1.setRepay_no((bean.getRepay_no()+1)+"");
		List<WmsFinaCreRealrepayInfo> listNew1 =wmsFinaCreRealrepayInfoDao.getListByEntity(t1);
		t.setRepay_principal(listNew.get(0).getRepay_principal().add(bean.getThis_principal()));//递增本期已还还款本金
		t.setRepay_interest(listNew.get(0).getRepay_interest().add(bean.getThis_interest()));//递增本机已还还款利息
		t.setUn_repay_principal(list.get(0).getOrg_repay_principal().subtract(t.getRepay_principal()));//递减本期未还本金
		t.setUn_repay_interest(list.get(0).getOrg_repay_interest().subtract(t.getRepay_interest()));//递减本期未还利息
	
		if(queryInfo.getRepay_date()!=null&&!"".equals(queryInfo.getRepay_date())){//存在实际还款额
			t.setIs_total_repayment(list.get(0).getTotal_repayment());//本期已还总额=本期应还总额
			t.setun_total_repayment(new BigDecimal(0));//本期未还总额
		}else{
			t.setIs_total_repayment(list.get(0).getIs_total_repayment().add(t.getRepay_principal().add(t.getRepay_interest())));//本期已还总额
			t.setun_total_repayment(list.get(0).getTotal_repayment().subtract(t.getIs_total_repayment()));//本期未还总额
			t.setTotal_cur_late_fee(list.get(0).getTotal_cur_late_fee().add(bean.getThis_late_fees()));//滞纳金总额 b
			t.setCur_late_fee(list.get(0).getCur_late_fee().add(bean.getThis_late_fees()));//已还滞纳金 b
			t.setTotal_derate(list.get(0).getTotal_derate().add(bean.getThis_amount_relief()));//历史减免额b
			t.setBq_cur_late_fee(bean.getThis_late_fees());//本期应还滞纳金 b
		}
		if(t.getUn_repay_interest().compareTo(new BigDecimal(100))<=0){//如果剩余利息小于100包含一百则此一百利息滚到下一期本期还款结束
			//listNew1.get(0).setun_total_repayment(listNew1.get(0).getun_total_repayment().add(t.getUn_repay_interest()));//将本期未还利息滚到下期  --下期未还总额
			if(listNew1!=null&&listNew1.size()>0){
				listNew1.get(0).setOrg_repay_interest(listNew1.get(0).getOrg_repay_interest().add(t.getUn_repay_interest()));//将本期未还利息滚到下期--下期其中利息(本期利息+之前欠息)
				listNew1.get(0).setUn_repay_interest(listNew1.get(0).getUn_repay_interest().add(t.getUn_repay_interest()));//下期未还利息
			}
			t.setUn_repay_interest(new BigDecimal(0));//将本期未还利息值0
		}
		t.setAdjustment_amount(bean.getAdjustment_amount());//调整额
		
		if(listNew1!=null&&listNew1.size()>0){
			wmsFinaCreRealrepayInfoDao.updateRecord(listNew1.get(0));
		}
		//期还款台账处理
		WmsFinaCrePeriodRepay  wmsFinaCrePeriodRepay=new WmsFinaCrePeriodRepay();
		wmsFinaCrePeriodRepay.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
		List<WmsFinaCrePeriodRepay> listr=wmsFinaCrePeriodRepayDao.getListByEntity(wmsFinaCrePeriodRepay);//根据贷款主表查询全部期还款台账 
		if(listr ==null||listr.size()<bean.getRepay_no()){
			resStr = "error";//期还款台账错误
			return resStr;
		}
		wmsFinaCrePeriodRepay=listr.get(bean.getRepay_no());//获取下期期还款台账
		//更新还款信息表
		WmsFinaCreRepay wRepay = new WmsFinaCreRepay();
		wRepay.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
		List<WmsFinaCreRepay>listRepay=wmsFinaCreRepayDao.getListByEntity(wRepay);
		WmsFinaCreRepay wmsFinaCreRepay=new WmsFinaCreRepay();
		wmsFinaCreRepay.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());//贷款信息表主键
		wmsFinaCreRepay.setRepay_principal(listRepay.get(0).getRepay_principal().add(bean.getThis_principal()));//已还本金
		wmsFinaCreRepay.setRepay_interest(listRepay.get(0).getRepay_interest().add(bean.getThis_interest()));//已还利息
		wmsFinaCreRepay.setUn_pay_principal(listRepay.get(0).getPrincipal().subtract(wmsFinaCreRepay.getRepay_principal()));//剩余本金
		wmsFinaCreRepay.setUn_pay_interest(listRepay.get(0).getInterest().subtract(wmsFinaCreRepay.getRepay_interest()));//剩余利息
		wmsFinaCreRepay.setMatching_creditor((wmsFinaCreRepay.getUn_pay_principal().add(wmsFinaCreRepay.getUn_pay_interest())).multiply(new BigDecimal(sysPropertyDao.get(23).getProperty_value())));//可匹配的债权
		if(queryInfo.getRepay_date()!=null&&!"".equals(queryInfo.getRepay_date())){//存在实际还款额
			wmsFinaCreRepay.setTotal_overdue_day(listRepay.get(0).getTotal_overdue_day());//总逾期天数
			wmsFinaCreRepay.setTotal_late_fee(listRepay.get(0).getTotal_late_fee());//总应滞纳金额b
			wmsFinaCreRepay.setTotal_pay_late_fee(listRepay.get(0).getTotal_pay_late_fee());//已缴纳滞纳金额b
			wmsFinaCreRepay.setTotal_derate(listRepay.get(0).getTotal_derate());//总减免额b
		}else{
			wmsFinaCreRepay.setTotal_overdue_day(listRepay.get(0).getTotal_overdue_day()+listRepay.get(0).getCur_overdue_day());//总逾期天数
			wmsFinaCreRepay.setTotal_late_fee(new BigDecimal(listRepay.get(0).getTotal_late_fee()).add(listRepay.get(0).getCur_late_fee()).intValue());//总应滞纳金额b
			wmsFinaCreRepay.setTotal_pay_late_fee(listRepay.get(0).getTotal_pay_late_fee().add(bean.getThis_amount_relief()));//已缴纳滞纳金额b
			wmsFinaCreRepay.setTotal_derate(listRepay.get(0).getTotal_derate().add(bean.getThis_amount_relief()));//总减免额b	
			wmsFinaCreRepay.setCur_overdue_type("4");//4、正常
		}
		if(t.getUn_repay_interest().compareTo(new BigDecimal(100))<=0){//如果应还款表中未还利息为小于100的话则此期还款结束  还款状态为正常
			wmsFinaCreRepay.setRepay_status("1");//还款状态为正常
			wmsFinaCreRepay.setUn_pay_period(listRepay.get(0).getUn_pay_period()-1);//未还期数
			wmsFinaCreRepay.setRepay_period(listRepay.get(0).getRepay_period()+1);//已还期数
			wmsFinaCreRepay.setCurrent_repay_date(wmsFinaCrePeriodRepay.getCurrent_repay_date());//本期应还款日期
		}
		//判断是否为结清状态
		if(wmsFinaCreRepay.getUn_pay_principal().compareTo(new BigDecimal(0))==0&&wmsFinaCreRepay.getUn_pay_interest().compareTo(new BigDecimal(0))<=0){//未还本金未还利息都为0
			if((new BigDecimal(wmsFinaCreRepay.getTotal_late_fee()).subtract(wmsFinaCreRepay.getTotal_pay_late_fee()).subtract(wmsFinaCreRepay.getTotal_derate())).compareTo(new BigDecimal(0))<=0){//总应缴纳滞纳金-已还滞纳金-减免额小于等于0
				wmsFinaCreRepay.setClean_up_status("1");//结清状态1、正常结清
				wmsFinaCreRepay.setRepay_status("3");//3、结清
				wmsFinaCreRepay.setClean_up_date(new java.sql.Date(System.currentTimeMillis()));//结清日期
			}
		}
		wmsFinaCreRepayDao.updateByEntity(wmsFinaCreRepay);
		//更新期还款台账表
		wmsFinaCrePeriodRepay=listr.get(bean.getRepay_no()-1);//获取当前的期还款台账
		wmsFinaCrePeriodRepay.setRepay_date(new java.sql.Date(System.currentTimeMillis()));//实际还款日期
		wmsFinaCrePeriodRepay.setRepay_total(wmsFinaCrePeriodRepay.getOrg_repay_principal().add(wmsFinaCrePeriodRepay.getOrg_repay_interest()));//实际还款总额
		wmsFinaCrePeriodRepay.setRepay_principal(wmsFinaCrePeriodRepay.getOrg_repay_principal());//实际还款本金
		wmsFinaCrePeriodRepay.setRepay_interest(wmsFinaCrePeriodRepay.getOrg_repay_interest());//应还款利息
		wmsFinaCrePeriodRepay.setIs_overdue("1");//是否逾期
		wmsFinaCrePeriodRepay.setCur_overdue_day(wmsFinaCreRepay.getCur_overdue_day());//逾期天数
		wmsFinaCrePeriodRepay.setCur_late_fee(bean.getThis_amount_relief());//滞纳金额
		wmsFinaCrePeriodRepay.setDerate(bean.getThis_amount_relief());//减免金额
		if(queryInfo.getRepay_date()!=null&&!"".equals(queryInfo.getRepay_date())){//存在实际还款时间
			//期还款台账处理
			wmsFinaCrePeriodRepay.setRepay_date(queryInfo.getRepay_date());//实际还款日期
			wmsFinaCrePeriodRepay.setIs_overdue("0");//是否逾期
			wmsFinaCrePeriodRepay.setCur_overdue_day(0);//逾期天数
			wmsFinaCrePeriodRepay.setCur_late_fee(new BigDecimal(0));//滞纳金额
		}
		wmsFinaCrePeriodRepayDao.update(wmsFinaCrePeriodRepay);//更新期还款台账表
		wmsFinaCreRealrepayInfoDao.updateRecord(t);//应还款表
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}
	/**
	 * 集中向还款明细表中存储相应的数据:根据本次分配的
	 * 本金this_principal 
	 * 利息this_interest   
	 * 滞纳金this_late_fees   
	 * 调整额adjustment_amount 
	 * 减免额this_amount_relief 还款项类型决定有几条明细需要记录
	 * @param bean
	 * @param systime
	 * @param ret
	 * @return
	 */
	private int setWmsFinaCreRepaymentDetailsBean(WmsFinaCreRepaymentHistory bean,long systime,int ret){
		WmsFinaCrePeriodRepay wCrePeriodRepay = new WmsFinaCrePeriodRepay();
		wCrePeriodRepay.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
		wCrePeriodRepay.setRepay_no(bean.getRepay_no());
		//查询符合期数和贷款信息表主键对应的期还款台账主键
		List<WmsFinaCrePeriodRepay>list=wmsFinaCrePeriodRepayDao.getListByEntity(wCrePeriodRepay);
		if(bean.getThis_principal()!=null){
			//初始化存储明细表中的数据
			WmsFinaCreRepaymentDetails wmsFinaCreRepaymentDetails = new WmsFinaCreRepaymentDetails();
			wmsFinaCreRepaymentDetails.setWms_fina_cre_repayment_history_id(bean.getWms_fina_cre_repayment_history_id());//还款历史主键
			wmsFinaCreRepaymentDetails.setRepay_no(bean.getRepay_no());//还款期数
			wmsFinaCreRepaymentDetails.setRepayment_history_code(bean.getRepayment_history_code());//还款历史流水编号
			wmsFinaCreRepaymentDetails.setRepayment_date(new java.sql.Date(systime));//还款日期
			wmsFinaCreRepaymentDetails.setWms_cre_appro_id(bean.getWms_cre_appro_id());
			wmsFinaCreRepaymentDetails.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
			wmsFinaCreRepaymentDetails.setWms_fina_cre_pay_id(bean.getWms_fina_cre_pay_id());
			wmsFinaCreRepaymentDetails.setWms_fina_cre_period_pay_id(list.get(0).getWms_fina_cre_period_pay_id());
			wmsFinaCreRepaymentDetails.setEnable_flag("1");
			wmsFinaCreRepaymentDetails.setRepayment_style("本金");//还款项类型
			wmsFinaCreRepaymentDetails.setRepayment_price(bean.getThis_principal());//本次还的本金
			wmsFinaCreRepaymentDetails.setThis_repayment_remark(bean.getThis_repayment_remark());//备注
			ret=wmsFinaCreRepaymentDetailsDao.save(wmsFinaCreRepaymentDetails);
		}
		if(bean.getThis_interest()!=null){
			//初始化存储明细表中的数据
			WmsFinaCreRepaymentDetails wmsFinaCreRepaymentDetails = new WmsFinaCreRepaymentDetails();
			wmsFinaCreRepaymentDetails.setWms_fina_cre_repayment_history_id(bean.getWms_fina_cre_repayment_history_id());//还款历史主键
			wmsFinaCreRepaymentDetails.setRepay_no(bean.getRepay_no());//还款期数
			wmsFinaCreRepaymentDetails.setRepayment_history_code(bean.getRepayment_history_code());//还款历史流水编号
			wmsFinaCreRepaymentDetails.setRepayment_date(new java.sql.Date(systime));//还款日期
			wmsFinaCreRepaymentDetails.setWms_cre_appro_id(bean.getWms_cre_appro_id());
			wmsFinaCreRepaymentDetails.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
			wmsFinaCreRepaymentDetails.setWms_fina_cre_pay_id(bean.getWms_fina_cre_pay_id());
			wmsFinaCreRepaymentDetails.setWms_fina_cre_period_pay_id(list.get(0).getWms_fina_cre_period_pay_id());
			wmsFinaCreRepaymentDetails.setEnable_flag("1");
			wmsFinaCreRepaymentDetails.setRepayment_style("利息");//还款类型
			wmsFinaCreRepaymentDetails.setRepayment_price(bean.getThis_interest());//本次还的利息
			wmsFinaCreRepaymentDetails.setThis_repayment_remark(bean.getThis_repayment_remark());//备注
			ret=wmsFinaCreRepaymentDetailsDao.save(wmsFinaCreRepaymentDetails);
		}
		if(bean.getThis_late_fees()!=null){
			//初始化存储明细表中的数据
			WmsFinaCreRepaymentDetails wmsFinaCreRepaymentDetails = new WmsFinaCreRepaymentDetails();
			wmsFinaCreRepaymentDetails.setWms_fina_cre_repayment_history_id(bean.getWms_fina_cre_repayment_history_id());//还款历史主键
			wmsFinaCreRepaymentDetails.setRepay_no(bean.getRepay_no());//还款期数
			wmsFinaCreRepaymentDetails.setRepayment_history_code(bean.getRepayment_history_code());//还款历史流水编号
			wmsFinaCreRepaymentDetails.setRepayment_date(new java.sql.Date(systime));//还款日期
			wmsFinaCreRepaymentDetails.setWms_cre_appro_id(bean.getWms_cre_appro_id());
			wmsFinaCreRepaymentDetails.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
			wmsFinaCreRepaymentDetails.setWms_fina_cre_pay_id(bean.getWms_fina_cre_pay_id());
			wmsFinaCreRepaymentDetails.setWms_fina_cre_period_pay_id(list.get(0).getWms_fina_cre_period_pay_id());
			wmsFinaCreRepaymentDetails.setEnable_flag("1");
			wmsFinaCreRepaymentDetails.setRepayment_style("滞纳金");
			wmsFinaCreRepaymentDetails.setRepayment_price(bean.getThis_late_fees());//本次还的滞纳金
			wmsFinaCreRepaymentDetails.setThis_repayment_remark(bean.getThis_repayment_remark());//备注
			ret=wmsFinaCreRepaymentDetailsDao.save(wmsFinaCreRepaymentDetails);
		}
		if(bean.getAdjustment_amount()!=null){
			//初始化存储明细表中的数据
			WmsFinaCreRepaymentDetails wmsFinaCreRepaymentDetails = new WmsFinaCreRepaymentDetails();
			wmsFinaCreRepaymentDetails.setWms_fina_cre_repayment_history_id(bean.getWms_fina_cre_repayment_history_id());//还款历史主键
			wmsFinaCreRepaymentDetails.setRepay_no(bean.getRepay_no());//还款期数
			wmsFinaCreRepaymentDetails.setRepayment_history_code(bean.getRepayment_history_code());//还款历史流水编号
			wmsFinaCreRepaymentDetails.setRepayment_date(new java.sql.Date(systime));//还款日期
			wmsFinaCreRepaymentDetails.setWms_cre_appro_id(bean.getWms_cre_appro_id());
			wmsFinaCreRepaymentDetails.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
			wmsFinaCreRepaymentDetails.setWms_fina_cre_pay_id(bean.getWms_fina_cre_pay_id());
			wmsFinaCreRepaymentDetails.setWms_fina_cre_period_pay_id(list.get(0).getWms_fina_cre_period_pay_id());
			wmsFinaCreRepaymentDetails.setEnable_flag("1");
			wmsFinaCreRepaymentDetails.setRepayment_style("调整额");
			wmsFinaCreRepaymentDetails.setRepayment_price(bean.getAdjustment_amount());//本次调整额
			wmsFinaCreRepaymentDetails.setThis_repayment_remark(bean.getThis_repayment_remark());//备注
			ret=wmsFinaCreRepaymentDetailsDao.save(wmsFinaCreRepaymentDetails);
		}
		if(bean.getThis_amount_relief()!=null){
			//初始化存储明细表中的数据
			WmsFinaCreRepaymentDetails wmsFinaCreRepaymentDetails = new WmsFinaCreRepaymentDetails();
			wmsFinaCreRepaymentDetails.setWms_fina_cre_repayment_history_id(bean.getWms_fina_cre_repayment_history_id());//还款历史主键
			wmsFinaCreRepaymentDetails.setRepay_no(bean.getRepay_no());//还款期数
			wmsFinaCreRepaymentDetails.setRepayment_history_code(bean.getRepayment_history_code());//还款历史流水编号
			wmsFinaCreRepaymentDetails.setRepayment_date(new java.sql.Date(systime));//还款日期
			wmsFinaCreRepaymentDetails.setWms_cre_appro_id(bean.getWms_cre_appro_id());
			wmsFinaCreRepaymentDetails.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
			wmsFinaCreRepaymentDetails.setWms_fina_cre_pay_id(bean.getWms_fina_cre_pay_id());
			wmsFinaCreRepaymentDetails.setWms_fina_cre_period_pay_id(list.get(0).getWms_fina_cre_period_pay_id());
			wmsFinaCreRepaymentDetails.setEnable_flag("1");
			wmsFinaCreRepaymentDetails.setRepayment_style("减免额");
			wmsFinaCreRepaymentDetails.setRepayment_price(bean.getThis_amount_relief());//本次减免额
			wmsFinaCreRepaymentDetails.setThis_repayment_remark(bean.getThis_repayment_remark());//备注
			ret=wmsFinaCreRepaymentDetailsDao.save(wmsFinaCreRepaymentDetails);
		}
		return ret;
	}
	/**
	 * 实现集中获取还款历史信息
	 * @param bean
	 * @param user
	 * @return
	 */
	private WmsFinaCreRepaymentHistory setWmsFinaCreRepaymentHistoryBean(WmsFinaCreRepaymentHistory bean,UserBean user,long systime,WmsFinaCreRepaymentHistorySearchBeanVO queryInfo){
		bean.setRepay_no(bean.getRepay_no()+1);//期数
		bean.setRepayment_history_code(CodeNoUtil.getFinaHKCode());//还款流水编码
		bean.setRepayment_date(new java.sql.Date(systime));//还款日期
		Timestamp time =new Timestamp(systime);//系统时间
		bean.setCreate_user_id(user.getUserId());//创建人
		bean.setCreate_user_name(user.getRealname());//创建人姓名
		bean.setCreate_user_datetime(time);//创建日期
		bean.setCreate_user_dept_id(user.getDeptId().toString());//创建人部门ID
		bean.setEnable_flag("1");
		if(queryInfo.getThis_collateral_ids()!=null){
			bean.setThis_collateral_ids(queryInfo.getThis_collateral_ids().replace(',','#'));//存在的抵押ID,变换格式2#4#7			
		}
		WmsFinaCreRealrepayInfo t = new WmsFinaCreRealrepayInfo();
		t.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());//还款信息表主键
		t.setRepay_no(String.valueOf(bean.getRepay_no()));//对应现在的所处的还款期数
		List<WmsFinaCreRealrepayInfo> listNew =wmsFinaCreRealrepayInfoDao.getListByEntity(t);
		if(listNew.get(0).getTotal_repayment().compareTo(listNew.get(0).getRepay_principal().add(listNew.get(0).getRepay_interest()))==0){
			bean.setThe_clear_marks(1);
		}else{
			bean.setThe_clear_marks(0);
		}
		return bean;
	}

	@Override
	@Transactional
	public String update(WmsFinaCreRepaymentHistory bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsfinacrerepaymenthistoryDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsFinaCreRepaymentHistory() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsFinaCreRepaymentHistory> getListByEntity(WmsFinaCreRepaymentHistory queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsFinaCreRepaymentHistory> beanList = wmsfinacrerepaymenthistoryDao.getListByEntity(queryInfo);
		return beanList;
	}
	/**
	 * Description :根据 wms_cre_credit_head_id 查询历史信息
	 * @param primary key 
	 * @return Map
	 * @author baisong
	 */	
	@Override
	public  Map<String, Object> getInfoByHK(Integer wms_cre_credit_head_id) {
		Map<String, Object> map=new HashMap<>();
		List<WmsPostRemindHistory>	list = wmsfinacrerepaymenthistoryDao.getlist(wms_cre_credit_head_id);
		map.put("Rows", list);
		return map;
	}
	
	/***
	 * 根据wms_cre_credit_head_id查询多条逾期历史
	 * @param wms_cre_credit_head_id
	 * @return list
	 * baisong
	 */
	@Override
	public Map<String, Object> searchHistory(
			WmsFinaCreRepaymentHistorySearchBeanVO queryInfo) {
		Map<String, Object> map=new HashMap<>();
		List<Map<String, Object>> list=wmsfinacrerepaymenthistoryDao.searchHistory(Integer.valueOf(queryInfo.getWms_cre_credit_head_id()));
		if(list==null||list.size()==0){
			map.put("Rows", list);
			return map;
		}
		/*int no=0;//记录当前是第几期
		int dayInt=0;//记录时间间隔
		Date current_repay_date=(Date) list.get(0).get("current_repay_date");//本期应还款日期
		for(int i=0;i<list.size();i++){
			String repay_date_str="";
			Map<String, Object> map1=list.get(i);
			Date repayment_date=(Date) map1.get("repayment_date");//实际还款日期
			BigDecimal late_fees=new BigDecimal(0);
			if(no!=(int)map1.get("repay_no")){
				no=(int)map1.get("repay_no");
				dayInt= calInterval(current_repay_date,repayment_date,"D");//计算俩个日期相差多少天
				repay_date_str=current_repay_date.toString()+"-"+repayment_date.toString();
			}else{
				dayInt= calInterval((Date) list.get(i-1).get("repayment_date"),repayment_date,"D");//计算俩个日期相差多少天
				late_fees=new BigDecimal(dayInt).multiply( toBigDecimal(map1.get("liquidated_damages")));
				//返回的结果是int类型，-1表示小于，0是等于，1是大于。
				if(toBigDecimal(map1.get("this_late_fees")).compareTo(late_fees)==-1){
					//自动计算滞纳金
					late_fees=late_fees.add(toBigDecimal(list.get(i-1).get("late_fees")).subtract(toBigDecimal(list.get(i-1).get("this_late_fees"))).subtract(toBigDecimal(list.get(i-1).get("this_amount_relief"))));
				}
				repay_date_str=list.get(i-1).get("repayment_date")+"-"+repayment_date.toString();//逾期日期 从哪天到哪天
			}
			map1.put("dayInt", dayInt);//记录时间间隔
			map1.put("late_fees", late_fees);//在时间间隔内-应还滞纳金
			map1.put("repay_date_str", repay_date_str);//在时间间隔内-应还滞纳金
		}*/
		map.put("Rows", list);
		return map;
	}
	/**
	 * Description :根据 wms_cre_credit_head_id以及期数  查询对应期还款历史
	 * @param primary key 
	 * @return Map
	 * @author hancd
	 */	
    @Override
    public Map<String, Object> getInfoByRepaymentorRepayNo(
    		WmsFinaCreRepaymentHistorySearchBeanVO queryInfo) {
    	Map<String,Object> paramMap = new HashMap<>();
    	paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
    	paramMap.put("repay_no", queryInfo.getRepay_no());
    	List<Map<String,Object>> list=wmsfinacrerepaymenthistoryDao.searchByRepaymentforIdorNO(paramMap);
    	Map<String,Object>map= new HashMap<>();
    	map.put("Rows", list);
    	return map;
    }
	/**
	 * Description :逾期还款计算提成
	 * @param primary key 
	 * @return Map
	 * @author baisong 
	 */	
    @Override
    public Map<String, Object> getDraw(
    		WmsFinaDrawBeanVO queryInfo) {
    	int sDays=0;//逾期天数
    	int dDays=0;//多算天数
    	BigDecimal d_Damages=new BigDecimal(0);//多算的违约金
    	BigDecimal reality_return_amount=new BigDecimal(0);//实际缴回违约金金额
    	
    	Map<String,Object> paramMap = new HashMap<>();
    	//催缴主表
    	WmsPostDunningHead wmsPostDunningHead=wmsPostDunningHeadDao.get(queryInfo.getWms_post_dunning_head_id());
    	WmsFinaCreRepay wmsFinaCreRepay=wmsFinaCreRepayDao.get(queryInfo.getWms_fina_cre_pay_id());//还款表
    	paramMap.put("wms_post_dunning_head_id", wmsPostDunningHead.getWms_post_dunning_head_id());//催缴单主键
    	paramMap.put("wms_cre_credit_head_id", wmsFinaCreRepay.getWms_cre_credit_head_id());//还款信息表主键
    	//0:贷后主管；1：贷后专员； 2：上门人员
    	if(("".equals(wmsPostDunningHead.getHome_dunning_status())||wmsPostDunningHead.getHome_dunning_status()==null)&& "1".equals(wmsPostDunningHead.getLoansupervisor_result())){
    		/*List<Map<String, Object>> list=pmpersonnelDao.getPostInfo(wmsPostDunningHead.getFinal_dunning_id());//根据人员id获取人员职务信息
    		if(list!=null&&list.size()>0){//如果有 则此人为 主管
    			paramMap.put("userType", 0);//人员类型	
    		}else{
    			paramMap.put("userType", 1);//人员类型	
    		}*/
    		paramMap.put("userType", 1);//人员类型	
    		paramMap.put("userid", wmsPostDunningHead.getFinal_dunning_id());//人员id
    		paramMap.put("username", wmsPostDunningHead.getFinal_dunning_name());//人员name
    		paramMap.put("belonging", 1);//提成归属: 1 代表电话催缴   2代表上门催缴	
    	}else if((!"".equals(wmsPostDunningHead.getHome_dunning_status())&&wmsPostDunningHead.getHome_dunning_status()!=null)||("2".equals(wmsPostDunningHead.getLoansupervisor_result()))){
    		paramMap.put("userType", 2);//人员类型
    		paramMap.put("userid", wmsPostDunningHead.getVisit_dunning_id());//人员id
    		paramMap.put("username", wmsPostDunningHead.getVisit_dunning_name());//人员name
    		paramMap.put("belonging", 2);//提成归属: 1 代表电话催缴   2代表上门催缴
    		List<WmsPostDunningHead> listHead=wmsPostDunningHeadDao.getList4(wmsPostDunningHead.getWms_cre_credit_head_id());//获取对应的电话催缴单据
    		if(listHead!=null&&listHead.size()>0){//找到了电话催缴单据
    			/*paramMap.put("wms_post_dunning_head_id1", listHead.get(0).getWms_post_dunning_head_id());//
        		List<Map<String, Object>> listmap=pmpersonnelDao.getPostInfo(listHead.get(0).getFinal_dunning_id());//根据人员id获取人员职务信息
        		if(listmap!=null&&listmap.size()>0){//如果有 则此人为 主管
        			paramMap.put("userType1", 0);//人员类型	
        		}else{
        			paramMap.put("userType1", 1);//人员类型	
        		}*/	
        		paramMap.put("wms_post_dunning_head_id1", listHead.get(0).getWms_post_dunning_head_id());//
        		paramMap.put("userType1", 1);//人员类型	
    		}
    		
    	}
    	/*if(queryInfo.getRepay_date()!=null&&"".equals(queryInfo.getRepay_date())){///存在实际还款时间
    		//多算的逾期天数
    		dDays =	calInterval(queryInfo.getRepay_date(),getNow(),"D");//实际还款时间和当前时间的天数差
    		//实际逾期天数
    		sDays =	calInterval(wmsFinaCreRepay.getCurrent_repay_date(),queryInfo.getRepay_date(),"D");//应还款时间和实际还款时间的差
    		//多算的逾期滞纳金
    		d_Damages=wmsFinaCreRepay.getLiquidated_damages().multiply(new BigDecimal(dDays));
    		reality_return_amount=queryInfo.getThis_late_fees().subtract(d_Damages);//实际违约金
    	}else{
    		//实际逾期天数
			sDays =	calInterval(wmsFinaCreRepay.getCurrent_repay_date(),getNow(),"D");//应还款时间和实际还款时间的差
			reality_return_amount=queryInfo.getThis_late_fees();
    	}*/
    	sDays=wmsPostDunningHead.getOverdue_day();//逾期天数
    	reality_return_amount=queryInfo.getThis_late_fees();
    	paramMap.put("reality_return_amount", reality_return_amount);//实际缴回金额
    	BigDecimal ratio=SysTools.getReturnRatio(sDays,(int)paramMap.get("userType"));//计算提成(计算提成比例) 
    	paramMap.put("reality_return_ratio", ratio.movePointRight(2));//提成比率
    	paramMap.put("reality_return_ommission", SysTools.getReturnOmmission(reality_return_amount,ratio));//提成
    	if((int)paramMap.get("belonging")==2){//如果是上门催缴 需要计算电话催缴提成
    		paramMap.put("reality_return_amount1", reality_return_amount);//实际缴回金额
        	BigDecimal ratio1=SysTools.getReturnRatio(sDays,(int)paramMap.get("userType1"));//计算提成(计算提成比例) 
        	paramMap.put("reality_return_ratio1", ratio1.movePointRight(2));//提成比率
        	paramMap.put("reality_return_ommission1", SysTools.getReturnOmmission(reality_return_amount,ratio1));//提成
    	}
    	paramMap.put("expect_payment_amount", wmsPostDunningHead.getExpect_payment_amount());//预期缴回金额
    	paramMap.put("expect_return_amount", wmsPostDunningHead.getReturn_amount());//缴回违约金金额
    	paramMap.put("expect_return_ratio", wmsPostDunningHead.getReturn_ratio());//缴回比例
    	paramMap.put("expect_return_ommission", wmsPostDunningHead.getReturn_ommission());//提成
    	
    	
    	return paramMap;
    }
	/**
	 * 实现把每次实际还款总额 金额均匀匹配  优先级   等额本息  本金 利息 其他     先息后本  利息 本金  其他
	 */
	@Override
	public WmsFinaCreRealrepayInfo getFPPrive(
			WmsFinaCreRepaymentHistorySearchBeanVO queryInfo) {
		java.math.BigDecimal this_total_repayment=queryInfo.getThis_total_repayment();//本次实际还款总额
		java.math.BigDecimal this_amount_relief=queryInfo.getThis_amount_relief();//本次减免额
		
		//获取应还款表中对于本期还款情况的查询
		WmsFinaCreRealrepayInfo wmsFinaCreRealrepayInfo= new WmsFinaCreRealrepayInfo();
		wmsFinaCreRealrepayInfo.setWms_cre_credit_head_id(Integer.valueOf(queryInfo.getWms_cre_credit_head_id()));
		wmsFinaCreRealrepayInfo.setRepay_no(String.valueOf(queryInfo.getRepay_no()+1));
		List<WmsFinaCreRealrepayInfo> list =wmsFinaCreRealrepayInfoDao.getListByEntity(wmsFinaCreRealrepayInfo);
		
		//根据产品类型分类
		WmsFinaCreRealrepayInfo creRealrepayInfo= new WmsFinaCreRealrepayInfo();
		//if(queryInfo.getPayment_contract_type()==1){//等额本息
			if(list.get(0).getRepay_principal().compareTo(list.get(0).getOrg_repay_principal())==0)
				//本金还完
				{
					//因本期本金已还清分配置0
					creRealrepayInfo.setRepay_principal(new BigDecimal(0));
					//判断：本次还款<=剩余应还利息    只能把本次金额填充到利息中 其它分配 置0
					if(this_total_repayment.compareTo(list.get(0).getOrg_repay_interest().subtract(list.get(0).getRepay_interest()))==0||this_total_repayment.compareTo(list.get(0).getOrg_repay_interest().subtract(list.get(0).getRepay_interest()))==-1){
						creRealrepayInfo.setRepay_interest(this_total_repayment);
						creRealrepayInfo.setBq_cur_late_fee(new BigDecimal(0));
						creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
					}
					//判断:本次还款>剩余应还利息
					else if(this_total_repayment.compareTo(list.get(0).getOrg_repay_interest().subtract(list.get(0).getRepay_interest()))==1){
						//补全利息
						creRealrepayInfo.setRepay_interest(list.get(0).getOrg_repay_interest().subtract(list.get(0).getRepay_interest()));
						//补全利息剩余的金额：
						java.math.BigDecimal bqlxsyje =this_total_repayment.subtract(list.get(0).getOrg_repay_interest().subtract(list.get(0).getRepay_interest()));
						//本期不存在滞纳金
						if(list.get(0).getBq_cur_late_fee().compareTo(new BigDecimal(0))==0){
							//说明总金额没有  本期滞纳金不存在  就可以剩余的填充到调整额中
							creRealrepayInfo.setBq_cur_late_fee(new BigDecimal(0));
							creRealrepayInfo.setAdjustment_amount(bqlxsyje);
						}
						//本期存在滞纳金
						else if(list.get(0).getBq_cur_late_fee().compareTo(new BigDecimal(0))==1){
							//剩余利息金额<=本期滞纳金
							if(bqlxsyje.compareTo(list.get(0).getBq_cur_late_fee())==0||bqlxsyje.compareTo(list.get(0).getBq_cur_late_fee())==-1){
								//说明本次未填写减免额
								if(this_amount_relief.compareTo(new BigDecimal(0))==0){
									creRealrepayInfo.setBq_cur_late_fee(bqlxsyje);
									creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
								}
								//说明本次填写减免额
								else{
									//仍欠的滞纳金金额=本期滞纳金-剩余利息剩余金额
									java.math.BigDecimal bqsy=list.get(0).getBq_cur_late_fee().subtract(bqlxsyje);
									//本次减免额=仍欠的滞纳金金额
									if(bqsy.compareTo(this_amount_relief)==0||bqsy.compareTo(this_amount_relief)==-1){
										creRealrepayInfo.setBq_cur_late_fee(bqlxsyje.add(bqsy));
										creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
										//把本次的减免额添加到历史减免额中
										creRealrepayInfo.setTotal_derate(list.get(0).getTotal_derate().add(bqsy));
									}else{
										creRealrepayInfo.setBq_cur_late_fee(bqlxsyje.add(this_amount_relief));
										creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
										//把本次的减免额添加到历史减免额中
										creRealrepayInfo.setTotal_derate(list.get(0).getTotal_derate().add(this_amount_relief));
									}
								}
							}
							//剩余利息金额>本期滞纳金
							else if(bqlxsyje.compareTo(list.get(0).getBq_cur_late_fee())==1){
								//本期滞纳金起始等于滞纳金总额
								creRealrepayInfo.setBq_cur_late_fee(list.get(0).getBq_cur_late_fee());
								//补全本期滞纳金金额剩余金额
								java.math.BigDecimal bqbqznj =bqlxsyje.subtract(list.get(0).getBq_cur_late_fee());
								//如果本期滞纳金能够补全，说明总滞纳金也不全部还完。
								creRealrepayInfo.setAdjustment_amount(bqbqznj);
							}
						}
					}
				}
			   //本金没还完
				else if(list.get(0).getRepay_principal().compareTo(list.get(0).getOrg_repay_principal())==-1)
				{
					//本次还款额<=剩余应还款本金：说明本次还款只能填充本金  分配本金=this_total_repayment 分配利息为0  分配滞纳金0  分配调整0
					if(this_total_repayment.compareTo(list.get(0).getOrg_repay_principal().subtract(list.get(0).getRepay_principal()))==0
							||(this_total_repayment.compareTo(list.get(0).getOrg_repay_principal().subtract(list.get(0).getRepay_principal()))==-1)){
						creRealrepayInfo.setRepay_principal(this_total_repayment);
						creRealrepayInfo.setRepay_interest(new BigDecimal(0));
						creRealrepayInfo.setBq_cur_late_fee(new BigDecimal(0));
						creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
					}
					//本次还款额>剩余应还款本金：说明本次还款可以补全本金  补全本金  剩余金额 可以补利息 滞纳金 调整额
					else if(this_total_repayment.compareTo(list.get(0).getOrg_repay_principal().subtract(list.get(0).getRepay_principal()))==1)
					{
						//补全本金
						creRealrepayInfo.setRepay_principal(list.get(0).getOrg_repay_principal().subtract(list.get(0).getRepay_principal()));
						//补全本金后剩余的金额：
						java.math.BigDecimal bqbjsyje=this_total_repayment.subtract(list.get(0).getOrg_repay_principal().subtract(list.get(0).getRepay_principal()));
						//判断剩余金额<=记录中的剩余应还利息 :说明剩余金额 只够填充利息  其他分配为0   利息：bqbjsyje 滞纳金为0  调整额为0
						if(bqbjsyje.compareTo(list.get(0).getOrg_repay_interest().subtract(list.get(0).getRepay_interest()))==0 
								||bqbjsyje.compareTo(list.get(0).getOrg_repay_interest().subtract(list.get(0).getRepay_interest()))==-1){
							creRealrepayInfo.setRepay_interest(bqbjsyje);
							creRealrepayInfo.setBq_cur_late_fee(new BigDecimal(0));
							creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
						}
						//判断剩余金额>记录中的剩余应还利息 可以补全利息  剩余可以进行 滞纳金 调整额的分配
						else if(bqbjsyje.compareTo(list.get(0).getOrg_repay_interest().subtract(list.get(0).getRepay_interest()))==1){
							//补全利息
							creRealrepayInfo.setRepay_interest(list.get(0).getOrg_repay_interest().subtract(list.get(0).getRepay_interest()));
							//补全利息后的剩余金额：
							java.math.BigDecimal bqlxsyje=bqbjsyje.subtract(list.get(0).getOrg_repay_interest().subtract(list.get(0).getRepay_interest()));
							//本期不存在滞纳金
							if(list.get(0).getBq_cur_late_fee().compareTo(new BigDecimal(0))==0){
								creRealrepayInfo.setBq_cur_late_fee(new BigDecimal(0));
								creRealrepayInfo.setAdjustment_amount(bqlxsyje);
							}
							//本期存在滞纳金
							else{
								creRealrepayInfo.setTotal_derate(list.get(0).getTotal_derate().add(this_amount_relief));
								//剩余利息金额<=本期滞纳金
								if(bqlxsyje.compareTo(list.get(0).getBq_cur_late_fee())==0||bqlxsyje.compareTo(list.get(0).getBq_cur_late_fee())==-1){
									//说明本次未填写减免额
									if(this_amount_relief.compareTo(new BigDecimal(0))==0){
										creRealrepayInfo.setBq_cur_late_fee(bqlxsyje);
										creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
									}
									//说明本次填写减免额
									else{
										//仍欠的滞纳金金额=本期滞纳金-剩余利息剩余金额
										java.math.BigDecimal bqsy=list.get(0).getBq_cur_late_fee().subtract(bqlxsyje);
										//本次减免额=仍欠的滞纳金金额
										if(bqsy.compareTo(this_amount_relief)==0||bqsy.compareTo(this_amount_relief)==-1){
											creRealrepayInfo.setBq_cur_late_fee(bqlxsyje.add(bqsy));
											creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
											//把本次的减免额添加到历史减免额中
											creRealrepayInfo.setTotal_derate(list.get(0).getTotal_derate().add(bqsy));
										}else{
											creRealrepayInfo.setBq_cur_late_fee(bqlxsyje.add(this_amount_relief));
											creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
											//把本次的减免额添加到历史减免额中
											creRealrepayInfo.setTotal_derate(list.get(0).getTotal_derate().add(this_amount_relief));
										}
									}
								}
								//剩余利息金额>本期滞纳金
								else if(bqlxsyje.compareTo(list.get(0).getBq_cur_late_fee())==1){
									//本期滞纳金起始等于滞纳金总额
									creRealrepayInfo.setBq_cur_late_fee(list.get(0).getBq_cur_late_fee());
									//补全本期滞纳金金额剩余金额
									java.math.BigDecimal bqbqznj =bqlxsyje.subtract(list.get(0).getBq_cur_late_fee());
									//如果本期滞纳金能够补全，说明总滞纳金也不全部还完。
									creRealrepayInfo.setAdjustment_amount(bqbqznj);
								}
							}
						}
					}
				}
		//}
		
//		else if(queryInfo.getPayment_contract_type()==2){//先息后本
//			//判断是否是最后一期
//			WmsFinaCreRepay wCreRepay = new WmsFinaCreRepay();
//			wCreRepay.setWms_cre_credit_head_id(Integer.valueOf(queryInfo.getWms_cre_credit_head_id()));
//			List<WmsFinaCreRepay> listRepay=wmsFinaCreRepayDao.getListByEntity(wCreRepay);
//			/**********************************************************************************将要还的这期为最后一期 应补本金*/
//			if((queryInfo.getRepay_no()+1)==listRepay.get(0).getBorrow_deadline()){
//				//代表记录中本金还完  只需处理本期滞纳金  滞纳金总额
//				if(list.get(0).getRepay_principal().compareTo(list.get(0).getOrg_repay_principal())==0){
//					//本期不存在滞纳金
//					if(list.get(0).getBq_cur_late_fee().compareTo(new BigDecimal(0))==0){
//						creRealrepayInfo.setBq_cur_late_fee(new BigDecimal(0));
//						creRealrepayInfo.setAdjustment_amount(this_total_repayment);
//					}
//					//本期存在滞纳金
//					else{
//						creRealrepayInfo.setTotal_derate(list.get(0).getTotal_derate().add(this_amount_relief));
//						//剩余利息金额<=本期滞纳金
//						if(this_total_repayment.compareTo(list.get(0).getBq_cur_late_fee())==0||this_total_repayment.compareTo(list.get(0).getBq_cur_late_fee())==-1){
//							//说明本次未填写减免额
//							if(this_amount_relief.compareTo(new BigDecimal(0))==0){
//								creRealrepayInfo.setBq_cur_late_fee(this_total_repayment);
//								creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
//							}
//							//说明本次填写减免额
//							else{
//								//仍欠的滞纳金金额=本期滞纳金-剩余利息剩余金额
//								java.math.BigDecimal bqsy=list.get(0).getBq_cur_late_fee().subtract(this_total_repayment);
//								//本次减免额=仍欠的滞纳金金额
//								if(bqsy.compareTo(this_amount_relief)==0||bqsy.compareTo(this_amount_relief)==-1){
//									creRealrepayInfo.setBq_cur_late_fee(this_total_repayment.add(bqsy));
//									creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
//									//把本次的减免额添加到历史减免额中
//									creRealrepayInfo.setTotal_derate(list.get(0).getTotal_derate().add(bqsy));
//								}else{
//									creRealrepayInfo.setBq_cur_late_fee(this_total_repayment.add(this_amount_relief));
//									creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
//									//把本次的减免额添加到历史减免额中
//									creRealrepayInfo.setTotal_derate(list.get(0).getTotal_derate().add(this_amount_relief));
//								}
//							}
//						}
//						//剩余利息金额>本期滞纳金
//						else if(this_total_repayment.compareTo(list.get(0).getBq_cur_late_fee())==1){
//							//本期滞纳金起始等于滞纳金总额
//							creRealrepayInfo.setBq_cur_late_fee(list.get(0).getBq_cur_late_fee());
//							//补全本期滞纳金金额剩余金额
//							java.math.BigDecimal bqbqznj =this_total_repayment.subtract(list.get(0).getBq_cur_late_fee());
//							//如果本期滞纳金能够补全，说明总滞纳金也不全部还完。
//							creRealrepayInfo.setAdjustment_amount(bqbqznj);
//						}
//					}
//					
//				}
//				//代表记录中本金未还完   补全本金  利息无需处理   其他
//				else if(list.get(0).getRepay_principal().compareTo(list.get(0).getOrg_repay_principal())==-1){
//					//本次还款本额<=剩余未还本金
//					if(this_total_repayment.compareTo(list.get(0).getOrg_repay_principal().subtract(list.get(0).getRepay_principal()))==0
//							||this_total_repayment.compareTo(list.get(0).getOrg_repay_principal().subtract(list.get(0).getRepay_principal()))==-1){
//						creRealrepayInfo.setRepay_principal(this_total_repayment);
//						creRealrepayInfo.setRepay_interest(new BigDecimal(0));
//						creRealrepayInfo.setBq_cur_late_fee(new BigDecimal(0));
//						creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
//					}
//					//本次还款本额>剩余未还本金
//					else if(this_total_repayment.compareTo(list.get(0).getOrg_repay_principal().subtract(list.get(0).getRepay_principal()))==1){
//						//补全本金
//						creRealrepayInfo.setRepay_principal(list.get(0).getOrg_repay_principal().subtract(list.get(0).getRepay_principal()));
//						//补全本金后，剩余金额
//						java.math.BigDecimal bqbjsyje=this_total_repayment.subtract(list.get(0).getOrg_repay_principal().subtract(list.get(0).getRepay_principal()));
//						//对滞纳金情况进行查询处理
//						//本期不存在滞纳金
//						if(list.get(0).getBq_cur_late_fee().compareTo(new BigDecimal(0))==0){
//							creRealrepayInfo.setBq_cur_late_fee(new BigDecimal(0));
//							creRealrepayInfo.setAdjustment_amount(bqbjsyje);
//						}
//						//本期存在滞纳金
//						else{
//							creRealrepayInfo.setTotal_derate(list.get(0).getTotal_derate().add(this_amount_relief));
//							//剩余利息金额<=本期滞纳金
//							if(bqbjsyje.compareTo(list.get(0).getBq_cur_late_fee())==0||bqbjsyje.compareTo(list.get(0).getBq_cur_late_fee())==-1){
//								//说明本次未填写减免额
//								if(this_amount_relief.compareTo(new BigDecimal(0))==0){
//									creRealrepayInfo.setBq_cur_late_fee(bqbjsyje);
//									creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
//								}
//								//说明本次填写减免额
//								else{
//									//仍欠的滞纳金金额=本期滞纳金-剩余利息剩余金额
//									java.math.BigDecimal bqsy=list.get(0).getBq_cur_late_fee().subtract(bqbjsyje);
//									//本次减免额=仍欠的滞纳金金额
//									if(bqsy.compareTo(this_amount_relief)==0||bqsy.compareTo(this_amount_relief)==-1){
//										creRealrepayInfo.setBq_cur_late_fee(bqbjsyje.add(bqsy));
//										creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
//										//把本次的减免额添加到历史减免额中
//										creRealrepayInfo.setTotal_derate(list.get(0).getTotal_derate().add(bqsy));
//									}else{
//										creRealrepayInfo.setBq_cur_late_fee(bqbjsyje.add(this_amount_relief));
//										creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
//										//把本次的减免额添加到历史减免额中
//										creRealrepayInfo.setTotal_derate(list.get(0).getTotal_derate().add(this_amount_relief));
//									}
//								}
//							}
//							//剩余利息金额>本期滞纳金
//							else if(bqbjsyje.compareTo(list.get(0).getBq_cur_late_fee())==1){
//								//本期滞纳金起始等于滞纳金总额
//								creRealrepayInfo.setBq_cur_late_fee(list.get(0).getBq_cur_late_fee());
//								//补全本期滞纳金金额剩余金额
//								java.math.BigDecimal bqbqznj =bqbjsyje.subtract(list.get(0).getBq_cur_late_fee());
//								//如果本期滞纳金能够补全，说明总滞纳金也不全部还完。
//								creRealrepayInfo.setAdjustment_amount(bqbqznj);
//							}
//						}
//					}
//				}
//			}
//			/**************************************************************将要还的这期不是最后一期   先要补齐利息   本金无需补  再看其他*/
//			else if((queryInfo.getRepay_no()+1)!=listRepay.get(0).getBorrow_deadline()){
//				//代表记录中利息还完
//				if(list.get(0).getRepay_interest().compareTo(list.get(0).getOrg_repay_interest())==0)
//				{
//					creRealrepayInfo.setRepay_interest(new BigDecimal(0));
//					creRealrepayInfo.setRepay_principal(new BigDecimal(0));
//					//查看本期滞纳金
//					//本期滞纳金不存在
//		            if(list.get(0).getBq_cur_late_fee().compareTo(new BigDecimal(0))==0){
//		            	creRealrepayInfo.setBq_cur_late_fee(new BigDecimal(0));
//		            	creRealrepayInfo.setAdjustment_amount(this_total_repayment);
//		            }
//		            //本期滞纳金存在
//		            else{
//		            	//本期滞纳金<=本次还款额
//		            	if(list.get(0).getBq_cur_late_fee().compareTo(this_total_repayment)==0||list.get(0).getBq_cur_late_fee().compareTo(this_total_repayment)==-1){
//		            		creRealrepayInfo.setBq_cur_late_fee(list.get(0).getBq_cur_late_fee());
//		            		creRealrepayInfo.setAdjustment_amount(this_total_repayment.subtract(list.get(0).getBq_cur_late_fee()));
//		            	}
//		            	//本期滞纳金>本次还款额
//		            	else{
//		            		//说明本次没有抵款额
//		            		if(this_amount_relief.compareTo(new BigDecimal(0))==0){
//		            			creRealrepayInfo.setBq_cur_late_fee(this_total_repayment);
//		            			creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
//		            		}else{
//		            			//仍欠的滞纳金金额=本期滞纳金-剩余利息剩余金额
//								java.math.BigDecimal bqsy=list.get(0).getBq_cur_late_fee().subtract(this_total_repayment);
//								//本次减免额=仍欠的滞纳金金额
//								if(bqsy.compareTo(this_amount_relief)==0||bqsy.compareTo(this_amount_relief)==-1){
//									creRealrepayInfo.setBq_cur_late_fee(this_total_repayment.add(bqsy));
//									creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
//									//把本次的减免额添加到历史减免额中
//									creRealrepayInfo.setTotal_derate(list.get(0).getTotal_derate().add(bqsy));
//								}else{
//									creRealrepayInfo.setBq_cur_late_fee(this_total_repayment.add(this_amount_relief));
//									creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
//									//把本次的减免额添加到历史减免额中
//									creRealrepayInfo.setTotal_derate(list.get(0).getTotal_derate().add(this_amount_relief));
//								}
//		            		}
//		            	}
//		            }
//				}
//				//代表记录中利息未还完
//				else if(list.get(0).getRepay_interest().compareTo(list.get(0).getOrg_repay_interest())==-1)
//				{
//					//1.本次还款额<=剩余应还利息
//					if(this_total_repayment.compareTo(list.get(0).getOrg_repay_interest().subtract(list.get(0).getRepay_interest()))==0
//							||this_total_repayment.compareTo(list.get(0).getOrg_repay_interest().subtract(list.get(0).getRepay_interest()))==-1)
//					{
//						creRealrepayInfo.setRepay_interest(this_total_repayment);
//						creRealrepayInfo.setRepay_principal(new BigDecimal(0));
//						creRealrepayInfo.setBq_cur_late_fee(new BigDecimal(0));
//						creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
//					}
//					//2.本次还款额>剩余应还利息
//					else if(this_total_repayment.compareTo(list.get(0).getOrg_repay_interest().subtract(list.get(0).getRepay_interest()))==1)
//					{
//						//1.补全剩余利息
//						creRealrepayInfo.setRepay_interest(list.get(0).getOrg_repay_interest().subtract(list.get(0).getRepay_interest()));
//						//补全利息后，剩余部分金额：
//						java.math.BigDecimal bqlxsyje=this_total_repayment.subtract(list.get(0).getOrg_repay_interest().subtract(list.get(0).getRepay_interest()));
//						//2.查看滞纳金情况 
//						//本期不存在滞纳金
//						if(list.get(0).getBq_cur_late_fee().compareTo(new BigDecimal(0))==0){
//							creRealrepayInfo.setBq_cur_late_fee(new BigDecimal(0));
//							creRealrepayInfo.setAdjustment_amount(bqlxsyje);
//						}
//						//本期存在滞纳金
//						else{
//							creRealrepayInfo.setTotal_derate(list.get(0).getTotal_derate().add(this_amount_relief));
//							//剩余利息金额<=本期滞纳金
//							if(bqlxsyje.compareTo(list.get(0).getBq_cur_late_fee())==0||bqlxsyje.compareTo(list.get(0).getBq_cur_late_fee())==-1){
//								//说明本次未填写减免额
//								if(this_amount_relief.compareTo(new BigDecimal(0))==0){
//									creRealrepayInfo.setBq_cur_late_fee(bqlxsyje);
//									creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
//								}
//								//说明本次填写减免额
//								else{
//									//仍欠的滞纳金金额=本期滞纳金-剩余利息剩余金额
//									java.math.BigDecimal bqsy=list.get(0).getBq_cur_late_fee().subtract(bqlxsyje);
//									//本次减免额=仍欠的滞纳金金额
//									if(bqsy.compareTo(this_amount_relief)==0||bqsy.compareTo(this_amount_relief)==-1){
//										creRealrepayInfo.setBq_cur_late_fee(bqlxsyje.add(bqsy));
//										creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
//										//把本次的减免额添加到历史减免额中
//										creRealrepayInfo.setTotal_derate(list.get(0).getTotal_derate().add(bqsy));
//									}else{
//										creRealrepayInfo.setBq_cur_late_fee(bqlxsyje.add(this_amount_relief));
//										creRealrepayInfo.setAdjustment_amount(new BigDecimal(0));
//										//把本次的减免额添加到历史减免额中
//										creRealrepayInfo.setTotal_derate(list.get(0).getTotal_derate().add(this_amount_relief));
//									}
//								}
//							}
//							//剩余利息金额>本期滞纳金
//							else if(bqlxsyje.compareTo(list.get(0).getBq_cur_late_fee())==1){
//								//本期滞纳金起始等于滞纳金总额
//								creRealrepayInfo.setBq_cur_late_fee(list.get(0).getBq_cur_late_fee());
//								//补全本期滞纳金金额剩余金额
//								java.math.BigDecimal bqbqznj =bqlxsyje.subtract(list.get(0).getBq_cur_late_fee());
//								//如果本期滞纳金能够补全，说明总滞纳金也不全部还完。
//								creRealrepayInfo.setAdjustment_amount(bqbqznj);
//							}
//						}
//					}
//				}
//			}
//		}
		return creRealrepayInfo;
	}
	//转换成BigDecimal类型
	 private BigDecimal toBigDecimal(Object obj){
		 
		return new BigDecimal(obj.toString());
	}
	/**
     * 计算两个日期的时间间隔
     *@author baisong
     * */
    private static int calInterval(Date sDate, Date eDate, String type)
    {
        //时间间隔，初始为0
        int interval = 0;
         
        /*比较两个日期的大小，如果开始日期更大，则交换两个日期*/
        //标志两个日期是否交换过
        boolean reversed = false;
        if(compareDate(sDate, eDate) > 0)
        {
            Date dTest = sDate;
            sDate = eDate;
            eDate = dTest;
            //修改交换标志
            reversed = true;
        }       
        /*将两个日期赋给日历实例，并获取年、月、日相关字段值*/
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
        //年
        if(cTrim(type).equals("Y") || cTrim(type).equals("y"))
        {
            interval = eYears - sYears;
            if(eMonths < sMonths)
            {
                --interval;
            }
        }
        //月
        else if(cTrim(type).equals("M") || cTrim(type).equals("m"))
        {
            interval = 12 * (eYears - sYears);
            interval += (eMonths - sMonths);
        }
        //日
        else if(cTrim(type).equals("D") || cTrim(type).equals("d"))
        {
            interval = 365 * (eYears - sYears);
            interval += (eDays - sDays);
            //除去闰年天数
            while(sYears < eYears)
            {
                if(isLeapYear(sYears))
                {
                    --interval;
                }
                ++sYears;
            }
        }
        //如果开始日期更大，则返回负值
        if(reversed)
        {
            interval = -interval;
        }
        //返回计算结果
        return interval;
    }  
    /**
     * 判定某个年份是否是闰年
     *@author baisong
     * */
    private static boolean isLeapYear(int year)
    {
        return (year%400 == 0 || (year%4 == 0 && year%100 != 0));
    }      
   /**
   *
   * 字符串去除两头空格，如果为空，则返回""，如果不空，则返回该字符串去掉前后空格
   *@author baisong
   *
   */
   public static String cTrim(String tStr)
   {
       String ttStr = "";
       if (tStr == null)
       {}
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
     *
     * @param eDate结束时间
     *
     * @return result返回结果(0--相同  1--前者大  2--后者大)
     * */
    private static int compareDate(Date sDate, Date eDate)
    {
        int result = 0;
        //将开始时间赋给日历实例
        Calendar sC = Calendar.getInstance();
        sC.setTime(sDate);
        //将结束时间赋给日历实例
        Calendar eC = Calendar.getInstance();
        eC.setTime(eDate);
        //比较
        result = sC.compareTo(eC);
        //返回结果
        return result;
    }
    /**
     * 公共方法获取当前日期
     * @return
     */
    public static Date getNow(){
        Calendar nowcalendar = Calendar.getInstance();
        return new Date(nowcalendar.getTimeInMillis());
    }
    public static  void main(String a[]){
    	System.out.print(new BigDecimal(2).divide(new BigDecimal(1)));	
    }
}
