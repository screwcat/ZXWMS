package com.zx.emanage.util.gen.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.math.BigDecimal;

import com.zx.sframe.util.mybatis.BaseEntity;

/*
 * @version 2.0
 */

public class WmsInveCommissionRankingReward implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_commission_ranking_reward_id;
	
	private Integer wms_inve_commission_reward_head_rules_id;
	
	private java.math.BigDecimal team_net_growth;
	
	private java.math.BigDecimal team_stock;
	
	private java.math.BigDecimal monthly_added;
	
	private Integer rank_num;
	
	private boolean isExcludePKFlag;
	
	
	/**
	* default val cols name array
	*/	
	private static String[] defaultValColArr = {
	};
	
	/**
	* pk cols name array
	*/	
	private static String[] pkColArr = {
	  	"wms_inve_commission_ranking_reward_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_commission_ranking_reward_id",
		"wms_inve_commission_reward_head_rules_id",
		"team_net_growth",
		"team_stock",
		"monthly_added",
		"rank_num",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_commission_ranking_reward_id () {
		return wms_inve_commission_ranking_reward_id;
	}
	
	public void setWms_inve_commission_ranking_reward_id (Integer obj) {
		wms_inve_commission_ranking_reward_id = obj;
	}
	
	public Integer getWms_inve_commission_reward_head_rules_id () {
		return wms_inve_commission_reward_head_rules_id;
	}
	
	public void setWms_inve_commission_reward_head_rules_id (Integer obj) {
		wms_inve_commission_reward_head_rules_id = obj;
	}
	
	public java.math.BigDecimal getTeam_net_growth () {
		return team_net_growth;
	}
	
	public void setTeam_net_growth (java.math.BigDecimal obj) {
		team_net_growth = obj;
	}
	
	public java.math.BigDecimal getTeam_stock () {
		return team_stock;
	}
	
	public void setTeam_stock (java.math.BigDecimal obj) {
		team_stock = obj;
	}
	
	public java.math.BigDecimal getMonthly_added () {
		return monthly_added;
	}
	
	public void setMonthly_added (java.math.BigDecimal obj) {
		monthly_added = obj;
	}
	
	public Integer getRank_num () {
		return rank_num;
	}
	
	public void setRank_num (Integer obj) {
		rank_num = obj;
	}
	
	public boolean getgetIsExcludePKFlag () {
		return isExcludePKFlag;
	}
	
	public void setgetIsExcludePKFlag (boolean obj) {
		isExcludePKFlag = obj;
	}
	
	
	/**
	* put all columns into a map
	*/
	public void putInMap(Map<String, Object> paramMap) {
		paramMap.put("wms_inve_commission_ranking_reward_id", this.wms_inve_commission_ranking_reward_id);
		paramMap.put("wms_inve_commission_reward_head_rules_id", this.wms_inve_commission_reward_head_rules_id);
		paramMap.put("team_net_growth", this.team_net_growth);
		paramMap.put("team_stock", this.team_stock);
		paramMap.put("monthly_added", this.monthly_added);
		paramMap.put("rank_num", this.rank_num);
		paramMap.put("isExcludePKFlag", this.isExcludePKFlag);
	}
	
	/**
	* return the columns map
	*/
	public Map<String, Object> getInfoMap() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		this.putInMap(paramMap);
		return paramMap;
	}
	
	/**
	* remove default value and pk if it is null
	*/
	private Map<String, Object> dealWithMap(Map<String, Object> paramMap) {
		Set<String> set = new HashSet<String>();
		for (String colName : defaultValColArr) {
			set.add(colName);
		}
		for (String colName : pkColArr) {
			set.add(colName);
		}
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String colName = iterator.next();
			if(paramMap.get(colName) == null) {
				paramMap.remove(colName);
			}
		}
		return paramMap;
	}
}