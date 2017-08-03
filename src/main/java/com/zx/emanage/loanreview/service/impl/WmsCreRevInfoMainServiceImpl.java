package com.zx.emanage.loanreview.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.util.StringUtil;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.vo.GridDataBean;
import com.google.gson.Gson;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditReturnSearchDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineContactDao;
import com.zx.emanage.cremanage.service.IWmsHouseCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsCreditWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.loanreview.persist.WmsCreRevAttDao;
import com.zx.emanage.loanreview.persist.WmsCreRevInfoCarDao;
import com.zx.emanage.loanreview.persist.WmsCreRevInfoCompDao;
import com.zx.emanage.loanreview.persist.WmsCreRevInfoContactDao;
import com.zx.emanage.loanreview.persist.WmsCreRevInfoCourtCaseDao;
import com.zx.emanage.loanreview.persist.WmsCreRevInfoCriminalDao;
import com.zx.emanage.loanreview.persist.WmsCreRevInfoInsuranceDao;
import com.zx.emanage.loanreview.persist.WmsCreRevInfoMainDao;
import com.zx.emanage.loanreview.persist.WmsCreRevInfoModelDao;
import com.zx.emanage.loanreview.service.IWmsCreRevInfoMainService;
import com.zx.emanage.loanreview.vo.WmsCreRevInfoMainSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineContact;
import com.zx.emanage.util.gen.entity.WmsCreRevAtt;
import com.zx.emanage.util.gen.entity.WmsCreRevInfoCar;
import com.zx.emanage.util.gen.entity.WmsCreRevInfoComp;
import com.zx.emanage.util.gen.entity.WmsCreRevInfoContact;
import com.zx.emanage.util.gen.entity.WmsCreRevInfoCourtCase;
import com.zx.emanage.util.gen.entity.WmsCreRevInfoCriminal;
import com.zx.emanage.util.gen.entity.WmsCreRevInfoInsurance;
import com.zx.emanage.util.gen.entity.WmsCreRevInfoMain;
import com.zx.emanage.util.gen.entity.WmsCreRevInfoModel;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevinfomainService")
public class WmsCreRevInfoMainServiceImpl implements IWmsCreRevInfoMainService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevInfoMainServiceImpl.class);

    @Autowired
    private WmsCreRevInfoMainDao wmscrerevinfomainDao;// 信息总体情况

    @Autowired
    private WmsCreRevAttDao wmsCreRevAttDao;// 附件

    @Autowired
    private WmsCreRevInfoCourtCaseDao wmsCreRevInfoCourtCaseDao;// 法院网执行情况

    @Autowired
    private WmsCreRevInfoCriminalDao wmsCreRevInfoCriminalDao;// 犯罪记录

    @Autowired
    private WmsCreRevInfoCarDao wmsCreRevInfoCarDao;// 车辆记录

    @Autowired
    private WmsCreRevInfoInsuranceDao wmsCreRevInfoInsuranceDao;// 保险攻击经

    @Autowired
    private WmsCreRevInfoCompDao wmsCreRevInfoCompDao;// 企业基本情况

    @Autowired
    private WmsCreRevInfoModelDao wmsCreRevInfoModelDao;// 信调重要信息dao

    @Autowired
    private WmsCreCustomerChangeLineContactDao wmsCreCustomerChangeLineContactDao;// 客户变更联系人

    @Autowired
    private WmsCreRevInfoContactDao wmsCreRevInfoContactDao;// 信调联系人信息表

    @Autowired
    private IWmsCreditWorkFlowService creditWorkFlowService;

    @Autowired
    private WmsCreCreditHeadDao wmscrecreditheadDao;

    @Autowired
    private IWmsHouseCreditWorkFlowService houseCreditWorkFlowService;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private WmsCreCreditReturnSearchDao wmsCreCreditReturnSearchDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreRevInfoMainSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrerevinfomainDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreRevInfoMainSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrerevinfomainDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrerevinfomainDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreRevInfoMain getInfoByPK(Integer wms_cre_rev_info_main_id)
    {
        return wmscrerevinfomainDao.get(wms_cre_rev_info_main_id);
    }

    @Override
    @Transactional
    public String saveHouse(WmsCreRevInfoMain bean, UserBean user, String grInfo, String qyInfo,
                            WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, Integer flag)
    {
        String resStr = "temOK";
        int ret = 0;
        // 对个人信息进行解析
        List<Map<String, Object>> grInfoArr = jsonObjList(grInfo);

        for (Map<String, Object> singleGrinfoTab : grInfoArr)
        {
            // 获取主贷人和附带人变更ID
            Integer wms_cre_credit_line_customer_change_head_id = Integer.parseInt(singleGrinfoTab.get("wms_cre_credit_line_customer_change_head_id")
                                                                                                  .toString());
            // 先删除相应数据
            Map<String, Object> wmsCreRevInfoMainMap = new HashMap<>();
            wmsCreRevInfoMainMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevInfoMainMap.put("wms_cre_credit_line_customer_change_head_id",
                                     wms_cre_credit_line_customer_change_head_id);
            wmscrerevinfomainDao.deleteInfo(wmsCreRevInfoMainMap);
            Map<String, Object> ztqkMap = (Map<String, Object>) singleGrinfoTab.get("ztqkJson");// 总体情况和备注
            WmsCreRevInfoMain wmsCreRevInfoMain = toWmsCreRevInfoMainEntity(ztqkMap,
                                                                            Integer.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                                            wms_cre_credit_line_customer_change_head_id,
                                                                            user);
            // 在存储数据
            ret = wmscrerevinfomainDao.save(wmsCreRevInfoMain);
            // 向附件表里面存储附件信息
            List<Map<String, Object>> bxfileArr = (List<Map<String, Object>>) singleGrinfoTab.get("fileArr");// 附件
            // 先删除相应数据
            Map<String, Object> wmsCreRevfileMap = new HashMap<>();
            wmsCreRevfileMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevfileMap.put("wms_cre_credit_line_customer_change_head_id",
                                 wms_cre_credit_line_customer_change_head_id);
            wmsCreRevfileMap.put("data_type", "1");
            wmsCreRevAttDao.deleteInfo(wmsCreRevfileMap);
            for (Map<String, Object> fi : bxfileArr)
            {
                WmsCreRevAtt wmsCreRevfile = toWmsCreRevAttEntity(fi,
                                                                  "1",
                                                                  Integer.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                                  wms_cre_credit_line_customer_change_head_id);
                // 在存储
                ret = wmsCreRevAttDao.save(wmsCreRevfile);
            }
            // 存储个人法院方案件
            List<Map<String, Object>> grfywArr = (List<Map<String, Object>>) singleGrinfoTab.get("info_grfyw");
            // 先删除相应数据
            Map<String, Object> wmsCreRevInfoCourtCaseMap = new HashMap<>();
            wmsCreRevInfoCourtCaseMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevInfoCourtCaseMap.put("wms_cre_credit_line_customer_change_head_id",
                                          wms_cre_credit_line_customer_change_head_id);
            wmsCreRevInfoCourtCaseMap.put("court_case_type", "1");
            wmsCreRevInfoCourtCaseDao.deleteInfo(wmsCreRevInfoCourtCaseMap);
            for (Map<String, Object> fyw : grfywArr)
            {
                WmsCreRevInfoCourtCase wmsCreRevInfoCourtCase = toWmsCreRevInfoCourtCaseEntity(fyw,
                                                                                               Integer.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                                                               wms_cre_credit_line_customer_change_head_id);
                // 在存储
                ret = wmsCreRevInfoCourtCaseDao.save(wmsCreRevInfoCourtCase);
            }
            // 存储个人犯罪
            List<Map<String, Object>> grfzArr = (List<Map<String, Object>>) singleGrinfoTab.get("info_grfz");
            // 先删除相应数据
            Map<String, Object> wmsCreRevInfoCriminalMap = new HashMap<>();
            wmsCreRevInfoCriminalMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevInfoCriminalMap.put("wms_cre_credit_line_customer_change_head_id",
                                         wms_cre_credit_line_customer_change_head_id);
            wmsCreRevInfoCriminalDao.deleteInfo(wmsCreRevInfoCriminalMap);
            for (Map<String, Object> grfz : grfzArr)
            {
                WmsCreRevInfoCriminal wmsCreRevInfoCriminal = toWmsCreRevInfoCriminalEntity(grfz,
                                                                                            Integer.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                                                            wms_cre_credit_line_customer_change_head_id);
                // 在存储
                ret = wmsCreRevInfoCriminalDao.save(wmsCreRevInfoCriminal);
            }
            // 存储个人车辆
            List<Map<String, Object>> grclArr = (List<Map<String, Object>>) singleGrinfoTab.get("info_grcl");
            // 先删除相应数据
            Map<String, Object> wmsCreRevInfoCarMap = new HashMap<>();
            wmsCreRevInfoCarMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevInfoCarMap.put("wms_cre_credit_line_customer_change_head_id",
                                    wms_cre_credit_line_customer_change_head_id);
            wmsCreRevInfoCarDao.deleteInfo(wmsCreRevInfoCarMap);
            for (Map<String, Object> grcl : grclArr)
            {
                WmsCreRevInfoCar wmsCreRevInfoCar = toWmsCreRevInfoCarEntity(grcl,
                                                                             Integer.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                                             wms_cre_credit_line_customer_change_head_id);
                // 在存储
                ret = wmsCreRevInfoCarDao.save(wmsCreRevInfoCar);
            }
            // 存储养老保险
            Map<String, Object> grbxyanglaoArr = (Map<String, Object>) singleGrinfoTab.get("grbxyanglao");
            WmsCreRevInfoInsurance wmsCreRevInfoInsurance1 = toWmsCreRevInfoInsuranceEntity(grbxyanglaoArr,
                                                                                            Integer.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                                                            wms_cre_credit_line_customer_change_head_id);
            // 先删除相应数据
            Map<String, Object> wmsCreRevInfoInsurance1Map = new HashMap<>();
            wmsCreRevInfoInsurance1Map.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevInfoInsurance1Map.put("wms_cre_credit_line_customer_change_head_id",
                                           wms_cre_credit_line_customer_change_head_id);
            wmsCreRevInfoInsurance1Map.put("lnsurance_type", grbxyanglaoArr.get("lnsurance_type"));
            wmsCreRevInfoInsuranceDao.deleteInfo(wmsCreRevInfoInsurance1Map);
            // 在存储
            ret = wmsCreRevInfoInsuranceDao.save(wmsCreRevInfoInsurance1);
            // 存储医疗保险
            Map<String, Object> grbxyiliaoArr = (Map<String, Object>) singleGrinfoTab.get("grbxyiliao");
            WmsCreRevInfoInsurance wmsCreRevInfoInsurance2 = toWmsCreRevInfoInsuranceEntity(grbxyiliaoArr,
                                                                                            Integer.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                                                            wms_cre_credit_line_customer_change_head_id);
            // 先删除相应数据
            Map<String, Object> wmsCreRevInfoInsurance2Map = new HashMap<>();
            wmsCreRevInfoInsurance2Map.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevInfoInsurance2Map.put("wms_cre_credit_line_customer_change_head_id",
                                           wms_cre_credit_line_customer_change_head_id);
            wmsCreRevInfoInsurance2Map.put("lnsurance_type", grbxyiliaoArr.get("lnsurance_type"));
            wmsCreRevInfoInsuranceDao.deleteInfo(wmsCreRevInfoInsurance2Map);
            // 在存储
            ret = wmsCreRevInfoInsuranceDao.save(wmsCreRevInfoInsurance2);
            // 存储公积金
            Map<String, Object> grbxgongjjArr = (Map<String, Object>) singleGrinfoTab.get("grbxgongjj");
            WmsCreRevInfoInsurance wmsCreRevInfoInsurance3 = toWmsCreRevInfoInsuranceEntity(grbxgongjjArr,
                                                                                            Integer.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                                                            wms_cre_credit_line_customer_change_head_id);
            // 先删除相应数据
            Map<String, Object> wmsCreRevInfoInsurance3Map = new HashMap<>();
            wmsCreRevInfoInsurance3Map.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevInfoInsurance3Map.put("wms_cre_credit_line_customer_change_head_id",
                                           wms_cre_credit_line_customer_change_head_id);
            wmsCreRevInfoInsurance3Map.put("lnsurance_type", grbxgongjjArr.get("lnsurance_type"));
            wmsCreRevInfoInsuranceDao.deleteInfo(wmsCreRevInfoInsurance3Map);
            // 在存储
            ret = wmsCreRevInfoInsuranceDao.save(wmsCreRevInfoInsurance3);
            // 存储通话记录其他说明
            List<WmsCreCustomerChangeLineContact> gridtelphonecountArr = JsonUtil.jsonArrayToList(singleGrinfoTab.get("grid_telphone_count")
                                                                                                                 .toString(),
                                                                                                  WmsCreCustomerChangeLineContact.class);
            for (WmsCreCustomerChangeLineContact gridtel : gridtelphonecountArr)
            {
                WmsCreCustomerChangeLineContact wmsCreCustomerChangeLineContact = new WmsCreCustomerChangeLineContact();
                // 主键1
                wmsCreCustomerChangeLineContact.setWms_cre_customer_change_line_contact_id(gridtel.getWms_cre_customer_change_line_contact_id());
                // 结果1audit_result1
                if (gridtel.getPhone1_1() != null && gridtel.getPhone2_1() != null)
                {
                    wmsCreCustomerChangeLineContact.setAudit_result1(String.valueOf(Integer.valueOf(gridtel.getPhone1_1())
                                                                                    + Integer.valueOf(gridtel.getPhone2_1())));
                }
                else if (gridtel.getPhone1_1() != null)
                {
                    wmsCreCustomerChangeLineContact.setAudit_result1(String.valueOf(gridtel.getPhone1_1()));
                }
                else if (gridtel.getPhone2_1() != null)
                {
                    wmsCreCustomerChangeLineContact.setAudit_result1(String.valueOf(gridtel.getPhone2_1()));
                }
                // 结果2audit_result2
                if (gridtel.getPhone1_2() != null && gridtel.getPhone2_2() != null)
                {
                    wmsCreCustomerChangeLineContact.setAudit_result2(String.valueOf(Integer.valueOf(gridtel.getPhone1_2())
                                                                                    + Integer.valueOf(gridtel.getPhone2_2())));
                }
                else if (gridtel.getPhone1_2() != null)
                {
                    wmsCreCustomerChangeLineContact.setAudit_result2(String.valueOf(gridtel.getPhone1_2()));
                }
                else if (gridtel.getPhone2_2() != null)
                {
                    wmsCreCustomerChangeLineContact.setAudit_result2(String.valueOf(gridtel.getPhone2_2()));
                }
                // 结果3audit_result3
                if (gridtel.getPhone1_3() != null && gridtel.getPhone2_3() != null)
                {
                    wmsCreCustomerChangeLineContact.setAudit_result3(String.valueOf(Integer.valueOf(gridtel.getPhone1_3())
                                                                                    + Integer.valueOf(gridtel.getPhone2_3())));
                }
                else if (gridtel.getPhone1_3() != null)
                {
                    wmsCreCustomerChangeLineContact.setAudit_result3(String.valueOf(gridtel.getPhone1_3()));
                }
                else if (gridtel.getPhone2_3() != null)
                {
                    wmsCreCustomerChangeLineContact.setAudit_result3(String.valueOf(gridtel.getPhone2_3()));
                }
                // 1号联系次数第一个月phone1_1
                wmsCreCustomerChangeLineContact.setPhone1_1(gridtel.getPhone1_1());
                // 1号联系次数第二个月phone1_2
                wmsCreCustomerChangeLineContact.setPhone1_2(gridtel.getPhone1_2());
                // 1号联系次数第三个月phone1_3
                wmsCreCustomerChangeLineContact.setPhone1_3(gridtel.getPhone1_3());
                // 2号联系次数第一个月phone2_1
                wmsCreCustomerChangeLineContact.setPhone2_1(gridtel.getPhone2_1());
                // 2号联系次数第二个月phone2_2
                wmsCreCustomerChangeLineContact.setPhone2_2(gridtel.getPhone2_2());
                // 2号联系次数第三个月phone2_3
                wmsCreCustomerChangeLineContact.setPhone2_3(gridtel.getPhone2_3());
                // 电话小号contact_mobile_phone_short
                wmsCreCustomerChangeLineContact.setContact_mobile_phone_short(gridtel.getContact_mobile_phone_short());
                ret = wmsCreCustomerChangeLineContactDao.update(wmsCreCustomerChangeLineContact);
            }
        }
        // 对企业信息进行解析
        List<Map<String, Object>> qyInfoArr = jsonObjList(qyInfo);
        for (Map<String, Object> singleQyinfoTab : qyInfoArr)
        {
            Integer wms_cre_credit_line_customer_change_head_id = Integer.parseInt(singleQyinfoTab.get("wms_cre_credit_line_customer_change_head_id")
                                                                                                  .toString());
            Map<String, Object> ztqkMap = (Map<String, Object>) singleQyinfoTab.get("ztqkJson");// 总体情况和备注
            WmsCreRevInfoMain wmsCreRevInfoMain = updateWmsCreRevInfoMainEntity(ztqkMap,
                                                                                Integer.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                                                wms_cre_credit_line_customer_change_head_id);
            ret = wmscrerevinfomainDao.updateQyFyw(wmsCreRevInfoMain);
            // 企业基本信息的存储
            List<Map<String, Object>> qybaseInfoArr = (List<Map<String, Object>>) singleQyinfoTab.get("qybaseInfo");
            Map<String, Object> wmsCreRevInfoCompMap = new HashMap<>();
            wmsCreRevInfoCompMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevInfoCompMap.put("wms_cre_credit_line_customer_change_head_id",
                                     wms_cre_credit_line_customer_change_head_id);
            wmsCreRevInfoCompDao.deleteInfo(wmsCreRevInfoCompMap);
            for (Map<String, Object> qybaseInfo : qybaseInfoArr)
            {
                WmsCreRevInfoComp wmsCreRevInfoComp = toWmsCreRevInfoCompEntity(qybaseInfo,
                                                                                Integer.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                                                wms_cre_credit_line_customer_change_head_id);
                ret = wmsCreRevInfoCompDao.save(wmsCreRevInfoComp);
            }
            // 企业法院网信息的存储info_qyfyw
            List<Map<String, Object>> info_qyfywArr = (List<Map<String, Object>>) singleQyinfoTab.get("info_qyfyw");
            Map<String, Object> wmsCreRevInfoCourtCaseMap = new HashMap<>();
            wmsCreRevInfoCourtCaseMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevInfoCourtCaseMap.put("wms_cre_credit_line_customer_change_head_id",
                                          wms_cre_credit_line_customer_change_head_id);
            wmsCreRevInfoCourtCaseMap.put("court_case_type", "2");
            wmsCreRevInfoCourtCaseDao.deleteInfo(wmsCreRevInfoCourtCaseMap);
            for (Map<String, Object> info_qyfyw : info_qyfywArr)
            {
                WmsCreRevInfoCourtCase wmsCreRevInfoCourtCase = toWmsCreRevInfoCourtCaseEntity(info_qyfyw,
                                                                                               Integer.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                                                               wms_cre_credit_line_customer_change_head_id);
                ret = wmsCreRevInfoCourtCaseDao.save(wmsCreRevInfoCourtCase);
            }
        }
        if (ret == 0)
        {
            resStr = "error";
        }
        if (flag == 1)
        {
            resStr = "success";
            // 改变房产信息组的审批状态
            Timestamp sysTime = new Timestamp(System.currentTimeMillis());
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("info_appro_user_id", user.getUserId());
            params.put("info_appro_user_name", user.getRealname());
            params.put("info_appro_timestamp", sysTime);
            params.put("info_appro_result", "true".equals(approveHouseWorkFlowVO.getPass()) ? "1" : "0");
            params.put("info_appro_advice", approveHouseWorkFlowVO.getAdvice());
            params.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            wmscrecreditheadDao.updateRecord(params);
            // 调用信调组审批状态
            approveHouseWorkFlowVO.setUserId(String.valueOf(user.getUserId()));
            resStr = houseCreditWorkFlowService.creInfoTeamHouseCheckApproveWorkFlow(approveHouseWorkFlowVO);
        }
        return resStr;
    }

    @Override
    @Transactional
    public String save(WmsCreRevInfoMain bean, UserBean user, String grInfo, String qyInfo,
                       WmsCreditWorkFlowVO approveWorkFlowVO, Integer flag, String InfoImData)
    {
        String resStr = "temOK";
        int ret = 0;
        // 对个人信息进行解析
        List<Map<String, Object>> grInfoArr = jsonObjList(grInfo);

        for (Map<String, Object> singleGrinfoTab : grInfoArr)
        {
            // 获取主贷人和附带人变更ID
            Integer wms_cre_credit_line_customer_change_head_id = Integer.parseInt(singleGrinfoTab.get("wms_cre_credit_line_customer_change_head_id")
                                                                                                  .toString());
            // 先删除相应数据
            Map<String, Object> wmsCreRevInfoMainMap = new HashMap<>();
            wmsCreRevInfoMainMap.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevInfoMainMap.put("wms_cre_credit_line_customer_change_head_id",
                                     wms_cre_credit_line_customer_change_head_id);
            wmscrerevinfomainDao.deleteInfo(wmsCreRevInfoMainMap);
            Map<String, Object> ztqkMap = (Map<String, Object>) singleGrinfoTab.get("ztqkJson");// 总体情况和备注
            WmsCreRevInfoMain wmsCreRevInfoMain = toWmsCreRevInfoMainEntity(ztqkMap,
                                                                            approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                                            wms_cre_credit_line_customer_change_head_id,
                                                                            user);
            // 在存储数据
            ret = wmscrerevinfomainDao.save(wmsCreRevInfoMain);
            // 向附件表里面存储附件信息
            List<Map<String, Object>> bxfileArr = (List<Map<String, Object>>) singleGrinfoTab.get("fileArr");// 附件
            // 先删除相应数据
            Map<String, Object> wmsCreRevfileMap = new HashMap<>();
            wmsCreRevfileMap.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevfileMap.put("wms_cre_credit_line_customer_change_head_id",
                                 wms_cre_credit_line_customer_change_head_id);
            wmsCreRevfileMap.put("data_type", "1");
            wmsCreRevAttDao.deleteInfo(wmsCreRevfileMap);
            for (Map<String, Object> fi : bxfileArr)
            {
                WmsCreRevAtt wmsCreRevfile = toWmsCreRevAttEntity(fi, "1",
                                                                  approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                                  wms_cre_credit_line_customer_change_head_id);
                // 在存储
                ret = wmsCreRevAttDao.save(wmsCreRevfile);
            }
            // 存储个人法院方案件
            List<Map<String, Object>> grfywArr = (List<Map<String, Object>>) singleGrinfoTab.get("info_grfyw");
            // 先删除相应数据
            Map<String, Object> wmsCreRevInfoCourtCaseMap = new HashMap<>();
            wmsCreRevInfoCourtCaseMap.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevInfoCourtCaseMap.put("wms_cre_credit_line_customer_change_head_id",
                                          wms_cre_credit_line_customer_change_head_id);
            wmsCreRevInfoCourtCaseMap.put("court_case_type", "1");
            wmsCreRevInfoCourtCaseDao.deleteInfo(wmsCreRevInfoCourtCaseMap);
            for (Map<String, Object> fyw : grfywArr)
            {
                WmsCreRevInfoCourtCase wmsCreRevInfoCourtCase = toWmsCreRevInfoCourtCaseEntity(fyw,
                                                                                               approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                                                               wms_cre_credit_line_customer_change_head_id);
                // 在存储
                ret = wmsCreRevInfoCourtCaseDao.save(wmsCreRevInfoCourtCase);
            }
            // 存储个人犯罪
            List<Map<String, Object>> grfzArr = (List<Map<String, Object>>) singleGrinfoTab.get("info_grfz");
            // 先删除相应数据
            Map<String, Object> wmsCreRevInfoCriminalMap = new HashMap<>();
            wmsCreRevInfoCriminalMap.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevInfoCriminalMap.put("wms_cre_credit_line_customer_change_head_id",
                                         wms_cre_credit_line_customer_change_head_id);
            wmsCreRevInfoCriminalDao.deleteInfo(wmsCreRevInfoCriminalMap);
            for (Map<String, Object> grfz : grfzArr)
            {
                WmsCreRevInfoCriminal wmsCreRevInfoCriminal = toWmsCreRevInfoCriminalEntity(grfz,
                                                                                            approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                                                            wms_cre_credit_line_customer_change_head_id);
                // 在存储
                ret = wmsCreRevInfoCriminalDao.save(wmsCreRevInfoCriminal);
            }
            // 存储个人车辆
            List<Map<String, Object>> grclArr = (List<Map<String, Object>>) singleGrinfoTab.get("info_grcl");
            // 先删除相应数据
            Map<String, Object> wmsCreRevInfoCarMap = new HashMap<>();
            wmsCreRevInfoCarMap.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevInfoCarMap.put("wms_cre_credit_line_customer_change_head_id",
                                    wms_cre_credit_line_customer_change_head_id);
            wmsCreRevInfoCarDao.deleteInfo(wmsCreRevInfoCarMap);
            for (Map<String, Object> grcl : grclArr)
            {
                WmsCreRevInfoCar wmsCreRevInfoCar = toWmsCreRevInfoCarEntity(grcl,
                                                                             approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                                             wms_cre_credit_line_customer_change_head_id);
                // 在存储
                ret = wmsCreRevInfoCarDao.save(wmsCreRevInfoCar);
            }
            // 存储养老保险
            Map<String, Object> grbxyanglaoArr = (Map<String, Object>) singleGrinfoTab.get("grbxyanglao");
            WmsCreRevInfoInsurance wmsCreRevInfoInsurance1 = toWmsCreRevInfoInsuranceEntity(grbxyanglaoArr,
                                                                                            approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                                                            wms_cre_credit_line_customer_change_head_id);
            // 先删除相应数据
            Map<String, Object> wmsCreRevInfoInsurance1Map = new HashMap<>();
            wmsCreRevInfoInsurance1Map.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevInfoInsurance1Map.put("wms_cre_credit_line_customer_change_head_id",
                                           wms_cre_credit_line_customer_change_head_id);
            wmsCreRevInfoInsurance1Map.put("lnsurance_type", grbxyanglaoArr.get("lnsurance_type"));
            wmsCreRevInfoInsuranceDao.deleteInfo(wmsCreRevInfoInsurance1Map);
            // 在存储
            ret = wmsCreRevInfoInsuranceDao.save(wmsCreRevInfoInsurance1);
            // 存储医疗保险
            Map<String, Object> grbxyiliaoArr = (Map<String, Object>) singleGrinfoTab.get("grbxyiliao");
            WmsCreRevInfoInsurance wmsCreRevInfoInsurance2 = toWmsCreRevInfoInsuranceEntity(grbxyiliaoArr,
                                                                                            approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                                                            wms_cre_credit_line_customer_change_head_id);
            // 先删除相应数据
            Map<String, Object> wmsCreRevInfoInsurance2Map = new HashMap<>();
            wmsCreRevInfoInsurance2Map.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevInfoInsurance2Map.put("wms_cre_credit_line_customer_change_head_id",
                                           wms_cre_credit_line_customer_change_head_id);
            wmsCreRevInfoInsurance2Map.put("lnsurance_type", grbxyiliaoArr.get("lnsurance_type"));
            wmsCreRevInfoInsuranceDao.deleteInfo(wmsCreRevInfoInsurance2Map);
            // 在存储
            ret = wmsCreRevInfoInsuranceDao.save(wmsCreRevInfoInsurance2);
            // 存储公积金
            Map<String, Object> grbxgongjjArr = (Map<String, Object>) singleGrinfoTab.get("grbxgongjj");
            WmsCreRevInfoInsurance wmsCreRevInfoInsurance3 = toWmsCreRevInfoInsuranceEntity(grbxgongjjArr,
                                                                                            approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                                                            wms_cre_credit_line_customer_change_head_id);
            // 先删除相应数据
            Map<String, Object> wmsCreRevInfoInsurance3Map = new HashMap<>();
            wmsCreRevInfoInsurance3Map.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevInfoInsurance3Map.put("wms_cre_credit_line_customer_change_head_id",
                                           wms_cre_credit_line_customer_change_head_id);
            wmsCreRevInfoInsurance3Map.put("lnsurance_type", grbxgongjjArr.get("lnsurance_type"));
            wmsCreRevInfoInsuranceDao.deleteInfo(wmsCreRevInfoInsurance3Map);
            // 在存储
            ret = wmsCreRevInfoInsuranceDao.save(wmsCreRevInfoInsurance3);
            // 新 存储通话记录其他说明信息
            // List<WmsCreRevInfoContact>gridphoneArr=JsonUtil.jsonArrayToList(singleGrinfoTab.get("grid_telphone").toString(),
            // WmsCreRevInfoContact.class);
            // List<Map<String,Object>>gridphoneArr=(List<Map<String,Object>>)singleGrinfoTab.get("grid_telphone");
            // 清空表中的数据
            // Map<String,Object>wmsCreRevInfoContactMap=new HashMap<>();
            // wmsCreRevInfoContactMap.put("wms_cre_credit_head_id",
            // approveWorkFlowVO.getWms_cre_credit_head_id());
            // wmsCreRevInfoContactMap.put("wms_cre_credit_line_customer_change_head_id",
            // wms_cre_credit_line_customer_change_head_id);
            // wmsCreRevInfoContactDao.deleteInfo(wmsCreRevInfoContactMap);
            // for(WmsCreRevInfoContact contactArr:gridphoneArr){
            // WmsCreRevInfoContact wmsCreRevInfoContact =
            // toWmsCreRevInfoContactEntity(contactArr,approveWorkFlowVO.getWms_cre_credit_head_id(),wms_cre_credit_line_customer_change_head_id);
            // 存储
            // ret=wmsCreRevInfoContactDao.save(contactArr);
            // }
            // 存储通话记录其他说明
            List<WmsCreCustomerChangeLineContact> gridtelphonecountArr = JsonUtil.jsonArrayToList(singleGrinfoTab.get("grid_telphone_count")
                                                                                                                 .toString(),
                                                                                                  WmsCreCustomerChangeLineContact.class);
            for (WmsCreCustomerChangeLineContact gridtel : gridtelphonecountArr)
            {
                WmsCreCustomerChangeLineContact wmsCreCustomerChangeLineContact = new WmsCreCustomerChangeLineContact();
                // 主键1
                wmsCreCustomerChangeLineContact.setWms_cre_customer_change_line_contact_id(gridtel.getWms_cre_customer_change_line_contact_id());
                // 结果1audit_result1
                if (gridtel.getPhone1_1() != null && gridtel.getPhone2_1() != null)
                {
                    wmsCreCustomerChangeLineContact.setAudit_result1(String.valueOf(Integer.valueOf(gridtel.getPhone1_1())
                                                                                    + Integer.valueOf(gridtel.getPhone2_1())));
                }
                else if (gridtel.getPhone1_1() != null)
                {
                    wmsCreCustomerChangeLineContact.setAudit_result1(String.valueOf(gridtel.getPhone1_1()));
                }
                else if (gridtel.getPhone2_1() != null)
                {
                    wmsCreCustomerChangeLineContact.setAudit_result1(String.valueOf(gridtel.getPhone2_1()));
                }
                // 结果2audit_result2
                if (gridtel.getPhone1_2() != null && gridtel.getPhone2_2() != null)
                {
                    wmsCreCustomerChangeLineContact.setAudit_result2(String.valueOf(Integer.valueOf(gridtel.getPhone1_2())
                                                                                    + Integer.valueOf(gridtel.getPhone2_2())));
                }
                else if (gridtel.getPhone1_2() != null)
                {
                    wmsCreCustomerChangeLineContact.setAudit_result2(String.valueOf(gridtel.getPhone1_2()));
                }
                else if (gridtel.getPhone2_2() != null)
                {
                    wmsCreCustomerChangeLineContact.setAudit_result2(String.valueOf(gridtel.getPhone2_2()));
                }
                // 结果3audit_result3
                if (gridtel.getPhone1_3() != null && gridtel.getPhone2_3() != null)
                {
                    wmsCreCustomerChangeLineContact.setAudit_result3(String.valueOf(Integer.valueOf(gridtel.getPhone1_3())
                                                                                    + Integer.valueOf(gridtel.getPhone2_3())));
                }
                else if (gridtel.getPhone1_3() != null)
                {
                    wmsCreCustomerChangeLineContact.setAudit_result3(String.valueOf(gridtel.getPhone1_3()));
                }
                else if (gridtel.getPhone2_3() != null)
                {
                    wmsCreCustomerChangeLineContact.setAudit_result3(String.valueOf(gridtel.getPhone2_3()));
                }
                // 1号联系次数第一个月phone1_1
                wmsCreCustomerChangeLineContact.setPhone1_1(gridtel.getPhone1_1());
                // 1号联系次数第二个月phone1_2
                wmsCreCustomerChangeLineContact.setPhone1_2(gridtel.getPhone1_2());
                // 1号联系次数第三个月phone1_3
                wmsCreCustomerChangeLineContact.setPhone1_3(gridtel.getPhone1_3());
                // 2号联系次数第一个月phone2_1
                wmsCreCustomerChangeLineContact.setPhone2_1(gridtel.getPhone2_1());
                // 2号联系次数第二个月phone2_2
                wmsCreCustomerChangeLineContact.setPhone2_2(gridtel.getPhone2_2());
                // 2号联系次数第三个月phone2_3
                wmsCreCustomerChangeLineContact.setPhone2_3(gridtel.getPhone2_3());
                // 电话小号contact_mobile_phone_short
                wmsCreCustomerChangeLineContact.setContact_mobile_phone_short(gridtel.getContact_mobile_phone_short());
                ret = wmsCreCustomerChangeLineContactDao.update(wmsCreCustomerChangeLineContact);
            }
        }
        // 对企业信息进行解析
        List<Map<String, Object>> qyInfoArr = jsonObjList(qyInfo);
        for (Map<String, Object> singleQyinfoTab : qyInfoArr)
        {
            Integer wms_cre_credit_line_customer_change_head_id = Integer.parseInt(singleQyinfoTab.get("wms_cre_credit_line_customer_change_head_id")
                                                                                                  .toString());
            Map<String, Object> ztqkMap = (Map<String, Object>) singleQyinfoTab.get("ztqkJson");// 总体情况和备注
            WmsCreRevInfoMain wmsCreRevInfoMain = updateWmsCreRevInfoMainEntity(ztqkMap,
                                                                                approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                                                wms_cre_credit_line_customer_change_head_id);
            ret = wmscrerevinfomainDao.updateQyFyw(wmsCreRevInfoMain);
            // 企业基本信息的存储
            List<Map<String, Object>> qybaseInfoArr = (List<Map<String, Object>>) singleQyinfoTab.get("qybaseInfo");
            Map<String, Object> wmsCreRevInfoCompMap = new HashMap<>();
            wmsCreRevInfoCompMap.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevInfoCompMap.put("wms_cre_credit_line_customer_change_head_id",
                                     wms_cre_credit_line_customer_change_head_id);
            wmsCreRevInfoCompDao.deleteInfo(wmsCreRevInfoCompMap);
            for (Map<String, Object> qybaseInfo : qybaseInfoArr)
            {
                WmsCreRevInfoComp wmsCreRevInfoComp = toWmsCreRevInfoCompEntity(qybaseInfo,
                                                                                approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                                                wms_cre_credit_line_customer_change_head_id);
                ret = wmsCreRevInfoCompDao.save(wmsCreRevInfoComp);
            }
            // 企业法院网信息的存储info_qyfyw
            List<Map<String, Object>> info_qyfywArr = (List<Map<String, Object>>) singleQyinfoTab.get("info_qyfyw");
            Map<String, Object> wmsCreRevInfoCourtCaseMap = new HashMap<>();
            wmsCreRevInfoCourtCaseMap.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevInfoCourtCaseMap.put("wms_cre_credit_line_customer_change_head_id",
                                          wms_cre_credit_line_customer_change_head_id);
            wmsCreRevInfoCourtCaseMap.put("court_case_type", "2");
            wmsCreRevInfoCourtCaseDao.deleteInfo(wmsCreRevInfoCourtCaseMap);
            for (Map<String, Object> info_qyfyw : info_qyfywArr)
            {
                WmsCreRevInfoCourtCase wmsCreRevInfoCourtCase = toWmsCreRevInfoCourtCaseEntity(info_qyfyw,
                                                                                               approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                                                               wms_cre_credit_line_customer_change_head_id);
                ret = wmsCreRevInfoCourtCaseDao.save(wmsCreRevInfoCourtCase);
            }
        }
        // 对该单据主贷人重要数据的存储
        List<Map<String, Object>> workDatas = jsonObjList(InfoImData);
        // 先删除该表单ID所含有的数据
        Map<String, Object> wmsCreRevInfoModelMap = new HashMap<>();
        wmsCreRevInfoModelMap.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        wmsCreRevInfoModelDao.deleteInfo(wmsCreRevInfoModelMap);
        for (Map<String, Object> singletab : workDatas)
        {
            Map<String, Object> works = (Map<String, Object>) singletab.get("workData");
            WmsCreRevInfoModel wmsInfoModel = toWmsCreRevInfoModelEntity(works,
                                                                         approveWorkFlowVO.getWms_cre_credit_head_id());
            ret = wmsCreRevInfoModelDao.save(wmsInfoModel);
        }
        if (ret == 0)
        {
            resStr = "error";
        }
        // 保存执行流程
        if (flag == 1)
        {
            resStr = "success";
            Timestamp sysTime = new Timestamp(System.currentTimeMillis());
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("info_appro_user_id", user.getUserId());
            params.put("info_appro_user_name", user.getRealname());
            params.put("info_appro_timestamp", sysTime);
            params.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            wmscrecreditheadDao.updateRecord(params);
            // 存储流程
            approveWorkFlowVO.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());
            approveWorkFlowVO.setUser_id(user.getUserId());
            resStr = creditWorkFlowService.infoTeamCheckApproveWorkFlow(approveWorkFlowVO);
        }
        return resStr;
    }

    @Override
    public java.util.Map<String, Object> getInfoByFKForAddgr(Integer wms_cre_credit_head_id,
                                                             Integer wms_cre_credit_line_customer_change_head_id)
    {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        parmMap.put("wms_cre_credit_line_customer_change_head_id", wms_cre_credit_line_customer_change_head_id);
        WmsCreRevInfoMain wmsCreRevInfoMain = wmscrerevinfomainDao.getByFK(parmMap);// 获取总体情况
        resultMap.put("ztqk", wmsCreRevInfoMain);
        // 获取个人法院网信息
        Map<String, Object> courtcaseMap = new HashMap<>();
        courtcaseMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        courtcaseMap.put("wms_cre_credit_line_customer_change_head_id", wms_cre_credit_line_customer_change_head_id);
        courtcaseMap.put("court_case_type", 1);
        List<Map<String, Object>> wmsCreRevInfoCourtCaseLinelist = wmsCreRevInfoCourtCaseDao.search(courtcaseMap);// 个人法院网
        resultMap.put("grfyw", wmsCreRevInfoCourtCaseLinelist);
        // 获取个人信息-犯罪记录信息
        List<Map<String, Object>> wmsCreRevInfoCriminalLinelist = wmsCreRevInfoCriminalDao.search(parmMap);// 犯罪记录
        resultMap.put("grfz", wmsCreRevInfoCriminalLinelist);
        // 获取个人信息-车辆记录信息
        List<Map<String, Object>> mcreCreRevInfoCarLinelist = wmsCreRevInfoCarDao.search(parmMap);// 车辆信息
        resultMap.put("grcl", mcreCreRevInfoCarLinelist);
        // 获取个人养老保险记录信息1
        Map<String, Object> insurance1Map = new HashMap<>();
        insurance1Map.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        insurance1Map.put("wms_cre_credit_line_customer_change_head_id", wms_cre_credit_line_customer_change_head_id);
        insurance1Map.put("lnsurance_type", 1);
        WmsCreRevInfoInsurance wmsCreRevInfoInsurance1 = wmsCreRevInfoInsuranceDao.getByFK(insurance1Map);
        resultMap.put("gryanglao", wmsCreRevInfoInsurance1);
        // 获取个人医疗记录信息2
        Map<String, Object> insurance2Map = new HashMap<>();
        insurance2Map.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        insurance2Map.put("wms_cre_credit_line_customer_change_head_id", wms_cre_credit_line_customer_change_head_id);
        insurance2Map.put("lnsurance_type", 2);
        WmsCreRevInfoInsurance wmsCreRevInfoInsurance2 = wmsCreRevInfoInsuranceDao.getByFK(insurance2Map);
        resultMap.put("gryiliao", wmsCreRevInfoInsurance2);
        // 获取个人公积金记录信息3
        Map<String, Object> insurance3Map = new HashMap<>();
        insurance3Map.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        insurance3Map.put("wms_cre_credit_line_customer_change_head_id", wms_cre_credit_line_customer_change_head_id);
        insurance3Map.put("lnsurance_type", 3);
        WmsCreRevInfoInsurance wmsCreRevInfoInsurance3 = wmsCreRevInfoInsuranceDao.getByFK(insurance3Map);
        resultMap.put("grgongjijin", wmsCreRevInfoInsurance3);
        // 获取附件信息
        parmMap.put("data_type", "1");
        List<Map<String, Object>> wmsCreRevAttList = wmsCreRevAttDao.search(parmMap);// 附件信息
        resultMap.put("fjxx", wmsCreRevAttList);
        return resultMap;
    }

    @Override
    public Map<String, Object> getInfoByFKForAddqy(Integer wms_cre_credit_head_id,
                                                   Integer wms_cre_credit_line_customer_change_head_id)
    {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> parmMap = new HashMap<>();
        // 获取总体情况
        parmMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        parmMap.put("wms_cre_credit_line_customer_change_head_id", wms_cre_credit_line_customer_change_head_id);
        WmsCreRevInfoMain wmsCreRevInfoMain = wmscrerevinfomainDao.getByFK(parmMap);// 获取总体情况
        resultMap.put("ztqk", wmsCreRevInfoMain);
        // 获取企业基本信息
        List<Map<String, Object>> wmsCreRevInfoCompLinelist = wmsCreRevInfoCompDao.search(parmMap);// 获取企业基本信息
        resultMap.put("qybase", wmsCreRevInfoCompLinelist);
        // 获取企业法院网信息
        Map<String, Object> courtcaseMap = new HashMap<>();
        courtcaseMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        courtcaseMap.put("wms_cre_credit_line_customer_change_head_id", wms_cre_credit_line_customer_change_head_id);
        courtcaseMap.put("court_case_type", "2");
        List<Map<String, Object>> wmsCreRevInfoCourtCaseLinelist = wmsCreRevInfoCourtCaseDao.search(courtcaseMap);// 企业法院网
        resultMap.put("qyfyw", wmsCreRevInfoCourtCaseLinelist);
        return resultMap;
    }

    @Override
    public String infoToBack(WmsCreditWorkFlowVO approveWorkFlowVO, UserBean user)
    {
        String resStr = "success";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("is_review_back", "1");
        paramMap.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        wmscrecreditheadDao.updateRecord(paramMap);
        //查询
        Map<String, Object> resMap = wmscrecreditheadDao.getWmsCreCredReturnInfoById(approveWorkFlowVO.getWms_cre_credit_head_id());
        Task task = taskService.createTaskQuery().taskId(approveWorkFlowVO.getTaskId()).singleResult();
        resMap.put("audit_group", task.getName());//退件审核组
        resMap.put("audit_reason", approveWorkFlowVO.getAdvice());//退件原因
        //插入信贷退件查询表
        wmsCreCreditReturnSearchDao.addWmsCreCreditReturnInfo(resMap);
        approveWorkFlowVO.setUser_id(user.getUserId());
        resStr = creditWorkFlowService.creCheckCreditSupply(approveWorkFlowVO);// 信贷流程处理
        return resStr;
    }

    @Override
    public String houseInfoToBack(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, UserBean user)
    {
        String resStr = "success";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("is_review_back", "1");
        paramMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
        wmscrecreditheadDao.updateRecord(paramMap);
        approveHouseWorkFlowVO.setUserId(String.valueOf(user.getUserId()));
        resStr = houseCreditWorkFlowService.creCheckHouseSupply(approveHouseWorkFlowVO);// 房贷流程处理
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreRevInfoMain bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevinfomainDao.update(bean); // update a record replace
                                                 // columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    // 修改信息总体表情况中的企业法院网案件实体
    private WmsCreRevInfoMain updateWmsCreRevInfoMainEntity(Map<String, Object> ztqkMap,
                                                            Integer wms_cre_credit_head_id,
                                                            Integer wms_cre_credit_line_customer_change_head_id)
    {
        WmsCreRevInfoMain wmsCreRevInfoMain = new WmsCreRevInfoMain();
        wmsCreRevInfoMain.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        wmsCreRevInfoMain.setWms_cre_credit_line_customer_change_head_id(wms_cre_credit_line_customer_change_head_id);
        wmsCreRevInfoMain.setComp_court_case((String) ztqkMap.get("comp_court_case"));
        return wmsCreRevInfoMain;
    }

    // 实现企业基本信息情况实体类
    private WmsCreRevInfoComp toWmsCreRevInfoCompEntity(Map<String, Object> qybaseInfo, Integer wms_cre_credit_head_id,
                                                        Integer wms_cre_credit_line_customer_change_head_id)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        WmsCreRevInfoComp wmsCreRevInfoComp = new WmsCreRevInfoComp();
        // 是否有营业执照has_enterprise
        wmsCreRevInfoComp.setHas_enterprise((String) qybaseInfo.get("has_enterprise"));
        // 原因enterprise_reason
        wmsCreRevInfoComp.setEnterprise_reason((String) qybaseInfo.get("enterprise_reason"));
        // 企业类型company_type
        wmsCreRevInfoComp.setCompany_type((String) qybaseInfo.get("company_type"));
        // 注册资金register_money
        if (StringUtil.isNotBlank((String) qybaseInfo.get("register_money")))
        {
            wmsCreRevInfoComp.setRegister_money(new BigDecimal(qybaseInfo.get("register_money").toString()));
        }
        // 经营状态operation_state
        wmsCreRevInfoComp.setOperation_state((String) qybaseInfo.get("operation_state"));
        // 发照日期date_of_issue
        if (StringUtil.isNotBlank((String) qybaseInfo.get("date_of_issue")))
        {
            try
            {
                wmsCreRevInfoComp.setDate_of_issue(new Date(sdf.parse(qybaseInfo.get("date_of_issue").toString())
                                                               .getTime()));
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        // 营业期限开始issue_begin_date
        if (StringUtil.isNotBlank((String) qybaseInfo.get("issue_begin_date")))
        {
            try
            {
                wmsCreRevInfoComp.setIssue_begin_date(new Date(sdf.parse(qybaseInfo.get("issue_begin_date").toString())
                                                                  .getTime()));
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        // 营业期限截止issue_end_date
        if (StringUtil.isNotBlank((String) qybaseInfo.get("issue_end_date")))
        {
            try
            {
                wmsCreRevInfoComp.setIssue_end_date(new Date(sdf.parse(qybaseInfo.get("issue_end_date").toString())
                                                                .getTime()));
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        // 股东人数shareholders_num
        if (StringUtil.isNotBlank((String) qybaseInfo.get("shareholders_num")))
        {
            wmsCreRevInfoComp.setShareholders_num(Integer.valueOf(qybaseInfo.get("shareholders_num").toString()));
        }
        // 出资方式way_of_contribution
        wmsCreRevInfoComp.setWay_of_contribution((String) qybaseInfo.get("way_of_contribution"));
        // 是否年检is_inspection
        wmsCreRevInfoComp.setIs_inspection((String) qybaseInfo.get("is_inspection"));
        // 组织机构代码证是否一致is_same_organ_code
        wmsCreRevInfoComp.setIs_same_organ_code((String) qybaseInfo.get("is_same_organ_code"));
        // 组织机构代码是否年检 is_inspection_organ_code
        wmsCreRevInfoComp.setIs_inspection_organ_code((String) qybaseInfo.get("is_inspection_organ_code"));
        // 其他信息是否一致is_same_other
        wmsCreRevInfoComp.setIs_same_other((String) qybaseInfo.get("is_same_other"));
        // 是否具有纳税人资格is_taxpayer
        wmsCreRevInfoComp.setIs_taxpayer((String) qybaseInfo.get("is_taxpayer"));
        // 纳税状态pay_taxes_status
        wmsCreRevInfoComp.setPay_taxes_status((String) qybaseInfo.get("pay_taxes_status"));
        // 企业基本情况特殊说明comp_remark
        wmsCreRevInfoComp.setComp_remark((String) qybaseInfo.get("comp_remark"));
        wmsCreRevInfoComp.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        wmsCreRevInfoComp.setWms_cre_credit_line_customer_change_head_id(wms_cre_credit_line_customer_change_head_id);
        return wmsCreRevInfoComp;
    }

    // 实现公积金养老保险实体类
    private WmsCreRevInfoInsurance toWmsCreRevInfoInsuranceEntity(Map<String, Object> Arr,
                                                                  Integer wms_cre_credit_head_id,
                                                                  Integer wms_cre_credit_line_customer_change_head_id)
    {
        WmsCreRevInfoInsurance wmsCreRevInfoInsurance = new WmsCreRevInfoInsurance();
        // 保险类型lnsurance_type
        wmsCreRevInfoInsurance.setLnsurance_type((String) Arr.get("lnsurance_type"));
        // 查询方式lookup_mode
        wmsCreRevInfoInsurance.setLookup_mode((String) Arr.get("lookup_mode"));
        // 查询结果lookup_result
        wmsCreRevInfoInsurance.setLookup_result((String) Arr.get("lookup_result"));
        // 单位名称是否一致lnsurance_com_same
        wmsCreRevInfoInsurance.setLnsurance_com_same((String) Arr.get("lnsurance_com_same"));
        // 参保状态lnsurance_status
        wmsCreRevInfoInsurance.setLnsurance_status((String) Arr.get("lnsurance_status"));
        wmsCreRevInfoInsurance.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        wmsCreRevInfoInsurance.setWms_cre_credit_line_customer_change_head_id(wms_cre_credit_line_customer_change_head_id);
        return wmsCreRevInfoInsurance;
    }

    // 生成车辆信息实体类
    private WmsCreRevInfoCar toWmsCreRevInfoCarEntity(Map<String, Object> grcl, Integer wms_cre_credit_head_id,
                                                      Integer wms_cre_credit_line_customer_change_head_id)
    {
        WmsCreRevInfoCar wmsCreRevInfoCar = new WmsCreRevInfoCar();
        // 车辆牌子car_brand
        wmsCreRevInfoCar.setCar_brand((String) grcl.get("car_brand"));
        // 车辆特殊说明car_remark
        wmsCreRevInfoCar.setCar_remark((String) grcl.get("car_remark"));
        wmsCreRevInfoCar.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        wmsCreRevInfoCar.setWms_cre_credit_line_customer_change_head_id(wms_cre_credit_line_customer_change_head_id);
        return wmsCreRevInfoCar;
    }

    // 生成犯罪记录信息实体类
    private WmsCreRevInfoCriminal toWmsCreRevInfoCriminalEntity(Map<String, Object> grfz,
                                                                Integer wms_cre_credit_head_id,
                                                                Integer wms_cre_credit_line_customer_change_head_id)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        WmsCreRevInfoCriminal wmsCreRevInfoCriminal = new WmsCreRevInfoCriminal();
        // 案件类型case_type
        wmsCreRevInfoCriminal.setCase_type((String) grfz.get("case_type"));
        // 立案时间accreditation_date
        if (StringUtil.isNotBlank((String) grfz.get("accreditation_date")))
        {
            try
            {
                wmsCreRevInfoCriminal.setAccreditation_date(new Date(sdf.parse(grfz.get("accreditation_date")
                                                                                   .toString()).getTime()));
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        // 结案时间close_date
        if (StringUtil.isNotBlank((String) grfz.get("close_date")))
        {
            try
            {
                wmsCreRevInfoCriminal.setClose_date(new Date(sdf.parse(grfz.get("close_date").toString()).getTime()));
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        // 执行的executive_area
        wmsCreRevInfoCriminal.setExecutive_area((String) grfz.get("executive_area"));
        // 执行方式executive_way
        wmsCreRevInfoCriminal.setExecutive_way((String) grfz.get("executive_way"));
        // 案件说明criminal_remark
        wmsCreRevInfoCriminal.setCriminal_remark((String) grfz.get("criminal_remark"));
        wmsCreRevInfoCriminal.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        wmsCreRevInfoCriminal.setWms_cre_credit_line_customer_change_head_id(wms_cre_credit_line_customer_change_head_id);
        return wmsCreRevInfoCriminal;
    }

    // 生成个人法院网实体类
    private WmsCreRevInfoCourtCase toWmsCreRevInfoCourtCaseEntity(Map<String, Object> fyw,
                                                                  Integer wms_cre_credit_head_id,
                                                                  Integer wms_cre_credit_line_customer_change_head_id)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        WmsCreRevInfoCourtCase wmsCreRevInfoCourtCase = new WmsCreRevInfoCourtCase();
        // 执行状态execute_status
        wmsCreRevInfoCourtCase.setExecute_status((String) fyw.get("execute_status"));
        // 立案时间accreditation_date
        if (StringUtil.isNotBlank((String) fyw.get("accreditation_date")))
        {
            try
            {
                wmsCreRevInfoCourtCase.setAccreditation_date(new Date(sdf.parse(fyw.get("accreditation_date")
                                                                                   .toString()).getTime()));
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        // 执行标的execute_target
        wmsCreRevInfoCourtCase.setExecute_target((String) fyw.get("execute_target"));
        // 案件类型court_case_type 1代表个人法院网
        wmsCreRevInfoCourtCase.setCourt_case_type((String) fyw.get("court_case_type"));
        // 特殊说明court_case_remark
        wmsCreRevInfoCourtCase.setCourt_case_remark((String) fyw.get("court_case_remark"));
        // wms_cre_credit_head_id
        wmsCreRevInfoCourtCase.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        //
        wmsCreRevInfoCourtCase.setWms_cre_credit_line_customer_change_head_id(wms_cre_credit_line_customer_change_head_id);
        return wmsCreRevInfoCourtCase;
    }

    // 生成附件信息实体类
    private WmsCreRevAtt toWmsCreRevAttEntity(Map<String, Object> fi, String data_type, Integer wms_cre_credit_head_id,
                                              Integer wms_cre_credit_line_customer_change_head_id)
    {
        WmsCreRevAtt file = new WmsCreRevAtt();
        file.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        file.setData_type(data_type);
        file.setWms_cre_credit_line_customer_change_head_id(wms_cre_credit_line_customer_change_head_id);
        file.setAttachment_old_name(fi.get("attachment_old_name").toString());
        file.setAttachment_new_name(fi.get("attachment_new_name").toString());
        file.setAttachment_address(fi.get("attachment_address").toString());
        file.setAttachment_type(fi.get("attachment_type").toString());
        return file;
    }

    // 实现联系小号信息的实体类
    private WmsCreRevInfoContact toWmsCreRevInfoContactEntity(Map<String, Object> contactArr,
                                                              Integer wms_cre_credit_head_id,
                                                              Integer wms_cre_credit_line_customer_change_head_id)
    {
        WmsCreRevInfoContact wmsContact = new WmsCreRevInfoContact();
        // 信用贷款主键
        wmsContact.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        // 客户信息变更主键
        wmsContact.setWms_cre_credit_line_customer_change_head_id(wms_cre_credit_line_customer_change_head_id);
        // 客户变更联系人主键
        wmsContact.setWms_cre_customer_change_line_contact_id(Integer.valueOf(contactArr.get("wms_cre_customer_change_line_contact_id")
                                                                                        .toString()));
        // 是否是小号
        wmsContact.setIs_phone_short((String) contactArr.get("is_phone_short"));
        if (contactArr.get("is_phone_short").equals("1"))
        {
            // 小号
            wmsContact.setContact_mobile_phone_short((String) contactArr.get("contact_mobile_phone_short"));
        }
        // 1号卡
        wmsContact.setPhone1_1(Integer.valueOf(contactArr.get("phone1_1").toString()));
        wmsContact.setPhone1_2(Integer.valueOf(contactArr.get("phone1_2").toString()));
        wmsContact.setPhone1_3(Integer.valueOf(contactArr.get("phone1_3").toString()));
        // 2号卡
        wmsContact.setPhone2_1(Integer.valueOf(contactArr.get("phone2_1").toString()));
        wmsContact.setPhone2_2(Integer.valueOf(contactArr.get("phone2_2").toString()));
        wmsContact.setPhone2_3(Integer.valueOf(contactArr.get("phone2_3").toString()));
        return wmsContact;
    }

    // 实现信息主表实体类的实现
    private WmsCreRevInfoMain toWmsCreRevInfoMainEntity(Map<String, Object> ztqkMap, Integer wms_cre_credit_head_id,
                                                        Integer wms_cre_credit_line_customer_change_head_id,
                                                        UserBean user)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        WmsCreRevInfoMain wmsCreRevInfoMain = new WmsCreRevInfoMain();
        // 个人姓名
        wmsCreRevInfoMain.setCustomer_name((String) ztqkMap.get("customer_name"));
        // 个人属性
        wmsCreRevInfoMain.setCustomer_prop((String) ztqkMap.get("customer_prop"));
        // 个人法院网是否有
        wmsCreRevInfoMain.setPriv_court_case((String) ztqkMap.get("priv_court_case"));
        // 企业法院网是否有
        wmsCreRevInfoMain.setComp_court_case((String) ztqkMap.get("comp_court_case"));
        // 有无犯罪记录
        wmsCreRevInfoMain.setIs_crime_record((String) ztqkMap.get("is_crime_record"));
        // 有无车辆记录
        wmsCreRevInfoMain.setIs_car_record((String) ztqkMap.get("is_car_record"));
        // 是否是循环贷
        wmsCreRevInfoMain.setIs_re_loan((String) ztqkMap.get("is_re_loan"));
        // 上期借款合同金额
        if (StringUtil.isNotBlank((String) ztqkMap.get("last_loan_money")))
        {
            wmsCreRevInfoMain.setLast_loan_money(new BigDecimal(ztqkMap.get("last_loan_money").toString()));
        }
        // 还款情况
        wmsCreRevInfoMain.setRepayment_status((String) ztqkMap.get("repayment_status"));
        // 上期合同申请日期
        if (StringUtil.isNotBlank((String) ztqkMap.get("last_apply_date")))
        {
            try
            {
                wmsCreRevInfoMain.setLast_apply_date(new Date(sdf.parse(ztqkMap.get("last_apply_date").toString())
                                                                 .getTime()));
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        // 上期结清日期
        if (StringUtil.isNotBlank((String) ztqkMap.get("last_clean_date")))
        {
            try
            {
                wmsCreRevInfoMain.setLast_clean_date(new Date(sdf.parse(ztqkMap.get("last_clean_date").toString())
                                                                 .getTime()));
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        // 循环贷特殊说明
        wmsCreRevInfoMain.setOverdue_status((String) ztqkMap.get("overdue_status"));
        // 手机卡1是否实名
        wmsCreRevInfoMain.setIs_real_name_tel1((String) ztqkMap.get("is_real_name_tel1"));
        // 手机卡2是否实名
        wmsCreRevInfoMain.setIs_real_name_tel2((String) ztqkMap.get("is_real_name_tel2"));
        // 卡1姓名
        wmsCreRevInfoMain.setReal_name_tel1((String) ztqkMap.get("real_name_tel1"));
        // 卡2姓名
        wmsCreRevInfoMain.setReal_name_tel2((String) ztqkMap.get("real_name_tel2"));
        // 卡1入网时间
        if (StringUtil.isNotBlank((String) ztqkMap.get("phone_access_date1")))
        {
            try
            {
                wmsCreRevInfoMain.setPhone_access_date1(new Date(
                                                                 sdf.parse(ztqkMap.get("phone_access_date1").toString())
                                                                    .getTime()));
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        // 卡2入网时间
        if (StringUtil.isNotBlank((String) ztqkMap.get("phone_access_date2")))
        {
            try
            {
                wmsCreRevInfoMain.setPhone_access_date2(new Date(
                                                                 sdf.parse(ztqkMap.get("phone_access_date2").toString())
                                                                    .getTime()));
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        // 通话记录特殊说明
        wmsCreRevInfoMain.setPhone_remark((String) ztqkMap.get("phone_remark"));
        // 申请人姓名
        wmsCreRevInfoMain.setApply_name((String) ((Map<String, Object>) ztqkMap.get("xxfcdd")).get("apply_name"));
        // 身份证号码
        wmsCreRevInfoMain.setId_card((String) ((Map<String, Object>) ztqkMap.get("xxfcdd")).get("id_card"));
        // 产权人
        wmsCreRevInfoMain.setOwner_name((String) ((Map<String, Object>) ztqkMap.get("xxfcdd")).get("owner_name"));
        // 共同拥有人
        wmsCreRevInfoMain.setCo_owner_name((String) ((Map<String, Object>) ztqkMap.get("xxfcdd")).get("co_owner_name"));
        // 房产证号house_card_id
        wmsCreRevInfoMain.setHouse_card_id((String) ((Map<String, Object>) ztqkMap.get("xxfcdd")).get("house_card_id"));
        // 房产地址house_address
        wmsCreRevInfoMain.setHouse_address((String) ((Map<String, Object>) ztqkMap.get("xxfcdd")).get("house_address"));
        // 权利人obligee_name
        wmsCreRevInfoMain.setObligee_name((String) ((Map<String, Object>) ztqkMap.get("xxfcdd")).get("obligee_name"));
        // 抵押金额mortgage_amount
        if (StringUtil.isNotBlank((String) ((Map<String, Object>) ztqkMap.get("xxfcdd")).get("mortgage_amount")))
        {
            wmsCreRevInfoMain.setMortgage_amount(new BigDecimal(
                                                                ((Map<String, Object>) ztqkMap.get("xxfcdd")).get("mortgage_amount")
                                                                                                             .toString()));
        }
        // 约定期限agree_limit_time
        wmsCreRevInfoMain.setAgree_limit_time((String) ((Map<String, Object>) ztqkMap.get("xxfcdd")).get("agree_limit_time"));
        // 其他other_remark
        wmsCreRevInfoMain.setOther_remark((String) ((Map<String, Object>) ztqkMap.get("xxfcdd")).get("other_remark"));
        // 查档房产局estate_board
        wmsCreRevInfoMain.setEstate_board((String) ((Map<String, Object>) ztqkMap.get("xxfcdd")).get("estate_board"));
        // 房产调档记录说明board_remark
        wmsCreRevInfoMain.setBoard_remark((String) ((Map<String, Object>) ztqkMap.get("xxfcdd")).get("board_remark"));

        // 保险攻击经说明insurance_remark
        wmsCreRevInfoMain.setInsurance_remark((String) ztqkMap.get("insurance_remark"));

        Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        // 创建ID
        wmsCreRevInfoMain.setCreate_user_id(user.getUserId());
        // 创建人姓名
        wmsCreRevInfoMain.setCreate_user_name(user.getRealname());
        // 创建时间
        wmsCreRevInfoMain.setCreate_timestamp(sysTime);
        wmsCreRevInfoMain.setLast_update_user_id(user.getUserId());
        wmsCreRevInfoMain.setLast_update_timestamp(sysTime);
        wmsCreRevInfoMain.setEnable_flag("1");
        wmsCreRevInfoMain.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        wmsCreRevInfoMain.setWms_cre_credit_line_customer_change_head_id(wms_cre_credit_line_customer_change_head_id);
        return wmsCreRevInfoMain;
    }

    // 实现信调重要数据实体类的实现
    private WmsCreRevInfoModel toWmsCreRevInfoModelEntity(Map<String, Object> info_workIm,
                                                          Integer wms_cre_credit_head_id)
    {
        WmsCreRevInfoModel wmsInfoModel = new WmsCreRevInfoModel();
        wmsInfoModel.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        wmsInfoModel.setWork_unit_full_name((String) info_workIm.get("work_unit_full_name"));
        wmsInfoModel.setWork_unit_property(Integer.valueOf((String) info_workIm.get("work_unit_property")));
        wmsInfoModel.setComp_industry(Integer.valueOf((String) info_workIm.get("comp_industry")));
        wmsInfoModel.setWork_year(Integer.valueOf((String) info_workIm.get("work_year")));
        wmsInfoModel.setDuty_of_work(Integer.valueOf((String) info_workIm.get("duty_of_work")));
        wmsInfoModel.setComp_industry_year(Integer.valueOf((String) info_workIm.get("comp_industry_year")));
        wmsInfoModel.setUnit_tel((String) info_workIm.get("unit_tel"));
        wmsInfoModel.setAnnual_income(Integer.valueOf((String) info_workIm.get("annual_income")));
        return wmsInfoModel;
    }

    private List<WmsCreRevInfoMain> getListByEntity(WmsCreRevInfoMain queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreRevInfoMain> beanList = wmscrerevinfomainDao.getListByEntity(queryInfo);
        return beanList;
    }

    /**
     * 将传入的json字符串转换为元素为map集合的List集合
     * 
     * @param jsonArrStr
     * @return
     */
    private List<Map<String, Object>> jsonObjList(String jsonArrStr)
    {
        List<Map<String, Object>> jsonObjList = new ArrayList<Map<String, Object>>();
        List<?> jsonList = jsonToList(jsonArrStr);
        Gson gson = new Gson();
        for (Object object : jsonList)
        {
            String jsonStr = gson.toJson(object);
            Map<?, ?> json = jsonToMap(jsonStr);
            jsonObjList.add((Map<String, Object>) json);
        }
        return jsonObjList;
    }

    /**
     * 将传入的json字符串解析为Map集合
     * 
     * @param jsonStr
     * @return
     */
    private static Map<?, ?> jsonToMap(String jsonStr)
    {
        Map<?, ?> ObjectMap = null;
        Gson gson = new Gson();
        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Map<?, ?>>()
        {
        }.getType();
        ObjectMap = gson.fromJson(jsonStr, type);
        return ObjectMap;
    }

    /**
     * 将传入的json字符串解析为List集合
     * 
     * @param jsonStr
     * @return
     */
    private static List<?> jsonToList(String jsonStr)
    {
        List<?> ObjectList = null;
        Gson gson = new Gson();
        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<?>>()
        {
        }.getType();
        ObjectList = gson.fromJson(jsonStr, type);
        return ObjectList;
    }
}
