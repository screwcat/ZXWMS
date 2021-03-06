package com.zx.emanage.workflow.util;

import java.lang.reflect.Field;

/**
 * 工作流流程定义中以FormKey发布的表单信息中相关的字段信息帮助类
 * 
 * @author Hancd
 */
public class WorkflowConstantHelp
{

    // --------------流程定义的KEY---------------
    // --信贷流程
    public static final String CREDITPROCESS = "creditProcess";
    // -- 提前还款流程
    public static final String BEFOREREPAYPROCESS = "beforeRepayProcess";
    // -- 转件流程
    public static final String TRANSFERPAPERPROCESS = "transferPaperProcess";
    // -- 房贷流程
    public static final String HOUSINGLOANPROCESS = "housingLoanProcess";
    // -- 理财赎回流程
    public static final String FINANCIALREDEMPTIONPROCESS = "financialRedemptionProcess";
    // -- 理财pad端赎回流程
    public static final String PADFINANCIALREDEMPTIONPROCESS = "PadFinancialRedeemption";
    // -- 车贷流程
    public static final String CARLOANPROCESS ="carLoanProcess";
    // -- 债权调整流程
    public static final String DEBTADJUSTMENTPROCESS ="debtAdjustmentProcess";
    // -- 产品变更流程
    public static final String PROINVECHANGEPROCESS ="proInveChangeProcess";
    //--  理财上单流程
    public static final String FINANCIALSINGLEROCESS="financialSingleProcess";
    // -- 新房贷流程
    public static final String PERFECTHOUSINGLOANPROCESS = "perfectHousingLoanProcess";
    // -- 新房贷流程-2016/5/10
    public static final String UPHOUSINGLOANPROCESS = "upHousingLoanProcess";
    
    // -- 工资设定流程
    public static final String INVESALARYSET = "inveSalarySet";

    // ---------------信用贷款流程节点名称定义--------------
    public static final String CREDITPROCESS_DKFH = "贷款复核";

    public static final String CREDITPROCESS_CXSQ = "重新申请";

    public static final String CREDITPROCESS_LSSP = "流水审批";

    public static final String CREDITPROCESS_XXSP = "信息审批";

    public static final String CREDITPROCESS_DSSP = "电审审批";

    public static final String CREDITPROCESS_ZXSP = "证信审批";

    public static final String CREDITPROCESS_YDSP = "验点审批";

    public static final String CREDITPROCESS_BJ = "补件";

    public static final String CREDITPROCESS_DKZS = "贷款终审";

    public static final String CREDITPROCESS_ZSMQ = "终审面签";

    public static final String CREDITPROCESS_HTQD = "合同签订";

    public static final String CREDITPROCESS_FKSQ = "放款申请";

    public static final String CREDITPROCESS_FKSQSP = "放款申请审批";

    public static final String CREDITPROCESS_FKQR = "放款确认";

    public static final String CREDITPROCESS_ZZ = "终止";

    public static final String CREDITPROCESS_FYXD = "复议修订";

    // --------------提前还款流程节点名称定义----------------
    public static final String BEFOREREPAYPROCESS_DHSP = "贷后审批";

    public static final String BEFOREREPAYPROCESS_XSSP = "销售审批";

    public static final String BEFOREREPAYPROCESS_CWSP = "财务审批";

    public static final String BEFOREREPAYPROCESS_CXSQ = "重新申请";

    // --------------转件流程节点名称定义-------------------
    public static final String TRANSFERPAPERPROCESS_DHSP = "贷后审批";

    public static final String TRANSFERPAPERPROCESS_XSSP = "销售审批";

    public static final String TRANSFERPAPERPROCESS_CWSP = "财务审批";

    public static final String TRANSFERPAPERPROCESS_FWSP = "法务审批";

    public static final String TRANSFERPAPERPROCESS_CXSQ = "重新申请";

    // --------------房贷流程节点名称定义-------------------
    public static final String HOUSINGLOANPROCESS_DKFH = "贷款复核";

    public static final String HOUSINGLOANPROCESS_CXSQ = "重新申请";

    public static final String HOUSINGLOANPROCESS_LSSP = "流水审批";

    public static final String HOUSINGLOANPROCESS_BJSP = "办件审批";

    public static final String HOUSINGLOANPROCESS_ZXSP = "证信审批";

    public static final String HOUSINGLOANPROCESS_DSSP = "电审审批";

