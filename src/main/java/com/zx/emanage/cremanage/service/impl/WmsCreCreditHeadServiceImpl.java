package com.zx.emanage.cremanage.service.impl;

/**
 * 版权所有：版权所有(C) 2014，沈阳新融金融信息服务有限公司
 * 文件名称: 
 * 系统名称：
 * 模块名称：
 * 完成日期：
 * 作    者：
 * 内容摘要：  
 * 
 * 文件调用：
 * 修改记录：01
 * 修改时间：20141121
 * 修 改 人:  hancd
 * 修改内容：增加信贷复核和验点数据的区域划分功能
 * 关联BUG：
 * 修改方法：在查询过程中parmMap中添加录单员的区域编码和登录人的区域编码进行对比
 * 
 * 修改记录：02
 * 修改时间：20141203
 * 修 改 人:  hancd
 * 修改内容：增加贷款查询条件城市和单据状态  
 *           增加审批查询条件城市和单据状态
 *           
 * 修改记录：03
 * 修改时间：20150114
 * 修 改 人:  hancd
 * 修改内容：  
 *          增加对角色是业务部门团队经理进行权限过滤
 * 关联BUG：
 * 修改方法：          
 * 
 * 修改记录：04
 * 修改时间：20150120
 * 修改人：wangyujian
 * 关联BUG：
 * 修改内容：判断当前登录人是否是“信审部门主管”
 * 修改方法：getCreditCheckListWithPaging()，添加当登录人为信息审核人员时，“Information_personnel”值为1
 *          否则值为0
 *          
 * 修改记录：05
 * 修改时间：20150210
 * 修改人：hancd
 * 关联BUG：
 * 修改内容：处理业务管理>贷款查询处于待贷前审批状态单据 对验点 初面 的细节区分  
 * 修改方法：
 */
import java.beans.IntrospectionException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import jodd.typeconverter.Convert;

import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.zx.emanage.cremanage.persist.IWmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCarpCarsCustomerInfoDao;
import com.zx.emanage.cremanage.persist.WmsCreCarpHousingAttDao;
import com.zx.emanage.cremanage.persist.WmsCreCarpHousingCustomerInfoDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDiffPhoneDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadLogDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerDataAttachmentDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineCarpinfoDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineCompanyDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineContactDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineHouseinfoDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineWorkinfoDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingApplyAttDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingApprovalInfoDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingAttDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingCreditConfirmDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingCustomerHouseDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingFileInfoDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingPaymentDao;
import com.zx.emanage.cremanage.persist.WmsCusCustomerChangeChildDao;
import com.zx.emanage.cremanage.service.IWmsCarLoanWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.cremanage.service.IWmsCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsHouseCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.Client;
import com.zx.emanage.cremanage.vo.SysSendInfoVO;
import com.zx.emanage.cremanage.vo.WmsCarInfoVO;
import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsChildChangeInfoVO;
import com.zx.emanage.cremanage.vo.WmsChildInfoVO;
import com.zx.emanage.cremanage.vo.WmsCompanyChangeInfoVO;
import com.zx.emanage.cremanage.vo.WmsCompanyInfoVO;
import com.zx.emanage.cremanage.vo.WmsCreCreditCopyBeanVO;
import com.zx.emanage.cremanage.vo.WmsCreCreditHeadHouseSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsCreCreditHeadSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsCreFinalAuditAmountSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsHouseChangeInfoVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsHouseInfoVO;
import com.zx.emanage.cremanage.vo.WmsMoaHouseInfoVO;
import com.zx.emanage.cusmanage.persist.WmsCusCustomerChildDao;
import com.zx.emanage.cusmanage.persist.WmsCusCustomerHeadDao;
import com.zx.emanage.cusmanage.persist.WmsCusCustomerLineCarpinfoDao;
import com.zx.emanage.cusmanage.persist.WmsCusCustomerLineCompanyDao;
import com.zx.emanage.cusmanage.persist.WmsCusCustomerLineHouseinfoDao;
import com.zx.emanage.cusmanage.persist.WmsCusCustomerLineWorkinfoDao;
import com.zx.emanage.loanappro.persist.WmsCreCreditFinalApplDao;
import com.zx.emanage.loanappro.persist.WmsCreCreditVisaApplDao;
import com.zx.emanage.loancheck.persist.WmsCreHousingCheckDao;
import com.zx.emanage.loanreview.persist.WmsCreHousingAssessmentDao;
import com.zx.emanage.loanreview.persist.WmsCreHousingTrialAssessmentDao;
import com.zx.emanage.loanreview.persist.WmsCreRevAttDao;
import com.zx.emanage.loanreview.persist.WmsCreRevCertificateMainDao;
import com.zx.emanage.loanreview.persist.WmsCreRevCertificateModelDao;
import com.zx.emanage.loanreview.persist.WmsCreRevPhoneContactDao;
import com.zx.emanage.loanreview.persist.WmsCreRevPhoneMainDao;
import com.zx.emanage.loanreview.persist.WmsCreRevPhoneModelDao;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.SysMenuDao;
import com.zx.emanage.sysmanage.persist.SysRoleDao;
import com.zx.emanage.sysmanage.persist.SysUserRoleDao;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.sysmanage.vo.MessageVo;
import com.zx.emanage.telUserLoanInfo.persist.WmsCreCreditNotificationMessageDao;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.WmsHelp;
import com.zx.emanage.util.gen.entity.ColumnInfo;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsCreCarpCarsCustomerInfo;
import com.zx.emanage.util.gen.entity.WmsCreCarpHousingAtt;
import com.zx.emanage.util.gen.entity.WmsCreCarpHousingCustomerInfo;
import com.zx.emanage.util.gen.entity.WmsCreCreditFinalAppl;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsCreCreditHeadDiffPhone;
import com.zx.emanage.util.gen.entity.WmsCreCreditHeadLog;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerDataAttachment;
import com.zx.emanage.util.gen.entity.WmsCreCreditNotificationMessage;
import com.zx.emanage.util.gen.entity.WmsCreCreditVisaAppl;
import com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineCarpinfo;
import com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineCompany;
import com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineContact;
import com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineHouseinfo;
import com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineWorkinfo;
import com.zx.emanage.util.gen.entity.WmsCreHousingApplyAtt;
import com.zx.emanage.util.gen.entity.WmsCreHousingApprovalInfo;
import com.zx.emanage.util.gen.entity.WmsCreHousingAssessment;
import com.zx.emanage.util.gen.entity.WmsCreHousingAtt;
import com.zx.emanage.util.gen.entity.WmsCreHousingCheck;
import com.zx.emanage.util.gen.entity.WmsCreHousingCreditConfirm;
import com.zx.emanage.util.gen.entity.WmsCreHousingCustomerHouse;
import com.zx.emanage.util.gen.entity.WmsCreHousingFileInfo;
import com.zx.emanage.util.gen.entity.WmsCreHousingPayment;
import com.zx.emanage.util.gen.entity.WmsCreHousingTrialAssessment;
import com.zx.emanage.util.gen.entity.WmsCreRevAtt;
import com.zx.emanage.util.gen.entity.WmsCreRevCertificateMain;
import com.zx.emanage.util.gen.entity.WmsCreRevCertificateModel;
import com.zx.emanage.util.gen.entity.WmsCreRevPhoneContact;
import com.zx.emanage.util.gen.entity.WmsCreRevPhoneMain;
import com.zx.emanage.util.gen.entity.WmsCreRevPhoneModel;
import com.zx.emanage.util.gen.entity.WmsCusCustomerChangeChild;
import com.zx.emanage.util.gen.entity.WmsCusCustomerChild;
import com.zx.emanage.util.gen.entity.WmsCusCustomerHead;
import com.zx.emanage.util.gen.entity.WmsCusCustomerLineCarpinfo;
import com.zx.emanage.util.gen.entity.WmsCusCustomerLineCompany;
import com.zx.emanage.util.gen.entity.WmsCusCustomerLineHouseinfo;
import com.zx.emanage.util.gen.entity.WmsCusCustomerLineWorkinfo;
import com.zx.emanage.workflow.service.IWorkflowService;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.emanage.workflow.util.WorkflowInfoHelp;
import com.zx.emanage.workflow.util.WorkflowSearchInfoHelp;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.HttpClientUtil;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.SysUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * @ClassName: WmsCreCreditHeadServiceImpl
 * @Description: 内容摘要：贷款主表类
 * @author baisong
 * @date 2016年11月21日
 * @version V1.0
 * history:
 * 1、2016年11月25日 baisong 创建文件
 */
@Service("wmscrecreditheadService")
public class WmsCreCreditHeadServiceImpl implements IWmsCreCreditHeadService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditHeadServiceImpl.class);

    @Autowired
    private WmsCreCreditHeadDao wmscrecreditheadDao_m;

    @Autowired
    private WmsCreHousingAttDao wmscrehousingattDao;

    @Autowired
    private IWmsCreCreditHeadDao wmscrecreditheadDao;

    @Autowired
    private WmsCusCustomerHeadDao wmscuscustomerheadDao_m;

    @Autowired
    private WmsCreCreditLineCustomerChangeHeadDao wmscrecreditlinecustomerchangeheadDao_m;

    @Autowired
    private WmsCreCustomerChangeLineContactDao wmscrecustomerchangelinecontactDao_m;

    @Autowired
    private WmsCreCreditLineCustomerDataAttachmentDao wmscrecreditlinecustomerdataattachmentDao_m;

    @Autowired
    private WmsCreCustomerChangeLineHouseinfoDao wmscrecustomerchangelinehouseinfoDao_m;
    
    @Autowired
    private WmsCreCustomerChangeLineCarpinfoDao wmscrecustomerchangelinecarpinfoDao_m;

    @Autowired
    private WmsCreCustomerChangeLineWorkinfoDao wmscrecustomerchangelineworkinfoDao_m;

    @Autowired
    private WmsCusCustomerLineHouseinfoDao wmsCusCustomerLineHouseinfoDao_m;

    @Autowired
    private WmsCusCustomerLineWorkinfoDao wmsCusCustomerLineWorkinfoDao_m;

    @Autowired
    private IWorkflowService workflowService;

    @Autowired
    private IWmsCreditWorkFlowService creditWorkFlowService;

    @Autowired
    private WmsCreCreditHeadDiffPhoneDao wmsCreCreditHeadDiffPhoneDao;

    @Autowired
    private WmsCreHousingCustomerHouseDao wmsCreHousingCustomerHouseDao;

    @Autowired
    private IWmsHouseCreditWorkFlowService houseCreditWorkFlowService;
    
    @Autowired
    private WmsCreCustomerChangeLineCompanyDao wmsCreCustomerChangeLineCompanyDao;
    
    @Autowired
    private WmsCreCustomerChangeLineCarpinfoDao wmsCreCustomerChangeLineCarpinfoDao;

    @Autowired
    private WmsCusCustomerLineCompanyDao wmsCusCustomerLineCompanyDao;

    @Autowired
    private WmsCusCustomerChildDao wmsCusCustomerChildDao;

    @Autowired
    private WmsCusCustomerChangeChildDao wmsCusCustomerChangeChildDao;

    @Autowired
    private SysRoleDao sysRoleDao;
    
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    
    @Autowired
    private IWmsCarLoanWorkFlowService carLoanWorkFlowService;
    
    @Autowired
    private WmsCusCustomerLineCarpinfoDao wmsCusCustomerLineCarpinfoDao;

    @Autowired
    private WmsCreCarpHousingCustomerInfoDao wmsCreCarpHousingCustomerInfoDao;
   
    @Autowired
    private WmsCreCarpCarsCustomerInfoDao wmsCreCarpCarsCustomerInfoDao;
    
    @Autowired
    private WmsCreCarpHousingAttDao wmsCreCarpHousingAttDao;
    
    @Autowired
    private IWmsLoanWorkFlowService   wmsLoanWorkFlowService;//新房贷贷款流程
    
    @Autowired
    private WmsCreHousingApplyAttDao wmsCreHousingApplyAttDao;
    
    @Autowired
    private WmsCreHousingFileInfoDao wmsCreHousingFileInfoDao;
    
    @Autowired
    private PmPersonnelDao pmPersonnelDao;
    
    @Autowired
    private WmsCreCreditNotificationMessageDao wmscrecreditnotificationmessagedao;
    
    @Autowired
    private WmsCreCreditHeadLogDao wmsCreCreditHeadLogDao;
    @Autowired
    private  WmsCreCreditLineCustomerChangeHeadDao wmsCreCreditLineCustomerChangeHeadDao;
    @Autowired
    private SysMenuDao sysMenuDao;//菜单表
    @Autowired
    private WmsCreRevAttDao wmsCreRevAttDao; // 贷款贷前审核附件
    @Autowired
    private WmsCreHousingCheckDao wmsCreHousingCheckDao;// 房产办件
    @Autowired
    private WmsCreHousingAssessmentDao wmsCreHousingAssessmentDao;// 房贷——办件审核组——验房评估信息
    @Autowired
    private WmsCreHousingCreditConfirmDao wmsCreHousingCreditConfirmDao;// 授信确认

    @Autowired
    private WmsCreHousingTrialAssessmentDao wmsCreHousingTrialAssessmentDao;// 房贷——贷款初审评估单
    @Autowired
    private WmsCreHousingPaymentDao wmsCreHousingPaymentDao;// 房贷——贷款缴费信息表
    @Autowired
    private WmsCreRevCertificateMainDao wmsCreRevCertificateMainDao;// 征信主表
    @Autowired
    private WmsCreRevCertificateModelDao wmsCreRevCertificateModelDao;// 贷款征信审核——模型
    @Autowired
    private WmsCreRevPhoneMainDao wmsCreRevPhoneMainDao;// 贷款电审审核——电审总体情况
    @Autowired
    private WmsCreRevPhoneModelDao wmsCreRevPhoneModelDao;// 贷款电审审核——模型
    @Autowired
    private WmsCreRevPhoneContactDao wmsCreRevPhoneContactDao;// 贷款电审审核——联系人情况
    @Autowired
    private WmsCreHousingApprovalInfoDao wmsCreHousingApprovalInfoDao;// 审批历史表
    @Autowired
    private WmsCreCreditFinalApplDao wmsCreCreditFinalApplDao;// 终审审批表
    @Autowired
    private WmsCreCreditVisaApplDao wmsCreCreditVisaApplDao;// 面签表
    /**
     * 贷款查询数据导出功能
     */
    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user)
    {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCity()))
        {
            paramMap.put("city", SysTools.getSqlLikeParam(queryInfo.getCity()));
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_status()) && !queryInfo.getBill_status().equals("-1"))
        {
            paramMap.put("bill_status", queryInfo.getBill_status());
        }
        if (StringUtil.isNotBlank(queryInfo.getDept_city_sel())) {
        	paramMap.put("dept_city_sel", queryInfo.getDept_city_sel());
        }
        if (StringUtil.isNotBlank(queryInfo.getDept_name_sel())) {
        	paramMap.put("dept_name_sel", queryInfo.getDept_name_sel());
        }
        if (StringUtil.isNotBlank(queryInfo.getMort_flag()))
        {
            paramMap.put("mort_flag", queryInfo.getMort_flag());// 抵押状态
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        // /修改记录:01 增加创建人区域编码和当前登录人的区域编码对比
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        // 修改记录:02 去掉区域编码控制实行可配置权限
        // 登陆人id
        paramMap.put("salesman_id", user.getUserId());
        // 菜单
		paramMap.put("menu_url", WmsHelp.MENU_URL_DKCX_LIST);
        // 获取可查看的部门
        paramMap.put("childrendept", queryChildrenDeptInfo(paramMap));
		
        paramMap.put("Rows", wmscrecreditheadDao_m.getListWithoutPaging(paramMap));
        return paramMap;
    }

    /**
     * 实现信贷贷款复核导出功能
     * 
     * @param queryInfo
     * @return record
     * @author hancd
     */
    @Override
    public Map<String, Object> getCheckListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_DKFH);
        // 根据用户ID,提供节点名字,流程实例key
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        List<Integer> idList = new ArrayList<Integer>();
        if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
        {
            Map<String, Object> resMap = new HashMap<String, Object>();
            resMap.put("Rows", new ArrayList<Map<String, Object>>());
            return resMap;
        }
        for (WorkflowInfoHelp workflowinfohelp : workflowInfoHelps)
        {
            idList.add(Integer.valueOf(workflowinfohelp.getBusinessKey()));
        }
        paramMap.put("idList", idList);
        // /修改记录:01 增加区域编码进行区域区分数据导出
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        List<Map<String, Object>> list = wmscrecreditheadDao_m.search(paramMap);
        paramMap.put("Rows", list);
        return paramMap;
    }

    /**
     * Description: 实现信贷:贷款复核 流水审批 信息审批 电审审批 证信审批 验点审批 页面查询数据显示功能
     * 
     * @param flag_byk
     * @param flag_byk=0 代表信贷贷款复核
     * @param flag_byk=1 代表信贷流水审批
     * @param flag_byk=2 代表信贷信息审批
     * @param flag_byk=3 代表信贷电审审批
     * @param flag_byk=4 代表信贷证信审批
     * @param flag_byk=5 代表信贷验点审批
     * @author hancd
     */
    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user, String flag_byk)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 判断单据编码
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        // 判断客户名称
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", "%" + queryInfo.getCustomer_name() + "%");
        }
        // 判断手机号码
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            paramMap.put("mobile_telephone", "%" + queryInfo.getMobile_telephone() + "%");
        }
        // 判断业务员
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", "%" + queryInfo.getSalesman_name() + "%");
            paramMap.put("salesman_shortcode", "%" + queryInfo.getSalesman_name() + "%");
        }
        // 判断时间
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        // 判断身份证号
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        // 判断创建人ID
        if (StringUtil.isNotBlank(queryInfo.getFlag()))
        {
            paramMap.put("create_user_id", user.getUserId());
        }
        // 判断流水审批人ID
        if (StringUtil.isNotBlank(queryInfo.getFlag_water()))
        {
            paramMap.put("water_appro_user_id", queryInfo.getFlag_water());
        }
        // 判断信息审批人ID
        if (StringUtil.isNotBlank(queryInfo.getFlag_info()))
        {
            paramMap.put("info_appro_user_id", queryInfo.getFlag_info());
        }
        // 判断电审审批人ID
        if (StringUtil.isNotBlank(queryInfo.getFlag_phone()))
        {
            paramMap.put("phone_appro_user_id", queryInfo.getFlag_phone());
        }
        // 判断证信审批人ID
        if (StringUtil.isNotBlank(queryInfo.getFlag_certificate()))
        {
            paramMap.put("certificate_appro_user_id", queryInfo.getFlag_certificate());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());

        // 查询条件(流程代办方面)
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        if (flag_byk.equals("0"))
        {
            // 贷款复核节点
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_DKFH);// 自定义的节点名称
            // /修改记录:01 增加区域编码create_user_city_code
            paramMap.put("create_user_city_code", user.getUser_regionNumber());
        }
        else if (flag_byk.equals("1"))
        {
            // 流水节点
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_LSSP);// 自定义的节点名称
        }
        else if (flag_byk.equals("2"))
        {
            // 信息数据显示
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_XXSP);// 自定义的节点名称
        }
        else if (flag_byk.equals("3"))
        {
            // 电审数据显示
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_DSSP);// 自定义的节点名称
        }
        else if (flag_byk.equals("4"))
        {
            // 证信数据显示
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_ZXSP);// 自定义的节点名称
        }
        else if (flag_byk.equals("5"))
        {
            // 验点数据显示
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_YDSP);// 自定义的节点名称
            // /修改记录:01 增加区域编码create_user_city_code
            //paramMap.put("create_user_city_code", user.getUser_regionNumber());
            // 2016-10-19 修改验点才菜单权限根据配置来控制--取代区域编码控制
           paramMap.put("salesman_id", user.getUserId());//登陆人id
           paramMap.put("menu_url", WmsHelp.MENU_URL_YD_LIST); //菜单url 验点
           paramMap.put("childrendept", queryChildrenDeptInfo(paramMap)); //获取可查看的部门
           
        }
        // 根据用户ID,提供节点名字,流程实例key
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        List<Integer> idList = new ArrayList<Integer>();
        if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
        {
            Map<String, Object> resMap = new HashMap<String, Object>();
            return resMap;
        }
        for (WorkflowInfoHelp workflowinfohelp : workflowInfoHelps)
        {
            idList.add(Integer.valueOf(workflowinfohelp.getBusinessKey()));
        }
        paramMap.put("idList", idList);
        List<Map<String, Object>> list = new ArrayList<>();
        int count = 0;
        // 进行区分查询 复核,验点与其他审批进行区分
        if (flag_byk.equals("0"))
        {
            list = wmscrecreditheadDao_m.searchforCreditCheck(paramMap);
            count = wmscrecreditheadDao_m.findCountforCreditCheck(paramMap);
        }
        else if (flag_byk.equals("5"))
        {
            list = wmscrecreditheadDao_m.getYdListWithPaging(paramMap);
            count = wmscrecreditheadDao_m.findCountforYdListWithPaging(paramMap);
        }
        else
        {
            list = wmscrecreditheadDao_m.search(paramMap);
            count = wmscrecreditheadDao_m.findCount(paramMap);
        }
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), count, list);

        for (Map<String, Object> map : list)
        {
            Integer wms_cre_credit_head_id = (Integer) map.get("wms_cre_credit_head_id");
            for (WorkflowInfoHelp workflowinfohelp : workflowInfoHelps)
            {
                if (Integer.valueOf(workflowinfohelp.getBusinessKey()).compareTo(wms_cre_credit_head_id) == 0)
                {
                    map.put("taskId", workflowinfohelp.getTaskId());
                    break;
                }
            }
        }
        return bean.getGridData();
    }

    @Override
    public Map<String, Object> getListWithPagingForFd(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", "%" + queryInfo.getCustomer_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            paramMap.put("mobile_telephone", "%" + queryInfo.getMobile_telephone() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        List<Map<String, Object>> list = wmscrecreditheadDao_m.searchForFd(paramMap);
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), wmscrecreditheadDao_m.findCountForFd(paramMap), list);
        return bean.getGridData();
    }
    
    @Override
    public Map<String, Object> getListWithPagingForCar(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", "%" + queryInfo.getCustomer_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            paramMap.put("mobile_telephone", "%" + queryInfo.getMobile_telephone() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        List<Map<String, Object>> list = wmscrecreditheadDao_m.searchForCar(paramMap);
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), wmscrecreditheadDao_m.findCountForCar(paramMap), list);
        return bean.getGridData();
    }

    /**
     * 房贷各审核组公用方法 2：为办件审核组方法
     */
    @Override
    public Map<String, Object> getListWithPagingForFdForAdd(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user,
                                                            String housekey)
    {
        Map<String, Object> paramMap = new HashMap<>();
        // 2016-12-19日修改 改变原有的列表查询方式 （办件审核组）
        if ("2".equals(housekey))
        {
            paramMap.put("bill_status", "D");
            // 开发模式 1为开发模式 其他为正常权限模式
            if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode")))
            {
                paramMap.put("salesman_id", user.getUserId());// 登陆人id
                paramMap.put("menu_url", WmsHelp.MENU_URL_FCHC_LIST);
                paramMap.put("childrendept", queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
            }
        }
        if("10".equals(housekey)){//判断电审审核组
    		  paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "17",WorkflowConstantHelp.UPHOUSINGLOANPROCESS);//合并俩个流程	
        }
        if("8".equals(housekey)){//判断征信审核组
  		      paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "18",WorkflowConstantHelp.UPHOUSINGLOANPROCESS);//合并俩个流程	
        }
        // housekey==2 （办件审核组）
        if (paramMap.get("idList") == null && !"2".equals(housekey))
        {
        	 Map<String, Object> resultMap = new HashMap<String, Object>();
             resultMap.put("Rows", new ArrayList<Map<String, Object>>());
             return resultMap;
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", "%" + queryInfo.getCustomer_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            paramMap.put("mobile_telephone", "%" + queryInfo.getMobile_telephone() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getMort_flag()))
        {
            paramMap.put("mort_flag", queryInfo.getMort_flag());// 抵押状态
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int count = 0;
        if ("2".endsWith(housekey)||"10".endsWith(housekey)||"8".endsWith(housekey))//办件 电审和征信2016-10-9
        {
            // 房贷：A 草稿 B待复核 C待贷前审核 D待终审 E待放款准备 F待放款审核 G待放款 H已完成 Z终止 I 复核退回 J 补件中
            // K待审批 L待初评预估 M合同完善完成 N授信确认 O 房产核查缴费 P初评退件 Q 终审退件
            paramMap.put("housekey", housekey);
            list = wmscrecreditheadDao_m.searchForFdCardForAdd(paramMap);
            count = wmscrecreditheadDao_m.findCountForFdCardForAdd(paramMap);
        }
        else
        {
            paramMap.put("create_user_city_code", user.getUser_regionNumber());
            list = wmscrecreditheadDao_m.searchForFdForAdd(paramMap);
            count = wmscrecreditheadDao_m.findCountForFdForAdd(paramMap);
        }
        //判断是否是房产核查
        if (!"2".endsWith(housekey))
        {
            list = houseCreditWorkFlowService.addTaskIDHouse(list, (List<Integer>) paramMap.get("idList"),
                                                             (List<String>) paramMap.get("taskIdList"));
        }
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), count, list);
        return bean.getGridData();
    }
    /**
     * 房贷各审核组公用方法 2：为办件审核组方法
     * 
     * Description :房贷查询列表分页-完善
     * 
     * @return
     * @author baisong
     */
    @Override
    public Map<String, Object> getListWithPagingForFdForAddMakeup(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user,
                                                            String housekey)
    {
        Map<String, Object> paramMap =new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", "%" + queryInfo.getCustomer_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            paramMap.put("mobile_telephone", "%" + queryInfo.getMobile_telephone() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getMort_flag()))
        {
            paramMap.put("mort_flag", queryInfo.getMort_flag());// 抵押状态
        }
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        if(!"0".equals(queryInfo.getIs_need_paging())){
        	 paramMap.put("offset", queryInfo.getOffset());
             paramMap.put("pagesize", queryInfo.getPagesize());
        }
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int count = 0;
        // 开发模式 1为开发模式 其他为正常权限模式
        if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode")))
        {
            paramMap.put("salesman_id", user.getUserId());// 登陆人id
            paramMap.put("menu_url", WmsHelp.MENU_URL_FCHCWS_LIST);
            paramMap.put("childrendept", queryChildrenDeptInfo(paramMap));// 获取可查看的部门
        }
        list = wmscrecreditheadDao_m.searchForFdCardForAddMakeup(paramMap);
        count = wmscrecreditheadDao_m.findCountForFdCardForAddMakeup(paramMap);
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), count, list);
       
        return bean.getGridData();
    }
    /**
     * 车贷各审核组公用方法 2：为办件审核组方法
     */
    @Override
    public Map<String, Object> getListWithPagingForCDForAdd(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user,
                                                            String carkey)
    {
        Map<String, Object> paramMap = carLoanWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
                                                                                    carkey);
        if (paramMap.get("idList") == null)
        {
            return new HashMap<String, Object>();
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", "%" + queryInfo.getCustomer_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            paramMap.put("mobile_telephone", "%" + queryInfo.getMobile_telephone() + "%");
        }
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int count = 0;
        if ("3".endsWith(carkey))
        {
            list = wmscrecreditheadDao_m.searchForCdCardForAdd(paramMap);
            count = wmscrecreditheadDao_m.findCountForCdCardForAdd(paramMap);
        }
        list = carLoanWorkFlowService.setWorkFlowTaskID(list, (List<Integer>) paramMap.get("idList"),
                                                         (List<String>) paramMap.get("taskIdList"), (List<String>)paramMap.get("approvesGroups"), (List<String>)paramMap.get("approveAdvices"), (List<String>)paramMap.get("approveTimes"));
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), count, list);
        return bean.getGridData();
    }
    /**
     * 车贷 2：为评估审核组方法--导出excel
     */
    @Override
    public Map<String, Object> getListWithoutPagingForCDForAdd(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user,
                                                            String carkey)
    {
        Map<String, Object> paramMap = carLoanWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
                                                                                    carkey);
        if (paramMap.get("idList") == null)
        {
            return new HashMap<String, Object>();
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", "%" + queryInfo.getCustomer_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            paramMap.put("mobile_telephone", "%" + queryInfo.getMobile_telephone() + "%");
        }
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int count = 0;
        if ("3".endsWith(carkey))
        {
            list = wmscrecreditheadDao_m.searchForCdCardForAdd(paramMap);
          
        }
        paramMap.clear();
        paramMap.put("Rows", list);
        return paramMap;
    }
    /**
     * 实现贷款查询 操作
     */
    @Override
    public Map<String, Object> getListWithPagingforadd(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", "%" + queryInfo.getSalesman_name() + "%");
            paramMap.put("salesman_shortcode", "%" + queryInfo.getSalesman_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getFlag()))
        {
            paramMap.put("create_user_id", user.getUserId());
        }
        if (StringUtil.isNotBlank(queryInfo.getFlag_water()))
        {
            paramMap.put("water_appro_user_id", queryInfo.getFlag_water());
        }
        if (StringUtil.isNotBlank(queryInfo.getFlag_info()))
        {
            paramMap.put("info_appro_user_id", queryInfo.getFlag_info());
        }
        if (StringUtil.isNotBlank(queryInfo.getFlag_phone()))
        {
            paramMap.put("phone_appro_user_id", queryInfo.getFlag_phone());
        }
        if (StringUtil.isNotBlank(queryInfo.getFlag_certificate()))
        {
            paramMap.put("certificate_appro_user_id", queryInfo.getFlag_certificate());
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", "%" + queryInfo.getCustomer_name() + "%");
        }
        // /修改记录：02 增加筛选条件城市 增加筛选条件单据状态
        if (StringUtil.isNotBlank(queryInfo.getCity()))
        {
            paramMap.put("city", SysUtil.getSqlLikeParam(queryInfo.getCity()));
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_status()) && !queryInfo.getBill_status().equals("-1"))
        {
            paramMap.put("bill_status", queryInfo.getBill_status());
        }
        if (StringUtil.isNotBlank(queryInfo.getDept_city_sel())) {
            paramMap.put("dept_city_sel", queryInfo.getDept_city_sel());
        }
        if (StringUtil.isNotBlank(queryInfo.getDept_name_sel())) {
            paramMap.put("dept_name_sel", queryInfo.getDept_name_sel());
        }
        if (StringUtil.isNotBlank(queryInfo.getMort_flag()))
        {
            paramMap.put("mort_flag", queryInfo.getMort_flag());// 抵押状态
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        // /修改记录:01 增加创建人区域编码和当前登录人的区域编码对比
        paramMap.put("create_user_city_code", user.getUser_regionNumber());

		paramMap.put("salesman_id", user.getUserId());// 登陆人id
		paramMap.put("menu_url", WmsHelp.MENU_URL_DKCX_LIST);
		paramMap.put("childrendept",
				queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
        List<Map<String, Object>> list = wmscrecreditheadDao_m.search(paramMap);
        // /修改记录：03
        int key = sysRoleDao.findRole(user.getUserId());
        if (key == 1)
        {// 说明是业务部门经理
            for (Map<String, Object> map : list)
            {
                Integer wms_cre_credit_head_id = (Integer) map.get("wms_cre_credit_head_id");
                map.put("roleKey", "role_" + wms_cre_credit_head_id);
            }
        }
        ///修改记录:05
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        workflowSearchInfoHelp.setUserId(sysUserRoleDao.findUserIDs().get(0));
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_YDSP);
        List<WorkflowInfoHelp> workflowInfoHelps =workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        List<Integer>idList = new ArrayList<>();
        for(WorkflowInfoHelp workflowInfoHelp:workflowInfoHelps){
            idList.add(Integer.valueOf(workflowInfoHelp.getBusinessKey()));
        }
        for(Map<String,Object>map:list){
            Integer wms_cre_credit_head_id =(Integer)map.get("wms_cre_credit_head_id");
            for(int i=0;i<idList.size();i++){
                if(idList.get(i).compareTo(wms_cre_credit_head_id)==0){
                    map.put("keyValue", "1");
                    break;
                }
            }
        }
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), wmscrecreditheadDao_m.findCount(paramMap), list);
        return bean.getGridData();
    }

    /**
     * 贷款查询 根据单据编号查询信息
     */
    @Override
    public WmsCreCreditHead getInfoByPK(Integer wms_cre_credit_head_id)
    {
        // �?��处理
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        paramMap.put("hprocess_time", WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_TIME);
        List<WmsCreCreditHead> resList = wmscrecreditheadDao_m.getInfo(paramMap);
        return resList.get(0);
    }
    
    public BigDecimal getFinalAuditAmount(WmsCreFinalAuditAmountSearchBeanVO auditAmountSearchBeanVO) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("customer_name", auditAmountSearchBeanVO.getCustomer_name());
		paramMap.put("house_building_area", auditAmountSearchBeanVO.getHouse_building_area());
		paramMap.put("house_address", auditAmountSearchBeanVO.getHouse_address());
		List<Map<String, Object>> list = wmscrecreditheadDao_m.getFinalAuditAmount(paramMap);
		if (list.size() > 0) {
			return new BigDecimal(list.get(0).get("appro_limit").toString());
		} else {
			return new BigDecimal(0);
		}
	}

    /**
     * 保存 贷款申请信息
     * 
     * @param mcch
     * @param mcclcda
     * @param mccclc
     * @param user
     * @return
     */
    @Override
    @Transactional
    public String saveAll(WmsCreCreditHead mcch, String attachment, String mccclc, UserBean user, String clientId,
                          String modifyJsonCus, String isComOrZC)
    {
        // 表示暂存状态:temOK
        String result = "temOK";
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间

        List<WmsCreCreditLineCustomerDataAttachment> Lattachment = JsonUtil.jsonArrayToList(attachment,
                                                                                            WmsCreCreditLineCustomerDataAttachment.class); // 前台json附件路径数据转换为list
        List<WmsCreCustomerChangeLineContact> Lmccclc = JsonUtil.jsonArrayToList(mccclc,
                                                                                 WmsCreCustomerChangeLineContact.class); // 前台json客户联系人数据转换为list数据

        /*----------------------------------------------wms_cre_credit_head 贷款申请单主�?begin----------------------------------------------*/
        mcch.setCreate_user_id(user.getUserId()); // 创建人id
        mcch.setCreate_user_name(user.getRealname());// 创建人名�?
        mcch.setCreate_timestamp(sysTime);// 创建时间
        mcch.setCreate_user_city_code(user.getUser_regionNumber());// 存储创建者的城市区域编码
        mcch.setCre_type("1");
        mcch.setEnable_flag("1");// 是否有效
        mcch.setBill_code(CodeNoUtil.getCreCode()); // 信用贷款单据的编�?
        mcch.setBill_status("0");// 单据的状�?0.草稿状�?
        mcch.setIs_link_repeat("0");
        /*if (mcch.getSalesman_id() != null)
        {
            mcch.setCity(UserCityUtil.getUserCity(mcch.getCity()));
        }*/
        // 备份产品类型 把原有产品类型备份到cre_loan_type_backup字段以备以后面签如果客户选择修改期限或者产品类型
        mcch.setCre_loan_type_backup(mcch.getCre_loan_type());
        // 备份申请最长申请还款期限
        mcch.setMax_repayment_time_limit_backup(mcch.getMax_repayment_time_limit());
        mcch.setCreate_user_dept_id(user.getDeptId());
        wmscrecreditheadDao_m.saveByPk(mcch); // 保存贷款单据信息并返回保存后的id
        Integer wmsCreCreditHeadId = mcch.getWms_cre_credit_head_id();
        /*----------------------------------------------wms_cre_credit_head 贷款申请单主�?end----------------------------------------------*/
        if (isComOrZC.equals("1"))
        {
            // 表示提交操作：该为OK
            result = "OK";
            /*---------------------�?��流程-------------------*/
            creditWorkFlowService.startWorkFlow(String.valueOf(user.getUserId()), String.valueOf(wmsCreCreditHeadId));
            Map<String, Object> mccs = new HashMap<String, Object>();
            mccs.put("bill_status", "1");// 0.草稿 1.待复核状�? 2.待贷前审核状�? 3.待终�?
                                         // 4.待面�? 5.面签通过 9终止
            mccs.put("wms_cre_credit_head_id", wmsCreCreditHeadId);
            wmscrecreditheadDao_m.updateRecord(mccs);// 启动流程后根据表单id，修改表单状�?
        }
        /*----------------------------------------------wms_cre_credit_line_customer_change_head 客户信息变更�?begin----------------------------------------------*/
        WmsCreCreditLineCustomerChangeHead wmscrecreditlinecustomerchangehead; // 客户信息变更�?

        List<WmsCusCustomerHead> wmscuscustomerheadvo = new ArrayList<WmsCusCustomerHead>();
        if (!(clientId.equals("")))
        {
            for (String id : clientId.split(","))
            {
                String[] idarr = id.split("@@@");
                WmsCusCustomerHead mcchvo = wmscuscustomerheadDao_m.get(Integer.valueOf(idarr[0]));
                mcchvo.setEnable_flag(idarr[1]);
                wmscuscustomerheadvo.add(mcchvo);
            }
            ;
        }
        List<WmsCusCustomerHead> wmsCusCustomerHeadVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                     WmsCusCustomerHead.class);// 修改后list
        // 修改后的工作信息
        List<WmsCreCustomerChangeLineWorkinfo> WmsCreCustomerChangeLineWorkinfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                                                 WmsCreCustomerChangeLineWorkinfo.class);// 修改后list

        // housestr
        List<WmsHouseInfoVO> wmsHouseInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsHouseInfoVO.class);// 修改后list
        
        // 修改后的车产信息
        List<WmsCarInfoVO> wmsCarInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsCarInfoVO.class);

        // 修改后的公司信息
        List<WmsCompanyInfoVO> wmsCompanyInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsCompanyInfoVO.class);

        // 修改后的子女信息
        List<WmsChildInfoVO> wmsChildInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsChildInfoVO.class);

        boolean isRepeaded = false;
        for (int i = 0; i < wmscuscustomerheadvo.size(); i++)
        {
            boolean ishave = false;//
            Integer org_custom_info_id = wmscuscustomerheadvo.get(i).getWms_cus_customer_id();
            wmscrecreditlinecustomerchangehead = new WmsCreCreditLineCustomerChangeHead();
            int j = 0;// 修改客户信息list的index
            if (wmsCusCustomerHeadVOList != null && wmsCusCustomerHeadVOList.size() > 0)
            {
                for (j = wmsCusCustomerHeadVOList.size() - 1; j >= 0; j--)
                {
                    if (wmsCusCustomerHeadVOList.get(j).getWms_cus_customer_id()
                                                .compareTo(wmscuscustomerheadvo.get(i).getWms_cus_customer_id()) == 0)
                    {
                        ishave = true;
                        WmsCusCustomerHead pageVo = wmsCusCustomerHeadVOList.get(j);
                        copyWmscrecreditlinecustomerchangehead(wmscrecreditlinecustomerchangehead, pageVo,
                                                               wmsCreCreditHeadId, user, wmscuscustomerheadvo.get(i));
                        break;
                    }
                }
            }

            if (!ishave)
            {
                copyWmscrecreditlinecustomerchangehead(wmscrecreditlinecustomerchangehead, wmscuscustomerheadvo.get(i),
                                                       wmsCreCreditHeadId, user, wmscuscustomerheadvo.get(i));
            }
            wmscrecreditlinecustomerchangeheadDao_m.saveWithKey(wmscrecreditlinecustomerchangehead);
            for (int k = 0; k < Lmccclc.size(); k++)
            {
                WmsCreCustomerChangeLineContact mccclcChange = Lmccclc.get(k);
                if (mccclcChange.getWms_cre_credit_line_customer_change_head_id()
                                .equals(wmscrecreditlinecustomerchangehead.getWms_cus_customer_head_id()))
                {
                    mccclcChange.setWms_cre_credit_line_customer_change_head_id(wmscrecreditlinecustomerchangehead.getWms_cre_credit_line_customer_change_head_id());
                    Lmccclc.set(k, mccclcChange);
                }
            }
            if (isComOrZC.equals("1"))
            {
                // �?��身份证号重复，并将重复记录存入数据库
                isRepeaded = isRepeaded || this.compareSfz(wmscrecreditlinecustomerchangehead, user);
                // 客户电话与联系人电话重复记录集合
                isRepeaded = isRepeaded || this.compareKhAndLxr(wmscrecreditlinecustomerchangehead, user);
                // 客户与客户电话重�?
                isRepeaded = isRepeaded || this.compareKhAndKh(wmscrecreditlinecustomerchangehead, user);
            }

            Integer customer_id = wmscrecreditlinecustomerchangehead.getWms_cre_credit_line_customer_change_head_id();
            if (ishave)
            {
                // 工作信息
                WmsCreCustomerChangeLineWorkinfo vo = WmsCreCustomerChangeLineWorkinfoVOList.get(j);// 工作信息
                vo.setWms_cre_credit_line_customer_change_head_id(customer_id);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                vo.setEnable_flag("1");
                wmscrecustomerchangelineworkinfoDao_m.addNewRecord(vo);

                // 房产信息
                for (Integer m = wmsHouseInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsHouseInfoVO housevo = wmsHouseInfoVOList.get(m);
                    if (org_custom_info_id.compareTo(housevo.getWms_cus_customer_id()) == 0)
                    {
                        String housestr = housevo.getHousestr();
                        List<WmsCreCustomerChangeLineHouseinfo> houseList = JsonUtil.jsonArrayToList(housestr,
                                                                                                     WmsCreCustomerChangeLineHouseinfo.class);
                        for (WmsCreCustomerChangeLineHouseinfo housevo_p : houseList)
                        {
                            housevo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            housevo_p.setCreate_user_id(user.getUserId());
                            housevo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            housevo_p.setLast_update_user_id(user.getUserId());
                            housevo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            housevo_p.setEnable_flag("1");
                            wmscrecustomerchangelinehouseinfoDao_m.addNewRecord(housevo_p);
                        }
                        break;
                    }
                }
                
                // 车产信息
                for (Integer m = wmsCarInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsCarInfoVO carvo = wmsCarInfoVOList.get(m);
                    if (org_custom_info_id.compareTo(carvo.getWms_cus_customer_id()) == 0)
                    {
                        String carstr = carvo.getCarstr();
                        List<WmsCreCustomerChangeLineCarpinfo> carList = JsonUtil.jsonArrayToList(carstr, WmsCreCustomerChangeLineCarpinfo.class);
                        for (WmsCreCustomerChangeLineCarpinfo carvo_p : carList)
                        {
                            carvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            carvo_p.setCreate_user_id(user.getUserId());
                            carvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            carvo_p.setLast_update_user_id(user.getUserId());
                            carvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            carvo_p.setEnable_flag("1");
                            wmscrecustomerchangelinecarpinfoDao_m.addNewRecordReKey(carvo_p);
                        }
                        break;
                    }
                }

                // 公司信息
                for (Integer m = wmsCompanyInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsCompanyInfoVO companyvo = wmsCompanyInfoVOList.get(m);
                    if (org_custom_info_id.compareTo(companyvo.getWms_cus_customer_id()) == 0)
                    {
                        String companystr = companyvo.getCompanystr();
                        List<WmsCreCustomerChangeLineCompany> companyList = JsonUtil.jsonArrayToList(companystr,
                                                                                                     WmsCreCustomerChangeLineCompany.class);
                        for (WmsCreCustomerChangeLineCompany companyvo_p : companyList)
                        {
                            companyvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            companyvo_p.setCreate_user_id(user.getUserId());
                            companyvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            companyvo_p.setLast_update_user_id(user.getUserId());
                            companyvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            companyvo_p.setEnable_flag("1");
                            wmsCreCustomerChangeLineCompanyDao.save(companyvo_p);
                        }
                        break;
                    }
                }
                // 子女
                for (Integer m = wmsChildInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsChildInfoVO childvo = wmsChildInfoVOList.get(m);
                    if (org_custom_info_id.compareTo(childvo.getWms_cus_customer_id()) == 0)
                    {
                        String childstr = childvo.getCusChild();
                        List<WmsCusCustomerChangeChild> childList = JsonUtil.jsonArrayToList(childstr,
                                                                                             WmsCusCustomerChangeChild.class);
                        for (WmsCusCustomerChangeChild childvo_p : childList)
                        {
                            childvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            childvo_p.setCreate_user_id(user.getUserId());
                            childvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            childvo_p.setLast_update_user_id(user.getUserId());
                            childvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            childvo_p.setEnable_flag("1");
                            wmsCusCustomerChangeChildDao.save(childvo_p);
                        }
                        break;
                    }
                }
            }
            else
            {
                // 工作信息
                WmsCusCustomerLineWorkinfo workinfoQry = new WmsCusCustomerLineWorkinfo();
                workinfoQry.setWms_cus_customer_id(org_custom_info_id);
                workinfoQry.setEnable_flag("1");
                List<WmsCusCustomerLineWorkinfo> list = wmsCusCustomerLineWorkinfoDao_m.getSingleTableListByAndMethod(workinfoQry);
                if (list != null && list.size() > 0)
                {
                    WmsCusCustomerLineWorkinfo customerLineWork = list.get(0);
                    WmsCreCustomerChangeLineWorkinfo customerChangeLineWork = new WmsCreCustomerChangeLineWorkinfo();
                    copyWmsCreCustomerChangeLineWorkinfo(customerLineWork, customerChangeLineWork);
                    customerChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                    customerChangeLineWork.setCreate_user_id(user.getUserId());
                    customerChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                    customerChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                    wmscrecustomerchangelineworkinfoDao_m.addNewRecord(customerChangeLineWork);
                }

                // 房产信息
                WmsCusCustomerLineHouseinfo qryinfo = new WmsCusCustomerLineHouseinfo();
                qryinfo.setWms_cus_customer_id(org_custom_info_id);
                qryinfo.setEnable_flag("1");
                List<WmsCusCustomerLineHouseinfo> list2 = wmsCusCustomerLineHouseinfoDao_m.getSingleTableListByAndMethod(qryinfo);
                if (list2 != null)
                {
                    for (int k = 0; k < list2.size(); k++)
                    {
                        WmsCusCustomerLineHouseinfo houserWork = list2.get(k);
                        WmsCreCustomerChangeLineHouseinfo houserWorkChangeLineWork = new WmsCreCustomerChangeLineHouseinfo();
                        copyWmsCreCustomerChangeLineHouseinfo(houserWork, houserWorkChangeLineWork);
                        houserWorkChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                        houserWorkChangeLineWork.setCreate_user_id(user.getUserId());
                        houserWorkChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                        houserWorkChangeLineWork.setLast_update_user_id(user.getUserId());
                        houserWorkChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                        houserWorkChangeLineWork.setEnable_flag("1");
                        wmscrecustomerchangelinehouseinfoDao_m.addNewRecord(houserWorkChangeLineWork);
                    }
                }
                
                // 车产信息
                WmsCusCustomerLineCarpinfo carpinfo = new WmsCusCustomerLineCarpinfo();
                carpinfo.setWms_cus_customer_id(org_custom_info_id);
                carpinfo.setEnable_flag("1");
                List<WmsCusCustomerLineCarpinfo> listcar = wmsCusCustomerLineCarpinfoDao.getListByEntity(carpinfo);
                if(listcar != null) {
                	for(int m = 0; m < listcar.size(); m++) {
                		WmsCusCustomerLineCarpinfo lineCarpinfo = listcar.get(m);
                		boolean carFlag = false;
                		WmsCreCustomerChangeLineCarpinfo changeLineCarpinfo = new WmsCreCustomerChangeLineCarpinfo();
                		copyWmsCreCustomerChangeLineCarinfo(lineCarpinfo, changeLineCarpinfo);
                		changeLineCarpinfo.setWms_cre_credit_line_customer_change_head_id(customer_id);
                		changeLineCarpinfo.setCreate_user_id(user.getUserId());
                		changeLineCarpinfo.setCreate_timestamp(sysTime);
                		changeLineCarpinfo.setLast_update_user_id(user.getUserId());
                		changeLineCarpinfo.setLast_update_timestamp(sysTime);
                		changeLineCarpinfo.setEnable_flag("1");
                		wmscrecustomerchangelinecarpinfoDao_m.addNewRecordReKey(changeLineCarpinfo);
                	}
                }

                // 公司信息
                WmsCusCustomerLineCompany qryinfo3 = new WmsCusCustomerLineCompany();
                qryinfo3.setWms_cus_customer_id(org_custom_info_id);
                qryinfo3.setEnable_flag("1");
                List<WmsCusCustomerLineCompany> list3 = wmsCusCustomerLineCompanyDao.getSingleTableListByAndMethod(qryinfo3);
                if (list3 != null)
                {
                    for (int k = 0; k < list3.size(); k++)
                    {
                        WmsCusCustomerLineCompany companyWork = list3.get(k);
                        WmsCreCustomerChangeLineCompany companyWorkChangeLineWork = new WmsCreCustomerChangeLineCompany();
                        copyWmsCreCustomerChangeLinCompanyinfo(companyWork, companyWorkChangeLineWork);
                        companyWorkChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                        companyWorkChangeLineWork.setCreate_user_id(user.getUserId());
                        companyWorkChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                        companyWorkChangeLineWork.setLast_update_user_id(user.getUserId());
                        companyWorkChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                        companyWorkChangeLineWork.setEnable_flag("1");
                        wmsCreCustomerChangeLineCompanyDao.save(companyWorkChangeLineWork);
                    }
                }

                // 子女信息
                WmsCusCustomerChild qryinfo4 = new WmsCusCustomerChild();
                qryinfo4.setWms_cus_customer_id(org_custom_info_id);
                qryinfo4.setEnable_flag("1");
                List<WmsCusCustomerChild> list4 = wmsCusCustomerChildDao.getSingleTableListByAndMethod(qryinfo4);
                if (list4 != null)
                {
                    for (int k = 0; k < list4.size(); k++)
                    {
                        WmsCusCustomerChild childWork = list4.get(k);
                        WmsCusCustomerChangeChild childWorkChangeLineWork = new WmsCusCustomerChangeChild();
                        copyWmsCreCustomerChangeLinChildinfo(childWork, childWorkChangeLineWork);
                        childWorkChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                        childWorkChangeLineWork.setCreate_user_id(user.getUserId());
                        childWorkChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                        childWorkChangeLineWork.setLast_update_user_id(user.getUserId());
                        childWorkChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                        childWorkChangeLineWork.setEnable_flag("1");
                        wmsCusCustomerChangeChildDao.save(childWorkChangeLineWork);
                    }
                }
            }
        }

        /*----------------------------------------------wms_cre_credit_line_customer_change_head 客户信息变更�?end----------------------------------------------*/

        /*----------------------------------------------wms_cre_customer_change_line_contact 客户联系人表 begin----------------------------------------------*/
        for (int i = 0; i < Lmccclc.size(); i++)
        {
            Lmccclc.get(i).setWms_cre_credit_head_id(wmsCreCreditHeadId);
            wmscrecustomerchangelinecontactDao_m.addNewRecord(Lmccclc.get(i));
            if (isComOrZC.equals("1"))
            {
                // 联系人电话重复记录集�?
                isRepeaded = isRepeaded || this.compareLxrAndLxr(Lmccclc.get(i), user);
                // 联系人与客户电话重复
                isRepeaded = isRepeaded || this.compareLxrAndKh(Lmccclc.get(i), user);
            }
        }
        if (isRepeaded)
        {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("is_link_repeat", "1");
            params.put("wms_cre_credit_head_id", wmsCreCreditHeadId);
            wmscrecreditheadDao_m.updateRecord(params);
        }
        /*----------------------------------------------wms_cre_customer_change_line_contact 客户联系人表 end----------------------------------------------*/

        /*----------------------------------------------wms_cre_credit_line_customer_data_attachment 客户资料附件�?begin----------------------------------------------*/
        for (int i = 0; i < Lattachment.size(); i++)
        {
            Lattachment.get(i).setWms_cre_credit_head_id(wmsCreCreditHeadId);
            wmscrecreditlinecustomerdataattachmentDao_m.addNewRecord(Lattachment.get(i));
        }
        /*----------------------------------------------wms_cre_credit_line_customer_data_attachment 客户资料附件�?end----------------------------------------------*/

        return result;
    }

    private void copyWmsCreCustomerChangeLineHouseinfo(WmsCusCustomerLineHouseinfo houserWork,
                                                       WmsCreCustomerChangeLineHouseinfo houserWorkChangeLineWork)
    {

        try
        {
            com.zx.emanage.util.gen.BeanUtils.copyProperties(houserWorkChangeLineWork, houserWork);
        }
        catch (Exception e)
        {

            e.printStackTrace();

        }

    }
    
    private void copyWmsCreCustomerChangeLineCarinfo(WmsCusCustomerLineCarpinfo carpinfo,
    		WmsCreCustomerChangeLineCarpinfo changeLineCarpinfo)
    {
    	
    	try
    	{
    		com.zx.emanage.util.gen.BeanUtils.copyProperties(changeLineCarpinfo, carpinfo);
    	}
    	catch (Exception e)
    	{
    		
    		e.printStackTrace();
    		
    	}
    }

    private void copyWmsCreCustomerChangeLinCompanyinfo(WmsCusCustomerLineCompany companyWork,
                                                        WmsCreCustomerChangeLineCompany companyWorkChangeLineWork)
    {

        try
        {
            com.zx.emanage.util.gen.BeanUtils.copyProperties(companyWorkChangeLineWork, companyWork);
        }
        catch (Exception e)
        {

            e.printStackTrace();

        }

    }

    private void copyWmsCreCustomerChangeLinChildinfo(WmsCusCustomerChild childWork,
                                                      WmsCusCustomerChangeChild childWorkChangeLineWork)
    {

        try
        {
            com.zx.emanage.util.gen.BeanUtils.copyProperties(childWorkChangeLineWork, childWork);
        }
        catch (Exception e)
        {

            e.printStackTrace();

        }
    }

    private void copyWmsCreCustomerChangeLineWorkinfo(WmsCusCustomerLineWorkinfo customerLineWork,
                                                      WmsCreCustomerChangeLineWorkinfo customerChangeLineWork)
    {
        try
        {
            com.zx.emanage.util.gen.BeanUtils.copyProperties(customerChangeLineWork, customerLineWork);
        }
        catch (Exception e)
        {

            e.printStackTrace();

        }
    }

    private void copyWmscrecreditlinecustomerchangehead(WmsCreCreditLineCustomerChangeHead wmscrecreditlinecustomerchangehead,
                                                        WmsCusCustomerHead pageVo, Integer wmsCreCreditHeadId,
                                                        UserBean user, WmsCusCustomerHead databaseVo)
    {

        wmscrecreditlinecustomerchangehead.setCreate_user_id(user.getUserId()); // 创建人id
        wmscrecreditlinecustomerchangehead.setCreate_user_name(user.getRealname());// 创建人名�?
        wmscrecreditlinecustomerchangehead.setCreate_timestamp(new Timestamp(new Date().getTime()));// 创建时间
        wmscrecreditlinecustomerchangehead.setLast_update_timestamp(new Timestamp(new Date().getTime()));// 上次修改时间
        wmscrecreditlinecustomerchangehead.setEnable_flag("1");// 是否有效
        wmscrecreditlinecustomerchangehead.setWms_cre_credit_head_id(wmsCreCreditHeadId);// 贷款单据主表ID
        wmscrecreditlinecustomerchangehead.setWms_cus_customer_head_id(Integer.valueOf(pageVo.getWms_cus_customer_id()));// 客户表主键id
        wmscrecreditlinecustomerchangehead.setCustomer_code(databaseVo.getCustomer_code());// 客户编码
        wmscrecreditlinecustomerchangehead.setCustomer_name(pageVo.getCustomer_name());// 姓名
        wmscrecreditlinecustomerchangehead.setCustomer_ever_name(pageVo.getCustomer_ever_name());// 曾用�?
        wmscrecreditlinecustomerchangehead.setCustomer_category(pageVo.getCustomer_category());// '客户类别
        wmscrecreditlinecustomerchangehead.setGender(pageVo.getGender());// 性别
        wmscrecreditlinecustomerchangehead.setMax_degree(pageVo.getMax_degree());// �?��学历
        wmscrecreditlinecustomerchangehead.setBirthday(pageVo.getBirthday());// 出生日期
        wmscrecreditlinecustomerchangehead.setId_card(pageVo.getId_card());// 身份证号
        wmscrecreditlinecustomerchangehead.setProvince(pageVo.getProvince());// 户籍地址_�?
        wmscrecreditlinecustomerchangehead.setCity(pageVo.getCity());// 户籍详细地址_�?
        wmscrecreditlinecustomerchangehead.setDistrict(pageVo.getDistrict());// 户籍地址_�?
        wmscrecreditlinecustomerchangehead.setAddress_more(pageVo.getAddress_more());// 户籍地址_更详�?
        wmscrecreditlinecustomerchangehead.setHas_children(pageVo.getHas_children());// 有无子女
        wmscrecreditlinecustomerchangehead.setChildren_name(pageVo.getChildren_name());// 子女的姓�?
        wmscrecreditlinecustomerchangehead.setChildren_address(pageVo.getChildren_address());// 子女的住�?
        wmscrecreditlinecustomerchangehead.setChildren_telephone(pageVo.getChildren_telephone());// 子女的电�?
        wmscrecreditlinecustomerchangehead.setHas_married(pageVo.getHas_married());// 婚姻状况
        wmscrecreditlinecustomerchangehead.setHas_house(pageVo.getHas_house());// 房产状况
        wmscrecreditlinecustomerchangehead.setCurrent_address_province(pageVo.getCurrent_address_province());// 目前详细住址_�?
        wmscrecreditlinecustomerchangehead.setCurrent_address_city(pageVo.getCurrent_address_city());// 目前详细住址_�?
        wmscrecreditlinecustomerchangehead.setCurrent_address_district(pageVo.getCurrent_address_district());// 目前详细住址_�?
        wmscrecreditlinecustomerchangehead.setCurrent_address_more(pageVo.getCurrent_address_more());// 目前详细住址_更详�?
        wmscrecreditlinecustomerchangehead.setPost_code(pageVo.getPost_code());// 邮政编码
        wmscrecreditlinecustomerchangehead.setFixed_telephone(pageVo.getFixed_telephone());// 固定电话
        wmscrecreditlinecustomerchangehead.setMobile_telephone1(pageVo.getMobile_telephone1());// 申请人手机号�?
        wmscrecreditlinecustomerchangehead.setService_password1(pageVo.getService_password1());// 手机号码1的服务密�?
        wmscrecreditlinecustomerchangehead.setMobile_telephone2(pageVo.getMobile_telephone2());// 申请人手机号�?
        wmscrecreditlinecustomerchangehead.setService_password2(pageVo.getService_password2());// 手机号码2的服务密�?
        wmscrecreditlinecustomerchangehead.setPersonal_year_income(pageVo.getPersonal_year_income());// 个人年收�?
        wmscrecreditlinecustomerchangehead.setCustomer_email(pageVo.getCustomer_email());// 电子邮件地址
        wmscrecreditlinecustomerchangehead.setCredit_card_limit(pageVo.getCredit_card_limit());// 信用卡最高额�?
        wmscrecreditlinecustomerchangehead.setCommon_occupants(pageVo.getCommon_occupants());// 共同居住�?
        wmscrecreditlinecustomerchangehead.setIs_major(databaseVo.getEnable_flag());// 用Enable_flag字段传是否主贷人
        wmscrecreditlinecustomerchangehead.setStatus("1");// 客户变更信息录入状�?(0:暂存�?:提交�?
        wmscrecreditlinecustomerchangehead.setWork_stat(pageVo.getWork_stat());
        wmscrecreditlinecustomerchangehead.setHouse_stat(pageVo.getHouse_stat());
        wmscrecreditlinecustomerchangehead.setResidence_time(pageVo.getResidence_time());
        wmscrecreditlinecustomerchangehead.setCre_card_num(pageVo.getCre_card_num());
        wmscrecreditlinecustomerchangehead.setExisting_loan(pageVo.getExisting_loan());
        wmscrecreditlinecustomerchangehead.setMonthly_income(pageVo.getMonthly_income());
        wmscrecreditlinecustomerchangehead.setResource_of_tel1(pageVo.getResource_of_tel1());
        wmscrecreditlinecustomerchangehead.setResource_of_tel2(pageVo.getResource_of_tel2());
        wmscrecreditlinecustomerchangehead.setIs_real_name_tel1(pageVo.getIs_real_name_tel1());
        wmscrecreditlinecustomerchangehead.setIs_real_name_tel2(pageVo.getIs_real_name_tel2());
        wmscrecreditlinecustomerchangehead.setReal_name_tel1(pageVo.getReal_name_tel1());
        wmscrecreditlinecustomerchangehead.setReal_name_rela1(pageVo.getReal_name_rela1());
        wmscrecreditlinecustomerchangehead.setReal_name_tel2(pageVo.getReal_name_tel2());
        wmscrecreditlinecustomerchangehead.setReal_name_rela2(pageVo.getReal_name_rela2());
    }

    /**
     * 审核管理-->审批查询
     */
    @Override
    public Map<String, Object> getCreditCheckListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            resMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            resMap.put("salesman_name", "%" + queryInfo.getSalesman_name() + "%");
            resMap.put("salesman_shortcode", "%" + queryInfo.getSalesman_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            resMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            resMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            resMap.put("id_card", queryInfo.getId_card());
        }
        if (queryInfo.getCre_type() == null || queryInfo.getCre_type() == " ")
        {
            resMap.put("cre_type", SysTools.getSqlLikeParam(""));
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()))
        {
            resMap.put("cre_type", queryInfo.getCre_type());
        }
        if (StringUtils.isNotBlank(queryInfo.getSalesman_city_code())) {
            resMap.put("salesman_city_code", queryInfo.getSalesman_city_code());
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            resMap.put("customer_name", "%" + queryInfo.getCustomer_name() + "%");
        }
        // /修改记录:02 增加筛选条件城市
        if (StringUtil.isNotBlank(queryInfo.getCity()))
        {
            resMap.put("city", SysTools.getSqlLikeParam(queryInfo.getCity()));
        }
        // /修改记录:02 增加筛选条件单据状态
        if (StringUtil.isNotBlank(queryInfo.getBill_status()) && !queryInfo.getBill_status().equals("-1"))
        {
            resMap.put("bill_status", queryInfo.getBill_status());
        }
        if (StringUtil.isNotBlank(queryInfo.getDept_city_sel())) {
        	resMap.put("dept_city_sel", queryInfo.getDept_city_sel());
        }
        if (StringUtil.isNotBlank(queryInfo.getDept_name_sel())) {
        	resMap.put("dept_name_sel", queryInfo.getDept_name_sel());
        }
        resMap.put("sortname", queryInfo.getSortname());
        resMap.put("sortorder", queryInfo.getSortorder());
        resMap.put("offset", queryInfo.getOffset());
        resMap.put("pagesize", queryInfo.getPagesize());
        resMap.put("hprocess_time",WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_TIME);
        List<Map<String, Object>> list = wmscrecreditheadDao_m.getCreditCheckListWithPaging(resMap);
//        // /04根据当前登录人ID，查询当前登录人是否为信息部门主管
//        int key = sysRoleDao.findRoleForCDS(user.getUserId());
//        // /key值为1时当前登陆人为信息部门主管
//        if (key == 1)
//        {
//            for (Map<String, Object> map : list)
//            {
//                map.put("Information_personnel", 1);
//            }
//        }
//        else
//        {
//            for (Map<String, Object> map : list)
//            {
//                map.put("Information_personnel", 0);
//            }
//        }
        ///修改记录：05
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        workflowSearchInfoHelp.setUserId(sysUserRoleDao.findUserIDs().get(0));
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_YDSP);
        List<WorkflowInfoHelp> workflowInfoHelps =workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        List<Integer>idList = new ArrayList<>();
        for(WorkflowInfoHelp workflowInfoHelp:workflowInfoHelps){
            idList.add(Integer.valueOf(workflowInfoHelp.getBusinessKey()));
        }
        for(Map<String,Object>map:list){
            Integer wms_cre_credit_head_id =(Integer)map.get("wms_cre_credit_head_id");
            for(int i=0;i<idList.size();i++){
                if(idList.get(i).compareTo(wms_cre_credit_head_id)==0){
                    map.put("keyValue", "1");
                    break;
                }
            }
        }
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), wmscrecreditheadDao_m.findCreditCheckCount(resMap),
                                             list);
        return bean.getGridData();
    }

    @Override
    public Map<String, Object> getFollowCheckListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            resMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            resMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            resMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            resMap.put("mobile_telephone", SysTools.getSqlLikeParam(queryInfo.getMobile_telephone()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            resMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            resMap.put("create_timestamp1", sb.toString());
        }
        resMap.put("sortname", queryInfo.getSortname());
        resMap.put("sortorder", queryInfo.getSortorder());
        resMap.put("offset", queryInfo.getOffset());
        resMap.put("pagesize", queryInfo.getPagesize());
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), wmscrecreditheadDao_m.findFollowCheckCount(resMap),
                                             wmscrecreditheadDao_m.getFollowCheckListWithPaging(resMap));
        return bean.getGridData();
    }

    @Override
    public Map<String, Object> getLoanCheckListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();

        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            resMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            resMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            resMap.put("mobile_telephone", SysTools.getSqlLikeParam(queryInfo.getMobile_telephone()));
        }
        resMap.put("sortname", queryInfo.getSortname());
        resMap.put("sortorder", queryInfo.getSortorder());
        resMap.put("offset", queryInfo.getOffset());
        resMap.put("pagesize", queryInfo.getPagesize());
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), wmscrecreditheadDao_m.findLoanCheckCount(resMap),
                                             wmscrecreditheadDao_m.getLoanCheckListWithPaging(resMap));
        return bean.getGridData();
    }

    @Override
    public String creditApproveSave(String wms_credit_head_ids, String taskids, String wmsCreditLimits,
                                    String wmsCreditCreLoanTypes, String pass, String advice, String user_id)
    {
        String result = "OK";
        String[] ids = wms_credit_head_ids.split(",");
        String[] taskIds = taskids.split(",");
        String[] creditlimits = wmsCreditLimits.split(",");
        String[] creditloantypes = wmsCreditCreLoanTypes.split(",");
        for (int i = 0; i < ids.length; i++)
        {
            WmsCreditWorkFlowVO approveWorkFlowVO = new WmsCreditWorkFlowVO();
            approveWorkFlowVO.setWms_cre_credit_head_id(Integer.parseInt(ids[i]));
            approveWorkFlowVO.setTaskId(taskIds[i]);
            approveWorkFlowVO.setCredit_limit(creditlimits[i]);
            approveWorkFlowVO.setCre_loan_type(creditloantypes[i]);
            approveWorkFlowVO.setPass(pass);
            approveWorkFlowVO.setAdvice(advice);
            approveWorkFlowVO.setUser_id(Integer.valueOf(user_id));
            creditWorkFlowService.creCheckCreditApproveWorkFlow(approveWorkFlowVO);
        }
        return result;
    }

    @Override
    public Map<String, Object> getCreditCheckListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            resMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            resMap.put("salesman_name", "%" + queryInfo.getSalesman_name() + "%");
            resMap.put("salesman_shortcode", "%" + queryInfo.getSalesman_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            resMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            resMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            resMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()))
        {
            resMap.put("cre_type", queryInfo.getCre_type());
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            resMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        // /修改记录：02 增加筛选条件 城市
        if (StringUtil.isNotBlank(queryInfo.getCity()))
        {
            resMap.put("city", SysTools.getSqlLikeParam(queryInfo.getCity()));
        }
        // /修改记录: 02 增加筛选条件单据状态
        if (StringUtil.isNotBlank(queryInfo.getBill_status()) && !queryInfo.getBill_status().equals("-1"))
        {
            resMap.put("bill_status", queryInfo.getBill_status());
        }
        if (StringUtil.isNotBlank(queryInfo.getDept_city_sel())) {
        	resMap.put("dept_city_sel", queryInfo.getDept_city_sel());
        }
        if (StringUtil.isNotBlank(queryInfo.getDept_name_sel())) {
        	resMap.put("dept_name_sel", queryInfo.getDept_name_sel());
        }
        resMap.put("sortname", queryInfo.getSortname());
        resMap.put("sortorder", queryInfo.getSortorder());
        resMap.put("Rows", wmscrecreditheadDao_m.getCreditCheckListWithoutPaging(resMap));
        return resMap;
    }

    @Override
    public Map<String, Object> getFollowCheckListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            resMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            resMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            resMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            resMap.put("mobile_telephone", SysTools.getSqlLikeParam(queryInfo.getMobile_telephone()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            resMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            resMap.put("create_timestamp1", sb.toString());
        }
        resMap.put("sortname", queryInfo.getSortname());
        resMap.put("sortorder", queryInfo.getSortorder());
        resMap.put("Rows", wmscrecreditheadDao_m.getFollowCheckListWithoutPaging(resMap));
        return resMap;
    }

    @Override
    public Map<String, Object> getFollowCheckListWithoutPagingForFd(WmsCreCreditHeadSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            resMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            resMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            resMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            resMap.put("mobile_telephone", SysTools.getSqlLikeParam(queryInfo.getMobile_telephone()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            resMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            resMap.put("create_timestamp1", sb.toString());
        }
        resMap.put("sortname", queryInfo.getSortname());
        resMap.put("sortorder", queryInfo.getSortorder());
        resMap.put("Rows", wmscrecreditheadDao_m.getFollowCheckListWithoutPagingForFd(resMap));
        return resMap;
    }
    @Override
    public Map<String, Object> getFollowCheckListWithoutPagingForCar(WmsCreCreditHeadSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            resMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            resMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            resMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            resMap.put("mobile_telephone", SysTools.getSqlLikeParam(queryInfo.getMobile_telephone()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            resMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            resMap.put("create_timestamp1", sb.toString());
        }
        resMap.put("sortname", queryInfo.getSortname());
        resMap.put("sortorder", queryInfo.getSortorder());
        resMap.put("Rows", wmscrecreditheadDao_m.getFollowCheckListWithoutPagingForCar(resMap));
        return resMap;
    }

    @Override
    public Map<String, Object> getLoanCheckListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            resMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            resMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            resMap.put("mobile_telephone", SysTools.getSqlLikeParam(queryInfo.getMobile_telephone()));
        }
        resMap.put("sortname", queryInfo.getSortname());
        resMap.put("sortorder", queryInfo.getSortorder());
        resMap.put("Rows", wmscrecreditheadDao_m.getLoanCheckListWithoutPaging(resMap));
        return resMap;
    }

    /**
     * 实现信贷各个审批组数据导出功能
     */
    @Override
    public Map<String, Object> getTeamListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user,
                                                        String flag_byk)
    {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 判断单据编号
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        // 判断单据客户名称
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", "%" + queryInfo.getCustomer_name() + "%");
        }
        // 判断联系电话
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            paramMap.put("mobile_telephone", "%" + queryInfo.getMobile_telephone() + "%");
        }
        // 判断业务员
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", "%" + queryInfo.getSalesman_name() + "%");
            paramMap.put("salesman_shortcode", "%" + queryInfo.getSalesman_name() + "%");
        }
        // 判断时间区间
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        // 判断身份证
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        // 判断流水审批人id
        if (StringUtil.isNotBlank(queryInfo.getFlag_water()))
        {
            paramMap.put("water_appro_user_id", queryInfo.getFlag_water());
        }
        // 判断信息审批人id
        if (StringUtil.isNotBlank(queryInfo.getFlag_info()))
        {
            paramMap.put("info_appro_user_id", queryInfo.getFlag_info());
        }
        // 判断电审审批人id
        if (StringUtil.isNotBlank(queryInfo.getFlag_phone()))
        {
            paramMap.put("phone_appro_user_id", queryInfo.getFlag_phone());
        }
        // 判断征信审批人id
        if (StringUtil.isNotBlank(queryInfo.getFlag_certificate()))
        {
            paramMap.put("certificate_appro_user_id", queryInfo.getFlag_certificate());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        // 查询条件(流程代办方面)
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        if (flag_byk.equals("0"))
        {
            // 贷款复核节点
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_DKFH);
            paramMap.put("create_user_city_code", user.getUser_regionNumber());
        }
        else if (flag_byk.equals("1"))
        {
            // 流水节点
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_LSSP);// 自定义的节点名称
        }
        else if (flag_byk.equals("2"))
        {
            // 信息数据显示
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_XXSP);// 自定义的节点名称
        }
        else if (flag_byk.equals("3"))
        {
            // 电审数据显示
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_DSSP);// 自定义的节点名称
        }
        else if (flag_byk.equals("4"))
        {
            // 证信数据显示
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_ZXSP);// 自定义的节点名称
        }
        else if (flag_byk.equals("5"))
        {
            // 验点数据阶段
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_YDSP);// 自定义的节点名称
            // /修改记录:01 增加区域编码create_user_city_code
            //paramMap.put("create_user_city_code", user.getUser_regionNumber());
            // 2016-10-19 修改验点才菜单权限根据配置来控制--取代区域编码控制
            paramMap.put("salesman_id", user.getUserId());//登陆人id
            paramMap.put("menu_url", WmsHelp.MENU_URL_YD_LIST); //菜单url 验点
            paramMap.put("childrendept", queryChildrenDeptInfo(paramMap)); //获取可查看的部门
        }
        // 根据用户ID,提供节点名字,流程实例key
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        List<Integer> idList = new ArrayList<Integer>();
        if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
        {
            Map<String, Object> resMap = new HashMap<String, Object>();
            resMap.put("Rows", new ArrayList<Map<String, Object>>());
            return resMap;
        }
        for (WorkflowInfoHelp workflowinfohelp : workflowInfoHelps)
        {
            idList.add(Integer.valueOf(workflowinfohelp.getBusinessKey()));
        }
        paramMap.put("idList", idList);
        paramMap.put("Rows", wmscrecreditheadDao_m.getTeamListWithoutPaging(paramMap));
        return paramMap;
    }

    @Override
    public Map<String, Object> getTeamListWithoutPagingForFdForAdd(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                                   UserBean bean, String housekey)
    {
        Map<String, Object> paramMap = houseCreditWorkFlowService.getIdListWorkFlow(String.valueOf(bean.getUserId()),
                                                                                    housekey);
        if("10".equals(housekey)){//判断电审审核组
    		  paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,bean.getUserId(), "17",WorkflowConstantHelp.UPHOUSINGLOANPROCESS);//合并俩个流程	
    		  paramMap.put("create_user_city_code", bean.getUser_regionNumber());
        }
        if("8".equals(housekey)){//判断征信审核组
  		  	  paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,bean.getUserId(), "18",WorkflowConstantHelp.UPHOUSINGLOANPROCESS);//合并俩个流程	
  		  	  paramMap.put("create_user_city_code", bean.getUser_regionNumber());
        }
        if (paramMap.get("idList") == null)
        {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("Rows", new ArrayList<Map<String, Object>>());
            return resultMap;
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", "%" + queryInfo.getCustomer_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            paramMap.put("mobile_telephone", "%" + queryInfo.getMobile_telephone() + "%");
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditheadDao_m.getTeamListWithoutPagingForFd(paramMap);
        paramMap.put("Rows", list);
        return paramMap;
    }

    @Override
    public Map<String, Object> getDiffPhoneListById(String org_wms_cre_credit_head_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, Object> resMap = new HashMap<String, Object>();
        paramMap.put("sortname", "diff_bill_code");
        paramMap.put("sortorder", "ASC");
        paramMap.put("org_wms_cre_credit_head_id", org_wms_cre_credit_head_id);
        resMap.put("Rows", wmsCreCreditHeadDiffPhoneDao.getListByMid(paramMap));
        return resMap;
    }

    @Override
    @Transactional
    public String saveHouseCreAll(WmsCreCreditHead mcch, String mccclc, UserBean user, String clientId,
                                  String modifyJsonCus, String house_use, String zdrID, String mcclhid, String fileArr, String fileArrkh,
                                  String isComOrZC, Integer[] deleteAttIds)
    {
        String result = "temOK";
        int isvcchid = 0;// 客户房产信息更改表的主键
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间

        List<WmsCreCustomerChangeLineContact> mcclc = JsonUtil.jsonArrayToList(mccclc,
                                                                               WmsCreCustomerChangeLineContact.class); // 前台json客户联系人数据转换为list数据
        List<WmsCreHousingAtt> attachment = JsonUtil.jsonArrayToList(fileArr, WmsCreHousingAtt.class); // 前台json附件路径数据转换为list
        
        List<WmsCreHousingApplyAtt> atthousing = JsonUtil.jsonArrayToList(fileArrkh, WmsCreHousingApplyAtt.class); //验房附件
        /*----------------------------------------------wms_cre_credit_head 贷款申请单主�?begin----------------------------------------------*/
        
        mcch.setCreate_user_id(user.getUserId()); // 创建人id
        mcch.setCreate_user_name(user.getRealname());// 创建人名�?
        mcch.setCreate_timestamp(sysTime);
        mcch.setCreate_user_city_code(user.getUser_regionNumber());// 创建人区域编码
        mcch.setEnable_flag("1");// 是否有效
        mcch.setCre_type("2");
        mcch.setBill_status("A");// 单据的状�?A.草稿状�?
        mcch.setIs_link_repeat("0");
        mcch.setVersion_number("1");//数据来源1为pc端 2为手机端（移动端申请的单据）
        /*if (mcch.getSalesman_id() != null)
        {
            mcch.setCity(UserCityUtil.getUserCity(mcch.getCity()));
        }*/
        if(mcch.getWms_cre_credit_head_id()!=null&&!"".equals(mcch.getWms_cre_credit_head_id())){
        	 wmscrecreditheadDao_m.updateforhouse(mcch); // 更新贷款单据信息并返回保存后的id--用于moa手机贷款申请
             /**
              * 如果存在上传信息表 就把当前单据状态修改成已生成贷款带锯
              * 2016-3-7 baisong
              */
              /*WmsCreHousingFileInfo housing = new WmsCreHousingFileInfo();
              housing.setWms_cre_credit_head_id(mcch.getWms_cre_credit_head_id());
              housing.setBill_status(1);
              wmsCreHousingFileInfoDao.update(housing);*/
        }else{
        	 mcch.setBill_code(CodeNoUtil.getHouseCreCode());// 信用贷款单据的编�?
            mcch.setIs_workflow("1");
        	 wmscrecreditheadDao_m.saveByPk(mcch); // 保存贷款单据信息并返回保存后的id	
        }
       
        Integer wmsCreCreditHeadId = mcch.getWms_cre_credit_head_id();// 得到新增的贷款的主键
        /*----------------------------------------------wms_cre_credit_head 贷款申请单主�?end----------------------------------------------*/
        if (isComOrZC.equals("1"))
        {
            result = "OK";
            /*---------------------�?��流程-------------------*/
            //houseCreditWorkFlowService.startHouseCreditWorkFlow(String.valueOf(user.getUserId()),
            //                                                   String.valueOf(wmsCreCreditHeadId));
            wmsLoanWorkFlowService.startFinancialWorkFlow(String.valueOf(user.getUserId()),String.valueOf(wmsCreCreditHeadId),null,null,WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
        }
        /*----------------------------------------------wms_cre_credit_line_customer_change_head 客户信息变更�?begin----------------------------------------------*/
        WmsCreCreditLineCustomerChangeHead wmscrecreditlinecustomerchangehead; // 客户信息变更�?
        // 贷款人信息LIST
        List<WmsCusCustomerHead> wmscuscustomerheadvo = new ArrayList<WmsCusCustomerHead>();
        if (!(clientId.equals("")))
        {
            for (String id : clientId.split(","))
            {
                String[] idarr = id.split("@@@");
                WmsCusCustomerHead mcchvo = wmscuscustomerheadDao_m.get(Integer.valueOf(idarr[0]));
                mcchvo.setEnable_flag(idarr[1]);
                wmscuscustomerheadvo.add(mcchvo);
            }
            ;
        }
        List<WmsCusCustomerHead> wmsCusCustomerHeadVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                     WmsCusCustomerHead.class);// 修改后list
        // 修改后的工作信息
        List<WmsCreCustomerChangeLineWorkinfo> WmsCreCustomerChangeLineWorkinfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                                                 WmsCreCustomerChangeLineWorkinfo.class);// 修改后list
        // housestr
        List<WmsHouseInfoVO> wmsHouseInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsHouseInfoVO.class);// 修改后list

        // 修改后的车产信息
        List<WmsCarInfoVO> wmsCarInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsCarInfoVO.class);
        
        // 修改后的公司信息
        List<WmsCompanyInfoVO> wmsCompanyInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsCompanyInfoVO.class);

        // 修改后的子女信息
        List<WmsChildInfoVO> wmsChildInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsChildInfoVO.class);
        boolean isRepeaded = false;

        for (int i = 0; i < wmscuscustomerheadvo.size(); i++)
        {
            boolean ishave = false;// 贷款人信息是否被手动修改�?
            Integer org_custom_info_id = wmscuscustomerheadvo.get(i).getWms_cus_customer_id();
            wmscrecreditlinecustomerchangehead = new WmsCreCreditLineCustomerChangeHead();
            int j = 0;// 修改客户信息list的index
            if (wmsCusCustomerHeadVOList != null && wmsCusCustomerHeadVOList.size() > 0)
            {
                for (j = wmsCusCustomerHeadVOList.size() - 1; j >= 0; j--)
                {
                    if (wmsCusCustomerHeadVOList.get(j).getWms_cus_customer_id()
                                                .compareTo(wmscuscustomerheadvo.get(i).getWms_cus_customer_id()) == 0)
                    {
                        ishave = true;
                        WmsCusCustomerHead pageVo = wmsCusCustomerHeadVOList.get(j);
                        copyWmscrecreditlinecustomerchangehead(wmscrecreditlinecustomerchangehead, pageVo,
                                                               wmsCreCreditHeadId, user, wmscuscustomerheadvo.get(i));
                        break;
                    }
                }
            }

            if (!ishave)
            {
                copyWmscrecreditlinecustomerchangehead(wmscrecreditlinecustomerchangehead, wmscuscustomerheadvo.get(i),
                                                       wmsCreCreditHeadId, user, wmscuscustomerheadvo.get(i));
            }
            wmscrecreditlinecustomerchangeheadDao_m.saveWithKey(wmscrecreditlinecustomerchangehead);
            for (int k = 0; k < mcclc.size(); k++)
            {
                WmsCreCustomerChangeLineContact mccclcChange = mcclc.get(k);
                if (mccclcChange.getWms_cre_credit_line_customer_change_head_id()
                                .equals(wmscrecreditlinecustomerchangehead.getWms_cus_customer_head_id()))
                {
                    mccclcChange.setWms_cre_credit_line_customer_change_head_id(wmscrecreditlinecustomerchangehead.getWms_cre_credit_line_customer_change_head_id());
                    mcclc.set(k, mccclcChange);
                }
            }
            if (isComOrZC.equals("1"))
            {
                // �?��身份证号重复，并将重复记录存入数据库
                isRepeaded = isRepeaded || this.compareSfz(wmscrecreditlinecustomerchangehead, user);
                // 客户电话与联系人电话重复记录集合
                isRepeaded = isRepeaded || this.compareKhAndLxr(wmscrecreditlinecustomerchangehead, user);
                // 客户与客户电话重�?
                isRepeaded = isRepeaded || this.compareKhAndKh(wmscrecreditlinecustomerchangehead, user);
            }

            Integer customer_id = wmscrecreditlinecustomerchangehead.getWms_cre_credit_line_customer_change_head_id();
            if (ishave)
            {
                // 工作信息
                WmsCreCustomerChangeLineWorkinfo vo = WmsCreCustomerChangeLineWorkinfoVOList.get(j);// 工作信息
                vo.setWms_cre_credit_line_customer_change_head_id(customer_id);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                vo.setEnable_flag("1");
                wmscrecustomerchangelineworkinfoDao_m.addNewRecord(vo);

                // 房产信息
                for (Integer m = wmsHouseInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsHouseInfoVO housevo = wmsHouseInfoVOList.get(m);
                    //修改后的房产信息与原房产信息主贷人对应上
                    if (org_custom_info_id.compareTo(housevo.getWms_cus_customer_id()) == 0)
                    {
                        String housestr = housevo.getHousestr();
                        List<WmsCreCustomerChangeLineHouseinfo> houseList = JsonUtil.jsonArrayToList(housestr,
                                                                                                     WmsCreCustomerChangeLineHouseinfo.class);
                        for (WmsCreCustomerChangeLineHouseinfo housevo_p : houseList)
                        {
                            boolean hosFlag = false;
                            
                            if (housevo_p.getEnable_flag() != null && housevo_p.getEnable_flag().equals(mcclhid))
                            {
                                hosFlag = true;
                            }
                            housevo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            housevo_p.setCreate_user_id(user.getUserId());
                            housevo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            housevo_p.setLast_update_user_id(user.getUserId());
                            housevo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            housevo_p.setEnable_flag("1");
                            if (hosFlag)
                            {
                                wmscrecustomerchangelinehouseinfoDao_m.addNewRecordReKey(housevo_p);
                                isvcchid = housevo_p.getWms_cre_customer_change_line_houseinfo_id();
                            }
                            else
                                wmscrecustomerchangelinehouseinfoDao_m.addNewRecord(housevo_p);
                        }
                        break;
                    }
                }
                
                // 车产信息
                for (Integer m = wmsCarInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsCarInfoVO carvo = wmsCarInfoVOList.get(m);
                    if (org_custom_info_id.compareTo(carvo.getWms_cus_customer_id()) == 0)
                    {
                        String carstr = carvo.getCarstr();
                        List<WmsCreCustomerChangeLineCarpinfo> carList = JsonUtil.jsonArrayToList(carstr, WmsCreCustomerChangeLineCarpinfo.class);
                        for (WmsCreCustomerChangeLineCarpinfo carvo_p : carList)
                        {
                            carvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            carvo_p.setCreate_user_id(user.getUserId());
                            carvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            carvo_p.setLast_update_user_id(user.getUserId());
                            carvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            carvo_p.setEnable_flag("1");
                            wmscrecustomerchangelinecarpinfoDao_m.addNewRecordReKey(carvo_p);
                        }
                        break;
                    }
                }

                // 公司信息
                for (Integer m = wmsCompanyInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsCompanyInfoVO companyvo = wmsCompanyInfoVOList.get(m);
                    if (org_custom_info_id.compareTo(companyvo.getWms_cus_customer_id()) == 0)
                    {
                        String companystr = companyvo.getCompanystr();
                        List<WmsCreCustomerChangeLineCompany> companyList = JsonUtil.jsonArrayToList(companystr,
                                                                                                     WmsCreCustomerChangeLineCompany.class);
                        for (WmsCreCustomerChangeLineCompany companyvo_p : companyList)
                        {
                            companyvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            companyvo_p.setCreate_user_id(user.getUserId());
                            companyvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            companyvo_p.setLast_update_user_id(user.getUserId());
                            companyvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            companyvo_p.setEnable_flag("1");
                            wmsCreCustomerChangeLineCompanyDao.save(companyvo_p);
                        }
                        break;
                    }
                }
                // 子女
                for (Integer m = wmsChildInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsChildInfoVO childvo = wmsChildInfoVOList.get(m);
                    if (org_custom_info_id.compareTo(childvo.getWms_cus_customer_id()) == 0)
                    {
                        String childstr = childvo.getCusChild();
                        List<WmsCusCustomerChangeChild> childList = JsonUtil.jsonArrayToList(childstr,
                                                                                             WmsCusCustomerChangeChild.class);
                        for (WmsCusCustomerChangeChild childvo_p : childList)
                        {
                            childvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            childvo_p.setCreate_user_id(user.getUserId());
                            childvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            childvo_p.setLast_update_user_id(user.getUserId());
                            childvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            childvo_p.setEnable_flag("1");
                            wmsCusCustomerChangeChildDao.save(childvo_p);
                        }
                        break;
                    }
                }
            }
            else
            {
                // 工作信息
                WmsCusCustomerLineWorkinfo workinfoQry = new WmsCusCustomerLineWorkinfo();
                workinfoQry.setWms_cus_customer_id(org_custom_info_id);
                workinfoQry.setEnable_flag("1");
                List<WmsCusCustomerLineWorkinfo> list = wmsCusCustomerLineWorkinfoDao_m.getSingleTableListByAndMethod(workinfoQry);
                if (list != null && list.size() > 0)
                {
                    WmsCusCustomerLineWorkinfo customerLineWork = list.get(0);
                    WmsCreCustomerChangeLineWorkinfo customerChangeLineWork = new WmsCreCustomerChangeLineWorkinfo();
                    copyWmsCreCustomerChangeLineWorkinfo(customerLineWork, customerChangeLineWork);
                    customerChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                    customerChangeLineWork.setCreate_user_id(user.getUserId());
                    customerChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                    customerChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                    wmscrecustomerchangelineworkinfoDao_m.addNewRecord(customerChangeLineWork);
                }

                // 房产信息
                WmsCusCustomerLineHouseinfo qryinfo = new WmsCusCustomerLineHouseinfo();
                qryinfo.setWms_cus_customer_id(org_custom_info_id);
                qryinfo.setEnable_flag("1");
                List<WmsCusCustomerLineHouseinfo> list2 = wmsCusCustomerLineHouseinfoDao_m.getSingleTableListByAndMethod(qryinfo);
                if (list2 != null)
                {
                    for (int k = 0; k < list2.size(); k++)
                    {
                        WmsCusCustomerLineHouseinfo houserWork = list2.get(k);
                        boolean hosFlag = false;
                        if (String.valueOf(houserWork.getWms_cus_customer_line_houseinfo_id()).equals(mcclhid))
                        {
                            hosFlag = true;
                        }
                        WmsCreCustomerChangeLineHouseinfo houserWorkChangeLineWork = new WmsCreCustomerChangeLineHouseinfo();
                        copyWmsCreCustomerChangeLineHouseinfo(houserWork, houserWorkChangeLineWork);
                        houserWorkChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                        houserWorkChangeLineWork.setCreate_user_id(user.getUserId());
                        houserWorkChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                        houserWorkChangeLineWork.setLast_update_user_id(user.getUserId());
                        houserWorkChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                        houserWorkChangeLineWork.setEnable_flag("1");
                        if (hosFlag)
                        {
                            wmscrecustomerchangelinehouseinfoDao_m.addNewRecordReKey(houserWorkChangeLineWork);
                            isvcchid = houserWorkChangeLineWork.getWms_cre_customer_change_line_houseinfo_id();
                        }
                        else
                            wmscrecustomerchangelinehouseinfoDao_m.addNewRecord(houserWorkChangeLineWork);
                    }
                }
                
                // 车产信息
                WmsCusCustomerLineCarpinfo carpinfo = new WmsCusCustomerLineCarpinfo();
                carpinfo.setWms_cus_customer_id(org_custom_info_id);
                carpinfo.setEnable_flag("1");
                List<WmsCusCustomerLineCarpinfo> listcar = wmsCusCustomerLineCarpinfoDao.getListByEntity(carpinfo);
                if(listcar != null) {
                	for(int m = 0; m < listcar.size(); m++) {
                		WmsCusCustomerLineCarpinfo lineCarpinfo = listcar.get(m);
                		WmsCreCustomerChangeLineCarpinfo changeLineCarpinfo = new WmsCreCustomerChangeLineCarpinfo();
                		copyWmsCreCustomerChangeLineCarinfo(lineCarpinfo, changeLineCarpinfo);
                		changeLineCarpinfo.setWms_cre_credit_line_customer_change_head_id(customer_id);
                		changeLineCarpinfo.setCreate_user_id(user.getUserId());
                		changeLineCarpinfo.setCreate_timestamp(sysTime);
                		changeLineCarpinfo.setLast_update_user_id(user.getUserId());
                		changeLineCarpinfo.setLast_update_timestamp(sysTime);
                		changeLineCarpinfo.setEnable_flag("1");
                		wmscrecustomerchangelinecarpinfoDao_m.addNewRecordReKey(changeLineCarpinfo);
                	}
                }
                
                // 公司信息
                WmsCusCustomerLineCompany qryinfo3 = new WmsCusCustomerLineCompany();
                qryinfo3.setWms_cus_customer_id(org_custom_info_id);
                qryinfo3.setEnable_flag("1");
                List<WmsCusCustomerLineCompany> list3 = wmsCusCustomerLineCompanyDao.getSingleTableListByAndMethod(qryinfo3);
                if (list3 != null)
                {
                    for (int k = 0; k < list3.size(); k++)
                    {
                        WmsCusCustomerLineCompany companyWork = list3.get(k);
                        WmsCreCustomerChangeLineCompany companyWorkChangeLineWork = new WmsCreCustomerChangeLineCompany();
                        copyWmsCreCustomerChangeLinCompanyinfo(companyWork, companyWorkChangeLineWork);
                        companyWorkChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                        companyWorkChangeLineWork.setCreate_user_id(user.getUserId());
                        companyWorkChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                        companyWorkChangeLineWork.setLast_update_user_id(user.getUserId());
                        companyWorkChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                        companyWorkChangeLineWork.setEnable_flag("1");
                        wmsCreCustomerChangeLineCompanyDao.save(companyWorkChangeLineWork);
                    }
                }

                // 子女信息
                WmsCusCustomerChild qryinfo4 = new WmsCusCustomerChild();
                qryinfo4.setWms_cus_customer_id(org_custom_info_id);
                qryinfo4.setEnable_flag("1");
                List<WmsCusCustomerChild> list4 = wmsCusCustomerChildDao.getSingleTableListByAndMethod(qryinfo4);
                if (list4 != null)
                {
                    for (int k = 0; k < list4.size(); k++)
                    {
                        WmsCusCustomerChild childWork = list4.get(k);
                        WmsCusCustomerChangeChild childWorkChangeLineWork = new WmsCusCustomerChangeChild();
                        copyWmsCreCustomerChangeLinChildinfo(childWork, childWorkChangeLineWork);
                        childWorkChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                        childWorkChangeLineWork.setCreate_user_id(user.getUserId());
                        childWorkChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                        childWorkChangeLineWork.setLast_update_user_id(user.getUserId());
                        childWorkChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                        childWorkChangeLineWork.setEnable_flag("1");
                        wmsCusCustomerChangeChildDao.save(childWorkChangeLineWork);
                    }
                }
            }
        }

        /*----------------------------------------------wms_cre_credit_line_customer_change_head 客户信息变更�?end----------------------------------------------*/
        // 抵押房产信息
        WmsCreHousingCustomerHouse wmshch = new WmsCreHousingCustomerHouse();
        wmshch.setWms_cre_credit_head_id(wmsCreCreditHeadId);
        wmshch.setWms_cre_customer_change_line_houseinfo_id(isvcchid);
        wmshch.setHouse_use(house_use);
        wmshch.setCreate_user_id(user.getUserId()); // 创建人id
        wmshch.setCreate_user_name(user.getRealname());// 创建人名�?
        wmshch.setCreate_timestamp(sysTime);// 创建时间
        wmsCreHousingCustomerHouseDao.save(wmshch);
        // 添加抵押房产信息 �?
        /*----------------------------------------------wms_cre_customer_change_line_contact 客户联系人表 begin----------------------------------------------*/
        for (int i = 0; i < mcclc.size(); i++)
        {
            mcclc.get(i).setWms_cre_credit_head_id(wmsCreCreditHeadId);
            wmscrecustomerchangelinecontactDao_m.addNewRecord(mcclc.get(i));
            if (isComOrZC.equals("1"))
            {
                // 联系人电话重复记录集�?
                isRepeaded = isRepeaded || this.compareLxrAndLxr(mcclc.get(i), user);
                // 联系人与客户电话重复
                isRepeaded = isRepeaded || this.compareLxrAndKh(mcclc.get(i), user);
            }
        }
        if (isRepeaded)
        {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("is_link_repeat", "1");// 联系方式是否重复
            params.put("wms_cre_credit_head_id", wmsCreCreditHeadId);
            wmscrecreditheadDao_m.updateRecord(params);
        }
        /*----------------------------------------------wms_cre_customer_change_line_contact 客户联系人表 end----------------------------------------------*/
        for (int i = 0; i < attachment.size(); i++)
        {
            WmsCreHousingAtt mplm = attachment.get(i);
            mplm.setData_type("5");// 5为初审评估信息附�?
            // mplm.setWms_cre_credit_head_id(Integer.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()));
            mplm.setWms_cre_credit_head_id(mcch.getWms_cre_credit_head_id());
            mplm.setCreate_user_id(user.getUserId()); // 创建人id
            mplm.setCreate_user_name(user.getRealname());// 创建人名�?
            mplm.setCreate_timestamp(sysTime);// 创建时间
            mplm.setEnable_flag("1");// 是否有效
            wmscrehousingattDao.save(mplm);
        }

        if(null != atthousing) {
        	Map<String, Object> attParamMap = new HashMap<String, Object>();
            attParamMap.put("bill_code", mcch.getBill_code());
        	//保存上传的图片信息
            for(WmsCreHousingApplyAtt att: atthousing) {
                att.setWms_cre_credit_head_id(mcch.getWms_cre_credit_head_id());
                attParamMap.put("data_detail_name", att.getData_detail_name());
                //生成新的图片编号
                att.setAttachment_old_name(wmsCreHousingApplyAttDao.getNextAttSeqByBillCodeAndDataTypeName(attParamMap));
                wmsCreHousingApplyAttDao.save(att);
            }
        }
        
        return result;
    }

    /**
     * 房贷完善列表导出
     */
    @Override
    public Map<String, Object> getHouseListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user)
    {

   	   // 流程获得idList
        Map<String, Object> paramMap = houseCreditWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),"0");
       //paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "1");//合并俩个流程
        paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "1",WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);//合并俩个流程
        if (paramMap.get("idList") == null)
        {
            return new HashMap<>();
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        paramMap.put("create_user_id", user.getUserId());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        List<Map<String, Object>> list = wmscrecreditheadDao_m.recheckForHouseWithout(paramMap);
        paramMap.put("Rows", list);
        return paramMap;
    }

    /**
     * 实现房贷完善列表
     */
    @Override
    public Map<String, Object> getListWithPagingforaddHouse(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user)
    {
        // 流程获得idList(String.valueOf(user.getUserId()),"0");
    	 // 流程获得idList
        Map<String, Object> paramMap = houseCreditWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),"0");
        //paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "1");//合并俩个流程
        paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "1",WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);//合并俩个流程
        if (paramMap.get("idList") == null)
        {
            return new HashMap<>();
        }
        // 添加查询条件
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        paramMap.put("create_user_id", user.getUserId());
        paramMap.put("salesman_city_code", user.getUser_city());
        paramMap.put("cre_type", queryInfo.getCre_type());
        paramMap.put("bill_status", "B");
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        List<Map<String, Object>> list = wmscrecreditheadDao_m.recheckForHouse(paramMap);
        // 添加taskId
        list = houseCreditWorkFlowService.addTaskIDHouse(list, (List<Integer>) paramMap.get("idList"),
                                                         (List<String>) paramMap.get("taskIdList"));
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), wmscrecreditheadDao_m.findCountRecheck0(paramMap),
                                             list);
        return bean.getGridData();
    }

    /**
     * 实现贷款复核退回查询
     */
    @Override
    public Map<String, Object> getRecheckReturnListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = creditWorkFlowService.getIdTaskIdCreditList(String.valueOf(user.getUserId()),
                                                                                   "12");
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        paramMap.put("create_user_id", user.getUserId());
        paramMap.put("cre_type", "1");
        paramMap.put("bill_status", "12");
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        List<Map<String, Object>> list = wmscrecreditheadDao_m.getRecheckListWithPaging(paramMap);
        list = creditWorkFlowService.addTaskCredit(list, (List<Integer>) paramMap.get("idList"),
                                                   (List<String>) paramMap.get("taskIdList"));
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), wmscrecreditheadDao_m.findCountForRecheck(paramMap),
                                             list);
        return bean.getGridData();
    }
	/**
	 * 房贷完善退回--重新申请
	 */
    @Override
    public Map<String, Object> getCompleteReturnListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user)
    {	// 流程获得idList
        Map<String, Object> paramMap = houseCreditWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
                                                                                    "11");
        //paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "2");//合并俩个流程
        paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "2",WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);//合并俩个流程
       if (paramMap.get("idList") == null)
        {
            return new HashMap<>();
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        paramMap.put("create_user_id", user.getUserId());
        paramMap.put("cre_type", "2");
        paramMap.put("bill_status", "I");
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        // 增加区域编码限定
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        List<Map<String, Object>> list = wmscrecreditheadDao_m.getRecheckListWithPaging(paramMap);
        list = houseCreditWorkFlowService.addTaskIDHouse(list, (List<Integer>) paramMap.get("idList"),
                                                         (List<String>) paramMap.get("taskIdList"));
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), wmscrecreditheadDao_m.findCountForRecheck(paramMap),
                                             list);
        return bean.getGridData();
    }

    /**
     * 实现信贷复核导出
     */
    @Override
    public Map<String, Object> getRecheckReturnListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user)
    {

        Map<String, Object> paramMap = creditWorkFlowService.getIdTaskIdCreditList(String.valueOf(user.getUserId()),
                                                                                   "12");
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        paramMap.put("bill_status", "12");
        paramMap.put("cre_type", "1");
        paramMap.put("create_user_id", user.getUserId());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditheadDao_m.getRecheckListWithoutPaging(paramMap);
        paramMap.put("Rows", list);
        return paramMap;
    }
    

    @Override
    public Map<String, Object> getCompleteReturnListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user)
    {
    	// 流程获得idList
        Map<String, Object> paramMap = houseCreditWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
                                                                                    "11");
        //paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "2");//合并俩个流程
        paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "2",WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);//合并俩个流程
        if (paramMap.get("idList") == null)
        {
            return new HashMap<>();
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        paramMap.put("bill_status", "I");
        paramMap.put("cre_type", "2");
        paramMap.put("create_user_id", user.getUserId());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditheadDao_m.getRecheckListWithoutPaging(paramMap);
        paramMap.put("Rows", list);
        return paramMap;
    }

    @Override
    public Map<String, Object> getLoanBeforeReturnListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = creditWorkFlowService.getIdTaskIdCreditList(String.valueOf(user.getUserId()),
                                                                                   "13");
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        paramMap.put("create_user_id", user.getUserId());
        paramMap.put("cre_type", "1");
        paramMap.put("bill_status", "13");
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        List<Map<String, Object>> list = wmscrecreditheadDao_m.getLoanBeforeReturnListWithPaging(paramMap);
        list = creditWorkFlowService.addTaskCredit(list, (List<Integer>) paramMap.get("idList"),
                                                   (List<String>) paramMap.get("taskIdList"),
                                                   (List<String>) paramMap.get("approvesGroups"),
                                                   (List<String>) paramMap.get("approveAdvices"),
                                                   (List<String>) paramMap.get("approveTimes"));
        GridDataBean bean = new GridDataBean(queryInfo.getPage(),
                                             wmscrecreditheadDao_m.findCountForLoanBeforeReturn(paramMap), list);
        return bean.getGridData();
    }

    @Override
    public Map<String, Object> getHouseBeforeReturnListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = houseCreditWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
                                                                                    "12");
        paramMap = wmsLoanWorkFlowService.setTaskListBJ(paramMap,user.getUserId(), "5");//合并俩个流程	
        if (paramMap.get("idList") == null)
        {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("Rows", new ArrayList<Map<String, Object>>());
            return resultMap;
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        paramMap.put("create_user_id", user.getUserId());
        paramMap.put("cre_type", "2");
        paramMap.put("bill_status", "J");
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        List<Map<String, Object>> list = wmscrecreditheadDao_m.getLoanBeforeReturnListWithPaging(paramMap);
        list = houseCreditWorkFlowService.addTaskIDHouse(list, (List<Integer>) paramMap.get("idList"),
                                                         (List<String>) paramMap.get("taskIdList"),
                                                         (List<String>) paramMap.get("approvesGroups"),
                                                         (List<String>) paramMap.get("approveAdvices"),
                                                         (List<String>) paramMap.get("approveTimes"));
        GridDataBean bean = new GridDataBean(queryInfo.getPage(),
                                             wmscrecreditheadDao_m.findCountForLoanBeforeReturn(paramMap), list);
        return bean.getGridData();
    }

    @Override
    public Map<String, Object> getLoanBeforeReturnListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                                    UserBean user)
    {
        Map<String, Object> paramMap = creditWorkFlowService.getIdTaskIdCreditList(String.valueOf(user.getUserId()),
                                                                                   "13");
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        paramMap.put("create_user_id", user.getUserId());
        paramMap.put("cre_type", "1");
        paramMap.put("bill_status", "13");
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        List<Map<String, Object>> list = wmscrecreditheadDao_m.getLoanBeforeReturnListWithPaging(paramMap);
        list = creditWorkFlowService.addTaskCredit(list, (List<Integer>) paramMap.get("idList"),
                                                   (List<String>) paramMap.get("taskIdList"),
                                                   (List<String>) paramMap.get("approvesGroups"),
                                                   (List<String>) paramMap.get("approveAdvices"),
                                                   (List<String>) paramMap.get("approveTimes"));
        paramMap.put("Rows", list);
        return paramMap;
    }

    @Override
    public Map<String, Object> getHouseBeforeReturnListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                                     UserBean user)
    {
        Map<String, Object> paramMap = houseCreditWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
                                                                                    "12");
        paramMap = wmsLoanWorkFlowService.setTaskListBJ(paramMap,user.getUserId(), "5");//合并俩个流程	
        if (paramMap.get("idList") == null)
        {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("Rows", new ArrayList<Map<String, Object>>());
            return resultMap;
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        paramMap.put("create_user_id", user.getUserId());
        paramMap.put("cre_type", "2");
        paramMap.put("bill_status", "J");
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        List<Map<String, Object>> list = wmscrecreditheadDao_m.getLoanBeforeReturnListWithPaging(paramMap);
        list = houseCreditWorkFlowService.addTaskIDHouse(list, (List<Integer>) paramMap.get("idList"),
                                                         (List<String>) paramMap.get("taskIdList"),
                                                         (List<String>) paramMap.get("approvesGroups"),
                                                         (List<String>) paramMap.get("approveAdvices"),
                                                         (List<String>) paramMap.get("approveTimes"));
        paramMap.put("Rows", list);
        return paramMap;
    }

    @Override
    @Transactional
    public String updateCredit(WmsCreCreditHead mcch, String attachment, String mccclc, UserBean user, String clientId,
                               String modifyJsonCus, String isComOrZC)
    {
        String result = "temOK";
        Integer wmsCreCreditHeadId = mcch.getWms_cre_credit_head_id();// 贷款表主�?

        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间

        List<WmsCreCreditLineCustomerDataAttachment> Lattachment = JsonUtil.jsonArrayToList(attachment,
                                                                                            WmsCreCreditLineCustomerDataAttachment.class); // 前台json附件路径数据转换为list
        List<WmsCreCustomerChangeLineContact> Lmccclc = JsonUtil.jsonArrayToList(mccclc,
                                                                                 WmsCreCustomerChangeLineContact.class); // 前台json客户联系人数据转换为list数据

        /*----------------------------------------------wms_cre_credit_head 贷款申请单主�?begin----------------------------------------------*/
        mcch.setLast_update_user_id(user.getUserId()); // 更新人id
        mcch.setLast_update_timestamp(sysTime);// 更新时间
        /*if (mcch.getSalesman_id() != null)
        {
            if (mcch.getCity().indexOf("0") != -1)
            {
                mcch.setCity(UserCityUtil.getUserCity(mcch.getCity()));
            }
        }*/
        // 把贷款产品类型 存储到备份的字段中
        mcch.setCre_loan_type_backup(mcch.getCre_loan_type());
        // 把贷款申请最大期限 存储到备份的字段中
        mcch.setMax_repayment_time_limit_backup(mcch.getMax_repayment_time_limit());

        wmscrecreditheadDao_m.updateMCCHWhenUpdateOrReSub(mcch.getInfoMap()); // 更新贷款主表
        /*----------------------------------------------wms_cre_credit_head 贷款申请单主�?end----------------------------------------------*/

        // 待写.........................................
        if (isComOrZC.equals("1"))
        {
            result = "OK";
            WmsCreCreditHead searchBillSt = wmscrecreditheadDao_m.get(wmsCreCreditHeadId);
            String keyPass = "";
            if (searchBillSt.getBill_status().equals("0"))
            {
                keyPass = "1";// 代表草稿
            }
            else if (searchBillSt.getBill_status().equals("12"))
            {
                keyPass = "2";// 代表复核退回
            }
            else if (searchBillSt.getBill_status().equals("13"))
            {
                keyPass = "3";// 代表补件
            }
            /*---------------------信贷流程-------------------*/
            WmsCreditWorkFlowVO aWorkFlowVO = new WmsCreditWorkFlowVO();
            aWorkFlowVO.setUser_id(user.getUserId());
            aWorkFlowVO.setWms_cre_credit_head_id(wmsCreCreditHeadId);
            aWorkFlowVO.setCredit_limit(String.valueOf(mcch.getCredit_limit()));
            aWorkFlowVO.setCre_loan_type(String.valueOf(mcch.getCre_loan_type()));
            // creditWorkFlowService.creditInquiryTreatment(String.valueOf(user.getUserId()),
            // String.valueOf(wmsCreCreditHeadId),String.valueOf(mcch.getCredit_limit()),String.valueOf(mcch.getCre_loan_type()),keyPass);
            creditWorkFlowService.creditInquiryTreatment(aWorkFlowVO, keyPass);
        }

        /*----------------------------------------------wms_cre_credit_line_customer_change_head 客户信息变更�?begin----------------------------------------------*/
        WmsCreCreditLineCustomerChangeHead wmscrecreditlinecustomerchangehead; // 客户信息变更�?

        List<WmsCusCustomerHead> wmscuscustomerheadvo = new ArrayList<WmsCusCustomerHead>();// 客户信息
                                                                                            // 通过查询主键得到
        List<WmsCreCreditLineCustomerChangeHead> wmscuscustomerchangeheadvoList = new ArrayList<WmsCreCreditLineCustomerChangeHead>();// 客户信息变更信息
                                                                                                                                      // 通过查询主键得到
        ArrayList<Integer> changedNochangeIDS = new ArrayList<>();
        if (!(clientId.equals("")))
        {
            for (String id : clientId.split(","))
            {
                String[] idarr = id.split("@@@");
                if (("change").equals(idarr[2]))
                {
                    WmsCreCreditLineCustomerChangeHead mcclcchVOByGet = wmscrecreditlinecustomerchangeheadDao_m.get(Integer.valueOf(idarr[0]));
                    wmscuscustomerchangeheadvoList.add(mcclcchVOByGet);
                    changedNochangeIDS.add(Integer.valueOf(idarr[0]));
                    Map<String, Object> updateIsMajorMap = new HashMap<String, Object>();
                    updateIsMajorMap.put("is_major", idarr[1]);
                    updateIsMajorMap.put("wms_cre_credit_line_customer_change_head_id", idarr[0]);
                    wmscrecreditlinecustomerchangeheadDao_m.updateIsMajor(updateIsMajorMap);
                }
                else if (("cus").equals(idarr[2]))
                {
                    WmsCusCustomerHead mcchvo = wmscuscustomerheadDao_m.get(Integer.valueOf(idarr[0]));
                    mcchvo.setEnable_flag(idarr[1]);
                    wmscuscustomerheadvo.add(mcchvo);
                }

            }
            ;
        }

        // 删除 未�?择的客户变更信息
        List<WmsCreCreditLineCustomerChangeHead> allChangeCusIn = wmscrecreditlinecustomerchangeheadDao_m.getWmsCreCreditLineCustomerChangeHeadListWithoutPaging(wmsCreCreditHeadId);
        ArrayList<Integer> dltIdsList = new ArrayList<>();
        for (int i = 0; i < allChangeCusIn.size(); i++)
        {
            boolean notInFlag = false;
            for (int j = 0; j < wmscuscustomerchangeheadvoList.size(); j++)
            {
                if (allChangeCusIn.get(i)
                                  .getWms_cre_credit_line_customer_change_head_id()
                                  .equals(wmscuscustomerchangeheadvoList.get(j)
                                                                        .getWms_cre_credit_line_customer_change_head_id()))
                {
                    notInFlag = true;
                    break;
                }
            }
            if (!notInFlag)
            {
                dltIdsList.add(allChangeCusIn.get(i).getWms_cre_credit_line_customer_change_head_id());
            }
        }
        Map<String, Object> deleteParamsMap = new HashMap<String, Object>();
        if (dltIdsList.size() > 0)
        {
            deleteParamsMap.put("dltIDArr", dltIdsList);
            wmscrecreditlinecustomerchangeheadDao_m.deleteByMap(deleteParamsMap);
            wmscrecustomerchangelinecontactDao_m.deleteByMap(deleteParamsMap);
            wmscrecustomerchangelinehouseinfoDao_m.deleteByMap(deleteParamsMap);
            wmsCreCustomerChangeLineCarpinfoDao.deleteByMap(deleteParamsMap);
            wmscrecustomerchangelineworkinfoDao_m.deleteByMap(deleteParamsMap);
            wmsCreCustomerChangeLineCompanyDao.deleteByMap(deleteParamsMap);
            wmsCusCustomerChangeChildDao.deleteByMap(deleteParamsMap);
        }
        // 删除�?��重复校验
        dltIdsList.clear();
        dltIdsList.add(wmsCreCreditHeadId);
        deleteParamsMap.clear();
        deleteParamsMap.put("dltIDArr", dltIdsList);
        wmsCreCreditHeadDiffPhoneDao.deleteByMap(deleteParamsMap);

        // 修改后的客户信息
        List<WmsCusCustomerHead> wmsCusCustomerHeadVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                     WmsCusCustomerHead.class);// 修改后list
        // 修改后的工作信息
        List<WmsCreCustomerChangeLineWorkinfo> WmsCreCustomerChangeLineWorkinfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                                                 WmsCreCustomerChangeLineWorkinfo.class);// 修改后list

        // housestr
        List<WmsHouseInfoVO> wmsHouseInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsHouseInfoVO.class);// 修改后list
        
        // 修改后的车产信息
        List<WmsCarInfoVO> wmsCarInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsCarInfoVO.class);
        
        // 修改后的公司信息
        List<WmsCompanyInfoVO> wmsCompanyInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsCompanyInfoVO.class);

        // 修改后的子女信息
        List<WmsChildInfoVO> wmsChildInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsChildInfoVO.class);

        //
        boolean isRepeaded = false;
        for (int i = 0; i < wmscuscustomerheadvo.size(); i++)
        {
            boolean ishave = false;// 是否修改�?
            Integer org_custom_info_id = wmscuscustomerheadvo.get(i).getWms_cus_customer_id();
            wmscrecreditlinecustomerchangehead = new WmsCreCreditLineCustomerChangeHead();
            int j = 0;// 修改客户信息list的index
            if (wmsCusCustomerHeadVOList != null && wmsCusCustomerHeadVOList.size() > 0)
            {
                if (wmsCusCustomerHeadVOList.get(j).getWms_cus_customer_id() != null)
                {
                    for (j = wmsCusCustomerHeadVOList.size() - 1; j >= 0; j--)
                    {
                        if (wmsCusCustomerHeadVOList.get(j).getWms_cus_customer_id()
                                                    .compareTo(wmscuscustomerheadvo.get(i).getWms_cus_customer_id()) == 0)
                        {
                            ishave = true;
                            WmsCusCustomerHead pageVo = wmsCusCustomerHeadVOList.get(j);
                            copyWmscrecreditlinecustomerchangehead(wmscrecreditlinecustomerchangehead, pageVo,
                                                                   wmsCreCreditHeadId, user,
                                                                   wmscuscustomerheadvo.get(i));
                            break;
                        }
                    }
                }
                else
                {
                    break;
                }
            }

            if (!ishave)
            {
                copyWmscrecreditlinecustomerchangehead(wmscrecreditlinecustomerchangehead, wmscuscustomerheadvo.get(i),
                                                       wmsCreCreditHeadId, user, wmscuscustomerheadvo.get(i));
            }
            wmscrecreditlinecustomerchangeheadDao_m.saveWithKey(wmscrecreditlinecustomerchangehead);
            for (int k = 0; k < Lmccclc.size(); k++)
            {
                WmsCreCustomerChangeLineContact mccclcChange = Lmccclc.get(k);
                if (mccclcChange.getWms_cre_credit_line_customer_change_head_id()
                                .equals(wmscrecreditlinecustomerchangehead.getWms_cus_customer_head_id()))
                {
                    mccclcChange.setWms_cre_credit_line_customer_change_head_id(wmscrecreditlinecustomerchangehead.getWms_cre_credit_line_customer_change_head_id());
                    Lmccclc.set(k, mccclcChange);
                }
            }
            if (isComOrZC.equals("1"))
            {
                // �?��身份证号重复，并将重复记录存入数据库
                isRepeaded = isRepeaded || this.compareSfz(wmscrecreditlinecustomerchangehead, user);
                // 客户电话与联系人电话重复记录集合
                isRepeaded = isRepeaded || this.compareKhAndLxr(wmscrecreditlinecustomerchangehead, user);
                // 客户与客户电话重�?
                isRepeaded = isRepeaded || this.compareKhAndKh(wmscrecreditlinecustomerchangehead, user);
            }
            Integer customer_id = wmscrecreditlinecustomerchangehead.getWms_cre_credit_line_customer_change_head_id();
            if (ishave)
            {
                // 工作信息
                WmsCreCustomerChangeLineWorkinfo vo = WmsCreCustomerChangeLineWorkinfoVOList.get(j);// 工作信息
                vo.setWms_cre_credit_line_customer_change_head_id(customer_id);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                vo.setEnable_flag("1");
                wmscrecustomerchangelineworkinfoDao_m.addNewRecord(vo);

                // 房产信息
                for (Integer m = wmsHouseInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsHouseInfoVO housevo = wmsHouseInfoVOList.get(m);
                    if (org_custom_info_id.compareTo(housevo.getWms_cus_customer_id()) == 0)
                    {
                        String housestr = housevo.getHousestr();
                        List<WmsCreCustomerChangeLineHouseinfo> houseList = JsonUtil.jsonArrayToList(housestr,
                                                                                                     WmsCreCustomerChangeLineHouseinfo.class);
                        for (WmsCreCustomerChangeLineHouseinfo housevo_p : houseList)
                        {
                            housevo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            housevo_p.setCreate_user_id(user.getUserId());
                            housevo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            housevo_p.setLast_update_user_id(user.getUserId());
                            housevo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            housevo_p.setEnable_flag("1");
                            wmscrecustomerchangelinehouseinfoDao_m.addNewRecord(housevo_p);
                        }
                        break;
                    }
                }
                
                // 车产信息
                for (Integer m = wmsCarInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsCarInfoVO carvo = wmsCarInfoVOList.get(m);
                    if (org_custom_info_id.compareTo(carvo.getWms_cre_credit_line_customer_change_head_id()) == 0)
                    {
                        String carstr = carvo.getCarstr();
                        List<WmsCreCustomerChangeLineCarpinfo> carList = JsonUtil.jsonArrayToList(carstr, WmsCreCustomerChangeLineCarpinfo.class);
                        for (WmsCreCustomerChangeLineCarpinfo carvo_p : carList)
                        {
                            carvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            carvo_p.setCreate_user_id(user.getUserId());
                            carvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            carvo_p.setLast_update_user_id(user.getUserId());
                            carvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            carvo_p.setEnable_flag("1");
                            wmscrecustomerchangelinecarpinfoDao_m.addNewRecordReKey(carvo_p);
                        }
                        break;
                    }
                }

                // 公司信息
                for (Integer m = wmsCompanyInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsCompanyInfoVO companyvo = wmsCompanyInfoVOList.get(m);
                    if (org_custom_info_id.compareTo(companyvo.getWms_cus_customer_id()) == 0)
                    {
                        String companystr = companyvo.getCompanystr();
                        List<WmsCreCustomerChangeLineCompany> companyList = JsonUtil.jsonArrayToList(companystr,
                                                                                                     WmsCreCustomerChangeLineCompany.class);
                        for (WmsCreCustomerChangeLineCompany companyvo_p : companyList)
                        {
                            companyvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            companyvo_p.setCreate_user_id(user.getUserId());
                            companyvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            companyvo_p.setLast_update_user_id(user.getUserId());
                            companyvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            companyvo_p.setEnable_flag("1");
                            wmsCreCustomerChangeLineCompanyDao.save(companyvo_p);
                        }
                        break;
                    }
                }
                // 子女
                for (Integer m = wmsChildInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsChildInfoVO childvo = wmsChildInfoVOList.get(m);
                    if (org_custom_info_id.compareTo(childvo.getWms_cus_customer_id()) == 0)
                    {
                        String childstr = childvo.getCusChild();
                        List<WmsCusCustomerChangeChild> childList = JsonUtil.jsonArrayToList(childstr,
                                                                                             WmsCusCustomerChangeChild.class);
                        for (WmsCusCustomerChangeChild childvo_p : childList)
                        {
                            childvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            childvo_p.setCreate_user_id(user.getUserId());
                            childvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            childvo_p.setLast_update_user_id(user.getUserId());
                            childvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            childvo_p.setEnable_flag("1");
                            wmsCusCustomerChangeChildDao.save(childvo_p);
                        }
                        break;
                    }
                }
            }
            else
            {
                // 工作信息
                WmsCusCustomerLineWorkinfo workinfoQry = new WmsCusCustomerLineWorkinfo();
                workinfoQry.setWms_cus_customer_id(org_custom_info_id);
                workinfoQry.setEnable_flag("1");
                List<WmsCusCustomerLineWorkinfo> list = wmsCusCustomerLineWorkinfoDao_m.getSingleTableListByAndMethod(workinfoQry);
                if (list != null && list.size() > 0)
                {
                    WmsCusCustomerLineWorkinfo customerLineWork = list.get(0);
                    WmsCreCustomerChangeLineWorkinfo customerChangeLineWork = new WmsCreCustomerChangeLineWorkinfo();
                    copyWmsCreCustomerChangeLineWorkinfo(customerLineWork, customerChangeLineWork);
                    customerChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                    customerChangeLineWork.setCreate_user_id(user.getUserId());
                    customerChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                    customerChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                    wmscrecustomerchangelineworkinfoDao_m.addNewRecord(customerChangeLineWork);
                }

                // 房产信息
                WmsCusCustomerLineHouseinfo qryinfo = new WmsCusCustomerLineHouseinfo();
                qryinfo.setWms_cus_customer_id(org_custom_info_id);
                qryinfo.setEnable_flag("1");
                List<WmsCusCustomerLineHouseinfo> list2 = wmsCusCustomerLineHouseinfoDao_m.getSingleTableListByAndMethod(qryinfo);
                if (list2 != null)
                {
                    for (int k = 0; k < list2.size(); k++)
                    {
                        WmsCusCustomerLineHouseinfo houserWork = list2.get(k);
                        WmsCreCustomerChangeLineHouseinfo houserWorkChangeLineWork = new WmsCreCustomerChangeLineHouseinfo();
                        copyWmsCreCustomerChangeLineHouseinfo(houserWork, houserWorkChangeLineWork);
                        houserWorkChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                        houserWorkChangeLineWork.setCreate_user_id(user.getUserId());
                        houserWorkChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                        houserWorkChangeLineWork.setLast_update_user_id(user.getUserId());
                        houserWorkChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                        houserWorkChangeLineWork.setEnable_flag("1");
                        wmscrecustomerchangelinehouseinfoDao_m.addNewRecord(houserWorkChangeLineWork);
                    }
                }
                
                // 车产信息
                WmsCusCustomerLineCarpinfo carpinfo = new WmsCusCustomerLineCarpinfo();
                carpinfo.setWms_cus_customer_id(org_custom_info_id);
                carpinfo.setEnable_flag("1");
                List<WmsCusCustomerLineCarpinfo> listcar = wmsCusCustomerLineCarpinfoDao.getListByEntity(carpinfo);
                if(listcar != null) {
                	for(int m = 0; m < listcar.size(); m++) {
                		WmsCusCustomerLineCarpinfo lineCarpinfo = listcar.get(m);
                		WmsCreCustomerChangeLineCarpinfo changeLineCarpinfo = new WmsCreCustomerChangeLineCarpinfo();
                		copyWmsCreCustomerChangeLineCarinfo(lineCarpinfo, changeLineCarpinfo);
                		changeLineCarpinfo.setWms_cre_credit_line_customer_change_head_id(customer_id);
                		changeLineCarpinfo.setCreate_user_id(user.getUserId());
                		changeLineCarpinfo.setCreate_timestamp(sysTime);
                		changeLineCarpinfo.setLast_update_user_id(user.getUserId());
                		changeLineCarpinfo.setLast_update_timestamp(sysTime);
                		changeLineCarpinfo.setEnable_flag("1");
                		wmscrecustomerchangelinecarpinfoDao_m.addNewRecordReKey(changeLineCarpinfo);
                	}
                }

                // 公司信息
                WmsCusCustomerLineCompany qryinfo3 = new WmsCusCustomerLineCompany();
                qryinfo3.setWms_cus_customer_id(org_custom_info_id);
                qryinfo3.setEnable_flag("1");
                List<WmsCusCustomerLineCompany> list3 = wmsCusCustomerLineCompanyDao.getSingleTableListByAndMethod(qryinfo3);
                if (list3 != null)
                {
                    for (int k = 0; k < list3.size(); k++)
                    {
                        WmsCusCustomerLineCompany companyWork = list3.get(k);
                        WmsCreCustomerChangeLineCompany companyWorkChangeLineWork = new WmsCreCustomerChangeLineCompany();
                        copyWmsCreCustomerChangeLinCompanyinfo(companyWork, companyWorkChangeLineWork);
                        companyWorkChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                        companyWorkChangeLineWork.setCreate_user_id(user.getUserId());
                        companyWorkChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                        companyWorkChangeLineWork.setLast_update_user_id(user.getUserId());
                        companyWorkChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                        companyWorkChangeLineWork.setEnable_flag("1");
                        wmsCreCustomerChangeLineCompanyDao.save(companyWorkChangeLineWork);
                    }
                }

                // 子女信息
                WmsCusCustomerChild qryinfo4 = new WmsCusCustomerChild();
                qryinfo4.setWms_cus_customer_id(org_custom_info_id);
                qryinfo4.setEnable_flag("1");
                List<WmsCusCustomerChild> list4 = wmsCusCustomerChildDao.getSingleTableListByAndMethod(qryinfo4);
                if (list4 != null)
                {
                    for (int k = 0; k < list4.size(); k++)
                    {
                        WmsCusCustomerChild childWork = list4.get(k);
                        WmsCusCustomerChangeChild childWorkChangeLineWork = new WmsCusCustomerChangeChild();
                        copyWmsCreCustomerChangeLinChildinfo(childWork, childWorkChangeLineWork);
                        childWorkChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                        childWorkChangeLineWork.setCreate_user_id(user.getUserId());
                        childWorkChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                        childWorkChangeLineWork.setLast_update_user_id(user.getUserId());
                        childWorkChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                        childWorkChangeLineWork.setEnable_flag("1");
                        wmsCusCustomerChangeChildDao.save(childWorkChangeLineWork);
                    }
                }
            }
        }

        // 修改后的客户信息
        List<WmsCreCreditLineCustomerChangeHead> wmsCusCustomerHeadChangeVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                                           WmsCreCreditLineCustomerChangeHead.class);// 修改后list
        // 修改后的工作信息
        List<WmsCreCustomerChangeLineWorkinfo> WmsCreCustomerChangeLineWorkinfoVOList2 = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                                                  WmsCreCustomerChangeLineWorkinfo.class);// 修改后list

        // housestr
        List<WmsHouseChangeInfoVO> wmsHouseInfoChangeVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                       WmsHouseChangeInfoVO.class);// 修改后list
        // 修改后的车产信息
        List<WmsCarInfoVO> wmsCarInfoChangeVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsCarInfoVO.class);
        
        // 修改后的公司信息
        List<WmsCompanyChangeInfoVO> wmsCompanyInfoChangeVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                           WmsCompanyChangeInfoVO.class);

        // 修改后的子女信息
        List<WmsChildChangeInfoVO> wmsChildInfoChangeVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                       WmsChildChangeInfoVO.class);

        for (int i = 0; i < wmscuscustomerchangeheadvoList.size(); i++)
        {
            boolean ishave = false;// 是否修改�?
            Integer org_custom_info_change_id = wmscuscustomerchangeheadvoList.get(i)
                                                                              .getWms_cre_credit_line_customer_change_head_id();
            wmscrecreditlinecustomerchangehead = wmscuscustomerchangeheadvoList.get(i);
            int j = 0;// 修改客户信息list的index
            if (wmsCusCustomerHeadChangeVOList != null && wmsCusCustomerHeadChangeVOList.size() > 0)
            {
                for (j = wmsCusCustomerHeadChangeVOList.size() - 1; j >= 0; j--)
                {
                    if (wmsCusCustomerHeadChangeVOList.get(j).getWms_cre_credit_line_customer_change_head_id() != null)
                    {
                        if (wmsCusCustomerHeadChangeVOList.get(j)
                                                          .getWms_cre_credit_line_customer_change_head_id()
                                                          .compareTo(wmscuscustomerchangeheadvoList.get(i)
                                                                                                   .getWms_cre_credit_line_customer_change_head_id()) == 0)
                        {
                            ishave = true;
                            ArrayList<Integer> dltOneByOCICIDList = new ArrayList<>();
                            dltOneByOCICIDList.add(org_custom_info_change_id);
                            Map<String, Object> dltOneByOCICIDMap = new HashMap<String, Object>();
                            dltOneByOCICIDMap.put("dltIDArr", dltOneByOCICIDList);
                            WmsCreCreditLineCustomerChangeHead mcclcchVOReadyToDelete = wmscrecreditlinecustomerchangeheadDao_m.get(org_custom_info_change_id);
                            wmscrecreditlinecustomerchangeheadDao_m.deleteByMap(dltOneByOCICIDMap);
                            wmscrecustomerchangelinehouseinfoDao_m.deleteByMap(dltOneByOCICIDMap);
                            wmsCreCustomerChangeLineCarpinfoDao.deleteByMap(dltOneByOCICIDMap);
                            wmscrecustomerchangelineworkinfoDao_m.deleteByMap(dltOneByOCICIDMap);
                            wmsCreCustomerChangeLineCompanyDao.deleteByMap(dltOneByOCICIDMap);
                            wmsCusCustomerChangeChildDao.deleteByMap(dltOneByOCICIDMap);
                            WmsCreCreditLineCustomerChangeHead newInsertVO = wmsCusCustomerHeadChangeVOList.get(j);
                            newInsertVO.setWms_cre_credit_head_id(mcclcchVOReadyToDelete.getWms_cre_credit_head_id());
                            newInsertVO.setWms_cus_customer_head_id(mcclcchVOReadyToDelete.getWms_cus_customer_head_id());
                            newInsertVO.setCustomer_code(mcclcchVOReadyToDelete.getCustomer_code());
                            newInsertVO.setCreate_user_id(mcclcchVOReadyToDelete.getCreate_user_id());
                            newInsertVO.setCreate_user_name(mcclcchVOReadyToDelete.getCreate_user_name());
                            newInsertVO.setCreate_timestamp(mcclcchVOReadyToDelete.getCreate_timestamp());
                            newInsertVO.setLast_update_user_id(user.getUserId());
                            newInsertVO.setLast_update_timestamp(sysTime);
                            newInsertVO.setEnable_flag("1");
                            newInsertVO.setIs_major(mcclcchVOReadyToDelete.getIs_major());
                            wmscrecreditlinecustomerchangeheadDao_m.saveWithKey(newInsertVO);
                            break;
                        }
                    }
                    else
                    {
                        break;
                    }
                }
            }

            if (isComOrZC.equals("1"))
            {
                // �?��身份证号重复，并将重复记录存入数据库
                isRepeaded = isRepeaded || this.compareSfz(wmscrecreditlinecustomerchangehead, user);
                // 客户电话与联系人电话重复记录集合
                isRepeaded = isRepeaded || this.compareKhAndLxr(wmscrecreditlinecustomerchangehead, user);
                // 客户与客户电话重�?
                isRepeaded = isRepeaded || this.compareKhAndKh(wmscrecreditlinecustomerchangehead, user);
            }

            Integer customer_id = org_custom_info_change_id;
            if (ishave)
            {
                // 工作信息
                WmsCreCustomerChangeLineWorkinfo vo = WmsCreCustomerChangeLineWorkinfoVOList2.get(j);// 工作信息
                vo.setWms_cre_credit_line_customer_change_head_id(customer_id);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                vo.setEnable_flag("1");
                wmscrecustomerchangelineworkinfoDao_m.addNewRecord(vo);

                // 房产信息
                for (Integer m = wmsHouseInfoChangeVOList.size() - 1; m >= 0; --m)
                {
                    WmsHouseChangeInfoVO housechangevo = wmsHouseInfoChangeVOList.get(m);
                    if (org_custom_info_change_id.compareTo(housechangevo.getWms_cre_credit_line_customer_change_head_id()) == 0)
                    {
                        String housestr = housechangevo.getHousestr();
                        List<WmsCreCustomerChangeLineHouseinfo> houseList = JsonUtil.jsonArrayToList(housestr,
                                                                                                     WmsCreCustomerChangeLineHouseinfo.class);
                        for (WmsCreCustomerChangeLineHouseinfo housevo_p : houseList)
                        {
                            housevo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            housevo_p.setCreate_user_id(user.getUserId());
                            housevo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            housevo_p.setLast_update_user_id(user.getUserId());
                            housevo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            housevo_p.setEnable_flag("1");
                            wmscrecustomerchangelinehouseinfoDao_m.addNewRecord(housevo_p);
                        }
                        break;
                    }
                }
                
                // 车产信息
                for (Integer m = wmsCarInfoChangeVOList.size() - 1; m >= 0; --m)
                {
                    WmsCarInfoVO carvo = wmsCarInfoChangeVOList.get(m);
                    if (org_custom_info_change_id.compareTo(carvo.getWms_cre_credit_line_customer_change_head_id()) == 0)
                    {
                        String carstr = carvo.getCarstr();
                        List<WmsCreCustomerChangeLineCarpinfo> carList = JsonUtil.jsonArrayToList(carstr, WmsCreCustomerChangeLineCarpinfo.class);
                        for (WmsCreCustomerChangeLineCarpinfo carvo_p : carList)
                        {
                            carvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            carvo_p.setCreate_user_id(user.getUserId());
                            carvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            carvo_p.setLast_update_user_id(user.getUserId());
                            carvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            carvo_p.setEnable_flag("1");
                            wmscrecustomerchangelinecarpinfoDao_m.addNewRecordReKey(carvo_p);
                        }
                        break;
                    }
                }

                // 公司信息
                for (Integer m = wmsCompanyInfoChangeVOList.size() - 1; m >= 0; --m)
                {
                    WmsCompanyChangeInfoVO companychangevo = wmsCompanyInfoChangeVOList.get(m);
                    if (org_custom_info_change_id.compareTo(companychangevo.getWms_cre_credit_line_customer_change_head_id()) == 0)
                    {
                        String companystr = companychangevo.getCompanystr();
                        List<WmsCreCustomerChangeLineCompany> companyList = JsonUtil.jsonArrayToList(companystr,
                                                                                                     WmsCreCustomerChangeLineCompany.class);
                        for (WmsCreCustomerChangeLineCompany companyvo_p : companyList)
                        {
                            companyvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            companyvo_p.setCreate_user_id(user.getUserId());
                            companyvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            companyvo_p.setLast_update_user_id(user.getUserId());
                            companyvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            companyvo_p.setEnable_flag("1");
                            wmsCreCustomerChangeLineCompanyDao.save(companyvo_p);
                        }
                        break;
                    }
                }
                // 子女
                for (Integer m = wmsChildInfoChangeVOList.size() - 1; m >= 0; --m)
                {
                    WmsChildChangeInfoVO childchangevo = wmsChildInfoChangeVOList.get(m);
                    if (org_custom_info_change_id.compareTo(childchangevo.getWms_cre_credit_line_customer_change_head_id()) == 0)
                    {
                        String childstr = childchangevo.getCusChild();
                        List<WmsCusCustomerChangeChild> childList = JsonUtil.jsonArrayToList(childstr,
                                                                                             WmsCusCustomerChangeChild.class);
                        for (WmsCusCustomerChangeChild childvo_p : childList)
                        {
                            childvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            childvo_p.setCreate_user_id(user.getUserId());
                            childvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            childvo_p.setLast_update_user_id(user.getUserId());
                            childvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            childvo_p.setEnable_flag("1");
                            wmsCusCustomerChangeChildDao.save(childvo_p);
                        }
                        break;
                    }
                }
            }
        }

        /*----------------------------------------------wms_cre_credit_line_customer_change_head 客户信息变更�?end----------------------------------------------*/

        /*----------------------------------------------wms_cre_customer_change_line_contact 客户联系人表 begin----------------------------------------------*/
        for (int i = 0; i < Lmccclc.size(); i++)
        {
            WmsCreCustomerChangeLineContact everyContactVO = Lmccclc.get(i);
            Lmccclc.get(i).setWms_cre_credit_head_id(wmsCreCreditHeadId);
            if (everyContactVO.getWms_cre_customer_change_line_contact_id() != null
                && everyContactVO.getWms_cre_customer_change_line_contact_id() != 0)
            {
                wmscrecustomerchangelinecontactDao_m.updateChangeContantBF(everyContactVO);
            }
            else
            {
                wmscrecustomerchangelinecontactDao_m.addNewRecord(Lmccclc.get(i));
            }
            if (isComOrZC.equals("1"))
            {
                // 联系人电话重复记录集�?
                isRepeaded = isRepeaded || this.compareLxrAndLxr(Lmccclc.get(i), user);
                // 联系人与客户电话重复
                isRepeaded = isRepeaded || this.compareLxrAndKh(Lmccclc.get(i), user);
            }
        }
        if (isRepeaded)
        {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("is_link_repeat", "1");
            params.put("wms_cre_credit_head_id", wmsCreCreditHeadId);
            wmscrecreditheadDao_m.updateRecord(params);
        }
        /*----------------------------------------------wms_cre_customer_change_line_contact 客户联系人表 end----------------------------------------------*/

        /*----------------------------------------------wms_cre_credit_line_customer_data_attachment 客户资料附件�?begin----------------------------------------------*/
        // 删除�?��该贷款的客户附件
        ArrayList<Integer> attDeletList = new ArrayList<>();
        attDeletList.add(wmsCreCreditHeadId);
        Map<String, Object> attDeletMap = new HashMap<String, Object>();
        attDeletMap.put("dltIDArr", attDeletList);
        wmscrecreditlinecustomerdataattachmentDao_m.deleteByMap(attDeletMap);
        for (int i = 0; i < Lattachment.size(); i++)
        {
            Lattachment.get(i).setWms_cre_credit_head_id(wmsCreCreditHeadId);
            wmscrecreditlinecustomerdataattachmentDao_m.addNewRecord(Lattachment.get(i));
        }
        /*----------------------------------------------wms_cre_credit_line_customer_data_attachment 客户资料附件�?end----------------------------------------------*/

        return result;
    }

    @Override
    @Transactional
    /*public String updateHouseCreAll(WmsCreCreditHead mcch, String mccclc, UserBean user, String clientId,
                                    String modifyJsonCus, String house_use, String zdrID, String mcclhid,
                                    String fileArr, String isComOrZC)*/
    public String updateHouseCreAll(WmsCreCreditHead mcch, UserBean user, WmsCreCreditHeadHouseSearchBeanVO bean)
    {
        String result = "temOK";
        Integer wmsCreCreditHeadId = mcch.getWms_cre_credit_head_id();// 得到新增的贷款的主键

        int isvcchid = 0;// 客户房产信息更改表的主键
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间

        List<WmsCreCustomerChangeLineContact> mcclc = JsonUtil.jsonArrayToList(bean.getLinkmaninfo(),
                                                                               WmsCreCustomerChangeLineContact.class); // 前台json客户联系人数据转换为list数据
        List<WmsCreHousingAtt> attachment = JsonUtil.jsonArrayToList(bean.getFileArr(), WmsCreHousingAtt.class); // 前台json附件路径数据转换为list
        List<WmsCreHousingApplyAtt> khatt = JsonUtil.jsonArrayToList(bean.getFileArrkh(), WmsCreHousingApplyAtt.class); // 前台json附件路径数据转换为list
        /*----------------------------------------------wms_cre_credit_head 贷款申请单主�?begin----------------------------------------------*/
        mcch.setLast_update_user_id(user.getUserId()); // 更新人id
        mcch.setLast_update_timestamp(sysTime);// 更新时间
        /*if (mcch.getSalesman_id() != null)
        {
            if (mcch.getCity().indexOf("0") != -1)
            {
                mcch.setCity(UserCityUtil.getUserCity(mcch.getCity()));
            }
        }*/
        mcch.setCreate_user_id(user.getUserId());
        mcch.setCreate_user_name(user.getRealname());
        //mcch.setCreate_timestamp(sysTime);
        mcch.setCreate_user_city_code(user.getUser_regionNumber());// 存储创建者的城市区域编码
        mcch.setCreate_user_dept_id(user.getDeptId());
        mcch.setEnable_flag("1");
        mcch.setIs_link_repeat("0");
        wmscrecreditheadDao_m.updateMCCHWhenUpdateOrReSub(mcch.getInfoMap()); // 更新贷款主表
        /*----------------------------------------------wms_cre_credit_head 贷款申请单主�?end----------------------------------------------*/
        if (bean.getIsComOrZC().equals("1"))
        {
            result = "OK";
            WmsCreCreditHead searchBillSt = wmscrecreditheadDao_m.get(wmsCreCreditHeadId);
            String keyPass = "";
            if (searchBillSt.getBill_status().equals("A"))
            {
                keyPass = "1";
            }
            else if (searchBillSt.getBill_status().equals("I"))
            {
                keyPass = "2";
            }
            else if (searchBillSt.getBill_status().equals("J"))
            {
                keyPass = "3";
            }
            /*---------------------�?��流程-------------------*/
            if("1".equals(bean.getEdition_num())){//<!--房贷流程版本号 1为老流程  2为新流程-->
                houseCreditWorkFlowService.houseCreditInquiryTreatment(String.valueOf(user.getUserId()),String.valueOf(wmsCreCreditHeadId), keyPass);	
            }else if("2".equals(bean.getEdition_num())){
            	 //房贷新流程
                wmsLoanWorkFlowService.houseCreditInquiryTreatment(String.valueOf(user.getUserId()), String.valueOf(wmsCreCreditHeadId), keyPass);
            }
            WmsCreHousingFileInfo housing = new WmsCreHousingFileInfo();
            housing.setWms_cre_credit_head_id(mcch.getWms_cre_credit_head_id());
            housing.setBill_status(1);
            wmsCreHousingFileInfoDao.update(housing);             
        }
        /*----------------------------------------------wms_cre_credit_line_customer_change_head 客户信息变更�?begin----------------------------------------------*/
        WmsCreCreditLineCustomerChangeHead wmscrecreditlinecustomerchangehead; // 客户信息变更�?

        List<WmsCusCustomerHead> wmscuscustomerheadvo = new ArrayList<WmsCusCustomerHead>();// 客户信息
                                                                                            // 通过查询主键得到
        List<WmsCreCreditLineCustomerChangeHead> wmscuscustomerchangeheadvoList = new ArrayList<WmsCreCreditLineCustomerChangeHead>();// 客户信息变更信息                                                                                                               // 通过查询主键得到
        ArrayList<Integer> changedNochangeIDS = new ArrayList<>();
        if (!(bean.getPersonArr().equals("")))
        {
            for (String id : bean.getPersonArr().split(","))
            {
                String[] idarr = id.split("@@@");
                if (("change").equals(idarr[2]))
                {
                    WmsCreCreditLineCustomerChangeHead mcclcchVOByGet = wmscrecreditlinecustomerchangeheadDao_m.get(Integer.valueOf(idarr[0]));
                    wmscuscustomerchangeheadvoList.add(mcclcchVOByGet);
                    changedNochangeIDS.add(Integer.valueOf(idarr[0]));
                    Map<String, Object> updateIsMajorMap = new HashMap<String, Object>();
                    updateIsMajorMap.put("is_major", idarr[1]);
                    updateIsMajorMap.put("wms_cre_credit_line_customer_change_head_id", idarr[0]);
                    wmscrecreditlinecustomerchangeheadDao_m.updateIsMajor(updateIsMajorMap);
                }
                else if (("cus").equals(idarr[2]))
                {
                    WmsCusCustomerHead mcchvo = wmscuscustomerheadDao_m.get(Integer.valueOf(idarr[0]));
                    mcchvo.setEnable_flag(idarr[1]);
                    wmscuscustomerheadvo.add(mcchvo);
                }

            }
            ;
        }

        // 删除 未�?择的客户变更信息
        List<WmsCreCreditLineCustomerChangeHead> allChangeCusIn = wmscrecreditlinecustomerchangeheadDao_m.getWmsCreCreditLineCustomerChangeHeadListWithoutPaging(wmsCreCreditHeadId);
        ArrayList<Integer> dltIdsList = new ArrayList<>();
        for (int i = 0; i < allChangeCusIn.size(); i++)
        {
            boolean notInFlag = false;
            for (int j = 0; j < wmscuscustomerchangeheadvoList.size(); j++)
            {
                if (allChangeCusIn.get(i)
                                  .getWms_cre_credit_line_customer_change_head_id()
                                  .equals(wmscuscustomerchangeheadvoList.get(j)
                                                                        .getWms_cre_credit_line_customer_change_head_id()))
                {
                    notInFlag = true;
                    break;
                }
            }
            if (!notInFlag)
            {
                dltIdsList.add(allChangeCusIn.get(i).getWms_cre_credit_line_customer_change_head_id());
            }
        }
        Map<String, Object> deleteParamsMap = new HashMap<String, Object>();
        if (dltIdsList.size() > 0)
        {
            deleteParamsMap.put("dltIDArr", dltIdsList);
            wmscrecreditlinecustomerchangeheadDao_m.deleteByMap(deleteParamsMap);
            wmscrecustomerchangelinecontactDao_m.deleteByMap(deleteParamsMap);
            wmscrecustomerchangelinehouseinfoDao_m.deleteByMap(deleteParamsMap);
            wmsCreCustomerChangeLineCarpinfoDao.deleteByMap(deleteParamsMap);
            wmscrecustomerchangelineworkinfoDao_m.deleteByMap(deleteParamsMap);
            wmsCreCustomerChangeLineCompanyDao.deleteByMap(deleteParamsMap);
            wmsCusCustomerChangeChildDao.deleteByMap(deleteParamsMap);
        }
        // 删除�?��重复校验
        dltIdsList.clear();
        dltIdsList.add(wmsCreCreditHeadId);
        deleteParamsMap.clear();
        deleteParamsMap.put("dltIDArr", dltIdsList);
        wmsCreCreditHeadDiffPhoneDao.deleteByMap(deleteParamsMap);

        // 修改后的客户信息
        List<WmsCusCustomerHead> wmsCusCustomerHeadVOList = JsonUtil.jsonArrayToList(bean.getModifyJsonCus(),
                                                                                     WmsCusCustomerHead.class);// 修改后list
        // 修改后的工作信息
        List<WmsCreCustomerChangeLineWorkinfo> WmsCreCustomerChangeLineWorkinfoVOList = JsonUtil.jsonArrayToList(bean.getModifyJsonCus(),
                                                                                                                 WmsCreCustomerChangeLineWorkinfo.class);// 修改后list

        // housestr
        List<WmsHouseInfoVO> wmsHouseInfoVOList = JsonUtil.jsonArrayToList(bean.getModifyJsonCus(), WmsHouseInfoVO.class);// 修改后list
        
        // 修改后的车产信息
        List<WmsCarInfoVO> wmsCarInfoVOList = JsonUtil.jsonArrayToList(bean.getModifyJsonCus(), WmsCarInfoVO.class);

        // 修改后的公司信息
        List<WmsCompanyInfoVO> wmsCompanyInfoVOList = JsonUtil.jsonArrayToList(bean.getModifyJsonCus(), WmsCompanyInfoVO.class);

        // 修改后的子女信息
        List<WmsChildInfoVO> wmsChildInfoVOList = JsonUtil.jsonArrayToList(bean.getModifyJsonCus(), WmsChildInfoVO.class);

        //
        boolean isRepeaded = false;
        for (int i = 0; i < wmscuscustomerheadvo.size(); i++)
        {
            boolean ishave = false;// 是否修改�?
            Integer org_custom_info_id = wmscuscustomerheadvo.get(i).getWms_cus_customer_id();
            wmscrecreditlinecustomerchangehead = new WmsCreCreditLineCustomerChangeHead();
            int j = 0;// 修改客户信息list的index
            if (wmsCusCustomerHeadVOList != null && wmsCusCustomerHeadVOList.size() > 0)
            {
                if (wmsCusCustomerHeadVOList.get(j).getWms_cus_customer_id() != null)
                {
                    for (j = wmsCusCustomerHeadVOList.size() - 1; j >= 0; j--)
                    {
                        if (wmsCusCustomerHeadVOList.get(j).getWms_cus_customer_id()
                                                    .compareTo(wmscuscustomerheadvo.get(i).getWms_cus_customer_id()) == 0)
                        {
                            ishave = true;
                            WmsCusCustomerHead pageVo = wmsCusCustomerHeadVOList.get(j);
                            copyWmscrecreditlinecustomerchangehead(wmscrecreditlinecustomerchangehead, pageVo,
                                                                   wmsCreCreditHeadId, user,
                                                                   wmscuscustomerheadvo.get(i));
                            break;
                        }
                    }
                }
                else
                {
                    break;
                }
            }

            if (!ishave)
            {
                copyWmscrecreditlinecustomerchangehead(wmscrecreditlinecustomerchangehead, wmscuscustomerheadvo.get(i),
                                                       wmsCreCreditHeadId, user, wmscuscustomerheadvo.get(i));
            }
            wmscrecreditlinecustomerchangeheadDao_m.saveWithKey(wmscrecreditlinecustomerchangehead);
            for (int k = 0; k < mcclc.size(); k++)
            {
                WmsCreCustomerChangeLineContact mccclcChange = mcclc.get(k);
                if (mccclcChange.getWms_cre_credit_line_customer_change_head_id()
                                .equals(wmscrecreditlinecustomerchangehead.getWms_cus_customer_head_id()))
                {
                    mccclcChange.setWms_cre_credit_line_customer_change_head_id(wmscrecreditlinecustomerchangehead.getWms_cre_credit_line_customer_change_head_id());
                    mcclc.set(k, mccclcChange);
                }
            }
            if (bean.getIsComOrZC().equals("1"))
            {
                // �?��身份证号重复，并将重复记录存入数据库
                isRepeaded = isRepeaded || this.compareSfz(wmscrecreditlinecustomerchangehead, user);
                // 客户电话与联系人电话重复记录集合
                isRepeaded = isRepeaded || this.compareKhAndLxr(wmscrecreditlinecustomerchangehead, user);
                // 客户与客户电话重�?
                isRepeaded = isRepeaded || this.compareKhAndKh(wmscrecreditlinecustomerchangehead, user);
            }
            Integer customer_id = wmscrecreditlinecustomerchangehead.getWms_cre_credit_line_customer_change_head_id();
            if (ishave)
            {
                // 工作信息
                WmsCreCustomerChangeLineWorkinfo vo = WmsCreCustomerChangeLineWorkinfoVOList.get(j);// 工作信息
                vo.setWms_cre_credit_line_customer_change_head_id(customer_id);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                vo.setEnable_flag("1");
                wmscrecustomerchangelineworkinfoDao_m.addNewRecord(vo);

                // 房产信息
                for (Integer m = wmsHouseInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsHouseInfoVO housevo = wmsHouseInfoVOList.get(m);
                    if(housevo!=null){
	                    if (org_custom_info_id.compareTo(housevo.getWms_cus_customer_id()) == 0)
	                    {
	                        String housestr = housevo.getHousestr();
	                        List<WmsCreCustomerChangeLineHouseinfo> houseList = JsonUtil.jsonArrayToList(housestr,
	                                                                                                     WmsCreCustomerChangeLineHouseinfo.class);
	                        for (WmsCreCustomerChangeLineHouseinfo housevo_p : houseList)
	                        {
	                            boolean hosFlag = false;
	                            if (housevo_p.getEnable_flag() != null && housevo_p.getEnable_flag().equals(bean.getMcclhid()))
	                            {
	                                hosFlag = true;
	                            }
	                            housevo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
	                            housevo_p.setCreate_user_id(user.getUserId());
	                            housevo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
	                            housevo_p.setLast_update_user_id(user.getUserId());
	                            housevo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
	                            housevo_p.setEnable_flag("1");
	                            if (hosFlag)
	                            {
	                                wmscrecustomerchangelinehouseinfoDao_m.addNewRecordReKey(housevo_p);
	                                isvcchid = housevo_p.getWms_cre_customer_change_line_houseinfo_id();
	                            }
	                            else
	                                wmscrecustomerchangelinehouseinfoDao_m.addNewRecord(housevo_p);
	                        }
	                        break;
	                    }
                	
                    }
                }
                
                // 车产信息
                for (Integer m = wmsCarInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsCarInfoVO carvo = wmsCarInfoVOList.get(m);
                    if(carvo!=null){
	                    //if (org_custom_info_id.compareTo(carvo.getWms_cre_credit_line_customer_change_head_id()) == 0)
                    	//2016-3-14 房贷申请时 修改会报错 
                    	 if (org_custom_info_id.compareTo(carvo.getWms_cus_customer_id()) == 0)
                    	{
	                        String carstr = carvo.getCarstr();
	                        List<WmsCreCustomerChangeLineCarpinfo> carList = JsonUtil.jsonArrayToList(carstr, WmsCreCustomerChangeLineCarpinfo.class);
	                        for (WmsCreCustomerChangeLineCarpinfo carvo_p : carList)
	                        {
	                            carvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
	                            carvo_p.setCreate_user_id(user.getUserId());
	                            carvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
	                            carvo_p.setLast_update_user_id(user.getUserId());
	                            carvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
	                            carvo_p.setEnable_flag("1");
	                            wmscrecustomerchangelinecarpinfoDao_m.addNewRecordReKey(carvo_p);
	                        }
	                        break;
	                    }
                    }
                }

                // 公司信息
                for (Integer m = wmsCompanyInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsCompanyInfoVO companyvo = wmsCompanyInfoVOList.get(m);
                    if(companyvo!=null){
	                    if (org_custom_info_id.compareTo(companyvo.getWms_cus_customer_id()) == 0)
	                    {
	                        String companystr = companyvo.getCompanystr();
	                        List<WmsCreCustomerChangeLineCompany> companyList = JsonUtil.jsonArrayToList(companystr,
	                                                                                                     WmsCreCustomerChangeLineCompany.class);
	                        for (WmsCreCustomerChangeLineCompany companyvo_p : companyList)
	                        {
	                            companyvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
	                            companyvo_p.setCreate_user_id(user.getUserId());
	                            companyvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
	                            companyvo_p.setLast_update_user_id(user.getUserId());
	                            companyvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
	                            companyvo_p.setEnable_flag("1");
	                            wmsCreCustomerChangeLineCompanyDao.save(companyvo_p);
	                        }
	                        break;
	                    }
                    }
                }
                // 子女
                for (Integer m = wmsChildInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsChildInfoVO childvo = wmsChildInfoVOList.get(m);
                    if(childvo!=null){
	                    if (org_custom_info_id.compareTo(childvo.getWms_cus_customer_id()) == 0)
	                    {
	                        String childstr = childvo.getCusChild();
	                        List<WmsCusCustomerChangeChild> childList = JsonUtil.jsonArrayToList(childstr,
	                                                                                             WmsCusCustomerChangeChild.class);
	                        for (WmsCusCustomerChangeChild childvo_p : childList)
	                        {
	                            childvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
	                            childvo_p.setCreate_user_id(user.getUserId());
	                            childvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
	                            childvo_p.setLast_update_user_id(user.getUserId());
	                            childvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
	                            childvo_p.setEnable_flag("1");
	                            wmsCusCustomerChangeChildDao.save(childvo_p);
	                        }
	                        break;
	                    }
                    }
                }
            }
            else
            {
                // 工作信息
                WmsCusCustomerLineWorkinfo workinfoQry = new WmsCusCustomerLineWorkinfo();
                workinfoQry.setWms_cus_customer_id(org_custom_info_id);
                workinfoQry.setEnable_flag("1");
                List<WmsCusCustomerLineWorkinfo> list = wmsCusCustomerLineWorkinfoDao_m.getSingleTableListByAndMethod(workinfoQry);
                if (list != null && list.size() > 0)
                {
                    WmsCusCustomerLineWorkinfo customerLineWork = list.get(0);
                    WmsCreCustomerChangeLineWorkinfo customerChangeLineWork = new WmsCreCustomerChangeLineWorkinfo();
                    copyWmsCreCustomerChangeLineWorkinfo(customerLineWork, customerChangeLineWork);
                    customerChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                    customerChangeLineWork.setCreate_user_id(user.getUserId());
                    customerChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                    customerChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                    wmscrecustomerchangelineworkinfoDao_m.addNewRecord(customerChangeLineWork);
                }

                // 房产信息
                WmsCusCustomerLineHouseinfo qryinfo = new WmsCusCustomerLineHouseinfo();
                qryinfo.setWms_cus_customer_id(org_custom_info_id);
                qryinfo.setEnable_flag("1");
                List<WmsCusCustomerLineHouseinfo> list2 = wmsCusCustomerLineHouseinfoDao_m.getSingleTableListByAndMethod(qryinfo);
                if (list2 != null)
                {
                    for (int k = 0; k < list2.size(); k++)
                    {
                        WmsCusCustomerLineHouseinfo houserWork = list2.get(k);
                        boolean hosFlag = false;
                        if (String.valueOf(houserWork.getWms_cus_customer_line_houseinfo_id()).equals(bean.getMcclhid()))
                        {
                            hosFlag = true;
                        }
                        WmsCreCustomerChangeLineHouseinfo houserWorkChangeLineWork = new WmsCreCustomerChangeLineHouseinfo();
                        copyWmsCreCustomerChangeLineHouseinfo(houserWork, houserWorkChangeLineWork);
                        houserWorkChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                        houserWorkChangeLineWork.setCreate_user_id(user.getUserId());
                        houserWorkChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                        houserWorkChangeLineWork.setLast_update_user_id(user.getUserId());
                        houserWorkChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                        houserWorkChangeLineWork.setEnable_flag("1");
                        if (hosFlag)
                        {
                            wmscrecustomerchangelinehouseinfoDao_m.addNewRecordReKey(houserWorkChangeLineWork);
                            isvcchid = houserWorkChangeLineWork.getWms_cre_customer_change_line_houseinfo_id();
                        }
                        else
                            wmscrecustomerchangelinehouseinfoDao_m.addNewRecord(houserWorkChangeLineWork);
                    }
                }
                
                // 车产信息
                WmsCusCustomerLineCarpinfo carpinfo = new WmsCusCustomerLineCarpinfo();
                carpinfo.setWms_cus_customer_id(org_custom_info_id);
                carpinfo.setEnable_flag("1");
                List<WmsCusCustomerLineCarpinfo> listcar = wmsCusCustomerLineCarpinfoDao.getListByEntity(carpinfo);
                if(listcar != null) {
                	for(int m = 0; m < listcar.size(); m++) {
                		WmsCusCustomerLineCarpinfo lineCarpinfo = listcar.get(m);
                		WmsCreCustomerChangeLineCarpinfo changeLineCarpinfo = new WmsCreCustomerChangeLineCarpinfo();
                		copyWmsCreCustomerChangeLineCarinfo(lineCarpinfo, changeLineCarpinfo);
                		changeLineCarpinfo.setWms_cre_credit_line_customer_change_head_id(customer_id);
                		changeLineCarpinfo.setCreate_user_id(user.getUserId());
                		changeLineCarpinfo.setCreate_timestamp(sysTime);
                		changeLineCarpinfo.setLast_update_user_id(user.getUserId());
                		changeLineCarpinfo.setLast_update_timestamp(sysTime);
                		changeLineCarpinfo.setEnable_flag("1");
                		wmscrecustomerchangelinecarpinfoDao_m.addNewRecordReKey(changeLineCarpinfo);
                	}
                }

                // 公司信息
                WmsCusCustomerLineCompany qryinfo3 = new WmsCusCustomerLineCompany();
                qryinfo3.setWms_cus_customer_id(org_custom_info_id);
                qryinfo3.setEnable_flag("1");
                List<WmsCusCustomerLineCompany> list3 = wmsCusCustomerLineCompanyDao.getSingleTableListByAndMethod(qryinfo3);
                if (list3 != null)
                {
                    for (int k = 0; k < list3.size(); k++)
                    {
                        WmsCusCustomerLineCompany companyWork = list3.get(k);
                        WmsCreCustomerChangeLineCompany companyWorkChangeLineWork = new WmsCreCustomerChangeLineCompany();
                        copyWmsCreCustomerChangeLinCompanyinfo(companyWork, companyWorkChangeLineWork);
                        companyWorkChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                        companyWorkChangeLineWork.setCreate_user_id(user.getUserId());
                        companyWorkChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                        companyWorkChangeLineWork.setLast_update_user_id(user.getUserId());
                        companyWorkChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                        companyWorkChangeLineWork.setEnable_flag("1");
                        wmsCreCustomerChangeLineCompanyDao.save(companyWorkChangeLineWork);
                    }
                }

                // 子女信息
                WmsCusCustomerChild qryinfo4 = new WmsCusCustomerChild();
                qryinfo4.setWms_cus_customer_id(org_custom_info_id);
                qryinfo4.setEnable_flag("1");
                List<WmsCusCustomerChild> list4 = wmsCusCustomerChildDao.getSingleTableListByAndMethod(qryinfo4);
                if (list4 != null)
                {
                    for (int k = 0; k < list4.size(); k++)
                    {
                        WmsCusCustomerChild childWork = list4.get(k);
                        WmsCusCustomerChangeChild childWorkChangeLineWork = new WmsCusCustomerChangeChild();
                        copyWmsCreCustomerChangeLinChildinfo(childWork, childWorkChangeLineWork);
                        childWorkChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                        childWorkChangeLineWork.setCreate_user_id(user.getUserId());
                        childWorkChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                        childWorkChangeLineWork.setLast_update_user_id(user.getUserId());
                        childWorkChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                        childWorkChangeLineWork.setEnable_flag("1");
                        wmsCusCustomerChangeChildDao.save(childWorkChangeLineWork);
                    }
                }
            }
        }

        // 修改后的客户信息
        List<WmsCreCreditLineCustomerChangeHead> wmsCusCustomerHeadChangeVOList = JsonUtil.jsonArrayToList(bean.getModifyJsonCus(),
                                                                                                           WmsCreCreditLineCustomerChangeHead.class);// 修改后list
        // 修改后的工作信息
        List<WmsCreCustomerChangeLineWorkinfo> WmsCreCustomerChangeLineWorkinfoVOList2 = JsonUtil.jsonArrayToList(bean.getModifyJsonCus(),
                                                                                                                  WmsCreCustomerChangeLineWorkinfo.class);// 修改后list

        // housestr
        List<WmsHouseChangeInfoVO> wmsHouseInfoChangeVOList = JsonUtil.jsonArrayToList(bean.getModifyJsonCus(),
                                                                                       WmsHouseChangeInfoVO.class);// 修改后list

        // 修改后的公司信息
        List<WmsCompanyChangeInfoVO> wmsCompanyInfoChangeVOList = JsonUtil.jsonArrayToList(bean.getModifyJsonCus(),
                                                                                           WmsCompanyChangeInfoVO.class);

        // 修改后的子女信息
        List<WmsChildChangeInfoVO> wmsChildInfoChangeVOList = JsonUtil.jsonArrayToList(bean.getModifyJsonCus(),
                                                                                       WmsChildChangeInfoVO.class);

        for (int i = 0; i < wmscuscustomerchangeheadvoList.size(); i++)
        {
            boolean ishave = false;// 是否修改�?
            Integer org_custom_info_change_id = wmscuscustomerchangeheadvoList.get(i)
                                                                              .getWms_cre_credit_line_customer_change_head_id();
            wmscrecreditlinecustomerchangehead = wmscuscustomerchangeheadvoList.get(i);
            boolean isMFlag = false;
            int j = 0;// 修改客户信息list的index
            if (wmsCusCustomerHeadChangeVOList != null && wmsCusCustomerHeadChangeVOList.size() > 0)
            {
                for (j = wmsCusCustomerHeadChangeVOList.size() - 1; j >= 0; j--)
                {
                    if (wmsCusCustomerHeadChangeVOList.get(j).getWms_cre_credit_line_customer_change_head_id() != null)
                    {
                        if (wmsCusCustomerHeadChangeVOList.get(j)
                                                          .getWms_cre_credit_line_customer_change_head_id()
                                                          .compareTo(wmscuscustomerchangeheadvoList.get(i)
                                                                                                   .getWms_cre_credit_line_customer_change_head_id()) == 0)
                        {
                            ishave = true;
                            ArrayList<Integer> dltOneByOCICIDList = new ArrayList<>();
                            dltOneByOCICIDList.add(org_custom_info_change_id);
                            Map<String, Object> dltOneByOCICIDMap = new HashMap<String, Object>();
                            dltOneByOCICIDMap.put("dltIDArr", dltOneByOCICIDList);
                            WmsCreCreditLineCustomerChangeHead mcclcchVOReadyToDelete = wmscrecreditlinecustomerchangeheadDao_m.get(org_custom_info_change_id);
                            wmscrecreditlinecustomerchangeheadDao_m.deleteByMap(dltOneByOCICIDMap);
                            wmscrecustomerchangelinehouseinfoDao_m.deleteByMap(dltOneByOCICIDMap);
                            wmsCreCustomerChangeLineCarpinfoDao.deleteByMap(dltOneByOCICIDMap);
                            wmscrecustomerchangelineworkinfoDao_m.deleteByMap(dltOneByOCICIDMap);
                            wmsCreCustomerChangeLineCompanyDao.deleteByMap(dltOneByOCICIDMap);
                            wmsCusCustomerChangeChildDao.deleteByMap(dltOneByOCICIDMap);
                            WmsCreCreditLineCustomerChangeHead newInsertVO = wmsCusCustomerHeadChangeVOList.get(j);
                            newInsertVO.setWms_cre_credit_head_id(mcclcchVOReadyToDelete.getWms_cre_credit_head_id());
                            newInsertVO.setWms_cus_customer_head_id(mcclcchVOReadyToDelete.getWms_cus_customer_head_id());
                            newInsertVO.setCustomer_code(mcclcchVOReadyToDelete.getCustomer_code());
                            newInsertVO.setCreate_user_id(mcclcchVOReadyToDelete.getCreate_user_id());
                            newInsertVO.setCreate_user_name(mcclcchVOReadyToDelete.getCreate_user_name());
                            newInsertVO.setCreate_timestamp(mcclcchVOReadyToDelete.getCreate_timestamp());
                            newInsertVO.setLast_update_user_id(user.getUserId());
                            newInsertVO.setLast_update_timestamp(sysTime);
                            newInsertVO.setEnable_flag("1");
                            newInsertVO.setIs_major(mcclcchVOReadyToDelete.getIs_major());
                            if (mcclcchVOReadyToDelete.getIs_major().equals("1"))
                            {
                                isMFlag = true;
                            }
                            wmscrecreditlinecustomerchangeheadDao_m.saveWithKey(newInsertVO);
                            break;
                        }
                    }
                    else
                    {
                        break;
                    }
                }
            }

            if (bean.getIsComOrZC().equals("1"))
            {
                // �?��身份证号重复，并将重复记录存入数据库
                isRepeaded = isRepeaded || this.compareSfz(wmscrecreditlinecustomerchangehead, user);
                // 客户电话与联系人电话重复记录集合
                isRepeaded = isRepeaded || this.compareKhAndLxr(wmscrecreditlinecustomerchangehead, user);
                // 客户与客户电话重�?
                isRepeaded = isRepeaded || this.compareKhAndKh(wmscrecreditlinecustomerchangehead, user);
            }

            Integer customer_id = org_custom_info_change_id;
            if (ishave)
            {
                // 工作信息
                WmsCreCustomerChangeLineWorkinfo vo = WmsCreCustomerChangeLineWorkinfoVOList2.get(j);// 工作信息
                vo.setWms_cre_credit_line_customer_change_head_id(customer_id);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                vo.setEnable_flag("1");
                wmscrecustomerchangelineworkinfoDao_m.addNewRecord(vo);

                // 房产信息
                for (Integer m = wmsHouseInfoChangeVOList.size() - 1; m >= 0; --m)
                {
                    WmsHouseChangeInfoVO housechangevo = wmsHouseInfoChangeVOList.get(m);
                    if (org_custom_info_change_id.compareTo(housechangevo.getWms_cre_credit_line_customer_change_head_id()) == 0)
                    {
                        String housestr = housechangevo.getHousestr();
                        List<WmsCreCustomerChangeLineHouseinfo> houseList = JsonUtil.jsonArrayToList(housestr,
                                                                                                     WmsCreCustomerChangeLineHouseinfo.class);
                        for (WmsCreCustomerChangeLineHouseinfo housevo_p : houseList)
                        {
                            boolean hosFlag = false;
                            if (housevo_p.getEnable_flag() != null && housevo_p.getEnable_flag().equals(bean.getMcclhid()))
                            {
                                hosFlag = true;
                            }
                            housevo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            housevo_p.setCreate_user_id(user.getUserId());
                            housevo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            housevo_p.setLast_update_user_id(user.getUserId());
                            housevo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            housevo_p.setEnable_flag("1");
                            wmscrecustomerchangelinehouseinfoDao_m.addNewRecordReKey(housevo_p);
                            if (isMFlag && hosFlag)
                            {
                                isvcchid = housevo_p.getWms_cre_customer_change_line_houseinfo_id();
                            }
                        }
                        break;
                    }
                }
                
                // 车产信息
                for (Integer m = wmsCarInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsCarInfoVO carvo = wmsCarInfoVOList.get(m);
                    if (org_custom_info_change_id.compareTo(carvo.getWms_cre_credit_line_customer_change_head_id()) == 0)
                    {
                        String carstr = carvo.getCarstr();
                        List<WmsCreCustomerChangeLineCarpinfo> carList = JsonUtil.jsonArrayToList(carstr, WmsCreCustomerChangeLineCarpinfo.class);
                        for (WmsCreCustomerChangeLineCarpinfo carvo_p : carList)
                        {
                            carvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            carvo_p.setCreate_user_id(user.getUserId());
                            carvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            carvo_p.setLast_update_user_id(user.getUserId());
                            carvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            carvo_p.setEnable_flag("1");
                            wmscrecustomerchangelinecarpinfoDao_m.addNewRecordReKey(carvo_p);
                        }
                        break;
                    }
                }

                // 公司信息
                for (Integer m = wmsCompanyInfoChangeVOList.size() - 1; m >= 0; --m)
                {
                    WmsCompanyChangeInfoVO companychangevo = wmsCompanyInfoChangeVOList.get(m);
                    if (org_custom_info_change_id.compareTo(companychangevo.getWms_cre_credit_line_customer_change_head_id()) == 0)
                    {
                        String companystr = companychangevo.getCompanystr();
                        List<WmsCreCustomerChangeLineCompany> companyList = JsonUtil.jsonArrayToList(companystr,
                                                                                                     WmsCreCustomerChangeLineCompany.class);
                        for (WmsCreCustomerChangeLineCompany companyvo_p : companyList)
                        {
                            companyvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            companyvo_p.setCreate_user_id(user.getUserId());
                            companyvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            companyvo_p.setLast_update_user_id(user.getUserId());
                            companyvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            companyvo_p.setEnable_flag("1");
                            wmsCreCustomerChangeLineCompanyDao.save(companyvo_p);
                        }
                        break;
                    }
                }
                // 子女
                for (Integer m = wmsChildInfoChangeVOList.size() - 1; m >= 0; --m)
                {
                    WmsChildChangeInfoVO childchangevo = wmsChildInfoChangeVOList.get(m);
                    if (org_custom_info_change_id.compareTo(childchangevo.getWms_cre_credit_line_customer_change_head_id()) == 0)
                    {
                        String childstr = childchangevo.getCusChild();
                        List<WmsCusCustomerChangeChild> childList = JsonUtil.jsonArrayToList(childstr,
                                                                                             WmsCusCustomerChangeChild.class);
                        for (WmsCusCustomerChangeChild childvo_p : childList)
                        {
                            childvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            childvo_p.setCreate_user_id(user.getUserId());
                            childvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            childvo_p.setLast_update_user_id(user.getUserId());
                            childvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            childvo_p.setEnable_flag("1");
                            wmsCusCustomerChangeChildDao.save(childvo_p);
                        }
                        break;
                    }
                }
            }
        }

        if (isvcchid == 0)
        {
            if (bean.getMcclhid() != null && !bean.getMcclhid().equals(""))
            {
                if (bean.getMcclhid().indexOf("change") != -1)
                {
                	bean.setMcclhid( bean.getMcclhid().replaceAll("change", "")); ;
                }
            }
            try
            {
                isvcchid = Integer.parseInt(bean.getMcclhid());
            }
            catch (Exception e)
            {
                isvcchid = 0;
            }
        }

        /*----------------------------------------------wms_cre_credit_line_customer_change_head 客户信息变更�?end----------------------------------------------*/
        // 删除抵押房产信息
        Map<String, Object> hchDeltMap = new HashMap<>();
        ArrayList<Integer> hchDeltList = new ArrayList<>();
        hchDeltList.add(wmsCreCreditHeadId);
        hchDeltMap.put("dltIDArr", hchDeltList);
        wmsCreHousingCustomerHouseDao.deleteByMap(hchDeltMap);
        // 抵押房产信息
        if (isvcchid != 0)
        {
            WmsCreHousingCustomerHouse wmshch = new WmsCreHousingCustomerHouse();
            wmshch.setWms_cre_credit_head_id(wmsCreCreditHeadId);
            wmshch.setWms_cre_customer_change_line_houseinfo_id(isvcchid);
            wmshch.setHouse_use(bean.getHouse_use());
            wmshch.setCreate_user_id(user.getUserId()); // 创建人id
            wmshch.setCreate_user_name(user.getRealname());// 创建人名�?
            wmshch.setCreate_timestamp(sysTime);// 创建时间
            wmsCreHousingCustomerHouseDao.save(wmshch);
        }
        // 添加抵押房产信息 �?
        /*----------------------------------------------wms_cre_customer_change_line_contact 客户联系人表 begin----------------------------------------------*/
        ArrayList<Integer> dltIDArr = new ArrayList<>();
        for (int i = 0; i < mcclc.size(); i++)
        {
            WmsCreCustomerChangeLineContact everyContactVO = mcclc.get(i);
            mcclc.get(i).setWms_cre_credit_head_id(wmsCreCreditHeadId);
            dltIDArr.add(everyContactVO.getWms_cre_customer_change_line_contact_id());
            if (everyContactVO.getWms_cre_customer_change_line_contact_id() != null
                && everyContactVO.getWms_cre_customer_change_line_contact_id() != 0)
            {
                wmscrecustomerchangelinecontactDao_m.updateChangeContantBF(everyContactVO);
            }
            else
            {
                wmscrecustomerchangelinecontactDao_m.addNewRecord(mcclc.get(i));
            }
            if (bean.getIsComOrZC().equals("1"))
            {
                // 联系人电话重复记录集�?
                isRepeaded = isRepeaded || this.compareLxrAndLxr(mcclc.get(i), user);
                // 联系人与客户电话重复
                isRepeaded = isRepeaded || this.compareLxrAndKh(mcclc.get(i), user);
            }
        }
        //删除多余的联系人--亲属
        Map<String, Object> paramMap=new HashMap<>();
        if(null != dltIDArr && dltIDArr.size() > 0) {
            paramMap.put("dltIDArr", dltIDArr);
            paramMap.put("wms_cre_credit_head_id", wmsCreCreditHeadId);
            wmscrecustomerchangelinecontactDao_m.deleteOut(paramMap);
        }
        if (isRepeaded)
        {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("is_link_repeat", "1");// 联系方式是否重复
            params.put("wms_cre_credit_head_id", wmsCreCreditHeadId);
            wmscrecreditheadDao_m.updateRecord(params);
        }
        /*----------------------------------------------wms_cre_customer_change_line_contact 客户联系人表 end----------------------------------------------*/

        if(StringUtils.isNotEmpty(bean.getDeletefileArrId())) {
        	String[] data_typeList = bean.getDeletefileArrId().split(",");
        	 // 删除附件
            for (int i = 0; i < data_typeList.length; i++)
            {
                Map<String, Object> deletAtt = new HashMap<>();
                deletAtt.put("data_type", data_typeList[i]);
                deletAtt.put("wms_cre_credit_head_id", wmsCreCreditHeadId);
                wmscrehousingattDao.deleteRecords(deletAtt);
            }
        }
        /*for (int i = 0; i < attachment.size(); i++)
        {
            if (!data_typeList.contains(attachment.get(i).getData_type()))
            {
                data_typeList.add(attachment.get(i).getData_type());
            }
        }*/

        for (int i = 0; i < attachment.size(); i++)
        {
            WmsCreHousingAtt mplm = attachment.get(i);
            mplm.setWms_cre_housing_att_id(null);
            // mplm.setWms_cre_credit_head_id(Integer.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()));
            mplm.setWms_cre_credit_head_id(mcch.getWms_cre_credit_head_id());
            mplm.setCreate_user_id(user.getUserId()); // 创建人id
            mplm.setCreate_user_name(user.getRealname());// 创建人名�?
            mplm.setCreate_timestamp(sysTime);// 创建时间
            mplm.setEnable_flag("1");// 是否有效
            wmscrehousingattDao.save(mplm);
        }
        
        /*******************************客户信息控件操作      焦德龙************************/
        /*ArrayList<String> data_typeListkh = new ArrayList<>();
        for (int i = 0; i < khatt.size(); i++)
        {
            if (!data_typeListkh.contains(khatt.get(i).getData_type_name()))
            {
            	data_typeListkh.add(khatt.get(i).getData_type_name());
            }
        }
        // 删除附件
        for (int i = 0; i < data_typeListkh.size(); i++)
        {
            Map<String, Object> deletAtt = new HashMap<>();
            deletAtt.put("data_type_name", khatt.get(i).getData_type_name());
            deletAtt.put("wms_cre_credit_head_id", wmsCreCreditHeadId);
            wmsCreHousingApplyAttDao.deleteatt(deletAtt);
        }*/
        
       /* if(StringUtils.isNotEmpty(bean.getDeletefileArrkhId())) {
        	
        	 // 删除附件
            for (int i = 0; i < deleteIdArray.length; i++)
            {
                Map<String, Object> deletAtt = new HashMap<>();
                deletAtt.put("data_type_name", deleteIdArray[i]);
                deletAtt.put("wms_cre_credit_head_id", wmsCreCreditHeadId);
                wmsCreHousingApplyAttDao.deleteatt(deletAtt);
            }
        }*/
        //String[] deleteIdArray = bean.getDeletefileArrkhId().split(",");
        
        if(bean.getDeleteAttIds() != null && bean.getDeleteAttIds().length > 0) {
            Map<String, Object> deletAttMap = new HashMap<>();
            deletAttMap.put("wms_cre_credit_head_id", wmsCreCreditHeadId);
            deletAttMap.put("data_type_name", "843");
            deletAttMap.put("deleteAttIds", bean.getDeleteAttIds());
            wmsCreHousingApplyAttDao.deleteatt(deletAttMap);
        }
       
        mcch = this.wmscrecreditheadDao_m.get(wmsCreCreditHeadId);
        Map<String, Object> attParamMap = new HashMap<String, Object>();
        attParamMap.put("bill_code", mcch.getBill_code());
        for (int i = 0; i < khatt.size(); i++) {
            WmsCreHousingApplyAtt houseapp = khatt.get(i);
            houseapp.setWms_cre_credit_head_id(wmsCreCreditHeadId);
            if(houseapp.getWms_cre_housing_apply_att_id() == null) {
                //生成新的图片编号
                attParamMap.put("data_detail_name", houseapp.getData_detail_name());
                houseapp.setAttachment_old_name(wmsCreHousingApplyAttDao.getNextAttSeqByBillCodeAndDataTypeName(attParamMap));
                wmsCreHousingApplyAttDao.save(houseapp);
            } else {
                wmsCreHousingApplyAttDao.update(houseapp);
            }
        }
        
        return result;
    }

    // 比较身份证重复
    private boolean compareSfz(WmsCreCreditLineCustomerChangeHead wmscrecreditlinecustomerchangehead, UserBean user)
    {
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
        boolean isRepeaded = false;
        String id_card = wmscrecreditlinecustomerchangehead.getId_card();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if(id_card==null||"".equals(id_card)){
        	return false;
        }
        paramMap.put("id_card", id_card);
        paramMap.put("wms_cre_credit_head_id", wmscrecreditlinecustomerchangehead.getWms_cre_credit_head_id());
        List<Map<String, Object>> cs = wmscrecreditlinecustomerchangeheadDao_m.searchForDiff(paramMap);
        for (Map<String, Object> c : cs)
        {
            isRepeaded = true;
            WmsCreCreditHeadDiffPhone p = new WmsCreCreditHeadDiffPhone();
            p.setDiff_customer_names(getDiff_customer_names((Integer) c.get("wms_cre_credit_head_id")));
            p.setUser_type("3");
            p.setOrg_wms_cre_credit_head_id(wmscrecreditlinecustomerchangehead.getWms_cre_credit_head_id());
            p.setOrg_customer_contact_id(wmscrecreditlinecustomerchangehead.getWms_cre_credit_line_customer_change_head_id());
            p.setOrg_name(wmscrecreditlinecustomerchangehead.getCustomer_name());
            p.setOrg_phone(id_card);
            p.setDiff_wms_cre_credit_head_id((Integer) c.get("wms_cre_credit_head_id"));
            p.setDiff_customer_contact_id((Integer) c.get("wms_cre_credit_line_customer_change_head_id"));
            p.setDiff_name((String) c.get("customer_name"));
            p.setCreate_user_id(user.getUserId());
            p.setCreate_timestamp(sysTime);
            p.setLast_update_user_id(user.getUserId());
            p.setLast_update_timestamp(sysTime);
            p.setEnable_flag("1");
            p.setOrg_bill_code(wmscrecreditheadDao_m.get(wmscrecreditlinecustomerchangehead.getWms_cre_credit_head_id())
                                                    .getBill_code());
            if (c != null && c.get("wms_cre_credit_head_id") != null)
            {
                WmsCreCreditHead wmscrecredithead = wmscrecreditheadDao_m.get((Integer) c.get("wms_cre_credit_head_id"));
                // 判断是否为空
                if (wmscrecredithead != null && wmscrecredithead.getBill_code() != null)
                {
                    p.setDiff_bill_code(wmscrecredithead.getBill_code());
                    wmsCreCreditHeadDiffPhoneDao.save(p);
                }
            }
        }
        return isRepeaded;
    }

    // 比较联系人与联系人电话重复
    private boolean compareLxrAndLxr(WmsCreCustomerChangeLineContact wmsCreCustomerChangeLineContact, UserBean user)
    {
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
        boolean isRepeaded = false;
        Map<String, Object> params = new HashMap<String, Object>();
        if(wmsCreCustomerChangeLineContact.getContact_mobile_phone()==null||"".equals(wmsCreCustomerChangeLineContact.getContact_mobile_phone())){
        	return false;
        }
        params.put("contact_mobile_phone", wmsCreCustomerChangeLineContact.getContact_mobile_phone());
        params.put("wms_cre_credit_head_id", wmsCreCustomerChangeLineContact.getWms_cre_credit_head_id());
        List<WmsCreCustomerChangeLineContact> repeatRecords = wmscrecustomerchangelinecontactDao_m.searchByPhone(params);
        if (repeatRecords != null && repeatRecords.size() != 0)
        {
            isRepeaded = true;
            for (WmsCreCustomerChangeLineContact r : repeatRecords)
            {
                WmsCreCreditHeadDiffPhone p = new WmsCreCreditHeadDiffPhone();
                p.setDiff_customer_names(getDiff_customer_names(r.getWms_cre_credit_head_id()));
                p.setUser_type("2");
                p.setOrg_wms_cre_credit_head_id(wmsCreCustomerChangeLineContact.getWms_cre_credit_head_id());
                p.setOrg_customer_contact_id(wmsCreCustomerChangeLineContact.getWms_cre_customer_change_line_contact_id());
                p.setOrg_name(wmsCreCustomerChangeLineContact.getContact_name());
                p.setOrg_phone(wmsCreCustomerChangeLineContact.getContact_mobile_phone());
                p.setDiff_wms_cre_credit_head_id(r.getWms_cre_credit_head_id());
                p.setDiff_customer_contact_id(r.getWms_cre_customer_change_line_contact_id());
                p.setDiff_name(r.getContact_name());
                p.setCreate_user_id(user.getUserId());
                p.setCreate_timestamp(sysTime);
                p.setLast_update_user_id(user.getUserId());
                p.setLast_update_timestamp(sysTime);
                p.setEnable_flag("1");
                // 判断是否为空
                if (wmsCreCustomerChangeLineContact != null && wmsCreCustomerChangeLineContact.getWms_cre_credit_head_id() != null)
                {
                    WmsCreCreditHead wCreCreditHead = wmscrecreditheadDao_m.get(wmsCreCustomerChangeLineContact.getWms_cre_credit_head_id());
                    // 判断是否为空
                    if (wCreCreditHead != null)
                    {
                        p.setOrg_bill_code(wCreCreditHead.getBill_code());
                    }
                }
                // 判断是否为空
                if(r!=null&&r.getWms_cre_credit_head_id()!=null){
                    WmsCreCreditHead wCreCreditHead = wmscrecreditheadDao_m.get(r.getWms_cre_credit_head_id());
                    // 判断是否为空
                    if (wCreCreditHead != null)
                    {
                        p.setDiff_bill_code(wCreCreditHead.getBill_code());
                    }
                }
                // /判断编号是否为空
                if (p.getDiff_bill_code() != null && p.getOrg_bill_code() != null)
                {
                    wmsCreCreditHeadDiffPhoneDao.save(p);
                }
            }
        }
        return isRepeaded;
    }

    // 比较联系人与客户电话重复
    private boolean compareLxrAndKh(WmsCreCustomerChangeLineContact wmsCreCustomerChangeLineContact, UserBean user)
    {
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
        boolean isRepeaded = false;
        Map<String, Object> params = new HashMap<String, Object>();
        if(wmsCreCustomerChangeLineContact.getContact_mobile_phone()==null||"".equals(wmsCreCustomerChangeLineContact.getContact_mobile_phone())){
        	return false;
        }
        params.put("contact_mobile_phone", wmsCreCustomerChangeLineContact.getContact_mobile_phone());
        params.put("wms_cre_credit_head_id", wmsCreCustomerChangeLineContact.getWms_cre_credit_head_id());
        List<Map<String, Object>> repeatRecords2 = wmscrecreditlinecustomerchangeheadDao_m.searchForDiff(params);
        if (repeatRecords2 != null && repeatRecords2.size() != 0)
        {
            isRepeaded = true;
            for (Map<String, Object> c : repeatRecords2)
            {
                WmsCreCreditHeadDiffPhone p = new WmsCreCreditHeadDiffPhone();
                p.setDiff_customer_names(getDiff_customer_names((Integer) c.get("wms_cre_credit_head_id")));
                p.setUser_type("2");
                p.setOrg_wms_cre_credit_head_id(wmsCreCustomerChangeLineContact.getWms_cre_credit_head_id());
                p.setOrg_customer_contact_id(wmsCreCustomerChangeLineContact.getWms_cre_customer_change_line_contact_id());
                p.setOrg_name(wmsCreCustomerChangeLineContact.getContact_name());
                p.setDiff_wms_cre_credit_head_id((Integer) c.get("wms_cre_credit_head_id"));
                p.setDiff_customer_contact_id((Integer) c.get("wms_cre_credit_line_customer_change_head_id"));
                p.setDiff_name((String) c.get("customer_name"));
                p.setCreate_user_id(user.getUserId());
                p.setCreate_timestamp(sysTime);
                p.setLast_update_user_id(user.getUserId());
                p.setLast_update_timestamp(sysTime);
                p.setEnable_flag("1");
                p.setOrg_bill_code(wmscrecreditheadDao_m.get(wmsCreCustomerChangeLineContact.getWms_cre_credit_head_id())
                                                        .getBill_code());
                p.setDiff_bill_code(wmscrecreditheadDao_m.get((Integer) c.get("wms_cre_credit_head_id")).getBill_code());
                if (wmsCreCustomerChangeLineContact.getContact_mobile_phone().equals(c.get("mobile_telephone1")
                                                                                      .toString()))
                {
                    p.setOrg_phone(wmsCreCustomerChangeLineContact.getContact_mobile_phone());
                    wmsCreCreditHeadDiffPhoneDao.save(p);
                }
                if (wmsCreCustomerChangeLineContact.getContact_mobile_phone().equals(c.get("mobile_telephone2")
                                                                                      .toString()))
                {
                    p.setOrg_phone(wmsCreCustomerChangeLineContact.getContact_mobile_phone());
                    wmsCreCreditHeadDiffPhoneDao.save(p);
                }
            }
        }
        return isRepeaded;
    }

    // 比较客户与客户电话重复
    private boolean compareKhAndKh(WmsCreCreditLineCustomerChangeHead wmscrecreditlinecustomerchangehead, UserBean user)
    {
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
        boolean isRepeaded = false;
        Map<String, Object> params = new HashMap<String, Object>();
        boolean isnull=true;
        if (!StringUtil.isEmpty(wmscrecreditlinecustomerchangehead.getMobile_telephone1()))
        {
            params.put("mobile_telephone1", wmscrecreditlinecustomerchangehead.getMobile_telephone1());
            isnull=false;
        }
        if (!StringUtil.isEmpty(wmscrecreditlinecustomerchangehead.getMobile_telephone2()))
        {
            params.put("mobile_telephone2", wmscrecreditlinecustomerchangehead.getMobile_telephone2());
            isnull=false;
        }
        if(isnull){
        	return false;
        }
        params.put("wms_cre_credit_head_id", wmscrecreditlinecustomerchangehead.getWms_cre_credit_head_id());
        List<Map<String, Object>> repeatRecords2 = wmscrecreditlinecustomerchangeheadDao_m.searchForDiff(params);
        if (repeatRecords2 != null && repeatRecords2.size() != 0)
        {
            isRepeaded = true;
            for (Map<String, Object> c : repeatRecords2)
            {
                WmsCreCreditHeadDiffPhone p = new WmsCreCreditHeadDiffPhone();
                p.setDiff_customer_names(getDiff_customer_names((Integer) c.get("wms_cre_credit_head_id")));
                p.setUser_type("1");
                p.setOrg_wms_cre_credit_head_id(wmscrecreditlinecustomerchangehead.getWms_cre_credit_head_id());
                p.setOrg_customer_contact_id(wmscrecreditlinecustomerchangehead.getWms_cre_credit_line_customer_change_head_id());
                p.setOrg_name(wmscrecreditlinecustomerchangehead.getCustomer_name());
                p.setDiff_wms_cre_credit_head_id((Integer) c.get("wms_cre_credit_head_id"));
                p.setDiff_customer_contact_id((Integer) c.get("wms_cre_credit_line_customer_change_head_id"));
                p.setDiff_name((String) c.get("customer_name"));
                p.setCreate_user_id(user.getUserId());
                p.setCreate_timestamp(sysTime);
                p.setLast_update_user_id(user.getUserId());
                p.setLast_update_timestamp(sysTime);
                p.setEnable_flag("1");
                p.setOrg_bill_code(wmscrecreditheadDao_m.get(wmscrecreditlinecustomerchangehead.getWms_cre_credit_head_id())
                                                        .getBill_code());
                p.setDiff_bill_code(wmscrecreditheadDao_m.get((Integer) c.get("wms_cre_credit_head_id")).getBill_code());
                if (wmscrecreditlinecustomerchangehead.getMobile_telephone1().equals(c.get("mobile_telephone1")
                                                                                      .toString()))
                {
                    p.setOrg_phone(wmscrecreditlinecustomerchangehead.getMobile_telephone1());
                    wmsCreCreditHeadDiffPhoneDao.save(p);
                }
                if (wmscrecreditlinecustomerchangehead.getMobile_telephone1().equals(c.get("mobile_telephone2")
                                                                                      .toString()))
                {
                    p.setOrg_phone(wmscrecreditlinecustomerchangehead.getMobile_telephone1());
                    wmsCreCreditHeadDiffPhoneDao.save(p);
                }
                if (wmscrecreditlinecustomerchangehead.getMobile_telephone2().equals(c.get("mobile_telephone1")
                                                                                      .toString()))
                {
                    p.setOrg_phone(wmscrecreditlinecustomerchangehead.getMobile_telephone2());
                    wmsCreCreditHeadDiffPhoneDao.save(p);
                }
                if (wmscrecreditlinecustomerchangehead.getMobile_telephone2().equals(c.get("mobile_telephone2")
                                                                                      .toString())
                    && !"无".equals(wmscrecreditlinecustomerchangehead.getMobile_telephone2())
                    && !"没有".equals(wmscrecreditlinecustomerchangehead.getMobile_telephone2()))
                {
                    p.setOrg_phone(wmscrecreditlinecustomerchangehead.getMobile_telephone2());
                    wmsCreCreditHeadDiffPhoneDao.save(p);
                }
            }
        }
        return isRepeaded;
    }

    // 比较客户与联系人电话重复
    private boolean compareKhAndLxr(WmsCreCreditLineCustomerChangeHead wmscrecreditlinecustomerchangehead, UserBean user)
    {
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
        boolean isRepeaded = false;
        Map<String, Object> params = new HashMap<String, Object>();
        boolean isnull=true;
        if (!StringUtil.isEmpty(wmscrecreditlinecustomerchangehead.getMobile_telephone1()))
        {
            params.put("mobile_telephone1", wmscrecreditlinecustomerchangehead.getMobile_telephone1());
            isnull=false;
        }
        if (!StringUtil.isEmpty(wmscrecreditlinecustomerchangehead.getMobile_telephone2()))
        {
            params.put("mobile_telephone2", wmscrecreditlinecustomerchangehead.getMobile_telephone2());
            isnull=false;
        }
        if(isnull){
        	return false;
        }
        params.put("wms_cre_credit_head_id", wmscrecreditlinecustomerchangehead.getWms_cre_credit_head_id());
        List<WmsCreCustomerChangeLineContact> repeatRecords = wmscrecustomerchangelinecontactDao_m.searchByPhone2(params);
        if (repeatRecords != null && repeatRecords.size() != 0)
        {
            isRepeaded = true;
            for (WmsCreCustomerChangeLineContact r : repeatRecords)
            {
                WmsCreCreditHeadDiffPhone p = new WmsCreCreditHeadDiffPhone();
                p.setDiff_customer_names(getDiff_customer_names(r.getWms_cre_credit_head_id()));
                p.setUser_type("1");
                p.setOrg_wms_cre_credit_head_id(wmscrecreditlinecustomerchangehead.getWms_cre_credit_head_id());
                p.setOrg_customer_contact_id(wmscrecreditlinecustomerchangehead.getWms_cus_customer_head_id());
                p.setOrg_name(wmscrecreditlinecustomerchangehead.getCustomer_name());
                p.setOrg_phone(r.getContact_mobile_phone());
                p.setDiff_wms_cre_credit_head_id(r.getWms_cre_credit_head_id());
                p.setDiff_customer_contact_id(r.getWms_cre_customer_change_line_contact_id());
                p.setDiff_name(r.getContact_name());
                p.setCreate_user_id(user.getUserId());
                p.setCreate_timestamp(sysTime);
                p.setLast_update_user_id(user.getUserId());
                p.setLast_update_timestamp(sysTime);
                p.setEnable_flag("1");
                // 判断是否为空
                if (wmscrecreditlinecustomerchangehead != null && wmscrecreditlinecustomerchangehead.getWms_cre_credit_head_id() != null)
                {
                    // 添加判断
                    WmsCreCreditHead whead = wmscrecreditheadDao_m.get(wmscrecreditlinecustomerchangehead.getWms_cre_credit_head_id());
                    if (whead != null)
                    {
                        p.setOrg_bill_code(whead.getBill_code());
                    }
                }
                if (r != null && r.getWms_cre_credit_head_id() != null)
                {
                    // 添加判断
                    WmsCreCreditHead wmscrecredithead = wmscrecreditheadDao_m.get(r.getWms_cre_credit_head_id());
                    if (wmscrecredithead != null)
                    {
                        p.setDiff_bill_code(wmscrecredithead.getBill_code());
                    }
                }
                // /判断编号是否为空
                if (p.getDiff_bill_code() != null && p.getOrg_bill_code() != null)
                {
                    wmsCreCreditHeadDiffPhoneDao.save(p);
                }
            }
        }
        return isRepeaded;
    }

    private String getDiff_customer_names(Integer wms_cre_credit_head_id)
    {
        WmsCreCreditLineCustomerChangeHead main = wmscrecreditlinecustomerchangeheadDao_m.getMainByFk(wms_cre_credit_head_id);
        List<WmsCreCreditLineCustomerChangeHead> notMains = wmscrecreditlinecustomerchangeheadDao_m.getNotMainByFk(wms_cre_credit_head_id);
        String diff_customer_names = main.getCustomer_name();
        if (notMains.size() != 0)
        {
            diff_customer_names += "/";
            for (WmsCreCreditLineCustomerChangeHead customer : notMains)
            {
                diff_customer_names += (customer.getCustomer_name() + ",");
            }
            diff_customer_names = diff_customer_names.substring(0, diff_customer_names.length() - 1);
        }
        return diff_customer_names;
    }

    /**
     * 房贷办件审核组导出功能
     */
    @Override
    public Map<String, Object> getCardListWithoutPagingForFdForAdd(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                                   UserBean bean, String housekey)
    {
        Map<String, Object> paramMap=new HashMap<>();

        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", "%" + queryInfo.getCustomer_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            paramMap.put("mobile_telephone", "%" + queryInfo.getMobile_telephone() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getMort_flag()))
        {
            paramMap.put("mort_flag", queryInfo.getMort_flag());// 抵押状态
        }

        // 开发模式 1为开发模式 其他为正常权限模式
        if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode")))
        {
            paramMap.put("salesman_id", bean.getUserId());// 登陆人id
            paramMap.put("menu_url", WmsHelp.MENU_URL_ZSSP_LIST);
            paramMap.put("childrendept", queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
        }
        paramMap.put("bill_status", "D");
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditheadDao_m.getCardListWithoutPagingForFd(paramMap);
        paramMap.put("Rows", list);
        return paramMap;
    }

    /**
     * 信贷复议修订列表显示
     */
    @Override
    public Map<String, Object> getLoanReviewReturnListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = creditWorkFlowService.getIdTaskIdCreditList(user.getUserId().toString(), "15");
        if (paramMap.get("idList") == null)
        {
            return new HashMap<>();
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        // 根据提供idList查询相应的数据
        List<Map<String, Object>> list = wmscrecreditheadDao_m.getLoanReviewReturnListWithPaging(paramMap);
        // 需要重组list集合
        list = creditWorkFlowService.addTaskCredit(list, (List<Integer>) paramMap.get("idList"),
                                                   (List<String>) paramMap.get("taskIdList"),
                                                   (List<String>) paramMap.get("approvesGroups"));
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditheadDao_m.getLoanReviewReturnCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * 信贷复议修订列表导出
     */
    @Override
    public Map<String, Object> getLoanReviewReturnWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = creditWorkFlowService.getIdTaskIdCreditList(String.valueOf(user.getUserId()),
                                                                                   "15");
        if (paramMap.get("idList") == null)
        {
            return new HashMap<>();
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditheadDao_m.getLoanReviewReturnListWithoutPaging(paramMap);
        // 需要重组list集合
        list = creditWorkFlowService.addTaskCredit(list, (List<Integer>) paramMap.get("idList"),
                                                   (List<String>) paramMap.get("taskIdList"),
                                                   (List<String>) paramMap.get("approvesGroups"));
        paramMap.put("Rows", list);
        return paramMap;
    }

    /**
     * 实现对信贷和房贷单据 草稿状态下的单据进行删除操作 软删除(逻辑删除)
     */
    @Override
    public String deleteByPK(Integer wms_cre_credit_head_id, Integer cre_type)
    {
        String result = "success";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        paramMap.put("cre_type", cre_type);
        int ret = 0;
        ret = wmscrecreditheadDao_m.deleteByPK(paramMap);
        //2016-03-15 baisong 手机端申请的单据需要同时删除上传信息表
        WmsCreHousingFileInfo wmsCreHousingFileInfo=new WmsCreHousingFileInfo();
        wmsCreHousingFileInfo.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        wmsCreHousingFileInfo.setEnable_flag("0");
        wmsCreHousingFileInfoDao.update(wmsCreHousingFileInfo);
        if (ret == 0)
        {
            return result = "error";
        }
        return result;
    }

    @Override
    public String ReviewRevisionCredit(WmsCreCreditHead mcch, String attachment, String mccclc, UserBean user,
                                       String clientId, String modifyJsonCus, String isComOrZC, String taskId)
    {
        String result = "temOK";
        Integer wmsCreCreditHeadId = mcch.getWms_cre_credit_head_id();// 贷款表主�?

        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间

        List<WmsCreCreditLineCustomerDataAttachment> Lattachment = JsonUtil.jsonArrayToList(attachment,
                                                                                            WmsCreCreditLineCustomerDataAttachment.class); // 前台json附件路径数据转换为list
        List<WmsCreCustomerChangeLineContact> Lmccclc = JsonUtil.jsonArrayToList(mccclc,
                                                                                 WmsCreCustomerChangeLineContact.class); // 前台json客户联系人数据转换为list数据

        /*----------------------------------------------wms_cre_credit_head 贷款申请单主�?begin----------------------------------------------*/
        mcch.setLast_update_user_id(user.getUserId()); // 更新人id
        mcch.setLast_update_timestamp(sysTime);// 更新时间
        /*if (mcch.getSalesman_id() != null)
        {
            if (mcch.getCity().indexOf("0") != -1)
            {
                mcch.setCity(UserCityUtil.getUserCity(mcch.getCity()));
            }
        }*/
        // 把贷款产品类型 存储到备份的字段中
        mcch.setCre_loan_type_backup(mcch.getCre_loan_type());
        // 把贷款申请最大期限 存储到备份的字段中
        mcch.setMax_repayment_time_limit_backup(mcch.getMax_repayment_time_limit());

        wmscrecreditheadDao_m.updateMCCHWhenUpdateOrReSub(mcch.getInfoMap()); // 更新贷款主表
        /*----------------------------------------------wms_cre_credit_head 贷款申请单主�?end----------------------------------------------*/
        if (isComOrZC.equals("1"))
        {
            result = "OK";
            WmsCreCreditHead searchBillSt = wmscrecreditheadDao_m.get(wmsCreCreditHeadId);
            String keyPass = "";
            if (searchBillSt.getBill_status().equals("15"))
            {
                keyPass = "4";// 代表待复议修订
            }
            /*---------------------信贷流程-------------------*/
            // 根据该单据主键ID，查询出处于复议修订状态的单据
            WmsCreditWorkFlowVO aWorkFlowVO = new WmsCreditWorkFlowVO();
            aWorkFlowVO.setUser_id(user.getUserId());
            aWorkFlowVO.setWms_cre_credit_head_id(wmsCreCreditHeadId);
            aWorkFlowVO.setCredit_limit(String.valueOf(mcch.getCredit_limit()));
            aWorkFlowVO.setCre_loan_type(String.valueOf(mcch.getCre_loan_type()));
            aWorkFlowVO.setTaskId(taskId);
            creditWorkFlowService.creditInquiryTreatment(aWorkFlowVO, keyPass);
        }

        /*----------------------------------------------wms_cre_credit_line_customer_change_head 客户信息变更�?begin----------------------------------------------*/
        WmsCreCreditLineCustomerChangeHead wmscrecreditlinecustomerchangehead; // 客户信息变更�?

        List<WmsCusCustomerHead> wmscuscustomerheadvo = new ArrayList<WmsCusCustomerHead>();// 客户信息
                                                                                            // 通过查询主键得到
        List<WmsCreCreditLineCustomerChangeHead> wmscuscustomerchangeheadvoList = new ArrayList<WmsCreCreditLineCustomerChangeHead>();// 客户信息变更信息
                                                                                                                                      // 通过查询主键得到
        ArrayList<Integer> changedNochangeIDS = new ArrayList<>();
        if (!(clientId.equals("")))
        {
            for (String id : clientId.split(","))
            {
                String[] idarr = id.split("@@@");
                if (("change").equals(idarr[2]))
                {
                    WmsCreCreditLineCustomerChangeHead mcclcchVOByGet = wmscrecreditlinecustomerchangeheadDao_m.get(Integer.valueOf(idarr[0]));
                    wmscuscustomerchangeheadvoList.add(mcclcchVOByGet);
                    changedNochangeIDS.add(Integer.valueOf(idarr[0]));
                    Map<String, Object> updateIsMajorMap = new HashMap<String, Object>();
                    updateIsMajorMap.put("is_major", idarr[1]);
                    updateIsMajorMap.put("wms_cre_credit_line_customer_change_head_id", idarr[0]);
                    wmscrecreditlinecustomerchangeheadDao_m.updateIsMajor(updateIsMajorMap);
                }
                else if (("cus").equals(idarr[2]))
                {
                    WmsCusCustomerHead mcchvo = wmscuscustomerheadDao_m.get(Integer.valueOf(idarr[0]));
                    mcchvo.setEnable_flag(idarr[1]);
                    wmscuscustomerheadvo.add(mcchvo);
                }

            }
            ;
        }

        // 删除 未�?择的客户变更信息
        List<WmsCreCreditLineCustomerChangeHead> allChangeCusIn = wmscrecreditlinecustomerchangeheadDao_m.getWmsCreCreditLineCustomerChangeHeadListWithoutPaging(wmsCreCreditHeadId);
        ArrayList<Integer> dltIdsList = new ArrayList<>();
        for (int i = 0; i < allChangeCusIn.size(); i++)
        {
            boolean notInFlag = false;
            for (int j = 0; j < wmscuscustomerchangeheadvoList.size(); j++)
            {
                if (allChangeCusIn.get(i)
                                  .getWms_cre_credit_line_customer_change_head_id()
                                  .equals(wmscuscustomerchangeheadvoList.get(j)
                                                                        .getWms_cre_credit_line_customer_change_head_id()))
                {
                    notInFlag = true;
                    break;
                }
            }
            if (!notInFlag)
            {
                dltIdsList.add(allChangeCusIn.get(i).getWms_cre_credit_line_customer_change_head_id());
            }
        }
        Map<String, Object> deleteParamsMap = new HashMap<String, Object>();
        if (dltIdsList.size() > 0)
        {
            deleteParamsMap.put("dltIDArr", dltIdsList);
            wmscrecreditlinecustomerchangeheadDao_m.deleteByMap(deleteParamsMap);
            wmscrecustomerchangelinecontactDao_m.deleteByMap(deleteParamsMap);
            wmscrecustomerchangelinehouseinfoDao_m.deleteByMap(deleteParamsMap);
            wmsCreCustomerChangeLineCarpinfoDao.deleteByMap(deleteParamsMap);
            wmscrecustomerchangelineworkinfoDao_m.deleteByMap(deleteParamsMap);
            wmsCreCustomerChangeLineCompanyDao.deleteByMap(deleteParamsMap);
            wmsCusCustomerChangeChildDao.deleteByMap(deleteParamsMap);
        }
        // 删除�?��重复校验
        dltIdsList.clear();
        dltIdsList.add(wmsCreCreditHeadId);
        deleteParamsMap.clear();
        deleteParamsMap.put("dltIDArr", dltIdsList);
        wmsCreCreditHeadDiffPhoneDao.deleteByMap(deleteParamsMap);

        // 修改后的客户信息
        List<WmsCusCustomerHead> wmsCusCustomerHeadVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                     WmsCusCustomerHead.class);// 修改后list
        // 修改后的工作信息
        List<WmsCreCustomerChangeLineWorkinfo> WmsCreCustomerChangeLineWorkinfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                                                 WmsCreCustomerChangeLineWorkinfo.class);// 修改后list

        // housestr
        List<WmsHouseInfoVO> wmsHouseInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsHouseInfoVO.class);// 修改后list

        // 修改后的公司信息
        List<WmsCompanyInfoVO> wmsCompanyInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsCompanyInfoVO.class);

        // 修改后的子女信息
        List<WmsChildInfoVO> wmsChildInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsChildInfoVO.class);

        //
        boolean isRepeaded = false;
        for (int i = 0; i < wmscuscustomerheadvo.size(); i++)
        {
            boolean ishave = false;// 是否修改�?
            Integer org_custom_info_id = wmscuscustomerheadvo.get(i).getWms_cus_customer_id();
            wmscrecreditlinecustomerchangehead = new WmsCreCreditLineCustomerChangeHead();
            int j = 0;// 修改客户信息list的index
            if (wmsCusCustomerHeadVOList != null && wmsCusCustomerHeadVOList.size() > 0)
            {
                if (wmsCusCustomerHeadVOList.get(j).getWms_cus_customer_id() != null)
                {
                    for (j = wmsCusCustomerHeadVOList.size() - 1; j >= 0; j--)
                    {
                        if (wmsCusCustomerHeadVOList.get(j).getWms_cus_customer_id()
                                                    .compareTo(wmscuscustomerheadvo.get(i).getWms_cus_customer_id()) == 0)
                        {
                            ishave = true;
                            WmsCusCustomerHead pageVo = wmsCusCustomerHeadVOList.get(j);
                            copyWmscrecreditlinecustomerchangehead(wmscrecreditlinecustomerchangehead, pageVo,
                                                                   wmsCreCreditHeadId, user,
                                                                   wmscuscustomerheadvo.get(i));
                            break;
                        }
                    }
                }
                else
                {
                    break;
                }
            }

            if (!ishave)
            {
                copyWmscrecreditlinecustomerchangehead(wmscrecreditlinecustomerchangehead, wmscuscustomerheadvo.get(i),
                                                       wmsCreCreditHeadId, user, wmscuscustomerheadvo.get(i));
            }
            wmscrecreditlinecustomerchangeheadDao_m.saveWithKey(wmscrecreditlinecustomerchangehead);
            for (int k = 0; k < Lmccclc.size(); k++)
            {
                WmsCreCustomerChangeLineContact mccclcChange = Lmccclc.get(k);
                if (mccclcChange.getWms_cre_credit_line_customer_change_head_id()
                                .equals(wmscrecreditlinecustomerchangehead.getWms_cus_customer_head_id()))
                {
                    mccclcChange.setWms_cre_credit_line_customer_change_head_id(wmscrecreditlinecustomerchangehead.getWms_cre_credit_line_customer_change_head_id());
                    Lmccclc.set(k, mccclcChange);
                }
            }
            if (isComOrZC.equals("1"))
            {
                // �?��身份证号重复，并将重复记录存入数据库
                isRepeaded = isRepeaded || this.compareSfz(wmscrecreditlinecustomerchangehead, user);
                // 客户电话与联系人电话重复记录集合
                isRepeaded = isRepeaded || this.compareKhAndLxr(wmscrecreditlinecustomerchangehead, user);
                // 客户与客户电话重�?
                isRepeaded = isRepeaded || this.compareKhAndKh(wmscrecreditlinecustomerchangehead, user);
            }
            Integer customer_id = wmscrecreditlinecustomerchangehead.getWms_cre_credit_line_customer_change_head_id();
            if (ishave)
            {
                // 工作信息
                WmsCreCustomerChangeLineWorkinfo vo = WmsCreCustomerChangeLineWorkinfoVOList.get(j);// 工作信息
                vo.setWms_cre_credit_line_customer_change_head_id(customer_id);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                vo.setEnable_flag("1");
                wmscrecustomerchangelineworkinfoDao_m.addNewRecord(vo);

                // 房产信息
                for (Integer m = wmsHouseInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsHouseInfoVO housevo = wmsHouseInfoVOList.get(m);
                    if (org_custom_info_id.compareTo(housevo.getWms_cus_customer_id()) == 0)
                    {
                        String housestr = housevo.getHousestr();
                        List<WmsCreCustomerChangeLineHouseinfo> houseList = JsonUtil.jsonArrayToList(housestr,
                                                                                                     WmsCreCustomerChangeLineHouseinfo.class);
                        for (WmsCreCustomerChangeLineHouseinfo housevo_p : houseList)
                        {
                            housevo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            housevo_p.setCreate_user_id(user.getUserId());
                            housevo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            housevo_p.setLast_update_user_id(user.getUserId());
                            housevo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            housevo_p.setEnable_flag("1");
                            wmscrecustomerchangelinehouseinfoDao_m.addNewRecord(housevo_p);
                        }
                        break;
                    }
                }

                // 公司信息
                for (Integer m = wmsCompanyInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsCompanyInfoVO companyvo = wmsCompanyInfoVOList.get(m);
                    if(companyvo!=null){
		                if (org_custom_info_id.compareTo(companyvo.getWms_cus_customer_id()) == 0)
		                {
		                    String companystr = companyvo.getCompanystr();
		                    List<WmsCreCustomerChangeLineCompany> companyList = JsonUtil.jsonArrayToList(companystr,
		                                                                                                 WmsCreCustomerChangeLineCompany.class);
		                    for (WmsCreCustomerChangeLineCompany companyvo_p : companyList)
		                    {
		                        companyvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
		                        companyvo_p.setCreate_user_id(user.getUserId());
		                        companyvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
		                        companyvo_p.setLast_update_user_id(user.getUserId());
		                        companyvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
		                        companyvo_p.setEnable_flag("1");
		                        wmsCreCustomerChangeLineCompanyDao.save(companyvo_p);
		                    }
		                    break;
		                }
                    }
                }
                // 子女
                for (Integer m = wmsChildInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsChildInfoVO childvo = wmsChildInfoVOList.get(m);
                    if(childvo!=null){
	                    if (org_custom_info_id.compareTo(childvo.getWms_cus_customer_id()) == 0)
	                    {
	                        String childstr = childvo.getCusChild();
	                        List<WmsCusCustomerChangeChild> childList = JsonUtil.jsonArrayToList(childstr,
	                                                                                             WmsCusCustomerChangeChild.class);
	                        for (WmsCusCustomerChangeChild childvo_p : childList)
	                        {
	                            childvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
	                            childvo_p.setCreate_user_id(user.getUserId());
	                            childvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
	                            childvo_p.setLast_update_user_id(user.getUserId());
	                            childvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
	                            childvo_p.setEnable_flag("1");
	                            wmsCusCustomerChangeChildDao.save(childvo_p);
	                        }
	                        break;
	                    }
                    }
                }
            }
            else
            {
                // 工作信息
                WmsCusCustomerLineWorkinfo workinfoQry = new WmsCusCustomerLineWorkinfo();
                workinfoQry.setWms_cus_customer_id(org_custom_info_id);
                workinfoQry.setEnable_flag("1");
                List<WmsCusCustomerLineWorkinfo> list = wmsCusCustomerLineWorkinfoDao_m.getSingleTableListByAndMethod(workinfoQry);
                if (list != null && list.size() > 0)
                {
                    WmsCusCustomerLineWorkinfo customerLineWork = list.get(0);
                    WmsCreCustomerChangeLineWorkinfo customerChangeLineWork = new WmsCreCustomerChangeLineWorkinfo();
                    copyWmsCreCustomerChangeLineWorkinfo(customerLineWork, customerChangeLineWork);
                    customerChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                    customerChangeLineWork.setCreate_user_id(user.getUserId());
                    customerChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                    customerChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                    wmscrecustomerchangelineworkinfoDao_m.addNewRecord(customerChangeLineWork);
                }

                // 房产信息
                WmsCusCustomerLineHouseinfo qryinfo = new WmsCusCustomerLineHouseinfo();
                qryinfo.setWms_cus_customer_id(org_custom_info_id);
                qryinfo.setEnable_flag("1");
                List<WmsCusCustomerLineHouseinfo> list2 = wmsCusCustomerLineHouseinfoDao_m.getSingleTableListByAndMethod(qryinfo);
                if (list2 != null)
                {
                    for (int k = 0; k < list2.size(); k++)
                    {
                        WmsCusCustomerLineHouseinfo houserWork = list2.get(k);
                        WmsCreCustomerChangeLineHouseinfo houserWorkChangeLineWork = new WmsCreCustomerChangeLineHouseinfo();
                        copyWmsCreCustomerChangeLineHouseinfo(houserWork, houserWorkChangeLineWork);
                        houserWorkChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                        houserWorkChangeLineWork.setCreate_user_id(user.getUserId());
                        houserWorkChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                        houserWorkChangeLineWork.setLast_update_user_id(user.getUserId());
                        houserWorkChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                        houserWorkChangeLineWork.setEnable_flag("1");
                        wmscrecustomerchangelinehouseinfoDao_m.addNewRecord(houserWorkChangeLineWork);
                    }
                }

                // 公司信息
                WmsCusCustomerLineCompany qryinfo3 = new WmsCusCustomerLineCompany();
                qryinfo3.setWms_cus_customer_id(org_custom_info_id);
                qryinfo3.setEnable_flag("1");
                List<WmsCusCustomerLineCompany> list3 = wmsCusCustomerLineCompanyDao.getSingleTableListByAndMethod(qryinfo3);
                if (list3 != null)
                {
                    for (int k = 0; k < list3.size(); k++)
                    {
                        WmsCusCustomerLineCompany companyWork = list3.get(k);
                        WmsCreCustomerChangeLineCompany companyWorkChangeLineWork = new WmsCreCustomerChangeLineCompany();
                        copyWmsCreCustomerChangeLinCompanyinfo(companyWork, companyWorkChangeLineWork);
                        companyWorkChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                        companyWorkChangeLineWork.setCreate_user_id(user.getUserId());
                        companyWorkChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                        companyWorkChangeLineWork.setLast_update_user_id(user.getUserId());
                        companyWorkChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                        companyWorkChangeLineWork.setEnable_flag("1");
                        wmsCreCustomerChangeLineCompanyDao.save(companyWorkChangeLineWork);
                    }
                }

                // 子女信息
                WmsCusCustomerChild qryinfo4 = new WmsCusCustomerChild();
                qryinfo4.setWms_cus_customer_id(org_custom_info_id);
                qryinfo4.setEnable_flag("1");
                List<WmsCusCustomerChild> list4 = wmsCusCustomerChildDao.getSingleTableListByAndMethod(qryinfo4);
                if (list4 != null)
                {
                    for (int k = 0; k < list4.size(); k++)
                    {
                        WmsCusCustomerChild childWork = list4.get(k);
                        WmsCusCustomerChangeChild childWorkChangeLineWork = new WmsCusCustomerChangeChild();
                        copyWmsCreCustomerChangeLinChildinfo(childWork, childWorkChangeLineWork);
                        childWorkChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                        childWorkChangeLineWork.setCreate_user_id(user.getUserId());
                        childWorkChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                        childWorkChangeLineWork.setLast_update_user_id(user.getUserId());
                        childWorkChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                        childWorkChangeLineWork.setEnable_flag("1");
                        wmsCusCustomerChangeChildDao.save(childWorkChangeLineWork);
                    }
                }
            }
        }

        // 修改后的客户信息
        List<WmsCreCreditLineCustomerChangeHead> wmsCusCustomerHeadChangeVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                                           WmsCreCreditLineCustomerChangeHead.class);// 修改后list
        // 修改后的工作信息
        List<WmsCreCustomerChangeLineWorkinfo> WmsCreCustomerChangeLineWorkinfoVOList2 = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                                                  WmsCreCustomerChangeLineWorkinfo.class);// 修改后list

        // housestr
        List<WmsHouseChangeInfoVO> wmsHouseInfoChangeVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                       WmsHouseChangeInfoVO.class);// 修改后list

        // 修改后的公司信息
        List<WmsCompanyChangeInfoVO> wmsCompanyInfoChangeVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                           WmsCompanyChangeInfoVO.class);

        // 修改后的子女信息
        List<WmsChildChangeInfoVO> wmsChildInfoChangeVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                       WmsChildChangeInfoVO.class);

        for (int i = 0; i < wmscuscustomerchangeheadvoList.size(); i++)
        {
            boolean ishave = false;// 是否修改�?
            Integer org_custom_info_change_id = wmscuscustomerchangeheadvoList.get(i)
                                                                              .getWms_cre_credit_line_customer_change_head_id();
            wmscrecreditlinecustomerchangehead = wmscuscustomerchangeheadvoList.get(i);
            int j = 0;// 修改客户信息list的index
            if (wmsCusCustomerHeadChangeVOList != null && wmsCusCustomerHeadChangeVOList.size() > 0)
            {
                for (j = wmsCusCustomerHeadChangeVOList.size() - 1; j >= 0; j--)
                {
                    if (wmsCusCustomerHeadChangeVOList.get(j).getWms_cre_credit_line_customer_change_head_id() != null)
                    {
                        if (wmsCusCustomerHeadChangeVOList.get(j)
                                                          .getWms_cre_credit_line_customer_change_head_id()
                                                          .compareTo(wmscuscustomerchangeheadvoList.get(i)
                                                                                                   .getWms_cre_credit_line_customer_change_head_id()) == 0)
                        {
                            ishave = true;
                            ArrayList<Integer> dltOneByOCICIDList = new ArrayList<>();
                            dltOneByOCICIDList.add(org_custom_info_change_id);
                            Map<String, Object> dltOneByOCICIDMap = new HashMap<String, Object>();
                            dltOneByOCICIDMap.put("dltIDArr", dltOneByOCICIDList);
                            WmsCreCreditLineCustomerChangeHead mcclcchVOReadyToDelete = wmscrecreditlinecustomerchangeheadDao_m.get(org_custom_info_change_id);
                            wmscrecreditlinecustomerchangeheadDao_m.deleteByMap(dltOneByOCICIDMap);
                            wmscrecustomerchangelinehouseinfoDao_m.deleteByMap(dltOneByOCICIDMap);
                            wmsCreCustomerChangeLineCarpinfoDao.deleteByMap(dltOneByOCICIDMap);
                            wmscrecustomerchangelineworkinfoDao_m.deleteByMap(dltOneByOCICIDMap);
                            wmsCreCustomerChangeLineCompanyDao.deleteByMap(dltOneByOCICIDMap);
                            wmsCusCustomerChangeChildDao.deleteByMap(dltOneByOCICIDMap);
                            WmsCreCreditLineCustomerChangeHead newInsertVO = wmsCusCustomerHeadChangeVOList.get(j);
                            newInsertVO.setWms_cre_credit_head_id(mcclcchVOReadyToDelete.getWms_cre_credit_head_id());
                            newInsertVO.setWms_cus_customer_head_id(mcclcchVOReadyToDelete.getWms_cus_customer_head_id());
                            newInsertVO.setCustomer_code(mcclcchVOReadyToDelete.getCustomer_code());
                            newInsertVO.setCreate_user_id(mcclcchVOReadyToDelete.getCreate_user_id());
                            newInsertVO.setCreate_user_name(mcclcchVOReadyToDelete.getCreate_user_name());
                            newInsertVO.setCreate_timestamp(mcclcchVOReadyToDelete.getCreate_timestamp());
                            newInsertVO.setLast_update_user_id(user.getUserId());
                            newInsertVO.setLast_update_timestamp(sysTime);
                            newInsertVO.setEnable_flag("1");
                            newInsertVO.setIs_major(mcclcchVOReadyToDelete.getIs_major());
                            wmscrecreditlinecustomerchangeheadDao_m.saveWithKey(newInsertVO);
                            break;
                        }
                    }
                    else
                    {
                        break;
                    }
                }
            }

            if (isComOrZC.equals("1"))
            {
                // �?��身份证号重复，并将重复记录存入数据库
                isRepeaded = isRepeaded || this.compareSfz(wmscrecreditlinecustomerchangehead, user);
                // 客户电话与联系人电话重复记录集合
                isRepeaded = isRepeaded || this.compareKhAndLxr(wmscrecreditlinecustomerchangehead, user);
                // 客户与客户电话重�?
                isRepeaded = isRepeaded || this.compareKhAndKh(wmscrecreditlinecustomerchangehead, user);
            }

            Integer customer_id = org_custom_info_change_id;
            if (ishave)
            {
                // 工作信息
                WmsCreCustomerChangeLineWorkinfo vo = WmsCreCustomerChangeLineWorkinfoVOList2.get(j);// 工作信息
                vo.setWms_cre_credit_line_customer_change_head_id(customer_id);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                vo.setEnable_flag("1");
                wmscrecustomerchangelineworkinfoDao_m.addNewRecord(vo);

                // 房产信息
                for (Integer m = wmsHouseInfoChangeVOList.size() - 1; m >= 0; --m)
                {
                    WmsHouseChangeInfoVO housechangevo = wmsHouseInfoChangeVOList.get(m);
                    if (org_custom_info_change_id.compareTo(housechangevo.getWms_cre_credit_line_customer_change_head_id()) == 0)
                    {
                        String housestr = housechangevo.getHousestr();
                        List<WmsCreCustomerChangeLineHouseinfo> houseList = JsonUtil.jsonArrayToList(housestr,
                                                                                                     WmsCreCustomerChangeLineHouseinfo.class);
                        for (WmsCreCustomerChangeLineHouseinfo housevo_p : houseList)
                        {
                            housevo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            housevo_p.setCreate_user_id(user.getUserId());
                            housevo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            housevo_p.setLast_update_user_id(user.getUserId());
                            housevo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            housevo_p.setEnable_flag("1");
                            wmscrecustomerchangelinehouseinfoDao_m.addNewRecord(housevo_p);
                        }
                        break;
                    }
                }

                // 公司信息
                for (Integer m = wmsCompanyInfoChangeVOList.size() - 1; m >= 0; --m)
                {
                    WmsCompanyChangeInfoVO companychangevo = wmsCompanyInfoChangeVOList.get(m);
                    if (org_custom_info_change_id.compareTo(companychangevo.getWms_cre_credit_line_customer_change_head_id()) == 0)
                    {
                        String companystr = companychangevo.getCompanystr();
                        List<WmsCreCustomerChangeLineCompany> companyList = JsonUtil.jsonArrayToList(companystr,
                                                                                                     WmsCreCustomerChangeLineCompany.class);
                        for (WmsCreCustomerChangeLineCompany companyvo_p : companyList)
                        {
                            companyvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            companyvo_p.setCreate_user_id(user.getUserId());
                            companyvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            companyvo_p.setLast_update_user_id(user.getUserId());
                            companyvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            companyvo_p.setEnable_flag("1");
                            wmsCreCustomerChangeLineCompanyDao.save(companyvo_p);
                        }
                        break;
                    }
                }
                // 子女
                for (Integer m = wmsChildInfoChangeVOList.size() - 1; m >= 0; --m)
                {
                    WmsChildChangeInfoVO childchangevo = wmsChildInfoChangeVOList.get(m);
                    if (org_custom_info_change_id.compareTo(childchangevo.getWms_cre_credit_line_customer_change_head_id()) == 0)
                    {
                        String childstr = childchangevo.getCusChild();
                        List<WmsCusCustomerChangeChild> childList = JsonUtil.jsonArrayToList(childstr,
                                                                                             WmsCusCustomerChangeChild.class);
                        for (WmsCusCustomerChangeChild childvo_p : childList)
                        {
                            childvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            childvo_p.setCreate_user_id(user.getUserId());
                            childvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            childvo_p.setLast_update_user_id(user.getUserId());
                            childvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            childvo_p.setEnable_flag("1");
                            wmsCusCustomerChangeChildDao.save(childvo_p);
                        }
                        break;
                    }
                }
            }
        }

        /*----------------------------------------------wms_cre_credit_line_customer_change_head 客户信息变更�?end----------------------------------------------*/

        /*----------------------------------------------wms_cre_customer_change_line_contact 客户联系人表 begin----------------------------------------------*/
        for (int i = 0; i < Lmccclc.size(); i++)
        {
            WmsCreCustomerChangeLineContact everyContactVO = Lmccclc.get(i);
            Lmccclc.get(i).setWms_cre_credit_head_id(wmsCreCreditHeadId);
            if (everyContactVO.getWms_cre_customer_change_line_contact_id() != null
                && everyContactVO.getWms_cre_customer_change_line_contact_id() != 0)
            {
                wmscrecustomerchangelinecontactDao_m.updateChangeContantBF(everyContactVO);
            }
            else
            {
                wmscrecustomerchangelinecontactDao_m.addNewRecord(Lmccclc.get(i));
            }
            if (isComOrZC.equals("1"))
            {
                // 联系人电话重复记录集�?
                isRepeaded = isRepeaded || this.compareLxrAndLxr(Lmccclc.get(i), user);
                // 联系人与客户电话重复
                isRepeaded = isRepeaded || this.compareLxrAndKh(Lmccclc.get(i), user);
            }
        }
        if (isRepeaded)
        {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("is_link_repeat", "1");
            params.put("wms_cre_credit_head_id", wmsCreCreditHeadId);
            wmscrecreditheadDao_m.updateRecord(params);
        }
        /*----------------------------------------------wms_cre_customer_change_line_contact 客户联系人表 end----------------------------------------------*/

        /*----------------------------------------------wms_cre_credit_line_customer_data_attachment 客户资料附件�?begin----------------------------------------------*/
        // 删除�?��该贷款的客户附件
        ArrayList<Integer> attDeletList = new ArrayList<>();
        attDeletList.add(wmsCreCreditHeadId);
        Map<String, Object> attDeletMap = new HashMap<String, Object>();
        attDeletMap.put("dltIDArr", attDeletList);
        wmscrecreditlinecustomerdataattachmentDao_m.deleteByMap(attDeletMap);
        for (int i = 0; i < Lattachment.size(); i++)
        {
            Lattachment.get(i).setWms_cre_credit_head_id(wmsCreCreditHeadId);
            wmscrecreditlinecustomerdataattachmentDao_m.addNewRecord(Lattachment.get(i));
        }
        /*----------------------------------------------wms_cre_credit_line_customer_data_attachment 客户资料附件�?end----------------------------------------------*/

        return result;
    }

	/**
	 * 查询客户的房贷信息（如果多条显示最后一条）
	 */
	public Map<String, Object> getWmsCrecreditHeadInfoByCusid(Integer wms_cus_customer_id) {
		return wmscrecreditheadDao_m.getWmsCrecreditHeadInfoByCusid(wms_cus_customer_id);
	}

	/**
	 * 根据ID查询贷款单信息
	 */
	public Map<String, Object> getWmsCusCreditHeadInfo(Integer wms_cre_credit_head_id) {
		return wmscrecreditheadDao_m.getWmsCusCreditHeadInfo(wms_cre_credit_head_id);
	}

	/**
	 * 车贷申请
	 */
	public String saveCarCreAll(WmsCreCreditHead mcch, String mccclc, UserBean user, String clientId, String modifyJsonCus,
			String house_situation, String zdrID, String mcclhid, String mcclcid, String fileArr, String isComOrZC, String is_house_loan) {
		
		String result = "temOK";
        int isvcchid = 0;// 客户房产信息更改表的主键
        int isvcccid = 0;// 客户车产信息更改表的主键
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间

        List<WmsCreCustomerChangeLineContact> mcclc = JsonUtil.jsonArrayToList(mccclc,
                                                                               WmsCreCustomerChangeLineContact.class); // 前台json客户联系人数据转换为list数据
        List<WmsCreCarpHousingAtt> attachment = JsonUtil.jsonArrayToList(fileArr, WmsCreCarpHousingAtt.class); // 前台json附件路径数据转换为list
        /*----------------------------------------------wms_cre_credit_head 贷款申请单主�?begin----------------------------------------------*/
        
        mcch.setCreate_user_id(user.getUserId()); // 创建人id
        mcch.setCreate_user_name(user.getRealname());// 创建人名�?
        mcch.setCreate_timestamp(sysTime);// 创建时间
        mcch.setCreate_user_city_code(user.getUser_regionNumber());// 创建人区域编码
        mcch.setEnable_flag("1");// 是否有效
        mcch.setCre_type("3");//车贷
        mcch.setBill_code(CodeNoUtil.getCarCreCode()); // 信用贷款单据的编�?
        mcch.setBill_status("A1");// 单据的状:A1.草稿状;
        mcch.setIs_link_repeat("0");
        /*if (mcch.getSalesman_id() != null)
        {
            mcch.setCity(UserCityUtil.getUserCity(mcch.getCity()));
        }*/
        wmscrecreditheadDao_m.saveByPk(mcch); // 保存贷款单据信息并返回保存后的id
        Integer wmsCreCreditHeadId = mcch.getWms_cre_credit_head_id();// 得到新增的贷款的主键
        /*----------------------------------------------wms_cre_credit_head 贷款申请单主�?end----------------------------------------------*/
        if (isComOrZC.equals("1"))
        {
            result = "OK";
            /*---------------------�?��流程-------------------*/
            carLoanWorkFlowService.startWorkFlow(String.valueOf(user.getUserId()),
                                                                String.valueOf(wmsCreCreditHeadId));
        }
        /*----------------------------------------------wms_cre_credit_line_customer_change_head 客户信息变更�?begin----------------------------------------------*/
        WmsCreCreditLineCustomerChangeHead wmscrecreditlinecustomerchangehead; // 客户信息变更�?
        // 贷款人信息LIST
        List<WmsCusCustomerHead> wmscuscustomerheadvo = new ArrayList<WmsCusCustomerHead>();
        if (!(clientId.equals("")))
        {
            for (String id : clientId.split(","))
            {
                String[] idarr = id.split("@@@");
                WmsCusCustomerHead mcchvo = wmscuscustomerheadDao_m.get(Integer.valueOf(idarr[0]));
                mcchvo.setEnable_flag(idarr[1]);
                wmscuscustomerheadvo.add(mcchvo);
            }
            ;
        }
        List<WmsCusCustomerHead> wmsCusCustomerHeadVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                     WmsCusCustomerHead.class);// 修改后list
        // 修改后的工作信息
        List<WmsCreCustomerChangeLineWorkinfo> WmsCreCustomerChangeLineWorkinfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                                                 WmsCreCustomerChangeLineWorkinfo.class);// 修改后list
        // housestr
        List<WmsHouseInfoVO> wmsHouseInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsHouseInfoVO.class);// 修改后list
      
        // 修改后的车产信息
        List<WmsCarInfoVO> wmsCarInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsCarInfoVO.class);
        
        // 修改后的公司信息
        List<WmsCompanyInfoVO> wmsCompanyInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsCompanyInfoVO.class);

        // 修改后的子女信息
        List<WmsChildInfoVO> wmsChildInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsChildInfoVO.class);
        boolean isRepeaded = false;

        for (int i = 0; i < wmscuscustomerheadvo.size(); i++)
        {
            boolean ishave = false;// 贷款人信息是否被手动修改�?
            Integer org_custom_info_id = wmscuscustomerheadvo.get(i).getWms_cus_customer_id();
            wmscrecreditlinecustomerchangehead = new WmsCreCreditLineCustomerChangeHead();
            int j = 0;// 修改客户信息list的index
            if (wmsCusCustomerHeadVOList != null && wmsCusCustomerHeadVOList.size() > 0)
            {
                for (j = wmsCusCustomerHeadVOList.size() - 1; j >= 0; j--)
                {
                    if (wmsCusCustomerHeadVOList.get(j).getWms_cus_customer_id()
                                                .compareTo(wmscuscustomerheadvo.get(i).getWms_cus_customer_id()) == 0)
                    {
                        ishave = true;
                        WmsCusCustomerHead pageVo = wmsCusCustomerHeadVOList.get(j);
                        copyWmscrecreditlinecustomerchangehead(wmscrecreditlinecustomerchangehead, pageVo,
                                                               wmsCreCreditHeadId, user, wmscuscustomerheadvo.get(i));
                        break;
                    }
                }
            }

            if (!ishave)
            {
                copyWmscrecreditlinecustomerchangehead(wmscrecreditlinecustomerchangehead, wmscuscustomerheadvo.get(i),
                                                       wmsCreCreditHeadId, user, wmscuscustomerheadvo.get(i));
            }
            wmscrecreditlinecustomerchangeheadDao_m.saveWithKey(wmscrecreditlinecustomerchangehead);
            //客户联系人信息
            for (int k = 0; k < mcclc.size(); k++)
            {
                WmsCreCustomerChangeLineContact mccclcChange = mcclc.get(k);
                if (mccclcChange.getWms_cre_credit_line_customer_change_head_id()
                                .equals(wmscrecreditlinecustomerchangehead.getWms_cus_customer_head_id()))
                {
                    mccclcChange.setWms_cre_credit_line_customer_change_head_id(wmscrecreditlinecustomerchangehead.getWms_cre_credit_line_customer_change_head_id());
                    mcclc.set(k, mccclcChange);
                }
            }
            if (isComOrZC.equals("1"))
            {
                // �?��身份证号重复，并将重复记录存入数据库
                isRepeaded = isRepeaded || this.compareSfz(wmscrecreditlinecustomerchangehead, user);
                // 客户电话与联系人电话重复记录集合
                isRepeaded = isRepeaded || this.compareKhAndLxr(wmscrecreditlinecustomerchangehead, user);
                // 客户与客户电话重�?
                isRepeaded = isRepeaded || this.compareKhAndKh(wmscrecreditlinecustomerchangehead, user);
            }

            Integer customer_id = wmscrecreditlinecustomerchangehead.getWms_cre_credit_line_customer_change_head_id();
            
            if (ishave)
            {
                // 工作信息
                WmsCreCustomerChangeLineWorkinfo vo = WmsCreCustomerChangeLineWorkinfoVOList.get(j);// 工作信息
                vo.setWms_cre_credit_line_customer_change_head_id(customer_id);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                vo.setEnable_flag("1");
                wmscrecustomerchangelineworkinfoDao_m.addNewRecord(vo);

                // 房产信息
                for (Integer m = wmsHouseInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsHouseInfoVO housevo = wmsHouseInfoVOList.get(m);
                    if (org_custom_info_id.compareTo(housevo.getWms_cus_customer_id()) == 0)
                    {
                        String housestr = housevo.getHousestr();
                        List<WmsCreCustomerChangeLineHouseinfo> houseList = JsonUtil.jsonArrayToList(housestr,
                                                                                                     WmsCreCustomerChangeLineHouseinfo.class);
                        for (WmsCreCustomerChangeLineHouseinfo housevo_p : houseList)
                        {
                            boolean hosFlag = false;
                            if (housevo_p.getEnable_flag() != null && housevo_p.getEnable_flag().equals(mcclhid))
                            {
                                hosFlag = true;
                            }
                            housevo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            housevo_p.setCreate_user_id(user.getUserId());
                            housevo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            housevo_p.setLast_update_user_id(user.getUserId());
                            housevo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            housevo_p.setEnable_flag("1");
                            if (hosFlag)
                            {
                                wmscrecustomerchangelinehouseinfoDao_m.addNewRecordReKey(housevo_p);
                                isvcchid = housevo_p.getWms_cre_customer_change_line_houseinfo_id();
                                
                                // 抵押房产信息
                                WmsCreCarpHousingCustomerInfo wmsCreCarpHousingCustomerInfo = new WmsCreCarpHousingCustomerInfo();
                                wmsCreCarpHousingCustomerInfo.setWms_cre_credit_head_id(wmsCreCreditHeadId);
                                wmsCreCarpHousingCustomerInfo.setWms_cre_customer_change_line_houseinfo_id(isvcchid);
                                wmsCreCarpHousingCustomerInfo.setHouse_situation(house_situation);
                                wmsCreCarpHousingCustomerInfo.setHouse_type(housevo_p.getHouse_type());
                                wmsCreCarpHousingCustomerInfo.setBuilt_area(String.valueOf(housevo_p.getHouse_building_area()));
                                wmsCreCarpHousingCustomerInfo.setIs_house_loan(is_house_loan);
                                wmsCreCarpHousingCustomerInfo.setHouse_address_province(housevo_p.getHouse_address_province());
                                wmsCreCarpHousingCustomerInfo.setHouse_address_city(housevo_p.getHouse_address_city());
                                wmsCreCarpHousingCustomerInfo.setHouse_address_area(housevo_p.getHouse_address_district());
                                wmsCreCarpHousingCustomerInfo.setHouse_address_other(housevo_p.getHouse_address_more());
                                wmsCreCarpHousingCustomerInfo.setCreate_user_id(user.getUserId());
                                wmsCreCarpHousingCustomerInfo.setCreate_timestamp(sysTime);
                                wmsCreCarpHousingCustomerInfo.setEnable_flag("1");
                                //插入车贷抵押房产信息表
                                wmsCreCarpHousingCustomerInfoDao.save(wmsCreCarpHousingCustomerInfo);
                                
                            }
                            else
                                wmscrecustomerchangelinehouseinfoDao_m.addNewRecord(housevo_p);
                        }
                        break;
                    }
                }
                
                // 车产信息
                for (Integer m = wmsCarInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsCarInfoVO carvo = wmsCarInfoVOList.get(m);
                    if (org_custom_info_id.compareTo(carvo.getWms_cus_customer_id()) == 0)
                    {
                        String carstr = carvo.getCarstr();
                        List<WmsCreCustomerChangeLineCarpinfo> carList = JsonUtil.jsonArrayToList(carstr, WmsCreCustomerChangeLineCarpinfo.class);
                        for (WmsCreCustomerChangeLineCarpinfo carvo_p : carList)
                        {
                            boolean carFlag = false;
                            if (carvo_p.getEnable_flag() != null && carvo_p.getEnable_flag().equals(mcclcid))
                            {
                                carFlag = true;
                            }
                            carvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            carvo_p.setCreate_user_id(user.getUserId());
                            carvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            carvo_p.setLast_update_user_id(user.getUserId());
                            carvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            carvo_p.setEnable_flag("1");
                            if (carFlag)
                            {
                            	wmscrecustomerchangelinecarpinfoDao_m.addNewRecordReKey(carvo_p);
                                isvcccid = carvo_p.getWms_cre_customer_change_line_carpinfo_id();
                                
                                //抵押车产信息
                                WmsCreCarpCarsCustomerInfo carpCarsCustomerInfo = new WmsCreCarpCarsCustomerInfo();
                                carpCarsCustomerInfo.setWms_cre_credit_head_id(wmsCreCreditHeadId);
                                carpCarsCustomerInfo.setWms_cre_customer_change_line_carpinfo_id(isvcccid);
                                carpCarsCustomerInfo.setCar_name(carvo_p.getCar_name());
                                carpCarsCustomerInfo.setCar_no(carvo_p.getCar_no());
                                carpCarsCustomerInfo.setCar_purchase_time(carvo_p.getCar_purchase_time());
                                carpCarsCustomerInfo.setCreate_user_id(carvo_p.getCreate_user_id());
                                carpCarsCustomerInfo.setCreate_timestamp(carvo_p.getCreate_timestamp());
                                carpCarsCustomerInfo.setEnable_flag("1");
                                //插入抵押房产信息表
                                wmsCreCarpCarsCustomerInfoDao.save(carpCarsCustomerInfo);
                                
                            }
                            else
                            	wmscrecustomerchangelinecarpinfoDao_m.addNewRecordReKey(carvo_p);
                        }
                        break;
                    }
                }

                // 公司信息
                for (Integer m = wmsCompanyInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsCompanyInfoVO companyvo = wmsCompanyInfoVOList.get(m);
                    if (org_custom_info_id.compareTo(companyvo.getWms_cus_customer_id()) == 0)
                    {
                        String companystr = companyvo.getCompanystr();
                        List<WmsCreCustomerChangeLineCompany> companyList = JsonUtil.jsonArrayToList(companystr,
                                                                                                     WmsCreCustomerChangeLineCompany.class);
                        for (WmsCreCustomerChangeLineCompany companyvo_p : companyList)
                        {
                            companyvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            companyvo_p.setCreate_user_id(user.getUserId());
                            companyvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            companyvo_p.setLast_update_user_id(user.getUserId());
                            companyvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            companyvo_p.setEnable_flag("1");
                            wmsCreCustomerChangeLineCompanyDao.save(companyvo_p);
                        }
                        break;
                    }
                }
                // 子女
                for (Integer m = wmsChildInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsChildInfoVO childvo = wmsChildInfoVOList.get(m);
                    if (org_custom_info_id.compareTo(childvo.getWms_cus_customer_id()) == 0)
                    {
                        String childstr = childvo.getCusChild();
                        List<WmsCusCustomerChangeChild> childList = JsonUtil.jsonArrayToList(childstr,
                                                                                             WmsCusCustomerChangeChild.class);
                        for (WmsCusCustomerChangeChild childvo_p : childList)
                        {
                            childvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            childvo_p.setCreate_user_id(user.getUserId());
                            childvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            childvo_p.setLast_update_user_id(user.getUserId());
                            childvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            childvo_p.setEnable_flag("1");
                            wmsCusCustomerChangeChildDao.save(childvo_p);
                        }
                        break;
                    }
                }
            }
            else
            {
                // 工作信息
                WmsCusCustomerLineWorkinfo workinfoQry = new WmsCusCustomerLineWorkinfo();
                workinfoQry.setWms_cus_customer_id(org_custom_info_id);
                workinfoQry.setEnable_flag("1");
                List<WmsCusCustomerLineWorkinfo> list = wmsCusCustomerLineWorkinfoDao_m.getSingleTableListByAndMethod(workinfoQry);
                if (list != null && list.size() > 0)
                {
                    WmsCusCustomerLineWorkinfo customerLineWork = list.get(0);
                    WmsCreCustomerChangeLineWorkinfo customerChangeLineWork = new WmsCreCustomerChangeLineWorkinfo();
                    copyWmsCreCustomerChangeLineWorkinfo(customerLineWork, customerChangeLineWork);
                    customerChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                    customerChangeLineWork.setCreate_user_id(user.getUserId());
                    customerChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                    customerChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                    wmscrecustomerchangelineworkinfoDao_m.addNewRecord(customerChangeLineWork);
                }

                // 房产信息
                WmsCusCustomerLineHouseinfo qryinfo = new WmsCusCustomerLineHouseinfo();
                qryinfo.setWms_cus_customer_id(org_custom_info_id);
                qryinfo.setEnable_flag("1");
                List<WmsCusCustomerLineHouseinfo> list2 = wmsCusCustomerLineHouseinfoDao_m.getSingleTableListByAndMethod(qryinfo);
                if (list2 != null)
                {
                    for (int k = 0; k < list2.size(); k++)
                    {
                        WmsCusCustomerLineHouseinfo houserWork = list2.get(k);
                        boolean hosFlag = false;
                        if (String.valueOf(houserWork.getWms_cus_customer_line_houseinfo_id()).equals(mcclhid))
                        {
                            hosFlag = true;
                        }
                        WmsCreCustomerChangeLineHouseinfo houserWorkChangeLineWork = new WmsCreCustomerChangeLineHouseinfo();
                        copyWmsCreCustomerChangeLineHouseinfo(houserWork, houserWorkChangeLineWork);
                        houserWorkChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                        houserWorkChangeLineWork.setCreate_user_id(user.getUserId());
                        houserWorkChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                        houserWorkChangeLineWork.setLast_update_user_id(user.getUserId());
                        houserWorkChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                        houserWorkChangeLineWork.setEnable_flag("1");
                        if (hosFlag)
                        {
                            wmscrecustomerchangelinehouseinfoDao_m.addNewRecordReKey(houserWorkChangeLineWork);
                            isvcchid = houserWorkChangeLineWork.getWms_cre_customer_change_line_houseinfo_id();
                            // 抵押房产信息
                            WmsCreCarpHousingCustomerInfo wmsCreCarpHousingCustomerInfo = new WmsCreCarpHousingCustomerInfo();
                            wmsCreCarpHousingCustomerInfo.setWms_cre_credit_head_id(wmsCreCreditHeadId);
                            wmsCreCarpHousingCustomerInfo.setWms_cre_customer_change_line_houseinfo_id(isvcchid);
                            wmsCreCarpHousingCustomerInfo.setHouse_situation(house_situation);
                            wmsCreCarpHousingCustomerInfo.setHouse_type(houserWorkChangeLineWork.getHouse_type());
                            wmsCreCarpHousingCustomerInfo.setBuilt_area(String.valueOf(houserWorkChangeLineWork.getHouse_building_area()));
                            wmsCreCarpHousingCustomerInfo.setIs_house_loan(is_house_loan);
                            wmsCreCarpHousingCustomerInfo.setHouse_address_province(houserWorkChangeLineWork.getHouse_address_province());
                            wmsCreCarpHousingCustomerInfo.setHouse_address_city(houserWorkChangeLineWork.getHouse_address_city());
                            wmsCreCarpHousingCustomerInfo.setHouse_address_area(houserWorkChangeLineWork.getHouse_address_district());
                            wmsCreCarpHousingCustomerInfo.setHouse_address_other(houserWorkChangeLineWork.getHouse_address_more());
                            wmsCreCarpHousingCustomerInfo.setCreate_user_id(user.getUserId());
                            wmsCreCarpHousingCustomerInfo.setCreate_timestamp(sysTime);
                            wmsCreCarpHousingCustomerInfo.setEnable_flag("1");
                            //插入车贷抵押房产信息表
                            wmsCreCarpHousingCustomerInfoDao.save(wmsCreCarpHousingCustomerInfo);
                        }
                        else
                            wmscrecustomerchangelinehouseinfoDao_m.addNewRecord(houserWorkChangeLineWork);
                    }
                }
                
                // 车产信息
                WmsCusCustomerLineCarpinfo carpinfo = new WmsCusCustomerLineCarpinfo();
                carpinfo.setWms_cus_customer_id(org_custom_info_id);
                carpinfo.setEnable_flag("1");
                List<WmsCusCustomerLineCarpinfo> listcar = wmsCusCustomerLineCarpinfoDao.getListByEntity(carpinfo);
                if(listcar != null) {
                	for(int m = 0; m < listcar.size(); m++) {
                		WmsCusCustomerLineCarpinfo lineCarpinfo = listcar.get(m);
                		boolean carFlag = false;
                		if (String.valueOf(lineCarpinfo.getWms_cus_customer_line_carpinfo_id()).equals(mcclcid)) {
                			carFlag = true;
                        }
                		WmsCreCustomerChangeLineCarpinfo changeLineCarpinfo = new WmsCreCustomerChangeLineCarpinfo();
                		copyWmsCreCustomerChangeLineCarinfo(lineCarpinfo, changeLineCarpinfo);
                		changeLineCarpinfo.setWms_cre_credit_line_customer_change_head_id(customer_id);
                		changeLineCarpinfo.setCreate_user_id(user.getUserId());
                		changeLineCarpinfo.setCreate_timestamp(sysTime);
                		changeLineCarpinfo.setLast_update_user_id(user.getUserId());
                		changeLineCarpinfo.setLast_update_timestamp(sysTime);
                		changeLineCarpinfo.setEnable_flag("1");
                		
                		if(carFlag) {
                			wmscrecustomerchangelinecarpinfoDao_m.addNewRecordReKey(changeLineCarpinfo);
                            isvcccid = changeLineCarpinfo.getWms_cre_customer_change_line_carpinfo_id();
                            //抵押车产信息
                            WmsCreCarpCarsCustomerInfo carpCarsCustomerInfo = new WmsCreCarpCarsCustomerInfo();
                            carpCarsCustomerInfo.setWms_cre_credit_head_id(wmsCreCreditHeadId);
                            carpCarsCustomerInfo.setWms_cre_customer_change_line_carpinfo_id(isvcccid);
                            carpCarsCustomerInfo.setCar_name(changeLineCarpinfo.getCar_name());
                            carpCarsCustomerInfo.setCar_no(changeLineCarpinfo.getCar_no());
                            carpCarsCustomerInfo.setCar_purchase_time(changeLineCarpinfo.getCar_purchase_time());
                            carpCarsCustomerInfo.setCreate_user_id(changeLineCarpinfo.getCreate_user_id());
                            carpCarsCustomerInfo.setCreate_timestamp(changeLineCarpinfo.getCreate_timestamp());
                            carpCarsCustomerInfo.setEnable_flag("1");
                            //插入抵押房产信息表
                            wmsCreCarpCarsCustomerInfoDao.save(carpCarsCustomerInfo);
                        }
                        else
                        	wmscrecustomerchangelinecarpinfoDao_m.addNewRecordReKey(changeLineCarpinfo);
                	}
                }
                
                // 公司信息
                WmsCusCustomerLineCompany qryinfo3 = new WmsCusCustomerLineCompany();
                qryinfo3.setWms_cus_customer_id(org_custom_info_id);
                qryinfo3.setEnable_flag("1");
                List<WmsCusCustomerLineCompany> list3 = wmsCusCustomerLineCompanyDao.getSingleTableListByAndMethod(qryinfo3);
                if (list3 != null)
                {
                    for (int k = 0; k < list3.size(); k++)
                    {
                        WmsCusCustomerLineCompany companyWork = list3.get(k);
                        WmsCreCustomerChangeLineCompany companyWorkChangeLineWork = new WmsCreCustomerChangeLineCompany();
                        copyWmsCreCustomerChangeLinCompanyinfo(companyWork, companyWorkChangeLineWork);
                        companyWorkChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                        companyWorkChangeLineWork.setCreate_user_id(user.getUserId());
                        companyWorkChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                        companyWorkChangeLineWork.setLast_update_user_id(user.getUserId());
                        companyWorkChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                        companyWorkChangeLineWork.setEnable_flag("1");
                        wmsCreCustomerChangeLineCompanyDao.save(companyWorkChangeLineWork);
                    }
                }

                // 子女信息
                WmsCusCustomerChild qryinfo4 = new WmsCusCustomerChild();
                qryinfo4.setWms_cus_customer_id(org_custom_info_id);
                qryinfo4.setEnable_flag("1");
                List<WmsCusCustomerChild> list4 = wmsCusCustomerChildDao.getSingleTableListByAndMethod(qryinfo4);
                if (list4 != null)
                {
                    for (int k = 0; k < list4.size(); k++)
                    {
                        WmsCusCustomerChild childWork = list4.get(k);
                        WmsCusCustomerChangeChild childWorkChangeLineWork = new WmsCusCustomerChangeChild();
                        copyWmsCreCustomerChangeLinChildinfo(childWork, childWorkChangeLineWork);
                        childWorkChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                        childWorkChangeLineWork.setCreate_user_id(user.getUserId());
                        childWorkChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                        childWorkChangeLineWork.setLast_update_user_id(user.getUserId());
                        childWorkChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                        childWorkChangeLineWork.setEnable_flag("1");
                        wmsCusCustomerChangeChildDao.save(childWorkChangeLineWork);
                    }
                }
            }
        }

        /*----------------------------------------------wms_cre_credit_line_customer_change_head 客户信息变更�?end----------------------------------------------*/
        
        /*----------------------------------------------wms_cre_customer_change_line_contact 客户联系人表 begin----------------------------------------------*/
        for (int i = 0; i < mcclc.size(); i++)
        {
            mcclc.get(i).setWms_cre_credit_head_id(wmsCreCreditHeadId);
            wmscrecustomerchangelinecontactDao_m.addNewRecord(mcclc.get(i));
            if (isComOrZC.equals("1"))
            {
                // 联系人电话重复记录集�?
                isRepeaded = isRepeaded || this.compareLxrAndLxr(mcclc.get(i), user);
                // 联系人与客户电话重复
                isRepeaded = isRepeaded || this.compareLxrAndKh(mcclc.get(i), user);
            }
        }
        if (isRepeaded)
        {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("is_link_repeat", "1");// 联系方式是否重复
            params.put("wms_cre_credit_head_id", wmsCreCreditHeadId);
            wmscrecreditheadDao_m.updateRecord(params);
        }
        /*----------------------------------------------wms_cre_customer_change_line_contact 客户联系人表 end----------------------------------------------*/
        //附件信息
        for (int i = 0; i < attachment.size(); i++)
        {
        	WmsCreCarpHousingAtt mplm = attachment.get(i);
            mplm.setData_type("1");// 1为贷款申请
            // mplm.setWms_cre_credit_head_id(Integer.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()));
            mplm.setWms_cre_credit_head_id(mcch.getWms_cre_credit_head_id());
            mplm.setCreate_user_id(user.getUserId()); // 创建人id
            mplm.setCreate_user_name(user.getRealname());// 创建人名�?
            mplm.setCreate_timestamp(sysTime);// 创建时间
            mplm.setEnable_flag("1");// 是否有效
            wmsCreCarpHousingAttDao.save(mplm);
        }
        return result;
	}

	@Override
	public Map<String, Object> getCarCreditWithPagingList(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user) {
		// 流程获得idList
        Map<String, Object> paramMap = carLoanWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()), "1");
        if (paramMap.get("idList") == null)
        {
            return new HashMap<>();
        }
        // 添加查询条件
        if (StringUtil.isNotBlank(queryInfo.getBill_code())) {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name())) {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_start())) {
	 		paramMap.put("create_timestamp_start", queryInfo.getCreate_timestamp_start());
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())) {
	 		paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
	 	}
        if (StringUtil.isNotBlank(queryInfo.getId_card())) {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        paramMap.put("create_user_id", user.getUserId());
        paramMap.put("salesman_city_code", user.getUser_city());
        paramMap.put("cre_type", queryInfo.getCre_type());
        paramMap.put("bill_status", "B1");
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        List<Map<String, Object>> list = wmscrecreditheadDao_m.recheckForCar0(paramMap);
        // 添加taskId
        list = houseCreditWorkFlowService.addTaskIDHouse(list, (List<Integer>) paramMap.get("idList"),
                                                         (List<String>) paramMap.get("taskIdList"));
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), wmscrecreditheadDao_m.findCountRecheck0(paramMap),
                                             list);
        return bean.getGridData();
	}

	@Override
	public Map<String, Object> getCarCreditWithoutPagingList(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user) {
		// 流程获得idList
        Map<String, Object> paramMap = carLoanWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()), "1");
        if (paramMap.get("idList") == null)
        {
            return new HashMap<>();
        }
        // 添加查询条件
        if (StringUtil.isNotBlank(queryInfo.getBill_code())) {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name())) {
        	paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        /*if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp())) {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }*/
        if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_start())) {
	 		paramMap.put("create_timestamp_start", queryInfo.getCreate_timestamp_start());
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())) {
	 		paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
	 	}
        if (StringUtil.isNotBlank(queryInfo.getId_card())) {
        	paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        paramMap.put("create_user_id", user.getUserId());
        paramMap.put("salesman_city_code", user.getUser_city());
        paramMap.put("cre_type", queryInfo.getCre_type());
        paramMap.put("bill_status", "B1");
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditheadDao_m.recheckForCar0(paramMap);
        paramMap.put("Rows", list);
        return paramMap;
	}

	@Override
	public Map<String, Object> getCarRecheckReturnListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user) {
		Map<String, Object> paramMap = carLoanWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()), "2");
		if (StringUtil.isNotBlank(queryInfo.getBill_code())) {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_start())) {
	 		paramMap.put("create_timestamp_start", queryInfo.getCreate_timestamp_start());
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())) {
	 		paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
	 	}
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name())) {
        	paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
		//车贷
		paramMap.put("cre_type", "3");
		//复核退回
		paramMap.put("create_user_id", user.getUserId());
		paramMap.put("bill_status", "M1");
		paramMap.put("sortname", queryInfo.getSortname());
		paramMap.put("sortorder", queryInfo.getSortorder());
		paramMap.put("offset", queryInfo.getOffset());
		paramMap.put("pagesize", queryInfo.getPagesize());
		List<Map<String, Object>> list = wmscrecreditheadDao_m.recheckForCar(paramMap);
		list = carLoanWorkFlowService.setWorkFlowTaskID(list, (List<Integer>) paramMap.get("idList"), (List<String>) paramMap.get("taskIdList"), 
				(List<String>)paramMap.get("approvesGroups"), (List<String>)paramMap.get("approveAdvices"), (List<String>)paramMap.get("approveTimes"));
		GridDataBean bean = new GridDataBean(queryInfo.getPage(), wmscrecreditheadDao_m.findCountRecheck(paramMap), list);
		
		return bean.getGridData();
	}
	
	/**
     * 实现车贷复核退回导出
     */
    public Map<String, Object> getCarRecheckReturnListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user)
    {

    	Map<String, Object> paramMap = carLoanWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()), "2");
    	if (StringUtil.isNotBlank(queryInfo.getBill_code())) {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
    	if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_start())) {
	 		paramMap.put("create_timestamp_start", queryInfo.getCreate_timestamp_start());
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())) {
	 		paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
	 	}
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name())) {
        	paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
		//车贷
		paramMap.put("cre_type", "3");
		//复核退回
		paramMap.put("create_user_id", user.getUserId());
		paramMap.put("bill_status", "M1");
		paramMap.put("sortname", queryInfo.getSortname());
		paramMap.put("sortorder", queryInfo.getSortorder());
		paramMap.put("offset", queryInfo.getOffset());
		paramMap.put("pagesize", queryInfo.getPagesize());
		List<Map<String, Object>> list = wmscrecreditheadDao_m.recheckForCar(paramMap);
        paramMap.put("Rows", list);
        return paramMap;
    }

	@Override
	public String updateCarCreAll(WmsCreCreditHead mcch, String mccclc, UserBean user, String clientId, String modifyJsonCus,
			String house_situation, String zdrID, String mcclhid, String mcclcid, String fileArr, String isComOrZC, String is_house_loan, String taskId) {
		
		String result = "temOK";
		Integer wmsCreCreditHeadId = mcch.getWms_cre_credit_head_id();// 得到新增的贷款的主键
        int isvcchid = 0;// 客户房产信息更改表的主键
        int isvcccid = 0;// 客户车产信息更改表的主键
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间

        List<WmsCreCustomerChangeLineContact> mcclc = JsonUtil.jsonArrayToList(mccclc,
                                                                               WmsCreCustomerChangeLineContact.class); // 前台json客户联系人数据转换为list数据
        List<WmsCreCarpHousingAtt> attachment = JsonUtil.jsonArrayToList(fileArr, WmsCreCarpHousingAtt.class); // 前台json附件路径数据转换为list
		
        /*贷款申请单主�?begin----------------------------------------------*/
        mcch.setLast_update_user_id(user.getUserId()); // 更新人id
        mcch.setLast_update_timestamp(sysTime);// 更新时间
        
        wmscrecreditheadDao_m.updateMCCHWhenUpdateOrReSub(mcch.getInfoMap()); // 更新贷款主表
        //更新抵押房产的房产归属（house_situation）和房屋是否有贷款（is_house_loan）
        WmsCreCarpHousingCustomerInfo creCarpHousingCustomerInfo = new WmsCreCarpHousingCustomerInfo();
        creCarpHousingCustomerInfo.setHouse_situation(house_situation);
        creCarpHousingCustomerInfo.setIs_house_loan(is_house_loan);
        creCarpHousingCustomerInfo.setWms_cre_credit_head_id(wmsCreCreditHeadId);
        wmsCreCarpHousingCustomerInfoDao.updateHouseSituationAndIsHouseLoan(creCarpHousingCustomerInfo);
        
        //流程信息更新
        if (isComOrZC.equals("1"))
        {
            result = "OK";
            WmsCreCreditHead searchBillSt = wmscrecreditheadDao_m.get(wmsCreCreditHeadId);
            String keyPass = "";
            if (searchBillSt.getBill_status().equals("A1")) {
            	carLoanWorkFlowService.startWorkFlow(String.valueOf(user.getUserId()),
                        String.valueOf(wmsCreCreditHeadId));
            } else {
            	 if (searchBillSt.getBill_status().equals("M1")) {
                     keyPass = "2";
                 } else if (searchBillSt.getBill_status().equals("L1")) {
                     keyPass = "5";
                 }
            	 /*---------------------�?��流程-------------------*/
                 WmsCarLoanWorkFlowVO wVo = new WmsCarLoanWorkFlowVO();
                 wVo.setUserId(String.valueOf(user.getUserId()));
                 wVo.setWms_cre_credit_head_id(wmsCreCreditHeadId);
                 wVo.setTaskId(taskId);
                 carLoanWorkFlowService.carLoanApprovalProcess(wVo, keyPass);
            }
            
        }
        
        WmsCreCreditLineCustomerChangeHead wmscrecreditlinecustomerchangehead; // 客户信息变更??
        // 贷款人信息LIST
        List<WmsCusCustomerHead> wmscuscustomerheadvo = new ArrayList<WmsCusCustomerHead>();
        List<WmsCreCreditLineCustomerChangeHead> wmscuscustomerchangeheadvoList = new ArrayList<WmsCreCreditLineCustomerChangeHead>();
        // 通过查询主键得到
        ArrayList<Integer> changedNochangeIDS = new ArrayList<>();
        if (!(clientId.equals("")))
        {
            for (String id : clientId.split(","))
            {
                String[] idarr = id.split("@@@");
                if (("change").equals(idarr[2]))
                {
                    WmsCreCreditLineCustomerChangeHead mcclcchVOByGet = wmscrecreditlinecustomerchangeheadDao_m.get(Integer.valueOf(idarr[0]));
                    wmscuscustomerchangeheadvoList.add(mcclcchVOByGet);
                    changedNochangeIDS.add(Integer.valueOf(idarr[0]));
                    Map<String, Object> updateIsMajorMap = new HashMap<String, Object>();
                    updateIsMajorMap.put("is_major", idarr[1]);
                    updateIsMajorMap.put("wms_cre_credit_line_customer_change_head_id", idarr[0]);
                    wmscrecreditlinecustomerchangeheadDao_m.updateIsMajor(updateIsMajorMap);
                }
                else if (("cus").equals(idarr[2]))
                {
                    WmsCusCustomerHead mcchvo = wmscuscustomerheadDao_m.get(Integer.valueOf(idarr[0]));
                    mcchvo.setEnable_flag(idarr[1]);
                    wmscuscustomerheadvo.add(mcchvo);
                }

            }
        }
        
        // 删除 未�?择的客户变更信息
        List<WmsCreCreditLineCustomerChangeHead> allChangeCusIn = wmscrecreditlinecustomerchangeheadDao_m.getWmsCreCreditLineCustomerChangeHeadListWithoutPaging(wmsCreCreditHeadId);
        ArrayList<Integer> dltIdsList = new ArrayList<>();
        for (int i = 0; i < allChangeCusIn.size(); i++)
        {
            boolean notInFlag = false;
            for (int j = 0; j < wmscuscustomerchangeheadvoList.size(); j++)
            {
                if (allChangeCusIn.get(i)
                                  .getWms_cre_credit_line_customer_change_head_id()
                                  .equals(wmscuscustomerchangeheadvoList.get(j)
                                                                        .getWms_cre_credit_line_customer_change_head_id()))
                {
                    notInFlag = true;
                    break;
                }
            }
            if (!notInFlag)
            {
                dltIdsList.add(allChangeCusIn.get(i).getWms_cre_credit_line_customer_change_head_id());
            }
        }
        Map<String, Object> deleteParamsMap = new HashMap<String, Object>();
        if (dltIdsList.size() > 0)
        {
            deleteParamsMap.put("dltIDArr", dltIdsList);
            wmscrecreditlinecustomerchangeheadDao_m.deleteByMap(deleteParamsMap);
            wmscrecustomerchangelinecontactDao_m.deleteByMap(deleteParamsMap);
            wmscrecustomerchangelinehouseinfoDao_m.deleteByMap(deleteParamsMap);
            wmsCreCustomerChangeLineCarpinfoDao.deleteByMap(deleteParamsMap);
            wmscrecustomerchangelineworkinfoDao_m.deleteByMap(deleteParamsMap);
            wmsCreCustomerChangeLineCompanyDao.deleteByMap(deleteParamsMap);
            wmsCusCustomerChangeChildDao.deleteByMap(deleteParamsMap);
        }
        // 删除�?��重复校验
        dltIdsList.clear();
        dltIdsList.add(wmsCreCreditHeadId);
        deleteParamsMap.clear();
        deleteParamsMap.put("dltIDArr", dltIdsList);
        wmsCreCreditHeadDiffPhoneDao.deleteByMap(deleteParamsMap);
        
        
        // 修改后的客户信息
        List<WmsCusCustomerHead> wmsCusCustomerHeadVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                     WmsCusCustomerHead.class);// 修改后list
        // 修改后的工作信息
        List<WmsCreCustomerChangeLineWorkinfo> WmsCreCustomerChangeLineWorkinfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                                                 WmsCreCustomerChangeLineWorkinfo.class);// 修改后list
        // housestr
        List<WmsHouseInfoVO> wmsHouseInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsHouseInfoVO.class);// 修改后list
        
        // 修改后的车产信息
        List<WmsCarInfoVO> wmsCarInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsCarInfoVO.class);
        
        // 修改后的公司信息
        List<WmsCompanyInfoVO> wmsCompanyInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsCompanyInfoVO.class);

        // 修改后的子女信息
        List<WmsChildInfoVO> wmsChildInfoVOList = JsonUtil.jsonArrayToList(modifyJsonCus, WmsChildInfoVO.class);
        boolean isRepeaded = false;

        for (int i = 0; i < wmscuscustomerheadvo.size(); i++)
        {
            boolean ishave = false;// 是否修改�?
            Integer org_custom_info_id = wmscuscustomerheadvo.get(i).getWms_cus_customer_id();
            wmscrecreditlinecustomerchangehead = new WmsCreCreditLineCustomerChangeHead();
            int j = 0;// 修改客户信息list的index
            if (wmsCusCustomerHeadVOList != null && wmsCusCustomerHeadVOList.size() > 0)
            {
                if (wmsCusCustomerHeadVOList.get(j).getWms_cus_customer_id() != null)
                {
                    for (j = wmsCusCustomerHeadVOList.size() - 1; j >= 0; j--)
                    {
                        if (wmsCusCustomerHeadVOList.get(j).getWms_cus_customer_id()
                                                    .compareTo(wmscuscustomerheadvo.get(i).getWms_cus_customer_id()) == 0)
                        {
                            ishave = true;
                            WmsCusCustomerHead pageVo = wmsCusCustomerHeadVOList.get(j);
                            copyWmscrecreditlinecustomerchangehead(wmscrecreditlinecustomerchangehead, pageVo,
                                                                   wmsCreCreditHeadId, user,
                                                                   wmscuscustomerheadvo.get(i));
                            break;
                        }
                    }
                }
                else
                {
                    break;
                }
            }

            if (!ishave)
            {
                copyWmscrecreditlinecustomerchangehead(wmscrecreditlinecustomerchangehead, wmscuscustomerheadvo.get(i),
                                                       wmsCreCreditHeadId, user, wmscuscustomerheadvo.get(i));
            }
            wmscrecreditlinecustomerchangeheadDao_m.saveWithKey(wmscrecreditlinecustomerchangehead);
            for (int k = 0; k < mcclc.size(); k++)
            {
                WmsCreCustomerChangeLineContact mccclcChange = mcclc.get(k);
                if (mccclcChange.getWms_cre_credit_line_customer_change_head_id()
                                .equals(wmscrecreditlinecustomerchangehead.getWms_cus_customer_head_id()))
                {
                    mccclcChange.setWms_cre_credit_line_customer_change_head_id(wmscrecreditlinecustomerchangehead.getWms_cre_credit_line_customer_change_head_id());
                    mcclc.set(k, mccclcChange);
                }
            }
            if (isComOrZC.equals("1"))
            {
                // �?��身份证号重复，并将重复记录存入数据库
                isRepeaded = isRepeaded || this.compareSfz(wmscrecreditlinecustomerchangehead, user);
                // 客户电话与联系人电话重复记录集合
                isRepeaded = isRepeaded || this.compareKhAndLxr(wmscrecreditlinecustomerchangehead, user);
                // 客户与客户电话重�?
                isRepeaded = isRepeaded || this.compareKhAndKh(wmscrecreditlinecustomerchangehead, user);
            }
            Integer customer_id = wmscrecreditlinecustomerchangehead.getWms_cre_credit_line_customer_change_head_id();
            if (ishave)
            {
                // 工作信息
                WmsCreCustomerChangeLineWorkinfo vo = WmsCreCustomerChangeLineWorkinfoVOList.get(j);// 工作信息
                vo.setWms_cre_credit_line_customer_change_head_id(customer_id);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                vo.setEnable_flag("1");
                wmscrecustomerchangelineworkinfoDao_m.addNewRecord(vo);

                // 房产信息
                for (Integer m = wmsHouseInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsHouseInfoVO housevo = wmsHouseInfoVOList.get(m);
                    if (org_custom_info_id.compareTo(housevo.getWms_cus_customer_id()) == 0)
                    {
                        String housestr = housevo.getHousestr();
                        List<WmsCreCustomerChangeLineHouseinfo> houseList = JsonUtil.jsonArrayToList(housestr,
                                                                                                     WmsCreCustomerChangeLineHouseinfo.class);
                        for (WmsCreCustomerChangeLineHouseinfo housevo_p : houseList)
                        {
                            boolean hosFlag = false;
                            if (housevo_p.getEnable_flag() != null && housevo_p.getEnable_flag().equals(mcclhid))
                            {
                                hosFlag = true;
                            }
                            housevo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            housevo_p.setCreate_user_id(user.getUserId());
                            housevo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            housevo_p.setLast_update_user_id(user.getUserId());
                            housevo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            housevo_p.setEnable_flag("1");
                            if (hosFlag)
                            {
                                wmscrecustomerchangelinehouseinfoDao_m.addNewRecordReKey(housevo_p);
                                isvcchid = housevo_p.getWms_cre_customer_change_line_houseinfo_id();
                                
                                // 删除抵押房产信息
                                Map<String, Object> hchDeltMap = new HashMap<>();
                                ArrayList<Integer> hchDeltList = new ArrayList<>();
                                hchDeltList.add(wmsCreCreditHeadId);
                                hchDeltMap.put("dltIDArr", hchDeltList);
                                wmsCreCarpHousingCustomerInfoDao.deleteByMap(hchDeltMap);
                                
                                // 抵押房产信息
                                WmsCreCarpHousingCustomerInfo wmsCreCarpHousingCustomerInfo = new WmsCreCarpHousingCustomerInfo();
                                wmsCreCarpHousingCustomerInfo.setWms_cre_credit_head_id(wmsCreCreditHeadId);
                                wmsCreCarpHousingCustomerInfo.setWms_cre_customer_change_line_houseinfo_id(isvcchid);
                                wmsCreCarpHousingCustomerInfo.setHouse_situation(house_situation);
                                wmsCreCarpHousingCustomerInfo.setHouse_type(housevo_p.getHouse_type());
                                wmsCreCarpHousingCustomerInfo.setBuilt_area(String.valueOf(housevo_p.getHouse_building_area()));
                                wmsCreCarpHousingCustomerInfo.setIs_house_loan(is_house_loan);
                                wmsCreCarpHousingCustomerInfo.setHouse_address_province(housevo_p.getHouse_address_province());
                                wmsCreCarpHousingCustomerInfo.setHouse_address_city(housevo_p.getHouse_address_city());
                                wmsCreCarpHousingCustomerInfo.setHouse_address_area(housevo_p.getHouse_address_district());
                                wmsCreCarpHousingCustomerInfo.setHouse_address_other(housevo_p.getHouse_address_more());
                                wmsCreCarpHousingCustomerInfo.setCreate_user_id(user.getUserId());
                                wmsCreCarpHousingCustomerInfo.setCreate_timestamp(sysTime);
                                wmsCreCarpHousingCustomerInfo.setEnable_flag("1");
                                //插入车贷抵押房产信息表
                                wmsCreCarpHousingCustomerInfoDao.save(wmsCreCarpHousingCustomerInfo);
                             
                            }
                            else
                                wmscrecustomerchangelinehouseinfoDao_m.addNewRecord(housevo_p);
                        }
                        break;
                    }
                }
                
                // 车产信息
                for (Integer m = wmsCarInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsCarInfoVO carvo = wmsCarInfoVOList.get(m);
                    if (org_custom_info_id.compareTo(carvo.getWms_cre_credit_line_customer_change_head_id()) == 0)
                    {
                        String carstr = carvo.getCarstr();
                        List<WmsCreCustomerChangeLineCarpinfo> carList = JsonUtil.jsonArrayToList(carstr, WmsCreCustomerChangeLineCarpinfo.class);
                        for (WmsCreCustomerChangeLineCarpinfo carvo_p : carList)
                        {
                            boolean carFlag = false;
                            if (carvo_p.getEnable_flag() != null && carvo_p.getEnable_flag().equals(mcclcid))
                            {
                                carFlag = true;
                            }
                            carvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            carvo_p.setCreate_user_id(user.getUserId());
                            carvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            carvo_p.setLast_update_user_id(user.getUserId());
                            carvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            carvo_p.setEnable_flag("1");
                            if (carFlag)
                            {
                            	wmscrecustomerchangelinecarpinfoDao_m.addNewRecordReKey(carvo_p);
                                isvcccid = carvo_p.getWms_cre_customer_change_line_carpinfo_id();
                                
                                // 删除抵押车产信息
                                Map<String, Object> hchDeltMap = new HashMap<>();
                                ArrayList<Integer> hchDeltList = new ArrayList<>();
                                hchDeltList.add(wmsCreCreditHeadId);
                                hchDeltMap.put("dltIDArr", hchDeltList);
                                wmsCreCarpCarsCustomerInfoDao.deleteByMap(hchDeltMap);
                                
                                //抵押车产信息
                                WmsCreCarpCarsCustomerInfo carpCarsCustomerInfo = new WmsCreCarpCarsCustomerInfo();
                                carpCarsCustomerInfo.setWms_cre_credit_head_id(wmsCreCreditHeadId);
                                carpCarsCustomerInfo.setWms_cre_customer_change_line_carpinfo_id(isvcccid);
                                carpCarsCustomerInfo.setCar_name(carvo_p.getCar_name());
                                carpCarsCustomerInfo.setCar_no(carvo_p.getCar_no());
                                carpCarsCustomerInfo.setCar_purchase_time(carvo_p.getCar_purchase_time());
                                carpCarsCustomerInfo.setCreate_user_id(carvo_p.getCreate_user_id());
                                carpCarsCustomerInfo.setCreate_timestamp(carvo_p.getCreate_timestamp());
                                carpCarsCustomerInfo.setEnable_flag("1");
                                //插入抵押房产信息表
                                wmsCreCarpCarsCustomerInfoDao.save(carpCarsCustomerInfo);
                                
                                
                            }
                            else
                            	wmscrecustomerchangelinecarpinfoDao_m.addNewRecord(carvo_p);
                        }
                        break;
                    }
                }

                // 公司信息
                for (Integer m = wmsCompanyInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsCompanyInfoVO companyvo = wmsCompanyInfoVOList.get(m);
                    if (org_custom_info_id.compareTo(companyvo.getWms_cus_customer_id()) == 0)
                    {
                        String companystr = companyvo.getCompanystr();
                        List<WmsCreCustomerChangeLineCompany> companyList = JsonUtil.jsonArrayToList(companystr,
                                                                                                     WmsCreCustomerChangeLineCompany.class);
                        for (WmsCreCustomerChangeLineCompany companyvo_p : companyList)
                        {
                            companyvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            companyvo_p.setCreate_user_id(user.getUserId());
                            companyvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            companyvo_p.setLast_update_user_id(user.getUserId());
                            companyvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            companyvo_p.setEnable_flag("1");
                            wmsCreCustomerChangeLineCompanyDao.save(companyvo_p);
                        }
                        break;
                    }
                }
                // 子女
                for (Integer m = wmsChildInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsChildInfoVO childvo = wmsChildInfoVOList.get(m);
                    if (org_custom_info_id.compareTo(childvo.getWms_cus_customer_id()) == 0)
                    {
                        String childstr = childvo.getCusChild();
                        List<WmsCusCustomerChangeChild> childList = JsonUtil.jsonArrayToList(childstr,
                                                                                             WmsCusCustomerChangeChild.class);
                        for (WmsCusCustomerChangeChild childvo_p : childList)
                        {
                            childvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            childvo_p.setCreate_user_id(user.getUserId());
                            childvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            childvo_p.setLast_update_user_id(user.getUserId());
                            childvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            childvo_p.setEnable_flag("1");
                            wmsCusCustomerChangeChildDao.save(childvo_p);
                        }
                        break;
                    }
                }
            }
            else
            {
                // 工作信息
                WmsCusCustomerLineWorkinfo workinfoQry = new WmsCusCustomerLineWorkinfo();
                workinfoQry.setWms_cus_customer_id(org_custom_info_id);
                workinfoQry.setEnable_flag("1");
                List<WmsCusCustomerLineWorkinfo> list = wmsCusCustomerLineWorkinfoDao_m.getSingleTableListByAndMethod(workinfoQry);
                if (list != null && list.size() > 0)
                {
                    WmsCusCustomerLineWorkinfo customerLineWork = list.get(0);
                    WmsCreCustomerChangeLineWorkinfo customerChangeLineWork = new WmsCreCustomerChangeLineWorkinfo();
                    copyWmsCreCustomerChangeLineWorkinfo(customerLineWork, customerChangeLineWork);
                    customerChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                    customerChangeLineWork.setCreate_user_id(user.getUserId());
                    customerChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                    customerChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                    wmscrecustomerchangelineworkinfoDao_m.addNewRecord(customerChangeLineWork);
                }

                // 房产信息
                WmsCusCustomerLineHouseinfo qryinfo = new WmsCusCustomerLineHouseinfo();
                qryinfo.setWms_cus_customer_id(org_custom_info_id);
                qryinfo.setEnable_flag("1");
                List<WmsCusCustomerLineHouseinfo> list2 = wmsCusCustomerLineHouseinfoDao_m.getSingleTableListByAndMethod(qryinfo);
                if (list2 != null)
                {
                    for (int k = 0; k < list2.size(); k++)
                    {
                        WmsCusCustomerLineHouseinfo houserWork = list2.get(k);
                        boolean hosFlag = false;
                        if (String.valueOf(houserWork.getWms_cus_customer_line_houseinfo_id()).equals(mcclhid))
                        {
                            hosFlag = true;
                        }
                        WmsCreCustomerChangeLineHouseinfo houserWorkChangeLineWork = new WmsCreCustomerChangeLineHouseinfo();
                        copyWmsCreCustomerChangeLineHouseinfo(houserWork, houserWorkChangeLineWork);
                        houserWorkChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                        houserWorkChangeLineWork.setCreate_user_id(user.getUserId());
                        houserWorkChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                        houserWorkChangeLineWork.setLast_update_user_id(user.getUserId());
                        houserWorkChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                        houserWorkChangeLineWork.setEnable_flag("1");
                        if (hosFlag)
                        {
                            wmscrecustomerchangelinehouseinfoDao_m.addNewRecordReKey(houserWorkChangeLineWork);
                            isvcchid = houserWorkChangeLineWork.getWms_cre_customer_change_line_houseinfo_id();
                            
                            // 删除抵押房产信息
                            Map<String, Object> hchDeltMap = new HashMap<>();
                            ArrayList<Integer> hchDeltList = new ArrayList<>();
                            hchDeltList.add(wmsCreCreditHeadId);
                            hchDeltMap.put("dltIDArr", hchDeltList);
                            wmsCreCarpHousingCustomerInfoDao.deleteByMap(hchDeltMap);
                            
                            // 抵押房产信息
                            WmsCreCarpHousingCustomerInfo wmsCreCarpHousingCustomerInfo = new WmsCreCarpHousingCustomerInfo();
                            wmsCreCarpHousingCustomerInfo.setWms_cre_credit_head_id(wmsCreCreditHeadId);
                            wmsCreCarpHousingCustomerInfo.setWms_cre_customer_change_line_houseinfo_id(isvcchid);
                            wmsCreCarpHousingCustomerInfo.setHouse_situation(house_situation);
                            wmsCreCarpHousingCustomerInfo.setHouse_type(houserWorkChangeLineWork.getHouse_type());
                            wmsCreCarpHousingCustomerInfo.setBuilt_area(String.valueOf(houserWorkChangeLineWork.getHouse_building_area()));
                            wmsCreCarpHousingCustomerInfo.setIs_house_loan(is_house_loan);
                            wmsCreCarpHousingCustomerInfo.setHouse_address_province(houserWorkChangeLineWork.getHouse_address_province());
                            wmsCreCarpHousingCustomerInfo.setHouse_address_city(houserWorkChangeLineWork.getHouse_address_city());
                            wmsCreCarpHousingCustomerInfo.setHouse_address_area(houserWorkChangeLineWork.getHouse_address_district());
                            wmsCreCarpHousingCustomerInfo.setHouse_address_other(houserWorkChangeLineWork.getHouse_address_more());
                            wmsCreCarpHousingCustomerInfo.setCreate_user_id(user.getUserId());
                            wmsCreCarpHousingCustomerInfo.setCreate_timestamp(sysTime);
                            wmsCreCarpHousingCustomerInfo.setEnable_flag("1");
                            //插入车贷抵押房产信息表
                            wmsCreCarpHousingCustomerInfoDao.save(wmsCreCarpHousingCustomerInfo);
                        }
                        else
                            wmscrecustomerchangelinehouseinfoDao_m.addNewRecord(houserWorkChangeLineWork);
                    }
                }
                
                // 车产信息
                WmsCusCustomerLineCarpinfo carpinfo = new WmsCusCustomerLineCarpinfo();
                carpinfo.setWms_cus_customer_id(org_custom_info_id);
                carpinfo.setEnable_flag("1");
                List<WmsCusCustomerLineCarpinfo> listcar = wmsCusCustomerLineCarpinfoDao.getListByEntity(carpinfo);
                if(listcar != null) {
                	for(int m = 0; m < listcar.size(); m++) {
                		WmsCusCustomerLineCarpinfo lineCarpinfo = listcar.get(m);
                		boolean carFlag = false;
                		if (String.valueOf(lineCarpinfo.getWms_cus_customer_line_carpinfo_id()).equals(mcclcid)) {
                			carFlag = true;
                        }
                		WmsCreCustomerChangeLineCarpinfo changeLineCarpinfo = new WmsCreCustomerChangeLineCarpinfo();
                		copyWmsCreCustomerChangeLineCarinfo(lineCarpinfo, changeLineCarpinfo);
                		changeLineCarpinfo.setWms_cre_credit_line_customer_change_head_id(customer_id);
                		changeLineCarpinfo.setCreate_user_id(user.getUserId());
                		changeLineCarpinfo.setCreate_timestamp(sysTime);
                		changeLineCarpinfo.setLast_update_user_id(user.getUserId());
                		changeLineCarpinfo.setLast_update_timestamp(sysTime);
                		changeLineCarpinfo.setEnable_flag("1");
                		
                		if(carFlag) {
                			wmscrecustomerchangelinecarpinfoDao_m.addNewRecordReKey(changeLineCarpinfo);
                            isvcccid = changeLineCarpinfo.getWms_cre_customer_change_line_carpinfo_id();
                            
                         // 删除抵押车产信息
                            Map<String, Object> hchDeltMap = new HashMap<>();
                            ArrayList<Integer> hchDeltList = new ArrayList<>();
                            hchDeltList.add(wmsCreCreditHeadId);
                            hchDeltMap.put("dltIDArr", hchDeltList);
                            wmsCreCarpCarsCustomerInfoDao.deleteByMap(hchDeltMap);
                            
                          //抵押车产信息
                            WmsCreCarpCarsCustomerInfo carpCarsCustomerInfo = new WmsCreCarpCarsCustomerInfo();
                            carpCarsCustomerInfo.setWms_cre_credit_head_id(wmsCreCreditHeadId);
                            carpCarsCustomerInfo.setWms_cre_customer_change_line_carpinfo_id(isvcccid);
                            carpCarsCustomerInfo.setCar_name(changeLineCarpinfo.getCar_name());
                            carpCarsCustomerInfo.setCar_no(changeLineCarpinfo.getCar_no());
                            carpCarsCustomerInfo.setCar_purchase_time(changeLineCarpinfo.getCar_purchase_time());
                            carpCarsCustomerInfo.setCreate_user_id(changeLineCarpinfo.getCreate_user_id());
                            carpCarsCustomerInfo.setCreate_timestamp(changeLineCarpinfo.getCreate_timestamp());
                            carpCarsCustomerInfo.setEnable_flag("1");
                            //插入抵押房产信息表
                            wmsCreCarpCarsCustomerInfoDao.save(carpCarsCustomerInfo);
                        }
                        else
                        	wmscrecustomerchangelinecarpinfoDao_m.addNewRecord(changeLineCarpinfo);
                	}
                }

                // 公司信息
                WmsCusCustomerLineCompany qryinfo3 = new WmsCusCustomerLineCompany();
                qryinfo3.setWms_cus_customer_id(org_custom_info_id);
                qryinfo3.setEnable_flag("1");
                List<WmsCusCustomerLineCompany> list3 = wmsCusCustomerLineCompanyDao.getSingleTableListByAndMethod(qryinfo3);
                if (list3 != null)
                {
                    for (int k = 0; k < list3.size(); k++)
                    {
                        WmsCusCustomerLineCompany companyWork = list3.get(k);
                        WmsCreCustomerChangeLineCompany companyWorkChangeLineWork = new WmsCreCustomerChangeLineCompany();
                        copyWmsCreCustomerChangeLinCompanyinfo(companyWork, companyWorkChangeLineWork);
                        companyWorkChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                        companyWorkChangeLineWork.setCreate_user_id(user.getUserId());
                        companyWorkChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                        companyWorkChangeLineWork.setLast_update_user_id(user.getUserId());
                        companyWorkChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                        companyWorkChangeLineWork.setEnable_flag("1");
                        wmsCreCustomerChangeLineCompanyDao.save(companyWorkChangeLineWork);
                    }
                }

                // 子女信息
                WmsCusCustomerChild qryinfo4 = new WmsCusCustomerChild();
                qryinfo4.setWms_cus_customer_id(org_custom_info_id);
                qryinfo4.setEnable_flag("1");
                List<WmsCusCustomerChild> list4 = wmsCusCustomerChildDao.getSingleTableListByAndMethod(qryinfo4);
                if (list4 != null)
                {
                    for (int k = 0; k < list4.size(); k++)
                    {
                        WmsCusCustomerChild childWork = list4.get(k);
                        WmsCusCustomerChangeChild childWorkChangeLineWork = new WmsCusCustomerChangeChild();
                        copyWmsCreCustomerChangeLinChildinfo(childWork, childWorkChangeLineWork);
                        childWorkChangeLineWork.setWms_cre_credit_line_customer_change_head_id(customer_id);
                        childWorkChangeLineWork.setCreate_user_id(user.getUserId());
                        childWorkChangeLineWork.setCreate_timestamp(new Timestamp(new Date().getTime()));
                        childWorkChangeLineWork.setLast_update_user_id(user.getUserId());
                        childWorkChangeLineWork.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                        childWorkChangeLineWork.setEnable_flag("1");
                        wmsCusCustomerChangeChildDao.save(childWorkChangeLineWork);
                    }
                }
            }
        }

        // 修改后的客户信息
        List<WmsCreCreditLineCustomerChangeHead> wmsCusCustomerHeadChangeVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                                           WmsCreCreditLineCustomerChangeHead.class);// 修改后list
        // 修改后的工作信息
        List<WmsCreCustomerChangeLineWorkinfo> WmsCreCustomerChangeLineWorkinfoVOList2 = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                                                  WmsCreCustomerChangeLineWorkinfo.class);// 修改后list

        // housestr
        List<WmsHouseChangeInfoVO> wmsHouseInfoChangeVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                       WmsHouseChangeInfoVO.class);// 修改后list
        // 修改后的公司信息
        List<WmsCompanyChangeInfoVO> wmsCompanyInfoChangeVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                           WmsCompanyChangeInfoVO.class);

        // 修改后的子女信息
        List<WmsChildChangeInfoVO> wmsChildInfoChangeVOList = JsonUtil.jsonArrayToList(modifyJsonCus,
                                                                                       WmsChildChangeInfoVO.class);

        for (int i = 0; i < wmscuscustomerchangeheadvoList.size(); i++)
        {
            boolean ishave = false;// 是否修改�?
            Integer org_custom_info_change_id = wmscuscustomerchangeheadvoList.get(i)
                                                                              .getWms_cre_credit_line_customer_change_head_id();
            wmscrecreditlinecustomerchangehead = wmscuscustomerchangeheadvoList.get(i);
            boolean isMFlag = false;
            int j = 0;// 修改客户信息list的index
            if (wmsCusCustomerHeadChangeVOList != null && wmsCusCustomerHeadChangeVOList.size() > 0)
            {
                for (j = wmsCusCustomerHeadChangeVOList.size() - 1; j >= 0; j--)
                {
                    if (wmsCusCustomerHeadChangeVOList.get(j).getWms_cre_credit_line_customer_change_head_id() != null)
                    {
                        if (wmsCusCustomerHeadChangeVOList.get(j)
                                                          .getWms_cre_credit_line_customer_change_head_id()
                                                          .compareTo(wmscuscustomerchangeheadvoList.get(i)
                                                                                                   .getWms_cre_credit_line_customer_change_head_id()) == 0)
                        {
                            ishave = true;
                            ArrayList<Integer> dltOneByOCICIDList = new ArrayList<>();
                            dltOneByOCICIDList.add(org_custom_info_change_id);
                            Map<String, Object> dltOneByOCICIDMap = new HashMap<String, Object>();
                            dltOneByOCICIDMap.put("dltIDArr", dltOneByOCICIDList);
                            WmsCreCreditLineCustomerChangeHead mcclcchVOReadyToDelete = wmscrecreditlinecustomerchangeheadDao_m.get(org_custom_info_change_id);
                            wmscrecreditlinecustomerchangeheadDao_m.deleteByMap(dltOneByOCICIDMap);
                            wmscrecustomerchangelinehouseinfoDao_m.deleteByMap(dltOneByOCICIDMap);
                            wmsCreCustomerChangeLineCarpinfoDao.deleteByMap(dltOneByOCICIDMap);
                            wmscrecustomerchangelineworkinfoDao_m.deleteByMap(dltOneByOCICIDMap);
                            wmsCreCustomerChangeLineCompanyDao.deleteByMap(dltOneByOCICIDMap);
                            wmsCusCustomerChangeChildDao.deleteByMap(dltOneByOCICIDMap);
                            WmsCreCreditLineCustomerChangeHead newInsertVO = wmsCusCustomerHeadChangeVOList.get(j);
                            newInsertVO.setWms_cre_credit_head_id(mcclcchVOReadyToDelete.getWms_cre_credit_head_id());
                            newInsertVO.setWms_cus_customer_head_id(mcclcchVOReadyToDelete.getWms_cus_customer_head_id());
                            newInsertVO.setCustomer_code(mcclcchVOReadyToDelete.getCustomer_code());
                            newInsertVO.setCreate_user_id(mcclcchVOReadyToDelete.getCreate_user_id());
                            newInsertVO.setCreate_user_name(mcclcchVOReadyToDelete.getCreate_user_name());
                            newInsertVO.setCreate_timestamp(mcclcchVOReadyToDelete.getCreate_timestamp());
                            newInsertVO.setLast_update_user_id(user.getUserId());
                            newInsertVO.setLast_update_timestamp(sysTime);
                            newInsertVO.setEnable_flag("1");
                            newInsertVO.setIs_major(mcclcchVOReadyToDelete.getIs_major());
                            if (mcclcchVOReadyToDelete.getIs_major().equals("1"))
                            {
                                isMFlag = true;
                            }
                            wmscrecreditlinecustomerchangeheadDao_m.saveWithKey(newInsertVO);
                            break;
                        }
                    }
                    else
                    {
                        break;
                    }
                }
            }

            if (isComOrZC.equals("1"))
            {
                // �?��身份证号重复，并将重复记录存入数据库
                isRepeaded = isRepeaded || this.compareSfz(wmscrecreditlinecustomerchangehead, user);
                // 客户电话与联系人电话重复记录集合
                isRepeaded = isRepeaded || this.compareKhAndLxr(wmscrecreditlinecustomerchangehead, user);
                // 客户与客户电话重�?
                isRepeaded = isRepeaded || this.compareKhAndKh(wmscrecreditlinecustomerchangehead, user);
            }

            Integer customer_id = org_custom_info_change_id;
            if (ishave)
            {
                // 工作信息
                WmsCreCustomerChangeLineWorkinfo vo = WmsCreCustomerChangeLineWorkinfoVOList2.get(j);// 工作信息
                vo.setWms_cre_credit_line_customer_change_head_id(customer_id);
                vo.setCreate_user_id(user.getUserId());
                vo.setCreate_timestamp(new Timestamp(new Date().getTime()));
                vo.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                vo.setEnable_flag("1");
                wmscrecustomerchangelineworkinfoDao_m.addNewRecord(vo);

                // 房产信息
                for (Integer m = wmsHouseInfoChangeVOList.size() - 1; m >= 0; --m)
                {
                    WmsHouseChangeInfoVO housechangevo = wmsHouseInfoChangeVOList.get(m);
                    if (org_custom_info_change_id.compareTo(housechangevo.getWms_cre_credit_line_customer_change_head_id()) == 0)
                    {
                        String housestr = housechangevo.getHousestr();
                        List<WmsCreCustomerChangeLineHouseinfo> houseList = JsonUtil.jsonArrayToList(housestr,
                                                                                                     WmsCreCustomerChangeLineHouseinfo.class);
                        for (WmsCreCustomerChangeLineHouseinfo housevo_p : houseList)
                        {
                            boolean hosFlag = false;
                            if (housevo_p.getEnable_flag() != null && housevo_p.getEnable_flag().equals(mcclhid))
                            {
                                hosFlag = true;
                            }
                            housevo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            housevo_p.setCreate_user_id(user.getUserId());
                            housevo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            housevo_p.setLast_update_user_id(user.getUserId());
                            housevo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            housevo_p.setEnable_flag("1");
                            wmscrecustomerchangelinehouseinfoDao_m.addNewRecordReKey(housevo_p);
                            if (isMFlag && hosFlag)
                            {
                                isvcchid = housevo_p.getWms_cre_customer_change_line_houseinfo_id();
                                
                                // 删除抵押房产信息
                                Map<String, Object> hchDeltMap = new HashMap<>();
                                ArrayList<Integer> hchDeltList = new ArrayList<>();
                                hchDeltList.add(wmsCreCreditHeadId);
                                hchDeltMap.put("dltIDArr", hchDeltList);
                                wmsCreCarpHousingCustomerInfoDao.deleteByMap(hchDeltMap);
                                
                                // 抵押房产信息
                                WmsCreCarpHousingCustomerInfo wmsCreCarpHousingCustomerInfo = new WmsCreCarpHousingCustomerInfo();
                                wmsCreCarpHousingCustomerInfo.setWms_cre_credit_head_id(wmsCreCreditHeadId);
                                wmsCreCarpHousingCustomerInfo.setWms_cre_customer_change_line_houseinfo_id(isvcchid);
                                wmsCreCarpHousingCustomerInfo.setHouse_situation(house_situation);
                                wmsCreCarpHousingCustomerInfo.setHouse_type(housevo_p.getHouse_type());
                                wmsCreCarpHousingCustomerInfo.setBuilt_area(String.valueOf(housevo_p.getHouse_building_area()));
                                wmsCreCarpHousingCustomerInfo.setIs_house_loan(is_house_loan);
                                wmsCreCarpHousingCustomerInfo.setHouse_address_province(housevo_p.getHouse_address_province());
                                wmsCreCarpHousingCustomerInfo.setHouse_address_city(housevo_p.getHouse_address_city());
                                wmsCreCarpHousingCustomerInfo.setHouse_address_area(housevo_p.getHouse_address_district());
                                wmsCreCarpHousingCustomerInfo.setHouse_address_other(housevo_p.getHouse_address_more());
                                wmsCreCarpHousingCustomerInfo.setCreate_user_id(user.getUserId());
                                wmsCreCarpHousingCustomerInfo.setCreate_timestamp(sysTime);
                                wmsCreCarpHousingCustomerInfo.setEnable_flag("1");
                                //插入车贷抵押房产信息表
                                wmsCreCarpHousingCustomerInfoDao.save(wmsCreCarpHousingCustomerInfo);
                            }
                        }
                        break;
                    }
                }
                
                // 车产信息
                for (Integer m = wmsCarInfoVOList.size() - 1; m >= 0; --m)
                {
                    WmsCarInfoVO carvo = wmsCarInfoVOList.get(m);
                    if (org_custom_info_change_id.compareTo(carvo.getWms_cre_credit_line_customer_change_head_id()) == 0)
                    {
                        String carstr = carvo.getCarstr();
                        List<WmsCreCustomerChangeLineCarpinfo> carList = JsonUtil.jsonArrayToList(carstr, WmsCreCustomerChangeLineCarpinfo.class);
                        for (WmsCreCustomerChangeLineCarpinfo carvo_p : carList)
                        {
                            boolean carFlag = false;
                            if (carvo_p.getEnable_flag() != null && carvo_p.getEnable_flag().equals(mcclcid))
                            {
                                carFlag = true;
                            }
                            carvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            carvo_p.setCreate_user_id(user.getUserId());
                            carvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            carvo_p.setLast_update_user_id(user.getUserId());
                            carvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            carvo_p.setEnable_flag("1");
                            if (carFlag)
                            {
                            	wmscrecustomerchangelinecarpinfoDao_m.addNewRecordReKey(carvo_p);
                                isvcccid = carvo_p.getWms_cre_customer_change_line_carpinfo_id();
                                
                             // 删除抵押车产信息
                                Map<String, Object> hchDeltMap = new HashMap<>();
                                ArrayList<Integer> hchDeltList = new ArrayList<>();
                                hchDeltList.add(wmsCreCreditHeadId);
                                hchDeltMap.put("dltIDArr", hchDeltList);
                                wmsCreCarpCarsCustomerInfoDao.deleteByMap(hchDeltMap);
                                
                                //抵押车产信息
                                WmsCreCarpCarsCustomerInfo carpCarsCustomerInfo = new WmsCreCarpCarsCustomerInfo();
                                carpCarsCustomerInfo.setWms_cre_credit_head_id(wmsCreCreditHeadId);
                                carpCarsCustomerInfo.setWms_cre_customer_change_line_carpinfo_id(isvcccid);
                                carpCarsCustomerInfo.setCar_name(carvo_p.getCar_name());
                                carpCarsCustomerInfo.setCar_no(carvo_p.getCar_no());
                                carpCarsCustomerInfo.setCar_purchase_time(carvo_p.getCar_purchase_time());
                                carpCarsCustomerInfo.setCreate_user_id(carvo_p.getCreate_user_id());
                                carpCarsCustomerInfo.setCreate_timestamp(carvo_p.getCreate_timestamp());
                                carpCarsCustomerInfo.setEnable_flag("1");
                                //插入抵押房产信息表
                                wmsCreCarpCarsCustomerInfoDao.save(carpCarsCustomerInfo);
                            }
                            else
                            	wmscrecustomerchangelinecarpinfoDao_m.addNewRecord(carvo_p);
                        }
                        break;
                    }
                }
                
                // 公司信息
                for (Integer m = wmsCompanyInfoChangeVOList.size() - 1; m >= 0; --m)
                {
                    WmsCompanyChangeInfoVO companychangevo = wmsCompanyInfoChangeVOList.get(m);
                    if (org_custom_info_change_id.compareTo(companychangevo.getWms_cre_credit_line_customer_change_head_id()) == 0)
                    {
                        String companystr = companychangevo.getCompanystr();
                        List<WmsCreCustomerChangeLineCompany> companyList = JsonUtil.jsonArrayToList(companystr,
                                                                                                     WmsCreCustomerChangeLineCompany.class);
                        for (WmsCreCustomerChangeLineCompany companyvo_p : companyList)
                        {
                            companyvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            companyvo_p.setCreate_user_id(user.getUserId());
                            companyvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            companyvo_p.setLast_update_user_id(user.getUserId());
                            companyvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            companyvo_p.setEnable_flag("1");
                            wmsCreCustomerChangeLineCompanyDao.save(companyvo_p);
                        }
                        break;
                    }
                }
                // 子女
                for (Integer m = wmsChildInfoChangeVOList.size() - 1; m >= 0; --m)
                {
                    WmsChildChangeInfoVO childchangevo = wmsChildInfoChangeVOList.get(m);
                    if (org_custom_info_change_id.compareTo(childchangevo.getWms_cre_credit_line_customer_change_head_id()) == 0)
                    {
                        String childstr = childchangevo.getCusChild();
                        List<WmsCusCustomerChangeChild> childList = JsonUtil.jsonArrayToList(childstr,
                                                                                             WmsCusCustomerChangeChild.class);
                        for (WmsCusCustomerChangeChild childvo_p : childList)
                        {
                            childvo_p.setWms_cre_credit_line_customer_change_head_id(customer_id);
                            childvo_p.setCreate_user_id(user.getUserId());
                            childvo_p.setCreate_timestamp(new Timestamp(new Date().getTime()));
                            childvo_p.setLast_update_user_id(user.getUserId());
                            childvo_p.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                            childvo_p.setEnable_flag("1");
                            wmsCusCustomerChangeChildDao.save(childvo_p);
                        }
                        break;
                    }
                }
            }
        }

        if (isvcchid == 0)
        {
            if (mcclhid != null && !mcclhid.equals(""))
            {
                if (mcclhid.indexOf("change") != -1)
                {
                    mcclhid = mcclhid.replaceAll("change", "");
                }
            }
            try
            {
                isvcchid = Integer.parseInt(mcclhid);
            }
            catch (Exception e)
            {
                isvcchid = 0;
            }
        }

        /*----------------------------------------------wms_cre_credit_line_customer_change_head 客户信息变更�?end----------------------------------------------*/
        /*----------------------------------------------wms_cre_customer_change_line_contact 客户联系人表 begin----------------------------------------------*/
        for (int i = 0; i < mcclc.size(); i++)
        {
            WmsCreCustomerChangeLineContact everyContactVO = mcclc.get(i);
            mcclc.get(i).setWms_cre_credit_head_id(wmsCreCreditHeadId);
            if (everyContactVO.getWms_cre_customer_change_line_contact_id() != null
                && everyContactVO.getWms_cre_customer_change_line_contact_id() != 0)
            {
                wmscrecustomerchangelinecontactDao_m.updateChangeContantBF(everyContactVO);
            }
            else
            {
                wmscrecustomerchangelinecontactDao_m.addNewRecord(mcclc.get(i));
            }
            if (isComOrZC.equals("1"))
            {
                // 联系人电话重复记录集�?
                isRepeaded = isRepeaded || this.compareLxrAndLxr(mcclc.get(i), user);
                // 联系人与客户电话重复
                isRepeaded = isRepeaded || this.compareLxrAndKh(mcclc.get(i), user);
            }
        }
        if (isRepeaded)
        {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("is_link_repeat", "1");// 联系方式是否重复
            params.put("wms_cre_credit_head_id", wmsCreCreditHeadId);
            wmscrecreditheadDao_m.updateRecord(params);
        }
        /*----------------------------------------------wms_cre_customer_change_line_contact 客户联系人表 end----------------------------------------------*/

        /*ArrayList<String> data_typeList = new ArrayList<>();
        for (int i = 0; i < attachment.size(); i++)
        {
            if (!data_typeList.contains(attachment.get(i).getData_type()))
            {
                data_typeList.add(attachment.get(i).getData_type());
            }
        }
        // 删除�?��附件
        for (int i = 0; i < data_typeList.size(); i++)
        {
            Map<String, Object> deletAtt = new HashMap<>();
            deletAtt.put("data_type", data_typeList.get(i));
            deletAtt.put("wms_cre_credit_head_id", wmsCreCreditHeadId);
            wmsCreCarpHousingAttDao.deleteRecords(deletAtt);
        }*/

        Map<String, Object> deletAtt = new HashMap<>();
        deletAtt.put("data_type", '1');
        deletAtt.put("wms_cre_credit_head_id", wmsCreCreditHeadId);
        wmsCreCarpHousingAttDao.deleteRecords(deletAtt);
        //附件信息
        for (int i = 0; i < attachment.size(); i++)
        {
        	WmsCreCarpHousingAtt mplm = attachment.get(i);
        	mplm.setData_type("1");// 1为贷款申请
            // mplm.setWms_cre_credit_head_id(Integer.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()));
            mplm.setWms_cre_credit_head_id(mcch.getWms_cre_credit_head_id());
            mplm.setCreate_user_id(user.getUserId()); // 创建人id
            mplm.setCreate_user_name(user.getRealname());// 创建人名??
            mplm.setCreate_timestamp(sysTime);// 创建时间
            mplm.setEnable_flag("1");// 是否有效
            wmsCreCarpHousingAttDao.save(mplm);
        }
        return result;
	}

	@Override
	public Map<String, Object> getCarBeforeReturnListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user) {
		Map<String, Object> paramMap = carLoanWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()), "5");
		if (StringUtil.isNotBlank(queryInfo.getBill_code())) {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_start())) {
	 		paramMap.put("create_timestamp_start", queryInfo.getCreate_timestamp_start());
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())) {
	 		paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
	 	}
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name())) {
        	paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
		//车贷
		paramMap.put("cre_type", "3");
		//贷前退回
		paramMap.put("create_user_id", user.getUserId());
		paramMap.put("bill_status", "L1");
		paramMap.put("sortname", queryInfo.getSortname());
		paramMap.put("sortorder", queryInfo.getSortorder());
		List<Map<String, Object>> list = wmscrecreditheadDao_m.recheckForCar(paramMap);
		list = carLoanWorkFlowService.setWorkFlowTaskID(list, (List<Integer>) paramMap.get("idList"), (List<String>) paramMap.get("taskIdList"), 
				(List<String>)paramMap.get("approvesGroups"), (List<String>)paramMap.get("approveAdvices"), (List<String>)paramMap.get("approveTimes"));
        paramMap.put("Rows", list);
        return paramMap;
	}

	@Override
	public Map<String, Object> getCarBeforeReturnListWithPaging(
			WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user) {
		Map<String, Object> paramMap = carLoanWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()), "5");
		if (StringUtil.isNotBlank(queryInfo.getBill_code())) {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_start())) {
	 		paramMap.put("create_timestamp_start", queryInfo.getCreate_timestamp_start());
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())) {
	 		paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
	 	}
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name())) {
        	paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
		//车贷
		paramMap.put("cre_type", "3");
		//贷前退回
		paramMap.put("create_user_id", user.getUserId());
		paramMap.put("bill_status", "L1");
		paramMap.put("sortname", queryInfo.getSortname());
		paramMap.put("sortorder", queryInfo.getSortorder());
		paramMap.put("offset", queryInfo.getOffset());
		paramMap.put("pagesize", queryInfo.getPagesize());
		List<Map<String, Object>> list = wmscrecreditheadDao_m.recheckForCar(paramMap);
		list = carLoanWorkFlowService.setWorkFlowTaskID(list, (List<Integer>) paramMap.get("idList"), (List<String>) paramMap.get("taskIdList"), 
				(List<String>)paramMap.get("approvesGroups"), (List<String>)paramMap.get("approveAdvices"), (List<String>)paramMap.get("approveTimes"));
		GridDataBean bean = new GridDataBean(queryInfo.getPage(), wmscrecreditheadDao_m.findCountRecheck(paramMap), list);
		
		return bean.getGridData();
	}
	
	/**
     * Description: 房产初评列表页查询
     * @param queryInfo
     * @param user
     * @author wangyihan 
     * @return
     */
    @Override
    public Map<String, Object> gethousePreliminaryAssessmentListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //单据编码
        if (StringUtil.isNotBlank(queryInfo.getBill_code())) {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        //客户名称
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name())) {
            paramMap.put("customer_name", "%" + queryInfo.getCustomer_name() + "%");
        }
        //身份证号
        if (StringUtil.isNotBlank(queryInfo.getId_card())) {
            paramMap.put("id_card", "%" + queryInfo.getId_card() + "%");
        }
        //手机号码
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone())) {
            paramMap.put("mobile_telephone", "%" + queryInfo.getMobile_telephone() + "%");
        }
        //城市
        if (StringUtil.isNotBlank(queryInfo.getSalesman_city_code()))
        {
            paramMap.put("salesman_city_code", queryInfo.getSalesman_city_code());
        }
        //业务员
        if(StringUtil.isNotBlank(queryInfo.getSalesman_name_str())) {
            paramMap.put("salesman_name_str", SysTools.getSqlLikeParam(queryInfo.getSalesman_name_str()));
        }
        //申请时间从
        if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_start())) {
            paramMap.put("create_timestamp_start", queryInfo.getCreate_timestamp_start());
        }
        //申请时间到
        if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())) {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getMort_flag()))
        {
            paramMap.put("mort_flag", queryInfo.getMort_flag());// 抵押状态
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        // 开发模式 1为开发模式 其他为正常权限模式
        if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode")))
        {
            paramMap.put("salesman_id", user.getUserId());// 登陆人id
            paramMap.put("menu_url", WmsHelp.MENU_URL_CSPG_LIST);
            paramMap.put("childrendept", queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
        }

        List<Map<String, Object>> list = wmscrecreditheadDao_m.housePreLiminaryAssessmentList(paramMap);
        
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), 
                wmscrecreditheadDao_m.housePreLiminaryAssessmentCount(paramMap), list);

        return bean.getGridData();
    }
    
    /**
     * Description: 房产初评列表页导出
     * @param queryInfo
     * @param user
     * @author wangyihan 
     * @return
     */
    @Override
    public Map<String, Object> gethousePreliminaryAssessmentListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //单据编码
        if (StringUtil.isNotBlank(queryInfo.getBill_code())) {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        //客户名称
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name())) {
            paramMap.put("customer_name", "%" + queryInfo.getCustomer_name() + "%");
        }
        //身份证号
        if (StringUtil.isNotBlank(queryInfo.getId_card())) {
            paramMap.put("id_card", "%" + queryInfo.getId_card() + "%");
        }
        //手机号码
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone())) {
            paramMap.put("mobile_telephone", "%" + queryInfo.getMobile_telephone() + "%");
        }
        //城市
        if (StringUtil.isNotBlank(queryInfo.getSalesman_city_code()))
        {
            paramMap.put("salesman_city_code", queryInfo.getSalesman_city_code());
        }
        //业务员
        if(StringUtil.isNotBlank(queryInfo.getSalesman_name_str())) {
            paramMap.put("salesman_name_str", SysTools.getSqlLikeParam(queryInfo.getSalesman_name_str()));
        }
        //申请时间从
        if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_start())) {
            paramMap.put("create_timestamp_start", queryInfo.getCreate_timestamp_start());
        }
        //申请时间到
        if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())) {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getMort_flag()))
        {
            paramMap.put("mort_flag", queryInfo.getMort_flag());// 抵押状态
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        // 开发模式 1为开发模式 其他为正常权限模式
        if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode")))
        {
            paramMap.put("salesman_id", user.getUserId());// 登陆人id
            paramMap.put("menu_url", WmsHelp.MENU_URL_CSPG_LIST);
            paramMap.put("childrendept", queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
        }
        List<Map<String, Object>> list = wmscrecreditheadDao_m.housePreLiminaryAssessmentList(paramMap);
        
        //实现对数据信息屏蔽效果
//        SysSpecialUser specialUser = new SysSpecialUser();
//        specialUser.setEnable_flag("1");
//        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
//        list = SysTools.setListView(list, user, specilaUsers);
        
//        //添加taskId
//        list = houseCreditWorkFlowService.addTaskIDHouse(list, (List<Integer>) paramMap.get("idList"), 
//                (List<String>) paramMap.get("taskIdList"));
//          GridDataBean bean = new GridDataBean(queryInfo.getPage(), 
//                   wmscrecreditheadDao_m.housePreLiminaryAssessmentCount(paramMap), list);
        
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }
    /**
   	 * Description :【新房贷】流程历程查看
   	 * @param  wms_inve_transa_id
   	 * @return Map
   	 * @date 2016年2月25日 上午10:02
   	 * @author baisong
   	 */
	@Override
	public Map<String, Object> getPerfectHousingLoanProcessView(
			String wms_cre_credit_head_id) {
		return wmsLoanWorkFlowService.inveWorkFlowProcess(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS, wms_cre_credit_head_id);
	}
	  /**
   	 * Description :【新房贷】流程历程查看
   	 * @param  wms_inve_transa_id
   	 * @return Map
   	 * @date 2016年2月25日 上午10:02
   	 * @author baisong
   	 */
	@Override
	public Map<String, Object> getHeadInfo(Integer wms_cre_credit_head_id) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
		paramMap.put("hprocess_time", WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_TIME);
		return  wmscrecreditheadDao_m.searchWmsCreCreditHeadByMap(paramMap);
	}
	/**
	 * 查询业务员信息
	 */
	@Override
	public Map<String, Object> getHeadSalesman(Integer wms_cre_credit_head_id) {
		Map<String, Object> map=wmscrecreditheadDao_m.getHeadSalesman(wms_cre_credit_head_id);
		return map;
	}
	
	/**
     * 保存房贷申请
     * @date 2016.5.11
     */
	@Transactional
    public Map<String, Object> saveHouseLoan(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user) {
        log.debug("--------------------保存房贷申请开始--------------------");
        
        Map<String, Object> resMap = new HashMap<String, Object>();
        
        long now_long = System.currentTimeMillis();
        java.sql.Timestamp now_timestamp = new java.sql.Timestamp(now_long);
        queryInfo.setNow_timestamp(now_timestamp);
        
        //保存修改日志
        try {
            queryInfo.setLog_type("2");//修改房贷单据
            saveHouseLoanLog(queryInfo, user);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        
        //保存房贷单据信息
        WmsCreCreditHead wmsCreCreditHead = JsonUtil.jsonStringToBean(queryInfo.getHouseLoanInfo(), WmsCreCreditHead.class);
        wmsCreCreditHead.setLast_update_user_id(user.getUserId());
        wmsCreCreditHead.setLast_update_timestamp(now_timestamp);
        wmscrecreditheadDao_m.updateWmsCreCreditHead(wmsCreCreditHead);
        
        //保存联系人信息
        List<WmsCreCustomerChangeLineContact> contactList = JsonUtil.jsonArrayToList(queryInfo.getContactPeopleString(), WmsCreCustomerChangeLineContact.class);
        for(WmsCreCustomerChangeLineContact contact : contactList) {
            if(contact.getWms_cre_customer_change_line_contact_id() == null) {
                wmscrecustomerchangelinecontactDao_m.addNewRecord(contact);
            } else {
                wmscrecustomerchangelinecontactDao_m.update(contact);
            }
        }
        
        //删除在页面手动删除的联系人信息
        if(queryInfo.getDeleteContactIdArray() != null && queryInfo.getDeleteContactIdArray().length > 0) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("pkArray", queryInfo.getDeleteContactIdArray());
            wmscrecustomerchangelinecontactDao_m.deleteByPKs(paramMap);
        }
        
        //删除手动删除的附件信息
        if(queryInfo.getDeleteAttIds() != null && queryInfo.getDeleteAttIds().length > 0) {
            Map<String, Object> deletAtt = new HashMap<>();
            deletAtt.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
            deletAtt.put("data_type_name", queryInfo.getData_type_name());
            deletAtt.put("deleteAttIds", queryInfo.getDeleteAttIds());
            wmsCreHousingApplyAttDao.deleteatt(deletAtt);
        }
       
        List<WmsCreHousingApplyAtt> attList = JsonUtil.jsonArrayToList(queryInfo.getAttListJsonString(), WmsCreHousingApplyAtt.class);

        Map<String, Object> attParamMap = new HashMap<String, Object>();
        attParamMap.put("bill_code", wmsCreCreditHead.getBill_code());
        for (int i = 0; i < attList.size(); i++) {
            WmsCreHousingApplyAtt houseapp = attList.get(i);
            houseapp.setWms_cre_credit_head_id(wmsCreCreditHead.getWms_cre_credit_head_id());
            if(houseapp.getWms_cre_housing_apply_att_id() == null) {
                //生成新的图片编号
                attParamMap.put("data_detail_name", houseapp.getData_detail_name());
                houseapp.setAttachment_old_name(wmsCreHousingApplyAttDao.getNextAttSeqByBillCodeAndDataTypeName(attParamMap));
                wmsCreHousingApplyAttDao.save(houseapp);
            } else {
                wmsCreHousingApplyAttDao.update(houseapp);
            }
        }
        // 是否完善联系人 是否完善联系人 1完善 0/空 未完善
        if ("1".equals(queryInfo.getIs_finish()))
        {
            // 更改联系人表字段 是否完善联系人
            WmsCreCreditLineCustomerChangeHead wmsCreCreditLineCustomerChangeHead = new WmsCreCreditLineCustomerChangeHead();
            wmsCreCreditLineCustomerChangeHead.setWms_cre_credit_head_id(wmsCreCreditHead.getWms_cre_credit_head_id());
            wmsCreCreditLineCustomerChangeHead.setIs_major("1");
            wmsCreCreditLineCustomerChangeHead.setIs_finish(queryInfo.getIs_finish());
            wmsCreCreditLineCustomerChangeHeadDao.updateforBLTwo(wmsCreCreditLineCustomerChangeHead);
        }
            // 如果是退件过来的单据提交的时候需要调用流程
            WmsHouseCreditWorkFlowVO vo = new WmsHouseCreditWorkFlowVO();
            vo.setWms_cre_credit_head_id(wmsCreCreditHead.getWms_cre_credit_head_id().toString());
            vo.setPass("perfect");// 信息完善
            vo.setAdvice("信息完善完成");
            vo.setUserId(user.getUserId().toString());
            vo.setDebtkey("WSXX");
            wmsLoanWorkFlowService.publicApprovalStatus(vo);

        log.debug("--------------------保存房贷申请结束--------------------");
        return resMap;
    }
    
	/**
     * 获取房贷单据信息完善列表
     */
    @Override
    public Map<String, Object> getHouseLoanCompletedList(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name())) {
            paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_code())) {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_start())) {
            paramMap.put("create_timestamp_start", queryInfo.getCreate_timestamp_start());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())) {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name())) {
            paramMap.put("salesman_nameOrSalesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(user.getUser_regionNumber())) {//区域编码
            paramMap.put("create_user_city_code", user.getUser_regionNumber());
        }
        if (StringUtil.isNotBlank(queryInfo.getMort_flag()))
        {
            paramMap.put("mort_flag", queryInfo.getMort_flag());// 抵押状态
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        
        if(StringUtils.isNotEmpty(queryInfo.getIs_need_paging())) {
            if(queryInfo.getIs_need_paging().equals("0")) {
                paramMap.put("offset", null);
                paramMap.put("pagesize", null);
            }
        } else {
            paramMap.put("offset", queryInfo.getOffset());
            paramMap.put("pagesize", queryInfo.getPagesize());
        }
        
        // 根据提供idList查询相应的数据
        List<Map<String, Object>> list = wmscrecreditheadDao_m.getHouseLoanCompletedList(paramMap);
        
        GridDataBean<Map<String, Object>> bean = 
                new GridDataBean<Map<String, Object>>(
                        queryInfo.getPage(), 
                        wmscrecreditheadDao_m.getHouseLoanCompletedCount(paramMap),
                        list);
        return bean.getGridData();
    }
    
    /**
     * moa调用wms方法:房产核查结果保存
     * @param WmsMoaHouseInfoVO bean
     * @return 
     */
    @Override
    @Transactional
    public Map<String, Object> savePropertyVerificationInfoUp(WmsMoaHouseInfoVO bean) {
        Map<String, Object> map = new HashMap<String, Object>();
        //获取审批人
        PmPersonnel pmPersonnel = new PmPersonnel();
        pmPersonnel.setPersonnel_id(Integer.valueOf(bean.getPersonnel_id()));
        List<PmPersonnel> listp = pmPersonnelDao.getListByEntity(pmPersonnel);
        pmPersonnel= listp.get(0);
        if(listp == null || listp.size() == 0) {
            map.put("result", "error");
            map.put("message", "获取审批人调用失败！");
            return map;
        }
        
        Integer wms_cre_credit_head_id = Integer.valueOf(bean.getWms_cre_credit_head_id());
        WmsCreCreditHead  wmsCreCreditHead = wmscrecreditheadDao_m.get(wms_cre_credit_head_id);
        
        Map<String, Object> attParamMap = new HashMap<String, Object>();
        attParamMap.put("bill_code", wmsCreCreditHead.getBill_code());
        //保存附件信息
        List<WmsCreHousingApplyAtt> list = new ArrayList<WmsCreHousingApplyAtt>(); 
        if(StringUtils.isNotEmpty(bean.getFile_info_json())) {
            if (bean.getFile_info_json() != null && !"".equals(bean.getFile_info_json()) && !"[]".equals(bean.getFile_info_json()) && !"null".equals(bean.getFile_info_json()))
            {
                list = JsonUtil.jsonArrayToList(bean.getFile_info_json(), WmsCreHousingApplyAtt.class);
                for (WmsCreHousingApplyAtt att : list)
                {
                    att.setWms_cre_credit_head_id(wms_cre_credit_head_id);

                    // 生成新的图片编号
                    attParamMap.put("data_detail_name", att.getData_detail_name());
                    att.setAttachment_old_name(wmsCreHousingApplyAttDao.getNextAttSeqByBillCodeAndDataTypeName(attParamMap));
                    wmsCreHousingApplyAttDao.save(att);
                }
            }
        }
        
        if ("1".equals(bean.getIs_save()))
        {
        	// 调用流程
            WmsHouseCreditWorkFlowVO vo = new WmsHouseCreditWorkFlowVO();
            vo.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
            // 核查结果
            if (bean.getPass().equals("1"))
            {
                vo.setPass("accord");
            }
            else
            {
                vo.setPass("notaccord");
            }
            vo.setUserId(listp.get(0).getPersonnel_id().toString());
            vo.setAdvice(bean.getAdvice());
            vo.setDebtkey("FCHC");
            // 判断是否走流程
            if (vo != null && vo.getPass() != null)
            {
                // 走流程
                Map<String, Object> flowMap = wmsLoanWorkFlowService.publicApprovalStatus(vo);
                // 判断流程是否正常
                if (flowMap != null && flowMap.get("result") != null && "statusError".equals(flowMap.get("result").toString()))
                {
                    return flowMap;
                }
            }
        }
        
		if ("1".equals(bean.getIs_save())) {
			// 更新贷款主表信息
			Timestamp now = new Timestamp(System.currentTimeMillis());
			WmsCreCreditHead wHead = new WmsCreCreditHead();// 主表
			wHead.setWms_cre_credit_head_id(Integer.valueOf(bean.getWms_cre_credit_head_id()));
			wHead.setHouse_appro_user_id(listp.get(0).getPersonnel_id());// 核查人id
			wHead.setHouse_appro_user_name(listp.get(0).getPersonnel_name());// 核查人姓名
			wHead.setHouse_appro_timestamp(now);// 核查时间
			wHead.setHouse_appro_result(bean.getPass());// 核查结果
			wHead.setHouse_appro_advice(bean.getAdvice());// 核查意见
			wHead.setHouse_appro_result_page(bean.getPass());// 核查审批结果展示
			wmscrecreditheadDao_m.updateforhouse(wHead);
            // 发送消息 作废了 此节点不发送消息
            /*Map<String, Object> customerChangeParamMap = new HashMap<String, Object>();
            customerChangeParamMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
            customerChangeParamMap.put("is_major", "1");
            customerChangeParamMap.put("enable_flag", "1");
            List<Map<String, Object>> customerChangeList = this.wmscrecreditlinecustomerchangeheadDao_m
            		.search(customerChangeParamMap);
            if (customerChangeList != null && customerChangeList.size() == 1) {
            	// 调用方法map
            	Map<String, Object> sendMap = new HashMap<String, Object>();
            	// 发送短信map
            	Map<String, String> msgMap = new HashMap<String, String>();
            	// 参数map
            	Map<String, String> paramMap = new HashMap<String, String>();
            	sendMap.put("short_message", "1");
            	msgMap.put("tpl_id", "1694");

            	sendMap.put("user_id", pmPersonnel.getPersonnel_id());
            	// 根据权限获取人
            	sendMap.put("role_value", "1");// 终审人员--WorkflowRoleHelp.HOUSE_GLBJL

            	// 根据菜单查询人员
            	sendMap.put("menu_url", WmsHelp.MENU_URL_ZSSP_LIST);// 终审审批ual
            	sendMap.put("salesman_dept_id", wmsCreCreditHead.getSalesman_dept_id());// 部门id

            	paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
            	paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
            	paramMap.put("city", wmsCreCreditHead.getCity());

            	Gson gson = new Gson();
            	msgMap.put("paramJson", gson.toJson(paramMap));
            	sendMap.put("msgMap", msgMap);
            	this.getUserAndSendInfo(sendMap);
            }*/
            try
            {
                // 调用方法map
                Map<String, Object> sendMap = new HashMap<String, Object>();
                // 参数map
                Map<String, String> paramMap = new HashMap<String, String>();
                // 根据菜单查询人员
                sendMap.put("menu_url", WmsHelp.MENU_URL_ZSSP_LIST);// 终审审批ual
                sendMap.put("salesman_dept_id", wmsCreCreditHead.getSalesman_dept_id());
                sendMap.put("role_value", "1");
                paramMap.put("bill_status", "E");// 终审审批单据状态
                // // 获取初评单据数量
                // List<Map<String, Object>> listS =
                // wmscrecreditheadDao_m.getStatusCount(paramMap);
                // if (listS != null)
                // {
                // paramMap.put("number", listS.size() + "");
                // }
                // else
                // {
                    paramMap.put("number", "1");
                // }
                sendMap.put("pc_msg_code", "20015");// pc端消息code
                sendMap.put("pc_message", "1");// 标识发送pc消息
                sendMap.put("msg_map", paramMap);// 极光推送的消息内容参数
                // 异步发送消息
                sendInfoAsynchronous(sendMap);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
		}
        map.put("result", "success");
        map.put("message", "调用成功");
        
        return map;
    }
    
    
    /**
     * 
     * @Title: DocumentVoidUp
     * @Description: TODO(手机端单据作废兼容组合贷) 房产核查为4放款申请为9放款审核为10
     * @param advice
     * @param wms_cre_credit_head_id
     * @param pass
     * @param debtkey
     * @param personnelId 
     * @author: baisong
     * @time:2017年1月11日 下午1:51:32
     * @see com.zx.emanage.cremanage.service.IWmsCreCreditHeadService#DocumentVoidUp(java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer)
     * history:
     * 1、2017年1月11日 baisong 创建方法
     */
	@Override
    @Transactional
    public void DocumentVoidUp(String advice, Integer wms_cre_credit_head_id, String pass, String debtkey, Integer personnelId)
    {
	    
	    java.sql.Timestamp now_timestamp = new java.sql.Timestamp(System.currentTimeMillis());
	    PmPersonnel   pPersonnel=  pmPersonnelDao.get(personnelId);//获取人员
        Integer wms_head_id = wms_cre_credit_head_id;
        // 房产核查
        if ("4".equals(debtkey))
        {
            debtkey = "FCHC";
            pass = "nullify";
        }
        // 放款申请
        else if ("9".equals(debtkey))
        {
            debtkey = "FKSQ";
            pass = "nullify";
        }
        // 放款审核
        else if ("10".equals(debtkey))
        {
            debtkey = "FKSP";
            pass = "nullify";
        }
        /*Map<String, Object> map = new HashMap<>();
         map.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
         // 放款申请
         if ("9".equals(debtkey))
         {
             map.put("bill_status", "E,M,F");
         }
         // 放款审核
         else if ("10".equals(debtkey))
         {
             map.put("bill_status", "K");
         }
         List<Map<String, Object>> list = new ArrayList<>();
         boolean is_all = false;
         // 放款申请/放款审核
         if ("9".equals(debtkey) || "10".equals(debtkey))
         {
             // (获取组合贷单据情况)
             list = wmscrecreditheadDao_m.getGroupHeadByApp(map);
             int num = 0;
             // 2条都为等额本息单据，作废其中一条，另一条不作废（3条组合贷，2条等额本息，1条先息后本，作废其中一条等额本息，其它两条不会被作废）
             // 循环单据判断还款类型
             for (Map<String, Object> param : list)
             {
                 // 判断是否是等额本金 payment_contract_type 1等额本金2先息后本
                 if (param != null && param.get("payment_contract_type") != null && "1".equals(param.get("payment_contract_type").toString()) && param.get("wms_cre_credit_head_id") != null && wms_cre_credit_head_id.toString().equals(param.get("wms_cre_credit_head_id").toString()))
                 {
                     is_all = true;
                 }
                 // 判断是否是等额本息
                 if (param != null && param.get("payment_contract_type") != null && "1".equals(param.get("payment_contract_type").toString()))
                 {
                     num++;
                 }
             }
             // 判断是否是多条等额本息
             if (num > 1)
             {
                 is_all = false;
             }
         }
         else
         {
             list.add(map);
         }
         // 判断list是否为空
         if (list != null && list.size() > 0)
         {
             for (Map<String, Object> wheadMap : list)
             {
                 Integer wms_head_id = Integer.valueOf((wheadMap.get("wms_cre_credit_head_id").toString()));
                 // 如果作废的是等额本金则全部作废 如果作废的不是等额本金 则作废用户选择的那条
                 if (is_all || wms_cre_credit_head_id.toString().equals(wms_head_id.toString()))
                 {*/
        // 更新贷款主表(作废相关字段)
        Map<String, Object> updateMap = new HashMap<String, Object>();
        updateMap.put("wms_cre_credit_head_id", wms_head_id);
        updateMap.put("nullify_type", debtkey);// 5 贷款终审 6--合同 7--公证// 8--他项
                                               // 9--放款申请 10--放款审批//
                                               // 11--放款确认2016-9-27添加作废节点
        updateMap.put("nullify_user_id", personnelId);
        updateMap.put("nullify_user_name", pPersonnel.getPersonnel_name());// 姓名
        updateMap.put("nullify_timestamp", now_timestamp);
        updateMap.put("nullify_reason", advice);
        this.wmscrecreditheadDao_m.updateForDocumentVoidUp(updateMap);

        // 调用流程
        WmsHouseCreditWorkFlowVO vo = new WmsHouseCreditWorkFlowVO();
        vo.setWms_cre_credit_head_id(wms_head_id.toString());
        // 核查结果
        vo.setPass(pass);
        vo.setUserId(personnelId.toString());
        vo.setAdvice(advice);
        vo.setDebtkey(debtkey);
        // 判断是否走流程
        if (vo != null && vo.getPass() != null)
        {
            // 走流程
            wmsLoanWorkFlowService.publicApprovalStatus(vo);
        }
        WmsCreCreditHead wCreCreditHead = wmscrecreditheadDao_m.get(wms_head_id);
        /*// 新流程发送短信
        if ("2".equals(wCreCreditHead.getVersion_number()))
                        {
            // 查询客户姓名
            Map<String, Object> customerChangeParamMap = new HashMap<String, Object>();
            customerChangeParamMap.put("wms_cre_credit_head_id", wms_head_id.toString());
            customerChangeParamMap.put("is_major", "1");
            customerChangeParamMap.put("enable_flag", "1");

            PmPersonnel pmPersonnel = pmPersonnelDao.get(wCreCreditHead.getSalesman_id());
            List<Map<String, Object>> customerChangeList = this.wmsCreCreditLineCustomerChangeHeadDao.search(customerChangeParamMap);
            // 调用发送信息的接口出现异常 不会影响正常流程
            try
            {
                if (customerChangeList != null && customerChangeList.size() == 1)
                {
                    // 调用方法map
                    Map<String, Object> sendMap = new HashMap<String, Object>();
                    // 参数map
                    Map<String, String> paramMap = new HashMap<String, String>();
                    // 参数map
                    Map<String, String> msg_extras = new HashMap<String, String>();

                    sendMap.put("role_value", "1");// 管理部经理- WorkflowRoleHelp.HOUSE_GLBJL
                    // 根据菜单查询人员
                    sendMap.put("menu_url", WmsHelp.MENU_URL_ZSSP_LIST);// 终审审批ual
                    sendMap.put("salesman_dept_id", wCreCreditHead.getSalesman_dept_id());// 业务员部门id

                    paramMap.put("bill_code", wCreCreditHead.getBill_code());
                    paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                    paramMap.put("city", wCreCreditHead.getCity());
                    // 激光推送
                    sendMap.put("quart_message", "1");// 消息附加参数
                    // 如果是消息中心
                    sendMap.put("message_center", "1");
                    msg_extras.put("wms_cre_credit_head_id", wCreCreditHead.getWms_cre_credit_head_id().toString());// 添加参数
                    sendMap.put("msg_extras", msg_extras);// 消息附加参数
                    sendMap.put("msg_code", "20009");// 消息编码
                    sendMap.put("msg_code_role", "20009");// 消息编码--流程角色

                    paramMap.put("wms_cre_credit_head_id", wCreCreditHead.getWms_cre_credit_head_id().toString());
                    paramMap.put("bill_status", wCreCreditHead.getBill_status());
                    paramMap.put("create_user_id", pmPersonnel.getPersonnel_id().toString());
                    paramMap.put("create_user_name", pmPersonnel.getPersonnel_name());
                    sendMap.put("msg_map", paramMap);// 极光推送和消息中心的消息内容参数

                    this.getUserAndSendInfo(sendMap);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            
        }}*/
        /*       }
            }
        }*/
	}
	
    /**
     * 方法用途：用于发送消息 ，消息中心消息，短信息，极光推送消息
     * @param Map<String,Object> map 当前参数中会传递多个数据 标示发送消息的情况和内容 
     * @param map中  message_center 如果值为1则发送消息中心消息
     * @param map中  short_message  如果值为1则发送短信息消息
     * @param map中  quart_message  如果值为1则发送极光消息
     * @param map中  pc_message  如果值为1则发送pc端消息
     * @param map中  role_value     传递节点角色名 各个节点名称 请查看WorkflowRoleHelp类 
     * @param map中  role_outside   如果是1则需要获取获取门店或者团队经理  除了流程节点角色以外的人需要发送消息 如：客户经理所在团队的团队经理、所在门店的门店负责人、客户经理本人
     * @param map中post_number_list 是一个list 里面参数是标识查询客户经理 团队经理 门店经理的   'KHJL','TDJL','MDJL'
     * @param map中msg_code			消息编码
     * @param map中is_dis_area		如果传递1则将人员进行区域划分按照区域编码区分
     * @param map中user_id 		是查询客户经理团队经理门店经理所使用的人员id
     * @param map中is_oneself 	是否把消息发送给当前业务员本人1是0否
     * @param map中其他参数自定义
     * @param 极光推送参数 msg_map 消息内容参数
     * @param 极光推送参数 msg_extras 消息附加参数
     * @return Map<String, Object> map 返回值中有一个reuslt标示成功失败其他值自定义
     * @author baisong
     */
	@Override
	public Map<String, Object> getUserAndSendInfo(Map<String, Object> map) {
		Map<String,Object> resMap=new HashMap<>();
		String reuslt="success";
		if(map==null){
			reuslt="error";
			resMap.put("reuslt", reuslt);
			return resMap;
		}
		//获取传进来的流程角色编码来查询当前角色下有哪些人
		List<Map<String,Object>> list=new ArrayList<>();
        List<Map<String, Object>> listUserMap = new ArrayList<>();
		if(map.get("role_value")!=null&&!"".equals(map.get("role_value"))){
            /*list=sysUserRoleDao.getRoleListUser(map);
            if(list==null){//||list.size()==0
            	reuslt="error";
            	resMap.put("reuslt", reuslt);
            	return resMap;
            }*/
            if (map.get("menu_url") != null && map.get("role_name") == null)
            {
                resMap.put("menu_url", map.get("menu_url"));// 菜单URL
                // 查询当前url的权限都有谁
                listUserMap = sysUserRoleDao.getUserByMenu(resMap);
                // 循环判断数据权限
                for (Map<String, Object> mapUser : listUserMap)
                {
                    // 人员部门和业务单据部门和菜单不能为空
                    if (mapUser != null && mapUser.get("personnel_deptId") != null && map.get("salesman_dept_id") != null)
                    {
                        mapUser.put("salesman_dept_id", map.get("salesman_dept_id"));// 单据里面的业务员部门主键
                        mapUser.put("menu_url", map.get("menu_url"));// 菜单URL
                        String isMenu = sysMenuDao.isChildrenDept(mapUser);
                        if ("1".equals(isMenu))
                        {
                            list.add(mapUser);
                        }
                    }
                }
            }
            else if (map.get("role_name") != null)
            {
                resMap.put("role_name", map.get("role_name"));
                list = sysUserRoleDao.getUserByRole(resMap);
            }
		}
		//根据传递过来的人员id获取客户经理团队经理门店经理
		List<Map<String,Object>> listuser=new ArrayList<>();
		//根据职务区分处理的人员list
		List<Map<String,Object>> listuseropt=new ArrayList<>();
		if("1".equals(map.get("role_outside"))){
			//获取传进来的人员id获取客户经理团队经理门店经理 发送短信息时候 需要判断人员职务  'KHJL','TDJL','MDJL'
			listuser=sysUserRoleDao.getSuperiorAndoneself(map);
			if(list==null){
				reuslt="error";
				resMap.put("reuslt", reuslt);
				return resMap;
			}
			//根据控制获取对应的人 并添加到角色的list中
			if(map.get("post_number_list")!=null&&((List)map.get("post_number_list")).size()>0){
				List<String> listpost=(List)map.get("post_number_list");
				for(int i=0;i<listpost.size();i++){
					for(int j=0;j<listuser.size();j++){
						if(listpost.get(i).equals(listuser.get(j).get("post_number"))){
							listuseropt.add(listuser.get(j));
						}
					}
				}
			}
		}
		//去除重复项
		listuseropt=RemoveSame(listuseropt);
        /*boolean is_dis_area=true;
        if("1".equals(map.get("is_dis_area"))){//是否需要区分地区
        	is_dis_area=false;
        	//判断区域编码
        	for(int i=0;i<list.size();i++){
        		if((list.get(i).get("personnel_regionNumber") != null) && 
        				(map.get("regionNumber") != null) && 
        				(is_dis_area||!list.get(i).get("personnel_regionNumber").equals(map.get("regionNumber")))){
        			list.remove(i);//移除不对应的人员
        			i--;
        		}
        	}
        }*/
		//消息中心
		if(map.get("message_center")!=null&&"1".equals(map.get("message_center").toString())){
		  try {
				Map<String, String> paramMap = new HashMap<String, String>();
				Map<String, String> msgMap =(Map)(map.get("msg_map"));
				paramMap.put("bill_code",	msgMap.get("bill_code")==null ? null: msgMap.get("bill_code").toString());
				paramMap.put("customer_name", msgMap.get("customer_name")==null ? null: msgMap.get("customer_name").toString());
				paramMap.put("salesman_name_code",msgMap.get("salesman_name_code") ==null ? null: msgMap.get("salesman_name_code").toString());
				paramMap.put("appro_limit",msgMap.get("appro_limit") ==null ? null: msgMap.get("appro_limit").toString());
				paramMap.put("advice", msgMap.get("advice")==null ? null: msgMap.get("advice").toString());
				paramMap.put("vehicle_evaluation_price", msgMap.get("vehicle_evaluation_price")==null ? null: msgMap.get("vehicle_evaluation_price").toString());
				
	            paramMap.put("wms_cre_credit_head_id", msgMap.get("wms_cre_credit_head_id")==null ? null: msgMap.get("wms_cre_credit_head_id").toString());
	            paramMap.put("bill_status",  msgMap.get("bill_status")==null ? null: msgMap.get("bill_status").toString());
	            paramMap.put("create_user_id",  msgMap.get("create_user_id")==null ? null: msgMap.get("create_user_id").toString());
	            paramMap.put("create_user_name",  msgMap.get("create_user_name")==null ? null: msgMap.get("create_user_name").toString());
                paramMap.put("end_date", msgMap.get("end_date") == null ? null : msgMap.get("end_date").toString());
	            
	            paramMap.put("city",  msgMap.containsKey("city") ? msgMap.get("city") : null);
                paramMap.put("app_name", msgMap.containsKey("app_name") ? msgMap.get("app_name") : null);
                paramMap.put("credit_limit", msgMap.containsKey("credit_limit") ? msgMap.get("credit_limit").replace(".00", "").replace(".0", "") : null);
                paramMap.put("dept_info_md", msgMap.containsKey("dept_info_md") ? msgMap.get("dept_info_md") : null);// 门店信息
                paramMap.put("dept_info", msgMap.containsKey("dept_info") ? msgMap.get("dept_info") : null);// 部门信息
				//循环取出listuser里的ID,SHORTCODE,NAME 添加到表中
				if(map.get("role_outside")!=null&&"1".equals(map.get("role_outside"))){
					String sendStr = SysUtil.replaceParamValueByMap(PlatformGlobalVar.SYS_PROPERTIES.get(map.get("msg_code").toString()), paramMap);
					paramMap.put("notification_message", sendStr);
					for(int a=0;a<listuseropt.size();a++){
						paramMap.put("personnel_id", listuseropt.get(a).get("personnel_id").toString());
						paramMap.put("personnel_shortCode", listuseropt.get(a).get("personnel_shortCode").toString());
						paramMap.put("personnel_name", listuseropt.get(a).get("personnel_name").toString());
						WmsCreCreditNotificationMessage message = new WmsCreCreditNotificationMessage();
						try {
							//map转对象
							message = (WmsCreCreditNotificationMessage) SysTools.convertMap(WmsCreCreditNotificationMessage.class, paramMap);
						} catch (IllegalAccessException | InstantiationException
								| IntrospectionException e) {
							e.printStackTrace();
						}
						message.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
						message.setIs_read(1); //未读状态
						message.setEnable_flag("1");
						message.setCreate_user_id(Integer.valueOf(paramMap.get("create_user_id")));
						message.setPersonnel_id(Integer.valueOf(paramMap.get("personnel_id")));
						message.setWms_cre_credit_head_id(Integer.valueOf(paramMap.get("wms_cre_credit_head_id")));
						message.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
						message.setLast_update_user_id(Integer.valueOf(paramMap.get("create_user_id")));
						wmscrecreditnotificationmessagedao.save(message);
					}
				} 
				if(null!=map.get("role_value")&&!"".equals(map.get("role_value").toString())){
					String sendStr = SysUtil.replaceParamValueByMap(PlatformGlobalVar.SYS_PROPERTIES.get(map.get("msg_code_role").toString()), paramMap);
					paramMap.put("notification_message", sendStr);
					for(int a=0;a<list.size();a++){
						paramMap.put("personnel_id", list.get(a).get("personnel_id").toString());
						paramMap.put("personnel_shortCode", list.get(a).get("personnel_shortCode").toString());
						paramMap.put("personnel_name", list.get(a).get("personnel_name").toString());
						WmsCreCreditNotificationMessage message = new WmsCreCreditNotificationMessage();
						try {
							//map转对象
							message = (WmsCreCreditNotificationMessage) SysTools.convertMap(WmsCreCreditNotificationMessage.class, paramMap);
						} catch (IllegalAccessException | InstantiationException
								| IntrospectionException e) {
							e.printStackTrace();
						}
						message.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
						message.setIs_read(1); //未读状态
						message.setEnable_flag("1");
						message.setCreate_user_id(Integer.valueOf(paramMap.get("create_user_id")));
						message.setPersonnel_id(Integer.valueOf(paramMap.get("personnel_id")));
						message.setWms_cre_credit_head_id(Integer.valueOf(paramMap.get("wms_cre_credit_head_id")));
						message.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
						message.setLast_update_user_id(Integer.valueOf(paramMap.get("create_user_id")));
						wmscrecreditnotificationmessagedao.save(message);
					}
			
				}
				if(null!=map.get("is_oneself")&&"1".equals(map.get("is_oneself").toString())){//is_oneself
						String sendStr = SysUtil.replaceParamValueByMap(PlatformGlobalVar.SYS_PROPERTIES.get(map.get("msg_code").toString()), paramMap);
						paramMap.put("notification_message", sendStr);
						paramMap.put("personnel_id", msgMap.get("personnel_id")==null ? null: msgMap.get("personnel_id").toString());
						paramMap.put("personnel_shortCode", msgMap.get("personnel_shortCode")==null ? null: msgMap.get("personnel_shortCode").toString());
						paramMap.put("personnel_name",msgMap.get("personnel_name")==null ? null: msgMap.get("personnel_name").toString());
						WmsCreCreditNotificationMessage message = new WmsCreCreditNotificationMessage();
						try {
							//map转对象
							message = (WmsCreCreditNotificationMessage) SysTools.convertMap(WmsCreCreditNotificationMessage.class, paramMap);
						} catch (IllegalAccessException | InstantiationException
								| IntrospectionException e) {
							e.printStackTrace();
						}
						message.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
						message.setIs_read(1); //未读状态
						message.setEnable_flag("1");
						message.setCreate_user_id(Integer.valueOf(paramMap.get("create_user_id")));
						message.setPersonnel_id(Integer.valueOf(paramMap.get("personnel_id")));
						message.setWms_cre_credit_head_id(Integer.valueOf(paramMap.get("wms_cre_credit_head_id")));
						message.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
						message.setLast_update_user_id(Integer.valueOf(paramMap.get("create_user_id")));
						wmscrecreditnotificationmessagedao.save(message);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//短信息
		if(map.get("short_message")!=null&&"1".equals(map.get("short_message").toString())){
			    if(list != null&&list .size()>0) {
			        try {
			            Map<String, String> msgMap = (Map<String, String>)map.get("msgMap");
			            for(Map<String, Object> tempMap : list) {
			                msgMap.put("user_id", tempMap.get("personnel_id").toString());
			                msgMap.put("tel", tempMap.get("personnel_contactTel").toString());
			                SysUtil.sendMsg((Map<String, String>)map.get("msgMap"));
			            }
	                } catch (ClientProtocolException e) {
	                    e.printStackTrace();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
			    }	
			    if(null!=map.get("is_oneself")&&"1".equals(map.get("is_oneself").toString())){//is_oneself给当前业务员发
			    	 try {
				            SysUtil.sendMsg((Map<String, String>)map.get("msgMap"));
		                } catch (ClientProtocolException e) {
		                    e.printStackTrace();
		                } catch (IOException e) {
		                    e.printStackTrace();
		                }
			    }
		}
		//极光推送
		if(map.get("quart_message")!=null&&"1".equals(map.get("quart_message").toString())){
			try{
				if(null!=map.get("role_value")&&!"".equals(map.get("role_value").toString())){
					if(list.size()>0){
						List<String> listStr=new ArrayList<>();
						for(Map<String,Object> maplist: list){
							listStr.add(maplist.get("personnel_shortCode").toString());
						}
						SysSendInfoVO sysSendInfoVO =new SysSendInfoVO();
						sysSendInfoVO.setUser_code(listStr);//人员短工号
                        // app别名
                        if (map.get("app_name") != null)
                        {
                            sysSendInfoVO.setApp_name(map.get("app_name").toString());
                        }
						sysSendInfoVO.setMsg_code(map.get("msg_code_role").toString());// 消息编码
						sysSendInfoVO.setMap((Map)(map.get("msg_map")));//推送消息参数（模版生成消息时的参数）
						sysSendInfoVO.setExtras((Map)(map.get("msg_extras")));//消息附加数据
						sendJGInfo(sysSendInfoVO);//激光推送
					}
				}
				if(null!=map.get("role_outside")&&"1".equals(map.get("role_outside").toString())){
					//map中post_number_list 是一个list 里面参数是标识查询客户经理 团队经理 门店经理的   'KHJL','TDJL','MDJL'
					if(map.get("role_outside")!=null&&map.get("post_number_list")!=null
						&&"1".equals(map.get("role_outside").toString())&&listuseropt.size()>0){//如果传递1则将人员进行区域划分按照区域编码区分
						List<String> listStr=new ArrayList<>();
						for(Map<String,Object> maplist: listuseropt){
							listStr.add(maplist.get("personnel_shortCode").toString());
						}
						SysSendInfoVO sysSendInfoVO =new SysSendInfoVO();
						sysSendInfoVO.setUser_code(listStr);//人员短工号
                        // app别名
                        if (map.get("app_name") != null)
                        {
                            sysSendInfoVO.setApp_name(map.get("app_name").toString());
                        }
						sysSendInfoVO.setMsg_code(map.get("msg_code").toString());// 消息编码
						sysSendInfoVO.setMap((Map)(map.get("msg_map")));//推送消息参数（模版生成消息时的参数）
						sysSendInfoVO.setExtras((Map)(map.get("msg_extras")));//消息附加数据
						sendJGInfo(sysSendInfoVO);//激光推送
					}
				}
				if(null!=map.get("is_oneself")&&"1".equals(map.get("is_oneself").toString())){//is_oneself
						List<String> listStr=new ArrayList<>();
						listStr.add(((Map)(map.get("msg_map"))).get("personnel_shortCode")==null ? null: ((Map)(map.get("msg_map"))).get("personnel_shortCode").toString());
						SysSendInfoVO sysSendInfoVO =new SysSendInfoVO();
						sysSendInfoVO.setUser_code(listStr);//人员短工号
						// app别名
						if (map.get("app_name") != null)
						{
						    sysSendInfoVO.setApp_name(map.get("app_name").toString());
						}
						sysSendInfoVO.setMsg_code(map.get("msg_code").toString());// 消息编码
						sysSendInfoVO.setMap((Map)(map.get("msg_map")));//推送消息参数（模版生成消息时的参数）
						sysSendInfoVO.setExtras((Map)(map.get("msg_extras")));//消息附加数据
						sendJGInfo(sysSendInfoVO);//激光推送
				}
			}catch(Exception e){
				e.printStackTrace();
			}	
		}
        // PC端消息推送
        if (map.get("pc_message") != null && "1".equals(map.get("pc_message").toString()))
        {
            try
            {
                if (null != map.get("role_value") && !"".equals(map.get("role_value").toString()))
                {
                    if (list.size() > 0)
                    {
                        MessageVo mvo = new MessageVo();
                        // 消息接收人code
                        List<String> listStr = new ArrayList<>();
                        for (Map<String, Object> maplist : list)
                        {
                            listStr.add(maplist.get("personnel_shortCode").toString());
                        }
                        Map<String, String> msgMap = (Map) (map.get("msg_map"));
                        String sendStr = SysUtil.replaceParamValueByMap(PlatformGlobalVar.SYS_PROPERTIES.get(map.get("pc_msg_code").toString()), msgMap);
                        mvo.setMessage(sendStr);// 消息内容
                        mvo.setUserCode(listStr);// 接收人code
                        try
                        {
                            // 发送信息
                            String wmsurl = PlatformGlobalVar.SYS_PROPERTIES.get("server_wms_url");
                            wmsurl = wmsurl.replace(" ", "");
                            wmsurl = wmsurl.replace("http://", "");
                            wmsurl = wmsurl.replace("HTTP://", "");
                            WebSocketContainer container = ContainerProvider.getWebSocketContainer(); // 获取WebSocket连接器，其中具体实现可以参照websocket-api.jar的源码,Class.forName("org.apache.tomcat.websocket.WsWebSocketContainer");
                            String uri = "ws://" + wmsurl + "/websocket?id=" + 100000;
                            Session session = container.connectToServer(Client.class, new URI(uri)); // 连接会话
                            Gson gson = new Gson();
                            session.getBasicRemote().sendText(gson.toJson(mvo)); // 发送文本消息
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
		resMap.put("reuslt", reuslt);
		return resMap;
	}
	/**
	 * 极光推送 
	 * @param sysSendInfoVO
	 * @return String
	 * @author baisong
	 */
	public String sendJGInfo(SysSendInfoVO sysSendInfoVO){
		 //极光推送需要调用moa项目里面的方法
    	Map<String, Object> mapInfo = new HashMap<String, Object>();
    	RestTemplate restTemplate = new RestTemplate();
        String url = PlatformGlobalVar.SYS_PROPERTIES.get("moaUrl") + "/wms/pushManageForWMS.do";
        log.info("极光服务器地址"+url);
        /*MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();
        form.add("sysSendInfoVO",new Gson().toJson(sysSendInfoVO));
        mapInfo = restTemplate.postForObject(url, form,Map.class);*/
        // 短工号问题
        for (int i = 0; i < sysSendInfoVO.getUser_code().size(); i++)
        {
            // 标识app是否显示数量 0 为不显示 1为显示
            sysSendInfoVO.getExtras().put("badge", "0");
        }
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
   		nvps.add(new BasicNameValuePair("sysSendInfoVO",new Gson().toJson(sysSendInfoVO)));
   		Map<String, Object> resMap = new HashMap<String, Object>();
   		try {
   			mapInfo = HttpClientUtil.post(url, nvps, Map.class);
   		} catch (IOException e) {
   			e.printStackTrace();
   			resMap.put("message", e.getMessage());
   		}
        if(mapInfo != null) {
        	if("000".equals(mapInfo.get("ret_code"))){
        		return "success";
        	}
        }
		return "error";
	}
	
	/**
	 * 保存修改日志
	 * @author Administrator
	 * @param WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public void saveHouseLoanLog(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user) throws JsonGenerationException, JsonMappingException, IOException {
	    //记录修改日志
        ObjectMapper mapper = new ObjectMapper();
        WmsCreCreditHeadLog wmsCreCreditHeadLog = new WmsCreCreditHeadLog();
	    if(StringUtils.isNotEmpty(queryInfo.getLog_type())) {
	        wmsCreCreditHeadLog.setLog_type(queryInfo.getLog_type());
	        wmsCreCreditHeadLog.setCreate_timestamp(queryInfo.getNow_timestamp());
            wmsCreCreditHeadLog.setCreate_user_id(user.getUserId());
            wmsCreCreditHeadLog.setCreate_user_name(user.getRealname());
            wmsCreCreditHeadLog.setWms_cre_credit_head_id(queryInfo.getWms_cre_credit_head_id());
            wmsCreCreditHeadLog.setEnable_flag("1");
            
            Map<String, Object> prevParamMap = new HashMap<String, Object>();
            prevParamMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
            prevParamMap.put("enable_flag", "1");
            prevParamMap.put("sortname", null);
            prevParamMap.put("sortorder", null);
            prevParamMap.put("offset", null);
            prevParamMap.put("pagesize", null);
	        //修改客户信息
	        if("1".equals(queryInfo.getLog_type())) {
	            //保存修改日志
	            List<Map<String, Object>> prevWmsCreCreditLineCustomerChangeHeadList = 
	                    this.wmscrecreditlinecustomerchangeheadDao_m.search(prevParamMap);//客户信息
	            wmsCreCreditHeadLog.setWms_cre_credit_line_customer_change_head_json(
	                    mapper.writeValueAsString(prevWmsCreCreditLineCustomerChangeHeadList));
	            if(prevWmsCreCreditLineCustomerChangeHeadList != null && prevWmsCreCreditLineCustomerChangeHeadList.size() > 0) {
	                Integer wms_cre_credit_line_customer_change_head_id = 
	                        (Integer)prevWmsCreCreditLineCustomerChangeHeadList.get(0).get("wms_cre_credit_line_customer_change_head_id");
	                prevParamMap = new HashMap<String, Object>();
	                prevParamMap.put("wms_cre_credit_line_customer_change_head_id", wms_cre_credit_line_customer_change_head_id);
	                
	                List<Map<String, Object>> prevWmsCreCustomerChangeLineHouseinfoList = 
	                        this.wmscrecustomerchangelinehouseinfoDao_m.search(prevParamMap);//房产信息
	                List<Map<String, Object>> prevWmsCreCustomerChangeLineWorkinfoList = 
	                        this.wmscrecustomerchangelineworkinfoDao_m.search(prevParamMap);//工作信息
	                prevParamMap.put("sortname", "");
	                prevParamMap.put("sortorder", "");
	                List<Map<String, Object>> prevWmsCreCustomerChangeLineCompanyList = 
	                        this.wmsCreCustomerChangeLineCompanyDao.search(prevParamMap);//公司信息
	                
	                wmsCreCreditHeadLog.setWms_cre_customer_change_line_houseinfo_json(
	                        mapper.writeValueAsString(prevWmsCreCustomerChangeLineHouseinfoList));
	                wmsCreCreditHeadLog.setWms_cre_customer_change_line_workinfo_json(
	                        mapper.writeValueAsString(prevWmsCreCustomerChangeLineWorkinfoList));
	                wmsCreCreditHeadLog.setWms_cre_customer_change_line_company_json(
	                        mapper.writeValueAsString(prevWmsCreCustomerChangeLineCompanyList));
	            }
	        }
	        //修改房贷单据
	        if("2".equals(queryInfo.getLog_type())) {
	            WmsCreCreditHead wmsCreCreditHeadPrev = this.wmscrecreditheadDao_m.get(queryInfo.getWms_cre_credit_head_id());
	            
	            prevParamMap = new HashMap<String, Object>();
	            prevParamMap.put("wms_cre_credit_line_customer_change_head_id", queryInfo.getWms_cre_credit_head_id());
	            List<Map<String, Object>> prevWmsCreCustomerChangeLineContactList = 
	                    this.wmscrecustomerchangelinecontactDao_m.search(prevParamMap);
                wmsCreCreditHeadLog.setWms_cre_credit_head_json(mapper.writeValueAsString(wmsCreCreditHeadPrev));//单据信息
                wmsCreCreditHeadLog.setWms_cre_customer_change_line_contact_json(
                        mapper.writeValueAsString(prevWmsCreCustomerChangeLineContactList));//联系人信息
                System.out.println(mapper.writeValueAsString(prevWmsCreCustomerChangeLineContactList).length());
	        }
	        //放款申请
	        if("3".equals(queryInfo.getLog_type())) {
	            
	        }
	        //补录
            if("4".equals(queryInfo.getLog_type())) {
                //保存修改日志
                WmsCreCreditHead wmsCreCreditHeadPrev = this.wmscrecreditheadDao_m.get(queryInfo.getWms_cre_credit_head_id());
                wmsCreCreditHeadLog.setWms_cre_credit_head_json(mapper.writeValueAsString(wmsCreCreditHeadPrev));//单据信息
                
                List<Map<String, Object>> prevWmsCreCreditLineCustomerChangeHeadList = 
                        this.wmscrecreditlinecustomerchangeheadDao_m.search(prevParamMap);//客户信息
                wmsCreCreditHeadLog.setWms_cre_credit_line_customer_change_head_json(
                        mapper.writeValueAsString(prevWmsCreCreditLineCustomerChangeHeadList));
                
                if(prevWmsCreCreditLineCustomerChangeHeadList != null && prevWmsCreCreditLineCustomerChangeHeadList.size() > 0) {
                    Integer wms_cre_credit_line_customer_change_head_id = 
                            (Integer)prevWmsCreCreditLineCustomerChangeHeadList.get(0).get("wms_cre_credit_line_customer_change_head_id");
                    prevParamMap = new HashMap<String, Object>();
                    prevParamMap.put("wms_cre_credit_line_customer_change_head_id", wms_cre_credit_line_customer_change_head_id);
                    
                    List<Map<String, Object>> prevWmsCreCustomerChangeLineHouseinfoList = 
                            this.wmscrecustomerchangelinehouseinfoDao_m.search(prevParamMap);//房产信息
                    
                    wmsCreCreditHeadLog.setWms_cre_customer_change_line_houseinfo_json(
                            mapper.writeValueAsString(prevWmsCreCustomerChangeLineHouseinfoList));
                }
            }
	        wmsCreCreditHeadLogDao.save(wmsCreCreditHeadLog);
	    }
	}
	/**
	 * arraylist 去除重复项
	 * @param list
	 * @return ArrayList
	 * @author baisong
	 */
	private List RemoveSame(List<Map<String, Object>>  list)
    {
		try{
			//Constructing HashSet using list
			HashSet<Map<String,Object>> set = new HashSet<Map<String,Object>>(list);
			//Constructing list using set
			ArrayList<Map<String,Object>> listWithoutDuplicateElements = new ArrayList<Map<String,Object>>(set);
			//Printing list
			return listWithoutDuplicateElements;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}
	}

	@Override
	public WmsCreCreditHead getwmscrecredit(Integer wms_cre_credit_head_id) {
		return wmscrecreditheadDao_m.get(wms_cre_credit_head_id);
	}
    /**
     * Description : 获取流程的版本号
     * 
     * @param String
     * @return 
     * @author baisong
     * @date 2016-10-17
     */
	@Override
	public Map<String,Object> getWorkFlowVersion(WmsHouseCreditWorkFlowVO wDebtWorkFlowVO){
		Map<String,Object> map=new HashMap<>();
		String version = wmsLoanWorkFlowService.getVersion_(wDebtWorkFlowVO.getTaskId());
		map.put("version", version);
		return map;
	}
    /**
     * 根据菜单url获取当前人的权限
     * 
     * @return String  人员可以查看的部门id
     * @author baisong
     * @date 2016-10-19
     */
	@Override
	public String queryChildrenDeptInfo(Map<String,Object> map){
		String reuslt="";
		List<Map<String,Object>> list=sysMenuDao.queryChildrenDeptInfo(map);
		if(list!=null&&list.size()>0){
			reuslt=list.get(0).get("childrendept").toString();
		}
		return reuslt;
	}

    /**
     * 
     * @Title: getTaskInfo
     * @Description: (根据业务主键id获取task信息)
     * @param wDebtWorkFlowVO
     * @return 
     * @author: baisong
     * @time:2016年12月19日 下午1:57:46
     * history:
     * 1、2016年12月19日 baisong 创建方法
     */
    @Override
    public Map<String, Object> getTaskInfo(WmsHouseCreditWorkFlowVO wDebtWorkFlowVO)
    {
        Map<String, Object> map = new HashMap<>();
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 业务主键
        workflowSearchInfoHelp.setBusinessKey(wDebtWorkFlowVO.getBusinessId());
        // 节点名称
        workflowSearchInfoHelp.setTaskName(wDebtWorkFlowVO.getDebtkey());
        Task task = workflowService.getTaskInfo(workflowSearchInfoHelp);
        if (task != null)
        {
            map.put("taskId", task.getId());
        }
        // 如果是合同签订则改变查询条件（因UPHOUSINGLOANPROCESS和PERFECTHOUSINGLOANPROCESS版本合同节点名称不一致的问题）
        else if (task == null && WorkflowConstantHelp.UPHOUSINGLOANPROCESS_HTQD.equals(wDebtWorkFlowVO.getDebtkey()))
        {
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_QDHT);
            task = workflowService.getTaskInfo(workflowSearchInfoHelp);
        }
        if (task != null)
        {
            map.put("taskId", task.getId());
        }
        return map;
    }

    /**
     * @Title: copyHeadTableInfo
     * @Description: (组合贷复制所有表)
     * @param queryInfo
     * @param user
     * @return 
     * @author: baisong
     * @time:2016年12月23日 下午4:56:19
     * @see com.zx.emanage.cremanage.service.IWmsCreCreditHeadService#copyHeadTableInfo(com.zx.emanage.cremanage.vo.WmsCreCreditHeadSearchBeanVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2016年12月23日 baisong 创建方法
    */
    @Override
    public Map<String, Object> copyHeadTableInfo(WmsCreCreditCopyBeanVO queryInfo, UserBean user)
    {
        // 新保存后主键
        Integer wms_cre_credit_head_id = 0; // 贷款主表主键
        List<Integer> wcustomer_change_head_id_list =new ArrayList<>();// 客户信息变更主表

        // 旧表主键
        List<Integer>  old_wcustomer_change_head_id_list = new ArrayList<>();// 客户信息变更主表--旧
        // 贷款主表
        WmsCreCreditHead wHead = wmscrecreditheadDao_m.get(queryInfo.getWms_cre_credit_head_id());
        // 数据不为空
        if (wHead != null)
        {
            wHead.setWms_cre_credit_head_id(null);
            //申请金额
            wHead.setCredit_limit(queryInfo.getCredit_limit());
            // 期限
            wHead.setMax_repayment_time_limit(queryInfo.getMax_repayment_time_limit());
            // 单据状态
            wHead.setBill_status(queryInfo.getBill_status());
            // 产品
            wHead.setCre_loan_type(queryInfo.getCre_loan_type());
            // 利率
            wHead.setLoan_interest_rate(queryInfo.getLoan_interest_rate());
            // 终审金额
            wHead.setAppro_limit(queryInfo.getAppro_limit());
            // 组合贷主键
            wHead.setWms_cre_credit_group_id(queryInfo.getWms_cre_credit_group_id());
            // 是否存在流程1有0没有
            wHead.setIs_workflow("0");
            // 时间是否是当前时间1为是 0或者空就是原始时间
            if ("1".equals(queryInfo.getIs_now()))
            {
                wHead.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
            }
            wHead.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
            wHead.setLast_update_user_id(user.getUserId());
            // 房贷编号
            if (queryInfo.getBill_code() == null || "".equals(queryInfo.getBill_code()))
            {
                wHead.setBill_code(CodeNoUtil.getHouseCreCode());
            }
            else
            {
                wHead.setBill_code(queryInfo.getBill_code());
            }
            wHead.setIs_group_flag(queryInfo.getIs_group_flag());
            wmscrecreditheadDao_m.save(wHead);
            wms_cre_credit_head_id = wHead.getWms_cre_credit_head_id();
        }
        // 房贷——抵押房产信息
        WmsCreHousingCustomerHouse customerHouse = wmsCreHousingCustomerHouseDao.getCopyInfo(queryInfo.getWms_cre_credit_head_id());

        // 客户信息表更表
        List<WmsCreCreditLineCustomerChangeHead> wChangeHeadList = wmscrecreditlinecustomerchangeheadDao_m.getCopyInfo(queryInfo.getWms_cre_credit_head_id());
        // 数据不为空
        if (wChangeHeadList != null && wChangeHeadList.size()>0)
        {
            for(WmsCreCreditLineCustomerChangeHead wChangeHead :  wChangeHeadList){
                wChangeHead.setWms_cre_credit_head_id(wms_cre_credit_head_id);
                Integer wms_cre_credit_line_customer_change_head_id =wChangeHead.getWms_cre_credit_line_customer_change_head_id(); // 贷款主表主键
                // 旧记录主键记录
                old_wcustomer_change_head_id_list.add(wChangeHead.getWms_cre_credit_line_customer_change_head_id());
                wChangeHead.setWms_cre_credit_line_customer_change_head_id(null);
                wmscrecreditlinecustomerchangeheadDao_m.save(wChangeHead);
                //新纪录主键  
                wcustomer_change_head_id_list.add(wChangeHead.getWms_cre_credit_line_customer_change_head_id());
                /**********处理客户相关数据***********/
                // 客户公司信息
                List<WmsCreCustomerChangeLineCompany> wCompanyList = wmsCreCustomerChangeLineCompanyDao.getCopyInfo(wms_cre_credit_line_customer_change_head_id);
                // 数据不为空
                if (wCompanyList != null && wCompanyList.size() > 0)
                {
                    for (WmsCreCustomerChangeLineCompany wCompany : wCompanyList)
                    {
                        wCompany.setWms_cre_customer_change_line_company_id(null);
                        wCompany.setWms_cre_credit_line_customer_change_head_id(wChangeHead.getWms_cre_credit_line_customer_change_head_id());

                    }
                    wmsCreCustomerChangeLineCompanyDao.saveBatch(wCompanyList);
                }
                // 客户联系人信息
                List<WmsCreCustomerChangeLineContact> changeLineContactList = wmscrecustomerchangelinecontactDao_m.getCopyInfo(wms_cre_credit_line_customer_change_head_id);
                // 数据不为空
                if (changeLineContactList != null && changeLineContactList.size() > 0)
                {
                    for (WmsCreCustomerChangeLineContact changeLineContact : changeLineContactList)
                    {
                        changeLineContact.setWms_cre_customer_change_line_contact_id(null);
                        changeLineContact.setWms_cre_credit_head_id(wms_cre_credit_head_id);
                        changeLineContact.setWms_cre_credit_line_customer_change_head_id(wChangeHead.getWms_cre_credit_line_customer_change_head_id());

                    }
                    wmscrecustomerchangelinecontactDao_m.saveBatch(changeLineContactList);
                }
                // 客户子女变更信息
                List<WmsCusCustomerChangeChild> changeChildList = wmsCusCustomerChangeChildDao.getCopyInfo(wms_cre_credit_line_customer_change_head_id);
                // 数据不为空
                if (changeChildList != null && changeChildList.size() > 0)
                {
                    for (WmsCusCustomerChangeChild changeChild : changeChildList)
                    {
                        changeChild.setWms_cus_customer_change_child_id(null);
                        changeChild.setWms_cre_credit_line_customer_change_head_id(wChangeHead.getWms_cre_credit_line_customer_change_head_id());
                    }
                    wmsCusCustomerChangeChildDao.saveBatch(changeChildList);
                }
                // 客户工作信息
                List<WmsCreCustomerChangeLineWorkinfo> workinfoList = wmscrecustomerchangelineworkinfoDao_m.getCopyInfo(wms_cre_credit_line_customer_change_head_id);
                // 数据不为空
                if (workinfoList != null && workinfoList.size() > 0)
                {
                    for (WmsCreCustomerChangeLineWorkinfo workinfo : workinfoList)
                    {
                        workinfo.setWms_cre_customer_change_line_workinfo_id(null);
                        workinfo.setWms_cre_credit_line_customer_change_head_id(wChangeHead.getWms_cre_credit_line_customer_change_head_id());
                    }
                    wmscrecustomerchangelineworkinfoDao_m.saveBatch(workinfoList);
                }
                // 客户房产信息
                List<WmsCreCustomerChangeLineHouseinfo> houseinfoList = wmscrecustomerchangelinehouseinfoDao_m.getCopyInfo(wms_cre_credit_line_customer_change_head_id);
                // 数据不为空
                if (houseinfoList != null && houseinfoList.size() > 0)
                {
                    for (WmsCreCustomerChangeLineHouseinfo houseinfo : houseinfoList)
                    {
                        // 判断是否是之前抵押的房产 true是false否
                        boolean isHouse = false;
                        if (customerHouse != null)
                        {
                            // 判断是否是之前抵押的房产
                            if (customerHouse.getWms_cre_customer_change_line_houseinfo_id().toString().equals(houseinfo.getWms_cre_customer_change_line_houseinfo_id().toString()))
                            {
                                isHouse = true;
                            }
                        }
                        houseinfo.setWms_cre_customer_change_line_houseinfo_id(null);
                        houseinfo.setWms_cre_credit_line_customer_change_head_id(wChangeHead.getWms_cre_credit_line_customer_change_head_id());
                        wmscrecustomerchangelinehouseinfoDao_m.addNewRecordReKey(houseinfo);
                        // 判断是否是之前抵押的房产
                        if (isHouse)
                        {
                            customerHouse.setWms_cre_customer_change_line_houseinfo_id(houseinfo.getWms_cre_customer_change_line_houseinfo_id());
                            customerHouse.setWms_cre_credit_head_id(wms_cre_credit_head_id);
                            customerHouse.setId(null);
                            wmsCreHousingCustomerHouseDao.save(customerHouse);
                        }
                    }
                    // wmscrecustomerchangelineworkinfoDao_m.saveBatch(workinfoList);
                }
                // 客户车产信息
                List<WmsCreCustomerChangeLineCarpinfo> carpinfoList = wmscrecustomerchangelinecarpinfoDao_m.getCopyInfo(wChangeHead.getWms_cre_credit_line_customer_change_head_id());
                // 数据不为空
                if (carpinfoList != null && carpinfoList.size() > 0)
                {
                    for (WmsCreCustomerChangeLineCarpinfo carpinfo : carpinfoList)
                    {
                        carpinfo.setWms_cre_customer_change_line_carpinfo_id(null);
                        carpinfo.setWms_cre_credit_line_customer_change_head_id(wChangeHead.getWms_cre_credit_line_customer_change_head_id());
                    }
                    wmscrecustomerchangelinecarpinfoDao_m.saveBatch(carpinfoList);
                }
                // 贷前附件信息信息
                List<WmsCreRevAtt> revattList = wmsCreRevAttDao.getCopyInfo(wChangeHead.getWms_cre_credit_line_customer_change_head_id());
                // 数据不为空
                if (revattList != null && revattList.size() > 0)
                {
                    for (WmsCreRevAtt revatt : revattList)
                    {
                        revatt.setWms_cre_credit_line_customer_change_head_id(null);
                        revatt.setWms_cre_credit_head_id(wms_cre_credit_head_id);
                        revatt.setWms_cre_credit_line_customer_change_head_id(wChangeHead.getWms_cre_credit_line_customer_change_head_id());

                    }
                    wmsCreRevAttDao.saveBatch(revattList);
                }

            } 
        }
        // 房贷——授信确认
        WmsCreHousingCreditConfirm creditConfirm = wmsCreHousingCreditConfirmDao.getCopyInfo(queryInfo.getWms_cre_credit_head_id());
        // 数据不为空
        if (creditConfirm != null)
        {
            creditConfirm.setWms_cre_housing_credit_confirm_id(null);
            creditConfirm.setWms_cre_credit_head_id(wms_cre_credit_head_id);
            wmsCreHousingCreditConfirmDao.save(creditConfirm);
        }
        // 判断是否是授信确认
        if (creditConfirm != null && "0".equals(creditConfirm.getCredit_confirm_result()))
        {
            // 贷款征信审核——征信总体情况
            List<WmsCreRevCertificateMain> certificateMainList = wmsCreRevCertificateMainDao.getCopyInfo(queryInfo.getWms_cre_credit_head_id());
            // 数据不为空
            if (certificateMainList != null && certificateMainList.size() > 0)
            {
                for (WmsCreRevCertificateMain certificateMain : certificateMainList)
                {

                    certificateMain.setWms_cre_credit_head_id(wms_cre_credit_head_id);
                    for (int i = 0; i < old_wcustomer_change_head_id_list.size(); i++)
                    {
                        if (certificateMain != null && certificateMain.getWms_cre_credit_line_customer_change_head_id() != null && old_wcustomer_change_head_id_list.get(i).toString().equals(certificateMain.getWms_cre_credit_line_customer_change_head_id().toString()))
                        {
                            certificateMain.setWms_cre_credit_line_customer_change_head_id(wcustomer_change_head_id_list.get(i));
                       }
                    }
                    certificateMain.setWms_cre_credit_line_customer_change_head_id(null);
                    certificateMain.setWms_cre_rev_certificate_main_id(null);
                }
                wmsCreRevCertificateMainDao.saveBatch(certificateMainList);
            }
            // 贷款征信审核——模型
            WmsCreRevCertificateModel certificateModel = wmsCreRevCertificateModelDao.getCopyInfo(queryInfo.getWms_cre_credit_head_id());
            // 数据不为空
            if (certificateModel != null)
            {
                certificateModel.setWms_cre_rev_certificate_model_id(null);
                certificateModel.setWms_cre_credit_head_id(wms_cre_credit_head_id);
                wmsCreRevCertificateModelDao.save(certificateModel);
            }

            // 贷款电审审核——电审总体情况
            List<WmsCreRevPhoneMain> phoneMainList = wmsCreRevPhoneMainDao.getCopyInfo(queryInfo.getWms_cre_credit_head_id());
            // 数据不为空
            if (phoneMainList != null && phoneMainList.size() > 0)
            {
                for (WmsCreRevPhoneMain phoneMain : phoneMainList)
                {

                    phoneMain.setWms_cre_credit_head_id(wms_cre_credit_head_id);
                    for (int i = 0; i < old_wcustomer_change_head_id_list.size(); i++)
                    {
                        // 判断主键是否相等
                        if (phoneMain != null && phoneMain.getWms_cre_credit_line_customer_change_head_id() != null && old_wcustomer_change_head_id_list.get(i).toString().equals(phoneMain.getWms_cre_credit_line_customer_change_head_id().toString()))
                        {
                            phoneMain.setWms_cre_credit_line_customer_change_head_id(wcustomer_change_head_id_list.get(i));
                        }
                    }
                    phoneMain.setWms_cre_credit_line_customer_change_head_id(null);
                    phoneMain.setWms_cre_rev_phone_main_id(null);
                }
                wmsCreRevPhoneMainDao.saveBatch(phoneMainList);
            }
            // 贷款电审审核——模型
            WmsCreRevPhoneModel phoneModel = wmsCreRevPhoneModelDao.getCopyInfo(queryInfo.getWms_cre_credit_head_id());
            // 数据不为空
            if (phoneModel != null)
            {
                phoneModel.setWms_cre_rev_phone_model_id(null);
                phoneModel.setWms_cre_credit_head_id(wms_cre_credit_head_id);
                wmsCreRevPhoneModelDao.save(phoneModel);
            }
            // 贷款电审审核——联系人情况
            List<WmsCreRevPhoneContact> phoneContactList = wmsCreRevPhoneContactDao.getCopyInfo(queryInfo.getWms_cre_credit_head_id());
            // 数据不为空
            // 数据不为空
            if (phoneContactList != null && phoneContactList.size() > 0)
            {
                for (WmsCreRevPhoneContact phoneContact : phoneContactList)
                {
                    phoneContact.setWms_cre_credit_line_customer_change_head_id(null);
                    phoneContact.setWms_cre_credit_head_id(wms_cre_credit_head_id);
                    for (int i = 0; i < wcustomer_change_head_id_list.size(); i++)
                    {
                        // 判断主键是否相等
                        if (phoneContact != null && phoneContact.getWms_cre_credit_line_customer_change_head_id() != null && wcustomer_change_head_id_list.get(i).toString().equals(phoneContact.getWms_cre_credit_line_customer_change_head_id().toString()))
                        {
                            phoneContact.setWms_cre_credit_line_customer_change_head_id(old_wcustomer_change_head_id_list.get(i));
                        }
                    }
                }
                wmsCreRevPhoneContactDao.saveBatch(phoneContactList);
            }
        }
        // 贷前附件信息信息
        List<WmsCreCreditLineCustomerDataAttachment> dataAttachmentList = wmscrecreditlinecustomerdataattachmentDao_m.getCopyInfo(queryInfo.getWms_cre_credit_head_id());
        // 数据不为空
        if (dataAttachmentList != null && dataAttachmentList.size() > 0)
        {
            for (WmsCreCreditLineCustomerDataAttachment dataAttachment : dataAttachmentList)
            {
                dataAttachment.setWms_cre_credit_line_customer_data_attachment_id(null);
                dataAttachment.setWms_cre_credit_head_id(wms_cre_credit_head_id);
            }
            wmscrecreditlinecustomerdataattachmentDao_m.saveBatch(dataAttachmentList);
        }
        // 房贷——办件审核组——验房单
        WmsCreHousingCheck housingCheck = wmsCreHousingCheckDao.getCopyInfo(queryInfo.getWms_cre_credit_head_id());
        // 数据不为空
        if (housingCheck != null)
        {
            housingCheck.setWms_cre_housing_check_id(null);
            housingCheck.setWms_cre_credit_head_id(wms_cre_credit_head_id);
            wmsCreHousingCheckDao.save(housingCheck);
        }
        // 房贷——办件审核组——验房单
        WmsCreHousingAssessment housingAssessment = wmsCreHousingAssessmentDao.getCopyInfo(queryInfo.getWms_cre_credit_head_id());
        // 数据不为空
        if (housingAssessment != null)
        {
            housingAssessment.setWms_cre_housing_assessment_id(null);
            housingAssessment.setWms_cre_credit_head_id(wms_cre_credit_head_id);
            wmsCreHousingAssessmentDao.save(housingAssessment);
        }

        // 房贷——贷款上传信息表
        WmsCreHousingFileInfo housingFileInfo = wmsCreHousingFileInfoDao.getCopyInfo(queryInfo.getWms_cre_credit_head_id());
        // 数据不为空
        if (housingFileInfo != null)
        {
            housingFileInfo.setWms_cre_housing_file_info_id(null);
            housingFileInfo.setWms_cre_credit_head_id(wms_cre_credit_head_id);
            wmsCreHousingFileInfoDao.save(housingFileInfo);
        }
        // 房贷——贷款初审评估单
        WmsCreHousingTrialAssessment housingTrialAssessment = wmsCreHousingTrialAssessmentDao.getCopyInfo(queryInfo.getWms_cre_credit_head_id());
        // 数据不为空
        if (housingTrialAssessment != null)
        {
            housingTrialAssessment.setWms_cre_housing_trial_assessment_id(null);
            housingTrialAssessment.setWms_cre_credit_head_id(wms_cre_credit_head_id);
            wmsCreHousingTrialAssessmentDao.save(housingTrialAssessment);
        }
        // 房贷——贷款申请上传资料表
        List<WmsCreHousingApplyAtt> applyAttList = wmsCreHousingApplyAttDao.getCopyInfo(queryInfo.getWms_cre_credit_head_id());
        // 数据不为空
        if (applyAttList != null && applyAttList.size() > 0)
        {
            for (WmsCreHousingApplyAtt applyAtt : applyAttList)
            {
                applyAtt.setWms_cre_housing_apply_att_id(null);
                // 判断上传附件信息表
                if (housingFileInfo != null && housingFileInfo.getWms_cre_housing_file_info_id() != null)
                {
                    applyAtt.setWms_cre_housing_file_info_id(housingFileInfo.getWms_cre_housing_file_info_id());
                }
                else
                {
                    applyAtt.setWms_cre_housing_file_info_id(null);
                }
                applyAtt.setWms_cre_credit_head_id(wms_cre_credit_head_id);
            }
            wmsCreHousingApplyAttDao.saveBatch(applyAttList);
        }
        // 房贷——房产缴费
        WmsCreHousingPayment housingPayment = wmsCreHousingPaymentDao.getCopyInfo(queryInfo.getWms_cre_credit_head_id());
        // 数据不为空
        if (housingPayment != null)
        {
            housingPayment.setWms_cre_housing_payment_id(null);
            housingPayment.setWms_cre_credit_head_id(wms_cre_credit_head_id);
            housingPayment.setPayment_amount(new BigDecimal(0));
            wmsCreHousingPaymentDao.save(housingPayment);
        }
        // 房贷——终审审批
        WmsCreCreditFinalAppl wmsCreCreditFinalAppl = wmsCreCreditFinalApplDao.getCopyInfo(queryInfo.getWms_cre_credit_head_id());
        // 数据不为空
        if (wmsCreCreditFinalAppl != null)
        {
            wmsCreCreditFinalAppl.setWms_cre_credit_final_appl_id(null);
            wmsCreCreditFinalAppl.setWms_cre_credit_head_id(wms_cre_credit_head_id);
            wmsCreCreditFinalApplDao.save(wmsCreCreditFinalAppl);
        }
        // 房贷——面签审批
        WmsCreCreditVisaAppl wmsCreCreditVisaAppl = wmsCreCreditVisaApplDao.getCopyInfo(queryInfo.getWms_cre_credit_head_id());
        // 数据不为空
        if (wmsCreCreditVisaAppl != null)
        {
            wmsCreCreditVisaAppl.setWms_cre_credit_visa_appl_id(null);
            wmsCreCreditVisaAppl.setWms_cre_credit_head_id(wms_cre_credit_head_id);
            wmsCreCreditVisaApplDao.save(wmsCreCreditVisaAppl);
        }
        WmsCreHousingApprovalInfo wmsCreHousingApprovalInfo = new WmsCreHousingApprovalInfo();
        wmsCreHousingApprovalInfo.setWms_cre_credit_head_id(wHead.getWms_cre_credit_head_id());
        wmsCreHousingApprovalInfo.setApproval_type("4");// 组合贷拆分
        wmsCreHousingApprovalInfo.setApproval_result("true");
        wmsCreHousingApprovalInfo.setApproval_user_id(user.getUserId());
        wmsCreHousingApprovalInfo.setApproval_user_name(user.getRealname());
        wmsCreHousingApprovalInfo.setApproval_time(new Timestamp(System.currentTimeMillis()));
        wmsCreHousingApprovalInfo.setApproval_advice("拆分成组合贷款");
        wmsCreHousingApprovalInfo.setApproval_task_type(queryInfo.getApproval_task_type());
        wmsCreHousingApprovalInfo.setApproval_task_code(queryInfo.getApproval_task_code());
        wmsCreHousingApprovalInfo.setEnable_flag("1");
        wmsCreHousingApprovalInfoDao.saveInfo(wmsCreHousingApprovalInfo);
        Map<String,Object> map=new HashMap<>();
        map.put("wHead", wHead);
        return map;
    }

    /**
     * @Title: wmsPerfectContract
     * @Description: TODO(查询主表信息)
     * @param wms_cre_credit_head_id
     * @return 
     * @author: jiaodelong
     * @time:2016年12月26日 下午3:55:33
     * @see com.zx.emanage.cremanage.service.IWmsCreCreditHeadService#wmsPerfectContract(java.lang.Integer)
     * history:
     * 1、2016年12月26日 jiaodelong 创建方法
    */
    @Override
    public Map<String, Object> wmsPerfectContract(Integer wms_cre_credit_head_id)
    {
        return wmscrecreditheadDao_m.getApproLimitGroup(wms_cre_credit_head_id);
    }
    
    /**
     * 
     * @Title: getWmsCreCreditHeadList
     * @Description: 查询贷款主表list
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2016年12月28日 上午10:25:59
     * history:
     * 1、2016年12月28日 wangyihan 创建方法
     */
    @Override
    public WmsCreCreditHeadSearchBeanVO getWmsCreCreditHeadList(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if(queryInfo.getWms_cre_credit_group_id() != null) {
            paramMap.put("wms_cre_credit_group_id", queryInfo.getWms_cre_credit_group_id());
        }
        if(StringUtils.isNotEmpty(queryInfo.getCre_type())) {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        if(StringUtils.isNotEmpty(queryInfo.getRepay_status_in())) {
            paramMap.put("repay_status_in", SysTools.getSqlInParam(queryInfo.getRepay_status_in()));
        }
        if(StringUtils.isNotEmpty(queryInfo.getSame_status())) {
            paramMap.put("same_status", queryInfo.getSame_status());
            paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        }
        if(StringUtils.isNotEmpty(queryInfo.getSortname()) && StringUtils.isNotEmpty(queryInfo.getSortorder())) {
            paramMap.put("sortname", queryInfo.getSortname());
            paramMap.put("sortorder", queryInfo.getSortorder());
        }
        queryInfo.setWmsCreCreditHeadList(wmscrecreditheadDao_m.getWmsCreCreditHeadList(paramMap));
        return queryInfo;
    }

    /**
     * @Title: sendInfoAsynchronous
     * @Description:  发送消息-短信、极光、消息中心
     * @author: baisong
     * @time:2017年4月13日 上午11:00:16
     * history:
     * 1、2017年4月13日 baisong 创建方法
     */
    public void sendInfoAsynchronous(final Map<String, Object> sendMap)
    {
        Thread th = new Thread(new Runnable()
        {
            public void run()
            {
                log.info("-------------<!-- 开启线程发送消息 --> start---------");
                getUserAndSendInfo(sendMap);
                log.info("-------------<!-- 开启线程发送消息 --> end---------");
            }
        });
        th.start();
    }
    
    /**
     * 
     * @Title: searchLoanNumberStatisticsList
     * @Description: 查询件数统计列表
     * @param request
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2017年6月15日 下午1:15:55
     * history:
     * 1、2017年6月15日 wangyihan 创建方法
     */
    public WmsCreCreditHeadSearchBeanVO searchLoanNumberStatisticsList(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if(StringUtils.isNotEmpty(queryInfo.getCreate_timestamp_start())) {
            paramMap.put("create_timestamp_start", queryInfo.getCreate_timestamp_start());
        }
        if(StringUtils.isNotEmpty(queryInfo.getCreate_timestamp_end())) {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if(StringUtils.isNotEmpty(queryInfo.getCity())) {
            paramMap.put("city", queryInfo.getCity());
        }
        if(StringUtils.isNotEmpty(queryInfo.getDept_name())) {
            paramMap.put("dept_name", SysTools.getSqlLikeParam(queryInfo.getDept_name()));
        }
        if(StringUtils.isNotEmpty(queryInfo.getSalesman_name())) {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        //初评件数总计
        Integer intern_num_total = 0;
        //验点件数总计
        Integer check_point_num_total = 0;
        //放款件数总计
        Integer loans_num_total = 0;
        //放款金额总计
        Double loans_amount_total = 0.0;
        if(StringUtils.isNotEmpty(queryInfo.getIs_need_paging()) && queryInfo.getIs_need_paging().equals("0")) {
            paramMap.put("offset", null);
            paramMap.put("pagesize", null);
            list = this.wmscrecreditheadDao_m.searchLoanNumberStatisticsList(paramMap);
        } else {
            paramMap.put("offset", queryInfo.getOffset());
            paramMap.put("pagesize", queryInfo.getPagesize());
            list = this.wmscrecreditheadDao_m.searchLoanNumberStatisticsList(paramMap);
        }
        
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), 
                                             wmscrecreditheadDao_m.searchLoanNumberStatisticsListCount(paramMap),
                                             list);
        Map<String, Object> map = bean.getGridData();
        queryInfo.setMap(map);
        return queryInfo;
    }
    
    /**
     * 
     * @Title: searchLoanNumberTotal
     * @Description: 查询件数统计总数
     * @param request
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2017年6月15日 下午1:15:55
     * history:
     * 1、2017年6月15日 wangyihan 创建方法
     */
    public WmsCreCreditHeadSearchBeanVO searchLoanNumberTotal(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if(StringUtils.isNotEmpty(queryInfo.getCreate_timestamp_start())) {
            paramMap.put("create_timestamp_start", queryInfo.getCreate_timestamp_start());
        }
        if(StringUtils.isNotEmpty(queryInfo.getCreate_timestamp_end())) {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if(StringUtils.isNotEmpty(queryInfo.getCity())) {
            paramMap.put("city", queryInfo.getCity());
        }
        if(StringUtils.isNotEmpty(queryInfo.getDept_name())) {
            paramMap.put("dept_name", SysTools.getSqlLikeParam(queryInfo.getDept_name()));
        }
        if(StringUtils.isNotEmpty(queryInfo.getSalesman_name())) {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        
        //初评件数总计
        Integer intern_num_total = 0;
        //验点件数总计
        Integer check_point_num_total = 0;
        //放款件数总计
        Integer loans_num_total = 0;
        //放款金额总计
        Double loans_amount_total = 0.0;
        paramMap.put("offset", null);
        paramMap.put("pagesize", null);
        List<Map<String, Object>> list = this.wmscrecreditheadDao_m.searchLoanNumberStatisticsList(paramMap);
        //计算总计
        for(Map<String, Object> tmpMap: list) {
            intern_num_total += new Integer(tmpMap.get("intern_num").toString());
            check_point_num_total += new Integer(tmpMap.get("check_point_num").toString());
            loans_num_total += new Integer(tmpMap.get("loans_num").toString());
            loans_amount_total += new Double(tmpMap.get("loans_amount").toString());
        }
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("intern_num_total", intern_num_total.toString());
        map.put("check_point_num_total", check_point_num_total.toString());
        map.put("loans_num_total", loans_num_total.toString());
        map.put("loans_amount_total", loans_amount_total.toString());
        queryInfo.setMap(map);
        return queryInfo;
    }
    
    /**
     * 
     * @Title: billStatusStaticsDisp
     * @Description: 系统单据状态统计初始化
     * @param request
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2017年7月6日 下午1:15:55
     * history:
     * 1、2017年7月6日 wangyihan 创建方法
     */
    public WmsCreCreditHeadSearchBeanVO billStatusStaticsDisp(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("effective_time", WorkflowConstantHelp.SYS_BILL_STATUS_STATISTICS_EFFECTIVE_TIME);
        //上单、放款总件数、金额汇总
        Map<String, Object> map = this.wmscrecreditheadDao_m.loansStatisticsAll(paramMap);
        //上单、放款数汇总
        List<Map<String, Object>> list2 = this.wmscrecreditheadDao_m.searchBillStatusStaticsByYear(paramMap);
        Map<String, Object> map_2 = new HashMap<String, Object>();
        map_2.put("Rows", list2);
        
        //单据状态统计
        List<Map<String, Object>> list3 = this.wmscrecreditheadDao_m.loansStatistics(paramMap);
        
        // 存放column的集合
        LinkedList<ColumnInfo> columnList = new LinkedList<ColumnInfo>();
        ColumnInfo columnInfo = new ColumnInfo();
        columnInfo.setName("NO1");
        columnInfo.setWidth(180);
        columnInfo.setFrozen(true);
        // column标题列第一列
        columnList.addFirst(columnInfo);

        List<Map<String, Object>> gridList = new ArrayList<Map<String, Object>>();
        
        // 存放件数信息
        Map<String, Object> map1 = new HashMap<String, Object>();
        // 存放金额(万元)信息
        Map<String, Object> map2 = new HashMap<String, Object>();
        // 存放封装之后的column和grid
        Map<String, Object> map3 = new HashMap<String, Object>();
        // gird第一列显示内容
        map1.put("NO1", "件数");
        map2.put("NO1", "金额(万元)");
        // 遍历集合 将组名等属性封装放到list中 并且将grid第一行已售额度和grid第二行放到map中
        for (int i = 0; i < list3.size(); i++)
        {
            // 封装column 设置各属性
            columnInfo = new ColumnInfo();
            columnInfo.setName(list3.get(i).get("bill_status").toString());
            columnInfo.setDisplay(list3.get(i).get("value_meaning").toString());
            columnInfo.setWidth(120);
            columnList.add(columnInfo);
            // 封装grid第一行 件数
            map1.put(list3.get(i).get("bill_status").toString(), list3.get(i).get("count"));
            // 封装grid第二行 金额(万元)
            map2.put(list3.get(i).get("bill_status").toString(), list3.get(i).get("total_amount"));
        }
        gridList.add(map1);
        gridList.add(map2);
        // map存放column 和 rows
        map3.put("columns", columnList);
        map3.put("Rows", gridList);
        
        resMap.put("map1", map);
        resMap.put("map2", map_2);
        resMap.put("map3", map3);
        queryInfo.setMap(resMap);
        
        return queryInfo;
    }
    
    /**
     * 
     * @Title: searchBillStatusStaticsByTime
     * @Description: 根据日期查询上单、放款数汇总
     * @param request
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2017年7月6日 下午1:15:55
     * history:
     * 1、2017年7月6日 wangyihan 创建方法
     */
    public WmsCreCreditHeadSearchBeanVO searchBillStatusStaticsByTime(WmsCreCreditHeadSearchBeanVO queryInfo, UserBean user) {
        Date now_time_date = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        String now_time = format1.format(now_time_date);
        String statistics_time = queryInfo.getStatistics_time();
        Map<String, Object> resMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("effective_time", WorkflowConstantHelp.SYS_BILL_STATUS_STATISTICS_EFFECTIVE_TIME);
        paramMap.put("statistics_time", queryInfo.getStatistics_time());
        List<Map<String, Object>> res_list = new ArrayList<Map<String, Object>>();
        //按年统计，例如2017年的所有月份
        if(statistics_time.length() == 4) {
            List<Map<String, Object>> list = this.wmscrecreditheadDao_m.searchBillStatusStaticsByMonth(paramMap);
            List<String> time_list = new ArrayList<String>();
            String tmp_time = "";
            for(Integer i = 1; i <= 12; i++) {
                tmp_time = i < 10 ? (statistics_time + "-0" + i.toString()) : statistics_time + "-" + i.toString();
                try
                {
                    //判断是否小于统计开始时间
                    if(format1.parse(tmp_time).getTime() 
                        < format1.parse(WorkflowConstantHelp.SYS_BILL_STATUS_STATISTICS_EFFECTIVE_TIME.substring(0, 7)).getTime()) {
                        continue;
                    }
                    //判断是否大于当前时间
                    if(format1.parse(tmp_time).getTime() 
                        > now_time_date.getTime()) {
                        break;
                    }
                    time_list.add(tmp_time);
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }
            }
            Map<String, Object> map_obj = new HashMap<String, Object>();
            for(String s : time_list) {
                map_obj = new HashMap<String, Object>();
                map_obj.put("time", Integer.parseInt(s.substring(6, 7)) + "月");
                map_obj.put("real_time", s);
                for(Map<String, Object> tmp_map : list) {
                    if(s.equals(tmp_map.get("time").toString())) {
                        if(tmp_map.get("type").equals("1")) {
                            map_obj.put("count1", tmp_map.get("data"));
                        } else if(tmp_map.get("type").equals("2")) {
                            map_obj.put("count2", tmp_map.get("data"));
                        } else if(tmp_map.get("type").equals("3")) {
                            map_obj.put("sum", tmp_map.get("data"));
                        }
                    }
                }
                if(!map_obj.containsKey("count1")) {
                    map_obj.put("count1", 0);
                }
                if(!map_obj.containsKey("count2")) {
                    map_obj.put("count2", 0);
                }
                if(!map_obj.containsKey("sum")) {
                    map_obj.put("sum", 0);
                }
                res_list.add(map_obj);
            }
        }
        //按月统计，例如2017年5月的所有天
        else if(statistics_time.length() == 7) {
            List<Map<String, Object>> list = this.wmscrecreditheadDao_m.searchBillStatusStaticsByDay(paramMap);
            List<String> time_list = new ArrayList<String>();
            
            Calendar cal = Calendar.getInstance();  
            try
            {
                cal.setTime(format1.parse(statistics_time));
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            int year = cal.get(Calendar.YEAR);  
            int m = cal.get(Calendar.MONTH);  
            int dayNumOfMonth = getDaysByYearMonth(year, m);  
            cal.set(Calendar.DAY_OF_MONTH, 1);// 从一号开始  
          
            String tmp_time = "";
            for (int i = 0; i < dayNumOfMonth; i++, cal.add(Calendar.DATE, 1)) {  
                Date d = cal.getTime();  
                try
                {
                    //判断是否小于统计开始时间
                    if(d.getTime() 
                        < format2.parse(WorkflowConstantHelp.SYS_BILL_STATUS_STATISTICS_EFFECTIVE_TIME).getTime()) {
                        continue;
                    }
                    //判断是否大于当前时间
                    if(d.getTime() 
                        > now_time_date.getTime()) {
                        break;
                    }
                    tmp_time = format2.format(d);
                    time_list.add(tmp_time);
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }
                
            }  
            
            Map<String, Object> map_obj = new HashMap<String, Object>();
            for(String s : time_list) {
                map_obj = new HashMap<String, Object>();
                map_obj.put("time", Integer.parseInt(s.substring(8, 10)) + "日");
                map_obj.put("real_time", s);
                for(Map<String, Object> tmp_map : list) {
                    if(s.equals(tmp_map.get("time").toString())) {
                        if(tmp_map.get("type").equals("1")) {
                            map_obj.put("count1", tmp_map.get("data"));
                        } else if(tmp_map.get("type").equals("2")) {
                            map_obj.put("count2", tmp_map.get("data"));
                        } else if(tmp_map.get("type").equals("3")) {
                            map_obj.put("sum", tmp_map.get("data"));
                        }
                    }
                }
                if(!map_obj.containsKey("count1")) {
                    map_obj.put("count1", 0);
                }
                if(!map_obj.containsKey("count2")) {
                    map_obj.put("count2", 0);
                }
                if(!map_obj.containsKey("sum")) {
                    map_obj.put("sum", 0);
                }
                res_list.add(map_obj);
            }
        }
        resMap.put("Rows", res_list);
        queryInfo.setMap(resMap);
        return queryInfo;
    }

    //获取指定月份的天数  
    public static int getDaysByYearMonth(int year, int month) {  
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    } 
    
}