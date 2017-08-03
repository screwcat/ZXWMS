package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInveTransaIncome;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveTransaIncomeDao extends BaseDao<WmsInveTransaIncome> {
	
	
	/**
	 * 删除某个单据的某个时间的公益收益
	 * 
	 * @param paramMap
	 * @return
	 *
	 * @author 金志明
	 * @date 2017年10月11日 下午2:28:16
	 */
	public void deleteTransaIncome(Map<String, Object> paramMap);
	
	/**
	 * 删除传入理财单据主键集合的所有收益信息
	 * 
	 * @param list
	 *
	 * @author 金志明
	 * @date 2016年10月12日 下午4:36:42
	 */
	public void removeWmsInveTransaIncomes(List<String> list);
	
	/**
	 * 删除所有收益中单据的收益信息
	 * 
	 *
	 * @author 金志明
	 * @date 2016年10月12日 下午4:39:50
	 */
	public void removeWmsInveTransaIncomesInIncome();
	
    /**
    * @Title: removeTransaIncomes
    * @Description: 删除制定单据的收益信息
    * @param paramMap 
    * @author: jinzhm
    * @time:2016年11月17日 上午9:26:02
    * history:
    * 1、2016年11月17日 jinzhm 创建方法
    */
    public void removeTransaIncomes(Map<String, Object> paramMap);

    /**
     * @Title: addBatchWmsInveTransaIncomes 
     * @Description: 批量添加收益信息
     * @param wmsInveTransaIncomes
     * @return int 
     * @throws
     * @author lvtu 
     * @date 2015年8月28日 上午9:58:37
     */
	public int addBatchWmsInveTransaIncomes(List<WmsInveTransaIncome> wmsInveTransaIncomes);
	
	/**
	 * @Title: getWmsInveTransaIncome 
	 * @Description: 查询收益信息
	 * @param paramMap
	 * @return WmsInveTransaIncome 
	 * @throws
	 * @author lvtu 
	 * @date 2015年9月1日 上午9:39:35
	 */
	public WmsInveTransaIncome getWmsInveTransaIncome(Map<String, Object> paramMap);
	
	/**
	 * @Title: delWmsInveTransaIncomes 
	 * @Description: 根据上单信息表主键删除收益信息
	 * @param wms_inve_transa_id
	 * @return int 
	 * @throws
	 * @author lvtu 
	 * @date 2015年9月10日 下午7:05:20
	 */
	public int delWmsInveTransaIncomes(Integer wms_inve_transa_id);
	
	/**
	 * 根据理财上单主键获得完全赎回的赎回日期
	 * @param wms_inve_transa_id
	 * @return 返回只有redeem_date的map
	 */
	public Map<String, Object> getWmsTransaFullRedeemInfo(Integer wms_inve_transa_id);

	/**
	 * 根据对象中的wms_inve_transa_id和return_date查询得到客户收益信息
	 * @param wmsInveTransaIncome
	 * @return
	 * @author donghao
	 * @date 2016年10月10日13:59:07
	 */
	public List<WmsInveTransaIncome> getWmsInveTransaIncomeByWmsInveTransaIdAndReturnDate(WmsInveTransaIncome wmsInveTransaIncome);

	/**
	 * 根据条件获取打款表数据
	 * @param paramMap
	 * @return
	 * @author donghao
	 * @date 2016年10月11日10:43:18
	 */
	public List<Map<String, Object>> getPlayMoneyList(Map<String, Object> paramMap);

	/**
	 * 根据传入的参数查询收益总表信息
	 * 
	 * @param paramMap
	 * @return
	 * @author donghao
	 * @date 2016年10月12日09:21:10
	 */
	public List<Map<String, Object>> getIncomeTotalList(Map<String, Object> paramMap);

    /**
     * @Title: updateIncomePayStatusAndActReturnDateByBillCode
     * @Description: 根据理财单据编号修改支付状态及实际支付时间
     * @param billCodes 理财单据编号，多个时用逗号隔开
     * @author: jinzhm
     * @time:2016年12月7日 下午4:39:35
     * history:
     * 1、2016年12月7日 jinzhm 创建方法
     */
    public void updateIncomePayStatusAndActReturnDateByBillCode(Map<String, Object> paramMap);

    /**
     * @Title: getMonthIncomeByBillCodeAndReturnDate
     * @Description: 根据理财单据编号和收益月份查找正常收益和公益收益不是未支付状态的数据
     * @param paramMap 包含理财单据编号（bill_code），收益时间（return_date）
     * @return 返回查询出的正常收益或公益收益支付状态不是未支付的记录
     * @author: jinzhm
     * @time:2016年12月7日 上午10:52:45
     * history:
     * 1、2016年12月7日 jinzhm 创建方法
     */
    public List<Map<String, Object>> getMonthIncomeByBillCodeAndReturnDate(Map<String, Object> paramMap);

    /**
     * @Title: getCusIncomeWithCustomerSummary
     * @Description: 根据salesman_id,statics_month分页查询按客户维度汇总的客户收益信息(查历史)
     * @param paramMap 查询条件
     * @return 返回按客户维度汇总的客户收益信息
     * @author: jinzhm
     * @time:2017年1月3日 下午3:11:06
     * history:
     * 1、2017年1月3日 jinzhm 创建方法
     */
    public List<Map<String, Object>> getCusIncomeWithCustomerSummary(Map<String, Object> paramMap);
    
    /**
     * @Title: getCusIncomeWithCustomerSummaryRealTime
     * @Description: 根据salesman_id,statics_month分页查询按客户维度汇总的客户收益信息(查当前月份)
     * @param paramMap 查询条件
     * @return 返回按客户维度汇总的客户收益信息
     * @author: jinzhm
     * @time:2017年1月5日 下午12:51:18
     * history:
     * 1、2017年1月5日 jinzhm 创建方法
     */
    public List<Map<String, Object>> getCusIncomeWithCustomerSummaryRealTime(Map<String, Object> paramMap);

    /**
     * @Title: getCusIncomeWithTransaSummary
     * @Description: 根据客户id和查询月份查询某一个客户在某一个月的单据收益信息集合(查历史)
     * @param paramMap 查询条件
     * @return 返回某一个客户在某一个月的单据收益信息集合
     * @author: jinzhm
     * @time:2017年1月3日 下午4:38:33
     * history:
     * 1、2017年1月3日 jinzhm 创建方法
     */
    public List<Map<String, Object>> getCusIncomeWithTransaSummary(Map<String, Object> paramMap);

    /**
     * @Title: getCusIncomeWithTransaSummaryRealTime
     * @Description: 根据客户id和查询月份查询某一个客户在某一个月的单据收益信息集合(查当前月份)
     * @param paramMap 查询条件
     * @return 返回某一个客户在某一个月的单据收益信息集合
     * @author: jinzhm
     * @time:2017年1月5日 下午1:04:37
     * history:
     * 1、2017年1月5日 jinzhm 创建方法
     */
    public List<Map<String, Object>> getCusIncomeWithTransaSummaryRealTime(Map<String, Object> paramMap);

    /**
     * @Title: getCusIncomeWithSalesmanSummary
     * @Description: 根据查询月份和团队id查询某个月，某些团队下的单据收益信息集合（按客户经理汇总）
     * @param paramMap 查询条件
     * @return 返回单据收益信息集合（按客户经理汇总）
     * @author: jinzhm
     * @time:2017年1月3日 下午5:49:22
     * history:
     * 1、2017年1月3日 jinzhm 创建方法
     */
    public List<Map<String, Object>> getCusIncomeWithSalesmanSummary(Map<String, Object> paramMap);

    /**
     * @Title: validateTransaIncomeStatusAndIncome
     * @Description: 导入时验证要导入数据的支付状态和收益金额的方法
     * @param paramMap 查询条件map
     * @return 返回数据说明数据有问题，返回null说明没有问题
     * @author: jinzhm
     * @time:2017年1月6日 下午12:59:34
     * history:
     * 1、2017年1月6日 jinzhm 创建方法
     */
    public Map<String, Object> validateTransaIncomeStatusAndIncome(Map<String, Object> paramMap);

    /**
     * @Title: insertShareHolderTransaIncome
     * @Description: 保存股东单据不打收益信息
     * @param dataMap 股东单据不打收益数据map对象
     * @author: jinzhm
     * @time:2017年6月7日 上午9:57:50
     * history:
     * 1、2017年6月7日 jinzhm 创建方法
     */
    public void insertShareHolderTransaIncome(Map<String, Object> dataMap);
}