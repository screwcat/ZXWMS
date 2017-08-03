package com.zx.emanage.inve.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jodd.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInveRedeemDetailService;
import com.zx.emanage.inve.service.IWmsInveReplaceService;
import com.zx.emanage.inve.service.impl.WmsInveReplaceServiceImpl;
import com.zx.emanage.sysmanage.persist.SysOrganizationDao;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveRedeemDetail;
import com.zx.emanage.util.gen.entity.WmsInveReplace;
import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.emanage.inve.vo.WmsInveRedeemDetailSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveRedeemDetailController
{
    private static Logger log = LoggerFactory.getLogger(WmsInveRedeemDetailController.class);

    @Autowired
    private IWmsInveRedeemDetailService wmsinveredeemdetailService;
    
    @Autowired
	private IWmsInveReplaceService wmsinvereplaceService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinveredeemdetailwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsInveRedeemDetailSearchBeanVO queryInfo)
    {
        return wmsinveredeemdetailService.getListWithoutPaging(queryInfo);
    }

    /**
     * @Title:getListWithoutPagingBywiri
     * 
     * Description :通过赎回表主键，获取赎回明细表数据
     * 
     * @param queryInfo
     * @return record list
     * @author ry
     */
    @RequestMapping(value = "/inve/wmsinveredeemdetailwithoutpaginglistbywiri.do", method = { RequestMethod.GET,
                                                                                             RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPagingBywiri(WmsInveRedeemDetailSearchBeanVO queryInfo)
    {
        return wmsinveredeemdetailService.getListWithoutPagingBywiri(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinveredeemdetailwithpaginglist.do", method = { RequestMethod.GET,
                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsInveRedeemDetailSearchBeanVO queryInfo)
    {
        return wmsinveredeemdetailService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInveRedeemDetailVO
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinveredeemdetailinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsInveRedeemDetail getInfoByPK(Integer wms_inve_redeem_detail_id)
    {
        return wmsinveredeemdetailService.getInfoByPK(wms_inve_redeem_detail_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinveredeemdetailsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsInveRedeemDetail bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinveredeemdetailService.save(bean, user);
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
     * @title:doUpdate
     * 
     * Description :理财赎回三级审批
     * 
     * @param request
     * 
     * @param wms_inve_redeem_detail 赎回明细表
     * 
     * @param aInveWorkFlowVO 赎回流程
     * 
     * @param wms_inve_redeem_data 赎回主表
     * 
     * @return "success" or "error" or user defined
     * 
     * @author hancd
     */
    @RequestMapping(value = "/inve/wmsinveredeemdetailupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsInveRedeemDetail wms_inve_redeem_detail,
    						WmsInveDebtWorkFlowVO wDebtWorkFlowVO, WmsInveRedeem wInveRedeem,String redeemGridData)
    {
        String result = "";
        // 获取当前登录用户
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinveredeemdetailService.update(wms_inve_redeem_detail, user, wDebtWorkFlowVO, wInveRedeem,redeemGridData);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 记录日志
        if ("success".equals(result))
        {
            String msg = "业务管理--理财管理--赎回审核";
            SysTools.saveLog(request, msg);
        }
        // 返回执行结果
        return result;
    }
    
    
    /**
     * @title:doUpdate
     * 
     * Description :理财赎回特批
     * 
     * @param request
     * 
     * @param wms_inve_redeem_detail 赎回明细表
     * 
     * @param aInveWorkFlowVO 赎回流程
     * 
     * @param wms_inve_redeem_data 赎回主表
     * 
     * @return "success" or "error" or user defined
     * 
     * @author jiaodelong
     */
    @RequestMapping(value = "/inve/wmsinvespecialRedemptionUpdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String specialRedemptionUpdate(HttpServletRequest request, WmsInveRedeemDetail wms_inve_redeem_detail,
    						WmsInveDebtWorkFlowVO wDebtWorkFlowVO, WmsInveRedeem wInveRedeem,String redeemGridData)
    {
        String result = "";
        // 获取当前登录用户
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinveredeemdetailService.specialRedemptionUpdate(wms_inve_redeem_detail, user, wDebtWorkFlowVO, wInveRedeem,redeemGridData);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 记录日志
        if ("success".equals(result))
        {
            String msg = "业务管理--理财管理--赎回特批";
            SysTools.saveLog(request, msg);
        }
        // 返回执行结果
        return result;
    }

    /**
     * Description :通过赎回单据主键查询出多条赎回明细单据信息 以供打印多条合同
     * 
     * @param primary key
     * @return WmsInveRedeemDetailVO
     * @author baisong
     */
    @RequestMapping(value = "/inve/wmsinveredeemdetaillistbypk.do", method = { RequestMethod.POST })
    @ResponseBody
    public List<WmsInveRedeemDetail> getListByPK(Integer wms_inve_redeem_id)
    {
        List<WmsInveRedeemDetail> list = wmsinveredeemdetailService.getListByPK(wms_inve_redeem_id);
        return list;
    }

    /**
     * Description :通过赎回单据主键查询出赎回明细与单据表信息
     * 
     * @param primary key
     * @return map
     * @author zhubo
     */
    @RequestMapping(value = "/inve/wmsinveredeemallinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<Map<String, Object>> getRedeemAllInfo(Integer wms_inve_redeem_id)
    {
        return wmsinveredeemdetailService.getRedeemAllInfo(wms_inve_redeem_id);
    }
    /**
     * Description :doUpdateProduct 领导审核修改--产品变更
     * 
     * @param request
     * @param wms_inve_redeem_detail 赎回明细表
     * @param aInveWorkFlowVO 赎回流程
     * @param wms_inve_redeem_data 赎回主表
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @RequestMapping(value = "/inve/wmsinveredeemdetailupdateproduct.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdateProduct(HttpServletRequest request, String wms_inve_redeem_detail,
    		WmsInveDebtWorkFlowVO wDebtWorkFlowVO, WmsInveRedeem wInveRedeem)
    {
        String result = "";
        // 获取当前登录用户
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinveredeemdetailService.updateProduct(wms_inve_redeem_detail, user, wDebtWorkFlowVO, wInveRedeem);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 记录日志
        if ("success".equals(result))
        {
            String msg = "业务管理--理财管理--赎回审核";
            SysTools.saveLog(request, msg);
        }
        // 返回执行结果
        return result;
    }
    /**
     * @title:doUpdateforphone
     * Description :理财赎回三级审批-给手机赎回审批使用
     * @param user_id 
     * @param aInveWorkFlowVO 赎回流程
     * @param wms_inve_redeem_id 赎回主表 
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @RequestMapping(value = "/inve/wmsinveredeemdetaildoUpdateforphone.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String,Object> doUpdateforphone(HttpServletRequest request,String personnel_shortcode,
    		WmsInveDebtWorkFlowVO wDebtWorkFlowVO,String wms_inve_redeem_id,String wms_inve_replace_id)
    {
    	Map<String,Object> map=new HashMap<>();
        String result = "";
        if(StringUtil.isBlank(wms_inve_redeem_id)){
        	WmsInveReplace bean = new WmsInveReplace();
        	bean.setWms_inve_replace_id(new Integer(wms_inve_replace_id));
        	wmsinvereplaceService.zjlsp(bean, null, wDebtWorkFlowVO.getPass());
        	map.put("result", "success");
 	        map.put("message", "总经理确认成功");
        	map.put("flag", true);
        	return map;
        }
        try
        {
        	map = wmsinveredeemdetailService.doUpdateforphone(personnel_shortcode, wDebtWorkFlowVO, wms_inve_redeem_id);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
            map.put("flag", false);
            map.put("result", result);
            map.put("message", e.getMessage());
        }
        // 记录日志
        if ("success".equals(result))
        {
            String msg = "业务管理--理财管理--手机赎回审核";
            SysTools.saveLog(request, msg);
        }
        // 返回执行结果
        return map;
    }

}