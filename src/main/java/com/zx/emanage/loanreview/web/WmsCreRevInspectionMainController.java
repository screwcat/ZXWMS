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

import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.loanreview.service.IWmsCreRevInspectionMainService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreRevInspectionMain;
import com.zx.emanage.loanreview.vo.WmsCreRevInspectionMainSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreRevInspectionMainController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevInspectionMainController.class);

    @Autowired
    private IWmsCreRevInspectionMainService wmscrerevinspectionmainService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevinspectionmainwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                                 RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreRevInspectionMainSearchBeanVO queryInfo)
    {
        return wmscrerevinspectionmainService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevinspectionmainwithpaginglist.do", method = { RequestMethod.GET,
                                                                                              RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreRevInspectionMainSearchBeanVO queryInfo)
    {
        return wmscrerevinspectionmainService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevInspectionMainVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevinspectionmaininfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreRevInspectionMain getInfoByPK(Integer wms_cre_rev_inspection_main_id)
    {
        return wmscrerevinspectionmainService.getInfoByPK(wms_cre_rev_inspection_main_id);
    }

    /**
     * Description :根据外键读取验点信息
     * 
     * @param primary key
     * @return WmsCreRevInspectionMainVO
     * @author hancd
     */
    @RequestMapping(value = "/loanreview/wmscrerevinspectionmaininfobyfk.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmscrerevinspectionmainService.getInfoByFK(wms_cre_credit_head_id);
    }

    /**
     * Description :验点单据数据的存储
     * 
     * @param approveWorkFlowVO 工作流需要工具VO
     * @param ydfamliy 验点家庭背景数据
     * @param flag 代表是否暂存(flag==0暂存 flag==1保存)
     * @param fileArr 验点上传的附件信息
     * @return "success" or "error" or user defined
     * @author hancd
     */
    @RequestMapping(value = "/loanreview/wmscrerevinspectionmainsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreRevInspectionMain bean,
                         WmsCreditWorkFlowVO approveWorkFlowVO, String ydfamliy, Integer flag, String fileArrs)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevinspectionmainService.save(bean, approveWorkFlowVO, ydfamliy, flag, user, fileArrs);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "贷前管理-信贷审核-验点审核组-保存";
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
    @RequestMapping(value = "/loanreview/wmscrerevinspectionmainupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreRevInspectionMain bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevinspectionmainService.update(bean, user);
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
     * Description :信贷验点退件
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author hancd
     */
    @RequestMapping(value = "/loanreview/creditydtoback.do", method = { RequestMethod.POST })
    @ResponseBody
    public String creditydToBack(HttpServletRequest request, WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevinspectionmainService.creditydToBack(approveWorkFlowVO, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "贷前管理-信贷审核-验点审核组-退件";
            SysTools.saveLog(request, msg); // 保存日志
        }
        return result;
    }
}