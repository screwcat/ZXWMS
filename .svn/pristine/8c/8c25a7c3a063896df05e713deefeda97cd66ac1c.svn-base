package com.zx.emanage.loancheck.web;

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

import com.zx.emanage.cremanage.service.IWmsCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.cremanage.vo.WmsCreCreditHeadSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class LoanCheckListController
{
    private static Logger log = LoggerFactory.getLogger(LoanCheckListController.class);

    @Autowired
    private IWmsCreCreditHeadService wmscrecreditheadService;

    @Autowired
    private IWmsCreditWorkFlowService approveWorkFlowService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditheadwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                         RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getListWithoutPaging(queryInfo, user);
    }

    /**
     * 流水、信息、电审、征信数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loancheck/teamwithoutpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getTeamListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                        HttpServletRequest request, String flag_byk)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getTeamListWithoutPaging(queryInfo, user, flag_byk);
    }

    /**
     * 房贷贷前跟踪数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loancheck/teamwithoutpaginglistforfd.do", method = { RequestMethod.GET,
                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getTeamListWithoutPagingForFd(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                             HttpServletRequest request, String flag_byk)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getTeamListWithoutPaging(queryInfo, user, flag_byk);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditheadwithpaginglist.do", method = { RequestMethod.GET,
                                                                                      RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request,
                                                 String flag_byk)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getListWithPaging(queryInfo, user, flag_byk);
    }

    /**
     * 流水审批列表
     * 
     * @param queryInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/loancheck/waterwithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getWaterListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                      HttpServletRequest request, String flag_byk)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getListWithPaging(queryInfo, user, flag_byk);
    }

    /**
     * 信息审批列表
     * 
     * @param queryInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/loancheck/infowithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getInfoListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                     HttpServletRequest request, String flag_byk)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getListWithPaging(queryInfo, user, flag_byk);
    }

    /**
     * 电审审批列表
     * 
     * @param queryInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/loancheck/phonewithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getPhoneListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                      HttpServletRequest request, String flag_byk)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getListWithPaging(queryInfo, user, flag_byk);
    }

    /**
     * 房贷电审审批列表
     * 
     * @param queryInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/loancheck/housePhonewithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getHousePhoneListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                           HttpServletRequest request, String flag_byk)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getListWithPagingForFdForAdd(queryInfo, user, "10");
    }

    /**
     * 征信审批列表
     * 
     * @param queryInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/loancheck/certificatewithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCertificateListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                            HttpServletRequest request, String flag_byk)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getListWithPaging(queryInfo, user, flag_byk);
    }

    /**
     * 验点审批列表
     * 
     * @param queryInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/loancheck/creydwithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getYdListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request,
                                                   String flag_byk)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getListWithPaging(queryInfo, user, flag_byk);
    }

    /**
     * 房贷贷前跟踪列表
     * 
     * @param queryInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/loancheck/certificatewithpaginglistforfd.do", method = { RequestMethod.GET,
                                                                                      RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCertificateListWithPagingForFd(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                                 HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getListWithPagingForFd(queryInfo, user);
    }
    /**
     * 车贷贷前跟踪列表
     * 
     * @param queryInfo
     * @param request
     * @return
     * @author baisong
     */
    @RequestMapping(value = "/loancheck/certificatewithpaginglistforcar.do", method = { RequestMethod.GET,
                                                                                      RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCertificateListWithPagingForCar(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                                 HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getListWithPagingForCar(queryInfo, user);
    }
    /**
     * 审批查询列表分页
     * 
     * @param queryInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/loancheck/creditCheckWithPaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCreditCheckListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                            HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getCreditCheckListWithPaging(queryInfo, user);
    }

    /**
     * 审批查询数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loancheck/creditCheckWithoutPaginglist.do", method = { RequestMethod.GET,
                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCreditCheckListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo)
    {

        return wmscrecreditheadService.getCreditCheckListWithoutPaging(queryInfo);
    }

    /**
     * 房贷贷前跟踪数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loancheck/followCheckWithoutPaginglistForFd.do", method = { RequestMethod.GET,
                                                                                         RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getFollowCheckListWithoutPagingForFd(WmsCreCreditHeadSearchBeanVO queryInfo)
    {

        return wmscrecreditheadService.getFollowCheckListWithoutPagingForFd(queryInfo);
    }
    /**
     * 车贷贷前跟踪数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loancheck/followCheckWithoutPaginglistForCar.do", method = { RequestMethod.GET,
                                                                                         RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getFollowCheckListWithoutPagingForCar(WmsCreCreditHeadSearchBeanVO queryInfo)
    {

        return wmscrecreditheadService.getFollowCheckListWithoutPagingForCar(queryInfo);
    }
    /**
     * 贷前跟踪数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loancheck/followCheckWithoutPaginglist.do", method = { RequestMethod.GET,
                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getFollowCheckListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo)
    {

        return wmscrecreditheadService.getFollowCheckListWithoutPaging(queryInfo);
    }

    /**
     * 贷前跟踪列表
     * 
     * @param queryInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/loancheck/followCheckWithPaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getFollowCheckListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                            HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getFollowCheckListWithPaging(queryInfo, user);
    }

    /**
     * 贷前查询
     * 
     * @param queryInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/loancheck/loanCheckWithPaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getLoanCheckListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                          HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getLoanCheckListWithPaging(queryInfo, user);
    }

    /**
     * 贷前查询数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loancheck/loanCheckWithoutPaginglist.do", method = { RequestMethod.GET,
                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getLoanCheckListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo)
    {
        return wmscrecreditheadService.getLoanCheckListWithoutPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditHeadVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditheadinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCreditHead getInfoByPK(Integer wms_cre_credit_head_id)
    {
        return wmscrecreditheadService.getInfoByPK(wms_cre_credit_head_id);
    }

    /**
     * 房贷办件数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loancheck/teamwithoutpaginglistforfdbj.do", method = { RequestMethod.GET,
                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getTeamListWithoutPagingForFdBj(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                               HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getCardListWithoutPagingForFdForAdd(queryInfo, user, "2");
    }

    /**
     * 房贷流水数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loancheck/teamwithoutpaginglistforfdls.do", method = { RequestMethod.GET,
                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getTeamListWithoutPagingForFdLs(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                               HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getTeamListWithoutPagingForFdForAdd(queryInfo, user, "1");
    }

    /**
     * 房贷证信数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loancheck/teamwithoutpaginglistforfdzx.do", method = { RequestMethod.GET,
                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getTeamListWithoutPagingForFdZx(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                               HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getTeamListWithoutPagingForFdForAdd(queryInfo, user, "8");
    }

    /**
     * 房贷信息数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loancheck/teamwithoutpaginglistforfdxx.do", method = { RequestMethod.GET,
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
     * 房贷电审征信等数据导出
     * 
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "/loancheck/teamwithoutpaginglistforfdDs.do", method = { RequestMethod.GET,
                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getTeamListWithoutPagingForFdDs(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                               HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getTeamListWithoutPagingForFdForAdd(queryInfo, user, "10");
    }
    
    /**
     * 房产初评查询页查询
     * 
     * @param queryInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/loanreview/housepreliminaryassessmentlist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> gethousePreliminaryAssessmentListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.gethousePreliminaryAssessmentListWithPaging(queryInfo, user);
    }
    
    /**
     * 房产初评查询页导出
     * 
     * @param queryInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/loanreview/housepreliminaryassessmentlistexport.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> gethousePreliminaryAssessmentListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.gethousePreliminaryAssessmentListWithoutPaging(queryInfo, user);
    }
    
}