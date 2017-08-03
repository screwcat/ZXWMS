package com.zx.emanage.cremanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import jodd.typeconverter.Convert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreHousingFileInfoDao;
import com.zx.emanage.cremanage.service.IWmsCreHousingFileInfoService;
import com.zx.emanage.util.gen.domain.WmsCusCustomerLineHouseinfo;
import com.zx.emanage.util.gen.entity.WmsCreHousingFileInfo;
import com.zx.emanage.cremanage.vo.WmsCreHousingFileInfoSearchBeanVO;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrehousingfileinfoService")
public class WmsCreHousingFileInfoServiceImpl implements IWmsCreHousingFileInfoService {
	private static Logger log = LoggerFactory.getLogger(WmsCreHousingFileInfoServiceImpl.class);

	@Autowired
	private WmsCreHousingFileInfoDao wmscrehousingfileinfoDao;
//贷款列表导出
	@Override
	public Map<String, Object> getListWithoutPaging(UserBean user,WmsCreHousingFileInfo queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	 	  // 判断单据编码
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        // 判断办件员
        if (StringUtil.isNotBlank(queryInfo.getCreate_user_name()))
        {
            paramMap.put("create_user_name", "%" + queryInfo.getCreate_user_name() + "%");
        }
        // 判断时间
        if (queryInfo.getCreate_timestamp()!=null)
        {
            paramMap.put("create_timestamp",queryInfo.getCreate_timestamp() + " 00:00:00");
        }
        paramMap.put("create_user_city_code", user.getUser_regionNumber());//城市编码
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmscrehousingfileinfoDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsCreHousingFileInfoSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmscrehousingfileinfoDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrehousingfileinfoDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCreHousingFileInfo getInfoByPK(Integer wms_cre_housing_file_info_id) {
		return wmscrehousingfileinfoDao.get(wms_cre_housing_file_info_id);
	}

	@Override	
	@Transactional
	public String save(WmsCreHousingFileInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrehousingfileinfoDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCreHousingFileInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrehousingfileinfoDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCreHousingFileInfo() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreHousingFileInfo> getListByEntity(WmsCreHousingFileInfo queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreHousingFileInfo> beanList = wmscrehousingfileinfoDao.getListByEntity(queryInfo);
		return beanList;
	}
	//贷款申请列表显示
	@Override
	public Map<String, Object> getHouseCreditList(WmsCreHousingFileInfo queryInfo,UserBean user) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		  // 判断单据编码
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        // 判断办件员
        if (StringUtil.isNotBlank(queryInfo.getCreate_user_name()))
        {
            paramMap.put("create_user_name", "%" + queryInfo.getCreate_user_name() + "%");
        }
        // 判断时间
        if (queryInfo.getCreate_timestamp()!=null)
        {
            paramMap.put("create_timestamp","%" + queryInfo.getCreate_timestamp() + "%");
        }
        paramMap.put("create_user_city_code", user.getUser_regionNumber());//城市编码
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmscrehousingfileinfoDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrehousingfileinfoDao.findCount(paramMap),list);
		return bean.getGridData();
	}
}
