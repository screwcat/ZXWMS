package com.zx.emanage.loanfina.vo;

import java.util.List;
import java.util.Map;

import com.zx.platform.syscontext.vo.GridParamBean;

/**
 * 
 * @ClassName: WmsFinaCreLoanAppSearchBeanVO
 * @Description: 内容摘要：放款申请相关VO
 * @author wangyihan
 * @date 2016年12月26日
 * @version V1.0
 * history:
 * 1、2016年12月26日 wangyihan 创建文件
 */

public class WmsFinaCreLoanAppSearchBeanVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;
    
    //放款申请附件(json格式字符串)
    private String wmsFinaCreLoanAppAttListJson;
    
    //贷款表主键
    private Integer wms_cre_credit_head_id;
    
    //放款申请信息以及合同(json格式字符串)
    private String loanApprovalInfoJson;
    
    private String Id_card_list;
    private String bank_list;
    private String transfer_list;
    private String protocol_list;
    
    private String fileArr;
    
    private Integer wms_fina_cre_loan_app;
    
    private  String edition_num;//房贷流程版本号1为旧2为新
    
    private  String taskId;
    
    private String version_number;//数据来源1为pc 2为移动端
    
    private String result;
    
    private String save_type;//1：放款申请 2：放款审批 3：放款确认
    
    private String wms_cre_credit_head_list_json;
    
    private Integer[] wms_fina_cre_loan_app_arr;
    
    /** 放款申请组合贷初始化信息 **/
    private List<Map<String, Object>> initCombineLoanInfoMap;
    
    private String wms_fina_cre_loan_app_list_json;

    private String bill_status;
    
    /** 放款组合贷单据list **/
    private List<Map<String, Object>> combineLoanList;
    
    private Integer wms_cre_credit_group_id;
    
    private String group_list;
    
    private String pass;
    
    private String advice;
    
    private String bill_type;
    // 校验标识如果为1则为校验其他为正常提交
    private String check_flag;
    // 组合编码
    private String alert_info;
    public String getWmsFinaCreLoanAppAttListJson() {
        return wmsFinaCreLoanAppAttListJson;
    }

    public void setWmsFinaCreLoanAppAttListJson(String wmsFinaCreLoanAppAttListJson) {
        this.wmsFinaCreLoanAppAttListJson = wmsFinaCreLoanAppAttListJson;
    }

    public Integer getWms_cre_credit_head_id() {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer wms_cre_credit_head_id) {
        this.wms_cre_credit_head_id = wms_cre_credit_head_id;
    }

    public String getLoanApprovalInfoJson() {
		return loanApprovalInfoJson;
	}

	public void setLoanApprovalInfoJson(String loanApprovalInfoJson) {
		this.loanApprovalInfoJson = loanApprovalInfoJson;
	}

	public String getId_card_list() {
        return Id_card_list;
    }

    public void setId_card_list(String id_card_list) {
        Id_card_list = id_card_list;
    }

    public String getBank_list() {
        return bank_list;
    }

    public void setBank_list(String bank_list) {
        this.bank_list = bank_list;
    }

    public String getTransfer_list() {
        return transfer_list;
    }

    public void setTransfer_list(String transfer_list) {
        this.transfer_list = transfer_list;
    }

    public String getProtocol_list() {
        return protocol_list;
    }

    public void setProtocol_list(String protocol_list) {
        this.protocol_list = protocol_list;
    }

    public List<Map<String, Object>> getInitCombineLoanInfoMap()
    {
        return initCombineLoanInfoMap;
    }

    public void setInitCombineLoanInfoMap(List<Map<String, Object>> initCombineLoanInfoMap)
    {
        this.initCombineLoanInfoMap = initCombineLoanInfoMap;
    }

    public String getFileArr()
    {
        return fileArr;
    }

    public void setFileArr(String fileArr)
    {
        this.fileArr = fileArr;
    }

    public Integer getWms_fina_cre_loan_app()
    {
        return wms_fina_cre_loan_app;
    }

    public void setWms_fina_cre_loan_app(Integer wms_fina_cre_loan_app)
    {
        this.wms_fina_cre_loan_app = wms_fina_cre_loan_app;
    }

    public String getEdition_num()
    {
        return edition_num;
    }

    public void setEdition_num(String edition_num)
    {
        this.edition_num = edition_num;
    }

    public String getTaskId()
    {
        return taskId;
    }

    public void setTaskId(String taskId)
    {
        this.taskId = taskId;
    }

    public String getVersion_number()
    {
        return version_number;
    }

    public void setVersion_number(String version_number)
    {
        this.version_number = version_number;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public String getWms_cre_credit_head_list_json()
    {
        return wms_cre_credit_head_list_json;
    }

    public void setWms_cre_credit_head_list_json(String wms_cre_credit_head_list_json)
    {
        this.wms_cre_credit_head_list_json = wms_cre_credit_head_list_json;
    }

    public Integer[] getWms_fina_cre_loan_app_arr()
    {
        return wms_fina_cre_loan_app_arr;
    }

    public void setWms_fina_cre_loan_app_arr(Integer[] wms_fina_cre_loan_app_arr)
    {
        this.wms_fina_cre_loan_app_arr = wms_fina_cre_loan_app_arr;
    }

    public String getWms_fina_cre_loan_app_list_json()
    {
        return wms_fina_cre_loan_app_list_json;
    }

    public void setWms_fina_cre_loan_app_list_json(String wms_fina_cre_loan_app_list_json)
    {
        this.wms_fina_cre_loan_app_list_json = wms_fina_cre_loan_app_list_json;
    }

    public String getSave_type()
    {
        return save_type;
    }

    public void setSave_type(String save_type)
    {
        this.save_type = save_type;
    }

    public String getBill_status()
    {
        return bill_status;
    }

    public void setBill_status(String bill_status)
    {
        this.bill_status = bill_status;
    }

    public List<Map<String, Object>> getCombineLoanList()
    {
        return combineLoanList;
    }

    public void setCombineLoanList(List<Map<String, Object>> combineLoanList)
    {
        this.combineLoanList = combineLoanList;
    }

    public Integer getWms_cre_credit_group_id()
    {
        return wms_cre_credit_group_id;
    }

    public void setWms_cre_credit_group_id(Integer wms_cre_credit_group_id)
    {
        this.wms_cre_credit_group_id = wms_cre_credit_group_id;
    }

    public String getGroup_list()
    {
        return group_list;
    }

    public void setGroup_list(String group_list)
    {
        this.group_list = group_list;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public String getAdvice()
    {
        return advice;
    }

    public void setAdvice(String advice)
    {
        this.advice = advice;
    }

    public String getBill_type()
    {
        return bill_type;
    }

    public void setBill_type(String bill_type)
    {
        this.bill_type = bill_type;
    }

    public String getCheck_flag()
    {
        return check_flag;
    }

    public void setCheck_flag(String check_flag)
    {
        this.check_flag = check_flag;
    }

    public String getAlert_info()
    {
        return alert_info;
    }

    public void setAlert_info(String alert_info)
    {
        this.alert_info = alert_info;
    }

}