package com.zx.emanage.inve.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveTransaCardSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveTransaSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveVerifyCustomerSignedVo;
import com.zx.emanage.util.gen.entity.CrmCustomerInfo;
import com.zx.emanage.util.gen.entity.WmsInveCustomer;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: IWmsInveSignedApplicationService
 * 模块名称：
 * @Description: 内容摘要：
 * @author DongHao
 * @date 2017年2月5日
 * @version V1.0
 * history:
 * 1、2017年2月5日 DongHao 创建文件
 */
public interface IWmsInveSignedApplicationService
{

    /**
     * @Title: getSignedInfos
     * @Description: 签单申请列表信息查询
     * @param queryInfo 查询条件对象
     * @param user 当前登录用户信息
     * @return 
     * @author: DongHao
     * @time:2017年2月4日 下午5:13:53
     * history:
     * 1、2017年2月4日 DongHao 创建方法
    */
    public Map<String, Object> getSignedInfos(WmsInveTransaCardSearchBeanVO queryInfo, UserBean user);

    /**
     * @Title: getAmountConfirmInfos
     * @Description: 查询支付确认相关数据信息
     * @param queryInfo 查询条件对象
     * @param user
     * @return 
     * @author: DongHao
     * @time:2017年2月6日 下午5:54:30
     * history:
     * 1、2017年2月6日 DongHao 创建方法
    */
    public Map<String, Object> getAmountConfirmInfos(WmsInveTransaCardSearchBeanVO queryInfo, UserBean user);

    /**
     * @Title: getCustomerIncomeCardInfos
     * @Description: 根据客户的用户名和身份证号获取对应的收益卡信息
     * @param cus_name 客户姓名
     * @param id_card 身份证号
     * @return 
     * @author: DongHao
     * @time:2017年2月10日 上午11:26:55
     * history:
     * 1、2017年2月10日 DongHao 创建方法
    */
    public List<Map<String, Object>> getCustomerIncomeCardInfos(String cus_name, String id_card);

    /**
     * @Title: saveSigendApplicationInfo
     * @Description: 保存签单信息
     * @param vo
     * @return 
     * @author: DongHao
     * @time:2017年2月10日 下午5:56:14
     * history:
     * 1、2017年2月10日 DongHao 创建方法
    */
    public String saveSigendApplicationInfo(WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd, String fileArr, String saveFlag,
                                            WmsInveCustomer wmsCustomer, WmsInveTransaSearchBeanVO beanVO, UserBean user);

    // /**
    // * @Title: saveShareHolderSignedApplicationInfo
    // * @Description: 保存股东签单信息
    // * @param transa 签单信息
    // * @param transaProd 签单产品信息
    // * @param customer 客户信息
    // * @param salesman_commission_coefficient 客户经理提点系数
    // * @param dept_manager_commission_coefficient 部门经理提点系数
    // * @param bean 签单查询信息
    // * @param user 登录用户信息
    // * @return 成功或失败标志
    // * @author: jinzhm
    // * @time:2017年6月5日 下午3:13:22
    // * history:
    // * 1、2017年6月5日 jinzhm 创建方法
    // */
    // public String saveShareHolderSignedApplicationInfo(WmsInveTransa transa,
    // WmsInveTransaProd transaProd, WmsInveCustomer customer,
    // BigDecimal salesman_commission_coefficient, BigDecimal
    // dept_manager_commission_coefficient,
    // WmsInveTransaSearchBeanVO bean, UserBean user);
    /**
     * @Title: generatePTPTransa
     * @Description: 生成ptp单据信息
     * @param param 投资信息
     * @return 生成ptp单据后返回ptp和wms的单据对应情况集合
     * @author: jinzhm
     * @time:2017年6月20日 上午10:30:30
     * history:
     * 1、2017年6月20日 jinzhm 创建方法
     */
    public List<Map<String, Object>> generatePTPTransa(String param);
    
    /**
     * @Title: getWmsInveTransaInfosByWmsInveTransaId
     * @Description: 根据上单表主键获取上单信息
     * @param wms_inve_transa_id
     * @return 
     * @author: DongHao
     * @time:2017年2月11日 下午1:11:01
     * history:
     * 1、2017年2月11日 DongHao 创建方法
    */
    public Map<String, Object> getWmsInveTransaInfosByWmsInveTransaId(String wms_inve_transa_id);

    /**
     * @Title: getWmsInveRenYuanInfo
     * @Description: 根据上单表主键获取对应的人员信息
     * @param wms_inve_transa_id
     * @return 
     * @author: DongHao
     * @time:2017年2月13日 下午4:30:33
     * history:
     * 1、2017年2月13日 DongHao 创建方法
    */
    public Map<String, Object> getWmsInveRenYuanInfo(String wms_inve_transa_id);

    /**
     * @Title: getSigendProtocolList
     * @Description: 合同签订信息列表
     * @param queryInfo
     * @param user
     * @return 
     * @author: DongHao
     * @time:2017年2月13日 下午5:52:17
     * history:
     * 1、2017年2月13日 DongHao 创建方法
    */
    public Map<String, Object> getSigendProtocolList(WmsInveTransaCardSearchBeanVO queryInfo, UserBean user);

    /**
     * @Title: withdrawSingle
     * @Description: 撤单
     * @param wms_inve_transa_id
     * @param user
     * @return 
     * @author: DongHao
     * @time:2017年2月13日 下午6:39:29
     * history:
     * 1、2017年2月13日 DongHao 创建方法
    */
    public Map<String, Object> withdrawSingle(String wms_inve_transa_id, String advice, String data_status, UserBean user);

