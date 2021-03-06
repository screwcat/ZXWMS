package com.zx.emanage.inve.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInveClerkProtocolService;
import com.zx.emanage.inve.service.IWmsInveCreditLimitService;
import com.zx.emanage.inve.service.IWmsInvePruductCategoryService;
import com.zx.emanage.inve.service.IWmsInveTransaApprovalInfoService;
import com.zx.emanage.inve.service.IWmsInveTransaService;
import com.zx.emanage.inve.vo.WmsInveDebtHeadSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.emanage.inve.vo.WmsInveIncomeBillVo;
import com.zx.emanage.inve.vo.WmsInveSelectBillBeanVo;
import com.zx.emanage.inve.vo.WmsInveTransaCardSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveTransaSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveCustomer;
import com.zx.emanage.util.gen.entity.WmsInveCustomerCard;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaApprovalInfo;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.JasperUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveTransaController
{
    private static Logger log = LoggerFactory.getLogger(WmsInveTransaController.class);

    @Autowired
    private IWmsInveTransaService wmsinvetransaService;

    @Autowired
    private IWmsInveTransaApprovalInfoService wmsinvetransaapprovalinfoservice;

    @Autowired
    private IWmsInveCreditLimitService wmsInveCreditLimitServiceImpl; // 债权限制service

    @Autowired
    private IWmsInveClerkProtocolService wmsinveclerkprotocolService;

    @Autowired
    private IWmsInvePruductCategoryService wmsinvepruductcategoryService;
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransawithoutpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsInveTransaSearchBeanVO queryInfo)
    {
        return wmsinvetransaService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransawithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsInveTransaSearchBeanVO queryInfo, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getList(queryInfo, user);
    }

    /**
     * 理财查询列表
     * 
     * @param queryInfo
     * @return record list
     * @author zhubo
     */
    @RequestMapping(value = "/inve/getsearchfinancialwithpaginglist.do", method = { RequestMethod.GET,
                                                                                   RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getSearchFinancialListWithPaging(WmsInveTransaCardSearchBeanVO queryInfo,
                                                                HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getSearchFinancialListWithPaging(queryInfo, user);
    }

    /**
     * @Title: exportFinancialTransa
     * @Description: 理财财务主管导出签单情况报表
     * @param queryInfo 查询条件
     * @param response 响应信息
     * @author: jinzhm
     * @time:2017年5月10日 下午2:29:21
     * history:
     * 1、2017年5月10日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/exportFinancialTransa.do", method = { RequestMethod.GET, RequestMethod.POST })
    public void exportFinancialTransa(WmsInveTransaCardSearchBeanVO queryInfo, HttpServletResponse response)
    {
        wmsinvetransaService.exportFinancialTransa(queryInfo, response);
    }

    /**
     * @Title: exportTransa
     * @Description: 理财柜员主管导出签单情况报表
     * @param queryInfo 查询条件
     * @param response 响应信息
     * @author: jinzhm
     * @time:2017年5月11日 上午9:25:32
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/exportTransa.do", method = { RequestMethod.GET, RequestMethod.POST })
    public void exportTransa(WmsInveTransaCardSearchBeanVO queryInfo, HttpServletResponse response)
    {
        wmsinvetransaService.exportTransa(queryInfo, response);
    }

    /**
     * 理财特批列表
     * 
     * @param queryInfo
     * @return record list
     * @author lvtu
     */
    @RequestMapping(value = "/inve/getapprovalfinancialwithpaginglist.do", method = { RequestMethod.GET,
                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getApprovalFinancialListWithPaging(WmsInveTransaCardSearchBeanVO queryInfo,
                                                                  HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getApprovalFinancialListWithPaging(queryInfo, user);
    }

    /**
     * 理财特批列表导出
     * 
     * @param queryInfo
     * @return record list
     * @author lvtu
     */
    @RequestMapping(value = "/inve/getapprovalfinancialwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                        RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getApprovalFinancialListWithoutPaging(WmsInveTransaCardSearchBeanVO queryInfo,
                                                                     HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getApprovalFinancialListWithoutPaging(queryInfo, user);
    }

    /**
     * 理财查询列表导出
     * 
     * @param queryInfo
     * @return record list
     * @author zhubo
     */
    @RequestMapping(value = "/inve/getsearchfinancialwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                      RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getSearchFinancialListWithoutPaging(WmsInveTransaCardSearchBeanVO queryInfo,
                                                                   HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getSearchFinancialListWithoutPaging(queryInfo, user);
    }

    /**
     * @Title:getAmountCheckingReportWithPaging
     * 
     * Description:【理财上单流程】 【财务管理】-【理财上单】-【对账报表】 列表显示
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     * @
     */
    @RequestMapping(value = "/inve/getamountcheckingreportwithpaginglist.do", method = { RequestMethod.GET,
                                                                                        RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getAmountCheckingReportWithPaging(WmsInveTransaCardSearchBeanVO queryInfo,
                                                                 HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getAmountCheckingReportWithPaging(queryInfo, user);
    }

    /**
     * @Title:getAmountCheckingReportWithoutPaging
     * Description:【理财上单流程】 【财务管理】-【理财上单】-【对账报表】 列表导出
     * @param queryInfo
     * @return record list
     * @author zhubo
     */
    @RequestMapping(value = "/inve/getamountcheckingreportwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                           RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getAmountCheckingReportWithoutPaging(WmsInveTransaCardSearchBeanVO queryInfo,
                                                                    HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getAmountCheckingReportWithoutPaging(queryInfo, user);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInveTransaVO
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransainfo.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getInfo(Integer wms_inve_transa_id)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        WmsInveTransa wmsInveTransa = wmsinvetransaService.getInfoByPK(wms_inve_transa_id);
        resMap.put("wmsInveTransa", wmsInveTransa);
        resMap.put("remainTime",
                   (wmsInveTransa.getLast_update_timestamp().getTime() + 1000 * 60 * 60 * 24 - System.currentTimeMillis()));// 支付剩余时间
        return resMap;
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInveTransaVO
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvetransainfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsInveTransa getInfoByPK(Integer wms_inve_transa_id)
    {
        return wmsinvetransaService.getInfoByPK(wms_inve_transa_id);
    }

    @RequestMapping(value = "/inve/wmsinvetransainfobypk4Extend.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getInfoByPK4Extend(Integer wms_inve_transa_id)
    {
        return wmsinvetransaService.getInfoByPK4Extend(wms_inve_transa_id);
    }

    //
    // /**
    // * 修改单据状态
    // * @author jiadoelong
    // */
    // @RequestMapping(value = "/inve/wmsinvetransacancel.do", method = {
    // RequestMethod.POST })
    // @ResponseBody
    // public String doCancel(HttpServletRequest request)
    // {
    // HttpSession session = request.getSession();
    // UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
    // String wms_inve_transa_id = request.getParameter("wms_inve_transa_id");
    // String status = request.getParameter("data_status");
    // String taskId = request.getParameter("taskId");
    // String different = request.getParameter("different");
    // String adviceLC = request.getParameter("adviceLC");
    // return
    // wmsinvetransaService.doCancel(wms_inve_transa_id,status,taskId,different,user,adviceLC);
    // }

    /**
    * 根据上单ID  查询单据状态
    * @author jiadoelong
    */
    @RequestMapping(value = "/inve/wmsinvetransadSearchStatus.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSearchStatus(HttpServletRequest request)
    {
        String wms_inve_transa_id = request.getParameter("wms_inve_transa_id");
        return wmsinvetransaService.doSearchStatus(wms_inve_transa_id);
    }

    /**
     * 保存草稿单据作废原因
     * @date 2015年12月15日 13:11
     * @author jiadoelong
     */
    @RequestMapping(value = "/inve/savetosubmitnullifyCG.do", method = { RequestMethod.POST })
    @ResponseBody
    public String saveToSubmitNullifyCG(HttpServletRequest request, WmsInveTransaApprovalInfo info)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaapprovalinfoservice.saveToCancelCG(user, info);
    }

    /**
     * 保存复核退回单据作废原因
     * @date 2015年12月15日 13:11
     * @author jiadoelong
     */
    @RequestMapping(value = "/inve/savetosubmitnullify.do", method = { RequestMethod.POST })
    @ResponseBody
    public String saveToSubmitNullify(HttpServletRequest request, WmsInveTransaApprovalInfo info,
                                      WmsInveDebtWorkFlowVO wDebtWorkFlowVO)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaapprovalinfoservice.saveToCancel(user, info, wDebtWorkFlowVO);
    }

    /**
     * 保存审批意见
     * @param incomeCardParams 上单审核修改的收益卡信息
     * @date 2015年12月15日 13:11
     * @author jiadoelong
     * history:
     * 1. 2017年7月10日17:12:10 donghao 修改方法,在方法上增加参数incomeCardParams收益卡参数,在上单审核操作中可以修改客户的收益卡的银行和支行信息
     *                                只有审核通过了才可以修改,审核不通过则不修改
     */
    @RequestMapping(value = "/inve/saveToSubmitFinancialReturn.do", method = { RequestMethod.POST })
    @ResponseBody
    public String saveTosubmitFinancialReturn(HttpServletRequest request, WmsInveTransaApprovalInfo info,
                                              String yjtype, String taskId, String compe, String incomeCardParams)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);

        try
        {
            result = wmsinvetransaapprovalinfoservice.saveToSubmitNullify(info, taskId, yjtype, user, compe, incomeCardParams);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "业务管理-理财管理-理财复核";
            SysTools.saveLog(request, msg);
        }
        return result;
    }

    /**
     * Description :理财上单保存理财信息
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author wangyishun
     */
    @RequestMapping(value = "/inve/wmsinvetransasave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd,
                         String fileArr, String saveFlag, WmsInveCustomer wmsCustomer, WmsInveTransaSearchBeanVO beanVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransaService.save(wmsInveTransa, wmsInveTransaProd, fileArr, user, saveFlag, wmsCustomer,
                                               beanVO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "业务管理-理财管理-理财上单";
            SysTools.saveLog(request, msg);
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
    @RequestMapping(value = "/inve/wmsinvetransaupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsInveTransa bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransaService.update(bean, user);
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
     * @Title: updateForJEZF 
     * Description :【理财上单】财务管理>金额管理>金额支付
     * @param bean
     * @param itcardJSON
     * @param user
     * @param wmsTransaProd
     * @param fileArr
     * @param flagKey
     * @return "success" or "error" or user definedSS
     * @date 2015年12月14日 14:56
     * @author hancd
     */
    @RequestMapping(value = "/inve/wmsinvetransaupdateforjezf.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdateForJEZF(HttpServletRequest request, WmsInveTransa bean, String itcardJSON,
                                  WmsInveTransaProd wmsTransaProd, WmsInveTransaSearchBeanVO beanvo,
                                  WmsInveCustomerCard wmsInveCustomerCard)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            /***************验证债权是否匹配成功!****************/
            if (beanvo.getFlagKey().equals("1"))
            {
                
                /*
                 * 增加判断全集团销售限额的限制(允许支付返回true,否则返回false)
                 * 注:在金额支付提交时加该验证针对的情况是在上单的时候选择的不可拆分类产品,再金额支付的时候将产品修改成可拆分类的产品,
                 *   此时需要进行全集团的销售限额的限制 。
                 */
                boolean isAllGroupSaleLimit = wmsinvetransaService.verifyIsAllGroupSaleLimit(bean, wmsTransaProd, user);
                
                //判断全集团限制金额允许支付则返回true,否则返回false
                if(!isAllGroupSaleLimit)
                {
                    return "error2";
                }

                // 根据当前登录人所在地区获取债权限制表中最小的债权限制金额
                // 调用验证债权是否可以使用传入的产品id(新产品id和老产品id都设置成金额支付时所获取的产品id)
                boolean isCreditLimitAccount = wmsInveCreditLimitServiceImpl.verifyCreditLimitAccountAvailable(user,
                                                                                                               wmsTransaProd.getProduct_account(),
                                                                                                               bean.getDate_of_act(),
                                                                                                               bean.getWms_inve_transa_id(),
                                                                                                               wmsTransaProd.getWms_inve_pruduct_category_id(),
                                                                                                               wmsTransaProd.getWms_inve_pruduct_category_id());

                // 判断债权是否可以使用
                if (!isCreditLimitAccount)
                {
                    return "error1";
                }

                // 验证债权是否匹配成功
                boolean bool = wmsinvetransaService.confirmCreditMatched(wmsTransaProd.getWms_inve_pruduct_category_id(),
                                                                         bean.getWms_inve_transa_id(),
                                                                         wmsTransaProd.getProduct_account(),
                                                                         bean.getDate_of_act(), user);
                if (!bool)
                {
                    // 债权匹配不足时需要向债权限制表中添加债权限制记录
                    wmsInveCreditLimitServiceImpl.saveCreditLimit(user, wmsTransaProd.getProduct_account());

                    return "error1";
                }
            }

            result = wmsinvetransaService.updateForJEZF(bean, itcardJSON, user, wmsTransaProd, beanvo,
                                                        wmsInveCustomerCard);

            // 根据产品主键查询对应产品
            WmsInvePruductCategory wmsInveProductCategory = wmsinvepruductcategoryService.getInfoByPK(wmsTransaProd.getWms_inve_pruduct_category_id());
            // 产品不提供纸质合同 生成合同相关数据
            if ("0".equals(wmsInveProductCategory.getHas_paper_protocol()))
            {
                // 合同生效时间默认为当前时间
                final String begin_of_date = DateUtil.date2String(bean.getDate_of_act(), "yyyy-MM-dd");
                // 合同类型默认为线上合同
                final String protocol_type = "1";

                // jinzhm 修改，使用线程调用自动生成合同数据
                try
                {
                    wmsinveclerkprotocolService.save(begin_of_date, bean.getWms_inve_transa_id(), user, protocol_type,
                                                     null, null);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    throw new RuntimeException("执行自动生成合同相关数据出现错误！");
                }

            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        if ("success".equals(result))
        {
            String msg = "财务管理>金额管理>金额支付";
            SysTools.saveLog(request, msg);
        }
        return result;
    }

    /**
     * @Title: changeCategoryOrPaymentAccount
     * @Description: 修改产品或投资金额触发调用处理债权
     * @param request 请求信息
     * @param origCategoryId 原产品id
     * @param newCategoryId 新产品id
     * @param transaId 上单主键
     * @param productAccount 投资金额
     * @param actDateOfPayment 实际支付日期
     * @return 是否匹配到债权（不需要匹配债权时也会返回true）
     * @author: jinzhm
     * @time:2017年2月14日 下午2:35:16
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/changeCategoryOrPaymentAccount.do", method = { RequestMethod.POST })
    @ResponseBody
    public boolean changeCategoryOrPaymentAccount(HttpServletRequest request, int origCategoryId, int newCategoryId,
                                                  int transaId, BigDecimal productAccount, String actDateOfPayment)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.changeCategoryOrPaymentAccount(origCategoryId, newCategoryId, transaId,
                                                                   productAccount,
                                                                   DateUtil.strDate(actDateOfPayment, null), user);
    }

    /**
     * @Title: confirmCreditMatched
     * @Description: 确认单据债权匹配占用是否正确
     * @param request 请求信息
     * @param categoryId 产品id
     * @param transaId 上单主键
     * @param productAccount 投资金额
     * @param actDateOfPayment 实际支付日期
     * @param user 登录用户信息
     * @return 是否债权匹配成功（不需要匹配债权时也返回true）
     * @author: jinzhm
     * @time:2017年2月14日 下午3:19:34
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/confirmCreditMatched.do", method = { RequestMethod.POST })
    @ResponseBody
    public boolean confirmCreditMatched(HttpServletRequest request, int categoryId, int transaId,
                                        BigDecimal productAccount, String actDateOfPayment)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.confirmCreditMatched(categoryId, transaId, productAccount,
                                                         DateUtil.strDate(actDateOfPayment, null), user);
    }

    /**
     * @Title: releaseMatchedCredit
     * @Description: 释放已经匹配的债权（关闭金额支付弹出框时调用）
     * @param request 请求信息
     * @param transaId 上单主键
     * @author: jinzhm
     * @time:2017年2月14日 下午3:52:11
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/releaseMatchedCredit.do", method = { RequestMethod.POST })
    @ResponseBody
    public void releaseMatchedCredit(HttpServletRequest request, int transaId)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        wmsinvetransaService.releaseMatchedCredit(transaId, user);
    }

    /**
     * @Title: matchHoldCredit
     * @Description: 打开支付确认窗口时债权匹配并占用
     * @param request 请求信息
     * @param transaId 上单主键
     * @param categoryId 产品主键
     * @param productAccount 投资金额
     * @param actDateOfPayment 实际支付日期
     * @return 是否匹配成功
     * @author: jinzhm
     * @time:2017年2月14日 下午4:56:39
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/matchHoldCredit.do", method = { RequestMethod.POST })
    @ResponseBody
    public boolean matchHoldCredit(HttpServletRequest request, int transaId, int categoryId, BigDecimal productAccount,
                                   String actDateOfPayment)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (actDateOfPayment == null || "".equals(actDateOfPayment))
        {
            Date nowDate = DateUtil.getDate10(new Date());
            actDateOfPayment = format.format(nowDate);
        }
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.matchHoldCredit(transaId, categoryId, productAccount,
                                                    DateUtil.strDate(actDateOfPayment, null), user);
    }

    /**
     * 获取债权匹配页面列表数据
     * 
     * @param queryInfo
     * @return Map
     * @author 张风山
     */
    @RequestMapping(value = "/inve/wmsinvetransawithpaginglistformatch.do", method = { RequestMethod.GET,
                                                                                      RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPagingForMatch(WmsInveTransaSearchBeanVO queryInfo, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getListWithPagingForMatch(queryInfo, user);
    }

    /**
     * 获取债权匹配页面列表导出
     * 
     * @param queryInfo
     * @return Map
     * @author 白松
     */
    @RequestMapping(value = "/inve/wmsinvetransawithpaginglistformatchdaochu.do", method = { RequestMethod.GET,
                                                                                            RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListForMatch(WmsInveTransaSearchBeanVO queryInfo, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getList(queryInfo, user);
    }

    /**
     * 获取协议续期页面列表数据
     * 
     * @param queryInfo
     * @return Map
     * @author baisong
     */
    @RequestMapping(value = "/inve/wmsinvetransawithpaginglistforRenewal.do", method = { RequestMethod.GET,
                                                                                        RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPagingForRenewal(WmsInveTransaSearchBeanVO queryInfo,
                                                           HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getListWithPagingForRenewal(queryInfo, user);
    }

    /**
     * 获取协议续期页面列表导出
     * 
     * @param queryInfo
     * @return Map
     * @author baisong
     */
    @RequestMapping(value = "/inve/wmsinvetransawithpaginglistforRenewalExcel.do", method = { RequestMethod.GET,
                                                                                             RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListForRenewalExcel(WmsInveTransaSearchBeanVO queryInfo, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getListRenewalExcel(queryInfo, user);
    }

    /**
     * 获取债权选择页面列表数据显示
     * @param queryInfo
     * @return Map
     * @author hancd
     */
    @RequestMapping(value = "/inve/wmsinvetransawithpaginglistforchoose.do", method = { RequestMethod.GET,
                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPagingForChoose(HttpServletRequest request,
                                                          WmsInveTransaSearchBeanVO queryInfo)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getListWithPagingForChoose(queryInfo, user);
    }

    /**
     * Description :【理财上单】-【金额管理】获取金额管理列表页数据
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     */
    @RequestMapping(value = "/inve/wmsinvetransaforjeglwithpaginglist.do", method = { RequestMethod.GET,
                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListForJEGLWithPaging(WmsInveTransaSearchBeanVO queryInfo, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getListForJEGLWithPaging(queryInfo, user);
    }

    /**
     * Description :【理财上单】-【金额管理】获取金额管理导出数据
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     */
    @RequestMapping(value = "/inve/wmsinvetransaforjeglwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                        RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListForJEGLWithoutPaging(WmsInveTransaSearchBeanVO queryInfo,
                                                           HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getListForJEGLWithoutPaging(queryInfo, user);
    }

    /**
     * @Title: toBackforJEZF 
     * Description :【理财上单】财务管理>金额管理>支付退回
     * @param bean
     * @param itcardJSON
     * @param user
     * @param wmsTransaProd
     * @param ransaAtt
     * @param flagKey
     * @return "success" or "error" or user definedSS
     * @date 2015年12月14日 14:56
     * @author hancd
     */
    @RequestMapping(value = "/inve/toBackforJEZF.do", method = { RequestMethod.POST })
    @ResponseBody
    public String toBackforJEZF(HttpServletRequest request, WmsInveTransa bean, WmsInveDebtWorkFlowVO wDebtWorkFlowVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransaService.toBackforJEZF(bean, user, wDebtWorkFlowVO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        if ("success".equals(result))
        {
            String msg = "财务管理>金额管理>支付退回操作";
            SysTools.saveLog(request, msg);
        }
        return result;
    }

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author wangyishun
     */
    @RequestMapping(value = "/inve/getsdlcxx.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getSdlcxx(Integer wms_inve_transa_id, Integer wms_inve_transa_prod_id)
    {
        return wmsinvetransaService.getSdlcxx(wms_inve_transa_id, wms_inve_transa_prod_id);
    }

    /**
     * Description :理财 债权转让及受让协议 后台查询数据 查询了很多表
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @RequestMapping(value = "/inve/wmsinvetransafindforlc.do", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getMapForLc(Integer wms_inve_transa_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap = wmsinvetransaService.getMapForLc(wms_inve_transa_id);
        return paramMap;
    }

    /**
     * 赎回申请列表
     * 
     * @param queryInfo
     * @return record list
     * @author zhubo
     */
    @RequestMapping(value = "/inve/getredeemapplylistwithpaging.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getRedemptionApplyListWithPaging(WmsInveTransaCardSearchBeanVO queryInfo,
                                                                HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getRedeemApplyListWithPaging(queryInfo, user);
    }

    /**
     * 赎回申请列表导出
     * 
     * @param queryInfo
     * @return record list
     * @author zhubo
     */
    @RequestMapping(value = "/inve/getredeemapplylistwithoutpaging.do", method = { RequestMethod.GET,
                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getRedemptionApplyListWithoutPaging(WmsInveTransaCardSearchBeanVO queryInfo,
                                                                   HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getRedeemApplyListWithoutPaging(queryInfo, user);
    }

    /**
     * 续期申请列表
     * @param queryInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/inve/getExtendapplylistwithpaging.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getExtendapplylistwithpaging(WmsInveTransaCardSearchBeanVO queryInfo,
                                                            HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getExtendapplylistwithpaging(queryInfo, user);
    }

    /**
     * 续期申请列表导出
     * @param queryInfo
     * @return record list
     * @author zhubo
     */
    @RequestMapping(value = "/inve/getExtendapplylistwithoutpaging.do", method = { RequestMethod.GET,
                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getExtendapplylistwithoutpaging(WmsInveTransaCardSearchBeanVO queryInfo,
                                                               HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getExtendapplylistwithoutpaging(queryInfo, user);
    }

    /**
     * 理财特批详细信息
     * 
     * @param queryInfo
     * @return record list
     * @author lvtu
     */
    @RequestMapping(value = "/inve/getkhlctpxx.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getApprovalFinancialInfo(HttpSession session, Integer wms_inve_transa_id,
                                                        Integer wms_inve_transa_prod_id,
                                                        Integer wms_inve_transa_protocol_id)
    {
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getApprovalFinancialInfo(wms_inve_transa_id, wms_inve_transa_prod_id,
                                                             wms_inve_transa_protocol_id);
    }

    /**
     * 理财业务特批详细信息
     * 
     * @param queryInfo
     * @return record list
     * @author lvtu
     */
    @RequestMapping(value = "/inve/getywlctpxx.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getApprovalFinancialywInfo(HttpSession session, Integer wms_inve_transa_id,
                                                          Integer wms_inve_transa_prod_id,
                                                          Integer wms_inve_transa_protocol_id)
    {
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getApprovalFinancialywInfo(wms_inve_transa_id, wms_inve_transa_prod_id,
                                                               wms_inve_transa_protocol_id);
    }

    /**
     * 根据上单信息表主键获取相关信息--上单表上单产品表
     * 
     * @param queryInfo
     * @return Map
     * @author baisong
     */
    @RequestMapping(value = "/inve/getObjectInfo.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getObjectInfo(WmsInveTransaCardSearchBeanVO queryInfo, HttpServletRequest request)
    {
        return wmsinvetransaService.getObjectInfo(queryInfo);
    }

    /**
     * Description :【理财上单】流程历程查看
     * @param  wms_inve_transa_id
     * @return Map
     * @date 2015年12月12日 上午10:02
     * @author hancd
     */
    @RequestMapping(value = "/inve/getFinancialSingleProcessView.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getFinancialSingleProcessView(String wms_inve_transa_id)
    {
        return wmsinvetransaService.getFinancialSingleProcessView(wms_inve_transa_id);
    }

    /**
    * Description :【理财上单】-【上单审核】获取审核列表
    * @param queryInfo
    * @return record list
    * @author hancd
    * @date 2015年12月15日 13:11
    */
    @RequestMapping(value = "/inve/wmsinvetransaforsdshwithpaginglist.do", method = { RequestMethod.GET,
                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListForSDSHWithPaging(WmsInveTransaSearchBeanVO queryInfo, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getListForSDSHWithPaging(queryInfo, user);
    }

    /**
     * Description :【理财上单】-【上单审核】获取审核导出
     * @param queryInfo
     * @return record list
     * @author hancd
     * @date 2015年12月15日 13:11
     */
    @RequestMapping(value = "/inve/wmsinvetransaforsdshwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                        RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListForSDSHWithoutPaging(WmsInveTransaSearchBeanVO queryInfo,
                                                           HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getListForSDSHWithoutPaging(queryInfo, user);
    }

    /**
     * Description :【理财复核】列表显示
     * @param queryInfo
     * @return record list
     * @author jiaodelong
     */
    @RequestMapping(value = "/inve/getWmsFinancialReturnpaginglist.do", method = { RequestMethod.GET,
                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getFinancialReturnpaginglist(WmsInveDebtHeadSearchBeanVO queryInfo,
                                                            HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getFinancialReturnpaginglist(queryInfo, user);
    }

    /**
     * Description :【理财复核】 导出
     * @param queryInfo
     * @return record list
     * @author jiaodelong
     */
    @RequestMapping(value = "/inve/getFinancialReturnWithoutPaging.do", method = { RequestMethod.GET,
                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getFinancialReturnWithoutPaging(WmsInveDebtHeadSearchBeanVO queryInfo,
                                                               HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getFinancialReturnWithoutPaging(queryInfo, user);
    }

    /**
     * Description :【复核退回】列表显示
     * @param queryInfo
     * @return record list
     * @author jiaodelong
     */
    @RequestMapping(value = "/inve/getreturnChecklist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getreturnChecklist(WmsInveDebtHeadSearchBeanVO queryInfo, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getreturnChecklist(queryInfo, user);
    }

    /**
     * Description :【复核退回】导出
     * @param queryInfo
     * @return record list
     * @author jiaodelong
     */
    @RequestMapping(value = "/inve/getreturncheckoutpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getreturncheckoutpaginglist(WmsInveDebtHeadSearchBeanVO queryInfo,
                                                           HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getreturncheckoutpaginglist(queryInfo, user);
    }

    /**
     * @Title: toSingleNullifyforSDSH 
     * Description :【理财上单】财务管理>理财上单>上单审核>单据作废
     * @param bean
     * @param wDebtWorkFlowVO
     * @return "success" or "error" or user definedSS
     * @date 2015年12月14日 14:56
     * @author hancd
     */
    @RequestMapping(value = "/inve/toSingleNullifyforSDSH.do", method = { RequestMethod.POST })
    @ResponseBody
    public String toSingleNullifyforSDSH(HttpServletRequest request, WmsInveTransa bean, WmsInveTransaProd wTransaProd,
                                         WmsInveDebtWorkFlowVO wDebtWorkFlowVO, String debtkey)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransaService.toSingleNullify(bean, wTransaProd, user, wDebtWorkFlowVO, debtkey);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        if ("success".equals(result))
        {
            String msg = "财务管理>理财上单>上单审核>单据作废操作";
            SysTools.saveLog(request, msg);
        }
        return result;
    }

    /**
     *@Title: getPrintProtocolWithPaginglist 
     * Description :【财务管理】-【理财上单】-【理财签约】(打印协议/客户确认)  获取签约列表数据
     * @param queryInfo
     * @return record list
     * @author hancd
     * @date 2015年12月15日 13:11
     */
    @RequestMapping(value = "/inve/getPrintProtocolWithPaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getPrintProtocolWithPaginglist(WmsInveTransaSearchBeanVO queryInfo,
                                                              HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getPrintProtocolWithPaginglist(queryInfo, user);
    }

    /**
     *@Title: getPrintProtocolWithoutPaginglist 
     * Description :【财务管理】-【理财上单】-【理财签约】(打印协议/客户确认)  获取签约列表导出
     * @param queryInfo
     * @return record list
     * @author hancd
     * @date 2015年12月15日 13:11
     */
    @RequestMapping(value = "/inve/getPrintProtocolWithoutPaginglist.do", method = { RequestMethod.GET,
                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getPrintProtocolWithoutPaginglist(WmsInveTransaSearchBeanVO queryInfo,
                                                                 HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getPrintProtocolWithoutPaginglist(queryInfo, user);
    }

    /**
     *@Title: getPrintProtocolWithoutPaginglist 
     * Description :【财务管理】-【理财上单】-【理财签约】(客户确认)操作
     * @param request
     * @param wTransaProd
     * @param wDebtWorkFlowVO
     * @param fileArr
     * @return "success" or "error" or other
     * @author hancd
     * @date 2015年12月16日 12:11
     */
    @RequestMapping(value = "/inve/toSingleConfirmforLCQY.do", method = { RequestMethod.POST })
    @ResponseBody
    public String toSingleConfirmforLCQY(HttpServletRequest request, WmsInveTransaProd wTransaProd,
                                         WmsInveDebtWorkFlowVO wDebtWorkFlowVO, String fileArr)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransaService.toSingleConfirmforLCQY(wTransaProd, user, wDebtWorkFlowVO, fileArr);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        if ("success".equals(result))
        {
            String msg = "财务管理>理财上单>理财签约>客户确认";
            SysTools.saveLog(request, msg);
        }
        return result;
    }

    /**
     * @Description :上单管理--支付退回列表
     * @param queryInfo
     * @return record list
     * @author baisong
     * @date 2015-12-16
     */
    @RequestMapping(value = "/inve/getListWithPagingForPaidReturn.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPagingForPaidReturn(WmsInveTransaSearchBeanVO queryInfo,
                                                              HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getListWithPagingForPaidReturn(queryInfo, user);
    }

    /**
     * Description :上单管理--支付退回列表  导出
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/inve/getListWithoutPagingForPaidReturnExcel.do", method = { RequestMethod.GET,
                                                                                         RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPagingForPaidReturnExcel(WmsInveTransaSearchBeanVO queryInfo,
                                                                      HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getListWithoutPagingForPaidReturnExcel(queryInfo, user);
    }

    /**
     * @Description :上单管理--审核退回列表
     * @param queryInfo
     * @return record list
     * @author baisong
     * @date 2015-12-16
     */
    @RequestMapping(value = "/inve/getListBackDirectorApproval.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListBackDirectorApproval(WmsInveTransaSearchBeanVO queryInfo,
                                                           HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getListBackDirectorApproval(queryInfo, user);
    }

    /**
     * Description :上单管理--审核退回列表  导出
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/inve/getListBackDirectorApprovalExcel.do", method = { RequestMethod.GET,
                                                                                   RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListBackDirectorApprovalExcel(WmsInveTransaSearchBeanVO queryInfo,
                                                                HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getListBackDirectorApprovalExcel(queryInfo, user);
    }

    /**
    * Description :理财上单保存理财信息--有支付页面信息的
    * 
    * @param bean
    * @return "success" or "error" or user defined
    * @author baisong
    */
    @RequestMapping(value = "/inve/wmsinvetransasaveupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSaveUpdate(HttpServletRequest request, WmsInveTransa wmsInveTransa,
                               WmsInveTransaProd wmsInveTransaProd, String fileArr, String saveFlag,
                               WmsInveCustomer wmsCustomer, WmsInveTransaSearchBeanVO beanVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvetransaService.saveUpdate(wmsInveTransa, wmsInveTransaProd, fileArr, user, saveFlag,
                                                     wmsCustomer, beanVO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "业务管理-理财管理-理财上单";
            SysTools.saveLog(request, msg);
        }
        return result;
    }

    /**
     * Description: 根据业务员的人员表的id信息进行获取对应的各个总的信息
     * @param personne_id 人员信息表主键
     * @param request 
     * @param response
     * @return
     * @author donghao
     * @date 2016年8月10日14:53:31
     */
    @RequestMapping(value = "/inve/wmsfindpersonnelinformation.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> wmsfindpersonnelinformation(@RequestParam("personne_id") int personne_id,
                                                           @RequestParam("concurrent_post_deptid") int concurrent_post_deptid,
                                                           HttpServletRequest request, HttpServletResponse response)
    {
        Map<String, Object> list = wmsinvetransaService.findPersonnelInformationByPersonnelId(personne_id,
                                                                                              concurrent_post_deptid);
        return list;
    }

    /**
     * @Deprecated 获取产品信息
     * @param request
     * @param response
     * @return
     * @author donghao
     * @date 2016年8月26日10:42:38
     */
    @RequestMapping(value = "/inve/wmsinvetransagetcategory.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<WmsInvePruductCategory> wmsinvetransagetcategory(HttpServletRequest request,
                                                                 HttpServletResponse response)
    {
        List<WmsInvePruductCategory> results = wmsinvetransaService.findCategoryAll();
        return results;
    }

    /**
     * @Deprecated 根据客户id获取对应的客户的上单和赎回的单据信息
     * @param request
     * @param response
     * @return
     * @author donghao
     * @date 2016年8月26日13:50:46
     */
    @RequestMapping(value = "/inve/wmsinvetransabills.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> wmsinvetransabills(WmsInveSelectBillBeanVo queryInfo, HttpServletRequest request,
                                                  HttpServletResponse response)
    {
        Map<String, Object> list = wmsinvetransaService.findWmsInveTransaBills(queryInfo);
        return list;
    }

    /**
     * 重新发送短信验证码通知客户 - 赎回时，点击重新发送功能调用
     * @param wms_inve_transa_id 理财上单主键
     * @return
     */
    @RequestMapping(value = "/inve/resendmessagenotification.do", method = { RequestMethod.POST })
    @ResponseBody
    public String reSendMessageNotification(String wms_inve_transa_id)
    {
        String result = "success";
        if (StringUtils.isEmpty(wms_inve_transa_id))
        {
            return "error";
        }
        result = wmsinvetransaService.reSendMessageNotification(wms_inve_transa_id);
        return result;
    }

    @RequestMapping(value = "/inve/regenerateincomeandlog.dos", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> reGenerateIncomeAndLog(String transaIds)
    {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("result", "success");
        try
        {
            wmsinvetransaService.reGenerateIncomeAndLog(transaIds);
        }
        catch (Exception e)
        {
            returnMap.put("result", "error");
        }
        return returnMap;
    }

    /**
     * @Title: hanleYearPublicIncome
     * @Description: 年付公益收益处理
     *      将原来按年支付生成的公益收益全部干掉后，按月生成公益收益（当前月份以超过新生成的公益收益应付月份状态设置成已支付（4））
     * @author: jinzhm
     * @time:2016年11月16日 下午5:58:58
     * history:
     * 1、2016年11月16日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/handleYearPublicIncome.dos", method = { RequestMethod.GET })
    @ResponseBody
    public void hanleYearPublicIncome()
    {
        wmsinvetransaService.handleYearPublicIncome();
    }

    /**
     * @Title getHistoryCustomerBankInfo
     * @Description 根据客户的姓名获取客户存在系统中的收益卡信息
     * @param cus_name
     *            客户名称
     * @param id_card
     *            证件号码
     * @param request
     * @param response
     * @return 返回list集合
     * @author DongHao
     * @date 2016年11月1日11:19:43
     */
    @RequestMapping(value = "/inve/getHistoryCustomerBankInfo.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<Map<String, Object>> getHistoryCustomerBankInfo(String cus_name, String id_card,
                                                                HttpServletRequest request, HttpServletResponse response)
    {
        List<Map<String, Object>> resultMap = wmsinvetransaService.getHistoryCustomerBankInfo(cus_name, id_card);
        return resultMap;
    }

    /**
     * @Title: isFinancialEmployee
     * @Description: 校验登录人是否为财务柜员权限用户
     * @param request 请求信息
     * @return 是否为财务柜员角色用户标记
     * @author: jinzhm
     * @time:2017年2月8日 上午8:20:08
     * history:
     * 1、2017年2月8日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/isFinancialEmployee.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> isFinancialEmployee(HttpServletRequest request)
    {
        // 获得登录用户信息
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        // 返回校验结果
        return wmsinvetransaService.isFinancialEmployee(user);
    }

    /**
     * 
     * @Title: printTransaProtocolpdf
     * @Description: 打印金额支付时 附件资金出借服务申请表
     * @param request
     * @param response
     * @param wms_inve_transa_id 
     * @author: zhangyunfei
     * @time:2017年2月14日 上午10:22:42
     * history:
     * 1、2017年2月14日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/printTransaProtocolpdf.pdf", method = { RequestMethod.GET, RequestMethod.POST })
    public void printTransaProtocolpdf(HttpServletRequest request, HttpServletResponse response,
                                       Integer wms_inve_transa_id)
    {
        if (wms_inve_transa_id == null)
        {
            return;
        }
        // pMap 存放人理财相关信息 用于打印附件
        Map<String, Object> pMap = wmsinvetransaService.getLcInfoByTransaId(wms_inve_transa_id);

        List<String> listUrl = new ArrayList<String>();
        listUrl.add("att_perCapitalLendApplyment.jasper");
        String baseDir = WmsInveClerkProtocolController.class.getResource("/").getPath();
        String logoDir = baseDir + "jasper/logo_zx.jpg";
        pMap.put("logoDir", logoDir);

        String fileurl = "";
        List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
        JasperPrint jasperPrint = null;
        try
        {
            for (int i = 0; i < listUrl.size(); i++)
            {
                fileurl = JasperUtil.class.getClassLoader().getResource("/").getPath() + "/jasper/" + listUrl.get(i);
                File file = new File(fileurl);
                InputStream jr = new FileInputStream(file);
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jr);
                jasperReport.getFields();

                jasperPrint = JasperFillManager.fillReport(jasperReport, pMap, new JREmptyDataSource());
                jasperPrintList.add(jasperPrint);

            }
            JasperUtil.doprint(jasperPrintList, response);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 
     * @Title: getAdjustShortMessage_grid
     * @Description: 根据id查询上单客户短信设置信息
     * @param id
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月14日 上午11:46:49
     * history:
     * 1、2017年3月14日 zhangmingjian 创建方法
     */
    @RequestMapping(value = "/inve/getAdjustShortMessage_grid.do")
    @ResponseBody
    public Map<String, Object> getAdjustShortMessage_grid(HttpServletRequest request)
    {
        String id = (String) request.getParameter("id");
        Map<String, Object> result = new HashMap<>();
        result.put("Rows", wmsinvetransaService.getAdjustShortMessage_grid(id));
        return result;
    }

    /**
     * 
     * @Title: adjustShortMessage_update
     * @Description:更新批量短信设置信息
     * @param request
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月15日 下午6:43:34
     * history:
     * 1、2017年3月15日 zhangmingjian 创建方法
     */
    @RequestMapping(value = "/inve/adjustShortMessage_update.do")
    @ResponseBody
    public boolean adjustShortMessage_update(HttpServletRequest request, String data)
    {
        boolean flag = true;
        if (StringUtils.isNotBlank(data))
        {
            List<Map<String, Object>> list = null;
            ObjectMapper om = new ObjectMapper();
            try
            {
                list = om.readValue(data, new TypeReference<List<Map<String, Object>>>()
                {
                });
                flag = wmsinvetransaService.adjustShortMessage_update(request, list);
            }
            catch (JsonParseException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                flag = false;
            }
            catch (JsonMappingException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                flag = false;
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                flag = false;
            }

        }
        else
        {
            flag = false;
        }

        return flag;
    }

    /**
     * 
     * @Title: savePayVouchersInfo
     * @Description: 保存补传凭证附件
     * @param request
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月16日 下午5:04:19
     * history:
     * 1、2017年3月16日 zhangmingjian 创建方法
     */
    @RequestMapping(value = "/inve/savePayVouchersInfo.do")
    @ResponseBody
    public boolean savePayVouchersInfo(HttpServletRequest request, String data)
    {
        boolean flag = true;
        List<Map<String, Object>> list = null;
        ObjectMapper om = new ObjectMapper();
        try
        {
            list = om.readValue(data, new TypeReference<List<Map<String, Object>>>()
            {
            });
            flag = wmsinvetransaService.savePayVouchersInfo(list);
        }
        catch (JsonParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            flag = false;
        }
        catch (JsonMappingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            flag = false;
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }


    /**
     * 
     * @Title: getInveIncomeBillInfos
     * @Description: 收益账单信息表
     * @param request 页面信息
     * @param queryInfo 查询条件对象
     * @return  返回数据集合
     * @author: DongHao
     * @time:2017年3月31日 上午9:17:43
     * history:
     * 1、2017年3月31日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getInveIncomeBillInfos.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getInveIncomeBillInfos(HttpServletRequest request, WmsInveIncomeBillVo queryInfo)
    {

        // 获取当前登录人信息
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);

        return wmsinvetransaService.getInveIncomeBillInfos(queryInfo, user);
    }

    /**
     * 
     * @Title: getWmsInveTransaDataStatus
     * @Description: 获取收益账单信息单据状态
     * @return  返回list集合
     * @author: DongHao
     * @time:2017年3月31日 上午11:56:18
     * history:
     * 1、2017年3月31日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getWmsInveTransaDataStatus.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<Map<String, Object>> getWmsInveTransaDataStatus()
    {
        return wmsinvetransaService.getWmsInveTransaDataStatus();
    }

    /**
     * 
     * @Title: findIncomeMonth
     * @Description: 获取最大收益月份
     * @return 返回list集合
     * @author: DongHao
     * @time:2017年3月31日 下午1:09:51
     * history:
     * 1、2017年3月31日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getMaxIncomeMonth.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getMaxIncomeMonth()
    {
        return wmsinvetransaService.getMaxIncomeMonth();
    }

    /**
     * 
     * @Title: verifyBillIdCardIsConsistent
     * @Description: 根据传入的ids进行获取单据,并且进行判断是否有效证件一致
     * @param ids 上单表主键ids字符串(多个以逗号分隔)
     * @return 验证有效证件一致返回true,不一致返回false
     * @author: DongHao
     * @time:2017年3月31日 下午3:32:54
     * history:
     * 1、2017年3月31日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/verifyBillIdCardIsConsistent.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public boolean verifyBillIdCardIsConsistent(String ids)
    {
        return wmsinvetransaService.verifyBillIdCardIsConsistent(ids);
    }

    /**
     * 
     * @Title: getCategoryTotalBillInfos
     * @Description: 根据单据id进行获取对应的单据的收益信息
     * @param ids 上单表主键(多个单据id以","分隔)
     * @param return_date 收益月份
     * @param type 获取数据的类型(1表示已获收益, 2表示待获收益)
     * @return 返回数据集合
     * @author: DongHao
     * @time:2017年4月1日 上午10:02:21
     * history:
     * 1、2017年4月1日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getCategoryTotalBillInfos.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<Map<String, Object>> getCategoryTotalBillInfos(String ids, String return_date, Integer type)
    {
        return wmsinvetransaService.getCategoryTotalBillInfos(ids, return_date, type);
    }

    /**
     * 
     * @Title: getYueFenTotalBillInfos
     * @Description: 获取按照月份进行统计的单据的收益信息
     * @param return_date 收益月份
     * @param type 查询数据的类型(1表示获取已获收益,2表示待获收益)
     * @param ids 上单表主键字符串(多个以","分隔)
     * @return 返回数据集合
     * @author: DongHao
     * @time:2017年4月1日 下午3:42:03
     * history:
     * 1、2017年4月1日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getYueFenTotalBillInfos.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<Map<String, Object>> getYueFenTotalBillInfos(String return_date, Integer type, String ids)
    {
        return wmsinvetransaService.getYueFenTotalBillInfos(return_date, type, ids);
    }

    /**
     * 
     * @Title: getCustomerTotalBillInfos
     * @Description: 获取客户收益总信息
     * @param return_date 收益日期
     * @param ids 上单表主键字符串类型(多个主键以","号分隔)
     * @return 返回map客户收益账单信息
     * @author: DongHao
     * @time:2017年4月5日 下午4:53:10
     * history:
     * 1、2017年4月5日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getCustomerTotalBillInfos.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCustomerTotalBillInfos(String return_date, String ids)
    {

        return wmsinvetransaService.getCustomerTotalBillInfos(return_date, ids);
    }

    /**
     * @Title: printCustomerIncomeBillpdf
     * @Description: 打印客户收益账单
     * @param request
     * @param response
     * @param return_date   收益月份
     * @param ids   上单产品主键集合(逗号分隔字符串)
     * @author: zhangyunfei
     * @time:2017年4月13日 上午10:30:10
     * history:
     * 1、2017年4月13日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/printCustomerIncomeBillpdf.pdf", method = { RequestMethod.GET, RequestMethod.POST })
    public void printCustomerIncomeBillpdf(HttpServletRequest request, HttpServletResponse response,
                                           String return_date, String ids)
    {
        if (ids == null)
        {
            return;
        }

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        // 打印客户收益账单
        wmsinvetransaService.printCustomerIncomeBillpdf(return_date, ids, response);
    }

    /**
     * 
     * @Title: verifyIsExistCredit
     * @Description: 验证债权每日销售限额是否可用
     * @param wms_inve_transa_id 上单表主键
     * @param request 页面对象信息
     * @param product_account 投资金额
     * @return 返回布尔类型的变量,每日销售限额可以使用返回true,不可以使用返回false
     * @author: DongHao
     * @time:2017年3月1日 上午12:53:02
     * history:
     * 1、2017年3月1日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/verifyIsExistCredit.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public boolean verifyIsExistCredit(Integer wms_inve_transa_id, HttpServletRequest request)
    {
        // 获取当前登录人信息
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);

        return wmsinvetransaService.verifyIsExistCredit(wms_inve_transa_id, user);
    }

    /**
     * 
     * @Title: getRole
     * @Description: 获取角色权限
     * @param request
     * @return 
     * @author: zhangmingjian
     * @time:2017年5月8日 下午5:52:59
     * history:
     * 1、2017年5月8日 zhangmingjian 创建方法
     */
    @RequestMapping(value = "/inve/getRole.do")
    @ResponseBody
    public Map<String, Object> getRole(HttpServletRequest request)
    {
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransaService.getRole(user.getUserId());
    }
    
    /**
     * 
     * @Title: wmsVerifyCustomerByPtp
     * @Description: 在wms系统中验证当前登录ptp的用户是否在wms投资过,并且符合要求
     * @param costomer_id crm客户id
     * @param month_num 要求的投资月数
     * @return 返回map集合信息提示
     * @author: DongHao
     * @time:2017年6月19日 上午8:55:14
     * history:
     * 1、2017年6月19日 DongHao 创建方法
     */
    @RequestMapping(value="/inve/wmsVerifyCustomerByPtp.dos", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> wmsVerifyCustomerByPtp(Integer costomer_id, Integer month_num)
    {
        //定义返回结果信息的map集合
        Map<String, Object> resMap = new HashMap<String, Object>();
        
        //定义变量用来标记当前客户是否在wms满足投资要求,满足返回true,否则返回false,默认为false
        boolean bool = false;
        
        try
        {
            //进行验证当前登录的客户是否满足在wms的投资要求
            bool = wmsinvetransaService.wmsVerifyCustomerByPtp(costomer_id, month_num);
            resMap.put("ret_code", "000");
            resMap.put("ret_msg", "操作成功!");
            if(bool)
            {
                resMap.put("chack_result", 1);
            }
            else
            {
                resMap.put("chack_result", 0);  
            }
            
        }
        catch (Exception e)
        {
            resMap.put("ret_code", "001");
            resMap.put("ret_msg", "操作失败!");
            resMap.put("chack_result", 0);
        }
        
        return resMap;
    }
}