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

import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreCreditHeadSearchBeanVO;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineBankStreamService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineBankStreamSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineBankStream;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditLineBankStreamController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineBankStreamController.class);

    @Autowired
    private IWmsCreCreditLineBankStreamService wmscrecreditlinebankstreamService;

    @Autowired
    private IWmsCreCreditHeadService wmscrecreditheadService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinebankstreamwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                                   RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineBankStreamSearchBeanVO queryInfo)
    {
        return wmscrecreditlinebankstreamService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinebankstreamwithpaginglist.do", method = { RequestMethod.GET,
                                                                                                RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCreditLineBankStreamSearchBeanVO queryInfo)
    {
        return wmscrecreditlinebankstreamService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineBankStreamVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinebankstreaminfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCreditLineBankStream getInfoByPK(Integer wms_cre_credit_line_bank_stream_id)
    {
        return wmscrecreditlinebankstreamService.getInfoByPK(wms_cre_credit_line_bank_stream_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinebankstreamsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCreditLineBankStream bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinebankstreamService.save(bean, user);
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
     * Description :提交审批 保存数据
     * 
     * @param
     * @return "success" or "error" or user defined
     * @author ry
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinebankstreamdosubsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSubSave(HttpServletRequest request, String divwt1_data, String divwt2_data, String divwt3_data,
                            String divwt4_data, int wms_cre_credit_head_id, WmsCreditWorkFlowVO approveWorkFlowVO)
    {

        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinebankstreamService.doSubSave(divwt1_data, divwt2_data, divwt3_data, divwt4_data,
                                                                 wms_cre_credit_head_id, user, approveWorkFlowVO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "贷前管理-流水审核组-审核";
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }

    /**
     * Description :提交审批 保存数据
     * 
     * @param
     * @return "success" or "error" or user defined
     * @author wangyishun
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinebankstreamfddosubsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSubSaveFd(HttpServletRequest request, String divwt1_data, String divwt2_data, String divwt3_data,
                              String divwt4_data, WmsHouseCreditWorkFlowVO vo)
    {

        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinebankstreamService.doSubSaveFd(divwt1_data, divwt2_data, divwt3_data, divwt4_data,
                                                                   user, vo);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "贷前管理-房贷审核-流水审核组-审核";
            SysTools.saveLog(request, msg); // record log method
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
    @RequestMapping(value = "/loancheck/wmscrecreditlinebankstreamupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCreditLineBankStream bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinebankstreamService.update(bean, user);
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
     * Description :查询流水审批
     * 
     * @param wms_cre_credit_head_id, query_type, query_tb
     * @return
     * @author ry
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinebankstreamdoquery.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> doQuery(int wms_cre_credit_head_id, String query_type, String query_tb)
    {
        return wmscrecreditlinebankstreamService.doQuery(wms_cre_credit_head_id, query_type, query_tb);
    }

    /**
     * 流水审核列表
     * 
     * @param queryInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/loancheck/houserunapprovalwithpaginglist.do", method = { RequestMethod.GET,
                                                                                      RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCreHousingCheckListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo,
                                                                HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditheadService.getListWithPagingForFdForAdd(queryInfo, user, "1");
    }
}