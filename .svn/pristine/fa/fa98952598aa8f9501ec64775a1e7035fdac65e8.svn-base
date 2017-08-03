package com.zx.emanage.inve.web;

import java.util.ArrayList;
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

import com.zx.emanage.inve.service.IWmsInveTransaProdService;
import com.zx.emanage.inve.vo.WmsInveTransaProdSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveTransaProdController
{
    private static Logger log = LoggerFactory.getLogger(WmsInveTransaProdController.class);

    @Autowired
    private IWmsInveTransaProdService wmsinvetransaprodService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransaprodwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsInveTransaProdSearchBeanVO queryInfo)
    {
        return wmsinvetransaprodService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransaprodwithpaginglist.do", method = { RequestMethod.GET,
                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsInveTransaProdSearchBeanVO queryInfo)
    {
        return wmsinvetransaprodService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInveTransaProdVO
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransaprodinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsInveTransaProd getInfoByPK(Integer wms_inve_transa_prod_id)
    {
        return wmsinvetransaprodService.getInfoByPK(wms_inve_transa_prod_id);
    }

    /**
     * Description :通过上单信息主键获取上单产品信息
     * 
     * @param primary key
     * @return WmsInveTransaProdVO
     * @author ry
     */
    @RequestMapping(value = "/inve/wmsinvetransaprodinfobypkforjegl.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsInveTransaProd getInfoByPKForJEGL(Integer wms_inve_transa_id)
    {
        return wmsinvetransaprodService.getInfoByPKForJEGL(wms_inve_transa_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransaprodsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsInveTransaProd bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransaprodService.save(bean, user);
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
    @RequestMapping(value = "/inve/wmsinvetransaprodupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsInveTransaProd bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransaprodService.update(bean, user);
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
     * 获取上单产品信息
     * @param queryInfo
     * @return record list
     * @author 张风山
     */
    @RequestMapping(value = "/inve/getinvetransaprodbyid.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListByID(HttpServletRequest request, WmsInveTransaProdSearchBeanVO queryInfo, String wms_inve_transa_prod_id)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaprodService.getListByID(wms_inve_transa_prod_id, user);
    }
    
    
    
    /**
     * Description :修改收益卡信息
     * 
     * @param bean
     * @author jiaodelong
     */
    @RequestMapping(value = "/inve/updateIncomeCard.do", method = { RequestMethod.POST })
    @ResponseBody
    public String updateIncomeCard(HttpServletRequest request,WmsInveTransaProd wmsTransaProd)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransaprodService.updateIncomeCard(user,wmsTransaProd);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "财务管理-金额到账-修改收益卡信息";
            SysTools.saveLog(request, msg);
        }
        return result;
    }
    
    /**
     * 
     * @Title: getWmsInveTransaProdInfos
     * @Description: 根据传入的客户姓名和身份证号获取对应的收益卡信息
     * @param customer_name 客户姓名
     * @param id_card_number 身份证号
     * @return 返回Map集合
     * @author: DongHao
     * @time:2016年12月16日 上午10:59:18
     * history:
     * 1、2016年12月16日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getWmsInveTransaProdInfosMoa.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getWmsInveTransaProdInfos(String customer_name, String id_card_number)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try
        {
            resultMap = wmsinvetransaprodService.getWmsInveTransaProdInfos(customer_name, id_card_number);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            resultMap.put("flag", "false");
            resultMap.put("data_list", new ArrayList<Map<String, Object>>());
        }
        return resultMap;
    }
    

}