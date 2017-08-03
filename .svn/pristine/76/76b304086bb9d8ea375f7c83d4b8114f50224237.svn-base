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

import com.zx.emanage.loanreview.service.IWmsCreRevInfoModelService;
import com.zx.emanage.loanreview.vo.WmsCreRevInfoModelSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevInfoModel;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreRevInfoModelController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevInfoModelController.class);

    @Autowired
    private IWmsCreRevInfoModelService wmscrerevinfomodelService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevinfomodelwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                            RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreRevInfoModelSearchBeanVO queryInfo)
    {
        return wmscrerevinfomodelService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevinfomodelwithpaginglist.do", method = { RequestMethod.GET,
                                                                                         RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreRevInfoModelSearchBeanVO queryInfo)
    {
        return wmscrerevinfomodelService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevInfoModelVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevinfomodelinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreRevInfoModel getInfoByPK(Integer wms_cre_rev_info_model_id)
    {
        return wmscrerevinfomodelService.getInfoByPK(wms_cre_rev_info_model_id);
    }

    /**
     * Description :通过外键实现对该数据详细的查询
     * 
     * @param F key
     * @return WmsCreRevInfoModelVO
     * @author hancd
     */
    @RequestMapping(value = "/loanreview/wmscrerevinfomodelinfobyfk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreRevInfoModel getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmscrerevinfomodelService.getInfoByFK(wms_cre_credit_head_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevinfomodelsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreRevInfoModel bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevinfomodelService.save(bean, user);
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
    @RequestMapping(value = "/loanreview/wmscrerevinfomodelupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreRevInfoModel bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevinfomodelService.update(bean, user);
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
}