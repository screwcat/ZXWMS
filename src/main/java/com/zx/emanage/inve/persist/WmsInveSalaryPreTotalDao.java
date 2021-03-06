package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveSalaryPreTotalSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveSalaryPreTotal;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveSalaryPreTotalDao
 * 模块名称：
 * @Description: 内容摘要：
 * @author DongHao
 * @date 2017年1月7日
 * @version V1.0
 * history:
 * 1、2017年1月7日 DongHao 创建文件
 */
@MyBatisRepository
public interface WmsInveSalaryPreTotalDao extends BaseDao<WmsInveSalaryPreTotal> {

    /**
     * @Title: getTeamLis
     * @Description: 获取所属团队信息
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年1月7日 下午5:20:25
     * history:
     * 1、2017年1月7日 DongHao 创建方法
    */
    List<Map<String, Object>> getTeamLis(Map<String, Object> paramsMap);

    /**
     * @Title: getPerformanceSalarySettingInfos
     * @Description: 获取绩效工资设定信息列表
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年1月7日 下午6:02:51
     * history:
     * 1、2017年1月7日 DongHao 创建方法
    */
    List<Map<String, Object>> getPerformanceSalarySettingInfos(Map<String, Object> paramsMap);

    /**
     * @Title: getPerformanceSalarySettingCount
     * @Description: 获取绩效工资设定信息表统计数量
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年1月7日 下午6:14:19
     * history:
     * 1、2017年1月7日 DongHao 创建方法
    */
    int getPerformanceSalarySettingCount(Map<String, Object> paramsMap);

    /**
     * @Title: getPersonnelByPersonnelId
     * @Description: 根据人员id获取人员信息
     * @param create_user_id
     * @return 
     * @author: DongHao
     * @time:2017年1月8日 下午4:02:54
     * history:
     * 1、2017年1月8日 DongHao 创建方法
    */
    Map<String, Object> getPersonnelByPersonnelId(String create_user_id);

    /**
     * @Title: updateWmsInveSalaryPreTotal
     * @Description: 修改工资设定单据的状态
     * @param paramsMap 
     * @author: DongHao
     * @time:2017年1月8日 下午4:39:13
     * history:
     * 1、2017年1月8日 DongHao 创建方法
    */
    void updateWmsInveSalaryPreTotal(Map<String, Object> paramsMap);


    /**
     * @Title: getPerformanceSalaryModifyCount
     * @Description: 获取待修订的单据的统计数量
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年1月9日 上午9:07:19
     * history:
     * 1、2017年1月9日 DongHao 创建方法
    */
    int getPerformanceSalaryModifyCount(Map<String, Object> paramsMap);

    /**
     * @Title: getPerformanceSalaryAuditInfos
     * @Description: 获取待审核的单据
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年1月9日 上午9:26:02
     * history:
     * 1、2017年1月9日 DongHao 创建方法
    */
    List<Map<String, Object>> getPerformanceSalaryAuditInfos(Map<String, Object> paramsMap);

    /**
     * @Title: getPerformanceSalaryAuditCount
     * @Description: 获取待我审批的统计数量
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年1月9日 上午9:26:46
     * history:
     * 1、2017年1月9日 DongHao 创建方法
    */
    int getPerformanceSalaryAuditCount(Map<String, Object> paramsMap);

    /**
     * @Title: updateBatchWmsInveSalaryPreTotalInfos
     * @Description: 批量更新
     * @param paramsMap 
     * @author: DongHao
     * @time:2017年1月27日 下午12:35:08
     * history:
     * 1、2017年1月27日 DongHao 创建方法
    */
    void updateBatchWmsInveSalaryPreTotalInfos(List<Map<String, Object>> totals);

    /**
     * @Title: getDataStatus
     * @Description: 获取工资设定单据状态
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年1月12日 上午11:12:20
     * history:
     * 1、2017年1月12日 DongHao 创建方法
    */
    List<Map<String, Object>> getDataStatus(Map<String, Object> paramsMap);

    /**
     * @Title: updateWmsInveSalarySetDataStatus
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param wms_inve_salary_pre_total_id 
     * @author: DongHao
     * @time:2016年12月31日 下午12:12:27
     * history:
     * 1、2016年12月31日 DongHao 创建方法
    */
    void updateWmsInveSalarySetDataStatus(Integer wms_inve_salary_pre_total_id);

