package com.zx.emanage.reportmanage.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.emanage.reportmanage.persist.WmsInveAchievementReportDao;
import com.zx.emanage.reportmanage.persist.WmsProviderForAppDao;
import com.zx.emanage.reportmanage.service.WmsProviderForAppService;
import com.zx.emanage.reportmanage.vo.WmsProviderForAppVO;
/**
 * @ClassName: WmsProviderForAppServiceImpl
 * @Description: wms为MOA提供接口实现类,
 * 计算结果保留2位，四舍五入
 * @author WangShuai
 * @date 2017年1月3日
 * @version V1.0
 * history:
 * 1、2017年1月3日 WangShuai 创建文件
 */
@Service("wmsProviderForAppService")
public class WmsProviderForAppServiceImpl implements WmsProviderForAppService{
	//新增规则ID
    public static int RULEMODULE_ADD=1;
	// 新旧产品截止时间
    public static String LIMIT_DATE = "2016-04-01";
    // 不参与计算的产品ID
    public static String REJECT_PROID = "34,44";
    // 特殊产品
    private static String specialpro = "25,32,33";
	
	private static String AC_STA_TP_CM="st_cm";//业绩客户经理临时查询类型
	private static String AC_STA_TP_TM="st_tm";//业绩团队临时查询类型
	private static String AC_STA_TP_DP="st_dt";//业绩部门临时查询类型
	private static String AC_STA_TP_VE="st_ve";//业绩部门临时查询类型
	private static String AC_STA_TP_CR="st_cr";//业绩中心临时查询类型
	
	
	
	private int GENERAL_MANAGER_ID = 184;//总经理ID
	
	private static int PAGE_SIZE=10;//设置默认为10条没页
	
	private static String RET_CODE_SUCC="000";//请求成功
	private static String RET_MSG_SUCC="请求成功";//请求成功
	
	//新增奖系数下限
	private static BigDecimal new_add_coeff_low=new BigDecimal("0.008");
	//新增奖系数上限
	private static BigDecimal new_add_coeff_high=new BigDecimal("0.012");
	//在职存量系数
	private static BigDecimal stock_coeff_onl=new BigDecimal("0.001");
	//兼职存量系数
	private static BigDecimal stock_coeff_conp=new BigDecimal("0.002");
	
    // private static String
    // new_pros="14,15,16,20,21,23,24,25,26,27,28,29,30,31,32,33,35,36,37,38,39,40,41,43,42";
	
	@Autowired
    WmsInveAchievementReportDao wmsInveAchievementReportDao;

    @Autowired
	WmsProviderForAppDao wmsProviderForAppDao;
//	//在实例化类的时候就处理好新产品问题，避免后面浪费时间
//	static {
//		Map<String,String> res_tep=new HashMap<String,String>();
//		res_tep=wmsInveAchievementReportDao.getNewProListByRule(RULEMODULE_ADD);
//		new_pros=res_tep.get("wms_inve_pruduct_category_ids");
//	}
	
    private String getNewPros()
    {
        // 获取新产品
        Map<String, String> res_tep = new HashMap<String, String>();
        res_tep = wmsInveAchievementReportDao.getNewProListByRule(RULEMODULE_ADD);
        return res_tep.get("wms_inve_pruduct_category_ids");
    }

