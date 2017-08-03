package com.zx.emanage.loanreview.web;

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

import com.zx.emanage.loanreview.service.IWmsCreHousingAssessmentService;
import com.zx.emanage.loanreview.vo.WmsCreHousingAssessmentSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreHousingAssessment;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreHousingAssessmentController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreHousingAssessmentController.class);

    @Autowired
    private IWmsCreHousingAssessmentService wmscrehousingassessmentService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrehousingassessmentwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                                 RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreHousingAssessmentSearchBeanVO queryInfo)
    {
        return wmscrehousingassessmentService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrehousingassessmentwithpaginglist.do", method = { RequestMethod.GET,
                                                                                              RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreHousingAssessmentSearchBeanVO queryInfo)
    {
        return wmscrehousingassessmentService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreHousingAssessmentVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrehousingassessmentinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreHousingAssessment getInfoByPK(Integer wms_cre_housing_assessment_id)
    {
        return wmscrehousingassessmentService.getInfoByPK(wms_cre_housing_assessment_id);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreHousingAssessmentVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrehousingassessmentinfobyfk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreHousingAssessment getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmscrehousingassessmentService.getInfoByFK(wms_cre_credit_head_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrehousingassessmentsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreHousingAssessment bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrehousingassessmentService.save(bean, user);
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
    @RequestMapping(value = "/loanreview/wmscrehousingassessmentupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreHousingAssessment bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrehousingassessmentService.update(bean, user);
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
}