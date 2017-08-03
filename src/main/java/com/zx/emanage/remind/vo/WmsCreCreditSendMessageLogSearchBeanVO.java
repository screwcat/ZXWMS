package com.zx.emanage.remind.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/**
 * 
 * @ClassName: WmsCreCreditSendMessageLogSearchBeanVO
 * @Description: 内容摘要：
 * @author wangyihan
 * @date 2016年11月24日
 * @version V1.0
 * history:
 * 1、2016年11月24日 wangyihan 创建文件
 */
public class WmsCreCreditSendMessageLogSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;

	private String wms_cre_credit_notary_warn_id_list;
	
	/** 单据主键id数组 **/
	private Integer[] wms_cre_credit_notary_warn_id_arr;
	
	/** 还款提醒发送短信list json字符串 **/
	private String wms_cre_credit_notary_warn_list_json;

	/** 发送短信类型 */
	private String send_message_type;
	
	/** 所有勾选记录 json字符串 */
	private String wms_cre_credit_notary_warn_all_list_json;
	
	public String getWms_cre_credit_notary_warn_id_list() {
		return wms_cre_credit_notary_warn_id_list;
	}

	public void setWms_cre_credit_notary_warn_id_list(
			String wms_cre_credit_notary_warn_id_list) {
		this.wms_cre_credit_notary_warn_id_list = wms_cre_credit_notary_warn_id_list;
	}

    public String getWms_cre_credit_notary_warn_list_json()
    {
        return wms_cre_credit_notary_warn_list_json;
    }

    public void setWms_cre_credit_notary_warn_list_json(String wms_cre_credit_notary_warn_list_json)
    {
        this.wms_cre_credit_notary_warn_list_json = wms_cre_credit_notary_warn_list_json;
    }

    public Integer[] getWms_cre_credit_notary_warn_id_arr()
    {
        return wms_cre_credit_notary_warn_id_arr;
    }

    public void setWms_cre_credit_notary_warn_id_arr(Integer[] wms_cre_credit_notary_warn_id_arr)
    {
        this.wms_cre_credit_notary_warn_id_arr = wms_cre_credit_notary_warn_id_arr;
    }

    public String getSend_message_type()
    {
        return send_message_type;
    }

    public void setSend_message_type(String send_message_type)
    {
        this.send_message_type = send_message_type;
    }

    public String getWms_cre_credit_notary_warn_all_list_json()
    {
        return wms_cre_credit_notary_warn_all_list_json;
    }

    public void setWms_cre_credit_notary_warn_all_list_json(String wms_cre_credit_notary_warn_all_list_json)
    {
        this.wms_cre_credit_notary_warn_all_list_json = wms_cre_credit_notary_warn_all_list_json;
    }

    
    
}