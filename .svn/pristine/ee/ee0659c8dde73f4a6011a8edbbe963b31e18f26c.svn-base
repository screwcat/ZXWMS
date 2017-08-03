package com.zx.emanage.reportmanage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jodd.typeconverter.Convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zx.emanage.reportmanage.persist.WmsInveSalesmanCommissionAndCusIncomeDao;
import com.zx.emanage.reportmanage.service.IWmsInveSalesmanCommissionAndCusIncomeService;
import com.zx.emanage.reportmanage.vo.WmsInveSalesmanCommissionAndCusIncomeVO;
import com.zx.emanage.sysmanage.persist.SysDeptDao;
import com.zx.emanage.sysmanage.persist.SysRoleDao;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.ExpertUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveSalesmanCommissionAndCusIncomeServiceImpl
 * 模块名称：业务员佣金及客户收益统计service实现类
 * @Description: 内容摘要：主要包括根据查询条件查询统计数据和导出统计数据功能
 * @author jinzhm
 * @date 2016年12月9日
 * @version V1.0
 * history:
 * 1、2016年12月9日 jinzhm 创建文件
 */
@Service("wmsInveSalesmanCommissionAndCusIncomeService")
public class WmsInveSalesmanCommissionAndCusIncomeServiceImpl implements IWmsInveSalesmanCommissionAndCusIncomeService
{

    @Autowired
    private WmsInveSalesmanCommissionAndCusIncomeDao wmsInveSalesmanCommissionAndCusIncomeDao;

    @Autowired
    private SysRoleDao sysroleDao_m;

    @Autowired
    private SysDeptDao sysdeptDao;

    /**
     * @Title: getSalesmanCommissionAndCusIncomeWithPaging
     * @Description: 根据查询条件查询业务员佣金及客户收益信息（进行分页）
     * @param scaciVo 业务员佣金及客户收益统计vo对象
     * @param user 用户信息
     * @return 根据查询条件查询出的业务员佣金及客户收益信息数据集合包含分页
     * @author: jinzhm
     * @time:2016年12月9日 上午11:01:57
     * @see com.zx.emanage.reportmanage.service.IWmsInveSalesmanCommissionAndCusIncomeService#getSalesmanCommissionAndCusIncomeWithPaging(com.zx.emanage.reportmanage.vo.WmsInveSalesmanCommissionAndCusIncomeVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2016年12月9日 jinzhm 创建方法
    */
    @Override
    public Map<String, Object> getSalesmanCommissionAndCusIncomeWithPaging(WmsInveSalesmanCommissionAndCusIncomeVO scaciVo,
                                                                           UserBean user)
    {
        // 将页面查询条件转换成map
        Map<String, Object> paramMap = getSalesmanCommissionAndCusIncomeQueryParamMap(scaciVo, user, true);
        String queryMonth = String.valueOf(paramMap.get("query_month"));
        // 查询结果对象
        List<Map<String, Object>> salesmanCommissionAndCusIncomeMapList = new ArrayList<Map<String, Object>>();
        GridDataBean<Map<String, Object>> bean = null;

        // 查询是当月
        if (queryMonth.equals((DateUtil.date2String(DateUtil.getDate10(new Date()), "yyyy-MM"))))
        {
            // 查询统计数据
            salesmanCommissionAndCusIncomeMapList = wmsInveSalesmanCommissionAndCusIncomeDao.queryWmsInveSalesmanCommissionAndCusIncome(paramMap);

            // 返回数据封装格式化
            bean = new GridDataBean<Map<String, Object>>(
                                                         scaciVo.getPage(),
                                                         wmsInveSalesmanCommissionAndCusIncomeDao.queryWmsInveSalesmanCommissionAndCusIncomeCount(paramMap),
                                                         salesmanCommissionAndCusIncomeMapList);
        }
        else
        {
            salesmanCommissionAndCusIncomeMapList = wmsInveSalesmanCommissionAndCusIncomeDao.queryWmsInveSalesmanCommissionAndCusIncomeOtherMonth(paramMap);

            // 返回数据封装格式化
            bean = new GridDataBean<Map<String, Object>>(
                                                         scaciVo.getPage(),
                                                         wmsInveSalesmanCommissionAndCusIncomeDao.queryWmsInveSalesmanCommissionAndCusIncomeOtherMonthCount(paramMap),
                                                         salesmanCommissionAndCusIncomeMapList);
        }

        return bean.getGridData();
    }

