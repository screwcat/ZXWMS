package com.zx.emanage.creditRightManager.persist;


import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInveCreditPackage;
import com.zx.emanage.util.gen.vo.WmsInveOfflineCreditImportTemp;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * @ClassName: WmsInveCreditPackageDao
 * @Description: 债权包查询Dao
 * @author WangShuai
 * @date 2017年2月8日
 * @version V1.0
 * history:
 * 1、2017年2月8日 WangShuai 创建文件
 */
@MyBatisRepository
public interface WmsInveCreditCRPackageDao{

    int save(WmsInveCreditPackage creditPackage);

	List<Map<String, Object>> search(Map<String, Object> paramMap);

	int findCount(Map<String, Object> paramMap);

	List<Map<String, Object>> getAllLocalNumList();

	List<Map<String, Object>> getAllRelePerList();
	
    /**
     * @Title: getAllNeedNotifyCustomerForCreditEndList
     * @Description: 根据查询条件查询匹配的债权有到期的单据信息
     * @param paramMap 查询条件
     * @return 匹配的债权有到期的单据信息
     * @author: jinzhm
     * @time:2017年3月13日 下午2:39:59
     * history:
     * 1、2017年3月13日 jinzhm 创建方法
     */
    List<Map<String, Object>> getAllNeedNotifyCustomerForCreditEndList(Map<String, Object> paramMap);

    /**
     * @Title: getAllNeedNotifyCustomerForCreditEndListWhitoutPaging
     * @Description: 无分页查询所有客户债权到期需要提醒的单据信息
     * @param paramMap
     * @return 
     * @author: jinzhm
     * @time:2017年6月28日 下午2:14:07
     * history:
     * 1、2017年6月28日 jinzhm 创建方法
     */
    List<Map<String, Object>> getAllNeedNotifyCustomerForCreditEndListWhitoutPaging(Map<String, Object> paramMap);

    /**
     * @Title: getAllNeedNotifyCustomerForCreditEndListCount
     * @Description: 根据查询条件查询匹配的债权有到期的单据信息个数
     * @param paramMap 查询条件
     * @return 匹配的债权有到期的单据信息个数
     * @author: jinzhm
     * @time:2017年3月13日 下午2:40:24
     * history:
     * 1、2017年3月13日 jinzhm 创建方法
     */
    int getAllNeedNotifyCustomerForCreditEndListCount(Map<String, Object> paramMap);

    /**
     * @Title: updateCreditEndNotifyTime
     * @Description: 修改债权到期最后一次提醒客户时间
     * @param paramMap 
     * @author: jinzhm
     * @time:2017年3月14日 上午11:57:09
     * history:
     * 1、2017年3月14日 jinzhm 创建方法
     */
    void updateCreditEndNotifyTime(Map<String, Object> paramMap);

    /**
     * @Title: getCreditPackageByProtocolIdYearNum
     * @Description: 根据抵押包合同编号查找一个抵押包信息
     * @param protocol_id_year_num 抵押包合同编号
     * @return 抵押包信息
     * @author: jinzhm
     * @time:2017年4月7日 下午4:25:04
     * history:
     * 1、2017年4月7日 jinzhm 创建方法
     */
    WmsInveCreditPackage getCreditPackageByProtocolIdYearNum(String protocol_id_year_num);

    /**
     * @Title: queryOfflineCredit
     * @Description: 查询要导入处理的线下债权集合
     * @return 线下债权临时表信息
     * @author: jinzhm
     * @time:2017年4月11日 上午8:59:38
     * history:
     * 1、2017年4月11日 jinzhm 创建方法
     */
    List<WmsInveOfflineCreditImportTemp> queryOfflineCredit();

    /**
     * @Title: updateOfflineCredit
     * @Description: 更新线下债权匹配数据记录
     * @param offlineCredit 线下债权匹配数据对象
     * @return 受影响行数
     * @author: jinzhm
     * @time:2017年4月11日 上午9:47:11
     * history:
     * 1、2017年4月11日 jinzhm 创建方法
     */
    int updateOfflineCredit(WmsInveOfflineCreditImportTemp offlineCredit);
}