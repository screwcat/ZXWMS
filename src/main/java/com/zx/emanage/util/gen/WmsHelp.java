package com.zx.emanage.util.gen;

public class WmsHelp{
	//*****部门信息开始****//
    public static final Integer DEPT_LEVEL_5=5;//营业部门等级-- 例如:沈阳营业一部	
    public static final String DEPT_CODE_5="DPH%YY%";//部门编码 沈阳营业一部 鞍山营业二部 例如DPHSYYYYBXX001 or DPHASYYEBXX001
 
    public static final Integer DEPT_LEVEL_6=6;//业务部门等级-- 例如:业务一部	
    public static final String DEPT_CODE_6="DPHYW%";//部门编码 业务一部  业务二部 例如DPHYWYBXXXX004 or DPHYWEBXXXX001 
   
    public static final String TOP_DEPT_CODE="DPHCPGLBXXX001";//贷款端--DPHCPGLBXXX001 产品管理部   测试环境id=31 
    
    public static final String DEPT_CODE_KFB="DPHKFZXXXXX001";//贷款端--客服部code --放款确认使用的催缴部门人员 
    //*****部门信息结束****//
    
    //*****职务信息开始****//
    public static final String POST_TDJL_CODE="PPH%TDJL%";//贷款端--团队经理职务编码  PPHTDJLXXXX001   PPHJXTDJLXX001
    public static final String POST_YYBJL_CODE="PPH%YYBJL%";//贷款端--营业部经理职务编码  PPHYYBJLXXX002   PPHYYBJLXXX002
    public static final String POST_KHJL_CODE="PPH%KHJL%";//贷款端--客户经理 PPHKHJLXXXX019   PPHJXKHJLXX003
    //*****职务信息结束****//
    

    //*********字典表id开始**********//
    public static final Integer SYS_DICT_ID=120;//用于区分是否走授信确认的字典表  暂时标记了沈阳长春不走
    
    public static final Integer SYS_DICT_ID_APPL_REASON=103;//用于区分是否走授信确认的字典表  暂时标记了沈阳长春不走

    public static final String SYS_DICT_ID_MIS_HOUSE_ID = "846";// 贷款申请图片房产证字典表id(小类di)

    public static final String SYS_DICT_ID_MIS_HOUSE_PID = "843";// 贷款申请图片房产证字典表id（大类id）

    //*********字典表id结束**********//

    // *********常量开始**********//
    public static final Integer BORROW_DEADLINE = 3;// 签订合同时候默认期数

    // *********常量结束**********//

    //*********菜单url开始**********//
    public static final String MENU_URL_YD_LIST="loanreview/creYdTeamCheckSearch.html";//信贷--验点查询列表url
    public static final String MENU_URL_MQ_LIST="loanappro/loanApproInterView.html";//信贷--面签查询列表url
	public static final String MENU_URL_GZDQTX_LIST = "remind/NotarizationExpirationReminderList.html";// 公证到期提醒url
	public static final String MENU_URL_GZDQCX_LIST = "remind/NotarizationExpirationReminderSearch.html";// 公证到期查询url
    
	public static final String MENU_URL_CSPG_LIST = "loanreview/housePreliminaryAssessmentList.html";// 初审评估url
	public static final String MENU_URL_DKCX_LIST = "creditManage/searchCredit.html";// 贷款查询url

	public static final String MENU_URL_SRTX_LIST = "remind/BirthdayReminderList.html";// 生日提醒url
	public static final String MENU_URL_HKCX_LIST = "remind/RepaymentSearchList.html";// 还款查询url
	public static final String MENU_URL_HKQR_LIST = "remind/repaymentConfirmationList.html";// 还款确认url
	public static final String MENU_URL_HKTX_LIST = "remind/RepaymentReminderList.html";// 还款提醒url
    public static final String MENU_URL_DKSJCX_LIST = "remind/loansSearchList.html";// 贷款数据查询url

    public static final String MENU_URL_GZTX_LIST = "loanappro/notarizationOtherConfirm.html";// 公证/他项确认url

    public static final String MENU_URL_ZSSP_LIST = "loanappro/houseLoanAgreePageList.html";// 终审管理》房贷终审》终审审批url

    public static final String MENU_URL_ZSMQ_LIST = "loanappro/houseLoanVisaPageList.html";// 终审管理》房贷终审》终审面签url

    public static final String MENU_URL_FCJF_LIST = "creditManage/housingPaymentVerificationList.html";// 业务管理》房贷》房产缴费url

    public static final String MENU_URL_FCHCJFCX_LIST = "creditManage/housingPaymentVerificationSeach.html";// 终审管理》房贷终审》缴费查询url

    public static final String MENU_URL_FCHC_LIST = "loanreview/houseCardApprovalList.html";// 审核管理》房贷》房产核查url

    public static final String MENU_URL_FCHCWS_LIST = "loanreview/houseCardApprovalMakeupList.html";// 审核管理》房贷》房产核查完善url

    public static final String MENU_URL_FKSP_LIST = "loanappro/houseMakeLoanApprovalList.html";// 终审管理》房贷终审》放款审批url

    public static final String MENU_URL_ZSGQHF_LIST = "loanappro/finalBillsExpiredAndRestoredList.html";// 终审管理》房贷终审》终审过期恢复url

    public static final String MENU_URL_QDHT_LIST = "loanappro/agreeInfoList.html";// 终审管理》房贷终审》终审签合同url

    public static final String MENU_URL_HTCX_LIST = "loanappro/loanApproBorrowProtocolList.html";// 终审管理》房贷终审》合同查询url

    public static final String MENU_URL_SPCX_LIST = "loanappro/loanApproSearch.html";// 终审管理》房贷终审》审批查询url
    //*********菜单url结束**********//
    
    public static final String MENU_ID_FCHC_LIST = "73";// 菜单id 房贷房产核查

    /******常量开始*****/
    public static final String APP_NAME_MIS = "MIS";// app别名mis

    public static final String APP_NAME_MIF = "MIF";// app别名mif

    public static final String YWB_ROLE_NAME = "业管部消息";// 业务部角色名称 例如 业务部消息

    public static final String FCHC_ZG_ROLE_NAME = "房产核查部主管";// 业务部角色名称 例如 业务部消息

    public static final String SYSTEM_START_DATE = "2017-05-15";// 系统启用时间

    public static final String ERROR_TYPE_CLAIMS_PACKAGE_SYS = "ERROR_TYPE_CLAIMS_PACKAGE_SYS";// 推送债权包方法系统异常单据记录

    public static final String ERROR_TYPE_CLAIMS_PACKAGE_SYS_INFO = "推送债权包方法系统异常单据记录";// 推送债权包方法系统异常单据记录描述

    public static final String ERROR_TYPE_CLAIMS_PACKAGE_DATA = "ERROR_TYPE_CLAIMS_PACKAGE_DATA";// 推送债权包方法数据异常单据记录

    public static final String ERROR_TYPE_CLAIMS_PACKAGE_DATA_INFO = "推送债权包方法数据异常单据记录";// 推送债权包方法数据异常单据记录描述

    /*****常量结束******/
}
