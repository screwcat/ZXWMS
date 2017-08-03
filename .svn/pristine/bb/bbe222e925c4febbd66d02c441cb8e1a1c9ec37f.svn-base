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

import com.zx.emanage.inve.service.IWmsInveSpecialApprovalService;
import com.zx.emanage.util.gen.entity.WmsInveSpecialApproval;
import com.zx.emanage.inve.vo.WmsInveSpecialApprovalSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveSpecialApprovalController
{
    private static Logger log = LoggerFactory.getLogger(WmsInveSpecialApprovalController.class);

    @Autowired
    private IWmsInveSpecialApprovalService wmsinvespecialapprovalService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvespecialapprovalwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                          RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsInveSpecialApprovalSearchBeanVO queryInfo)
    {
        return wmsinvespecialapprovalService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvespecialapprovalwithpaginglist.do", method = { RequestMethod.GET,
                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsInveSpecialApprovalSearchBeanVO queryInfo)
    {
        return wmsinvespecialapprovalService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInveSpecialApprovalVO
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvespecialapprovalinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsInveSpecialApproval getInfoByPK(Integer special_approval_leader_id)
    {
        return wmsinvespecialapprovalService.getInfoByPK(special_approval_leader_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvespecialapprovalsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsInveSpecialApproval bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvespecialapprovalService.save(bean, user);
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
    @RequestMapping(value = "/inve/wmsinvespecialapprovalupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsInveSpecialApproval bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvespecialapprovalService.update(bean, user);
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
     * Description :查询特批领导，请选择使用‘-1’
     * 
     * @return list
     * @author ry
     */
    @RequestMapping(value = "/inve/wmsinvespecialapprovalistforselect.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<com.zx.emanage.util.gen.entity.WmsInveSpecialApproval> getInveSpecialapprova()
    {

        List<com.zx.emanage.util.gen.entity.WmsInveSpecialApproval> wmsInveSpecialApproval = wmsinvespecialapprovalService.getAllInveSpecialapprova();
        return wmsInveSpecialApproval;
    }
}