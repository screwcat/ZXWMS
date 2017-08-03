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

import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineCustomerInfoService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineCustomerInfoSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineApplicant;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCompanyCondition;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerInfo;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineHouseRecord;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditLineCustomerInfoController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineCustomerInfoController.class);

    @Autowired
    private IWmsCreCreditLineCustomerInfoService wmscrecreditlinecustomerinfoService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecustomerinfowithoutpaginglist.do", method = {
                                                                                                     RequestMethod.GET,
                                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCustomerInfoSearchBeanVO queryInfo)
    {
        return wmscrecreditlinecustomerinfoService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecustomerinfowithpaginglist.do", method = { RequestMethod.GET,
                                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCustomerInfoSearchBeanVO queryInfo)
    {
        return wmscrecreditlinecustomerinfoService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineCustomerInfoVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecustomerinfoinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCreditLineCustomerInfo getInfoByPK(Integer wms_cre_credit_line_customer_info_id)
    {
        return wmscrecreditlinecustomerinfoService.getInfoByPK(wms_cre_credit_line_customer_info_id);
    }

    /**
     * 实现信息组审批
     * 
     * @param creCreditCustomerInfo 客户信息实体
     * @param creCreditCompanyCondition 企业基本情况信息实体
     * @param creCreditLineHouseRecord 房产档案记录
     * @param paramCsinfo 法院网案件执行状态
     * @param paramTelinfo 通讯记录信息
     * @param fileArrTelPhone 通讯记录附件信息
     * @param paramMedicareinfo 医保信息
     * @param fileArrMedicare 医保上传附件信息
     * @param paramReserveinfo 公积金信息
     * @param fileArrReserve 公积金上传附件信息
     * @param bainfoDetailListStr 企业基本信息集合
     * @param approveWorkFlowVO 工作流公共类
     * @param telsId 电话通讯录信息
     * @return
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecustomerinfosave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCreditLineCustomerInfo creCreditCustomerInfo,
                         WmsCreCreditLineCompanyCondition creCreditCompanyCondition,
                         WmsCreCreditLineHouseRecord creCreditLineHouseRecord, String paramCsinfo, String paramTelinfo,
                         String fileArrTelPhone, String paramMedicareinfo, String fileArrMedicare,
                         String paramReserveinfo, String fileArrReserve, String bainfoAndDetaiList,
                         WmsCreditWorkFlowVO approveWorkFlowVO, String telsId)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinecustomerinfoService.save(creCreditCustomerInfo, creCreditCompanyCondition,
                                                              creCreditLineHouseRecord, paramCsinfo, paramTelinfo,
                                                              fileArrTelPhone, paramMedicareinfo, fileArrMedicare,
                                                              paramReserveinfo, fileArrReserve, bainfoAndDetaiList,
                                                              approveWorkFlowVO, user, telsId);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        // record log
        if ("success".equals(result))
        {
            String msg = "信贷管理-信息审核组-审批";
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
    @RequestMapping(value = "/loancheck/wmscrecreditlinecustomerinfoupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCreditLineCustomerInfo bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinecustomerinfoService.update(bean, user);
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
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineApplicantVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecustomerinfoinfobyfk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCreditLineCustomerInfo getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmscrecreditlinecustomerinfoService.getInfoByFK(wms_cre_credit_head_id);
    }
}