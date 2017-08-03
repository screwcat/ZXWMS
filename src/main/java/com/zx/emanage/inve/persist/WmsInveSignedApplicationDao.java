package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveSignedApplicationDao
 * 模块名称：
 * @Description: 内容摘要：
 * @author DongHao
 * @date 2017年2月5日
 * @version V1.0
 * history:
 * 1、2017年2月5日 DongHao 创建文件
 */
@MyBatisRepository
public interface WmsInveSignedApplicationDao
{

    /**
     * @Title: getSignedInfos
     * @Description: 签单申请列表信息查询
     * @param paramsMap 查询条件map集合
     * @return 
     * @author: DongHao
     * @time:2017年2月4日 下午6:06:47
     * history:
     * 1、2017年2月4日 DongHao 创建方法
    */
    public List<Map<String, Object>> getSignedInfos(Map<String, Object> paramsMap);

    /**
     * @Title: getSignedInfosCount
     * @Description: 签单申请列表信息数量查询
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年2月4日 下午6:12:37
     * history:
     * 1、2017年2月4日 DongHao 创建方法
    */
    public int getSignedInfosCount(Map<String, Object> paramsMap);

    /**
     * @Title: getCustomerIncomeCardInfos
     * @Description: 查询客户收益卡信息
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年2月10日 上午11:29:50
     * history:
     * 1、2017年2月10日 DongHao 创建方法
    */
    public List<Map<String, Object>> getCustomerIncomeCardInfos(Map<String, Object> paramsMap);

    /**
     * @Title: getWmsInveTransaInfosByWmsInveTransaId
     * @Description: 根据上单表主键获取上单信息
     * @param wms_inve_transa_id
     * @return 
     * @author: DongHao
     * @time:2017年2月11日 下午1:12:34
     * history:
     * 1、2017年2月11日 DongHao 创建方法
    */
    public Map<String, Object> getWmsInveTransaInfosByWmsInveTransaId(String wms_inve_transa_id);

    /**
     * @Title: getAmountConfirmInfos
     * @Description: 获取待支付的单据
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年2月13日 上午10:16:54
     * history:
     * 1、2017年2月13日 DongHao 创建方法
    */
    public List<Map<String, Object>> getAmountConfirmInfos(Map<String, Object> paramsMap);

    /**
     * @Title: getAmountConfirmInfosCount
     * @Description: 获取待支付的单据统计数量
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年2月13日 上午10:21:05
     * history:
     * 1、2017年2月13日 DongHao 创建方法
    */
    public int getAmountConfirmInfosCount(Map<String, Object> paramsMap);

    /**
     * @Title: getWmsInveRenYuanInfo
     * @Description: 根据上单表主键获取对应的人员信息
     * @param wms_inve_transa_id
     * @return 
     * @author: DongHao
     * @time:2017年2月13日 下午4:31:32
     * history:
     * 1、2017年2月13日 DongHao 创建方法
    */
    public Map<String, Object> getWmsInveRenYuanInfo(String wms_inve_transa_id);

    /**
     * @Title: getSigendProtocolList
     * @Description: 合同签订信息列表
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年2月13日 下午5:55:10
     * history:
     * 1、2017年2月13日 DongHao 创建方法
    */
    public List<Map<String, Object>> getSigendProtocolList(Map<String, Object> paramsMap);

    /**
     * @Title: getSigendProtocolListCount
     * @Description: 合同签订信息列表信息统计
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年2月13日 下午5:55:27
     * history:
     * 1、2017年2月13日 DongHao 创建方法
    */
    public int getSigendProtocolListCount(Map<String, Object> paramsMap);

    /**
     * @Title: updateContractSigningType
     * @Description: 修改单据的合同类型
     * @param wms_inve_transa_id 
     * @author: DongHao
     * @time:2017年2月28日 上午1:46:29
     * history:
     * 1、2017年2月28日 DongHao 创建方法
    */
    public void updateContractSigningType(String wms_inve_transa_id);

    /**
     * @Title: updateContractSigningTypeOnLine
     * @Description: 如果将时间改成当前合同类型为线上
     * @param wms_inve_transa_id 
     * @author: DongHao
     * @time:2017年2月28日 上午6:25:38
     * history:
     * 1、2017年2月28日 DongHao 创建方法
    */
    public void updateContractSigningTypeOnLine(String wms_inve_transa_id);

