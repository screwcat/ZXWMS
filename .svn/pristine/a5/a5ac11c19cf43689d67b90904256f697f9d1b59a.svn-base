package com.zx.emanage.loanfina.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.loanfina.persist.WmsFinaCrePosDao;
import com.zx.emanage.loanfina.service.IWmsFinaCrePosService;
import com.zx.emanage.util.gen.entity.WmsFinaCrePos;
import com.zx.emanage.loanfina.vo.WmsFinaCrePosSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsfinacreposService")
public class WmsFinaCrePosServiceImpl implements IWmsFinaCrePosService {
	private static Logger log = LoggerFactory.getLogger(WmsFinaCrePosServiceImpl.class);

	@Autowired
	private WmsFinaCrePosDao wmsfinacreposDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsFinaCrePosSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	 	if (queryInfo.getEnable_flag() != null) {
	 		paramMap.put("enable_flag", queryInfo.getEnable_flag());
	    }
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsfinacreposDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	/**
	 * 财务POS机查询（分页）
	 */
	public Map<String, Object> getListWithPaging(WmsFinaCrePosSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (queryInfo.getEnable_flag() != null) {
	 		paramMap.put("enable_flag", queryInfo.getEnable_flag());
	    }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsfinacreposDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsfinacreposDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsFinaCrePos getInfoByPK(Integer wms_fina_cre_pos_id) {
		WmsFinaCrePos wmsFinaCrePos= new WmsFinaCrePos();
		if(wms_fina_cre_pos_id!=-1||wms_fina_cre_pos_id!=-2){
			wmsFinaCrePos=wmsfinacreposDao.get(wms_fina_cre_pos_id);
		}
		return wmsFinaCrePos;
	}

	@Override	
	@Transactional
	public String save(WmsFinaCrePos bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsfinacreposDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsFinaCrePos bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsfinacreposDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsFinaCrePos() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsFinaCrePos> getListByEntity(WmsFinaCrePos queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsFinaCrePos> beanList = wmsfinacreposDao.getListByEntity(queryInfo);
		return beanList;
	}

	/**
	 * @Title: doChangePos 
	 * @Description: 新增/修改财务POS机信息
	 * @param bean
	 * @param user
	 * @return String    返回类型
	 * @throws
	 */
	public String doChangePos(String bean, UserBean user) {
		
		String resStr = "success";
		Timestamp sysTime = new Timestamp(System.currentTimeMillis());
		List<WmsFinaCrePos> list = JsonUtil.jsonArrayToList(bean, WmsFinaCrePos.class);
		
		try {
			for(WmsFinaCrePos wmsFinaCrePos : list) {
				//ID不为空执行更新操作，否则执行添加操作
				if(wmsFinaCrePos.getWms_fina_cre_pos_id() != null) {
					wmsFinaCrePos.setLast_update_user_id(user.getUserId());
					wmsFinaCrePos.setLast_update_user_name(user.getRealname());
					wmsFinaCrePos.setLast_update_user_datetime(sysTime);
					wmsFinaCrePos.setCreate_user_datetime(null);
					wmsFinaCrePos.setCreate_user_id(null);
					wmsFinaCrePos.setCreate_user_name(null);
					wmsfinacreposDao.update(wmsFinaCrePos);
				} else {
					wmsFinaCrePos.setCreate_user_datetime(sysTime);
					wmsFinaCrePos.setCreate_user_id(user.getUserId());
					wmsFinaCrePos.setCreate_user_name(user.getRealname());
					wmsFinaCrePos.setLast_update_user_id(null);
					wmsFinaCrePos.setLast_update_user_name(null);
					wmsFinaCrePos.setLast_update_user_datetime(null);
					wmsfinacreposDao.save(wmsFinaCrePos);
				}
			}
		} catch (Exception e) {
			resStr = "error";
		}
		
		return resStr;
	}
	/**
	 * 获取财务收款所有POS机信息
	 * @param isEmpty
	 * @param isAll
	 * @author hancd
	 */
	@Override
	public List<WmsFinaCrePos> getIDByEmpty(String isEmpty, String isAll) {
		WmsFinaCrePos finaCrePos = new WmsFinaCrePos();
		finaCrePos.setEnable_flag("1");
		List<WmsFinaCrePos> list = wmsfinacreposDao.getListByEntity(finaCrePos);
        if ("true".equals(isEmpty))
        {
        	WmsFinaCrePos entity = new WmsFinaCrePos();
        	entity.setWms_fina_cre_pos_id(-1);
        	entity.setPos_code("请选择");
            list.add(0, entity);
        }
        if ("true".equals(isAll))
        {
        	WmsFinaCrePos entity = new WmsFinaCrePos();
        	entity.setWms_fina_cre_pos_id(-2);
        	entity.setPos_code("全部");
            list.add(0, entity);
        }
        return list;
	}
}
