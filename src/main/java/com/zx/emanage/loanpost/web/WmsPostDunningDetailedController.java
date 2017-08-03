package com.zx.emanage.loanpost.web;

import java.util.List;
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

import com.zx.emanage.loanpost.service.IWmsPostDunningDetailedService;
import com.zx.emanage.util.gen.entity.WmsPostDunningDetailed;
import com.zx.emanage.loanpost.vo.WmsPostDunningDetailedSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsPostDunningDetailedController {
    private static Logger log = LoggerFactory.getLogger(WmsPostDunningDetailedController.class);
    
    @Autowired
    private IWmsPostDunningDetailedService wmspostdunningdetailedService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanpost/wmspostdunningdetailedwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsPostDunningDetailedSearchBeanVO queryInfo) {
        return wmspostdunningdetailedService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanpost/wmspostdunningdetailedwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsPostDunningDetailedSearchBeanVO queryInfo) {
        return wmspostdunningdetailedService.getListWithPaging(queryInfo);
    }
    
    /**
     * Description :get single-table information by primary key 
     * @param primary key 
     * @return WmsPostDunningDetailedVO
     * @author auto_generator
     */ 
    @RequestMapping(value = "/loanpost/wmspostdunningdetailedinfobypk.do", method = {RequestMethod.GET})
    @ResponseBody
    public WmsPostDunningDetailed getInfoByPK(Integer wms_post_dunning_detailed_id) {
        return wmspostdunningdetailedService.getInfoByPK(wms_post_dunning_detailed_id);
    }   

    /**
     * Description :add method
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */ 
    @RequestMapping(value = "/loanpost/wmspostdunningdetailedsave.do", method = {RequestMethod.POST})
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsPostDunningDetailed bean) {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        try {
            result = wmspostdunningdetailedService.save(bean, user);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = "error";
        }
        /*
        // record log   
        if("success".equals(result)){
            String msg = "log content";
            SysTools.saveLog(request, msg); // record log method
        }
        */
        return result;
    }

    /**
     * Description :update method
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */ 
    @RequestMapping(value = "/loanpost/wmspostdunningdetailedupdate.do", method = {RequestMethod.POST})
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsPostDunningDetailed bean) {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        try {
            result = wmspostdunningdetailedService.update(bean, user);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = "error";
        }
        /*          
        // record log   
        if("success".equals(result)){
            String msg = "log content";
            SysTools.saveLog(request, msg); // record log method
        }
        */
        return result;
    }
    /**
     * Description :通过催缴单ID获取详细信息
     * @param primary key 
     * @return WmsPostDunningHeadVO
     * @author hancd
     */ 
     @RequestMapping(value = "/loanpost/wmspostdunningdetailedbypk.do", method = {RequestMethod.GET})
     @ResponseBody
     public List<WmsPostDunningDetailed> getWmsPostDunningDetailedInfoByPK(Integer wms_post_dunning_head_id) {
         return wmspostdunningdetailedService.getWmsPostDunningDetailedInfoByPK(wms_post_dunning_head_id);
     }
    /**
     * Description :getlist
     * @param bean
     * @return "success" or "error" or user defined
     * @author baisong
     */ 
    @RequestMapping(value = "/loanpost/getListByEntity.do", method = {RequestMethod.GET})
    @ResponseBody
    private List<WmsPostDunningDetailed> getListByEntity(WmsPostDunningDetailed queryInfo, Boolean isExcludePKFlag) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsPostDunningDetailed> beanList = wmspostdunningdetailedService.getListByEntity(queryInfo,isExcludePKFlag);
        return beanList;
    }
}