package com.zx.emanage.sysmanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.sysmanage.persist.WmsInveTaxpointRulesDao;
import com.zx.emanage.sysmanage.service.IWmsInveTaxpointRulesService;
import com.zx.emanage.util.gen.entity.WmsInveTaxpointRules;
import com.zx.emanage.sysmanage.vo.WmsInveTaxpointRulesSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvetaxpointrulesService")
public class WmsInveTaxpointRulesServiceImpl implements IWmsInveTaxpointRulesService {
	private static Logger log = LoggerFactory.getLogger(WmsInveTaxpointRulesServiceImpl.class);

	@Autowired
	private WmsInveTaxpointRulesDao wmsinvetaxpointrulesDao;
	
	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveTaxpointRulesSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvetaxpointrulesDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveTaxpointRulesSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvetaxpointrulesDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvetaxpointrulesDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveTaxpointRules getInfoByPK(Integer wms_inve_taxpoint_rules_id) {
		return wmsinvetaxpointrulesDao.get(wms_inve_taxpoint_rules_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveTaxpointRules bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvetaxpointrulesDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}
	
	@Override
	@Transactional
	public String update(WmsInveTaxpointRules bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvetaxpointrulesDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * @Title:taxfixedToPage
	 * Description :获取税点固定值
	 * @return "success" or "error"
	 * @author hancd
	 * @date 2015年12月28日
	 */
	@Override
	public String taxfixedToPage() {
		Map<String, Object> map=wmsinvetaxpointrulesDao.getTax_fixed();
		String tax="";
		for(String key:map.keySet()){
			tax=(String) map.get(key);
		}
		return tax;
	}
	/**
	 * @Title:doChange
	 * Description :理财税点信息保存操作
	 * @param request
	 * @param beanJSON
	 * @return "success" or "error"
	 * @author hancd
	 * @date 2015年12月28日
	 */
	@Override
	@Transactional
	public String doChange(String beanJson, UserBean user) {
		String resStr = "success";
		List<WmsInveTaxpointRules> list = JsonUtil.jsonArrayToList(beanJson, WmsInveTaxpointRules.class);
		try {
			for(int i=0;i<list.size();i++) {	
				//获取每个wmsInveTaxpointRules对象
				WmsInveTaxpointRules wmsInveTaxpointRules=list.get(i);
				//如果必填的三个属性是否有一项或多项为空
				if(wmsInveTaxpointRules.getSingle_start()==null||wmsInveTaxpointRules.getSingle_start().toString()==""
				   ||wmsInveTaxpointRules.getSingle_end()==null||wmsInveTaxpointRules.getSingle_end().toString()==""
				   ||wmsInveTaxpointRules.getTax_coefficient()==null||wmsInveTaxpointRules.getTax_coefficient().toString()==""){
					resStr="800334";
					return resStr;
				}
				//如果添加的数据多余一条判断数据添写是否正确
				if(list.size()>1){
					for(int j=i+1;j<list.size();j++){
						WmsInveTaxpointRules wmsTax=list.get(j);
						if(wmsTax.getSingle_start()<wmsInveTaxpointRules.getSingle_end()){
							resStr="800138";
							return resStr;
						}
					}
				}
				//判断上单金额是否大于追单金额
				if(wmsInveTaxpointRules.getSingle_end()<=wmsInveTaxpointRules.getSingle_start()){
					resStr="800137";
					return resStr;
				}
			}
			//清空数据
			wmsinvetaxpointrulesDao.clear();
			for(int i=0;i<list.size();i++) {	
				WmsInveTaxpointRules wmsInveTaxpointRules=list.get(i);
				wmsInveTaxpointRules.setCreate_user_id(user.getUserId());
				wmsInveTaxpointRules.setCreate_datetime(new java.sql.Timestamp(System.currentTimeMillis()));
				wmsInveTaxpointRules.setEnable_flag("1");
				wmsinvetaxpointrulesDao.save(wmsInveTaxpointRules);	
			}
		} catch (Exception e) {
			resStr = "error";
		}
		return resStr;
	}
	
}
