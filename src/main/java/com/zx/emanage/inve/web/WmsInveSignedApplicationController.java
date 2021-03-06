package com.zx.emanage.inve.web;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInvePruductCategoryService;
import com.zx.emanage.inve.service.IWmsInveSignedApplicationService;
import com.zx.emanage.inve.vo.WmsInveTransaCardSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveTransaSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveVerifyCustomerSignedVo;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.CrmCustomerInfo;
import com.zx.emanage.util.gen.entity.WmsInveCustomer;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveSignedApplicationController
 * 模块名称：
 * @Description: 内容摘要：
 * @author DongHao
 * @date 2017年2月5日
 * @version V1.0
 * history:
 * 1、2017年2月5日 DongHao 创建文件
 */
@Controller
public class WmsInveSignedApplicationController
{

    private static Logger log = LoggerFactory.getLogger(WmsInveSignedApplicationController.class);

    @Autowired
    private IWmsInveSignedApplicationService wmsInveSignedApplicationService;

    @Autowired
    private IWmsInvePruductCategoryService wmsinvepruductcategoryService;

    /**
     *
     * @Title: getSignedInfos
     * @Description: 签单申请列表信息查询
     * @param queryInfo 查询条件对象
     * @param request
     * @param response
     * @return 
     * @author: DongHao
     * @time:2017年2月4日 下午5:12:00
     * history:
     * 1、2017年2月4日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getSignedInfos.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getSignedInfos(WmsInveTransaCardSearchBeanVO queryInfo, HttpServletRequest request, HttpServletResponse response)
    {
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        return wmsInveSignedApplicationService.getSignedInfos(queryInfo, user);
    }

    /**
     * 
     * @Title: getAmountConfirmInfos
     * @Description: 查询支付确认相关数据信息
     * @param queryInfo 查询条件对象
     * @param request
     * @param response
     * @return 
     * @author: DongHao
     * @time:2017年2月6日 下午5:53:33
     * history:
     * 1、2017年2月6日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getAmountConfirmInfos.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getAmountConfirmInfos(WmsInveTransaCardSearchBeanVO queryInfo, HttpServletRequest request, HttpServletResponse response)
    {
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        return wmsInveSignedApplicationService.getAmountConfirmInfos(queryInfo, user);
    }


    /**
     * 
     * @Title: getAllCategoryInfos
     * @Description: 获取产品信息
     * @param cktype
     * @param category_name
     * @param wms_inve_pruduct_category_id
     * @param platform_user
     * @param request
     * @param response
     * @return 
     * @author: DongHao
     * @time:2017年2月10日 上午9:21:06
     * history:
     * 1、2017年2月10日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getAllCategoryInfos.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getAllCategoryInfos(String cktype, String category_name, Integer wms_inve_pruduct_category_id, String platform_user,
                                                   HttpServletRequest request, HttpServletResponse response)
    {
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);

        if (category_name != null && "-1".equals(category_name))
        {
            category_name = null;
        }

        if (wms_inve_pruduct_category_id != null && wms_inve_pruduct_category_id == -1)
        {
            wms_inve_pruduct_category_id = null;
        }

        List<Map<String, Object>> list = wmsinvepruductcategoryService.getAllListWithoutPaging(user, cktype, category_name,
                                                                                               wms_inve_pruduct_category_id, platform_user);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("Rows", list);
        return resultMap;
    }

    /**
     * 
     * @Title: getCustomerIncomeCardInfos
     * @Description: 获取客户收益卡信息
     * @param cus_name
     * @param id_card
     * @param request
     * @param response
     * @return 
     * @author: DongHao
     * @time:2017年2月10日 上午11:26:17
     * history:
     * 1、2017年2月10日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getCustomerIncomeCardInfos.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<Map<String, Object>> getCustomerIncomeCardInfos(String cus_name, String id_card, HttpServletRequest request,
                                                                HttpServletResponse response)
    {

        return wmsInveSignedApplicationService.getCustomerIncomeCardInfos(cus_name, id_card);
    }

    /**
     * 
     * @Title: saveSigendApplicationInfo
     * @Description: 保存签单信息
     * @param request
     * @param wmsInveTransa
     * @param wmsInveTransaProd
     * @param fileArr
     * @param saveFlag
     * @param wmsCustomer
     * @param beanVO
     * @return 
     * @author: DongHao
     * @time:2017年2月11日 上午9:14:35
     * history:
     * 1、2017年2月11日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/saveSigendApplicationInfo.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String saveSigendApplicationInfo(HttpServletRequest request, WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd,
                                            String fileArr, String saveFlag,WmsInveCustomer wmsCustomer,WmsInveTransaSearchBeanVO beanVO)
    {
        // 返回结果信息
        String result = "";

        // 自定义针对返回结果信息进行解析成字符串数据
        String[] s = null;

        // 获取当前登录的客户信息
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);

        // 定义签单释债权返回的结果信息字符串
        String creditStr = "success";
        try
        {

            // 执行上单操作
            result = wmsInveSignedApplicationService.saveSigendApplicationInfo(wmsInveTransa, wmsInveTransaProd, fileArr, saveFlag, wmsCustomer,
                                                                               beanVO, user);

            // 判断当前单据的状态是否是待修订(签约退回)的单据 LCType表示单据的状态
            if ("19".equals(beanVO.getLCtype()))
            {

                // 判断单据的合同类型是否为需要签线上合同
                if ("2".equals(wmsInveTransa.getContract_signing_type()))
                {
                    // 执行债权操作
                    creditStr = wmsInveSignedApplicationService.creditHandler(wmsInveTransa, wmsInveTransaProd, beanVO, user);
                }

            }

            // 对签单返回的结果信息进行解析成以","分割的字符串数组
            s = result.split(",");
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        // 判断结果集字符串数据不等于空并且签单返回的信息是success
        if (s != null && "success".equals(s[0]))
        {
            String msg = "业务管理-理财管理-理财上单";
            SysTools.saveLog(request, msg);
                
            // 判断字符串中处于第二位的字符串是否为空
            if (s[1] != null || !"".equals(s[1]))
            {
                // 判断单据的状态是否为待修订(支付退回)、待修订(签约退回)、待修订(审核退回)的单据状态
                // 如果单据状态为以上几种单据状态,返回的结果信息是不包含排队信息的
                if ("12".equals(beanVO.getLCtype()) || "17".equals(beanVO.getLCtype()) || "19".equals(beanVO.getLCtype()))
                {
                    result = "success";
                }
                else
                {
                   // 单据状态不是草稿、待修订(支付退回)、待修订(签约退回)、待修订(签约退回)的单据状态返回排队信息
                   result = "尊敬的客户您好:<br/>&nbsp;&nbsp;&nbsp;&nbsp;您的前方正有" + s[1] + "个客户等待办理业务,请关注窗口信息,过号则会重排";
                }
            }
    
        }

        return result;
    }

    /**
     * 
     * @Title: getWmsInveTransaInfos
     * @Description: 根据上单表主键获取上单信息
     * @param wms_inve_transa_id
     * @return 
     * @author: DongHao
     * @time:2017年2月11日 下午1:10:06
     * history:
     * 1、2017年2月11日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getWmsInveTransaInfosByWmsInveTransaId.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getWmsInveTransaInfosByWmsInveTransaId(String wms_inve_transa_id)
    {
        return wmsInveSignedApplicationService.getWmsInveTransaInfosByWmsInveTransaId(wms_inve_transa_id);
    }

    /**
     * 
     * @Title: getWmsInveRenYuanInfo
     * @Description: 根据上单表主键获取对应的人员信息
     * @param wms_inve_transa_id
     * @return 
     * @author: DongHao
     * @time:2017年2月13日 下午4:30:01
     * history:
     * 1、2017年2月13日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getWmsInveRenYuanInfo.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getWmsInveRenYuanInfo(String wms_inve_transa_id)
    {

        return wmsInveSignedApplicationService.getWmsInveRenYuanInfo(wms_inve_transa_id);
    }

    /**
     * 
     * @Title: getSigendProtocolList
     * @Description: 合同签订信息列表
     * @param queryInfo
     * @param request
     * @param response
     * @return 
     * @author: DongHao
     * @time:2017年2月13日 下午5:52:00
     * history:
     * 1、2017年2月13日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getSigendProtocolList.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getSigendProtocolList(WmsInveTransaCardSearchBeanVO queryInfo, HttpServletRequest request, HttpServletResponse response)
    {
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        return wmsInveSignedApplicationService.getSigendProtocolList(queryInfo, user);
    }

    /**
     * 
     * @Title: withdrawSingle
     * @Description: 撤单流程操作
     * @param wms_inve_transa_id
     * @param request
     * @return 
     * @author: DongHao
     * @time:2017年2月14日 上午8:51:52
     * history:
     * 1、2017年2月14日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/withdrawSingle.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> withdrawSingle(String wms_inve_transa_id, String advice, String data_status, HttpServletRequest request)
    {
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        return wmsInveSignedApplicationService.withdrawSingle(wms_inve_transa_id, advice, data_status, user);
    }

    /**
     * 
     * @Title: invalidSingle
     * @Description: 作废流程操作
     * @param wms_inve_transa_id
     * @param advice
     * @param request
     * @return 
     * @author: DongHao
     * @time:2017年2月14日 上午9:29:09
     * history:
     * 1、2017年2月14日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/invalidSingle.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> invalidSingle(String wms_inve_transa_id, String advice, String data_status, HttpServletRequest request)
    {
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        return wmsInveSignedApplicationService.invalidSingle(wms_inve_transa_id, advice, data_status, user);
    }

    /**
     * 
     * @Title: backSingle
     * @Description: 退回流程操作
     * @param wms_inve_transa_id
     * @param advice
     * @param data_status
     * @param request
     * @return 
     * @author: DongHao
     * @time:2017年2月14日 上午11:12:26
     * history:
     * 1、2017年2月14日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/backSingle.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> backSingle(String wms_inve_transa_id, String advice, String data_status, String taskId, HttpServletRequest request)
    {
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        return wmsInveSignedApplicationService.backSingle(wms_inve_transa_id, advice, data_status, taskId, user);
    }

    /**
     * 
     * @Title: findClerkDataBySalemanCount
     * @Description: 获取排队人数
     * @param request
     * @return 
     * @author: DongHao
     * @time:2017年2月27日 下午11:31:22
     * history:
     * 1、2017年2月27日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/findClerkDataBySalemanCount.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public int findClerkDataBySalemanCount(HttpServletRequest request)
    {
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        return wmsInveSignedApplicationService.findClerkDataBySalemanCount(user);
    }

    /**
     * 
     * @Title: getCategorySelect
     * @Description: 获取业务员对应的可以使用的产品信息
     * @param cktype
     * @param category_name
     * @param wms_inve_pruduct_category_id
     * @param platform_user
     * @param request
     * @param response
     * @return 
     * @author: DongHao
     * @time:2017年2月28日 上午12:51:41
     * history:
     * 1、2017年2月28日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getCategorySelect.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<Map<String, Object>> getCategorySelect(String cktype, String category_name, Integer wms_inve_pruduct_category_id,
                                                       String platform_user, HttpServletRequest request, HttpServletResponse response)
    {
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);

        List<Map<String, Object>> list = wmsinvepruductcategoryService.getAllListWithoutPaging(user, cktype, category_name,
                                                                                               wms_inve_pruduct_category_id, platform_user);

        Map<String, Object> selectMap = new HashMap<String, Object>();

        selectMap.put("category_name", "请选择");
        selectMap.put("wms_inve_pruduct_category_id", -1);

        list.add(0, selectMap);

        return list;
    }

    /**
     * 
     * @Title: updateContractSigningType
     * @Description: 在金额支付的时候修改实际支付时间小于当前时间则将设置成线下合同
     * @param wms_inve_transa_id
     * @return 
     * @author: DongHao
     * @time:2017年2月28日 上午1:41:04
     * history:
     * 1、2017年2月28日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/updateContractSigningType.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public boolean updateContractSigningType(String wms_inve_transa_id, String act_of_date, String origCategoryId, String newCategoryId,
                                          BigDecimal productAccount, HttpServletRequest request)
    {
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        boolean flag = wmsInveSignedApplicationService.updateContractSigningType(wms_inve_transa_id, act_of_date, origCategoryId, newCategoryId, productAccount,
                                                                  user);
        return flag;
    }

    /**
     * 
     * @Title: verificationCustomerInfo
     * @Description: 对签单的客户信息进行二次验证
     * @param info 客户信息
     * @return 返回map集合
     * @author: DongHao
     * @time:2017年2月23日 下午2:49:50
     * history:
     * 1、2017年2月23日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/verificationCustomerInfo.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> verificationCustomerInfo(CrmCustomerInfo info, String personnel_shortCode)
    {
        return wmsInveSignedApplicationService.verificationCustomerInfo(info, personnel_shortCode);
    }
    
    /**
     * 
     * @Title: verifyCustomerIsBuy
     * @Description: 验证客户是否满足购买新产品的要求
     * @param vo 验证客户信息对象
     * @return 验证通过返回
     * @author: DongHao
     * @time:2017年7月18日 下午1:50:18
     * history:
     * 1、2017年7月18日 DongHao 创建方法
     */
    @RequestMapping(value="/inve/verifyCustomerIsBuy.do", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> verifyCustomerIsBuy(WmsInveVerifyCustomerSignedVo vo)
    {
        
        return wmsInveSignedApplicationService.verifyCustomerIsBuy(vo);
    }


}
