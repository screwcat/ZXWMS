package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.util.credit.CreditData;
import com.zx.emanage.util.gen.entity.WmsInveCreditPackage;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveCreditPackage
 * 模块名称：抵押包信息dao
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年2月11日
 * @version V1.0
 * history:
 * 1、2017年2月11日 jinzhm 创建文件
 */
@MyBatisRepository
public interface WmsInveCreditPackageDao extends BaseDao<WmsInveCreditPackage>
{
    /**
     * @Title: queryAllCreditList
     * @Description: 获得所有抵押包信息（包括分组情况）
     * @param paramMap 查询条件
     * @return 抵押包信息
     * @author: jinzhm
     * @time:2017年2月11日 下午1:19:06
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
     */
    public List<CreditData> queryAllCreditList(Map<String, Object> paramMap);

    /**
     * @Title: queryAllCreditPackageByTransaId
     * @Description: 根据上单主键和合同主键查询某个匹配状态的抵押包信息
     * @param paramMap 查询条件
     * @return 抵押包信息集合
     * @author: jinzhm
     * @time:2017年2月15日 上午8:39:54
     * history:
     * 1、2017年2月15日 jinzhm 创建方法
     */
    public List<WmsInveCreditPackage> queryAllCreditPackageByTransaId(Map<String, Object> paramMap);

    /**
     * @Title: getCompanyRemainCreditPackage
     * @Description: 实时债权匹配情况-----查询 全集团剩余债权额度、今日到期抵押包额度
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月5日 下午4:39:03
     * history:
     * 1、2017年4月5日 Administrator 创建方法
    */
    public List<Map<String, Object>> getCompanyRemainCreditPackage();

    /**
     * @Title: getGroupRemainCreditPackage
     * @Description: 实时债权匹配情况-----查询 各组已售额度、剩余债权额度
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月19日 下午2:56:32
     * history:
     * 1、2017年4月19日 Administrator 创建方法
    */
    public List<Map<String, Object>> getGroupRemainCreditPackage();

    /**
     * @Title: getRegionRemainCreditPackage
     * @Description: 实时债权匹配情况-----查询 各地区今日已售额度、剩余债权额度
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月10日 上午10:21:15
     * history:
     * 1、2017年4月10日 Administrator 创建方法
    */
    public List<Map<String, Object>> getRegionRemainCreditPackage();
}
