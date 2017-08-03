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

import com.zx.emanage.loancheck.service.IWmsCreCreditLineHouseRecordService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineHouseRecordSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineHouseRecord;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditLineHouseRecordController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineHouseRecordController.class);

    @Autowired
    private IWmsCreCreditLineHouseRecordService wmscrecreditlinehouserecordService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinehouserecordwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineHouseRecordSearchBeanVO queryInfo)
    {
        return wmscrecreditlinehouserecordService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinehouserecordwithpaginglist.do", method = { RequestMethod.GET,
                                                                                                 RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCreditLineHouseRecordSearchBeanVO queryInfo)
    {
        return wmscrecreditlinehouserecordService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineHouseRecordVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinehouserecordinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCreditLineHouseRecord getInfoByPK(Integer wms_cre_credit_line_house_record_id)
    {
        return wmscrecreditlinehouserecordService.getInfoByPK(wms_cre_credit_line_house_record_id);
    }

    /**
     * 通过主表单ID外键获取相应的档案归档记录
     * 
     * @param primary key
     * @return WmsCreCreditLineHouseRecord
     * @author hancd
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinehouserecordinfobyfk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCreditLineHouseRecord getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmscrecreditlinehouserecordService.getInfoByFK(wms_cre_credit_head_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinehouserecordsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCreditLineHouseRecord bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinehouserecordService.save(bean, user);
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
    @RequestMapping(value = "/loancheck/wmscrecreditlinehouserecordupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCreditLineHouseRecord bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinehouserecordService.update(bean, user);
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