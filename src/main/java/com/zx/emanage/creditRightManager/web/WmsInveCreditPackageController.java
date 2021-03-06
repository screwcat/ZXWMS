package com.zx.emanage.creditRightManager.web;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.creditRightManager.service.IWmsInveCreditPackageService;
import com.zx.emanage.creditRightManager.service.WmsInveMulticreInfoService;
import com.zx.emanage.creditRightManager.vo.WmsCredRightNotifyCustomerSearchBeanVo;
import com.zx.emanage.creditRightManager.vo.WmsInveCreditPackageSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveCreditPackage;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * @ClassName: WmsInveCreditPackageController
 * @Description: 债券包查询
 * @author WangShuai
 * @date 2017年2月8日
 * @version V1.0
 * history:
 * 1、2017年2月8日 WangShuai 创建文件
 */
@Controller
public class WmsInveCreditPackageController {
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(WmsInveCreditPackageController.class);
	
	@Autowired
	private IWmsInveCreditPackageService wmsinvecreditpackageService;

    @Autowired
    private WmsInveMulticreInfoService wmsInveMulticreInfoService;


	
	/**
	 * @Title: getListWithPaging
	 * @Description: 查询
	 * @param queryInfo
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月8日 下午1:37:23
	 * history:
	 * 1、2017年2月8日 WangShuai 创建方法
	*/
	@RequestMapping(value = "/creRigMan/wmsinvecreditpackagewithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveCreditPackageSearchBeanVO queryInfo) {
		return wmsinvecreditpackageService.getListWithPaging(queryInfo);
	}
	
	/**
	 * @Title: getAllLocalNumList
	 * @Description: 获取地区下拉框
	 * @param request
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月20日 下午4:40:00
	 * history:
	 * 1、2017年2月20日 WangShuai 创建方法
	*/
	@RequestMapping(value ="/creRigMan/getAllLocalNumList.do",method ={RequestMethod.GET})
	@ResponseBody
	public List<Map<String,Object>> getAllLocalNumList(HttpServletRequest request){
		List<Map<String,Object>> resList = wmsinvecreditpackageService.getAllLocalNumList();
	    return resList;
	}
	
	/**
	 * @Title: getAllRelePerList
	 * @Description: 获取他项人下拉框
	 * @param request
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月20日 下午4:40:11
	 * history:
	 * 1、2017年2月20日 WangShuai 创建方法
	*/
	@RequestMapping(value ="/creRigMan/getAllRelePerList.do",method ={RequestMethod.GET})
	@ResponseBody
	public List<Map<String,Object>> getAllRelePerList(HttpServletRequest request){
		List<Map<String,Object>> resList = wmsinvecreditpackageService.getAllRelePerList();
	    return resList;
	}

    @RequestMapping(value = "/inve/updateWmsInveCreditPackage.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String updateWmsInveCreditPackage(WmsInveCreditPackage bean, HttpServletRequest request)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvecreditpackageService.update(bean, user);
    }

    /**
     * @Title: getNotifyCustomerForCreditEnd
     * @Description: 查询抵押包到期的客户单据信息
     * @param request 请求信息
     * @param searchBeanVo 查询条件
     * @return 抵押包到期的客户单据信息
     * @author: jinzhm
     * @time:2017年3月13日 上午10:12:39
     * history:
     * 1、2017年3月13日 jinzhm 创建方法
     */
    @RequestMapping(value = "/creRigMan/getNotifyCustomerForCreditEnd.do", method = { RequestMethod.GET,
                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getNotifyCustomerForCreditEnd(HttpServletRequest request,
                                                             WmsCredRightNotifyCustomerSearchBeanVo searchBeanVo)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvecreditpackageService.getNotifyCustomerForCreditEnd(searchBeanVo, user);
    }
    
    /**
     * @Title: getNotifyCustomerForCreditEndWithoutPaging
     * @Description: 无分页查询抵押包到期的客户单据信息
     * @param request 请求信息
     * @param searchBeanVo 查询条件
     * @return 抵押包到期的客户单据信息集合
     * @author: jinzhm
     * @time:2017年6月28日 下午2:27:34
     * history:
     * 1、2017年6月28日 jinzhm 创建方法
     */
    @RequestMapping(value = "/creRigMan/getNotifyCustomerForCreditEndWithoutPaging.do", method = { RequestMethod.GET,
                                                                                                  RequestMethod.POST })
    @ResponseBody
    public List<Map<String, Object>> getNotifyCustomerForCreditEndWithoutPaging(HttpServletRequest request,
                                                                          WmsCredRightNotifyCustomerSearchBeanVo searchBeanVo)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvecreditpackageService.getNotifyCustomerForCreditEndWithoutPaging(searchBeanVo, user);
    }
    
    /**
     * @Title: notifyCustomerForCreditEnd
     * @Description: 导入excel进行债权到期短信发送
     * @param request
     * @param response
     * @return 
     * @author: jinzhm
     * @time:2017年6月8日 下午6:13:51
     * history:
     * 1、2017年6月8日 jinzhm 创建方法
     */
    @RequestMapping(value = "/creRigMan/notifyCustomerForCreditEnd.do")
    @ResponseBody
    public Map<String, Object> notifyCustomerForCreditEnd(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvecreditpackageService.notifyCustomerForCreditEnd(request, response, user);
    }