    public static final String HOUSINGLOANPROCESS_XXSP = "信息审批";

    public static final String HOUSINGLOANPROCESS_BJ = "补件";

    public static final String HOUSINGLOANPROCESS_DKZS = "贷款终审";

    public static final String HOUSINGLOANPROCESS_QDHT = "签订合同";

    public static final String HOUSINGLOANPROCESS_GZ = "公证";

    public static final String HOUSINGLOANPROCESS_TX = "他项";

    public static final String HOUSINGLOANPROCESS_FKSQ = "放款申请";

    public static final String HOUSINGLOANPROCESS_FKSQSP = "放款申请审批";

    public static final String HOUSINGLOANPROCESS_FKQR = "放款确认";

    // ---------------赎回流程节点名称定义------------------
    public static final String FINANCIALREDEMPTIONPROCESS_JLSP = "经理审批";

    public static final String FINANCIALREDEMPTIONPROCESS_FZJLSP = "副总经理审批";

    public static final String FINANCIALREDEMPTIONPROCESS_ZJLSP = "总经理审批";

    public static final String FINANCIALREDEMPTIONPROCESS_CXXD = "重新修订";

    public static final String FINANCIALREDEMPTIONPROCESS_HK = "回款";

    public static final String FINANCIALREDEMPTIONPROCESS_TPSQ = "特批申请";
      
    // ---------------赎回流程定义常用变量定义--------------
    public static final String FINANCIAL_TPSQ = "tpsq";// 特批申请

    // ---------------车贷流程节点名称定义--------------
    public static final String CARLOANPROCESS_CDFH ="车贷复核";
    
    public static final String CARLOANPROCESS_PGSH ="评估审核";
    
    public static final String CARLOANPROCESS_FHTH ="复核退回";
    
    public static final String CARLOANPROCESS_BJSP ="办件审批";
    
    public static final String CARLOANPROCESS_BJ="补件";
    
    public static final String CARLOANPROCESS_ZSSP ="终审审批";
    
    public static final String CARLOANPROCESS_HTQD ="合同签订";
    
    public static final String CARLOANPROCESS_FKSQ ="放款申请";
    
    public static final String CARLOANPROCESS_FKSP ="放款审批";
    
    public static final String CARLOANPROCESS_FKQR ="放款确认";
    
    //-----------------债权调整流程定义-----------------
    public static final String DEBTADJUSTMENTPROCESS_JLSP="经理审批";
    
    public static final String DEBTADJUSTMENTPROCESS_FZJLSP="副总经理审批";
    
    public static final String DEBTADJUSTMENTPROCESS_ZJLSP="总经理审批";

    public static final String DEBTADJUSTMENTPROCESS_CWQR="财务确认";
    
    public static final String DEBTADJUSTMENTPROCESS_XD="修订";
    
    public static final String DEBTADJUSTMENTPROCESS_TPSQ="特批申请";
    
    public static final String DEBTADJUSTMENTPROCESS_ZQTZ="债权调整";
    
    public static final String DEBTADJUSTMENTPROCESS_XYQD="协议签订";
    
    // -----------------产品变更流程定义-----------------
    public static final String PROINVECHANGEPROCESS_JLSP ="经理审批";
    
    public static final String PROINVECHANGEPROCESS_FZJLSP ="副总经理审批";
    
    public static final String PROINVECHANGEPROCESS_ZJLSP="总经理审批";

    public static final String PROINVECHANGEPROCESS_BGQR="变更确认";
    
    public static final String PROINVECHANGEPROCESS_XD="修订";
    
    public static final String PROINVECHANGEPROCESS_TPSQ="特批申请";
    
    public static final String PROINVECHANGEPROCESS_ZQTZ="债权调整";
    
    public static final String PROINVECHANGEPROCESS_XYQD="协议签订";
    
    //------------------理财上单流程定义-------------------
    public static final String FINANCIALSINGLEROCESS_FH="复核";
    
    public static final String FINANCIALSINGLEROCESS_FHXD="复核修订";
    
    public static final String FINANCIALSINGLEROCESS_ZF="支付";
    
    public static final String FINANCIALSINGLEROCESS_ZFTH = "待修订（支付退回）";
    
    public static final String FINANCIALSINGLEROCESS_QY = "待签约";

    public static final String FINANCIALSINGLEROCESS_QYTH = "待修订（签约退回）";

