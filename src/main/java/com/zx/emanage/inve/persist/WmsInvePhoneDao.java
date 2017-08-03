package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInvePhoneDao
 * 模块名称：
 * @Description: 内容摘要：
 * @author DongHao
 * @date 2016年12月30日
 * @version V1.0
 * history:
 * 1、2016年12月30日 DongHao 创建文件
 */
@MyBatisRepository
public interface WmsInvePhoneDao
{

    /**
     * 
     * @Title: getSpecialMenuInfosMoa
     * @Description: 根据当前登录MOA的用户的id进行获取对应的特殊的菜单项
     * @param map
     * @return 
     * @author: DongHao
     * @time:2016年12月30日 下午2:27:12
     * history:
     * 1、2016年12月30日 DongHao 创建方法
     */
    List<Map<String, Object>> getSpecialMenuInfosMoa(Map<String, Object> map);

    /**
     * @Title: getSpecialTeamInfosMoa
     * @Description: 获取团队信息
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年1月1日 下午5:09:22
     * history:
     * 1、2017年1月1日 DongHao 创建方法
    */
    List<Map<String, Object>> getSpecialTeamInfosMoa(Map<String, Object> paramsMap);

    /**
     * @Title: getPersonnelById
     * @Description: 根据人员id获取人员信息
     * @param personnel_id
     * @return 
     * @author: DongHao
     * @time:2017年1月1日 下午5:47:23
     * history:
     * 1、2017年1月1日 DongHao 创建方法
    */
    Map<String, Object> getPersonnelById(int personnel_id);

    /**
     * @Title: getSpecialTeamIdsStr
     * @Description: 获取所有团队的所有id
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年1月1日 下午6:23:38
     * history:
     * 1、2017年1月1日 DongHao 创建方法
    */
    String getSpecialTeamIdsStr(Map<String, Object> paramsMap);

    /**
     * @Title: getSpecialDeptInfosMoa
     * @Description: 获取数据权限下的部门信息
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年1月3日 上午9:45:35
     * history:
     * 1、2017年1月3日 DongHao 创建方法
    */
    List<Map<String, Object>> getSpecialDeptInfosMoa(Map<String, Object> paramsMap);

    /**
     * @Title: getSpecialDeptIdsStr
     * @Description: 获取所有部门的所有id
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年1月3日 上午9:45:54
     * history:
     * 1、2017年1月3日 DongHao 创建方法
    */
    String getSpecialDeptIdsStr(Map<String, Object> paramsMap);

    /**
     * @Title: searchForPhoneAppData
     * @Description: 查询赎回单据信息
     * @param threeApprovalMap
     * @return 
     * @author: DongHao
     * @time:2017年1月5日 下午3:00:33
     * history:
     * 1、2017年1月5日 DongHao 创建方法
    */
    List<Map<String, Object>> searchForPhoneAppData(Map<String, Object> threeApprovalMap);

    /**
     * @Title: getRelatedMeInfos
     * @Description: 获取与我有关的数据信息
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年1月5日 下午4:11:54
     * history:
     * 1、2017年1月5日 DongHao 创建方法
    */
    List<Map<String, Object>> getRelatedMeInfos(Map<String, Object> paramsMap);

    /**
     * @Title: getAchVicegelListMoa
     * @Description: 获取数据权限下的副总清单
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年1月6日 上午11:47:13
     * history:
     * 1、2017年1月6日 DongHao 创建方法
    */
    List<Map<String, Object>> getAchVicegelListMoa(Map<String, Object> paramsMap);

    /**
     * @Title: getIncomeCardPadList
     * @Description: 根据客户姓名和客户身份证号进行查询客户收益卡信息
     * @param paramsMap 查询条件参数map集合
     * @return 返回客户收益卡list集合
     * @author: DongHao
     * @time:2017年3月1日 下午8:50:55
     * history:
     * 1、2017年3月1日 DongHao 创建方法
    */
    List<Map<String, Object>> getIncomeCardPadList(Map<String, Object> paramsMap);

