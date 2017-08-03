package com.zx.emanage.inve.service;

import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveTransaSearchBeanVO;
import com.zx.emanage.util.gen.entity.CrmCustomerInfo;
import com.zx.emanage.util.gen.entity.WmsInveCustomer;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: IWmsInvePhoneService
 * 模块名称：
 * @Description: 内容摘要：
 * @author DongHao
 * @date 2016年12月30日
 * @version V1.0
 * history:
 * 1、2016年12月30日 DongHao 创建文件
 */
public interface IWmsInvePhoneService
{

    /**
     * @Title: getClerkSortData
     * @Description: 获取排队动态信息及当日设置的剩余可售额度
     * @param personnel_id 登录用户id
     * @return 排队动态信息及当日剩余可售额度
     * @author: jinzhm
     * @time:2017年4月6日 下午5:00:50
     * history:
     * 1、2017年4月6日 jinzhm 创建方法
     */
    Map<String, Object> getClerkSortData(String personnel_id);

    /**
     * @Title: getSpecialMenuInfosMoa
     * @Description: 根据当前登录的用户id,获取app端特殊菜单项
     * @param personnel_id
     * @return 
     * @author: DongHao
     * @param version 
     * @time:2016年12月30日 下午2:23:08
     * history:
     * 1、2016年12月30日 DongHao 创建方法
    */
    Map<String, Object> getSpecialMenuInfosMoa(String personnel_id, String version);

    /**
     * @Title: getSpecialTeamInfosMoa
     * @Description: 获取团队信息
     * @param personnel_id
     * @param dept_id
     * @param menu_code
     * @return 
     * @author: DongHao
     * @time:2017年1月1日 下午5:07:54
     * history:
     * 1、2017年1月1日 DongHao 创建方法
    */
    Map<String, Object> getSpecialTeamInfosMoa(int personnel_id, String dept_id, String menu_code);

    /**
     * @Title: getSpecialDeptInfosMoa
     * @Description: 获取数据权限下的部门信息
     * @param personnel_id
     * @param dept_id
     * @param menu_code
     * @return 
     * @author: DongHao
     * @time:2017年1月3日 上午9:33:00
     * history:
     * 1、2017年1月3日 DongHao 创建方法
    */
    Map<String, Object> getSpecialDeptInfosMoa(String personnel_id, String dept_id, String menu_code);

    /**
     * @Title: getSyncMePerformanceInfosMoa
     * @Description: 同步与我有关的业绩信息
     * @param personnel_id 人员id
     * @param query_type 查询数据范围(1:表示待我处理数据,2:与我相关数据)
     * @return 
     * @author: DongHao
     * @time:2017年1月5日 下午2:50:33
     * history:
     * 1、2017年1月5日 DongHao 创建方法
    */
    Map<String, Object> getSyncMePerformanceInfosMoa(String personnel_id, String query_type);

    /**
     * @Title: getAchVicegelListMoa
     * @Description: 获取数据权限下的副总清单
     * @param menu_code
     * @param personnel_id
     * @return 
     * @author: DongHao
     * @time:2017年1月6日 上午11:45:51
     * history:
     * 1、2017年1月6日 DongHao 创建方法
    */
    List<Map<String, Object>> getAchVicegelListMoa(String menu_code, String personnel_id);

    /**
     * @Title: getClerkData
     * @Description:获取手持端排队客户列表信息
     * @param personnel_id
     * @return 
     * @author: Guanxu
     * @time:2017年2月16日 下午1:57:58
     * history:
     * 1、2017年2月16日 Guanxu 创建方法
    */
    List<Map<String, Object>> getClerkData(String personnel_id);

    /**
     * @Title: getIncomeCardPad
     * @Description: 根据客户姓名和客户身份证号进行查询客户收益卡信息
     * @return 返回客户收益卡list集合
     * @author: DongHao
     * @time:2017年3月1日 下午8:47:59
     * history:
     * 1、2017年3月1日 DongHao 创建方法
    */
    List<Map<String, Object>> getIncomeCardPad(String cus_name, String id_card);

    /**
     * @Title: getCustomerListPad
     * @Description: 根据客户姓名、联系方式和有效证件进行获取客户的信息
     * @param bean 客户信息对象
     * @param contact_number 联系方式
     * @param id_card_number 有效证件
     * @param user 当前登录用户信息
     * @return 返回map集合
     * @author: DongHao
     * @time:2017年3月1日 下午9:21:11
     * history:
     * 1、2017年3月1日 DongHao 创建方法
    */
    Map<String, Object> getCustomerListPad(CrmCustomerInfo bean);

