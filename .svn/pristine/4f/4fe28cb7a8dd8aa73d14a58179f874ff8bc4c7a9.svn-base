package com.zx.emanage.loanreview.web;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.zx.emanage.cremanage.service.IWmsCreCreditLineCustomerChangeHeadService;
import com.zx.emanage.cremanage.service.IWmsCreCustomerChangeLineContactService;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.loanreview.service.IWmsCreRevPhoneContactService;
import com.zx.emanage.loanreview.service.IWmsCreRevPhoneMainService;
import com.zx.emanage.loanreview.service.IWmsCreRevPhoneModelService;
import com.zx.emanage.loanreview.vo.WmsCreRevPhoneCustomerContactVO;
import com.zx.emanage.loanreview.vo.WmsCreRevPhoneMainSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.domain.WmsCreCustomerChangeLineContact;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead;
import com.zx.emanage.util.gen.entity.WmsCreRevPhoneContact;
import com.zx.emanage.util.gen.entity.WmsCreRevPhoneMain;
import com.zx.emanage.util.gen.entity.WmsCreRevPhoneModel;
import com.zx.emanage.util.gen.entity.WmsCreRevWaterMain;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreRevPhoneMainController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevPhoneMainController.class);

    @Autowired
    private IWmsCreRevPhoneMainService wmscrerevphonemainService;

    @Autowired
    private IWmsCreRevPhoneContactService wmscrerevphonecontactService;

    @Autowired
    private IWmsCreCreditLineCustomerChangeHeadService wmsCreCreditLineCustomerChangeHeadService;

    @Autowired
    private IWmsCreCustomerChangeLineContactService wmsCreCustomerChangeLineContactService;

    @Autowired
    private IWmsCreRevPhoneModelService wmsCreRevPhoneModelService;// 电审重要数据
                                                                   // --模型

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevphonemainwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                            RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreRevPhoneMainSearchBeanVO queryInfo)
    {
        return wmscrerevphonemainService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevphonemainwithpaginglist.do", method = { RequestMethod.GET,
                                                                                         RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreRevPhoneMainSearchBeanVO queryInfo)
    {
        return wmscrerevphonemainService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevPhoneMainVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevphonemaininfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreRevPhoneMain getInfoByPK(Integer wms_cre_rev_phone_main_id)
    {
        return wmscrerevphonemainService.getInfoByPK(wms_cre_rev_phone_main_id);
    }

    /**
     * Description :update method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevphonemainupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreRevPhoneMain bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevphonemainService.update(bean, user);
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
     * Description :获取贷款人以及对应的联系人信息
     * 
     * @param wms_cre_credit_head_id
     * @author 张风山
     */
    @RequestMapping(value = "/loanreview/getCustomerChangeHeadList.do", method = { RequestMethod.GET,
                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCustomerChangeHeadList(String wms_cre_credit_head_id)
    {

        Map<String, Object> customerMap = wmsCreCreditLineCustomerChangeHeadService.getWmsCreCreditLineCustomerChangeHeadListWithoutPaging(Integer.parseInt(wms_cre_credit_head_id));

        com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineContact queryInfo = new com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineContact();
        com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead majorInfo = (WmsCreCreditLineCustomerChangeHead) customerMap.get("major");
        queryInfo.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
        queryInfo.setWms_cre_credit_line_customer_change_head_id(majorInfo.getWms_cre_credit_line_customer_change_head_id());
        List<com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineContact> majorContactList = wmsCreCustomerChangeLineContactService.getContactList(queryInfo);
        WmsCreRevPhoneCustomerContactVO wmsCreRevPhoneCustomerContactVO = new WmsCreRevPhoneCustomerContactVO();
        List<WmsCreRevPhoneCustomerContactVO> major = new ArrayList<WmsCreRevPhoneCustomerContactVO>();
        wmsCreRevPhoneCustomerContactVO.setWmsCreCreditLineCustomerChangeHead(majorInfo);
        wmsCreRevPhoneCustomerContactVO.setWmsCreCustomerChangeLineContactList(majorContactList);
        major.add(wmsCreRevPhoneCustomerContactVO);
        customerMap.put("majorInfo", major);
        List<WmsCreRevPhoneCustomerContactVO> notMajor = new ArrayList<WmsCreRevPhoneCustomerContactVO>();
        List<com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead> notMajorInfo = (List<WmsCreCreditLineCustomerChangeHead>) customerMap.get("notMajor");
        if (notMajorInfo != null)
        {
            for (int i = 0; i < notMajorInfo.size(); i++)
            {
                com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineContact notMajorQueryInfo = new com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineContact();
                notMajorQueryInfo.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
                notMajorQueryInfo.setWms_cre_credit_line_customer_change_head_id(notMajorInfo.get(i)
                                                                                             .getWms_cre_credit_line_customer_change_head_id());
                List<com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineContact> notMajorContactList = wmsCreCustomerChangeLineContactService.getContactList(notMajorQueryInfo);
                WmsCreRevPhoneCustomerContactVO notMajorwmsCreRevPhoneCustomerContactVO = new WmsCreRevPhoneCustomerContactVO();
                notMajorwmsCreRevPhoneCustomerContactVO.setWmsCreCreditLineCustomerChangeHead(notMajorInfo.get(i));
                notMajorwmsCreRevPhoneCustomerContactVO.setWmsCreCustomerChangeLineContactList(notMajorContactList);
                notMajor.add(notMajorwmsCreRevPhoneCustomerContactVO);
            }
        }

        customerMap.put("notMajorInfo", notMajor);
        return customerMap;
    }

    /**
     * 保存信贷电审审核信息
     * 
     * @param bean
     * @author 张风山
     */
    @RequestMapping(value = "/loanreview/saveDsInfo.do", method = { RequestMethod.POST })
    @ResponseBody
    public String saveDsInfo(HttpServletRequest request, String wms_cre_credit_head_id, String dsInfo, String lxrInfo,
                             WmsCreditWorkFlowVO approveWorkFlowVO, String phone_appro_eval, String documentary_opinion, String saveFlag,
                             WmsCreRevPhoneModel wmsCreRevPhoneModel)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevphonemainService.save(wms_cre_credit_head_id, dsInfo, lxrInfo, approveWorkFlowVO,
                                                    phone_appro_eval, documentary_opinion, saveFlag, user, wmsCreRevPhoneModel);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "贷前管理-信贷审核-电审审核组-审核";
            SysTools.saveLog(request, msg);
        }
        return result;
    }

    /**
     * 保存房贷电审审核信息
     * 
     * @param bean
     * @author 张风山
     */
    @RequestMapping(value = "/loanreview/saveHouseDsInfo.do", method = { RequestMethod.POST })
    @ResponseBody
    public String saveHouseDsInfo(HttpServletRequest request, String wms_cre_credit_head_id, String dsInfo,
                                  String lxrInfo, WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO,
                                  String phone_appro_eval, String saveFlag)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevphonemainService.saveHouse(wms_cre_credit_head_id, dsInfo, lxrInfo,
                                                         approveHouseWorkFlowVO, phone_appro_eval, saveFlag, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "贷前管理-房贷审核-电审审核组-审核";
            SysTools.saveLog(request, msg);
        }
        return result;
    }

    /**
     * Description :显示电审意见tab页的信息
     * 
     * @param wms_cre_credit_head_id
     * @author 张风山
     */
    @RequestMapping(value = "/loanreview/getDsyjInfo.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getDsyjInfo(String wms_cre_credit_head_id)
    {

        WmsCreRevPhoneMain wmsCreRevPhoneMain = new WmsCreRevPhoneMain();
        WmsCreRevPhoneContact wmsCreRevPhoneContact = new WmsCreRevPhoneContact();
        wmsCreRevPhoneMain.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
        wmsCreRevPhoneContact.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
        List<WmsCreRevPhoneMain> wmsCreRevPhoneMainList = wmscrerevphonemainService.getListByEntity(wmsCreRevPhoneMain);
        List<WmsCreRevPhoneContact> wmsCreRevPhoneContactList = wmscrerevphonecontactService.getList(wmsCreRevPhoneContact);
        WmsCreRevPhoneModel wmsCreRevPhoneModel = wmsCreRevPhoneModelService.getInfoByFK(Integer.parseInt(wms_cre_credit_head_id));
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("wmsCreRevPhoneMain", wmsCreRevPhoneMainList);
        resultMap.put("lxrqk", wmsCreRevPhoneContactList);
        resultMap.put("zysj", wmsCreRevPhoneModel);
        return resultMap;
    }

    /**
     * Description :信贷电审退件
     * 
     * @param bean
     * @author 张风山
     */
    @RequestMapping(value = "/loanreview/phonetoback.do", method = { RequestMethod.POST })
    @ResponseBody
    public String phoneToBack(HttpServletRequest request, WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevphonemainService.phoneToBack(approveWorkFlowVO, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "贷前管理-信贷审核-电审审核组-退件";
            SysTools.saveLog(request, msg); // 保存日志
        }
        return result;
    }

    /**
     * Description :房贷电审退件
     * 
     * @param bean
     * @author 张风山
     */
    @RequestMapping(value = "/loanreview/housephonetoback.do", method = { RequestMethod.POST })
    @ResponseBody
    public String housePhoneToBack(HttpServletRequest request, WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevphonemainService.housePhoneToBack(approveHouseWorkFlowVO, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "贷前管理-房贷审核-电审审核组-退件";
            SysTools.saveLog(request, msg); // 保存日志
        }
        return result;
    }

}