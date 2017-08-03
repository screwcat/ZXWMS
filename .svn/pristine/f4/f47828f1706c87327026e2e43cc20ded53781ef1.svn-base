package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCreditEmailAttDao;
import com.zx.emanage.inve.service.IWmsInveCreditEmailAttService;
import com.zx.emanage.util.gen.entity.WmsInveCreditEmailAtt;
import com.zx.emanage.inve.vo.WmsInveCreditEmailAttSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecreditemailattService")
public class WmsInveCreditEmailAttServiceImpl implements IWmsInveCreditEmailAttService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCreditEmailAttServiceImpl.class);

	@Autowired
	private WmsInveCreditEmailAttDao wmsinvecreditemailattDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCreditEmailAttSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecreditemailattDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCreditEmailAttSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecreditemailattDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecreditemailattDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCreditEmailAtt getInfoByPK(Integer wms_inve_credit_email_att_id) {
		return wmsinvecreditemailattDao.get(wms_inve_credit_email_att_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCreditEmailAtt bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecreditemailattDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCreditEmailAtt bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecreditemailattDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCreditEmailAtt() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCreditEmailAtt> getListByEntity(WmsInveCreditEmailAtt queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCreditEmailAtt> beanList = wmsinvecreditemailattDao.getListByEntity(queryInfo);
		return beanList;
	}
}
