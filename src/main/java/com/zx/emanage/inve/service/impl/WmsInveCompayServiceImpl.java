package com.zx.emanage.inve.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCompayDao;
import com.zx.emanage.inve.service.IWmsInveCompayService;
import com.zx.emanage.inve.vo.WmsInveCompaySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveCompay;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecompayService")
public class WmsInveCompayServiceImpl implements IWmsInveCompayService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCompayServiceImpl.class);

	@Autowired
	private WmsInveCompayDao wmsinvecompayDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCompaySearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecompayDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCompaySearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecompayDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecompayDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCompay getInfoByPK(Integer wms_inve_compay_id) {
		return wmsinvecompayDao.get(wms_inve_compay_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCompay bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecompayDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCompay bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecompayDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCompay() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCompay> getListByEntity(WmsInveCompay queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCompay> beanList = wmsinvecompayDao.getListByEntity(queryInfo);
		return beanList;
	}

	@Override
	public List<WmsInveCompay> getCompayListList(String isEmpty, String isAll) {
		 List<WmsInveCompay> list =new ArrayList<WmsInveCompay>();
		List<WmsInveCompay> wmsInveCompayList=wmsinvecompayDao.searcompay();
        for(WmsInveCompay wmsinvecompay:wmsInveCompayList){
        	WmsInveCompay compay= new WmsInveCompay();
        	compay.setDept_id(wmsinvecompay.getDept_id());
        	compay.setCompay_name(wmsinvecompay.getCompay_name());
            list.add(compay);
        }
        if ("true".equals(isAll))
        {
        	WmsInveCompay entity = new WmsInveCompay();
            entity.setDept_id(-1);
            entity.setCompay_name("---全部---");
            list.add(0, entity);
        }
        if ("true".equals(isEmpty))
        {
        	WmsInveCompay entity = new WmsInveCompay();
            entity.setDept_id(-2);
            entity.setCompay_name("---请选择---");
            list.add(0, entity);
        }
        return list;
	}
}
