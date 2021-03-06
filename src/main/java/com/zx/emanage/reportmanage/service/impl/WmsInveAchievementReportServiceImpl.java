package com.zx.emanage.reportmanage.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zx.emanage.reportmanage.persist.WmsInveAchievementReportDao;
import com.zx.emanage.reportmanage.service.IWmsInveAchievementReportService;
import com.zx.emanage.reportmanage.vo.WmsInveAchievementReportVO;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.PoiExportUtil;
import com.zx.sframe.util.PoiUtilDetailTitle;
import com.zx.sframe.util.vo.UserBean;

/**
 * @ClassName: WmsInveAchievementReportServiceImpl
 * @Description: 报表处理类
 * @author WangShuai
 * @date 2016年12月2日
 * @version V1.0
 * history:
 * 1、2016年12月2日 WangShuai 创建文件
 * 2.工具方法  getSpecialVal
 *
 */
@Service("wmsInveAchievementReportService")
public class WmsInveAchievementReportServiceImpl implements IWmsInveAchievementReportService{
    // 新旧产品截止时间
    public static String LIMIT_DATE = "2016-04-01";

    // 不参与计算的产品ID
    public static String REJECT_PROID = "34,44";
    //新增规则ID
    public static int RULEMODULE_ADD=1;
    // 特殊产品
    private static String specialpro = "25,32,33";

    private int GENERAL_MANAGER_ID = 184;
    private static String op_per_status="0,1,2,4,5,6";//在职员工状态
	private static String all_per_status="0,1,2,4,5,6,7";//在职+兼职员工状态

    @Autowired
    WmsInveAchievementReportDao wmsInveAchievementReportDao;


    /**
     * getWmsinveAchievementAllColumns
     * 获取表头
     * @param wmsInveAchievementReportVO
     * @return
     * @throws ParseException 
     * Map<String,Object>
     * @exception 
     * @since  1.0.0
     */
    @Override
    public Map<String, Object> getWmsinveAchievementAllColumns(WmsInveAchievementReportVO wmsInveAchievementReportVO) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String start_date = wmsInveAchievementReportVO.getStart_date();
        String end_date = wmsInveAchievementReportVO.getEnd_date();
        /*获取当前月份*/
        Calendar c_t = Calendar.getInstance();
        c_t.setTime(sdf.parse(end_date));
        //当前月份
        int curr_mon=c_t.get(Calendar.MONTH)+1;
        
        //获取end_date 所在的季度的上一个季度（中文）
        String last_sea=getLastSeasonCName(end_date);
        //获取当前季度
        String curr_sea=getCurrSeasonCName(end_date);
        //获取本季度的开始月份（已经处理好）
        int currsea_mon=getCurrSeasonStMon(end_date);
        //获取上季度的开始月份（已经处理好）
        int lastsea_mon=getLastSeasonStMon(end_date);
        		
        Map<String, Object> res = new HashMap<String, Object>();
        StringBuilder columns_gr = new StringBuilder();
        StringBuilder columns_td = new StringBuilder();
        // 个人拼接
        columns_gr.append("[{display: '一级部门',name: 'company_name',width: 180,minWidth: 50,isSort:false},");
        columns_gr.append("{display: '二级部门',name: 'dept_name',width: 180,minWidth: 50,isSort:false},");
        columns_gr.append("{display: '员工姓名与短工号',name: 'personnel_name_detail',width: 180,minWidth: 50,isSort:false},");
        columns_gr.append("{display: '人员状态',name: 'personnel_state',width: 180,minWidth: 50,isSort:false},");
        // 团队拼接
        columns_td.append("[{display: '一级部门',name: 'company_name',width: 180,minWidth: 50,isSort:false},");
        columns_td.append("{display: '二级部门',name: 'dept_name',width: 180,minWidth: 50,isSort:false},");
        columns_td.append("{display: '员工姓名与短工号',name: 'personnel_name_detail',width: 180,minWidth: 50,isSort:false},");
        columns_td.append("{display: '职务',name: 'post_name',width: 180,minWidth: 50,isSort:false},");
        columns_td.append("{display: '人员状态',name: 'personnel_state',width: 180,minWidth: 50,isSort:false},");
        //如果开始事件部位空，并且开始时间不等于结束时间
        if(StringUtils.isBlank(start_date))
        {
        	start_date=end_date;
        }
        
        Date sd = sdf.parse(start_date);
        Calendar sdca = Calendar.getInstance();
            
       //默认循环，最多找一年的数据
        for (int i = 0; i < 12; i++)
        {
        	sdca.setTime(sd);
        	sdca.add(Calendar.MONTH, i);
        	
            String month = String.valueOf(sdca.get(Calendar.MONTH)+1);
            // 个人拼接
            columns_gr.append("{ display: '" + month + "月新增业绩(元)'," 
            					+ " columns:["
            					+ "{display: '签单金额',name: '" + month + "_per_add_base',width: 180,minWidth: 50,isSort:false}," 
            					+ "{display: '客户‘坑’金额（全部）',name: '" + month + "_per_back_base',width: 180,minWidth: 50,isSort:false},"
            					+ "{display: '客户‘坑’金额（新产品）',name: '" + month + "_per_back_base_newpro',width: 150,minWidth: 50,isSort:false},"
            					+ "{display: '雷六金额（其他月）',name: '" + month + "_per_special_add',width: 180,minWidth: 50,isSort:false},"
            					+ "{display: '合计-非年化',name: '" + month + "_per_sum_add',width: 180,minWidth: 50,isSort:false},"
            					+ "{display: '合计-年化（全部产品）',name: '" + month + "_per_sum_add_deal',width: 180,minWidth: 50,isSort:false},"
            					+ "{display: '合计-年化（新产品）',name: '" + month + "_per_sum_newpro_add_deal',width: 180,minWidth: 50,isSort:false}]},"
            					+ "{display: '" + month + "月赎回业绩（元）',name: '" + month + "_per_redeem_base',width: 180,minWidth: 50,isSort:false},"
            					+ "{display: '" + month + "月净增业绩（元）',name: '" + month + "_per_clear_add',width: 180,minWidth: 50,isSort:false},"
            					+ "{display: '" + month + "月本金再投金额（元）',name: '" + month + "_reinve_mon',width: 180,minWidth: 50,isSort:false},"
            					);
            // 团队拼接
            columns_td.append("{ display: '" + month + "月'," + " "
            					+ "columns:["
            					+ "{display: '部门新增（元）',name: '" + month + "_team_add_base',width: 180,minWidth: 50,isSort:false}," 
            					+ "{display: '部门赎回（元）',name: '" + month + "_team_redeem_all_base',width: 180,minWidth: 50,isSort:false},"
            					+ "{display: '部门新增-不含续期（元）（在职+兼职）',name: '" + month + "_team_exc_add_base',width: 250,minWidth: 50,isSort:false},"
            					+ "{display: '部门赎回-不含续期（元）（在职+兼职）',name: '" + month + "_team_exc_redeem_all_base',width: 250,minWidth: 50,isSort:false},"
            					+ "{display: '部门净增（元）（在职+兼职）',name: '" + month + "_team_clear_add',width: 250,minWidth: 50,isSort:false},"
            					+ "{display: '部门本金续投金额（元）',name: '" + month + "_team_reinve_mon',width: 250,minWidth: 50,isSort:false},"
            					+ "{display: '本月开资人员存量（元）',name: '" + month + "_team_gsalary_stock_all',width: 250,minWidth: 50,isSort:false},"
            					+ "{display: '上月开资人员存量（元）',name: '" + month + "_lastmonth_team_gsalary_stock_all',width: 250,minWidth: 50,isSort:false},"
            					+ "{display: '开资人员净增业绩（元）',name: '" + month + "_team_month_clear_stock',width: 180,minWidth: 50,isSort:false},"
            					+ "{display: '开资人数（人）',name: '" + month + "_team_staff_num',width: 180,minWidth: 50,isSort:false},"
            					+ "{display: '人均净增（元/月）',name: '" + month + "_team_per_increase',width: 180,minWidth: 50,isSort:false}]},"
            				);
           
            
            //如果时间到达结束时间
            String mod_sta = sdf.format(sdca.getTime());
            //当处理后的时间与结束事件相同的时候
            if (mod_sta.equals(end_date))
            {
                break;
            }
        }

        columns_gr.append("{ display: '"+last_sea+"新增业绩(元)'," 
        					+ " columns:[" 
        					+ "{display:'"+lastsea_mon+"月-年化',name:'sm0_per_add_deal',width: 180,minWidth: 50,isSort:false}," 
        					+ "{display:'"+(lastsea_mon+1)+"月-年化',name:'sm1_per_add_deal',width: 180,minWidth: 50,isSort:false},"  
        					+ "{display:'"+(lastsea_mon+2)+"月-年化',name:'sm2_per_add_deal',width: 180,minWidth: 50,isSort:false},"
        					+ "{display:'季度合计',name:'pre_sea_all',width: 180,minWidth: 50,isSort:false}"  
        					+ "]},");
        
        columns_gr.append("{ display: '"+curr_sea+"新增业绩(元)'," 
				+ " columns:[" 
				+ "{display:'"+currsea_mon+"月-年化',name:'sm3_per_add_deal',width: 180,minWidth: 50,isSort:false}," 
				+ "{display:'"+(currsea_mon+1)+"月-年化',name:'sm4_per_add_deal',width: 180,minWidth: 50,isSort:false},"  
				+ "{display:'"+(currsea_mon+2)+"月-年化',name:'sm5_per_add_deal',width: 180,minWidth: 50,isSort:false},"
				+ "{display:'季度合计',name:'cur_sea_all',width: 180,minWidth: 50,isSort:false}"  
				+ "]},");
        
