package com.zx.emanage.inve.web;

import java.math.BigDecimal;
import java.util.Date;
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

import com.zx.emanage.inve.service.ISingleWmsInveClerkDataService;
import com.zx.emanage.inve.service.IWmsInveClerkDataService;
import com.zx.emanage.inve.vo.WmsInveClerkDataSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveClerkData;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveClerkDataController
 * 模块名称：
 * @Description: 内容摘要：柜员业务表主要处理数据
 * @author Guanxu
 * @date 2017年2月13日
 * @version V1.0
 * history:
 * 1、2017年2月13日 Guanxu 创建文件
 */
@Controller
public class WmsInveClerkDataController
{
    private static Logger log = LoggerFactory.getLogger(WmsInveClerkDataController.class);

    @Autowired
    private IWmsInveClerkDataService wmsInveClerkDataService;

    @Autowired
    private ISingleWmsInveClerkDataService SingleWmsInveClerkDataService;

    /**
     * @Title: getClerkDataWithPaging
     * @Description: 分页查询柜员工作台数据信息集合
     * @param queryInfo 查询条件
     * @param request 请求信息
     * @return 返回工作台数据信息集合
     * @author: jinzhm
     * @time:2017年2月8日 下午5:52:55
     * history:
     * 1、2017年2月8日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/getClerkDataWithPaging.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getClerkDataWithPaging(WmsInveClerkDataSearchBeanVO queryInfo, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsInveClerkDataService.getClerkDataWithPaging(queryInfo, user);
    }

    /**
     * 
     * @Title: checkAndLockData
     * @Description:柜员操作台、单据支付、合同签订、合同改签、合同续签前首先需要调用此方法判断是否可以进行锁定并处理
     * @param request
     * @param wms_inve_clerk_data_id
     * @param wms_inve_transa_id
     * @return 
     * success:锁定成功，开始处理数据
     * other_locked：锁定失败，其他人已锁定数据
     * error：柜员业务表中无此数据
     * @author: Guanxu
     * @time:2017年2月13日 下午3:35:23
     * history:
     * 1、2017年2月13日 Guanxu 创建方法
     */
    @RequestMapping(value = "/inve/checkAndLockData.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String checkAndLockData(HttpServletRequest request, Integer wms_inve_clerk_data_id,
                                   Integer wms_inve_transa_id)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return SingleWmsInveClerkDataService.checkAndLockData(user, wms_inve_clerk_data_id, wms_inve_transa_id);
    }

    /**
     * @Title: getRedeemClerkDataWithPaging
     * @Description: 分页查询合同改签数据信息集合
     * @param queryInfo 查询条件
     * @param request 请求信息
     * @return 合同改签数据信息集合
     * @author: jinzhm
     * @time:2017年2月9日 上午11:25:31
     * history:
     * 1、2017年2月9日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/getRedeemClerkDataWithPaging.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getRedeemClerkDataWithPaging(WmsInveClerkDataSearchBeanVO queryInfo,
                                                            HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsInveClerkDataService.getRedeemClerkDataWithPaging(queryInfo, user);
    }

    /**
     * @Title: getExtendClerkDataWithPaging
     * @Description: 分页查询合同续签数据信息集合
     * @param queryInfo 查询条件
     * @param request 请求信息
     * @return 合同续签数据信息集合
     * @author: jinzhm
     * @time:2017年2月9日 上午11:53:21
     * history:
     * 1、2017年2月9日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/getExtendClerkDataWithPaging.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getExtendClerkDataWithPaging(WmsInveClerkDataSearchBeanVO queryInfo,
                                                            HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsInveClerkDataService.getExtendClerkDataWithPaging(queryInfo, user);
    }

    /**
     * @Title: reStartSort
     * @Description: 单据重新排序
     * @param clerkData 单据信息
     * @param request 请求信息
     * @author: jinzhm
     * @time:2017年2月9日 上午11:09:03
     * history:
     * 1、2017年2月9日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/reStartSort.do", method = { RequestMethod.POST })
    @ResponseBody
    public void reStartSort(WmsInveClerkData clerkData, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        wmsInveClerkDataService.reStartSort(clerkData, user);
    }

    /**
     * @Title: reOrder
     * @Description: 单据整体重新排序，将排序时间早于设置的时间之前的所有签单单据重新进行排序
     * @param request 请求信息
     * @param hour 时
     * @param minute 分 
     * @author: jinzhm
     * @time:2017年4月6日 上午9:24:37
     * history:
     * 1、2017年4月6日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/reOrder.do", method = { RequestMethod.POST })
    @ResponseBody
    public void reOrder(HttpServletRequest request, Integer hour, Integer minute)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        wmsInveClerkDataService.reOrder(hour, minute, user);
    }

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinveclerkdatawithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsInveClerkDataSearchBeanVO queryInfo)
    {
        return wmsInveClerkDataService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinveclerkdatawithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsInveClerkDataSearchBeanVO queryInfo)
    {
        return wmsInveClerkDataService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key 
     * @param primary key 
     * @return WmsInveClerkDataVO
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinveclerkdatainfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsInveClerkData getInfoByPK(Integer wms_inve_clerk_data_id)
    {
        return wmsInveClerkDataService.getInfoByPK(wms_inve_clerk_data_id);
    }

    /**
     * Description :add method
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinveclerkdatasave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsInveClerkData bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsInveClerkDataService.save(bean, user);
        }
        catch (Exception e)
        {
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
    @RequestMapping(value = "/inve/wmsinveclerkdataupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsInveClerkData bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsInveClerkDataService.update(bean, user);
        }
        catch (Exception e)
        {
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
     * @Title: setKeepAccount
     * @Description: 设置当天的剩余可售额度
     * @param request 请求信息
     * @param account 可售额度
     * @return 设置成功标志
     * @author: jinzhm
     * @time:2017年4月6日 下午1:11:10
     * history:
     * 1、2017年4月6日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/setKeepAccount.do", method = { RequestMethod.POST })
    @ResponseBody
    public String setKeepAccount(HttpServletRequest request, BigDecimal account)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            return wmsInveClerkDataService.setKeepAccount(user, account);
        }
        catch (Exception e)
        {
            return "error";
        }
    }

    /**
     * @Title: getKeepAccount
     * @Description: 获取当天设置的可售额度
     * @param request 请求信息
     * @return 可售额度
     * @author: jinzhm
     * @time:2017年4月6日 下午1:12:03
     * history:
     * 1、2017年4月6日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/getKeepAccount.do", method = { RequestMethod.GET })
    @ResponseBody
    public String getKeepAccount(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsInveClerkDataService.getKeepAccount(user);
    }

    /**
     * @Title: getSummaryClerkTransaData
     * @Description: 获得某日的提单统计信息
     * @param request 请求信息
     * @return 统计信息
     * @author: jinzhm
     * @time:2017年3月21日 上午11:22:00
     * history:
     * 1、2017年3月21日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/getSummaryClerkTransaData.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getSummaryClerkTransaData(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsInveClerkDataService.getSummaryClerkTransaData(DateUtil.getDate10(new Date()), user);
    }
}