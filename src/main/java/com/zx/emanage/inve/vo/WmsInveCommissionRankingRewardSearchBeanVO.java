package com.zx.emanage.inve.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsInveCommissionRankingRewardSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	private Integer	reward_method;
	public Integer getReward_method() {
		return reward_method;
	}
	public void setReward_method(Integer reward_method) {
		this.reward_method = reward_method;
	}
}