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

import com.zx.emanage.inve.service.IWmsInvePruductDeadlineService;
import com.zx.emanage.util.gen.entity.WmsInvePruductDeadline;
import com.zx.emanage.inve.vo.WmsInvePruductDeadlineSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInvePruductDeadlineController
{
    private static Logger log = LoggerFactory.getLogger(WmsInvePruductDeadlineController.class);

    @Autowired
    private IWmsInvePruductDeadlineService wmsinvepruductdeadlineService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvepruductdeadlinewithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                          RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsInvePruductDeadlineSearchBeanVO queryInfo)
    {
        return wmsinvepruductdeadlineService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvepruductdeadlinewithpaginglist.do", method = { RequestMethod.GET,
                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsInvePruductDeadlineSearchBeanVO queryInfo)
    {
        return wmsinvepruductdeadlineService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInvePruductDeadlineVO
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvepruductdeadlineinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsInvePruductDeadline getInfoByPK(Integer wms_inve_pruduct_deadline_id)
    {
        return wmsinvepruductdeadlineService.getInfoByPK(wms_inve_pruduct_deadline_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvepruductdeadlinesave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsInvePruductDeadline bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvepruductdeadlineService.save(bean, user);
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
    @RequestMapping(value = "/inve/wmsinvepruductdeadlineupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsInvePruductDeadline bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvepruductdeadlineService.update(bean, user);
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
    @RequestMapping(value = "/inve/wmsinvepruductdeadlinegetlist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListforlc(Integer wms_inve_pruduct_category_id)
    {
        return wmsinvepruductdeadlineService.getListforlc(wms_inve_pruduct_category_id);
    }
}