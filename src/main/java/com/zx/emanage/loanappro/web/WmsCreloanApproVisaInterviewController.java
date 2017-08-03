package com.zx.emanage.loanappro.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.cremanage.service.IWmsCreditWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanappro.service.WmsCreloanApproVisaInterviewService;
import com.zx.emanage.loanappro.vo.WmsCreCreditHeadVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreCreditFinalAppl;
import com.zx.emanage.workflow.service.IWorkflowService;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0  终审审批---终审面签--面签页面 请求的controller
 *               终审审批 ---审批  页面请求   的controller
 * @author
 */

@Controller
public class WmsCreloanApproVisaInterviewController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreloanApproVisaInterviewController.class);

    @Autowired
    private IWorkflowService workflowService;// 设置继续审批的流程变量

    @Autowired
    private WmsCreloanApproVisaInterviewService wmscreloanapprovisainterviewService;// 终审
                                                                                    // 面签的service

    @Autowired
    private IWmsCreditWorkFlowService creditWorkFlowService;// 信贷流程service

    /**
     * Description :add method
     * 
     * @param bean 终审
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loanappro/wmscreapproappealInterviewsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCreditHeadVO bean, WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscreloanapprovisainterviewService.save(bean, user, approveWorkFlowVO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }// record log
        if ("success".equals(result))
        {
            String msg = "终审管理-终审审批-审批";
            SysTools.saveLog(request, msg); // record log method
        }

        return result;
    }

    /**
     * Description :实现面前审批
     * 
     * @param bean 页面传递参数的VO
     * @param approveWorkFlowVO 信贷流程VO
     * @param fileArrs 面签环节上传的附件信息
     * @return "success" or "error"
     * @author hancd
     */
    @RequestMapping(value = "/loanappro/wmscreapproappealInterview.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSaveInterview(HttpServletRequest request, WmsCreCreditHeadVO bean,
                                  WmsCreditWorkFlowVO approveWorkFlowVO, String fileArrs)
    {
        String result = "";
        HttpSession session = request.getSession();
        // 获取当前登录用户
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            // 调用saveInterview实现数据存储
            result = wmscreloanapprovisainterviewService.saveInterview(bean, user, approveWorkFlowVO, fileArrs);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 如果处理成功，把信息记录到日志
        if ("success".equals(result))
        {
            String msg = "终审管理-终审面签-面签";
            SysTools.saveLog(request, msg); // record log method
        }
        // 返回处理的结果
        return result;
    }

    /**
     * Description :add method
     * 
     * @param bean 判断数据表单是新流程还是旧流程
     * @return "success" or "error" or user defined
     * @author hancd
     */
    @RequestMapping(value = "/loanappro/wmscreapproappealInterviewboolean.do", method = { RequestMethod.POST })
    @ResponseBody
    public String InterViewBoolean(WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        String result = "";
        try
        {
            result = creditWorkFlowService.BooleanCheckloanApproInterView(approveWorkFlowVO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        return result;
    }

    /**
     * 
     * @Title: doSaveforHouse
     * @Description: TODO(终审审批保存-房贷)
     * @param request
     * @param bean
     * @param approveHouseWorkFlowVO
     * @param beanVo
     * @return 
     * @author: baisong
     * @time:2017年2月21日 下午2:21:45
     * history:
     * 1、2017年2月21日 baisong 创建方法
     */
    @RequestMapping(value = "/loanappro/wmscreapproappealforHousesave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSaveforHouse(HttpServletRequest request, WmsCreCreditFinalAppl bean, WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, WmsCreCreditHeadVO beanVo)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscreloanapprovisainterviewService.saveforhouse(bean, user, approveHouseWorkFlowVO, beanVo);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }// record log
        if ("success".equals(result))
        {
            String msg = "终审管理-房产终审-审批";
            SysTools.saveLog(request, msg); // record log method
        }

        return result;
    }
    /**
     * Description :add method
     * 
     * @param bean 终审---车
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @RequestMapping(value = "/loanappro/wmscreapproappealforCarsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSaveforcar(HttpServletRequest request, WmsCreCreditHeadVO bean,
    		WmsCarLoanWorkFlowVO wVo)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscreloanapprovisainterviewService.saveforcar(bean, user, wVo);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }// record log
        if ("success".equals(result))
        {
            String msg = "终审管理-房产终审-审批";
            SysTools.saveLog(request, msg); // record log method
        }

        return result;
    }

}