    /**
     * @Title: getCategoryListPad
     * @Description: 根据当前登录的用户获取产品信息
     * @param category_type 产品类型 1：表示公司主推产品,2:表示柜员可售产品
     * @param user 用户信息
     * @return  返回产品信息list集合
     * @author: DongHao
     * @time:2017年3月1日 下午9:58:52
     * history:
     * 1、2017年3月1日 DongHao 创建方法
    */
    List<Map<String, Object>> getCategoryListPad(int category_type, UserBean user);

    /**
     * @Title: verificationCustomerPad
     * @Description: 验证客户关键信息是否存在
     * @param contact_number 联系电话
     * @param id_card_number 有效证件
     * @param costomer_id 客户id
     * @param user 用户对象
     * @return 返回map集合
     * @author: DongHao
     * @time:2017年3月2日 上午12:44:47
     * history:
     * 1、2017年3月2日 DongHao 创建方法
    */
    Map<String, Object> verificationCustomerPad(String contact_number, String id_card_number, String costomer_id, UserBean user);

    /**
     * @Title: getClerkDataCount
     * @Description: 获取客户经理名下排队人员总数
     * @param personnel_id
     * @return 
     * @author: Guanxu
     * @time:2017年3月1日 下午1:19:48
     * history:
     * 1、2017年3月1日 Guanxu 创建方法
    */
    int getClerkDataCount(String personnel_id);

    /**
     * @Title: getTeamCommMonByDeptManagerMoa
     * @Description: 获取管理佣金按部门经理进行汇总(接口 33)
     * @param page 当前页
     * @param page_size 每页显示的记录数
     * @param statics_month 统计月份
     * @param dept_ids 团队id(多个团队id以","分隔)
     * @param personnel_info 部门经理姓名或者部门经理短工号
     * @param personnel_id 当前登录moa人员id
     * @return 返回数据集合
     * @author: DongHao
     * @time:2017年3月10日 下午4:24:30
     * history:
     * 1、2017年3月10日 DongHao 创建方法
    */
    Map<String, Object> getTeamCommMonByDeptManagerMoa(Integer page, Integer page_size, String statics_month, String dept_ids, String personnel_info,
                                                       Integer personnel_id);

    /**
     * @Title: getTeamCommMonBySalemanMoa
     * @Description: 获取管理佣金按客户经理汇总
     * @param offset 当前页
     * @param pagesize 每页显示的数据数量
     * @param statics_month 佣金统计月份
     * @param dept_ids 团队id值(多个团队id以","分隔)
     * @param personnel_info 客户经理姓名或客户经理短工号
     * @param personnel_id 部门经理人员id
     * @param user_id 当前登录moa的人员id
     * @return 返回Map数据集合
     * @author: DongHao
     * @time:2017年3月13日 上午9:47:10
     * history:
     * 1、2017年3月13日 DongHao 创建方法
    */
    Map<String, Object> getTeamCommMonBySalemanMoa(Integer offset, Integer pagesize, String statics_month, String dept_ids, String personnel_info,
                                                   String personnel_id, Integer user_id);

    /**
     * @Title: getTeamCommMonByItemMoa
     * @Description: 获取管理佣金按佣金项汇总
     * @param statics_month 佣金统计月份
     * @param personnel_id 客户经理人员id
     * @return 返回结果集合
     * @author: DongHao
     * @time:2017年3月13日 上午11:17:22
     * history:
     * 1、2017年3月13日 DongHao 创建方法
    */
    Map<String, Object> getTeamCommMonByItemMoa(String statics_month, String personnel_id);

    /**
     * @Title: getTeamCommMonByManagerMoa
     * @Description: 获取管理佣金按客户经理汇总For副总/中分总/总经理/后线领导
     * @param page 当前页
     * @param pagesize 每页显示的记录数
     * @param personnel_info 客户经理姓名或客户经理的短工号
     * @param statics_month 佣金统计月份
     * @param personnel_id 当前登录Moa系统的人员id
     * @return 返回结果集合
     * @author: DongHao
     * @time:2017年3月13日 上午11:44:24
     * history:
     * 1、2017年3月13日 DongHao 创建方法
    */
    Map<String, Object> getTeamCommMonByManagerMoa(Integer page, Integer pagesize, String personnel_info, String statics_month, Integer personnel_id);

