package com.zx.emanage.loanreview.web;

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

import com.zx.emanage.loanreview.service.IWmsCreRevCertificateModelService;
import com.zx.emanage.loanreview.vo.WmsCreRevCertificateModelSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevCertificateModel;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreRevCertificateModelController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevCertificateModelController.class);

    @Autowired
    private IWmsCreRevCertificateModelService wmscrerevcertificatemodelService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevcertificatemodelwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                                   RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreRevCertificateModelSearchBeanVO queryInfo)
    {
        return wmscrerevcertificatemodelService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevcertificatemodelwithpaginglist.do", method = { RequestMethod.GET,
                                                                                                RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreRevCertificateModelSearchBeanVO queryInfo)
    {
        return wmscrerevcertificatemodelService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevCertificateModelVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevcertificatemodelinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreRevCertificateModel getInfoByPK(Integer wms_cre_rev_certificate_model_id)
    {
        return wmscrerevcertificatemodelService.getInfoByPK(wms_cre_rev_certificate_model_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevcertificatemodelsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreRevCertificateModel bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevcertificatemodelService.save(bean, user);
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
    @RequestMapping(value = "/loanreview/wmscrerevcertificatemodelupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreRevCertificateModel bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevcertificatemodelService.update(bean, user);
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
     * 通过主表单ID获得征信重要数据
     * 
     * @param primary key
     * @return WmsCreRevCertificateModelVO
     * @author zhubo
     */
    @RequestMapping(value = "/loanreview/wmscrerevcertificatemodelinfobyfk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreRevCertificateModel getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmscrerevcertificatemodelService.getInfoByFK(wms_cre_credit_head_id);
    }
}