        columns_gr.append("{ display: '截止"+curr_mon+"月底个人存量（元）'," 
        					+ " columns:[" 
        					+ "{display:'全部-非年化',name:'per_stock_all',width: 180,minWidth: 50,isSort:false}," 
        					+ "{display:'等级排名',name:'per_stock_lev',width: 180,minWidth: 50,isSort:false}," 
        					+ "{display:'全部-年化',name:'per_stock_all_deal',width: 180,minWidth: 50,isSort:false}," 
        					+ "{display:'新产品-非年化',name:'per_stock_new',width: 180,minWidth: 50,isSort:false}," 
        					+ "{display:'新产品-年化',name:'per_stock_new_deal',width: 180,minWidth: 50,isSort:false}]}]");

        
        // 团队拼接
        columns_td.append("{ display: '"+curr_sea+"'," + " "
        					+ "columns:[" 
        					+ "{display:'部门净增（元）',name:'team_sea_clear_add',width: 180,minWidth: 50,isSort:false}," 
        					+ "{display:'部门人数（人）',name:'team_sea_staff_num',width: 180,minWidth: 50,isSort:false}," 
        					+ "{display:'人均净增（元/季）',name:'team_sea_per_increase',width: 180,minWidth: 50,isSort:false}," 
        					+ "{display:'转正员工（人）',name:'team_sea_staff_num_regular',width: 180,minWidth: 50,isSort:false}]},");
        
        columns_td.append("{ display: '截止"+curr_mon+"月底部门存量（元）',"
							+ "columns:[" 
							+ "{display:'全部-非年化',name:'team_stock_all',width: 180,minWidth: 50,isSort:false}," 
							+ "{display:'全部-年化',name:'team_stock_all_deal',width: 180,minWidth: 50,isSort:false}," 
							+ "{display:'新产品-非年化',name:'team_stock_new',width: 180,minWidth: 50,isSort:false}," 
							+ "{display:'新产品-年化',name:'team_stock_new_deal',width: 180,minWidth: 50,isSort:false}]}]");
        
