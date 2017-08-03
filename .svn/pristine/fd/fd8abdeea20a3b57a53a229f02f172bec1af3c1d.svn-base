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
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.zx.emanage.inve.persist.WmsInveCustomerCardDao;
import com.zx.emanage.inve.persist.WmsInveCustomerCardUpdateLogDao;
import com.zx.emanage.inve.persist.WmsInveCustomerDao;
import com.zx.emanage.inve.persist.WmsInveTransaApprovalInfoDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.persist.WmsInveTransaMatchDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.inve.persist.WmsInveTransaProtocolDao;
import com.zx.emanage.inve.service.IWmsInveTransaApprovalInfoService;
import com.zx.emanage.inve.service.IWmsInveTransaProtocolService;
import com.zx.emanage.inve.service.IWmsInveTransaService;
import com.zx.emanage.inve.service.IWmsInveWorkFlowService;
import com.zx.emanage.inve.util.CountIncome;
import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.emanage.inve.vo.WmsInveTransaApprovalInfoSearchBeanVO;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.util.gen.entity.WmsInveCustomerCard;
import com.zx.emanage.util.gen.entity.WmsInveCustomerCardUpdateLog;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaApprovalInfo;
import com.zx.emanage.util.gen.entity.WmsInveTransaMatch;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.emanage.util.gen.entity.WmsInveTransaSalesmanHis;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.platform.syscontext.util.DateUtil;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvetransaapprovalinfoService")
public class WmsInveTransaApprovalInfoServiceImpl implements IWmsInveTransaApprovalInfoService {
	private static Logger log = LoggerFactory.getLogger(WmsInveTransaApprovalInfoServiceImpl.class);

	@Autowired
	private WmsInveTransaApprovalInfoDao wmsinvetransaapprovalinfoDao;
	
	@Autowired
	private IWmsInveWorkFlowService iWmsInveWorkFlowService;
	
	@Autowired
	private WmsInveTransaDao wmsInveTransaDao;
	
	@Autowired
	private WmsInveTransaProdDao wmsInveTransaProdDao;
	
	@Autowired
	private WmsInveTransaMatchDao wmsInveTransaMatchDao;
	
	@Autowired
	private WmsInveTransaProtocolDao wmsInveTransaProtocolDao;
	
	@Autowired
	private IWmsInveTransaProtocolService wmsInveTransaProtocolService;
	
	@Autowired
	private IWmsInveTransaService wmsInveTransaService;
	
	@Autowired
	private WmsInveCustomerDao wmsInveCustomerDao;
	
	@Autowired
	private WmsInveCustomerCardDao wmsInveCustomerCardDao;
	
