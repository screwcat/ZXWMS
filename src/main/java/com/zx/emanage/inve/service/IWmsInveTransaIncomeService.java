package com.zx.emanage.inve.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.emanage.inve.vo.WmsInveTransaIncomeSearchBeanVO;
import com.zx.emanage.reportmanage.vo.WmsInveCustomerMonthlyIncomeBeanImportVo;
import com.zx.emanage.reportmanage.vo.WmsInveCustomerMonthlyIncomeBeanVo;
import com.zx.emanage.util.gen.entity.WmsInveTransaIncome;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveTransaIncomeService
{

    /**
     * @Title: computeCategoryIncome
     * @Description: 计算某个产品投资多少金额时的收益情况
     * @param categoryId 产品id
     * @param productAccount 投资金额（单位：万元）
     * @return 返回收益情况
     * @author: jinzhm
     * @time:2017年2月21日 下午12:00:34
     * history:
     * 1、2017年2月21日 jinzhm 创建方法
     */
    public Map<String, Object> computeCategoryIncome(int categoryId, int productAccount);

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsInveTransaIncomeSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsInveTransaIncomeSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInveTransaIncomeVO
     * @author auto_generator
     */
    public WmsInveTransaIncome getInfoByPK(Integer wms_inve_transa_income_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsInveTransaIncome bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsInveTransaIncome bean, UserBean user);

    /**
     * 根据客户收益信息表主键ID更新客户收益信息表中的调整金额等信息
     * @param wmsInveTransaIncome
     * @param user
     * @return
     * @author donghao
     * @date 2016年10月9日14:32:25
     */
	public Map<String, Object> updateInveTransaIncome(WmsInveTransaIncome wmsInveTransaIncome, UserBean user);

	/**
	 * 导出客户每月收益报表
	 * @param queryInfo
	 * @param response
	 * @author donghao
	 * @date 2016年10月10日17:31:21
	 */
	public void expertWmsInveTransaIncomeInfo(WmsInveCustomerMonthlyIncomeBeanVo queryInfo, HttpServletResponse response);

    /**
     * @Title: importWmsInveTransaIncomeInfo
     * @Description: 导入客户收益报表
     * @param queryInfo
     * @param response 
     * @author: jinzm
     * @time:2016年12月1日 下午3:25:27
     * history:
     * 1、2016年12月1日 Administrator 创建方法
    */
    public WmsInveCustomerMonthlyIncomeBeanImportVo importWmsInveTransaIncomeInfo(HttpServletRequest request, HttpServletResponse response, WmsInveCustomerMonthlyIncomeBeanImportVo queryInfo, UserBean user);

    /**
     * @Title: getCusIncomeWithCustomerSummary
     * @Description: 按客户维度统计某一个月某一个客户的所有客户收益信息
     * @param page 要查询页数
     * @param page_size 每页显示计数数
     * @param statics_month 查询月份
     * @param salesman_id 客户经理id
     * @param cus_name 客户姓名
     * @return 按客户维度汇总的某个客户经理的客户收益信息
     * @author: jinzhm
     * @time:2017年1月3日 下午3:08:11
     * history:
     * 1、2017年1月3日 jinzhm 创建方法
     */
    public Map<String, Object> getCusIncomeWithCustomerSummary(Integer page, Integer page_size, String statics_month,
                                                               String salesman_id, String cus_name);
    
    /**
     * @Title: getCusIncomeWithTransaSummary
     * @Description: 查询某一个客户在某一个月的单据收益信息集合
     * @param page 要查询页数
     * @param page_size 每页显示计数数
     * @param statics_month 查询月份
     * @param cus_id 客户id
     * @return 返回某一个客户在某一个月的单据收益信息集合
     * @author: jinzhm
     * @time:2017年1月3日 下午4:34:58
     * history:
     * 1、2017年1月3日 jinzhm 创建方法
     */
    public Map<String, Object> getCusIncomeWithTransaSummary(Integer page, Integer page_size, String statics_month,
                                                             String cus_id);

    /**
     * @Title: getCusIncomeWithSalesmanSummary
     * @Description: 查询某个月团队下所有客户经理维度汇总的收益信息集合
     * @param page 要查询页数
     * @param page_size 每页显示计数数
     * @param statics_month 查询月份
     * @param team_id 团队id（多个用英文逗号隔开）
     * @param salesman_name 客户经理姓名或短工号
     * @return 返回某个月团队下所有客户经理维度汇总的收益信息集合
     * @author: jinzhm
     * @time:2017年1月3日 下午5:43:50
     * history:
     * 1、2017年1月3日 jinzhm 创建方法
     */
    public Map<String, Object> getCusIncomeWithSalesmanSummary(Integer page, Integer page_size, String statics_month,
                                                               String team_ids, String salesman_name);

    /**
     * @Title: computeCategoryIncome
     * @Description: 根据合同生效日期进行收益计算
     * @param wms_inve_pruduct_category_id
     * @param product_account
     * @param date
     * @return 
     * @author: zhangyunfei
     * @time:2017年3月27日 下午5:15:39
     * history:
     * 1、2017年3月27日 Administrator 创建方法
    */
    public Map<String, Object> computeCategoryIncome(int wms_inve_pruduct_category_id, int product_account, Date date);

    /**
     * @Title: getIncomeListGtRedeemDate
     * @Description: 筛选集合中日期大于赎回日期的数据
     * @param incomeList
     * @param redeem_date 
     * @author: zhangyunfei
     * @time:2017年3月27日 下午6:17:22
     * history:
     * 1、2017年3月27日 Administrator 创建方法
    */
    public List<Map<String, Object>> getIncomeListGtRedeemDate(List<Map<String, Object>> incomeList, Date redeem_date);

    /**
     * @Title: calTotal_Income
     * @Description: 计算客户收益总和
     * @param cIMap 
     * @author: zhangyunfei
     * @time:2017年3月28日 上午11:49:09
     * history:
     * 1、2017年3月28日 Administrator 创建方法
    */
    public void calTotal_Income(Map<String, Object> cIMap);
}