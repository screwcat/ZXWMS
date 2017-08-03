package com.zx.emanage.cremanage.web;

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

import com.zx.emanage.cremanage.service.IWmsCreHousingAttService;
import com.zx.emanage.cremanage.vo.WmsCreCreditHeadHouseSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreHousingAttSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreHousingAtt;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreHousingAttController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreHousingAttController.class);

    @Autowired
    private IWmsCreHousingAttService wmscrehousingattService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrehousingattwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                         RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreHousingAttSearchBeanVO queryInfo)
    {
        return wmscrehousingattService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author wangyishun
     */
    @RequestMapping(value = "/cremanage/wmscrehousingattwithoutpaginglistforht.do", method = { RequestMethod.GET,
                                                                                              RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPagingForHt(WmsCreHousingAttSearchBeanVO queryInfo)
    {
        return wmscrehousingattService.getListWithoutPagingForHt(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrehousingattwithpaginglist.do", method = { RequestMethod.GET,
                                                                                      RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreHousingAttSearchBeanVO queryInfo)
    {
        return wmscrehousingattService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreHousingAttVO
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrehousingattinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreHousingAtt getInfoByPK(Integer wms_cre_housing_att_id)
    {
        return wmscrehousingattService.getInfoByPK(wms_cre_housing_att_id);
    }

    /**
     * Description :get single-table information by foreign key
     * 
     * @param wms_cre_credit_head_id
     * @return WmsCreHousingAttVO
     */
    @RequestMapping(value = "/cremanage/wmscrehousingattinfobyfk.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<WmsCreHousingAtt> getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmscrehousingattService.getInfoByFK(wms_cre_credit_head_id);
    }

    /**
     * Description :add method
     * 
     * @param approveHouseWorkFlowVO fileArr
     * @return "success" or "error" or user defined
     * @author ry
     */
    @RequestMapping(value = "/cremanage/wmscrehousingattsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, String fileArr)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrehousingattService.doSQSave(approveHouseWorkFlowVO, fileArr, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "贷款管理-房屋贷款-信息完善";
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }

    /**
     * Description :补录合同
     * 
     * @param taskId wms_cre_credit_head_id fileArr
     * @return "success" or "error" or user defined
     * @author zhubo
     */
    @RequestMapping(value = "/cremanage/wmscreagreeattsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doBLSave(HttpServletRequest request, WmsCreCreditHeadHouseSearchBeanVO bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrehousingattService.doBLSave(user, bean);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
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
    @RequestMapping(value = "/cremanage/wmscrehousingattupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreHousingAtt bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrehousingattService.update(bean, user);
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

    @RequestMapping(value = "/cremanage/wmscrehousingattnotarizationsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String addNotarizationNewRecord(HttpServletRequest request, WmsCreCreditHeadHouseSearchBeanVO bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrehousingattService.addNotarizationNewRecord(user, bean);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        if ("success".equals(result))
        {
            String msg = "终审管理>房贷终审>公证";
            SysTools.saveLog(request, msg);
        }
        return result;

    }
    @RequestMapping(value = "/cremanage/addSupplementAgreeInfo.do", method = { RequestMethod.POST })
    @ResponseBody
    public String addSupplementAgreeInfo(HttpServletRequest request, WmsCreCreditHeadHouseSearchBeanVO bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrehousingattService.addSupplementAgreeInfo(user, bean);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        if ("success".equals(result))
        {
            String msg = "终审管理>房贷终审>合同补充";
            SysTools.saveLog(request, msg);
        }
        return result;

    }

    @RequestMapping(value = "/cremanage/wmscrehousingattotherssave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String addOthersNewRecord(HttpServletRequest request, WmsCreCreditHeadHouseSearchBeanVO bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrehousingattService.addOthersNewRecord(user, bean);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        if ("success".equals(result))
        {
            String msg = "终审管理>房贷终审>他项";
            SysTools.saveLog(request, msg);
        }
        return result;
    }
}