	@Autowired
	private WmsInveCustomerCardUpdateLogDao wmsInveCustomerCardUpdateLogDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveTransaApprovalInfoSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvetransaapprovalinfoDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveTransaApprovalInfoSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvetransaapprovalinfoDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvetransaapprovalinfoDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveTransaApprovalInfo getInfoByPK(Integer wms_inve_transa_approval_info_id) {
		return wmsinvetransaapprovalinfoDao.get(wms_inve_transa_approval_info_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveTransaApprovalInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvetransaapprovalinfoDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveTransaApprovalInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvetransaapprovalinfoDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveTransaApprovalInfo() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveTransaApprovalInfo> getListByEntity(WmsInveTransaApprovalInfo queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveTransaApprovalInfo> beanList = wmsinvetransaapprovalinfoDao.getListByEntity(queryInfo);
		return beanList;
	}
	/**
	 * 集中处理：理财上单复核和上单审核  审批结果处理
	 * @author jiaodelong
	 * history:
	 * 1. 2017年7月10日17:14:51 donghao 修改方法,在方法参数中增加incomeCardParams收益卡的参数,在上单审核操作过程中可以修改客户的收益卡的银行和支行名称,审核通过的时候需要修改
	 *                                审核不通过则不修改
	 * 
	 */
	@Override
	@Transactional
	public String saveToSubmitNullify(WmsInveTransaApprovalInfo info,String taskId,String yjtype,UserBean user,String compe, String incomeCardParams) {
		
		if(yjtype.equals("fh")){//理财复核不同意
        	info.setReview_id(user.getUserId());
        	info.setReview_name(user.getRealname());
        	info.setReview_result(info.getReview_result());
        	info.setReview_time(new Timestamp(System.currentTimeMillis()));
        	  // ***********判断复核是否发生并发现象****************
            Integer transastate = wmsInveTransaDao.getFhConcurrent(info.getWms_inve_transa_id());
            if (transastate != 9)//9待复核
            {
                return  "exist";
            }
            // ******************************************
        	//调用流程(公用)
            WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
            wDebtWorkFlowVO.setTaskId(taskId);
            wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
            wDebtWorkFlowVO.setBusinessId(info.getWms_inve_transa_id().toString());//上单信息表id
            wDebtWorkFlowVO.setDebtkey("1");
            wDebtWorkFlowVO.setUserID(user.getUserId().toString());
            wDebtWorkFlowVO.setAdvice(info.getReview_advice());
            wDebtWorkFlowVO.setPass(info.getReview_result()==1?"true":"false");
            iWmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
        	
        }else if(yjtype.equals("zf")){//上单审核不同意
        	info.setSupervisor_confirmation_id(user.getUserId());
        	info.setSupervisor_confirmation_name(user.getRealname());
        	info.setSupervisor_confirmation_result(info.getReview_result());
        	info.setSupervisor_confirmation_time(new Timestamp(System.currentTimeMillis()));
            
            if(info.getSupervisor_confirmation_result()==0){//不通过
                // WmsInveTransa wmsInveTransa =new WmsInveTransa();
                // wmsInveTransa.setWms_inve_transa_id(info.getWms_inve_transa_id());
                // wmsInveTransa.setAccount_status("1");//1待支付 2已支付 3已到账 4部分到账
                // wmsInveTransaDao.update(wmsInveTransa);
                
               /* //审核不通过将支付信息设置成为失效状态
                wmsInveTransaService.wmsInveTransaInvalid(info.getWms_inve_transa_id(), user);*/
                
                //调用流程(公用)
                WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
                wDebtWorkFlowVO.setTaskId(taskId);
                wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
                wDebtWorkFlowVO.setBusinessId(info.getWms_inve_transa_id().toString());//上单信息表id
                wDebtWorkFlowVO.setDebtkey("3");
                wDebtWorkFlowVO.setUserID(user.getUserId().toString());
                wDebtWorkFlowVO.setAdvice(info.getSupervisor_confirmation_advice());
                wDebtWorkFlowVO.setPass("false");                	
                iWmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
                
                
               
            }else if(info.getSupervisor_confirmation_result()==1){//通过
            	 //节流：主管确认后 就直接算 理财单据的收益信息和日志信息 以及债权匹配信息和协议信息
            	WmsInveTransa wmsInveTransa = wmsInveTransaDao.get(info.getWms_inve_transa_id());
            	WmsInveTransaProd wmsTransaProd =wmsInveTransaProdDao.get(info.getWms_inve_transa_prod_id());
            	
            	Date end_of_date = null;
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
                		end_of_date = new Date(date.getTime());
                	}else{
                		end_of_date = setDatebyCalendar(wmsInveTransa.getDate_of_payment(), wmsTransaProd.getProduct_deadline());
                	}
        		} catch (ParseException e) {
        		}
            	
            	
            	Timestamp sysTime = new Timestamp(System.currentTimeMillis());
                //保存协议签订信息
               WmsInveTransaProtocol wInveTransaProtocol = new WmsInveTransaProtocol();
               wInveTransaProtocol.setWms_inve_transa_id(wmsTransaProd.getWms_inve_transa_id());//上单信息表主键
               wInveTransaProtocol.setWms_inve_transa_prod_id(wmsTransaProd.getWms_inve_transa_prod_id());//上单产品表主键
               wInveTransaProtocol.setProt_code(CodeNoUtil.getEnviProCode());//获取协议编号
               wInveTransaProtocol.setA_name("测试甲");//甲方姓名
               wInveTransaProtocol.setA_id_card("111111111111111111");//甲方身份证号
               wInveTransaProtocol.setProduct_account(wmsInveTransaProdDao.get(wmsTransaProd.getWms_inve_transa_prod_id()).getProduct_account());
               wInveTransaProtocol.setDate_of_payment(wmsInveTransa.getDate_of_payment());
               wInveTransaProtocol.setA_bank("111111111111111111");
               wInveTransaProtocol.setA_number("111111111111111111");
               wInveTransaProtocol.setB_name(wmsInveTransaDao.get(wmsTransaProd.getWms_inve_transa_id()).getCus_name());//客户姓名
               wInveTransaProtocol.setB_id_card(wmsInveTransaDao.get(wmsTransaProd.getWms_inve_transa_id()).getId_card());//客户身份证号
               wInveTransaProtocol.setCategory_name(wmsInveTransaProdDao.get(wmsTransaProd.getWms_inve_transa_prod_id()).getCategory_name());
               wInveTransaProtocol.setProduct_deadline(wmsInveTransaProdDao.get(wmsTransaProd.getWms_inve_transa_prod_id()).getProduct_deadline());
               wInveTransaProtocol.setSign_place("111111111111111111");
               wInveTransaProtocol.setB_data("111111111111111111");
               wInveTransaProtocol.setEnd_of_date(end_of_date);//结束日期
               wInveTransaProtocol.setCreate_user_id(user.getUserId());
               wInveTransaProtocol.setCreate_user_name(user.getRealname());
               wInveTransaProtocol.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
               wInveTransaProtocol.setLast_update_user_id(user.getUserId());
               wInveTransaProtocol.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
               wInveTransaProtocol.setEnable_flag("1");
               wInveTransaProtocol.setContact_address("111111111111111111");
               wInveTransaProtocol.setPost_code("111111");
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
               inveTransaMatch.setCreate_user_id(user.getUserId());
               inveTransaMatch.setCreate_timestamp(sysTime);
               inveTransaMatch.setLast_update_user_id(user.getUserId());
               inveTransaMatch.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
               inveTransaMatch.setEnable_flag("1");
               inveTransaMatch.setWms_inve_redeem_id(0);
               inveTransaMatch.setWms_inve_transa_protocol_id(wInveTransaProtocol.getWms_inve_transa_protocol_id());
               wmsInveTransaMatchDao.save(inveTransaMatch);
                try {
                	//计算收益和日志
                	wmsInveTransaProtocolService.SethandleIncomeAndLogInfo(wInveTransaProtocol, user);
        		} catch (Exception e) {
        			return "error";
        		}
                
                /******************************上单审核通过,修改了客户收益卡需要更新收益卡操作....开始**********************************/
                //判断收益卡json字符串是否为空
                if(incomeCardParams != null && !"".equals(incomeCardParams))
                {
                    //将json字符串转换成map集合
                    Map<String, Object> incomeCardMap = new Gson().fromJson(incomeCardParams, HashMap.class);
                    
                    //判断map集合是否为空
                    if(incomeCardMap != null && incomeCardMap.size() > 0)
                    {
                        //从集合中取出收益卡表主键
                        Integer wms_inve_customer_card_id = Double.valueOf(incomeCardMap.get("wms_inve_customer_card_id").toString()).intValue();
                        
                        //从map集合中取出银行名称
                        String bank_of_deposit = (String)incomeCardMap.get("bank_of_deposit");
                        
                        //从map集合中取出支行名称
                        String bank_branch = (String)incomeCardMap.get("bank_branch");
                        
                        //根据收益卡表主键获取对应的收益卡信息
                        WmsInveCustomerCard wmsInveCustomerCard = wmsInveCustomerCardDao.get(wms_inve_customer_card_id);
                        
                        //判断只要银行和支行名称任意一个与上单产品表中的对应不上都需要进行更新进行修改
                        if(!bank_of_deposit.equals(wmsTransaProd.getBank_of_deposit()) || !bank_branch.equals(wmsTransaProd.getBank_branch())
                                                                                       || !bank_of_deposit.equals(wmsInveCustomerCard.getBank_of_deposit())
                                                                                       || !bank_branch.equals(wmsInveCustomerCard.getBank_branch()))
                        {
                            //创建更新客户收益卡信息对象
                            WmsInveCustomerCardUpdateLog customerCardUpdateLog = new WmsInveCustomerCardUpdateLog();
                            //设置原始的银行名称
                            customerCardUpdateLog.setOriginal_bank_of_deposit(wmsTransaProd.getBank_of_deposit());
                            //设置原始的支行名称
                            customerCardUpdateLog.setOriginal_bank_branch(wmsTransaProd.getBank_branch());
                            //设置新的银行名称
                            customerCardUpdateLog.setNew_bank_of_deposit(bank_of_deposit);
                            //设置新的支行名称
                            customerCardUpdateLog.setNew_bank_branch(bank_branch);
                            //设置创建人(也就是修改人)
                            customerCardUpdateLog.setCreate_user_id(user.getUserId());
                            //设置创建时间(也就是修改时间)
                            customerCardUpdateLog.setCreate_date_time(new Timestamp(System.currentTimeMillis()));
                            //设置对应的客户收益卡表主键
                            customerCardUpdateLog.setWms_inve_customer_card_id(wms_inve_customer_card_id);
                            //设置客户的上单单据表主键
                            customerCardUpdateLog.setWms_inve_transa_id(info.getWms_inve_transa_id());
                            //设置客户上单产品表主键
                            customerCardUpdateLog.setWms_inve_transa_prod_id(info.getWms_inve_transa_prod_id());
                            
                            //保存修改的收益卡信息记录
                            wmsInveCustomerCardUpdateLogDao.saveCustomerCardUpdateLog(customerCardUpdateLog);
                            
                            
                            //更新上单产品表中修改的银行和支行名称
                            wmsTransaProd.setBank_of_deposit(bank_of_deposit);
                            wmsTransaProd.setBank_branch(bank_branch);
                            wmsInveTransaProdDao.update(wmsTransaProd);
                            
                            
                            //更新客户收益卡表的银行和支行名称
                            wmsInveCustomerCard.setBank_of_deposit(bank_of_deposit);
                            wmsInveCustomerCard.setBank_branch(bank_branch);
                            wmsInveCustomerCard.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
                            wmsInveCustomerCard.setLast_update_user_id(user.getUserId());
                            wmsInveCustomerCardDao.update(wmsInveCustomerCard);
                        }
                        
                    }
                    
                }
                
                
                
                /******************************上单审核通过,修改了客户收益卡需要更新收益卡操作....结束**********************************/
                
                
                //调用流程(公用)
                WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
                wDebtWorkFlowVO.setTaskId(taskId);
                wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
                wDebtWorkFlowVO.setBusinessId(info.getWms_inve_transa_id().toString());//上单信息表id
                wDebtWorkFlowVO.setDebtkey("5");
                wDebtWorkFlowVO.setUserID(user.getUserId().toString());
                wDebtWorkFlowVO.setAdvice(info.getSupervisor_confirmation_advice());
                // if(compe.equals("compe")){
                // wDebtWorkFlowVO.setPass("compe");
                // }else{
                	wDebtWorkFlowVO.setPass("true");                	
                // }
                iWmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
                
                

              //添加业务人员的历史信息
              //WmsInveTransa wmsInveTransa = wmsInveTransaDao.get(info.getWms_inve_transa_id());
                if(wmsInveTransa != null){
                	WmsInveTransaSalesmanHis inveTransaSalesmanHis = new WmsInveTransaSalesmanHis();
                	
                	inveTransaSalesmanHis.setWms_inve_transa_id(wmsInveTransa.getWms_inve_transa_id());
                	
                	inveTransaSalesmanHis.setSalesman_id(wmsInveTransa.getSalesman_id());
                	inveTransaSalesmanHis.setSalesman_dept_id(wmsInveTransa.getSalesman_dept_id());
                	
                	inveTransaSalesmanHis.setDepartment_manager_dept_id(wmsInveTransa.getDepartment_manager_dept_id());
                	inveTransaSalesmanHis.setDepartment_manager_id(wmsInveTransa.getDepartment_manager_id());
                	
                	inveTransaSalesmanHis.setVice_general_manager_dept_id(wmsInveTransa.getVice_general_manager_dept_id());
                	inveTransaSalesmanHis.setVice_general_manager_id(wmsInveTransa.getVice_general_manager_id());
                	
                	inveTransaSalesmanHis.setGeneral_manager_dept_id(wmsInveTransa.getGeneral_manager_dept_id());
                	inveTransaSalesmanHis.setGeneral_manager_id(wmsInveTransa.getGeneral_manager_id());
                	
                	inveTransaSalesmanHis.setCenter_manager_dept_id(wmsInveTransa.getCenter_manager_dept_id());
                	inveTransaSalesmanHis.setCenter_manager_id(wmsInveTransa.getCenter_manager_id());
                	
                	inveTransaSalesmanHis.setCreate_user_id(user.getUserId());
                	inveTransaSalesmanHis.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
                	
                	inveTransaSalesmanHis.setRemark("");
                	wmsInveTransaDao.saveWmsInveTransaSalesmanHis(inveTransaSalesmanHis);
                }
                
                //根据上单表主键获取上单信息
                WmsInveTransa transa = wmsInveTransaDao.get(info.getWms_inve_transa_id());
                
                //上单审核通过之后需要更新wms_inve_customer客户信息表中的costomer_id
                int index = wmsInveCustomerDao.updateCostomerIdByWmsInveTransaId(transa);
                
                if(index > 0)
                {
                    Map<String, Object> customerMap = wmsInveTransaDao.findCustomerInfoById(wmsInveTransa.getWms_inve_customer_id());
                    // 同步客户信息到crm
                    synchronizeCustomerInfoToCrm(customerMap);
                }
            }
        }
		
		

		//查询此人是否有退回记录
		WmsInveTransaApprovalInfo wmsInveTransaApprovalInfo = new WmsInveTransaApprovalInfo();
		wmsInveTransaApprovalInfo.setWms_inve_transa_id(info.getWms_inve_transa_id());
 		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("wms_inve_transa_id", info.getWms_inve_transa_id());
        List<WmsInveTransaApprovalInfo> beanList = wmsinvetransaapprovalinfoDao.getListByEntity(wmsInveTransaApprovalInfo);
        Integer resStr = 0;
        if(beanList.size() > 0){
        	resStr = wmsinvetransaapprovalinfoDao.updateAdvice(info);
        }else{
        	resStr = wmsinvetransaapprovalinfoDao.save(info);
        }
		if(resStr > 0){
			return "success";
		}else{
			return "error";
		}
	}
	
	
/**
 * 保存作废原因
 */
	@Override
	public String saveToCancel(UserBean user,WmsInveTransaApprovalInfo info,WmsInveDebtWorkFlowVO wDebtWorkFlowVO) {
		Integer resStr = 0;
		
		info.setSupervisor_confirmation_void_id(user.getUserId());
    	info.setSupervisor_confirmation_void_name(user.getRealname());
    	info.setSupervisor_confirmation_void_time(new Timestamp(System.currentTimeMillis()));
		resStr = wmsinvetransaapprovalinfoDao.save(info);
		
	      //调用流程(公用)
        wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
        wDebtWorkFlowVO.setBusinessId(info.getWms_inve_transa_id().toString());//上单信息表id
        wDebtWorkFlowVO.setDebtkey("6");
        wDebtWorkFlowVO.setUserID(info.getSupervisor_confirmation_id().toString());
        wDebtWorkFlowVO.setPass("nullify");
        wDebtWorkFlowVO.setAdvice(info.getSupervisor_confirmation_void_advice());
        iWmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
		if(resStr == 1){
			return "success";
		}else{
			return "error";
		}
	}
	/**
	 * 草稿状态理财单据作废
	 * @param user
	 * @param info
	 * @author jiaodelong
	 * @date 2015年12月21 20:54
	 */
	@Override
	public String saveToCancelCG(UserBean user,WmsInveTransaApprovalInfo info) {
		int resStr = 0;
		//保存作废原因
		info.setSupervisor_confirmation_void_id(user.getUserId());
    	info.setSupervisor_confirmation_void_name(user.getRealname());
    	info.setSupervisor_confirmation_void_time(new Timestamp(System.currentTimeMillis()));
		resStr = wmsinvetransaapprovalinfoDao.save(info);
		
		//修改上单信息表状态
		WmsInveTransa wmsInveTransa=new WmsInveTransa();
		wmsInveTransa.setWms_inve_transa_id(info.getWms_inve_transa_id());
        wmsInveTransa.setIs_order_redeem("0");// 是否预约赎回为否
		wmsInveTransa.setData_status("7");//已终止
		wmsInveTransaDao.update(wmsInveTransa);
		
		if(resStr == 1){
			return "success";
		}else{
			return "error";
		}
	}
	
	
	/**
	 * 获取当前日期
	 * @return
	 */
    private Date getNow()
    {
        Calendar nowcalendar = Calendar.getInstance();
        return new Date(nowcalendar.getTimeInMillis());
    }
    
