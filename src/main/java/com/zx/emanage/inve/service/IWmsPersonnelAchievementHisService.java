package com.zx.emanage.inve.service;

import java.util.Map;

import com.zx.emanage.inve.vo.WmsPersonnelAchievementHisSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsPersonnelAchievementHis;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsPersonnelAchievementHisService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsPersonnelAchievementHisSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsPersonnelAchievementHisSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsPersonnelAchievementHisVO
	 * @author auto_generator
	 */	
	public WmsPersonnelAchievementHis getInfoByPK(Integer wms_personnel_achievement_his_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsPersonnelAchievementHis bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsPersonnelAchievementHis bean, UserBean user);

    /**
     * @Title: getPersonnelAchievementByPid
     * @Description: 通过人员Id和static_month查询员工本月 上季度 本季度业绩
     * @param queryInfo
     * @return 
     * @author: zhangyunfei
     * @time:2017年1月12日 下午2:51:35
     * history:
     * 1、2017年1月12日 Administrator 创建方法
    */
    public Map<String, Object> getPersonnelAchievementByPid(WmsPersonnelAchievementHisSearchBeanVO queryInfo);
}