package com.zx.emanage.inve.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCommionRecordCoeffDao;
import com.zx.emanage.inve.persist.WmsInveUserTodoDao;
import com.zx.emanage.inve.service.IWmsInveCommionRecordCoeffService;
import com.zx.emanage.inve.vo.WmsInveCommionRecordCoeffSearchBeanVO;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsInveCommionRecordCoeff;
import com.zx.emanage.util.gen.entity.WmsInveUserTodo;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommionrecordcoeffService")
public class WmsInveCommionRecordCoeffServiceImpl implements IWmsInveCommionRecordCoeffService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommionRecordCoeffServiceImpl.class);

	@Autowired
	private WmsInveCommionRecordCoeffDao wmsinvecommionrecordcoeffDao;
	
	@Autowired
	private WmsInveUserTodoDao wmsInveUserTodoDao;
	
	@Autowired
	private PmPersonnelDao pmPersonnelDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommionRecordCoeffSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommionrecordcoeffDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	/**
	 * 根据代办事项中的月份查找佣金提成系数修正信息
	 * @return
	 */
	@Override
	public Map<String, Object> getMonthCommionListWithoutPaging(UserBean user) {
		Map<String, Object> resMap = new HashMap<String, Object>();//返回到页面的map
		//查询登录人的代办事项，如果有要处理的代办事项查询提成系数修正表返回，如果没有返回空map
		List<WmsInveUserTodo> wmsInveUserTodoList = wmsInveUserTodoDao.getWmsInveUserTodoByUserId(user.getUserId());
		WmsInveUserTodo userTodo = null;
		userTodo = !wmsInveUserTodoList.isEmpty() ? wmsInveUserTodoList.get(0)
				: null;
		if(userTodo == null)
			return resMap;
		//根据代办事项中的年月查询提成系数修正信息
		String month = userTodo.getRemark();
        String lastSeaNum = getLastSeasonNum(month);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("month", month);
        paramMap.put("lastSeaNum", lastSeaNum);
        paramMap.put("check_stamonth", "2016-08");// 统计月份
        List<Map<String, Object>> recordcoeffList = wmsinvecommionrecordcoeffDao.getCommionOfMonthListWithoutPaingByMonth(paramMap);
		resMap.put("Rows",recordcoeffList);
		return resMap;
	}

    private String getLastSeasonNum(String statics_month)
    {
        String seaNum = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        Date sd;
        try
        {
            sd = sdf.parse(statics_month);
            c.setTime(sd);
            c.add(Calendar.MONTH, -3);
            int year = c.get(Calendar.YEAR);
            seaNum = "" + year;
            int currentMonth = c.get(Calendar.MONTH) + 1;
            // 第一季度
            if (currentMonth >= 1 && currentMonth <= 3)
            {
                seaNum = seaNum + "0";
            }
            // 第二季度
            else if (currentMonth >= 4 && currentMonth <= 6)
            {
                seaNum = seaNum + "1";
            }
            // 第三季度
            else if (currentMonth >= 7 && currentMonth <= 9)
            {
                seaNum = seaNum + "2";
            }
            // 第四季度
            else if (currentMonth >= 10 && currentMonth <= 12)
            {
                seaNum = seaNum + "3";
            }
            return seaNum;
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }

	/**
	 * 根据代办事项中的月份查找提成系数审核清空信息
     * @return Map
     * @author zhangyunfei
     */
	@Override
	public Map<String, Object> getCommionListWithoutPagingByMonth(UserBean user,WmsInveCommionRecordCoeff wmsInveCommionRecordCoeff) {
        Map<String, Object> resMap = new HashMap<String, Object>();// 返回到页面的map
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("month", wmsInveCommionRecordCoeff.getStatics_month());
        paramMap.put("lastSeaNum", getLastSeasonNum(wmsInveCommionRecordCoeff.getStatics_month()));
        paramMap.put("check_stamonth", "2016-08");// 统计月份
		//根据代办事项中的年月查询提成系数修正信息
        List<Map<String, Object>> recordcoeffList = wmsinvecommionrecordcoeffDao.getCommionListWithoutPaingByMonth(paramMap);
		resMap.put("Rows",recordcoeffList);
		return resMap;
	}
	
	/**
	 * 保存提成系数修正信息
	 * 
	 * @param user
	 * @param params 前台传过来的集合字符串
	 * @return
	 */
	@Override
	@Transactional
	public String saveMonthCommionList(UserBean user, String params) {
		// 如果传入参数param为空，则直接返回。
		if (StringUtils.isEmpty(params)) {
			return "error";
		}

		// 先检查是否有代办事项，如果有进行修改，没有就不进行修改。
		// 不管是副总或财务修改完成后都要处理代办事项，修改状态及处理时间。
		List<WmsInveUserTodo> wmsInveUserTodoList = wmsInveUserTodoDao
				.getWmsInveUserTodoByUserId(user.getUserId());
		WmsInveUserTodo userTodo = null;
		if (!wmsInveUserTodoList.isEmpty()) {
			// 修改原代办事项记录
			userTodo = wmsInveUserTodoList.get(0);
			userTodo.setData_status("2");// 修改状态为已完成
			userTodo.setHandle_datetme(new Timestamp(System.currentTimeMillis()));
			wmsInveUserTodoDao.update(userTodo);// 修改原代办事项
		}
		// 如果没有代办事项，则直接返回成功信息
		if (userTodo == null) {
			return "success";
		}

		String month = userTodo.getRemark();
		Date date = DateUtil.strDate(month + "-01", null);
		int monthOfDays = DateUtil.getDaysOfMonth(date);

		Map<String, Object> upMaps = new HashMap<String, Object>();
		List<Integer> personnelIdList = new ArrayList<Integer>();

		// 将前台传进来的params字符串转成集合
		List<WmsInveCommionRecordCoeff> records = JsonUtil.jsonArrayToList(
				params, WmsInveCommionRecordCoeff.class);
		// 循环修改提成系数
		WmsInveCommionRecordCoeff commionRecordCoeff = null;// 修改时使用新的对象，以免其他值可能被修改。
		
		//通过修改系数首先需要将record表中的调整金额字段进行设置为0(要求是与股东表相关联的人员的设置成0)
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("month", userTodo.getRemark());
		wmsinvecommionrecordcoeffDao.updateCommionRecordWithAdjustAmountSetZieo(paramsMap);
		
		for (WmsInveCommionRecordCoeff recordCoeff : records) {
			commionRecordCoeff = new WmsInveCommionRecordCoeff();
			commionRecordCoeff.setWms_inve_commion_record_coeff_id(recordCoeff
					.getWms_inve_commion_record_coeff_id());
			if (PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.usercode").equals(
					user.getUserCode())) {
				// 设置副总
				if (recordCoeff.getGeneral_manager_coeff() != null) {
					// 如果冯总修改了，就保存冯总修改后的信息
					commionRecordCoeff.setGeneral_manager_coeff(recordCoeff
							.getGeneral_manager_coeff().divide(
									new BigDecimal(100), 8,
									RoundingMode.HALF_UP));
				} else {
					// 如果冯总没修改，就保存原先的信息
					commionRecordCoeff.setGeneral_manager_coeff(recordCoeff
							.getTeam_comm_coeff());
				}
				commionRecordCoeff.setGeneral_manager_id(user.getUserId());
				commionRecordCoeff.setGeneral_manager_datetime(new Timestamp(
						System.currentTimeMillis()));
			} else if (PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.cwzgcode")
					.equals(user.getUserCode())) {
				boolean flag = false;// 标志提成系数是否有变化
				// 设置财务
				commionRecordCoeff.setFinance_manager_id(user.getUserId());
				commionRecordCoeff.setFinance_manager_datetime(new Timestamp(
						System.currentTimeMillis()));
				if (recordCoeff.getFinance_manager_coeff() != null) {
					// 如果财务修改了提成系数，就保存财务修改的。
					commionRecordCoeff.setFinance_manager_coeff(recordCoeff
							.getFinance_manager_coeff().divide(
									new BigDecimal(100), 8,
									RoundingMode.HALF_UP));
				} else {
					// 如果财务没修改提成系数，就保存冯总修改的（冯总没修改时，此字段处值是最初的）。
					commionRecordCoeff.setFinance_manager_coeff(recordCoeff
							.getGeneral_manager_coeff());
				}
				//如果最初没有提成系数，之后财务确认修改了提成系数也当做是修改了提成系数
				if ((recordCoeff.getTeam_comm_coeff() == null && commionRecordCoeff
						.getFinance_manager_coeff() != null)
						|| (recordCoeff.getTeam_comm_coeff() != null && recordCoeff
								.getTeam_comm_coeff().compareTo(
										commionRecordCoeff
												.getFinance_manager_coeff()) != 0)) {
					flag = true;// 如果最初提成系数和最后财务确认提成系数不相同的话，表示冯总或财务有修改提成系数
				}
				// 计算修改系数后的金额
				// commionRecordCoeff.setTeam_comm_mon_modify((recordCoeff
				// .getTeam_comm_mon().multiply(commionRecordCoeff
				// .getFinance_manager_coeff().multiply(
				// new BigDecimal(monthOfDays)).divide(
				// new BigDecimal(monthOfDays), 8,
				// RoundingMode.HALF_UP))).setScale(2,
				// RoundingMode.HALF_UP));


				if (flag) {
					// 重新计算
					personnelIdList.add(recordCoeff.getSalesman_id());
					
					// 重新计算金额佣金统计信息
					Map<String, Object> uMaps = new HashMap<String, Object>();
					uMaps.put("comm_coeff",
							commionRecordCoeff.getFinance_manager_coeff());
					uMaps.put("month_of_days", monthOfDays);
					uMaps.put("month", userTodo.getRemark());// 年月份
					uMaps.put("personnel_id", recordCoeff.getSalesman_id());// 用户id
					uMaps.put("dept_id", recordCoeff.getDept_id());
					wmsinvecommionrecordcoeffDao
							.updateCommissionPerformanceWithVerifyAdjust(uMaps);// 重新计算新佣金提成系数及提成金额提成金额
					wmsinvecommionrecordcoeffDao.updateOldCommHisWithVerifyAdjust(uMaps);// 重新计算老佣金提成系数及提成金额
                    wmsinvecommionrecordcoeffDao.updateSpecialOldCommData(uMaps);// 计算老佣金特殊数据
                    wmsinvecommionrecordcoeffDao.updateSpecialCommionRecord(uMaps);// 老佣金提成修正，调整金额
					wmsinvecommionrecordcoeffDao.updateOldCommionRecordWithVerifyAdjust(uMaps);// 重新计算团队提成金额（老佣金）
				}
			}
			wmsinvecommionrecordcoeffDao.update(commionRecordCoeff);
		}

		
		//根据传入的条件重新计算股东单据的关联的人员的调整金额
		wmsinvecommionrecordcoeffDao.updateCommRecordAdjustAmountReappearCalculation(paramsMap);
		
		upMaps.put("month", month);
		upMaps.put("personnelIdList", personnelIdList);

		// 重新计算记录
		if (!personnelIdList.isEmpty()) {
			wmsinvecommionrecordcoeffDao
					.updateCommionRecordWithVerifyAdjust(upMaps);//重新计算团队提成金额（新佣金）
			
			wmsinvecommionrecordcoeffDao
					.updateCommionRecordCoeffWithVerfyAdjust(upMaps);
		}

		// 如果副总提成系数修改完成后，修改原代办事项记录状态。之后在插入一条财务代办记录。
		// 如果财务提成系数修改完成后，修改原代办事项记录状态。并且重新计算
		if (PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.usercode").equals(
				user.getUserCode())) {
			if (userTodo != null) {
				String remark = userTodo.getRemark();// remark中有待办事项是要修正的佣金系数提成时间
				// 查询财务人员id
				PmPersonnel pmPersonnel = new PmPersonnel();
				pmPersonnel
						.setPersonnel_shortcode(PlatformGlobalVar.SYS_PROPERTIES
								.get("com.zx.cwzgcode"));
				List<PmPersonnel> pmPersonnelList = pmPersonnelDao
						.getListByEntity(pmPersonnel);
				if (!pmPersonnelList.isEmpty()) {
					pmPersonnel = pmPersonnelList.get(0);// 该查询肯定存在记录否则该功能不能继续
					// 修改原代办事项记录后，新增一条财务的代办事项记录
					userTodo = new WmsInveUserTodo();
					userTodo.setUser_id((Integer) pmPersonnel.getPersonnel_id());// 设置财务的userId
					userTodo.setCreate_datetime(new Timestamp(System
							.currentTimeMillis()));
					userTodo.setCreate_user_id(user.getUserId());
					userTodo.setData_status("1");// 设置待办事项状态为办理中
					userTodo.setRemark(remark);// 设置系数提成时间
					wmsInveUserTodoDao.save(userTodo);
				}
			}
		}
		return "success";
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommionRecordCoeffSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecommionrecordcoeffDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommionrecordcoeffDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCommionRecordCoeff getInfoByPK(Integer wms_inve_commion_record_coeff_id) {
		return wmsinvecommionrecordcoeffDao.get(wms_inve_commion_record_coeff_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommionRecordCoeff bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommionrecordcoeffDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommionRecordCoeff bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommionrecordcoeffDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommionRecordCoeff() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommionRecordCoeff> getListByEntity(WmsInveCommionRecordCoeff queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommionRecordCoeff> beanList = wmsinvecommionrecordcoeffDao.getListByEntity(queryInfo);
		return beanList;
	}
}
