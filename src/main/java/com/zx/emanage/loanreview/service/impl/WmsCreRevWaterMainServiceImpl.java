package com.zx.emanage.loanreview.service.impl;

import java.sql.Timestamp;
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

import com.google.gson.Gson;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditReturnSearchDao;
import com.zx.emanage.cremanage.service.IWmsCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsHouseCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanreview.persist.WmsCreRevAttDao;
import com.zx.emanage.loanreview.persist.WmsCreRevWaterMainDao;
import com.zx.emanage.loanreview.persist.WmsCreRevWaterModelDao;
import com.zx.emanage.loanreview.persist.WmsCreRevWaterPrivPassbookDao;
import com.zx.emanage.loanreview.persist.WmsCreRevWaterPrivSalaryDao;
import com.zx.emanage.loanreview.persist.WmsCreRevWaterRepayLoanDao;
import com.zx.emanage.loanreview.persist.WmsCreRevWaterRepayLoanLineDao;
import com.zx.emanage.loanreview.service.IWmsCreRevWaterMainService;
import com.zx.emanage.loanreview.vo.WmsCreRevWaterMainSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevAtt;
import com.zx.emanage.util.gen.entity.WmsCreRevWaterMain;
import com.zx.emanage.util.gen.entity.WmsCreRevWaterModel;
import com.zx.emanage.util.gen.entity.WmsCreRevWaterPrivPassbook;
import com.zx.emanage.util.gen.entity.WmsCreRevWaterPrivSalary;
import com.zx.emanage.util.gen.entity.WmsCreRevWaterRepayLoan;
import com.zx.emanage.util.gen.entity.WmsCreRevWaterRepayLoanLine;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevwatermainService")
public class WmsCreRevWaterMainServiceImpl implements IWmsCreRevWaterMainService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevWaterMainServiceImpl.class);

    @Autowired
    private WmsCreRevWaterMainDao wmscrerevwatermainDao;

    @Autowired
    private WmsCreRevWaterRepayLoanDao wmsCreRevWaterRepayLoanDao;

    @Autowired
    private WmsCreRevWaterRepayLoanLineDao wmsCreRevWaterRepayLoanLineDao;

    @Autowired
    private WmsCreRevWaterPrivPassbookDao wmsCreRevWaterPrivPassbookDao;

    @Autowired
    private WmsCreRevWaterPrivSalaryDao wmsCreRevWaterPrivSalaryDao;

    @Autowired
    private WmsCreRevWaterModelDao wmsCreRevWaterModelDao;

    @Autowired
    private WmsCreRevAttDao wmsCreRevAttDao;

    @Autowired
    private WmsCreCreditHeadDao wmscrecreditheaddao;

    @Autowired
    private IWmsCreditWorkFlowService creditWorkFlowService;

    @Autowired
    private IWmsHouseCreditWorkFlowService houseCreditWorkFlowService;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private WmsCreCreditReturnSearchDao wmsCreCreditReturnSearchDao;
    @Autowired
    private IWmsLoanWorkFlowService   wmsLoanWorkFlowService;//新房贷贷款流程

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreRevWaterMainSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrerevwatermainDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreRevWaterMainSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrerevwatermainDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrerevwatermainDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreRevWaterMain getInfoByPK(Integer wms_cre_rev_water_main_id)
    {
        return wmscrerevwatermainDao.get(wms_cre_rev_water_main_id);
    }
    /**
     * 实现信贷流水审核信息记录功能
     * 
     */
    @Override
    @Transactional
    public String save(String dsls, String dgls, String zysj, String water_appro_eval, UserBean user,
                       WmsCreditWorkFlowVO approveWorkFlowVO, String saveStatus)
    {
        if ("1".equals(saveStatus))
        {
            String advice = "单据评价：";
            if ("1".equals(water_appro_eval))
            {
                advice += "优<br />";
            }
            else if ("2".equals(water_appro_eval))
            {
                advice += "良<br />";
            }
            else if ("3".equals(water_appro_eval))
            {
                advice += "中<br />";
            }
            else if ("4".equals(water_appro_eval))
            {
                advice += "差<br />";
            }
            approveWorkFlowVO.setYadvice(approveWorkFlowVO.getAdvice());
            approveWorkFlowVO.setAdvice(advice + approveWorkFlowVO.getAdvice());
        }
        String resStr = "success";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, Object>> dslsArr = jsonObjList(dsls);
        for (Map<String, Object> singleDslsTab : dslsArr)
        {// 对私流水
            Integer wms_cre_credit_line_customer_change_head_id = Integer.parseInt(singleDslsTab.get("wms_cre_credit_line_customer_change_head_id")
                                                                                                .toString());
            // 删除总体情况
            Map<String, Object> wmsCreRevWaterMainParams = new HashMap<>();
            wmsCreRevWaterMainParams.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevWaterMainParams.put("wms_cre_credit_line_customer_change_head_id",
                                         wms_cre_credit_line_customer_change_head_id);
            wmsCreRevWaterMainParams.put("warter_type", "0");
            wmscrerevwatermainDao.deleteRecords(wmsCreRevWaterMainParams);
            // 保存总体情况
            Map<String, Object> ztqkMap = (Map<String, Object>) singleDslsTab.get("ztqk");// 总体情况和备注
            WmsCreRevWaterMain wmsCreRevWaterMain = toWmsCreRevWaterMainEntity(ztqkMap,
                                                                               "0",
                                                                               approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                                               wms_cre_credit_line_customer_change_head_id,
                                                                               user);
            wmscrerevwatermainDao.save(wmsCreRevWaterMain);
            // 删除附件
            Map<String, Object> fileParams = new HashMap<>();
            fileParams.put("data_type", "2");
            fileParams.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            fileParams.put("wms_cre_credit_line_customer_change_head_id", wms_cre_credit_line_customer_change_head_id);
            wmsCreRevAttDao.deleteRecords(fileParams);
            List<Map<String, Object>> fileArr = (List<Map<String, Object>>) singleDslsTab.get("fileArr");// 附件
            for (Map<String, Object> m : fileArr)
            {
                WmsCreRevAtt file = toWmsCreRevAttEntity(m, "2", approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                         wms_cre_credit_line_customer_change_head_id);
                wmsCreRevAttDao.save(file);
            }
            // 查询对私流水
            Map<String, Object> wmsCreRevWaterRepayLoanParams = new HashMap<>();
            wmsCreRevWaterRepayLoanParams.put("repay_loan_type", "1");
            wmsCreRevWaterRepayLoanParams.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevWaterRepayLoanParams.put("wms_cre_credit_line_customer_change_head_id",
                                              wms_cre_credit_line_customer_change_head_id);
            List<Map<String, Object>> dslsxxPreDelete = wmsCreRevWaterRepayLoanDao.search(wmsCreRevWaterRepayLoanParams);
            // 删除对私流水子组数据
            for (Map<String, Object> m : dslsxxPreDelete)
            {
                Integer wms_cre_rev_water_repay_loan_id = Integer.parseInt(m.get("wms_cre_rev_water_repay_loan_id")
                                                                            .toString());
                Map<String, Object> wmsCreRevWaterRepayLoanLineParams = new HashMap<>();
                wmsCreRevWaterRepayLoanLineParams.put("wms_cre_rev_water_repay_loan_id",
                                                      wms_cre_rev_water_repay_loan_id);
                wmsCreRevWaterRepayLoanLineParams.put("wms_cre_credit_head_id",
                                                      approveWorkFlowVO.getWms_cre_credit_head_id());
                wmsCreRevWaterRepayLoanLineDao.deleteRecords(wmsCreRevWaterRepayLoanLineParams);

            }
            // 删除对私流水
            wmsCreRevWaterRepayLoanDao.deleteRecords(wmsCreRevWaterRepayLoanParams);
            // 保存对私流水
            List<Map<String, Object>> dslsxxArr = (List<Map<String, Object>>) singleDslsTab.get("dsls");// 对私流水信息数组
            for (Map<String, Object> dslsxx : dslsxxArr)
            {
                WmsCreRevWaterRepayLoan wmsCreRevWaterRepayLoan = toWmsCreRevWaterRepayLoanEntity(dslsxx,
                                                                                                  "1",
                                                                                                  approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                                                                  wms_cre_credit_line_customer_change_head_id);
                wmsCreRevWaterRepayLoanDao.save(wmsCreRevWaterRepayLoan);
                // 保存对私流水子组
                Integer wms_cre_rev_water_repay_loan_id = wmsCreRevWaterRepayLoan.getWms_cre_rev_water_repay_loan_id();
                List<Map<String, Object>> dslsxxGroupArr = (List<Map<String, Object>>) dslsxx.get("dslsGroup");// 对私流水组内数组
                for (Map<String, Object> dslsxxGroupRecord : dslsxxGroupArr)
                {
                    WmsCreRevWaterRepayLoanLine wmsCreRevWaterRepayLoanLine = toWmsCreRevWaterRepayLoanLineEntity(dslsxxGroupRecord,
                                                                                                                  approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                                                                                  wms_cre_rev_water_repay_loan_id);
                    wmsCreRevWaterRepayLoanLineDao.save(wmsCreRevWaterRepayLoanLine);
                }
            }
            // 查询还贷信息
            wmsCreRevWaterRepayLoanParams.put("repay_loan_type", "2");
            List<Map<String, Object>> hdxxPreDelete = wmsCreRevWaterRepayLoanDao.search(wmsCreRevWaterRepayLoanParams);
            // 删除还贷信息子组数据
            for (Map<String, Object> m : hdxxPreDelete)
            {
                Integer wms_cre_rev_water_repay_loan_id = Integer.parseInt(m.get("wms_cre_rev_water_repay_loan_id")
                                                                            .toString());
                Map<String, Object> wmsCreRevWaterRepayLoanLineParams = new HashMap<>();
                wmsCreRevWaterRepayLoanLineParams.put("wms_cre_rev_water_repay_loan_id",
                                                      wms_cre_rev_water_repay_loan_id);
                wmsCreRevWaterRepayLoanLineParams.put("wms_cre_credit_head_id",
                                                      approveWorkFlowVO.getWms_cre_credit_head_id());
                wmsCreRevWaterRepayLoanLineDao.deleteRecords(wmsCreRevWaterRepayLoanLineParams);

            }
            // 删除还贷信息
            wmsCreRevWaterRepayLoanDao.deleteRecords(wmsCreRevWaterRepayLoanParams);
            // 保存还贷信息
            List<Map<String, Object>> hdxxArr = (List<Map<String, Object>>) singleDslsTab.get("hdxx");// 还贷信息数组
            for (Map<String, Object> hdxx : hdxxArr)
            {
                WmsCreRevWaterRepayLoan wmsCreRevWaterRepayLoan = toWmsCreRevWaterRepayLoanEntity(hdxx,
                                                                                                  "2",
                                                                                                  approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                                                                  wms_cre_credit_line_customer_change_head_id);
                wmsCreRevWaterRepayLoanDao.save(wmsCreRevWaterRepayLoan);
                // 保存还贷子组
                Integer wms_cre_rev_water_repay_loan_id = wmsCreRevWaterRepayLoan.getWms_cre_rev_water_repay_loan_id();
                List<Map<String, Object>> hdxxGroupArr = (List<Map<String, Object>>) hdxx.get("hdxxGroup");// 还贷信息组内数组
                for (Map<String, Object> hdxxGroupRecord : hdxxGroupArr)
                {
                    WmsCreRevWaterRepayLoanLine wmsCreRevWaterRepayLoanLine = toWmsCreRevWaterRepayLoanLineEntity(hdxxGroupRecord,
                                                                                                                  approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                                                                                  wms_cre_rev_water_repay_loan_id);
                    wmsCreRevWaterRepayLoanLineDao.save(wmsCreRevWaterRepayLoanLine);
                }
            }
            // 删除存折信息
            Map<String, Object> wmsCreRevWaterPrivPassbookParams = new HashMap<>();
            wmsCreRevWaterPrivPassbookParams.put("wms_cre_credit_head_id",
                                                 approveWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevWaterPrivPassbookParams.put("wms_cre_credit_line_customer_change_head_id",
                                                 wms_cre_credit_line_customer_change_head_id);
            wmsCreRevWaterPrivPassbookDao.deleteRecords(wmsCreRevWaterPrivPassbookParams);
            // 保存存折信息
            List<Map<String, Object>> czxxArr = (List<Map<String, Object>>) singleDslsTab.get("czxx");// 存折信息数组
            for (Map<String, Object> czxx : czxxArr)
            {
                WmsCreRevWaterPrivPassbook wmsCreRevWaterPrivPassbook = toWmsCreRevWaterPrivPassbookEntity(czxx,
                                                                                                           approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                                                                           wms_cre_credit_line_customer_change_head_id);
                wmsCreRevWaterPrivPassbookDao.save(wmsCreRevWaterPrivPassbook);
            }
            // 删除工资信息
            Map<String, Object> wmsCreRevWaterPrivSalaryParams = new HashMap<>();
            wmsCreRevWaterPrivSalaryParams.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevWaterPrivSalaryParams.put("wms_cre_credit_line_customer_change_head_id",
                                               wms_cre_credit_line_customer_change_head_id);
            wmsCreRevWaterPrivSalaryDao.deleteRecords(wmsCreRevWaterPrivSalaryParams);
            // 保存工资信息
            List<Map<String, Object>> gzxxArr = (List<Map<String, Object>>) singleDslsTab.get("gzxx");// 工资信息数组
            for (Map<String, Object> gzxx : gzxxArr)
            {
                WmsCreRevWaterPrivSalary wmsCreRevWaterPrivSalary = toWmsCreRevWaterPrivSalaryEntity(gzxx,
                                                                                                     approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                                                                     wms_cre_credit_line_customer_change_head_id);
                wmsCreRevWaterPrivSalaryDao.save(wmsCreRevWaterPrivSalary);
            }
        }
        List<Map<String, Object>> dglsArr = jsonObjList(dgls);
        for (Map<String, Object> singleDglsTab : dglsArr)
        {// 对公流水
            Integer wms_cre_credit_line_customer_change_head_id = Integer.parseInt(singleDglsTab.get("wms_cre_credit_line_customer_change_head_id")
                                                                                                .toString());
            // 删除总体情况
            Map<String, Object> wmsCreRevWaterMainParams = new HashMap<>();
            wmsCreRevWaterMainParams.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevWaterMainParams.put("wms_cre_credit_line_customer_change_head_id",
                                         wms_cre_credit_line_customer_change_head_id);
            wmsCreRevWaterMainParams.put("warter_type", "1");
            wmscrerevwatermainDao.deleteRecords(wmsCreRevWaterMainParams);
            // 保存总体情况
            Map<String, Object> ztqkMap = (Map<String, Object>) singleDglsTab.get("ztqk");// 总体情况和备注
            WmsCreRevWaterMain wmsCreRevWaterMain = toWmsCreRevWaterMainEntity(ztqkMap,
                                                                               "1",
                                                                               approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                                               wms_cre_credit_line_customer_change_head_id,
                                                                               user);
            wmscrerevwatermainDao.save(wmsCreRevWaterMain);
            // 删除附件
            Map<String, Object> fileParams = new HashMap<>();
            fileParams.put("data_type", "3");
            fileParams.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            fileParams.put("wms_cre_credit_line_customer_change_head_id", wms_cre_credit_line_customer_change_head_id);
            wmsCreRevAttDao.deleteRecords(fileParams);
            // 保存附件
            List<Map<String, Object>> fileArr = (List<Map<String, Object>>) singleDglsTab.get("fileArr");// 附件
            for (Map<String, Object> m : fileArr)
            {
                WmsCreRevAtt file = toWmsCreRevAttEntity(m, "3", approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                         wms_cre_credit_line_customer_change_head_id);
                wmsCreRevAttDao.save(file);
            }
            // 查询对公流水
            Map<String, Object> wmsCreRevWaterRepayLoanParams = new HashMap<>();
            wmsCreRevWaterRepayLoanParams.put("repay_loan_type", "3");
            wmsCreRevWaterRepayLoanParams.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevWaterRepayLoanParams.put("wms_cre_credit_line_customer_change_head_id",
                                              wms_cre_credit_line_customer_change_head_id);
            List<Map<String, Object>> dglsxxPreDelete = wmsCreRevWaterRepayLoanDao.search(wmsCreRevWaterRepayLoanParams);
            // 删除对公流水子组数据
            for (Map<String, Object> m : dglsxxPreDelete)
            {
                Integer wms_cre_rev_water_repay_loan_id = Integer.parseInt(m.get("wms_cre_rev_water_repay_loan_id")
                                                                            .toString());
                Map<String, Object> wmsCreRevWaterRepayLoanLineParams = new HashMap<>();
                wmsCreRevWaterRepayLoanLineParams.put("wms_cre_rev_water_repay_loan_id",
                                                      wms_cre_rev_water_repay_loan_id);
                wmsCreRevWaterRepayLoanLineParams.put("wms_cre_credit_head_id",
                                                      approveWorkFlowVO.getWms_cre_credit_head_id());
                wmsCreRevWaterRepayLoanLineDao.deleteRecords(wmsCreRevWaterRepayLoanLineParams);
            }
            // 删除对公流水
            wmsCreRevWaterRepayLoanDao.deleteRecords(wmsCreRevWaterRepayLoanParams);
            // 保存对公流水
            List<Map<String, Object>> dglsxxArr = (List<Map<String, Object>>) singleDglsTab.get("dgls");// 对公流水信息数组
            for (Map<String, Object> dglsxx : dglsxxArr)
            {
                WmsCreRevWaterRepayLoan wmsCreRevWaterRepayLoan = toWmsCreRevWaterRepayLoanEntity(dglsxx,
                                                                                                  "3",
                                                                                                  approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                                                                  wms_cre_credit_line_customer_change_head_id);
                wmsCreRevWaterRepayLoanDao.save(wmsCreRevWaterRepayLoan);
                // 保存对公流水子组
                Integer wms_cre_rev_water_repay_loan_id = wmsCreRevWaterRepayLoan.getWms_cre_rev_water_repay_loan_id();
                List<Map<String, Object>> dglsxxGroupArr = (List<Map<String, Object>>) dglsxx.get("dglsGroup");// 对公流水组内数组
                for (Map<String, Object> dglsxxGroupRecord : dglsxxGroupArr)
                {
                    WmsCreRevWaterRepayLoanLine wmsCreRevWaterRepayLoanLine = toWmsCreRevWaterRepayLoanLineEntity(dglsxxGroupRecord,
                                                                                                                  approveWorkFlowVO.getWms_cre_credit_head_id(),
                                                                                                                  wms_cre_rev_water_repay_loan_id);
                    wmsCreRevWaterRepayLoanLineDao.save(wmsCreRevWaterRepayLoanLine);
                }
            }
        }
        wmsCreRevWaterModelDao.deleteRecordByFk(approveWorkFlowVO.getWms_cre_credit_head_id());
        WmsCreRevWaterModel wmsCreRevWaterModel = JsonUtil.jsonArrayToList(zysj, WmsCreRevWaterModel.class).get(0);
        wmsCreRevWaterModel.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());
        wmsCreRevWaterModelDao.save(wmsCreRevWaterModel);
        if ("1".equals(saveStatus))
        {
            Timestamp sysTime = new Timestamp(System.currentTimeMillis());
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("water_appro_user_id", user.getUserId());
            params.put("water_appro_user_name", user.getRealname());
            params.put("water_appro_timestamp", sysTime);
            params.put("water_appro_eval", water_appro_eval);
            params.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            wmscrecreditheaddao.updateRecord(params);
            approveWorkFlowVO.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());
            approveWorkFlowVO.setUser_id(user.getUserId());
            resStr = creditWorkFlowService.runTeamCheckApproveWorkFlow(approveWorkFlowVO);
        }
        return resStr;
    }

    @Override
    @Transactional
    public String saveForFd(String dsls, String dgls, String water_appro_eval, UserBean user,
                            WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, String saveStatus)
    {
        String water_appro_advice = approveHouseWorkFlowVO.getAdvice();
        String advice = "单据评价：";
        if ("1".equals(water_appro_eval))
        {
            advice += "优<br />";
        }
        else if ("2".equals(water_appro_eval))
        {
            advice += "良<br />";
        }
        else if ("3".equals(water_appro_eval))
        {
            advice += "中<br />";
        }
        else if ("4".equals(water_appro_eval))
        {
            advice += "差<br />";
        }
        approveHouseWorkFlowVO.setAdvice(advice + approveHouseWorkFlowVO.getAdvice());
        String resStr = "success";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, Object>> dslsArr = jsonObjList(dsls);
        for (Map<String, Object> singleDslsTab : dslsArr)
        {// 对私流水
            Integer wms_cre_credit_line_customer_change_head_id = Integer.parseInt(singleDslsTab.get("wms_cre_credit_line_customer_change_head_id")
                                                                                                .toString());
            // 删除总体情况
            Map<String, Object> wmsCreRevWaterMainParams = new HashMap<>();
            wmsCreRevWaterMainParams.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevWaterMainParams.put("wms_cre_credit_line_customer_change_head_id",
                                         wms_cre_credit_line_customer_change_head_id);
            wmsCreRevWaterMainParams.put("warter_type", "0");
            wmscrerevwatermainDao.deleteRecords(wmsCreRevWaterMainParams);
            // 保存总体情况
            Map<String, Object> ztqkMap = (Map<String, Object>) singleDslsTab.get("ztqk");// 总体情况和备注
            WmsCreRevWaterMain wmsCreRevWaterMain = toWmsCreRevWaterMainEntity(ztqkMap,
                                                                               "0",
                                                                               Integer.parseInt(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                                               wms_cre_credit_line_customer_change_head_id,
                                                                               user);
            wmscrerevwatermainDao.save(wmsCreRevWaterMain);
            // 删除附件
            Map<String, Object> fileParams = new HashMap<>();
            fileParams.put("data_type", "2");
            fileParams.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            fileParams.put("wms_cre_credit_line_customer_change_head_id", wms_cre_credit_line_customer_change_head_id);
            wmsCreRevAttDao.deleteRecords(fileParams);
            List<Map<String, Object>> fileArr = (List<Map<String, Object>>) singleDslsTab.get("fileArr");// 附件
            for (Map<String, Object> m : fileArr)
            {
                WmsCreRevAtt file = toWmsCreRevAttEntity(m,
                                                         "2",
                                                         Integer.parseInt(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                         wms_cre_credit_line_customer_change_head_id);
                wmsCreRevAttDao.save(file);
            }
            // 查询对私流水
            Map<String, Object> wmsCreRevWaterRepayLoanParams = new HashMap<>();
            wmsCreRevWaterRepayLoanParams.put("repay_loan_type", "1");
            wmsCreRevWaterRepayLoanParams.put("wms_cre_credit_head_id",
                                              approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevWaterRepayLoanParams.put("wms_cre_credit_line_customer_change_head_id",
                                              wms_cre_credit_line_customer_change_head_id);
            List<Map<String, Object>> dslsxxPreDelete = wmsCreRevWaterRepayLoanDao.search(wmsCreRevWaterRepayLoanParams);
            // 删除对私流水子组数据
            for (Map<String, Object> m : dslsxxPreDelete)
            {
                Integer wms_cre_rev_water_repay_loan_id = Integer.parseInt(m.get("wms_cre_rev_water_repay_loan_id")
                                                                            .toString());
                Map<String, Object> wmsCreRevWaterRepayLoanLineParams = new HashMap<>();
                wmsCreRevWaterRepayLoanLineParams.put("wms_cre_rev_water_repay_loan_id",
                                                      wms_cre_rev_water_repay_loan_id);
                wmsCreRevWaterRepayLoanLineParams.put("wms_cre_credit_head_id",
                                                      approveHouseWorkFlowVO.getWms_cre_credit_head_id());
                wmsCreRevWaterRepayLoanLineDao.deleteRecords(wmsCreRevWaterRepayLoanLineParams);

            }
            // 删除对私流水
            wmsCreRevWaterRepayLoanDao.deleteRecords(wmsCreRevWaterRepayLoanParams);
            // 保存对私流水
            List<Map<String, Object>> dslsxxArr = (List<Map<String, Object>>) singleDslsTab.get("dsls");// 对私流水信息数组
            for (Map<String, Object> dslsxx : dslsxxArr)
            {
                WmsCreRevWaterRepayLoan wmsCreRevWaterRepayLoan = toWmsCreRevWaterRepayLoanEntity(dslsxx,
                                                                                                  "1",
                                                                                                  Integer.parseInt(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                                                                  wms_cre_credit_line_customer_change_head_id);
                wmsCreRevWaterRepayLoanDao.save(wmsCreRevWaterRepayLoan);
                // 保存对私流水子组
                Integer wms_cre_rev_water_repay_loan_id = wmsCreRevWaterRepayLoan.getWms_cre_rev_water_repay_loan_id();
                List<Map<String, Object>> dslsxxGroupArr = (List<Map<String, Object>>) dslsxx.get("dslsGroup");// 对私流水组内数组
                for (Map<String, Object> dslsxxGroupRecord : dslsxxGroupArr)
                {
                    WmsCreRevWaterRepayLoanLine wmsCreRevWaterRepayLoanLine = toWmsCreRevWaterRepayLoanLineEntity(dslsxxGroupRecord,
                                                                                                                  Integer.parseInt(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                                                                                  wms_cre_rev_water_repay_loan_id);
                    wmsCreRevWaterRepayLoanLineDao.save(wmsCreRevWaterRepayLoanLine);
                }
            }
            // 查询还贷信息
            wmsCreRevWaterRepayLoanParams.put("repay_loan_type", "2");
            List<Map<String, Object>> hdxxPreDelete = wmsCreRevWaterRepayLoanDao.search(wmsCreRevWaterRepayLoanParams);
            // 删除还贷信息子组数据
            for (Map<String, Object> m : hdxxPreDelete)
            {
                Integer wms_cre_rev_water_repay_loan_id = Integer.parseInt(m.get("wms_cre_rev_water_repay_loan_id")
                                                                            .toString());
                Map<String, Object> wmsCreRevWaterRepayLoanLineParams = new HashMap<>();
                wmsCreRevWaterRepayLoanLineParams.put("wms_cre_rev_water_repay_loan_id",
                                                      wms_cre_rev_water_repay_loan_id);
                wmsCreRevWaterRepayLoanLineParams.put("wms_cre_credit_head_id",
                                                      approveHouseWorkFlowVO.getWms_cre_credit_head_id());
                wmsCreRevWaterRepayLoanLineDao.deleteRecords(wmsCreRevWaterRepayLoanLineParams);

            }
            // 删除还贷信息
            wmsCreRevWaterRepayLoanDao.deleteRecords(wmsCreRevWaterRepayLoanParams);
            // 保存还贷信息
            List<Map<String, Object>> hdxxArr = (List<Map<String, Object>>) singleDslsTab.get("hdxx");// 还贷信息数组
            for (Map<String, Object> hdxx : hdxxArr)
            {
                WmsCreRevWaterRepayLoan wmsCreRevWaterRepayLoan = toWmsCreRevWaterRepayLoanEntity(hdxx,
                                                                                                  "2",
                                                                                                  Integer.parseInt(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                                                                  wms_cre_credit_line_customer_change_head_id);
                wmsCreRevWaterRepayLoanDao.save(wmsCreRevWaterRepayLoan);
                // 保存还贷子组
                Integer wms_cre_rev_water_repay_loan_id = wmsCreRevWaterRepayLoan.getWms_cre_rev_water_repay_loan_id();
                List<Map<String, Object>> hdxxGroupArr = (List<Map<String, Object>>) hdxx.get("hdxxGroup");// 还贷信息组内数组
                for (Map<String, Object> hdxxGroupRecord : hdxxGroupArr)
                {
                    WmsCreRevWaterRepayLoanLine wmsCreRevWaterRepayLoanLine = toWmsCreRevWaterRepayLoanLineEntity(hdxxGroupRecord,
                                                                                                                  Integer.parseInt(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                                                                                  wms_cre_rev_water_repay_loan_id);
                    wmsCreRevWaterRepayLoanLineDao.save(wmsCreRevWaterRepayLoanLine);
                }
            }
            // 删除存折信息
            Map<String, Object> wmsCreRevWaterPrivPassbookParams = new HashMap<>();
            wmsCreRevWaterPrivPassbookParams.put("wms_cre_credit_head_id",
                                                 approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevWaterPrivPassbookParams.put("wms_cre_credit_line_customer_change_head_id",
                                                 wms_cre_credit_line_customer_change_head_id);
            wmsCreRevWaterPrivPassbookDao.deleteRecords(wmsCreRevWaterPrivPassbookParams);
            // 保存存折信息
            List<Map<String, Object>> czxxArr = (List<Map<String, Object>>) singleDslsTab.get("czxx");// 存折信息数组
            for (Map<String, Object> czxx : czxxArr)
            {
                WmsCreRevWaterPrivPassbook wmsCreRevWaterPrivPassbook = toWmsCreRevWaterPrivPassbookEntity(czxx,
                                                                                                           Integer.parseInt(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                                                                           wms_cre_credit_line_customer_change_head_id);
                wmsCreRevWaterPrivPassbookDao.save(wmsCreRevWaterPrivPassbook);
            }
            // 删除工资信息
            Map<String, Object> wmsCreRevWaterPrivSalaryParams = new HashMap<>();
            wmsCreRevWaterPrivSalaryParams.put("wms_cre_credit_head_id",
                                               approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevWaterPrivSalaryParams.put("wms_cre_credit_line_customer_change_head_id",
                                               wms_cre_credit_line_customer_change_head_id);
            wmsCreRevWaterPrivSalaryDao.deleteRecords(wmsCreRevWaterPrivSalaryParams);
            // 保存工资信息
            List<Map<String, Object>> gzxxArr = (List<Map<String, Object>>) singleDslsTab.get("gzxx");// 工资信息数组
            for (Map<String, Object> gzxx : gzxxArr)
            {
                WmsCreRevWaterPrivSalary wmsCreRevWaterPrivSalary = toWmsCreRevWaterPrivSalaryEntity(gzxx,
                                                                                                     Integer.parseInt(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                                                                     wms_cre_credit_line_customer_change_head_id);
                wmsCreRevWaterPrivSalaryDao.save(wmsCreRevWaterPrivSalary);
            }
        }
        List<Map<String, Object>> dglsArr = jsonObjList(dgls);
        for (Map<String, Object> singleDglsTab : dglsArr)
        {// 对公流水
            Integer wms_cre_credit_line_customer_change_head_id = Integer.parseInt(singleDglsTab.get("wms_cre_credit_line_customer_change_head_id")
                                                                                                .toString());
            // 删除总体情况
            Map<String, Object> wmsCreRevWaterMainParams = new HashMap<>();
            wmsCreRevWaterMainParams.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevWaterMainParams.put("wms_cre_credit_line_customer_change_head_id",
                                         wms_cre_credit_line_customer_change_head_id);
            wmsCreRevWaterMainParams.put("warter_type", "1");
            wmscrerevwatermainDao.deleteRecords(wmsCreRevWaterMainParams);
            // 保存总体情况
            Map<String, Object> ztqkMap = (Map<String, Object>) singleDglsTab.get("ztqk");// 总体情况和备注
            WmsCreRevWaterMain wmsCreRevWaterMain = toWmsCreRevWaterMainEntity(ztqkMap,
                                                                               "1",
                                                                               Integer.parseInt(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                                               wms_cre_credit_line_customer_change_head_id,
                                                                               user);
            wmscrerevwatermainDao.save(wmsCreRevWaterMain);
            // 删除附件
            Map<String, Object> fileParams = new HashMap<>();
            fileParams.put("data_type", "3");
            fileParams.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            fileParams.put("wms_cre_credit_line_customer_change_head_id", wms_cre_credit_line_customer_change_head_id);
            wmsCreRevAttDao.deleteRecords(fileParams);
            // 保存附件
            List<Map<String, Object>> fileArr = (List<Map<String, Object>>) singleDglsTab.get("fileArr");// 附件
            for (Map<String, Object> m : fileArr)
            {
                WmsCreRevAtt file = toWmsCreRevAttEntity(m,
                                                         "3",
                                                         Integer.parseInt(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                         wms_cre_credit_line_customer_change_head_id);
                wmsCreRevAttDao.save(file);
            }
            // 查询对公流水
            Map<String, Object> wmsCreRevWaterRepayLoanParams = new HashMap<>();
            wmsCreRevWaterRepayLoanParams.put("repay_loan_type", "3");
            wmsCreRevWaterRepayLoanParams.put("wms_cre_credit_head_id",
                                              approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            wmsCreRevWaterRepayLoanParams.put("wms_cre_credit_line_customer_change_head_id",
                                              wms_cre_credit_line_customer_change_head_id);
            List<Map<String, Object>> dglsxxPreDelete = wmsCreRevWaterRepayLoanDao.search(wmsCreRevWaterRepayLoanParams);
            // 删除对公流水子组数据
            for (Map<String, Object> m : dglsxxPreDelete)
            {
                Integer wms_cre_rev_water_repay_loan_id = Integer.parseInt(m.get("wms_cre_rev_water_repay_loan_id")
                                                                            .toString());
                Map<String, Object> wmsCreRevWaterRepayLoanLineParams = new HashMap<>();
                wmsCreRevWaterRepayLoanLineParams.put("wms_cre_rev_water_repay_loan_id",
                                                      wms_cre_rev_water_repay_loan_id);
                wmsCreRevWaterRepayLoanLineParams.put("wms_cre_credit_head_id",
                                                      approveHouseWorkFlowVO.getWms_cre_credit_head_id());
                wmsCreRevWaterRepayLoanLineDao.deleteRecords(wmsCreRevWaterRepayLoanLineParams);
            }
            // 删除对公流水
            wmsCreRevWaterRepayLoanDao.deleteRecords(wmsCreRevWaterRepayLoanParams);
            // 保存对公流水
            List<Map<String, Object>> dglsxxArr = (List<Map<String, Object>>) singleDglsTab.get("dgls");// 对公流水信息数组
            for (Map<String, Object> dglsxx : dglsxxArr)
            {
                WmsCreRevWaterRepayLoan wmsCreRevWaterRepayLoan = toWmsCreRevWaterRepayLoanEntity(dglsxx,
                                                                                                  "3",
                                                                                                  Integer.parseInt(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                                                                  wms_cre_credit_line_customer_change_head_id);
                wmsCreRevWaterRepayLoanDao.save(wmsCreRevWaterRepayLoan);
                // 保存对公流水子组
                Integer wms_cre_rev_water_repay_loan_id = wmsCreRevWaterRepayLoan.getWms_cre_rev_water_repay_loan_id();
                List<Map<String, Object>> dglsxxGroupArr = (List<Map<String, Object>>) dglsxx.get("dglsGroup");// 对公流水组内数组
                for (Map<String, Object> dglsxxGroupRecord : dglsxxGroupArr)
                {
                    WmsCreRevWaterRepayLoanLine wmsCreRevWaterRepayLoanLine = toWmsCreRevWaterRepayLoanLineEntity(dglsxxGroupRecord,
                                                                                                                  Integer.parseInt(approveHouseWorkFlowVO.getWms_cre_credit_head_id()),
                                                                                                                  wms_cre_rev_water_repay_loan_id);
                    wmsCreRevWaterRepayLoanLineDao.save(wmsCreRevWaterRepayLoanLine);
                }
            }
        }
        if ("1".equals(saveStatus))
        {
            Timestamp sysTime = new Timestamp(System.currentTimeMillis());
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("water_appro_user_id", user.getUserId());
            params.put("water_appro_user_name", user.getRealname());
            params.put("water_appro_timestamp", sysTime);
            params.put("water_appro_eval", water_appro_eval);
            params.put("water_appro_result", "true".equals(approveHouseWorkFlowVO.getPass()) ? "1" : "0");
            params.put("water_appro_advice", water_appro_advice);
            params.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            wmscrecreditheaddao.updateRecord(params);
            approveHouseWorkFlowVO.setUserId(String.valueOf(user.getUserId()));
            resStr = houseCreditWorkFlowService.runTeamHouseCheckApproveWorkFlow(approveHouseWorkFlowVO);
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreRevWaterMain bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevwatermainDao.update(bean); // update a record replace
                                                  // columns, nonsupport null
                                                  // val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    public Map<String, Object> getInfoByFK(Integer wms_cre_credit_head_id,
                                           Integer wms_cre_credit_line_customer_change_head_id)
    {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        paramMap.put("wms_cre_credit_line_customer_change_head_id", wms_cre_credit_line_customer_change_head_id);
        paramMap.put("warter_type", "0");
        WmsCreRevWaterMain wmsCreRevWaterMain = wmscrerevwatermainDao.getByFK(paramMap);// 总体情况
        resultMap.put("ztqk", wmsCreRevWaterMain);
        paramMap.put("repay_loan_type", "1");
        List<Map<String, Object>> wmsCreRevWaterRepayLoanList = wmsCreRevWaterRepayLoanDao.search(paramMap);// 对私流水
        paramMap.remove("repay_loan_type");
        List<Map<String, Object>> wmsCreRevWaterRepayLoanLineList = new ArrayList<>();
        for (Map<String, Object> entity : wmsCreRevWaterRepayLoanList)
        {
            Map<String, Object> innerResultMap = new HashMap<>();
            innerResultMap.put("wms_cre_rev_water_repay_loan_id", entity.get("wms_cre_rev_water_repay_loan_id"));
            innerResultMap.put("wms_cre_credit_head_id", entity.get("wms_cre_credit_head_id"));
            wmsCreRevWaterRepayLoanLineList = wmsCreRevWaterRepayLoanLineDao.search(innerResultMap);// 对私流水内组
            entity.put("dslsGroup", wmsCreRevWaterRepayLoanLineList);
        }
        resultMap.put("dsls", wmsCreRevWaterRepayLoanList);
        List<Map<String, Object>> wmsCreRevWaterPrivPassbookList = wmsCreRevWaterPrivPassbookDao.search(paramMap);// 存折信息
        resultMap.put("czxx", wmsCreRevWaterPrivPassbookList);
        List<Map<String, Object>> wmsCreRevWaterPrivSalaryList = wmsCreRevWaterPrivSalaryDao.search(paramMap);// 工资信息
        resultMap.put("gzxx", wmsCreRevWaterPrivSalaryList);
        paramMap.put("repay_loan_type", "2");
        List<Map<String, Object>> wmsCreRevWaterRepayLoanList2 = wmsCreRevWaterRepayLoanDao.search(paramMap);// 还贷信息
        paramMap.remove("repay_loan_type");
        List<Map<String, Object>> wmsCreRevWaterRepayLoanLineList2 = new ArrayList<>();
        for (Map<String, Object> entity : wmsCreRevWaterRepayLoanList2)
        {
            Map<String, Object> innerResultMap = new HashMap<>();
            innerResultMap.put("wms_cre_rev_water_repay_loan_id", entity.get("wms_cre_rev_water_repay_loan_id"));
            innerResultMap.put("wms_cre_credit_head_id", entity.get("wms_cre_credit_head_id"));
            wmsCreRevWaterRepayLoanLineList2 = wmsCreRevWaterRepayLoanLineDao.search(innerResultMap);// 对私流水内组
            entity.put("hdxxGroup", wmsCreRevWaterRepayLoanLineList2);
        }
        resultMap.put("hdxx", wmsCreRevWaterRepayLoanList2);
        paramMap.put("data_type", "2");
        List<Map<String, Object>> wmsCreRevAttList = wmsCreRevAttDao.search(paramMap);// 附件信息
        resultMap.put("fjxx", wmsCreRevAttList);
        return resultMap;
    }

    @Override
    public Map<String, Object> getInfoByFKForAdd(Integer wms_cre_credit_head_id,
                                                 Integer wms_cre_credit_line_customer_change_head_id)
    {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        paramMap.put("wms_cre_credit_line_customer_change_head_id", wms_cre_credit_line_customer_change_head_id);
        paramMap.put("warter_type", "1");
        WmsCreRevWaterMain wmsCreRevWaterMain = wmscrerevwatermainDao.getByFK(paramMap);// 总体情况
        resultMap.put("ztqk", wmsCreRevWaterMain);
        paramMap.put("repay_loan_type", "3");
        List<Map<String, Object>> wmsCreRevWaterRepayLoanList = wmsCreRevWaterRepayLoanDao.search(paramMap);// 对公流水
        paramMap.remove("repay_loan_type");
        List<Map<String, Object>> wmsCreRevWaterRepayLoanLineList = new ArrayList<>();
        for (Map<String, Object> entity : wmsCreRevWaterRepayLoanList)
        {
            Map<String, Object> innerResultMap = new HashMap<>();
            innerResultMap.put("wms_cre_rev_water_repay_loan_id", entity.get("wms_cre_rev_water_repay_loan_id"));
            innerResultMap.put("wms_cre_credit_head_id", entity.get("wms_cre_credit_head_id"));
            wmsCreRevWaterRepayLoanLineList = wmsCreRevWaterRepayLoanLineDao.search(innerResultMap);// 对私流水内组
            entity.put("dglsGroup", wmsCreRevWaterRepayLoanLineList);
        }
        resultMap.put("dgls", wmsCreRevWaterRepayLoanList);
        paramMap.put("data_type", "3");
        List<Map<String, Object>> wmsCreRevAttList = wmsCreRevAttDao.search(paramMap);// 附件信息
        resultMap.put("fjxx", wmsCreRevAttList);
        return resultMap;
    }

    @Override
    @Transactional
    public String waterToBack(WmsCreditWorkFlowVO approveWorkFlowVO, UserBean user)
    {
        String resStr = "success";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("is_review_back", "1");
        paramMap.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        wmscrecreditheaddao.updateRecord(paramMap);
        //查询
        Map<String, Object> resMap = wmscrecreditheaddao.getWmsCreCredReturnInfoById(approveWorkFlowVO.getWms_cre_credit_head_id());
        Task task = taskService.createTaskQuery().taskId(approveWorkFlowVO.getTaskId()).singleResult();
        resMap.put("audit_group", task.getName());//退件审核组
        resMap.put("audit_reason", approveWorkFlowVO.getAdvice());//退件原因
        //插入信贷退件查询表
        wmsCreCreditReturnSearchDao.addWmsCreCreditReturnInfo(resMap);
        approveWorkFlowVO.setUser_id(user.getUserId());
        resStr = creditWorkFlowService.creCheckCreditSupply(approveWorkFlowVO);// 流程处理
        return resStr;
    }

    @Override
    @Transactional
    public String waterToBackForFd(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, UserBean user)
    {
        String resStr = "success";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("is_review_back", "1");
        paramMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
        wmscrecreditheaddao.updateRecord(paramMap);
        approveHouseWorkFlowVO.setUserId(user.getUserId().toString());
        if("2".equals(approveHouseWorkFlowVO.getEdition_num())){//1为旧流程2为新流程
        	if("2".equals(approveHouseWorkFlowVO.getVersion_number())){
        		approveHouseWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.UPHOUSINGLOANPROCESS);
        	}else{
        		approveHouseWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
        	}
        	//approveHouseWorkFlowVO.setDebtkey("3");
        	approveHouseWorkFlowVO.setPass("supply");
        	wmsLoanWorkFlowService.publicApproval(approveHouseWorkFlowVO);
        }else if("1".equals(approveHouseWorkFlowVO.getEdition_num())){
        	 resStr = houseCreditWorkFlowService.creCheckHouseSupply(approveHouseWorkFlowVO);// 流程处理
        }
       
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreRevWaterMain() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreRevWaterMain> getListByEntity(WmsCreRevWaterMain queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreRevWaterMain> beanList = wmscrerevwatermainDao.getListByEntity(queryInfo);
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

    // 生成总体情况信息实体类
    private WmsCreRevWaterMain toWmsCreRevWaterMainEntity(Map<String, Object> ztqkMap, String warter_type,
                                                          Integer wms_cre_credit_head_id,
                                                          Integer wms_cre_credit_line_customer_change_head_id,
                                                          UserBean user)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        WmsCreRevWaterMain wmsCreRevWaterMain = new WmsCreRevWaterMain();
        wmsCreRevWaterMain.setWarter_type(warter_type);
        wmsCreRevWaterMain.setIs_standard((String) ztqkMap.get("is_standard"));
        wmsCreRevWaterMain.setEvaluation((String) ztqkMap.get("evaluation"));
        wmsCreRevWaterMain.setLiabilities((String) ztqkMap.get("liabilities"));
        if (StringUtil.isNotBlank((String) ztqkMap.get("warter_copies")))
        {
            wmsCreRevWaterMain.setWarter_copies(ztqkMap.get("warter_copies").toString());
        }
        if (StringUtil.isNotBlank((String) ztqkMap.get("pri_warter_copies")))
        {
            wmsCreRevWaterMain.setPri_warter_copies(ztqkMap.get("pri_warter_copies").toString());
        }
        if (StringUtil.isNotBlank((String) ztqkMap.get("repay_copies")))
        {
            wmsCreRevWaterMain.setRepay_copies(ztqkMap.get("repay_copies").toString());
        }
        wmsCreRevWaterMain.setRemark((String) ztqkMap.get("remark"));
        wmsCreRevWaterMain.setComp_type((String) ztqkMap.get("comp_type"));
        wmsCreRevWaterMain.setIs_credit_rating_overdue((String) ztqkMap.get("is_credit_rating_overdue"));
        wmsCreRevWaterMain.setIs_need_supple((String) ztqkMap.get("is_need_supple"));
        if (StringUtil.isNotBlank((String) ztqkMap.get("supple_date")))
        {
            wmsCreRevWaterMain.setSupple_date(ztqkMap.get("supple_date").toString());
        }
        wmsCreRevWaterMain.setHas_passbook((String) ztqkMap.get("has_passbook"));
        wmsCreRevWaterMain.setHas_salary((String) ztqkMap.get("has_salary"));
        wmsCreRevWaterMain.setWms_cre_credit_line_customer_change_head_id(wms_cre_credit_line_customer_change_head_id);
        wmsCreRevWaterMain.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        wmsCreRevWaterMain.setCreate_user_id(user.getUserId());
        wmsCreRevWaterMain.setCreate_user_name(user.getRealname());
        wmsCreRevWaterMain.setCreate_timestamp(sysTime);
        wmsCreRevWaterMain.setLast_update_user_id(user.getUserId());
        wmsCreRevWaterMain.setLast_update_timestamp(sysTime);
        wmsCreRevWaterMain.setEnable_flag("1");
        return wmsCreRevWaterMain;
    }

    // 生成附件信息实体类
    private WmsCreRevAtt toWmsCreRevAttEntity(Map<String, Object> m, String data_type, Integer wms_cre_credit_head_id,
                                              Integer wms_cre_credit_line_customer_change_head_id)
    {
        WmsCreRevAtt file = new WmsCreRevAtt();
        file.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        file.setData_type(data_type);
        file.setWms_cre_credit_line_customer_change_head_id(wms_cre_credit_line_customer_change_head_id);
        file.setAttachment_old_name(m.get("attachment_old_name").toString());
        file.setAttachment_new_name(m.get("attachment_new_name").toString());
        file.setAttachment_address(m.get("attachment_address").toString());
        file.setAttachment_type(m.get("attachment_type").toString());
        return file;
    }

    // 生成对私、对公、还贷信息实体类
    private WmsCreRevWaterRepayLoan toWmsCreRevWaterRepayLoanEntity(Map<String, Object> dslsxx, String repay_loan_type,
                                                                    Integer wms_cre_credit_head_id,
                                                                    Integer wms_cre_credit_line_customer_change_head_id)
    {
        WmsCreRevWaterRepayLoan wmsCreRevWaterRepayLoan = new WmsCreRevWaterRepayLoan();
        wmsCreRevWaterRepayLoan.setRepay_loan_type(repay_loan_type);
        wmsCreRevWaterRepayLoan.setName((String) dslsxx.get("name"));
        wmsCreRevWaterRepayLoan.setBank_name((String) dslsxx.get("bank_name"));
        wmsCreRevWaterRepayLoan.setBeak_card_no_tail((String) dslsxx.get("beak_card_no_tail"));
        wmsCreRevWaterRepayLoan.setWater_begin_month((String) dslsxx.get("water_begin_month"));
        wmsCreRevWaterRepayLoan.setWater_end_month((String) dslsxx.get("water_end_month"));
        if (StringUtil.isNotBlank((String) dslsxx.get("account_balance")))
        {
            wmsCreRevWaterRepayLoan.setAccount_balance(dslsxx.get("account_balance").toString());
        }
        wmsCreRevWaterRepayLoan.setIs_repay((String) dslsxx.get("is_repay"));
        wmsCreRevWaterRepayLoan.setIs_pay_taxes((String) dslsxx.get("is_pay_taxes"));
        wmsCreRevWaterRepayLoan.setIs_late_pay_taxes((String) dslsxx.get("is_late_pay_taxes"));
        wmsCreRevWaterRepayLoan.setIs_pay_wages((String) dslsxx.get("is_pay_wages"));
        wmsCreRevWaterRepayLoan.setIs_arrears((String) dslsxx.get("is_arrears"));
        if (StringUtil.isNotBlank((String) dslsxx.get("late_pay_taxes_days")))
        {
            wmsCreRevWaterRepayLoan.setLate_pay_taxes_days(dslsxx.get("late_pay_taxes_days").toString());
        }
        if (StringUtil.isNotBlank((String) dslsxx.get("arrears_days")))
        {
            wmsCreRevWaterRepayLoan.setArrears_days(dslsxx.get("arrears_days").toString());
        }
        wmsCreRevWaterRepayLoan.setRemark((String) dslsxx.get("remark"));
        wmsCreRevWaterRepayLoan.setWms_cre_credit_line_customer_change_head_id(wms_cre_credit_line_customer_change_head_id);
        wmsCreRevWaterRepayLoan.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        return wmsCreRevWaterRepayLoan;
    }

    // 生成子信息实体类
    private WmsCreRevWaterRepayLoanLine toWmsCreRevWaterRepayLoanLineEntity(Map<String, Object> dslsxxGroupRecord,
                                                                            Integer wms_cre_credit_head_id,
                                                                            Integer wms_cre_rev_water_repay_loan_id)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        WmsCreRevWaterRepayLoanLine wmsCreRevWaterRepayLoanLine = new WmsCreRevWaterRepayLoanLine();
        wmsCreRevWaterRepayLoanLine.setRepay_name((String) dslsxxGroupRecord.get("repay_name"));
        if (StringUtil.isNotBlank((String) dslsxxGroupRecord.get("repay_date")))
        {
            wmsCreRevWaterRepayLoanLine.setRepay_date(dslsxxGroupRecord.get("repay_date").toString());
        }
        if (StringUtil.isNotBlank((String) dslsxxGroupRecord.get("loan_repay_date")))
        {
            wmsCreRevWaterRepayLoanLine.setLoan_repay_date(dslsxxGroupRecord.get("loan_repay_date").toString());
        }
        if (StringUtil.isNotBlank((String) dslsxxGroupRecord.get("repay_account")))
        {
            wmsCreRevWaterRepayLoanLine.setRepay_account(dslsxxGroupRecord.get("repay_account").toString());
        }
        wmsCreRevWaterRepayLoanLine.setIs_overdue((String) dslsxxGroupRecord.get("is_overdue"));
        wmsCreRevWaterRepayLoanLine.setRemark((String) dslsxxGroupRecord.get("remark"));
        wmsCreRevWaterRepayLoanLine.setWms_cre_rev_water_repay_loan_id(wms_cre_rev_water_repay_loan_id);
        wmsCreRevWaterRepayLoanLine.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        if (StringUtil.isNotBlank((String) dslsxxGroupRecord.get("loan_date")))
        {
            wmsCreRevWaterRepayLoanLine.setLoan_date(dslsxxGroupRecord.get("loan_date").toString());
        }
        if (StringUtil.isNotBlank((String) dslsxxGroupRecord.get("loan_amount")))
        {
            wmsCreRevWaterRepayLoanLine.setLoan_amount(dslsxxGroupRecord.get("loan_amount").toString());
        }
        if (StringUtil.isNotBlank((String) dslsxxGroupRecord.get("overdue_days")))
        {
            wmsCreRevWaterRepayLoanLine.setOverdue_days(dslsxxGroupRecord.get("overdue_days").toString());
        }
        if (StringUtil.isNotBlank((String) dslsxxGroupRecord.get("late_fee")))
        {
            wmsCreRevWaterRepayLoanLine.setLate_fee(dslsxxGroupRecord.get("late_fee").toString());
        }
        return wmsCreRevWaterRepayLoanLine;
    }

    // 生成存折信息实体类
    private WmsCreRevWaterPrivPassbook toWmsCreRevWaterPrivPassbookEntity(Map<String, Object> czxx,
                                                                          Integer wms_cre_credit_head_id,
                                                                          Integer wms_cre_credit_line_customer_change_head_id)
    {
        WmsCreRevWaterPrivPassbook wmsCreRevWaterPrivPassbook = new WmsCreRevWaterPrivPassbook();
        wmsCreRevWaterPrivPassbook.setPsbook_name((String) czxx.get("psbook_name"));
        wmsCreRevWaterPrivPassbook.setBank_name((String) czxx.get("bank_name"));
        wmsCreRevWaterPrivPassbook.setIs_check((String) czxx.get("is_check"));
        if (StringUtil.isNotBlank((String) czxx.get("account_balance")))
        {
            wmsCreRevWaterPrivPassbook.setAccount_balance(czxx.get("account_balance").toString());
        }
        wmsCreRevWaterPrivPassbook.setWms_cre_credit_line_customer_change_head_id(wms_cre_credit_line_customer_change_head_id);
        wmsCreRevWaterPrivPassbook.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        return wmsCreRevWaterPrivPassbook;
    }

    // 生成工资信息实体类
    private WmsCreRevWaterPrivSalary toWmsCreRevWaterPrivSalaryEntity(Map<String, Object> gzxx,
                                                                      Integer wms_cre_credit_head_id,
                                                                      Integer wms_cre_credit_line_customer_change_head_id)
    {
        WmsCreRevWaterPrivSalary wmsCreRevWaterPrivSalary = new WmsCreRevWaterPrivSalary();
        if (gzxx.get("name") != null)
        {
            wmsCreRevWaterPrivSalary.setName((String) gzxx.get("name"));
        }
        if (gzxx.get("bank_name") != null)
        {
            wmsCreRevWaterPrivSalary.setBank_name((String) gzxx.get("bank_name"));
        }
        if (gzxx.get("water_begin_month") != null)
        {
            wmsCreRevWaterPrivSalary.setWater_begin_month((String) gzxx.get("water_begin_month"));
        }
        if (gzxx.get("water_end_month") != null)
        {
            wmsCreRevWaterPrivSalary.setWater_end_month((String) gzxx.get("water_end_month"));
        }
        if (gzxx.get("pay_type") != null)
        {
            wmsCreRevWaterPrivSalary.setPay_type((String) gzxx.get("pay_type"));
        }
        if (gzxx.get("account_balance") != null && StringUtil.isNotBlank((String) gzxx.get("account_balance")))
        {
            wmsCreRevWaterPrivSalary.setAccount_balance(gzxx.get("account_balance").toString());
        }
        if (gzxx.get("fixed_pay_day") != null && StringUtil.isNotBlank((String) gzxx.get("fixed_pay_day")))
        {
            wmsCreRevWaterPrivSalary.setFixed_pay_day(gzxx.get("fixed_pay_day").toString());
        }
        if (gzxx.get("fixed_payment") != null && StringUtil.isNotBlank((String) gzxx.get("fixed_payment")))
        {
            wmsCreRevWaterPrivSalary.setFixed_payment(gzxx.get("fixed_payment").toString());
        }
        if (gzxx.get("un_fixed_pay_day") != null)
        {
            wmsCreRevWaterPrivSalary.setUn_fixed_pay_day((String) gzxx.get("un_fixed_pay_day"));
        }
        if (gzxx.get("un_fixed_payment") != null)
        {
            wmsCreRevWaterPrivSalary.setUn_fixed_payment((String) gzxx.get("un_fixed_payment"));
        }
        wmsCreRevWaterPrivSalary.setRemark((String) gzxx.get("remark"));
        wmsCreRevWaterPrivSalary.setWms_cre_credit_line_customer_change_head_id(wms_cre_credit_line_customer_change_head_id);
        wmsCreRevWaterPrivSalary.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        return wmsCreRevWaterPrivSalary;
    }
}
