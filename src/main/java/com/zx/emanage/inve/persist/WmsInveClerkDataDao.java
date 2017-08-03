package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveClerkProtocolVO;
import com.zx.emanage.util.gen.entity.WmsInveClerkData;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveClerkDataDao
 * 模块名称：柜员业务数据dao
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年2月8日
 * @version V1.0
 * history:
 * 1、2017年2月8日 jinzhm 创建文件
 */
@MyBatisRepository
public interface WmsInveClerkDataDao extends BaseDao<WmsInveClerkData>
{

    /**
     * @Title: updateByTransaId
     * @Description: 根据上单主键修改柜员业务数据
     *  <b>只有签单流程中可以使用</b>
     * @param clerkData 
     * @author: jinzhm
     * @time:2017年3月23日 下午2:22:26
     * history:
     * 1、2017年3月23日 jinzhm 创建方法
     */
    public void updateByTransaId(WmsInveClerkData clerkData);

    /**
     * @Title: findClerkDataCount
     * @Description: 查询柜员业务个数
     * @param paramMap 查询条件
     * @return 柜员业务数据个数
     * @author: jinzhm
     * @time:2017年2月13日 下午1:04:10
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
     */
    public int findClerkDataCount(Map<String, Object> paramMap);

    /**
     * @Title: searchClerkData
     * @Description: 查询柜员业务数据集合
     * @param paramMap 查询条件
     * @return 柜员业务数据集合
     * @author: jinzhm
     * @time:2017年2月13日 下午1:03:46
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
     */
    public List<Map<String, Object>> searchClerkData(Map<String, Object> paramMap);

    /**
     * @Title: searchRedeemClerkData
     * @Description: 查询合同改签列表信息
     * @param paramMap 查询条件
     * @return 合同改签数据信息集合
     * @author: jinzhm
     * @time:2017年2月9日 上午11:48:54
     * history:
     * 1、2017年2月9日 jinzhm 创建方法
     */
    public List<Map<String, Object>> searchRedeemClerkData(Map<String, Object> paramMap);

    /**
     * @Title: findRedeemClerkDataCount
     * @Description: 查询合同改签数据个数
     * @param paramMap 查询条件
     * @return 个数
     * @author: jinzhm
     * @time:2017年2月9日 下午12:49:54
     * history:
     * 1、2017年2月9日 jinzhm 创建方法
     */
    public int findRedeemClerkDataCount(Map<String, Object> paramMap);

