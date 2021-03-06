package com.zx.emanage.loanappro.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.typeconverter.Convert;
import jodd.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zx.emanage.cremanage.persist.WmsCreContractLenderDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineHouseinfoDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingApprovalInfoDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingCustomerHouseDao;
import com.zx.emanage.cremanage.service.IWmsCarLoanWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.cremanage.service.IWmsCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsHouseCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanappro.persist.WmsCreApproBorrowProtocolDao;
import com.zx.emanage.loanappro.persist.WmsCreApproComDebtorDao;
import com.zx.emanage.loanappro.persist.WmsCreApproProtocolAttachDao;
import com.zx.emanage.loanappro.persist.WmsCreApproProtocolSecuredDao;
import com.zx.emanage.loanappro.persist.WmsCreApproServiceProtocolDao;
import com.zx.emanage.loanappro.persist.WmsCreCreditServiceTypeDao;
import com.zx.emanage.loanappro.persist.WmsCrePersonnelInfoDao;
import com.zx.emanage.loanappro.persist.WmsSysPropertyDao;
import com.zx.emanage.loanappro.service.IWmsCreApproBorrowProtocolService;
import com.zx.emanage.loanappro.vo.WmsCreApproBorrowProtocolSearchBeanVO;
import com.zx.emanage.loanappro.vo.WmsCreApproBorrowProtocolSearchConditionBeanVO;
import com.zx.emanage.loanfina.persist.WmsFinaCreLoanAppDao;
import com.zx.emanage.remind.persist.WmsCreCreditNotaryWarnDao;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.sysmanage.util.UserCityUtil;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.WmsHelp;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsCreApproBorrowProtocol;
import com.zx.emanage.util.gen.entity.WmsCreApproComDebtor;
import com.zx.emanage.util.gen.entity.WmsCreApproProtocolAttach;
import com.zx.emanage.util.gen.entity.WmsCreApproProtocolSecured;
import com.zx.emanage.util.gen.entity.WmsCreApproServiceProtocol;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead;
import com.zx.emanage.util.gen.entity.WmsCreCreditNotaryWarn;
import com.zx.emanage.util.gen.entity.WmsCreCreditServiceType;
import com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineHouseinfo;
import com.zx.emanage.util.gen.entity.WmsCreHousingApprovalInfo;
import com.zx.emanage.util.gen.entity.WmsCreHousingCustomerHouse;
import com.zx.emanage.util.gen.entity.WmsCrePersonnelInfo;
import com.zx.emanage.util.gen.entity.WmsFinaCreLoanApp;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.ExpertUtil;
import com.zx.sframe.util.JasperUtil;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreApproBorrowProtocolServiceImpl
 * 模块名称：合同
 * @Description: 内容摘要：合同
 * @author baisong
 * @date 2016年12月29日
 * @version V1.0
 * history:
 * 1、2016年12月29日 baisong 创建文件
 */
