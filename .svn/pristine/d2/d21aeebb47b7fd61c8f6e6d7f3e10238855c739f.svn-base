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

import com.zx.emanage.inve.exception.InveTransException;
import com.zx.emanage.inve.exception.InveTransSysException;
import com.zx.emanage.inve.exception.InveTransaConcurrentException;
import com.zx.emanage.inve.service.IWmsInveTransaMatchService;
import com.zx.emanage.inve.vo.WmsInveTransaMatchSearchBeanVO;
import com.zx.emanage.loanappro.service.IWmsSysPropertyService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveTransaMatch;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.util.gen.entity.WmsSysProperty;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveTransaMatchController
{
    private static Logger log = LoggerFactory.getLogger(WmsInveTransaMatchController.class);

    @Autowired
    private IWmsInveTransaMatchService wmsinvetransamatchService;

    @Autowired
    private IWmsSysPropertyService wmsSysPropertyService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransamatchwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                      RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsInveTransaMatchSearchBeanVO queryInfo)
    {
        return wmsinvetransamatchService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransamatchwithpaginglist.do", method = { RequestMethod.GET,
                                                                                   RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsInveTransaMatchSearchBeanVO queryInfo)
    {
        return wmsinvetransamatchService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInveTransaMatchVO
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransamatchinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsInveTransaMatch getInfoByPK(Integer wms_inve_transa_match)
    {
        return wmsinvetransamatchService.getInfoByPK(wms_inve_transa_match);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransamatchsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsInveTransaMatch bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransamatchService.save(bean, user);
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
    @RequestMapping(value = "/inve/wmsinvetransamatchupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsInveTransaMatch bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransamatchService.update(bean, user);
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
     * 保存债权匹配信息
     * @param transaMatch 抵押类债权信息(房贷债权和车贷债权)
     * @param wmsInveTransaProd 上单产品信息
     * @param wms_inve_redeem_id 赎回ID
     * @param category_type 产品类型
     * @param bean
     * @return "success" or "error" or user defined
     * @author hancd
     */
    @RequestMapping(value = "/inve/wmsinvetransamatchsaveinfo.do", method = { RequestMethod.POST })
    @ResponseBody
    public String saveMatch(HttpServletRequest request, String transaMatch, WmsInveTransaProd wmsInveTransaProd,
                            String wms_inve_redeem_id,String category_type)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            System.out.println(transaMatch);
            // result = wmsinvetransamatchService.autoTransaMatch(90);
            result = wmsinvetransamatchService.saveMatch(transaMatch, wmsInveTransaProd, wms_inve_redeem_id, user,category_type);
        }
        catch (InveTransException e)
        {
            result = "nomatch";
        }
        catch(InveTransSysException e)
        {
        	result = "sysnomatch";
        }
        catch(InveTransaConcurrentException e)
        {
            result = "concurrenterror";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error(e.getMessage());
            result = "error";
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "业务管理-理财管理-债权匹配";
            SysTools.saveLog(request, msg);
        }
        return result;
    }

    /**
     * 债权匹配表中根据上单产品的id查询匹配人信息
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransamatchgetMapForLc.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<Map<String, Object>> getMapForLc(Integer wms_inve_transa_prod_id, Integer wms_inve_redeem_id)
    {
        return wmsinvetransamatchService.getMapForLc(wms_inve_transa_prod_id, wms_inve_redeem_id);
    }
    /**
     * 债权匹配表中根据债权调整的id查询匹配人信息
     * 
     * @param queryInfo
     * @return record list
     * @author jiaodelong
     */
    @RequestMapping(value = "/inve/wmsinvetransamatchgetMapForHeadid.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<Map<String, Object>> getMapForHeadid(Integer wms_inve_debt_head_id)
    {
        return wmsinvetransamatchService.getMapForHeadid(wms_inve_debt_head_id);
    }

    /**
     * 债权匹配表中根据上单产品的id查询匹配人信息
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransamatchgetMapForLcSh.do", method = { RequestMethod.GET,
                                                                                  RequestMethod.POST })
    @ResponseBody
    public List<Map<String, Object>> getMapForLcSh(Integer wms_inve_transa_prod_id)
    {
        return wmsinvetransamatchService.getMapForLcSh(wms_inve_transa_prod_id);
    }

    /**
     * 根据赎回单据表id获取部分赎回单据得信息，以便债权调整
     * 
     * @param queryInfo
     * @return record list
     * @author 张风山
     */
    @RequestMapping(value = "/inve/getInveTransaMatchList.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getInveTransaMatchList(Integer wms_inve_redeem_id)
    {
        return wmsinvetransamatchService.getInveTransaMatchList(wms_inve_redeem_id);
    }

    /**
     * 保存赎回单据(部分赎回)债权调整
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author 张风山
     */
    @RequestMapping(value = "/inve/wmsinvetransamatchsaveAllInfo.do", method = { RequestMethod.POST })
    @ResponseBody
    public String saveAllMatch(HttpServletRequest request, String tzInfo, String wms_inve_redeem_id)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransamatchService.saveAllMatch(tzInfo, wms_inve_redeem_id, user);
        }
        catch (InveTransException e)
        {
            result = "nomatch";
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        if ("success".equals(result))
        {
            String msg = "业务管理-理财管理-债权调整";
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }

    /**
     * 保存债权自动匹配规则并调整债权
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author 张风山
     */
    @RequestMapping(value = "/inve/wmsinvesavematchrule.do", method = { RequestMethod.POST })
    @ResponseBody
    public String saveMatchRule(HttpServletRequest request, String match_rule, String match_ratio)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransamatchService.saveMatchRule(match_rule, match_ratio, user);
        }
        catch (InveTransException e)
        {
            result = "nomatch";
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        if ("success".equals(result))
        {
            String msg = "系统管理-债权匹配规则";
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }

    /**
     * Description :通过传入属性表主键值
     * 
     * @param primary key
     * @return WmsInveTransaMatchVO
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsyspropertyinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsSysProperty getSysPropertyByPK(Integer wms_sys_property_id)
    {
        return wmsSysPropertyService.getInfoByPK(wms_sys_property_id);
    }
    
    /**
     * 债权匹配表中根据债权调整的对应协议表id查询对应信息
     * @date 2015-12-3
     * @param wms_inve_transa_protocol_id  
     * @return record list
     * @exception 
     * @author baisong
     */
    @RequestMapping(value = "/inve/getwmsinvetransamatchgetMapbyprotocolid.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<Map<String, Object>> getMapByProtocolid(Integer wms_inve_transa_protocol_id)
    {
        return wmsinvetransamatchService.getMapByProtocolid(wms_inve_transa_protocol_id);
    }

}