package com.zx.emanage.inve.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveClerkDataSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveClerkData;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveClerkDataService {

    /**
     * 业务类别-签单单据
     */
    public static final String TRANSA = "1";

    /**
     * 业务类别-续期单据
     */
    public static final String EXTEND_TRANSA = "2";

    /**
     * 业务类别-赎回单据
     */
    public static final String REDEEM = "3";

    /**
     * 业务类别-债权匹配
     */
    public static final String CREDIT = "4";

    /**
     * @Title: getClerkDataWithPaging
     * @Description: 分页查询柜员工作台业务数据
     * @param queryInfo 查询条件
     * @param user 登录用户信息
     * @return 柜员工作台业务数据
     * @author: jinzhm
     * @time:2017年2月8日 上午11:03:51
     * history:
     * 1、2017年2月8日 jinzhm 创建方法
     */
    public Map<String, Object> getClerkDataWithPaging(WmsInveClerkDataSearchBeanVO queryInfo, UserBean user);

    /**
     * @Title: getRedeemClerkDataWithPaging
     * @Description: 分页查询合同改签（赎回）单据信息
     * @param queryInfo 查询条件
     * @param user 登录用户信息
     * @return 合同改签单据信息集合
     * @author: jinzhm
     * @time:2017年2月9日 上午11:10:37
     * history:
     * 1、2017年2月9日 jinzhm 创建方法
     */
    public Map<String, Object> getRedeemClerkDataWithPaging(WmsInveClerkDataSearchBeanVO queryInfo, UserBean user);

    /**
     * @Title: getExtendClerkDataWithPaging
     * @Description: 分页查询合同续签单据数据信息
     * @param queryInfo 查询条件
     * @param user 登录用户信息
     * @return 
     * @author: jinzhm
     * @time:2017年2月9日 上午11:51:56
     * history:
     * 1、2017年2月9日 jinzhm 创建方法
     */
    public Map<String, Object> getExtendClerkDataWithPaging(WmsInveClerkDataSearchBeanVO queryInfo, UserBean user);

    /**
     * @Title: reStartSort
     * @Description: 将业务单据重新进入排序
     * @param clerkData 业务单据信息
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年2月9日 上午8:57:14
     * history:
     * 1、2017年2月9日 jinzhm 创建方法
     */
    public void reStartSort(WmsInveClerkData clerkData, UserBean user);

    /**
     * @Title: reOrder
     * @Description: 单据整体重新排序，将排序时间早于设置的时间之前的所有签单单据重新进行排序
     * @param hour 时
     * @param minute 分
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年4月6日 上午9:26:32
     * history:
     * 1、2017年4月6日 jinzhm 创建方法
     */
    public void reOrder(Integer hour, Integer minute, UserBean user);

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveClerkDataSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveClerkDataSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveClerkDataVO
	 * @author auto_generator
	 */	
	public WmsInveClerkData getInfoByPK(Integer wms_inve_clerk_data_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveClerkData bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveClerkData bean, UserBean user);

    /**
     * @Title: getKeepAccount
     * @Description: 获得当天设置的剩余可售额度
     * @param user 登录用户信息
     * @return 剩余可售额度
     * @author: jinzhm
     * @time:2017年4月6日 上午8:08:57
     * history:
     * 1、2017年4月6日 jinzhm 创建方法
     */
    public String getKeepAccount(UserBean user);

    /**
     * @Title: setKeepAccount
     * @Description: 设置当天的剩余可售额度
     * @param user 登录用户信息
     * @param account 可售额度
     * @return 设置成功标志
     * @author: jinzhm
     * @time:2017年4月6日 上午8:13:45
     * history:
     * 1、2017年4月6日 jinzhm 创建方法
     */
    public String setKeepAccount(UserBean user, BigDecimal account);

    /**
     * @Title: getSummaryClerkTransaData
     * @Description: 获得柜员工作台某日的提单统计信息
     * @param data 日期
     * @param user 登录用户信息
     * @return 统计信息
     * @author: jinzhm
     * @time:2017年3月21日 上午11:23:10
     * history:
     * 1、2017年3月21日 jinzhm 创建方法
     */
    public Map<String, Object> getSummaryClerkTransaData(Date date, UserBean user);
}