	/**
	 * @Title: getPersonnelcomm_dept_sta_v197
	 * @Description: 接口18，根据客户经理汇总
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年1月3日 下午4:00:44
	 * history:
	 * 1、2017年1月3日 WangShuai 创建方法
	*/
	@Override
	public Map<String,Object> getPersonnelcomm_dept_sta_v197(
			WmsProviderForAppVO wmsProviderForAppVO) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		//取参数
		Map<String,Object> para=new HashMap<String,Object>();
		String statics_month=wmsProviderForAppVO.getStatics_month();
		String dept_ids=wmsProviderForAppVO.getDept_ids();
        String personnel_info = wmsProviderForAppVO.getPersonnel_info();
		int page=Integer.parseInt(StringUtils.isEmpty(wmsProviderForAppVO.getPage())?"0":wmsProviderForAppVO.getPage());
		int page_size=Integer.parseInt(StringUtils.isEmpty(wmsProviderForAppVO.getPage_size())?"0":wmsProviderForAppVO.getPage_size());
		para.put("statics_month", statics_month);
		para.put("dept_ids", dept_ids);
		para.put("personnel_info", personnel_info);
		para.put("page", checkPage(page));
		para.put("page_size", checkPageSize(page_size));
		//查询结果
		List<Map<String,Object>> resList=new ArrayList<Map<String,Object>>();
		//判断查询月份是否是当前时间
		Calendar ca = Calendar.getInstance();
		String tempca=sdf.format(ca.getTime());
		//查询时间是当月,则需要统计即时数据
		if(tempca.equals(statics_month))
		{
			
		}
		//不是当月，从已统计信息中获取
		else
		{
			//获取统计信息
			resList=wmsProviderForAppDao.getPersonnelCommDeptStaOtherMonth(para);
		}
		
		
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("ret_code", RET_CODE_SUCC);
		res.put("ret_msg", RET_MSG_SUCC);
		res.put("ret_data",resList);
		return res;
	}
	
	
	/**
	 * @Title: checkPageSize
	 * @Description: 设置默认的pageSize
	 * @param page_size
	 * @return 
	 * @author: WangShuai
	 * @time:2017年1月4日 上午10:46:32
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
	private Object checkPageSize(int page_size) {
		return 0==page_size?PAGE_SIZE:page_size;
	}


	/**
	 * @Title: checkPage
	 * @Description: 设置默认的page，如果为0则设置为第一页
	 * @param page
	 * @return 
	 * @author: WangShuai
	 * @time:2017年1月4日 上午10:46:47
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
	private Object checkPage(int page) {
		return 0==page?1:page;
	}


	/**
	 * @Title: getPersonnelcomm_comm_item_sta_v197
	 * @Description: 接口19，自己的个人佣金或某一客户经理的个人佣金
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年1月4日 上午10:39:45
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
	@Override
	public Map<String, Object> getPersonnelcomm_comm_item_sta_v197(
			WmsProviderForAppVO wmsProviderForAppVO) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		//取参数
		Map<String,Object> para=new HashMap<String,Object>();
		String statics_month=wmsProviderForAppVO.getStatics_month();
		String personnel_ids = wmsProviderForAppVO.getPersonnel_ids();
		int page=Integer.parseInt(StringUtils.isEmpty(wmsProviderForAppVO.getPage())?"0":wmsProviderForAppVO.getPage());
		int page_size=Integer.parseInt(StringUtils.isEmpty(wmsProviderForAppVO.getPage_size())?"0":wmsProviderForAppVO.getPage_size());
		para.put("statics_month", statics_month);
		para.put("personnel_ids", personnel_ids);
		para.put("page", checkPage(page));
		para.put("page_size", checkPageSize(page_size));
		//查询结果
		List<Map<String,Object>> resList=new ArrayList<Map<String,Object>>();
		//判断查询月份是否是当前时间
		Calendar ca = Calendar.getInstance();
		String tempca=sdf.format(ca.getTime());
		//查询时间是当月,则需要统计即时数据
		if(tempca.equals(statics_month))
		{
			
		}
		//不是当月，从已统计信息中获取
		else
		{
			//获取统计信息
			resList=wmsProviderForAppDao.getPersonnelcommCommItemSta(para);
		}
		
		
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("ret_code", RET_CODE_SUCC);
		res.put("ret_msg", RET_MSG_SUCC);
		res.put("ret_data",resList);
		
		
		return res;
	}

	/**
	 * @Title: getPersonnel_comm_inve_sta_v197
	 * @Description: 接口20，用户查看每一单带来的佣金情况；
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年1月4日 上午11:11:27
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
	@Override
	public Map<String, Object> getPersonnel_comm_inve_sta_v197(
			WmsProviderForAppVO wmsProviderForAppVO) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		//取参数
		Map<String,Object> para=new HashMap<String,Object>();
		String statics_month=wmsProviderForAppVO.getStatics_month();
		String personnel_ids = wmsProviderForAppVO.getPersonnel_ids();
		String personnel_info = wmsProviderForAppVO.getPersonnel_info();
		String comm_item_ids = wmsProviderForAppVO.getComm_item_ids();
		int page=Integer.parseInt(StringUtils.isEmpty(wmsProviderForAppVO.getPage())?"0":wmsProviderForAppVO.getPage());
		int page_size=Integer.parseInt(StringUtils.isEmpty(wmsProviderForAppVO.getPage_size())?"0":wmsProviderForAppVO.getPage_size());
		para.put("statics_month", statics_month);
		para.put("personnel_ids", personnel_ids);
		para.put("personnel_info", personnel_info);
		para.put("comm_item_ids", comm_item_ids);
		para.put("page", checkPage(page));
		para.put("page_size", checkPageSize(page_size));
		//查询结果
		List<Map<String,Object>> resList=new ArrayList<Map<String,Object>>();
		//判断查询月份是否是当前时间
		Calendar ca = Calendar.getInstance();
		String tempca=sdf.format(ca.getTime());
		//查询时间是当月,则需要统计即时数据
		if(tempca.equals(statics_month))
		{
			
		}
		//不是当月，从已统计信息中获取
		else
		{
			//查询老佣金
			if("01".equals(comm_item_ids))
			{
				resList=wmsProviderForAppDao.getPersonnelCommInveSta_old(para);
			}
			//查询新开单将
			else if("02".equals(comm_item_ids))
			{
				resList=wmsProviderForAppDao.getPersonnelCommInveSta_new_add(para);
			}
			//查询新存量奖
			else if("03".equals(comm_item_ids))
			{
				resList=wmsProviderForAppDao.getPersonnelCommInveSta_new_stock(para);
			}
		}
		
		
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("ret_code", RET_CODE_SUCC);
		res.put("ret_msg", RET_MSG_SUCC);
		res.put("ret_data",resList);
		
		return res;
	}

	/**
	 * @Title: getAch_center_sta_v197
	 * @Description: 接口21，用户查看各个中心的业绩情况
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2017年1月4日 下午2:42:47
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
	@Override
	public Map<String, Object> getAch_center_sta_v197(
			WmsProviderForAppVO wmsProviderForAppVO) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		//取参数
		Map<String,Object> para=new HashMap<String,Object>();
		String statics_month=wmsProviderForAppVO.getStatics_month();
		String sel_pid=wmsProviderForAppVO.getSel_pid();
		//目前要查全公司，所以即使传参数也不处理，需要处理用 find_in_set 取部门来处理
		String cener_ids = wmsProviderForAppVO.getCener_ids();
		int page=Integer.parseInt(StringUtils.isEmpty(wmsProviderForAppVO.getPage())?"0":wmsProviderForAppVO.getPage());
		int page_size=Integer.parseInt(StringUtils.isEmpty(wmsProviderForAppVO.getPage_size())?"0":wmsProviderForAppVO.getPage_size());
		para.put("statics_month", statics_month);
		para.put("cener_ids", cener_ids);
		para.put("statics_personnel_id", sel_pid);
		para.put("statics_type", AC_STA_TP_CR);//部门统计处理
		para.put("page", checkPage(page));
		para.put("page_size", checkPageSize(page_size));
		//查询结果
		List<Map<String,Object>> resList=new ArrayList<Map<String,Object>>();
		//判断查询月份是否是当前时间
		Calendar ca = Calendar.getInstance();
		String tempca=sdf.format(ca.getTime());
		//查询时间是当月,则需要统计即时数据
		if(tempca.equals(statics_month))
		{
			long stl=System.currentTimeMillis();
			System.out.println("21接口即时计算开始时间："+System.currentTimeMillis());
			//获取即时数据问题
			// 进行操作的参数
			String sta_begin_date="";
			String sta_end_date="";
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf1.parse(statics_month);
			Calendar cat = Calendar.getInstance();
			cat.setTime(date);
			cat.set(Calendar.DAY_OF_MONTH, 1);
			// 获取当月的第一天
			sta_begin_date = sdf2.format(cat.getTime());
			ca = Calendar.getInstance();
			ca.add(Calendar.DAY_OF_MONTH, 1);
			// 获取当前时间的下一天
			sta_end_date = sdf2.format(ca.getTime());
			
			para.put("new_pros", getNewPros());
			para.put("limit_date", LIMIT_DATE);
			para.put("reject_proid", REJECT_PROID);
			para.put("specialpro", specialpro);
			para.put("sta_begin_date", sta_begin_date);
			para.put("sta_end_date", sta_end_date);
			//进行临时计算，将数据放入指定的表
			//step1 删除之前的统计
			wmsProviderForAppDao.deleteAchPersonnelStaCurr(para);
			//step2 计算全公司
			wmsProviderForAppDao.insertAchAllPerStaCurr(para);
			//
			wmsProviderForAppDao.updateAchAllPerAddCurr(para);
			//团队本金续投金额
			wmsProviderForAppDao.updateAchPersonnelReinveCurr(para);
			//查询
			resList=wmsProviderForAppDao.getAchCenterStaCurr(para);
			System.out.println("21接口即时计算结束时间："+System.currentTimeMillis()+";用时:"+((System.currentTimeMillis()-stl)/1000)+"秒");
		}
		//不是当月，从已统计信息中获取
		else
		{
			resList=wmsProviderForAppDao.getAchCenterSta(para);
		}
		Map<String,Object> dataMap=new HashMap<String,Object>();
		//转换名称
		String center_name="";
		switch (cener_ids)
		{
			case "01": center_name="全公司";break;
			case "02": center_name="财富管理中心";break;
			case "03": center_name="普惠金融中心";break;
			case "04": center_name="互联网中心";break;
			case "05": center_name="职能中心";break;
			default: center_name="全公司";
		}
		dataMap.put("center_name", center_name);
		dataMap.put("center_ach_list", resList);
		
		//处理返回值
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("ret_code", RET_CODE_SUCC);
		res.put("ret_msg", RET_MSG_SUCC);
		res.put("ret_data",dataMap);
		
		return res;
	}

	/**
	 * @Title: getAch_dept_sta_v197
	 * @Description: 接口22，按团队汇总业绩,采用根据team汇总，之后在汇总查询的方法
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2017年1月4日 下午4:12:23
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
	@Override
	public Map<String, Object> getAch_dept_sta_v197(
			WmsProviderForAppVO wmsProviderForAppVO) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		//取参数
		Map<String,Object> para=new HashMap<String,Object>();
		String statics_month=wmsProviderForAppVO.getStatics_month();
		//目前要查全公司，所以即使传参数也不处理，需要处理用 find_in_set 取部门来处理
		//临时查询人员ID
		String sel_pid=wmsProviderForAppVO.getSel_pid();
		String dept_ids = wmsProviderForAppVO.getDept_ids();
		int page=Integer.parseInt(StringUtils.isEmpty(wmsProviderForAppVO.getPage())?"0":wmsProviderForAppVO.getPage());
		int page_size=Integer.parseInt(StringUtils.isEmpty(wmsProviderForAppVO.getPage_size())?"0":wmsProviderForAppVO.getPage_size());
		para.put("statics_month", statics_month);
		para.put("dept_ids", dept_ids);
		para.put("statics_personnel_id", sel_pid);
		para.put("statics_type", AC_STA_TP_DP);//部门统计处理
		para.put("page", checkPage(page));
		para.put("page_size", checkPageSize(page_size));
		if(StringUtils.isEmpty(dept_ids)){
			Map<String,Object> res=new HashMap<String,Object>();
			res.put("ret_code", RET_CODE_SUCC);
			res.put("ret_msg", RET_MSG_SUCC);
			
			return res;
		}
		
		//查询结果
		List<Map<String,Object>> resList=new ArrayList<Map<String,Object>>();
		//判断查询月份是否是当前时间
		Calendar ca = Calendar.getInstance();
		String tempca=sdf.format(ca.getTime());
		//查询时间是当月,则需要统计即时数据
		if(tempca.equals(statics_month))
		{
			long stl=System.currentTimeMillis();
			System.out.println("22接口即时计算开始时间："+System.currentTimeMillis());
			//获取即时数据问题
			// 进行操作的参数
			String sta_begin_date="";
			String sta_end_date="";
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf1.parse(statics_month);
			Calendar cat = Calendar.getInstance();
			cat.setTime(date);
			cat.set(Calendar.DAY_OF_MONTH, 1);
			// 获取当月的第一天
			sta_begin_date = sdf2.format(cat.getTime());
			ca = Calendar.getInstance();
			ca.add(Calendar.DAY_OF_MONTH, 1);
			// 获取当前时间的下一天
			sta_end_date = sdf2.format(ca.getTime());
			
			para.put("new_pros", getNewPros());
			para.put("limit_date", LIMIT_DATE);
			para.put("reject_proid", REJECT_PROID);
			para.put("specialpro", specialpro);
			para.put("sta_begin_date", sta_begin_date);
			para.put("sta_end_date", sta_end_date);
			//进行临时计算，将数据放入指定的表
			//step1 删除之前的统计
			wmsProviderForAppDao.deleteAchPersonnelStaCurr(para);
			//计算团队统计存量，并插入
			wmsProviderForAppDao.insertTeamDeptStockDataCurr(para);
			//计算团队统计新增，更新
			wmsProviderForAppDao.updateTeamDeptAddDataCurr(para);
			//团队本金续投金额
			wmsProviderForAppDao.updateTeamReinveMonCurr(para);
			//chaxun 
			resList=wmsProviderForAppDao.getAchDeptStaCurr(para);
			System.out.println("22接口即时计算结束时间："+System.currentTimeMillis()+";用时:"+((System.currentTimeMillis()-stl)/1000)+"秒");
		}
		//不是当月，从已统计信息中获取
		else
		{
			resList=wmsProviderForAppDao.getAchDeptSta(para);
		}
		//处理返回值
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("ret_code", RET_CODE_SUCC);
		res.put("ret_msg", RET_MSG_SUCC);
		res.put("ret_data",resList);
		
		return res;
	}

	/**
	 * @Title: getAch_team_sta_v197
	 * @Description: 接口23，按团队汇总业绩
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2017年1月4日 下午4:33:21
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
	@Override
	public Map<String, Object> getAch_team_sta_v197(
			WmsProviderForAppVO wmsProviderForAppVO) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		//取参数
		Map<String,Object> para=new HashMap<String,Object>();
		String statics_month=wmsProviderForAppVO.getStatics_month();
		//临时查询人员ID
		String sel_pid=wmsProviderForAppVO.getSel_pid();
		//目前要查全公司，所以即使传参数也不处理，需要处理用 find_in_set 取部门来处理
		String dept_ids = wmsProviderForAppVO.getDept_ids();
		String team_ids = wmsProviderForAppVO.getTeam_ids();
		int page=Integer.parseInt(StringUtils.isEmpty(wmsProviderForAppVO.getPage())?"0":wmsProviderForAppVO.getPage());
		int page_size=Integer.parseInt(StringUtils.isEmpty(wmsProviderForAppVO.getPage_size())?"0":wmsProviderForAppVO.getPage_size());
		para.put("statics_month", statics_month);
		para.put("dept_ids", dept_ids);
		para.put("team_ids", team_ids);
		para.put("page", checkPage(page));
		para.put("statics_personnel_id", sel_pid);
		para.put("page_size", checkPageSize(page_size));
		para.put("statics_type", AC_STA_TP_TM);//团队统计处理
		//查询结果
		List<Map<String,Object>> resList=new ArrayList<Map<String,Object>>();
		//判断查询月份是否是当前时间
		Calendar ca = Calendar.getInstance();
		String tempca=sdf.format(ca.getTime());
		//查询时间是当月,则需要统计即时数据
		if(tempca.equals(statics_month))
		{
			long stl=System.currentTimeMillis();
			System.out.println("23接口即时计算开始时间："+System.currentTimeMillis());
			//获取即时数据问题
			// 进行操作的参数
			String sta_begin_date="";
			String sta_end_date="";
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf1.parse(statics_month);
			Calendar cat = Calendar.getInstance();
			cat.setTime(date);
			cat.set(Calendar.DAY_OF_MONTH, 1);
			// 获取当月的第一天
			sta_begin_date = sdf2.format(cat.getTime());
			ca = Calendar.getInstance();
			ca.add(Calendar.DAY_OF_MONTH, 1);
			// 获取当前时间的下一天
			sta_end_date = sdf2.format(ca.getTime());
			
			para.put("new_pros", getNewPros());
			para.put("limit_date", LIMIT_DATE);
			para.put("reject_proid", REJECT_PROID);
			para.put("specialpro", specialpro);
			para.put("sta_begin_date", sta_begin_date);
			para.put("sta_end_date", sta_end_date);
			//进行临时计算，将数据放入指定的表
			//step1 删除之前的统计
			wmsProviderForAppDao.deleteAchPersonnelStaCurr(para);
			//计算团队统计存量，并插入
			wmsProviderForAppDao.insertTeamDeptStockDataCurr(para);
			//计算团队统计新增，更新
			wmsProviderForAppDao.updateTeamDeptAddDataCurr(para);
			//团队本金续投金额
			wmsProviderForAppDao.updateTeamReinveMonCurr(para);
			//查询操作结果
			resList=wmsProviderForAppDao.getAchTeamStaCurr(para);
			System.out.println("23接口即时计算结束时间："+System.currentTimeMillis()+";用时:"+((System.currentTimeMillis()-stl)/1000)+"秒");
		}
		//不是当月，从已统计信息中获取
		else
		{
			resList=wmsProviderForAppDao.getAchTeamSta(para);
		}
		//处理返回值
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("ret_code", RET_CODE_SUCC);
		res.put("ret_msg", RET_MSG_SUCC);
		res.put("ret_data",resList);
		
		return res;
	}

	/**
	 * @Title: getAch_personnel_sta_v197
	 * @Description: 接口24，按个人汇总业绩
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2017年1月4日 下午5:27:27
	 * history:
	 * 1、2017年1月4日 WangShuai 创建方法
	*/
	@Override
	public Map<String, Object> getAch_personnel_sta_v197(
			WmsProviderForAppVO wmsProviderForAppVO) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		//取参数
		Map<String,Object> para=new HashMap<String,Object>();
		String statics_month=wmsProviderForAppVO.getStatics_month();
		//目前要查全公司，所以即使传参数也不处理，需要处理用 find_in_set 取部门来处理
		String personnel_ids = wmsProviderForAppVO.getPersonnel_ids();
		String personnel_info = wmsProviderForAppVO.getPersonnel_info();
		String team_ids = wmsProviderForAppVO.getTeam_ids();
		//临时查询人员ID
		String sel_pid=wmsProviderForAppVO.getSel_pid();
		
		int page=Integer.parseInt(StringUtils.isEmpty(wmsProviderForAppVO.getPage())?"0":wmsProviderForAppVO.getPage());
		int page_size=Integer.parseInt(StringUtils.isEmpty(wmsProviderForAppVO.getPage_size())?"0":wmsProviderForAppVO.getPage_size());
		para.put("statics_month", statics_month);
		para.put("personnel_ids", personnel_ids);
		para.put("personnel_info", personnel_info);
		para.put("team_ids", team_ids);
		para.put("statics_personnel_id", sel_pid);
		para.put("statics_type", AC_STA_TP_CM);
		para.put("page", checkPage(page));
		para.put("page_size", checkPageSize(page_size));
		//查询结果
		List<Map<String,Object>> resList=new ArrayList<Map<String,Object>>();
		//判断查询月份是否是当前时间
		Calendar ca = Calendar.getInstance();
		String tempca=sdf.format(ca.getTime());
		//查询时间是当月,则需要统计即时数据
		if(tempca.equals(statics_month))
		{
			long stl=System.currentTimeMillis();
			System.out.println("24接口即时计算开始时间："+System.currentTimeMillis());
			// 进行操作的参数
			String sta_begin_date="";
			String sta_end_date="";
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
	        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	        Date date = sdf1.parse(statics_month);
	        Calendar cat = Calendar.getInstance();
	        cat.setTime(date);
	        cat.set(Calendar.DAY_OF_MONTH, 1);
	        // 获取当月的第一天
	        sta_begin_date = sdf2.format(cat.getTime());
	        ca = Calendar.getInstance();
	        ca.add(Calendar.DAY_OF_MONTH, 1);
	        // 获取当前时间的下一天
	        sta_end_date = sdf2.format(ca.getTime());
			
			
            para.put("new_pros", getNewPros());
			para.put("limit_date", LIMIT_DATE);
			para.put("reject_proid", REJECT_PROID);
			para.put("specialpro", specialpro);
			para.put("sta_begin_date", sta_begin_date);
			para.put("sta_end_date", sta_end_date);
			//进行临时计算，将数据放入指定的表
			//step1 删除之前的统计
			wmsProviderForAppDao.deleteAchPersonnelStaCurr(para);
			//存量处理
			wmsProviderForAppDao.insertAchPersonnelStaStockCurr(para);
			//处理个人的本金续投金额
			wmsProviderForAppDao.updateAchPersonnelReinveCurr(para);
			//新增处理
			wmsProviderForAppDao.updateAchPersonnelStaAddCurr(para);
			
			resList=wmsProviderForAppDao.getAchPersonnelSta_curr(para);
			System.out.println("24接口即时计算结束时间："+System.currentTimeMillis()+";用时:"+((System.currentTimeMillis()-stl)/1000)+"秒");
		}
		//不是当月，从已统计信息中获取
		else
		{
			resList=wmsProviderForAppDao.getAchPersonnelSta(para);
		}
		//处理返回值
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("ret_code", RET_CODE_SUCC);
		res.put("ret_msg", RET_MSG_SUCC);
		res.put("ret_data",resList);
		
		return res;
	}

	/**
	 * @Title: getAch_inve_sta_v197
	 * @Description: 接口汇总，按单据展现信息，接口25
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2017年1月5日 上午10:42:13
	 * history:
	 * 1、2017年1月5日 WangShuai 创建方法
	*/
	@Override
	public Map<String, Object> getAch_inve_sta_v197(
			WmsProviderForAppVO wmsProviderForAppVO) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		//取参数
		Map<String,Object> para=new HashMap<String,Object>();
		String statics_month=wmsProviderForAppVO.getStatics_month();
		//目前要查全公司，所以即使传参数也不处理，需要处理用 find_in_set 取部门来处理
		//临时查询人员ID
		String sel_pid=wmsProviderForAppVO.getSel_pid();
		String personnel_ids = wmsProviderForAppVO.getPersonnel_ids();
		String personnel_info = wmsProviderForAppVO.getPersonnel_info();
		int page=Integer.parseInt(StringUtils.isEmpty(wmsProviderForAppVO.getPage())?"0":wmsProviderForAppVO.getPage());
		int page_size=Integer.parseInt(StringUtils.isEmpty(wmsProviderForAppVO.getPage_size())?"0":wmsProviderForAppVO.getPage_size());
		para.put("statics_month", statics_month);
		para.put("personnel_ids", personnel_ids);
		para.put("personnel_info", personnel_info);
		para.put("statics_personnel_id", sel_pid);
		para.put("statics_type", AC_STA_TP_CM);
		para.put("page", checkPage(page));
		para.put("page_size", checkPageSize(page_size));
		
		
		String sta_begin_date="";
		String sta_end_date="";
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf1.parse(statics_month);
        Calendar cat = Calendar.getInstance();
        cat.setTime(date);
        cat.set(Calendar.DAY_OF_MONTH, 1);
        // 获取当月的第一天
        sta_begin_date = sdf2.format(cat.getTime());
        cat.add(Calendar.MONTH, 1);
        // 获取当前时间的下一天
        sta_end_date = sdf2.format(cat.getTime());
        para.put("sta_begin_date", sta_begin_date);
        para.put("sta_end_date", sta_end_date);
        
        
		//查询结果
		List<Map<String,Object>> resList=new ArrayList<Map<String,Object>>();
		//判断查询月份是否是当前时间
		Calendar ca = Calendar.getInstance();
		String tempca=sdf.format(ca.getTime());
		//查询时间是当月,则需要统计即时数据
		if(tempca.equals(statics_month))
		{
			ca = Calendar.getInstance();
			ca.add(Calendar.DAY_OF_MONTH, 1);
			sta_end_date = sdf2.format(cat.getTime());
			para.put("sta_end_date", sta_end_date);
			
			long stl=System.currentTimeMillis();
			System.out.println("25接口即时计算开始时间："+System.currentTimeMillis());
			resList=wmsProviderForAppDao.getAchInveSta_curr(para);
			System.out.println("25接口即时计算结束时间："+System.currentTimeMillis()+";用时:"+((System.currentTimeMillis()-stl)/1000)+"秒");
			
		}
		//不是当月，从已统计信息中获取
		else
		{
			resList=wmsProviderForAppDao.getAchInveSta(para);
		}
		//处理返回值
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("ret_code", RET_CODE_SUCC);
		res.put("ret_msg", RET_MSG_SUCC);
		res.put("ret_data",resList);
		
		return res;
	}

	/**
	 * @Title: getAch_vice_sta_v197
	 * @Description: 副总统计，接口29
	 * @param wmsProviderForAppVO
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2017年1月5日 下午2:30:55
	 * history:
	 * 1、2017年1月5日 WangShuai 创建方法
	*/
	@Override
	public Map<String, Object> getAch_vice_sta_v197(
			WmsProviderForAppVO wmsProviderForAppVO) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		//取参数
		Map<String,Object> para=new HashMap<String,Object>();
		String statics_month=wmsProviderForAppVO.getStatics_month();
		//目前要查全公司，所以即使传参数也不处理，需要处理用 find_in_set 取部门来处理//临时查询人员ID
		String sel_pid=wmsProviderForAppVO.getSel_pid();
		String vice_personnel_id = wmsProviderForAppVO.getVice_personnel_id();
		String vice_team_id = wmsProviderForAppVO.getVice_team_id();
		if(StringUtils.isEmpty(vice_personnel_id)){
			//处理返回值
			Map<String,Object> res=new HashMap<String,Object>();
			res.put("ret_code", RET_CODE_SUCC);
			res.put("ret_msg", RET_MSG_SUCC);
			res.put("ret_data","");
			
			return res;
		}
		if("0".equals(vice_personnel_id)){
			vice_personnel_id="";
		}
		if("0".equals(vice_team_id)){
			vice_team_id="";
		}
		int page=Integer.parseInt(StringUtils.isEmpty(wmsProviderForAppVO.getPage())?"0":wmsProviderForAppVO.getPage());
		int page_size=Integer.parseInt(StringUtils.isEmpty(wmsProviderForAppVO.getPage_size())?"0":wmsProviderForAppVO.getPage_size());
		para.put("statics_month", statics_month);
		para.put("vice_personnel_id", vice_personnel_id);
		para.put("vice_team_id", vice_team_id);
		para.put("general_manager_id", GENERAL_MANAGER_ID);
		para.put("statics_personnel_id", sel_pid);
		para.put("statics_type", AC_STA_TP_VE);
		para.put("page", checkPage(page));
		para.put("page_size", checkPageSize(page_size));
		//查询结果
		List<Map<String,Object>> resList=new ArrayList<Map<String,Object>>();
		//判断查询月份是否是当前时间
		Calendar ca = Calendar.getInstance();
		String tempca=sdf.format(ca.getTime());
		//查询时间是当月,则需要统计即时数据
		if(tempca.equals(statics_month))
		{
			long stl=System.currentTimeMillis();
			System.out.println("29接口即时计算开始时间："+System.currentTimeMillis());
			// 进行操作的参数
			String sta_begin_date="";
			String sta_end_date="";
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf1.parse(statics_month);
			Calendar cat = Calendar.getInstance();
			cat.setTime(date);
			cat.set(Calendar.DAY_OF_MONTH, 1);
			// 获取当月的第一天
			sta_begin_date = sdf2.format(cat.getTime());
			ca = Calendar.getInstance();
			ca.add(Calendar.DAY_OF_MONTH, 1);
			// 获取当前时间的下一天
			sta_end_date = sdf2.format(ca.getTime());
				
			
            para.put("new_pros", getNewPros());
			para.put("limit_date", LIMIT_DATE);
			para.put("reject_proid", REJECT_PROID);
			para.put("specialpro", specialpro);
			para.put("sta_begin_date", sta_begin_date);
			para.put("sta_end_date", sta_end_date);
			//进行临时计算，将数据放入指定的表
			//step1 删除之前的统计
			wmsProviderForAppDao.deleteAchPersonnelStaCurr(para);
			//1中心总统计存量
			wmsProviderForAppDao.insertAchCerterManStockCurr(para);
			//2副总统计存量
			wmsProviderForAppDao.insertAchViceManStockCurr(para);
//			//3总统计存量
//			wmsProviderForAppDao.insertAchGelManStockCurr(para);
			
			//1中心总统计新增
			wmsProviderForAppDao.updateAchCerterManAddCurr(para);
			//2副总统计新增
			wmsProviderForAppDao.updateAchViceManAddCurr(para);
			//3统计中心总的本金再投
			wmsProviderForAppDao.updateAchCenterTeamReinveMon(para);
			//统计副总的本金再投
			wmsProviderForAppDao.updateAchViceTeamReinveMon(para);
			
//			//3总统计新增
//			wmsProviderForAppDao.updateAchGelManAddCurr(para);
			
			
			resList=wmsProviderForAppDao.getAchViceStaCurr(para);
			System.out.println("29接口即时计算结束时间："+System.currentTimeMillis()+";用时:"+((System.currentTimeMillis()-stl)/1000)+"秒");
		}
		//不是当月，从已统计信息中获取
		else
		{
			resList=wmsProviderForAppDao.getAchViceSta(para);
		}
		//处理返回值
		Map<String,Object> res=new HashMap<String,Object>();
		res.put("ret_code", RET_CODE_SUCC);
		res.put("ret_msg", RET_MSG_SUCC);
		res.put("ret_data",resList);
		
		return res;
	}

}
