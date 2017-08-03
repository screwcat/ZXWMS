package com.zx.emanage.inve.web;

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

import com.zx.emanage.inve.service.IWmsInvePosService;
import com.zx.emanage.util.gen.entity.WmsInvePos;
import com.zx.emanage.inve.vo.WmsInvePosSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInvePosController
{
    private static Logger log = LoggerFactory.getLogger(WmsInvePosController.class);

    @Autowired
    private IWmsInvePosService wmsinveposService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinveposwithoutpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsInvePosSearchBeanVO queryInfo)
    {
        return wmsinveposService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author ry
     */
    @RequestMapping(value = "/inve/wmsinveposwithoutpaginglistEnable.do", method = { RequestMethod.GET,
                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPagingEnable(WmsInvePosSearchBeanVO queryInfo)
    {
        queryInfo.setEnable_flag("1");
        return wmsinveposService.getListWithoutPaging(queryInfo);
    }

    /**
     * @Title: getListWithPaging 
     * @Description: 理财POS机查询（分页）
     * @param queryInfo
     * @return    
     * @return Map<String,Object>    
     * @throws
     * @author lvtu 
     * @date 2015年6月29日 下午3:55:28
     */
    @RequestMapping(value = "/inve/wmsinveposwithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsInvePosSearchBeanVO queryInfo)
    {
        return wmsinveposService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInvePosVO
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinveposinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsInvePos getInfoByPK(Integer wms_inve_pos_id)
    {
        return wmsinveposService.getInfoByPK(wms_inve_pos_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvepossave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsInvePos bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinveposService.save(bean, user);
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
     * Description :更改pos机信息
     * 
     * @param beanJSON
     * @return "success" or "error" or user defined
     * @author ry
     */
    @RequestMapping(value = "/inve/wmsinveposdochange.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doChangePos(HttpServletRequest request, String beanJSON)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinveposService.doChangePos(beanJSON, user);
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
    @RequestMapping(value = "/inve/wmsinveposupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsInvePos bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinveposService.update(bean, user);
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
     * Description :获取POS机所有信息
     * 
     * @param primary key
     * @return WmsInvePosVO
     * @author zhubo
     */
    @RequestMapping(value = "/inve/getallwmsinveposinfo.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<WmsInvePos> getAllInvePosInfo()
    {
        List<WmsInvePos> list = wmsinveposService.getAllInvePosInfo();
        return list;
    }
}