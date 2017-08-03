package com.zx.emanage.sysmanage.web;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.zx.emanage.sysmanage.service.ICrmCustomerInfoService;
import com.zx.emanage.sysmanage.vo.CrmCustomerInfoSearchBeanVO;
import com.zx.emanage.util.gen.entity.CrmCustomerInfo;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class CrmCustomerInfoController {
	private static Logger log = LoggerFactory.getLogger(CrmCustomerInfoController.class);
	
	@Autowired
	private ICrmCustomerInfoService crmcustomerinfoService;

	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return CrmCustomerInfoVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/sysmanage/crmcustomerinfoinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public CrmCustomerInfo getInfoByPK(Integer costomer_id) {
		return crmcustomerinfoService.getInfoByPK(costomer_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/sysmanage/crmcustomerinfosave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, CrmCustomerInfo bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = crmcustomerinfoService.save(bean, user);
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
	@RequestMapping(value = "/sysmanage/crmcustomerinfoupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, CrmCustomerInfo bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = crmcustomerinfoService.update(bean, user);
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
     * Description :WMS传递参数  
     * @param primary key 
     * @return CrmCustomerInfoVO
     * @author hancd
     */ 
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/sysmanage/getcrmcustomerinfoinfobypk.do", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<Map<String, Object>> getInfoByBean(CrmCustomerInfo bean) {
        bean.setPage(1);
        bean.setSize(10);
        Map<String, Object> resultMap = crmcustomerinfoService.getInfoByBean(bean);
        if (resultMap != null && resultMap.get("list") != null)
        {
            return (List<Map<String, Object>>) resultMap.get("list");
        }
        else
        {
            return null;
        }
    }  

	/**
     * Description :通过查询条件查询相对应的显示信息
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     */
    @RequestMapping(value = " /sysmanage/getsearchKHlist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getsearchKHlist(CrmCustomerInfoSearchBeanVO queryInfo)
    {
        return crmcustomerinfoService.getsearchKHlist(queryInfo);
    }
    
	/**
	 * 接口：crm端发起客户迁移申请确认时，调用此接口。
	 *  根据传入的参数获得客户是否存在理财单据信息(状态不是1.草稿，6.已完成，7.已终止）。
	 * 
	 * @param pm_personnel_id
	 *            原客户经理Id
	 * @param cus_ids
	 *            迁移客户集合（多个用户使用逗号分割）
	 * @return
	 * @author jinzhm
	 */
	@RequestMapping(value = "/wmsinve/getCustomerDataMoa.dos", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getCustomerDataMoa(String pm_personnel_id,
			String cus_ids) {
		Map<String, Object> rMaps = new HashMap<String, Object>();
		// 传入两个参数为空判断，任意参数有为空，直接返回。
		if (StringUtil.isBlank(pm_personnel_id) || StringUtil.isBlank(cus_ids)) {
			rMaps.put("ret_code", "002");
			rMaps.put("ret_msg", "接口调用失败");
			return rMaps;
		}
		try {
			rMaps = crmcustomerinfoService.getCustomerDataMoa(pm_personnel_id,
					cus_ids);
		} catch (Exception e) {
			log.error(e.getMessage());
			rMaps.put("ret_code", "002");
			rMaps.put("ret_msg", "接口调用失败");
		}
		return rMaps;
	}
    
	/**
	 * 接口：crm端审核通过客户迁移申请时，调用此接口。
	 * 根据原客户经理id和迁移客户集合查询上单信息(状态不是1.草稿，6.已完成，7.已终止），并修改上单信息的归属业务员为新客户经理Id（包括其他上级经理及部门）。
	 * 
	 * @param pm_personnel_id
	 *            原客户经理Id
	 * @param cus_ids
	 *            迁移客户集合（多个用户使用逗号分割）
	 * @param new_pm_personnel_id
	 *            新客户经理Id
	 * @return
	 * @author jinzhm
	 */
	@RequestMapping(value = "/wmsinve/changeCustomerDataMoa.dos", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
    public Map<String, Object> changeCustomerDataMoa(String pm_personnel_id, String cus_ids,
                                                     String new_pm_personnel_id, HttpServletRequest request)
    {
		Map<String, Object> rMaps = new HashMap<String, Object>();
		// 判断原客户经理Id，迁移客户集合，新客户经理id是否为空，任意一个值为空则直接返回并提示。
		if (StringUtil.isBlank(pm_personnel_id) || StringUtil.isBlank(cus_ids)
				|| StringUtil.isBlank(new_pm_personnel_id)) {
			rMaps.put("ret_code", "002");
			rMaps.put("ret_msg", "接口调用失败");
			return rMaps;
		}
		
		List<LinkedTreeMap> cusIdList = new Gson().fromJson(cus_ids, ArrayList.class);
		
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		try {
			rMaps = crmcustomerinfoService.changeCustomerDataMoa(
					pm_personnel_id, cusIdList, new_pm_personnel_id, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			rMaps.put("ret_code", "002");
			rMaps.put("ret_msg", "接口调用失败");
		}
		return rMaps;
	}
	
	/**
	 * 接口：crm端查看客户存量信息。
	 * 根据传入客户id集合查询客户所有收益中的单据总和返回。
	 * @param cus_ids 客户集合；多个客户用逗号隔开。
	 * @return {ret_code: 000,ret_msg: '操作成功',data_list:[{customer_id:'1',stock_amount:'100000'}]}
	 */
	@RequestMapping(value = "/wmsinve/getCustomerStockMoa.dos", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCustomerStockMoa(String cus_ids){
		Map<String, Object> rMaps = new HashMap<String, Object>();
		try {
			rMaps = crmcustomerinfoService.getCustomerStockMoa(cus_ids);
		} catch (Exception e) {
			log.error(e.getMessage());
			rMaps.put("ret_code", "002");
			rMaps.put("ret_msg", "接口调用失败");
		}
		return rMaps;
	}

	 /**
     * @Title: getBankName
     * @Description: 根据银行卡号获取银行名称
     * @param card_no 银行卡号
     * @return 银行名称
     * @author: jinzhm
     * @time:2017年2月28日 下午2:22:32
     * history:
     * 1、2017年2月28日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/getBankNamePad.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getBankName(String card_no)
    {
        final String bank_name_str = "邮政储蓄";
        Map<String, Object> rMaps = new HashMap<String, Object>();
        try
        {
            rMaps = crmcustomerinfoService.getBankName(card_no);
            Map<String, Object> bankMap = (Map<String, Object>) rMaps.get("ret_data");
            if (bankMap != null && bankMap.get("bank_name") != null && bankMap.get("bank_name").toString().indexOf(bank_name_str) > -1)
            {
                rMaps.put("ret_code", "002");
                rMaps.put("ret_msg", "暂不支持邮政储蓄银行卡,请更换其它银行卡");
                return rMaps;
            }
            rMaps.put("ret_msg", "操作成功");
            rMaps.put("ret_code", "000");
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("bank_name", "");
            rMaps.put("ret_data", dataMap);
            rMaps.put("ret_code", "000");
            rMaps.put("ret_msg", "操作成功");
        }
        return rMaps;
    }
}