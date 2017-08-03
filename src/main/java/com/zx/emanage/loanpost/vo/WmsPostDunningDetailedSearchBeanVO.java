package com.zx.emanage.loanpost.vo;

import java.math.BigDecimal;
import java.sql.Date;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsPostDunningDetailedSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	String  final_dunning_name1;//代后专员
	String  dunning_remark1;//详情
	String  val;//详情
	String debtor_name;//客户姓名
	String debtor_tel;//联系电话
	Date protocol_creat_date;//签约日期
	int borrow_deadline;//期数
	BigDecimal matching_creditor;//剩余债权
	int cur_overdue_day ;//逾期天数
	BigDecimal total_pay_late_fee;//违约金
	java.sql.Date dunning_datetime2;//催缴日期
	String  dunning_remark2;//详情
	int dunning_id;//贷后专员id
	String dunning_name;//贷后专员姓名
	int dunning_deptId;//贷后专员部门id
	String resultMe;//代后

	public String getResultMe() {
		return resultMe;
	}
	public void setResultMe(String resultMe) {
		this.resultMe = resultMe;
	}
	public int getDunning_id() {
		return dunning_id;
	}
	public void setDunning_id(int dunning_id) {
		this.dunning_id = dunning_id;
	}
	public String getDunning_name() {
		return dunning_name;
	}
	public void setDunning_name(String dunning_name) {
		this.dunning_name = dunning_name;
	}
	public int getDunning_deptId() {
		return dunning_deptId;
	}
	public void setDunning_deptId(int dunning_deptId) {
		this.dunning_deptId = dunning_deptId;
	}
	
	public java.sql.Date getDunning_datetime2() {
		return dunning_datetime2;
	}
	public void setDunning_datetime2(java.sql.Date dunning_datetime2) {
		this.dunning_datetime2 = dunning_datetime2;
	}
	
	public String getDunning_remark2() {
		return dunning_remark2;
	}
	public void setDunning_remark2(String dunning_remark2) {
		this.dunning_remark2 = dunning_remark2;
	}
	public BigDecimal getMatching_creditor() {
		return matching_creditor;
	}
	public void setMatching_creditor(BigDecimal matching_creditor) {
		this.matching_creditor = matching_creditor;
	}
	public int getBorrow_deadline() {
		return borrow_deadline;
	}
	public void setBorrow_deadline(int borrow_deadline) {
		this.borrow_deadline = borrow_deadline;
	}
	public String getDebtor_name() {
		return debtor_name;
	}
	public void setDebtor_name(String debtor_name) {
		this.debtor_name = debtor_name;
	}
	public String getDebtor_tel() {
		return debtor_tel;
	}
	public void setDebtor_tel(String debtor_tel) {
		this.debtor_tel = debtor_tel;
	}
	public Date getProtocol_creat_date() {
		return protocol_creat_date;
	}
	public void setProtocol_creat_date(Date protocol_creat_date) {
		this.protocol_creat_date = protocol_creat_date;
	}

	public int getCur_overdue_day() {
		return cur_overdue_day;
	}
	public void setCur_overdue_day(int cur_overdue_day) {
		this.cur_overdue_day = cur_overdue_day;
	}
	public BigDecimal getTotal_pay_late_fee() {
		return total_pay_late_fee;
	}
	public void setTotal_pay_late_fee(BigDecimal total_pay_late_fee) {
		this.total_pay_late_fee = total_pay_late_fee;
	}
	public String getFinal_dunning_name1() {
		return final_dunning_name1;
	}
	public void setFinal_dunning_name1(String final_dunning_name1) {
		this.final_dunning_name1 = final_dunning_name1;
	}
	public String getDunning_remark1() {
		return dunning_remark1;
	}
	public void setDunning_remark1(String dunning_remark1) {
		this.dunning_remark1 = dunning_remark1;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}