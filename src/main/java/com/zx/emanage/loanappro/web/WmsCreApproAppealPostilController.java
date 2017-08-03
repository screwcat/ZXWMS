package com.zx.emanage.loanappro.web;

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

import com.zx.emanage.loanappro.service.IWmsCreApproAppealPostilService;
import com.zx.emanage.loanappro.vo.WmsCreApproAppealPostilSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreApproAppealPostil;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineApplicant;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreApproAppealPostilController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreApproAppealPostilController.class);

    @Autowired
    private IWmsCreApproAppealPostilService wmscreapproappealpostilService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanappro/wmscreapproappealpostilwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                                RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreApproAppealPostilSearchBeanVO queryInfo)
    {
        return wmscreapproappealpostilService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanappro/wmscreapproappealpostilwithpaginglist.do", method = { RequestMethod.GET,
                                                                                             RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreApproAppealPostilSearchBeanVO queryInfo)
    {
        return wmscreapproappealpostilService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreApproAppealPostilVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanappro/wmscreapproappealpostilinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreApproAppealPostil getInfoByPK(Integer wms_cre_appro_appeal_postil_id)
    {
        return wmscreapproappealpostilService.getInfoByPK(wms_cre_appro_appeal_postil_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loanappro/wmscreapproappealpostilsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreApproAppealPostil bean, int wms_cre_credit_head_id)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscreapproappealpostilService.save(bean, user, wms_cre_credit_head_id);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        // record log
        if ("success".equals(result))
        {
            String msg = "终审管理-终审审批-批注";
            SysTools.saveLog(request, msg); // record log method
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
    @RequestMapping(value = "/loanappro/wmscreapproappealpostilupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreApproAppealPostil bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscreapproappealpostilService.update(bean, user);
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
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineApplicantVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanappro/wmscreapproappealpostilbyfk.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmscreapproappealpostilService.getInfoByFK(wms_cre_credit_head_id);
    }

}