package com.zx.emanage.inve.web;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInveCreditLimitService;
import com.zx.emanage.util.gen.entity.WmsInveCreditLimit;
import com.zx.emanage.inve.vo.WmsInveCreditLimitSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveCreditLimitController {
	private static Logger log = LoggerFactory.getLogger(WmsInveCreditLimitController.class);
	
	@Autowired
	private IWmsInveCreditLimitService wmsinvecreditlimitService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecreditlimitwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveCreditLimitSearchBeanVO queryInfo) {
		return wmsinvecreditlimitService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecreditlimitwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveCreditLimitSearchBeanVO queryInfo) {
		return wmsinvecreditlimitService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCreditLimitVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecreditlimitinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveCreditLimit getInfoByPK(Integer wms_inve_credit_limit) {
		return wmsinvecreditlimitService.getInfoByPK(wms_inve_credit_limit);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecreditlimitsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveCreditLimit bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecreditlimitService.save(bean, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		/*
		// record log	
		if("success".equals(result)){
			String msg = "log content";
			SysTools.saveLog(request, msg); // record log method
		}
		*/
		return result;
	}

	/**
	 * Description :update method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecreditlimitupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveCreditLimit bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecreditlimitService.update(bean, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		/*			
		// record log	
		if("success".equals(result)){
			String msg = "log content";
			SysTools.saveLog(request, msg); // record log method
		}
		*/
		return result;
	}	
	
	/**
	 * 
	 * @Title: verifyCreditLimitAccountAvailable
	 * @Description: 判断债权投资金额是否可以匹配债权
	 * @param product_account 投资金额
	 * @param actDateOfPayment 实际支付日期
	 * @param wms_inve_transa_id 上单表主键
	 * @param origCategoryId 老产品的产品id
	 * @param newCategoryId 更换新产品的id
	 * @param request 页面信息对象
	 * @return 如果投资金额小于债权限制金额返回true,否则返回false
	 * @author: DongHao
	 * @time:2017年4月7日 上午11:40:58
	 * history:
	 * 1、2017年4月7日 DongHao 创建方法
	 */
	@RequestMapping(value = "/inve/verifyCreditLimitAccountAvailable.do",method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public boolean verifyCreditLimitAccountAvailable(BigDecimal product_account,String actDateOfPayment,Integer wms_inve_transa_id, Integer origCategoryId, Integer newCategoryId, HttpServletRequest request)
	{
	    //获取当前登录的人员信息
        UserBean user = (UserBean)request.getSession().getAttribute(GlobalVal.USER_SESSION);
        
        //定义时间格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        Date actDate = null;
        
        try
        {
            actDate = format.parse(actDateOfPayment);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        
	    return wmsinvecreditlimitService.verifyCreditLimitAccountAvailable(user, product_account, actDate, wms_inve_transa_id, origCategoryId, newCategoryId);
	}
	
	/**
	 * 
	 * @Title: saveCreditLimit
	 * @Description: 债权不足时需要向债权限制信息表中添加记录
	 * @param product_account 投资金额
	 * @param request 页面对象信息
	 * @return 返回错误信息提示
	 * @author: DongHao
	 * @time:2017年4月7日 上午11:45:21
	 * history:
	 * 1、2017年4月7日 DongHao 创建方法
	 */
	@RequestMapping(value="/inve/saveCreditLimit.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> saveCreditLimit(BigDecimal product_account, HttpServletRequest request)
	{
	    //定义返回结果信息map集合
	    Map<String, Object> res_map = new HashMap<String, Object>();
	    
	    //获取当前登录的人员信息
        UserBean user = (UserBean)request.getSession().getAttribute(GlobalVal.USER_SESSION);
        
	    try
        {
            res_map = wmsinvecreditlimitService.saveCreditLimit(user, product_account);
        }
        catch (Exception e)
        {
            res_map.put("error", "error");
            e.printStackTrace();
        }
	    
	    return res_map;
	    
	}
}