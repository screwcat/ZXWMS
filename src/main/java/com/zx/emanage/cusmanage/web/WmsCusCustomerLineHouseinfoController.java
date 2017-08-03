package com.zx.emanage.cusmanage.web;

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

import com.zx.emanage.cusmanage.service.IWmsCusCustomerLineHouseinfoService;
import com.zx.emanage.cusmanage.vo.WmsCusCustomerLineHouseinfoSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCusCustomerLineHouseinfo;
import com.zx.emanage.util.gen.vo.WmsCusCustomerLineHouseinfoVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCusCustomerLineHouseinfoController
{
    private static Logger log = LoggerFactory.getLogger(WmsCusCustomerLineHouseinfoController.class);

    @Autowired
    private IWmsCusCustomerLineHouseinfoService wmscuscustomerlinehouseinfoService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerlinehouseinfowithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCusCustomerLineHouseinfoSearchBeanVO queryInfo)
    {
        return wmscuscustomerlinehouseinfoService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerlinehouseinfowithpaginglist.do", method = { RequestMethod.GET,
                                                                                                 RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCusCustomerLineHouseinfoSearchBeanVO queryInfo)
    {
        return wmscuscustomerlinehouseinfoService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCusCustomerLineHouseinfoVO
     * @author auto_generator
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerlinehouseinfoinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCusCustomerLineHouseinfoVO getInfoByPK(Integer wms_cus_customer_line_houseinfo_id)
    {
        return wmscuscustomerlinehouseinfoService.getInfoByPK(wms_cus_customer_line_houseinfo_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerlinehouseinfosave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCusCustomerLineHouseinfo bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscuscustomerlinehouseinfoService.save(bean, user);
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
    @RequestMapping(value = "/cusmanage/wmscuscustomerlinehouseinfoupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCusCustomerLineHouseinfo bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscuscustomerlinehouseinfoService.update(bean, user);
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
    @RequestMapping(value = "/cusmanage/wmscuscustomerlinehouseinfodelete.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doDelete(HttpServletRequest request, WmsCusCustomerLineHouseinfo bean)
    {
        String result = wmscuscustomerlinehouseinfoService.delete(bean);
        /*
         * // record log if("success".equals(result)){ String msg =
         * "log content"; SysTools.saveLog(request, msg); // record log method }
         */
        return result;
    }
}