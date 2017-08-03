package com.zx.emanage.inve.web;

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

import com.zx.emanage.inve.service.IWmsInvePruductReturnService;
import com.zx.emanage.util.gen.entity.WmsInvePruductReturn;
import com.zx.emanage.inve.vo.WmsInvePruductReturnSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveTransaSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInvePruductReturnController
{
    private static Logger log = LoggerFactory.getLogger(WmsInvePruductReturnController.class);

    @Autowired
    private IWmsInvePruductReturnService wmsinvepruductreturnService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvepruductreturnwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                        RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsInvePruductReturnSearchBeanVO queryInfo)
    {
        return wmsinvepruductreturnService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvepruductreturnwithpaginglist.do", method = { RequestMethod.GET,
                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsInvePruductReturnSearchBeanVO queryInfo)
    {
        return wmsinvepruductreturnService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInvePruductReturnVO
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvepruductreturninfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsInvePruductReturn getInfoByPK(Integer wms_inve_pruduct_return_id)
    {
        return wmsinvepruductreturnService.getInfoByPK(wms_inve_pruduct_return_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvepruductreturnsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsInvePruductReturn bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvepruductreturnService.save(bean, user);
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
    @RequestMapping(value = "/inve/wmsinvepruductreturnupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsInvePruductReturn bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvepruductreturnService.update(bean, user);
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
     * 产品复制页面 初始化数据
     * 
     * @param queryInfo
     * @return Map
     * @author baisong
     */
    @RequestMapping(value = "/inve/wmsinvepruductreturngetlist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListforlc(Integer wms_inve_pruduct_category_id)
    {
        return wmsinvepruductreturnService.getListforlc(wms_inve_pruduct_category_id);
    }
}