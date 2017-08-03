package com.zx.emanage.sysmanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.sysmanage.persist.SysSpecialUserDao;
import com.zx.emanage.sysmanage.service.ISysSpecialUserService;
import com.zx.emanage.util.gen.entity.SysSpecialUser;
import com.zx.emanage.sysmanage.vo.SysSpecialUserSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("sysspecialuserService")
public class SysSpecialUserServiceImpl implements ISysSpecialUserService {
	private static Logger log = LoggerFactory.getLogger(SysSpecialUserServiceImpl.class);

	@Autowired
	private SysSpecialUserDao sysspecialuserDao;

	@Override
	public Map<String, Object> getListWithoutPaging(SysSpecialUserSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = sysspecialuserDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(SysSpecialUserSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = sysspecialuserDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), sysspecialuserDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public SysSpecialUser getInfoByPK(Integer sys_special_user_id) {
		return sysspecialuserDao.get(sys_special_user_id);
	}

	@Override	
	@Transactional
	public String save(SysSpecialUser bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = sysspecialuserDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(SysSpecialUser bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = sysspecialuserDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new SysSpecialUser() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<SysSpecialUser> getListByEntity(SysSpecialUser queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<SysSpecialUser> beanList = sysspecialuserDao.getListByEntity(queryInfo);
		return beanList;
	}
	
	/**
	 * 获取特批人列表数据
	 */
	public List<SysSpecialUser> getSysSpecialUserList(SysSpecialUser queryInfo, UserBean user) {
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        return sysspecialuserDao.getListByEntity(specialUser);
	}
	
}
