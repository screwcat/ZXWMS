package com.zx.emanage.cremanage.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingAttDao;
import com.zx.emanage.cremanage.service.IWmsCreHousingAttService;
import com.zx.emanage.cremanage.service.IWmsHouseCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCreCreditHeadHouseSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsCreHousingAttSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanappro.persist.WmsCreApproBorrowProtocolDao;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsCreHousingAtt;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreHousingAttServiceImpl
 * 模块名称：附件表
 * @Description: 内容摘要：附件相关内容
 * @author baisong
 * @date 2016年12月30日
 * @version V1.0
 * history:
 * 1、2016年12月30日 baisong 创建文件
 */

@Service("wmscrehousingattService")
public class WmsCreHousingAttServiceImpl implements IWmsCreHousingAttService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreHousingAttServiceImpl.class);

    @Autowired
    private WmsCreHousingAttDao wmscrehousingattDao;

    @Autowired
    private WmsCreCreditHeadDao wmscrecreditheadDao;

    @Autowired
    private IWmsHouseCreditWorkFlowService houseCreditWorkFlowService;
    @Autowired
    private IWmsLoanWorkFlowService   wmsLoanWorkFlowService;//新房贷贷款流程

    @Autowired
    private WmsCreApproBorrowProtocolDao wmsCreApproBorrowProtocolDao;// 合同

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreHousingAttSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        List<Map<String, Object>> list = wmscrehousingattDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithoutPagingForHt(WmsCreHousingAttSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        paramMap.put("wms_cre_credit_head_id_arr", queryInfo.getWms_cre_credit_head_id_arr());
        List<Map<String, Object>> list = wmscrehousingattDao.searchForHt(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreHousingAttSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrehousingattDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrehousingattDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreHousingAtt getInfoByPK(Integer wms_cre_housing_att_id)
    {
        return wmscrehousingattDao.getInfoByFK(wms_cre_housing_att_id);
    }

    @Override
    @Transactional
    public String doSQSave(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, String fileArr, UserBean user)
    {
        String result = "OK";
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
        List<WmsCreHousingAtt> Lattachment = JsonUtil.jsonArrayToList(fileArr, WmsCreHousingAtt.class); // 前台json附件路径数据转换为list
        /*----------------------------------------------WmsPostLoanMigration资料附件�?begin----------------------------------------------*/
        // 附件先更新再添加
        WmsCreHousingAtt mplmDropThisMig = new WmsCreHousingAtt();
        mplmDropThisMig.setWms_cre_credit_head_id(Integer.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()));
        mplmDropThisMig.setEnable_flag("0");// 是否有效
        wmscrehousingattDao.updateEnable(mplmDropThisMig);
        // 删除�?��附件
        Map<String, Object> deletAtt = new HashMap<>();
        deletAtt.put("data_type", 1);
        deletAtt.put("wms_cre_credit_head_id", Integer.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()));
        wmscrehousingattDao.deleteRecords(deletAtt);
        // 更新后添加附�?
        for (int i = 0; i < Lattachment.size(); i++)
        {
            WmsCreHousingAtt mplm = Lattachment.get(i);
            mplm.setData_type("1");// 1为信息完善附�?
            mplm.setWms_cre_credit_head_id(Integer.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()));
            mplm.setCreate_user_id(user.getUserId()); // 创建人id
            mplm.setCreate_user_name(user.getRealname());// 创建人名�?
            mplm.setCreate_timestamp(sysTime);// 创建时间
            mplm.setEnable_flag("1");// 是否有效
            wmscrehousingattDao.save(mplm);
        }
        /*----------------------------------------------WmsPostLoanMigration资料附件�?end----------------------------------------------*/
        // 保存复核意见到主表单
        if ("false".equals(approveHouseWorkFlowVO.getPass()))
        {
            Map<String, Object> parmmap = new HashMap<String, Object>();
            parmmap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            parmmap.put("check_back_remark", approveHouseWorkFlowVO.getAdvice());
            wmscrecreditheadDao.updateRecord(parmmap);
        }
        // 流程�?��
        approveHouseWorkFlowVO.setUserId(String.valueOf(user.getUserId()));
        //房贷流程版本号 1为老流程  2为新流程
        if("1".equals(approveHouseWorkFlowVO.getEdition_num())){
        	houseCreditWorkFlowService.creCheckHouseCreditWorkFlow(approveHouseWorkFlowVO);
        }else if("2".equals(approveHouseWorkFlowVO.getEdition_num())){
            approveHouseWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
            approveHouseWorkFlowVO.setBusinessId(approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            approveHouseWorkFlowVO.setDebtkey("1");//流程节点
            wmsLoanWorkFlowService.publicApproval(approveHouseWorkFlowVO);
        }
        return result;
    }

    @Override
    @Transactional
    public String save(WmsCreHousingAtt bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrehousingattDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreHousingAtt bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrehousingattDao.update(bean); // update a record replace
                                                // columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreHousingAtt() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreHousingAtt> getListByEntity(WmsCreHousingAtt queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreHousingAtt> beanList = wmscrehousingattDao.getListByEntity(queryInfo);
        return beanList;
    }

    /**
     * 
     * @Title: addNotarizationNewRecord
     * @Description: TODO(公正)
     * @param user
     * @param bean
     * @return 
     * @author: baisong
     * @time:2016年12月30日 下午3:06:33
     * @see com.zx.emanage.cremanage.service.IWmsCreHousingAttService#addNotarizationNewRecord(com.zx.sframe.util.vo.UserBean, com.zx.emanage.cremanage.vo.WmsCreCreditHeadHouseSearchBeanVO)
     * history:
     * 1、2016年12月30日 baisong 创建方法
     */
    @Override
    @Transactional
    public String addNotarizationNewRecord(UserBean user, WmsCreCreditHeadHouseSearchBeanVO bean)
    {
        String resStr = "success";
        List<WmsCreHousingAtt> attachment = JsonUtil.jsonArrayToList(bean.getFileArr(), WmsCreHousingAtt.class);
        int ret = 0;

        // 查询组合贷
        Map<String, Object> map = new HashMap<>();
        map.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
        map.put("hprocess_time", WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_TIME);
        List<Map<String, Object>> wHeadList = wmscrecreditheadDao.getGroupHeadByNotariza(map);
        // 判断组合贷情况
        if (wHeadList != null && wHeadList.size() > 0)
        {
            for (Map<String, Object> wHeadMap : wHeadList)
            {
                // 删除附件--复议申请时如果不删除会导致附件重复
                wmscrehousingattDao.deleteByheadid(Integer.parseInt(wHeadMap.get("wms_cre_credit_head_id").toString()));
                for (int i = 0; i < attachment.size(); i++)
                {
                    WmsCreHousingAtt wmsCreHousingAtt = attachment.get(i);
                    wmsCreHousingAtt.setWms_cre_housing_att_id(null);
                    wmsCreHousingAtt.setWms_cre_credit_head_id(Integer.valueOf(wHeadMap.get("wms_cre_credit_head_id").toString()));
                    ret = wmscrehousingattDao.save(wmsCreHousingAtt);
                }
                // 判断是否正确保存
                if (attachment.size() > 0 && ret == 0)
                {
                    return "error";
                }
                WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO = new WmsHouseCreditWorkFlowVO();
                approveHouseWorkFlowVO.setUserId(String.valueOf(user.getUserId()));
                approveHouseWorkFlowVO.setWms_cre_credit_head_id(wHeadMap.get("wms_cre_credit_head_id").toString());
                // 流程标识是否为空
                if (wHeadMap.get("is_workflow") != null)
                {
                    approveHouseWorkFlowVO.setIs_workflow(wHeadMap.get("is_workflow").toString());
                }
                // 如果当前主键等于客户选择单据主键
                if (bean.getTaskId() != null && bean.getWms_cre_credit_head_id().equals(wHeadMap.get("wms_cre_credit_head_id").toString()))
                {
                    approveHouseWorkFlowVO.setTaskId(bean.getTaskId());
                }
                if ("2".equals(wHeadMap.get("edition_num").toString()))
                {// 2为新流程
                    approveHouseWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
                    approveHouseWorkFlowVO.setPass("true");
                    approveHouseWorkFlowVO.setDebtkey("7");// 流程节点
                    wmsLoanWorkFlowService.publicApproval(approveHouseWorkFlowVO);
                }
                else if ("1".equals(wHeadMap.get("edition_num").toString()))
                {// 1为旧流程
                    houseCreditWorkFlowService.theContractOrNotarizationOrOther(approveHouseWorkFlowVO, "1");
                }
            }
        }

        return resStr;
    }

    /**
     * 
     * @Title: addOthersNewRecord
     * @Description: TODO(他项)
     * @param user
     * @param bean
     * @return 
     * @author: baisong
     * @time:2016年12月30日 下午3:07:40
     * @see com.zx.emanage.cremanage.service.IWmsCreHousingAttService#addOthersNewRecord(com.zx.sframe.util.vo.UserBean, com.zx.emanage.cremanage.vo.WmsCreCreditHeadHouseSearchBeanVO)
     * history:
     * 1、2016年12月30日 baisong 创建方法
     */
    @Override
    @Transactional
    public String addOthersNewRecord(UserBean user,WmsCreCreditHeadHouseSearchBeanVO bean)
    {
        String resStr = "success";
        List<WmsCreHousingAtt> attachment = JsonUtil.jsonArrayToList(bean.getFileArr(), WmsCreHousingAtt.class);
        int ret = 0;
        // 查询组合贷
        Map<String, Object> map = new HashMap<>();
        map.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
        map.put("hprocess_time", WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_TIME);
        List<Map<String, Object>> wHeadList = wmscrecreditheadDao.getGroupHeadByNotariza(map);
        // 判断组合贷情况
        if (wHeadList != null && wHeadList.size() > 0)
        {
            for (Map<String, Object> wHeadMap : wHeadList)
            {
                for (int i = 0; i < attachment.size(); i++)
                {
                    WmsCreHousingAtt wmsCreHousingAtt = attachment.get(i);
                    wmsCreHousingAtt.setWms_cre_housing_att_id(null);
                    wmsCreHousingAtt.setWms_cre_credit_head_id(Integer.valueOf(wHeadMap.get("wms_cre_credit_head_id").toString()));
                    ret = wmscrehousingattDao.save(wmsCreHousingAtt);
                }
                // 判断是否正确保存
                if (attachment.size() > 0 && ret == 0)
                {
                    return "error";
                }
                WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO = new WmsHouseCreditWorkFlowVO();
                approveHouseWorkFlowVO.setUserId(String.valueOf(user.getUserId()));
                approveHouseWorkFlowVO.setWms_cre_credit_head_id(wHeadMap.get("wms_cre_credit_head_id").toString());
                // 流程标识是否为空
                if (wHeadMap.get("is_workflow") != null)
                {
                    approveHouseWorkFlowVO.setIs_workflow(wHeadMap.get("is_workflow").toString());
                }
                // 如果当前主键等于客户选择单据主键
                if (bean.getTaskId() != null && bean.getWms_cre_credit_head_id().equals(wHeadMap.get("wms_cre_credit_head_id").toString()))
                {
                    approveHouseWorkFlowVO.setTaskId(bean.getTaskId());
                }
                // 2为新流程1为旧流程
                if ("2".equals(wHeadMap.get("edition_num").toString()))
                {
                	 approveHouseWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
                	 approveHouseWorkFlowVO.setPass("true");
                     approveHouseWorkFlowVO.setDebtkey("8");//流程节点
                     wmsLoanWorkFlowService.publicApproval(approveHouseWorkFlowVO);
                }
                else if ("1".equals(wHeadMap.get("edition_num").toString()))
                {
                	 houseCreditWorkFlowService.theContractOrNotarizationOrOther(approveHouseWorkFlowVO, "2");
                }
            }
        }
        return resStr;
    }

    @Override
    public List<WmsCreHousingAtt> getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmscrehousingattDao.getInfoList(wms_cre_credit_head_id);
    }

    /**
     * 
     * @Title: doBLSave
     * @Description: TODO(合同补录)
     * @param user
     * @param bean
     * @return 
     * @author: baisong
     * @time:2017年1月9日 下午6:09:30
     * @see com.zx.emanage.cremanage.service.IWmsCreHousingAttService#doBLSave(com.zx.sframe.util.vo.UserBean, com.zx.emanage.cremanage.vo.WmsCreCreditHeadHouseSearchBeanVO)
     * history:
     * 1、2017年1月9日 baisong 创建方法
     */
    @Override
    @Transactional
   // public String doBLSave(UserBean user, String taskId, int wms_cre_credit_head_id, String fileArr)
    public String doBLSave(UserBean user, WmsCreCreditHeadHouseSearchBeanVO bean)
    
    {
        String resStr = "success";
        int ret = 0;
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        // 获取组合贷数据--如果是一条则正常走
        List<WmsCreCreditHead> list = wmscrecreditheadDao.getGroupHead(Integer.valueOf(bean.getWms_cre_credit_head_id()));
        List<Integer> wheadList = new ArrayList<>();
        for (WmsCreCreditHead whead : list)
        {
            wheadList.add(whead.getWms_cre_credit_head_id());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("headIdList", wheadList);
        List<Map<String, Object>> listBP = wmsCreApproBorrowProtocolDao.getheadListByProtocolMap(map);
        // 如果合同数量和组合贷单据数量不一致则提示
        if (listBP == null || list == null || list.size() != listBP.size())
        {
            return "numerror";
        }
        if (list != null && list.size() > 0)
        {
            // 组合贷循环
            for (WmsCreCreditHead wCreCreditHead : list)
            {

                List<WmsCreHousingAtt> attachment = JsonUtil.jsonArrayToList(bean.getFileArr(), WmsCreHousingAtt.class); // 前台json附件路径数据转换为list
                for (int i = 0; i < attachment.size(); i++)
                {
                    WmsCreHousingAtt mcha = attachment.get(i);
                    mcha.setData_type("7");// 7为补录合同附�?
                    mcha.setWms_cre_credit_head_id(wCreCreditHead.getWms_cre_credit_head_id());
                    mcha.setCreate_user_id(user.getUserId()); // 创建人id
                    mcha.setCreate_user_name(user.getRealname());// 创建人名�?
                    mcha.setCreate_timestamp(sysTime);// 创建时间
                    mcha.setEnable_flag("1");// 是否有效
                    mcha.setWms_cre_housing_att_id(null);
                    ret = wmscrehousingattDao.save(mcha);
                }
                // 附件是否存在并且保存正常
                if (attachment.size() > 0 && ret == 0)
                {
                    resStr = "error";
                }
                WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO = new WmsHouseCreditWorkFlowVO();
                approveHouseWorkFlowVO.setUserId(String.valueOf(user.getUserId()));
                approveHouseWorkFlowVO.setWms_cre_credit_head_id(String.valueOf(wCreCreditHead.getWms_cre_credit_head_id()));
                approveHouseWorkFlowVO.setTaskId(bean.getTaskId());
                if ("2".equals(bean.getEdition_num()))
                {
                    approveHouseWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
                    approveHouseWorkFlowVO.setBusinessId(wCreCreditHead.getWms_cre_credit_head_id().toString());
                    approveHouseWorkFlowVO.setPass("true");
                    approveHouseWorkFlowVO.setDebtkey("6");// 流程节点
                    wmsLoanWorkFlowService.publicApproval(approveHouseWorkFlowVO);
                }
                else if ("1".equals(bean.getEdition_num()))
                {
                    houseCreditWorkFlowService.theContractOrNotarizationOrOther(approveHouseWorkFlowVO, "0");
                }
            }
        }
        return resStr;
    }

    /**
     * 
     * @Title: addSupplementAgreeInfo
     * @Description: TODO(手机端流程补录合同)
     * @param user
     * @param bean
     * @return 
     * @author: jiaodelong
     * @time:2017年1月9日 下午2:54:48
     * @see com.zx.emanage.cremanage.service.IWmsCreHousingAttService#addSupplementAgreeInfo(com.zx.sframe.util.vo.UserBean, com.zx.emanage.cremanage.vo.WmsCreCreditHeadHouseSearchBeanVO)
     * history:
     * 1、2017年1月9日 jiaodelong 创建方法
     */
	@Override
    @Transactional
    public String addSupplementAgreeInfo(UserBean user, WmsCreCreditHeadHouseSearchBeanVO bean)
    {
        String resStr = "success";
        List<WmsCreHousingAtt> attachment = JsonUtil.jsonArrayToList(bean.getFileArr(), WmsCreHousingAtt.class);
        // (获取组合贷单据情况)
        List<Map<String, Object>> list = wmscrecreditheadDao.getGroupHeadByRepaymentType(Integer.valueOf(bean.getWms_cre_credit_head_id()));
        // 循环组合贷
        for (Map<String, Object> map : list)
        {

            int ret = 0;
            wmscrehousingattDao.deleteByheadid(Integer.parseInt(map.get("wms_cre_credit_head_id").toString()));
            // 循环附件
            for (int i = 0; i < attachment.size(); i++)
            {
                WmsCreHousingAtt wmsCreHousingAtt = attachment.get(i);
                wmsCreHousingAtt.setWms_cre_housing_att_id(null);
                wmsCreHousingAtt.setWms_cre_credit_head_id(Integer.valueOf(Integer.parseInt(map.get("wms_cre_credit_head_id").toString())));
                ret = wmscrehousingattDao.save(wmsCreHousingAtt);
            }
            // 如果附件个数大于0 并且保存为0则错误
            if (attachment.size() > 0 && ret == 0)
            {
                return "error";
            }
        }
        return resStr;
	}
}
