package com.zx.emanage.reportmanage.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.zx.emanage.reportmanage.vo.WmsInveSalesmanCommissionAndCusIncomeVO;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: IWmsInveSalesmanCommissionAndCusIncomeService
 * 模块名称：业务员佣金及客户收益统计模块service接口
 * @Description: 内容摘要：主要包括根据查询条件查询统计数据和导出统计数据功能
 * @author jinzhm
 * @date 2016年12月9日
 * @version V1.0
 * history:
 * 1、2016年12月9日 jinzhm 创建文件
 */
public interface IWmsInveSalesmanCommissionAndCusIncomeService
{

    /**
     * @Title: getSalesmanCommissionAndCusIncomeWithPaging
     * @Description: 分页根据查询条件查询统计数据
     * @param scaciVo 业务员佣金及客户收益统计vo对象
     * @param user 用户信息
     * @return 根据查询条件查询出的业务员佣金及客户收益信息数据集合包含分页
     * @author: jinzhm
     * @time:2016年12月9日 上午8:30:57
     * history:
     * 1、2016年12月9日 jinzhm 创建方法
     */
    public Map<String, Object> getSalesmanCommissionAndCusIncomeWithPaging(WmsInveSalesmanCommissionAndCusIncomeVO scaciVo,
                                                                           UserBean user);

    /**
     * @Title: getSalesmanCommissionAndCusIncomeForExport
     * @Description: 根据查询条件查询统计数据（导出）
     * @param scaciVo 业务员佣金及客户收益统计vo对象
     * @param user 用户信息
     * @param response 响应信息
     * @author: jinzhm
     * @time:2016年12月9日 上午8:31:09
     * history:
     * 1、2016年12月9日 jinzhm 创建方法
     */
    public void getSalesmanCommissionAndCusIncomeForExport(WmsInveSalesmanCommissionAndCusIncomeVO scaciVo,
                                                                          UserBean user, HttpServletResponse response);
}
