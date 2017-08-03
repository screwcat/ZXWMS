package com.zx.emanage.loanappro.web;

/**
 * 版权所有：版权所有(C) 2014，卓信财富
 * 文件名称: 
 * 系统名称：
 * 模块名称：
 * 完成日期：
 * 作    者：
 * 内容摘要：  
 * 
 * 文件调用：
 * 修改记录：001
 * 修改时间：2015-01-06 12:32
 * 修 改 人: hancd
 * 修改内容：增加信贷终审合同签订实现单据作废操作
 * 关联BUG：
 * 修改方法：
 * 
 * 文件调用：
 * 修改记录：002
 * 修改时间：2015-01-28 13:06
 * 修 改 人: hancd
 * 修改内容：增加信贷终审>初审面签列表页面的显示,导出,审批功能
 * 关联BUG：
 * 修改方法：
 */
import java.util.HashMap;
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

import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanappro.service.IWmsCreCreditApproModelService;
import com.zx.emanage.loanappro.service.IWmsCreCreditApproService;
import com.zx.emanage.loanappro.vo.WmsCreApproSearchBeanVO;
import com.zx.emanage.loanfina.vo.WmsFinaCreCancelBeanVo;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsFinaCreLoanApp;
import com.zx.emanage.util.gen.vo.WmsCreCreditHeadVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