    /**
     * @Title: getCategoryInfosByCompanyMainPush
     * @Description: 获取当前登录人员所在地区主推产品信息
     * @param pmPersonnel 当前登录的人员信息
     * @return 返回登录人员对应归属地的主推产品信息
     * @author: DongHao
     * @time:2017年3月1日 下午10:12:43
     * history:
     * 1、2017年3月1日 DongHao 创建方法
    */
    List<Map<String, Object>> getCategoryInfosByCompanyMainPush(PmPersonnel pmPersonnel);

    /**
     * @Title: getCategoryInfos
     * @Description: 根据条件查询产品信息
     * @param paramMap 条件参数map集合
     * @return  返回产品信息集合
     * @author: DongHao
     * @time:2017年3月1日 下午11:05:21
     * history:
     * 1、2017年3月1日 DongHao 创建方法
    */
    List<Map<String, Object>> getCategoryInfos(Map<String, Object> paramMap);

    /**
     * @Title: getTeamCommMonByDeptManagerMoa
     * @Description: 根据条件进行查询获取管理佣金按部门经理进行汇总
     * @param paramsMap 参数集合
     * @return 返回List
     * @author: DongHao
     * @time:2017年3月10日 下午4:40:34
     * history:
     * 1、2017年3月10日 DongHao 创建方法
    */
    List<Map<String, Object>> getTeamCommMonByDeptManagerMoa(Map<String, Object> paramsMap);

    /**
     * @Title: getTeamCommMonBySalemanMoa
     * @Description: 根据条件查询获取管理佣金按客户经理汇总信息
     * @param paramsMap 参数map集合
     * @return 返回结果集合
     * @author: DongHao
     * @time:2017年3月13日 上午10:20:51
     * history:
     * 1、2017年3月13日 DongHao 创建方法
    */
    List<Map<String, Object>> getTeamCommMonBySalemanMoa(Map<String, Object> paramsMap);

    /**
     * @Title: getTeamCommMonByItemMoa
    *  @Description: 接口35 获取管理佣金按佣金项汇总
     * @param paramsMap 查询条件参数
     * @return 返回结果集合
     * @author: DongHao
     * @time:2017年3月13日 上午11:21:39
     * history:
     * 1、2017年3月13日 DongHao 创建方法
    */
    List<Map<String, Object>> getTeamCommMonByItemMoa(Map<String, Object> paramsMap);

    /**
     * @Title: getOldTeamCommMonByData
     * @Description: 获取老佣金管理佣金按单据展现
     * @param paramsMap 查询条件参数集合
     * @return 返回结果集合
     * @author: DongHao
     * @time:2017年3月13日 下午12:56:41
     * history:
     * 1、2017年3月13日 DongHao 创建方法
    */
    List<Map<String, Object>> getOldTeamCommMonByData(Map<String, Object> paramsMap);

    /**
     * @Title: getNewTeamCommMonByData
     * @Description: 获取新佣金管理佣金按单据展现
     * @param paramsMap 查询条件参数集合
     * @return 返回结果集合
     * @author: DongHao
     * @time:2017年3月13日 下午12:57:17
     * history:
     * 1、2017年3月13日 DongHao 创建方法
    */
    List<Map<String, Object>> getNewTeamCommMonByData(Map<String, Object> paramsMap);

    /**
     * @Title: getTeamCommMonByManagerMoa
     * @Description: 获取副总/中分总/总经理/后线领导佣金情况
     * @param paramsMap 添加查询参数
     * @return 返回结果集合
     * @author: DongHao
     * @time:2017年3月13日 下午1:36:12
     * history:
     * 1、2017年3月13日 DongHao 创建方法
    */
    List<Map<String, Object>> getTeamCommMonByManagerMoa(Map<String, Object> paramsMap);

    /**
     * @Title: getTeamCommMonByManagerMoaReturnMap
     * @Description: 获取副总/中分总/总经理/后线领导佣金合计
     * @param paramsMap 查询条件参数
     * @return 返回map集合
     * @author: DongHao
     * @time:2017年3月13日 下午2:36:21
     * history:
     * 1、2017年3月13日 DongHao 创建方法
    */
    Map<String, Object> getTeamCommMonByManagerMoaReturnMap(Map<String, Object> paramsMap);

