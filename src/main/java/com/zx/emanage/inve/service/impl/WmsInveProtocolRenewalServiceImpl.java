package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import jodd.typeconverter.Convert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveProtocolRenewalDao;
import com.zx.emanage.inve.service.IWmsInveProtocolRenewalService;
import com.zx.emanage.sysmanage.persist.SysSpecialUserDao;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.SysSpecialUser;
import com.zx.emanage.util.gen.entity.WmsInveProtocolRenewal;
import com.zx.emanage.inve.vo.WmsInveProtocolRenewalSearchBeanVO;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinveprotocolrenewalService")
public class WmsInveProtocolRenewalServiceImpl implements IWmsInveProtocolRenewalService {
	private static Logger log = LoggerFactory.getLogger(WmsInveProtocolRenewalServiceImpl.class);

	@Autowired
	private WmsInveProtocolRenewalDao wmsinveprotocolrenewalDao;
	
	@Autowired
	private SysSpecialUserDao specialUserDao;
    /**
     * 获取协议续期页面列表数据-导出Excel
     * 
     * @param queryInfo
     * @return Map
     * @author baisong
     */
	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveProtocolRenewalSearchBeanVO queryInfo,UserBean user) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinveprotocolrenewalDao.search(paramMap);
	    SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser>specilaUsers = specialUserDao.getListByEntity(specialUser);
        list=SysTools.setListView(list, user, specilaUsers);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}
    /**
     * 获取协议续期页面列表数据
     * 
     * @param queryInfo
     * @return Map
     * @author baisong
     */
	@Override
	public Map<String, Object> getListWithPaging(WmsInveProtocolRenewalSearchBeanVO queryInfo,UserBean user) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", "%" + queryInfo.getCus_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinveprotocolrenewalDao.search(paramMap); 
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser>specilaUsers = specialUserDao.getListByEntity(specialUser);
        list=SysTools.setListView(list, user, specilaUsers);
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinveprotocolrenewalDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveProtocolRenewal getInfoByPK(Integer wms_inve_protocol_renewal_id) {
		return wmsinveprotocolrenewalDao.get(wms_inve_protocol_renewal_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveProtocolRenewal bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinveprotocolrenewalDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveProtocolRenewal bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinveprotocolrenewalDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveProtocolRenewal() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveProtocolRenewal> getListByEntity(WmsInveProtocolRenewal queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveProtocolRenewal> beanList = wmsinveprotocolrenewalDao.getListByEntity(queryInfo);
		return beanList;
	}
}
