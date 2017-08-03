package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveRedeemPrincipalDetailDao;
import com.zx.emanage.inve.service.IWmsInveRedeemPrincipalDetailService;
import com.zx.emanage.util.gen.entity.WmsInveRedeemPrincipalDetail;
import com.zx.emanage.inve.vo.WmsInveRedeemPrincipalDetailSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinveredeemprincipaldetailService")
public class WmsInveRedeemPrincipalDetailServiceImpl implements IWmsInveRedeemPrincipalDetailService {
	private static Logger log = LoggerFactory.getLogger(WmsInveRedeemPrincipalDetailServiceImpl.class);

	@Autowired
	private WmsInveRedeemPrincipalDetailDao wmsinveredeemprincipaldetailDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveRedeemPrincipalDetailSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinveredeemprincipaldetailDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveRedeemPrincipalDetailSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinveredeemprincipaldetailDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinveredeemprincipaldetailDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveRedeemPrincipalDetail getInfoByPK(Integer wms_inve_redeem_principal_detail_id) {
		return wmsinveredeemprincipaldetailDao.get(wms_inve_redeem_principal_detail_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveRedeemPrincipalDetail bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinveredeemprincipaldetailDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveRedeemPrincipalDetail bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinveredeemprincipaldetailDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveRedeemPrincipalDetail() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveRedeemPrincipalDetail> getListByEntity(WmsInveRedeemPrincipalDetail queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveRedeemPrincipalDetail> beanList = wmsinveredeemprincipaldetailDao.getListByEntity(queryInfo);
		return beanList;
	}
}