    /**
     * 
     * @Title: delIncomeCardByWmsInveCustomerCardId
     * @Description: 根据收益卡信息表主键进行删除收益卡信息
     * @param wms_inve_customer_card_id 收益卡信息表主键
     * @author: DongHao
     * @time:2017年6月6日 下午1:26:27
     * history:
     * 1、2017年6月6日 DongHao 创建方法
     */
    void delIncomeCardByWmsInveCustomerCardId(Integer wms_inve_customer_card_id);

    /**
     * 
     * @Title: getRedeemBillsPadByCrmid
     * @Description: 根据客户crmid进行获取当前客户的到期/超期的收益中的单据
     * @param costomer_id 客户crmid
     * @return 返回数据集合
     * @author: DongHao
     * @time:2017年6月6日 下午2:16:37
     * history:
     * 1、2017年6月6日 DongHao 创建方法
     */
    List<Map<String, Object>> getRedeemBillsPadByCrmid(Integer costomer_id);
  /**
   * 
   * @Title: getRedeemInfoPad
   * @Description: 获取单据赎回详情信息
   * @param wms_inve_transa_id
   * @return 
   * @author: zhangmingjian
   * @time:2017年6月7日 下午1:14:03
   * history:
   * 1、2017年6月7日 zhangmingjian 创建方法
   */
    public List<Map<String,Object>> getRedeemInfoPad(List<String> ids);
    /**
     * 
     * @Title: getWmsInveTransaInfoById
     * @Description: 根据上单表主键获取对应的单据信息
     * @param wms_inve_transa_id 上单表主键
     * @return 返回map集合
     * @author: DongHao
     * @time:2017年6月7日 下午2:11:30
     * history:
     * 1、2017年6月7日 DongHao 创建方法
     */
    Map<String, Object> getWmsInveTransaInfoById(String wms_inve_transa_id);

    /**
     * 
     * @Title: getWmsInveTransaByIds
     * @Description: 根据上单表主键单据集合进行获取上单单据信息
     * @param ids 主键集合
     * @return 返回对应的上单单据数据信息
     * @author: DongHao
     * @time:2017年9月20日 下午1:09:56
     * history:
     * 1、2017年9月20日 DongHao 创建方法
     */
    List<Map<String, Object>> getWmsInveTransaByIds(List<String> ids);

    /**
     * 
     * @Title: getCustomerByParamsMap
     * @Description: 根据指定条件获取对应的单据对应的客户
     * @param paramsMap 查询条件参数
     * @return 返回单据对应的客户信息数据集合
     * @author: DongHao
     * @time:2017年9月2日 上午9:37:02
     * history:
     * 1、2017年9月2日 DongHao 创建方法
     */
    List<WmsInveTransa> getCustomerByParamsMap(Map<String, Object> paramsMap);

    /**
     * 
     * @Title: getSatisfyConditionCustomer
     * @Description: 根据条件获取指定身份证号满足的单据
     * @param paramsMap 查询条件参数
     * @return 返回满足条件的客户单据集合
     * @author: DongHao
     * @time:2017年9月2日 上午9:51:33
     * history:
     * 1、2017年9月2日 DongHao 创建方法
     */
    List<WmsInveTransa> getSatisfyConditionCustomer(Map<String, Object> paramsMap);

    /**
     * 
     * @Title: getWmsInveTransaInfoByLis
     * @Description: 根据查询条件验证当前条件中的月份是否赎回
     * @param paramsMap 查询条件参数
     * @return 返回记录数
     * @author: DongHao
     * @time:2017年9月2日 上午10:54:11
     * history:
     * 1、2017年9月2日 DongHao 创建方法
     */
    int getWmsInveTransaInfoByLis(Map<String, Object> paramsMap);

    /**
     * 
     * @Title: saveCustomerByNewCategory
     * @Description: 将满足条件的客户添加到数据库表中
     * @param customerList 满足条件的客户
     * @author: DongHao
     * @time:2017年9月2日 上午11:21:03
     * history:
     * 1、2017年9月2日 DongHao 创建方法
     */
    void saveCustomerByNewCategory(List<Map<String, Object>> customerList);


}