@Controller
public class WmsCreCreditApproController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditApproController.class);

    @Autowired
    private IWmsCreCreditApproService wmscrecreditapproService;

    @Autowired
    private IWmsCreCreditApproModelService wmscrecreditappromodelService;

    /**
     * 终审审批数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getLoanApproApproveWithoutPagingList.do", method = { RequestMethod.GET,
                                                                                            RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getLoanApproApproveListWithoutPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                    HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getLoanApproApproveListWithoutPaging(queryInfo, user);
    }

    /**
     * 终审审批列表分页
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getLoanApproApproveWithPagingList.do", method = { RequestMethod.GET,
                                                                                         RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getLoanApproApproveListWithPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                 HttpServletRequest request)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getLoanApproApproveListWithPaging(queryInfo, user);
    }

    /**
     * 终审面签信息导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getLoanApproInterViewWithoutPagingList.do", method = { RequestMethod.GET,
                                                                                              RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getLoanApproInterViewListWithoutPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                      HttpServletRequest request)
    {
        // 获取当前登录人
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getLoanApproInterViewListWithoutPaging(queryInfo, user);
    }

    /**
     * 终审面签信息列表
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getLoanApproInterViewWithPagingList.do", method = { RequestMethod.GET,
                                                                                           RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getLoanApproInterViewListWithPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                   HttpServletRequest request)
    {
        // 获取当前登录人
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getLoanApproInterViewListWithPaging(queryInfo, user);
    }

    /**
     * 合同签订信息导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getAgreePrintLoanListWithoutPagingList.do", method = { RequestMethod.GET,
                                                                                              RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getAgreePrintLoanListWithoutPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                  HttpServletRequest request)
    {
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getAgreePrintLoanListWithoutPaging(queryInfo, user);
    }

    /**
     * 合同签订信息列表
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getAgreePrintLoanListWithPagingList.do", method = { RequestMethod.GET,
                                                                                           RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getAgreePrintLoanListWithPaging(WmsCreApproSearchBeanVO queryInfo,
                                                               HttpServletRequest request)
    {
        // 获取当前登录人
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getAgreePrintLoanListWithPaging(queryInfo, user);
    }

    /**
     * 审批查询信息导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getLoanApproSearchListWithoutPagingList.do", method = { RequestMethod.GET,
                                                                                               RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getLoanApproSearchListWithoutPaging(HttpServletRequest request, WmsCreApproSearchBeanVO queryInfo)
    {
        // 获取当前登录人
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getLoanApproSearchListWithoutPaging(queryInfo, user);
    }

    /**
     * 审批查询信息列表分页
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getLoanApproSearchListWithPagingList.do", method = { RequestMethod.GET,
                                                                                            RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getLoanApproSearchListWithPaging(HttpServletRequest request, WmsCreApproSearchBeanVO queryInfo)
    {
        // 获取当前登录人
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getLoanApproSearchListWithPaging(queryInfo, user);
    }

    /**
     * 信贷放款申请数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getMakeLoansListWithoutPagingList.do", method = { RequestMethod.GET,
                                                                                         RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getMakeLoansListWithoutPaging(HttpServletRequest request,
                                                             WmsCreApproSearchBeanVO queryInfo)
    {
        // 获取当前登录人
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getMakeloansListWithoutPaging(queryInfo, user);
    }

    /**
     * 信贷放款申请数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getMakeLoanApprovalListWithoutPagingList.do", method = { RequestMethod.GET,
                                                                                                RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getMakeLoanApprovalListWithoutPaging(HttpServletRequest request,
                                                                    WmsCreApproSearchBeanVO queryInfo)
    {
        // 获取当前登录人
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getMakeloanApprovalListWithoutPaging(queryInfo, user);
    }

    /**
     * 信贷放款申请信息列表
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getMakeLoanListWithPagingList.do", method = { RequestMethod.GET,
                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getMakeLoanListWithPaging(HttpServletRequest request, WmsCreApproSearchBeanVO queryInfo)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getMakeLoansListWithPaging(queryInfo, user);
    }

    /**
     * 
     * @Title: getHouseLoanApproApproveListWithPaging
     * @Description: TODO(终审审批房贷终审列表)
     * @param queryInfo
     * @param request
     * @return 
     * @author: baisong
     * @time:2017年2月21日 下午1:26:57
     * history:
     * 1、2017年2月21日 baisong 创建方法
     */
    @RequestMapping(value = "/loanappro/getHouseLoanApproApproveWithPagingList.do", method = { RequestMethod.GET,
                                                                                              RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getHouseLoanApproApproveListWithPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                      HttpServletRequest request)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getHouseLoanApproApproveListWithPaging(queryInfo, user);
    }

    /**
     * 终审审批列表分页--房产模块--房产终审--数据导出
     * 
     * @param queryInfo
     * @return baisong
     */
    @RequestMapping(value = "/loanappro/getHouseLoanApproApproveWithoutPagingList.do", method = { RequestMethod.GET,
                                                                                                 RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getHouseLoanApproApproveListWithoutPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                         HttpServletRequest request)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getHouseLoanApproApproveListWithoutPaging(queryInfo, user);
    }

    /**
     * 
     * @Title: getHouseLoanVisaApproveListWithPaging
     * @Description: TODO(终审面签房贷终审列表)
     * @param queryInfo
     * @param request
     * @return 
     * @author: baisong
     * @time:2017年2月21日 下午1:26:57
     * history:
     * 1、2017年2月21日 baisong 创建方法
     */
    @RequestMapping(value = "/loanappro/getHouseLoanVisaApproveWithPagingList.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getHouseLoanVisaApproveListWithPaging(WmsCreApproSearchBeanVO queryInfo, HttpServletRequest request)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getHouseLoanApproVisaListWithPaging(queryInfo, user);
    }

    /**
     * 终审审批列表分页--房产模块--签合同
     * 
     * @param queryInfo
     * @return baisong
     */
    @RequestMapping(value = "/loanappro/getAgreeLoanApproApproveWithPagingList.do", method = { RequestMethod.GET,
                                                                                              RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getAgreeLoanApproApproveListWithPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                      HttpServletRequest request)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getAgreeLoanApproApproveListWithPaging(queryInfo, user);
    }
    /**
     * 终审审批列表分页--房产模块--补充合同
     * 
     * @param queryInfo
     * @return jiaodelong
     */
    @RequestMapping(value = "/loanappro/getAgreeLoanApproApproveList.do", method = { RequestMethod.GET,
                                                                                              RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getAgreeLoanApproApproveList(WmsCreApproSearchBeanVO queryInfo,
                                                                      HttpServletRequest request)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getAgreeLoanApproApproveList(queryInfo, user);
    }

    /**
     * 终审审批列表分页--房产模块--签合同--数据导出
     * 
     * @return baisong
     */
    @RequestMapping(value = "/loanappro/getAgreeLoanListWithoutPagingList.do", method = { RequestMethod.GET,
                                                                                         RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getAgreeLoanListWithoutPaging(WmsCreApproSearchBeanVO queryInfo,
                                                             HttpServletRequest request)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getAgreeLoanListWithoutPaging(queryInfo, user);
    }
    
  /**
   * 
   * @Title: getAgreeLoanListWithoutPaging
   * @Description: TODO(合同补充导出)
   * @param queryInfo
   * @param request
   * @return 
   * @author: jiaodelong
   * @time:2016年12月20日 下午2:44:23
   * history:
   * 1、2016年12月20日 jiaodelong 创建方法
   */
    @RequestMapping(value = "/loanappro/getSupplementAgreeLoanListWithoutPagingList.do", method = { RequestMethod.GET,
                                                                                         RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getSupplementAgreeLoanListWithoutPaging(WmsCreApproSearchBeanVO queryInfo,
                                                             HttpServletRequest request)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getSupplementAgreeLoanListWithoutPaging(queryInfo, user);
    }

    /**
     * 公证数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getNotarizationWithoutPagingList.do", method = { RequestMethod.GET,
                                                                                        RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getNotarizationWithoutPaging(WmsCreApproSearchBeanVO queryInfo,
                                                            HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getNotarizationListWithoutPaging(queryInfo, user);
    }

    /**
     * 公证列表分页
     * 
     * @param queryInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/loanappro/getNotarizationWithPagingList.do", method = { RequestMethod.GET,
                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getNotarizationWithPaging(WmsCreApproSearchBeanVO queryInfo, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getNotarizationListWithPaging(queryInfo, user);
    }

    /**
     * 他项数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getOthersWithoutPagingList.do", method = { RequestMethod.GET,
                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getOthersWithoutPaging(WmsCreApproSearchBeanVO queryInfo, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getOthersListWithoutPaging(queryInfo, user);
    }

    /**
     * 他项列表分页
     * 
     * @param queryInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/loanappro/getOthersWithPagingList.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getOthersWithPaging(WmsCreApproSearchBeanVO queryInfo, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getOthersListWithPaging(queryInfo, user);

    }

    /**
     * 房贷放款申请数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getHousingMakeLoansListWithoutPagingList.do", method = { RequestMethod.GET,
                                                                                                RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getHousingMakeLoansListWithoutPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                    HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getHousingMakeloansListWithoutPaging(queryInfo, user);
    }
    /**
     * 车贷放款申请数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getCarMakeLoansListWithoutPagingList.do", method = { RequestMethod.GET,
                                                                                                RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCarMakeLoansListWithoutPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                    HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getCarMakeloansListWithoutPaging(queryInfo, user);
    }

    /**
     * 房贷放款审批数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getHouseMakeLoanApprovalListWithoutPaging.do", method = {
                                                                                                     RequestMethod.GET,
                                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getHouseMakeLoanApprovalListWithoutPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                         HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getHouseMakeloanApprovalListWithoutPaging(queryInfo, user);
    }
    /**
     * 车贷放款审批数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getCarMakeLoanApprovalListWithoutPagingList.do", method = {
                                                                                                     RequestMethod.GET,
                                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCarMakeLoanApprovalListWithoutPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                         HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getCarMakeloanApprovalListWithoutPaging(queryInfo, user);
    }
    /**
     * 房贷放款申请信息列表
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getHousingMakeLoanListWithPagingList.do", method = { RequestMethod.GET,
                                                                                            RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getHousingMakeLoanListWithPaging(HttpServletRequest request,
                                                                WmsCreApproSearchBeanVO queryInfo)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getHousingMakeLoansListWithPaging(queryInfo, user);
    }
    /**
     * 车贷放款申请信息列表
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getCarMakeLoanListWithPagingList.do", method = { RequestMethod.GET,
                                                                                            RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCarMakeLoanListWithPaging(HttpServletRequest request,
                                                                WmsCreApproSearchBeanVO queryInfo)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getCarMakeLoansListWithPaging(queryInfo, user);
    }
    /**
     * 房贷放款申请信息列表
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getwmsfinacreloanappbyentity.do", method = { RequestMethod.GET,
                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getWmsFinaCreLoanAppByEntity(WmsFinaCreLoanApp entity)
    {
        return wmscrecreditapproService.getWmsFinaCreLoanAppByEntity(entity);
    }

    /**
     * 信贷放款审批信息列表
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getMakeLoanApprovalListWithPagingList.do", method = { RequestMethod.GET,
                                                                                             RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getMakeLoanApprovalListWithPaging(HttpServletRequest request,
                                                                 WmsCreApproSearchBeanVO queryInfo)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getMakeLoanApprovalListWithPaging(queryInfo, user);
    }

    /**
     * Description :信贷放款审批提交审批
     * 
     * @param
     * @return "success" or "error" or user defined
     * @author wangyishun
     */
    @RequestMapping(value = "/loanappro/makeloanapprovalsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String makeLoanApprovalSave(HttpServletRequest request, WmsCreditWorkFlowVO approveWorkFlowVO)
    {

        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        approveWorkFlowVO.setUser_id(user.getUserId());
        try
        {
            result = wmscrecreditapproService.makeLoanApprovalSave(approveWorkFlowVO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "终审管理-信贷终审-放款审批-审批";
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }

    /**
     * Description :信贷放款审批提交审批
     * 
     * @param
     * @return "success" or "error" or user defined
     * @author wangyishun
     */
    @RequestMapping(value = "/loanappro/makeloanapprovalsaveCancel.do", method = { RequestMethod.POST,RequestMethod.GET  })
    @ResponseBody
    public String makeLoanApprovalSaveCancel(HttpServletRequest request, WmsCreditWorkFlowVO approveWorkFlowVO, WmsFinaCreCancelBeanVo bean, WmsCreCreditHead hbean)
    {

        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        approveWorkFlowVO.setUser_id(user.getUserId());
        try
        {
            result = wmscrecreditapproService.makeLoanApprovalSaveCancel(approveWorkFlowVO,bean,hbean,user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "终审管理-信贷终审-放款审批-合同作废";
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }
    /**
     * Description :房贷放款审批提交审批
     * 
     * @param
     * @return "success" or "error" or user defined
     * @author wangyishun
     */
    @RequestMapping(value = "/loanappro/makeloanapprovalsaveforfd.do", method = { RequestMethod.POST })
    @ResponseBody
    public String makeLoanApprovalSaveForFd(HttpServletRequest request, WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        approveHouseWorkFlowVO.setUserId(user.getUserId().toString());
        try
        {
            result = wmscrecreditapproService.makeLoanApprovalSaveForFd(approveHouseWorkFlowVO, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "终审管理-房贷终审-放款审批-审批";
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }
    /**
     * Description :车贷放款审批提交审批
     * 
     * @param
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @RequestMapping(value = "/loanappro/makeloanapprovalsaveforcar.do", method = { RequestMethod.POST })
    @ResponseBody
    public String makeLoanApprovalSaveForCar(HttpServletRequest request, WmsCarLoanWorkFlowVO wVo)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        wVo.setUserId(user.getUserId().toString());
        try
        {
            result = wmscrecreditapproService.makeLoanApprovalSaveForCar(wVo);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "终审管理-车贷终审-放款审批-审批";
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }

    /**
     * 房贷放款审批信息列表
     * 
     * @param queryInfo
     * @return
     * @author wangyishun
     */
    @RequestMapping(value = "/loanappro/getHouseMakeLoanApprovalListWithPagingList.do", method = { RequestMethod.GET,
                                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getHouseMakeLoanApprovalListWithPaging(HttpServletRequest request,
                                                                      WmsCreApproSearchBeanVO queryInfo)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getHouseMakeLoanApprovalListWithPaging(queryInfo, user);
    }
    /**
     * 车贷放款审批信息列表
     * 
     * @param queryInfo
     * @return
     * @author wangyishun
     */
    @RequestMapping(value = "/loanappro/getCarMakeLoanApprovalListWithPagingList.do", method = { RequestMethod.GET,
                                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCarMakeLoanApprovalListWithPaging(HttpServletRequest request,
                                                                      WmsCreApproSearchBeanVO queryInfo)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getCarMakeLoanApprovalListWithPaging(queryInfo, user);
    }

    /**
     * 测试模型计算结果
     * 
     * @param queryInfo
     * @return
     * @author 张风山
     */
    @RequestMapping(value = "/loanappro/modelTest.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> ModelTest(HttpServletRequest request, String wms_cre_credit_head_id)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        wmscrecreditappromodelService.getModelData(Integer.parseInt(wms_cre_credit_head_id));
        return null;
    }

    /**
     * 终审《信贷》复议申请列表显示 主要实现：查询符合复议要求的单据进行显示
     * 
     * @param queryInfo
     * @return
     * @author hancd
     */
    @RequestMapping(value = "/loanappro/getLoanReviewRevisionWithPagingList.do", method = { RequestMethod.GET,
                                                                                           RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getLoanReviewRevisionWithPagingList(WmsCreApproSearchBeanVO queryInfo,
                                                                   HttpServletRequest request)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getLoanReviewRevisionWithPagingList(queryInfo, user);
    }

    /**
     * 终审《信贷》复议申请列表数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/getLoanReviewRevisionWithOutPagingList.do", method = { RequestMethod.GET,
                                                                                              RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getLoanReviewRevisionWithOutPagingList(WmsCreApproSearchBeanVO queryInfo,
                                                                      HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getLoanReviewRevisionWithOutPagingList(queryInfo, user);
    }

    /**
     * 终审《信贷》复议申请提交
     * 
     * @param queryInfo
     * @param aWorkFlowVO 信贷流程公共类vo
     * @return
     */
    @RequestMapping(value = "/loanappro/toWmsReconsideration.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String toWmsReconsideration(WmsCreApproSearchBeanVO queryInfo, HttpServletRequest request,
                                       WmsCreditWorkFlowVO aWorkFlowVO)
    {
        String result = "";
        // 获取当前登录人信息
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditapproService.toWmsReconsideration(queryInfo, user, aWorkFlowVO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 如果成功,记录相应日志
        if ("success".equals(result))
        {
            String msg = "终审管理-信贷管理-复议申请";
            SysTools.saveLog(request, msg); // record log method
        }
        // 返回处理结果结果
        return result;
    }

    /**
     * 修改记录:001 实现合同签订单据作废操作
     * 
     * @param request
     * @param approveWorkFlowVO
     * @author hancd
     */
    @RequestMapping(value = "/loanappro/toInvalid.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String toInvalid(HttpServletRequest request, WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        String result = "success";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        approveWorkFlowVO.setUser_id(user.getUserId());
        try
        {
            result = wmscrecreditapproService.creCheckConcludeAndSignApprove(user,approveWorkFlowVO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        if ("success".equals(result))
        {
            String msg = "终审管理-信贷终审-合同签订-单据作废"; // record log
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }

    /**
     * 修改记录:002 实现初审面签页面列表的显示功能
     * 
     * @param request
     * @param queryInfo
     * @author hancd
     */
    @RequestMapping(value = "/loanappro/getLoanApproInitialInterViewWithPagingList.do", method = { RequestMethod.GET,
                                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getLoanApproInitialInterViewWithPagingList(HttpServletRequest request,
                                                                          WmsCreApproSearchBeanVO queryInfo)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getLoanApproInitialInterViewWithPagingList(queryInfo, user);
    }

    /**
     * 修改记录：002 实现初审面签页面列表信息的导出功能
     */
    @RequestMapping(value = "/loanappro/getLoanApproInitialInterViewWithoutPagingList.do", method = {
                                                                                                     RequestMethod.GET,
                                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getLoanApproInitialInterViewWithoutPagingList(HttpServletRequest request,
                                                                             WmsCreApproSearchBeanVO queryInfo)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getLoanApproInitialInterViewWithoutPagingList(queryInfo, user);
    }

    /**
     * 修改记录:002 实现处理初审面签审批意见的功能
     * 
     * @param request
     * @param approveWorkFlowVO
     * @author hancd
     */
    @RequestMapping(value = " /loanappro/loanApproInitialInter.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String loanApproInitialInter(HttpServletRequest request, WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditapproService.loanApproInitialInter(approveWorkFlowVO, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        if ("success".equals(result))
        {
            String msg = "终审管理-信贷终审-初审面签审核";
            SysTools.saveLog(request, msg);
        }
        return result;
    }
    
    /**
     * 终审审批列表分页--车模块--车终审
     * 
     * @param queryInfo
     * @return baisong
     */
    @RequestMapping(value = "/loanappro/getCarLoanApproApproveWithPagingList.do", method = { RequestMethod.GET,
                                                                                              RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCarLoanApproApproveListWithPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                      HttpServletRequest request)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getCarLoanApproApproveListWithPaging(queryInfo, user);
    }
    /**
     * 终审审批列表分页--车模块--车终审--导出excel
     * 
     * @param queryInfo
     * @return baisong
     */
    @RequestMapping(value = "/loanappro/getCarLoanApproApproveWithoutPagingList.do", method = { RequestMethod.GET,
                                                                                              RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCarLoanApproApproveListWithoutPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                      HttpServletRequest request)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getCarLoanApproApproveListWithoutPaging(queryInfo, user);
    }
    
    
    /**
     * 终审审批列表分页--车贷模块--签合同
     * 
     * @param queryInfo
     * @return baisong
     */
    @RequestMapping(value = "/loanappro/getAgreeLoanApproApproveWithPagingListCar.do", method = { RequestMethod.GET,
                                                                                              RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getAgreeLoanApproApproveListWithPagingCar(WmsCreApproSearchBeanVO queryInfo,
                                                                      HttpServletRequest request)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getAgreeLoanApproApproveListWithPagingCar(queryInfo, user);
    }
    /**
     * 终审审批列表分页--车贷模块--签合同--数据导出
     * 
     * @return baisong
     */
    @RequestMapping(value = "/loanappro/getAgreeLoanListWithoutPagingListcar.do", method = { RequestMethod.GET,
                                                                                         RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getAgreeLoanListWithoutPagingCar(WmsCreApproSearchBeanVO queryInfo,
                                                             HttpServletRequest request)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getAgreeLoanListWithoutPagingCar(queryInfo, user);
    }
    
    
    
    /**
     * WMS对MOA接口，放款审批
     * 
     * @return jiaodelong
     */
    @RequestMapping(value = "/wms/sendResultsLoanApprovalUp.do", method = { RequestMethod.GET,RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> sendResultsLoanApprovalUp(String pass,String advice,Integer wms_cre_credit_head_id,Integer userId,
    														 String userName,
                                                             HttpServletRequest request)
    {
    	Map<String, Object> resMap = new HashMap<String, Object>();
        try
        {
        	wmscrecreditapproService.sendResultsLoanApprovalUp(pass,advice,wms_cre_credit_head_id,userId,userName); 
        	resMap.put("result", "success");
        	resMap.put("flag", true);
    		resMap.put("message","请求成功！");
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        	resMap.put("result", "error");
        	resMap.put("flag", false);
    		resMap.put("message", e.getMessage());
        }
        return resMap;
    }
    

    /**
     * 复议申请列表
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/reviewRevisionOfHousingList.do", method = { RequestMethod.GET,
                                                                                            RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> reviewRevisionOfHousingList(WmsCreCreditHeadVO queryInfo)
    {
        return wmscrecreditapproService.reviewRevisionOfHousingList(queryInfo);
    }
    
    
    /**
     * 审批查询信息列表导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/reviewRevisionOfHousingListOut.do", method = { RequestMethod.GET,
                                                                                            RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> reviewRevisionOfHousingListOut(WmsCreCreditHeadVO queryInfo)
    {
        return wmscrecreditapproService.reviewRevisionOfHousingListOut(queryInfo);
    } 
    
    /**
     * 审批查询信息列表导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loanappro/returnNull.do", method = { RequestMethod.GET,RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> returnNull()
    {
        return null;
    } 
    
    
  /**
   * 
   * @Title: getcombinedLoanList
   * @Description: TODO(组合贷获取列表信息)
   * @param queryInfo
   * @param request
   * @return  Map<String, Object>
   * @author: jiaodelong
   * @time:2016年12月27日 下午1:58:13
   * history:
   * 1、2016年12月27日 jiaodelong 创建方法
   */
    @RequestMapping(value = "/loanappro/getcombinedLoanList.do", method = { RequestMethod.GET,RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getcombinedLoanList(WmsCreApproSearchBeanVO queryInfo,
                                                                      HttpServletRequest request)
    {
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditapproService.getcombinedLoanList(queryInfo, user);
    }
    
    /**
     * 
     * @Title: getcombinedLoanList
     * @Description: TODO(组合贷获取列表信息不含分页)
     * @param queryInfo
     * @param request
     * @return  Map<String, Object>
     * @author: jiaodelong
     * @time:2016年12月27日 下午1:58:13
     * history:
     * 1、2016年12月27日 jiaodelong 创建方法
     */
      @RequestMapping(value = "/loanappro/getcombinedLoanListAll.do", method = { RequestMethod.GET,RequestMethod.POST })
      @ResponseBody
      public Map<String, Object> getcombinedLoanListAll(WmsCreApproSearchBeanVO queryInfo,
                                                                        HttpServletRequest request)
      {
          // 获取当前登录人
          HttpSession session = request.getSession();
          UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
          return wmscrecreditapproService.getcombinedLoanListAll(queryInfo, user);
      }
    /**
     * 
     * @Title: getcombinedLoanList
     * @Description: TODO(根据身份证号组合贷获取列表信息)
     * @param queryInfo
     * @param request
     * @return  Map<String, Object>
     * @author: jiaodelong
     * @time:2016年12月27日 下午1:58:13
     * history:
     * 1、2016年12月27日 jiaodelong 创建方法
     */
      @RequestMapping(value = "/loanappro/getcombinedLoanListForIdCard.do", method = { RequestMethod.GET,RequestMethod.POST })
      @ResponseBody
      public Map<String, Object> getcombinedLoanListForIdCard(WmsCreApproSearchBeanVO queryInfo,
                                                                        HttpServletRequest request)
      {
          // 获取当前登录人
          HttpSession session = request.getSession();
          UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
          return wmscrecreditapproService.getcombinedLoanListForIdCard(queryInfo, user);
      }

      /**
       * 
       * @Title: sendResultsLoanApprovalUp
       * @Description: TODO(放款审批)
       * @param pass
       * @param advice
       * @param wms_cre_credit_head_id
       * @param userId
       * @param userName
       * @param request
       * @return 
       * @author: jiaodelong
       * @time:2017年1月12日 下午3:21:19
       * history:
       * 1、2017年1月12日 jiaodelong 创建方法
       */
    @RequestMapping(value = "/wms/sendResultsLoanApprovalUpAgain.do", method = { RequestMethod.GET, RequestMethod.POST })
      @ResponseBody
      public Map<String, Object> sendResultsLoanApprovalUpAgain(String pass,String advice,String group_list,Integer userId,
                                                               String userName,
                                                               HttpServletRequest request)
      {
          Map<String, Object> resMap = new HashMap<String, Object>();
          try
          {
              wmscrecreditapproService.sendResultsLoanApprovalUpAgain(pass,advice,group_list,userId,userName); 
              resMap.put("result", "success");
              resMap.put("flag", true);
              resMap.put("message","请求成功！");
          }
          catch (Exception e)
          {
              log.error(e.getMessage());
              resMap.put("result", "error");
              resMap.put("flag", false);
              resMap.put("message", e.getMessage());
          }
          return resMap;
      }
}