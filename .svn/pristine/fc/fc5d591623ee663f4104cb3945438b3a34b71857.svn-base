package com.zx.emanage.inve.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.typeconverter.Convert;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveSalaryDao;
import com.zx.emanage.inve.service.IWmsInveSalaryService;
import com.zx.emanage.inve.vo.WmsInveSalarySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveSalary;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.PoiExportUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvesalaryService")
public class WmsInveSalaryServiceImpl implements IWmsInveSalaryService {
	private static Logger log = LoggerFactory.getLogger(WmsInveSalaryServiceImpl.class);

	@Autowired
	private WmsInveSalaryDao wmsinvesalaryDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveSalarySearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvesalaryDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveSalarySearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvesalaryDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvesalaryDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveSalary getInfoByPK(Integer wms_inve_salary_id) {
		return wmsinvesalaryDao.get(wms_inve_salary_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveSalary bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvesalaryDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveSalary bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvesalaryDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveSalary() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveSalary> getListByEntity(WmsInveSalary queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveSalary> beanList = wmsinvesalaryDao.getListByEntity(queryInfo);
		return beanList;
	}

	    /**
     * 
     * @Title: exportExcelWmsInveSalary
     * @Description: 导出工资报表
     * @param statics_month
     * @param request
     * @param response 
     * @author: Administrator
     * @time:2017年1月5日 下午1:19:00
     * @see com.zx.emanage.inve.service.IWmsInveSalaryService#exportExcelWmsInveSalary(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     * history:
     * 1、2017年1月5日 Administrator 创建方法
     */
	@Override
    public void exportExcelWmsInveSalary(String statics_month, HttpServletRequest request, HttpServletResponse response)
    {
		Map<String,Object> listMap = new HashMap<String,Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
        String dateName = format.format(Convert.toDate(statics_month));
		
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(format.parse(dateName));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        // 季度编号 xxxx0(1/2/3) 某年第几季度
        String statics_season = getSeasonNum(statics_month);
        Map<String, Object> pmap = new HashMap<String, Object>();

        pmap.put("statics_month", statics_month);
        pmap.put("statics_season", statics_season);

		String titleValue = "数据有效性:截止"+ calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) +1) + "-" + calendar.getActualMaximum(Calendar.DAY_OF_MONTH)  + "财富管理平台数据";
		
        List<Map<String, Object>> wmsInveSalaryInfoList = wmsinvesalaryDao.getWmsInveSalaryInfoByDate(statics_month);// 获取工资基本信息数据
        List<Map<String, Object>> salaryPersonnelPerformanceList = wmsinvesalaryDao.getSalaryPersonnelPerformanceByDate(statics_month);// 获取个人业绩
        List<Map<String, Object>> salaryDeptPerformanceList = wmsinvesalaryDao.getSalaryDeptPerformanceByDate(pmap);// 获取部门业绩

		listMap.put("salaryInfoListTitle", dateName + "_财富管理中心_员工工资");
		listMap.put("personnelPerformanceListTitle", titleValue);
		listMap.put("deptPerformanceListTitle", titleValue);

		listMap.put("wmsInveSalaryInfoList", wmsInveSalaryInfoList);
		listMap.put("salaryPersonnelPerformanceList", salaryPersonnelPerformanceList);
		listMap.put("salaryDeptPerformanceList", salaryDeptPerformanceList);
		
		try {
    		String out_file_name = dateName + "工资报表_平台版本.xls";
			PoiExportUtil.doexport("工资报表模板.xls",listMap,out_file_name,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

    /**
     * 
     * @Title: getSeasonNum
     * @Description: 获取本季度编号
     * @param statics_month
     * @return 
     * @author: zhangyunfei
     * @time:2017年1月5日 下午1:17:02
     * history:
     * 1、2017年1月5日 Administrator 创建方法
     */
    private String getSeasonNum(String statics_month)
    {
        String seaNum = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        Date sd;
        try
        {
            sd = sdf.parse(statics_month);
            c.setTime(sd);
            int year = c.get(Calendar.YEAR);
            seaNum = "" + year;
            // 计算工资月 static_month上一个月
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
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return seaNum;
    }

    /**
     * 
     * @Title: selectWmsInveSalary
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param user
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年5月10日 下午3:00:59
     * history:
     * 1、2017年5月10日 zhangmingjian 创建方法
     */
    @Override
    public GridDataBean<Map<String, Object>>  selectWmsInveSalary( WmsInveSalarySearchBeanVO bean)
    {
        if(StringUtils.isBlank(bean.getPersonnel_state())){
            bean.setPersonnel_state("-1");
        }
        bean.setPage(bean.getOffset());
        List<Map<String, Object>> list = wmsinvesalaryDao.selectWmsInveSalaryInfoByDate(bean);
        Integer count = wmsinvesalaryDao.countWmsInveSalaryInfoByDate(bean);
        GridDataBean<Map<String, Object>> resultList = new GridDataBean<Map<String, Object>>(bean.getPage(), count, list);

        return resultList;
    }

    
    /**
     * 
     * @Title: exportExcel
     * @Description: 列表导出
     * @param statics_month
     * @param request
     * @param response 
     * @author: zhangmingjian
     * @time:2017年5月10日 下午6:07:13
     * history:
     * 1、2017年5月10日 zhangmingjian 创建方法
     */
    @Override
    public void exportExcel(WmsInveSalarySearchBeanVO bean, HttpServletRequest request, HttpServletResponse response)
    {
        bean.setExportExcel_flag("1");
         String statics_month = bean.getStatics_month();
         
         
        Map<String, Object> listMap = new HashMap<String, Object>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
        String dateName = format.format(Convert.toDate(statics_month));

        Calendar calendar = Calendar.getInstance();
        try
        {
            calendar.setTime(format.parse(dateName));
        }
        catch (ParseException e1)
        {
            e1.printStackTrace();
        }
        // 季度编号 xxxx0(1/2/3) 某年第几季度
        String statics_season = getSeasonNum(statics_month);
        Map<String, Object> pmap = new HashMap<String, Object>();

        pmap.put("statics_month", statics_month);
        pmap.put("statics_season", statics_season);

        String titleValue = "数据有效性:截止" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + "财富管理平台数据";

        List<Map<String, Object>> wmsInveSalaryInfoList = wmsinvesalaryDao.selectWmsInveSalaryInfoByDate(bean);// 获取工资基本信息数据
        List<Map<String, Object>> salaryPersonnelPerformanceList = wmsinvesalaryDao.getSalaryPersonnelPerformanceByDate(statics_month);// 获取个人业绩
        List<Map<String, Object>> salaryDeptPerformanceList = wmsinvesalaryDao.getSalaryDeptPerformanceByDate(pmap);// 获取部门业绩

        listMap.put("salaryInfoListTitle", dateName + "_财富管理中心_员工工资");
        listMap.put("personnelPerformanceListTitle", titleValue);
        listMap.put("deptPerformanceListTitle", titleValue);

        listMap.put("wmsInveSalaryInfoList", wmsInveSalaryInfoList);
        listMap.put("salaryPersonnelPerformanceList", salaryPersonnelPerformanceList);
        listMap.put("salaryDeptPerformanceList", salaryDeptPerformanceList);

        try
        {
            String out_file_name = dateName + "工资报表_平台版本.xls";
            PoiExportUtil.doexport("工资报表模板1.xls", listMap, out_file_name, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
