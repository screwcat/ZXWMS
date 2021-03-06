package com.zx.emanage.inve.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import com.zx.emanage.inve.service.IWmsInveTransaProtocolService;
import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.emanage.inve.vo.WmsInveTransaProtocolSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.emanage.util.gen.vo.WmsInveRedeemVO;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveTransaProtocolController
{
    private static Logger log = LoggerFactory.getLogger(WmsInveTransaProtocolController.class);

    @Autowired
    private IWmsInveTransaProtocolService wmsinvetransaprotocolService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransaprotocolwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                         RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsInveTransaProtocolSearchBeanVO queryInfo)
    {
        return wmsinvetransaprotocolService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransaprotocolwithpaginglist.do", method = { RequestMethod.GET,
                                                                                      RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsInveTransaProtocolSearchBeanVO queryInfo)
    {
        return wmsinvetransaprotocolService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInveTransaProtocolVO
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransaprotocolinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsInveTransaProtocol getInfoByPK(Integer wms_inve_transa_protocol_id)
    {
        return wmsinvetransaprotocolService.getInfoByPK(wms_inve_transa_protocol_id);
    }

    /**
     * Description :债权协议信息保存协议
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @RequestMapping(value = "/inve/wmsinvetransaprotocolsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsInveTransaProtocol bean, String xqxy, String flag)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransaprotocolService.save(bean, user, xqxy, flag);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        if ("success".equals(result))
        {
            String msg = "理财上单-债权匹配-理财协议保存";
            SysTools.saveLog(request, msg);
            String msg2 = "协议打印@@@" + bean.getWms_inve_transa_id() + "@@@" + "0" + "@@@" + bean.getProt_code() + "@@@"
                          + bean.getWms_inve_transa_prod_id();
            SysTools.saveLog(request, msg2);
        }
        return result;
    }
    

    /**
     * Description :update method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransaprotocolupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsInveTransaProtocol bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransaprotocolService.update(bean, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        /*
         * // record log if("success".equals(result)){ String msg =
         * "log content"; SysTools.saveLog(request, msg); // record log method }
         */
        return result;
    }

    /**
     * 通过上单信息表ID获得赎回申请信息
     * 
     * @param wms_inve_transa_id
     * @return map
     * @author zhubo
     */
    @RequestMapping(value = "/inve/getredeemapplyinfo.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getRedeemApply(HttpServletRequest request, String wms_inve_transa_id)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        Map<String, Object> map = wmsinvetransaprotocolService.getRedeemApply(wms_inve_transa_id);
        map.put("a_info", user.getRealname() + " " + user.getUserCode());
        return map;
    }
   
    /**
     * 验证赎回日期是否
     * @param 	WmsInveRedeemVO  
     * @return 	map
     * @author 	zhangyunfei
     * @date 	2016年08月10日
     */
    @RequestMapping(value = "/inve/checkRedeemDate.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String checkRedeemDate(HttpServletRequest request, WmsInveRedeem bean)
    {
    	String result = "";
    	Date now = new Date(System.currentTimeMillis());
    	Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum((Calendar.DAY_OF_MONTH)));
		now = DateUtil.strDate((new SimpleDateFormat("yyyy-MM-dd")).format(calendar.getTime()), null);
		//判断申请赎回日期是否大于当前月1号
    	if(!(now.compareTo(DateUtil.strDate(bean.getRedeem_date().toString(), null))<=0)){
    		return "dateerror";
    	}
    	return result;
    }
    
    
    /**
     * 通过赎回金额  赎回日期计算收益 管理费
     * @param 	WmsInveRedeemVO  
     * @return 	map
     * @author 	zhangyunfei
     * @date 	2016年08月10日
     */
    @RequestMapping(value = "/inve/getRedeemDueIncome.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getRedeemDueIncome(WmsInveRedeemVO wmsInveRedeemVO)
    {
        return wmsinvetransaprotocolService.getRedeemDueIncome(wmsInveRedeemVO);
    }
    
    /**
     * Description :add 协议
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @RequestMapping(value = "/inve/wmsinvetransaprotocolsaveback.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSaveBack(HttpServletRequest request, WmsInveTransaProtocol bean,
    						WmsInveDebtWorkFlowVO wDebtWorkFlowVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransaprotocolService.saveBack(bean, user, wDebtWorkFlowVO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        if ("success".equals(result))
        {
            String msg = "理财上单-债权匹配-赎回-理财协议保存";
            SysTools.saveLog(request, msg);
            String msg2 = "协议打印@@@" + bean.getWms_inve_transa_id() + "@@@" + "0" + "@@@" + bean.getProt_code() + "@@@"
                          + bean.getWms_inve_transa_prod_id();
            SysTools.saveLog(request, msg2);
        }
        return result;
    }

    /**
     * 通过上单信息表ID与赎回单据表ID获得赎回申请信息
     * 
     * @param wms_inve_transa_id
     * @return map
     * @author zhubo
     */
    @RequestMapping(value = "/inve/getprotocoldata.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getProtocolData(String checkedInveTransaIDs, String wms_inve_redeem_id)
    {
        return wmsinvetransaprotocolService.getProtocolData(checkedInveTransaIDs, wms_inve_redeem_id);
    }

    /**
     * 根据合同编号查询合同内容 baisong
     */
    @RequestMapping(value = "/inve/getProtocolByCode.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getInfoByCode(String prot_code)
    {
        return wmsinvetransaprotocolService.getInfoByCode(prot_code);
    }

    /**
     * 根据合同编号查询合同内容 暂时不使用2015-01-26 baisong
     */
    /*
     * @RequestMapping(value = "/inve/getCodeById.do", method =
     * {RequestMethod.GET, RequestMethod.POST})
     * @ResponseBody public Map<String, Object> getCodeById(Integer
     * wms_inve_transa_prod_id,Integer wms_inve_transa_id,Integer
     * wms_inve_redeem_id) { return
     * wmsinvetransaprotocolService.getCodeById(wms_inve_transa_prod_id,
     * wms_inve_transa_id,wms_inve_redeem_id); }
     */
    /**
     * 根据合同编号获取赎回ID的值
     * 
     * @author hancd
     */
    @RequestMapping(value = "/inve/getWmsInveTransaPro.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getWmsInveTransaPro(Integer wms_inve_transa_protocol_id)
    {
        return wmsinvetransaprotocolService.getWmsInveTransaPro(wms_inve_transa_protocol_id);
    }
    /**
     * Description :理财协议签订  新 假设   先定义此方法
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author hancd
     * @date 2015年12月16日 14:18
     */
    @RequestMapping(value = "/inve/towmsinvetransaprotocolsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String toSave(HttpServletRequest request, WmsInveTransaProtocol bean,WmsInveDebtWorkFlowVO workFlowVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransaprotocolService.toSave(bean, user,workFlowVO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        if ("success".equals(result))
        {
            String msg = "财务管理>理财上单>理财签约>打印协议";
            SysTools.saveLog(request, msg);
            String msg2 = "协议打印@@@" + bean.getWms_inve_transa_id() + "@@@" + "0" + "@@@" + bean.getProt_code() + "@@@"
                          + bean.getWms_inve_transa_prod_id();
            SysTools.saveLog(request, msg2);
        }
        return result;
    }
}