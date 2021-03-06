package com.zx.emanage.loancheck.service.impl;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineHouseinfoDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingApplyAttDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingAttDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingCustomerHouseDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingFileInfoDao;
import com.zx.emanage.cremanage.service.IWmsCarLoanWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.cremanage.service.IWmsHouseCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loancheck.persist.WmsCreHousingCheckDao;
import com.zx.emanage.loancheck.service.IWmsCreHousingCheckService;
import com.zx.emanage.loancheck.vo.WmsCreHousingCheckSearchBeanVO;
import com.zx.emanage.loanreview.persist.WmsCreHousingAssessmentDao;
import com.zx.emanage.util.gen.WmsHelp;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineHouseinfo;
import com.zx.emanage.util.gen.entity.WmsCreHousingApplyAtt;
import com.zx.emanage.util.gen.entity.WmsCreHousingAssessment;
import com.zx.emanage.util.gen.entity.WmsCreHousingAtt;
import com.zx.emanage.util.gen.entity.WmsCreHousingCheck;
import com.zx.emanage.util.gen.entity.WmsCreHousingCustomerHouse;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrehousingcheckService")
public class WmsCreHousingCheckServiceImpl implements IWmsCreHousingCheckService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreHousingCheckServiceImpl.class);

    @Autowired
    private WmsCreHousingCheckDao wmscrehousingcheckDao;

    @Autowired
    private WmsCreCreditHeadDao wmscrecreditheaddao;

    @Autowired
    private IWmsHouseCreditWorkFlowService houseCreditWorkFlowService;

    @Autowired
    private WmsCreHousingAttDao wmscrehousingattDao;

    @Autowired
    private WmsCreHousingAssessmentDao wmscrehousingassessmentDao;
	@Autowired
	private IWmsCarLoanWorkFlowService carLoanWorkFlowService;//流程-车
	
	@Autowired
	private WmsCreHousingApplyAttDao wmsCreHousingApplyAttDao;
	
	@Autowired
	private WmsCreHousingFileInfoDao wmsCreHousingFileInfoDao;
	@Autowired
	private IWmsLoanWorkFlowService   wmsLoanWorkFlowService;//新房贷贷款流程
	@Autowired
	private WmsCreCreditLineCustomerChangeHeadDao wmsCreCreditLineCustomerChangeHeadDao;//
	@Autowired
	private IWmsCreCreditHeadService wmsCreCreditHeadService;
	@Autowired
	private WmsCreHousingCustomerHouseDao wmsCreHousingCustomerHouseDao;
	@Autowired
	private WmsCreCustomerChangeLineHouseinfoDao wmsCreCustomerChangeLineHouseinfoDao;
	
	
    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreHousingCheckSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrehousingcheckDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreHousingCheckSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrehousingcheckDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrehousingcheckDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreHousingCheck getInfo(Integer wms_cre_credit_head_id)
    {
        return wmscrehousingcheckDao.get(wms_cre_credit_head_id);
    }

    /**
     * 
     * @Title: save
     * @Description: TODO(房产核查)
     * @param bean
     * @param wmsCreHousingAssessment
     * @param user
     * @param vo
     * @param fileArr
     * @param fileArryf
     * @return 
     * @author: baisong
     * @time:2017年2月21日 下午6:26:09
     * @see com.zx.emanage.loancheck.service.IWmsCreHousingCheckService#save(com.zx.emanage.util.gen.entity.WmsCreHousingCheck, com.zx.emanage.util.gen.entity.WmsCreHousingAssessment, com.zx.sframe.util.vo.UserBean, com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO, java.lang.String, java.lang.String)
     * history:
     * 1、2017年2月21日 baisong 创建方法
     */
    @Override
    @Transactional
    public String save(WmsCreHousingCheck bean, WmsCreHousingAssessment wmsCreHousingAssessment, UserBean user,
                       WmsHouseCreditWorkFlowVO vo, String fileArr, String fileArryf)
    {
        String resStr = "temOK";
        int ret = 0;
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        // 判断是否走流程
        if (vo != null && vo.getPass() != null)
        {
            vo.setDebtkey("FCHC");// 房产核查
            vo.setUserId(String.valueOf(user.getUserId()));
            // 走流程
            Map<String, Object> flowMap = wmsLoanWorkFlowService.publicApprovalStatus(vo);
            // 判断流程是否正常
            if (flowMap != null && flowMap.get("result") != null && "statusError".equals(flowMap.get("result").toString()))
            {
                return flowMap.get("result").toString();
            }
        }
        List<WmsCreHousingAtt> attachment = JsonUtil.jsonArrayToList(fileArr, WmsCreHousingAtt.class); // 前台json附件路径数据转换为list
        
        List<WmsCreHousingApplyAtt> atthousing = JsonUtil.jsonArrayToList(fileArryf, WmsCreHousingApplyAtt.class); //验房附件
        WmsCreHousingCustomerHouse wmscrehousingcustomerhouse = wmsCreHousingCustomerHouseDao.searchWmsCreCustomerChangeLineHouseinfoId(bean.getWms_cre_credit_head_id());
        WmsCreCustomerChangeLineHouseinfo wmscrecustomerchangelinehouseinfo = new WmsCreCustomerChangeLineHouseinfo();
        wmscrecustomerchangelinehouseinfo.setCommunity_name(bean.getCommunity_name());
        wmscrecustomerchangelinehouseinfo.setHouse_address_more(bean.getHouse_address());
        wmscrecustomerchangelinehouseinfo.setHouse_building_area(bean.getHouse_building_area());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
		try {
			date = format.parse(bean.getHouse_buy_date());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        if (date != null)
        {
            long date_long = date.getTime();
            Date house_buy_date = new Date(date_long);

            wmscrecustomerchangelinehouseinfo.setHouse_buy_date(house_buy_date);
        }
        if(bean!=null&&bean.getBuilding_age()!=null){
        	wmscrecustomerchangelinehouseinfo.setHouse_age(bean.getBuilding_age().toString());	
        }
        wmscrecustomerchangelinehouseinfo.setWms_cre_customer_change_line_houseinfo_id(wmscrehousingcustomerhouse.getWms_cre_customer_change_line_houseinfo_id());
        wmsCreCustomerChangeLineHouseinfoDao.update(wmscrecustomerchangelinehouseinfo);
        wmscrehousingcheckDao.deleteRecord(bean.getWms_cre_credit_head_id());
        bean.setCreate_user_id(user.getUserId()); // 传入创建人id
        bean.setCreate_user_name(user.getRealname());// 传入创建人名称
        bean.setCreate_timestamp(sysTime);
        bean.setLast_update_user_id(user.getUserId());
        bean.setLast_update_timestamp(sysTime);
        bean.setEnable_flag("1"); // 是否有效
        if(bean.getHouse_buy_date().equals("")){
        	bean.setHouse_buy_date(null);
        }
        ret = wmscrehousingcheckDao.save(bean);
        wmscrehousingassessmentDao.deleteForId(wmsCreHousingAssessment.getWms_cre_credit_head_id());
        wmsCreHousingAssessment.setCommunity_name(wmsCreHousingAssessment.getCommunity_name1());
        wmsCreHousingAssessment.setHouse_address(wmsCreHousingAssessment.getHouse_address1());
        wmsCreHousingAssessment.setHouse_building_area(wmsCreHousingAssessment.getHouse_building_area1());
        ret = wmscrehousingassessmentDao.save(wmsCreHousingAssessment);
        if (vo.getPass() != null)
        {
            resStr = "success";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("house_appro_user_id", user.getUserId());
            params.put("house_appro_user_name", user.getRealname());
            params.put("house_appro_timestamp", sysTime);
            params.put("house_appro_advice", vo.getAdvice());
            params.put("house_appro_result", "accord".equals(vo.getPass()) ? "1" : "0");// 当流做出来后需要修改此值为pass的值
            params.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
            wmscrecreditheaddao.updateRecord(params);
        }
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
        paramsMap.put("data_type", "4");
        wmscrehousingattDao.deleteRecords(paramsMap);
        for (int i = 0; i < attachment.size(); i++)
        {
            WmsCreHousingAtt mplm = attachment.get(i);
            mplm.setData_type("4");// 4为验房评估表附件
            mplm.setWms_cre_credit_head_id(Integer.valueOf(vo.getWms_cre_credit_head_id()));
            mplm.setCreate_user_id(user.getUserId()); // 创建人id
            mplm.setCreate_user_name(user.getRealname());// 创建人名字
            mplm.setCreate_timestamp(sysTime);// 创建时间
            mplm.setEnable_flag("1");// 是否有效
            wmscrehousingattDao.save(mplm);
        }
        
        WmsCreCreditHead mcch = wmscrecreditheaddao.get(bean.getWms_cre_credit_head_id());
        
        if(vo.getDeleteAttIds() != null) {
            Map<String, Object> deletAtt = new HashMap<>();
            deletAtt.put("wms_cre_credit_head_id",vo.getWms_cre_credit_head_id());
            deletAtt.put("data_type_name",vo.getData_type_name());
            deletAtt.put("deleteAttIds", vo.getDeleteAttIds());
            wmsCreHousingApplyAttDao.deleteatt(deletAtt);
        }
       
        Map<String, Object> attParamMap = new HashMap<String, Object>();
        attParamMap.put("bill_code", mcch.getBill_code());
        for (int i = 0; i < atthousing.size(); i++){
        	WmsCreHousingApplyAtt houseapp = atthousing.get(i);
        	houseapp.setWms_cre_housing_file_info_id(Integer.valueOf(vo.getWms_cre_credit_head_id()));
        	houseapp.setWms_cre_credit_head_id(mcch.getWms_cre_credit_head_id());
        	
            if(houseapp.getWms_cre_housing_apply_att_id() == null) {
                //生成新的图片编号
                attParamMap.put("data_detail_name", houseapp.getData_detail_name());
                houseapp.setAttachment_old_name(wmsCreHousingApplyAttDao.getNextAttSeqByBillCodeAndDataTypeName(attParamMap));
                wmsCreHousingApplyAttDao.save(houseapp);
            } else {
                wmsCreHousingApplyAttDao.update(houseapp);
            }
        }
        /*        //新流程发送短信
                // 查询客户姓名
                Map<String, Object> customerChangeParamMap = new HashMap<String, Object>();
                customerChangeParamMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
                customerChangeParamMap.put("is_major", "1");
                customerChangeParamMap.put("enable_flag", "1");
                List<Map<String, Object>> customerChangeList = this.wmsCreCreditLineCustomerChangeHeadDao.search(customerChangeParamMap);
                // 调用发送信息的接口出现异常 不会影响正常流程
                try
                {
                    if (customerChangeList != null && customerChangeList.size() == 1)
                    {
                        // 调用方法map
                        Map<String, Object> sendMap = new HashMap<String, Object>();
                        // 发送短信map
                        Map<String, String> msgMap = new HashMap<String, String>();
                        // 参数map
                        Map<String, String> paramMap = new HashMap<String, String>();
                        sendMap.put("short_message", "1");
                        msgMap.put("tpl_id", "1694");
                        sendMap.put("user_id", user.getUserId());
                        // 把业务部门主管修改成管理部经理
                        sendMap.put("role_value", "1");// 终审人员--WorkflowRoleHelp.HOUSE_GLBJL
                        // 根据菜单查询人员
                        sendMap.put("menu_url", WmsHelp.MENU_URL_ZSSP_LIST);// 终审审批ual
                        sendMap.put("salesman_dept_id", mcch.getSalesman_dept_id());// 初审评估ual

                        paramMap.put("bill_code", mcch.getBill_code());
                        paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                        paramMap.put("city", mcch.getCity());

                        msgMap.put("paramJson", new Gson().toJson(paramMap));
                        sendMap.put("msgMap", msgMap);
                        sendMap.put("short_message", "1");

                        this.wmsCreCreditHeadService.getUserAndSendInfo(sendMap);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }*/
        // 调用发送信息的接口出现异常 不会影响正常流程--发送pc端消息
        // 判断是否走流程
        if (vo != null && vo.getPass() != null)
        {
            try
            {
                // 调用方法map
                Map<String, Object> sendMap = new HashMap<String, Object>();
                // 参数map
                Map<String, String> paramMap = new HashMap<String, String>();
                // 根据菜单查询人员
                sendMap.put("menu_url", WmsHelp.MENU_URL_ZSSP_LIST);// 终审审批ual
                sendMap.put("salesman_dept_id", mcch.getSalesman_dept_id());
                sendMap.put("role_value", "1");
                paramMap.put("bill_status", "E");// 终审审批单据状态
//                // 获取初评单据数量
//                List<Map<String, Object>> listS = wmscrecreditheaddao.getStatusCount(paramMap);
//                if (listS != null)
//                {
//                    paramMap.put("number", listS.size() + "");
//                }
//                else
//                {
                    paramMap.put("number", "1");
                // }
                sendMap.put("pc_msg_code", "20015");// pc端消息code
                sendMap.put("pc_message", "1");// 标识发送pc消息
                sendMap.put("msg_map", paramMap);// 极光推送的消息内容参数
                this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }
    /**
     * 
     * 
     * Description :房贷查询列表分页-完善
     * 
     * @return
     * @author baisong
     */
    @Override
    @Transactional
    public String WmsCreHousingCheckSaveForCompleted(WmsCreHousingCheck bean, WmsCreHousingAssessment wmsCreHousingAssessment, UserBean user,
                       WmsHouseCreditWorkFlowVO vo, String fileArr, String fileArryf)
    {
        String resStr = "temOK";
        int ret = 0;
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        List<WmsCreHousingAtt> attachment = JsonUtil.jsonArrayToList(fileArr, WmsCreHousingAtt.class); // 前台json附件路径数据转换为list
        
        List<WmsCreHousingApplyAtt> atthousing = JsonUtil.jsonArrayToList(fileArryf, WmsCreHousingApplyAtt.class); //验房附件
        WmsCreHousingCustomerHouse wmscrehousingcustomerhouse = wmsCreHousingCustomerHouseDao.searchWmsCreCustomerChangeLineHouseinfoId(bean.getWms_cre_credit_head_id());
        WmsCreCustomerChangeLineHouseinfo wmscrecustomerchangelinehouseinfo = new WmsCreCustomerChangeLineHouseinfo();
        wmscrecustomerchangelinehouseinfo.setCommunity_name(bean.getCommunity_name());
        wmscrecustomerchangelinehouseinfo.setHouse_address_more(bean.getHouse_address());
        wmscrecustomerchangelinehouseinfo.setHouse_building_area(bean.getHouse_building_area());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try
        {
			date = format.parse(bean.getHouse_buy_date());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        if (date != null)
        {
            long date_long = date.getTime();
            Date house_buy_date = new Date(date_long);
            wmscrecustomerchangelinehouseinfo.setHouse_buy_date(house_buy_date);
        }
        // 判断日期是否为空
        if (StringUtil.isBlank(bean.getHouse_buy_date()))
        {
            bean.setHouse_buy_date(null);
        }
        if(bean!=null&&bean.getBuilding_age()!=null){
        	wmscrecustomerchangelinehouseinfo.setHouse_age(bean.getBuilding_age().toString());	
        }
        wmscrecustomerchangelinehouseinfo.setWms_cre_customer_change_line_houseinfo_id(wmscrehousingcustomerhouse.getWms_cre_customer_change_line_houseinfo_id());
        wmsCreCustomerChangeLineHouseinfoDao.update(wmscrecustomerchangelinehouseinfo);
        //wmscrehousingcheckDao.deleteRecord(bean.getWms_cre_credit_head_id());
        bean.setCreate_user_id(user.getUserId()); // 传入创建人id
        bean.setCreate_user_name(user.getRealname());// 传入创建人名称
        bean.setCreate_timestamp(sysTime);
        bean.setLast_update_user_id(user.getUserId());
        bean.setLast_update_timestamp(sysTime);
        bean.setEnable_flag("1"); // 是否有效
        ret = wmscrehousingcheckDao.updateformakeup(bean);
        if(ret==0){
        	ret =wmscrehousingcheckDao.save(bean);
        }
        //wmscrehousingassessmentDao.deleteForId(wmsCreHousingAssessment.getWms_cre_credit_head_id());
        wmsCreHousingAssessment.setCommunity_name(wmsCreHousingAssessment.getCommunity_name1());
        wmsCreHousingAssessment.setHouse_address(wmsCreHousingAssessment.getHouse_address1());
        wmsCreHousingAssessment.setHouse_building_area(wmsCreHousingAssessment.getHouse_building_area1());
        ret = wmscrehousingassessmentDao.updateformakeup(wmsCreHousingAssessment);
        if(ret==0){
        	ret = wmscrehousingassessmentDao.save(wmsCreHousingAssessment);
        }
//        if (vo.getPass() != null)
//        {
//            resStr = "success";
//            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("house_appro_user_id", user.getUserId());
//            params.put("house_appro_user_name", user.getRealname());
//            params.put("house_appro_timestamp", sysTime);
//            params.put("house_appro_advice", vo.getAdvice());
//            params.put("house_appro_result", "true".equals(vo.getPass()) ? "1" : "0");// 当流做出来后需要修改此值为pass的值
//            params.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
//            wmscrecreditheaddao.updateRecord(params);
//            vo.setUserId(String.valueOf(user.getUserId()));
//            // <!--房贷流程版本号 1为老流程  2为新流程-->
//            if("2".equals(vo.getEdition_num())){
//                //数据来源1为pc 2为移动端
//                if("2".equals(vo.getVersion_number())){//2016/5/10版本
//                    vo.setProcessDefinitionKey(WorkflowConstantHelp.UPHOUSINGLOANPROCESS);
//                }else{//2016/2/10版本
//                    vo.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
//                }
//                vo.setDebtkey("4");
//                wmsLoanWorkFlowService.publicApproval(vo);
//                resStr ="success";
//            }else if("1".equals(vo.getEdition_num())){
//                resStr = houseCreditWorkFlowService.doapprovalHouseWorkFlow(vo); 
//            }
//        }
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
        paramsMap.put("data_type", "4");
        wmscrehousingattDao.deleteRecords(paramsMap);
        for (int i = 0; i < attachment.size(); i++)
        {
            WmsCreHousingAtt mplm = attachment.get(i);
            mplm.setData_type("4");// 4为验房评估表附件
            mplm.setWms_cre_credit_head_id(Integer.valueOf(vo.getWms_cre_credit_head_id()));
            mplm.setCreate_user_id(user.getUserId()); // 创建人id
            mplm.setCreate_user_name(user.getRealname());// 创建人名字
            mplm.setCreate_timestamp(sysTime);// 创建时间
            mplm.setEnable_flag("1");// 是否有效
            wmscrehousingattDao.save(mplm);
        }
        
        WmsCreCreditHead mcch = wmscrecreditheaddao.get(bean.getWms_cre_credit_head_id());
        
        if(vo.getDeleteAttIds() != null && vo.getDeleteAttIds().length > 0) {
            Map<String, Object> deletAtt = new HashMap<>();
            deletAtt.put("wms_cre_credit_head_id",vo.getWms_cre_credit_head_id());
            deletAtt.put("data_type_name",vo.getData_type_name());
            deletAtt.put("deleteAttIds", vo.getDeleteAttIds());
            wmsCreHousingApplyAttDao.deleteatt(deletAtt);
        }
       
        Map<String, Object> attParamMap = new HashMap<String, Object>();
        attParamMap.put("bill_code", mcch.getBill_code());
        for (int i = 0; i < atthousing.size(); i++){
            WmsCreHousingApplyAtt houseapp = atthousing.get(i);
            houseapp.setWms_cre_credit_head_id(mcch.getWms_cre_credit_head_id());
            
            if(houseapp.getWms_cre_housing_apply_att_id() == null) {
                //生成新的图片编号
                attParamMap.put("data_detail_name", houseapp.getData_detail_name());
                houseapp.setAttachment_old_name(wmsCreHousingApplyAttDao.getNextAttSeqByBillCodeAndDataTypeName(attParamMap));
                wmsCreHousingApplyAttDao.save(houseapp);
            } else {
                wmsCreHousingApplyAttDao.update(houseapp);
            }
        }
        
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreHousingCheck bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrehousingcheckDao.update(bean); // update a record replace
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
     * WmsCreHousingCheck() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreHousingCheck> getListByEntity(WmsCreHousingCheck queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreHousingCheck> beanList = wmscrehousingcheckDao.getListByEntity(queryInfo);
        return beanList;
    }

    /**
     * 
     * @Title: cardToBack
     * @Description: TODO( 房贷办件过期)
     * @param approveHouseWorkFlowVO
     * @param user
     * @return 
     * @author: baisong
     * @time:2017年2月21日 下午6:40:40
     * @see com.zx.emanage.loancheck.service.IWmsCreHousingCheckService#cardToBack(com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月21日 baisong 创建方法
     */
    @Override
    @Transactional
    public String cardToBack(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, UserBean user)
    {
        String resStr = "success";
	    Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
        approveHouseWorkFlowVO.setUserId(user.getUserId().toString());
        // 判断是否走流程
        if (approveHouseWorkFlowVO != null && approveHouseWorkFlowVO.getPass() != null)
        {
            // 走流程
            Map<String, Object> flowMap = wmsLoanWorkFlowService.publicApprovalStatus(approveHouseWorkFlowVO);
            // 判断流程是否正常
            if (flowMap != null && flowMap.get("result") != null && "statusError".equals(flowMap.get("result").toString()))
            {
                return flowMap.get("result").toString();
            }
        }
	    //保存主表作废信息
	    WmsCreCreditHead hbean=new WmsCreCreditHead();
	    hbean.setWms_cre_credit_head_id(Integer.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()));
	    hbean.setNullify_type(approveHouseWorkFlowVO.getDebtkey());//房贷单据作废  5 贷款终审  6--合同 7--公证    8--他项   9--放款申请   10--放款审批   11--放款确认
	    hbean.setNullify_user_name(user.getRealname());
	    hbean.setNullify_user_id(user.getUserId());
	    hbean.setNullify_timestamp(sysTime);
	    hbean.setNullify_reason(approveHouseWorkFlowVO.getAdvice());//
	    wmscrecreditheaddao.update(hbean);//更新贷款主表
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("is_review_back", "1");
        paramMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
        wmscrecreditheaddao.updateRecord(paramMap);
        // 新流程发送短信--房产核查节点
        if ("FCHC".equals(approveHouseWorkFlowVO.getDebtkey()))
        {
            //查询客户姓名
            Map<String, Object> customerChangeParamMap = new HashMap<String, Object>();
            customerChangeParamMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            customerChangeParamMap.put("is_major", "1");
            customerChangeParamMap.put("enable_flag", "1");
            List<Map<String, Object>> customerChangeList = this.wmsCreCreditLineCustomerChangeHeadDao.search(customerChangeParamMap);
            //调用流程
	        WmsCreCreditHead wmsCreCreditHead = wmscrecreditheaddao.get(Integer.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()));//查询单据信息
            //调用发送信息的接口出现异常 不会影响正常流程  
            try{
                if(customerChangeList != null && customerChangeList.size() == 1) {
                    //调用方法map
                    Map<String, Object> sendMap = new HashMap<String, Object>();
                    //参数map
                    Map<String, String> paramMap1 = new HashMap<String, String>();
                    //参数map
                    Map<String, String> msg_extras = new HashMap<String, String>();
                    // 管理部经理
                    sendMap.put("role_value", "1");// WorkflowRoleHelp.HOUSE_GLBJL
                    // 根据菜单查询人员
                    sendMap.put("menu_url", WmsHelp.MENU_URL_ZSSP_LIST);// 终审审批ual
                    sendMap.put("salesman_dept_id", wmsCreCreditHead.getSalesman_dept_id());// 终审审批ual

                    paramMap1.put("bill_code", wmsCreCreditHead.getBill_code());
                    paramMap1.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                    paramMap1.put("city", wmsCreCreditHead.getCity());
                    //激光推送
                    sendMap.put("quart_message","1");//消息附加参数
                    //如果是消息中心
    	            sendMap.put("message_center","1");
    	          
    	            msg_extras.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());//添加参数
    	            sendMap.put("msg_extras", msg_extras);//消息附加参数  
    	            sendMap.put("msg_code", "20009");//消息编码
    	            sendMap.put("msg_code_role", "20009");//消息编码--流程角色
    	            
    	            paramMap1.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());
    	            paramMap1.put("bill_status", wmsCreCreditHead.getBill_status());
    	            paramMap1.put("create_user_id", user.getUserId().toString());
    	            paramMap1.put("create_user_name", user.getRealname());
    	            sendMap.put("msg_map", paramMap1);//极光推送和消息中心的消息内容参数
    	            
                    this.wmsCreCreditHeadService.getUserAndSendInfo(sendMap);
                }
            }catch (Exception e){
            	e.printStackTrace();        
            }
        }
        return resStr;
    }
    @Override
    @Transactional
    public String cardToBackCar(WmsCarLoanWorkFlowVO wVo, UserBean user)
    {
        String resStr = "success";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("is_review_back", "1");
        paramMap.put("wms_cre_credit_head_id", wVo.getWms_cre_credit_head_id());
        wmscrecreditheaddao.updateRecord(paramMap);
        wVo.setUserId(user.getUserId().toString());
        wVo.setPass("supply");//贷前推荐
        //carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
        resStr = carLoanWorkFlowService.carLoanApprovalProcess(wVo,"4");// 流程处理
        return resStr;
    }
    
    /**
     * Description : 发送房产评估单基本信息详细信息
     */
    @Override
    @Transactional
    public WmsCreHousingCheckSearchBeanVO sendHouseAssessmentBasicInfoOne(WmsCreHousingCheckSearchBeanVO queryInfo) {
    	String sendHouseAssessmentBasicInfoOneJson = queryInfo.getSendHouseAssessmentBasicInfoOneJson();
    	Map<String, String> sendHouseAssessmentBasicInfoOneMap = new HashMap<String, String>();
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		java.sql.Timestamp now_timestamp = new java.sql.Timestamp(System.currentTimeMillis());
    		sendHouseAssessmentBasicInfoOneMap = mapper.readValue(sendHouseAssessmentBasicInfoOneJson, new TypeReference<Map<String, String>>(){});
    		if(sendHouseAssessmentBasicInfoOneMap != null) {
    			//判断房产评估表是否有记录：有则更新无则新增
    			Map<String, Object> paramMap = new HashMap<String, Object>();
    			paramMap.put("wms_cre_credit_head_id", sendHouseAssessmentBasicInfoOneMap.get("wms_cre_credit_head_id"));
    			paramMap.put("enable_flag", "1");
    			int count = this.wmscrehousingcheckDao.findCount(paramMap);
    			
    			Integer user_id = StringUtils.isEmpty(sendHouseAssessmentBasicInfoOneMap.get("user_id")) ? null : Integer.parseInt(sendHouseAssessmentBasicInfoOneMap.get("user_id"));
    			
    			WmsCreHousingCheck bean = new WmsCreHousingCheck();
    			bean.setWms_cre_credit_head_id(Integer.parseInt(sendHouseAssessmentBasicInfoOneMap.get("wms_cre_credit_head_id").toString()));
    			bean.setTotal_floor(StringUtils.isEmpty(sendHouseAssessmentBasicInfoOneMap.get("total_floor")) ? null : Integer.parseInt(sendHouseAssessmentBasicInfoOneMap.get("total_floor")));
    			bean.setHouse_layer(sendHouseAssessmentBasicInfoOneMap.get("house_layer"));
    			bean.setIs_mortgage(sendHouseAssessmentBasicInfoOneMap.get("is_mortgage"));
    			bean.setHouse_lighting(sendHouseAssessmentBasicInfoOneMap.get("house_lighting"));
    			bean.setHousing_category(sendHouseAssessmentBasicInfoOneMap.get("housing_category"));
    			
    			bean.setHouse_address(sendHouseAssessmentBasicInfoOneMap.get("house_address_more"));
                bean.setHouse_building_area(StringUtils.isEmpty(sendHouseAssessmentBasicInfoOneMap.get("house_building_area")) ? null : new Double(sendHouseAssessmentBasicInfoOneMap.get("house_building_area")));
                bean.setCommunity_name(sendHouseAssessmentBasicInfoOneMap.get("community_name"));
    			
    			bean.setDecoration_Standard(StringUtils.isEmpty(sendHouseAssessmentBasicInfoOneMap.get("decoration_Standard")) ? null : Integer.parseInt(sendHouseAssessmentBasicInfoOneMap.get("decoration_Standard")));
    			bean.setHousing_pattern(StringUtils.isEmpty(sendHouseAssessmentBasicInfoOneMap.get("housing_pattern")) ? null : Integer.parseInt(sendHouseAssessmentBasicInfoOneMap.get("housing_pattern")));
    			bean.setHouse_usage(StringUtils.isEmpty(sendHouseAssessmentBasicInfoOneMap.get("house_usage")) ? null : Integer.parseInt(sendHouseAssessmentBasicInfoOneMap.get("house_usage")));
    			bean.setHouse_occupancy_rate(sendHouseAssessmentBasicInfoOneMap.get("house_occupancy_rate"));
    			bean.setIs_active(sendHouseAssessmentBasicInfoOneMap.get("is_active"));
    			bean.setMarital_status(sendHouseAssessmentBasicInfoOneMap.get("marital_status"));
    			bean.setOnline_fold(StringUtils.isEmpty(sendHouseAssessmentBasicInfoOneMap.get("online_fold")) ? null : new Double(sendHouseAssessmentBasicInfoOneMap.get("online_fold")));
    			bean.setRental_price(StringUtils.isEmpty(sendHouseAssessmentBasicInfoOneMap.get("rental_price")) ? null : new Double(sendHouseAssessmentBasicInfoOneMap.get("rental_price")));
    			bean.setHouse_transaction_price(StringUtils.isEmpty(sendHouseAssessmentBasicInfoOneMap.get("house_transaction_price")) ? null : new Double(sendHouseAssessmentBasicInfoOneMap.get("house_transaction_price")));
    			bean.setLast_update_user_id(user_id);
    			bean.setLast_update_timestamp(now_timestamp);
    			bean.setEnable_flag("1");
    			bean.setHouse_buy_date(sendHouseAssessmentBasicInfoOneMap.get("house_buy_date"));
    			bean.setBuilding_age(StringUtils.isEmpty(sendHouseAssessmentBasicInfoOneMap.get("house_age")) ? null : Integer.parseInt(sendHouseAssessmentBasicInfoOneMap.get("house_age")));
    			
    			if(count == 0) {
    				bean.setCreate_user_id(user_id);
    				bean.setCreate_user_name(sendHouseAssessmentBasicInfoOneMap.get("user_name"));
    				bean.setCreate_timestamp(now_timestamp);
    				this.wmscrehousingcheckDao.save(bean);
    			} else {
    				this.wmscrehousingcheckDao.update(bean);
    			}
    			
    			//2017.6.6王宇需求：房产核查时修改房产地址、小区名称、房产面积(wms_cre_housing_check)把之前业务员填写的给覆盖(wms_cre_customer_change_line_houseinfo)
    			Map<String, Object> houseChangeMap = new HashMap<String, Object>();
    			houseChangeMap.put("wms_cre_credit_head_id", sendHouseAssessmentBasicInfoOneMap.get("wms_cre_credit_head_id"));
    			houseChangeMap.put("is_major", "1");
    			houseChangeMap.put("house_building_area", StringUtils.isEmpty(sendHouseAssessmentBasicInfoOneMap.get("house_building_area")) ? null : new Double(sendHouseAssessmentBasicInfoOneMap.get("house_building_area")));
    			houseChangeMap.put("house_address_more", sendHouseAssessmentBasicInfoOneMap.get("house_address_more"));
    			houseChangeMap.put("community_name", sendHouseAssessmentBasicInfoOneMap.get("community_name"));
    			houseChangeMap.put("house_buy_date", sendHouseAssessmentBasicInfoOneMap.get("house_buy_date"));
    			houseChangeMap.put("house_age", sendHouseAssessmentBasicInfoOneMap.get("house_age"));
    			//更新字段不都为空
    			if(
			        StringUtils.isNotEmpty(sendHouseAssessmentBasicInfoOneMap.get("house_building_area")) && 
			        StringUtils.isNotEmpty(sendHouseAssessmentBasicInfoOneMap.get("house_address_more")) && 
			        StringUtils.isNotEmpty(sendHouseAssessmentBasicInfoOneMap.get("community_name")) && 
			        StringUtils.isNotEmpty(sendHouseAssessmentBasicInfoOneMap.get("house_buy_date")) && 
			        StringUtils.isNotEmpty(sendHouseAssessmentBasicInfoOneMap.get("house_age"))
    			) {
    			    wmsCreCustomerChangeLineHouseinfoDao.updateforBLTwo(houseChangeMap);
    			}
    		}
    	} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return queryInfo;
    }
    
    /**
     * Description : 发送房产评估单房产信息详细信息
     */
    @Override
    @Transactional
    public WmsCreHousingCheckSearchBeanVO sendHouseAssessmentBasicInfoTwo(WmsCreHousingCheckSearchBeanVO queryInfo) {
    	String sendHouseAssessmentBasicInfoTwoJson = queryInfo.getSendHouseAssessmentBasicInfoTwoJson();
    	Map<String, String> sendHouseAssessmentBasicInfoTwoMap = new HashMap<String, String>();
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		java.sql.Timestamp now_timestamp = new java.sql.Timestamp(System.currentTimeMillis());
    		sendHouseAssessmentBasicInfoTwoMap = mapper.readValue(sendHouseAssessmentBasicInfoTwoJson, new TypeReference<Map<String, String>>(){});
    		if(sendHouseAssessmentBasicInfoTwoMap != null) {
    			//判断房产评估表是否有记录：有则更新无则新增
    			Map<String, Object> paramMap = new HashMap<String, Object>();
    			paramMap.put("wms_cre_credit_head_id", sendHouseAssessmentBasicInfoTwoMap.get("wms_cre_credit_head_id"));
    			paramMap.put("enable_flag", "1");
    			int count = this.wmscrehousingcheckDao.findCount(paramMap);
    			
    			Integer user_id = StringUtils.isEmpty(sendHouseAssessmentBasicInfoTwoMap.get("user_id")) ? null : Integer.parseInt(sendHouseAssessmentBasicInfoTwoMap.get("user_id"));
    			
    			WmsCreHousingCheck bean = new WmsCreHousingCheck();
    			bean.setWms_cre_credit_head_id(Integer.parseInt(sendHouseAssessmentBasicInfoTwoMap.get("wms_cre_credit_head_id")));
    			bean.setHousing_towards(sendHouseAssessmentBasicInfoTwoMap.get("housing_towards"));
    			bean.setResidential_manage(sendHouseAssessmentBasicInfoTwoMap.get("residential_manage"));
    			bean.setFacilities(sendHouseAssessmentBasicInfoTwoMap.get("facilities"));
    			bean.setCo_habitation(sendHouseAssessmentBasicInfoTwoMap.get("co_habitation"));
    			bean.setResidential_environ(sendHouseAssessmentBasicInfoTwoMap.get("residential_environ"));
    			bean.setHouse_cleanliness(sendHouseAssessmentBasicInfoTwoMap.get("house_cleanliness"));
    			bean.setRemark(sendHouseAssessmentBasicInfoTwoMap.get("remark"));
    			bean.setLast_update_user_id(user_id);
    			bean.setLast_update_timestamp(now_timestamp);
    			bean.setEnable_flag("1");
    			
    			if(count == 0) {
    				bean.setCreate_user_id(user_id);
    				bean.setCreate_user_name(sendHouseAssessmentBasicInfoTwoMap.get("user_name"));
    				bean.setCreate_timestamp(now_timestamp);
    				this.wmscrehousingcheckDao.save(bean);
    			} else {
    				this.wmscrehousingcheckDao.update(bean);
    			}
    		}
    	} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return queryInfo;
    }
    
    
    
}
