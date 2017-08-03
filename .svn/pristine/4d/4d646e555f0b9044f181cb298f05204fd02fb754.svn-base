package com.zx.emanage.cremanage.web;

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

import com.zx.emanage.cremanage.service.IWmsCreCustomerChangeLineHouseinfoService;
import com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineHouseinfoSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCreCustomerChangeLineHouseinfo;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.vo.WmsCreCreditHeadVO;
import com.zx.emanage.util.gen.vo.WmsCreCustomerChangeLineHouseinfoVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCustomerChangeLineHouseinfoController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCustomerChangeLineHouseinfoController.class);

    @Autowired
    private IWmsCreCustomerChangeLineHouseinfoService wmscrecustomerchangelinehouseinfoService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelinehouseinfowithoutpaginglist.do", method = {
                                                                                                          RequestMethod.GET,
                                                                                                          RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCustomerChangeLineHouseinfoSearchBeanVO queryInfo)
    {
        return wmscrecustomerchangelinehouseinfoService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :房产核查缴费列表
     * 
     * @param queryInfo
     * @return record list
     * @author jiaodelong
     */
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelinehouseinfowithpaginglist.do", method = {
                                                                                                       RequestMethod.GET,
                                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCreditHeadVO queryInfo,HttpServletRequest request)
    {
    	 // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecustomerchangelinehouseinfoService.getListWithPaging(queryInfo,user);
    }
    
    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelinehouseinfowithpaginglistout.do", method = {
                                                                                                       RequestMethod.GET,
                                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPagingout(WmsCreCreditHeadVO queryInfo,HttpServletRequest request)
    {
    	 // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecustomerchangelinehouseinfoService.getListWithPagingout(queryInfo,user);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCustomerChangeLineHouseinfoVO
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelinehouseinfoinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCustomerChangeLineHouseinfoVO getInfoByPK(Integer wms_cre_customer_change_line_houseinfo_id)
    {
        return wmscrecustomerchangelinehouseinfoService.getInfoByPK(wms_cre_customer_change_line_houseinfo_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelinehouseinfosave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCustomerChangeLineHouseinfo bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecustomerchangelinehouseinfoService.save(bean, user);
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
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelinehouseinfoupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCustomerChangeLineHouseinfo bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecustomerchangelinehouseinfoService.update(bean, user);
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
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelinehouseinfodelete.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doDelete(HttpServletRequest request, WmsCreCustomerChangeLineHouseinfo bean)
    {
        String result = wmscrecustomerchangelinehouseinfoService.delete(bean);
        /*
         * // record log if("success".equals(result)){ String msg =
         * "log content"; SysTools.saveLog(request, msg); // record log method }
         */
        return result;
    }
}