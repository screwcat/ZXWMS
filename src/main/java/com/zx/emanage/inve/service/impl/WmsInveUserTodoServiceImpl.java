package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveUserTodoDao;
import com.zx.emanage.inve.service.IWmsInveUserTodoService;
import com.zx.emanage.util.gen.entity.WmsInveUserTodo;
import com.zx.emanage.inve.vo.WmsInveUserTodoSearchBeanVO;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinveusertodoService")
public class WmsInveUserTodoServiceImpl implements IWmsInveUserTodoService {
	private static Logger log = LoggerFactory.getLogger(WmsInveUserTodoServiceImpl.class);

	@Autowired
	private WmsInveUserTodoDao wmsinveusertodoDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveUserTodoSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinveusertodoDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveUserTodoSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinveusertodoDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinveusertodoDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveUserTodo getInfoByPK(Integer wms_inve_user_todo_id) {
		return wmsinveusertodoDao.get(wms_inve_user_todo_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveUserTodo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinveusertodoDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveUserTodo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinveusertodoDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveUserTodo() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveUserTodo> getListByEntity(WmsInveUserTodo queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveUserTodo> beanList = wmsinveusertodoDao.getListByEntity(queryInfo);
		return beanList;
	}


	@Override
	public Map<String, Object> findWmsInveUserToByUserId(Integer userId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int count = wmsinveusertodoDao.findWmsInveUserToByUserId(userId);
		if(count>0){
			resultMap.put("type", 1);
		}else{
			resultMap.put("type", 0);
		}
		return resultMap;
	}

	/**
	 * 根据登录人查询代办事项信息
	 * @param user 登录人信息
	 * @return
	 */
	@Override
	public Map<String, Object> getUserTodoByLoginUser(UserBean user) {
		List<Map<String, Object>> userTodoAndPersonnelList = wmsinveusertodoDao.getWmsInveUserTodoAndPersonByUserId(user.getUserId());
		if(!userTodoAndPersonnelList.isEmpty()){
			Map<String, Object> resMap = userTodoAndPersonnelList.get(0);
			String personnelShortCode = resMap.get("personnel_shortCode")+"";
			//如果是冯总审核flag设置成0，财务审核flag为1
			if(PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.usercode").equals(personnelShortCode)){
				resMap.put("flag", 0);
			}else if(PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.cwzgcode").equals(personnelShortCode)){
				resMap.put("flag", 1);
			}
			return resMap;
		}
		return null;
	}
	
}
