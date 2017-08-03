package com.zx.emanage.reportmanage.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.zx.platform.syscontext.vo.GridParamBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveSalesmanCommissionAndCusIncomeVO
 * 模块名称：业务员佣金及客户收益统计实体类
 * @Description: 内容摘要：主要包含和前台页面交互业务员佣金及客户收益统计属性
 * @author jinzhm
 * @date 2016年12月9日
 * @version V1.0
 * history:
 * 1、2016年12月9日 jinzhm 创建文件
 */
public class WmsInveSalesmanCommissionAndCusIncomeVO extends GridParamBean
{

    /**
     * @Fields serialVersionUID : 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 统计月份
     */
    private String query_month;

    /**
     * 一级部门id
     */
    private Integer company_id;

    // /**
    // * 一级部门
    // */
    // private String company_name;

    /**
     * 二级部门id
     */
    private Integer dept_id;

    /**
    * 二级部门
    */
    private String dept_name;

    /**
     * 客户经理
     */
    private String salesman_id;

    // /**
    // * 人员状态
    // */
    // private String salesman_status;
    //
    // /**
    // * 部门经理
    // */
    // private String department_manager_id;
    //
    // /**
    // * 副总经理
    // */
    // private String vice_general_manager_id;
    //
    // /**
    // * 总经理
    // */
    // private String general_manager_id;
    //
    // /**
    // * 中分总
    // */
    // private String center_manager_id;

    // /**
    // * 理财单据编号
    // */
    // private String bill_code;

    /**
     * 客户姓名
     */
    private String cus_name;

    /**
     * 签单日期开始
     */
    private Date date_of_payment_begin;

    /**
     * 签单日期结束
     */
    private Date date_of_payment_end;

    /**
     * 签单金额开始
     */
    private BigDecimal product_account_begin;

    /**
     * 签单金额结束
     */
    private BigDecimal product_account_end;

    /**
     * 产品
     */
    private String category_name;

    // /**
    // * 产品骑术
    // */
    // private Integer product_deadline;
    //
    // /**
    // * 赎回日期开始
    // */
    // private Date redeem_date_begin;
    //
    // /**
    // * 赎回日期结束
    // */
    // private Date redeem_date_end;
    //
    // /**
    // * 赎回金额开始
    // */
    // private BigDecimal redeem_amount_begin;
    //
    // /**
    // * 赎回金额结束
    // */
    // private BigDecimal redeem_amount_end;
    //
    // /**
    // * 正常收益开始
    // */
    // private BigDecimal payable_basic_income_begin;
    //
    // /**
    // * 正常收益结束
    // */
    // private BigDecimal payable_basic_income_end;
    //
    // /**
    // * 公益收益开始
    // */
    // private BigDecimal extend_income_begin;
    //
    // /**
    // * 公益收益结束
    // */
    // private BigDecimal extend_income_end;
    //
    // /**
    // * 收益合计开始
    // */
    // private BigDecimal total_income_begin;
    //
    // /**
    // * 收益合计结束
    // */
    // private BigDecimal total_income_end;
    //
    // /**
    // * 老产品个人佣金开始
    // */
    // private BigDecimal old_comm_mon_begin;
    //
    // /**
    // * 老产品个人佣金结束
    // */
    // private BigDecimal old_comm_mon_end;
    //
    // /**
    // * 老产品税费开始
    // */
    // private BigDecimal old_comm_tax_begin;
    //
    // /**
    // * 老产品税费结束
    // */
    // private BigDecimal old_comm_tax_end;
    //
    // /**
    // * 新产品开单奖开始
    // */
    // private BigDecimal add_comm_mon_begin;
    //
    // /**
    // * 新产品开单奖结束
    // */
    // private BigDecimal add_comm_mon_end;
    //
    // /**
    // * 新产品税费开始
    // */
    // private BigDecimal comm_tax_begin;
    //
    // /**
    // * 新产品税费结束
    // */
    // private BigDecimal comm_tax_end;
    //
    // /**
    // * 新产品存量奖开始
    // */
    // private BigDecimal stock_comm_mon_begin;
    //
    // /**
    // * 新产品存量奖结束
    // */
    // private BigDecimal stock_comm_mon_end;
    //
    // /**
    // * 佣金合计开始
    // */
    // private BigDecimal total_comm_begin;
    //
    // /**
    // * 佣金合计结束
    // */
    // private BigDecimal total_comm_end;
    //
    // /**
    // * 老产品团队佣金开始
    // */
    // private BigDecimal old_team_comm_mon_begin;
    //
    // /**
    // * 老产品团队佣金结束
    // */
    // private BigDecimal old_team_comm_mon_end;
    //
    // /**
    // * 新产品管理提成开始
    // */
    // private BigDecimal team_comm_mon_begin;
    //
    // /**
    // * 新产品管理提成结束
    // */
    // private BigDecimal team_comm_mon_end;
    //
    // /**
    // * 管理提成合计开始
    // */
    // private BigDecimal total_team_comm_begin;
    //
    // /**
    // * 管理提成合计结束
    // */
    // private BigDecimal total_team_comm_end;

