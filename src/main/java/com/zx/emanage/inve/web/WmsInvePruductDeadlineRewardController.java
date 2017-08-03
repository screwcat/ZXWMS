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

import com.zx.emanage.inve.service.IWmsInvePruductDeadlineRewardService;
import com.zx.emanage.util.gen.entity.WmsInvePruductDeadlineReward;
import com.zx.emanage.inve.vo.WmsInvePruductDeadlineRewardSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInvePruductDeadlineRewardController
{
    private static Logger log = LoggerFactory.getLogger(WmsInvePruductDeadlineRewardController.class);

    @Autowired
    private IWmsInvePruductDeadlineRewardService wmsinvepruductdeadlinerewardService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvepruductdeadlinerewardwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                                RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsInvePruductDeadlineRewardSearchBeanVO queryInfo)
    {
        return wmsinvepruductdeadlinerewardService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvepruductdeadlinerewardwithpaginglist.do", method = { RequestMethod.GET,
                                                                                             RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsInvePruductDeadlineRewardSearchBeanVO queryInfo)
    {
        return wmsinvepruductdeadlinerewardService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInvePruductDeadlineRewardVO
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvepruductdeadlinerewardinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsInvePruductDeadlineReward getInfoByPK(Integer wms_inve_pruduct_deadline_reward_id)
    {
        return wmsinvepruductdeadlinerewardService.getInfoByPK(wms_inve_pruduct_deadline_reward_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvepruductdeadlinerewardsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsInvePruductDeadlineReward bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvepruductdeadlinerewardService.save(bean, user);
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
    @RequestMapping(value = "/inve/wmsinvepruductdeadlinerewardupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsInvePruductDeadlineReward bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvepruductdeadlinerewardService.update(bean, user);
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
     * Description :奖励利率赋值
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/inve/wmsinvepruductdeadlinerewardlistval.do", method = { RequestMethod.GET,
                                                                                      RequestMethod.POST })
    @ResponseBody
    public List<List<Map<String, Object>>> getListVal(Integer wms_inve_pruduct_category_id)
    {
        return wmsinvepruductdeadlinerewardService.getListVal(wms_inve_pruduct_category_id);
    }

    /**
     * 奖励利率查询
     * 
     * @param
     * @return
     * @author baisong
     */
    @RequestMapping(value = "/inve/showValCheck.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> showValCheck(HttpServletRequest request, Integer wms_inve_pruduct_deadline_id)
    {

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvepruductdeadlinerewardService.showValCheck(wms_inve_pruduct_deadline_id);
    }

    /**
     * 奖励利率查询
     * 
     * @param
     * @return
     * @author baisong
     */
    @RequestMapping(value = "/inve/showValbycategory.do", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> showVal(HttpServletRequest request, Integer wms_inve_pruduct_category_id)
    {

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvepruductdeadlinerewardService.showValbycategory(wms_inve_pruduct_category_id);
    }
}