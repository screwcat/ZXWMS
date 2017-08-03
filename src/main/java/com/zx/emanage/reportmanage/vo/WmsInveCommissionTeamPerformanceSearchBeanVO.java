package com.zx.emanage.reportmanage.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsInveCommissionTeamPerformanceSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	
	private Integer team_user_id;//团队经理id
	
	private long create_datetime;//
	
	private String show;//展示
	
	public Integer getTeam_user_id() {
		return team_user_id;
	}
	public void setTeam_user_id(Integer team_user_id) {
		this.team_user_id = team_user_id;
	}
	public long getCreate_datetime() {
		return create_datetime;
	}
	public void setCreate_datetime(long create_datetime) {
		this.create_datetime = create_datetime;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
}