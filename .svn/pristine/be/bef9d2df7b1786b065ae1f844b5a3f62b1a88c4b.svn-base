package com.zx.emanage.inve.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.zx.emanage.inve.persist.WmsInveApprovalProcessDao;
import com.zx.emanage.inve.persist.WmsInvePruductCategoryDao;
import com.zx.emanage.inve.persist.WmsInvePruductRebateWayDao;
import com.zx.emanage.inve.persist.WmsInvePruductYearPaySpecialDao;
import com.zx.emanage.inve.persist.WmsInveRedeemDao;
import com.zx.emanage.inve.persist.WmsInveRedeemDetailDao;
import com.zx.emanage.inve.persist.WmsInveReplaceDao;
import com.zx.emanage.inve.persist.WmsInveTransaCardDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.persist.WmsInveTransaIncomeDao;
import com.zx.emanage.inve.persist.WmsInveTransaLogDao;
import com.zx.emanage.inve.persist.WmsInveTransaMatchDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.inve.persist.WmsInveTransaProtocolDao;
import com.zx.emanage.inve.service.IWmsInveReplaceService;
import com.zx.emanage.inve.service.IWmsInveTransaProtocolService;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsInveApprovalProcess;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInvePruductRebateWay;
import com.zx.emanage.util.gen.entity.WmsInvePruductYearPaySpecial;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveRedeemDetail;
import com.zx.emanage.util.gen.entity.WmsInveReplace;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaCard;
import com.zx.emanage.util.gen.entity.WmsInveTransaIncome;
import com.zx.emanage.util.gen.entity.WmsInveTransaLog;
import com.zx.emanage.util.gen.entity.WmsInveTransaMatch;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.emanage.inve.util.CountIncome;
import com.zx.emanage.inve.util.CountIncomeFactory;
import com.zx.emanage.inve.util.CountIncomeForFullMonth;
import com.zx.emanage.inve.util.CountIncomeForFullYear;
import com.zx.emanage.inve.util.CountIncomeForTotal;
import com.zx.emanage.inve.vo.WmsInveReplaceSearchBeanVO;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.platform.syscontext.util.DateUtil;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.SysUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvereplaceService")
public class WmsInveReplaceServiceImpl implements IWmsInveReplaceService {
	private static Logger log = LoggerFactory.getLogger(WmsInveReplaceServiceImpl.class);

