package com.zx.emanage.loancheck.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.cremanage.persist.WmsCreCreditAttDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.service.IWmsCreditWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineAccuFundDao;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineCallListDao;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineCompanyConditionDao;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineCompanyConditionInvestorDao;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineCourtCaseStatusDao;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineCustomerInfoDao;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineHouseRecordDao;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineMediInsuDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineCustomerInfoService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineCompanyConditionVO;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineCustomerInfoSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditAtt;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineAccuFund;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCallList;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCompanyCondition;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCompanyConditionInvestor;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCourtCaseStatus;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerInfo;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineHouseRecord;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineMediInsu;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlinecustomerinfoService")
public class WmsCreCreditLineCustomerInfoServiceImpl implements IWmsCreCreditLineCustomerInfoService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineCustomerInfoServiceImpl.class);

    @Autowired
    private WmsCreCreditHeadDao wmscrecreditheaddao;// 信用贷款主表

    @Autowired
    private WmsCreCreditAttDao wmsCreCreditAttDao;// 附件存储表

    @Autowired
    private WmsCreCreditLineCustomerInfoDao wmscrecreditlinecustomerinfoDao;// 客户基本信息

    @Autowired
    private WmsCreCreditLineCompanyConditionDao wmscrecreditLinecompanyconditionDao;// 获取企业基本情况

    @Autowired
    private WmsCreCreditLineHouseRecordDao wmsCreCreditLineHouseRecordDao;// 房产调档档案

    @Autowired
    private WmsCreCreditLineCourtCaseStatusDao wmscrecreditLinecourtcasectatusDao;// 法院网案件执行状态

    @Autowired
    private WmsCreCreditLineCompanyConditionInvestorDao wmscrecreditlinecompanyconditioninvestorDao;// 获取企业基本情况-投资人信息

    @Autowired
    private IWmsCreditWorkFlowService creditWorkFlowService;// 信用贷款流程

    @Autowired
    private WmsCreCreditLineCallListDao wmsCreCreditLineCallListDao; // 通话

    @Autowired
    private WmsCreCreditLineMediInsuDao wmsCreCreditLineMediInsuDao;// 医保

    @Autowired
    private WmsCreCreditLineAccuFundDao wmsCreCreditLineAccuFundDao;// 公积金

    @Autowired
    private WmsCreCreditLineCustomerChangeHeadDao wmsCreCreditLineCustomerChangeHeadDao;// 用户变更表Dao

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCustomerInfoSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlinecustomerinfoDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCustomerInfoSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlinecustomerinfoDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlinecustomerinfoDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineCustomerInfo getInfoByPK(Integer wms_cre_credit_line_customer_info_id)
    {
        return wmscrecreditlinecustomerinfoDao.get(wms_cre_credit_line_customer_info_id);
    }

    /**
     * 实现信息组审批
     * 
     * @param creCreditCustomerInfo 客户信息实体
     * @param creCreditCompanyCondition 企业基本情况信息实体
     * @param creCreditLineHouseRecord 房产档案记录
     * @param paramCsinfo 法院网案件执行状态
     * @param paramTelinfo 通讯记录信息
     * @param fileArrTelPhone 通讯记录附件信息
     * @param paramMedicareinfo 医保信息
     * @param fileArrMedicare 医保上传附件信息
     * @param paramReserveinfo 公积金信息
     * @param fileArrReserve 公积金上传附件信息
     * @param bainfoDetailListStr 企业基本信息集合
     * @param approveWorkFlowVO 工作流公共类
     * @param telsId 电话通讯录信息
     * @return
     */
    @Override
    @Transactional
    public String save(WmsCreCreditLineCustomerInfo creCreditCustomerInfo,
                       WmsCreCreditLineCompanyCondition creCreditCompanyCondition,
                       WmsCreCreditLineHouseRecord creCreditLineHouseRecord, String paramCsinfo, String paramTelinfo,
                       String fileArrTelPhone, String paramMedicareinfo, String fileArrMedicare,
                       String paramReserveinfo, String fileArrReserve, String bainfoAndDetaiList,
                       WmsCreditWorkFlowVO approveWorkFlowVO, UserBean user, String telsId)
    {
        String resStr = "success";
        int ret = 0;
        // 将前台json数据类型转换成对应的object
        List<WmsCreCreditLineCallList> wmsCreCreditLineCallLists = JsonUtil.jsonArrayToList(paramTelinfo,
                                                                                            WmsCreCreditLineCallList.class);// 通话信息获取
        List<WmsCreCreditAtt> wmsCreCreditAttsfileArrTelPhones = JsonUtil.jsonArrayToList(fileArrTelPhone,
                                                                                          WmsCreCreditAtt.class); // 通讯记录附件的存储

        List<WmsCreCreditLineMediInsu> WmsCreCreditLineMediInsus = JsonUtil.jsonArrayToList(paramMedicareinfo,
                                                                                            WmsCreCreditLineMediInsu.class);// 医保信息获取
        List<WmsCreCreditAtt> wmsCreCreditAttsfileArrMedicares = JsonUtil.jsonArrayToList(fileArrMedicare,
                                                                                          WmsCreCreditAtt.class); // 医保记录附件的存储

        List<WmsCreCreditLineAccuFund> WmsCreCreditLineAccuFunds = JsonUtil.jsonArrayToList(paramReserveinfo,
                                                                                            WmsCreCreditLineAccuFund.class); // 公积金记录附件的存储
        List<WmsCreCreditAtt> wmsCreCreditAttsfileArrReserves = JsonUtil.jsonArrayToList(fileArrReserve,
                                                                                         WmsCreCreditAtt.class); // 公积金记录附件的存储

        List<WmsCreCreditLineCourtCaseStatus> csins = JsonUtil.jsonArrayToList(paramCsinfo,
                                                                               WmsCreCreditLineCourtCaseStatus.class);// 法院网案件执行状态

        List<WmsCreCreditLineCompanyConditionVO> bainfoDetailListStrs = JsonUtil.jsonArrayToList(bainfoAndDetaiList,
                                                                                                 WmsCreCreditLineCompanyConditionVO.class);// 获取企业基本情况和投资人信息

        List<Integer> telsIds = JsonUtil.jsonArrayToList(telsId, Integer.class);// 获取电话对应的变更id值
        // 信息审批组--->客户信息保存
        creCreditCustomerInfo.setCreate_user_id(user.getUserId());// 保存创建人信息
        creCreditCustomerInfo.setCreate_user_name(user.getRealname());
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());// 保存当前时间
        creCreditCustomerInfo.setCreate_timestamp(sysTime);
        creCreditCustomerInfo.setLast_update_user_id(user.getUserId());
        creCreditCustomerInfo.setLast_update_timestamp(sysTime);
        creCreditCustomerInfo.setEnable_flag("1");
        creCreditCustomerInfo.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());
        ret = wmscrecreditlinecustomerinfoDao.save(creCreditCustomerInfo);

        // 房产调档档案存储
        creCreditLineHouseRecord.setCreate_user_id(user.getUserId());// 保存创建人信息
        creCreditLineHouseRecord.setCreate_user_name(user.getRealname());
        sysTime = new Timestamp(System.currentTimeMillis());// 保存当前时间
        creCreditLineHouseRecord.setCreate_timestamp(sysTime);
        creCreditLineHouseRecord.setLast_update_user_id(user.getUserId());
        creCreditLineHouseRecord.setLast_update_timestamp(sysTime);
        creCreditLineHouseRecord.setEnable_flag("1");
        creCreditLineHouseRecord.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());
        ret = wmsCreCreditLineHouseRecordDao.save(creCreditLineHouseRecord);

        // 通话记录的信息的保存
        for (int i = 0; i < wmsCreCreditLineCallLists.size(); i++)
        {
            WmsCreCreditLineCallList call = new WmsCreCreditLineCallList();
            call.setUser_tel(wmsCreCreditLineCallLists.get(i).getUser_tel());
            call.setCard_open_time(wmsCreCreditLineCallLists.get(i).getCard_open_time());
            call.setOwner_name(wmsCreCreditLineCallLists.get(i).getOwner_name());
            call.setCard_remark(wmsCreCreditLineCallLists.get(i).getCard_remark());
            call.setCreate_user_id(user.getUserId());
            call.setCreate_user_name(user.getRealname());
            call.setCreate_timestamp(sysTime);
            call.setLast_update_user_id(user.getUserId());
            call.setLast_update_timestamp(sysTime);
            call.setEnable_flag("1");
            call.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());
            call.setWms_cre_credit_line_customer_change_head_id(telsIds.get(i));
            ret = wmsCreCreditLineCallListDao.save(call);
        }
        // 通话记录附件信息的存储
        for (WmsCreCreditAtt phone : wmsCreCreditAttsfileArrTelPhones)
        {
            phone.setData_type("2");
            phone.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());
            phone.setCreate_user_id(user.getUserId());
            phone.setCreate_user_name(user.getRealname());
            phone.setCreate_timestamp(sysTime);
            phone.setLast_update_user_id(user.getUserId());
            phone.setLast_update_timestamp(sysTime);
            phone.setEnable_flag("1");
            ret = wmsCreCreditAttDao.save(phone);
        }
        // 医保记录信息的存储
        for (WmsCreCreditLineMediInsu mediInsu : WmsCreCreditLineMediInsus)
        {
            mediInsu.setCreate_user_id(user.getUserId());
            mediInsu.setCreate_user_name(user.getRealname());
            mediInsu.setCreate_timestamp(sysTime);
            mediInsu.setLast_update_user_id(user.getUserId());
            mediInsu.setLast_update_timestamp(sysTime);
            mediInsu.setEnable_flag("1");
            mediInsu.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());
            ret = wmsCreCreditLineMediInsuDao.save(mediInsu);
        }
        // 医保记录附件信息的存储
        for (WmsCreCreditAtt medicares : wmsCreCreditAttsfileArrMedicares)
        {
            medicares.setData_type("4");
            medicares.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());
            medicares.setCreate_user_id(user.getUserId());
            medicares.setCreate_user_name(user.getRealname());
            medicares.setCreate_timestamp(sysTime);
            medicares.setLast_update_user_id(user.getUserId());
            medicares.setLast_update_timestamp(sysTime);
            medicares.setEnable_flag("1");
            ret = wmsCreCreditAttDao.save(medicares);
        }
        // 公积金记录信息的存储
        for (WmsCreCreditLineAccuFund accuFund : WmsCreCreditLineAccuFunds)
        {
            accuFund.setCreate_user_id(user.getUserId());
            accuFund.setCreate_user_name(user.getRealname());
            accuFund.setCreate_timestamp(sysTime);
            accuFund.setLast_update_user_id(user.getUserId());
            accuFund.setLast_update_timestamp(sysTime);
            accuFund.setEnable_flag("1");
            accuFund.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());
            ret = wmsCreCreditLineAccuFundDao.save(accuFund);
        }
        // 公积金记录附件信息的存储
        for (WmsCreCreditAtt reserves : wmsCreCreditAttsfileArrReserves)
        {
            reserves.setData_type("3");
            reserves.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());
            reserves.setCreate_user_id(user.getUserId());
            reserves.setCreate_user_name(user.getRealname());
            reserves.setCreate_timestamp(sysTime);
            reserves.setLast_update_user_id(user.getUserId());
            reserves.setLast_update_timestamp(sysTime);
            reserves.setEnable_flag("1");
            ret = wmsCreCreditAttDao.save(reserves);
        }

        // 企业基本情况----保存多个企业信息和投资人信息的保存* 一对多的关系
        for (WmsCreCreditLineCompanyConditionVO mccompanycondition : bainfoDetailListStrs)
        {
            // 存储企业基本情况
            mccompanycondition.setCreate_user_id(user.getUserId());
            mccompanycondition.setCreate_user_name(user.getRealname());
            mccompanycondition.setCreate_timestamp(sysTime);
            mccompanycondition.setLast_update_user_id(user.getUserId());
            mccompanycondition.setLast_update_timestamp(sysTime);
            mccompanycondition.setEnable_flag("1");
            mccompanycondition.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());
            ret = wmscrecreditLinecompanyconditionDao.save(mccompanycondition);
            // 投资人列表信息
            List<WmsCreCreditLineCompanyConditionInvestor> mccompanyconditionInvestors = JsonUtil.jsonArrayToList(mccompanycondition.getDetaiListStr(),
                                                                                                                  WmsCreCreditLineCompanyConditionInvestor.class);// 投资人信息
            for (WmsCreCreditLineCompanyConditionInvestor mccompanyconditionInvestor : mccompanyconditionInvestors)
            {
                mccompanyconditionInvestor.setCreate_user_id(user.getUserId());
                mccompanyconditionInvestor.setCreate_user_name(user.getRealname());
                mccompanyconditionInvestor.setCreate_timestamp(sysTime);
                mccompanyconditionInvestor.setLast_update_user_id(user.getUserId());
                mccompanyconditionInvestor.setLast_update_timestamp(sysTime);
                mccompanyconditionInvestor.setEnable_flag("1");
                mccompanyconditionInvestor.setWms_cre_credit_line_company_condition_id(mccompanycondition.getWms_cre_credit_line_company_condition_id());
                ret = wmscrecreditlinecompanyconditioninvestorDao.save(mccompanyconditionInvestor);
            }
        }

        // 法院网案件执行状态保存
        for (WmsCreCreditLineCourtCaseStatus c : csins)
        {
            c.setCreate_user_id(user.getUserId());
            c.setCreate_user_name(user.getRealname());
            c.setCreate_timestamp(sysTime);
            c.setLast_update_user_id(user.getUserId());
            c.setLast_update_timestamp(sysTime);
            c.setEnable_flag("1");
            c.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());
            ret = wmscrecreditLinecourtcasectatusDao.save(c);
        }
        // 信贷信息组审批的流程指向
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("info_appro_user_id", user.getUserId());
        params.put("info_appro_user_name", user.getRealname());
        params.put("info_appro_timestamp", sysTime);
        params.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        // 修改信息审批后的结果进行保存
        wmscrecreditheaddao.updateRecord(params);
        // 完成信息审批
        approveWorkFlowVO.setUser_id(user.getUserId());// 保存userid
        creditWorkFlowService.infoTeamCheckApproveWorkFlow(approveWorkFlowVO);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineCustomerInfo bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinecustomerinfoDao.update(bean); // update a record
                                                            // replace columns,
                                                            // nonsupport null
                                                            // val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreCreditLineCustomerInfo() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCreditLineCustomerInfo> getListByEntity(WmsCreCreditLineCustomerInfo queryInfo,
                                                               Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLineCustomerInfo> beanList = wmscrecreditlinecustomerinfoDao.getListByEntity(queryInfo);
        return beanList;
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineCustomerInfoVO
     * @author auto_generator
     */
    @Override
    public WmsCreCreditLineCustomerInfo getInfoByFK(Integer wms_cre_credit_line_customer_info_id)
    {
        return wmscrecreditlinecustomerinfoDao.getByFK(wms_cre_credit_line_customer_info_id);
    }

}
