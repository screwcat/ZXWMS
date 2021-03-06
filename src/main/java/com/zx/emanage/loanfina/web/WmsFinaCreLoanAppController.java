package com.zx.emanage.loanfina.web;

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

import com.zx.emanage.cremanage.vo.WmsCreCreditHeadHouseSearchBeanVO;
import com.zx.emanage.loanfina.service.IWmsFinaCreLoanAppService;
import com.zx.emanage.loanfina.service.IWmsFinaCreRepayService;
import com.zx.emanage.loanfina.vo.WmsFinaCreCancelBeanVo;
import com.zx.emanage.loanfina.vo.WmsFinaCreLoanAppSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsFinaCreLoanApp;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * @ClassName: WmsFinaCreLoanAppController
 * @Description: 内容摘要：放款申请相关Controller
 * @author wangyihan
 * @date 2016年12月23日
 * @version V1.0
 * history:
 * 1、2016年12月23日 wangyihan 创建文件
 */

@Controller
public class WmsFinaCreLoanAppController
{
    private static Logger log = LoggerFactory.getLogger(WmsFinaCreLoanAppController.class);

    @Autowired
    private IWmsFinaCreLoanAppService wmsfinacreloanappService;
    
    @Autowired
    private IWmsFinaCreRepayService wmsfinacrerepayService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanfina/wmsfinacreloanappwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                         RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsFinaCreLoanAppSearchBeanVO queryInfo)
    {
        return wmsfinacreloanappService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanfina/wmsfinacreloanappwithpaginglist.do", method = { RequestMethod.GET,
                                                                                      RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsFinaCreLoanAppSearchBeanVO queryInfo)
    {
        return wmsfinacreloanappService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsFinaCreLoanAppVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanfina/wmsfinacreloanappinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsFinaCreLoanApp getInfoByPK(Integer wms_fina_cre_loan_app)
    {
        return wmsfinacreloanappService.getInfoByPK(wms_fina_cre_loan_app);
    }

    /**
     * Description :放款申请进行数据保存
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loanfina/wmsfinacreloanappsave.do", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsFinaCreLoanApp bean, int wms_cre_credit_head_id, String taskId,
                         String fileArr)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsfinacreloanappService.save(bean, user, wms_cre_credit_head_id, taskId, fileArr);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "终审管理>信贷终审>放款申请";
            SysTools.saveLog(request, msg);
        }
        return result;
    }
    /**
     * Description :放款申请--终止合同
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loanfina/wmsfinacreloanappsaveCancel.do", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String doSaveCancel(HttpServletRequest request, WmsFinaCreCancelBeanVo bean, WmsCreCreditHead hbean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsfinacreloanappService.doSaveCancel(bean,user,hbean);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "终审管理>信贷终审>放款申请>合同作废";
            SysTools.saveLog(request, msg);
        }
        return result;
    }

    @RequestMapping(value = "/loanfina/wmsfinacrehousingloanappsave.do", method = { RequestMethod.POST,
                                                                                   RequestMethod.GET })
    @ResponseBody
    public String housingMakeLoansSave(HttpServletRequest request, WmsFinaCreLoanApp bean, WmsCreCreditHeadHouseSearchBeanVO beanvo)
    {
        String result = "success";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsfinacreloanappService.housingLoanAppSave(bean, user, beanvo);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return "error";
        }
        return result;
    }
    
    /**
     * 
     * @Title: housingMakeLoansSaveForCobine
     * @Description: discrpition：放款申请组合贷保存
     * @param request
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2016年12月26日 下午5:03:03
     * history:
     * 1、2016年12月26日 wangyihan 创建方法
     */
    @RequestMapping(value = "/loanfina/housingMakeLoansSaveForCobine.do", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String housingMakeLoansSaveForCobine(HttpServletRequest request, WmsFinaCreLoanAppSearchBeanVO queryInfo)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            queryInfo = wmsfinacrerepayService.housingMakeLoansSaveForCobine(queryInfo, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return "error";
        }
        return queryInfo.getResult();
    }
    
    /**
     * 
     * @Title: searchCombineLoanList
     * @Description: 查询组合贷单据
     * @param paramMap
     * @return 
     * @author: wangyihan
     * @time:2017年1月10日 下午2:03:30
     * history:
     * 1、2017年1月10日 wangyihan 创建方法
     */
    @RequestMapping(value = "/loanfina/searchCombineLoanList.do", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> searchCombineLoanList(HttpServletRequest request, WmsFinaCreLoanAppSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            queryInfo = wmsfinacrerepayService.searchCombineLoanList(queryInfo, user);
            resMap.put("combineLoanList", queryInfo.getCombineLoanList());
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }
        return resMap;
    }
    
    /**
     * 车贷放款申请
     * @param request
     * @param bean
     * @param wms_cre_credit_head_id
     * @param taskId
     * @param fileArr
     * @return String
     * baisong
     */
    @RequestMapping(value = "/loanfina/wmsfinacrecarloanappsave.do", method = { RequestMethod.POST,
            RequestMethod.GET })
		@ResponseBody
		public String carMakeLoansSave(HttpServletRequest request, WmsFinaCreLoanApp bean, int wms_cre_credit_head_id,
		String taskId, String fileArr)
			{
			String result = "success";
			HttpSession session = request.getSession();
			UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
				try
				{
					result = wmsfinacreloanappService.carLoanAppSave(bean, user, taskId, fileArr);
				}
					catch (Exception e)
				{
					log.error(e.getMessage());
					return "error";
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
    @RequestMapping(value = "/loanfina/wmsfinacreloanappupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsFinaCreLoanApp bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsfinacreloanappService.update(bean, user);
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
     * moa调用wms方法:放款申请结果保存
     * @param request
     * @param WmsMoaHouseInfoVO bean
     * @return 
     */
    @RequestMapping(value = "/cremanage/sendLoanApprovalInfoUp.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String,Object> sendLoanApprovalInfoUp(HttpServletRequest request, WmsFinaCreLoanAppSearchBeanVO bean) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = wmsfinacreloanappService.sendLoanApprovalInfoUp(bean);
            resMap.put("result", "success");
            resMap.put("message","操作成功");
            resMap.put("flag", true);
        } catch(Exception e) {
            resMap.put("result", "error");
            resMap.put("flag", false);
            resMap.put("message", e.getMessage());
        }
        return map;
    }
    
    /**
     * moa调用wms方法:放款申请结果保存(组合贷)
     * @param request
     * @param WmsMoaHouseInfoVO bean
     * @return 
     */
    @RequestMapping(value = "/cremanage/sendLoanApprovalInfoUpAgain.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String,Object> sendLoanApprovalInfoUpAgain(HttpServletRequest request, WmsFinaCreLoanAppSearchBeanVO bean) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = wmsfinacreloanappService.sendLoanApprovalInfoUpAgain(bean);
            resMap.put("result", "success");
            resMap.put("message","操作成功");
            resMap.put("flag", true);
        } catch(Exception e) {
            resMap.put("result", "error");
            resMap.put("flag", false);
            resMap.put("message", e.getMessage());
        }
        return map;
    }
    
    /**
     * 
     * @Title: initCombineLoanInfo
     * @Description: discription：放款申请组合贷初始化信息
     * @param request
     * @param bean
     * @return 
     * @author: wangyihan
     * @time:2016年12月23日 下午4:12:47
     * history:
     * 1、2016年12月23日 wangyihan 创建方法
     */
    @RequestMapping(value = "/cremanage/initCombineLoanInfo.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> initCombineLoanInfo(HttpServletRequest request, WmsFinaCreLoanAppSearchBeanVO queryInfo) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            queryInfo = wmsfinacreloanappService.initCombineLoanInfo(queryInfo);
            resMap.put("list", queryInfo.getInitCombineLoanInfoMap());
        } catch(Exception e) {
            log.error(e.getMessage());
        }
        return resMap;
    }
    /**
     * 
     * @Title: carMakeLoansSave
     * @Description: TODO(提交放款)
     * @param request
     * @param wms_cre_credit_head_id
     * @return 
     * @author: jiaodelong
     * @time:2017年2月27日 上午9:39:44
     * history:
     * 1、2017年2月27日 jiaodelong 创建方法
     */
    @RequestMapping(value = "/loanappro/wmsFinaCreLoanAppSaveForPerfact.do", method = { RequestMethod.POST,RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> carMakeLoansSave(HttpServletRequest request, WmsFinaCreLoanAppSearchBeanVO bean)
        {
        String result = "success";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        Map<String, Object> map = new HashMap<>();
        try
        {
            map = wmsfinacreloanappService.wmsFinaCreLoanAppSaveForPerfact(bean, user);
            map.put("result", result);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            map.put("result", "error");
            return map;
        }
        return map;
    }

    
}