	@Autowired
	private WmsInveReplaceDao wmsinvereplaceDao;
	@Autowired
	private WmsInveApprovalProcessDao wmsInveApprovalProcessDao;
    @Autowired
    private PmPersonnelDao pmpersonnelDao;
    @Autowired
	private WmsInveTransaDao wmsInveTransaDao;
	@Autowired
	private WmsInvePruductCategoryDao wmsInvePruductCategoryDao;
	@Autowired
	private WmsInveTransaProdDao wmsInveTransaProdDao;
    @Autowired
    private PmPersonnelDao pmPersonnelDao;
    @Autowired
	private WmsInveTransaCardDao wmsInveTransaCardDao;
	@Autowired
	private WmsInveTransaProtocolDao wmsInveTransaProtocolDao;
	@Autowired
	private WmsInveTransaMatchDao wmsInveTransaMatchDao;
	@Autowired
	private WmsInvePruductRebateWayDao wmsInvePruductRebateWayDao;
	@Autowired
	private WmsInveTransaIncomeDao wmsInveTransaIncomeDao;
	@Autowired
	private WmsInveTransaLogDao wmsInveTransaLogDao;
	@Autowired
	private WmsInvePruductYearPaySpecialDao wmsInvePruductYearPaySpecialDao;
    @Autowired
    private WmsInveRedeemDetailDao wmsinveredeemdetailDao;
	@Autowired
	private WmsInveRedeemDao wmsinveredeemDao;
    @Autowired
    private WmsInveTransaProtocolDao wmsinvetransaprotocolDao;
    @Autowired
    private WmsInveTransaMatchDao wmsinvetransamatchDao;
    @Autowired
    private IWmsInveTransaProtocolService wmsInveTransaProtocolService;
	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveReplaceSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvereplaceDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveReplaceSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        
        if(StringUtil.isNotBlank(queryInfo.getCus_name())){
        	paramMap.put("cus_name", queryInfo.getCus_name());
        }
        if(StringUtil.isNotBlank(queryInfo.getId_card())){
        	paramMap.put("id_card", queryInfo.getId_card());
        }
        if(StringUtil.isNotBlank(queryInfo.getData_status())){
        	paramMap.put("data_status", queryInfo.getData_status());
        }
        if(StringUtil.isNotBlank(queryInfo.getData_type())){
        	paramMap.put("data_type", "back");
        }
        List<Map<String,Object>> list = wmsinvereplaceDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvereplaceDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveReplace getInfoByPK(Integer wms_inve_replace_id) {
		return wmsinvereplaceDao.get(wms_inve_replace_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveReplace bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		String is_take_off_damages = bean.getIs_take_off_damages();
		if("0".equals(is_take_off_damages)){//不扣违约金
			bean.setData_status("2");
			//bean.setData_status("3");
		}else{
			bean.setData_status("3");
		}
		bean.setApply_datetime(new Date(System.currentTimeMillis()));
		bean.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
		bean.setCreate_user_id(user.getUserId());
		bean.setCreate_user_name(user.getRealname());
		bean.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
		bean.setLast_update_user_id(user.getUserId());
		bean.setEnable_flag("1");
		ret = wmsinvereplaceDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		//更新理财上单表的单据状态
		WmsInveTransa wmsInveTransa = new WmsInveTransa();
		
		wmsInveTransa.setWms_inve_transa_id(bean.getWms_inve_transa_id());
		wmsInveTransa.setData_status("18");//产品转换中
		wmsInveTransaDao.updateDataStatus(wmsInveTransa); 
		handleProcess(bean, user,"提交业务表单，发起流程","已提交","提交成功");
		if("0".equals(is_take_off_damages)){//不扣违约金
			getPmInfo();//发送短消息
		}
		return resStr;
	}

	private void handleProcess(WmsInveReplace bean, UserBean user,String taskName,String result,String advice) {
		WmsInveApprovalProcess processBean = new WmsInveApprovalProcess();
		processBean.setTaskname(taskName);
		processBean.setApprovers(user.getRealname());
		processBean.setApproveresult(result);
		processBean.setApproveadvice(advice);
		processBean.setApprovetime(new Timestamp(System.currentTimeMillis()));
		processBean.setPersonnel_deptname(user.getDeptSimpleName());
		processBean.setPersonnel_postname(user.getPostName());
		processBean.setData_id(bean.getWms_inve_replace_id());
		processBean.setData_type("1");
		wmsInveApprovalProcessDao.save(processBean);
	}
	
	
	public void getPmInfo() {
		String userCode = PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.usercode");
		PmPersonnel pmpersonnel  = new PmPersonnel();
		pmpersonnel.setPersonnel_shortcode(userCode);
		pmpersonnel = pmpersonnelDao.getListByEntity(pmpersonnel).get(0);
		try {
			Map<String, String> sendMap = new HashMap<>();
			if (pmpersonnel != null) {
				sendMap.put("tel", pmpersonnel.getPersonnel_contacttel());// 电话
			}
			// 发送短信
			sendMap.put("tpl_id", "1766");
			// 参数map
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("url", PlatformGlobalVar.SYS_PROPERTIES.get("zshUrl"));
			Gson gson = new Gson();
			sendMap.put("paramJson", gson.toJson(paramMap));
			SysUtil.sendMsg(sendMap);
		} catch (Exception e) {
		}
	}

	@Override
	@Transactional
	public String update(WmsInveReplace bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		String is_take_off_damages = bean.getIs_take_off_damages();
		if("0".equals(is_take_off_damages)){//不扣违约金
			bean.setData_status("2");
			//bean.setData_status("3");
		}else{
			bean.setData_status("3");
		}
		bean.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
		bean.setLast_update_user_id(user.getUserId());
		bean.setEnable_flag("1");
		ret = wmsinvereplaceDao.update(bean);
		if (ret == 0) {
			resStr = "error";
		}
		handleProcess(bean, user,"转换退回","已提交","提交成功");
		if("0".equals(is_take_off_damages)){//不扣违约金
			getPmInfo();//发送短消息
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveReplace() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveReplace> getListByEntity(WmsInveReplace queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveReplace> beanList = wmsinvereplaceDao.getListByEntity(queryInfo);
		return beanList;
	}

	@Override
	public Map<String, Object> get4Appro(Integer wms_inve_replace_id) {
		return wmsinvereplaceDao.get4Appro(wms_inve_replace_id);
	}
	
	/**
	 * 财务审批
	 */
	@Override
	@Transactional
	public String cwsp(WmsInveReplace bean, UserBean user, String pass,
			String advice) {
		if("true".equals(pass)){//审批通过
			bean = wmsinvereplaceDao.get(bean.getWms_inve_replace_id());
			WmsInveTransa wmsInveTransa_old = wmsInveTransaDao.get(bean.getWms_inve_transa_id());
			
			WmsInvePruductCategory wmsInvePruductCategory = wmsInvePruductCategoryDao.get(bean.getNew_wms_inve_pruduct_category_id());
			
			Map<String,Object> map = new HashMap<>();
			map.put("wms_inve_transa_id", bean.getWms_inve_transa_id());
			map.put("data_status", "18");
			Map<String,Object> transaMap = wmsInveTransaDao.getProtocolRenewalnew(map);
			//原始协议表主键
			Integer wms_inve_transa_protocol_id = (Integer) transaMap.get("wms_inve_transa_protocol_id");
			//原始上单产品表主键
			Integer wms_inve_transa_prod_id= (Integer) transaMap.get("wms_inve_transa_prod_id");

			WmsInveTransaProd wmsInveTransaProd_old = wmsInveTransaProdDao.get(wms_inve_transa_prod_id);
			
			WmsInveTransaProtocol wProtocol = wmsInveTransaProtocolDao.get(wms_inve_transa_protocol_id);
			
			Date end_of_date = (Date) transaMap.get("end_of_date");
			
			//判断是否为不扣违约金加钱转
			boolean is_add = false;
			String relpace_type_detail="";//转产品的具体类型1等额转2减钱转3加钱转4原加钱转部分
			BigDecimal convert_account = bean.getProduct_account();
			BigDecimal new_product_amount = bean.getProduct_account();
			String new_trans_id="";
			if(end_of_date.compareTo(bean.getBegin_of_date())>0 && bean.getIs_take_off_damages().equals("0") && (bean.getExtra_pay_amount() != null && bean.getExtra_pay_amount().compareTo(BigDecimal.ZERO)!= 0)){
				is_add = true;
				relpace_type_detail = "4";
				new_product_amount = bean.getProduct_account().subtract(bean.getOrg_product_amount());
				WmsInveTransa wTransa_new = CopyWmsInveTransaMethod(wmsInveTransa_old,bean.getBegin_of_date(),user,bean.getOrg_product_amount(),relpace_type_detail,convert_account);
				WmsInveTransaProd wTransaprod_new = CopyWmsInveTransaPordMethod(wTransa_new, wmsInveTransaProd_old, wmsInvePruductCategory, user);
				CopyWmsInveTransaCard(wTransa_new, wTransaprod_new);
				initProtocol(wTransaprod_new, wTransa_new, wProtocol,wmsInvePruductCategory);
				new_trans_id = ""+wTransa_new.getWms_inve_transa_id();
				//判读原单据是否完全转出，如果完全转出，则更新原单据为已完成状态，如果部分转出，则更新原单据为收益中，并更新原单据的投资金额
				//生成转出金额部分的上单信息表、上单产品表信息、收益信息、支付信息，单据来源为转产品，转产品类型为加钱转的原部分
				//生成原始单据的赎回单据，赎回的具体类型为1
			}
			//生成上单表、上单产品表信息、收益信息、支付信息
			if(bean.getExtra_pay_amount() != null && bean.getExtra_pay_amount().compareTo(BigDecimal.ZERO)!= 0){
				relpace_type_detail = "3";//加钱转
			}else if(bean.getProduct_account().compareTo(wmsInveTransaProd_old.getProduct_account())<0){
				relpace_type_detail = "2";//减钱转
			}else{
				relpace_type_detail = "1";//等额转
			}
			WmsInveTransa wTransa_new = CopyWmsInveTransaMethod(wmsInveTransa_old,bean.getBegin_of_date(),user,new_product_amount,relpace_type_detail,convert_account);
			WmsInveTransaProd wTransaprod_new = CopyWmsInveTransaPordMethod(wTransa_new, wmsInveTransaProd_old, wmsInvePruductCategory, user);
			CopyWmsInveTransaCard(wTransa_new, wTransaprod_new);
			initProtocol(wTransaprod_new, wTransa_new, wProtocol,wmsInvePruductCategory);
			//生成原单据的赎回单据
			saveWmsInveRedeem(wmsInveTransa_old,wmsInveTransaProd_old, user, bean,is_add,end_of_date);
			new_trans_id+=wTransaprod_new.getWms_inve_transa_id();
			bean.setNew_wms_inve_transa_id(new_trans_id);
			bean.setData_status("6");
			wmsinvereplaceDao.update(bean);
		}else{
			bean.setData_status("4");//审核退回
			wmsinvereplaceDao.update(bean);
		}
		handleProcess(bean, user,"财务审核","true".equals(pass)?"同意":"不同意",advice);
		return "success";
	}

	private WmsInveTransa CopyWmsInveTransaMethod(WmsInveTransa wTransa_old,java.sql.Date date, UserBean user,BigDecimal new_product_amount,String relpace_type_detail,BigDecimal convert_account) {
		Integer sales_man_id = wTransa_old.getBel_salesman_id_id() == null ? wTransa_old.getSalesman_id() : wTransa_old.getBel_salesman_id_id();
		//根据user_id获取状态
		PmPersonnel pmPersonnel = pmPersonnelDao.get(sales_man_id);
		String sales_man_name = pmPersonnel.getPersonnel_name();
		String personnel_state = "1";
		if("7".equals(pmPersonnel.getPersonnel_state())){
			personnel_state = "2";
		}
		//上单信息表
		WmsInveTransa wTransa_new = new WmsInveTransa();
		wTransa_new.setWms_inve_customer_id(wTransa_old.getWms_inve_customer_id());
		wTransa_new.setBill_code(CodeNoUtil.getEnviNoteCode());
		wTransa_new.setId_card(wTransa_old.getId_card());//身份证号
		wTransa_new.setCus_name(wTransa_old.getCus_name());//客户姓名
		wTransa_new.setCus_birthday(wTransa_old.getCus_birthday());//出生日期
		wTransa_new.setCus_gender(wTransa_old.getCus_gender());//性别
		wTransa_new.setCus_address(wTransa_old.getCus_address());//居住地址
		wTransa_new.setPost_code(wTransa_old.getPost_code());//邮政编码
		wTransa_new.setAddress_phone(wTransa_old.getAddress_phone());//居住电话
		wTransa_new.setWork_phone(wTransa_old.getWork_phone());//办公室电话
		wTransa_new.setCus_fax(wTransa_old.getCus_fax());//传真号码
		wTransa_new.setContact_address(wTransa_old.getContact_address());//通讯地址
		wTransa_new.setCustomer_email(wTransa_old.getCustomer_email());//电邮地址
		wTransa_new.setBill_send_mode(wTransa_old.getBill_send_mode());//账单默认发送方式
		wTransa_new.setInve_transa_type(wTransa_old.getInve_transa_type());//理财单类型
		wTransa_new.setProduct_total_count_lower(wTransa_old.getProduct_total_count_lower());//合计投资金额（小写）单位元
		wTransa_new.setProduct_total_count_upper(wTransa_old.getProduct_total_count_upper());//合计投资金额（大写）
		wTransa_new.setData_status("4");//单据状态4收益中
		wTransa_new.setAccount_status("3");//金额状态 2已支付3已到账
		wTransa_new.setPay_type(4);//1代表现金
		//支付时间为协议到期日期
		wTransa_new.setDate_of_payment(date);//支付时间
		wTransa_new.setPay_account(new_product_amount);//支付金额合计
		wTransa_new.setCash_pay_name(wTransa_old.getCash_pay_name());//现金支付人姓名
		wTransa_new.setAct_account_usr_id(wTransa_old.getAct_account_usr_id());//到账操作人主键
		//到账时间
		wTransa_new.setPay_usr_id(wTransa_old.getPay_usr_id());//支付操作人主键
		wTransa_new.setDate_of_act(date);//到账时间
		wTransa_new.setAct_account(new_product_amount);//到账金额合计==上单信息表中的理财金额
		wTransa_new.setFee_account(new BigDecimal(0));//手续费（合计金额与到账金额差）
		wTransa_new.setSalesman_name(sales_man_name);//业务员的名字
		wTransa_new.setSalesman_id(sales_man_id);//业务员主键
		wTransa_new.setSalesman_city(wTransa_old.getSalesman_city());//业务员城市
		wTransa_new.setSalesman_shortcode(pmPersonnel.getPersonnel_shortcode());//业务员
		wTransa_new.setSalesman_city_code(pmPersonnel.getPersonnel_regionnumber());//业务员城市编码
		wTransa_new.setSalesman_dept_id(pmPersonnel.getPersonnel_deptid());//业务员所在部门ID
		wTransa_new.setEnable_flag("1");//是否有效
		wTransa_new.setMobile_phone(wTransa_old.getMobile_phone());//移动电话
		wTransa_new.setDepartment_manager_id(wTransa_old.getDepartment_manager_id());//部门经理ID
		wTransa_new.setDepartment_manager_dept_id(wTransa_old.getDepartment_manager_dept_id());//部门经理城市编码
		wTransa_new.setDepartment_manager_city_code(wTransa_old.getDepartment_manager_city_code());//部门经理部门ID
		wTransa_new.setVice_general_manager_dept_id(wTransa_old.getVice_general_manager_dept_id());//副总经理ID
		wTransa_new.setVice_general_manager_id(wTransa_old.getVice_general_manager_id());//副总经理城市编码
		wTransa_new.setVice_general_manager_city_code(wTransa_old.getVice_general_manager_city_code());//副总经理部门ID
		wTransa_new.setGeneral_manager_id(wTransa_old.getGeneral_manager_id());//总经理ID
		wTransa_new.setGeneral_manager_dept_id(wTransa_old.getGeneral_manager_dept_id());//总经理城市编码
		wTransa_new.setGeneral_manager_city_code(wTransa_old.getGeneral_manager_city_code());//总经理部门ID
		wTransa_new.setCostomer_id(wTransa_old.getCostomer_id());//CRM关联信息表主键
		wTransa_new.setCustomer_source(wTransa_old.getCustomer_source());//客户来源
		wTransa_new.setCustomer_num(wTransa_old.getCustomer_num());//客户编号
		wTransa_new.setCreate_user_id(user.getUserId());//创建人
		wTransa_new.setCreate_user_name(user.getRealname());//创建人姓名
		wTransa_new.setCreate_user_dept_id(user.getDeptId());//创建人所在部门
		wTransa_new.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));//创建时间
		wTransa_new.setLast_update_user_id(user.getUserId());//最后修改人
		wTransa_new.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));//最后修改时间
		wTransa_new.setOld_wms_inve_transa_id(wTransa_old.getWms_inve_transa_id());//原上单单据id
		wTransa_new.setBill_source(2);//单据来源 1标示续期生产的单据2转产品
		wTransa_new.setRelpace_type_detail(relpace_type_detail);
		wTransa_new.setConvert_account(convert_account);
		//wTransa_new.setBel_salesman_id_id(wTransa_old.getBel_salesman_id_id());
		wTransa_new.setTrans_salesman_status(personnel_state);//兼职单还是在职单
		if(wTransa_old.getBill_source()!=null&&1==wTransa_old.getBill_source()){
			wTransa_new.setOld_date_of_payment(wTransa_old.getOld_date_of_payment());//原单据的上单支付时间
		}else{
			wTransa_new.setOld_date_of_payment(wTransa_old.getDate_of_payment());//原单据的上单支付时间
		}
		wmsInveTransaDao.save(wTransa_new);
		return wTransa_new;
	}
	
	private WmsInveTransaProd CopyWmsInveTransaPordMethod(WmsInveTransa wTransa_new,WmsInveTransaProd wmsInveTransaProd_old, WmsInvePruductCategory wCategory,UserBean user) {
		//上单产品信息表
		WmsInveTransaProd wmsInveTransaProd_new = new WmsInveTransaProd();
		wmsInveTransaProd_new.setWms_inve_transa_id(wTransa_new.getWms_inve_transa_id());//上单信息表主键
		wmsInveTransaProd_new.setWms_inve_pruduct_category_id(wCategory.getWms_inve_pruduct_category_id());//产品表主键
		wmsInveTransaProd_new.setCategory_name(wCategory.getCategory_name());//产品名称
		wmsInveTransaProd_new.setProduct_deadline(wCategory.getCategory_deadline());//理财期限（月）
		//区分是否是换了产品
		wmsInveTransaProd_new.setProduct_interest(wCategory.getCategory_return_rate().toString());//年化收益率（%）
		wmsInveTransaProd_new.setProduct_account(wTransa_new.getPay_account());//理财金额
		wmsInveTransaProd_new.setIs_finish("1");//债权匹配是否完成
		wmsInveTransaProd_new.setCard_owner_name(wmsInveTransaProd_old.getCard_owner_name());//卡主姓名
		wmsInveTransaProd_new.setBank_of_deposit(wmsInveTransaProd_old.getBank_of_deposit());//开户行
		wmsInveTransaProd_new.setCard_no(wmsInveTransaProd_old.getCard_no());//银行卡号
		wmsInveTransaProd_new.setCreate_user_id(user.getUserId());//创建人
		wmsInveTransaProd_new.setCreate_user_name(user.getRealname());//创建人姓名
		wmsInveTransaProd_new.setOrg_product_account(wTransa_new.getPay_account());
		
		wmsInveTransaProd_new.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));//创建时间
		
		wmsInveTransaProd_new.setEnable_flag("1");//数据状态
		wmsInveTransaProd_new.setProduct_deadline_name(wCategory.getCategory_deadline()+"期");//理财期限

		wmsInveTransaProd_new.setBank_of_deposit_pro(wmsInveTransaProd_old.getBank_of_deposit_pro());//开户行(省)
		wmsInveTransaProd_new.setBank_of_deposit_city(wmsInveTransaProd_old.getBank_of_deposit_city());//开户行(市)
		wmsInveTransaProd_new.setBank_branch(wmsInveTransaProd_old.getBank_branch());//开户行所属支行
		
		wmsInveTransaProd_new.setCategory_code(wCategory.getCategory_code());
		wmsInveTransaProd_new.setCategory_type(wCategory.getCategory_type().toString());
		wmsInveTransaProd_new.setIs_allopatry(wmsInveTransaProd_old.getIs_allopatry());//是否匹配异地债权
		wmsInveTransaProd_new.setIs_customer_confirmation(wmsInveTransaProd_old.getIs_customer_confirmation());//是否客户确认
		wmsInveTransaProd_new.setLast_update_user_id(user.getUserId());//最后修改人
		wmsInveTransaProd_new.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));//最后修改时间
		wmsInveTransaProdDao.save(wmsInveTransaProd_new);
		return wmsInveTransaProd_new;
	}
	
	private void CopyWmsInveTransaCard(WmsInveTransa wInveTransa2,WmsInveTransaProd wTransaProd2) {
		WmsInveTransaCard wmsInveTransaCard = new WmsInveTransaCard();
		wmsInveTransaCard.setWms_inve_transa_id(wInveTransa2.getOld_wms_inve_transa_id());
		List<WmsInveTransaCard>  listInveTransaCards=wmsInveTransaCardDao.getListByEntity(wmsInveTransaCard);
		//自动续期是现金支付 需要添加一条支付现金支付信息
		WmsInveTransaCard w = new WmsInveTransaCard();
		w.setWms_inve_transa_id(wInveTransa2.getWms_inve_transa_id());//上单信息表主键
		w.setCard_owner_name(wInveTransa2.getCus_name());//卡主姓名
		
		w.setPay_account(wTransaProd2.getProduct_account());//实际支付金额
		w.setIs_finish("1");//是否已到账
		w.setAct_account(wTransaProd2.getProduct_account());//到账金额
		if(listInveTransaCards!=null&&listInveTransaCards.size()>0){
			w.setCreate_user_id(listInveTransaCards.get(0).getCreate_user_id());//创建人
			w.setCreate_user_name(listInveTransaCards.get(0).getCreate_user_name());//创建人姓名
		}
		w.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));//创建时间
		w.setEnable_flag("1");//是否有效
		w.setPay_type("4");//支付方式1 现金 2银行卡3现金+银行卡4产品转换5单据续期
		w.setShould_pay_account(wTransaProd2.getProduct_account());//应支付金额
		wmsInveTransaCardDao.save(w);
	}
	
	/**
     * 设置日期 参数int i是为了方法的公共使用  主要的目的就是计算的月份上有差异对月份进行加减
     * @param wmsFinaCreLoanApp
     * @return
     */
    private static java.sql.Date setDatebyCalendar(Date sDate,int i){  
    	
        java.sql.Date  date1;
        Calendar  calendar=new GregorianCalendar();   
        calendar.setTime(sDate);
        calendar.add(Calendar.MONTH, +i);
        java.util.Date date_=calendar.getTime();
        date1=new java.sql.Date(date_.getTime()); 
        return date1;
    }
    
    public static void main(String args[]){
    	java.sql.Date  date1;
        Calendar  calendar=new GregorianCalendar(); 
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.MONTH, +12);
        java.util.Date date_=calendar.getTime();
        date1=new java.sql.Date(date_.getTime()); 
        System.out.println(date1);
        java.util.Date date =  DateUtil.changeDay(DateUtil.sqlDate2UtilDate(setDatebyCalendar(date1, 12)), -1);
        System.out.println(date);
    }
	
	public String initProtocol(WmsInveTransaProd wmsTransaProd,WmsInveTransa wmsInveTransa,	WmsInveTransaProtocol wProtocol,WmsInvePruductCategory category){
		java.sql.Date end_of_date = null;
    	Date startDate = null;
    	if(StringUtil.isBlank(wmsInveTransa.getOld_date_of_payment_str())){
    		startDate = wmsInveTransa.getDate_of_payment();
		}else{
			startDate = wmsInveTransa.getOld_date_of_payment();
		}
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(startDate.compareTo(df.parse(CountIncome.MAGIC_DATE_OF_PAYMENT_STR))>=0){
				java.util.Date date =  DateUtil.changeDay(DateUtil.sqlDate2UtilDate(setDatebyCalendar(wmsInveTransa.getDate_of_payment(), wmsTransaProd.getProduct_deadline())), -1);
        		end_of_date = new java.sql.Date(date.getTime());
        	}else{
        		end_of_date = setDatebyCalendar(wmsInveTransa.getDate_of_payment(), wmsTransaProd.getProduct_deadline());
        	}
		} catch (ParseException e) {
		}
        //保存协议签订信息
		String result="success";
       WmsInveTransaProtocol wInveTransaProtocol = new WmsInveTransaProtocol();
       wInveTransaProtocol.setWms_inve_transa_id(wmsTransaProd.getWms_inve_transa_id());//上单信息表主键
       wInveTransaProtocol.setWms_inve_transa_prod_id(wmsTransaProd.getWms_inve_transa_prod_id());//上单产品表主键
       wInveTransaProtocol.setProt_code(CodeNoUtil.getEnviProCode());//获取协议编号
       wInveTransaProtocol.setA_name(wProtocol.getA_name());//甲方姓名
       wInveTransaProtocol.setA_id_card(wProtocol.getA_id_card());//甲方身份证号
       wInveTransaProtocol.setProduct_account(wmsTransaProd.getProduct_account());
       wInveTransaProtocol.setDate_of_payment(wmsInveTransa.getDate_of_payment());//当前期的开始日期
       wInveTransaProtocol.setA_bank(wProtocol.getA_bank());
       wInveTransaProtocol.setA_number(wProtocol.getA_number());
       wInveTransaProtocol.setB_name(wProtocol.getB_name());//客户姓名
       wInveTransaProtocol.setB_id_card(wProtocol.getB_id_card());//客户身份证号
       wInveTransaProtocol.setCategory_name(wmsTransaProd.getCategory_name());
       wInveTransaProtocol.setProduct_deadline(wmsTransaProd.getProduct_deadline());
       wInveTransaProtocol.setSign_place(wProtocol.getSign_place());
       wInveTransaProtocol.setB_data(wProtocol.getB_data());
       wInveTransaProtocol.setEnd_of_date(end_of_date);//结束日期
       wInveTransaProtocol.setCreate_user_id(wProtocol.getCreate_user_id());
       wInveTransaProtocol.setCreate_user_name(wProtocol.getCreate_user_name());
       wInveTransaProtocol.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
       wInveTransaProtocol.setLast_update_user_id(wProtocol.getCreate_user_id());
       wInveTransaProtocol.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
       wInveTransaProtocol.setEnable_flag("1");
       wInveTransaProtocol.setContact_address(wProtocol.getContact_address());
       wInveTransaProtocol.setPost_code(wProtocol.getPost_code());
       wInveTransaProtocol.setWms_inve_redeem_id(0);
       wmsInveTransaProtocolDao.save(wInveTransaProtocol);
       
       //直接做一条测试据存储到债权表中
       WmsInveTransaMatch inveTransaMatch = new WmsInveTransaMatch();
       inveTransaMatch.setWms_inve_transa_prod_id(wmsTransaProd.getWms_inve_transa_prod_id());
       inveTransaMatch.setWms_fina_cre_repay_id(0);
       inveTransaMatch.setCredit_name("测试1");
       inveTransaMatch.setCredit_id_card("111111111111111111");
       inveTransaMatch.setAssign_account(wmsTransaProd.getProduct_account());
       inveTransaMatch.setDate_of_assign(new java.sql.Date(System.currentTimeMillis()));
       inveTransaMatch.setRepay_begin_date(new java.sql.Date(System.currentTimeMillis()));
       inveTransaMatch.setRepay_end_date(new java.sql.Date(System.currentTimeMillis()));
       inveTransaMatch.setProduct_interest_month( new BigDecimal(1) );
       inveTransaMatch.setCreate_user_id(wProtocol.getCreate_user_id());
       inveTransaMatch.setCreate_timestamp(new Timestamp(wmsInveTransa.getDate_of_payment().getTime()));
       inveTransaMatch.setLast_update_user_id(wProtocol.getCreate_user_id());
       inveTransaMatch.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
       inveTransaMatch.setEnable_flag("1");
       inveTransaMatch.setWms_inve_redeem_id(0);
       inveTransaMatch.setWms_inve_transa_protocol_id(wInveTransaProtocol.getWms_inve_transa_protocol_id());
       wmsInveTransaMatchDao.save(inveTransaMatch);
       //创建一个人 直接拿签订合同那个人来创建收益
       UserBean user=new UserBean();
       user.setUserId(wProtocol.getCreate_user_id());
       user.setRealname(wProtocol.getCreate_user_name()); 
        try {
        	//计算收益和日志
        	result =handleIncomeAndLogInfo(wInveTransaProtocol, category,user);
		} catch (Exception e) {
			result="error";
			return result;
		}
        return result;
	}
	
		private String handleIncomeAndLogInfo(WmsInveTransaProtocol bean, WmsInvePruductCategory category,UserBean user) {
			CountIncomeFactory.getCountIncome(bean).getIncomeAndLog(bean, user);
	    	return "success";
		}
		private String saveWmsInveRedeem(WmsInveTransa wmsInveTransa_old, WmsInveTransaProd wmsInveTransaProd_old, UserBean user,WmsInveReplace wmsInveReplace,boolean is_add, java.sql.Date end_of_date)
	    {
	    	Timestamp sysTime = new Timestamp(System.currentTimeMillis());
	    	WmsInveRedeem bean = new WmsInveRedeem();
	    	bean.setRedeem_date(wmsInveReplace.getBegin_of_date());//赎回日期
	        bean.setCreate_user_id(user.getUserId());
	        bean.setCreate_user_name(user.getRealname());
	        bean.setLast_update_user_id(user.getUserId());
	        bean.setCreate_user_dept_id(user.getDeptId());
	        bean.setCreate_timestamp(sysTime);
	        bean.setLast_update_timestamp(sysTime);
	        bean.setBill_code(CodeNoUtil.getReturnRedeemCode());
	        bean.setTotal_invest_amount(wmsInveTransaProd_old.getOrg_product_account());
	        bean.setWms_inve_customer_id(wmsInveTransa_old.getWms_inve_customer_id());
	        WmsInveTransa wTransa_old = wmsInveTransaDao.get(wmsInveTransaProd_old.getWms_inve_transa_id());
	        if(wTransa_old.getBel_salesman_id_id() != null){
	        	bean.setSalesman_id(wTransa_old.getBel_salesman_id_id());
	        }else{
	        	bean.setSalesman_id(wTransa_old.getSalesman_id());
	        }
	        
	        bean.setSalesman_dept_id(wTransa_old.getSalesman_dept_id());
	        bean.setId_card(wTransa_old.getId_card());
	        bean.setCus_name(wTransa_old.getCus_name());
	        bean.setRedeem_apply_total_amount(wmsInveReplace.getOrg_product_amount());
	        bean.setRedeem_reality_total_amount(wmsInveReplace.getOrg_product_amount());
	        bean.setIs_special_approval("0");
	        bean.setIs_payback("1");
	        //判断是否到期
	        if(wmsInveReplace.getBegin_of_date().compareTo(end_of_date)>=0){
	        	bean.setRedeem_type("5");//赎回类型1未到期赎回 2续期赎回 3转产品赎回(未到期)4到期赎回5转产品赎回（到期）
	        }else{
	        	bean.setRedeem_type("3");//赎回类型1未到期赎回 2续期赎回 3转产品赎回(未到期)4到期赎回5转产品赎回（到期）
	        }
	        bean.setEnable_flag("1");
	        bean.setIs_take_off_damages(wmsInveReplace.getIs_take_off_damages());//不收违约金
	        bean.setRedeem_company_reason(wmsInveReplace.getRedeem_company_reason());//协议到期
	        bean.setIs_fully_redeemed("1");
	        bean.setIs_finish("2");
	        bean.setIs_protocol_finish("2");
	        bean.setData_status("6");//单据状态为已完成
	        bean.setRedeem_type_detail(is_add ?"1":"");//赎回具体类型1转产品赎回加钱转部分（不扣业务员提成）
	        //客户姓名、身份证号
	        bean.setRedeem_reason(wmsInveReplace.getReplace_reason());//赎回事由
	        wmsinveredeemDao.save(bean);
	        WmsInveRedeemDetail wmsInveRedeemDetail = new WmsInveRedeemDetail();
	        wmsInveRedeemDetail.setCreate_user_id(user.getUserId());
	        wmsInveRedeemDetail.setLast_update_user_id(user.getUserId());
	        wmsInveRedeemDetail.setCreate_user_dept_id(user.getDeptId());
	        wmsInveRedeemDetail.setCreate_timestamp(new Timestamp(bean.getRedeem_date().getTime()));
	        wmsInveRedeemDetail.setLast_update_timestamp(new Timestamp(bean.getRedeem_date().getTime()));
	        wmsInveRedeemDetail.setWms_inve_redeem_id(bean.getWms_inve_redeem_id());
	        wmsInveRedeemDetail.setIs_protocol_finish("0");
	        wmsInveRedeemDetail.setEnable_flag("1");
	        wmsInveRedeemDetail.setDeduct_money(new BigDecimal(0));	
	        wmsInveRedeemDetail.setDeduct_tax_point(new BigDecimal(0));
	        wmsInveRedeemDetail.setWms_inve_transa_id(wmsInveTransaProd_old.getWms_inve_transa_id());
	        wmsInveRedeemDetail.setWms_inve_transa_prod_id(wmsInveTransaProd_old.getWms_inve_transa_prod_id());
	        wmsInveRedeemDetail.setProduct_account(wmsInveTransaProd_old.getProduct_account());
	        wmsInveRedeemDetail.setRedeem_amount(wmsInveReplace.getOrg_product_amount());
	        wmsInveRedeemDetail.setCategory_name(wmsInveTransaProd_old.getCategory_name());
	        wmsInveRedeemDetail.setManagement_fee_rate(new BigDecimal("0"));
	        BigDecimal extra_account = wmsInveTransaProd_old.getProduct_account().subtract(wmsInveReplace.getOrg_product_amount());
	        if(extra_account.compareTo(BigDecimal.ZERO) >0){//上单未完成时需要重新生成新的合同表数据
	        	wmsInveRedeemDetail.setIs_fully_redeemed("0");
	        }else{
	        	wmsInveRedeemDetail.setIs_fully_redeemed("1");
	        }
	        // 保存赎回明细表信息
	        wmsinveredeemdetailDao.save(wmsInveRedeemDetail);
	        //更改原单据更新理财中的金额
	        WmsInveTransaProd prod = new WmsInveTransaProd();
	        prod.setWms_inve_transa_prod_id(wmsInveTransaProd_old.getWms_inve_transa_prod_id());
	        prod.setProduct_account(extra_account);
	        wmsInveTransaDao.updateTransaProdAmount(prod);
	        prod = wmsInveTransaProdDao.get(wmsInveTransaProd_old.getWms_inve_transa_prod_id());
	        initProtocol(bean,prod, wmsInveTransa_old,wmsInveRedeemDetail,user);
	        
	        //更新产品转换表的状态为已完成，并更新新生成的上单表主键
			WmsInveTransa wmsInveTransa = new WmsInveTransa();
	        wmsInveTransa.setWms_inve_transa_id(wmsInveTransaProd_old.getWms_inve_transa_id());
			if(extra_account.compareTo(BigDecimal.ZERO) == 0){
				wmsInveTransa.setData_status("6");//已完成
			}else{
				wmsInveTransa.setData_status("4");//收益中
			}
			wmsInveTransaDao.updateDataStatus(wmsInveTransa); 
	    	return "success";
	    }

		private void initProtocol(WmsInveRedeem bean, WmsInveTransaProd prod,
				WmsInveTransa wmsInveTransa_old,  WmsInveRedeemDetail wmsInveRedeemDetail,UserBean user) {

            // 赎回明细中对应的单据完全赎回，则更新匹配和协议的赎回单据ID
            if (wmsInveRedeemDetail.getIs_fully_redeemed().equals("1"))
            {
            	 WmsInveTransaProtocol protocol = new WmsInveTransaProtocol();
                 protocol.setWms_inve_transa_id(wmsInveTransa_old.getWms_inve_transa_id());
                 protocol.setWms_inve_redeem_id(0);
                 protocol.setEnable_flag("1");
                 WmsInveTransaProtocol transaprotocol =  wmsinvetransaprotocolDao.getProtocolByCondition(protocol);
                //完全赎回的单据，把匹配过的债权变成失效状态
            	WmsInveTransaMatch wmsInveTransaMatchSearch = new WmsInveTransaMatch();
                wmsInveTransaMatchSearch.setWms_inve_transa_prod_id(wmsInveRedeemDetail.getWms_inve_transa_prod_id());
                wmsInveTransaMatchSearch.setWms_inve_redeem_id(wmsInveRedeemDetail.getWms_inve_redeem_id());
                wmsinvetransamatchDao.updateRedeem(wmsInveTransaMatchSearch);
                //完全赎回的单据，把协议变成失效状态
                WmsInveTransaProtocol updatebean = new WmsInveTransaProtocol();
                updatebean.setWms_inve_redeem_id(wmsInveRedeemDetail.getWms_inve_redeem_id());
                updatebean.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());
                updatebean.setWms_inve_transa_prod_id(wmsInveRedeemDetail.getWms_inve_transa_prod_id());
                updatebean.setLast_update_timestamp(new Timestamp(bean.getRedeem_date().getTime()));
                updatebean.setDate_of_payment(bean.getRedeem_date());
                wmsinvetransaprotocolDao.updateforback(updatebean);
               
                /*如果是完全赎回需要对收益表和上单操作日志表的预处理进行处理-- baisong 2016/4/19*/
                transaprotocol.setWms_inve_redeem_id(wmsInveRedeemDetail.getWms_inve_redeem_id());
                wmsInveTransaProtocolService.SethandleIncomeAndLogInfoSH(transaprotocol, user);
                
            }else if(wmsInveRedeemDetail.getIs_fully_redeemed().equals("0")){
             	//把匹配过的债权变成失效状态
            	WmsInveTransaMatch wmsInveTransaMatchSearch = new WmsInveTransaMatch();
                wmsInveTransaMatchSearch.setWms_inve_transa_prod_id(wmsInveRedeemDetail.getWms_inve_transa_prod_id());
                wmsInveTransaMatchSearch.setWms_inve_redeem_id(wmsInveRedeemDetail.getWms_inve_redeem_id());
                wmsinvetransamatchDao.updateRedeem(wmsInveTransaMatchSearch);
                //把协议变成失效状态
                WmsInveTransaProtocol updatebean = new WmsInveTransaProtocol();
                updatebean.setWms_inve_redeem_id(wmsInveRedeemDetail.getWms_inve_redeem_id());
                updatebean.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());
                updatebean.setWms_inve_transa_prod_id(wmsInveRedeemDetail.getWms_inve_transa_prod_id());
                updatebean.setLast_update_timestamp(new Timestamp(bean.getRedeem_date().getTime()));
                updatebean.setDate_of_payment(bean.getRedeem_date());
                wmsinvetransaprotocolDao.updateforback(updatebean);
                updatebean.setLast_update_timestamp(null);
                List<WmsInveTransaProtocol>  protocolList = wmsinvetransaprotocolDao.getListByEntity(updatebean);
                //把剩余投资金额进行重新债权匹配(假)
                BigDecimal  sy =wmsInveRedeemDetail.getProduct_account().subtract(wmsInveRedeemDetail.getRedeem_amount());
                //正常应该是多条，为了简单无论剩余多少金额都只给匹配一条假数据
                WmsInveTransaMatch inveTransaMatch = new WmsInveTransaMatch();
                inveTransaMatch.setWms_inve_transa_prod_id(wmsInveRedeemDetail.getWms_inve_transa_prod_id());
                inveTransaMatch.setWms_fina_cre_repay_id(0);
                inveTransaMatch.setCredit_name("测试1");
                inveTransaMatch.setCredit_id_card("111111111111111111");
                inveTransaMatch.setAssign_account(sy);
                inveTransaMatch.setDate_of_assign(new java.sql.Date(System.currentTimeMillis()));
                inveTransaMatch.setRepay_begin_date(new java.sql.Date(System.currentTimeMillis()));
                inveTransaMatch.setRepay_end_date(new java.sql.Date(System.currentTimeMillis()));
                inveTransaMatch.setProduct_interest_month( new BigDecimal(1) );
                inveTransaMatch.setCreate_user_id(user.getUserId());
                inveTransaMatch.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
                inveTransaMatch.setLast_update_user_id(user.getUserId());
                inveTransaMatch.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
                inveTransaMatch.setEnable_flag("1");
                inveTransaMatch.setWms_inve_redeem_id(0);
                wmsInveTransaMatchDao.save(inveTransaMatch);
                //把剩余投资金额进行重新打印协议(假)
                WmsInveTransaProtocol wInveTransaProtocol = new WmsInveTransaProtocol();
                wInveTransaProtocol.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());//上单信息表主键
                wInveTransaProtocol.setWms_inve_transa_prod_id(wmsInveRedeemDetail.getWms_inve_transa_prod_id());//上单产品表主键
                wInveTransaProtocol.setProt_code(CodeNoUtil.getEnviProCode());//获取协议编号
                wInveTransaProtocol.setA_name("测试甲");//甲方姓名
                wInveTransaProtocol.setA_id_card("111111111111111111");//甲方身份证号
                wInveTransaProtocol.setProduct_account(sy);
                //wInveTransaProtocol.setDate_of_payment(getNow());
                wInveTransaProtocol.setDate_of_payment(protocolList.get(0).getDate_of_payment());
                wInveTransaProtocol.setA_bank("111111111111111111");
                wInveTransaProtocol.setA_number("111111111111111111");
                wInveTransaProtocol.setB_name(wmsInveTransa_old.getCus_name());//客户姓名
                wInveTransaProtocol.setB_id_card(wmsInveTransa_old.getId_card());//客户身份证号
                wInveTransaProtocol.setCategory_name(prod.getCategory_name());
                wInveTransaProtocol.setProduct_deadline(prod.getProduct_deadline());
                wInveTransaProtocol.setSign_place("111111111111111111");
                wInveTransaProtocol.setB_data("111111111111111111");
                //wInveTransaProtocol.setEnd_of_date(setDatebyCalendar(wInveTransaProtocol.getDate_of_payment(), wInveTransaProtocol.getProduct_deadline()));//结束日期
                wInveTransaProtocol.setEnd_of_date(protocolList.get(0).getEnd_of_date());//结束日期
                wInveTransaProtocol.setCreate_user_id(user.getUserId());
                wInveTransaProtocol.setCreate_user_name(user.getRealname());
                wInveTransaProtocol.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
                wInveTransaProtocol.setLast_update_user_id(user.getUserId());
                wInveTransaProtocol.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
                wInveTransaProtocol.setEnable_flag("1");
                wInveTransaProtocol.setContact_address("111111111111111111");
                wInveTransaProtocol.setPost_code("111111");
                wInveTransaProtocol.setWms_inve_redeem_id(0);
                wmsinvetransaprotocolDao.save(wInveTransaProtocol);
                wInveTransaProtocol.setWms_inve_redeem_id(wmsInveRedeemDetail.getWms_inve_redeem_id());
                //把剩余的投资金额的理财单据重新计算收益和生产一条新的日志信息
                wmsInveTransaProtocolService.SethandleIncomeAndLogInfoSH(wInveTransaProtocol, user);
            }
        
		}

		@Override
		public String zjlsp(WmsInveReplace bean, UserBean user, String pass) {
			String data_status = "true".equals(pass) ? "3":"5";
			bean.setData_status(data_status);//审核退回
			wmsinvereplaceDao.update(bean);
			String userCode = PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.usercode");
			PmPersonnel pm  = new PmPersonnel();
			pm.setPersonnel_shortcode(userCode);
			pm = pmpersonnelDao.getListByEntity(pm).get(0);
			user = new UserBean();
			user.setDeptSimpleName(pm.getPersonnel_deptname());
			user.setRealname(pm.getPersonnel_name());
			user.setPostName(pm.getPersonnel_postname());
			handleProcess(bean, user,"总经理审核","true".equals(pass)?"同意":"不同意","");
			return "success";
		}

		@Override
		public String cancel(WmsInveReplace bean, UserBean user) {

			String resStr = "success";
			int ret = 0;
			bean.setData_status("7");
			bean.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
			bean.setLast_update_user_id(user.getUserId());
			bean.setEnable_flag("1");
			ret = wmsinvereplaceDao.update(bean);
			if (ret == 0) {
				resStr = "error";
			}
			//更新理财上单表的单据状态
			WmsInveTransa wmsInveTransa = new WmsInveTransa();
			
			wmsInveTransa.setWms_inve_transa_id(bean.getWms_inve_transa_id());
			wmsInveTransa.setData_status("4");//产品转换中
			wmsInveTransaDao.updateDataStatus(wmsInveTransa); 
			handleProcess(bean, user,"取消申请","已提交","提交成功");
			return resStr;
		
		}
}
