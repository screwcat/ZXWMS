package com.zx.emanage.loanpost.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.loanfina.persist.WmsFinaCrePeriodRepayDao;
import com.zx.emanage.loanpost.persist.WmsPostRemindHistoryDao;
import com.zx.emanage.loanpost.service.IWmsPostRemindHistoryService;
import com.zx.emanage.loanpost.vo.WmsPostRemindHistorySearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsPostRemindHistory;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmspostremindhistoryService")
public class WmsPostRemindHistoryServiceImpl implements IWmsPostRemindHistoryService {
	private static Logger log = LoggerFactory.getLogger(WmsPostRemindHistoryServiceImpl.class);

	@Autowired
	private WmsPostRemindHistoryDao wmspostremindhistoryDao;
	@Autowired
	private WmsFinaCrePeriodRepayDao wmsFinaCrePeriodRepayDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsPostRemindHistorySearchBeanVO queryInfo, UserBean user) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//合同编号
        if(StringUtil.isNotBlank(queryInfo.getProtocol_code())) {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
        }
        //客户姓名
        if(StringUtil.isNotBlank(queryInfo.getDebtor_name())) {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        //客户电话
        if(StringUtil.isNotBlank(queryInfo.getDebtor_tel())) {
            paramMap.put("debtor_tel", queryInfo.getDebtor_tel());
        }
        //业务员姓名或编号
        if(StringUtil.isNotBlank(queryInfo.getSalesman_name())) {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        //贷款产品
        if(StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type())) {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        //单据状态
        if(StringUtil.isNotBlank(queryInfo.getRepay_status()) && !"-1".equals(queryInfo.getRepay_status())) {
            paramMap.put("repay_status", queryInfo.getRepay_status());
        }
        //业务员
        paramMap.put("dunning_id", user.getUserId());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmspostremindhistoryDao.search(paramMap); 
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsPostRemindHistorySearchBeanVO queryInfo, UserBean user) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//合同编号
        if(StringUtil.isNotBlank(queryInfo.getProtocol_code())) {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
        }
        //客户姓名
        if(StringUtil.isNotBlank(queryInfo.getDebtor_name())) {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        //客户电话
        if(StringUtil.isNotBlank(queryInfo.getDebtor_tel())) {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        //业务员姓名或编号
        if(StringUtil.isNotBlank(queryInfo.getSalesman_name())) {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        //贷款产品
        if(StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type())) {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        //单据状态
        if(StringUtil.isNotBlank(queryInfo.getRepay_status()) && !"-1".equals(queryInfo.getRepay_status())) {
            paramMap.put("repay_status", queryInfo.getRepay_status());
        }
        //业务员
        paramMap.put("dunning_id", user.getUserId());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmspostremindhistoryDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmspostremindhistoryDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsPostRemindHistory getInfoByPK(Integer wms_post_remind_history_id) {
		return wmspostremindhistoryDao.get(wms_post_remind_history_id);
	}
	

	@Override	
	@Transactional
	public String save(WmsPostRemindHistory bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmspostremindhistoryDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsPostRemindHistory bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmspostremindhistoryDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	public String updatePhoneRemindHistory(UserBean user,int repay_no, int wms_cre_credit_head_id, String reminder_result) {
		String resStr = "success";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("reminder_result", reminder_result);
		paramMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
		paramMap.put("repay_no", repay_no);
		paramMap.put("reminder_id", user.getUserId());
		paramMap.put("reminder_name", user.getRealname());
		paramMap.put("reminder_deptid", user.getDeptId());

		Map<String, Object> resMap = wmspostremindhistoryDao.getRemindHistoryCountAndRes(paramMap);
		long count = (long) resMap.get("count");
		String remindeResult = (String) resMap.get("reminder_result");
		int retHistory = 0;
		if(count > 1 && (remindeResult == null || remindeResult != "1")) {
			long id = wmspostremindhistoryDao.getRemindHistoryTheSecondID(paramMap);
			paramMap.put("wms_post_remind_history_id", id);
			retHistory = wmspostremindhistoryDao.updatePhoneRemindHistoryno3(paramMap);
		} else {
			retHistory = wmspostremindhistoryDao.updatePhoneRemindHistory(paramMap);
		}
		
		Map<String, Date> dateMap = wmsFinaCrePeriodRepayDao.getReminderTime(paramMap);
		Date date3 = dateMap.get("3date");
		Date date1 = dateMap.get("1date");
		String dateNow = DateUtil.date2String(new Date(), "yyyy-MM-dd");
		Date today = DateUtil.strDate(dateNow, "yyyy-MM-dd");
		int retPeriod = 0;
		if(today.getTime() >= date3.getTime() && today.getTime() < date1.getTime()) {
			retPeriod = wmsFinaCrePeriodRepayDao.updatePhoneRemind(paramMap);
		}
		//当前时间大于第三天说明延期
		if(today.getTime() >= date1.getTime()) {
			retPeriod = wmsFinaCrePeriodRepayDao.updatePhoneRemindno3(paramMap);
		}
		if (retHistory < 1 || retPeriod < 1) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	public Map<String,Object> getInfoListWithPaging(WmsPostRemindHistorySearchBeanVO queryInfo, long Id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("Id", Id);
		List<Map<String,Object>> list = wmspostremindhistoryDao.getInfoListWithPaging(Id);
		GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmspostremindhistoryDao.getInfoListCount(Id),list);
		return bean.getGridData();
	}
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsPostRemindHistory() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsPostRemindHistory> getListByEntity(WmsPostRemindHistory queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsPostRemindHistory> beanList = wmspostremindhistoryDao.getListByEntity(queryInfo);
		return beanList;
	}
	/**
	 * Description :根据 wms_cre_credit_head_id 查询历史信息
	 * @param primary key 
	 * @return Map
	 * @author baisong
	 */	
	@Override
	public  Map<String, Object> getInfoByHK(Integer wms_cre_credit_head_id) {
		Map<String, Object> map=new HashMap<>();
		List<WmsPostRemindHistory>	list=wmspostremindhistoryDao.getlist(wms_cre_credit_head_id);
		map.put("Rows", list);
		return map;
	}

}
