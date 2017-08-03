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

import com.zx.emanage.cremanage.service.IWmsCreHousingCustomerHouseService;
import com.zx.emanage.cremanage.vo.WmsCreHousingCustomerHouseSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreHousingCustomerHouse;
import com.zx.emanage.util.gen.vo.WmsCreCreditLineCustomerChangeHeadVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreHousingCustomerHouseController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreHousingCustomerHouseController.class);

    @Autowired
    private IWmsCreHousingCustomerHouseService wmscrehousingcustomerhouseService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrehousingcustomerhousewithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                                   RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreHousingCustomerHouseSearchBeanVO queryInfo)
    {
        return wmscrehousingcustomerhouseService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :得到抵押房产信息 为房贷查询
     * 
     * @param queryInfo
     * @return record list
     * @author ry
     */
    @RequestMapping(value = "/cremanage/wmscrehousingcustomerhouseallinfowithmccidf.do", method = { RequestMethod.GET,
                                                                                                   RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getHCHInfoListWithoutPagingByMccid(WmsCreHousingCustomerHouseSearchBeanVO queryInfo)
    {
        return wmscrehousingcustomerhouseService.getHCHInfoListWithoutPagingByMccid(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrehousingcustomerhousewithpaginglist.do", method = { RequestMethod.GET,
                                                                                                RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreHousingCustomerHouseSearchBeanVO queryInfo)
    {
        return wmscrehousingcustomerhouseService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreHousingCustomerHouseVO
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrehousingcustomerhouseinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreHousingCustomerHouse getInfoByPK(Integer id)
    {
        return wmscrehousingcustomerhouseService.getInfoByPK(id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrehousingcustomerhousesave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreHousingCustomerHouse bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrehousingcustomerhouseService.save(bean, user);
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
    @RequestMapping(value = "/cremanage/wmscrehousingcustomerhouseupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreHousingCustomerHouse bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrehousingcustomerhouseService.update(bean, user);
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
     * 获取房屋信息
     * @param request
     * @param wms_cre_credit_head_id
     * @return
     */
    @RequestMapping(value = "/cremanage/getwmscrehousingcustomerhouseinfo.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getKHZL(HttpServletRequest request, Integer wms_cre_credit_head_id)
    {
        return wmscrehousingcustomerhouseService.getwmscrehousingcustomerhouseinfo(wms_cre_credit_head_id);
    }
}