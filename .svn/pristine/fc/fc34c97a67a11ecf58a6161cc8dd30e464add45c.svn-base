package com.zx.emanage.creditRightManager.service;




import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.emanage.creditRightManager.vo.WmsCredRightNotifyCustomerSearchBeanVo;
import com.zx.emanage.creditRightManager.vo.WmsInveCreditPackageSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveCreditPackage;
import com.zx.emanage.util.gen.vo.WmsInveOfflineCreditImportTemp;
import com.zx.sframe.util.vo.UserBean;


/**
 * @ClassName: IWmsInveCreditPackageService
 * @Description: 债权包查询service
 * @author WangShuai
 * @date 2017年2月8日
 * @version V1.0
 * history:
 * 1、2017年2月8日 WangShuai 创建文件
 */
public interface IWmsInveCreditPackageService {
	
	
	/**
	 * @Title: getListWithPaging
	 * @Description: 债权包列表
	 * @param queryInfo
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月8日 下午1:38:40
	 * history:
	 * 1、2017年2月8日 WangShuai 创建方法
	*/
	public Map<String, Object> getListWithPaging(WmsInveCreditPackageSearchBeanVO queryInfo);

	/**
	 * @Title: getAllLocalNumList
	 * @Description: 获取地区下拉框
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月20日 下午4:42:45
	 * history:
	 * 1、2017年2月20日 WangShuai 创建方法
	*/
	public List<Map<String, Object>> getAllLocalNumList();

	/**
	 * @Title: getAllRelePerList
	 * @Description: 获取他项人下拉框
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月20日 下午4:42:55
	 * history:
	 * 1、2017年2月20日 WangShuai 创建方法
	*/
	public List<Map<String, Object>> getAllRelePerList();

    /**
     * @Title: update
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param bean
     * @param user
     * @return 
     * @author: Guanxu
     * @time:2017年2月21日 下午7:29:23
     * history:
     * 1、2017年2月21日 Guanxu 创建方法
    */
    public String update(WmsInveCreditPackage bean, UserBean user);
	
    /**
     * @Title: getNotifyCustomerForCreditEnd
     * @Description: 查询抵押包到期要通知客户的数据
     * @param searchBeanVo 查询条件
     * @param user 登录用户
     * @return 抵押包到期的客户单据信息
     * @author: jinzhm
     * @time:2017年3月13日 上午10:18:20
     * history:
     * 1、2017年3月13日 jinzhm 创建方法
     */
    public Map<String, Object> getNotifyCustomerForCreditEnd(WmsCredRightNotifyCustomerSearchBeanVo searchBeanVo,
                                                             UserBean user);

    /**
     * @Title: getNotifyCustomerForCreditEndWithoutPaging
     * @Description: 无分页查询抵押包到期的客户单据信息
     * @param request 请求信息
     * @param searchBeanVo 查询条件
     * @return 抵押包到期的客户单据信息集合
     * @author: jinzhm
     * @time:2017年6月28日 下午2:28:52
     * history:
     * 1、2017年6月28日 jinzhm 创建方法
     */
    public List<Map<String, Object>> getNotifyCustomerForCreditEndWithoutPaging(WmsCredRightNotifyCustomerSearchBeanVo searchBeanVo,
                                                                                UserBean user);

    /**
     * @Title: notifyCustomerForCreditEnd
     * @Description: 根据地区发送债权到期提醒
     * @param regionNumber 地区编码
     * @param user 登录用户
     * @author: jinzhm
     * @time:2017年6月8日 下午4:06:53
     * history:
     * 1、2017年6月8日 jinzhm 创建方法
     */
    public Map<String, Object> notifyCustomerForCreditEnd(HttpServletRequest request, HttpServletResponse response,
                                                          UserBean user);

    /**
     * @Title: sendMessageToCustomerForCreditEnd
     * @Description: 发送短信提醒客户债权到期
     * @param data 客户及合同编号信息
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年3月14日 上午10:01:02
     * history:
     * 1、2017年3月14日 jinzhm 创建方法
     */
    public void sendMessageToCustomerForCreditEnd(String data, UserBean user);

    /**
     * @Title: handleOfflineImportCredit
     * @Description: 处理一条线下债权匹配数据
     * @param offlineCredit 线下债权匹配临时数据对象
     * @param wmsInveMulticreInfoId 债权匹配主键
     * @param user 登录用户信息
     * @return 是否使用库中抵押包。使用苦衷抵押包返回true
     * @throws Exception 
     * @author: jinzhm
     * @time:2017年4月11日 上午10:56:46
     * history:
     * 1、2017年4月11日 jinzhm 创建方法
    */
    public int handleOfflineImportCredit(WmsInveOfflineCreditImportTemp offlineCredit, Integer wmsInveMulticreInfoId,
                                   UserBean user) throws Exception;

    /**
     * @Title: destroyCreditPackage
     * @Description: 作废某个抵押包信息
     * @param creditPackageId 抵押包id
     * @param reason 作废原因
     * @param user 登录用户信息
     * @return 成功或错误标志信息
     * @author: jinzhm
     * @time:2017年3月14日 下午3:50:22
     * history:
     * 1、2017年3月14日 jinzhm 创建方法
     */
    public String destroyCreditPackage(int creditPackageId, String reason, UserBean user);

    /**
     * @Title: getCompanyRemainCreditPackage
     * @Description: 查询全集团剩余债权额度和今日到期抵押包额度
     * @author: zhangyunfei
     * @return 
     * @time:2017年4月5日 下午4:34:51
     * history:
     * 1、2017年4月5日 Administrator 创建方法
    */
    public List<Map<String, Object>> getCompanyRemainCreditPackage();

    /**
     * @Title: getGroupRemainCreditPackage
     * @Description: 查询各组剩余债权额度和已售额度
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月19日 下午2:54:29
     * history:
     * 1、2017年4月19日 Administrator 创建方法
    */
    public Map<String, Object> getGroupRemainCreditPackage();

    /**
     * @Title: getRegionRemainCreditPackage
     * @Description: 查询 各地区销售限额、已售额度、剩余债权额度
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月7日 下午4:36:11
     * history:
     * 1、2017年4月7日 Administrator 创建方法
    */
    public Map<String, Object> getRegionRemainCreditPackage();

    /**
     * @Title: getAllGroupSaleAmountInfo
     * @Description:  实时债权匹配情况-----查询 全集团销售情况 线上、线下(全新支付/赎回再签)
     * @return 
     * @author: zhangyunfei
     * @time:2017年5月10日 上午9:52:35
     * history:
     * 1、2017年5月10日 Administrator 创建方法
    */
    public Map<String, Object> getAllGroupSaleAmountInfo();
}