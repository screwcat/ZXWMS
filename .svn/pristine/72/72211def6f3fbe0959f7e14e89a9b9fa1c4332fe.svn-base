package com.zx.emanage.inve.web;

import java.util.List;
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

import com.zx.emanage.inve.service.IWmsInveTransaUpdateLogService;
import com.zx.emanage.inve.vo.WmsInveTransaUpdateLogSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveTransaUpdateLog;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveTransaUpdateLogController {
	private static Logger log = LoggerFactory.getLogger(WmsInveTransaUpdateLogController.class);
	
	@Autowired
	private IWmsInveTransaUpdateLogService wmsinvetransaupdatelogService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvetransaupdatelogwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveTransaUpdateLogSearchBeanVO queryInfo) {
		return wmsinvetransaupdatelogService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvetransaupdatelogwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveTransaUpdateLogSearchBeanVO queryInfo) {
		return wmsinvetransaupdatelogService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveTransaUpdateLogVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvetransaupdateloginfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveTransaUpdateLog getInfoByPK(Integer wms_inve_transa_update_log_id) {
		return wmsinvetransaupdatelogService.getInfoByPK(wms_inve_transa_update_log_id);
	}	

	/**
	 * @Title: doSave 
	 * @Description: 财务主管修改理财上单信息
	 * @param request
	 * @param bean
	 * @return    
	 * @return String    
	 * @throws
	 * @author lvtu 
	 * @date 2015年8月19日 下午2:32:47
	 */
	@RequestMapping(value = "/inve/wmsinvetransaupdatelogsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveTransaUpdateLog bean,Integer product_deadlinel) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvetransaupdatelogService.save(bean, user, product_deadlinel);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		
		// record log	
		if("success".equals(result)){
			String msg = "业务管理-理财查询-修改理财单据数据";
			SysTools.saveLog(request, msg);
		}
		
		return result;
	}
	
    /**
     * @Title: wmsInveTransaUpdateLogValidate
     * @Description: 验证数据处理功能中支付时间调整是否可以进行
     * @param bean 数据处理数据对象
     * @return 如果可以进行调整返回success
     * @author: jinzhm
     * @time:2017年1月6日 上午8:19:45
     * history:
     * 1、2017年1月6日 jinzhm 创建方法
     */
	@RequestMapping(value = "/inve/wmsinvetransaupdatelogvalidate.do", method={RequestMethod.POST})
	@ResponseBody
    public String wmsInveTransaUpdateLogValidate(WmsInveTransaUpdateLog bean)
    {
        return wmsinvetransaupdatelogService.wmsInveTransaUpdateLogValidate(bean);
    }

	/**
	 * Description :update method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvetransaupdatelogupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveTransaUpdateLog bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvetransaupdatelogService.update(bean, user);
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
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvetransaupdatelog.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<WmsInveTransaUpdateLog> getInfo(HttpSession session, WmsInveTransaUpdateLog bean) {
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsinvetransaupdatelogService.getInfo(bean, user);
	}
}