    /**
     * @Title: searchExtendClerkData
     * @Description: 查询合同续期列表数据集合
     * @param paramMap 查询条件
     * @return 合同续签数据信息集合
     * @author: jinzhm
     * @time:2017年2月9日 下午12:50:46
     * history:
     * 1、2017年2月9日 jinzhm 创建方法
     */
    public List<Map<String, Object>> searchExtendClerkData(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: searchClerkDataBySaleman
     * @Description:手持端业务人员获取客户的排队情况
     * @param paramMap
     * @return 
     * @author: Guanxu
     * @time:2017年2月16日 下午1:41:40
     * history:
     * 1、2017年2月16日 Guanxu 创建方法
     */
    public List<Map<String, Object>> searchClerkDataBySaleman(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: findClerkDataBySalemanCount
     * @Description: 手持端业务人员获取待办单据条数
     * @param paramMap
     * @return 
     * @author: Guanxu
     * @time:2017年2月16日 下午1:46:12
     * history:
     * 1、2017年2月16日 Guanxu 创建方法
     */
    public int findClerkDataBySalemanCount(Map<String, Object> paramMap);

    /**
     * @Title: findExtendClerkDataCount
     * @Description: 查询合同续签数据个数
     * @param paramMap 查询条件
     * @return 个数
     * @author: jinzhm
     * @time:2017年2月9日 下午12:51:29
     * history:
     * 1、2017年2月9日 jinzhm 创建方法
     */
    public int findExtendClerkDataCount(Map<String, Object> paramMap);

    /**
     * @Title: getWmsInveClerkDataByWmsInveTransaId
     * @Description: 根据上单表主键回去签单信息
     * @param wms_inve_transa_id
     * @return 
     * @author: DongHao
     * @time:2017年2月16日 下午12:48:00
     * history:
     * 1、2017年2月16日 DongHao 创建方法
    */
    public WmsInveClerkData getWmsInveClerkDataByWmsInveTransaId(Integer wms_inve_transa_id);

    /**
     * @Title: updateClerkDataFinished
     * @Description: 为单据续期修改是否完成操作
     * @param paramMap 
     * @author: jinzhm
     * @time:2017年2月17日 上午9:54:56
     * history:
     * 1、2017年2月17日 jinzhm 创建方法
     */
    public void updateClerkDataFinished(Map<String, Object> paramMap);

    /**
     * @Title: getWmsInveClerkDataByWmsInveTransaIdAndDataType
     * @Description: 根据上单表主键查询柜员业务表
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年2月16日 下午12:56:22
     * history:
     * 1、2017年2月16日 DongHao 创建方法
    */
    public WmsInveClerkData getWmsInveClerkDataByWmsInveTransaIdAndDataType(Map<String, Object> paramsMap);

    /**
     * @Title: disableWmsInveClerkData
     * @Description: 失效柜员业务表单据
     * @param wmsInveClerkData 
     * @author: zhangyunfei
     * @time:2017年2月17日 上午9:17:12
     * history:
     * 1、2017年2月17日 Administrator 创建方法
    */
    public void disableWmsInveClerkData(WmsInveClerkData wmsInveClerkData);

    /**
     * @Title: getOrderRedeemClerkProtocol
     * @Description:  查询到达预约赎回日的柜员协议集合
     * @param date
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月17日 下午4:40:53
     * history:
     * 1、2017年2月17日 Administrator 创建方法
    */
    public List<WmsInveClerkProtocolVO> getOrderRedeemClerkProtocol(String date);

    /**
     * @Title: deleteClerkDataByWmsInveTransaIdAndDataType
     * @Description: 根据上单表主键和是否预约续期进行更新enable_flag=0
     * @param data 
     * @author: DongHao
     * @time:2017年2月17日 下午5:46:37
     * history:
     * 1、2017年2月17日 DongHao 创建方法
    */
    public void deleteClerkDataByWmsInveTransaIdAndDataType(WmsInveClerkData data);

    /**
     * @Title: getWmsInveClerkDataByTransaId
     * @Description: 根据上单表主键获取柜员业务表信息
     * @param wms_inve_transa_id
     * @return 
     * @author: DongHao
     * @time:2017年2月28日 上午1:32:40
     * history:
     * 1、2017年2月28日 DongHao 创建方法
    */
    public WmsInveClerkData getWmsInveClerkDataByTransaId(Integer wms_inve_transa_id);

    /**
     * @Title: getSummaryClerkTransaData
     * @Description: 查询柜员工作台提单统计信息
     * @param paramMap 查询条件
     * @return 统计信息
     * @author: jinzhm
     * @time:2017年3月21日 下午2:43:07
     * history:
     * 1、2017年3月21日 jinzhm 创建方法
     */
    public Map<String, Object> getSummaryClerkTransaData(Map<String, Object> paramMap);

    /**
     * @Title: updateTransaProcCode
     * @Description: 更新柜员业务数据中的排队号
     * @param clerkData 需要有地区编号，单据类型及柜员业务主键
     * @author: jinzhm
     * @time:2017年3月30日 下午2:49:40
     * history:
     * 1、2017年3月30日 jinzhm 创建方法
     */
    public void updateTransaProcCode(WmsInveClerkData clerkData);

    /**
     * @Title: getReOrderClerkData
     * @Description: 获取要整体重新排序的柜员业务数据集合
     * @param paramMap 查询条件
     * @return 柜员业务数据集合
     * @author: jinzhm
     * @time:2017年4月6日 上午10:25:19
     * history:
     * 1、2017年4月6日 jinzhm 创建方法
     */
    public List<WmsInveClerkData> getReOrderClerkData(Map<String, Object> paramMap);

    /**
     * @Title: getAllGroupSaleAmountInfo
     * @Description: 实时债权匹配情况-----查询 全集团销售情况 线上、线下(全新支付/赎回再签)
     * @param paramMap
     * @return 
     * @author: zhangyunfei
     * @time:2017年5月10日 上午11:54:07
     * history:
     * 1、2017年5月10日 Administrator 创建方法
    */
    public List<Map<String, Object>> getAllGroupSaleAmountInfo(Map<String, Object> paramMap);
}
