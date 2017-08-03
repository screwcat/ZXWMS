package com.zx.emanage.loanpost.web;

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

import com.zx.emanage.loanfina.service.IWmsFinaCreRepayService;
import com.zx.emanage.loanfina.vo.WmsFinaCreRepaySearchBeanVO;
import com.zx.emanage.loanpost.service.IWmsPostLoanWorkFlowService;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller("loanPostWmsFinaCreRepayController")
public class WmsFinaCreRepayController
{
    private static Logger log = LoggerFactory.getLogger(WmsFinaCreRepayController.class);

    @Autowired
    private IWmsFinaCreRepayService wmsfinacrerepayService;

    @Autowired
    private IWmsPostLoanWorkFlowService wmsPostLoanWorkFlowService;

    /**
     * Description :提前还款申请列表分页查询
     * 
     * @param queryInfo
     * @return record list
     * @author wangyishun
     */
    @RequestMapping(value = "/loanpost/wmsfinacrerepaywithpaginglist.do", method = { RequestMethod.GET,
                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(HttpServletRequest request, WmsFinaCreRepaySearchBeanVO queryInfo)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsfinacrerepayService.getListWithPaging(queryInfo, user);
    }

    /**
     * Description :提前还款审核信息分页查询
     * 
     * @param queryInfo
     * @return record list
     * @author wangyishun
     */
    @RequestMapping(value = "/loanpost/wmsfinacrerepaywithpaginglistforadd.do", method = { RequestMethod.GET,
                                                                                          RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPagingForAdd(WmsFinaCreRepaySearchBeanVO queryInfo, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsfinacrerepayService.getListWithPagingForAdd(queryInfo, user);
    }

    /**
     * Description :提前还款申请信息导出
     * 
     * @param queryInfo
     * @return record list
     * @author wangyishun
     */
    @RequestMapping(value = "/loanpost/wmsfinacrerepaywithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsFinaCreRepaySearchBeanVO queryInfo, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsfinacrerepayService.getListWithoutPaging(queryInfo, user);
    }

    /**
     * Description :提前还款审核信息导出
     * 
     * @param queryInfo
     * @return record list
     * @author wangyishun
     */
    @RequestMapping(value = "/loanpost/wmsfinacrerepaywithoutpaginglistforadd.do", method = { RequestMethod.GET,
                                                                                             RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPagingForAdd(WmsFinaCreRepaySearchBeanVO queryInfo,
                                                          HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsfinacrerepayService.getListWithoutPagingForAdd(queryInfo, user);
    }

    /**
     * Description :结清证明打印信息导出与分页查询
     * 
     * @param queryInfo
     * @return record list
     * @author wangyishun
     */
    @RequestMapping(value = "/loanpost/wmsfinacrerepaywithpaginglistforclear.do", method = { RequestMethod.GET,
                                                                                            RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPagingForClear(WmsFinaCreRepaySearchBeanVO queryInfo)
    {
        return wmsfinacrerepayService.getListWithoutPagingForClear(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsFinaCreRepayVO
     * @author wangyishun
     */
    @RequestMapping(value = "/loanpost/wmsfinacrerepayinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getInfoByPK(Integer wms_fina_cre_pay_id)
    {
        return wmsfinacrerepayService.getInfoByPK(wms_fina_cre_pay_id);
    }

    /**
     * 实现货后提前还款管理流程历程显示
     * 
     * @param queryInfo
     * @return record list
     * @author wangyishun
     */
    @RequestMapping(value = "/loanpost/approvalProcessView.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPagingForClear(Integer wms_cre_credit_head_id)
    {
        return wmsPostLoanWorkFlowService.postLoanApproveProcessBefore(wms_cre_credit_head_id);
    }
   
    /**
     * 服务管理--贷后管理--贷后查询-查看客户信息
     * @param
     */
    @RequestMapping(value = "/loanpost/wmsfinacrerepaybypk.do", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getWmsFinaCreRepayBypk(HttpServletRequest request,Integer wms_cre_credit_head_id,Integer wms_fina_cre_pay_id) 
    {
    	   HttpSession session = request.getSession();
           UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        return wmsfinacrerepayService.getWmsFinaCreRepayBypk(user,wms_cre_credit_head_id,wms_fina_cre_pay_id);    
    }    
    /**
     * 服务管理--贷后管理--贷后查询数据显示--电话催缴
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/loanpost/postLoanCommissionerSearchWithPagingList.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getPostLoanSearchListWithPagingCommissioner(HttpServletRequest request,WmsFinaCreRepaySearchBeanVO queryInfo) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        return wmsfinacrerepayService.getListWithoutPagingCommissioner(queryInfo,user);
    }
    /**
     * 服务管理--贷后管理--贷后查询导出数据功能--上门催缴--excel
     * @param queryInfo
     * @return recod list
     * @author baisong
     */
    @RequestMapping(value="/loanpost/postLoanSearchWithoutPagingListCommissionerexcel.do",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getPostLoanSearchWithoutPagingListCommissionerexcel(HttpServletRequest request,WmsFinaCreRepaySearchBeanVO queryInfo) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        return wmsfinacrerepayService.getPostLoanSearchWithoutPagingListCommissionerexcel(queryInfo,user);
    }
    /**
     * 服务管理--贷后管理--贷后查询导出数据功能--上门催缴--excel
     * @param queryInfo
     * @return recod list
     * @author baisong
     */
    @RequestMapping(value="/loanpost/postLoanSearchWithoutPagingListexcel.do",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getPostLoanSearchWithoutPagingListexcel(HttpServletRequest request,WmsFinaCreRepaySearchBeanVO queryInfo) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        return wmsfinacrerepayService.getPostLoanSearchWithoutPagingListexcel(queryInfo,user);
    }
    /**
     * 服务管理--贷后管理--贷后查询数据显示--上门催缴列表 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/loanpost/postLoanVisitSearchWithPagingList.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> postLoanVisitSearchWithPagingList(HttpServletRequest request,WmsFinaCreRepaySearchBeanVO queryInfo) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        return wmsfinacrerepayService.postLoanVisitSearchWithPagingList(queryInfo,user);
    }
    /**
     * 服务管理--贷后管理--贷后查询数据显示--贷后专员-分配
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/loanpost/postLoanAllocationSearchWithPagingList.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getPostLoanSearchListWithPagingAllocation(HttpServletRequest request,WmsFinaCreRepaySearchBeanVO queryInfo) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        return wmsfinacrerepayService.getListWithoutPagingAllocation(queryInfo,user);
    }
    /**
     * 服务管理--贷后管理--贷后查询数据显示--贷后专员-分配
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/loanpost/postLoanAllocationSearchWithoutPagingList.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getPostLoanSearchListWithoutPagingAllocation(HttpServletRequest request,WmsFinaCreRepaySearchBeanVO queryInfo) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        return wmsfinacrerepayService.getListWithoutAllocation(queryInfo,user);
    }
    /**
     * 服务管理--结清管理--结清证明打印
     * 
     */
    @RequestMapping(value = "/loanpost/getWmsPostforClearById.do", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getWmsPostforClearById(Integer wms_cre_credit_head_id) 
    {
         return wmsfinacrerepayService.getWmsPostforClearById(wms_cre_credit_head_id);    
    }

    /**
     * 
     * @Title: getListWithoutPagingForAdd
     * @Description: TODO(提前还款申请判断是否是组合贷)
     * @param queryInfo
     * @param request
     * @return 
     * @author: baisong
     * @time:2017年1月16日 上午10:42:52
     * history:
     * 1、2017年1月16日 baisong 创建方法
     */
    @RequestMapping(value = "/loanpost/getGroupTpye.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getGroupTpye(WmsFinaCreRepaySearchBeanVO queryInfo, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsfinacrerepayService.getGroupTpye(queryInfo, user);
    }
}