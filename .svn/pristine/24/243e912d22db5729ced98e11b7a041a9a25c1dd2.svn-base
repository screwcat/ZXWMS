package com.zx.emanage.loancheck.web;

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

import com.zx.emanage.cremanage.service.IWmsHouseCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreCreditHeadSearchBeanVO;
import com.zx.emanage.loancheck.service.IWmsCreHousingCertificateService;
import com.zx.emanage.loancheck.service.IWmsCreHousingCheckService;
import com.zx.emanage.loancheck.vo.WmsCreHousingCheckSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreHousingCheck;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

@Controller
public class WmsCreHousingCertificateController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreHousingCheckController.class);

    @Autowired
    private IWmsCreHousingCheckService wmscrehousingcheckService;

    @Autowired
    private IWmsCreCreditHeadService wmscrecreditheadService;

    @Autowired
    private IWmsHouseCreditWorkFlowService approveHouseCreditWorkFlowService;

    @Autowired
    private IWmsCreHousingCertificateService wmscrehousingcertificateService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrehousingcertificatewithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                                 RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreHousingCheckSearchBeanVO queryInfo)
    {
        return wmscrehousingcheckService.getListWithoutPaging(queryInfo);
    }

    /**
     * 证信审批列表
     * 
     * @param queryInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/loancheck/wmscrehousingcertificatewithpaginglist.do", method = { RequestMethod.GET,
                                                                                              RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCreHousingCertificateListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                                      HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getListWithPagingForFdForAdd(queryInfo, user, "8");
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreHousingCheckVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrehousingcertificateinfo.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreHousingCheck getInfo(Integer wms_cre_credit_head_id)
    {
        return null;
    }

    /**
     * Description :提交审批 保存数据
     * 
     * @param vo fileArr
     * @return "success" or "error" or user defined
     * @author zhubo
     */
    @RequestMapping(value = "/loancheck/wmscrehousingcertificatesave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsHouseCreditWorkFlowVO vo, String fileArr)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrehousingcertificateService.save(user, vo, fileArr);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "贷前管理-房贷审核-征信审核组-审核";
            SysTools.saveLog(request, msg);
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
    @RequestMapping(value = "/loancheck/wmscrehousingcertificateupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreHousingCheck bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrehousingcheckService.update(bean, user);
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