    /**
     * @Title: getSalesmanCommissionAndCusIncomeForExport
     * @Description: 根据查询条件查询业务员佣金及客户收益信息（导出）
     * @param scaciVo 业务员佣金及客户收益统计vo对象
     * @param user 用户信息
     * @return 根据查询条件查询出的业务员佣金及客户收益信息数据集合不包含分页
     * @author: jinzhm
     * @time:2016年12月9日 上午11:01:57
     * @see com.zx.emanage.reportmanage.service.IWmsInveSalesmanCommissionAndCusIncomeService#getSalesmanCommissionAndCusIncomeForExport(com.zx.emanage.reportmanage.vo.WmsInveSalesmanCommissionAndCusIncomeVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2016年12月9日 jinzhm 创建方法
    */
    @Override
    public void getSalesmanCommissionAndCusIncomeForExport(WmsInveSalesmanCommissionAndCusIncomeVO scaciVo,
                                                           UserBean user, HttpServletResponse response)
    {
        String fileTitleName = "业务员佣金&客户收益统计表.xls";

        // 将页面查询条件转换成map
        Map<String, Object> paramMap = getSalesmanCommissionAndCusIncomeQueryParamMap(scaciVo, user, false);
        String queryMonth = String.valueOf(paramMap.get("query_month"));
        // 查询结果对象
        List<Map<String, Object>> salesmanCommissionAndCusIncomeMapList = new ArrayList<Map<String, Object>>();


        // 查询是当月
        if (queryMonth.equals((DateUtil.date2String(DateUtil.getDate10(new Date()), "yyyy-MM"))))
        {
            // 查询统计数据
            salesmanCommissionAndCusIncomeMapList = wmsInveSalesmanCommissionAndCusIncomeDao.queryWmsInveSalesmanCommissionAndCusIncome(paramMap);
        }
        else
        {
            salesmanCommissionAndCusIncomeMapList = wmsInveSalesmanCommissionAndCusIncomeDao.queryWmsInveSalesmanCommissionAndCusIncomeOtherMonth(paramMap);
        }

        String queryMonthUpper = DateUtil.date2String(Convert.toDate(queryMonth), "yyyy年MM月");
        fileTitleName = queryMonthUpper + fileTitleName;

        // 设置excel相关信息
        Map<String, Object> sheetDataMap = new HashMap<>();
        sheetDataMap.put("佣金收益统计表", salesmanCommissionAndCusIncomeMapList);

        // sheet内容中标题
        Map<String, Object> sheetTitleMap = new HashMap<>();
        String sheetContentTitle = "数据有效性：截止" + DateUtil.date2String(Convert.toDate(queryMonth), "yyyy-MM")
                                   + "财富管理平台数据";
        sheetTitleMap.put("佣金收益统计表", sheetContentTitle);
        sheetDataMap.put("sheetContentTitle", sheetTitleMap);
        // 导出
        try
        {
            ExpertUtil eu = new ExpertUtil();
            eu.expertExcel("业务员佣金&客户收益统计报表模板.xls", sheetDataMap, fileTitleName, "sheetContentTitle", 3, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @Title: getSalesmanCommissionAndCusIncomeQueryParamMap
     * @Description: 将前台查询条件参数转换成map形式
     * @param scaciVo 前台查询条件
     * @param user 登录用户信息
     * @param paginationFlag 是否分页标记
     * @return 返回map形式的查询条件
     * @author: jinzhm
     * @time:2016年12月9日 上午11:11:21
     * history:
     * 1、2016年12月9日 jinzhm 创建方法
     */
    private Map<String, Object> getSalesmanCommissionAndCusIncomeQueryParamMap(WmsInveSalesmanCommissionAndCusIncomeVO scaciVo,
                                                                               UserBean user,
                                                                               boolean paginationFlag)
    {
        // 将佣金及收益对象中的所有属性，如果有值的话转换成map形式
        Map<String, Object> paramMap = new HashMap<String, Object>();

        // 如果需要分页，设置分页信息
        if (paginationFlag)
        {
            paramMap.put("pagesize", scaciVo.getPagesize());
            paramMap.put("offset", scaciVo.getOffset());
        }

        // 统计月份
        if (StringUtil.isEmpty(scaciVo.getQuery_month()))
        {
            paramMap.put("query_month", DateUtil.date2String(DateUtil.getDate10(new Date()), "yyyy-MM"));
        }
        else
        {
            paramMap.put("query_month", scaciVo.getQuery_month());
        }

        // if (scaciVo.getCompany_id() != null)
        // {
        // paramMap.put("company_id", scaciVo.getCompany_id());
        // }
        // if (scaciVo.getDept_id() != null)
        // {
        // paramMap.put("dept_id", scaciVo.getDept_id());
        // }
        // if (StringUtil.isNotEmpty(scaciVo.getDept_name()))
        // {
        // paramMap.put("dept_name", scaciVo.getDept_name());
        // }
        // 部门名称需要支持逗号隔开查询多个部门
        if (StringUtil.isNotEmpty(scaciVo.getDept_name()))
        {
            // 获得页面输入部门名称。因有可能用中文逗号隔开，需要转换成英文逗号
            // 可能连续存在两个逗号,需要都替换成一个逗号
            String deptName = scaciVo.getDept_name().replaceAll("，", ",").replaceAll("[',']+", ",");
            List<String> deptNameList = CollectionUtils.arrayToList(deptName.split(","));
            paramMap.put("deptNameList", deptNameList);
        }

        if (StringUtil.isNotEmpty(scaciVo.getSalesman_id()))
        {
            paramMap.put("salesman_name", scaciVo.getSalesman_id());
        }
        // if (StringUtil.isNotEmpty(scaciVo.getSalesman_status()))
        // {
        // paramMap.put("salesman_status", scaciVo.getSalesman_status());
        // }
        // if (StringUtil.isNotEmpty(scaciVo.getDepartment_manager_id()))
        // {
        // paramMap.put("department_manager_id",
        // scaciVo.getDepartment_manager_id());
        // }
        // if (StringUtil.isNotEmpty(scaciVo.getVice_general_manager_id()))
        // {
        // paramMap.put("vice_general_manager_id",
        // scaciVo.getVice_general_manager_id());
        // }
        // if (StringUtil.isNotEmpty(scaciVo.getCenter_manager_id()))
        // {
        // paramMap.put("center_manager_id", scaciVo.getCenter_manager_id());
        // }
        // if (StringUtil.isNotEmpty(scaciVo.getBill_code()))
        // {
        // paramMap.put("bill_code", scaciVo.getBill_code());
        // }
        if (StringUtil.isNotEmpty(scaciVo.getCus_name()))
        {
            paramMap.put("cus_name", scaciVo.getCus_name());
        }
        if (scaciVo.getDate_of_payment_begin() != null)
        {
            paramMap.put("date_of_payment_begin", scaciVo.getDate_of_payment_begin());
        }
        if (scaciVo.getDate_of_payment_end() != null)
        {
            paramMap.put("date_of_payment_end", scaciVo.getDate_of_payment_end());
        }
        if (scaciVo.getProduct_account_begin() != null)
        {
            paramMap.put("product_account_begin", scaciVo.getProduct_account_begin());
        }
        if (scaciVo.getProduct_account_end() != null)
        {
            paramMap.put("product_account_end", scaciVo.getProduct_account_end());
        }
        if (StringUtil.isNotEmpty(scaciVo.getCategory_name()) && !"-1".equals(scaciVo.getCategory_name()))
        {
            paramMap.put("category_name", scaciVo.getCategory_name());
        }
        // if (scaciVo.getProduct_deadline() != null)
        // {
        // paramMap.put("product_deadline", scaciVo.getProduct_deadline());
        // }
        // if (scaciVo.getRedeem_date_begin() != null)
        // {
        // paramMap.put("redeem_date_begin", scaciVo.getRedeem_date_begin());
        // }
        // if (scaciVo.getRedeem_date_end() != null)
        // {
        // paramMap.put("redeem_date_end", scaciVo.getRedeem_date_end());
        // }
        // if (scaciVo.getRedeem_amount_begin() != null)
        // {
        // paramMap.put("redeem_amount_begin",
        // scaciVo.getRedeem_amount_begin());
        // }
        // if (scaciVo.getRedeem_amount_end() != null)
        // {
        // paramMap.put("redeem_amount_end", scaciVo.getRedeem_amount_end());
        // }
        // if (scaciVo.getPayable_basic_income_begin() != null)
        // {
        // paramMap.put("payable_basic_income_begin",
        // scaciVo.getPayable_basic_income_begin());
        // }
        // if (scaciVo.getPayable_basic_income_end() != null)
        // {
        // paramMap.put("payable_basic_income_end",
        // scaciVo.getPayable_basic_income_end());
        // }
        // if (scaciVo.getExtend_income_begin() != null)
        // {
        // paramMap.put("extend_income_begin",
        // scaciVo.getExtend_income_begin());
        // }
        // if (scaciVo.getExtend_income_end() != null)
        // {
        // paramMap.put("extend_income_end", scaciVo.getExtend_income_end());
        // }
        // if (scaciVo.getTotal_income_begin() != null)
        // {
        // paramMap.put("total_income_begin", scaciVo.getTotal_income_begin());
        // }
        // if (scaciVo.getTotal_income_end() != null)
        // {
        // paramMap.put("total_income_end", scaciVo.getTotal_income_end());
        // }
        // if (scaciVo.getOld_comm_mon_begin() != null)
        // {
        // paramMap.put("old_comm_mon_begin", scaciVo.getOld_comm_mon_begin());
        // }
        // if (scaciVo.getOld_comm_mon_end() != null)
        // {
        // paramMap.put("old_comm_mon_end", scaciVo.getOld_comm_mon_end());
        // }
        // if (scaciVo.getOld_comm_tax_begin() != null)
        // {
        // paramMap.put("old_comm_tax_begin", scaciVo.getOld_comm_tax_begin());
        // }
        // if (scaciVo.getOld_comm_tax_end() != null)
        // {
        // paramMap.put("old_comm_tax_end", scaciVo.getOld_comm_tax_end());
        // }
        // if (scaciVo.getAdd_comm_mon_begin() != null)
        // {
        // paramMap.put("add_comm_mon_begin", scaciVo.getAdd_comm_mon_begin());
        // }
        // if (scaciVo.getAdd_comm_mon_end() != null)
        // {
        // paramMap.put("add_comm_mon_end", scaciVo.getAdd_comm_mon_end());
        // }
        // if (scaciVo.getComm_tax_begin() != null)
        // {
        // paramMap.put("comm_tax_begin", scaciVo.getComm_tax_begin());
        // }
        // if (scaciVo.getComm_tax_end() != null)
        // {
        // paramMap.put("comm_tax_end", scaciVo.getComm_tax_end());
        // }
        // if (scaciVo.getStock_comm_mon_begin() != null)
        // {
        // paramMap.put("stock_comm_mon_begin",
        // scaciVo.getStock_comm_mon_begin());
        // }
        // if (scaciVo.getStock_comm_mon_end() != null)
        // {
        // paramMap.put("stock_comm_mon_end", scaciVo.getStock_comm_mon_end());
        // }
        // if (scaciVo.getTotal_comm_begin() != null)
        // {
        // paramMap.put("total_comm_begin", scaciVo.getTotal_comm_begin());
        // }
        // if (scaciVo.getTotal_comm_end() != null)
        // {
        // paramMap.put("total_comm_end", scaciVo.getTotal_comm_end());
        // }
        // if (scaciVo.getOld_team_comm_mon_begin() != null)
        // {
        // paramMap.put("old_team_comm_mon_begin",
        // scaciVo.getOld_team_comm_mon_begin());
        // }
        // if (scaciVo.getOld_team_comm_mon_end() != null)
        // {
        // paramMap.put("old_team_comm_mon_end",
        // scaciVo.getOld_team_comm_mon_end());
        // }
        // if (scaciVo.getTeam_comm_mon_begin() != null)
        // {
        // paramMap.put("team_comm_mon_begin",
        // scaciVo.getTeam_comm_mon_begin());
        // }
        // if (scaciVo.getOld_team_comm_mon_begin() != null)
        // {
        // paramMap.put("old_team_comm_mon_begin",
        // scaciVo.getOld_team_comm_mon_begin());
        // }
        // if (scaciVo.getTeam_comm_mon_end() != null)
        // {
        // paramMap.put("team_comm_mon_end", scaciVo.getTeam_comm_mon_end());
        // }
        // if (scaciVo.getTotal_team_comm_begin() != null)
        // {
        // paramMap.put("total_team_comm_begin",
        // scaciVo.getTotal_team_comm_begin());
        // }
        // if (scaciVo.getTotal_team_comm_end() != null)
        // {
        // paramMap.put("total_team_comm_end",
        // scaciVo.getTotal_team_comm_end());
        // }

        // 获取该用户的角色信息
        List<String> roleList = sysroleDao_m.getUserRoleNameList(user.getUserId());
        for (String role : roleList)
        {
            // 判断该用户的角色
            if (role.equals("理财业务接待专员"))
            {
                // 只能看见自己创建的单据
                paramMap.put("create_user_id", user.getUserId());
            }
            if (role.equals("理财业务接待部主管"))
            {
                // 根据部门ID获得子部门ID
                List<Integer> deptIds = sysdeptDao.getDeptId(user.getDeptId());
                deptIds.add(user.getDeptId());
                // 可以看见子部门的所有业务员单据
                paramMap.put("deptIds", deptIds);
            }
            if (role.equals("理财业务专员"))
            {
                // 可以看见自己是业务员的单据
                paramMap.put("salesman_id", user.getUserId());
            }
            if (role.equals("理财业务部主管"))
            {
                // 可以看见自己部门所有业务员的单据
                // 根据部门ID获得子部门ID
                List<Integer> deptIds = sysdeptDao.getDeptId(user.getDeptId());
                deptIds.add(user.getDeptId());
                // 可以看见子部门的所有业务员单据
                paramMap.put("deptIds", deptIds);
            }
            if (role.equals("理财业务部总监"))
            {
                paramMap.put("super_user", 1);
                // // 根据部门ID获得子部门ID
                // // List<Integer> deptIds =
                // sysdeptDao.getDeptId(user.getDeptId());
                // // deptIds.add(user.getDeptId());
                // // // 可以看见子部门的所有业务员单据
                // // paramMap.put("deptIds", deptIds);
                // //根据数据权限去获取部门
                // paramMap.put("salesman_id", user.getUserId());
                // paramMap.put("deptIds_menu", queryInfo.getMenu_id());
                // paramMap.put("deptIds_user_id", user.getUserId());
            }
            if (role.equals("理财业务部副总"))
            {
                // 根据数据权限去获取部门
                paramMap.put("salesman_id", user.getUserId());
                paramMap.put("deptIds_menu", scaciVo.getMenu_id());
                paramMap.put("deptIds_user_id", user.getUserId());
            }
            if (role.equals("理财财务专员"))
            {
                // 可以看见所有数据
                paramMap.put("financial_services", 1);
            }
            if (role.equals("超级用户"))
            {
                // 可以看见所有数据
                paramMap.put("super_user", 1);
            }
            if (role.equals("财务柜员"))
            {
                // 可以看见所有数据
                paramMap.put("financial_services", 1);
            }
            if (role.equals("理财财务主管"))
            {
                // 可以看见所有数据
                paramMap.put("financial_services", 1);
            }
        }
        paramMap.put("user_id", user.getUserId());

        return paramMap;
    }

}