    /**
     * @Title: getTeamCommMonByDataMoa
     * @Description: 获取管理佣金按单据展现
     * @param page 当前页
     * @param pagesize 每页显示多少记录数
     * @param personnel_info 客户姓名
     * @param statics_month 佣金统计月份
     * @param personnel_id 人员id
     * @param comm_item_ids 佣金项id(01表示老佣金,02表示新佣金)
     * @return 返回结果集合
     * @author: DongHao
     * @time:2017年3月13日 上午11:59:04
     * history:
     * 1、2017年3月13日 DongHao 创建方法
    */
    Map<String, Object> getTeamCommMonByDataMoa(Integer page, Integer pagesize, String personnel_info, String statics_month, Integer personnel_id,
                                                String comm_item_ids);

    /**
     * 
     * @Title: delIncomeCardByWmsInveCustomerCardId
     * @Description: 根据收益卡表主键进行删除收益卡信息
     * @param wms_inve_customer_card_id 收益卡表主键
     * @return 返回布尔类型的值,删除成功返回true,否则返回false
     * @author: DongHao
     * @time:2017年6月6日 下午1:21:03
     * history:
     * 1、2017年6月6日 DongHao 创建方法
     */
    boolean delIncomeCardByWmsInveCustomerCardId(Integer wms_inve_customer_card_id);

    /**
     * 
     * @Title: getRedeemBillsPad
     * @Description: 根据客户crmid进行获取客户的到期以及超期的收益中的单据
     * @param costomer_id 客户crmid
     * @return 返回数据集合
     * @author: DongHao
     * @time:2017年6月6日 下午2:14:43
     * history:
     * 1、2017年6月6日 DongHao 创建方法
     */
    List<Map<String, Object>> getRedeemBillsPad(Integer costomer_id);

    /**
     * 
     * @Title: inveRedemptionPadHandler
     * @Description: pad端执行赎回在上单的流程
     * @param user 当前登录的用户信息
     * @param wmsInveTransa 上单对象信息
     * @param wmsInveTransaProd 上单产品对象信息
     * @param wmsInveCustomer 上单客户对象信息
     * @param list 上单产品以及投资金额信息
     * @param beanVO 上单对象
     * @param version 版本号
     * @param wms_inve_transa_id_strs 上单产品表主键(多个以","分隔) 
     * @author: DongHao
     * @time:2017年6月7日 下午1:09:43
     * history:
     * 1、2017年6月7日 DongHao 创建方法
     */
    Map<String, Object> inveRedemptionPadHandler(UserBean user, WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd, WmsInveCustomer wmsInveCustomer, List<Map<String, Object>> list, WmsInveTransaSearchBeanVO beanVO, String version, String wms_inve_transa_id_strs);


    /**
     * 
     * @Title: getRedeemInfoPad
     * @Description: 获取单据赎回详情信息
     * @param wms_inve_transa_id
     * @return 
     * @author: zhangmingjian
     * @time:2017年6月7日 下午1:12:40
     * history:
     * 1、2017年6月7日 zhangmingjian 创建方法
     */
    public Map<String,Object> getRedeemInfoPad(String wms_inve_transa_id);
    
    /**
     * 
     * @Title: getCustomerBankCard
     * @Description: 根据身份证号，银行卡号获取银行卡相关信息
     * @param map
     * @return 
     * @author: zhangmingjian
     * @time:2017年6月9日 下午1:06:42
     * history:
     * 1、2017年6月9日 zhangmingjian 创建方法
     */
    public List<Map<String,Object>> getCustomerBankCard(Map<String,Object> map);

    /**
     * 
     * @Title: verifyBillIsSingle
     * @Description: 根据传入的信息验证是否可以进行赎回再签
     * @param wmsInveTransa 上单信息表主键
     * @param wmsInveTransaProd 上单产品表主键
     * @param categoryLis 上单产品信息
     * @param wms_inve_transa_id 赎回单据上单表主键(多个以“,”分隔)
     * @return 返回错误提示信息
     * @author: DongHao
     * @time:2017年9月20日 下午1:01:42
     * history:
     * 1、2017年9月20日 DongHao 创建方法
     */
    Map<String, Object> verifyBillIsSingle(WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd,List<Map<String, Object>> categoryLis, String wms_inve_transa_id);

    /**
     * 
     * @Title: genCustomerBuyNewCategoryData
     * @Description: 生成可以购买新产品的客户信息
     * @return 返回信息提示
     * @author: DongHao
     * @time:2017年9月2日 上午9:21:44
     * history:
     * 1、2017年9月2日 DongHao 创建方法
     */
    Map<String, Object> genCustomerBuyNewCategoryData() throws Exception;
}
