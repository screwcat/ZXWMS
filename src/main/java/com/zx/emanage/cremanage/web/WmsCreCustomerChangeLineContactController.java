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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zx.emanage.cremanage.service.IWmsCreCustomerChangeLineContactService;
import com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineContactSearchBeanVO;
import com.zx.emanage.loanreview.service.IWmsCreRevAttService;
import com.zx.emanage.util.gen.domain.WmsCreCustomerChangeLineContact;
import com.zx.emanage.util.gen.vo.WmsCreCustomerChangeLineContactVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCustomerChangeLineContactController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCustomerChangeLineContactController.class);

    @Autowired
    private IWmsCreCustomerChangeLineContactService wmscrecustomerchangelinecontactService;
    
    @Autowired
    private IWmsCreRevAttService iwmscrerevattService;
    

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelinecontactwithoutpaginglist.do", method = {
                                                                                                        RequestMethod.GET,
                                                                                                        RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo)
    {
        return wmscrecustomerchangelinecontactService.getListWithoutPaging(queryInfo);
    }

    /**
     * 通过提供主表单ID，获取相应联系人信息
     * 
     * @param wms_cre_credit_head_id
     * @param wms_cre_credit_line_customer_change_head_id
     * @return record list
     * @author hancd
     */
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelinecontactListWithPagingFK.do", method = {
                                                                                                       RequestMethod.GET,
                                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getContactsListWithPagingFK(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo)
    {
        return wmscrecustomerchangelinecontactService.getContactsListWithPagingFK(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelinecontactwithpaginglist.do", method = {
                                                                                                     RequestMethod.GET,
                                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo)
    {
        return wmscrecustomerchangelinecontactService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCustomerChangeLineContactVO
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelinecontactinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCustomerChangeLineContactVO getInfoByPK(Integer wms_cre_customer_change_line_contact_id)
    {
        return wmscrecustomerchangelinecontactService.getInfoByPK(wms_cre_customer_change_line_contact_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelinecontactsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCustomerChangeLineContact bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecustomerchangelinecontactService.save(bean, user);
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
     * Description :update method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelinecontactupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCustomerChangeLineContact bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecustomerchangelinecontactService.update(bean, user);
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
     * Description :delete method
     * 
     * @param pk
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelinecontactdelete.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doDelete(HttpServletRequest request, WmsCreCustomerChangeLineContact bean)
    {
        String result = wmscrecustomerchangelinecontactService.delete(bean);
        /*
         * // record log if("success".equals(result)){ String msg =
         * "log content"; SysTools.saveLog(request, msg); // record log method }
         */
        return result;
    }
    
    /**
     * 查询联系人信息
     */
    @RequestMapping(value = "/cremanage/searchContantList.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> searchContantList(HttpServletRequest request, WmsCreCustomerChangeLineContactSearchBeanVO queryInfo) {
        return wmscrecustomerchangelinecontactService.searchList(queryInfo);
    }
    
    
    /**
     * Description :3.37	信息完善 
     * @url /wms/sendCreditConfirmInfo.do
     * @param queryInfo
     * @return Map
     * @author  jiaodelong
     * @date 2016-10-18
     * @update 2017-2-24
     */
    @RequestMapping(value = "/wms/sendCreditConfirmInfo.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> sendCreditConfirmInfo(String wmsCreCustomerChangeLineContactBeanVoJson, String wmsCreRevAttListJson)
    {
    	Map<String, Object> resMap = new HashMap<String, Object>();
        try
        {	Gson gson = new Gson();
            String save_type = wmsCreRevAttListJson;// 保存类型
        	WmsCreCustomerChangeLineContactSearchBeanVO vo = gson.fromJson(wmsCreCustomerChangeLineContactBeanVoJson, new TypeToken<WmsCreCustomerChangeLineContactSearchBeanVO>(){}.getType());
            wmscrecustomerchangelinecontactService.sendCreditConfirmInfo(vo, save_type);
        	resMap.put("result", "success");
        	resMap.put("flag", true);
    		resMap.put("message","请求成功！");
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        	resMap.put("result", "error");
        	resMap.put("flag", false);
    		resMap.put("message", e.getMessage());
        }
        return resMap;
    }
    
    
    /**
     * 
     * @Title: getBizSavePerfectInfo
     * @Description: TODO(3.2.18        客户联系人信息保存)
     * @param wmsCreCustomerChangeLineContactBeanVoJson
     * @param wmsCreRevAttListJson
     * @return 
     * @author: jiaodelong
     * @time:2017年3月17日 下午5:00:14
     * history:
     * 1、2017年3月17日 jiaodelong 创建方法
     */
    @RequestMapping(value = "/wms/getBizSavePerfectInfo.do", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getBizSavePerfectInfo(String wmsCreCustomerChangeLineContactBeanVoJson, String wmsCreRevAttListJson)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try
        {   Gson gson = new Gson();
            String save_type = wmsCreRevAttListJson;// 保存类型
            WmsCreCustomerChangeLineContactSearchBeanVO vo = gson.fromJson(wmsCreCustomerChangeLineContactBeanVoJson, new TypeToken<WmsCreCustomerChangeLineContactSearchBeanVO>(){}.getType());
            wmscrecustomerchangelinecontactService.getBizSavePerfectInfo(vo, save_type);
            resMap.put("result", "success");
            resMap.put("flag", true);
            resMap.put("message","请求成功！");
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            resMap.put("result", "error");
            resMap.put("flag", false);
            resMap.put("message", e.getMessage());
        }
        return resMap;
    }
    
    
    
    
    
    
    
    
    /**
     * 根据headid查询联系人信息
     */
    @RequestMapping(value = "/cremanage/getContactsInfoList.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getContactsInfoList(HttpServletRequest request, WmsCreCustomerChangeLineContactSearchBeanVO queryInfo) {
        return wmscrecustomerchangelinecontactService.getContactsInfoList(queryInfo);
    }
    
    
}