    public static final String FINANCIALSINGLEROCESS_ZGQR="主管确认";
    
    public static final String FINANCIALSINGLEROCESS_SHTH = "待修订（审核退回）";
    
    public static final String FINANCIALSINGLEROCESS_CD = "待修订（主动撤单）";

    public static final String FINANCIALSINGLEROCESS_XYQD="协议签订";
    
    public static final String FINANCIALSINGLEROCESS_KHQR="客户确认";
    
    public static final String FINANCIALSINGLEROCESS_TDTH="退单退回";
    
    public static final String FINANCIALSINGLEROCESS_TDQR="退单确认";

    // --------------新房贷流程节点名称定义-------------------
    public static final String PERFECTHOUSINGLOANPROCESS_DKFH = "贷款复核";

    public static final String PERFECTHOUSINGLOANPROCESS_CXSQ = "重新申请";

    public static final String PERFECTHOUSINGLOANPROCESS_CPYG = "初评预估";

    public static final String PERFECTHOUSINGLOANPROCESS_BJSP = "办件验房";

    public static final String PERFECTHOUSINGLOANPROCESS_CPBJ = "初评补件";
    
    public static final String PERFECTHOUSINGLOANPROCESS_BJBJ = "办件补件";

    public static final String PERFECTHOUSINGLOANPROCESS_DKZS = "贷款终审";
    
    public static final String PERFECTHOUSINGLOANPROCESS_ZSBJ = "终审补件";

    public static final String PERFECTHOUSINGLOANPROCESS_QDHT = "签订合同";

    public static final String PERFECTHOUSINGLOANPROCESS_GZ = "公证";

    public static final String PERFECTHOUSINGLOANPROCESS_TX = "他项";

    public static final String PERFECTHOUSINGLOANPROCESS_FKSQ = "放款申请";

    public static final String PERFECTHOUSINGLOANPROCESS_FKSQSP = "放款申请审批";

    public static final String PERFECTHOUSINGLOANPROCESS_FKQR = "放款确认";
    
    public static final String PERFECTHOUSINGLOANPROCESS_FCHCJF = "房产核查缴费";
    
    // --------------新房贷流程节点名称定义 20160510 baisong-------------------
    public static final String UPHOUSINGLOANPROCESS_CPYG = "初评预估";

    public static final String UPHOUSINGLOANPROCESS_BJSP = "办件验房";
    
    public static final String UPHOUSINGLOANPROCESS_ZXSP = "证信审批";

    public static final String UPHOUSINGLOANPROCESS_DSSP = "电审审批";

    public static final String UPHOUSINGLOANPROCESS_DKZS = "贷款终审";
      
    public static final String UPHOUSINGLOANPROCESS_HTQD = "合同签订";

    public static final String UPHOUSINGLOANPROCESS_FKSQ = "放款申请";

    public static final String UPHOUSINGLOANPROCESS_FKSQSP = "放款申请审批";

    public static final String UPHOUSINGLOANPROCESS_FKQR = "放款确认";
    
    public static final String UPHOUSINGLOANPROCESS_FCHCJF = "房产核查缴费";
    
    public static final String UPHOUSINGLOANPROCESS_SXQR = "授信确认";
    
    public static final String UPHOUSINGLOANPROCESS_XXBL = "信息补录";

    // --------------2.0版本房贷流程节点名称定义 20170216 baisong-------------------
    public static final String STRHOUSINGLOANNODE_DKSQ = "贷款申请";

    public static final String STRHOUSINGLOANNODE_FCCP = "房产初评";

    public static final String STRHOUSINGLOANNODE_HCJF = "核查缴费";

    public static final String STRHOUSINGLOANNODE_FCHC = "房产核查";

    public static final String STRHOUSINGLOANNODE_ZSSP = "终审审批";

    public static final String STRHOUSINGLOANNODE_MSSP = "面审审批";

    public static final String STRHOUSINGLOANNODE_WSXX = "完善信息";

    public static final String STRHOUSINGLOANNODE_QDHT = "合同签订";

    public static final String STRHOUSINGLOANNODE_FKSQ = "放款申请";

    public static final String STRHOUSINGLOANNODE_FKSP = "放款审批";

    public static final String STRHOUSINGLOANNODE_FKWC = "放款完成";

    // --------------常用角色的定义---------------------
    public static final String XSGLBZG = "xsglbzg";// 销售管理部主管

