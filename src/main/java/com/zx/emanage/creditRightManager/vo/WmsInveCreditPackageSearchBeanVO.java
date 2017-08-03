package com.zx.emanage.creditRightManager.vo;



import com.zx.platform.syscontext.vo.GridParamBean;



/**
 * @ClassName: WmsInveCreditPackageSearchBeanVO
 * @Description: 债权包查询VO
 * @author WangShuai
 * @date 2017年2月8日
 * @version V1.0
 * history:
 * 1、2017年2月8日 WangShuai 创建文件
 */
public class WmsInveCreditPackageSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	
	private String cre_type;//抵押包状态
	private String cre_per_name;//抵押包状态
	private String protocol_id_year_num;//抵押包状态
	private String cre_per_card_id;//抵押包状态
	private String cre_pledge_mon_tt_low;//抵押包状态
	private String cre_pledge_mon_tt_high;//抵押包状态
	private String rele_per_id;//抵押包状态
	private String loca_num;//抵押包状态
	private String crepackage_state;//抵押包状态
	public String getCre_type() {
		return cre_type;
	}
	public void setCre_type(String cre_type) {
		this.cre_type = cre_type;
	}
	public String getCre_per_name() {
		return cre_per_name;
	}
	public void setCre_per_name(String cre_per_name) {
		this.cre_per_name = cre_per_name;
	}
	public String getProtocol_id_year_num() {
		return protocol_id_year_num;
	}
	public void setProtocol_id_year_num(String protocol_id_year_num) {
		this.protocol_id_year_num = protocol_id_year_num;
	}
	public String getCre_per_card_id() {
		return cre_per_card_id;
	}
	public void setCre_per_card_id(String cre_per_card_id) {
		this.cre_per_card_id = cre_per_card_id;
	}
	public String getCre_pledge_mon_tt_low() {
		return cre_pledge_mon_tt_low;
	}
	public void setCre_pledge_mon_tt_low(String cre_pledge_mon_tt_low) {
		this.cre_pledge_mon_tt_low = cre_pledge_mon_tt_low;
	}
	public String getCre_pledge_mon_tt_high() {
		return cre_pledge_mon_tt_high;
	}
	public void setCre_pledge_mon_tt_high(String cre_pledge_mon_tt_high) {
		this.cre_pledge_mon_tt_high = cre_pledge_mon_tt_high;
	}
	public String getRele_per_id() {
		return rele_per_id;
	}
	public void setRele_per_id(String rele_per_id) {
		this.rele_per_id = rele_per_id;
	}
	public String getLoca_num() {
		return loca_num;
	}
	public void setLoca_num(String loca_num) {
		this.loca_num = loca_num;
	}
	public String getCrepackage_state() {
		return crepackage_state;
	}
	public void setCrepackage_state(String crepackage_state) {
		this.crepackage_state = crepackage_state;
	}
	
	

}