    /**
     * @Title: getWmsInveJobTimeToEndOfDate
     * @Description: 在wmsinvejobtime获取end_of_date
     * @return 
     * @author: DongHao
     * @time:2017年1月13日 上午8:55:21
     * history:
     * 1、2017年1月13日 DongHao 创建方法
    */
    Map<String, Object> getWmsInveJobTimeToEndOfDate();

    /**
     * @Title: updatePayNumByDeptId
     * @Description: 根据dept_id更新当月开资人数
     * @param wmsInveSalaryPreTotalList 
     * @author: zhangyunfei
     * @time:2017年1月3日 下午3:38:19
     * history:
     * 1、2017年1月3日 Administrator 创建方法
    */
    void updatePayNumByDeptId(List<WmsInveSalaryPreTotal> wmsInveSalaryPreTotalList);

    /**
     * @Title: getGeneralAdvice
     * @Description: 根据绩效工资设定总表获取待修订状态的审批意见
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年1月4日 下午2:55:40
     * history:
     * 1、2017年1月4日 DongHao 创建方法
    */
    Map<String, Object> getGeneralAdvice(Map<String, Object> paramsMap);
    
    /**
     * @Title: getSalaryPendingApproval
     * @Description: 根据查询条件参数进行获取待我审批的工资绩效单据
     * @param paramsMap 查询参数map集合
     * @return 返回绩效工资的单据集合
     * @author: DongHao
     * @time:2017年4月10日 上午2:44:01
     * history:
     * 1、2017年4月10日 DongHao 创建方法
    */
    List<Map<String, Object>> getSalaryPendingApproval(Map<String, Object> paramsMap);
    
    
    /**
     * @Title: getSalaryBusinessInfos
     * @Description: 根据查询参数map进行获取绩效工资的相关业务单据信息
     * @param paramMap 查询参数集合
     * @return 返回绩效工资相关的信息集合
     * @author: DongHao
     * @time:2017年4月10日 上午7:50:35
     * history:
     * 1、2017年4月10日 DongHao 创建方法
    */
    List<Map<String, Object>> getSalaryBusinessInfos(Map<String, Object> paramMap);

    /**
     * @Title: getPerformanceSalaryInfoByIdMoa
     * @Description: 根据主键查询绩效工资审基本信息map
     * @param wms_inve_salary_pre_total_ids 
     * @author: zhangyunfei
     * @time:2017年4月19日 上午11:12:04
     * history:
     * 1、2017年4月19日 Administrator 创建方法
    */
    public Map<String, Object> getPerformanceSalaryInfoByIdMoa(Integer wms_inve_salary_pre_total_id);

    /**
     * @Title: getPerformanceSalaryDetailInfoByIdMoa
     * @Description: 根据主键查询绩效工资详情信息list
     * @return 
     * @author: zhangyunfei
     * @param paramsMap 
     * @time:2017年4月2日 下午3:14:42
     * history:
     * 1、2017年4月2日 Administrator 创建方法
    */
    public List<Map<String, Object>> getPerformanceSalaryDetailInfoByIdMoa(Map<String, Object> paramsMap);

    /**
     * @Title: getWmsInveSalaryPreTotalByIds
     * @Description: 根据主键集合查询绩效工资前提配置总表list
     * @param wms_inve_salary_pre_total_ids
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月24日 下午2:04:40
     * history:
     * 1、2017年4月24日 Administrator 创建方法
    */
    public List<WmsInveSalaryPreTotal> getWmsInveSalaryPreTotalByIds(String wms_inve_salary_pre_total_ids);

    /**
     * @Title: getPerformanceSalaryPreTotalIds
     * @Description: 根据部门id集合和月份查询以逗号分隔好的工资设定主表主键集合
     * @param wmsInveSalaryPreTotalVO
     * @return 主键集合(以逗号分隔)
     * @author: zhangyunfei
     * @time:2017年4月2日 下午5:30:59
     * history:
     * 1、2017年4月2日 Administrator 创建方法
    */
    public String getPerformanceSalaryPreTotalIds(WmsInveSalaryPreTotalSearchBeanVO wmsInveSalaryPreTotalVO);

    /**
     * @Title: getSalaryPreInfos
     * @Description: 根据查询参数获取对应的绩效工资设定单的信息
     * @param paramsMap 查询参数集合
     * @return  返回绩效工资的信息集合
     * @author: DongHao
     * @time:2017年4月1日 上午10:41:14
     * history:
     * 1、2017年4月1日 DongHao 创建方法
    */
    Map<String, Object> getSalaryPreInfos(Map<String, Object> paramsMap);

}