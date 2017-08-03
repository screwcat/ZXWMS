package com.zx.emanage.remind.web;


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




import com.zx.emanage.remind.service.IWmsCreCreditNotarySetService;
import com.zx.emanage.remind.vo.WmsCreCreditNotarySetSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditNotarySet;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @author administrator
 */
@Controller
public class WmsCreCreditNotarySetController {
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditNotarySetController.class);

    @Autowired
    private IWmsCreCreditNotarySetService wmscrecreditnotarysetService;

    /**
     * Description : 查询列表(不带分页)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    @RequestMapping(value = "/inve/SearchWmsCreCreditNotarySetWithoutPagingList.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCreditNotarySetSearchBeanVO queryInfo) {
        return wmscrecreditnotarysetService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description : 查询列表(带分页)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    @RequestMapping(value = "/inve/SearchWmsCreCreditNotarySetWithPagingList.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCreditNotarySetSearchBeanVO queryInfo) {
        return wmscrecreditnotarysetService.getListWithPaging(queryInfo);
    }

    /**
     * Description : 根据主键查询
     * 
     * @param primary key
     * @return WmsCreCreditNotarySetVO
     * @author administrator
     */
    @RequestMapping(value = "/inve/WmsCreCreditNotarySetGetInfoByPK.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public WmsCreCreditNotarySet getInfoByPK(Integer WmsCreCreditNotarySet_id) {
        return wmscrecreditnotarysetService.getInfoByPK(WmsCreCreditNotarySet_id);
    }

    /**
     * Description : 新增
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author administrator
     */
    @RequestMapping(value = "/remind/WmsCreCreditNotarySetSave.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCreditNotarySet bean) {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try {
            result = wmscrecreditnotarysetService.save(bean, user);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            result = "error";
        }
        return result;
    }

    /**
     * Description : 修改
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author administrator
     */
    @RequestMapping(value = "/inve/WmsCreCreditNotarySetUpdate.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCreditNotarySet bean) {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try {
            result = wmscrecreditnotarysetService.update(bean, user);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            result = "error";
        }
        return result;
    }
    /**
     * Description : 数据初始化
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    @RequestMapping(value = "/remind/getWmsCreCreditNotarySetInfo.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public WmsCreCreditNotarySet getWmsCreCreditNotarySetInfo() {
        return wmscrecreditnotarysetService.getWmsCreCreditNotarySetInfo();
    }
    
    
}
