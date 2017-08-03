package com.zx.emanage.inve.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCreditSplitSpecDao;
import com.zx.emanage.inve.service.IWmsInveCreditSplitSpecService;
import com.zx.emanage.inve.vo.WmsInveCreditSplitSpecSearchBeanVO;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.util.gen.entity.WmsInveCreditSplitSpec;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecreditsplitspecService")
public class WmsInveCreditSplitSpecServiceImpl implements IWmsInveCreditSplitSpecService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCreditSplitSpecServiceImpl.class);

	@Autowired
	private WmsInveCreditSplitSpecDao wmsinvecreditsplitspecDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCreditSplitSpecSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecreditsplitspecDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCreditSplitSpecSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecreditsplitspecDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecreditsplitspecDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCreditSplitSpec getInfoByPK(Integer wms_inve_credit_split_spec_id) {
		return wmsinvecreditsplitspecDao.get(wms_inve_credit_split_spec_id);
	}

	@Override	
	@Transactional
    public String save(String beanJSON, UserBean user)
    {
		String resStr = "success";
		int ret = 0;
        List<WmsInveCreditSplitSpec> list = JsonUtil.jsonArrayToList(beanJSON, WmsInveCreditSplitSpec.class);
        String code = CodeNoUtil.getWmsInveCreditSplitSpecCode();
        for (WmsInveCreditSplitSpec bean : list)
        {
            bean.setWms_inve_credit_split_spec_id(null);
            bean.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
            bean.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
            bean.setCreate_user_id(user.getUserId());
            bean.setSplit_code(code);
            ret = wmsinvecreditsplitspecDao.save(bean);
        }
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCreditSplitSpec bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecreditsplitspecDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCreditSplitSpec() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCreditSplitSpec> getListByEntity(WmsInveCreditSplitSpec queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCreditSplitSpec> beanList = wmsinvecreditsplitspecDao.getListByEntity(queryInfo);
		return beanList;
	}
}
