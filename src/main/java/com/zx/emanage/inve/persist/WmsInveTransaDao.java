package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zx.emanage.util.gen.entity.WmsInveSaleLimit;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.emanage.util.gen.entity.WmsInveTransaSalesmanHis;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveTransaDao extends BaseDao<WmsInveTransa>
{

    /**
     * Description :理财 债权转让及受让协议 后台查询数据 查询了很多表
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author baisong
     */
    public Map<String, Object> getMapForLc(Integer wms_inve_transa_id);

    public List<Map<String,Object>> searchForMatch(Map<String, Object> paramMap);

    int findCountForMatch(Map<String, Object> paramMaps);

    public List<Map<String, Object>> searchForChoose(Map<String, Object> paramMaps);

    int findCountForChoose(Map<String, Object> paramMaps);

    /**
     * search:根据传人的条件动态生成sql语句，如需分页需要在sql中加入offset、pagesize变量 <br/>
     * 
     * @author ry
     * @param parameters
     * @return
     */
    List<Map<String, Object>> searchForJEGL(Map<String, Object> parameters);

    /**
     * findCount:根据查询条件返回记录总条数. <br/>
     * 
     * @author ry
     * @param paramMaps
     * @return
     */
    int findCountForJEGL(Map<String, Object> paramMaps);

    /**
     * 理财查询分页列表
     * 
     * @param resMap
     * @return
     */
    List<Map<String, Object>> searchFinancial(Map<String, Object> resMap);

    /**
     * 根据查询条件返回理财查询记录总条数
     * 
     * @param paramMaps
     * @return
     */
    int findCountFinancial(Map<String, Object> paramMaps);

    /**
     * update:金额支付 更新. <br/>
     * 
     * @author ry
     * @param t
     */
    int updateInve_transaForJEZF(WmsInveTransa wmsInveTransa);

    /**
     * 赎回申请分页列表
     * 
     * @param resMap
     * @return
     */
    List<Map<String, Object>> redeemApplyList(Map<String, Object> resMap);

    /**
     * 根据查询条件返回赎回申请记录总条数
     * 
     * @param paramMaps
     * @return
     */
    int findCountRedeem(Map<String, Object> paramMaps);

    /**
     * 赎回修改
     * 
     * @author zhubo
     * @param t
     */
    int updateForRedeem(WmsInveTransa wmsInveTransa);
    
    /**
     * 获取协议续期页面列表数据 
     * 
     * @param queryInfo
     * @return Map
     * @author baisong
     */
    public List<Map<String,Object>> searchForRenewal(Map<String, Object> paramMap);
    /**
     * 获取协议续期页面列表数据 --导出Excel
     * 
     * @param queryInfo
     * @return int
     * @author baisong
     */
    int findCountForRenewal(Map<String, Object> paramMaps);
    
    /**
     * @Title: approvalFinancial 
     * @Description: 理财特批
     * @param paramMap
     * @return List<Map<String,Object>> 
     * @throws
     * @author lvtu 
     * @date 2015年9月7日 下午2:19:56
     */
	public List<Map<String, Object>> approvalFinancial(Map<String, Object> paramMap);
	public List<Map<String, Object>> approvalFinancialyw(Map<String, Object> paramMap);

	public int findCountApprovalFinancial(Map<String, Object> paramMap);

    /**
     * 根据上单信息表主键获取相关信息--上单表 上单产品表
     * 
     * @param wms_inve_transa_id
     * @return Map
     * @author baisong
     */
    public Map<String,Object> getObjectInfo(Integer wms_inve_transa_id);
    /**
	 * 理财上单草稿作废
	 * @param  wms_inve_transa_id
	 * @date 2015年12月15日 上午11:29
	 * @author jiaodelong
	 */
	public Integer doUpdateCancel(@Param("transa_id")Integer wms_inve_transa_id,@Param("status")Integer status);
    /**
     * Description :【理财上单】-【上单审核】获取审核查询数据
     * @param paramMap
     * @return list
     * @author hancd
     * @date 2015年12月15日 13:11
     */
	public List<Map<String, Object>> getListForSDSHList(
			Map<String, Object> paramMap);
	/**
     * Description :【理财上单】-【上单审核】获取审核查询数据数
     * @param paramMap
     * @return int
     * @author hancd
     * @date 2015年12月15日 13:11
     */
	public int findCountForSDSHList(Map<String, Object> paramMap);
	
	
	/**
	 * @Title: searchList 
	 * @Description: 上单管理--支付退回列表
	 * @param paramMap
	 * @return List<Map<String,Object>> 
	 * @throws
	 * @author baisong
	 * @date 2015年12月16日 下午1:18:22
	 */
	public List<Map<String, Object>> searchListForPaidReturn(Map<String, Object> paramMap);
	/**
	 * @Title: findListCount 
	 * @Description: 上单管理--支付退回列表
	 * @param paramMap
	 * @return int 
	 * @throws
	 * @author baisong
	 * @date 2015年12月16日 下午1:18:22
	 */
	public int findListCountForPaidReturn(Map<String, Object> paramMap);

	
	
	/**
	 * Description :【理财复核】列表显示
	 * @param queryInfo
	 * @return record list
	 * @author jiaodelong
	 */
	public List<Map<String, Object>> searchList(Map<String, Object> paramMap);
	/**
	 * Description :【理财复核】列表显示数量
	 * @param queryInfo
	 * @return record list
	 * @author jiaodelong
	 */
	public int findListCount(Map<String, Object> paramMap);
	 /**
 	  *@Title: getProtocolToConfirmList 
    * Description :【财务管理】-【理财上单】-【理财签约】(打印协议/客户确认) 
    * @param queryInfo
    * @return record list
    * @author hancd
    * @date 2015年12月15日 13:11
    */
	public List<Map<String, Object>> getProtocolToConfirmList(
			Map<String, Object> paramMap);
	/**
	  *@Title: findCountProtocolToConfirmList 
   * Description :【财务管理】-【理财上单】-【理财签约】(打印协议/客户确认) 
   * @param queryInfo
   * @return record list
   * @author hancd
   * @date 2015年12月15日 13:11
   */
	public int findCountProtocolToConfirmList(Map<String, Object> paramMap);
	
	/**
	 * @Title: searchList 
	 * @Description: 上单管理--审核退回列表
	 * @param paramMap
	 * @return List<Map<String,Object>> 
	 * @throws
	 * @author baisong
	 * @date 2015年12月16日 下午1:18:22
	 */
	public List<Map<String, Object>> searchListBackDirectorApproval(Map<String, Object> paramMap);
	/**
	 * @Title: findListCount 
	 * @Description: 上单管理--审核退回列表
	 * @param paramMap
	 * @return int 
	 * @throws
	 * @author baisong
	 * @date 2015年12月16日 下午1:18:22
	 */
	public int findListCountBackDirectorApproval(Map<String, Object> paramMap);
	/**
	 * Description :【复核退回】列表显示
	 * @param queryInfo
	 * @return record list
	 * @author jiaodelong
	 */
	public List<Map<String, Object>> searChreturnCheckList(Map<String, Object> paramMap);
	/**
	 * Description :【复核退回】列表显示数量
	 * @param queryInfo
	 * @return record list
	 * @author jiaodelong
	 */
	public int findChreturnCheckListCount(Map<String, Object> paramMap);
	/**
	 * 根据上单ID 查询单据状态（用于判断是否显示支付TAB页）
	 * 
	 * @param wms_inve_transa_id
	 * @return
	 */
	public String doSearchStatus(@Param("wms_inve_transa_id")Integer wms_inve_transa_id);
/**
 * 处理复核并发
 * 
 * @param wms_inve_transa_id
 * @return
 */
	public Integer getFhConcurrent(@Param("wms_inve_transa_id")Integer wms_inve_transa_id);

	public Map<String, Object> getInfoByPK4Extend(Integer wms_inve_transa_id);

	/**
	 * 只修改单据状态字段
	 * @param wmsInveTransa
	 * @return
	 * @author baisong
	 */
	public  int	updateDataStatus(WmsInveTransa wmsInveTransa);
	
	public Map<String, Object> getProtocolRenewalnew(Map<String,Object> paramMap);
	
	public List<Map<String, Object>> getAutoExtendTransa(Map<String,Object> paramMap);

	public void updateTransaProdAmount(WmsInveTransaProd prod);

	/**
	 * @Deprecated 保存理财上单业务人员历史数据
	 * @param inveTransaSalesmanHis
	 * @author donghao
	 * @date 2016年8月12日08:49:21
	 */
	public void saveWmsInveTransaSalesmanHis(
			WmsInveTransaSalesmanHis inveTransaSalesmanHis);

	/**
	 * 根据客户经理id和客户costomer_id查询所有理财单据。
	 * 客户经理ID（pm_personnel_id）对应单据归属人主键（bel_salesman_id_id）
	 * 
	 * @param 传入参数paramMap中key为pm_personnel_id和cus_ids
	 * @author jinzhm
	 * @return
	 */
	public List<Map<String, Object>> getCustomerDataMoa(
			Map<String, Object> paramMap);
	
	/**
	 * 将归属业务员为pm_personnel_id，客户id_card为cus_ids的上单信息记录的归属业务员及部门经理，副总经理，总经理，中分经理修改（包括部门）。
	 * @param 传入参数paramMap中key为原客户经理ID（pm_personnel_id），迁移数据集合（cus_ids）,新客户经理ID（new_pm_personnel_id）和新客户经理部门ID（new_pm_personnel_dept_id）
	 * @return
	 * @author jinzhm
	 */
	public int changeCustomerDataMoa(Map<String, Object> paramMap);

	/**
	 * @Deprecated 根据条件查询客户上单和赎回中的单据信息
	 * @param paramsMap
	 * @return
	 * @author donghao
	 * @date 2016年8月26日14:03:15
	 */
	public List<Map<String, Object>> findWmsInveTransaBills(Map<String, Object> paramsMap);

	/**
	 * @Deprecated 获取记录数
	 * @param paramsMap
	 * @return
	 * @author donghao
	 * @date 2016年8月26日14:21:16
	 */
	public int findCountWmsInveTransaBills(Map<String, Object> paramsMap);

	/**
	 * @Deprecated 根据客户信息表主键获取对应的客户信息
	 * @param wms_inve_customer_id
	 * @return
	 * @author donghao
	 * @date 2016年9月2日10:45:58
	 */
	public Map<String, Object> findCustomerInfoById(int wms_inve_customer_id);

	/**
	 * @Deprecated 根据上单信息表主键获取对应的上单信息
	 * @param wms_inve_transa_id
	 * @return
	 * @author donghao
	 * @date 2016年9月5日10:29:30
	 */
	public WmsInveTransa getWmsInveTransaByWmsInveTransaId(int wms_inve_transa_id);

	/**
	 * @Deprecated 根据赎回本金表主键和上单信息表主键获取字符信息
	 * @param paramsMap
	 * @return
	 * @author dognhao
	 * @date 2016年9月8日13:15:29
	 */
	public Map<String, Object> findWmsInveTransaCardByDetailIdAndTransaId(Map<String, Object> paramsMap);
	
	/**
	 * @Deprecated 根据赎回信息表主键进行查询对应的上单信息表的主键
	 * @param wms_inve_redeem_id
	 * @return
	 * @author donghao
	 * @date 2016年9月19日10:52:51
	 */
	public int getWmsInveTransaIdByWmsInveRedeemId(int wms_inve_redeem_id);

	/**
	 * @Deprecated 1.根据产品主键id计算出当前产品的总的支付金额
	 *             2.查询的上单信息表中的状态除了草稿和已终止的记录。
	 *             3.传入的map集合中包含两个参数 一个是产品id另一个是上单信息表id
	 * @param paramsMap
	 * @return 返回计算的结果
	 * @author donghao
	 * @date 2016年9月20日15:48:28
	 */
	public int getSumCategoryInveTransaPayaccountByCategoryId(Map<String, Object> paramsMap);

    /**
     * @Title: getNoIncomeList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @return
     * @author: Guanxu
     * @time:2016年10月10日 上午11:21:49 history: 1、2016年10月10日 Guanxu 创建方法
     */
    public List<WmsInveTransaProtocol> getNoIncomeList();
	
	/**
	 * 查询所有收益中的理财单据id
	 * 
	 * @return
	 *
	 * @author 金志明
	 * @date 2016年10月12日 下午4:49:31
	 */
	public List<String> queryAllInIncomeTransa();
	
	/**
	 * @Title: queryNewTransaForAutoExtend
	 * @Description: TODO 通过bill_code（理财单据编号）查询单据的上单信息主键和上单产品信息主键
	 * @param paramMap bill_code
	 * @return 上单信息主键和上单产品信息主键
	 * @author: jinzhiming
	 * @time:2016年10月31日 下午4:25:29
	 * history:
	 * 1、2016年10月31日 jinzhiming 创建方法
	 */
	public Map<String, Object> queryNewTransaForAutoExtend(Map<String, Object> paramMap);

	/**
	 * @Title getRenewalList
	 * @Description 内容摘要:续期申请查询数据列表
	 * @param paramMap
	 * @return
	 * @author DongHao
	 * @Time 2016年11月14日13:31:00
	 * @history: 1、2016年11月14日13:31:14 DongHao 创建方法
	 */
	public List<Map<String, Object>> getRenewalList(Map<String, Object> paramMap);

	/**
	 * @Title updateIsOrderRedeemByWmsInveTransaId
	 * @Description 内容摘要:更新预约赎回单据预约状态
	 * @param wms_inve_transa_id
	 * @author DongHao
	 * @Time 2016年12月1日23:42:04
	 * @history 1. 2016年12月1日23:42:11 DongHao 创建方法
	 */
	public void updateIsOrderRedeemByWmsInveTransaId(Integer wms_inve_transa_id);

    /**
     * @Title: getRenewalCount
     * @Description: 获取复核条件的记录数
     * @param paramMap 查询条件
     * @return 返回整型数据
     * @author: DongHao
     * @time:2016年11月16日 上午9:22:42
     * history:
     * 1、2016年11月16日 DongHao 创建方法
    */
    public int getRenewalCount(Map<String, Object> paramMap);

    /**
     * @Title: saveWmsInveTransaExtendHis
     * @Description: 添加预约续期的历史记录
     * @param wmsInveTransaExtendHisMap 
     * @author: DongHao
     * @time:2016年11月18日 下午4:53:16
     * history:
     * 1、2016年11月18日 DongHao 创建方法
    */
    public void saveWmsInveTransaExtendHis(Map<String, Object> wmsInveTransaExtendHisMap);

    /**
     * @Title: getInveRenewalByNowDateAndIsOrderExtend
     * @Description: 获取预约续期的单据
     * @param paramMap 续期时间
     * @return 
     * @author: DongHao
     * @time:2016年11月18日 下午5:07:34
     * history:
     * 1、2016年11月18日 DongHao 创建方法
    */
    public List<Map<String, Object>> getInveRenewalByNowDateAndIsOrderExtend(Map<String, Object> paramMap);

    /**
     * @Title: getRedeemDataId
     * @Description: 通过上单表主键获取处于赎回中的赎回表主键
     * @param wms_inve_transa_id
     * @return 
     * @author: Guanxu
     * @time:2016年11月27日 下午4:01:20
     * history:
     * 1、2016年11月27日 Guanxu 创建方法
    */
    public String getRedeemDataId(Integer wms_inve_transa_id);

    /**
     * @Title: getTransaExtendTime
     * @Description: 根据上单主键查询该单据续期信息
     * @param wms_inve_transa_id 上单主键
     * @return 续期信息
     * @author: jinzhm
     * @time:2016年12月9日 下午6:21:46
     * history:
     * 1、2016年12月9日 jinzhm 创建方法
     */
    public Map<String, Object> getTransaExtendTime(int wms_inve_transa_id);

    /**
     * @Title: getWmsInveTransaIncomeByWmsInveTransaId
     * @Description: 根据上单信息表主键查询对应的收益信息
     * @param wmsInveTransaId
     * @return 
     * @author: DongHao
     * @time:2016年12月28日 下午5:45:54
     * history:
     * 1、2016年12月28日 DongHao 创建方法
    */
    public Map<String, Object> getWmsInveTransaIncomeByWmsInveTransaId(int wmsInveTransaId);

    /**
     * @Title: getYearCategoryTransaList
     * @Description: 查询所有收益中的年付产品的单据信息
     * @return 收益中的年付产品的单据信息集合
     * @author: jinzhm
     * @time:2016年11月17日 上午8:35:09
     * history:
     * 1、2016年11月17日 jinzhm 创建方法
     */
    public List<Map<String, Object>> getYearCategoryTransaList();

    /**
     * @Title: getLcInfoByTransaId
     * @Description: 根据上单信息表主键查询对应的理财上单信息
     * @param wms_inve_transa_id
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月14日 上午11:20:00
     * history:
     * 1、2017年2月14日 Administrator 创建方法
    */
    public Map<String, Object> getLcInfoByTransaId(Integer wms_inve_transa_id);

    /**
     * @Title: getCategoryIncomeByCategoryId
     * @Description: 根据产品表主键获取收益金额
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年2月28日 上午5:53:57
     * history:
     * 1、2017年2月28日 DongHao 创建方法
    */
    public Map<String, Object> getCategoryIncomeByCategoryId(Map<String, Object> paramsMap);

    /**
     * @Title: getWmsInveTransaProdByWmsInveTransaId
     * @Description: 根据上单表主键获取上单产品表信息
     * @param wms_inve_transa_id
     * @return 
     * @author: DongHao
     * @time:2017年3月1日 上午1:48:51
     * history:
     * 1、2017年3月1日 DongHao 创建方法
    */
    public WmsInveTransaProd getWmsInveTransaProdByWmsInveTransaId(String wms_inve_transa_id);
    
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
    public List<Map<String,Object>> getAdjustShortMessage_grid(Map<String,Object> map);
    
    
    /**
     * 
     * @Title: adjustShortMessage_update
     * @Description: 更新批量短信设置信息
     * @param request
     * @param data
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月16日 上午10:20:47
     * history:
     * 1、2017年3月16日 zhangmingjian 创建方法
     */
    public Integer adjustShortMessage_update(Map<String,Object> map);
    /**
     * 
     * @Title: savePayVouchersInfo
     * @Description: 保存补签收款凭证
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月16日 下午5:00:53
     * history:
     * 1、2017年3月16日 zhangmingjian 创建方法
     */
    public Integer savePayVouchersInfo(Map<String,Object> map);
    
    
    

    /**
     * @Title: saveWmsInveNotificationLog
     * @Description: 保存发送短信通知日志信息
     * @param paramMap 短信通知日志信息
     * @author: jinzhm
     * @time:2017年3月14日 上午10:11:59
     * history:
     * 1、2017年3月14日 jinzhm 创建方法
     */
    public void saveWmsInveNotificationLog(Map<String, Object> paramMap);

    /**
     * @Title: getShareholderBillsByWmsInveTransaId
     * @Description: 根据续期单据的原始id到股东单据表中进行查询
     * @param wms_inve_transa_id 原始上单表主键
     * @return 返回记录数
     * @author: DongHao
     * @time:2017年3月22日 上午8:43:59
     * history:
     * 1、2017年3月22日 DongHao 创建方法
    */
    public Integer getShareholderBillsByWmsInveTransaId(Integer wms_inve_transa_id);

    /**
     * @Title: getInveIncomeBillInfos
     * @Description: 根据查询条件参数进行获取单据信息
     * @param paramsMap 查询条件参数
     * @return 返回list数据集合
     * @author: DongHao
     * @time:2017年3月31日 上午9:50:41
     * history:
     * 1、2017年3月31日 DongHao 创建方法
    */
    public List<Map<String, Object>> getInveIncomeBillInfos(Map<String, Object> paramsMap);

    /**
     * @Title: findInveIncomeBillCount
     * @Description: 根据查询条件参数进行获取单据信息统计数量
     * @param paramsMap 查询条件参数
     * @return 返回int类型的数值
     * @author: DongHao
     * @time:2017年3月31日 上午9:52:21
     * history:
     * 1、2017年3月31日 DongHao 创建方法
    */
    public int findInveIncomeBillCount(Map<String, Object> paramsMap);

    /**
     * @Title: getWmsInveTransaDataStatus
     * @Description: 获取收益账单信息单据状态
     * @return  返回list集合
     * @author: DongHao
     * @time:2017年3月31日 上午11:57:37
     * history:
     * 1、2017年3月31日 DongHao 创建方法
    */
    public List<Map<String, Object>> getWmsInveTransaDataStatus();

    /**
     * @Title: getMaxIncomeMonth
     * @Description: 获取收益月份(获取已经打过收益的收益月份,收益月份时间倒序排序)
     * @return 返回list集合
     * @author: DongHao
     * @time:2017年3月31日 下午1:11:44
     * history:
     * 1、2017年3月31日 DongHao 创建方法
    */
    public Map<String, Object> getMaxIncomeMonth();

    /**
     * @Title: verifyBillIdCardIsConsistent
     * @Description: 根据传入的ids进行获取单据,并且进行判断是否有效证件一致
     * @param idLis 主键集合
     * @return 验证有效证件一致返回true,不一致返回false
     * @author: DongHao
     * @time:2017年3月31日 下午3:36:17
     * history:
     * 1、2017年3月31日 DongHao 创建方法
    */
    public List<WmsInveTransa> verifyBillIdCardIsConsistent(List<String> idLis);

    /**
     * @Title: getCategoryTotalAlreadyIncomeInfosByIds
     * @Description: 根据上单表主键集合进行获取对应的单据已获收益信息
     * @param paramsMap 查询条件集合
     * @return 返回数据集合
     * @author: DongHao
     * @time:2017年4月1日 上午10:16:28
     * history:
     * 1、2017年4月1日 DongHao 创建方法
    */
    public List<Map<String, Object>> getCategoryTotalAlreadyIncomeInfosByIds(Map<String, Object> paramsMap);

    /**
     * @Title: getCategoryTotalStayIncomeInfosByIds
     * @Description: 根据上单表主键集合进行获取对应的单据待获收益信息
     * @param paramsMap 查询条件集合
     * @return 返回数据集合
     * @author: DongHao
     * @time:2017年4月1日 上午10:17:05
     * history:
     * 1、2017年4月1日 DongHao 创建方法
    */
    public List<Map<String, Object>> getCategoryTotalStayIncomeInfosByIds(Map<String, Object> paramsMap);

    /**
     * @Title: getMonthTotalAlreadyIncomeInfosByIds
     * @Description: 根据条件参数查询单据的收益信息(已获收益)
     * @param paramsMap 参数集合
     * @return 返回数据集合
     * @author: DongHao
     * @time:2017年4月1日 下午4:12:38
     * history:
     * 1、2017年4月1日 DongHao 创建方法
    */
    public List<Map<String, Object>> getMonthTotalAlreadyIncomeInfosByIds(Map<String, Object> paramsMap);

    /**
     * @Title: getMonthTotalStayIncomeInfosByIds
     * @Description: 根据条件参数查询单据的收益信息(待获收益)
     * @param paramsMap 参数集合
     * @return 返回数据集合
     * @author: DongHao
     * @time:2017年4月1日 下午4:13:06
     * history:
     * 1、2017年4月1日 DongHao 创建方法
    */
    public List<Map<String, Object>> getMonthTotalStayIncomeInfosByIds(Map<String, Object> paramsMap);

    /**
     * @Title: getCustomerTotalBillInfos
     * @Description: 根据查询条件进行获取客户的收益信息
     * @param paramsMap 查询条件参数
     * @return 返回map结果集合
     * @author: DongHao
     * @time:2017年4月5日 下午5:00:12
     * history:
     * 1、2017年4月5日 DongHao 创建方法
    */
    public Map<String, Object> getCustomerTotalBillInfos(Map<String, Object> paramsMap);

    /**
     * @Title: findWmsInveCustomerIdByCostomerId
     * @Description: 根据crm客户id获取wms系统的中的客户id
     * @param costomerId crm客户id
     * @return 返回wms_inve_customer_id
     * @author: DongHao
     * @time:2017年4月6日 下午4:03:58
     * history:
     * 1、2017年4月6日 DongHao 创建方法
    */
    public Integer findWmsInveCustomerIdByCostomerId(Integer costomerId);

    /**
     * @Title: getNowDayPaidAccount
     * @Description: 根据传入的当前人员id获取当天已支付的线上金额
     * @param userId 当前登录的人员id
     * @return 返回当天已支付的线上金额
     * @author: DongHao
     * @time:2017年4月11日 上午1:12:00
     * history:
     * 1、2017年4月11日 DongHao 创建方法
    */
    public Map<String, Object> getNowDayPaidAccount(Integer userId);

    /**
     * @Title: getNowDayCreditSaleLimit
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param userId
     * @return 
     * @author: DongHao
     * @time:2017年4月11日 上午1:16:00
     * history:
     * 1、2017年4月11日 DongHao 创建方法
    */
    public WmsInveSaleLimit getNowDayCreditSaleLimit(Integer userId);

    /**
     * @Title: getCustomerExtendBillInfo
     * @Description: 根据查询条件获取当前客户的续期本金使用情况(情况分为三种:1. 当前客户所具备的全部续期本金金额, 2. 待支付状态并且标记为续期本金单据的使用的续期本金金额, 3. 已占用的续期本金金额)
     * @param paramsMap 查询条件参数
     * @return 返回客户的续期本金的信息
     * @author: DongHao
     * @time:2017年4月13日04:29:05
     * history:
     * 1、2017年4月13日04:29:05 DongHao 创建方法
    */
	public Map<String, Object> getCustomerExtendBillInfo(Map<String, Object> paramsMap);

    /**
     * @Title: getCategoryType
     * @Description: 根据上单表主键获取对应的上单产品的产品类型
     * @param wms_inve_product_category_id 产品表主键
     * @return 返回产品类型
     * @author: DongHao
     * @time:2017年5月1日 下午7:46:48
     * history:
     * 1、2017年5月1日 DongHao 创建方法
    */
    public Map<String, Object> getCategoryType(Integer wms_inve_product_category_id);

    /**
     * @Title: getCUstomerProductAccount
     * @Description: 根据查询参数获取客户投资金额
     * @param paramsMap 查询参数
     * @return 返回map集合
     * @author: DongHao
     * @time:2017年5月1日 下午9:10:54
     * history:
     * 1、2017年5月1日 DongHao 创建方法
    */
    public Map<String, Object> getCUstomerProductAccount(Map<String, Object> paramsMap);

    /**
     * @Title: getCategoryTypeByWmsInveTransaId
     * @Description: 根据上单表主键获取对应的上单产品的类型
     * @param wms_inve_transa_id 上单表主键
     * @return 返回map产品信息
     * @author: DongHao
     * @time:2017年5月27日 下午4:59:20
     * history:
     * 1、2017年5月27日 DongHao 创建方法
    */
    public Map<String, Object> getCategoryTypeByWmsInveTransaId(Integer wms_inve_transa_id);

    /**
     * @Title: searchFinancialTransaListForExport
     * @Description: 为财务主管导出签单情况报表查询
     * @param paramMap 查询条件
     * @return 签单情况集合
     * @author: jinzhm
     * @time:2017年5月10日 上午11:48:43
     * history:
     * 1、2017年5月10日 jinzhm 创建方法
     */
    public List<Map<String, Object>> searchFinancialTransaListForExport(Map<String, Object> paramMap);

    /**
     * @Title: searchTransaListForExport
     * @Description: 为财务柜员主管导出签单情况报表查询
     * @param paramMap 查询条件
     * @return 签单情况集合
     * @author: jinzhm
     * @time:2017年5月11日 上午10:49:21
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
     */
    public List<Map<String, Object>> searchTransaListForExport(Map<String, Object> paramMap);

    /**
     * @Title: insertShareHolderTransaAutoExtend
     * @Description: 保存股东单据自动续期信息
     * @param dataMap 股东单据自动续期数据map对象
     * @author: jinzhm
     * @time:2017年6月7日 上午9:53:12
     * history:
     * 1、2017年6月7日 jinzhm 创建方法
     */
    public void insertShareHolderTransaAutoExtend(Map<String, Object> dataMap);

    /**
     * @Title: getTransaByOldLastTransaId
     * @Description: 根据上一个单据主键查找新的单据信息
     * @param oldLastTransaId 上一个单据主键
     * @return 单据信息
     * @author: jinzhm
     * @time:2017年6月7日 下午2:43:47
     * history:
     * 1、2017年6月7日 jinzhm 创建方法
     */
    public WmsInveTransa getTransaByOldLastTransaId(Integer oldLastTransaId);

    /**
     * @Title: getOldDateOfPaymentById
     * @Description: 通过主键查询单据原始上单时间
     * @param wms_inve_transa_id
     * @return 
     * @author: zhangyunfei
     * @time:2017年6月19日 下午4:38:44
     * history:
     * 1、2017年6月19日 Administrator 创建方法
    */
    public WmsInveTransa getOldDateOfPaymentById(Integer wms_inve_transa_id);

    /**
     * 
     * @Title: getWmsInveTransaInfoByIdCard
     * @Description: 根据客户的身份证号进行获取对应客户的单据信息
     * @param paramsMap 查询参数集合
     * @return 返回对应的数据集合
     * @author: DongHao
     * @time:2017年6月19日 上午9:03:21
     * history:
     * 1、2017年6月19日 DongHao 创建方法
     */
    public List<WmsInveTransa> getWmsInveTransaInfoByIdCard(Map<String, Object> paramsMap);

    /**
     * @Title: getCategoryProtocolTypeByWmsInveTransaId
     * @Description: 通过上单主键查询产品是否提供纸质合同 
     * @param wms_inve_transa_id
     * @return 
     * @author: zhangyunfei
     * @time:2017年7月18日 下午5:13:24
     * history:
     * 1、2017年7月18日 admin 创建方法
    */
    public Map<String, Object> getCategoryProtocolTypeByWmsInveTransaId(int wms_inve_transa_id);

    /**
     * @Title: getWithoutPaperProtocolTransaInfoList
     * @Description: 通过手机号查询无纸质合同产品单据信息按签单日期倒序
     * @param mobile_phone 手机号
     * @return 无纸质合同产品单据信息按签单日期倒序
     * @author: jinzhm
     * @time:2017年7月21日 上午9:03:08
     * history:
     * 1、2017年7月21日 jinzhm 创建方法
     */
    public List<WmsInveTransa> getWithoutPaperProtocolTransaInfoList(String mobile_phone);

}