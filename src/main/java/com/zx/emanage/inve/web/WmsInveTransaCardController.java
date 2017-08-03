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

import com.zx.emanage.inve.service.IWmsInveTransaCardService;
import com.zx.emanage.inve.service.IWmsInveTransaService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveTransaCard;
import com.zx.emanage.inve.vo.WmsInveTransaCardSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveTransaCardController
{
    private static Logger log = LoggerFactory.getLogger(WmsInveTransaCardController.class);

    @Autowired
    private IWmsInveTransaCardService wmsinvetransacardService;
    
    @Autowired
    private IWmsInveTransaService wmsInveTransaService; 

    /**
     * @Title:getListWithoutPaging
     * Description :【财务管理>理财上单>金额到账】 列表显示
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     * @date 2015年12月17日 15:16
     */
    @RequestMapping(value = "/inve/wmsinvetransacardwithpaginglist.do", method = { RequestMethod.GET,
                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(HttpServletRequest request,WmsInveTransaCardSearchBeanVO queryInfo)
    {
    	HttpSession session =request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransacardService.getListWithPaging(queryInfo,user);
    }
    
    /**
     * @Title:getListWithPaging
     * Description :【财务管理】【理财上单】【金额到账】 列表导出
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     * @date 2015年12月17日 15:16
     */
    @RequestMapping(value = "/inve/wmsinvetransacardwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(HttpServletRequest request,WmsInveTransaCardSearchBeanVO queryInfo)
    {
    	HttpSession session =request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransacardService.getListWithoutPaging(queryInfo,user);
    }
    
    /**
     * @Title :doUpdateForJEDZ
     * 
     * Description :【财务管理】【理财上单】【金额到账】 数据处理保存操作
     * 
     * @param bean
     * 
     * @return "success" or "error" or user defined
     * 
     * @author hancd
     * 
     * @date 2015年12月17日 16:49
     */
    @RequestMapping(value = "/inve/wmsinvetransacardupdateforjedz.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdateForJEDZ(HttpServletRequest request, String wms_inve_transa_id, String itcardJSON,
                                  String tzje, String payTypeStr)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransacardService.updateForJEDZ(user, wms_inve_transa_id, itcardJSON, tzje, payTypeStr);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        if ("success".equals(result))
        {
            String msg = "财务管理>理财上单>金额到账";
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }
    
    /**
     * Description :通过上单信息主键获取数据
     * 
     * @param queryInfo
     * @return record list
     * @author ry
     */
    @RequestMapping(value = "/inve/wmsinvetransacardforjedzwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                            RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPagingForJEDZ(WmsInveTransaCardSearchBeanVO queryInfo)
    {
        return wmsinvetransacardService.getListWithoutPagingForJEDZ(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInveTransaCardVO
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransacardinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsInveTransaCard getInfoByPK(Integer wms_inve_transa_card_id)
    {
        return wmsinvetransacardService.getInfoByPK(wms_inve_transa_card_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransacardsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsInveTransaCard bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransacardService.save(bean, user);
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
    @RequestMapping(value = "/inve/wmsinvetransacardupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsInveTransaCard bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransacardService.update(bean, user);
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
     * @Title:getInfoByFK
     * Description:【理财上单流程】 【财务管理】-【理财上单】-【对账报表】 账单详情
     * @param wms_inve_transa_id
     * @return WmsInveTransaCardVO
     * @author hancd
     * @date 2015年12月17日 14:39
     */
    @RequestMapping(value = "/inve/wmsinvetransacardinfobyfk.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getInfoByFK(Integer wms_inve_transa_id)
    {
        return wmsinvetransacardService.getInfoByFK(wms_inve_transa_id);
    }
    
    /**
     * @Deprecated 根据上单信息id获取对应单据的支付信息
     * @param wms_inve_transa_id
     * @return
     * @author donghao
     * @date 2016年9月1日10:31:40
     */
    @RequestMapping(value="/inve/wmsinvetransaforzfinfolist.do", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> findZfInfo(Integer wms_inve_transa_id){
    	return wmsinvetransacardService.findZfInfoByWmsInveTransaId(wms_inve_transa_id);
    }
    
    /**
     * 根据传入的上单信息表主键获取对应的单据的支付信息,需要将单据为续期本金方式的数据的试用金额释放掉
     * @param wms_inve_transa_id
     * @author donghao
     * @date 2016年9月16日17:18:53
     */
    @RequestMapping(value="/inve/wmsinvetransaforfreedredeembill.do", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void freedRedeemBill(Integer wms_inve_transa_id, Integer wms_inve_redeem_principal_detail_id, HttpServletRequest request){
    	HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
    	wmsInveTransaService.wmsInveTransaInvalidByWmsInveRedeemPrincipalDetailId(wms_inve_transa_id,wms_inve_redeem_principal_detail_id, user);
    }
}