    public static final String DHBMZG = "dhbmzg";// 贷后部门主管

    public static final String CWBMZG = "cwbmzg";// 财务部门主管

    public static final String FWBMZG = "fwbmzg";// 法务部门主管

    public static final String DQLSFHY = "dqlsfhy";// 贷前流水复核员

    public static final String DQXXFHY = "dqxxfhy";// 贷前信息复核员

    public static final String DQDSFHY = "dqdsfhy";// 贷前电审复核员

    public static final String DQZXFHY = "dqzxfhy";// 贷前证信复核员

    public static final String DQBJFHY = "dqbjfhy";// 贷前办件复核员

    public static final String DQYDFHY = "dqydfhy";// 贷前验点复合员

    public static final String LCYWZY = "lcywzy";// 理财业务专员

    public static final String LCYWJDZY = "lcywjdzy";// 理财业务接待专员
    
    // --------------新房贷流程角色----------------
    public static final String HTZY = "htzy";//合同专员

    public static final String HDQBJFHY = "dqbjfhy";// 贷前办件复核员
    
    public static final String GZFHY = "gzfhy";// 公正复核员

    // -----------------工资设定流程定义-----------------
    public static final String INVESALARYSET_CXXD = "重新修订";// 重新修订

    public static final String INVESALARYSET_FZSP = "副总审批";// 副总审批

    public static final String INVESALARYSET_ZJLSP = "总经理审批";// 总经理审批

    public static final String INVESALARYSET_ZDWCSERVICE = "任务超时";// 任务超时

    // --------------常用信贷复合验点审批的条件----------------
    //public static final Double CREDITPROCESS_YD_SQ_ED = 10.0;// 卓营贷申请额度  修改更新2015-06-03  修改2015-12-14 取消

    public static final String CREDITPROCESS_YD_SQ_LOAN_ZYID_TYPE = "283";// 信贷产品类型283为卓营贷

    public static final String CREDITPROCESS_YD_SQ_LOAN_ZYED_TYPE = "113";// 信贷产品类型113为卓业贷
    
    public static final String UPHOUSINGLOANPROCESS_CREDIT_CONFIRM = "0";// 房贷授信确认  1普通贷 0 授信贷

    // --------------添加审批人和审批组
    public static final String CANDIDATEUSERS = "candidateUsers";// 添加多个审批人（逗号分隔）(流程变量方式）

    public static final String CANDIDATEGROUPS = "candidateGroups";// 添加多个审批组（逗号分隔）(流程变量方式）

    // --------------添加流程状态
    public static final String PROCESSSTATUS_RUNNING = "未结束";

    public static final String PROCESSSTATUS_FINISH = "已结束";
    
    // --------------设置理财上单 中支付节点倒计时作废时间
    public static final String FINANCIALSINGLEROCESS_ZF_TIME = "PT24H";//生产使用24小时
    //public static final String FINANCIALSINGLEROCESS_ZF_TIME = "PT6M";//测试使用6分钟
    
    // --------------设置房贷新旧流程区分时间
    public static final String PERFECTHOUSINGLOANPROCESS_TIME = "2016/03/16";
    /**
     * pc版本流程部署版本号
     */
    public static final String PERFECTHOUSINGLOANPROCESS_VERSION = "1";
    /**
     * 手机办本流程部署版本号
     */
    public static final String UPHOUSINGLOANPROCESS_VERSION = "1";

    /**
     * 系统单据状态统计报表统计单据有效时间(该天以后的才统计且包括该天)
     */
    public static final String SYS_BILL_STATUS_STATISTICS_EFFECTIVE_TIME = "2017-05-15";
    
    
    /**
     * 
     * @Title: getValue
     * @Description: TODO(根据编码获取对应的值)
     * @param valuekey
     * @return 
     * @author: baisong
     * @time:2017年2月16日 下午4:20:58
     * history:
     * 1、2017年2月16日 baisong 创建方法
     */
    public static String getValue(String valuekey)
    {
        String value = "";
        try
        {
            Class ownerClass = Class.forName("com.zx.emanage.workflow.util.WorkflowConstantHelp");
            Field field = ownerClass.getField("STRHOUSINGLOANNODE_" + valuekey);
            value = field.get(ownerClass).toString();
        }
        catch (Exception e)
        {
            value = "";
            e.printStackTrace();
        }
        return value;
    }

}
