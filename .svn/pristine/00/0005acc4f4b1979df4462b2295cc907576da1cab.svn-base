package com.zx.emanage.reportmanage.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.reportmanage.service.IWmsInveCustomerMonthlyIncomeService;
import com.zx.emanage.reportmanage.vo.WmsInveCustomerMonthlyIncomeBeanVo;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2015，卓信金控
 * 文件名称: WmsInveCustomerMonthlyIncomeController.java
 * 系统名称：WMS
 * 模块名称：理财客户每月收益报表
 * 完成日期：
 * 作    者：
 * 内容摘要：
 * 
 * 文件调用：
 * 修改记录：
 * 修改时间：
 * 修 改 人: 
 * 修改内容：
 * 关联BUG：
 * 修改方法：
 */
@Controller
public class WmsInveCustomerMonthlyIncomeController {
	  private static Logger log = LoggerFactory.getLogger(WmsInveCustomerMonthlyIncomeController.class);
	  	@Autowired
	   private IWmsInveCustomerMonthlyIncomeService wmsInveCustomerMonthlyIncomeService;

	    /**
		 * 获取报表需要的数据 每月收益--报表
		 * @return List
		 * @author baisong
		 */
	    @RequestMapping(value="/reportmanage/getInveCustomerMonthlyIncomeListInfo.do",method={RequestMethod.GET,RequestMethod.POST})
	    @ResponseBody
	    public Map<String,Object> getDataViewList(WmsInveCustomerMonthlyIncomeBeanVo queryInfo, HttpServletRequest request, HttpServletResponse response){
	    	HttpSession session = request.getSession();
	    	UserBean user =(UserBean)session.getAttribute(GlobalVal.USER_SESSION);
	        return wmsInveCustomerMonthlyIncomeService.getMapInfo(queryInfo,user);
	    }
	    /**
		 * 获取报表需要的数据 每月收益--报表导出
		 * @return List
		 * @author baisong
		*/
		@RequestMapping(value="/reportmanage/getMapInfoListWithoutPaging.do",method={RequestMethod.GET,RequestMethod.POST})
		@ResponseBody
		public Map<String,Object> getMapInfoListWithoutPaging(WmsInveCustomerMonthlyIncomeBeanVo queryInfo){
		     return wmsInveCustomerMonthlyIncomeService.getMapInfoListWithoutPaging(queryInfo);
		}
	    /**
		 * 获取报表计算数据总和
		 * @return List
		 * @author baisong
		*/
		@RequestMapping(value="/reportmanage/geToalCountInfo.do",method={RequestMethod.GET,RequestMethod.POST})
		@ResponseBody
	public Map<String, Object> geToalCountInfo(
			WmsInveCustomerMonthlyIncomeBeanVo queryInfo,
			HttpServletRequest request, HttpServletResponse response) {
		return wmsInveCustomerMonthlyIncomeService.geToalCountInfo(queryInfo);
		}	
		/**
		 * 获取报表计算数据总和
		 * @return List
		 * @author baisong
		*/
		@RequestMapping(value="/reportmanage/getInveCustomerMonthlyIncomeListDetailInfo.do",method={RequestMethod.GET,RequestMethod.POST})
		@ResponseBody
		public Map<String,Object> getInveCustomerMonthlyIncomeListDetailInfo(WmsInveCustomerMonthlyIncomeBeanVo queryInfo){
			 return wmsInveCustomerMonthlyIncomeService.getInveCustomerMonthlyIncomeListDetailInfo(queryInfo);
		}
}
