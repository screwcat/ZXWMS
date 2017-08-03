package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInveCommionRecordCoeff;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveCommionRecordCoeffDao extends BaseDao<WmsInveCommionRecordCoeff> {
	
	    /**
     * 根据年月查询特称系数修正表中的数据。无分页。
     * @param paramMap 年月 格式（2016-09）
     * @return
     */
    public List<Map<String, Object>> getCommionOfMonthListWithoutPaingByMonth(Map<String, Object> paramMap);
	
	/**
	 * 根据代办事项中的月份查找提成系数审核清空信息
     * @param month 查询条件年月 格式（2016-09）
     * @author zhangyunfei
     */
    public List<Map<String, Object>> getCommionListWithoutPaingByMonth(Map<String, Object> paramMap);
	
    /**
     * 佣金提成系数在财务审核结束后重新计算新佣金业绩佣金统计信息
     * @param params
     * @return
     */
    public int updateCommissionPerformanceWithVerifyAdjust(Map<String, Object> params);
    
    /**
     * @Title: updateOldCommHisWithVerifyAdjust
     * @Description: 佣金提成系数在财务审核结束后重新计算老佣金业绩佣金统计信息
     * @param params
     * @return 
     * @author: jinzhiming
     * @time:2016年11月8日 下午3:57:12
     * history:
     * 1、2016年11月8日 jinzhiming 创建方法
     */
    public int updateOldCommHisWithVerifyAdjust(Map<String, Object> params);
    
    /**
     * @Title: updateOldCommionRecordWithVerifyAdjust
     * @Description: 佣金提成系数在财务审核结束后重新计算新佣金提成金额
     * @param params
     * @return 
     * @author: jinzhiming
     * @time:2016年11月8日 下午4:03:30
     * history:
     * 1、2016年11月8日 jinzhiming 创建方法
     */
    public int updateOldCommionRecordWithVerifyAdjust(Map<String, Object> params);
    
    /**
     * @Title: updateSpecialOldCommData
     * @Description: 老佣金提成修正，修改特殊数据
     * @param params
     * @return 
     * @author: jinzhm
     * @time:2016年12月3日 下午11:22:56
     * history:
     * 1、2016年12月3日 jinzhm 创建方法
     */
    public int updateSpecialOldCommData(Map<String, Object> params);
    
    /**
     * @Title: updateSpecialCommionRecord
     * @Description: 老佣金提成修正，调整金额
     * @param params
     * @return 
     * @author: jinzhm
     * @time:2016年12月3日 下午11:33:05
     * history:
     * 1、2016年12月3日 jinzhm 创建方法
     */
    public int updateSpecialCommionRecord(Map<String, Object> params);

    /**
     * 佣金提成系数在财务审核结束后重新计算
     * @param params
     * @return
     */
    public int updateCommionRecordWithVerifyAdjust(Map<String, Object> params);

    /**
     * 佣金提成系数在财务审核结束后重新计算佣金系数提成修正表中的修改后提成金额
     * @param params
     * @return
     */
    public int updateCommionRecordCoeffWithVerfyAdjust(Map<String, Object> params);

    /**
     * 
     * @Title: updateCommionRecordWithAdjustAmount
     * @Description: 修改系数的时候首先需要将与股东单据进行关联的record表中的调整金额设置成0,方便重新进行计算
     * @author: DongHao
     * @time:2017年11月1日 上午1:07:00
     * history:
     * 1、2017年11月1日 DongHao 创建方法
     */
    public void updateCommionRecordWithAdjustAmountSetZieo(Map<String, Object> paramsMap);

    /**
     * 
     * @Title: updateCommRecordAdjustAmountReappearCalculation
     * @Description: 重新计算与股东单据关联的人员的调整金额
     * @param paramsMap 
     * @author: DongHao
     * @time:2017年11月1日 上午1:18:07
     * history:
     * 1、2017年11月1日 DongHao 创建方法
     */
    public void updateCommRecordAdjustAmountReappearCalculation(Map<String, Object> paramsMap);
	
}