        res.put("columns_gr", columns_gr);
        res.put("columns_td", columns_td);
        return res;
    }

    /**
     * @Title: getLastSeasonStMon
     * @Description: 获取上月所在季度的开始月份
     * @param end_date
     * @return 
     * @author: WangShuai
     * @throws ParseException 
     * @time:2016年12月13日 下午7:56:26
     * history:
     * 1、2016年12月13日 WangShuai 创建方法
    */
    private int getLastSeasonStMon(String end_date) throws ParseException {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        Date sd = sdf.parse(end_date);
        c.setTime(sd);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        //第一季度
        if (currentMonth >= 1 && currentMonth <= 3)
        {
        	c.set(Calendar.MONTH, 0);
        }
        //第二季度
        else if (currentMonth >= 4 && currentMonth <= 6)
        {
        	c.set(Calendar.MONTH, 3);
        }
        //第三季度
        else if (currentMonth >= 7 && currentMonth <= 9)
        {
        	c.set(Calendar.MONTH, 6);
        }
        //第四季度
        else if (currentMonth >= 10 && currentMonth <= 12)
        {
        	c.set(Calendar.MONTH, 9);
        }
        
        c.add(Calendar.MONTH, -3);
        int mon =c.get(Calendar.MONTH);
        return mon+1;
	}

	/**
	 * @Title: getCurrSeasonStMon
	 * @Description: 获取本月所在季度的开始月份
	 * @param end_date
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2016年12月13日 下午7:56:07
	 * history:
	 * 1、2016年12月13日 WangShuai 创建方法
	*/
	private int getCurrSeasonStMon(String end_date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        Date sd = sdf.parse(end_date);
        c.setTime(sd);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        //第一季度
        if (currentMonth >= 1 && currentMonth <= 3)
        {
        	c.set(Calendar.MONTH, 0);
        }
        //第二季度
        else if (currentMonth >= 4 && currentMonth <= 6)
        {
        	c.set(Calendar.MONTH, 3);
        }
        //第三季度
        else if (currentMonth >= 7 && currentMonth <= 9)
        {
        	c.set(Calendar.MONTH, 6);
        }
        //第四季度
        else if (currentMonth >= 10 && currentMonth <= 12)
        {
        	c.set(Calendar.MONTH, 9);
        }
        
        int mon =c.get(Calendar.MONTH);
        return mon+1;
	}

	/**
     * @Title: getCurrSeasonCName
     * @Description: 获取当前季度的中文名称
     * @param end_date
     * @return 
     * @author: WangShuai
     * @throws ParseException 
     * @time:2016年12月13日 下午7:17:05
     * history:
     * 1、2016年12月13日 WangShuai 创建方法
    */
    private String getCurrSeasonCName(String end_date) throws ParseException {
    	String seaNum="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		Date sd = sdf.parse(end_date);
		c.setTime(sd);
		int year=c.get(Calendar.YEAR);
		seaNum=""+year;
		int currentMonth = c.get(Calendar.MONTH) + 1;
		//第一季度
		if (currentMonth >= 1 && currentMonth <= 3)
		{
			seaNum=seaNum+"年第一季度";
		}
		//第二季度
		else if (currentMonth >= 4 && currentMonth <= 6)
		{
			seaNum=seaNum+"年第二季度";
		}
		//第三季度
		else if (currentMonth >= 7 && currentMonth <= 9)
		{
			seaNum=seaNum+"年第三季度";
		}
		//第四季度
		else if (currentMonth >= 10 && currentMonth <= 12)
		{
			seaNum=seaNum+"年第四季度";
		}
		return seaNum;
	}

	/**
	 * @Title: getLastSeasonCName
	 * @Description: 获取上季度的中文名称
	 * @param end_date
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2016年12月13日 下午7:26:14
	 * history:
	 * 1、2016年12月13日 WangShuai 创建方法
	*/
	private String getLastSeasonCName(String end_date) throws ParseException {
		String seaNum="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		Date sd = sdf.parse(end_date);
		c.setTime(sd);
		c.add(Calendar.MONTH, -3);
		int year=c.get(Calendar.YEAR);
		seaNum=""+year;
		int currentMonth = c.get(Calendar.MONTH) + 1;
		//第一季度
		if (currentMonth >= 1 && currentMonth <= 3)
		{
			seaNum=seaNum+"年第一季度";
		}
		//第二季度
		else if (currentMonth >= 4 && currentMonth <= 6)
		{
			seaNum=seaNum+"年第二季度";
		}
		//第三季度
		else if (currentMonth >= 7 && currentMonth <= 9)
		{
			seaNum=seaNum+"年第三季度";
		}
		//第四季度
		else if (currentMonth >= 10 && currentMonth <= 12)
		{
			seaNum=seaNum+"年第四季度";
		}
		return seaNum;
	}

	/**
     * getAchievementPerStatisticswithpaginglist
     * 胡哦去个人统计信息
     * @param wmsInveAchievementReportVO
     * @return 
     * Map<String,Object>
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     */
    @Override
    public Map<String, Object> getAchievementPerStatisticswithpaginglist(WmsInveAchievementReportVO wmsInveAchievementReportVO, UserBean user) throws ParseException
    {
        /**处理参数
         * */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String start_date = wmsInveAchievementReportVO.getStart_date();
        String end_date = wmsInveAchievementReportVO.getEnd_date();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //判断处理dept_name
        if (StringUtil.isNotEmpty(wmsInveAchievementReportVO.getDept_name()))
        {
            // 获得页面输入部门名称。因有可能用中文逗号隔开，需要转换成英文逗号
            // 可能连续存在两个逗号,需要都替换成一个逗号
            String deptName = wmsInveAchievementReportVO.getDept_name().replaceAll("，", ",").replaceAll("[',']+", ",");
            @SuppressWarnings("unchecked")
			List<String> deptNameList = CollectionUtils.arrayToList(deptName.split(","));
            paramMap.put("deptNameList", deptNameList);
        }

        
        paramMap.put("personnel_info", wmsInveAchievementReportVO.getPersonnel_info());
        paramMap.put("personnel_state", wmsInveAchievementReportVO.getPersonnel_state());
        paramMap.put("per_sum_add_deal_low", wmsInveAchievementReportVO.getPer_sum_add_deal_low());
        paramMap.put("per_sum_add_deal_up", wmsInveAchievementReportVO.getPer_sum_add_deal_up());
        paramMap.put("per_clear_add_low", wmsInveAchievementReportVO.getPer_clear_add_low());
        paramMap.put("per_clear_add_up", wmsInveAchievementReportVO.getPer_clear_add_up());
        paramMap.put("per_stock_all_low", wmsInveAchievementReportVO.getPer_stock_all_low());
        paramMap.put("per_stock_all_up", wmsInveAchievementReportVO.getPer_stock_all_up());
        paramMap.put("per_stock_new_deal_low", wmsInveAchievementReportVO.getPer_stock_new_deal_low());
        paramMap.put("per_stock_new_deal_up", wmsInveAchievementReportVO.getPer_stock_new_deal_up());
        
        paramMap.put("sortname", wmsInveAchievementReportVO.getSortname());
        paramMap.put("sortorder", wmsInveAchievementReportVO.getSortorder());
        paramMap.put("pagesize", wmsInveAchievementReportVO.getPagesize());
        paramMap.put("offset", wmsInveAchievementReportVO.getOffset());
        paramMap.put("start_date", wmsInveAchievementReportVO.getStart_date());
        paramMap.put("end_date", wmsInveAchievementReportVO.getEnd_date());
        paramMap.put("statics_personnel_id", user.getUserId());
        
        Calendar c = Calendar.getInstance();
        String sta_month = sdf.format(c.getTime());// 获取当前月份
        
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        /**处理基础数据
         * */
        /*判断是否为当前月*/
        boolean currmonth_flag=false;
        
        if (sta_month.equals(end_date))
        {
        	currmonth_flag=true;
        	//进行当前月数据获取
        	list= wmsInveAchievementReportDao.searchBaseAchievementPerList_currmonth(paramMap); 
        }else{
        	list= wmsInveAchievementReportDao.searchBaseAchievementPerList(paramMap); 
        }
        
        
        List<Map<String,Object>> finallist=new ArrayList<Map<String,Object>>();
        
        //判断当前月份对应的上一个季度的起始月份，以及本季度的结束月份
        String season_stmon=getSeasonStMon(end_date);//上季度开始月份
        String season_edmon=getSeasonEdMon(end_date);//本季度结束月份
        
        //如果List不为空
        if(null!=list&&list.size()>0)
        {
        	//循环List
            for (Map<String, Object> baseMap : list)
            {
                // 如果开始月不存在，那么设置开始月为结束月
            	if(StringUtils.isBlank(start_date))
            	{
            		start_date=end_date;
            	}
                
            	Date sd = sdf.parse(start_date);
                Calendar sdca = Calendar.getInstance();
                //默认最多循环一年
                for (int i = 0; i < 12; i++)
                {// 这里要求查询范围不能超过12个月
                    sdca.setTime(sd);
                    sdca.add(Calendar.MONTH, i);
                    String month = String.valueOf(sdca.get(Calendar.MONTH)+1);
                    // 进行拼接的月份，为开始的月份向下去找，直到最后一个月
                    String mon_sel = sdf.format(sdca.getTime());
                    //本月
                    if(mon_sel.equals(end_date)){
                    	baseMap.put(month + "_per_add_base", getSpecialVal(baseMap,"per_add_base"));
                        baseMap.put(month + "_per_back_base", getSpecialVal(baseMap,"per_back_base"));
                        baseMap.put(month + "_per_back_base_newpro", getSpecialVal(baseMap,"per_back_base_newpro"));
                        baseMap.put(month + "_per_special_add", getSpecialVal(baseMap,"per_special_add"));
                        baseMap.put(month + "_per_sum_add", getSpecialVal(baseMap,"per_sum_add"));
                        baseMap.put(month + "_per_sum_add_deal", getSpecialVal(baseMap,"per_sum_add_deal"));
                        baseMap.put(month + "_per_sum_newpro_add_deal", getSpecialVal(baseMap,"per_sum_newpro_add_deal"));
                        baseMap.put(month + "_per_redeem_base", getSpecialVal(baseMap,"per_redeem_base"));
                        baseMap.put(month + "_per_clear_add", getSpecialVal(baseMap,"per_clear_add"));
                        baseMap.put(month + "_reinve_mon", getSpecialVal(baseMap,"reinve_mon"));
                        break;
                    }
                    //非本月
                    else
                    {
                    	//如果到达查询最后的月份
                        baseMap.put("mon_sel", mon_sel);
                        Map<String, Object> mid_res = wmsInveAchievementReportDao.getSpecialMonthPersonnelMap(baseMap);
                        //如果结果不为空
                        baseMap.put(month + "_per_add_base", getSpecialVal(mid_res,"per_add_base"));
                        baseMap.put(month + "_per_back_base", getSpecialVal(mid_res,"per_back_base"));
                        baseMap.put(month + "_per_back_base_newpro", getSpecialVal(mid_res,"per_back_base_newpro"));
                        baseMap.put(month + "_per_special_add", getSpecialVal(mid_res,"per_special_add"));
                        baseMap.put(month + "_per_sum_add", getSpecialVal(mid_res,"per_sum_add"));
                        baseMap.put(month + "_per_sum_add_deal", getSpecialVal(mid_res,"per_sum_add_deal"));
                        baseMap.put(month + "_per_sum_newpro_add_deal", getSpecialVal(mid_res,"per_sum_newpro_add_deal"));
                        baseMap.put(month + "_per_redeem_base", getSpecialVal(mid_res,"per_redeem_base"));
                        baseMap.put(month + "_per_clear_add", getSpecialVal(mid_res,"per_clear_add"));
                        baseMap.put(month + "_reinve_mon", getSpecialVal(mid_res,"reinve_mon"));
                    }
                    
                }
                
                /**
                 * 处理季度，区分上一个季度和本季度，因为要处理各个季度的汇总问题
                 * */
                //上一个季度新增汇总
                BigDecimal pre_sea_all=new BigDecimal("0");
                //本季度新增汇总
                BigDecimal cur_sea_all=new BigDecimal("0");
                // 从上一个季度的其实月份开始循环一直循环到本季度的结束月份--处理季度问题
                for (int i = 0; i < 6; i++)
                {
                    Date sd_sea = sdf.parse(season_stmon);
                    Calendar sdca_sea = Calendar.getInstance();
                    sdca_sea.setTime(sd_sea);
                    sdca_sea.add(Calendar.MONTH, i);
                    Calendar enca = Calendar.getInstance();
                    enca.setTime(sdf.parse(season_edmon));
                    
                    // 进行拼接的月份，为开始的月份向下去找，直到最后一个月
                    String mon_sel = sdf.format(sdca_sea.getTime());
                    baseMap.put("mon_sel", mon_sel);
                    //判断 如果是当月 使用当月的值进行赋值
                    Map<String, Object> mid_res=new HashMap<String,Object>();
                    if (mon_sel.equals(end_date))
                    {
                    	mid_res.put("per_add_deal", baseMap.get("per_sum_add_deal"));
                    }else{
                    	mid_res = wmsInveAchievementReportDao.getSpecialMonthPersonnelMap(baseMap);
                    }
                    
                    //如果结果不为空
                    
                    baseMap.put("sm" + String.valueOf(i) + "_per_add_deal", getSpecialVal(mid_res,"per_add_deal"));
                    
                    //当I小于3的时候是前一个季度的
                    if(i<3)
                    {
                    	//将前一个季度的值累加
                    	pre_sea_all=pre_sea_all.add(new BigDecimal(String.valueOf(baseMap.get("sm" + String.valueOf(i) + "_per_add_deal"))));
                    }
                    //否则为当前季度的
                    else
                    {
                    	//将本季度的值累加
                    	cur_sea_all=cur_sea_all.add(new BigDecimal(String.valueOf(baseMap.get("sm" + String.valueOf(i) + "_per_add_deal"))));
                    }
                    
                }
                baseMap.put("pre_sea_all", pre_sea_all.setScale(0, BigDecimal.ROUND_DOWN));
                baseMap.put("cur_sea_all", cur_sea_all.setScale(0, BigDecimal.ROUND_DOWN));
                finallist.add(baseMap);
                }
            }
        /**分页问题
         * */
        GridDataBean<Map<String,Object>> bean ;
        //是否本月，是本月
        if(currmonth_flag)
        {
        	bean = new GridDataBean<Map<String,Object>>(wmsInveAchievementReportVO.getPage(), wmsInveAchievementReportDao.findPerCount_currmonth(paramMap),finallist);
        }
        //非本月
        else
        {
        	bean = new GridDataBean<Map<String,Object>>(wmsInveAchievementReportVO.getPage(), wmsInveAchievementReportDao.findPerCount(paramMap),finallist);
        }
        
        return bean.getGridData();
    }

    /**
     * @Title: getSpecialVal
     * @Description: 获取Map下指定字段的值
     * @param mid_res
     * @param fieldname
     * @return 
     * @author: WangShuai
     * @time:2016年12月13日 下午9:00:34
     * history:
     * 1、2016年12月13日 WangShuai 创建方法
    */
    private String getSpecialVal(Map<String, Object> mid_res,String fieldname){
    	String val="";
    	//如果Map是空则返回0
    	if(null==mid_res)
    	{
    		val="0";
    	}
    	//如果Map 中字段是空 返回0
    	else if(null==mid_res.get(fieldname))
    	{
    		val="0";
    	}
    	//返回Map中字段的值
    	else
    	{
    		val=String.valueOf(mid_res.get(fieldname));
    	}
    	return val;
    }

    /**
     * @Title: getCurSeasonStMon
     * @Description: 获取本季度的开始时间
     * @param end_date
     * @return 
     * @author: WangShuai
     * @throws ParseException 
     * @time:2016年12月31日 上午11:21:39
     * history:
     * 1、2016年12月31日 WangShuai 创建方法
    */
    @SuppressWarnings("unused")
	private String getCurSeasonStMon(String end_date) throws ParseException {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        Date sd = sdf.parse(end_date);
        c.setTime(sd);

        int currentMonth = c.get(Calendar.MONTH) + 1;
        //第一季度
        if (currentMonth >= 1 && currentMonth <= 3) 
        {
            c.set(Calendar.MONTH, 2);
        } 
        //第二季度
        else if (currentMonth >= 4 && currentMonth <= 6) 
        {
            c.set(Calendar.MONTH, 5);
        } 
        //第三季度
        else if (currentMonth >= 7 && currentMonth <= 9) 
        {
            c.set(Calendar.MONTH, 8);
        } 
        //第四季度
        else if (currentMonth >= 10 && currentMonth <= 12) 
        {
            c.set(Calendar.MONTH, 11);
        }
        c.add(Calendar.MONTH, -3);
        String mod_sta=sdf.format(c.getTime());
        return mod_sta;
	}

	/**
	 * @Title: getPreSeasonEdMon
	 * @Description: 取上季度结束月份
	 * @param end_date
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2016年12月31日 上午11:20:03
	 * history:
	 * 1、2016年12月31日 WangShuai 创建方法
	*/
	@SuppressWarnings("unused")
	private String getPreSeasonEdMon(String end_date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        Date sd = sdf.parse(end_date);
        c.setTime(sd);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        //第一季度
        if (currentMonth >= 1 && currentMonth <= 3)
        {
        	c.set(Calendar.MONTH, 0);
        }
        //第二季度
        else if (currentMonth >= 4 && currentMonth <= 6)
        {
        	c.set(Calendar.MONTH, 3);
        }
        //第三季度
        else if (currentMonth >= 7 && currentMonth <= 9)
        {
        	c.set(Calendar.MONTH, 6);
        }
        //第四季度
        else if (currentMonth >= 10 && currentMonth <= 12)
        {
        	c.set(Calendar.MONTH, 9);
        }
        
        String mod_sta=sdf.format(c.getTime());
        return mod_sta;
	}

	/**
     * getAchievementTeamStatisticswithpaginglist
     * 获取团队统计信息
     * @param wmsInveAchievementReportVO
     * @return 
     * Map<String,Object>
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     */
    @Override
    public Map<String, Object> getAchievementTeamStatisticswithpaginglist(WmsInveAchievementReportVO wmsInveAchievementReportVO, UserBean user) throws ParseException
    {
        /**处理参数
         * */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String start_date = wmsInveAchievementReportVO.getStart_date();
        String end_date = wmsInveAchievementReportVO.getEnd_date();
        String last_statics_month=getLastStaticsMonth(end_date);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        //判断处理dept_name
        if (StringUtil.isNotEmpty(wmsInveAchievementReportVO.getDept_name()))
        {
            // 获得页面输入部门名称。因有可能用中文逗号隔开，需要转换成英文逗号
            // 可能连续存在两个逗号,需要都替换成一个逗号
            String deptName = wmsInveAchievementReportVO.getDept_name().replaceAll("，", ",").replaceAll("[',']+", ",");
            @SuppressWarnings("unchecked")
			List<String> deptNameList = CollectionUtils.arrayToList(deptName.split(","));
            paramMap.put("deptNameList", deptNameList);
        }
        paramMap.put("personnel_info", wmsInveAchievementReportVO.getPersonnel_info());
        paramMap.put("personnel_state", wmsInveAchievementReportVO.getPersonnel_state());
        paramMap.put("team_clear_add_low", wmsInveAchievementReportVO.getTeam_clear_add_low());
        paramMap.put("team_clear_add_up", wmsInveAchievementReportVO.getTeam_clear_add_up());
        paramMap.put("team_stock_all_low", wmsInveAchievementReportVO.getTeam_stock_all_low());
        paramMap.put("team_stock_all_up", wmsInveAchievementReportVO.getTeam_stock_all_up());
        
        paramMap.put("sortname", wmsInveAchievementReportVO.getSortname());
        paramMap.put("sortorder", wmsInveAchievementReportVO.getSortorder());
        paramMap.put("pagesize", wmsInveAchievementReportVO.getPagesize());
        paramMap.put("offset", wmsInveAchievementReportVO.getOffset());
        paramMap.put("start_date", wmsInveAchievementReportVO.getStart_date());
        paramMap.put("end_date", wmsInveAchievementReportVO.getEnd_date());
        paramMap.put("statics_personnel_id", user.getUserId());
        paramMap.put("last_statics_month", last_statics_month);
        
        
        //获取结束月所在的上一个季度的季度编号
        //将end_date 减3个月就一定是上个季度的月份，根据对应的月份获取季度编号
        Calendar c = Calendar.getInstance();
        Date sdda = sdf.parse(end_date);
        c.setTime(sdda);
        String ltm=sdf.format(c.getTime());
        // 获取统计月份所在的季度
        String last_seaNum=getSeasonNum(ltm);
        
        paramMap.put("statics_season",last_seaNum);
        
        
        Calendar ctp = Calendar.getInstance();
        String sta_month = sdf.format(ctp.getTime());// 获取当前月份
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        /**处理基础数据
         * */
        /*判断是否为当前月*/
        boolean currmonth_flag=false;
        
        if (sta_month.equals(end_date))
        {
        	currmonth_flag=true;
        	//进行当前月数据获取
        	list = wmsInveAchievementReportDao.searchBaseAchievementTeamList_currmonth(paramMap); 
        }else{
        	list = wmsInveAchievementReportDao.searchBaseAchievementTeamList(paramMap); 
        }
        /**处理多月份
         * */
        List<Map<String,Object>> middlelist=new ArrayList<Map<String,Object>>();
        if(null!=list&&list.size()>0){
            for (Map<String, Object> baseMap : list)
            {
            	// 如果开始月不存在，那么设置开始月为结束月
            	if(StringUtils.isBlank(start_date))
            	{
            		start_date=end_date;
            	}
                
            	Date sd = sdf.parse(start_date);
                Calendar sdca = Calendar.getInstance();
                
                for (int i = 0; i < 12; i++)
                {// 这里要求查询范围不能超过12个月
                    
                    sdca.setTime(sd);
                    sdca.add(Calendar.MONTH, i);
                    String month = String.valueOf(sdca.get(Calendar.MONTH)+1);
                    // 进行拼接的月份，为开始的月份向下去找，直到最后一个月
                    String mon_sel = sdf.format(sdca.getTime());
                  //如果与结束月份相等则结束
                    if (mon_sel.equals(end_date))
                    {
                    	baseMap.put(month + "_team_add_base",getSpecialVal(baseMap,"team_add_base"));
                        baseMap.put(month + "_team_redeem_all_base",getSpecialVal(baseMap,"team_redeem_all_base"));
                        baseMap.put(month + "_team_exc_add_base",getSpecialVal(baseMap,"team_exc_add_base"));
                        baseMap.put(month + "_team_exc_redeem_all_base",getSpecialVal(baseMap,"team_exc_redeem_all_base"));
                        baseMap.put(month + "_team_clear_add",getSpecialVal(baseMap,"team_clear_add"));
                        baseMap.put(month + "_team_reinve_mon",getSpecialVal(baseMap,"team_reinve_mon"));
                        baseMap.put(month + "_team_gsalary_stock_all",getSpecialVal(baseMap,"team_gsalary_stock_all"));
                        baseMap.put(month + "_lastmonth_team_gsalary_stock_all",getSpecialVal(baseMap,"lastmonth_team_gsalary_stock_all"));
                        baseMap.put(month + "_team_month_clear_stock",getSpecialVal(baseMap,"team_month_clear_stock"));
                        baseMap.put(month + "_team_staff_num",getSpecialVal(baseMap,"team_staff_num"));
                        baseMap.put(month + "_team_per_increase",getSpecialVal(baseMap,"team_per_increase"));
                        baseMap.put(month + "_team_sea_clear_add",getSpecialVal(baseMap,"team_sea_clear_add"));
                        baseMap.put(month + "_team_sea_staff_num",getSpecialVal(baseMap,"team_sea_staff_num"));
                        baseMap.put(month + "_team_sea_per_increase",getSpecialVal(baseMap,"team_sea_per_increase"));
                        baseMap.put(month + "_team_sea_staff_num_regular",getSpecialVal(baseMap,"team_sea_staff_num_regular"));
                    	break;
                    }
                    else
                    {
                    	baseMap.put("mon_sel", mon_sel);
                        Map<String, Object> mid_res = wmsInveAchievementReportDao.getSpecialMonthTeamMap(baseMap);
                        
                        baseMap.put(month + "_team_add_base",getSpecialVal(mid_res,"team_add_base"));
                        baseMap.put(month + "_team_redeem_all_base",getSpecialVal(mid_res,"team_redeem_all_base"));
                        baseMap.put(month + "_team_exc_add_base",getSpecialVal(mid_res,"team_exc_add_base"));
                        baseMap.put(month + "_team_exc_redeem_all_base",getSpecialVal(mid_res,"team_exc_redeem_all_base"));
                        baseMap.put(month + "_team_clear_add",getSpecialVal(mid_res,"team_clear_add"));
                        baseMap.put(month + "_team_reinve_mon",getSpecialVal(mid_res,"team_reinve_mon"));
                        baseMap.put(month + "_team_gsalary_stock_all",getSpecialVal(mid_res,"team_gsalary_stock_all"));
                        baseMap.put(month + "_lastmonth_team_gsalary_stock_all",getSpecialVal(mid_res,"lastmonth_team_gsalary_stock_all"));
                        baseMap.put(month + "_team_month_clear_stock",getSpecialVal(mid_res,"team_month_clear_stock"));
                        baseMap.put(month + "_team_staff_num",getSpecialVal(mid_res,"team_staff_num"));
                        baseMap.put(month + "_team_per_increase",getSpecialVal(mid_res,"team_per_increase"));
                        baseMap.put(month + "_team_sea_clear_add",getSpecialVal(mid_res,"team_sea_clear_add"));
                        baseMap.put(month + "_team_sea_staff_num",getSpecialVal(mid_res,"team_sea_staff_num"));
                        baseMap.put(month + "_team_sea_per_increase",getSpecialVal(mid_res,"team_sea_per_increase"));
                        baseMap.put(month + "_team_sea_staff_num_regular",getSpecialVal(mid_res,"team_sea_staff_num_regular"));
                    }
                }
                
                middlelist.add(baseMap);
            }
        }
        /**分页问题
         * */
        GridDataBean<Map<String,Object>> bean ;
        //是否本月，是本月
        if(currmonth_flag)
        {
        	bean = new GridDataBean<Map<String,Object>>(wmsInveAchievementReportVO.getPage(), wmsInveAchievementReportDao.findteamCount_currmonth(paramMap),middlelist);
        }
        //非本月
        else
        {
        	bean = new 
                    GridDataBean<Map<String,Object>>(wmsInveAchievementReportVO.getPage(), wmsInveAchievementReportDao.findteamCount(paramMap),middlelist);
        }
        return bean.getGridData();
    }


    
    /**
     * getSeasonStMon
     * 获取上一个季度的开始时间
     * @param end_date
     * @return
     * @throws ParseException 
     * String
     * @exception 
     * @since  1.0.0
     */
    private String getSeasonStMon(String end_date) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        Date sd = sdf.parse(end_date);
        c.setTime(sd);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        //第一季度
        if (currentMonth >= 1 && currentMonth <= 3)
        {
        	c.set(Calendar.MONTH, 0);
        }
        //第二季度
        else if (currentMonth >= 4 && currentMonth <= 6)
        {
        	c.set(Calendar.MONTH, 3);
        }
        //第三季度
        else if (currentMonth >= 7 && currentMonth <= 9)
        {
        	c.set(Calendar.MONTH, 6);
        }
        //第四季度
        else if (currentMonth >= 10 && currentMonth <= 12)
        {
        	c.set(Calendar.MONTH, 9);
        }
        
        c.add(Calendar.MONTH, -3);
        String mod_sta=sdf.format(c.getTime());
        return mod_sta;
    }



    /**
     * getSeasonEdMon
     * 获取本季度的技术时间
     * @param end_date
     * @return
     * @throws ParseException 
     * String
     * @exception 
     * @since  1.0.0
     */
    private String getSeasonEdMon(String end_date) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        Date sd = sdf.parse(end_date);
        c.setTime(sd);

        int currentMonth = c.get(Calendar.MONTH) + 1;
        //第一季度
        if (currentMonth >= 1 && currentMonth <= 3) 
        {
            c.set(Calendar.MONTH, 2);
        } 
        //第二季度
        else if (currentMonth >= 4 && currentMonth <= 6) 
        {
            c.set(Calendar.MONTH, 5);
        } 
        //第三季度
        else if (currentMonth >= 7 && currentMonth <= 9) 
        {
            c.set(Calendar.MONTH, 8);
        } 
        //第四季度
        else if (currentMonth >= 10 && currentMonth <= 12) 
        {
            c.set(Calendar.MONTH, 11);
        }
        String mod_sta=sdf.format(c.getTime());
        return mod_sta;
    }

    /**
     * doAchievementStaticticsExportPoi
     * 进行报表导出
     * @param request
     * @param response
     * @param wmsInveAchievementReportVO 
     * 
     * 
     * 是一个Map 的list 内容有
         * name ： sheet的名称
         * titleType ：sheet中title的类型，值有 3（3级表头），2（2级表头）,他同时也对应表头的级别
         * titleString ：sheet中title的串（没处理1级，有功夫再写，1级的也简单）
         * 3级表头字符串如下（使用 ,  :  _  =   @ 来进行分级处理，用List太麻烦，用数组没办法处理，只能这么做啦）
         * 例如：  TA:TA1=TA11@TA12_TA2,TB
         * 2级表头字符串如下（使用 ,  :  _ 来进行分级处理）例如： TA:TA1_TA2,TB
         * 
         * datalist ： 对应的数据，要求数据中不会出现null，或者少值，因为这样会出现串列的问题  List<List<Object>>
     * void
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     */
    @Override
    public Map<String,Object> doAchievementStaticticsExportPoi(HttpServletRequest request, HttpServletResponse response, WmsInveAchievementReportVO wmsInveAchievementReportVO, UserBean user) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStamp = sdf.format(new Date());
        String filename = "业绩统计报表_" + timeStamp + ".xls";
        List<Map<String, Object>> itsheets = new ArrayList<Map<String, Object>>();
        Map<String, Object> perMap = new HashMap<String, Object>();
        Map<String, Object> teamMap = new HashMap<String, Object>();
        // 处理个人相关数据
        perMap.put("name", "个人业绩");
        perMap.put("titleType", 2);
        perMap = getPersonnelAchievementTitle(perMap, wmsInveAchievementReportVO);
        perMap = getPersonnelAchievementDatalist(perMap, wmsInveAchievementReportVO,user);
        itsheets.add(perMap);
        // 处理团队相关数据
        teamMap.put("name", "团队业绩");
        teamMap.put("titleType", 2);
        teamMap = getTeamAchievementTitle(teamMap, wmsInveAchievementReportVO);
        teamMap = getTeamAchievementDatalist(teamMap, wmsInveAchievementReportVO,user);
        itsheets.add(teamMap);
        // 进行两个sheet的处理与导出
        PoiUtilDetailTitle poiUtilDetailTitle = new PoiUtilDetailTitle();
        poiUtilDetailTitle.createAndExportWbToFile(filename, itsheets);
        
        
        Map<String,Object> res=new HashMap<String,Object>();
        res.put("out_file_name", filename);
       
        return res;

    }

    private Map<String, Object> getTeamAchievementDatalist(Map<String, Object> teamMap, WmsInveAchievementReportVO wmsInveAchievementReportVO, UserBean user) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        String start_date = wmsInveAchievementReportVO.getStart_date();
        String end_date = wmsInveAchievementReportVO.getEnd_date();
        
        String last_statics_month=getLastStaticsMonth(end_date);
        
        Map<String, Object> para = new HashMap<String, Object>();
        //判断处理dept_name
        if (StringUtil.isNotEmpty(wmsInveAchievementReportVO.getDept_name()))
        {
            // 获得页面输入部门名称。因有可能用中文逗号隔开，需要转换成英文逗号
            // 可能连续存在两个逗号,需要都替换成一个逗号
            String deptName = wmsInveAchievementReportVO.getDept_name().replaceAll("，", ",").replaceAll("[',']+", ",");
            @SuppressWarnings("unchecked")
			List<String> deptNameList = CollectionUtils.arrayToList(deptName.split(","));
            para.put("deptNameList", deptNameList);
        }
        para.put("personnel_info", wmsInveAchievementReportVO.getPersonnel_info());
        para.put("personnel_state", wmsInveAchievementReportVO.getPersonnel_state());
        para.put("team_clear_add_low", wmsInveAchievementReportVO.getTeam_clear_add_low());
        para.put("team_clear_add_up", wmsInveAchievementReportVO.getTeam_clear_add_up());
        para.put("team_stock_all_low", wmsInveAchievementReportVO.getTeam_stock_all_low());
        para.put("team_stock_all_up", wmsInveAchievementReportVO.getTeam_stock_all_up());
        
        para.put("start_date", start_date);
        para.put("end_date", end_date);
        para.put("sortname", "");
        
        para.put("statics_personnel_id", user.getUserId());
        para.put("last_statics_month", last_statics_month);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        	
        // 获取结束月所在的上一个季度的季度编号
        // 将end_date 减3个月就一定是上个季度的月份，根据对应的月份获取季度编号
        Calendar c1 = Calendar.getInstance();
        Date sdda = sdf.parse(end_date);
        c1.setTime(sdda);
        String ltm = sdf.format(c1.getTime());
        // 获取统计月份所在的季度
        String cur_seaNum = getSeasonNum(ltm);

        para.put("statics_season", cur_seaNum);
        
        Calendar c = Calendar.getInstance();
        String sta_month = sdf.format(c.getTime());// 获取当前月份
        /**处理基础数据
         * */
        /*判断是否为当前月*/
        if (sta_month.equals(end_date))
        {
        	list=wmsInveAchievementReportDao.searchBaseAchievementTeamList_currmonth(para);
        }
        else{
        	list =wmsInveAchievementReportDao.searchBaseAchievementTeamList(para);
        }
        

        List<List<Object>> formatList = new ArrayList<List<Object>>();
        /**处理多月份
         * */
        if (null != list && list.size() > 0)
        {
            for (Map<String, Object> baseMap : list)
            {
                List<Object> res = new ArrayList<Object>();
                res.add(baseMap.get("company_name"));
                res.add(baseMap.get("dept_name"));
                res.add(baseMap.get("personnel_name_detail"));
                res.add(baseMap.get("personnel_state"));

                // 如果开始月不存在，那么设置开始月为结束月
            	if(StringUtils.isBlank(start_date))
            	{
            		start_date=end_date;
            	}
                
            	Date sd = sdf.parse(start_date);
                Calendar sdca = Calendar.getInstance();
                //默认最多循环一年
                for (int i = 0; i < 12; i++)
                {// 这里要求查询范围不能超过12个月
                    sdca.setTime(sd);
                    sdca.add(Calendar.MONTH, i);
                    String mon_sel=sdf.format(sdca.getTime());
                    //如果到达结束月，则跳出
                    if (mon_sel.equals(end_date))
                    {
                    	res.add(getSpecialVal(baseMap,"team_add_base"));
	                    res.add(getSpecialVal(baseMap,"team_redeem_all_base"));
	                    res.add(getSpecialVal(baseMap,"team_exc_add_base"));
	                    res.add(getSpecialVal(baseMap,"team_exc_redeem_all_base"));
	                    res.add(getSpecialVal(baseMap,"team_clear_add"));
	                    res.add(getSpecialVal(baseMap,"team_reinve_mon"));
	                    
	                    res.add(getSpecialVal(baseMap,"team_gsalary_stock_all"));
	                    res.add(getSpecialVal(baseMap,"lastmonth_team_gsalary_stock_all"));
	                    res.add(getSpecialVal(baseMap,"team_month_clear_stock"));
	                    
	                    res.add(getSpecialVal(baseMap,"team_staff_num"));
	                    res.add(getSpecialVal(baseMap,"team_per_increase"));
                            break;
                    }
                    else
                    {
                    	baseMap.put("mon_sel", mon_sel);
                        Map<String, Object> mid_res = wmsInveAchievementReportDao.getSpecialMonthTeamMap(baseMap);
                        res.add(getSpecialVal(mid_res,"team_add_base"));
                        res.add(getSpecialVal(mid_res,"team_redeem_all_base"));
                        res.add(getSpecialVal(mid_res,"team_exc_add_base"));
                        res.add(getSpecialVal(mid_res,"team_exc_redeem_all_base"));
                        res.add(getSpecialVal(mid_res,"team_clear_add"));
                        res.add(getSpecialVal(mid_res,"team_reinve_mon"));
	                    res.add(getSpecialVal(mid_res,"team_gsalary_stock_all"));
	                    res.add(getSpecialVal(mid_res,"lastmonth_team_gsalary_stock_all"));
	                    res.add(getSpecialVal(mid_res,"team_month_clear_stock"));
                        res.add(getSpecialVal(mid_res,"team_staff_num"));
                        res.add(getSpecialVal(mid_res,"team_per_increase"));
                    }
                }
                //季度数据
                res.add(baseMap.get("team_sea_clear_add"));
                res.add(baseMap.get("team_sea_staff_num"));
                res.add(baseMap.get("team_sea_per_increase"));
                res.add(baseMap.get("team_sea_staff_num_regular"));
                //存量数据
                res.add(baseMap.get("team_stock_all"));
                res.add(baseMap.get("team_stock_all_deal"));
                res.add(baseMap.get("team_stock_new"));
                res.add(baseMap.get("team_stock_new_deal"));
                
                formatList.add(res);
            }
        }
        teamMap.put("datalist", formatList);
        return teamMap;
    }

    private Map<String, Object> getTeamAchievementTitle(Map<String, Object> teamMap, WmsInveAchievementReportVO wmsInveAchievementReportVO) throws ParseException
    {

    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String export_start_month = wmsInveAchievementReportVO.getStart_date();
        String export_end_month = wmsInveAchievementReportVO.getEnd_date();
        //获取end_date 所在的季度的上一个季度（中文）
        String cur_sea = getCurrSeasonCName(export_end_month);
        /*获取当前月份*/
        Calendar c_t = Calendar.getInstance();
        c_t.setTime(sdf.parse(export_end_month));
        //当前月份
        int curr_mon=c_t.get(Calendar.MONTH)+1;
        
        StringBuilder titleString = new StringBuilder();
        titleString.append("一级部门,二级部门,员工姓名及短工号,人员状态,");

      //如果开始事件部位空，并且开始时间不等于结束时间
        if(StringUtils.isBlank(export_start_month))
        {
        	export_start_month=export_end_month;
        }
        
        Date sd = sdf.parse(export_start_month);
        Calendar sdca = Calendar.getInstance();
      //默认循环，最多找一年的数据
        for (int i = 0; i < 12; i++)
        {
        	sdca.setTime(sd);
        	sdca.add(Calendar.MONTH, i);
        	String mon_sel=sdf.format(sdca.getTime());
            String month = String.valueOf(sdca.get(Calendar.MONTH)+1);
            titleString.append(month + "月:部门新增（元）_部门赎回（元）_部门新增-不含续期（元）（在职+兼职）"
            		+ "_部门赎回-不含续期（元）（在职+兼职）_部门净增（元）（在职+兼职）_部门本金续投金额（元）_本月开资人员存量（元）"
            		+ "_上月开资人员存量（元）_开资人员净增业绩（元）_开资人数（人）_人均净增（元/月）,");
            //如果计算月等于结束月则结束
            if (mon_sel.equals(export_end_month))
            {
                break;
            }
               
        }
        titleString.append(cur_sea + ":部门净增（元）_部门人数（人）_人均净增（元/季）_转正员工（人）,");
    	titleString.append("截止"+curr_mon+"月底部门存量（元）:全部-非年化_全部-年化_新产品-非年化_新产品-年化");

        teamMap.put("titleString", titleString.toString());
        return teamMap;
    }


    /**
     * getPersonnelAchievementDatalist
     * 插入数据
     * @param perMap
     * @param wmsInveAchievementReportVO
     * @param user 
     * @return 
     * Map<String,Object>
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     */
    private Map<String, Object> getPersonnelAchievementDatalist(Map<String, Object> perMap, WmsInveAchievementReportVO wmsInveAchievementReportVO, UserBean user) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        String start_date = wmsInveAchievementReportVO.getStart_date();
        String end_date = wmsInveAchievementReportVO.getEnd_date();
        Map<String, Object> para = new HashMap<String, Object>();
        //判断处理dept_name
        if (StringUtil.isNotEmpty(wmsInveAchievementReportVO.getDept_name()))
        {
            // 获得页面输入部门名称。因有可能用中文逗号隔开，需要转换成英文逗号
            // 可能连续存在两个逗号,需要都替换成一个逗号
            String deptName = wmsInveAchievementReportVO.getDept_name().replaceAll("，", ",").replaceAll("[',']+", ",");
            @SuppressWarnings("unchecked")
			List<String> deptNameList = CollectionUtils.arrayToList(deptName.split(","));
            para.put("deptNameList", deptNameList);
        }
        para.put("personnel_info", wmsInveAchievementReportVO.getPersonnel_info());
        para.put("personnel_state", wmsInveAchievementReportVO.getPersonnel_state());
        para.put("per_sum_add_deal_low", wmsInveAchievementReportVO.getPer_sum_add_deal_low());
        para.put("per_sum_add_deal_up", wmsInveAchievementReportVO.getPer_sum_add_deal_up());
        para.put("per_clear_add_low", wmsInveAchievementReportVO.getPer_clear_add_low());
        para.put("per_clear_add_up", wmsInveAchievementReportVO.getPer_clear_add_up());
        para.put("per_stock_all_low", wmsInveAchievementReportVO.getPer_stock_all_low());
        para.put("per_stock_all_up", wmsInveAchievementReportVO.getPer_stock_all_up());
        para.put("per_stock_new_deal_low", wmsInveAchievementReportVO.getPer_stock_new_deal_low());
        para.put("per_stock_new_deal_up", wmsInveAchievementReportVO.getPer_stock_new_deal_up());
        para.put("start_date", start_date);
        para.put("end_date", end_date);
        para.put("sortname", "");
        
        para.put("statics_personnel_id", user.getUserId());
        
        
        
        Calendar c = Calendar.getInstance();
        String sta_month = sdf.format(c.getTime());// 获取当前月份
        
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        /**处理基础数据
         * */
        /*判断是否为当前月*/
        if (sta_month.equals(end_date))
        {
        	//进行当前月数据获取
        	list= wmsInveAchievementReportDao.searchBaseAchievementPerList_currmonth(para); 
        }else{
        	list= wmsInveAchievementReportDao.searchBaseAchievementPerList(para); 
        }
        
        List<List<Object>> formatList=new ArrayList<List<Object>>();
        //判断当前月份对应的上一个季度的起始月份，以及本季度的结束月份
        String season_stmon=getSeasonStMon(end_date);
        String season_edmon=getSeasonEdMon(end_date);
        if(null!=list&&list.size()>0){
            for (Map<String, Object> baseMap : list)
            {
                List<Object> res = new ArrayList<Object>();
                res.add(baseMap.get("company_name"));
                res.add(baseMap.get("dept_name"));
                res.add(baseMap.get("personnel_name_detail"));
                res.add(baseMap.get("personnel_state"));
             // 如果开始月不存在，那么设置开始月为结束月
            	if(StringUtils.isBlank(start_date))
            	{
            		start_date=end_date;
            	}
                
            	Date sd = sdf.parse(start_date);
                Calendar sdca = Calendar.getInstance();
                //默认最多循环一年
                for (int i = 0; i < 12; i++)
                {// 这里要求查询范围不能超过12个月
                    sdca.setTime(sd);
                    sdca.add(Calendar.MONTH, i);
                    String mon_sel=sdf.format(sdca.getTime());
                    //是本月
	                if (mon_sel.equals(end_date))
	                {
	                	res.add(getSpecialVal(baseMap,"per_add_base"));
	                    res.add(getSpecialVal(baseMap,"per_back_base"));
	                    res.add(getSpecialVal(baseMap,"per_back_base_newpro"));
	                    res.add(getSpecialVal(baseMap,"per_special_add"));
	                    res.add(getSpecialVal(baseMap,"per_sum_add"));
	                    res.add(getSpecialVal(baseMap,"per_sum_add_deal"));
	                    res.add(getSpecialVal(baseMap,"per_sum_newpro_add_deal"));
	                    res.add(getSpecialVal(baseMap,"per_redeem_base"));
	                    res.add(getSpecialVal(baseMap,"per_clear_add"));
	                    res.add(getSpecialVal(baseMap,"reinve_mon"));
	                    
	                    break;
	                }
	                //不是本月
	                else
	                {
	                	baseMap.put("mon_sel", mon_sel);
	                    
	                    Map<String, Object> mid_res = wmsInveAchievementReportDao.getSpecialMonthPersonnelMap(baseMap);
	                        
	                    res.add(getSpecialVal(mid_res,"per_add_base"));
	                    res.add(getSpecialVal(mid_res,"per_back_base"));
	                    res.add(getSpecialVal(mid_res,"per_back_base_newpro"));
	                    res.add(getSpecialVal(mid_res,"per_special_add"));
	                    res.add(getSpecialVal(mid_res,"per_sum_add"));
	                    res.add(getSpecialVal(mid_res,"per_sum_add_deal"));
	                    res.add(getSpecialVal(mid_res,"per_sum_newpro_add_deal"));
	                    res.add(getSpecialVal(mid_res,"per_redeem_base"));
	                    res.add(getSpecialVal(mid_res,"per_clear_add"));
	                    res.add(getSpecialVal(mid_res,"reinve_mon"));
	                }
                }
                
                /**
                 * 处理季度
                 * */
                // 从上一个季度的其实月份开始循环一直循环到本季度的结束月份--处理季度问题，
                //上一个季度新增汇总
                BigDecimal pre_sea_all=new BigDecimal("0");
                //本季度新增汇总
                BigDecimal cur_sea_all=new BigDecimal("0");
                for (int i = 0; i < 6; i++)
                {
                	//上一个季度的汇总已经结束，这里在res中插入汇总值
                	if(i==3)
                	{
                		res.add(pre_sea_all);
                	}
                	
                	
                    Date sd2 = sdf.parse(season_stmon);
                    Calendar sdca2 = Calendar.getInstance();
                    sdca2.setTime(sd2);
                    sdca2.add(Calendar.MONTH, i);
                    Calendar enca = Calendar.getInstance();
                    enca.setTime(sdf.parse(season_edmon));
                    // 进行拼接的月份，为开始的月份向下去找，直到最后一个月
                    String mon_sel = sdf.format(sdca2.getTime());
                    baseMap.put("mon_sel", mon_sel);
                    
                    Map<String, Object> mid_res=new HashMap<String,Object>();
                    if (mon_sel.equals(end_date))
                    {
                    	mid_res.put("per_add_deal", baseMap.get("per_sum_add_deal"));
                    }else{
                    	mid_res = wmsInveAchievementReportDao.getSpecialMonthPersonnelMap(baseMap);
                    }

                    res.add(getSpecialVal(mid_res,"per_add_deal"));
                    
                    //当I小于3的时候是前一个季度的
                    if(i<3){
                    	pre_sea_all=pre_sea_all.add(new BigDecimal(getSpecialVal(mid_res,"per_add_deal")));
                    }
                    //否则为当前季度的
                    else
                    {
                    	cur_sea_all=cur_sea_all.add(new BigDecimal(getSpecialVal(mid_res,"per_add_deal")));
                    }
                  //本季度的汇总已经结束，插入本季度的汇总值
                	if(i==5)
                	{
                		res.add(cur_sea_all);
                	}
                }
                res.add(getSpecialVal(baseMap,"per_stock_all"));
                res.add(getSpecialVal(baseMap,"per_stock_lev"));
                res.add(getSpecialVal(baseMap,"per_stock_all_deal"));
                res.add(getSpecialVal(baseMap,"per_stock_new"));
                res.add(getSpecialVal(baseMap,"per_stock_new_deal"));
                
                formatList.add(res);
                }
            }
        
        perMap.put("datalist", formatList);

        return perMap;
    }


    /**
     * getPersonnelAchievementTitle
     * 拼接个人列表的导出Title
     * @param perMap
     * @param wmsInveAchievementReportVO
     * @return 
     * Map<String,Object>
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     * 
     * 3级表头字符串如下（使用 ,  :  _  =   @ 来进行分级处理，用List太麻烦，用数组没办法处理，只能这么做啦）
     * 例如：  TA:TA1=TA11@TA12_TA2,TB
     * 2级表头字符串如下（使用 ,  :  _ 来进行分级处理）例如： TA:TA1_TA2,TB
     */
    private Map<String, Object> getPersonnelAchievementTitle(Map<String, Object> perMap, WmsInveAchievementReportVO wmsInveAchievementReportVO) throws ParseException
    {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String export_start_month = wmsInveAchievementReportVO.getStart_date();
        String export_end_month = wmsInveAchievementReportVO.getEnd_date();
        //获取end_date 所在的季度的上一个季度（中文）
        String last_sea=getLastSeasonCName(export_end_month);
        //获取当前季度
        String curr_sea=getCurrSeasonCName(export_end_month);
        //获取本季度的开始月份（已经处理好）
        int currsea_mon=getCurrSeasonStMon(export_end_month);
        //获取上季度的开始月份（已经处理好）
        int lastsea_mon=getLastSeasonStMon(export_end_month);
        /*获取当前月份*/
        Calendar c_t = Calendar.getInstance();
        c_t.setTime(sdf.parse(export_end_month));
        //当前月份
        int curr_mon=c_t.get(Calendar.MONTH)+1;
        
        StringBuilder titleString = new StringBuilder();
        titleString.append("一级部门,二级部门,员工姓名及短工号,人员状态,");

      //如果开始事件部位空，并且开始时间不等于结束时间
        if(StringUtils.isBlank(export_start_month))
        {
        	export_start_month=export_end_month;
        }
        
        Date sd = sdf.parse(export_start_month);
        Calendar sdca = Calendar.getInstance();
      //默认循环，最多找一年的数据
        for (int i = 0; i < 12; i++)
        {
        	sdca.setTime(sd);
        	sdca.add(Calendar.MONTH, i);
        	String mon_sel=sdf.format(sdca.getTime());
        	
            String month = String.valueOf(sdca.get(Calendar.MONTH)+1);   
            
            titleString.append(month + "月新增业绩(元):签单金额_客户‘坑’金额（全部）_客户‘坑’金额（新产品）_雷六金额（其他月）"
            		+ "_合计-非年化_合计-年化（全部产品）_合计-年化（新产品）_"+ month + "月赎回业绩（元）_"+ month + "月净增业绩（元）,"+ month + "月本金再投金额（元）,");
            //如果两个月份相同则跳出循环，结束
            if(mon_sel.equals(export_end_month))
            {
            	break;
            }
        }
        titleString.append(last_sea+"新增业绩(元):"+lastsea_mon+"月-年化_"+(lastsea_mon+1)+"月-年化_"+(lastsea_mon+2)+"月-年化_季度合计,");
        titleString.append(curr_sea+"新增业绩(元):"+currsea_mon+"月-年化_"+(currsea_mon+1)+"月-年化_"+(currsea_mon+2)+"月-年化_季度合计,");
        titleString.append("截止"+curr_mon+"月底个人存量（元）:全部-非年化_等级排名_全部-年化_新产品-非年化_新产品-年化");
        
        perMap.put("titleString", titleString.toString());
        return perMap;
    }


    /**
     * doachievementDayStaStaticticsExportPoi
     * 处理每日业绩报表导出
     * @param request
     * @param response
     * @param wmsInveAchievementReportVO 
     * void
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     */
    @Override
    public Map<String, Object> doachievementDayStaStaticticsExportPoi(HttpServletRequest request, HttpServletResponse response, WmsInveAchievementReportVO wmsInveAchievementReportVO,UserBean user) throws ParseException
    {
        String sel_begin_date = "";// 附表查询开始时间
        String sel_end_date = "";// 附表查询结束时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        boolean currMonFlag = true;// 判断是否是查询当月，true当月，false其他月份，为附录1和附录2的查询做判断，因为这两个数据查询sql相差太多，需单独处理，
        String export_date = wmsInveAchievementReportVO.getExport_end_month_day();
        /*判断查询的时间是否是当前月份，如果是则，查询每日备份，如果不是则查询每月的统计*/
        Map<String, Object> paramMap = new HashMap<String, Object>();// 查询参数
        paramMap.put("general_manager_id", GENERAL_MANAGER_ID);
        paramMap.put("reject_proid", REJECT_PROID);
        
        

        Calendar c = Calendar.getInstance();
        String sta_month = sdf.format(c.getTime());// 获取当前月份
        /*设置查询SQL的参数*/
        if (sta_month.equals(export_date))
        {// 是当前月份，则直接查询每日备份中的数据,
        	paramMap.put("statics_personnel_id", user.getUserId());
            paramMap.put("table_name", "wms_personnel_achievement_daysta"); // 每日备份表
            sel_end_date = sdf2.format(c.getTime());// 设置查询结束时间为当前
            c.set(Calendar.DAY_OF_MONTH, 1);
            sel_begin_date = sdf2.format(c.getTime());// 设置查询开始时间为当月第一天

            paramMap.put("export_month", export_date);// 查询月份
            paramMap.put("sel_begin_date", sel_begin_date);//
            paramMap.put("sel_end_date", sel_end_date);
        }
        else
        {// 如果不是折去查询每个月的统计数据
            currMonFlag = false;
            paramMap.put("table_name", "wms_personnel_achievement_his"); // 每月备份表
            paramMap.put("statics_month", export_date);// 查询月份
            Date seldate = sdf.parse(export_date);
            c.setTime(seldate);
            c.set(Calendar.DAY_OF_MONTH, 1);// 设置查询开始时间为查询月的1号
            sel_begin_date = sdf2.format(c.getTime());

            c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
            sel_end_date = sdf2.format(c.getTime());// 设置查询结束时间为查询月最后一天

            paramMap.put("sel_begin_date", sel_begin_date);//
            paramMap.put("sel_end_date", sel_end_date);
        }
        // 个人存量
        List<Map<String, Object>> per_stock_list = new ArrayList<Map<String, Object>>();

        // 如果是本月查询则进行一次日统计报表的计算
        if (currMonFlag)
        {
            // 与定时任务5完全相同
            doReportDaySta(sel_end_date,user.getUserId());
            per_stock_list = wmsInveAchievementReportDao.getDaystaPerStockList(paramMap);// 实时个人存量
        }
        else
        {
            per_stock_list = wmsInveAchievementReportDao.getHisPerStockList(paramMap);// 历史个人存量--ok
        }

        /*查询数据列表*/
        List<Map<String, Object>> per_add_list = wmsInveAchievementReportDao.getPerAddList(paramMap);// 个人新增--OK
        List<Map<String, Object>> team_add_list = wmsInveAchievementReportDao.getTeamAddList(paramMap);// 团队新增--OK
        List<Map<String, Object>> team_stock_list = wmsInveAchievementReportDao.getTeamStockList(paramMap);// 团队存量--OK
        List<Map<String, Object>> vice_add_list = wmsInveAchievementReportDao.getViceAddList(paramMap);// 副总新增
        List<Map<String, Object>> vice_stock_list = wmsInveAchievementReportDao.getViceStockList(paramMap);// 副总存量

        // 查询附录1和附录2
        List<Map<String, Object>> detail_inve_1 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> detail_inve_2 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> detail_inve_3 = new ArrayList<Map<String, Object>>();
        if (currMonFlag)
        {// 当月，直接查询获取
            detail_inve_1 = wmsInveAchievementReportDao.getCurrMonthDetailInve1(paramMap);
            detail_inve_2 = wmsInveAchievementReportDao.getCurrMonthDetailInve2(paramMap);
            detail_inve_3 = wmsInveAchievementReportDao.getCurrMonthDetailInve3(paramMap);
        }
        else
        {// 非当月，通过backup表获取数据信息
            detail_inve_1 = wmsInveAchievementReportDao.getOtherMonthDetailInve1(paramMap);
            detail_inve_2 = wmsInveAchievementReportDao.getOtherMonthDetailInve2(paramMap);
            detail_inve_3 = wmsInveAchievementReportDao.getOtherMonthDetailInve3(paramMap);
        }
        // sheet 参数list
        Map<String, Object> listMap = new HashMap<String, Object>();

        listMap.put("statics_date", export_date);

        listMap.put("per_add_list", per_add_list);
        listMap.put("per_stock_list", per_stock_list);
        listMap.put("team_add_list", team_add_list);
        listMap.put("team_stock_list", team_stock_list);

        listMap.put("vice_add_list", vice_add_list);
        listMap.put("vice_stock_list", vice_stock_list);
        listMap.put("detail_inve_1", detail_inve_1);
        listMap.put("detail_inve_2", detail_inve_2);
        listMap.put("detail_inve_3", detail_inve_3);
        Map<String, Object> res = new HashMap<String, Object>();
        try
        {
            // 为导出文件加上时间戳
            Calendar ca = Calendar.getInstance();
            String timestamp = sdf3.format(ca.getTime());
            String out_file_name = export_date + "业绩报表_" + timestamp + ".xls";
            // 模板导出文件
            PoiExportUtil.doExportToFile("业绩报表模板.xls", listMap, out_file_name);
            res.put("out_file_name", out_file_name);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * @Title: doReportDaySta
     * @Description: 日报表统计，与定时任务5完全相同
     * @param sel_end_date 当前查询时间
     * @author: WangShuai
     * @throws ParseException 
     * @time:2016年11月22日 下午1:47:50
     * history:
     * 1、2016年11月22日 WangShuai 创建方法
    */
    private void doReportDaySta(String sel_end_date,int userid) throws ParseException
    {
        // 计算当月第一天
        String sta_begin_date = "";
        // 计算当天时间+1
        String sta_end_date = "";
        String statics_month_sp="";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        // 进行操作的参数
        Date date = sdf.parse(sel_end_date);

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        // 获取当月的第一天
        sta_begin_date = sdf2.format(ca.getTime());
        statics_month_sp=sdf.format(ca.getTime());

        ca = Calendar.getInstance();
        ca.add(Calendar.DAY_OF_MONTH, 1);
        // 获取当前时间的下一天
        sta_end_date = sdf2.format(ca.getTime());
        //获取新产品
        Map<String,String> res_tep=new HashMap<String,String>();
		res_tep=wmsInveAchievementReportDao.getNewProListByRule(RULEMODULE_ADD);
		String new_pros=res_tep.get("wms_inve_pruduct_category_ids");
        // 进行参数处理
        Map<String, Object> para = new HashMap<String, Object>();

        para.put("sta_begin_date", sta_begin_date);
        para.put("sta_end_date", sta_end_date);
        para.put("statics_date", sta_end_date);
        para.put("limit_date", LIMIT_DATE);
        para.put("reject_proid", REJECT_PROID);
        para.put("specialpro", specialpro);
        para.put("statics_personnel_id",userid);
        para.put("new_pros",new_pros);
        para.put("statics_month_sp",statics_month_sp);
        

        // 先删除当月可能已经计算的业绩信息
        wmsInveAchievementReportDao.deleteExistsData(para);
        // 根据人员表进行个人存量数据的插入
        wmsInveAchievementReportDao.insertPersonnelStockData_daySta(para);
        // 插入冯总的个人存量（归属公司的情况下）
        wmsInveAchievementReportDao.insertPersonnelStockData_daySta_com(para);
        // 进行人员的个人新增非折比和折比计算,以及净增
        // 净增要根据客户进行归集，查询时要注意只有属于没有上单的
        wmsInveAchievementReportDao.updatePerAllAddData_daySta(para);
        // 更新冯总净增，转的数据
        wmsInveAchievementReportDao.updatePerAllAddData_daySta_com(para);
        //处理雷锋6的非本月发放金额
        wmsInveAchievementReportDao.updateSpecialProPerSta_daySta(para);
      		//雷锋6公司
        wmsInveAchievementReportDao.updateSpecialProPerSta_daySta_com(para);
        
        
        // 进行部门经理的团队存量计算
        wmsInveAchievementReportDao.updateTeamDeptStockData_daySta(para);
        //处理部门经理ID为空的部门存量，插入数据
        wmsInveAchievementReportDao.insertTeamDeptStockData_noManagerId_daySta(para);
        
        // 进行副总的团队存量计算
        wmsInveAchievementReportDao.updateTeamViceStockData_daySta(para);
        // 进行中分总的团队存量计算
        wmsInveAchievementReportDao.updateTeamCenterStockData_daySta(para);
        // 进行总经理的团队存量计算
        wmsInveAchievementReportDao.updateTeamGelStockData_daySta(para);
        // 总经理，归属公司的处理
        wmsInveAchievementReportDao.updateTeamGelStockData_daySta_com(para);

        // 进行部门经理的当月新增，赎回，净增计算
        wmsInveAchievementReportDao.updateTeamDeptAddData_daySta(para);
        // 进行副总的当月新增，赎回，净增计算
        wmsInveAchievementReportDao.updateTeamViceAddData_daySta(para);
        // 进行中分总的当月新增，赎回，净增计算
        wmsInveAchievementReportDao.updateTeamCenterAddData_daySta(para);
        // 进行总的当月新增，赎回，净增计算
        wmsInveAchievementReportDao.updateTeamGelAddData_daySta(para);
        // 进行总的新增处理，归属于公司的
        wmsInveAchievementReportDao.updateTeamGelAddData_daySta_com(para);
        
        
        
      //不包含续期的，全部人员单据
      	para.put("per_status", all_per_status);
      	wmsInveAchievementReportDao.updateTeamDeptAddData_withoutRenInve_daySta(para);
      	//进行副总的当月新增，赎回，净增计算-OK
      	wmsInveAchievementReportDao.updateTeamViceAddData_withoutRenInve_daySta(para);
      	//进行中分总的当月新增，赎回，净增计算
      	wmsInveAchievementReportDao.updateTeamCenterAddData_withoutRenInve_daySta(para);
      	//进行总的当月新增，赎回，净增计算-OK
      	wmsInveAchievementReportDao.updateTeamGelAddData_withoutRenInve_daySta(para);
      	//冯总转的团队净增-OK
      	wmsInveAchievementReportDao.updateTeamGelAddData_com_withoutRenInve_daySta(para);
      	
      	//进行团队当月转正人数计算
      	wmsInveAchievementReportDao.updateTeamChangeStateNum_daySta(para);
      	
      	//计算个人的本金续投金额
      	wmsInveAchievementReportDao.updatePersonnelReinveMon(para);
      	//计算个人的本金续投金额
      	wmsInveAchievementReportDao.updatePersonnelReinveMon_com(para);
    	//计算团队的本金续投金额
      	wmsInveAchievementReportDao.updateTeamReinveMon(para);
    	//计算中分总的本金续投
      	wmsInveAchievementReportDao.updateCenterReinveMon(para);
    	
      	wmsInveAchievementReportDao.updateViceReinveMon(para);

    }

    /**
	 * @Title: getSeasonNum
	 * @Description: 获取季节编号
	 * @param statics_month
	 * @return 
	 * @author: WangShuai
	 * @time:2016年12月13日 下午4:16:39
	 * history:
	 * 1、2016年12月13日 WangShuai 创建方法
	*/
	
	private String getSeasonNum(String statics_month) throws ParseException {
		String seaNum="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		Date sd = sdf.parse(statics_month);
		c.setTime(sd);
		int year=c.get(Calendar.YEAR);
		seaNum=""+year;
		int currentMonth = c.get(Calendar.MONTH) + 1;
		//第一季度
		if (currentMonth >= 1 && currentMonth <= 3)
		{
			seaNum=seaNum+"0";
		}
		//第二季度
		else if (currentMonth >= 4 && currentMonth <= 6)
		{
			seaNum=seaNum+"1";
		}
		//第三季度
		else if (currentMonth >= 7 && currentMonth <= 9)
		{
			seaNum=seaNum+"2";
		}
		//第四季度
		else if (currentMonth >= 10 && currentMonth <= 12)
		{
			seaNum=seaNum+"3";
		}
		return seaNum;
	}
	
	/**
	 * @Title: getCheckDateToStatic
	 * @Description: 判断查询时间是否进行日报表统计
	 * @param wmsInveAchievementReportVO
	 * @param user
	 * @return 
	 * @author: WangShuai
	 * @throws ParseException 
	 * @time:2016年12月15日 下午5:22:16
	 * history:
	 * 1、2016年12月15日 WangShuai 创建方法
	*/
	@Override
	public Map<String, Object> getCheckDateToStatic(
			WmsInveAchievementReportVO wmsInveAchievementReportVO, UserBean user) throws ParseException {
		String end_date = wmsInveAchievementReportVO.getEnd_date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		
		Calendar c = Calendar.getInstance();
	    String sta_month = sdf.format(c.getTime());// 获取当前月份
	    /*判断是否为当前月*/
	    if (sta_month.equals(end_date))
	    {
	       //进行日报表数据统计
	        doReportDaySta(end_date,user.getUserId());
	    }
	    Map<String,Object> res=new HashMap<String,Object>();
	    res.put("result","success");
		return res;
	}
	
	
	/**
     * @Title: getLastStaticsMonth
     * @Description: 获取上一个统计月份
     * @param statics_month
     * @return
     * @throws ParseException 
     * @author: WangShuai
     * @time:2017年1月10日 下午5:09:49
     * history:
     * 1、2017年1月10日 WangShuai 创建方法
    */
    private String getLastStaticsMonth(String statics_month) throws ParseException {
		Calendar ca = Calendar.getInstance();
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM");
		Date a=sdf2.parse(statics_month);
		ca.setTime(a);
		ca.add(GregorianCalendar.MONTH,-1);
		return sdf2.format(new Date(ca.getTimeInMillis()));
	}
}
