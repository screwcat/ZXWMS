package com.zx.emanage.loanappro.web;

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

import com.zx.emanage.loanappro.service.IWmsCreApproServiceProtocolService;
import com.zx.emanage.loanappro.vo.WmsCreApproServiceProtocolSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreApproServiceProtocol;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreApproServiceProtocolController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreApproServiceProtocolController.class);

    @Autowired
    private IWmsCreApproServiceProtocolService wmscreapproserviceprotocolService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanappro/wmscreapproserviceprotocolwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                                   RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreApproServiceProtocolSearchBeanVO queryInfo)
    {
        return wmscreapproserviceprotocolService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanappro/wmscreapproserviceprotocolwithpaginglist.do", method = { RequestMethod.GET,
                                                                                                RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreApproServiceProtocolSearchBeanVO queryInfo)
    {
        return wmscreapproserviceprotocolService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreApproServiceProtocolVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanappro/wmscreapproserviceprotocolinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreApproServiceProtocol getInfoByPK(Integer wms_cre_appro_service_protocol_id)
    {
        return wmscreapproserviceprotocolService.getInfoByPK(wms_cre_appro_service_protocol_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loanappro/wmscreapproserviceprotocolsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreApproServiceProtocol bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscreapproserviceprotocolService.save(bean, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        if ("success".equals(result))
        {
            String msg = "终审管理-信贷终审-终审面签-居间服务协议保存";
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
    @RequestMapping(value = "/loanappro/wmscreapproserviceprotocolupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreApproServiceProtocol bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscreapproserviceprotocolService.update(bean, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        if ("success".equals(result))
        {
            String msg = "终审管理-信贷终审-终审面签-居间服务协议更新";
            SysTools.saveLog(request, msg); // record log method
        }

        return result;
    }

    /**
     * 居间服务协议信息查询
     * 
     * @param
     * @return
     * @author 张风山
     */
    @RequestMapping(value = "/loancheck/wmscreapproserviceprotocolQuery.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> showTelTeamCheck(HttpServletRequest request, String wms_cre_credit_head_id)
    {

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscreapproserviceprotocolService.selectAllByWmsCreCreditHeadId(wms_cre_credit_head_id, user);
    }

    /**
     * 判断居间服务协议编号是否重复
     * 
     * @param
     * @return
     * @author 张风山
     */
    @RequestMapping(value = "/loancheck/wmscreapproserviceprotocolJudgeProtocolId.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> telTeamCheckJudgeProtocolId(HttpServletRequest request, String judgeString)
    {

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscreapproserviceprotocolService.selectAllByProtocolId(judgeString, user);
    }
}