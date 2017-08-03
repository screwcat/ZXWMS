package com.zx.emanage.loanreview.web;

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

import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.loanreview.service.IWmsCreRevCertificateMainService;
import com.zx.emanage.loanreview.vo.WmsCreRevCertificateMainSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreRevCertificateMain;
import com.zx.emanage.util.gen.entity.WmsCreRevCertificateModel;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreRevCertificateMainController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevCertificateMainController.class);

    @Autowired
    private IWmsCreRevCertificateMainService wmscrerevcertificatemainService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevcertificatemainwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreRevCertificateMainSearchBeanVO queryInfo)
    {
        return wmscrerevcertificatemainService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevcertificatemainwithpaginglist.do", method = { RequestMethod.GET,
                                                                                               RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreRevCertificateMainSearchBeanVO queryInfo)
    {
        return wmscrerevcertificatemainService.getListWithPaging(queryInfo);
    }

    /**
     * Description :获取特殊说明信息
     * 
     * @param primary key
     * @return WmsCreRevCertificateMain
     * @author zhubo
     */
    @RequestMapping(value = "/loanreview/wmscrerevcertificatemaininfobyentity.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<WmsCreRevCertificateMain> getInfoByEntity(WmsCreRevCertificateMain queryInfo)
    {
        return wmscrerevcertificatemainService.getInfoByEntity(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevCertificateMainVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevcertificatemaininfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreRevCertificateMain getInfoByPK(Integer wms_cre_rev_certificate_main_id)
    {
        return wmscrerevcertificatemainService.getInfoByPK(wms_cre_rev_certificate_main_id);
    }

    /**
     * Description :保存信贷征信附件和特殊说明信息及重要数据
     * 
     * @param
     * @return "success" or "error" or user defined
     * @author zhubo
     */
    @RequestMapping(value = "/loanreview/wmscrerevcertificatemainsavexd.do", method = { RequestMethod.POST })
    @ResponseBody
    public String saveXd(HttpServletRequest request, String zxsh, WmsCreRevCertificateModel bean,
                         WmsCreditWorkFlowVO approveWorkFlowVO, boolean isCommit)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevcertificatemainService.saveXd(zxsh, bean, approveWorkFlowVO, user, isCommit);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "贷前管理-信贷审核-征信审核组-审核";
            SysTools.saveLog(request, msg);
        }
        return result;
    }

    /**
     * Description :保存房贷征信附件及特殊说明信息
     * 
     * @param
     * @return "success" or "error" or user defined
     * @author zhubo
     */
    @RequestMapping(value = "/loanreview/wmscrerevcertificatemainsavefd.do", method = { RequestMethod.POST })
    @ResponseBody
    public String saveFd(HttpServletRequest request, String zxsh, WmsHouseCreditWorkFlowVO vo, boolean isCommit)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevcertificatemainService.saveFd(zxsh, vo, user, isCommit);
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
    @RequestMapping(value = "/loanreview/wmscrerevcertificatemainupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreRevCertificateMain bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevcertificatemainService.update(bean, user);
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
     * Description :信贷征信退件
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author zhubo
     */
    @RequestMapping(value = "/loanreview/creditcertificatetoback.do", method = { RequestMethod.POST })
    @ResponseBody
    public String waterToBack(HttpServletRequest request, WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevcertificatemainService.creditCertificateToBack(approveWorkFlowVO, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "贷前管理-信贷审核-征信审核组-退件";
            SysTools.saveLog(request, msg); // 保存日志
        }
        return result;
    }

    /**
     * Description :房贷征信退件
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author zhubo
     */
    @RequestMapping(value = "/loanreview/housecertificatetoback.do", method = { RequestMethod.POST })
    @ResponseBody
    public String houseCertificateToBack(HttpServletRequest request, WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevcertificatemainService.houseCertificateToBack(approveHouseWorkFlowVO, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "贷前管理-房贷审核-征信审核组-退件";
            SysTools.saveLog(request, msg); // 保存日志
        }
        return result;
    }
}