    /**
     * @Title: updateWmsInveClerkProtocolByWmsInveTransaId
     * @Description: 作废上单单据时需要同步更新柜员合同协议表enable_flag == 0
     * @param wms_inve_transa_id  上单表主键
     * @author: DongHao
     * @time:2017年3月2日 下午4:53:24
     * history:
     * 1、2017年3月2日 DongHao 创建方法
    */
    public void updateWmsInveClerkProtocolByWmsInveTransaId(String wms_inve_transa_id);

    /**
     * @Title: getLocalCustomerInfosByCostomerId
     * @Description: 根据crm客户id进行获取上单表中的数据进行验证有效证件号是否重复
     * @param costomer_id crm客户id
     * @return 返回客户信息集合
     * @author: DongHao
     * @time:2017年12月21日 上午5:22:49
     * history:
     * 1、2017年12月21日 DongHao 创建方法
    */
    public List<Map<String, Object>> getLocalCustomerInfosByCostomerId(Integer costomer_id);

    /**
     * @Title: getLocalCustomerInfosByIdCard
     * @Description: 根据客户的有效证件号进行获取客户信息数据
     * @param id_card_number 有效证件号
     * @return 返回客户信息集合
     * @author: DongHao
     * @time:2017年12月21日 上午5:35:18
     * history:
     * 1、2017年12月21日 DongHao 创建方法
    */
    public List<Map<String, Object>> getLocalCustomerInfosByIdCard(String id_card_number);

    /**
     * 
     * @Title: getWmsInvePruductCategoryByIds
     * @Description: 根据产品表主键集合进行获取产品信息
     * @param list 查询产品的id集合
     * @return 返回产品信息list集合
     * @author: DongHao
     * @time:2017年7月18日 下午2:12:54
     * history:
     * 1、2017年7月18日 DongHao 创建方法
     */
    public List<WmsInvePruductCategory> getWmsInvePruductCategoryByIds(List<String> list);

    /**
     * 
     * @Title: getCustomerBuyBillCount
     * @Description: 根据查询条件获取客户在系统中存在的满足条件的理财单据
     * @param paramsMap 查询条件
     * @return 返回int类型的数据记录数
     * @author: DongHao
     * @time:2017年7月18日 下午2:32:44
     * history:
     * 1、2017年7月18日 DongHao 创建方法
     */
    public int getCustomerBuyBillCount(Map<String, Object> paramsMap);

    /**
     * 
     * @Title: getCustomerIncomeingBill
     * @Description: 根据条件查询指定条件中是否仍有收益中的单据
     * @param paramsMap 查询参数map
     * @return 返回int类型的记录数量
     * @author: DongHao
     * @time:2017年9月1日 下午5:30:09
     * history:
     * 1、2017年9月1日 DongHao 创建方法
     */
    public int getCustomerIncomeingBill(Map<String, Object> paramsMap);

    /**
     * 
     * @Title: getWmsInveTransaRecentBill
     * @Description: 根据查询条件获取最近上单日期的收益中的单据
     * @param paramsMap 查询参数
     * @return 返回上单信息
     * @author: DongHao
     * @time:2017年9月1日 下午5:38:21
     * history:
     * 1、2017年9月1日 DongHao 创建方法
     */
    public WmsInveTransa getWmsInveTransaRecentBill(Map<String, Object> paramsMap);

    /**
     * 
     * @Title: getNewCategory
     * @Description: 根据产品表主键到限制可以购买新产品的客户表中进行获取,获取到数据则需要验证,否则不需要验证
     * @param wms_inve_pruduct_category_id 产品表主键
     * @return 返回获取到的记录数
     * @author: DongHao
     * @time:2017年9月2日 下午1:32:21
     * history:
     * 1、2017年9月2日 DongHao 创建方法
     */
    public int getNewCategory(Integer wms_inve_pruduct_category_id);

    /**
     * 
     * @Title: getNewCategoryLimitCustomer
     * @Description: 根据查询条件进行查询限制客户购买新产品的表中获取对应的客户,查询到则允许购买该产品,否则不允许购买该产品
     * @param paramsMap 查询条件map集合
     * @return 返回记录数
     * @author: DongHao
     * @time:2017年9月2日 下午1:38:40
     * history:
     * 1、2017年9月2日 DongHao 创建方法
     */
    public int getNewCategoryLimitCustomer(Map<String, Object> paramsMap);

}
