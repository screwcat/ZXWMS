package com.zx.emanage.cremanage.web;

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

import com.zx.emanage.cremanage.service.IWmsCreCreditLineCustomerChangeHeadService;
import com.zx.emanage.cremanage.vo.WmsCreCreditLineCustomerChangeHeadSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCreCreditLineCustomerChangeHead;
import com.zx.emanage.util.gen.vo.WmsCreCreditLineCustomerChangeHeadVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditLineCustomerChangeHeadController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineCustomerChangeHeadController.class);

    @Autowired
    private IWmsCreCreditLineCustomerChangeHeadService wmscrecreditlinecustomerchangeheadService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecreditlinecustomerchangeheadwithoutpaginglist.do", method = {
                                                                                                           RequestMethod.GET,
                                                                                                           RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCustomerChangeHeadSearchBeanVO queryInfo)
    {
        return wmscrecreditlinecustomerchangeheadService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecreditlinecustomerchangeheadwithpaginglist.do", method = {
                                                                                                        RequestMethod.GET,
                                                                                                        RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCustomerChangeHeadSearchBeanVO queryInfo)
    {

        return wmscrecreditlinecustomerchangeheadService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineCustomerChangeHeadVO
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecreditlinecustomerchangeheadinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead getInfoByPK(Integer wms_cre_credit_line_customer_change_head_id)
    {
        return wmscrecreditlinecustomerchangeheadService.getInfoByPK(wms_cre_credit_line_customer_change_head_id);
    }

    /**
     * Description :得到客户变更和工作变更信息
     * 
     * @param wms_cre_credit_line_customer_change_head_id
     * @return map
     * @author ry
     */
    @RequestMapping(value = "/cremanage/wmscrecreditlinecustomerchangeheadinfomapbypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getInfoMapByPK(Integer wms_cre_credit_line_customer_change_head_id)
    {
        return wmscrecreditlinecustomerchangeheadService.getInfoMapByPK(wms_cre_credit_line_customer_change_head_id);
    }

    /**
     * 实现信息审核组审批时主带人手机号
     * 
     * @param primary key
     * @return WmsCreCreditLineCustomerChangeHeadVO
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecreditlinecustomerchangeheadinfolistbytel.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getInfoListByTEL(Integer wms_cre_credit_head_id)
    {
        return wmscrecreditlinecustomerchangeheadService.getInfoListByTEL(wms_cre_credit_head_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecreditlinecustomerchangeheadsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCreditLineCustomerChangeHead bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinecustomerchangeheadService.save(bean, user);
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
    @RequestMapping(value = "/cremanage/wmscrecreditlinecustomerchangeheadupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCreditLineCustomerChangeHead bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinecustomerchangeheadService.update(bean, user);
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
     * Description :delete method
     * 
     * @param pk
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecreditlinecustomerchangeheaddelete.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doDelete(HttpServletRequest request, WmsCreCreditLineCustomerChangeHead bean)
    {
        String result = wmscrecreditlinecustomerchangeheadService.delete(bean);
        /*
         * // record log if("success".equals(result)){ String msg =
         * "log content"; SysTools.saveLog(request, msg); // record log method }
         */
        return result;
    }
    
    /**
     * 根据客户id查询所有客户信息(客户、子女、房产、工作、公司)
     * @author administrator
     */
    @RequestMapping(value = "/cremanage/searchAllCustomerInfo.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> searchAllCustomerInfo(HttpServletRequest request, WmsCreCreditLineCustomerChangeHeadSearchBeanVO queryInfo)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
           resMap = wmscrecreditlinecustomerchangeheadService.searchAllCustomerInfo(queryInfo, user);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = "error";
        }
        
        resMap.put("result", result);
        
        return resMap;
    }
    
    /**
     * 根据客户id删除所有客户信息(逻辑删除)
     * @author administrator
     */
    @RequestMapping(value = "/cremanage/wmsCustomerAllInfoDelete.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> wmsCustomerDelete(HttpServletRequest request, WmsCreCreditLineCustomerChangeHeadSearchBeanVO queryInfo)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            wmscrecreditlinecustomerchangeheadService.wmsCustomerAllInfoDelete(queryInfo, user);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = "error";
        }
        
        resMap.put("result", result);
        
        return resMap;
    }
    

    @RequestMapping(value = "/loancheck/getwmscrecreditlinecustomerchangeheadkhxx.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public WmsCreCreditLineCustomerChangeHeadVO getKHZL(HttpServletRequest request, Integer wms_cre_credit_head_id)
    {
        return wmscrecreditlinecustomerchangeheadService.getKHXX(wms_cre_credit_head_id);
    }
}