    /**
     * @Title: sendMessageToCustomerForCreditEnd
     * @Description: 给客户发送短信通知单据的债权到期
     * @param request 请求信息
     * @param data 客户及合同编号等相关数据
     * @return 成功标志
     * @author: jinzhm
     * @time:2017年3月14日 上午9:57:09
     * history:
     * 1、2017年3月14日 jinzhm 创建方法
     */
    @RequestMapping(value = "/creRigMan/sendMessageToCustomerForCreditEnd.do", method = { RequestMethod.GET,
                                                                                         RequestMethod.POST })
    @ResponseBody
    public String sendMessageToCustomerForCreditEnd(HttpServletRequest request, String data)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            wmsinvecreditpackageService.sendMessageToCustomerForCreditEnd(data, user);
        }
        catch (Exception e)
        {
            return "error";
        }
        return "success";
    }
    
    /**
     * @Title: handleOfflineImportCredit
     * @Description: 线下债权匹配数据处理
     * @param request 
     * @author: jinzhm
     * @time:2017年4月11日 上午9:57:30
     * history:
     * 1、2017年4月11日 jinzhm 创建方法
     */
    @RequestMapping(value = "/creRigMan/handleOfflineImportCredit.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public void handleOfflineImportCredit(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        wmsInveMulticreInfoService.handleOfflineImportCredit(user);
    }

    /**
     * @Title: destroyCreditPackage
     * @Description: 作废某个抵押包
     * @param request 请求信息
     * @param creditPackageId 抵押包id
     * @return 成功标志或错误信息
     * @author: jinzhm
     * @time:2017年3月14日 下午3:48:11
     * history:
     * 1、2017年3月14日 jinzhm 创建方法
     */
    @RequestMapping(value = "/creRigMan/destroyCreditPackage.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String destroyCreditPackage(HttpServletRequest request, int creditPackageId, String reason)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            return wmsinvecreditpackageService.destroyCreditPackage(creditPackageId, reason, user);
        }
        catch (Exception e)
        {
            return "error";
        }
    }

    /**
     * 
     * @Title: getCompanyRemainCreditPackage
     * @Description: 实时债权匹配情况-----查询 全集团剩余债权额度、今日到期抵押包额度
     * @param request
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月5日 下午4:34:18
     * history:
     * 1、2017年4月5日 Administrator 创建方法
     */
    @RequestMapping(value = "/creRigMan/getCompanyRemainCreditPackage.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCompanyRemainCreditPackage(HttpServletRequest request)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        // 实时债权匹配情况-----查询 全集团剩余债权额度、今日到期抵押包额度
        List<Map<String, Object>> list = wmsinvecreditpackageService.getCompanyRemainCreditPackage();

        map.put("Rows", list);
        return map;
    }

    /**
     * 
     * @Title: getRegionRemainCreditPackage
     * @Description: 实时债权匹配情况-----查询 各地区销售限额、已售额度、剩余债权额度
     * @param request
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月7日 下午4:33:38
     * history:
     * 1、2017年4月7日 Administrator 创建方法
     */
    @RequestMapping(value = "/creRigMan/getRegionRemainCreditPackage.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getRegionRemainCreditPackage(HttpServletRequest request)
    {
        // 实时债权匹配情况-----查询 各地区销售限额、已售额度、剩余债权额度
        Map<String, Object> map = wmsinvecreditpackageService.getRegionRemainCreditPackage();

        return map;
    }

    /**
     * 
     * @Title: getGroupRemainCreditPackage
     * @Description: 实时债权匹配情况-----查询 各组已售额度、剩余债权额度
     * @param request
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月19日 下午2:53:16
     * history:
     * 1、2017年4月19日 Administrator 创建方法
     */
    @RequestMapping(value = "/creRigMan/getGroupRemainCreditPackage.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getGroupRemainCreditPackage(HttpServletRequest request)
    {
        // 实时债权匹配情况-----查询 各组已售额度、剩余债权额度
        Map<String, Object> map = wmsinvecreditpackageService.getGroupRemainCreditPackage();

        return map;
    }

    /**
     * 
     * @Title: getAllGroupSaleAmountInfo
     * @Description: 实时债权匹配情况-----查询 全集团销售情况 线上、线下(全新支付/赎回再签)
     * @param request
     * @return map
     * @author: zhangyunfei
     * @time:2017年5月10日 上午9:50:10
     * history:
     * 1、2017年5月10日 Administrator 创建方法
     */
    @RequestMapping(value = "/creRigMan/getAllGroupSaleAmountInfo.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getAllGroupSaleAmountInfo(HttpServletRequest request)
    {
        // 实时债权匹配情况-----查询 各组已售额度、剩余债权额度
        Map<String, Object> map = wmsinvecreditpackageService.getAllGroupSaleAmountInfo();

        return map;
    }
}