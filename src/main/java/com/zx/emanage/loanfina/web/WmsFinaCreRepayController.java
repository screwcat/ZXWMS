package com.zx.emanage.loanfina.web;

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

import com.zx.emanage.loanappro.service.IWmsCreCreditApproService;
import com.zx.emanage.loanappro.vo.WmsCreApproSearchBeanVO;
import com.zx.emanage.loanfina.service.IWmsFinaCreRepayService;
import com.zx.emanage.loanfina.vo.WmsFinaCreAdvanceBeanVO;
import com.zx.emanage.loanfina.vo.WmsFinaCreCancelBeanVo;
import com.zx.emanage.loanfina.vo.WmsFinaCrePeriodRepaySearchBeanVO;
import com.zx.emanage.loanfina.vo.WmsFinaCreRepaySearchBeanVO;
import com.zx.emanage.loanfina.vo.WmsFinaGetInfoStopBeanVO;
import com.zx.emanage.loanpost.service.IWmsPostLoanWorkFlowService;
import com.zx.emanage.loanpost.vo.WmsPostLoanWorkFlowBeanVO;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepay;
import com.zx.emanage.util.gen.entity.WmsFinaTerminationContract;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsFinaCreRepayController
{
    private static Logger log = LoggerFactory.getLogger(WmsFinaCreRepayController.class);

    @Autowired
    private IWmsFinaCreRepayService wmsfinacrerepayService;

    @Autowired
    private IWmsCreCreditApproService wmscrecreditapproService;

    @Autowired
    private IWmsPostLoanWorkFlowService wmsPostLoanWorkFlowService;
    
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanfina/wmsfinacrerepaywithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(HttpServletRequest request, WmsFinaCreRepaySearchBeanVO queryInfo)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsfinacrerepayService.getListWithoutPaging(queryInfo, user);
    }

    /**
     * 用于提前还款确认 查询列表 Description :get record list records by vo queryInfo with
     * paging
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/loanfina/wmsfinacrerepaywithpaginglist.do", method = { RequestMethod.GET,
                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(HttpServletRequest request, WmsFinaCreRepaySearchBeanVO queryInfo)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsfinacrerepayService.getListWithPagingforadv(queryInfo, user);
    }

    /**
     * 提前还款导出excel
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/loanfina/wmsfinacrerepaywithoutpaginglistforadv.do", method = { RequestMethod.GET,
                                                                                             RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPagingforadv(HttpServletRequest request,
                                                          WmsFinaCreRepaySearchBeanVO queryInfo)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsfinacrerepayService.getListWithoutPagingforadv(queryInfo, user);
    }

    /**
     * 逾期查询列表 Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/loanfina/wmsfinacrerepaywithpaginglistforyuqi.do", method = { RequestMethod.GET,
                                                                                           RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPagingforyuqi(WmsFinaCreRepaySearchBeanVO queryInfo)
    {
        return wmsfinacrerepayService.getListWithPagingforyuqi(queryInfo);
    }

    /**
     * 逾期还款导出excel
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/loanfina/wmsfinacrerepaywithoutpaginglistforyuqi.do", method = { RequestMethod.GET,
                                                                                              RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPagingforyuqi(WmsFinaCreRepaySearchBeanVO queryInfo)
    {
        return wmsfinacrerepayService.getListWithoutPagingforyuqi(queryInfo);
    }

    /**
     *  Description :实现正常查询还款确认列表显示
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     */
    @RequestMapping(value = "/loanfina/wmsfinacrerepaywithpaginglistfornormal.do", method = { RequestMethod.GET,
                                                                                             RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPagingfornormal(WmsFinaCreRepaySearchBeanVO queryInfo)
    {
        return wmsfinacrerepayService.getListWithPagingfornormal(queryInfo);
    }

    /**
     * 正常还款导出excel
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/loanfina/wmsfinacrerepaywithoutpaginglistfornormal.do", method = { RequestMethod.GET,
                                                                                                RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPagingfornormal(WmsFinaCreRepaySearchBeanVO queryInfo)
    {
        return wmsfinacrerepayService.getListWithoutPagingfornormal(queryInfo);
    }

    /**
     * 终止合同审批中的打印协议
     * 
     * @param primary key
     * @return WmsFinaCreRepayVO
     * @author baisong
     */
    @RequestMapping(value = "/loanfina/wmsfinacrerepayinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getInfoByPK(Integer wms_fina_cre_pay_id)
    {
        return wmsfinacrerepayService.getInfoByPK(wms_fina_cre_pay_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loanfina/wmsfinacrerepaysave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsFinaCreRepay bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsfinacrerepayService.save(bean, user);
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
    @RequestMapping(value = "/loanfina/wmsfinacrerepayupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsFinaCreRepay bean,WmsFinaTerminationContract tc)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsfinacrerepayService.update_new(bean, user,tc);
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
     * 逾期还款确认获取信息
     * 
     * @param primary key
     * @return WmsCreCreditLineApplicantVO
     * @author baisong
     */
    @RequestMapping(value = "/loanfina/wmsfinacrerepayinfobyfk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsFinaCrePeriodRepaySearchBeanVO getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmsfinacrerepayService.getInfoByFK(wms_cre_credit_head_id);
    }

    /**
     * 正常还款确认获取信息
     * 
     * @param primary key
     * @return WmsCreCreditLineApplicantVO
     * @author baisong
     */
    @RequestMapping(value = "/loanfina/wmsfinacrerepayinfobyfkfornor.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String,Object> getInfoByFKForNor(Integer wms_cre_credit_head_id)
    {
        return wmsfinacrerepayService.getInfoByFKForNor(wms_cre_credit_head_id);
    }

    /**
     * 逾期还款确认 后数据保存 分别保存到期还款台账 和贷款还款信息表
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @RequestMapping(value = "/loanfina/wmsfinacrerepayupdateInfo.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdateInfo(HttpServletRequest request, WmsFinaCrePeriodRepaySearchBeanVO bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsfinacrerepayService.updateInfo(bean, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        // record log
        if ("success".equals(result))
        {
            String msg = "财务管理-还款管理-逾期还款确认";
            SysTools.saveLog(request, msg); // record log method
        }

        return result;
    }

    /**
     * 正常还款确认 后数据保存 分别保存到期还款台账 和贷款还款信息表
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @RequestMapping(value = "/loanfina/wmsfinacrerepayupdateInfofornor.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdateInfoForNor(HttpServletRequest request, WmsFinaCrePeriodRepaySearchBeanVO bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsfinacrerepayService.updateInfoForNor(bean, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        // record log
        if ("success".equals(result))
        {
            String msg = "财务管理-还款管理-正常还款确认";
            SysTools.saveLog(request, msg); // record log method
        }

        return result;
    }

    /**
     * 提前还款确认 后数据保存 分别保存到期还款台账 和贷款还款信息表advanceRepaymentAffirm.html
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @RequestMapping(value = "/loanfina/wmsfinacrerepayupdateInfoforadv.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdateInfoForAdv(HttpServletRequest request, WmsFinaCreAdvanceBeanVO bean,
                                     WmsPostLoanWorkFlowBeanVO wmsPostLoanWorkFlowBeanVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsfinacrerepayService.updateInfoForAdv(bean, user, wmsPostLoanWorkFlowBeanVO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        // record log
        if ("success".equals(result))
        {
            String msg = "财务管理-还款管理-正常还款确认";
            SysTools.saveLog(request, msg); // record log method
        }

        return result;
    }

    /**
     * 放款确认信息导出
     * 
     * @param queryInfo
     * @param request 返回相应信息
     * @return
     */
    @RequestMapping(value = "/loanfina/getPaymentConfirmationListWithoutPaginglist.do", method = { RequestMethod.GET,
                                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getPaymentConfirmationListWithoutPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                       HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getPaymentConfirmationListWithoutPaging(queryInfo, user);
    }

    /**
     * 放款确认信息列表
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanfina/getPaymentConfirmationListWithPaginglist.do", method = { RequestMethod.GET,
                                                                                               RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getPaymentConfirmationListWithPaging(HttpServletRequest request,
                                                                    WmsCreApproSearchBeanVO queryInfo)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getPaymentConfirmationListWithPaging(queryInfo, user);
    }

    /**
     * @param wms_cre_credit_head_id
     * @return map
     */
    @RequestMapping(value = "/loanfina/borrowandloanappbypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getBorrowAndLoanAppByPK(Integer wms_cre_credit_head_id)
    {
        return wmsfinacrerepayService.getBorrowAndLoanAppByPK(wms_cre_credit_head_id);
    }

    /**
     * 实现房贷放款确认功能
     * @param request
     * @param wms_cre_credit_head_id
     * @param taskId
     * @param cre_type
     * @return
     */
    @RequestMapping(value = "/loanfina/periodAndCreRepaySave.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String periodAndCreRepaySave(HttpServletRequest request, Integer wms_cre_credit_head_id, String taskId,
                                        String cre_type, String edition_num)
    {
        String result = "success";
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsfinacrerepayService.periodAndCreRepaySave(user, wms_cre_credit_head_id, taskId, cre_type, edition_num);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return "error";
        }
        if (result.equals("success"))
        {
            String msg = "财务管理-还款管理-确认放款";
            SysTools.saveLog(request, msg);
        }
        return result;
    }
    /**
     * 放款确认-车贷
     * @param request
     * @param wms_cre_credit_head_id
     * @param taskId
     * @param cre_type
     * @return
     * @author baisong
     */
    @RequestMapping(value = "/loanfina/periodAndCreRepaySaveCar.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String periodAndCreRepaySaveCar(HttpServletRequest request, Integer wms_cre_credit_head_id, String taskId,
                                        String cre_type)
    {
        String result = "";
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsfinacrerepayService.periodAndCreRepaySaveCar(user, wms_cre_credit_head_id, taskId, cre_type);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }
        if (result.equals("success"))
        {
            String msg = "财务管理-还款管理-车贷确认放款";
            SysTools.saveLog(request, msg);
        }
        return "success";
    }
    /***
     * 单据作废--信贷
     * @param request
     * @param bean
     * @param hbean
     * @param cre_type
     * @return
     * baisong
     */
    @RequestMapping(value = "/loanfina/periodAndCreRepaySaveCancel.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String periodAndCreRepaySaveCancel(HttpServletRequest request,WmsFinaCreCancelBeanVo bean, WmsCreCreditHead hbean,String cre_type)
    {
        String result = "";
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsfinacrerepayService.periodAndCreRepaySaveCancel(user, bean, hbean, cre_type);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }
        if (result.equals("success"))
        {
            String msg = "财务管理-还款管理-确认放款-合同作废";
            SysTools.saveLog(request, msg);
        }
        return "success";
    }
    /***
     * 单据作废--车贷
     * @param request
     * @param bean
     * @param hbean
     * @param cre_type
     * @return
     * baisong
     */
    @RequestMapping(value = "/loanfina/carPperiodAndCreRepaySaveCancel.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String carPeriodAndCreRepaySaveCancel(HttpServletRequest request,WmsFinaCreCancelBeanVo bean, WmsCreCreditHead hbean)
    {
        String result = "";
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsfinacrerepayService.carPeriodAndCreRepaySaveCancel(user, bean, hbean);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }
        if (result.equals("success"))
        {
            String msg = "财务管理-还款管理-确认放款-合同作废";
            SysTools.saveLog(request, msg);
        }
        return "success";
    }
    /**
     * Description :保存提前还款审核审批信息
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author wangyishun
     */
    @RequestMapping(value = "/loanpost/wmsfinacrerepayapprove.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSaveApprove(HttpServletRequest request, WmsPostLoanWorkFlowBeanVO wmsPostLoanWorkFlowBeanVO)
    {
        String result = "success";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        wmsPostLoanWorkFlowBeanVO.setUser_id(user.getUserId());
        try
        {
            wmsPostLoanWorkFlowService.wmsPostLoanApproveBefore(wmsPostLoanWorkFlowBeanVO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // record log
        String msg = "贷后管理-提前还款审核-审核";
        SysTools.saveLog(request, msg); // record log method
        return result;
    }
    /**
     * 清空数据库贷款还款表中的 贷后专员信息 id  name  deptid
     * @param wms_cre_credit_head_id
     * @return
     * baisong
     */
    @RequestMapping(value = "/loanfina/udpatenull.do", method = { RequestMethod.GET })
    @ResponseBody
    public String udpatenull(Integer wms_fina_cre_pay_id)
    {
        return wmsfinacrerepayService.udpatenull(wms_fina_cre_pay_id);
    }
    /**
     *  新增获取信贷债权详情接口
     * @param paramMap
     * @return
     * baisong
     */
    @RequestMapping(value = "/loanfina/getInfoforPTP.do", method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getInfoforPTP(HttpServletRequest request,String  idList)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsfinacrerepayService.getInfoforPTP(idList,user);
    }
    /**
     * 新增获取房贷债权详情接口
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/loanfina/getInfoforPTPhosue.do", method = { RequestMethod.GET ,RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getInfoforPTPhosue(HttpServletRequest request,String  idList)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsfinacrerepayService.getInfoforPTPhosue(idList,user);
    }
    /**
     * 终止合同审核 列表 
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/loanfina/wmsfinacrerepaywithpaginglistforstop.do", method = { RequestMethod.GET,
                                                                                           RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPagingforstop(WmsFinaGetInfoStopBeanVO queryInfo)
    {
        return wmsfinacrerepayService.getListWithPagingforstop(queryInfo);
    }
    /**
     * 终止合同确认 列表 
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/loanfina/wmsfinacrerepaywithpaginglistforaffirm.do", method = { RequestMethod.GET,
                                                                                           RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPagingforaffirm(WmsFinaGetInfoStopBeanVO queryInfo)
    {
        return wmsfinacrerepayService.getListWithPagingforaffirm(queryInfo);
    }
    /**
     * 终止合同审核 列表 -excel
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/loanfina/wmsfinacrerepaywithpaginglistforstopexcel.do", method = { RequestMethod.GET,
                                                                                           RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPagingforstopexcel(WmsFinaGetInfoStopBeanVO queryInfo)
    {
        return wmsfinacrerepayService.getListWithPagingforstopexcel(queryInfo);
    }
    /**
     * 终止合同确认 列表 -excel
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/loanfina/wmsfinacrerepaywithpaginglistforaffirmexcel.do", method = { RequestMethod.GET,
                                                                                           RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPagingforaffirmexcel(WmsFinaGetInfoStopBeanVO queryInfo)
    {
        return wmsfinacrerepayService.getListWithPagingforaffirmexcel(queryInfo);
    }
    
    /**
     * 终止合同审核 获取页面数据
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/loanfina/getInfoforstop.do", method = { RequestMethod.GET ,RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getInfoforstop(HttpServletRequest request,Integer  wms_cre_credit_head_id)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsfinacrerepayService.getInfoforstop(wms_cre_credit_head_id);
    }
    /**
     * 抵押物清单--抵押物编号
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/loanfina/getFinaDYCode.do", method = { RequestMethod.GET ,RequestMethod.POST})
    @ResponseBody
    public String getFinaDYCode()
    {
        return CodeNoUtil.getFinaDYCode();
    }
    /***
     * 单据作废--房贷
     * @param request
     * @param bean
     * @param hbean
     * @param cre_type
     * @return
     * baisong
     */
    @RequestMapping(value = "/loanfina/houseCancel.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String houseCancel(HttpServletRequest request,WmsFinaCreCancelBeanVo bean, WmsCreCreditHead hbean,String cre_type)
    {
        String result = "";
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsfinacrerepayService.houseCancel(user, bean, hbean, cre_type);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }
        if (result.equals("success"))
        {
            String msg = "财务管理-还款管理-确认放款-合同作废";
            SysTools.saveLog(request, msg);
        }
        return result;
    }

    /***
     * 退回--房贷
     * @param request
     * @param bean
     * @param hbean
     * @param cre_type
     * @return
     * baisong
     */
    @RequestMapping(value = "/loanfina/houseToBack.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String houseToBack(HttpServletRequest request, WmsFinaCreCancelBeanVo bean, WmsCreCreditHead hbean, String cre_type)
    {
        String result = "";
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsfinacrerepayService.houseToBack(user, bean, hbean, cre_type);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }
        if (result.equals("success"))
        {
            String msg = "财务管理-还款管理-确认放款-合同作废";
            SysTools.saveLog(request, msg);
        }
        return result;
    }
}