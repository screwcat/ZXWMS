package com.zx.emanage.loanpost.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.loanfina.persist.WmsFinaCrePeriodRepayDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreRepayDao;
import com.zx.emanage.loanfina.vo.WmsFinaCreRepaySearchBeanVO;
import com.zx.emanage.loanpost.persist.WmsPostDunningDetailedDao;
import com.zx.emanage.loanpost.persist.WmsPostDunningHeadDao;
import com.zx.emanage.loanpost.service.IWmsPostDunningHeadService;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.SysUserRoleDao;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepay;
import com.zx.emanage.loanpost.service.IWmsPostLoanWorkFlowService;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsCreRevPhoneMain;
import com.zx.emanage.util.gen.entity.WmsFinaCrePeriodRepay;
import com.zx.emanage.util.gen.entity.WmsPostDunningDetailed;
import com.zx.emanage.util.gen.entity.WmsPostDunningHead;
import com.zx.emanage.loanpost.vo.WmsAllocationSearchBeanVO;
import com.zx.emanage.loanpost.vo.WmsPostDunningDetailedSearchBeanVO;
import com.zx.emanage.loanpost.vo.WmsPostDunningHeadSearchBeanVO;
import com.zx.emanage.loanpost.vo.WmsPostLoanWorkFlowBeanVO;
import com.zx.emanage.loanreview.persist.WmsCreRevPhoneMainDao;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.SysUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmspostdunningheadService")
public class WmsPostDunningHeadServiceImpl implements IWmsPostDunningHeadService {
	private static Logger log = LoggerFactory.getLogger(WmsPostDunningHeadServiceImpl.class);

	@Autowired
	private WmsPostDunningHeadDao wmspostdunningheadDao;//贷后——催缴——催缴主表wms_post_dunning_head
	@Autowired
	private WmsPostDunningDetailedDao wmspostdunningdetailedDao;//贷后——催缴——催缴明细表wms_post_dunning_detailed
	@Autowired
	private WmsFinaCreRepayDao wmsFinaCreRepayDao;//贷款还款信息表
	@Autowired
	private IWmsPostLoanWorkFlowService wmsPostLoanWorkFlowService;//流程
	@Autowired
	private PmPersonnelDao personnelDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Autowired
	private WmsCreRevPhoneMainDao wmsCreRevPhoneMainDao;
	@Autowired
	private WmsFinaCrePeriodRepayDao wmsFinaCrePeriodRepayDao;
	
