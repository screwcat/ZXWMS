package com.zx.emanage.cremanage.web;

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

import com.zx.emanage.cremanage.service.IWmsCreCarpHousingAttService;
import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreCarpHousingAttSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsMoaHouseInfoVO;
import com.zx.emanage.cremanage.vo.WmsSearchHosuingVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreCarpHousingAtt;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCarpHousingAttController {
	private static Logger log = LoggerFactory.getLogger(WmsCreCarpHousingAttController.class);
	
	@Autowired
	private IWmsCreCarpHousingAttService wmscrecarphousingattService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanreview/wmscrecarphousingattwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsCreCarpHousingAttSearchBeanVO queryInfo) {
		return wmscrecarphousingattService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanreview/wmscrecarphousingattwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsCreCarpHousingAttSearchBeanVO queryInfo) {
		return wmscrecarphousingattService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCarpHousingAttVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanreview/wmscrecarphousingattinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsCreCarpHousingAtt getInfoByPK(Integer wms_cre_carp_housing_att_id) {
		return wmscrecarphousingattService.getInfoByPK(wms_cre_carp_housing_att_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanreview/wmscrecarphousingattsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsCreCarpHousingAtt bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecarphousingattService.save(bean, user);
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
	@RequestMapping(value = "/loanreview/wmscrecarphousingattupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsCreCarpHousingAtt bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecarphousingattService.update(bean, user);
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
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanreview/wmscrecarphousingattsaveCar.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSaveCar(HttpServletRequest request,WmsCreCarpHousingAttSearchBeanVO beanvo) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecarphousingattService.save(user,beanvo);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		
		// record log	
		if("success".equals(result)){
			String msg = "车贷管理--保存附件--"+beanvo.getData_type();
			SysTools.saveLog(request, msg); // record log method
		}
		return result;
	}
	
	@RequestMapping(value = "/cremanage/wmscrecarattsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCarLoanWorkFlowVO wmsCarLoanWorkFlowVO, String fileArr)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecarphousingattService.doSQSave(wmsCarLoanWorkFlowVO, fileArr, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "贷款管理-车贷贷款-贷款复核";
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }
	
	@RequestMapping(value = "/cremanage/wmscrecarattsavebatch.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSaveBatch(HttpServletRequest request, String wmsCreHeadIds, String wmsTaskIds,
		            String wmsCreditLimits, String wmsCreditCreLoanTypes, String pass,
		            String advice)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecarphousingattService.doSQSaveBatch(wmsCreHeadIds, wmsTaskIds, wmsCreditLimits,
												                wmsCreditCreLoanTypes, pass, advice,
												                String.valueOf(user.getUserId()));
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "贷款管理-车贷贷款-贷款复核";
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }
	/**
	 * moa 调用wms方法 申请 办件 补件等
	 * @param request
	 * @param wmsCarLoanWorkFlowVO
	 * @param fileArr
	 * @return 
	 */
	@RequestMapping(value = "/cremanage/wmsMoaSave.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String,Object> wmsMoaSave(HttpServletRequest request, WmsMoaHouseInfoVO bean)
    {
    	Map<String,Object> map=new HashMap<>();
    	//log.debug(new Gson().toJson(bean));
        String result = "";
        try
        {
        	map =  wmscrecarphousingattService.wmsMoaSave(bean);
        }
        catch (Exception e)
        {
        	log.error(e.getMessage());
            result = "error";
            map.put("flag", false);
            map.put("result", result);
            map.put("message", e.getMessage());
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "MOA申请"+bean.getFalg();
            SysTools.saveLog(request, msg); // record log method
        }
        return map;
    }
	/**
	 * moa 调用wms方法 办件查询列表 获取流程task  idList
	 * @param request
	 * @param wmsCarLoanWorkFlowVO
	 * @param fileArr
	 * @return 
	 */
	@RequestMapping(value = "/cremanage/getHosuingIdListMoa.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String,Object> getHosuingIdList(HttpServletRequest request, String  personnel_id)
    {
    	Map<String,Object> map=new HashMap<>();
    	//log.debug(new Gson().toJson(bean));
        String result = "";
        try
        {
        	map =  wmscrecarphousingattService.getHosuingIdList(personnel_id);
        }
        catch (Exception e)
        {
        	log.error(e.getMessage());
            result = "error";
            map.put("flag", false);
            map.put("result", result);
            map.put("message", e.getMessage());
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "MOA获取办件列表";
            SysTools.saveLog(request, msg); // record log method
        }
        return map;
    }
	/**
	 * moa 调用wms方法  获取单据的退件原因从流程历史中获取
	 * @param request
	 * @param param
	 * @param 
	 * @return Map<String,Object>
	 * @author baisong 
	 */
	@RequestMapping(value = "/cremanage/getHosuingWorkInfoMoa.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String,Object> getHosuingWorkInfo(HttpServletRequest request, String  param)
    {
    	Map<String,Object> map=new HashMap<>();
    	//log.debug(new Gson().toJson(bean));
        String result = "";
        try
        {
        	map =  wmscrecarphousingattService.getHosuingWorkInfo(param);
        }
        catch (Exception e)
        {
        	log.error(e.getMessage());
            result = "error";
            map.put("flag", false);
            map.put("result", result);
            map.put("message", e.getMessage());
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "MOA获取办件列表";
            SysTools.saveLog(request, msg); // record log method
        }
        return map;
    }
	///*************下面方法是给移动端第二版本使用开始*****************///
	/**
	 * moa 调用wms方法 各个查询列表 获取流程idlist 
	 * @param request
	 * @param wmsCarLoanWorkFlowVO
	 * @param personnel_id 使用 WmsSearchHosuingVO类接参数
	 * @param invekey   使用 WmsSearchHosuingVO类接参数
	 * @return 
	 */
	@RequestMapping(value = "/cremanage/getHosuingIdListTwoMoa.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String,Object> getHosuingIdListTwo(HttpServletRequest request,WmsSearchHosuingVO wmsSearchHosuingVO)
    {
    	Map<String,Object> map=new HashMap<>();
        String result = "";
        try
        {
        	map =  wmscrecarphousingattService.getHosuingIdList(wmsSearchHosuingVO);
        }
        catch (Exception e)
        {
        	log.error(e.getMessage());
            result = "error";
            map.put("flag", false);
            map.put("result", result);
            map.put("message", e.getMessage());
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "MOA获取办件列表";
            SysTools.saveLog(request, msg); // record log method
        }
        return map;
    }
	/**
	 * moa 调用wms方法贷款申请或补件
	 * @param request
	 * @param wmsCarLoanWorkFlowVO
	 * @param fileArr
	 * @return 
	 * @author baisong
	 */
	@RequestMapping(value = "/cremanage/wmsMoaSaveUp.do", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Map<String,Object> wmsMoaSaveUp(HttpServletRequest request, WmsMoaHouseInfoVO bean,String customer_info)
    {
    	Map<String,Object> map=new HashMap<>();
        String result = "";
        try
        {
        	map =  wmscrecarphousingattService.wmsMoaSaveUp(bean,customer_info);
        }
        catch (Exception e)
        {
        	log.error(e.getMessage());
            result = "error";
            map.put("flag", false);
            map.put("result", result);
            map.put("message", e.getMessage());
        }
        //record log
        if ("success".equals(result))
        {
            String msg = "MOA申请"+bean.getFalg();
            SysTools.saveLog(request, msg); // record log method
        }
        return map;
    }
	///*************移动端第二版本使用结束*****************///
    // **************2.0版本****************//
    /**
     * moa 调用wms方法贷款申请或补件
     * @param request
     * @param wmsCarLoanWorkFlowVO
     * @param fileArr
     * @return 
     * @author baisong
     */
    @RequestMapping(value = "/cremanage/wmsBizMoaSaveUp.do", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> wmsBizMoaSaveUp(HttpServletRequest request, WmsMoaHouseInfoVO bean, String customer_info)
    {
        Map<String, Object> map = new HashMap<>();
        String result = "";
        try
        {
            map = wmscrecarphousingattService.wmsBizMoaSaveUp(bean, customer_info);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
            map.put("flag", false);
            map.put("result", result);
            map.put("message", e.getMessage());
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "MOA申请" + bean.getFalg();
            SysTools.saveLog(request, msg); // record log method
        }
        return map;
    }

    /**
     * moa 手机端贷款单据删除(3.2.35 手机端贷款单据删除)
     * @param request
     * @param WmsMoaHouseInfoVO
     * @return 
     * @author baisong
     */
    @RequestMapping(value = "/wms/postBizDeleteBill.do", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> postBizDeleteBill(HttpServletRequest request, WmsMoaHouseInfoVO bean)
    {
        Map<String, Object> map = new HashMap<>();
        String result = "";
        try
        {
            map = wmscrecarphousingattService.postBizDeleteBill(bean);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
            map.put("flag", false);
            map.put("result", result);
            map.put("message", e.getMessage());
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "MOA申请" + bean.getFalg();
            SysTools.saveLog(request, msg); // record log method
        }
        return map;
    }
    // **************2.0版本****************//
}