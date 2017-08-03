package com.zx.emanage.loanfina.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsFinaCrePos;
import com.zx.emanage.loanfina.vo.WmsFinaCrePosSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsFinaCrePosService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsFinaCrePosSearchBeanVO queryInfo);
	
	/**
	 * @Title: getListWithPaging 
	 * @Description: 财务POS机查询（分页）
	 * @param queryInfo
	 * @return    
	 * @return Map<String,Object>    
	 * @throws
	 * @author lvtu 
	 * @date 2015年6月29日 下午4:04:32
	 */
	public Map<String, Object> getListWithPaging(WmsFinaCrePosSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsFinaCrePosVO
	 * @author auto_generator
	 */	
	public WmsFinaCrePos getInfoByPK(Integer wms_fina_cre_pos_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsFinaCrePos bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsFinaCrePos bean, UserBean user);
	
	/**
	 * @Title: doChangePos 
	 * @Description: TODO(新增/修改财务POS机信息) 
	 * @param bean
	 * @param user
	 * @return String    返回类型
	 * @throws
	 */
	public String doChangePos(String bean, UserBean user);
	/**
	 * 财务还款获取POS机信息
	 * @param isEmpty
	 * @param isAll
	 * @return
	 */
	public List<WmsFinaCrePos> getIDByEmpty(String isEmpty, String isAll);
}