	@Override
	public Map<String, Object> getListWithoutPaging(WmsPostDunningHeadSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmspostdunningheadDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsPostDunningHeadSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmspostdunningheadDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmspostdunningheadDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsPostDunningHead getInfoByPK(Integer wms_post_dunning_head_id) {
		return wmspostdunningheadDao.get(wms_post_dunning_head_id);
	}

	@Override	
	@Transactional
	public String save(WmsPostDunningHead bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmspostdunningheadDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsPostDunningHead bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmspostdunningheadDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	/**
	 * 上门催缴   催缴单信息保存更新
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */	
	@Override
	@Transactional
	public String doSaveUpdateVisit(WmsPostDunningHead bean, UserBean user,WmsPostDunningDetailed beanDetailed,WmsPostDunningDetailedSearchBeanVO wmsPostDunningDetailedSearchBeanVO) {
		String resStr = "success";
		int ret = 0;
		bean.setFinal_dunning_id(wmsPostDunningDetailedSearchBeanVO.getDunning_id());//贷后专员id
		bean.setFinal_dunning_name(wmsPostDunningDetailedSearchBeanVO.getDunning_name());//贷后专员姓名
		bean.setFinal_dunning_deptid(wmsPostDunningDetailedSearchBeanVO.getDunning_deptId());//贷后专员部门id
		bean.setFinal_dunning_datetime(new Timestamp(System.currentTimeMillis()));//贷后专员部门时间
		/*if(wmsPostDunningDetailedSearchBeanVO.getFinal_dunning_name1()!=null){
			bean.setFinal_dunning_name(wmsPostDunningDetailedSearchBeanVO.getFinal_dunning_name1());//最后分配催缴专员
		}*/
		bean.setDunning_name(wmsPostDunningDetailedSearchBeanVO.getDebtor_name());//客户姓名
		bean.setDunning_tel(wmsPostDunningDetailedSearchBeanVO.getDebtor_tel());//联系电话
		bean.setProtocol_creat_date((wmsPostDunningDetailedSearchBeanVO.getProtocol_creat_date()));//签约日期
		bean.setDeadline(wmsPostDunningDetailedSearchBeanVO.getBorrow_deadline());//期数
		bean.setUn_matching_creditor(wmsPostDunningDetailedSearchBeanVO.getMatching_creditor());//剩余债权cur_overdue_day
		bean.setOverdue_day(wmsPostDunningDetailedSearchBeanVO.getCur_overdue_day());//逾期天数
		bean.setDunning_status("8");//催缴单据状态	8上门催缴
		if(bean.getHome_dunning_status()==null||bean.getHome_dunning_status()==""){
			bean.setHome_dunning_status("1");//上门催缴状态 1：内部催缴
		}
		//上门催缴提成计算
		compute(bean, wmsPostDunningDetailedSearchBeanVO, 2);
		
		//提交操作val为1
		if("1".equals(wmsPostDunningDetailedSearchBeanVO.getVal())){
			bean.setPost_dunning_commissioner_time(new Date(System.currentTimeMillis() ));;//催缴单据状态
			bean.setDunning_status(bean.getFinal_dunning_result());//催缴单据状态
			if(bean.getHome_dunning_status()!=null){
				String status []=bean.getHome_dunning_status().split("#");
				for(int i=0;i<status.length;i++){
					if("1".equals(status[i])){//外部催缴
						bean.setOutside_dunning_result(bean.getFinal_dunning_result());//外包催缴结果
						/*1：内部催缴2：外包催缴3：法务催缴4：呆账*/
					}
					if("2".equals(status[i])){//法务催缴
						bean.setLegalappeal_dunning_result(bean.getFinal_dunning_result());//法务上诉催缴结果
						/*1：内部催缴2：外包催缴3：法务催缴4：呆账*/
					}
					if("3".equals(status[i])){//呆账
						bean.setLegalappeal_dunning_result(bean.getFinal_dunning_result());//呆账上诉催缴结果
						/*1：内部催缴2：外包催缴3：法务催缴4：呆账*/
						bean.setHome_dunning_status("4");//上门催缴状态
						bean.setDunning_status("7");//催缴单据状态7>呆账
					}
				}
				/*WmsFinaCrePeriodRepay wr=new WmsFinaCrePeriodRepay();
				wr.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());//主键
				wr.setRepay_no(bean.getRepay_period()+1);//期数
				//计算提成比例和提成
				WmsFinaCrePeriodRepay wmsFinaCrePeriodRepay = wmsFinaCrePeriodRepayDao.getbyobject(wr);
				BigDecimal orgRepayPrincipal = new BigDecimal(0);
				BigDecimal orgRepayInterest = new BigDecimal(0);
				if(wmsFinaCrePeriodRepay != null) {
					orgRepayPrincipal = (BigDecimal) wmsFinaCrePeriodRepay.getOrg_repay_principal();//应还本金
					orgRepayInterest = (BigDecimal) wmsFinaCrePeriodRepay.getOrg_repay_interest();//应还利息
				}
				BigDecimal ratio=SysTools.getReturnRatio(wmsPostDunningDetailedSearchBeanVO.getCur_overdue_day(),2);// (计算提成比例) */
				/*BigDecimal ratio=SysTools.getReturnRatio(wmsPostDunningDetailedSearchBeanVO.getCur_overdue_day(),2);// (计算提成比例) 
				int return_ratio=ratio.movePointRight(2).intValue();
				bean.setReturn_ratio(return_ratio);//(计算提成比例)
				BigDecimal return_amount=SysTools.getReturnAmount(bean.getExpect_payment_amount(),orgRepayPrincipal,orgRepayInterest);//(计算缴回违约金额	缴回违约金=预期缴回金额-应还本金-应还利息) 
				bean.setReturn_amount(return_amount);//缴回违约金金额
				bean.setReturn_ommission(ratio.multiply(return_amount));//提成*/
			}else{//内部催缴
				bean.setInternal_dunning_result(bean.getFinal_dunning_result());//内部催缴结果
			}
			if("1".equals(bean.getFinal_dunning_result())){//1成功 2失败  催缴结果
				bean.setDunning_status("5");//催缴单据状态--5>催缴确认
			}else{
				bean.setDunning_status("7");//催缴单据状态 7>呆账
			}
		}
		/*WmsPostDunningHead dh=new WmsPostDunningHead();
		dh.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
		dh.setWms_fina_cre_pay_id(bean.getWms_fina_cre_pay_id());
		dh.setEnable_flag("1");
		List<WmsPostDunningHead> list=wmspostdunningheadDao.getListById(dh);//催缴主表
		if(list!=null&&list.size()>0){*/
		if(bean.getWms_post_dunning_head_id()!=null&&!"".equals(bean.getWms_post_dunning_head_id())){//催缴主表id
			//bean.setWms_post_dunning_head_id(list.get(0).getWms_post_dunning_head_id());//复制id
			bean.setPost_dunning_update_datetime(new Timestamp(System.currentTimeMillis()));//催缴单更新时间
			ret = wmspostdunningheadDao.update(bean); // 
		}else{
			bean.setWms_post_dunning_head_id(null);//复制id
			bean.setCreate_dunning_id(wmsPostDunningDetailedSearchBeanVO.getDunning_id());//贷后专员id
			bean.setCreate_dunning_name(wmsPostDunningDetailedSearchBeanVO.getDunning_name());//贷后专员姓名
			bean.setCreate_dunning_deptid(wmsPostDunningDetailedSearchBeanVO.getDunning_deptId());//贷后专员部门id
			bean.setCreate_dunning_datetime(new Timestamp(System.currentTimeMillis()));//贷后专员部门id
			bean.setCreate_dunning_result(bean.getFinal_dunning_result());//贷后专员结果
			bean.setPost_dunning_create_datetime(new Timestamp(System.currentTimeMillis()));//催缴单创建时间
			bean.setPost_dunning_update_datetime(new Timestamp(System.currentTimeMillis()));//催缴单更新时间
			bean.setPost_dunning_cj_code(CodeNoUtil.getPostDunningCJCode());//贷后催缴单编码
			ret = wmspostdunningheadDao.save(bean);// 
		}
		//催缴详情
		List<WmsPostDunningDetailed> listdd=JsonUtil.jsonArrayToList(wmsPostDunningDetailedSearchBeanVO.getResultMe(), WmsPostDunningDetailed.class);
		if(listdd!=null){
			
			for(int i=0;i<listdd.size();i++){
				WmsPostDunningDetailed dd=listdd.get(i);
				dd.setWms_post_dunning_detailed_id(null);
				dd.setWms_post_dunning_head_id(bean.getWms_post_dunning_head_id());
				dd.setWms_fina_cre_pay_id(bean.getWms_fina_cre_pay_id());
				dd.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
				dd.setWms_post_dunning_head_id(bean.getWms_post_dunning_head_id());//催缴id
				dd.setEnable_flag("1");
				dd.setDunning_id(user.getUserId());//实时催缴专员ID
				dd.setDunning_name(user.getRealname());//实时催缴专员姓名
				dd.setDunning_deptid(user.getDeptId());//实时催缴专员DeptID
				ret = wmspostdunningdetailedDao.save(dd); //
			}
		}
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}
	
		/**
		 * @Title: compute 
		 * @Description: 计算提成等信息
		 * @param bean
		 * @param wmsPostDunningDetailedSearchBeanVO
		 * @param userType    
		 * @return void    返回类型
		 * @throws
		 * @author lvtu
		 */
		private void compute(WmsPostDunningHead bean, WmsPostDunningDetailedSearchBeanVO wmsPostDunningDetailedSearchBeanVO, int userType) {
			BigDecimal expectPaymentAmount = bean.getExpect_payment_amount();
			//电话催缴提成计算
			int overdueDay = wmsPostDunningDetailedSearchBeanVO.getCur_overdue_day();//逾期天数
			//计算提成比例和提成
			Map<String, Object> moneyMap = wmspostdunningheadDao.getOrgRepayPrincipalAndInterest(bean.getWms_cre_credit_head_id());
			BigDecimal orgRepayPrincipal = new BigDecimal(0);
			BigDecimal orgRepayInterest = new BigDecimal(0);
			if(moneyMap != null) {
				orgRepayPrincipal = (BigDecimal) moneyMap.get("org_repay_principal");//应还本金
				orgRepayInterest = (BigDecimal) moneyMap.get("org_repay_interest");//应还利息
			}
			
			BigDecimal returnAmount = SysTools.getReturnAmount(expectPaymentAmount, orgRepayPrincipal, orgRepayInterest);//缴回违约金金额
			//getReturnRatio() 0:贷后主管；1：贷后专员； 2：上门人员
	        BigDecimal ratio = SysTools.getReturnRatio(overdueDay, 2);//提成比例
	        BigDecimal returnOmmission = SysTools.getReturnOmmission(returnAmount, ratio);
	        ratio.movePointRight(2);
	        
	        bean.setExpect_payment_amount(expectPaymentAmount);
	        bean.setReturn_amount(returnAmount);
	        bean.setReturn_ratio(ratio.movePointRight(2).intValue());
	        bean.setReturn_ommission(returnOmmission);
		}
		/**
		 * 电话催缴  催缴单信息保存更新
		 * @param bean
		 * @return "success" or "error" or user defined doSaveUpdateVisit
		 * @author baisongsaveUpdate
		 */	
		@Override
		@Transactional
		public String saveUpdate(WmsPostDunningHead bean, UserBean user,WmsPostDunningDetailed beanDetailed,WmsPostDunningDetailedSearchBeanVO wmsPostDunningDetailedSearchBeanVO) {
			String resStr = "success";
			int ret = 0;
			bean.setFinal_dunning_id(wmsPostDunningDetailedSearchBeanVO.getDunning_id());//贷后专员id
			bean.setFinal_dunning_name(wmsPostDunningDetailedSearchBeanVO.getDunning_name());//贷后专员姓名
			bean.setFinal_dunning_deptid(wmsPostDunningDetailedSearchBeanVO.getDunning_deptId());//贷后专员部门id
			bean.setFinal_dunning_datetime(new Timestamp(System.currentTimeMillis()));//贷后专员部门时间
			bean.setDunning_name(wmsPostDunningDetailedSearchBeanVO.getDebtor_name());//客户姓名
			bean.setDunning_tel(wmsPostDunningDetailedSearchBeanVO.getDebtor_tel());//联系电话
			bean.setProtocol_creat_date((wmsPostDunningDetailedSearchBeanVO.getProtocol_creat_date()));//签约日期
			bean.setDeadline(wmsPostDunningDetailedSearchBeanVO.getBorrow_deadline());//期数
			bean.setUn_matching_creditor(wmsPostDunningDetailedSearchBeanVO.getMatching_creditor());//剩余债权cur_overdue_day
			bean.setOverdue_day(wmsPostDunningDetailedSearchBeanVO.getCur_overdue_day());//逾期天数
			bean.setPost_dunning_commissioner_time(new Date(System.currentTimeMillis()));//贷后专员提交处理时间
			
			//电话催缴提成计算
			compute(bean, wmsPostDunningDetailedSearchBeanVO, 1);
	        
			//提交操作val为1
			if("1".equals(wmsPostDunningDetailedSearchBeanVO.getVal())&&"3".equals(bean.getFinal_dunning_result())){
				bean.setDunning_status(bean.getFinal_dunning_result());//催缴单据状态
				bean.setPost_dunning_commissioner_time(new Date(System.currentTimeMillis() ));;//催缴单据状态
			}else if("1".equals(wmsPostDunningDetailedSearchBeanVO.getVal())){
				bean.setDunning_status("2");//催缴单据状态	
			}
			/*WmsPostDunningHead dh=new WmsPostDunningHead();
			dh.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
			dh.setWms_fina_cre_pay_id(bean.getWms_fina_cre_pay_id());
			dh.setEnable_flag("1");
			List<WmsPostDunningHead> list=wmspostdunningheadDao.getListById(dh);//催缴主表
			if(list!=null&&list.size()>0){*/
			if(bean.getWms_post_dunning_head_id()!=null&&!"".equals(bean.getWms_post_dunning_head_id())){//催缴主表id
				//bean.setWms_post_dunning_head_id(list.get(0).getWms_post_dunning_head_id());//复制id
				bean.setPost_dunning_update_datetime(new Timestamp(System.currentTimeMillis()));//催缴单更新时间
				ret = wmspostdunningheadDao.update(bean); // 
			}else{
				bean.setWms_post_dunning_head_id(null);//复制id
				bean.setCreate_dunning_id(wmsPostDunningDetailedSearchBeanVO.getDunning_id());//贷后专员id
				bean.setCreate_dunning_name(wmsPostDunningDetailedSearchBeanVO.getDunning_name());//贷后专员姓名
				bean.setCreate_dunning_deptid(wmsPostDunningDetailedSearchBeanVO.getDunning_deptId());//贷后专员部门id
				bean.setCreate_dunning_datetime(new Timestamp(System.currentTimeMillis()));//贷后专员部门id
				bean.setCreate_dunning_result(bean.getFinal_dunning_result());//贷后专员结果
				bean.setPost_dunning_cj_code(CodeNoUtil.getPostDunningCJCode());//贷后催缴单编码
				ret = wmspostdunningheadDao.save(bean);// 
			}
			//催缴详情
			List<WmsPostDunningDetailed> listdd=JsonUtil.jsonArrayToList(wmsPostDunningDetailedSearchBeanVO.getResultMe(), WmsPostDunningDetailed.class);
			if(listdd!=null){
				for(int i=0;i<listdd.size();i++){
					WmsPostDunningDetailed dd=listdd.get(i);
					dd.setWms_post_dunning_detailed_id(null);
					dd.setWms_post_dunning_head_id(bean.getWms_post_dunning_head_id());
					dd.setWms_fina_cre_pay_id(bean.getWms_fina_cre_pay_id());
					dd.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
					dd.setWms_post_dunning_head_id(bean.getWms_post_dunning_head_id());//催缴id
					dd.setEnable_flag("1");
					ret = wmspostdunningdetailedDao.save(dd); //
				}
			}
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}
	
	/**
	 * 根据 其他表id确定催缴主表信息
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author baisong
	 */
	public List<WmsPostDunningHead> getListByEntity(WmsPostDunningHead queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsPostDunningHead> beanList = wmspostdunningheadDao.getListByEntity(queryInfo);
		return beanList;
	}
	/**
     *查询催缴用户信息
     * @param paramMap
     * @return
     * baisong
     */
	@Override
	public List<Map<String, Object>> getDictDataByDictIdEmpty() {
		 List<Map<String, Object>> list=wmspostdunningheadDao.seachInfoCount();
		 Map<String,Object> map=new HashMap<>();
		 map.put("personnel_id", "");
		 map.put("personnel_name", "请选择");
		 map.put("dunning_number", ""); 
		 list.add(0, map);
		return list;
	}
	  /**
     * 查询催缴单据的数量
     * @param paramMap
     * @return
     * baisong
     */
	@Override
	public int seachCount(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user) {
		 	Map<String,Object> paramMap = new HashMap<>();
	        //合同编号
	        if(StringUtil.isNotBlank(queryInfo.getProtocol_code()))
	        {
	            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
	        }
	        //客户姓名
	        if(StringUtil.isNotBlank(queryInfo.getDebtor_name()))
	        {
	            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
	        }
	        //客户电话
	        if(StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
	        {
	            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
	        }
	        //业务员姓名或编号
	        if(StringUtil.isNotBlank(queryInfo.getSalesman_name()))
	        {
	            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
	        }
	        //产品
	        if(StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
	        {
	            paramMap.put("cre_type", queryInfo.getCre_type());
	        }
	        //催缴人姓名
	        if(StringUtil.isNotBlank(queryInfo.getDunning_name()))
	        {
	        	paramMap.put("dunning_name", SysTools.getSqlLikeParam(queryInfo.getDunning_name()));
	        }
		 int number=wmspostdunningheadDao.seachCount(paramMap);
		return number;
	}
   /**
	 * 贷后管理-催缴管理 --单据分配
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */	
	@Override
	@Transactional
	public String allocationNnumber(WmsAllocationSearchBeanVO wmsAllocationSearchBeanVO,WmsFinaCreRepaySearchBeanVO queryInfo) {
		String resStr = "success";
		int ret = 0;
		Map<String,Object> map = wmspostdunningheadDao.seachInfo(wmsAllocationSearchBeanVO.getDunning_id());//催缴人id
		if(map==null){
			resStr = "errorid";//获取催缴人错误
			return resStr;
		}
		if("false".equals(wmsAllocationSearchBeanVO.getSelectval())){//如果用户没选择
			Map<String,Object> paramMap = new HashMap<>();
	        //合同编号
	        if(StringUtil.isNotBlank(queryInfo.getProtocol_code()))
	        {
	            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
	        }
	        //客户姓名
	        if(StringUtil.isNotBlank(queryInfo.getDebtor_name()))
	        {
	            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
	        }
	        //客户电话
	        if(StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
	        {
	            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
	        }
	        //业务员姓名或编号
	        if(StringUtil.isNotBlank(queryInfo.getSalesman_name()))
	        {
	            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
	        }
	        //产品
	        if(StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
	        {
	            paramMap.put("cre_type", queryInfo.getCre_type());
	        }
	        //催缴人姓名
	        if(StringUtil.isNotBlank(queryInfo.getDunning_name()))
	        {
	        	paramMap.put("dunning_name", SysTools.getSqlLikeParam(queryInfo.getDunning_name()));
	        }
			paramMap.put("offset", 0);
			paramMap.put("pagesize", wmsAllocationSearchBeanVO.getAllocation_number());//要分配的数量
			List<Map<String, Object>> list=	wmsFinaCreRepayDao.searchforPostLoanAllocation(paramMap);
			if(list.size()<wmsAllocationSearchBeanVO.getAllocation_number()){
				resStr = "errornumber";//获取单据数量错误
				return resStr;	
			}
			for(int i=0;i<list.size();i++){
				WmsFinaCreRepay wmsFinaCreRepay=new WmsFinaCreRepay();
				Map<String, Object> map1=list.get(i);
				wmsFinaCreRepay.setWms_fina_cre_pay_id((Integer)map1.get("wms_fina_cre_pay_id"));//贷款还款表id
				wmsFinaCreRepay.setDunning_id((Integer)map.get("personnel_id"));//人员id
				wmsFinaCreRepay.setDunning_deptid((Integer)map.get("personnel_deptId"));//部门id
				wmsFinaCreRepay.setDunning_name((String)map.get("personnel_name"));//姓名
				wmsFinaCreRepay.setDunning_datetime(new Timestamp(System.currentTimeMillis()));
				ret =wmsFinaCreRepayDao.update(wmsFinaCreRepay);
				WmsPostDunningHead wmspostdunninghead=new WmsPostDunningHead();
				wmspostdunninghead.setWms_fina_cre_pay_id(wmsFinaCreRepay.getWms_fina_cre_pay_id());//贷款还款表id
				wmspostdunninghead.setWms_cre_appro_borrow_protocol_id(wmsFinaCreRepay.getWms_cre_appro_borrow_protocol_id());//合同表id
				wmspostdunninghead.setWms_cre_credit_head_id(wmsFinaCreRepay.getWms_cre_credit_head_id());//贷款信息主表
				List<WmsPostDunningHead> listhead=wmspostdunningheadDao.getListByEntity(wmspostdunninghead);
				if(listhead!=null&&listhead.size()>0){
					wmspostdunninghead.setWms_post_dunning_head_id(listhead.get(0).getWms_post_dunning_head_id());
					wmspostdunninghead.setDunning_status("1");//催缴单据状态 1> 待电话催缴 
					wmspostdunningheadDao.update(wmspostdunninghead);	
				}
			}
		}else if("true".equals(wmsAllocationSearchBeanVO.getSelectval())){//用户选择了一条单据
			Map<String,Object> map1=wmspostdunningheadDao.seachInfobyid(wmsAllocationSearchBeanVO.getWms_fina_cre_pay_id());//单据id
			if(map1==null){
				resStr = "errorpayid";//获取催缴单据错误
				return resStr;
			}
			WmsFinaCreRepay wmsFinaCreRepay=new WmsFinaCreRepay();
			wmsFinaCreRepay.setDunning_datetime(new Timestamp(System.currentTimeMillis()));
			wmsFinaCreRepay.setWms_fina_cre_pay_id((Integer)map1.get("wms_fina_cre_pay_id"));//贷款还款表id
			wmsFinaCreRepay.setDunning_id((Integer)map.get("personnel_id"));//人员id
			wmsFinaCreRepay.setDunning_deptid((Integer)map.get("personnel_deptId"));//部门id
			wmsFinaCreRepay.setDunning_name((String)map.get("personnel_name"));//姓名
			ret =wmsFinaCreRepayDao.update(wmsFinaCreRepay);
			WmsPostDunningHead wmspostdunninghead=new WmsPostDunningHead();
			wmspostdunninghead.setWms_fina_cre_pay_id(wmsFinaCreRepay.getWms_fina_cre_pay_id());//贷款还款表id
			wmspostdunninghead.setWms_cre_appro_borrow_protocol_id(wmsFinaCreRepay.getWms_cre_appro_borrow_protocol_id());//合同表id
			wmspostdunninghead.setWms_cre_credit_head_id(wmsFinaCreRepay.getWms_cre_credit_head_id());//贷款信息主表
			List<WmsPostDunningHead> listhead=wmspostdunningheadDao.getListByEntity(wmspostdunninghead);
			if(listhead!=null&&listhead.size()>0){
				wmspostdunninghead.setWms_post_dunning_head_id(listhead.get(0).getWms_post_dunning_head_id());
				wmspostdunninghead.setDunning_status("1");//催缴单据状态 1> 待电话催缴 
				wmspostdunningheadDao.update(wmspostdunninghead);
			}
		}
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}
	/**
	 * 获取催缴单据审核列表页面数据显示
	 * @param queryInfo 筛选条件
	 * @return
	 */
	@Override
	public Map<String, Object> getPostDunningReviewListWithPaging(WmsPostDunningHeadSearchBeanVO queryInfo,UserBean user)
	{
	    Map<String,Object> paramMap = new HashMap<>();
	    //合同编号
	    if(StringUtil.isNotBlank(queryInfo.getProtocol_code()))
	    {
	        paramMap.put("protocol_code",SysUtil.getSqlLikeParam(queryInfo.getProtocol_code()));
	    }
	    //客户姓名
	    if(StringUtil.isNotBlank(queryInfo.getDebtor_name()))
	    {
	        paramMap.put("debtor_name", SysUtil.getSqlLikeParam(queryInfo.getDebtor_name()));
	    }
	    //客户电话
	    if(StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
	    {
	        paramMap.put("debtor_tel", SysUtil.getSqlLikeParam(queryInfo.getDebtor_tel()));
	    }
	    //业务员
	    if(StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysUtil.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
	    //贷款产品
	    if(StringUtil.isNotBlank(queryInfo.getCre_type())&&!queryInfo.getCre_type().equals("-1"))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
	    //催缴人
	    if(StringUtil.isNotBlank(queryInfo.getFinal_dunning_name()))
        {
            paramMap.put("final_dunning_name",SysUtil.getSqlLikeParam(queryInfo.getFinal_dunning_name()));
        }
	    paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmspostdunningheadDao.searchforDunningReview(paramMap);
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmspostdunningheadDao.findCountDunningReview(paramMap),list);
        return bean.getGridData();          
	}
	/**
	 * 获取催缴单详细信息
	 */
	@Override
	public Map<String, Object> getWmsPostDunningHeadInfoByPK(Integer wms_post_dunning_head_id)
	{
	    return wmspostdunningheadDao.getWmsPostDunningHeadInfoByPK(wms_post_dunning_head_id);
	}
	
	 /**
     * Description:催缴审核单据保存
     * @param bean
     * @param user
     * @param flag  0代表暂存  1代表提交
     * @return
     */
	@Override
	public String reviewUpdate(WmsPostDunningHead bean, WmsPostDunningHeadSearchBeanVO queryInfo,UserBean user,String flag)
	{
	    String result="";
	    int ret;
	    if(flag.equals("0"))
	    {
	        ret=wmspostdunningheadDao.update(bean);
	        if(ret==1)
	        {
	            result="success";
	        }else{
	            result="error";
	        }
	    }
	    else if(flag.equals("1"))
	    {
	        //存储审核人信息
	        bean.setLoansupervisor_datetime(new Timestamp(System.currentTimeMillis()));
	        bean.setLoansupervisor_deptid(user.getDeptId());
	        bean.setLoansupervisor_id(user.getUserId());
	        bean.setLoansupervisor_name(user.getRealname());
	        ret=wmspostdunningheadDao.update(bean);
	        if(ret==1)
            {
	            Map<String,Object> paramap = new HashMap<>();
	            paramap.put("wms_post_dunning_head_id", bean.getWms_post_dunning_head_id());
	            //根据主管的选择 判断是要上门催缴还是催缴成功
	            if(bean.getLoansupervisor_result().equals("2")){//上门催缴
	                   paramap.put("dunning_status", 4);//上门催缴
	            }else if(bean.getLoansupervisor_result().equals("1")){//催缴成功
	                   paramap.put("dunning_status", 5);//催缴确认
	            }else if(bean.getLoansupervisor_result().equals("0")){//代表催缴驳回
	            	   paramap.put("dunning_status", 0);//催缴驳回
	            }
	            //贷后主管提交审核时间
	            paramap.put("post_dunning_commissioner_time", new java.util.Date());
	            wmspostdunningheadDao.updateReocod(paramap);
                result="success";
            }else{
                result="error";
            }
	    }
	    return result;
	}
	  /**
     * Description:催缴单审核页面数据导出
     * @param queryInfo
     * @param user
     * @return
     * @author hancd
     */
	@Override
	public Map<String, Object> getReviewWithoutPagingList(WmsPostDunningHeadSearchBeanVO queryInfo, UserBean user)
	{
        Map<String,Object>paramMap = new HashMap<>();
        //合同编号
        if(StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code",SysUtil.getSqlLikeParam(queryInfo.getProtocol_code()));
        }
        //客户姓名
        if(StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysUtil.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        //客户电话
        if(StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysUtil.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        //业务员
        if(StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysUtil.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        //贷款产品
        if(StringUtil.isNotBlank(queryInfo.getCre_type())&&!queryInfo.getCre_type().equals("-1"))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        //催缴人
        if(StringUtil.isNotBlank(queryInfo.getFinal_dunning_name()))
        {
            paramMap.put("final_dunning_name",SysUtil.getSqlLikeParam(queryInfo.getFinal_dunning_name()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String,Object>> list = wmspostdunningheadDao.searchforDunningReviewWithout(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows",list);
	    return resMap;
	}
	 /**
     * Description:上门催缴跟踪页面数据显示
     * @param queryInfo
     * @param user
     * @return
     * @author hancd
     */
	@Override
	public Map<String, Object> getPostDunningHomeListWithPaging(WmsPostDunningHeadSearchBeanVO queryInfo, UserBean user)
	{
//	    WmsPostLoanWorkFlowBeanVO beanVO = new WmsPostLoanWorkFlowBeanVO();
//        beanVO.setDunningKey("4");
//        beanVO.setUser_id(user.getUserId());
//        Map<String, Object> paramMap = wmsPostLoanWorkFlowService.getIdTaskIdList(beanVO);
        Map<String,Object> paramMap = new HashMap<>();
        //合同编号
        if(StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code",SysUtil.getSqlLikeParam(queryInfo.getProtocol_code()));
        }
        //客户姓名
        if(StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysUtil.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        //客户电话
        if(StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysUtil.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        //业务员
        if(StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysUtil.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        //贷款产品
        if(StringUtil.isNotBlank(queryInfo.getCre_type())&&!queryInfo.getCre_type().equals("-1"))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        //催缴人
        if(StringUtil.isNotBlank(queryInfo.getFinal_dunning_name()))
        {
            paramMap.put("final_dunning_name",SysUtil.getSqlLikeParam(queryInfo.getFinal_dunning_name()));
        }
        //上门催缴状态
        if(StringUtil.isNotBlank(queryInfo.getHome_dunning_status())&& !queryInfo.getHome_dunning_status().equals("-1"))
        {
            paramMap.put("home_dunning_status", queryInfo.getHome_dunning_status());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmspostdunningheadDao.searchforDunningHome(paramMap);
        //list =wmsPostLoanWorkFlowService.addTask(list, (List<Integer>)paramMap.get("idList"), (List<String>)paramMap.get("taskIdList"));
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmspostdunningheadDao.findCountDunningHome(paramMap),list);
        return bean.getGridData();          
	}
	/**
     * Description:上门催缴跟踪页面数据导出
     * @param queryInfo
     * @param user
     * @return
     */
	@Override
	public Map<String, Object> getHomeWithoutPagingList(WmsPostDunningHeadSearchBeanVO queryInfo, UserBean user)
	{
//	    WmsPostLoanWorkFlowBeanVO beanVO = new WmsPostLoanWorkFlowBeanVO();
//        beanVO.setDunningKey("4");
//        beanVO.setUser_id(user.getUserId());
//        Map<String, Object> paramMap = wmsPostLoanWorkFlowService.getIdTaskIdList(beanVO);
        Map<String,Object> paramMap = new HashMap<>();
        //合同编号
        if(StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code",SysUtil.getSqlLikeParam(queryInfo.getProtocol_code()));
        }
        //客户姓名
        if(StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysUtil.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        //客户电话
        if(StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysUtil.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        //业务员
        if(StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysUtil.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        //贷款产品
        if(StringUtil.isNotBlank(queryInfo.getCre_type())&&!queryInfo.getCre_type().equals("-1"))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        //催缴人
        if(StringUtil.isNotBlank(queryInfo.getFinal_dunning_name()))
        {
            paramMap.put("final_dunning_name",SysUtil.getSqlLikeParam(queryInfo.getFinal_dunning_name()));
        }
        //上门催缴状态
        if(StringUtil.isNotBlank(queryInfo.getHome_dunning_status())&& !queryInfo.getHome_dunning_status().equals("-1"))
        {
            paramMap.put("home_dunning_status", queryInfo.getHome_dunning_status());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String,Object>> list = wmspostdunningheadDao.searchforDunningHomeWithout(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows",list);
        return resMap;
	}
	 /**
     * Description:上门催缴跟踪催缴确认保存
     * @param bean
     * @param bean1
     * @param user
     * @param flag
     * @param taskId
     * @return
     */
	@Override
	public String doHomeSave(WmsPostDunningHead bean,WmsPostDunningHeadSearchBeanVO queryInfo, UserBean user, String flag,
	                         String taskId)
	{
	    String result="success";
	    int ret;
	    ret=wmspostdunningheadDao.update(bean);//更新催缴单
	    if(flag.equals("0")){//上门催缴暂存
	        
	    }else if(flag.equals("1")){//上门催缴提交
	        //WmsPostLoanWorkFlowBeanVO workFlowBeanVO = new WmsPostLoanWorkFlowBeanVO();
	        //workFlowBeanVO.setDunningKey("4");
            //workFlowBeanVO.setTaskId(taskId);
            //workFlowBeanVO.setUser_id(user.getUserId());
            //workFlowBeanVO.setAdvice(queryInfo.getAdvice());
            //workFlowBeanVO.setWms_post_dunning_head_id(bean.getWms_post_dunning_head_id());
	        Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("wms_post_dunning_head_id", bean.getWms_post_dunning_head_id());
            if(bean.getHome_dunning_status()==null){//说明进行的是内部催缴
                if(bean.getInternal_dunning_result().equals("1")){//说明内部催缴成功
                    //workFlowBeanVO.setPass("true");
                    //wmsPostLoanWorkFlowService.dunningApprovalWorkFlow(workFlowBeanVO);
                    paramMap.put("dunning_status", 5);//催缴确认
                }else{//内部催缴不成功，可以直接进行外包和法务催缴
                    paramMap.put("home_dunning_status","2#3");
                }
            }else{
                if(bean.getHome_dunning_status().split("#").length>1){//说明选中两个或者三个选择
                    bean.setOutside_dunning_result(queryInfo.getPass());
                    bean.setLegalappeal_dunning_result(queryInfo.getPass());
                    bean.setOutside_dunning_advice(queryInfo.getAdvice());
                    bean.setLegalappeal_dunning_advice(queryInfo.getAdvice());
                    if(queryInfo.getPass().equals("1")){//说明选择多项选择并且催缴成功
                        //workFlowBeanVO.setPass("true");
                        //wmsPostLoanWorkFlowService.dunningApprovalWorkFlow(workFlowBeanVO);
                        paramMap.put("dunning_status", 5);//催缴确认
                    }else{
                        //workFlowBeanVO.setPass("false");
                        //wmsPostLoanWorkFlowService.dunningApprovalWorkFlow(workFlowBeanVO);
                        paramMap.put("dunning_status", 7);//呆账
                        paramMap.put("home_dunning_status", 4);//内部上门催缴状态变成呆账
                    }
                }else if(bean.getHome_dunning_status().split("#").length==1){//说明选中一个
                    if(bean.getHome_dunning_status().split("#")[0].equals("2")){//说明是外包催缴
                        bean.setOutside_dunning_result(queryInfo.getPass());
                        bean.setOutside_dunning_advice(queryInfo.getAdvice());
                        if(queryInfo.getPass().equals("1")){//说明外包催缴成功
                            //workFlowBeanVO.setPass("true");
                            //wmsPostLoanWorkFlowService.dunningApprovalWorkFlow(workFlowBeanVO);  
                            paramMap.put("dunning_status",5);//催缴确认
                        }else{
                            paramMap.put("dunning_status",7);//呆账
                            paramMap.put("home_dunning_status", 4);//内部上门催缴状态变成呆账
                        }
                    }else if(bean.getHome_dunning_status().split("#")[0].equals("3")){//说明选法务上诉
                        bean.setLegalappeal_dunning_result(queryInfo.getPass());
                        bean.setLegalappeal_dunning_advice(queryInfo.getAdvice());
                        if(queryInfo.getPass().equals("1")){//说明选中法务上诉成功
                            //workFlowBeanVO.setPass("true");
                            //wmsPostLoanWorkFlowService.dunningApprovalWorkFlow(workFlowBeanVO);  
                            paramMap.put("dunning_status",5);//催缴确认
                        }else{
                            paramMap.put("dunning_status",7);//呆账
                            paramMap.put("home_dunning_status", 4);//内部上门催缴状态变成呆账
                        }
                    }
                }
            }
           wmspostdunningheadDao.updateReocod(paramMap);
	    }
        //保存跟踪记录信息
	    //保存跟踪信息,如果跟踪记录为空，就不进行存储
	    if((queryInfo.getDunning_names()==null&&queryInfo.getDunning_datetime()==null&&queryInfo.getDunning_remarks()==null)
	            ||(queryInfo.getDunning_names().equals("")&&queryInfo.getDunning_remarks().equals(""))){
	    }else{
	        WmsPostDunningDetailed wmsPostDunningDetailed = new WmsPostDunningDetailed();
	        wmsPostDunningDetailed.setWms_post_dunning_head_id(bean.getWms_post_dunning_head_id());
	        wmsPostDunningDetailed.setWms_fina_cre_pay_id(wmspostdunningheadDao.get(bean.getWms_post_dunning_head_id()).getWms_fina_cre_pay_id());
	        wmsPostDunningDetailed.setWms_cre_credit_head_id(wmspostdunningheadDao.get(bean.getWms_post_dunning_head_id()).getWms_cre_credit_head_id());
	        wmsPostDunningDetailed.setDunning_datetime(queryInfo.getDunning_datetime());
	        wmsPostDunningDetailed.setDunning_name(queryInfo.getDunning_names());
	        wmsPostDunningDetailed.setDunning_remark(queryInfo.getDunning_remarks());
	        wmsPostDunningDetailed.setDunning_deptid(personnelDao.get(Integer.valueOf(queryInfo.getDunning_ids())).getPersonnel_deptid());
	        wmsPostDunningDetailed.setDunning_id(Integer.valueOf(queryInfo.getDunning_ids()));
	        wmsPostDunningDetailed.setEnable_flag("1");
	        ret=wmspostdunningdetailedDao.save(wmsPostDunningDetailed);	
	    }
	    if(ret==0)
	    {
	        result="error";
	        
	    }
	    else
	    {
	        result="success";
	    }
	    return result;
	}
	/***
     * Description:判断是否进行过电话催缴提交操作
     *   
     * @param taskId
     * @return String
     * @author baisong
     * 
     * 修改记录：需要区分是谁的单据和催缴单ID 进行判断
     *           对催缴不同状态重新定义和拦截提示
     * 修改时间：2015-05-30
     * 修改人：hancd
     */
	@Override
	public String ischeck(WmsPostDunningHead bean) {
		  String result="success";
		  List<WmsPostDunningHead> list=wmspostdunningheadDao.getListByEntity(bean);
		  if(list!=null){
			  if(list.size()>0){
				  bean=list.get(0);
				  if("2".equals(bean.getDunning_status())){
					  result="error0";//催缴处理电催审核阶段不允许在提单
				  }
				  if("3".equals(bean.getDunning_status())){
					  result="error1";//催缴单处理电催分配阶段不允许在提单
				  }
				  if("5".equals(bean.getDunning_status())){
					  result="error2";//催缴单处于待逾期催缴确认阶段不允许在提单
				  }
			  }
		  }
		return result;
	}
	/**
	 * 获取催缴单历史信息--逾期还款确认驳回单据使用
	 * baisong
	 */
	@Override
	public Map<String, Object> getWmspostDunningheadInfoByHK(Integer wms_post_dunning_head_id, UserBean user) {
		Map<String, Object> resMap = wmspostdunningheadDao.getWmsPostDunningHeadInfoByPK(wms_post_dunning_head_id);
	    return resMap;
	}
	/**
	 * 获取催缴单历史信息
	 */
	@Override
	public Map<String, Object> getWmspostDunningheadInfoByPK(Integer wms_post_dunning_head_id, UserBean user) {
	    return wmspostdunningheadDao.getWmsPostDunningHeadInfoByPK(wms_post_dunning_head_id);
	}
	
	
	
	@Override
	public Map<String, Object> getPostLoanSearchListWithPaging(
			WmsPostDunningHeadSearchBeanVO queryInfo, UserBean user) {
		 Map<String,Object>paramMap = new HashMap<>();
	        //合同编号
	        if(StringUtil.isNotBlank(queryInfo.getProtocol_code()))
	        {
	            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
	        }
	        //客户姓名
	        if(StringUtil.isNotBlank(queryInfo.getDebtor_name()))
	        {
	            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
	        }
	        //客户电话
	        if(StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
	        {
	            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
	        }
	        //业务员姓名或编号
	        if(StringUtil.isNotBlank(queryInfo.getSalesman_name()))
	        {
	            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
	        }
	        //产品
	        if(StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
	        {
	            paramMap.put("cre_type", queryInfo.getCre_type());
	        }
	        //还款状态
	        if(StringUtil.isNotBlank(queryInfo.getRepay_status()) && !"-1".equals(queryInfo.getRepay_status()))
	        {
	            paramMap.put("repay_status", queryInfo.getRepay_status());
	        }
	        //上门催缴状态
	        if(StringUtil.isNotBlank(queryInfo.getHome_dunning_status())&&!"-1".equals(queryInfo.getHome_dunning_status()))
	        {   
	            paramMap.put("home_dunning_status", queryInfo.getHome_dunning_status());
	        }
	        //催缴状态
	        if(StringUtil.isNotBlank(queryInfo.getDunning_status())&&!"-1".equals(queryInfo.getDunning_status()))
	        {   
	            paramMap.put("dunning_status", queryInfo.getDunning_status());
	        }
	        //贷后专员
	        if(StringUtil.isNotBlank(queryInfo.getFinal_dunning_name())){
	            paramMap.put("final_dunning_name", SysUtil.getSqlLikeParam(queryInfo.getFinal_dunning_name()));
	        }
	        paramMap.put("sortname", queryInfo.getSortname());
	        paramMap.put("sortorder", queryInfo.getSortorder());
	        paramMap.put("pagesize", queryInfo.getPagesize());
	        paramMap.put("offset", queryInfo.getOffset());
	        List<Map<String,Object>> list = wmspostdunningheadDao.searchforPostLoan(paramMap);
	        GridDataBean<Map<String,Object>> bean = new 
	                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmspostdunningheadDao.findCountforPostLoan(paramMap),list);
	        return bean.getGridData();
	}
	
	@Override
	public Map<String, Object> getPostLoanSearchWithoutPagingList(
			WmsPostDunningHeadSearchBeanVO queryInfo, UserBean user) {
		Map<String,Object>paramMap = new HashMap<>();
		//合同编号
        if(StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
        }
        //客户姓名
        if(StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        //客户电话
        if(StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        //业务员姓名或编号
        if(StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        //产品
        if(StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        //还款状态
        if(StringUtil.isNotBlank(queryInfo.getRepay_status()) && !"-1".equals(queryInfo.getRepay_status()))
        {
            paramMap.put("repay_status", queryInfo.getRepay_status());
        }
        //上门催缴状态
        if(StringUtil.isNotBlank(queryInfo.getHome_dunning_status())&&!"-1".equals(queryInfo.getHome_dunning_status()))
        {   
            paramMap.put("home_dunning_status", queryInfo.getHome_dunning_status());
        }
        //催缴状态
        if(StringUtil.isNotBlank(queryInfo.getDunning_status())&&!"-1".equals(queryInfo.getDunning_status()))
        {   
            paramMap.put("dunning_status", queryInfo.getDunning_status());
        }
        //贷后专员
        if(StringUtil.isNotBlank(queryInfo.getFinal_dunning_name())){
            paramMap.put("final_dunning_name", SysUtil.getSqlLikeParam(queryInfo.getFinal_dunning_name()));
        }
        paramMap.put("dunning_id", user.getUserId());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String,Object>> list =wmspostdunningheadDao.searchforPostLoan(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows",list);
        return resMap;
	}
	/**
     * 服务管理--贷后管理-贷后查询-页面查看电审意见
     * @param wms_cre_credit_head_id
     * @return 
     * @author hancd
     */
    @Override
    public WmsCreRevPhoneMain getTelTeamAdvice(Integer wms_cre_credit_head_id)
    {
        WmsCreRevPhoneMain wmsCreRevPhoneMains=wmsCreRevPhoneMainDao.getListByPhone(wms_cre_credit_head_id);
        return wmsCreRevPhoneMains;
    }
	/**
     * Description :逾期确认查询列表 催缴状态弹出页面保存
     * @param WmsPostDunningHead 
     * @return "success" or "error" or user defined
     * @author baisong
     * 
     * 修改记录：2015-5-30 修改当财务对催缴单进行驳回操作时,把催缴单状态变成驳状态
     * 修改人：hancd
     */ 
	@Override
	public String doUpdateForReject(WmsPostDunningHead bean, UserBean user)
	{
	    String result="success";
	    int ret=0;
		   //bean.setReject_status("1");//驳回状态
		    bean.setReject_id(user.getUserId());//驳回操作人id
		    bean.setReject_name(user.getRealname());//驳回操作人name
		    bean.setReject_datetime(new Date(System.currentTimeMillis()));;//驳回时间
		    bean.setDunning_status("9");//改变催缴单状态为驳回状态
	        ret=wmspostdunningheadDao.update(bean);
		 if(ret==0){
			 result="error"; 
		 }
	    return result;
	}

	@Override
	public Map<String, Object> getReminderSearchList(WmsPostDunningHeadSearchBeanVO queryInfo, UserBean user) {
		Map<String,Object>paramMap = new HashMap<>();
        //合同编号
        if(StringUtil.isNotBlank(queryInfo.getPost_dunning_cj_code())) {
            paramMap.put("post_dunning_cj_code", SysTools.getSqlLikeParam(queryInfo.getPost_dunning_cj_code()));
        }
        //客户姓名
        if(StringUtil.isNotBlank(queryInfo.getDebtor_name())) {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        //客户电话
        if(StringUtil.isNotBlank(queryInfo.getDebtor_tel())) {
            paramMap.put("debtor_tel", queryInfo.getDebtor_tel());
        }
        //业务员姓名或编号
        if(StringUtil.isNotBlank(queryInfo.getSalesman_name())) {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        //产品
        if(StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type())) {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        //还款状态
        if(StringUtil.isNotBlank(queryInfo.getRepay_status()) && !"-1".equals(queryInfo.getRepay_status())) {
            paramMap.put("repay_status", queryInfo.getRepay_status());
        }
        //上门催缴状态
        if(StringUtil.isNotBlank(queryInfo.getHome_dunning_status())&&!"-1".equals(queryInfo.getHome_dunning_status())) {   
            paramMap.put("home_dunning_status", queryInfo.getHome_dunning_status());
        }
        //催缴状态
        if(StringUtil.isNotBlank(queryInfo.getDunning_status())&&!"-1".equals(queryInfo.getDunning_status())) {   
            paramMap.put("dunning_status", queryInfo.getDunning_status());
        }
        //应还款开始时间
        if(queryInfo.getCurrent_repay_date_start() != null) {
            paramMap.put("current_repay_date_start", queryInfo.getCurrent_repay_date_start());
        }
        //应还款结束时间
        if(queryInfo.getCurrent_repay_date_end() != null) {
        	paramMap.put("current_repay_date_end", queryInfo.getCurrent_repay_date_end());
        }
        //判断是贷后专员还是贷后主管
        Integer count = sysUserRoleDao.findRoleForDHZG(user.getUserId());
        if(count == null || count <= 0) { //不是贷后主管。 只能查询自己
        	paramMap.put("user_id", user.getUserId());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmspostdunningheadDao.getReminderSearchList(paramMap);
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmspostdunningheadDao.getReminderSearchListSize(paramMap),list);
        return bean.getGridData();
	}
	
	@Override
	public Map<String, Object> getReminderSearchListWithoutPage(WmsPostDunningHeadSearchBeanVO queryInfo, UserBean user) {
		Map<String,Object>paramMap = new HashMap<>();
        //合同编号
        if(StringUtil.isNotBlank(queryInfo.getPost_dunning_cj_code())) {
            paramMap.put("post_dunning_cj_code", SysTools.getSqlLikeParam(queryInfo.getPost_dunning_cj_code()));
        }
        //客户姓名
        if(StringUtil.isNotBlank(queryInfo.getDebtor_name())) {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        //客户电话
        if(StringUtil.isNotBlank(queryInfo.getDebtor_tel())) {
            paramMap.put("debtor_tel", queryInfo.getDebtor_tel());
        }
        //业务员姓名或编号
        if(StringUtil.isNotBlank(queryInfo.getSalesman_name())) {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        //产品
        if(StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type())) {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        //还款状态
        if(StringUtil.isNotBlank(queryInfo.getRepay_status()) && !"-1".equals(queryInfo.getRepay_status())) {
            paramMap.put("repay_status", queryInfo.getRepay_status());
        }
        //上门催缴状态
        if(StringUtil.isNotBlank(queryInfo.getHome_dunning_status())&&!"-1".equals(queryInfo.getHome_dunning_status())) {   
            paramMap.put("home_dunning_status", queryInfo.getHome_dunning_status());
        }
        //催缴状态
        if(StringUtil.isNotBlank(queryInfo.getDunning_status())&&!"-1".equals(queryInfo.getDunning_status())) {   
            paramMap.put("dunning_status", queryInfo.getDunning_status());
        }
        //应还款开始时间
        if(queryInfo.getCurrent_repay_date_start() != null) {
            paramMap.put("current_repay_date_start", queryInfo.getCurrent_repay_date_start());
        }
        //应还款结束时间
        if(queryInfo.getCurrent_repay_date_end() != null) {
        	paramMap.put("current_repay_date_end", queryInfo.getCurrent_repay_date_end());
        }
        //判断是贷后专员还是贷后主管
        Integer count = sysUserRoleDao.findRoleForDHZG(user.getUserId());
        if(count == null || count <= 0) { //不是贷后主管。 只能查询自己
        	paramMap.put("user_id", user.getUserId());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String,Object>> list = wmspostdunningheadDao.getReminderSearchList(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows",list);
        return resMap;
	}

	@Override
	public Map<String, Object> getReminderListBychid(Integer wms_cre_credit_head_id) {
		List<Map<String,Object>> list =wmspostdunningheadDao.getReminderListBychid(wms_cre_credit_head_id);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows",list);
        return resMap;
	}

	@Override
	public List<Map<String, Object>> getProcessIngofTracking(Integer wms_cre_credit_head_id) {
		//贷款单下所有催缴单信息
		List<Map<String,Object>> list =wmspostdunningheadDao.getReminderListBychid(wms_cre_credit_head_id);
		for(Map<String,Object> map : list) {
	        List<Map<String,Object>> processIngos = wmspostdunningdetailedDao.getWmsPostDunningDetailedInfo((Integer)map.get("wms_post_dunning_head_id"));
	        Map<String, Object> resMap = new HashMap<String, Object>();
	        resMap.put("Rows",processIngos);
	        map.put("processIngo", resMap);
		}
		return list;
	}

}
