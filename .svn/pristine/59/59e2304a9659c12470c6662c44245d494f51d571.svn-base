package com.zx.emanage.loanappro.web;

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

import com.zx.emanage.loanappro.service.IWmsSysPropertyService;
import com.zx.emanage.loanappro.vo.WmsSysPropertyPropertySearchBeanVO;
import com.zx.emanage.loanappro.vo.WmsSysPropertySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsSysProperty;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsSysPropertyController
{
    private static Logger log = LoggerFactory.getLogger(WmsSysPropertyController.class);

    @Autowired
    private IWmsSysPropertyService wmssyspropertyService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanappro/wmssyspropertywithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsSysPropertySearchBeanVO queryInfo)
    {
        return wmssyspropertyService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanappro/wmssyspropertywithpaginglist.do", method = { RequestMethod.GET,
                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsSysPropertySearchBeanVO queryInfo)
    {
        return wmssyspropertyService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsSysPropertyVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanappro/wmssyspropertyinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsSysProperty getInfoByPK(Integer wms_sys_property_id)
    {
        return wmssyspropertyService.getInfoByPK(wms_sys_property_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loanappro/wmssyspropertysave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsSysProperty bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmssyspropertyService.save(bean, user);
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
    @RequestMapping(value = "/loanappro/wmssyspropertyupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsSysProperty bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmssyspropertyService.update(bean, user);
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
     * for 房产终审 签合同--暂时废弃
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    /*
     * @RequestMapping(value = "/loanappro/wmssyspropertylistforhouse.do",
     * method = {RequestMethod.GET, RequestMethod.POST})
     * @ResponseBody public Map<String, Object> getListforhouse(String
     * protocol_type,Integer wms_cre_credit_head_id) { return
     * wmssyspropertyService
     * .searchforhouse(protocol_type,wms_cre_credit_head_id); }
     */

    /**
     * for 签合同-正在使用
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/loanappro/wmssyspropertylistforhouse.do", method = { RequestMethod.GET,
                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getforNewProtocol(HttpServletRequest request, String protocol_type,
                                                 Integer wms_cre_credit_head_id)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmssyspropertyService.getforNewProtocol(user, protocol_type, wms_cre_credit_head_id);
    }
    
    /**
     * 合同完善数据初始化
     * 
     * @param queryInfo
     * @return record list
     * @author jiaodelong
     */
    @RequestMapping(value = "/loanappro/wmsPerfectContract.do", method = { RequestMethod.GET,
                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> wmsPerfectContract(HttpServletRequest request,  WmsSysPropertyPropertySearchBeanVO queryInfo)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmssyspropertyService.getPerfectContract(user, queryInfo);
    }
    
    /**
     * 合同完善产品变动信息
     * 
     * @param queryInfo
     * @return record list
     * @author jiaodelong
     */
    @RequestMapping(value = "/loanappro/getCreLoanTypeChange.do", method = { RequestMethod.GET,
                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCreLoanTypeChange(HttpServletRequest request, WmsSysPropertyPropertySearchBeanVO queryInfo)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmssyspropertyService.getCreLoanTypeChange(user,queryInfo);
    }
    
    /**
     * 获取合同利率
     * 
     * @param queryInfo
     * @return record list
     * @author jiaodelong
     */
    @RequestMapping(value = "/loanappro/getBorrowDeadlineChange.do", method = { RequestMethod.GET,
                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getBorrowDeadlineChange(HttpServletRequest request, WmsSysPropertyPropertySearchBeanVO queryInfo)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmssyspropertyService.getBorrowDeadlineChange(user, queryInfo);
    }
    
    /**
     * for 签合同-出借人信息
     * 
     * @param queryInfo
     * @return record list
     * @author wangyihan
     */
    @RequestMapping(value = "/loanappro/searchLenderInfo.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> searchLenderInfo(HttpServletRequest request, WmsSysPropertySearchBeanVO queryInfo) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        resMap.put("lenderList", this.wmssyspropertyService.searchLenderInfo(queryInfo, user));
        return resMap;
    }
    /**
     * 贷款申请 贷款终审获取利率和期数等相关信息
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/loanappro/getprotocolProperty.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getprotocolProperty(HttpServletRequest request, WmsSysPropertyPropertySearchBeanVO queryInfo) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        resMap.put("newFD", this.wmssyspropertyService.getprotocolProperty(user,queryInfo));
        return resMap;
    }
    /**
     * 
     * @Title: getPaymentContractType
     * @Description: TODO(还款方式查询)
     * @param request
     * @param cre_loan_type
     * @return 
     * @author: jiaodelong
     * @time:2016年12月29日 下午2:29:16
     * history:
     * 1、2016年12月29日 jiaodelong 创建方法
     */
    @RequestMapping(value = "/loanappro/getPaymentContractType.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String getPaymentContractType(HttpServletRequest request,Integer cre_loan_type) {
        return wmssyspropertyService.getPaymentContractType(cre_loan_type);
    }
    
    

}