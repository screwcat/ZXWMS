package com.zx.emanage.inve.web;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.zx.emanage.inve.service.IWmsInveRedeemDetailService;
import com.zx.emanage.inve.service.IWmsInveRedeemService;
import com.zx.emanage.inve.service.IWmsInveReplaceService;
import com.zx.emanage.inve.service.IWmsInveSignedApplicationService;
import com.zx.emanage.inve.service.IWmsInveTransaProdService;
import com.zx.emanage.inve.service.IWmsInveTransaService;
import com.zx.emanage.inve.service.IWmsInveWorkFlowService;
import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.emanage.inve.vo.WmsInveRedeemSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveRedeemVO;
import com.zx.emanage.sysmanage.service.IPmPersonnelService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveRedeemAtt;
import com.zx.emanage.util.gen.entity.WmsInveRedeemDetail;
import com.zx.emanage.util.gen.entity.WmsInveTransaCard;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.emanage.workflow.util.WorkflowHistoryInfoHelp;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * @ClassName: WmsInveRedeemController
 * @Description: 赎回相关操作
 * @author zhangyunfei
 * @date 2016年11月23日
 * @version V1.0
 * history:
 * 1、2016年11月23日 Administrator 创建文件
 */
@Controller
public class WmsInveRedeemController
{
    private static Logger log = LoggerFactory.getLogger(WmsInveRedeemController.class);

    @Autowired
    private IWmsInveRedeemService wmsinveredeemService;

    @Autowired
    private IWmsInveRedeemService wmsInveTransaCardService;

    @Autowired
    private IWmsInveWorkFlowService aInveWorkFlowService;
    
    @Autowired
    private IWmsInveReplaceService wmsinvereplaceService;

    @Autowired
    private IWmsInveTransaProdService wmsinvetransaprodService;

    @Autowired
    private IWmsInveTransaService wmsInveTransaServiceImpl;

    @Autowired
    private IWmsInveRedeemDetailService wmsinveredeemdetailService;

    @Autowired
    private IPmPersonnelService pmpersonnelService;

    @Autowired
    private IWmsInveSignedApplicationService wmsInveSignedApplicationService;

    @Autowired
    private IWmsInveTransaService wmsInveTransaService;
    /**
     * Description :实现各个领导审批页面的数据导出
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinveredeemwithoutpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(HttpServletRequest request, WmsInveRedeemSearchBeanVO queryInfo)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinveredeemService.getListWithoutPaging(queryInfo, user);
    }

    /**
     * Description : 获取各个领导审批页面数据显示
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     */
    @RequestMapping(value = "/inve/wmsinveredeemwithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(HttpServletRequest request, WmsInveRedeemSearchBeanVO queryInfo)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinveredeemService.getListWithPaging(queryInfo, user);
    }

