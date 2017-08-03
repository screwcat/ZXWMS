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

import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreCreditHeadSearchBeanVO;
import com.zx.emanage.loanreview.service.IWmsCreRevInfoMainService;
import com.zx.emanage.loanreview.vo.WmsCreRevInfoMainSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreRevInfoMain;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreRevInfoMainController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevInfoMainController.class);

    @Autowired
    private IWmsCreRevInfoMainService wmscrerevinfomainService;

    @Autowired
    private IWmsCreCreditHeadService wmscrecreditheadService;

    /**
     * Description :房贷信息调查列表
     * 
     * @param queryInfo
     * @return record list
     * @author zhubo
     */
    @RequestMapping(value = "/loanreview/wmscrehousinginfowithpaginglist.do", method = { RequestMethod.GET,
                                                                                        RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPagingForFd(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                         HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getListWithPagingForFdForAdd(queryInfo, user, "9");
    }

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevinfomainwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                           RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreRevInfoMainSearchBeanVO queryInfo)
    {
        return wmscrerevinfomainService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevinfomainwithpaginglist.do", method = { RequestMethod.GET,
                                                                                        RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreRevInfoMainSearchBeanVO queryInfo)
    {
        return wmscrerevinfomainService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevInfoMainVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevinfomaininfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreRevInfoMain getInfoByPK(Integer wms_cre_rev_info_main_id)
    {
        return wmscrerevinfomainService.getInfoByPK(wms_cre_rev_info_main_id);
    }

    /**
     * 贷前-房贷-信息审批保存操作
     * 
     * @param bean
     * @param grInfo页面个人信息的所有信息
     * @param qyInfo页面企业信息的所有信息
     * @return "success" or "error" or user defined
     * @author hancd
     */
    @RequestMapping(value = "/loanreview/wmshouserevinfomainsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSaveHouse(HttpServletRequest request, WmsCreRevInfoMain bean, String grInfo, String qyInfo,
                              WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, Integer flag)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevinfomainService.saveHouse(bean, user, grInfo, qyInfo, approveHouseWorkFlowVO, flag);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "贷前管理-房贷审核-信调审核组-审核";
            SysTools.saveLog(request, msg);
        }
        return result;
    }

    /**
     * 贷前-信贷-信息审批保存操作
     * 
     * @param bean
     * @param grInfo页面个人信息的所有信息
     * @param qyInfo页面企业信息的所有信息
     * @param flag 区分是暂存还是保存 flag==0暂存 flag==1保存
     * @param InfoImData 信息存储重要数据
     * @return "success" or "error" or user defined
     * @author hancd
     */
    @RequestMapping(value = "/loanreview/wmscrerevinfomainsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreRevInfoMain bean, String grInfo, String qyInfo,
                         WmsCreditWorkFlowVO approveWorkFlowVO, Integer flag, String InfoImData)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevinfomainService.save(bean, user, grInfo, qyInfo, approveWorkFlowVO, flag, InfoImData);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "贷前管理-信贷审核-信调审核组-审核";
            SysTools.saveLog(request, msg);
        }
        return result;
    }

    /**
     * Description :信贷-信调审批组-个人信息表单上数据
     * 
     * @param primary key
     * @return WmsCreRevWaterMainVO
     * @author hancd
     */
    @RequestMapping(value = "/loanreview/wmscrerevinfomainbyfkforaddgr.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getInfoByFKForAddgr(Integer wms_cre_credit_head_id,
                                                   Integer wms_cre_credit_line_customer_change_head_id)
    {
        return wmscrerevinfomainService.getInfoByFKForAddgr(wms_cre_credit_head_id,
                                                            wms_cre_credit_line_customer_change_head_id);
    }

    /**
     * Description :信贷-信调审批组-企业信息表单上数据
     * 
     * @param primary key
     * @return WmsCreRevWaterMainVO
     * @author hancd
     */
    @RequestMapping(value = "/loanreview/wmscrerevinfomainbyfkforaddqy.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getInfoByFKForAddqy(Integer wms_cre_credit_head_id,
                                                   Integer wms_cre_credit_line_customer_change_head_id)
    {
        return wmscrerevinfomainService.getInfoByFKForAddqy(wms_cre_credit_head_id,
                                                            wms_cre_credit_line_customer_change_head_id);
    }

    /**
     * 房贷信息数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanreview/teamwithoutpaginglistforfdxx.do", method = { RequestMethod.GET,
                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getTeamListWithoutPagingForFdXx(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                               HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getTeamListWithoutPagingForFdForAdd(queryInfo, user, "9");
    }

    /**
     * Description :信贷信调退件
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author hancd
     */
    @RequestMapping(value = "/loanreview/creditInfotoback.do", method = { RequestMethod.POST })
    @ResponseBody
    public String creditToBack(HttpServletRequest request, WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevinfomainService.infoToBack(approveWorkFlowVO, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "贷前管理-信贷审核-信调审核组-退件";
            SysTools.saveLog(request, msg); // 保存日志
        }
        return result;
    }

    /**
     * Description :房贷信调退件
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author hancd
     */
    @RequestMapping(value = "/loanreview/houseInfotoback.do", method = { RequestMethod.POST })
    @ResponseBody
    public String houseInfoToBack(HttpServletRequest request, WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevinfomainService.houseInfoToBack(approveHouseWorkFlowVO, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "贷前管理-房贷审核-信调审核组-退件";
            SysTools.saveLog(request, msg); // 保存日志
        }
        return result;
    }
}