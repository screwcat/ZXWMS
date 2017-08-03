package com.zx.emanage.loanappro.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.loanappro.persist.WmsCrePersonnelBillChangeDao;
import com.zx.emanage.loanappro.service.IWmsCrePersonnelBillChangeService;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsCrePersonnelBillChange;
import com.zx.emanage.loanappro.vo.WmsCrePersonnelBillChangeSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrepersonnelbillchangeService")
public class WmsCrePersonnelBillChangeServiceImpl implements IWmsCrePersonnelBillChangeService {
	private static Logger log = LoggerFactory.getLogger(WmsCrePersonnelBillChangeServiceImpl.class);

	@Autowired
	private WmsCrePersonnelBillChangeDao wmscrepersonnelbillchangeDao;
	
	@Autowired
	private PmPersonnelDao pmPersonnelDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsCrePersonnelBillChangeSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmscrepersonnelbillchangeDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsCrePersonnelBillChangeSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmscrepersonnelbillchangeDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrepersonnelbillchangeDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCrePersonnelBillChange getInfoByPK(Integer wms_cre_personnel_bill_change_id) {
		return wmscrepersonnelbillchangeDao.get(wms_cre_personnel_bill_change_id);
	}

	@Override	
	@Transactional
	public String save(WmsCrePersonnelBillChange bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrepersonnelbillchangeDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCrePersonnelBillChange bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrepersonnelbillchangeDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCrePersonnelBillChange() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCrePersonnelBillChange> getListByEntity(WmsCrePersonnelBillChange queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCrePersonnelBillChange> beanList = wmscrepersonnelbillchangeDao.getListByEntity(queryInfo);
		return beanList;
	}

    /**
     * @Title: updateLastUpdateTime
     * @Description: TODO(更新最后修改时间)
     * @param user_id
     * @return 
     * @author: jiaodelong
     * @time:2017年3月22日 下午1:40:19
     * @see com.zx.emanage.loanappro.service.IWmsCrePersonnelBillChangeService#updateLastUpdateTime(java.lang.Integer)
     * history:
     * 1、2017年3月22日 jiaodelong 创建方法
    */
    @Override
    @Transactional
    public String updateLastUpdateTime(Integer user_id)
    {
        String resStr = "success";
        int ret = 0;
        WmsCrePersonnelBillChange bean = new WmsCrePersonnelBillChange();
        bean.setPersonnel_id(user_id);
        bean.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
        
        WmsCrePersonnelBillChange change = new WmsCrePersonnelBillChange();
        change.setPersonnel_id(user_id);
        List<WmsCrePersonnelBillChange> listChange = wmscrepersonnelbillchangeDao.getListByEntity(change);
        if(listChange != null && listChange.size()>0){
            ret = wmscrepersonnelbillchangeDao.updateLastUpdateTime(bean);
        }else{  //如果没有数据，插入一条
            bean.setEnable_flag("1");
            PmPersonnel pm = pmPersonnelDao.get(user_id);  
            bean.setPersonnel_shortcode(pm.getPersonnel_shortcode());
            wmscrepersonnelbillchangeDao.save(bean);
        }
        return resStr;
    }
}
