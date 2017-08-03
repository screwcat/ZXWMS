package com.zx.emanage.loancheck.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.service.IWmsHouseCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsCreditWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineBankStreamDao;
import com.zx.emanage.loancheck.persist.WmsCreCreditLinePersonCreditDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineBankStreamService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineBankStreamSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineBankStream;
import com.zx.emanage.util.gen.entity.WmsCreCreditLinePersonCredit;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlinebankstreamService")
public class WmsCreCreditLineBankStreamServiceImpl implements IWmsCreCreditLineBankStreamService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineBankStreamServiceImpl.class);

    @Autowired
    private WmsCreCreditLineBankStreamDao wmscrecreditlinebankstreamDao;

    @Autowired
    private WmsCreCreditLinePersonCreditDao wmscrecreditlinepersoncreditDao;

    @Autowired
    private WmsCreCreditHeadDao wmscrecreditheaddao;

    @Autowired
    private IWmsCreditWorkFlowService creditWorkFlowService;

    @Autowired
    private IWmsHouseCreditWorkFlowService houseCreditWorkFlowService;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineBankStreamSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlinebankstreamDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineBankStreamSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlinebankstreamDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlinebankstreamDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineBankStream getInfoByPK(Integer wms_cre_credit_line_bank_stream_id)
    {
        return wmscrecreditlinebankstreamDao.get(wms_cre_credit_line_bank_stream_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineBankStream bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinebankstreamDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineBankStream bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlinebankstreamDao.update(bean); // update a record
                                                          // replace columns,
                                                          // nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreCreditLineBankStream() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCreditLineBankStream> getListByEntity(WmsCreCreditLineBankStream queryInfo,
                                                             Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLineBankStream> beanList = wmscrecreditlinebankstreamDao.getListByEntity(queryInfo);
        return beanList;
    }

    /**
     * Description 保存审批数据
     * 
     * @return "success"
     * @author ry
     */
    @Override
    @Transactional
    public String doSubSave(String divwt1_data, String divwt2_data, String divwt3_data, String divwt4_data,
                            int wms_cre_credit_head_id, UserBean user, WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        // 个人银行流水
        List<WmsCreCreditLineBankStream> gryhlsList = JsonUtil.jsonArrayToList(divwt1_data,
                                                                               WmsCreCreditLineBankStream.class);
        // 企业银行流水
        List<WmsCreCreditLineBankStream> qyyhlsList = JsonUtil.jsonArrayToList(divwt2_data,
                                                                               WmsCreCreditLineBankStream.class);
        // 银行贷款
        List<WmsCreCreditLinePersonCredit> yhdkList = JsonUtil.jsonArrayToList(divwt3_data,
                                                                               WmsCreCreditLinePersonCredit.class);
        // 非银行贷款
        List<WmsCreCreditLinePersonCredit> fydkList = JsonUtil.jsonArrayToList(divwt4_data,
                                                                               WmsCreCreditLinePersonCredit.class);

        String resStr = "success";
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());

        // 保存个人银行流水数据
        for (WmsCreCreditLineBankStream gryh : gryhlsList)
        {
            gryh.setBank_stream_type("1");
            gryh.setCreate_user_id(user.getUserId());
            gryh.setCreate_user_name(user.getRealname());
            gryh.setCreate_timestamp(sysTime);
            gryh.setLast_update_user_id(user.getUserId());
            gryh.setLast_update_timestamp(sysTime);
            gryh.setEnable_flag("1");
            gryh.setWms_cre_credit_head_id(wms_cre_credit_head_id);
            wmscrecreditlinebankstreamDao.save(gryh);
        }

        // 保存企业银行流水数据
        for (WmsCreCreditLineBankStream qyyh : qyyhlsList)
        {
            qyyh.setBank_stream_type("2");
            qyyh.setCreate_user_id(user.getUserId());
            qyyh.setCreate_user_name(user.getRealname());
            qyyh.setCreate_timestamp(sysTime);
            qyyh.setLast_update_user_id(user.getUserId());
            qyyh.setLast_update_timestamp(sysTime);
            qyyh.setEnable_flag("1");
            qyyh.setWms_cre_credit_head_id(wms_cre_credit_head_id);
            wmscrecreditlinebankstreamDao.save(qyyh);
        }

        // 保存个人银行贷款数据
        for (WmsCreCreditLinePersonCredit yhdk : yhdkList)
        {
            yhdk.setPerson_credit_type("1");
            yhdk.setCreate_user_id(user.getUserId());
            yhdk.setCreate_user_name(user.getRealname());
            yhdk.setCreate_timestamp(sysTime);
            yhdk.setLast_update_user_id(user.getUserId());
            yhdk.setLast_update_timestamp(sysTime);
            yhdk.setEnable_flag("1");
            yhdk.setWms_cre_credit_head_id(wms_cre_credit_head_id);
            wmscrecreditlinepersoncreditDao.save(yhdk);
        }

        // 保存个人非银贷款数据
        for (WmsCreCreditLinePersonCredit fydk : fydkList)
        {
            fydk.setPerson_credit_type("2");
            fydk.setCreate_user_id(user.getUserId());
            fydk.setCreate_user_name(user.getRealname());
            fydk.setCreate_timestamp(sysTime);
            fydk.setLast_update_user_id(user.getUserId());
            fydk.setLast_update_timestamp(sysTime);
            fydk.setEnable_flag("1");
            fydk.setWms_cre_credit_head_id(wms_cre_credit_head_id);
            wmscrecreditlinepersoncreditDao.save(fydk);
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("water_appro_user_id", user.getUserId());
        params.put("water_appro_user_name", user.getRealname());
        params.put("water_appro_timestamp", sysTime);
        params.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        wmscrecreditheaddao.updateRecord(params);
        //
        approveWorkFlowVO.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        approveWorkFlowVO.setUser_id(user.getUserId());
        creditWorkFlowService.runTeamCheckApproveWorkFlow(approveWorkFlowVO);

        return resStr;
    }

    /**
     * Description 保存审批数据
     * 
     * @return "success"
     * @author wangyishun
     */
    @Override
    @Transactional
    public String doSubSaveFd(String divwt1_data, String divwt2_data, String divwt3_data, String divwt4_data,
                              UserBean user, WmsHouseCreditWorkFlowVO vo)
    {
        // 个人银行流水
        List<WmsCreCreditLineBankStream> gryhlsList = JsonUtil.jsonArrayToList(divwt1_data,
                                                                               WmsCreCreditLineBankStream.class);
        // 企业银行流水
        List<WmsCreCreditLineBankStream> qyyhlsList = JsonUtil.jsonArrayToList(divwt2_data,
                                                                               WmsCreCreditLineBankStream.class);
        // 银行贷款
        List<WmsCreCreditLinePersonCredit> yhdkList = JsonUtil.jsonArrayToList(divwt3_data,
                                                                               WmsCreCreditLinePersonCredit.class);
        // 非银行贷款
        List<WmsCreCreditLinePersonCredit> fydkList = JsonUtil.jsonArrayToList(divwt4_data,
                                                                               WmsCreCreditLinePersonCredit.class);

        String resStr = "success";
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());

        // 保存个人银行流水数据
        for (WmsCreCreditLineBankStream gryh : gryhlsList)
        {
            gryh.setBank_stream_type("1");
            gryh.setCreate_user_id(user.getUserId());
            gryh.setCreate_user_name(user.getRealname());
            gryh.setCreate_timestamp(sysTime);
            gryh.setLast_update_user_id(user.getUserId());
            gryh.setLast_update_timestamp(sysTime);
            gryh.setEnable_flag("1");
            gryh.setWms_cre_credit_head_id(Integer.parseInt(vo.getWms_cre_credit_head_id()));
            wmscrecreditlinebankstreamDao.save(gryh);
        }

        // 保存企业银行流水数据
        for (WmsCreCreditLineBankStream qyyh : qyyhlsList)
        {
            qyyh.setBank_stream_type("2");
            qyyh.setCreate_user_id(user.getUserId());
            qyyh.setCreate_user_name(user.getRealname());
            qyyh.setCreate_timestamp(sysTime);
            qyyh.setLast_update_user_id(user.getUserId());
            qyyh.setLast_update_timestamp(sysTime);
            qyyh.setEnable_flag("1");
            qyyh.setWms_cre_credit_head_id(Integer.parseInt(vo.getWms_cre_credit_head_id()));
            wmscrecreditlinebankstreamDao.save(qyyh);
        }

        // 保存个人银行贷款数据
        for (WmsCreCreditLinePersonCredit yhdk : yhdkList)
        {
            yhdk.setPerson_credit_type("1");
            yhdk.setCreate_user_id(user.getUserId());
            yhdk.setCreate_user_name(user.getRealname());
            yhdk.setCreate_timestamp(sysTime);
            yhdk.setLast_update_user_id(user.getUserId());
            yhdk.setLast_update_timestamp(sysTime);
            yhdk.setEnable_flag("1");
            yhdk.setWms_cre_credit_head_id(Integer.parseInt(vo.getWms_cre_credit_head_id()));
            wmscrecreditlinepersoncreditDao.save(yhdk);
        }

        // 保存个人非银贷款数据
        for (WmsCreCreditLinePersonCredit fydk : fydkList)
        {
            fydk.setPerson_credit_type("2");
            fydk.setCreate_user_id(user.getUserId());
            fydk.setCreate_user_name(user.getRealname());
            fydk.setCreate_timestamp(sysTime);
            fydk.setLast_update_user_id(user.getUserId());
            fydk.setLast_update_timestamp(sysTime);
            fydk.setEnable_flag("1");
            fydk.setWms_cre_credit_head_id(Integer.parseInt(vo.getWms_cre_credit_head_id()));
            wmscrecreditlinepersoncreditDao.save(fydk);
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("water_appro_user_id", user.getUserId());
        params.put("water_appro_user_name", user.getRealname());
        params.put("water_appro_timestamp", sysTime);
        params.put("water_appro_result", vo.getPass().equals("true") ? "1" : "0");
        params.put("wms_cre_credit_head_id", Integer.parseInt(vo.getWms_cre_credit_head_id()));
        wmscrecreditheaddao.updateRecord(params);
        vo.setUserId(String.valueOf(user.getUserId()));
        houseCreditWorkFlowService.runTeamHouseCheckApproveWorkFlow(vo);
        return resStr;
    }

    /**
     * Description 查询审批数据
     * 
     * @return map
     * @author ry
     */
    @Override
    public Map<String, Object> doQuery(int wms_cre_credit_head_id, String query_type, String query_tb)
    {

        List<WmsCreCreditLineBankStream> list1 = null;
        List<WmsCreCreditLinePersonCredit> list2 = null;

        Map<String, Object> resMap = new HashMap<String, Object>();
        if (query_tb.equals("1"))
        {
            WmsCreCreditLineBankStream mcclbs = new WmsCreCreditLineBankStream();
            mcclbs.setWms_cre_credit_head_id(wms_cre_credit_head_id);
            mcclbs.setBank_stream_type(query_type);
            list1 = wmscrecreditlinebankstreamDao.queryListByEntity(mcclbs);
            resMap.put("Rows", list1);
        }
        else if (query_tb.equals("2"))
        {
            WmsCreCreditLinePersonCredit mcclpc = new WmsCreCreditLinePersonCredit();
            mcclpc.setWms_cre_credit_head_id(wms_cre_credit_head_id);
            mcclpc.setPerson_credit_type(query_type);
            list2 = wmscrecreditlinepersoncreditDao.queryListByEntity(mcclpc);
            resMap.put("Rows", list2);
        }

        return resMap;
    }
}