    /**
     * 菜单id
     */
    private String menu_id;

    /**
     * @return the salesman_id
     */
    public String getSalesman_id()
    {
        return salesman_id;
    }

    /**
     * @param salesman_id the salesman_id to set
     */
    public void setSalesman_id(String salesman_id)
    {
        this.salesman_id = salesman_id;
    }

    /**
     * @return the cus_name
     */
    public String getCus_name()
    {
        return cus_name;
    }

    /**
     * @param cus_name the cus_name to set
     */
    public void setCus_name(String cus_name)
    {
        this.cus_name = cus_name;
    }

    /**
     * @return the date_of_payment_begin
     */
    public Date getDate_of_payment_begin()
    {
        return date_of_payment_begin;
    }

    /**
     * @param date_of_payment_begin the date_of_payment_begin to set
     */
    public void setDate_of_payment_begin(Date date_of_payment_begin)
    {
        this.date_of_payment_begin = date_of_payment_begin;
    }

    /**
     * @return the date_of_payment_end
     */
    public Date getDate_of_payment_end()
    {
        return date_of_payment_end;
    }

    /**
     * @param date_of_payment_end the date_of_payment_end to set
     */
    public void setDate_of_payment_end(Date date_of_payment_end)
    {
        this.date_of_payment_end = date_of_payment_end;
    }

    /**
     * @return the product_account_begin
     */
    public BigDecimal getProduct_account_begin()
    {
        return product_account_begin;
    }

    /**
     * @param product_account_begin the product_account_begin to set
     */
    public void setProduct_account_begin(BigDecimal product_account_begin)
    {
        this.product_account_begin = product_account_begin;
    }

    /**
     * @return the product_account_end
     */
    public BigDecimal getProduct_account_end()
    {
        return product_account_end;
    }

    /**
     * @param product_account_end the product_account_end to set
     */
    public void setProduct_account_end(BigDecimal product_account_end)
    {
        this.product_account_end = product_account_end;
    }

    /**
     * @return the category_name
     */
    public String getCategory_name()
    {
        return category_name;
    }

    /**
     * @param category_name the category_name to set
     */
    public void setCategory_name(String category_name)
    {
        this.category_name = category_name;
    }

    /**
     * @return the query_month
     */
    public String getQuery_month()
    {
        return query_month;
    }

    /**
     * @param query_month the query_month to set
     */
    public void setQuery_month(String query_month)
    {
        this.query_month = query_month;
    }

    /**
     * @return the company_id
     */
    public Integer getCompany_id()
    {
        return company_id;
    }

    /**
     * @param company_id the company_id to set
     */
    public void setCompany_id(Integer company_id)
    {
        this.company_id = company_id;
    }

    /**
     * @return the dept_id
     */
    public Integer getDept_id()
    {
        return dept_id;
    }

    /**
     * @param dept_id the dept_id to set
     */
    public void setDept_id(Integer dept_id)
    {
        this.dept_id = dept_id;
    }

    /**
     * @return the dept_name
     */
    public String getDept_name()
    {
        return dept_name;
    }

    /**
     * @param dept_name the dept_name to set
     */
    public void setDept_name(String dept_name)
    {
        this.dept_name = dept_name;
    }

    /**
     * @return the menu_id
     */
    public String getMenu_id()
    {
        return menu_id;
    }

    /**
     * @param menu_id the menu_id to set
     */
    public void setMenu_id(String menu_id)
    {
        this.menu_id = menu_id;
    }
    
}
