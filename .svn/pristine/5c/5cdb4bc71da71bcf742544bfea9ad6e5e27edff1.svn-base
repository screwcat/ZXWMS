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
import com.zx.emanage.loancheck.service.IWmsCreCreditLineCompanyAddressService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineCompanyAddressSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCompanyAddress;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineOther;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditLineCompanyAddressController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineCompanyAddressController.class);

    @Autowired
    private IWmsCreCreditLineCompanyAddressService wmscrecreditlinecompanyaddressService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecompanyaddresswithoutpaginglist.do", method = {
                                                                                                       RequestMethod.GET,
                                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCompanyAddressSearchBeanVO queryInfo)
    {
        return wmscrecreditlinecompanyaddressService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecompanyaddresswithpaginglist.do", method = { RequestMethod.GET,
                                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCompanyAddressSearchBeanVO queryInfo)
    {
        return wmscrecreditlinecompanyaddressService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineCompanyAddressVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecompanyaddressinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCreditLineCompanyAddress getInfoByPK(Integer wms_cre_credit_line_company_address_id)
    {
        return wmscrecreditlinecompanyaddressService.getInfoByPK(wms_cre_credit_line_company_address_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecompanyaddresssave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCreditLineCompanyAddress bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinecompanyaddressService.save(bean, user);
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
    @RequestMapping(value = "/loancheck/wmscrecreditlinecompanyaddressupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCreditLineCompanyAddress bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinecompanyaddressService.update(bean, user);
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
     * 电审审批组审批信息保存
     * 
     * @param
     * @return
     * @author 张风山
     */
    @RequestMapping(value = "/loancheck/telTeamCheckAddRecord.do", method = { RequestMethod.POST })
    @ResponseBody
    public String telTeamCheckAddRecord(HttpServletRequest request,
                                        String qyjbqk, // 企业基本情况
                                        String gqjg, // 股权结构、股东关系及实际经营者
                                        String qygdzc_cfxx, // 企业固定资产-厂房信息
                                        String qygdzc_clxx, // 企业固定资产-车辆信息
                                        String qygdzc_sbxx, // 企业固定资产-设备信息
                                        String sxyqyxx_syghs, // 上下游企业信息-上游供货商
                                        String sxyqyxx_xycgs, // 上下游企业信息-下游采购商
                                        String dywqk, // 抵押物情况
                                        String jyqk_yszkmx, // 经营情况-主要应收账款明细
                                        String jyqk_yfzkmx, // 经营情况-主要应付账款明细
                                        String jyqk_kcyl, // 经营情况-主要库存原料
                                        String jyqk_kcspmx, // 经营情况-主要库存商品明细
                                        String wms_cre_credit_head_id, WmsCreCreditLineOther wmsCreCreditLineOther,
                                        WmsCreditWorkFlowVO approveWorkFlowVO)
    {

        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);

        try
        {
            result = wmscrecreditlinecompanyaddressService.saveAll(qyjbqk, gqjg, qygdzc_cfxx, qygdzc_clxx, qygdzc_sbxx,
                                                                   sxyqyxx_syghs, sxyqyxx_xycgs, dywqk, jyqk_yszkmx,
                                                                   jyqk_yfzkmx, jyqk_kcyl, jyqk_kcspmx,
                                                                   wms_cre_credit_head_id, wmsCreCreditLineOther,
                                                                   approveWorkFlowVO, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        if ("success".equals(result))
        {
            String msg = "贷前管理-电审组审核-审核";
            SysTools.saveLog(request, msg); //
        }

        return result;
    }

    /**
     * 电审审核组审核信息显示
     * 
     * @param
     * @return
     * @author 张风山
     */
    @RequestMapping(value = "/loancheck/showTelTeamCheck.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> showTelTeamCheck(HttpServletRequest request, String wms_cre_credit_head_id)
    {

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditlinecompanyaddressService.selectAllByWmsCreCreditHeadId(wms_cre_credit_head_id, user);
    }

}