    /**
     * Description : 获取赎回分页数据
     * 
     * @param queryInfo
     * @return record list
     * @author ry
     */
    @RequestMapping(value = "/inve/wmsinveredeemwithpaginglistforquerysh.do", method = { RequestMethod.GET,
                                                                                        RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPagingForQuerySH(HttpServletRequest request,
                                                           WmsInveRedeemSearchBeanVO queryInfo)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinveredeemService.getListWithPagingForQuerySH(queryInfo, user);
    }

    /**
     * @Title: exportFinancialRedeem
     * @Description: 财务主管导出赎回报表
     * @param queryInfo 查询条件
     * @param response 响应信息
     * @author: jinzhm
     * @time:2017年5月10日 下午5:10:43
     * history:
     * 1、2017年5月10日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/exportFinancialRedeem.do", method = { RequestMethod.GET, RequestMethod.POST })
    public void exportFinancialRedeem(WmsInveRedeemSearchBeanVO queryInfo, HttpServletResponse response)
    {
        wmsinveredeemService.exportFinancialRedeem(queryInfo, response);
    }

    /**
     * @Title: exportRedeem
     * @Description: 柜员主管导出赎回报表
     * @param queryInfo 查询条件
     * @param response 响应信息
     * @author: jinzhm
     * @time:2017年5月11日 下午5:00:05
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/exportRedeem.do", method = { RequestMethod.GET, RequestMethod.POST })
    public void exportRedeem(WmsInveRedeemSearchBeanVO queryInfo, HttpServletResponse response)
    {
        wmsinveredeemService.exportRedeem(queryInfo, response);
    }

    /**
     * Description :获取赎回不分页数据
     * 
     * @param queryInfo
     * @return record list
     * @author ry
     */
    @RequestMapping(value = "/inve/wmsinveredeemwithoutpaginglistforquerysh.do", method = { RequestMethod.GET,
                                                                                           RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPagingForQuerySH(HttpServletRequest request,
                                                              WmsInveRedeemSearchBeanVO queryInfo)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinveredeemService.getListWithoutPagingForQuerySH(queryInfo, user);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInveRedeemVO
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinveredeeminfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsInveRedeem getInfoByPK(Integer wms_inve_redeem_id)
    {
        return wmsinveredeemService.getInfoByPK(wms_inve_redeem_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinveredeemsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsInveRedeem bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinveredeemService.save(bean, user);
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
    @RequestMapping(value = "/inve/wmsinveredeemupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsInveRedeem bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinveredeemService.update(bean, user);
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
     * 获取财务回款列表页面
     * 
     * @param queryInfo
     * @return record list
     * @author 张风山
     */
    @RequestMapping(value = "/inve/wmsInveRedeemList.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getWmsInveRedeemList(WmsInveRedeemSearchBeanVO queryInfo, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinveredeemService.getWmsInveredeemList(queryInfo, user);
    }

    /**
     * 获取财务回款列表页面--导出
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/inve/wmsInveRedeemListforEx.do", method = { RequestMethod.GET, RequestMethod.POST })
    public void getWmsInveRedeemListforexl(WmsInveRedeemSearchBeanVO queryInfo,
                                                          HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        wmsinveredeemService.getWmsInveRedeemListforexl(queryInfo, user, response);
    }

    /**
     * Description :查询赎回回款账户，请选择使用‘-1’
     * 
     * @return list
     * @author 张风山
     */
    @RequestMapping(value = "/inve/wmsinvepaybackcardlistforselect.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<WmsInveTransaCard> getInvePruductCategory(String wms_inve_redeem_id)
    {

        List<WmsInveTransaCard> wmsInveTransaCard = wmsinveredeemService.getCardListForSelect(Integer.parseInt(wms_inve_redeem_id));
        return wmsInveTransaCard;
    }

    /**
     * 实现赎回流程查看
     * 
     * @param wms_inve_redeem_id
     * @author hancd
     */
    @RequestMapping(value = "/inve/approvalProcessView.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getWmsInveRedemApprovalProcessView(String wms_inve_redeem_id)
    {
        return aInveWorkFlowService.inveWorkFlowProcess(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS,wms_inve_redeem_id);
    }

    /**
     * 验证赎回日期是否大于当月1号
     * @param   WmsInveRedeemVO  
     * @return  String
     * @author  zhangyunfei
     * @date    2016年08月10日
     */
    public String checkRedeemDate(WmsInveRedeem bean)
    {
        String result = "";
        Date now = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum((Calendar.DAY_OF_MONTH)));
        now = DateUtil.strDate((new SimpleDateFormat("yyyy-MM-dd")).format(calendar.getTime()), null);
        // 判断申请赎回日期是否大于当前月1号
        if (!(now.compareTo(DateUtil.strDate(bean.getRedeem_date().toString(), null)) <= 0))
        {
            return "dateerror";
        }
        return result;
    }
    
    
    /**
     * 
     * @Title: saveWmsInveRedeem
     * @Description: 保存赎回申请信息
     * @param request
     * @param bean
     * @param wmsInveRedeemDetail   
     * @param redeemGridData        (页面拼接的grid)
     * @param end_of_date_org       (原始到期日期)
     * @param is_order_redeem       (是否预约赎回)
     * @return result               
     * @author: zhangyunfei
     * @time:2016年11月23日 下午2:56:43
     * history:
     * 1、2016年11月23日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/saveWmsInveRedeem.do", method = { RequestMethod.POST })
    @ResponseBody
    public String saveWmsInveRedeem(HttpServletRequest request, WmsInveRedeem bean, WmsInveRedeemDetail wmsInveRedeemDetail, WmsInveRedeemVO wmsInveRedeemVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            // 验证赎回日期是否大于当月1号
            result = checkRedeemDate(bean);
            if (result.equals("dateerror"))
            {
                return result;
            }
            // 班里赎回由客户经理代办 需验证验证码
            if (wmsInveRedeemDetail.getIs_handle_self().equals("0"))
            {
                WmsInveTransaProd wmsInveTransaProd = wmsinvetransaprodService.getInfoByPKForJEGL(wmsInveRedeemDetail.getWms_inve_transa_id());
                // 验证码错误返回smserror
                if (!wmsInveRedeemDetail.getRedeem_sms_code().equals(wmsInveTransaProd.getRedeem_sms_code()))
                {
                    result = "smserror";
                    return result;
                }
            }
            //更改赎回类型
            if ("3".equals(bean.getRedeem_company_reason()))
            {// 协议到期
                bean.setRedeem_type("4");
            }
            else
            {
                bean.setRedeem_type("1");
            }
            result = wmsinveredeemService.saveWmsInveRedeem(bean, user, wmsInveRedeemDetail, wmsInveRedeemVO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // 保存日志
        if ("success".equals(result))
        {
            String msg = "业务管理-理财管理-赎回申请";
            //SysTools.saveLog(request, msg);
            // 非股东单据 并且不是全部赎回
            if (wmsInveTransaService.verifyIsShareholderBill(wmsInveRedeemDetail.getWms_inve_transa_id()) && !"1".equals(bean.getIs_fully_redeemed()))
            {
                int count = wmsInveSignedApplicationService.findClerkDataBySalemanCount(user);
                result = "尊敬的客户您好:<br/>&nbsp;&nbsp;&nbsp;&nbsp;您的前方正有" + (count - 1) + "个客户等待办理业务,请关注窗口信息,过号则会重排";
            }          
        }
        return result;
    }

    /**
     * Description :获得赎回修改列表
     * 
     * @param queryInfo
     * @return record list
     * @author zhubo
     */
    @RequestMapping(value = "/inve/getcheckreturnlist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCheckReturnList(HttpServletRequest request, WmsInveRedeemSearchBeanVO queryInfo)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinveredeemService.checkReturnList(queryInfo, user);
    }

    /**
     * Description : 导出赎回修改列表
     * 
     * @param queryInfo
     * @return record list
     * @author zhubo
     */
    @RequestMapping(value = "/inve/wmsinvecheckreturnlistwithout.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> checkReturnListWithout(HttpServletRequest request, WmsInveRedeemSearchBeanVO queryInfo)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinveredeemService.checkReturnListWithout(queryInfo, user);
    }

    /**
     * 保存回款信息
     * 
     * @param queryInfo
     * @return record list
     * @author 张风山
     */
    @RequestMapping(value = "/inve/wmsinvepaybacksaveinfo.do", method = { RequestMethod.POST })
    @ResponseBody
    public String saveRedeemPayBackInfo(HttpServletRequest request, WmsInveRedeem bean, String fileArr,
 WmsInveDebtWorkFlowVO wmsinvedebtworkflowvo)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinveredeemService.saveRedeemPayBackInfo(bean, user, fileArr, wmsinvedebtworkflowvo);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        if ("success".equals(result))
        {
            String msg = "财务管理--理财赎回--财务回款";
            SysTools.saveLog(request, msg);
        }
        return result;
    }

    /**
     * 保存理财赎回 特批信息
     * 
     * @param queryInfo
     * @return record list
     * @author 张风山
     */
    @RequestMapping(value = "/inve/wmsinvespecialapprovalsaveinfo.do", method = { RequestMethod.POST })
    @ResponseBody
    public String savespecialapprovalInfo(HttpServletRequest request, WmsInveRedeem bean,
 WmsInveDebtWorkFlowVO wDebtWorkFlowVO)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinveredeemService.saveRedeemspecialapprovalInfo(bean, user, wDebtWorkFlowVO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        if ("success".equals(result))
        {
            String msg = "财务管理--理财赎回--财务回款--特批处理";
            SysTools.saveLog(request, msg);
        }
        return result;
    }

    /**
     * 修改赎回申请信息
     * 
     * @param queryInfo
     * @return record list
     * @author 朱博
     */
    @RequestMapping(value = "/inve/updateWmsInveRedeem.do", method = { RequestMethod.POST })
    @ResponseBody
    public String updateWmsInveRedeem(HttpServletRequest request, WmsInveRedeem bean,WmsInveRedeemDetail wmsInveRedeemDetail,String redeemGridData,
                                      String taskId, String cxdeptid)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = checkRedeemDate(bean);
            if (!result.equals("dateerror"))
            {
                if ("3".equals(bean.getRedeem_company_reason()))
                {// 协议到期
                    bean.setRedeem_type("4");
                }
                else
                {
                    bean.setRedeem_type("1");
                }
                result = wmsinveredeemService.updateWmsInveRedeem(bean, user,wmsInveRedeemDetail,redeemGridData, taskId, cxdeptid);
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        if ("success".equals(result))
        {
            String msg = "业务管理-理财管理-赎回修订";
            SysTools.saveLog(request, msg);
        }
        return result;
    }

    /**
     * 取消赎回申请信息
     * 
     * @param queryInfo
     * @return record list
     * @author 朱博
     */
    @RequestMapping(value = "/inve/cancelWmsInveRedeem.do", method = { RequestMethod.POST })
    @ResponseBody
    public String cancelWmsInveRedeem(HttpServletRequest request, WmsInveDebtWorkFlowVO vo)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinveredeemService.cancelWmsInveRedeem(user, vo);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        if ("success".equals(result))
        {
            String msg = "业务管理-理财管理-取消赎回";
            SysTools.saveLog(request, msg);
        }
        return result;
    }

    /**
     * 
     * @Title: cancelWmsInveRedeemByCw
     * @Description: 理财赎回页面作废赎回单据
     * @param request
     * @param vo
     * @return 
     * @author: Guanxu
     * @time:2016年11月4日 下午4:56:53
     * history:
     * 1、2016年11月4日 Guanxu 创建方法
     */
    @RequestMapping(value = "/inve/cancelWmsInveRedeemByCw.do", method = { RequestMethod.POST })
    @ResponseBody
    public String cancelWmsInveRedeemByCw(HttpServletRequest request, WmsInveDebtWorkFlowVO vo)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinveredeemService.cancelWmsInveRedeemByCw(user, vo);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        if ("success".equals(result))
        {
            String msg = "业务管理-理财管理-取消赎回";
            SysTools.saveLog(request, msg);
        }
        return result;
    }

    /**
     * Description : 获取赎回附件信息
     * 
     * @param queryInfo
     * @return record list
     * @author zhubo
     */
    @RequestMapping(value = "/inve/getAttList.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<WmsInveRedeemAtt> getAttList(Integer wms_inve_redeem_id)
    {
        return wmsinveredeemService.getAttList(wms_inve_redeem_id);
    }
    /**
     * Description : 获取各个领导审批页面数据显示---给手机审批页面的
     * 
     * @param user_id
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/inve/phoneGetBackInfolist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> phoneGetBackInfo(String personnel_shortcode,String searchInfo)
    {
        if (personnel_shortcode != null)
        {
            return wmsinveredeemService.phoneGetBackInfo(personnel_shortcode, searchInfo);
        }
        else
        {
            return null;
        }
    }
    /**
     * Description : 获取各个领导审批页面数据显示---给手机审批页面的
     * 
     * @param user_id
     * @return record list
     * @author baisong
     */
    @RequestMapping(value = "/inve/phoneGetBackInfoAndHistory.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> phoneGetBackInfoAndHistory(String wms_inve_redeem_id,String taskId,String wms_inve_replace_id)
    {
        if (StringUtil.isNotBlank(wms_inve_redeem_id))
        {
            Map<String, Object> map = wmsinveredeemService.phoneGetBackInfoAndHistory(wms_inve_redeem_id);
            map.put("taskId", taskId);
            map.put("wms_inve_redeem_id", wms_inve_redeem_id);
            // 2016-11-23 查询流程历程时使用拆分前的赎回表主键
            Map<String, Object> reMap = aInveWorkFlowService.inveWorkFlowProcess(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS, "" + map.get("workflow_id"));
            map.put("Rows", reMap.get("Rows"));
            return map;
        }
        else if (StringUtil.isNotBlank(wms_inve_replace_id))
        {
            return wmsinvereplaceService.get4Appro(new Integer(wms_inve_replace_id));
        }
        else
        {
            return null;
        }
    }
    

    /**
     * Description : 赎回特批数据显示
     * 
     * @param queryInfo
     * @return record list
     * @author jiaodelong
     */
    @RequestMapping(value = "/inve/specialRedemptionList.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getSpecialRedemptionList(HttpServletRequest request, WmsInveRedeemSearchBeanVO queryInfo)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinveredeemService.getSpecialRedemptionList(queryInfo, user);
    }
    
    /**
     * @Titile:getRedeemApplyinfoByPk
     * Description:通过wms_inve_transa_id和wms_inve_redeem_id 查询赎回客户详细信息
     * @author zhangyunfei
     */
    @RequestMapping(value = "/inve/getredeemapplyinfoByPk.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getredeemapplyinfoByPk(HttpServletRequest request, Integer wms_inve_transa_id, Integer wms_inve_redeem_id, String is_return)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        Map<String, Object> map = wmsinveredeemService.getRedeemApplyinfoByPk(wms_inve_transa_id, wms_inve_redeem_id, is_return);
        map.put("a_info", user.getRealname() + " " + user.getUserCode());
        return map;
    }

    
    /**
     * Description : 赎回申请页面打印
     * 使用ireport模板打印(pdf)
     * @author zhangyunfei
     */
    @RequestMapping(value = "/inve/ireportpdf.pdf", method = { RequestMethod.GET, RequestMethod.POST })  
    public ModelAndView showReport(String paramJson, HttpServletRequest request) throws FileNotFoundException, JRException {  
        String fileurl = "";
        Gson gson = new Gson();
        Map<String, Object> parameterMap = gson.fromJson(paramJson, HashMap.class);
        if (parameterMap == null)
        {
            return null;
        }
        Map<String, String> map = wmsInveTransaServiceImpl.findCardnoAndIdCard((String) parameterMap.get("wms_inve_transa_id"));

        parameterMap.put("card_no", map.get("card_no").toString());
        // 身份证暂时需要加密
        // parameterMap.put("b_id_card", map.get("b_id_card").toString());

        // jasper文件路径 判断是否到期赎回 使用不同模板
        if ("1".equals(parameterMap.get("is_over_date").toString()))
        {
            fileurl = "simpleReport4";
        }
        else
        {
            fileurl = "simpleReport3";
        }
        JREmptyDataSource emptyData = new JREmptyDataSource();
        parameterMap.put("datasource", emptyData); 

        return new ModelAndView(fileurl, parameterMap); // simpleReport和views.properties配置的相同
    }  
    
    /**
     * 
     * @Title: getRedeemApplyInfoByPkMoa
     * @Description: 主键查询单据的赎回详细信息和流程
     * @param wms_inve_redeem_id
     * @param personnel_id 当前登录MOA的人员id
     * @return 
     * @author: zhangyunfei
     * @time:2016年12月7日 上午9:49:14
     * history:
     * 1、2016年12月7日 Administrator 创建方法
     * 2、2016年12月15日 DongHao 修改方法  在方法上增加当前MOA的登录人员id
     */
    @RequestMapping(value = "/inve/getRedeemApplyInfoByPkMoa.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getRedeemApplyInfoByPkMoa(String wms_inve_redeem_id, String personnel_id)
    {
        Date d1 = null;
        Date d2 = null;
        int betweenDays = 0;
        int hours = 0;
        int minutes = 0;
        Map<String, Object> map = wmsinveredeemService.getRedeemApplyInfoByPkMoa(Integer.valueOf(wms_inve_redeem_id), Integer.valueOf(personnel_id));

        // 2016-11-23 查询流程历程时使用拆分前的赎回表主键
        Map<String, Object> reMap = aInveWorkFlowService.inveWorkFlowProcess(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS, "" + map.get("workflow_id"));
        List<WorkflowHistoryInfoHelp> works = (List<WorkflowHistoryInfoHelp>) reMap.get("Rows");
        for (int i = 0; i < works.size(); i++)
        {
            // i=0 该单据状态为提交状态 没有处理单据的时间间隔
            if (i != 0)
            {
                d1 = DateUtil.strDate(works.get(i).getApproveTime(), "yyyy-MM-dd HH:mm:ss");
                d2 = DateUtil.strDate(works.get(i - 1).getApproveTime(), "yyyy-MM-dd HH:mm:ss");
                long seconds = DateUtil.getBetweenSecondsInt(d1, d2);
                betweenDays = (int) Math.abs(seconds / (1000 * 60 * 60 * 24));
                hours = (int) Math.abs((seconds % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                // 不足一分钟按一分钟计算
                minutes = (int) Math.abs((seconds % (1000 * 60 * 60 * 24)) % (1000 * 60 * 60) / (1000 * 60)) == 0 ? 1 : (int) Math.abs((seconds % (1000 * 60 * 60 * 24)) % (1000 * 60 * 60) / (1000 * 60));
                // 拼接审核处理过程花费的时间
                works.get(i).setApproveTimeInterval(betweenDays + "天" + hours + "小时" + minutes + "分钟");
                // 单据已完成
                if (!("回款".equals(works.get(i).getTaskName())) && !("重新修订".equals(works.get(i).getTaskName())))
                {
                    works.get(i).setTaskName("审批");
                }

                if ("重新修订".equals(works.get(i).getTaskName()))
                {
                    works.get(i).setTaskName("修订");
                }
            }
            // 单纯的修改第一个流程的taskName
            if (i == 0)
            {
                works.get(0).setTaskName("提交");
            }

            if ("审批通过".equals(works.get(i).getApproveResult()) || "已提交".equals(works.get(i).getApproveResult()))
            {
                works.get(i).setApprove_status(1);
            }
            else if ("待审核".equals(works.get(i).getApproveResult()))
            {
                works.get(i).setApprove_status(0);
            }
            else if ("审批不通过".equals(works.get(i).getApproveResult()))
            {
                works.get(i).setApprove_status(2);
            }
            // 拼接审批人员+短工号
            works.get(i).setApprovers(works.get(i).getApprovers() + " " + works.get(i).getPersonnel_shortCode());
        }

        // 获取流程
        getWorkFlowListByStatus(map, works);

        map.put("Rows", works);
        return map;
    }
    
    private WorkflowHistoryInfoHelp setWorkFlow(String approvers, String approversName, String personnel_postName, String taskName)
    {
        WorkflowHistoryInfoHelp work = new WorkflowHistoryInfoHelp();
        work.setApprove_status(0);
        work.setApprovers(approvers);
        work.setPersonnel_deptName(approversName);
        work.setPersonnel_postName(personnel_postName);
        work.setTaskName(taskName);
        return work;
    }

    /**
     * 
     * @Title: getWorkFlowListByStatus
     * @Description: 获取所有的流程节点
     * @param map
     * @param works 
     * @author: zhangyunfei
     * @time:2016年12月9日 上午8:50:43
     * history:
     * 1、2016年12月9日 Administrator 创建方法
     */
    private void getWorkFlowListByStatus(Map<String, Object> map, List<WorkflowHistoryInfoHelp> works)
    {
        // data_status 1 部门经理 2 副总 3 总 4 待修订 5 待回款 6 已完成 7 已终止
        if (Integer.parseInt(map.get("data_status").toString()) == 1 || Integer.parseInt(map.get("data_status").toString()) == 4)
        {
            // 部门经理
            works.add(setWorkFlow(map.get("bel_department_manager_name") + "", map.get("bel_department_manager_dept_name") + "", map.get("bel_department_manager_postName") + "", "审批"));
            // 副总
            works.add(setWorkFlow(map.get("bel_vice_general_manager_name") + "", map.get("bel_vice_general_manager_dept_name") + "", map.get("bel_vice_general_manager_postName") + "", "审批"));
            // 总
            works.add(setWorkFlow(map.get("bel_general_manager_name") + "", map.get("bel_general_manager_dept_name") + "", map.get("bel_general_manager_postName") + "", "审批"));

        }
        if (Integer.parseInt(map.get("data_status").toString()) == 2)
        {
            // 副总
            works.add(setWorkFlow(map.get("bel_vice_general_manager_name") + "", map.get("bel_vice_general_manager_dept_name") + "", map.get("bel_vice_general_manager_postName") + "", "审批"));
            // 总
            works.add(setWorkFlow(map.get("bel_general_manager_name") + "", map.get("bel_general_manager_dept_name") + "", map.get("bel_general_manager_postName") + "", "审批"));
        }
        if (Integer.parseInt(map.get("data_status").toString()) == 3)
        {
            // 总
            works.add(setWorkFlow(map.get("bel_general_manager_name") + "", map.get("bel_general_manager_dept_name") + "", map.get("bel_general_manager_postName") + "", "审批"));
        }
        // 财务(非终止 非已完成)
        if (Integer.parseInt(map.get("data_status").toString()) != 6 && Integer.parseInt(map.get("data_status").toString()) != 7 && Integer.parseInt(map.get("data_status").toString()) != 8)
        {
            String cwzgcode = PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.cw.cwzgcode");
            PmPersonnel pmPersonnel = new PmPersonnel();
            pmPersonnel.setPersonnel_shortcode(cwzgcode);
            List<PmPersonnel> pList = pmpersonnelService.getPmPersonnelListByEntity(pmPersonnel);
            works.add(setWorkFlow(pList.get(0).getPersonnel_name() + " " + cwzgcode, pList.get(0).getPersonnel_deptname(), pList.get(0).getPersonnel_postname(), "回款"));
        }

    }


    /**
     * 
     * @Title: updateWmsInveRedeemDetailMoa
     * @Description: 手机审核赎回单据
     * @param personnel_shortcode
     * @param wDebtWorkFlowVO
     * @param wms_inve_redeem_id
     * @return 
     * @author: zhangyunfei
     * @time:2016年12月9日 上午10:08:12
     * history:
     * 1、2016年12月9日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/updateWmsInveRedeemDetailMoa.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> updateWmsInveRedeemDetailMoa(HttpServletRequest request, String personnel_shortcode, WmsInveDebtWorkFlowVO wDebtWorkFlowVO, String wms_inve_redeem_id)
    {
        String result = "success";
        Map<String, Object> map = new HashMap<>();
        try
        {
            WmsInveRedeem wmsInveRedeem = wmsinveredeemService.getInfoByPK(Integer.parseInt(wms_inve_redeem_id));
            wDebtWorkFlowVO.setStatus(wmsInveRedeem.getData_status());
            Map<String, Object> resultMap = new HashMap<>();
            resultMap = wmsinveredeemdetailService.doUpdateforphone(personnel_shortcode, wDebtWorkFlowVO, wms_inve_redeem_id);
            map.put("ret_data", resultMap);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
            map.put("flag", false);
            map.put("ret_code", "001");
            map.put("message", e.getMessage());
        }
        // 记录日志
        if ("success".equals(result))
        {
            map.put("ret_code", "000");
            String msg = "业务管理--理财管理--手机赎回审核";
            SysTools.saveLog(request, msg);
        }
        map.put("ret_msg", ((Map) map.get("ret_data")).get("message"));
        // 返回执行结果
        return map;
    }

    /**
     * @Title: phoneGetPendingApprovalInfo
     * @Description: app首页查询代办事项的数据
     * @param personnelShortCode app端登录的用户的短工号
     * @param searchInfo 查询参数包括(客户姓名/客户经理姓名/客户经理短工号)
     * @param version 版本号
     * @return 
     * @author: DongHao
     * @time:2016年12月6日 下午3:41:36
     * history:
     * 1、2016年12月6日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/phoneGetPendingApprovalInfo.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> phoneGetPendingApprovalInfo(String personnel_shortCode, String searchInfo, String version)
    {
        if (personnel_shortCode != null)
        {
            return wmsinveredeemService.phoneGetPendingApprovalInfo(personnel_shortCode, searchInfo, version);
        }
        else
        {
            return null;
        }
    }

    /**
     * 
     * @Title: phoneGetRedeemByQueryInfo
     * @Description: 根据条件进行查询赎回单据信息
     * @param personnel_shortCode app端登录的用户短工号
     * @param searchInfo 查询参数包括(客户姓名/客户经理姓名/客户经理短工号)
     * @param query_type 查询的类型
     * @param page
     * @param page_size
     * @return 
     * @author: DongHao
     * @time:2016年12月6日 下午5:30:06
     * history:
     * 1、2016年12月6日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/phoneGetRedeemByQueryInfo.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> phoneGetRedeemByQueryInfo(String personnel_shortCode, String searchInfo, String query_type, String page, String page_size, String version)
    {

        int offset = 0;
        int pagesize = 0;
        if (Integer.parseInt(page) == 1)
        {
            offset = 0;
            pagesize = Integer.parseInt(page_size);
        }
        else
        {
            offset = Integer.parseInt(page_size) * Integer.parseInt(page) - Integer.parseInt(page_size);
            pagesize = Integer.parseInt(page_size);
        }

        return wmsinveredeemService.phoneGetRedeemByQueryInfo(personnel_shortCode, searchInfo, query_type, offset, pagesize, version);
    }

    /**
     * 
     * @Title: wmsPhoneQueryType
     * @Description: 根据人员的短工号进行获取对应的信息
     * @param personnel_shortCode
     * @return 
     * @author: DongHao
     * @time:2016年12月8日 下午3:00:04
     * history:
     * 1、2016年12月8日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/phoneQueryTypeWms.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> wmsPhoneQueryType(String personnel_shortCode)
    {

        return wmsinveredeemService.wmsPhoneQueryType(personnel_shortCode);
    }

    /**
     * 
     * @Title: phoneGetPendingApprovalInfoCountWMS
     * @Description: 获取代办任务数量
     * @param personnel_shortCode
     * @param searchInfo
     * @return 
     * @author: DongHao
     * @time:2016年12月14日 上午8:33:25
     * history:
     * 1、2016年12月14日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/phoneGetPendingApprovalInfoCountWMS.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> phoneGetPendingApprovalInfoCountWMS(String personnel_shortCode, String searchInfo)
    {
        if (personnel_shortCode != null)
        {
            return wmsinveredeemService.phoneGetPendingApprovalInfoCountWMS(personnel_shortCode, searchInfo);
        }
        else
        {
            return null;
        }
    }

    /**
     * 
     * @Title: saveWmsInveRedeemInfoPTP
     * @Description:  定时任务生成PTP赎回单据相关信息 
     * @param 
     * @return 
     * @author: zhangyunfei
     * @time:2017年6月23日 下午6:19:18
     * history:
     * 1、2017年6月23日 zhangyunfei 创建方法
     */
    @RequestMapping(value = "/inve/saveWmsInveRedeemInfoPTP.dos", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> saveWmsInveRedeemInfoPTP()
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try
        {
            wmsinveredeemService.saveWmsInveRedeemInfoPTP();
            resultMap.put("ret_code", "000");
            resultMap.put("ret_msg", "生成ptp赎回相关数据成功");
        }
        catch (Exception e)
        {
            resultMap.put("ret_code", "001");
            resultMap.put("ret_msg", "生成ptp赎回相关数据失败");
            e.printStackTrace();
        }
        return resultMap;
    }


    public static void main(String args[]) throws ParseException{
        Date now = new Date(System.currentTimeMillis());
        String end_of_date_org = "2016-06-12";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(df.parse(end_of_date_org).compareTo(df.parse(df.format(now))));
    }
}