    /**
     * @Title: invalidSingle
     * @Description: 作废
     * @param wms_inve_transa_id
     * @param advice
     * @param user
     * @return 
     * @author: DongHao
     * @time:2017年2月14日 上午9:29:32
     * history:
     * 1、2017年2月14日 DongHao 创建方法
    */
    public Map<String, Object> invalidSingle(String wms_inve_transa_id, String advice, String data_status, UserBean user);

    /**
     * @Title: backSingle
     * @Description: 退回操作流程
     * @param wms_inve_transa_id
     * @param advice
     * @param data_status
     * @param user
     * @return 
     * @author: DongHao
     * @time:2017年2月14日 上午11:13:08
     * history:
     * 1、2017年2月14日 DongHao 创建方法
    */
    public Map<String, Object> backSingle(String wms_inve_transa_id, String advice, String data_status, String taskId, UserBean user);

    /**
     * 
     * @Title: sigendProd
     * @Description: 签订协议时启动流程
     * @param wms_inve_transa_id
     * @param taskId
     * @param user_id
     * @return 
     * @author: DongHao
     * @time:2017年2月14日 下午1:18:05
     * history:
     * 1、2017年2月14日 DongHao 创建方法
     */
    public String sigendProd(String wms_inve_transa_id, String taskId, String user_id);

    /**
     * @Title: findClerkDataBySalemanCount
     * @Description: 获取排队人数
     * @param user
     * @return 
     * @author: DongHao
     * @time:2017年2月27日 下午11:29:22
     * history:
     * 1、2017年2月27日 DongHao 创建方法
    */
    public int findClerkDataBySalemanCount(UserBean user);

    /**
     * @Title: updateContractSigningType
     * @Description: 在金额支付的时候修改实际支付时间小于当前时间则将设置成线下合同
     * @param wms_inve_transa_id
     * @param act_of_date
     * @author: DongHao
     * @time:2017年2月28日 上午1:43:08
     * history:
     * 1、2017年2月28日 DongHao 创建方法
    */
    public boolean updateContractSigningType(String wms_inve_transa_id, String act_of_date, String origCategoryId, String newCategoryId,
                                          BigDecimal productAccount, UserBean user);

    /**
     * @Title: saveSignedInfoPad
     * @Description: pad客户端进行签单
     * @param wmsInveTransa 上单信息对象
     * @param wmsInveTransaProd 上单产品信息对象
     * @param wmsCustomer 客户信息对象
     * @param beanVO 上单查询信息对象
     * @param user 当前登录的客户信息对象
     * @return 返回map集合信息提示
     * @author: DongHao
     * @time:2017年3月3日 下午10:41:55
     * history:
     * 1、2017年3月3日 DongHao 创建方法
    */
    public Map<String, Object> saveSignedInfoPad(WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd, WmsInveCustomer wmsCustomer,
                                                 WmsInveTransaSearchBeanVO beanVO, UserBean user);

    /**
     * @Title: verificationCustomerInfo
     * @Description: 对签单的客户信息进行二次验证
     * @param info 客户信息
     * @param personnel_shortCode 业务员短工号
     * @return 返回map信息
     * @author: DongHao
     * @time:2017年2月23日 下午2:50:35
     * history:
     * 1、2017年2月23日 DongHao 创建方法
    */
    public Map<String, Object> verificationCustomerInfo(CrmCustomerInfo info, String personnel_shortCode);

    /**
     * @Title: creditHandler
     * @Description: 待修订的单据进行债权处理
     * @param wmsInveTransa 上单对象
     * @param wmsInveTransaProd 上单产品对象
     * @param beanVO 上单参数对象
     * @param user 用户对象
     * @return 返回错误处理信息
     * @author: DongHao
     * @throws Exception 
     * @time:2017年2月24日 下午6:08:23
     * history:
     * 1、2017年2月24日 DongHao 创建方法
    */
    public String creditHandler(WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd, WmsInveTransaSearchBeanVO beanVO, UserBean user)
                                                                                                                                                  throws Exception;

    /**
     * @Title: verificationSignedInfo
     * @Description: 验证pad客户端提交的签单信息是否正确
     * @param wmsInveTransa 上单信息
     * @param wmsInveTransaProd 上单产品信息
     * @param wmsCustomer 上单客户信息
     * @param beanVO 上单信息
     * @return 返回Map集合验证结果信息
     * @author: DongHao
     * @time:2017年3月1日 上午10:55:48
     * history:
     * 1、2017年3月1日 DongHao 创建方法
    */
    public Map<String, Object> verificationSignedInfo(WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd, WmsInveCustomer wmsCustomer,
                                                      WmsInveTransaSearchBeanVO beanVO);

    /**
     * 
     * @Title: isVerifyCategory
     * @Description: 验证购买的产品是否满足要求
     * @param wmsInveTransa 上单信息对象
     * @param wmsInveTransaProd 上单产品对象
     * @return 返回错误提示信息
     * @author: DongHao
     * @time:2017年9月20日 下午1:25:47
     * history:
     * 1、2017年9月20日 DongHao 创建方法
     */
    public Map<String, Object> isVerifyCategory(WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd);

    /**
     * 
     * @Title: verifyCustomerIsBuy
     * @Description: 验证客户多购买的产品是否瞒住购买的要求
     * @param vo 客户购买的产品信息对象
     * @return 允许购买返回map集合信息对象
     * @author: DongHao
     * @time:2017年7月18日 下午1:52:30
     * history:
     * 1、2017年7月18日 DongHao 创建方法
     */
    public Map<String, Object> verifyCustomerIsBuy(WmsInveVerifyCustomerSignedVo vo);
}
