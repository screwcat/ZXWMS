package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCreditSplitDetailDao;
import com.zx.emanage.inve.service.IWmsInveCreditSplitDetailService;
import com.zx.emanage.util.gen.entity.WmsInveCreditSplitDetail;
import com.zx.emanage.inve.vo.WmsInveCreditSplitDetailSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecreditsplitdetailService")
public class WmsInveCreditSplitDetailServiceImpl implements IWmsInveCreditSplitDetailService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCreditSplitDetailServiceImpl.class);

	@Autowired
	private WmsInveCreditSplitDetailDao wmsinvecreditsplitdetailDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCreditSplitDetailSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecreditsplitdetailDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCreditSplitDetailSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecreditsplitdetailDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecreditsplitdetailDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCreditSplitDetail getInfoByPK(Integer wms_inve_credit_split_detail_id) {
		return wmsinvecreditsplitdetailDao.get(wms_inve_credit_split_detail_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCreditSplitDetail bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecreditsplitdetailDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCreditSplitDetail bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecreditsplitdetailDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCreditSplitDetail() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCreditSplitDetail> getListByEntity(WmsInveCreditSplitDetail queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCreditSplitDetail> beanList = wmsinvecreditsplitdetailDao.getListByEntity(queryInfo);
		return beanList;
	}
}