@Service("wmscreapproborrowprotocolService")
public class WmsCreApproBorrowProtocolServiceImpl implements IWmsCreApproBorrowProtocolService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreApproBorrowProtocolServiceImpl.class);

    @Autowired
    private WmsCreApproBorrowProtocolDao wmscreapproborrowprotocolDao;

    @Autowired
    private WmsCreCreditHeadDao wmsCreCreditHeadDao;

    @Autowired
    private WmsCreApproServiceProtocolDao wmsCreApproServiceProtocolDao;

    @Autowired
    private WmsCrePersonnelInfoDao wmsCrePersonnelInfoDao;

    @Autowired
    private IWmsHouseCreditWorkFlowService houseCreditWorkFlowService;

    @Autowired
    private WmsCrePersonnelInfoDao wmscrepersonnelinfoDao;

    @Autowired
    private WmsCreApproServiceProtocolDao wmscreapproserviceprotocolDao;// 居间服务协议合同

    @Autowired
    private WmsCreApproProtocolAttachDao wmscreapproprotocolattachDao;// 合同-附件清单

    @Autowired
    private WmsCreApproComDebtorDao wmsCreApproComDebtorDao;// 共同债务人信息

    @Autowired
    private IWmsCreditWorkFlowService creditWorkFlowService;// 信贷流程service

    @Autowired
    private WmsFinaCreLoanAppDao wmsFinaCreLoanAppDao;
    
    @Autowired
    private IWmsCarLoanWorkFlowService carLoanWorkFlowService;//流程
    @Autowired
    private WmsCreApproProtocolSecuredDao wmsCreApproProtocolSecuredDao;//个人担保合同
	@Autowired
	private IWmsLoanWorkFlowService   wmsLoanWorkFlowService;//新房贷贷款流程
	
	@Autowired
    private WmsCreContractLenderDao wmsCreContractLenderDao;//债权人查询
	
	@Autowired
    private WmsCreCreditLineCustomerChangeHeadDao wmsCreCreditLineCustomerChangeHeadDao;
	
	@Autowired
	private PmPersonnelDao pmPersonnelDao;
	
	@Autowired
    private WmsCreCreditServiceTypeDao wmsCreCreditServiceTypeDao;

    @Autowired
    private WmsCreCreditNotaryWarnDao wmsCreCreditNotaryWarnDao;// 公正提醒表

    @Autowired
    private WmsCreCustomerChangeLineHouseinfoDao wmsCreCustomerChangeLineHouseinfoDao;// 房产表

    @Autowired
    private WmsCreHousingCustomerHouseDao wmsCreHousingCustomerHouseDao;// 房贷——抵押房产信息
	
	@Autowired
    private IWmsCreCreditHeadService wmsCreCreditHeadService;

    @Autowired
    private WmsCreHousingApprovalInfoDao wmsCreHousingApprovalInfoDao;// 审批表

    @Autowired
    private WmsSysPropertyDao wmssyspropertyDao;// 字典表
	
    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreApproBorrowProtocolSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscreapproborrowprotocolDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreApproBorrowProtocolSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscreapproborrowprotocolDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscreapproborrowprotocolDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreApproBorrowProtocol getInfoByPK(Integer wms_cre_appro_id)
    {
        return wmscreapproborrowprotocolDao.get(wms_cre_appro_id);
    }

    /**
     * 借款合同信息保存//已经废弃了
     * 
     * @param
     * @return
     * @author 张风山
     */
    @Override
    @Transactional
    public String save(WmsCreApproBorrowProtocol bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        String str = bean.getProtocol_id_year() + "年第" + bean.getProtocol_id_num() + "号";
        bean.setProtocol_id_year_num(str);
        bean.setCreate_user_id(user.getUserId());
        bean.setCreate_user_name(user.getRealname());
        bean.setEnable_flag("1");
        bean.setCreate_timestamp(new Timestamp(new Date().getTime()));
        bean.setLast_update_timestamp(new Timestamp(new Date().getTime()));

        // 更新贷款主表的"合同金额"字段
        Map<String, Object> mccs = new HashMap<String, Object>();
        mccs.put("protocol_amount", bean.getPrincipal_lower());
        mccs.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
        if (bean.getPrincipal_lower() != null)
        {
            wmsCreCreditHeadDao.updateRecord(mccs);
        }
        // 保存借款合同信息
        ret = wmscreapproborrowprotocolDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * 借款合同信息保存//新
     * 
     * @param
     * @return
     * @author baisong
     */
    @Override
    @Transactional
    public String saveBorrow(WmsCreApproBorrowProtocol bean, UserBean user,
                             WmsCreApproServiceProtocol wmsCreApproServiceProtocol,
                             WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        String resStr = "success";
        int ret = 0;
        String str = bean.getProtocol_id_year() + "年第" + bean.getProtocol_id_num() + "号";
        bean.setProtocol_id_year_num(str);
        bean.setCreate_user_id(user.getUserId());
        bean.setCreate_user_name(user.getRealname());
        bean.setEnable_flag("1");
        bean.setCreate_timestamp(new Timestamp(new Date().getTime()));
        bean.setLast_update_timestamp(new Timestamp(new Date().getTime()));
        // 更具该单据wms_cre_credit_head_id 查询出该单据还款类型
        WmsCreCreditHead wCreCreditHead = wmsCreCreditHeadDao.get(bean.getWms_cre_credit_head_id());
        if (wCreCreditHead.getCre_loan_type() == 110 || wCreCreditHead.getCre_loan_type() == 111
            || wCreCreditHead.getCre_loan_type() == 112 || wCreCreditHead.getCre_loan_type() == 113
            || wCreCreditHead.getCre_loan_type() == 256 || wCreCreditHead.getCre_loan_type() == 283)
        {
            // 等额本息
            bean.setPayment_contract_type("1");
        }
        else if (wCreCreditHead.getCre_loan_type() == 284 || wCreCreditHead.getCre_loan_type() == 116
                 || wCreCreditHead.getCre_loan_type() == 117)
        {
            // 先息后本
            bean.setPayment_contract_type("2");
        }
        // 更新贷款主表的"合同金额"字段
        Map<String, Object> mccs = new HashMap<String, Object>();
        mccs.put("protocol_amount", bean.getPrincipal_lower());
        mccs.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
        if (bean.getPrincipal_lower() != null)
        {
            wmsCreCreditHeadDao.updateRecord(mccs);
        }
        WmsCreCreditHead wmsCreCreditHead = wmsCreCreditHeadDao.get(bean.getWms_cre_credit_head_id());
        // 合同利率为合同表中的利率加上居间服务协议中的每月应支付的利率
        BigDecimal borrow_interest = (bean.getBorrow_interest().add(wmsCreApproServiceProtocol.getService_cost_month())).divide(new BigDecimal(
                                                                                                                                               100),
                                                                                                                                8,
                                                                                                                                BigDecimal.ROUND_UNNECESSARY);
        // (1-(月利率+居间服务利率)*期数),进位取整
        // BigDecimal suvs = new
        // BigDecimal(1).subtract(borrow_interest.multiply(new
        // BigDecimal(bean.getBorrow_deadline())));
        // 其中本金 = 面签确认金额=合同金额*(1-(月利率+居间服务利率)*期数),进位取整
        // BigDecimal cipal =
        // bean.getPrincipal_lower().multiply(suvs).divide(new BigDecimal(1), 0,
        // BigDecimal.ROUND_DOWN);
        BigDecimal cipal = wmsCreCreditHead.getVisa_limit().multiply(new BigDecimal(10000));// 面签金额==其中本金
        // 其中本金
        bean.setPrincipal(cipal);
        // 其中利息= 合同金额-其中本金
        BigDecimal interest = bean.getPrincipal_lower().subtract(cipal);
        if("2".equals(bean.getPayment_contract_type())){
        	 interest = bean.getRefund_limit_month().multiply(new BigDecimal(bean.getBorrow_deadline()));	
		}
        bean.setInterest(interest);
		
        // 月还款额 = 合同金额/期数
        BigDecimal refund_limit_month = bean.getPrincipal_lower().divide(new BigDecimal(bean.getBorrow_deadline()), 0,
                                                                         BigDecimal.ROUND_UP);
        // 月还款本金 = 其中本金/期数（进位取整）
        BigDecimal cipal_limit_month = cipal.divide(new BigDecimal(bean.getBorrow_deadline()), 0, BigDecimal.ROUND_UP);
        bean.setOrg_repay_principal(cipal_limit_month);
        // 月利息 = 月还款额 - 月还款本金
        bean.setOrg_repay_interest((refund_limit_month.subtract(cipal_limit_month)));
        // 保存借款合同信息
        ret = wmscreapproborrowprotocolDao.save(bean);

        wmsCreApproServiceProtocol.setCreate_user_id(user.getUserId());
        wmsCreApproServiceProtocol.setCreate_user_name(user.getRealname());
        wmsCreApproServiceProtocol.setEnable_flag("1");
        wmsCreApproServiceProtocol.setCreate_timestamp(new Timestamp(new Date().getTime()));
        wmsCreApproServiceProtocol.setLast_update_timestamp(new Timestamp(new Date().getTime()));
        ret = wmscreapproserviceprotocolDao.save(wmsCreApproServiceProtocol);// 保存居间服务协议表
        // 新流程执行该语句
        if (!approveWorkFlowVO.getTaskId().equals("") && approveWorkFlowVO.getTaskId() != null)
        {
            approveWorkFlowVO.setUser_id(user.getUserId());// 保存userid///
            creditWorkFlowService.creCheckConcludeAndSignApprove(approveWorkFlowVO);// 保存工作流内容
        }
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }
    /**
     * 借款合同信息保存--车贷
     * 
     * @param
     * @return
     * @author baisong
     */
    @Override
    @Transactional
    public String saveBorrowCar(WmsCreApproBorrowProtocol bean, UserBean user,
                             WmsCreApproServiceProtocol wmsCreApproServiceProtocol,
                             WmsCarLoanWorkFlowVO wVo)
    {
        String resStr = "success";
        int ret = 0;
        String str = bean.getProtocol_id_year() + "年第" + bean.getProtocol_id_num() + "号";
        bean.setProtocol_id_year_num(str);
        bean.setCreate_user_id(user.getUserId());
        bean.setCreate_user_name(user.getRealname());
        bean.setEnable_flag("1");
        bean.setCreate_timestamp(new Timestamp(new Date().getTime()));
        bean.setLast_update_timestamp(new Timestamp(new Date().getTime()));
        // 更具该单据wms_cre_credit_head_id 查询出该单据还款类型
        //WmsCreCreditHead wCreCreditHead = wmsCreCreditHeadDao.get(bean.getWms_cre_credit_head_id());
        if ("31".equals(bean.getProtocol_type()))//合同号为 c1的是等额本息合同不压车
        {
            // 等额本息
            bean.setPayment_contract_type("1");
        }
        else if ("32".equals(bean.getProtocol_type()) || "33".equals(bean.getProtocol_type()) )//先洗后本押车不押车
        {
            // 先息后本
            bean.setPayment_contract_type("2");
        }
        // 更新贷款主表的"合同金额"字段
        Map<String, Object> mccs = new HashMap<String, Object>();
        mccs.put("protocol_amount", bean.getPrincipal_lower());
        mccs.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
        if (bean.getPrincipal_lower() != null)
        {
            wmsCreCreditHeadDao.updateRecord(mccs);
        }
        WmsCreCreditHead wmsCreCreditHead = wmsCreCreditHeadDao.get(bean.getWms_cre_credit_head_id());
        // 合同利率为合同表中的利率加上居间服务协议中的每月应支付的利率
        BigDecimal borrow_interest = (bean.getBorrow_interest().add(wmsCreApproServiceProtocol.getService_cost_month())).divide(new BigDecimal(100),8,BigDecimal.ROUND_UNNECESSARY);
 
        BigDecimal cipal = wmsCreCreditHead.getAppro_limit();// 面签金额==其中本金.multiply(new BigDecimal(10000))
        // 其中本金
        bean.setPrincipal(cipal);
        // 其中利息= 合同金额-其中本金
        BigDecimal interest = bean.getPrincipal_lower().subtract(cipal);
        if("2".equals(bean.getPayment_contract_type())){// 先息后本
        	 interest = bean.getRefund_limit_month().multiply(new BigDecimal(bean.getBorrow_deadline()));	
		}
        bean.setInterest(interest);
		
        // 月还款额 = 合同金额/期数
        BigDecimal refund_limit_month = bean.getPrincipal_lower().divide(new BigDecimal(bean.getBorrow_deadline()), 0,BigDecimal.ROUND_UP);
        // 月还款本金 = 其中本金/期数（进位取整）
        BigDecimal cipal_limit_month = cipal.divide(new BigDecimal(bean.getBorrow_deadline()), 0, BigDecimal.ROUND_UP);
        if( "2".equals(bean.getPayment_contract_type())){ // 先息后本
        	//月还款金额 该数值 = 借款合同内的借款本金 * （借款月利率+居间服务利率），四舍五入取整。borrow_interest
           refund_limit_month = bean.getRefund_limit_month();//获取合同页面上的月还款额
    	   cipal_limit_month  = new BigDecimal(0);
        }
        bean.setOrg_repay_principal(cipal_limit_month);
        // 月利息 = 月还款额 - 月还款本金
        bean.setOrg_repay_interest((refund_limit_month.subtract(cipal_limit_month)));
        // 保存借款合同信息
        ret = wmscreapproborrowprotocolDao.save(bean);

        wmsCreApproServiceProtocol.setCreate_user_id(user.getUserId());
        wmsCreApproServiceProtocol.setCreate_user_name(user.getRealname());
        wmsCreApproServiceProtocol.setEnable_flag("1");
        wmsCreApproServiceProtocol.setCreate_timestamp(new Timestamp(new Date().getTime()));
        wmsCreApproServiceProtocol.setLast_update_timestamp(new Timestamp(new Date().getTime()));
        ret = wmscreapproserviceprotocolDao.save(wmsCreApproServiceProtocol);// 保存居间服务协议表
        // 新流程执行该语句
        /*if (!wVo.getTaskId().equals("") && wVo.getTaskId() != null)
        {
        	wVo.setUserId(user.getUserId().toString());// 保存userid///
        	//carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
            carLoanWorkFlowService.carLoanApprovalProcess(wVo,"7");// 保存工作流内容
        }*/
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * 借款合同信息更新
     * 
     * @param
     * @return
     * @author 张风山
     */
    @Override
    @Transactional
    public String update(WmsCreApproBorrowProtocol bean, UserBean user,
                         WmsCreApproServiceProtocol wmsCreApproServiceProtocol, WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        String resStr = "success";
        int ret = 0;

        // 更新贷款主表的"合同金额"字段
        Map<String, Object> mccs = new HashMap<String, Object>();
        mccs.put("protocol_amount", bean.getPrincipal_lower());
        mccs.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
        if (bean.getPrincipal_lower() != null)
        {
            wmsCreCreditHeadDao.updateRecord(mccs);
        }
        // 更新合同信息
        ret = wmscreapproborrowprotocolDao.update(bean);

        ret = wmscreapproserviceprotocolDao.update(wmsCreApproServiceProtocol);
        approveWorkFlowVO.setUser_id(user.getUserId());// 保存userid///
        approveWorkFlowVO.setPass("true");
        if (ret == 0)
        {
            approveWorkFlowVO.setPass("false");
            resStr = "error";
        }
        creditWorkFlowService.creCheckConcludeAndSignApprove(approveWorkFlowVO);// 保存工作流内容
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreApproBorrowProtocol() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreApproBorrowProtocol> getListByEntity(WmsCreApproBorrowProtocol queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreApproBorrowProtocol> beanList = wmscreapproborrowprotocolDao.getListByEntity(queryInfo);
        return beanList;
    }

    /**
     * 根据贷款id查询对应的借款合同的信息
     * 
     * @param
     * @return Map
     * @author 张风山
     */
    @Override
    public Map<String, Object> selectAllByWmsCreCreditHeadId(String wms_cre_credit_head_id, UserBean user)
    {
        WmsCreApproBorrowProtocol queryInfo = new WmsCreApproBorrowProtocol();
        queryInfo.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
        List<WmsCreApproBorrowProtocol> beanList = wmscreapproborrowprotocolDao.getListByEntity(queryInfo);

        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("jkht", beanList);
        return resMap;
    }

    /**
     * 根据合同编号查询对应的借款合同的信息
     * 
     * @param
     * @return Map
     * @author 张风山
     */
    @Override
    public Map<String, Object> selectAllByProtocolId(String judgeString, UserBean user)
    {
        WmsCreApproBorrowProtocol queryInfo = new WmsCreApproBorrowProtocol();
        Map<String, Object> resMap = new HashMap<String, Object>();
        String[] protocolIdStr = judgeString.split(",");
        String result = "have";
        if (protocolIdStr.length == 0)
        {
            resMap.put("jkhtpd", result);
            return resMap;
        }
        if(protocolIdStr.length == 1){
        	queryInfo.setProtocol_id_num(protocolIdStr[0]);
        }else{
        	queryInfo.setProtocol_id_year(protocolIdStr[0]);
            queryInfo.setProtocol_id_num(protocolIdStr[1]);
        }
        
        List<WmsCreApproBorrowProtocol> beanList = wmscreapproborrowprotocolDao.getListByEntity(queryInfo);

        if (beanList.size() == 0)
        {
            result = "nohave";
        }
        resMap.put("jkhtpd", result);
        return resMap;
    }

    /**
     * 借款合同信息保存--房产终审 --签合同
     * 
     * @param
     * @return
     * @author baisong
     */
    @Override
    @Transactional
    public String saveforhouse(WmsCreApproBorrowProtocol bean, UserBean user, WmsCrePersonnelInfo person,
                               WmsCreApproServiceProtocol service, String person_name1, String person_identity_id1,
                               String person_name2, String person_identity_id2,
                               WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, String tablevalue, String comnumval, 
                               WmsCreApproProtocolSecured wmsCreApproProtocolSecured)
    {
        String resStr = "success";
        int ret = 0;
        bean.setCreate_user_id(user.getUserId());
        bean.setCreate_user_name(user.getRealname());
        bean.setEnable_flag("1");
        bean.setCreate_timestamp(new Timestamp(new Date().getTime()));
        bean.setLast_update_timestamp(new Timestamp(new Date().getTime()));
        // 更具该单据wms_cre_credit_head_id 查询出该单据还款类型
        WmsCreCreditHead wCreCreditHead = wmsCreCreditHeadDao.get(bean.getWms_cre_credit_head_id());
        if (wCreCreditHead.getCre_loan_type() == 110 || wCreCreditHead.getCre_loan_type() == 111
            || wCreCreditHead.getCre_loan_type() == 112 || wCreCreditHead.getCre_loan_type() == 113
            || wCreCreditHead.getCre_loan_type() == 256 || wCreCreditHead.getCre_loan_type() == 283
            || wCreCreditHead.getCre_loan_type() == 320 || wCreCreditHead.getCre_loan_type() == 786)//房贷四号--等额本息 786 新房贷2号
        {
            // 等额本息
            bean.setPayment_contract_type("1");
        }
        else if (wCreCreditHead.getCre_loan_type() == 284 || wCreCreditHead.getCre_loan_type() == 116
                 || wCreCreditHead.getCre_loan_type() == 117|| wCreCreditHead.getCre_loan_type() == 319
                 || wCreCreditHead.getCre_loan_type() == 785)//房贷三号--先洗后本 785	新房贷1号
        {
            // 先息后本
            bean.setPayment_contract_type("2");
        }
        // 更新贷款主表的"合同金额"字段
        Map<String, Object> mccs = new HashMap<String, Object>();
        mccs.put("protocol_amount", bean.getPrincipal_lower());
        mccs.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
        if (bean.getPrincipal_lower() != null)
        {
            wmsCreCreditHeadDao.updateRecord(mccs);
        }
        bean.setPrincipal(bean.getPrincipal_lower());// 其中本金=借款本金
        bean.setInterest(new BigDecimal(0));// 其中利息为0
        // 合同利率为合同表中的利率加上居间服务协议中的每月应支付的利率
        BigDecimal borrow_interest = (bean.getBorrow_interest().add(service.getService_cost_month())).divide(new BigDecimal(
                                                                                                                     100));
       /* if (bean.getProtocol_type().equals("3") || bean.getProtocol_type().equals("4")
        	|| bean.getProtocol_type().equals("11")|| bean.getProtocol_type().equals("12"))*/
        if ("2".equals(bean.getPayment_contract_type()))//先息后本   
        {    
        	
        	if(bean.getProtocol_type().equals("11") || bean.getProtocol_type().equals("12")//房贷三号
        		|| bean.getProtocol_type().equals("21")|| bean.getProtocol_type().equals("22")
        		|| bean.getProtocol_type().equals("25"))//新房一号
        	{
        		bean.setLoan_amount(bean.getPrincipal_lower());//转账金额等于本金（为了后面方便）
        	}
            // 月还款额 = 合同金额*2%(利率)
            BigDecimal refund_limit_month = bean.getPrincipal_lower().multiply(borrow_interest);
            // bean.setRefund_limit_month(refund_limit_month);//ROUND_UNNECESSARY为取绝对精度
            // 断言请求的操作具有精确的结果，因此不需要舍入
            // 月还款本金 = 前n-1期：0 最后一期：合同金额
            BigDecimal cipal_limit_month = new BigDecimal(0);
            // 月还款本金本金
            bean.setOrg_repay_principal((cipal_limit_month));
            // 月利息 = 月还款额 - 月还款本金
            bean.setOrg_repay_interest((refund_limit_month.subtract(cipal_limit_month).divide(new BigDecimal(1), 0,
                                                                                              BigDecimal.ROUND_HALF_UP)));
        }
        /*else if (bean.getProtocol_type().equals("1") || bean.getProtocol_type().equals("2")
                 || bean.getProtocol_type().equals("6") || bean.getProtocol_type().equals("7")
                 || bean.getProtocol_type().equals("9") || bean.getProtocol_type().equals("10"))*/
        else  if ("1".equals(bean.getPayment_contract_type()))//等额本息
        {
            // 合同金额
        	WmsCreCreditHead wmsCreCreditHead = wmsCreCreditHeadDao.get(bean.getWms_cre_credit_head_id());
        	BigDecimal borrow = wmsCreCreditHead.getAppro_limit().multiply(new BigDecimal(10000));// 面签金额==其中本金
        	if(bean.getProtocol_type().equals("9") || bean.getProtocol_type().equals("10")//房贷4号
        		||bean.getProtocol_type().equals("23") || bean.getProtocol_type().equals("24")
        		||bean.getProtocol_type().equals("26"))//新房2号
        	{
        		bean.setLoan_amount(borrow);//转账金额等于本金（为了后面方便）
        	}
            // 贷款期数
            BigDecimal deadline = new BigDecimal(bean.getBorrow_deadline());
            // 月还款额 = 合同金额/期数+合同金额*1.27%（和之后四舍五入取整）
            BigDecimal refund_limit_month = (borrow.divide(deadline, 8, BigDecimal.ROUND_HALF_UP).add(borrow.multiply(borrow_interest))).divide(new BigDecimal(
                                                                                                                                                               1),
                                                                                                                                                0,
                                                                                                                                                BigDecimal.ROUND_HALF_UP);
            // 月还款本金 = 其中本金/期数（四舍五入取整）
            BigDecimal cipal_limit_month = borrow.divide(deadline, 0, BigDecimal.ROUND_HALF_UP);
            bean.setOrg_repay_principal(cipal_limit_month);
            // 月利息 = 月还款额 - 月还款本金
            bean.setOrg_repay_interest((refund_limit_month.subtract(cipal_limit_month)));
        }
        //如果合同编号为空则初始化合同编号
        if("".equals(bean.getProtocol_id_year_num())||bean.getProtocol_id_year_num()==null){
        	 bean.setProtocol_id_year_num(bean.getProtocol_id_year() + "年第" + bean.getProtocol_id_num() + "号");//
        }
        // 保存借款合同信息
        ret = wmscreapproborrowprotocolDao.saveforhouse(bean);

        service.setCreate_user_id(user.getUserId());
        service.setCreate_user_name(user.getRealname());
        service.setEnable_flag("1");
        service.setCreate_timestamp(new Timestamp(new Date().getTime()));
        service.setLast_update_timestamp(new Timestamp(new Date().getTime()));
        // 保存居间服务协议
        ret = wmsCreApproServiceProtocolDao.saveforhouse(service);

        // 保存抵押人或者是保证人信息
        if (person.getPerson_name() != null && person.getPerson_identity_id() != null
            && person.getPerson_identity_id() != "" && person.getPerson_name() != "")
        {
            String person_style = "2";// 人员的类型2为保证人
            // 保存抵押人或者是保证人信息
            savePerson(person.getPerson_name(), person.getPerson_identity_id(), user, bean, ret, person_style);
        }

        if (person_name1 != null && person_identity_id1 != null && person_name1 != "" && person_identity_id1 != "")
        {
            String person_style = "1";// 人员的类型1为抵押人
            // 保存抵押人或者是保证人信息
            savePerson(person_name1, person_identity_id1, user, bean, ret, person_style);
        }
        // 如果合同是等额只有一个抵押人 不执行此代码
        if (person_name2 != null && person_identity_id2 != null && person_name2 != "" && person_identity_id2 != "")
        {
            String person_style = "1";// 人员的类型1为抵押人
            // 保存抵押人或者是保证人信息
            savePerson(person_name2, person_identity_id2, user, bean, ret, person_style);
        }
        List<WmsCreApproProtocolAttach> wmsCreApproProtocolAttachListStrs = JsonUtil.jsonArrayToList(tablevalue,
                                                                                                     WmsCreApproProtocolAttach.class);// 获取附件信息
        if (wmsCreApproProtocolAttachListStrs != null)
        {
            // 保存合同中附件表格的信息
            for (WmsCreApproProtocolAttach wmsCreApproProtocolAttach : wmsCreApproProtocolAttachListStrs)
            {
                // 如果附件的序号为空则 此条记录不保存
            	//if (wmsCreApproProtocolAttach.getAttach_number().equals(""))
            	if ("".equals(wmsCreApproProtocolAttach.getAttach_number()))
                {
                    break;
                }
                wmsCreApproProtocolAttach.setCreate_user_id(user.getUserId());
                wmsCreApproProtocolAttach.setCreate_user_name(user.getRealname());
                wmsCreApproProtocolAttach.setCreate_timestamp(new Timestamp(new Date().getTime()));
                wmsCreApproProtocolAttach.setLast_update_user_id(user.getUserId());
                wmsCreApproProtocolAttach.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                wmsCreApproProtocolAttach.setEnable_flag("1");
                wmsCreApproProtocolAttach.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
                wmsCreApproProtocolAttach.setWms_cre_appro_id(bean.getWms_cre_appro_id());// 设置合同主键
                wmscreapproprotocolattachDao.save(wmsCreApproProtocolAttach);
            }
        }
        List<WmsCreApproComDebtor> WmsCreApproComDebtorListStrs = JsonUtil.jsonArrayToList(comnumval,
                                                                                           WmsCreApproComDebtor.class);// 获取共同债务人
        if (WmsCreApproComDebtorListStrs != null)
        {
            // 保存合同中共同债务人的信息
            for (WmsCreApproComDebtor wmsCreApproComDebtor : WmsCreApproComDebtorListStrs)
            {
                // 如果共同债务人姓名为空则 此条记录不保存
                if (wmsCreApproComDebtor.getCom_debtor_name().equals(""))
                {
                    break;
                }
                wmsCreApproComDebtor.setCreate_user_id(user.getUserId());
                wmsCreApproComDebtor.setCreate_user_name(user.getRealname());
                wmsCreApproComDebtor.setCreate_timestamp(new Timestamp(new Date().getTime()));
                wmsCreApproComDebtor.setLast_update_user_id(user.getUserId());
                wmsCreApproComDebtor.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                wmsCreApproComDebtor.setEnable_flag("1");
                wmsCreApproComDebtor.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
                wmsCreApproComDebtorDao.save(wmsCreApproComDebtor);
            }
        }
        //如果长春合同  （新房贷1号担责）（新房贷2号担责）需要保存个人担保合同
        if(("25".equals(bean.getProtocol_type())||"26".equals(bean.getProtocol_type()))&&wmsCreApproProtocolSecured!=null){
        	wmsCreApproProtocolSecured.setCreate_user_id(user.getUserId());
        	wmsCreApproProtocolSecured.setCreate_user_name(user.getRealname());
        	wmsCreApproProtocolSecured.setCreate_timestamp(new Timestamp(new Date().getTime()));
        	wmsCreApproProtocolSecured.setLast_update_user_id(user.getUserId());
        	wmsCreApproProtocolSecured.setLast_update_timestamp(new Timestamp(new Date().getTime()));
        	wmsCreApproProtocolSecured.setEnable_flag("1");
        	wmsCreApproProtocolSecured.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
        	wmsCreApproProtocolSecuredDao.save(wmsCreApproProtocolSecured);
        }
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    // 保存抵押人或者是保证人信息
    private void savePerson(String person_name, String person_identity_id, UserBean user,
                            WmsCreApproBorrowProtocol bean, int ret, String person_style)
    {
        WmsCrePersonnelInfo person = new WmsCrePersonnelInfo();
        person.setPerson_name(person_name);
        person.setPerson_identity_id(person_identity_id);
        person.setCreate_user_id(user.getUserId());
        person.setCreate_user_name(user.getRealname());
        person.setEnable_flag("1");
        person.setCreate_timestamp(new Timestamp(new Date().getTime()));
        person.setLast_update_timestamp(new Timestamp(new Date().getTime()));
        person.setPerson_style(person_style);// 设置为抵押人
        person.setWms_cre_appro_borrow_protocol(bean.getWms_cre_appro_id());// 设置合同主键
        // 保存抵押人或者是保证人信息
        ret = wmsCrePersonnelInfoDao.save(person);
    }

    /**
     * 借款合同信息查询--房产终审--签合同
     * 
     * @param
     * @return
     * @author baisong
     */
    public Map<String, Object> searchobjforhouse(Integer wms_cre_credit_head_id)
    {
        Map<String, Object> mccs = wmscreapproborrowprotocolDao.getobjforhouse(wms_cre_credit_head_id);
        
        //需求变更：若未签合同无债务人身份证号，则从客户信息表里获取
        if(mccs == null || !mccs.containsKey("debtor_identity_id")) {
            WmsCreCreditLineCustomerChangeHead customer_major = this.wmsCreCreditLineCustomerChangeHeadDao.getMainByFk(wms_cre_credit_head_id);
            if(customer_major != null) {
                mccs.put("debtor_identity_id", customer_major.getId_card());
            }
        }
        
        int wms_cre_appro_id = (int) mccs.get("wms_cre_appro_id");
        List<WmsCrePersonnelInfo> list = wmscrepersonnelinfoDao.searchforhouse(wms_cre_appro_id);
        List<Map<String, Object>> attachmap = wmscreapproprotocolattachDao.searchforhouse(wms_cre_credit_head_id);
        // 循环将值放入到mccs map中
        for (int i = 0; i < attachmap.size(); i++)
        {
            Map<String, Object> map = attachmap.get(i);
            mccs.put("attach_number" + i, map.get("attach_number"));// 序号
            mccs.put("mortgage_name" + i, map.get("mortgage_name"));// 抵押物名称
            mccs.put("buy_date" + i, map.get("buy_date"));// 购置日期
            mccs.put("house_area" + i, map.get("house_area"));// 面积
            mccs.put("estimate_value" + i, map.get("estimate_value"));// 评估价值
            mccs.put("mortgage_value" + i, map.get("mortgage_value"));// 抵押价值
            mccs.put("house_type" + i, map.get("house_type"));// 房产类型
            mccs.put("house_certificate_number" + i, map.get("house_certificate_number"));// 房产权证号码
            mccs.put("house_roll_number" + i, map.get("house_roll_number"));// 房产卷号
            mccs.put("explicit_address" + i, map.get("explicit_address"));// 详细地址
            mccs.put("hill_land", map.get("hill_land"));// 丘地号
            mccs.put("mortgage_value_caps", map.get("mortgage_value_caps"));// 抵押价格大写
            if(i ==0){
            	mccs.put("attach_number", map.get("attach_number"));// 序号
                mccs.put("mortgage_name", map.get("mortgage_name"));// 抵押物名称
                mccs.put("buy_date", map.get("buy_date"));// 购置日期
                mccs.put("house_area", map.get("house_area"));// 面积
                mccs.put("estimate_value", map.get("estimate_value"));// 评估价值
                mccs.put("mortgage_value", map.get("mortgage_value"));// 抵押价值
                mccs.put("house_type", map.get("house_type"));// 房产类型
                mccs.put("house_certificate_number", map.get("house_certificate_number"));// 房产权证号码
                mccs.put("house_roll_number", map.get("house_roll_number"));// 房产卷号
                mccs.put("explicit_address", map.get("explicit_address"));// 详细地址
                mccs.put("hill_land", map.get("hill_land"));// 丘地号
                mccs.put("mortgage_value_caps", map.get("mortgage_value_caps"));// 抵押价格大写
            }
        }
        if (list != null)
        {
            int num = 0;// 定义一个标识
            for (int i = 0; i < list.size(); i++)
            {
                WmsCrePersonnelInfo wmsCrePersonnelInfo = (WmsCrePersonnelInfo) list.get(i);// 获取列表内容
                if (wmsCrePersonnelInfo.getPerson_style().equals("2"))
                {// 如果人员类型为2 这赋值给保证人
                    mccs.put("person_identity_id", wmsCrePersonnelInfo.getPerson_identity_id());// 赋值身份证号
                    mccs.put("person_name", wmsCrePersonnelInfo.getPerson_name());// 赋值名字
                }
                else if (wmsCrePersonnelInfo.getPerson_style().equals("1"))
                {// 如果人员类型为1则赋值给抵押人
                    if (num == 0)
                    {
                        num += 1;// 如果第一个则赋值给此参数 否则赋值给其他参数
                        mccs.put("person_identity_id1", wmsCrePersonnelInfo.getPerson_identity_id());// 赋值身份证号
                        mccs.put("person_name1", wmsCrePersonnelInfo.getPerson_name());// 赋值名字
                    }
                    else
                    {
                        mccs.put("person_identity_id2", wmsCrePersonnelInfo.getPerson_identity_id());// 赋值身份证号
                        mccs.put("person_name2", wmsCrePersonnelInfo.getPerson_name());// 赋值名字
                    }
                }
            }
        }
        //如果是长春的新房贷1号担责和新房贷2号担责 （个人担保合同）
        if(mccs!=null&& mccs.get("protocol_type")!=null&& ("25".equals(mccs.get("protocol_type").toString())||"26".equals(mccs.get("protocol_type").toString()) ) ){
        	Map<String,Object> map=new HashMap<>();
        	map.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        	map.put("enable_flag", "1");
        	map.put("sortname", "wms_cre_credit_head_id");
        	map.put("sortorder", "asc");
        	List<Map<String,Object>> listSecured=wmsCreApproProtocolSecuredDao.search(map);
        	if(listSecured!=null&&listSecured.size()>0){
        		for (Map.Entry<String, Object> entry : listSecured.get(0).entrySet()) {
        			   mccs.put(entry.getKey(), entry.getValue());	
        		}
        	}
        }
        SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
        mccs.put("todayDate", dfm.format(new Date()));
        return mccs;
    }
    /**
     * -车贷--放款申请
     * 
     * @param
     * @return
     * @author baisong
     */
    public Map<String, Object> searchobjforCar(Integer wms_cre_credit_head_id)
    {
        Map<String, Object> mccs = wmscreapproborrowprotocolDao.getobjforcar(wms_cre_credit_head_id);
        SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
        mccs.put("todayDate", dfm.format(new Date()));
        return mccs;
    }

    @Override
    public WmsCreApproBorrowProtocol getBorrowProtocolByFK(Integer wms_cre_credit_head_id)
    {
        return wmscreapproborrowprotocolDao.getBorrowProtocolByFK(wms_cre_credit_head_id);
    }
    /**
     * Description :通过wms_cre_credit_head_id获取对应的合同信息
     * 
     * @param primary wms_cre_credit_head_id
     * @return int
     * @author baisong
     */
    @Override
    public int getNumberByFK(Integer wms_cre_credit_head_id)
    {
        return wmscreapproborrowprotocolDao.getNumberByFK(wms_cre_credit_head_id);
    }
    /**
	  * 给ireport导出合同pdf使用
	  * baisong
	  * 20160830
	  */
  @Override
  public Map<String, Object> creditExport(HttpServletRequest request, HttpServletResponse response,WmsCreApproBorrowProtocolSearchBeanVO beanVO) {
	  	String fileurl ="";
		//生成报表文件名
		String defaultFilename = "借款合同";
		
		Map<String,Object> pmap = searchinfoforhouse(beanVO.getWms_cre_credit_head_id());
		//jasper文件路径
		if(pmap!=null&&pmap.get("jasper_name")!=null){
			fileurl=pmap.get("jasper_name").toString();
		}
		//备用代码
		if(fileurl.equals("")&&"2".equals(pmap.get("payment_contract_type").toString())){
			fileurl="creditBorrowProtocol2.jasper";
		}else if(fileurl.equals("")&&"1".equals(pmap.get("payment_contract_type").toString())){
			fileurl="creditBorrowProtocol1.jasper";
		}
		//下载
		return JasperUtil.exportDK(pmap, beanVO.getFile_type(), fileurl, defaultFilename, request, response);
  }
  /**
   * 借款合同信息查询--ireport模板使用
   * 
   * @param
   * @return
   * @author baisong
   * @date 20160831
   */
  @Override
  public Map<String, Object> searchinfoforhouse(Integer wms_cre_credit_head_id)
  {
	  //获取保存的数据 以供给ireport导出合同pdf
      Map<String, Object> mccs = wmscreapproborrowprotocolDao.getInfoForHouseIReport(wms_cre_credit_head_id);
      //获取附件信息
      List<Map<String, Object>> attachmap = wmscreapproprotocolattachDao.searchforhouse(wms_cre_credit_head_id);
      // 循环将值放入到mccs map中
      for (int i = 0; i < attachmap.size(); i++)
      {
			Map<String, Object> map = attachmap.get(i);
			mccs.put("attach_number", map.get("attach_number"));// 序号
			mccs.put("mortgage_name", map.get("mortgage_name"));// 抵押物名称
          mccs.put("buy_date", map.get("buy_date"));// 购置日期
          mccs.put("house_area", map.get("house_area"));// 面积
          mccs.put("estimate_value", map.get("estimate_value"));// 评估价值
          mccs.put("mortgage_value", map.get("mortgage_value"));// 抵押价值
          mccs.put("house_type", map.get("house_type"));// 房产类型
          mccs.put("house_certificate_number", map.get("house_certificate_number"));// 房产权证号码
          mccs.put("house_roll_number", map.get("house_roll_number"));// 房产卷号
          mccs.put("explicit_address", map.get("explicit_address"));// 详细地址
          mccs.put("hill_land", map.get("hill_land"));// 丘地号
          mccs.put("mortgage_value_caps", map.get("mortgage_value_caps"));// 抵押价格大写
          mccs.put("confirm_house_value", map.get("confirm_house_value"));// 两方确认金额
      }
      //特殊字段处理
      //protocol_refund_interest_month房产局合同利率 已经在sql中处理
      if(mccs!=null&&mccs.get("debtor_name")!=null&&mccs.get("com_debtor_name")!=null&&!"".equals(mccs.get("debtor_name").toString())&&!"".equals(mccs.get("com_debtor_name").toString())){
    	  //借款人姓名
    	  mccs.put("debtor_name_all", mccs.get("debtor_name").toString()+"、"+mccs.get("com_debtor_name").toString());      
      }else if(mccs!=null&&mccs.get("debtor_name")!=null){
    	  mccs.put("debtor_name_all", mccs.get("debtor_name"));    
      }else if(mccs!=null){
    	  mccs.put("debtor_name_all","");  
      }
      //抵押房产面积 -抵押合同
      if(mccs!=null&&mccs.get("house_area")!=null&&!"".equals(mccs.get("house_area").toString())){
    	  mccs.put("house_area", mccs.get("house_area").toString()+"平方米");
      }
      return mccs;
  }
  /**
   * 
   * @Title: perfectContractSave
   * @Description: TODO(合同完善保存方法)
   * @param user
   * @param vo
   * @return String
   * @author: jiaodelong
   * @time:2017年2月23日 下午2:33:49
   * @see com.zx.emanage.loanappro.service.IWmsCreApproBorrowProtocolService#perfectContractSave(com.zx.sframe.util.vo.UserBean, com.zx.emanage.loanappro.vo.WmsCreApproBorrowProtocolSearchBeanVO)
   * history:
   * 1、2017年2月23日 jiaodelong 创建方法
   */
	@Override
	@Transactional
	public String perfectContractSave(UserBean user,WmsCreApproBorrowProtocolSearchBeanVO vo) {
	    Integer headid=null;
		String resStr = "success";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//根据姓名带出债权人信息
		paramMap.put("lender_name", vo.getCreditor_name());
		List<Map<String, Object>> lenderList = new ArrayList<Map<String, Object>>();
		lenderList = this.wmsCreContractLenderDao.search(paramMap);
		vo.setCreditor_tel(lenderList.get(0).get("mobile_phone").toString());//债权人电话
		vo.setCreditor_identity_id(lenderList.get(0).get("id_card").toString());//债权人身份证号
		vo.setCreditor_address(lenderList.get(0).get("contact_address").toString());//债权人地址
		vo.setCreditor_loan_name(lenderList.get(0).get("lender_name").toString());//债权人用户名
		vo.setCreditor_loan_number(lenderList.get(0).get("card_no").toString());//债权人账号
		vo.setCreditor_loan_bank(lenderList.get(0).get("bank_of_deposit_name").toString());//债权人银行
		
		//处理产品是否计算业绩存量等问题 保存标识到主表
        if (StringUtil.isNotBlank(vo.getBorrow_deadline()) && vo.getCre_loan_type() != null && vo.getWms_cre_credit_head_id() != null && vo.getAppro_limit() != null)
        {
		    Map<String,Object>  mapProtocolProperty=new HashMap<>();
            // 单据主键不为空
            if (vo.getWms_cre_credit_head_id() != null)
            {
                WmsCreCreditHead wHead = wmsCreCreditHeadDao.get(vo.getWms_cre_credit_head_id());
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
            mapProtocolProperty.put("borrow_deadline", vo.getBorrow_deadline());// 期限
            mapProtocolProperty.put("credit_limit", vo.getAppro_limit()/10000);// 获取申请贷款额度
            mapProtocolProperty.put("cre_loan_type", vo.getCre_loan_type());// 获取贷款产品
            // 产品配置表获取产品对应信息
            List<Map<String, Object>> retmapProtocolProperty = wmssyspropertyDao.getprotocolPropertyApply(mapProtocolProperty);
            // 如果产品存在
            if (retmapProtocolProperty != null && retmapProtocolProperty.size() > 0)
            {
                Map<String, Object> protocolMap = retmapProtocolProperty.get(0);
                // 是否计算业绩标识
                if (protocolMap.get("is_achievement") != null)
                {
                    vo.setIs_achievement(protocolMap.get("is_achievement").toString());
                }
                // 是否计算存量标识
                if (protocolMap.get("is_stock") != null)
                {
                    vo.setIs_stock(protocolMap.get("is_stock").toString());
                }

            }
		}
		//初始化总的本金和利息。每月本金和利息
		setProtocllInfo(vo);
		//展期续期不需要
//		if(!vo.getStatus().equals("2")){
//		    if(!vo.getProtocol_id_num().equals("")){
//	            String time = new Timestamp(System.currentTimeMillis()).toString();
//	            String riqi = time.split(" ")[0];
//	            String yue = riqi.split("-")[1];
//	            String ri = riqi.split("-")[2];
//	            
//	            String hetongyue = vo.getProtocol_id_num().substring(0,2);
//	            String hetongri = vo.getProtocol_id_num().substring(2,4);
//	            String hetongno = vo.getProtocol_id_num().substring(4,6);
//	             Map<String, Object> numMap = new HashMap<String, Object>();
//	            if(!yue.equals(hetongyue) || !ri.equals(hetongri)){
//	                 // 合同编号自动生成
//	                String yearnum = CodeNoUtil.getProCreCode();// 合同编号
//	                numMap.put("protocol_id_year_num", yearnum);
//	                String code[] = yearnum.split("年第");
//	                if (code.length > 1)
//	                {
////	                  String protocol_id_year = code[0];
//	                    String num = code[1];
//	                    String protocol_id_num = num.substring(0, num.length() - 1);
////	                  numMap.put("protocol_id_year", protocol_id_year);//合同编号 年
//	                    paramMap.put("protocol_id_num", protocol_id_num);//合同编号 号
//	                }
//	                vo.setProtocol_id_num(paramMap.get("protocol_id_num").toString());
//	            }
//	            vo.setProtocol_id_year_num(vo.getProtocol_id_year()+"年第"+vo.getProtocol_id_num()+"号");
//	        }else{
//	             // 合同编号自动生成
//	            String yearnum = CodeNoUtil.getProCreCode();// 合同编号
//	            vo.setProtocol_id_year_num(yearnum);//合同编号 年
//	            String code[] = yearnum.split("年第");
//	            if (code.length > 1)
//	            {
//	                String protocol_id_year = code[0];
//	                String num = code[1];
//	                String protocol_id_num = num.substring(0, num.length() - 1);
//	                vo.setProtocol_id_num(protocol_id_num);//合同编号 号
//	                vo.setProtocol_id_year(protocol_id_year);//合同编号 年
//	            }
//	        }
            /*		  //修改贷款主表单据状态   M合同完善完成
            	        WmsCreCreditHead head = new WmsCreCreditHead();
            	        head.setBill_status("M");
            	        head.setWms_cre_credit_head_id(vo.getWms_cre_credit_head_id());
            	        wmsCreCreditHeadDao.updateBillStatus(head);*/
//		}
		
		//处理利率问题 等额本息
		if("1".equals(vo.getPayment_contract_type())){
			vo.setService_refund_principal_month_lower(vo.getService_cost_month());
			vo.setService_cost_month(new BigDecimal(0));
		}
		   WmsCreCreditServiceType ServiceType=new WmsCreCreditServiceType();
		   ServiceType.setOld_wms_cre_credit_notary_warn_id(vo.getWms_cre_credit_notary_warn_id());
           //还款提醒表和贷款主表中间表
           List<WmsCreCreditServiceType> typeService= wmsCreCreditServiceTypeDao.getListByEntity(ServiceType);
		//判断如果status=1 为修改  为0是保存,2为展期和续期保存
		if((vo.getStatus().equals("2") || !vo.getBill_type().equals("01")) && !vo.getBill_status().equals("I") && !vo.getBill_status().equals("KT")){
            // 当前时间
            java.sql.Timestamp nowDate = new java.sql.Timestamp(System.currentTimeMillis());
		    //还款提醒表
		    WmsCreCreditNotaryWarn wmsCreCreditNotaryWarn= wmsCreCreditNotaryWarnDao.get(vo.getWms_cre_credit_notary_warn_id());
		    WmsCreCreditServiceType wmsCreCreditServiceType=new WmsCreCreditServiceType();
		    wmsCreCreditServiceType.setWms_cre_credit_notary_warn_id(vo.getWms_cre_credit_notary_warn_id());
		    //还款提醒表和贷款主表中间表
		    List<WmsCreCreditServiceType> typeServiceList= wmsCreCreditServiceTypeDao.getListByEntity(wmsCreCreditServiceType);
		    
		    //新增主表信息
		    WmsCreCreditHead head = new WmsCreCreditHead();
		    head.setBill_code(CodeNoUtil.getHouseCreCode());
		    head.setCredit_limit(new Double((vo.getAppro_limit() / 10000)));
		    head.setAppro_limit(new BigDecimal(vo.getAppro_limit() / 10000));
		    head.setMax_repayment_time_limit(Integer.parseInt(vo.getBorrow_deadline()));
		    head.setBill_status("I");
		    head.setVersion_number("3");
		    head.setCre_type("2");
		    head.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
		    if(vo.getConsult_service_cost() != null){
		        head.setCommission_rate(vo.getConsult_service_cost());
		    }
		    if(vo.getFees() != null){
		        head.setCommission_fee(vo.getFees());
		    }
		    //根据员工短工号获取人员表信息 赋值到主表
		    PmPersonnel pm = new PmPersonnel();
		    pm.setPersonnel_shortcode(vo.getSalesman_shortcode());
		    List<PmPersonnel> pmList = pmPersonnelDao.getListByEntity(pm);
		    head.setSalesman_id(pmList.get(0).getPersonnel_id());//业务员ID
		    head.setSalesman_code(pmList.get(0).getPersonnel_code());//业务员编码
		    head.setSalesman_name(pmList.get(0).getPersonnel_name());//业务员名字
		    head.setCity(UserCityUtil.getUserCity(pmList.get(0).getPersonnel_regionnumber()));//业务员城市
		    head.setCreate_user_id(pmList.get(0).getPersonnel_id());
		    head.setCreate_user_name(pmList.get(0).getPersonnel_name());
		    head.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
		    head.setCreate_user_dept_id(pmList.get(0).getPersonnel_deptid());
		    head.setCreate_user_city_code(pmList.get(0).getPersonnel_regionnumber());
		    head.setSalesman_dept_id(pmList.get(0).getPersonnel_deptid());
		    head.setSalesman_shortcode(pmList.get(0).getPersonnel_shortcode());
		    head.setSalesman_city_code(pmList.get(0).getPersonnel_regionnumber());
		    head.setBill_type(vo.getBill_type());
		    head.setCre_loan_type(vo.getCre_loan_type());
		    head.setEnable_flag("1");
            head.setLast_update_timestamp(nowDate);
            head.setIs_achievement(vo.getIs_achievement());// 是否计算业绩 0：否；1：是
            head.setIs_stock(vo.getIs_stock());// 是否计算存量 0：否；1：是
            if (vo.getMort_flag() != null && !"".equals(vo.getMort_flag()))
            {
                head.setMort_flag(vo.getMort_flag());// 抵押形式1一拆2二拆
            }
            else
            {
                head.setMort_flag("1");// 抵押形式1一拆2二拆
            }
		    wmsCreCreditHeadDao.save(head);
		    
		    
            // 审批表
            WmsCreHousingApprovalInfo approvalInfo = new WmsCreHousingApprovalInfo();// 房贷——审批信息表
            approvalInfo.setWms_cre_credit_head_id(head.getWms_cre_credit_head_id());
            approvalInfo.setApproval_task_type(WorkflowConstantHelp.STRHOUSINGLOANNODE_QDHT);// 审批节点
            approvalInfo.setApproval_type("1");// 审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
            // approvalInfo.setApproval_advice("合同信息补录完成");// 审批意见
            /*****************/
            StringBuffer sb = new StringBuffer();
            String advice = "合同信息补录完成";
            sb.append(advice);
            sb.append("     签约金额为");
            sb.append(":");
            sb.append(vo.getAppro_limit() + "元");
            approvalInfo.setApproval_advice(sb.toString());
            /*******************/
            approvalInfo.setApproval_result("true");// 审批结果 // 流程中传递pass的值
            approvalInfo.setApproval_user_id(user.getUserId());// 审批人id
            approvalInfo.setApproval_time(nowDate);// 审批时间
            approvalInfo.setApproval_task_code("QDHT");
            approvalInfo.setEnable_flag("1");
            wmsCreHousingApprovalInfoDao.saveInfo(approvalInfo);


		    //关联表新增
		    WmsCreCreditServiceType servicetype = new WmsCreCreditServiceType();
		    servicetype.setWms_cre_credit_head_id(head.getWms_cre_credit_head_id());
		    //上次单据主键
		    if(wmsCreCreditNotaryWarn.getWms_cre_credit_head_id()!=null){
		        servicetype.setPre_wms_cre_credit_head_id(wmsCreCreditNotaryWarn.getWms_cre_credit_head_id());
		        servicetype.setOld_wms_cre_credit_head_id(wmsCreCreditNotaryWarn.getWms_cre_credit_head_id());
		    }
		    //上次还款表主键
		    servicetype.setPre_wms_cre_credit_notary_warn_id(vo.getWms_cre_credit_notary_warn_id());
		    servicetype.setOld_wms_cre_credit_notary_warn_id(vo.getWms_cre_credit_notary_warn_id());
		    //原始单据号/上次单据号
		    servicetype.setBill_code(wmsCreCreditNotaryWarn.getBill_code());
		    
		    if(typeServiceList!=null&&typeServiceList.size()>0){
		        wmsCreCreditServiceType=typeServiceList.get(0);
		        servicetype.setOld_wms_cre_credit_head_id(wmsCreCreditServiceType.getOld_wms_cre_credit_head_id());
		        servicetype.setOld_wms_cre_credit_notary_warn_id(wmsCreCreditServiceType.getOld_wms_cre_credit_notary_warn_id());
		        servicetype.setBill_code(wmsCreCreditServiceType.getBill_code());
		    }
		    servicetype.setThe_number(vo.getThe_number());
		    servicetype.setEnable_flag("1");
		    servicetype.setCreate_user_name(user.getRealname());
		    servicetype.setCreate_user_id(user.getUserId());
		    servicetype.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));   
            servicetype.setLast_update_user_id(user.getUserId());
            servicetype.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
            servicetype.setOperating_time(new Timestamp(System.currentTimeMillis()));
		    wmsCreCreditServiceTypeDao.save(servicetype);
		    
		    //合同表
		    vo.setWms_cre_credit_head_id(head.getWms_cre_credit_head_id());
            vo.setProtocol_type(vo.getCre_loan_type().toString());
            vo.setEnable_flag("1");
            vo.setCreate_user_name(user.getRealname());
            vo.setCreate_user_id(user.getUserId().toString());
            vo.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
            if(vo.getBorrow_deadline().equals("")){
                vo.setBorrow_deadline(null);
            }
            //如果为展期保存，以前的合同表ID需要清空,再新增一条合同单据
            if(vo.getStatus().equals("1") && !vo.getBill_type().equals("01")){
                vo.setWms_cre_appro_id(null);
            }
            wmscreapproborrowprotocolDao.perfectContractSave(vo);
		    //合同附件表新增
            WmsCreApproProtocolAttach attach = new WmsCreApproProtocolAttach();
            attach.setWms_cre_appro_id(vo.getWms_cre_appro_id());
            attach.setWms_cre_credit_head_id(vo.getWms_cre_credit_head_id());
            attach.setHouse_area(vo.getHouse_area());
            attach.setHouse_certificate_number(vo.getHouse_certificate_number());
            attach.setHouse_roll_number(vo.getHouse_roll_number());
            attach.setExplicit_address(vo.getExplicit_address());
            attach.setCreate_user_id(pmList.get(0).getPersonnel_id());
            attach.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
            attach.setEnable_flag("1");
            if(vo.getConfirm_house_value() != null && vo.getConfirm_house_value().toString() != ""){
                attach.setConfirm_house_value(vo.getConfirm_house_value().toString());
            }
            wmscreapproprotocolattachDao.save(attach);
		    //区间管理服务协议表新增
            WmsCreApproServiceProtocol service = new WmsCreApproServiceProtocol();
            service.setProtocol_id_year(vo.getProtocol_id_year());
            service.setProtocol_id_num(vo.getProtocol_id_num());
            
            service.setProtocol_date(vo.getProtocol_date());
            service.setBorrower_name(vo.getDebtor_name());
            service.setBorrower_identity_id(vo.getDebtor_identity_id());
            service.setSecond_borrower_name(vo.getCom_debtor_name());
            service.setSecond_borrower_identity_id(vo.getCom_debtor_identity_id());
            service.setBorrower_address(vo.getDebtor_address());
            if(vo.getBorrow_deadline() != null){
                service.setRefund_deadline(Integer.parseInt(vo.getBorrow_deadline()));
            }
            service.setRefund_day(vo.getRefund_day());
            service.setWms_cre_credit_head_id(vo.getWms_cre_credit_head_id());
            service.setCreate_user_id(pmList.get(0).getPersonnel_id());
            service.setCreate_user_name(pmList.get(0).getPersonnel_name());
            service.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
            service.setEnable_flag("1");
            service.setConsult_service_cost(vo.getConsult_service_cost());
            service.setJujian_service_cost(vo.getJujian_service_cost());
            service.setService_cost_month(vo.getService_cost_month());
            service.setOwner(vo.getOwner());
            
            service.setAddressee_name(vo.getDebtor_name());
            service.setAddressee_tel(vo.getDebtor_tel());
            service.setProtocol_date(vo.getProtocol_date());
            service.setService_refund_principal_month_lower(vo.getService_refund_principal_month_lower());
            
            wmsCreApproServiceProtocolDao.save(service);
            
            //放款申请表保存
            WmsFinaCreLoanApp app = new WmsFinaCreLoanApp();
            app.setWms_cre_credit_head_id(vo.getWms_cre_credit_head_id());
//            app.setNotarial_fee(vo.getNotarial_fee());
            app.setPlatform_fee(vo.getNotarial_fee());
            app.setFees(vo.getFees());
            if(StringUtil.isNotEmpty(vo.getHouse_area())){
                app.setHouse_area(new BigDecimal(vo.getHouse_area()));
            }
            app.setLoan_amount_caps(vo.getLoan_amount_caps());
            app.setReceive_bank(vo.getDebtor_loan_bank());
            app.setCard_no(vo.getDebtor_loan_number());
            app.setCreate_user_id(pmList.get(0).getPersonnel_id());
            app.setCreate_user_name(pmList.get(0).getPersonnel_name());
            app.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
            app.setEnable_flag("1");
            app.setIt_cost_fee(vo.getIt_cost_fee());
            app.setExpedited_fee(vo.getExpedited_fee());
            app.setCre_type_name(vo.getCre_type_name());
            app.setNotary_is_finish(0);// 2017-07-05 默认值由1改为0
            app.setHe_is_finish(0);// 2017-07-05 默认值由1改为0
            app.setCheck_pay(vo.getCheck_pay());
            wmsFinaCreLoanAppDao.save(app);

            WmsCreCreditLineCustomerChangeHead changeHead = new WmsCreCreditLineCustomerChangeHead();
            WmsCreCustomerChangeLineHouseinfo houseInfo = new WmsCreCustomerChangeLineHouseinfo();
            if (wmsCreCreditNotaryWarn.getWms_cre_credit_head_id() != null)
            {
                // 复制客户主表
                changeHead = wmsCreCreditLineCustomerChangeHeadDao.getInfoForHeadId(wmsCreCreditNotaryWarn.getWms_cre_credit_head_id());
                changeHead.setWms_cre_credit_head_id(head.getWms_cre_credit_head_id());
                changeHead.setWms_cre_credit_line_customer_change_head_id(null);
                wmsCreCreditLineCustomerChangeHeadDao.save(changeHead);
                // 复制房产表
                WmsCreCreditLineCustomerChangeHead changeHeadOld = wmsCreCreditLineCustomerChangeHeadDao.getInfoForHeadId(wmsCreCreditNotaryWarn.getWms_cre_credit_head_id());
                houseInfo = wmsCreCustomerChangeLineHouseinfoDao.getInfoForCustomerId(changeHeadOld.getWms_cre_credit_line_customer_change_head_id());
                houseInfo.setWms_cre_customer_change_line_houseinfo_id(null);
                houseInfo.setWms_cre_credit_line_customer_change_head_id(changeHead.getWms_cre_credit_line_customer_change_head_id());
                wmsCreCustomerChangeLineHouseinfoDao.addNewRecord(houseInfo);
            }
            else
            {
                // 客户变更主表
                // WmsCreCreditLineCustomerChangeHead changeHead = new
                // WmsCreCreditLineCustomerChangeHead();
                changeHead.setWms_cre_credit_head_id(head.getWms_cre_credit_head_id());
                changeHead.setIs_major("1");
                // 客户姓名
                changeHead.setCustomer_name(wmsCreCreditNotaryWarn.getCustomer_name());
                // 客户身份证号码
                changeHead.setId_card(wmsCreCreditNotaryWarn.getId_card());
                // 客户户籍地址_更详细
                changeHead.setAddress_more(wmsCreCreditNotaryWarn.getCurrent_address_more());
                // 客户目前详细住址_更详细
                changeHead.setCurrent_address_more(wmsCreCreditNotaryWarn.getCurrent_address_more());
                // 客户联系电话
                changeHead.setMobile_telephone1(wmsCreCreditNotaryWarn.getMobile_telephone());
                // 客户联系电话
                changeHead.setMobile_telephone1(wmsCreCreditNotaryWarn.getMobile_telephone());
                changeHead.setCreate_user_name(user.getRealname());
                changeHead.setCreate_user_id(user.getUserId());
                changeHead.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
                changeHead.setLast_update_user_id(user.getUserId());
                changeHead.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
                changeHead.setEnable_flag("1");
                wmsCreCreditLineCustomerChangeHeadDao.save(changeHead);
                // 客户房产变更表
                // WmsCreCustomerChangeLineHouseinfo houseInfo = new
                // WmsCreCustomerChangeLineHouseinfo();
                houseInfo.setWms_cre_credit_line_customer_change_head_id(changeHead.getWms_cre_credit_line_customer_change_head_id());
                if (vo.getHouse_area() != null && vo.getHouse_area().toString() != "")
                { // 房产面积
                    houseInfo.setHouse_building_area(Double.valueOf(vo.getHouse_area()));
                }
                // 房产权证号码-房产证号
                houseInfo.setHouse_no(vo.getHouse_certificate_number());
                // 房产卷号
                houseInfo.setHouse_vol_no(vo.getHouse_roll_number());
                // 房产详细地址
                houseInfo.setHouse_address_more(vo.getExplicit_address());
                houseInfo.setCreate_user_id(user.getUserId());
                houseInfo.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
                houseInfo.setLast_update_user_id(user.getUserId());
                houseInfo.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
                houseInfo.setEnable_flag("1");
                wmsCreCustomerChangeLineHouseinfoDao.addNewRecord(houseInfo);
            }
            // 房贷——抵押房产信息
            WmsCreHousingCustomerHouse wmsCreHousingCustomerHouse = new WmsCreHousingCustomerHouse();
            wmsCreHousingCustomerHouse.setWms_cre_credit_head_id(head.getWms_cre_credit_head_id());
            wmsCreHousingCustomerHouse.setWms_cre_customer_change_line_houseinfo_id(houseInfo.getWms_cre_customer_change_line_houseinfo_id());
            wmsCreHousingCustomerHouse.setCreate_user_id(user.getUserId());
            wmsCreHousingCustomerHouse.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
            wmsCreHousingCustomerHouse.setLast_update_user_id(user.getUserId());
            wmsCreHousingCustomerHouse.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
            wmsCreHousingCustomerHouseDao.save(wmsCreHousingCustomerHouse);
		}
		else if(vo.getStatus().equals("1") || vo.getBill_status().equals("I") || vo.getBill_status().equals("KT")){
		    
		    headid = vo.getWms_cre_credit_head_id();
			if(vo.getBorrow_deadline().equals("")){
				vo.setBorrow_deadline(null);
			}
			wmscreapproborrowprotocolDao.updatePerfectContract(vo);
			WmsCreApproProtocolAttach attach = new WmsCreApproProtocolAttach();
			attach.setWms_cre_appro_id(vo.getWms_cre_appro_id());
			attach.setWms_cre_credit_head_id(vo.getWms_cre_credit_head_id());
			attach.setHouse_area(vo.getHouse_area());
			attach.setHouse_certificate_number(vo.getHouse_certificate_number());
			attach.setHouse_roll_number(vo.getHouse_roll_number());
			attach.setExplicit_address(vo.getExplicit_address());
			if(vo.getConfirm_house_value() != null && vo.getConfirm_house_value().toString() != ""){
				attach.setConfirm_house_value(vo.getConfirm_house_value().toString());
			}
			//修改关联表次数
			WmsCreCreditServiceType type = new WmsCreCreditServiceType();
			type.setThe_number(vo.getThe_number());
			type.setWms_cre_credit_head_id(vo.getWms_cre_credit_head_id());
			wmsCreCreditServiceTypeDao.updateTht_Number(type);
			
			
			wmscreapproprotocolattachDao.updateWmsCreApproProtocolAttach(attach);
			
			WmsCreApproServiceProtocol service = new WmsCreApproServiceProtocol();
			
			service.setProtocol_date(vo.getProtocol_date());
			service.setBorrower_name(vo.getDebtor_name());
			service.setBorrower_identity_id(vo.getDebtor_identity_id());
			service.setSecond_borrower_name(vo.getCom_debtor_name());
			service.setSecond_borrower_identity_id(vo.getCom_debtor_identity_id());
			service.setBorrower_address(vo.getDebtor_address());
			if(vo.getBorrow_deadline() != null){
				service.setRefund_deadline(Integer.parseInt(vo.getBorrow_deadline()));
			}
			service.setRefund_day(vo.getRefund_day());
			service.setWms_cre_credit_head_id(vo.getWms_cre_credit_head_id());
			service.setConsult_service_cost(vo.getConsult_service_cost());
			service.setJujian_service_cost(vo.getJujian_service_cost());
			service.setService_cost_month(vo.getService_cost_month());
			service.setOwner(vo.getOwner());
			
			service.setAddressee_name(vo.getDebtor_name());
			service.setAddressee_tel(vo.getDebtor_tel());
			service.setProtocol_date(vo.getProtocol_date());
			
			service.setService_refund_principal_month_lower(vo.getService_refund_principal_month_lower());
			
			wmsCreApproServiceProtocolDao.update(service);
			
			
			//放款申请表修改
            WmsFinaCreLoanApp app = new WmsFinaCreLoanApp();
            app.setWms_cre_credit_head_id(vo.getWms_cre_credit_head_id());
//            app.setNotarial_fee(vo.getPlatform_fee());
            app.setPlatform_fee(vo.getNotarial_fee());
            app.setFees(vo.getFees());
            if(StringUtil.isNotEmpty(vo.getHouse_area())){
                app.setHouse_area(new BigDecimal(vo.getHouse_area()));
            }
            app.setLoan_amount_caps(vo.getLoan_amount_caps());
            app.setReceive_bank(vo.getDebtor_loan_bank());
            app.setCard_no(vo.getDebtor_loan_number());
            app.setIt_cost_fee(vo.getIt_cost_fee());
            app.setExpedited_fee(vo.getExpedited_fee());
            app.setCre_type_name(vo.getCre_type_name());
            app.setCheck_pay(vo.getCheck_pay());
            wmsFinaCreLoanAppDao.updateForHeadId(app);
            // 查询抵押房产表
            WmsCreHousingCustomerHouse wmsCreHousingCustomerHouse = wmsCreHousingCustomerHouseDao.searchWmsCreCustomerChangeLineHouseinfoId(vo.getWms_cre_credit_head_id());
            if (wmsCreHousingCustomerHouse != null)
            {
                // 客户房产变更表
                WmsCreCustomerChangeLineHouseinfo wmsCreCustomerChangeLineHouseinfo = new WmsCreCustomerChangeLineHouseinfo();
                wmsCreCustomerChangeLineHouseinfo.setWms_cre_customer_change_line_houseinfo_id(wmsCreHousingCustomerHouse.getWms_cre_customer_change_line_houseinfo_id());
                if (vo.getHouse_area() != null && vo.getHouse_area().toString() != "")
                { // 房产面积
                    wmsCreCustomerChangeLineHouseinfo.setHouse_building_area(Double.valueOf(vo.getHouse_area()));
                }
                // 房产权证号码-房产证号
                wmsCreCustomerChangeLineHouseinfo.setHouse_no(vo.getHouse_certificate_number());
                // 房产卷号
                wmsCreCustomerChangeLineHouseinfo.setHouse_vol_no(vo.getHouse_roll_number());
                // 房产详细地址
                wmsCreCustomerChangeLineHouseinfo.setHouse_address_more(vo.getExplicit_address());

                wmsCreCustomerChangeLineHouseinfo.setLast_update_user_id(user.getUserId());
                wmsCreCustomerChangeLineHouseinfo.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
                wmsCreCustomerChangeLineHouseinfoDao.update(wmsCreCustomerChangeLineHouseinfo);
            }
            WmsCreHousingApprovalInfo approvalInfo = new WmsCreHousingApprovalInfo();// 房贷——审批信息表
            approvalInfo.setWms_cre_credit_head_id(vo.getWms_cre_credit_head_id());
            approvalInfo.setEnable_flag("1");
            approvalInfo.setApproval_type("1");
            approvalInfo.setApproval_task_code("QDHT");
            List<WmsCreHousingApprovalInfo> approvalInfoList = wmsCreHousingApprovalInfoDao.getListByEntity(approvalInfo);
            if (approvalInfoList != null && approvalInfoList.size() > 0)
            {
                approvalInfo.setWms_cre_housing_approval_info(approvalInfoList.get(approvalInfoList.size() - 1).getWms_cre_housing_approval_info());
                int index = approvalInfoList.get(approvalInfoList.size() - 1).getApproval_advice().lastIndexOf(":");
                if (index >= 0)
                {
                    StringBuffer sb = new StringBuffer();
                    String advice = approvalInfoList.get(approvalInfoList.size() - 1).getApproval_advice().substring(0, index);
                    sb.append(advice);
                    sb.append(":");
                    sb.append(vo.getAppro_limit() + "元");
                    approvalInfo.setApproval_advice(sb.toString());
                    wmsCreHousingApprovalInfoDao.update(approvalInfo);
                }
            }
		}else{
           
                headid = vo.getWms_cre_credit_head_id();
				vo.setProtocol_type(vo.getCre_loan_type().toString());
				
				vo.setEnable_flag("1");
				vo.setCreate_user_name(user.getRealname());
				vo.setCreate_user_id(user.getUserId().toString());
				vo.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
				if(vo.getBorrow_deadline().equals("")){
					vo.setBorrow_deadline(null);
				}
				wmscreapproborrowprotocolDao.perfectContractSave(vo);
				WmsCreApproProtocolAttach attach = new WmsCreApproProtocolAttach();
				attach.setWms_cre_appro_id(vo.getWms_cre_appro_id());
				attach.setWms_cre_credit_head_id(vo.getWms_cre_credit_head_id());
				attach.setHouse_area(vo.getHouse_area());
				attach.setHouse_certificate_number(vo.getHouse_certificate_number());
				attach.setHouse_roll_number(vo.getHouse_roll_number());
				attach.setExplicit_address(vo.getExplicit_address());
				attach.setCreate_user_id(user.getUserId());
				attach.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
				attach.setEnable_flag("1");
				if(vo.getConfirm_house_value() != null && vo.getConfirm_house_value().toString() != ""){
					attach.setConfirm_house_value(vo.getConfirm_house_value().toString());
				}
				wmscreapproprotocolattachDao.save(attach);
				WmsCreApproServiceProtocol service = new WmsCreApproServiceProtocol();
				service.setProtocol_id_year(vo.getProtocol_id_year());
				service.setProtocol_id_num(vo.getProtocol_id_num());
				
				service.setProtocol_date(vo.getProtocol_date());
				service.setBorrower_name(vo.getDebtor_name());
				service.setBorrower_identity_id(vo.getDebtor_identity_id());
				service.setSecond_borrower_name(vo.getCom_debtor_name());
				service.setSecond_borrower_identity_id(vo.getCom_debtor_identity_id());
				service.setBorrower_address(vo.getDebtor_address());
				if(vo.getBorrow_deadline() != null){
					service.setRefund_deadline(Integer.parseInt(vo.getBorrow_deadline()));
				}
				service.setRefund_day(vo.getRefund_day());
				service.setWms_cre_credit_head_id(vo.getWms_cre_credit_head_id());
				service.setCreate_user_id(user.getUserId());
				service.setCreate_user_name(user.getRealname());
				service.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
				service.setEnable_flag("1");
				service.setConsult_service_cost(vo.getConsult_service_cost());
				service.setJujian_service_cost(vo.getJujian_service_cost());
				service.setService_cost_month(vo.getService_cost_month());
				service.setOwner(vo.getOwner());
				
				service.setAddressee_name(vo.getDebtor_name());
				service.setAddressee_tel(vo.getDebtor_tel());
				service.setProtocol_date(vo.getProtocol_date());
				service.setService_refund_principal_month_lower(vo.getService_refund_principal_month_lower());
				
				wmsCreApproServiceProtocolDao.save(service);
				
			    //放款申请表保存
	            WmsFinaCreLoanApp app = new WmsFinaCreLoanApp();
	            app.setWms_cre_credit_head_id(vo.getWms_cre_credit_head_id());
	            //字段差异
	            app.setPlatform_fee(vo.getNotarial_fee());
	            app.setFees(vo.getFees());
	            if(StringUtil.isNotEmpty(vo.getHouse_area())){
	                app.setHouse_area(new BigDecimal(vo.getHouse_area()));
	            }
	            app.setLoan_amount_caps(vo.getLoan_amount_caps());
	            app.setReceive_bank(vo.getDebtor_loan_bank());
	            app.setCard_no(vo.getDebtor_loan_number());
	            app.setCreate_user_id(user.getUserId());
	            app.setCreate_user_name(user.getRealname());
	            app.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
	            app.setEnable_flag("1");
	            app.setIt_cost_fee(vo.getIt_cost_fee());
	            app.setExpedited_fee(vo.getExpedited_fee());
	            app.setCre_type_name(vo.getCre_type_name());
	            app.setNotary_is_finish(0);// 2017-07-05 默认值由1改为0
	            app.setHe_is_finish(0);// 2017-07-05 默认值由1改为0
	            app.setCheck_pay(vo.getCheck_pay());
	            wmsFinaCreLoanAppDao.save(app);
	            
	            
//				WmsCrePersonnelInfo personnel = new WmsCrePersonnelInfo();
//				personnel.setWms_cre_appro_borrow_protocol(vo.getWms_cre_appro_id());
//				personnel.setPerson_identity_id(vo.getPerson_identity_id());
//				personnel.setPerson_name(vo.getPerson_name());
//				personnel.setPerson_style("2");
//				personnel.setCreate_user_id(user.getUserId());
//				personnel.setCreate_user_name(user.getRealname());
//				personnel.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
//				personnel.setEnable_flag("1");
//				wmsCrePersonnelInfoDao.save(personnel);
		}
        if (headid != null)
        {
            // 更新贷款主表是否计算 业绩和存量标识
            WmsCreCreditHead head = new WmsCreCreditHead();
            head.setIs_achievement(vo.getIs_achievement());
            head.setIs_stock(vo.getIs_stock());
            head.setWms_cre_credit_head_id(headid);
            wmsCreCreditHeadDao.update(head);

            StringBuffer sb = new StringBuffer();
            String advice = "合同信息补录完成";
            sb.append(advice);
            sb.append("     签约金额为");
            sb.append(":");
            sb.append(vo.getAppro_limit() + "元");
            // 流程控制状态方法
            WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO = new WmsHouseCreditWorkFlowVO();
            approveHouseWorkFlowVO.setDebtkey("QDHT");
            approveHouseWorkFlowVO.setAdvice(sb.toString());
            approveHouseWorkFlowVO.setPass("true");
            approveHouseWorkFlowVO.setUserId(user.getUserId().toString());
            approveHouseWorkFlowVO.setWms_cre_credit_head_id(headid.toString());
            // 判断是否走流程
            if (approveHouseWorkFlowVO != null && approveHouseWorkFlowVO.getPass() != null)
            {
                // 走流程
                Map<String, Object> flowMap = wmsLoanWorkFlowService.publicApprovalStatus(approveHouseWorkFlowVO);
                // 判断流程是否正常
//                if (flowMap != null && flowMap.get("result") != null && "statusError".equals(flowMap.get("result").toString()))
//                {
//                    return flowMap.get("result").toString();
//                }
            }
            WmsCreCreditHead wmsCreCreditHead = new WmsCreCreditHead();
            wmsCreCreditHead.setWms_cre_credit_head_id(headid);
            wmsCreCreditHead.setCre_loan_type(vo.getCre_loan_type());
            this.wmsCreCreditHeadDao.updateforhouse(wmsCreCreditHead);
        }
		return resStr;
	}

	@Override
	public Map<String, Object> wmsPerfectContractCount(Integer wms_cre_credit_head_id) {
		String resStr = "success";
        WmsCreCreditHead wHead = wmsCreCreditHeadDao.get(wms_cre_credit_head_id);
        Map<String, Object> count = new HashMap<>();
        // 判断是否是组合贷
        if (wHead.getWms_cre_credit_group_id() == null)
        {
            count = wmscreapproborrowprotocolDao.getPerfectContract(wms_cre_credit_head_id);
            if (count != null && count.size() > 0)
            {
                count.put("resStr", resStr);
                return count;
            }
            else
            {
                return null;
            }
        }else{
            Map<String, Object> map = new HashMap<>();
            map.put("wms_cre_credit_group_id", wHead.getWms_cre_credit_group_id());
            map.put("bill_status", "E");
            // 查询是否存在组合贷没有合同完善的
            List<WmsCreCreditHead> list = wmsCreCreditHeadDao.judgeGroupCredit(map);
            if (list != null && list.size() > 0)
            {
                count.put("resStr", "1");
                return count;
            }else{
                count.put("resStr", "0");
                return count;
            }
        }


//        Map<String, Object> count = wmscreapproborrowprotocolDao.getPerfectContract(wms_cre_credit_head_id);
//		if(count!=null&&count.size()>0){
//			count.put("resStr", resStr);
//			return count;
//		}else{
//			return null;
//		}
		
	}
	
	public void setProtocllInfo (WmsCreApproBorrowProtocolSearchBeanVO bean){
		 bean.setPrincipal(bean.getPrincipal_lower());// 其中本金=借款本金
	        bean.setInterest(new BigDecimal(0));// 其中利息为0
	       
	        if ("2".equals(bean.getPayment_contract_type()))//先息后本   
	        {    
	        	// 合同利率为合同表中的利率加上居间服务协议中的每月应支付的利率
	        	BigDecimal borrow_interest = (bean.getBorrow_interest().add(bean.getService_cost_month())).divide(new BigDecimal(
                         100));
	        	
	            // 月还款额 = 合同金额*2%(利率)
	            BigDecimal refund_limit_month = bean.getPrincipal_lower().multiply(borrow_interest);
	            // 断言请求的操作具有精确的结果，因此不需要舍入
	            // 月还款本金 = 前n-1期：0 最后一期：合同金额
	            BigDecimal cipal_limit_month = new BigDecimal(0);
	            // 月还款本金本金
	            bean.setOrg_repay_principal((cipal_limit_month));
	            // 月利息 = 月还款额 - 月还款本金
	            bean.setOrg_repay_interest((refund_limit_month.subtract(cipal_limit_month).divide(new BigDecimal(1), 0,
	                                                                                              BigDecimal.ROUND_HALF_UP)));
	        }
	        else  if ("1".equals(bean.getPayment_contract_type()))//等额本息
	        {
	        	// 合同利率为合同表中的利率加上居间服务协议中的每月应支付的利率
	        	BigDecimal borrow_interest = bean.getBorrow_interest().divide(new BigDecimal(100));
	            // 合同金额
	        	//WmsCreCreditHead wmsCreCreditHead = wmsCreCreditHeadDao.get(bean.getWms_cre_credit_head_id());
	        	BigDecimal borrow = new BigDecimal(bean.getAppro_limit());// 面签金额==其中本金
	            // 贷款期数
	            BigDecimal deadline = new BigDecimal(bean.getBorrow_deadline());
	            // 月还款额 = 合同金额/期数+合同金额*1.27%（和之后四舍五入取整）
	            BigDecimal refund_limit_month = (bean.getRefund_limit_month());
	            // 月还款本金 = 其中本金/期数（四舍五入取整）
	            BigDecimal cipal_limit_month = borrow.divide(deadline, 0, BigDecimal.ROUND_HALF_UP);
	            bean.setOrg_repay_principal(cipal_limit_month);
	            // 月利息 = 月还款额 - 月还款本金
	            bean.setOrg_repay_interest((refund_limit_month.subtract(cipal_limit_month)));
	        }
	}
	
	/**
	 * 
	 * 获取贷款额度
	 * @param UserBean user, WmsCreApproBorrowProtocolSearchBeanVO vo
	 * @return 
	 */
	public Map<String, Object> getApproLimit(UserBean user, WmsCreApproBorrowProtocolSearchBeanVO vo) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		BigDecimal appro_limit = this.wmscreapproborrowprotocolDao.searchApproLimit(vo.getWms_cre_credit_head_id());
		resMap.put("appro_limit", appro_limit);
		return resMap;
	}

	@Override
	public Map<String, Object> getProtocolList(UserBean user, WmsCreApproBorrowProtocolSearchConditionBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String bill_code = queryInfo.getBill_code();
		if (StringUtil.isNotBlank(bill_code)){
            paramMap.put("bill_code", SysTools.getSqlLikeParam(bill_code));
        }
		
		String salesman_name = queryInfo.getSalesman_name();
		if (StringUtil.isNotBlank(salesman_name)){
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(salesman_name));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(salesman_name));
        }
		
		String create_timestamp_begin = queryInfo.getCreate_timestamp_begin();
        if (StringUtil.isNotBlank(create_timestamp_begin)){
            paramMap.put("create_timestamp_begin", Convert.toDate(create_timestamp_begin));
        }else{
        	 paramMap.put("create_timestamp_begin", Convert.toDate("2000-01-01"));
        }
        
        String create_timestamp_end = queryInfo.getCreate_timestamp_end();
        if (StringUtil.isNotBlank(create_timestamp_end)){
            paramMap.put("create_timestamp_end", Convert.toDate(create_timestamp_end + " 23:59:59"));
        }else{
        	paramMap.put("create_timestamp_end", new Date());
        }
        
        String debtor_name = queryInfo.getDebtor_name();
        if (StringUtil.isNotBlank(debtor_name)){
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(debtor_name));
        }
        
        String debtor_tel = queryInfo.getDebtor_tel();
        if (StringUtil.isNotBlank(debtor_tel)){
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(debtor_tel));
        }
        
        String protocol_date_begin = queryInfo.getProtocol_date_begin();
        if (StringUtil.isNotBlank(protocol_date_begin)){
            paramMap.put("protocol_date_begin", Convert.toDate(protocol_date_begin));
        }else{
	       	paramMap.put("protocol_date_begin", Convert.toDate("2000-01-01"));
	    }
        
        String protocol_date_end = queryInfo.getProtocol_date_end();
        if (StringUtil.isNotBlank(protocol_date_end)){
            paramMap.put("protocol_date_end", Convert.toDate(protocol_date_end + " 23:59:59"));
        }else{
        	paramMap.put("protocol_date_end", new Date());
        }
        
        String debtor_identity_id = queryInfo.getDebtor_identity_id();
        if (StringUtil.isNotBlank(debtor_identity_id)){
            paramMap.put("debtor_identity_id",  SysTools.getSqlLikeParam(debtor_identity_id));
        }
		
        String protocol_id_year_num = queryInfo.getProtocol_id_year_num();
        if (StringUtil.isNotBlank(protocol_id_year_num)){
            paramMap.put("protocol_id_year_num", SysTools.getSqlLikeParam(protocol_id_year_num));
        }
        
        String protocol_supply_date_begin = queryInfo.getProtocol_supply_date_begin();
        if (StringUtil.isNotBlank(protocol_supply_date_begin)){
            paramMap.put("protocol_supply_date_begin", Convert.toDate(protocol_supply_date_begin));
        }else{
       	    paramMap.put("protocol_supply_date_begin", Convert.toDate("2000-01-01"));
        }
        
        String protocol_supply_date_end = queryInfo.getProtocol_supply_date_end();
        if (StringUtil.isNotBlank(protocol_supply_date_end)){
            paramMap.put("protocol_supply_date_end", Convert.toDate(protocol_supply_date_end + " 23:59:59"));
        }else{
        	paramMap.put("protocol_supply_date_end", new Date());
        }
        
        String cre_loan_type = queryInfo.getCre_loan_type();
        if (StringUtil.isNotBlank(cre_loan_type)){
            paramMap.put("cre_loan_type", cre_loan_type);
        }
        
    	String bill_type = queryInfo.getBill_type();
        if (StringUtil.isNotBlank(bill_type)){
            paramMap.put("bill_type", bill_type);
        }
        String cre_type = queryInfo.getCre_type();
        if (StringUtil.isNotBlank(cre_type)){
            paramMap.put("cre_type", cre_type);
        }
        String mort_flag = queryInfo.getMort_flag();
        if (StringUtil.isNotBlank(mort_flag))
        {
            paramMap.put("mort_flag", mort_flag);// 抵押状态
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode")))
        {
            paramMap.put("salesman_id", user.getUserId());// 登陆人id
            paramMap.put("menu_url", WmsHelp.MENU_URL_HTCX_LIST);
            paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
        }
        List<Map<String,Object>> list = wmscreapproborrowprotocolDao.getProtocolList(paramMap);
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscreapproborrowprotocolDao.findProtocolCount(paramMap),list);
		return bean.getGridData();	
	}

	@Override
	public void protocolExportExcel(UserBean user, HttpServletResponse response,String search_params) {
		GsonBuilder gb = new GsonBuilder();
	    Gson g = gb.create();
	    Map<String, String> search_paramsMap = g.fromJson(search_params, new TypeToken<Map<String, String>>() {}.getType());
		Map<String,Object> paramMap = new HashMap<>();
		String bill_code = (String) search_paramsMap.get("bill_code");
		if (StringUtil.isNotBlank(bill_code)){
            paramMap.put("bill_code", SysTools.getSqlLikeParam(bill_code));
        }
		String salesman_name = (String) search_paramsMap.get("salesman_name");
		if (StringUtil.isNotBlank(salesman_name)){
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(salesman_name));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(salesman_name));
        }
		
		String create_timestamp_begin = (String) search_paramsMap.get("create_timestamp_begin");
        if (StringUtil.isNotBlank(create_timestamp_begin)){
            paramMap.put("create_timestamp_begin", Convert.toDate(create_timestamp_begin));
        }else{
        	 paramMap.put("create_timestamp_begin", Convert.toDate("2000-01-01"));
        }
        
        String create_timestamp_end = (String) search_paramsMap.get("create_timestamp_end");
        if (StringUtil.isNotBlank(create_timestamp_end)){
            paramMap.put("create_timestamp_end", Convert.toDate(create_timestamp_end + " 23:59:59"));
        }else{
        	paramMap.put("create_timestamp_end", new Date());
        }
        
        String debtor_name = (String) search_paramsMap.get("debtor_name");
        if (StringUtil.isNotBlank(debtor_name)){
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(debtor_name));
        }
        
        String debtor_tel = (String) search_paramsMap.get("debtor_tel");
        if (StringUtil.isNotBlank(debtor_tel)){
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(debtor_tel));
        }
        
        String protocol_date_begin = (String) search_paramsMap.get("protocol_date_begin");
        if (StringUtil.isNotBlank(protocol_date_begin)){
            paramMap.put("protocol_date_begin", Convert.toDate(protocol_date_begin));
        }else{
	       	paramMap.put("protocol_date_begin", Convert.toDate("2000-01-01"));
	    }
        
        String protocol_date_end = (String) search_paramsMap.get("protocol_date_end" + " 23:59:59");
        if (StringUtil.isNotBlank(protocol_date_end)){
            paramMap.put("protocol_date_end", Convert.toDate(protocol_date_end));
        }else{
        	paramMap.put("protocol_date_end", new Date());
        }
        
        String debtor_identity_id = (String) search_paramsMap.get("debtor_identity_id");
        if (StringUtil.isNotBlank(debtor_identity_id)){
            paramMap.put("debtor_identity_id",  SysTools.getSqlLikeParam(debtor_identity_id));
        }
		
        String protocol_id_year_num = (String) search_paramsMap.get("protocol_id_year_num");
        if (StringUtil.isNotBlank(protocol_id_year_num)){
            paramMap.put("protocol_id_year_num", SysTools.getSqlLikeParam(protocol_id_year_num));
        }
        
        String protocol_supply_date_begin = (String) search_paramsMap.get("protocol_supply_date_begin");
        if (StringUtil.isNotBlank(protocol_supply_date_begin)){
            paramMap.put("protocol_supply_date_begin", Convert.toDate(protocol_supply_date_begin));
        }else{
       	    paramMap.put("protocol_supply_date_begin", Convert.toDate("2000-01-01"));
        }
        
        String protocol_supply_date_end = (String) search_paramsMap.get("protocol_supply_date_end" + " 23:59:59");
        if (StringUtil.isNotBlank(protocol_supply_date_end)){
            paramMap.put("protocol_supply_date_end", Convert.toDate(protocol_supply_date_end));
        }else{
        	paramMap.put("protocol_supply_date_end", new Date());
        }
        
        String cre_loan_type = (String) search_paramsMap.get("cre_loan_type");
        if (StringUtil.isNotBlank(cre_loan_type)){
            paramMap.put("cre_loan_type", cre_loan_type);
        }
        
    	String bill_type = (String) search_paramsMap.get("bill_type");
        if (StringUtil.isNotBlank(bill_type)){
            paramMap.put("bill_type", bill_type);
        }
        String cre_type = (String) search_paramsMap.get("cre_type");
        if (StringUtil.isNotBlank(cre_type)){
            paramMap.put("cre_type", cre_type);
        }
        String mort_flag = search_paramsMap.get("mort_flag");
        if (StringUtil.isNotBlank(mort_flag))
        {
            paramMap.put("mort_flag", mort_flag);// 抵押状态
        }
        if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode")))
        {
            paramMap.put("salesman_id", user.getUserId());// 登陆人id
            paramMap.put("menu_url", WmsHelp.MENU_URL_HTCX_LIST);
            paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
        }
		List<Map<String,Object>> dailyFlowlist = wmscreapproborrowprotocolDao.findDailyFlowList(paramMap);
		List<Map<String,Object>> dailyCardlist = wmscreapproborrowprotocolDao.findDailyCardList(paramMap);
		List<Map<String,Object>> refundRemindList = wmscreapproborrowprotocolDao.findRefundRemindList(paramMap);
		//整个excel文件内容
		Map<String,Object> wholeExcel = new HashMap<String,Object>();
		wholeExcel.put("日流水", dailyFlowlist);
		wholeExcel.put("日刷卡", dailyCardlist);
		wholeExcel.put("还款提醒", refundRemindList);

    	try {
    		String out_file_name = "合同信息统计表.xlsx";
    		ExpertUtil eu = new ExpertUtil();
    		eu.expertExcel("合同查询模板.xlsx", wholeExcel, out_file_name, null, 1, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

    /**
     * @Title: getBorrowProtocolDetails
     * @Description: TODO(根据单据主表id获取合同信息)
     * @param wms_cre_credit_head_id
     * @return 
     * @author: handongchun
     * @time:2017年3月27日 下午2:25:36
     * @see com.zx.emanage.loanappro.service.IWmsCreApproBorrowProtocolService#getBorrowProtocolDetails(int)
     * history:
     * 1、2017年3月27日 handongchun 创建方法
    */
    @Override
    public Map<String, Object> getBorrowProtocolDetails(UserBean user, int wms_cre_credit_head_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode")))
        {
            paramMap.put("salesman_id", user.getUserId());// 登陆人id
            paramMap.put("menu_url", WmsHelp.MENU_URL_HTCX_LIST);
            paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
        }
        Map<String,Object> result=new HashMap<>();
        paramMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        result = wmscreapproborrowprotocolDao.getBorrowProtocolDetails(paramMap);
        Map<String, Object> protocolMap = new HashMap<String, Object>();
        protocolMap = wmscreapproborrowprotocolDao.getPerfectContractInfo(wms_cre_credit_head_id);
        //获取利率类型
        // 产品配置表
        Map<String,Object> mapProtocolProperty=new HashMap<>();
        mapProtocolProperty.put("borrow_deadline", protocolMap.get("borrow_deadline"));// 获取贷款主表期限
        mapProtocolProperty.put("credit_limit", Double.parseDouble(protocolMap.get("appro_limit").toString())/10000);// 获取申请贷款额度
        mapProtocolProperty.put("payment_contract_type", protocolMap.get("payment_contract_type"));// 获取属性表还款类型
        mapProtocolProperty.put("cre_loan_type", protocolMap.get("cre_loan_type"));// 获取贷款产品
        WmsCreCreditHead wCreditHead = wmsCreCreditHeadDao.get(wms_cre_credit_head_id);
        mapProtocolProperty.put("regionNumber", wCreditHead.getCreate_user_city_code());// 区域编码
        // 产品配置表获取产品对应信息
        List<Map<String, Object>> retmapProtocolProperty = wmssyspropertyDao.getprotocolPropertyApply(mapProtocolProperty);
        if(retmapProtocolProperty!=null&&retmapProtocolProperty.size()>0){
            Map<String,Object> retMap=retmapProtocolProperty.get(0);
            result.put("interest_type", retMap.get("interest_type"));// 利息类型
        }
        return result;
    }

    /**
     * @Title: findPreviousBorrowProtocol
     * @Description: TODO(根据手动输入的还款用户名和债务人身份证号匹配合同)
     * @param map
     * @return 
     * @author: handongchun
     * @time:2017年5月23日 下午1:26:41
     * @see com.zx.emanage.loanappro.service.IWmsCreApproBorrowProtocolService#findPreviousBorrowProtocol(java.util.Map)
     * history:
     * 1、2017年5月23日 handongchun 创建方法
    */
    @Override
    public Map<String, String> findPreviousBorrowProtocol(Map<String, String> map)
    {
        return wmscreapproborrowprotocolDao.findPreviousBorrowProtocol(map);
    }

    /**
     * @Title: validateProtocolIdYearNum
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param user
     * @param protocol_id_year_num
     * @return 
     * @author: handongchun
     * @time:2017年7月20日 上午11:14:28
     * @see com.zx.emanage.loanappro.service.IWmsCreApproBorrowProtocolService#validateProtocolIdYearNum(com.zx.sframe.util.vo.UserBean, java.lang.String)
     * history:
     * 1、2017年7月20日 handongchun 创建方法
    */
    @Override
    public String validateProtocolIdYearNum(UserBean user, int wms_cre_credit_head_id, String protocol_id_year_num,String bill_status)
    {
        if(wms_cre_credit_head_id == -1){
            return "not_exists";
        }
        int protocolCount  = wmscreapproborrowprotocolDao.validateProtocolIdYearNum(wms_cre_credit_head_id,protocol_id_year_num, bill_status);
        if(protocolCount>0){
            return "already_exists";
        }
        return "not_exists";
    }
	
	
	
}