    /**
     * 设置日期 参数int i是为了方法的公共使用 主要的目的就是计算的月份上有差异对月份进行加减
     * 
     * @param wmsFinaCreLoanApp
     * @return
     */
    private Date setDatebyCalendar(Date sDate, int i)
    {  
    	java.sql.Date  date1;
	    Calendar  calendar=new GregorianCalendar();   
	    calendar.setTime(sDate);
	    calendar.add(Calendar.MONTH, +i);
	    java.util.Date date_=calendar.getTime();
	    date1=new java.sql.Date(date_.getTime()); 
	    return date1;
	}
    
    
    /**
     * @Deprecated 根据上单表中的客户信息主键获取对应的客户信息然后将客户信息同步到crm系统
     * @param wms_inve_customer_id
     * @author donghao
     * @date 2016年9月2日10:21:04
     */
    private void synchronizeCustomerInfoToCrm(final Map<String, Object> customerMap)
    {
        try
        {
            Thread thread = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    // 调用crm接口
                    List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                    nvps.add(new BasicNameValuePair("interface_num", "CRM_OUT_002"));
                    nvps.add(new BasicNameValuePair("sys_num", "WMS"));
                    nvps.add(new BasicNameValuePair("user_id", "0"));

                    nvps.add(new BasicNameValuePair("costomer_id", "" + customerMap.get("costomer_id")));
                    nvps.add(new BasicNameValuePair("customer_name", "" + customerMap.get("cus_name")));
                    nvps.add(new BasicNameValuePair("contact_number", "" + customerMap.get("mobile_phone")));
                    nvps.add(new BasicNameValuePair("id_card_number", "" + customerMap.get("id_card")));
                    nvps.add(new BasicNameValuePair("email_adress", "" + customerMap.get("customer_email")));
                    nvps.add(new BasicNameValuePair("domicile_place", "" + customerMap.get("contact_address")));

                    try
                    {
                        HttpPost httpPost = new HttpPost(GlobalVal.EKP_LOGIN_URL + "/modi/restful/simple");
                        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
                        CloseableHttpClient httpclient = HttpClients.createDefault();
                        CloseableHttpResponse response = httpclient.execute(httpPost);
                        response.close();
                        httpclient.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}


