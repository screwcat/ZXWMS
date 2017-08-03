package com.zx.emanage.reportmanage.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.reportmanage.service.IWmsInveSalesmanCommissionAndCusIncomeService;
import com.zx.emanage.reportmanage.vo.WmsInveSalesmanCommissionAndCusIncomeVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveSalesmanCommissionAndCusIncomeController
 * 模块名称：业务员佣金及客户收益统计报表controller
 * @Description: 内容摘要：主要查询业务员的客户收益情况及业务员的佣金及团队佣金情况
 *  1. 查询列表
 *  2. 导出查询结果
 * @author jinzhm
 * @date 2016年12月8日
 * @version V1.0
 * history:
 * 1、2016年12月8日 jinzhm 创建文件
 */
@Controller
public class WmsInveSalesmanCommissionAndCusIncomeController
{

    @Autowired
    private IWmsInveSalesmanCommissionAndCusIncomeService wmsInveSalesmanCommissionAndCusIncomeService;

    /**
     * @Title: getSalesmanCommissionAndCusIncomeWithPaging
     * @Description: 根据页面查询条件查询出业务员佣金及客户收益统计数据（进行分页）
     * @param scaciVo 业务员佣金及客户收益统计vo对象
     * @param request 请求对象
     * @return 统计数据
     * @author: jinzhm
     * @time:2016年12月8日 下午3:47:28
     * history:
     * 1、2016年12月8日 jinzhm 创建方法
     */
    @RequestMapping(value = "/reportmanage/getSalesmanCommissionAndCusIncomeWithPaging.do", method = {
                                                                                                      RequestMethod.GET,
                                                                                                      RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getSalesmanCommissionAndCusIncomeWithPaging(WmsInveSalesmanCommissionAndCusIncomeVO scaciVo,
                                                                           HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmsInveSalesmanCommissionAndCusIncomeService.getSalesmanCommissionAndCusIncomeWithPaging(scaciVo, user);
    }

    /**
     * @Title: getSalesmanCommissionAndCusIncomeForExport
     * @Description: 根据页面查询条件导出业务员佣金及客户收益统计数据
     * @param scaciVo 业务员佣金及客户收益统计vo对象
     * @param request 请求对象
     * @return 统计数据
     * @author: jinzhm
     * @time:2016年12月9日 上午10:56:01
     * history:
     * 1、2016年12月9日 jinzhm 创建方法
     */
    @RequestMapping(value = "/reportmanage/getSalesmanCommissionAndCusIncomeForExport.do", method = {
                                                                                                     RequestMethod.GET,
                                                                                                     RequestMethod.POST })
    @ResponseBody
    public void getSalesmanCommissionAndCusIncomeForExport(WmsInveSalesmanCommissionAndCusIncomeVO scaciVo,
                                                                          HttpServletRequest request,
                                                                          HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        wmsInveSalesmanCommissionAndCusIncomeService.getSalesmanCommissionAndCusIncomeForExport(scaciVo, user,
                                                                                                       response);
    }
}
